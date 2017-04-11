/*
 * Copyright 2017 NUROX Ltd.
 *
 * Licensed under the NUROX Ltd Software License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.looseboxes.com/legal/licenses/software.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pdm;

import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appbase.ui.actions.SetLookAndFeel;
import com.bc.appbase.ui.UILog;
import com.bc.appcore.ResourceContext;
import com.bc.appcore.ResourceContextImpl;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.util.BlockingQueueThreadPoolExecutor;
import com.bc.appcore.util.LoggingConfigManagerImpl;
import com.bc.appcore.util.Settings;
import com.bc.appcore.util.SettingsImpl;
import com.bc.config.CompositeConfig;
import com.bc.config.Config;
import com.bc.config.ConfigService;
import com.bc.config.SimpleConfigService;
import com.bc.jpa.JpaContext;
import com.bc.jpa.JpaContextImpl;
import com.bc.jpa.sync.JpaSync;
import com.bc.jpa.sync.SlaveUpdates;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:21:47 PM
 */
public class TestApp {

    static final boolean PRODUCTION_MODE = false;
    
    public static final PdmAppImpl getInstance() {
        try{
            
            final Logger logger = Logger.getLogger(AppImplTest.class.getName());
            
            logger.log(Level.INFO, "TEST MODE");
            
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
            
            final String [] dirsToCreate = new String[]{
                    Paths.get(workingDir, FileNames.LOGS).toString()
//                    ,Paths.get(workingDir, FileNames.SLAVE_UPDATES_DIR).toString()
            };
            final String [] filesToCreate = new String[]{propsFile, loggingConfigFile};
            final ResourceContext fileManager = new ResourceContextImpl(dirsToCreate, filesToCreate);
            
            new LoggingConfigManagerImpl(fileManager).init(defaultLoggingConfigFile, loggingConfigFile);
            
            final ConfigService configService = new SimpleConfigService(
                    defaultPropsFile, propsFile);

            final Config config = new CompositeConfig(configService);
            
            new SetLookAndFeel().execute(null, 
                    Collections.singletonMap(
                            ParamNames.LOOK_AND_FEEL, 
                            config.getString(ConfigNames.LOOK_AND_FEEL)));
            
            final boolean WAS_INSTALLED = config.getBoolean(ConfigNames.INSTALLED);
            
            final UILog uiLog = new UILog("Startup Log");
            
            uiLog.show();
        
            uiLog.log("...Launching app");
        
            final String persistenceFile = config.getString(ConfigNames.PERSISTENCE_FILE);
            logger.log(Level.INFO, "Peristence file: {0}", persistenceFile);
            final URI peristenceURI = fileManager.getResource(persistenceFile).toURI();
            final JpaContext jpaContext = new JpaContextImpl(peristenceURI, null);

            final ExecutorService updateOutputService = 
                    new BlockingQueueThreadPoolExecutor("Service_for_writing_excel_data_to_disk_ThreadFactory", 1, 1, 1);

            final SlaveUpdates slaveUpdates = SlaveUpdates.NO_OP;

            final JpaSync jpaSync = JpaSync.NO_OP;
            
            uiLog.log("Creating UI");

            final Properties settingsMetaData = new Properties();
            
            try(Reader reader = new InputStreamReader(fileManager.getResourceAsStream("META-INF/properties/settings.properties"))) {
                settingsMetaData.load(reader);
            }
            
            final Settings settings = new SettingsImpl(configService, config, settingsMetaData);
            
            final PdmAppImpl app = new PdmAppImpl(
                    Paths.get(workingDir), configService, config, settings, jpaContext,
                    updateOutputService, slaveUpdates, jpaSync
            );
            
            app.init();
            
            return app;

        }catch(IOException | URISyntaxException | TaskExecutionException t) {
            
            throw new RuntimeException(t);
        }
    }
}

