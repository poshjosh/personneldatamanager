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
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appbase.ui.builder.UIBuilder;
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.jpa.dao.Dao;
import com.bc.util.JsonFormat;
import com.bc.util.MapBuilder;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import com.pdm.PdmApp;
import com.pdm.jpa.MethodFilterImpl;
import java.awt.Container;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 3:51:43 PM
 */
public class DisplayRecords implements Action<App,Boolean> {

    private static final Logger logger = Logger.getLogger(DisplayRecords.class.getName());
    
    @Override
    public Boolean execute(final App app, final Map<String, Object> params) throws TaskExecutionException {
        
        final Class entityType = (Class)params.get(ParamNames.RESULT_TYPE);
        final String idColumnName = app.getJpaContext().getMetaData().getIdColumnName(entityType);
        final List idsList = (List)params.get(idColumnName+"List");
        
        final SearchResultsPanel resultsPanel = (SearchResultsPanel)params.get(SearchResultsPanel.class.getName());
        
        try(final Dao dao = app.getDao(entityType)) {
            
            for(Object id : idsList) {
                
                this.execute(app, dao.find(entityType, id), resultsPanel);
            }        
        }
        
        return Boolean.TRUE;
    }
        
    public Container execute(final App app, Object entity, SearchResultsPanel resultsPanel) throws TaskExecutionException {
        
        final Map map = app.get(MapBuilder.class)
                .methodFilter(new MethodFilterImpl((PdmApp)app))
                .source(entity)
                .build();
        
        if(logger.isLoggable(Level.FINER)) {
            final StringBuilder appendTo = new StringBuilder();
            new JsonFormat(true, true, "  ").appendJSONString(map, appendTo);
            logger.log(Level.FINER, "Entity: {0}, Map:\n{1}", new Object[]{entity, appendTo});
        }
        
        final UIBuilder<Map, Container> uib = app.get(UIBuilder.class);

        final Container ui = uib.source(map).build();
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new JScrollPane(ui));
//        app.getUIContext().positionHalfScreenRight(frame);
        frame.pack();
        frame.setVisible(true);
        
        return ui;
    }
}
