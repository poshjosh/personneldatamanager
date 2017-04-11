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

package com.pdm.ui.actions;

import com.bc.jpa.dao.Dao;
import com.pdm.pu.entities.Officersdata;
import java.awt.Window;
import java.util.Map;
import java.util.logging.Logger;
import com.bc.appbase.App;
import com.bc.appbase.ui.builder.FromUIBuilder;
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.bc.appcore.parameter.ParameterNotFoundException;
import com.bc.jpa.util.EntityFromMapBuilder;
import com.bc.util.JsonFormat;
import com.pdm.PdmApp;
import com.pdm.util.MapFromEntityBuilder;
import com.pdm.util.OfficersdataMap;
import java.util.Optional;
import java.util.logging.Level;
import javax.swing.JComponent;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 4:08:54 PM
 */
public class AddOfficersdata implements Action<App, Officersdata> {

    private static final Logger logger = Logger.getLogger(AddOfficersdata.class.getName());

    @Override
    public Officersdata execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Level level = Level.FINE;
        
        final Optional optional = params.values().stream().filter((value) -> value instanceof JComponent).findFirst();
        
        if(!optional.isPresent()) {
            throw new ParameterNotFoundException();
        }
        
        final JComponent ui = (JComponent)optional.get();
        
        final Window window = (Window)ui.getTopLevelAncestor();
        
        final Map officersdataMap = new OfficersdataMap((PdmApp)app);
        logger.log(level, "Source: {0}", this.toJsonString(officersdataMap));
        
        final Map update = (Map)app.get(FromUIBuilder.class)
//                    .componentModel(app.get(ComponentModel.class))
                .context(app)
                .filter(FromUIBuilder.Filter.ACCEPT_ALL)
                .ui(ui)
                .source(officersdataMap)
//                .target(new LinkedHashMap())
                .build();
        
        logger.log(level, "Updated source: {0}", this.toJsonString(update));
        
        try(Dao dao = app.getDao()) {
            
            window.setVisible(false);
            
            dao.begin();
            
            final EntityFromMapBuilder.ResultHandler resultHandler = (Map src, Object entity) -> {
                
                logger.log(level, "Persisting: {0}", entity);
                
                dao.persist(entity);
            };
            
            final Officersdata officersdata = (Officersdata)app.get(EntityFromMapBuilder.class)
                    .resultHandler(resultHandler)
                    .source(update).build();
            
            dao.commit();
            
            app.getUIContext().showSuccessMessage("Success");
            
            return officersdata;
            
        }finally{
            
            window.dispose();
        }
    }
    
    public String toJsonString(App app, Object entity) {
        final Map map = new MapFromEntityBuilder((PdmApp)app)
                .source(entity)
                .build();
        return this.toJsonString(map);
    }

    public String toJsonString(Map map) {
        final StringBuilder b = new StringBuilder();
        JsonFormat fmt = new JsonFormat(true, true, "  ");
        fmt.appendJSONString(map, b);
        return b.toString();
    }
}
