package com.pdm;

import com.bc.appbase.ui.PopupImpl;
import com.bc.appbase.ui.SearchResultsPanel;
import com.bc.appbase.ui.UILog;
import com.bc.appbase.ui.actions.ActionCommands;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appbase.ui.actions.SetLookAndFeel;
import com.bc.appcore.ResourceContext;
import com.bc.appcore.ResourceContextImpl;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.jpa.SearchContext;
import com.bc.appcore.parameter.ParameterException;
import com.bc.appcore.util.BlockingQueueThreadPoolExecutor;
import com.bc.appcore.util.LoggingConfigManagerImpl;
import com.bc.config.CompositeConfig;
import com.bc.config.Config;
import com.bc.config.ConfigService;
import com.bc.config.SimpleConfigService;
import com.bc.jpa.JpaContext;
import com.bc.jpa.JpaContextImpl;
import com.bc.jpa.search.SearchResults;
import com.bc.jpa.sync.JpaSync;
import com.bc.jpa.sync.SlaveUpdates;
import com.bc.util.JsonFormat;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Officersdata;
import com.pdm.ui.PdmMainFrame;
import com.pdm.ui.PdmUIContext;
import com.pdm.ui.actions.PdmActionCommands;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 13, 2017 9:52:58 PM
 */
public class Personneldatamanager {

    public static final Boolean PRODUCTION_MODE = Boolean.FALSE;
    
    public static void main(String [] args) {
        
        try{
            
            final Logger logger = Logger.getLogger(Personneldatamanager.class.getName());
            
            logger.log(Level.INFO, "Production mode: {0}", PRODUCTION_MODE);
            
            final UILog uiLog = new UILog("Startup Log");
            
            uiLog.show();
            
            uiLog.log("Personnel Data Manager");
            uiLog.log("----------------------");
            uiLog.log("...Initializing");
            
            final String workingDir = Paths.get(System.getProperty("user.home"), FileNames.ROOT).toString();
            final String defaultLoggingConfigFile = "META-INF/properties/logging.properties";
            final String defaultPropsFile = "META-INF/properties/app.properties";
            final String loggingConfigFile;
            final String propsFile;
            if(PRODUCTION_MODE) {
                loggingConfigFile = Paths.get(workingDir, FileNames.CONFIGS, "logging.properties").toString();
                propsFile = Paths.get(workingDir, FileNames.CONFIGS, "app.properties").toString();
            }else{
                loggingConfigFile = "META-INF/properties/logging_devmode.properties";
                propsFile = "META-INF/properties/app_devmode.properties";
            }
            
            uiLog.log("Initializing folders");
            
            final String [] dirsToCreate = new String[]{
                    Paths.get(workingDir, FileNames.LOGS).toString()
//                    ,Paths.get(workingDir, FileNames.SLAVE_UPDATES_DIR).toString()
            };
            final String [] filesToCreate = new String[]{propsFile, loggingConfigFile};
            final ResourceContext fileManager = new ResourceContextImpl(dirsToCreate, filesToCreate);
            
            new LoggingConfigManagerImpl(fileManager).init(defaultLoggingConfigFile, loggingConfigFile, null);
            
            uiLog.log("Loading configurations");
            
            final ConfigService configService = new SimpleConfigService(
                    defaultPropsFile, propsFile);

            final Config config = new CompositeConfig(configService);
            
            if(logger.isLoggable(Level.FINER)) {
                final StringBuilder json = new StringBuilder();
                new JsonFormat(true, true, "  ").appendJSONString(config.getProperties(), json);
                logger.log(Level.FINER, "PROPERTIES:\n{0}", json);
            }
            
            uiLog.log("Setting Look and Feel");
            
            new SetLookAndFeel().execute(null, 
                    Collections.singletonMap(ParamNames.LOOK_AND_FEEL_NAME, 
                            config.getString(ConfigNames.LOOK_AND_FEEL)));
            
            final boolean WAS_INSTALLED = config.getBoolean(ConfigNames.INSTALLED);
            
            uiLog.log("Initializing database");
        
            final String persistenceFile = config.getString(ConfigNames.PERSISTENCE_FILE);
            logger.log(Level.INFO, "Peristence file: {0}", persistenceFile);
            final URI persistenceURI = fileManager.getResource(persistenceFile).toURI();
            final JpaContext jpaContext = new JpaContextImpl(persistenceURI, null);
            jpaContext.getBuilderForSelect(Airmansdata.class).getResultsAndClose();

            final ExecutorService updateOutputService = 
                    new BlockingQueueThreadPoolExecutor("Service_for_writing_excel_data_to_disk_ThreadFactory", 1, 1, 1);

            final SlaveUpdates slaveUpdates = SlaveUpdates.NO_OP;

            final JpaSync jpaSync = JpaSync.NO_OP;
            
            uiLog.log("Creating application context");
            
            final Properties settingsMetaData = new Properties();
            try(Reader reader = new InputStreamReader(fileManager.getResourceAsStream("META-INF/properties/settings.properties"))) {
                settingsMetaData.load(reader);
            }
            
            final PdmAppImpl app = new PdmAppImpl(
                    Paths.get(workingDir), configService, config, settingsMetaData, jpaContext,
                    updateOutputService, slaveUpdates, jpaSync
            );
            
            uiLog.log("Creating User Interface");
            
            /* Create and display the UIContext */
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try{
                        
                        app.init();
                        
                        uiLog.log("Configuring User Interface");
                        
                        final PdmUIContext uiContext = app.getUIContext();
                        
                        final PdmMainFrame mainFrame = uiContext.getMainFrame();
                        final SearchResultsPanel resultsPanel = mainFrame.getSearchResultsPanel();
                        
                        resultsPanel.getAddButton().setActionCommand(PdmActionCommands.DISPLAY_ADD_OFFICERDATA_UI);
                        uiContext.addActionListeners(resultsPanel, resultsPanel.getAddButton());
                        
                        mainFrame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                try{
                                    app.getAction(ActionCommands.EXIT_UI_THEN_EXIT).execute(app, Collections.EMPTY_MAP);
                                }catch(RuntimeException | ParameterException | TaskExecutionException exception ) {
                                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Unexpected error", exception);
                                    System.exit(0);
                                }
                            }
                        });
                        
