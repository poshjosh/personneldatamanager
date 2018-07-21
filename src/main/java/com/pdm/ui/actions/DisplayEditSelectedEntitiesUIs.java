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

import com.bc.appbase.App;
import com.bc.appbase.ui.actions.DisplaySelectedEntitiesUIs;
import com.bc.appbase.ui.actions.ParamNames;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Officersdata;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 17, 2017 8:04:41 AM
 */
public class DisplayEditSelectedEntitiesUIs extends DisplaySelectedEntitiesUIs {

    public DisplayEditSelectedEntitiesUIs() { }

    @Override
    public Container execute(App app, Map<String, Object> params) {
        
        final Class selectedEntityType = (Class)params.get(ParamNames.ENTITY_TYPE);
        
        params = new HashMap(params);
        params.put(ParamNames.TITLE, "Edit Personnel Record");
        params.put(ParamNames.TEXT, " Update ");
        params.put(ParamNames.ACTION_COMMAND, this.getActionCommand(selectedEntityType));
        params.put(ParamNames.EDITABLE, Boolean.TRUE);
        
        return super.execute(app, params); 
    }
    
    public String getActionCommand(Class entityType) {
        
        Objects.requireNonNull(entityType, "May not be null, parameter: "+ParamNames.ENTITY_TYPE);
        
        if(Officersdata.class == entityType) {
            return PdmActionCommands.ADD_OFFICERSDATA;
        }else if(Airmansdata.class == entityType) {
            return PdmActionCommands.ADD_AIRMANSDATA;
        }else{
            throw new IllegalArgumentException(entityType.getName());
        }
    }
}