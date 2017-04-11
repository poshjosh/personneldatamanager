/*
 * Copyright 2017 NUROX Ltd.
 *
 * Licensed under the NUROX Ltd Software License (the "License");
 * you may not use this file except in compliance source the License.
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

package com.pdm.ui.actions;

import com.bc.appbase.App;
import com.bc.appbase.ui.SearchResultsPanel;
import com.bc.appbase.ui.builder.UIBuilder;
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.jpa.dao.Dao;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import com.pdm.PdmApp;
import com.pdm.util.MapFromEntityBuilder;
import java.awt.Container;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 3:51:43 PM
 */
public class DisplayRecords implements Action<App,Boolean> {
    
    @Override
    public Boolean execute(final App app, final Map<String, Object> params) throws TaskExecutionException {
        
        final Class entityType = (Class)params.get("entityType");
        final String idColumnName = app.getJpaContext().getMetaData().getIdColumnName(entityType);
        final List idsList = (List)params.get(idColumnName+"List");
        
        final SearchResultsPanel resultsPanel = (SearchResultsPanel)params.get(SearchResultsPanel.class.getName());
        
        try(final Dao dao = app.getDao()) {
            
            for(Object id : idsList) {
                
                this.execute(app, dao.find(entityType, id), resultsPanel);
            }        
        }
        
        return Boolean.TRUE;
    }
        
    public Container execute(final App app, Object entity, SearchResultsPanel resultsPanel) throws TaskExecutionException {
        
        final Map map = new MapFromEntityBuilder((PdmApp)app)
                .source(entity)
                .build();
        
        final UIBuilder<Map, Container> uib = app.get(UIBuilder.class);

        final Container ui = uib.source(map).build();
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(ui));
        app.getUIContext().positionHalfScreenRight(frame);
        frame.pack();
        frame.setVisible(true);
        
        return ui;
    }
    
    public Object execute_old(final App app, Object entity, SearchResultsPanel resultsPanel) throws TaskExecutionException {
        
//        final String html = app.getHtmlBuilder(entity.getClass()).source(entity).build();

//        final EditorPaneFrame frame = ((DtbApp)app).getUIContext().createEditorPaneFrame(resultsPanel);

        final JFrame frame = new JFrame();

        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {

                try{
                    
                    app.getUIContext().positionHalfScreenRight(frame);
                    
//                    frame.getEditorPane().setContentType("text/html");
//                    frame.getEditorPane().setText(html);

                    frame.pack();
                    frame.setVisible(true);
                    
                }catch(RuntimeException e) {
                    
                    final String msg = "Error displaying task details";
                    
                    Logger.getLogger(this.getClass().getName()).log(Level.WARNING, msg, e);
                    
                    app.getUIContext().showErrorMessage(e, msg);
                }
            }
        });
        
        return null;
    }
}