                        uiLog.log("Loading search results");
                        
                        final Class<Officersdata> entityType = Officersdata.class;
                        app.getAttributes().put(ParamNames.RESULT_TYPE, entityType);

                        final SearchContext<Officersdata> searchContext = app.getSearchContext(entityType);
                        
                        final SearchResults<Officersdata> searchResults = searchContext.getSearchResults();
                        
                        uiContext.positionFullScreen(mainFrame);
                        
                        uiContext.loadSearchResultsUI(resultsPanel, searchContext, 
                                searchResults, "AppMainFrame", 0, 1, true);

                        mainFrame.pack();
                        
                        uiLog.log("Displaying User Interface");
                        
                        mainFrame.setVisible(true);
                        
                        app.getConfig().setBoolean(ConfigNames.INSTALLED, true);
                        
                        app.getConfigService().store();
                        
                        logger.log(Level.FINE, "Was installed: {0}, now installed: {1}",
                                new Object[]{WAS_INSTALLED, app.getConfig().getBoolean(ConfigNames.INSTALLED)});
                        
                        uiLog.log(WAS_INSTALLED ? "App Launch Successful" : "Installation Successful");
                        
                        if(!WAS_INSTALLED) {
                            
                            uiLog.querySaveLogThenSave();
                        }
                        
                        Runtime.getRuntime().addShutdownHook(new Thread("App_ShutdownHook_Thread") {
                            @Override
                            public void run() {
                                if(!app.isShutdown()) {
                                    app.shutdown();
                                }
                            }
                        });
                    }catch(Throwable t) {
                        
                        uiLog.log("Error");
                        uiLog.log(t);
                        
                        showErrorMessageAndExit(t);
                        
                    }finally{
                        
                        uiLog.dispose();
                    }
                }
            });
        }catch(Throwable t) {
            
            showErrorMessageAndExit(t);
        }
    }
    
    private static void showErrorMessageAndExit(Throwable t) {
        showErrorMessageAndExit(t, "Failed to start application", 0);
    }
    
    private static void showErrorMessageAndExit(Throwable t, String description, int exitCode) {
        
        Logger.getLogger(Personneldatamanager.class.getName()).log(Level.SEVERE, description, t);
        
        new PopupImpl(null).showErrorMessage(t, description);

        System.exit(exitCode);
    }
}
