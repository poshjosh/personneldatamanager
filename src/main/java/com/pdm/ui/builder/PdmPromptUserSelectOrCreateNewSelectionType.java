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

package com.pdm.ui.builder;

import com.bc.appbase.App;
import com.bc.appbase.ui.UIContext;
import com.bc.appbase.ui.builder.PromptUserSelectOrCreateNew;
import com.bc.appbase.ui.builder.impl.PromptUserSelectOrCreateNewSelectionType;
import com.bc.appcore.AppContext;
import com.bc.appcore.ObjectFactory;
import com.pdm.ConfigNames;
import com.pdm.pu.entities.Localgovernmentarea;
import com.pdm.pu.entities.Stateoforigin;
import com.pdm.pu.entities.Stateoforigin_;
import java.util.List;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 5, 2017 7:26:45 PM
 */
public class PdmPromptUserSelectOrCreateNewSelectionType 
        extends PromptUserSelectOrCreateNewSelectionType 
        implements PromptUserSelectOrCreateNew{

    public PdmPromptUserSelectOrCreateNewSelectionType(App app) {
        super(app, app, app.getUIContext());
    }
    
    public PdmPromptUserSelectOrCreateNewSelectionType(
            AppContext context, ObjectFactory objectFactory, UIContext uiContext) {
        super(context, objectFactory, uiContext);
    }
    
    @Override
    public <T> T search(Class valueType, String column, Object value, T outputIfNone) {
        
        if(Localgovernmentarea.class.isAssignableFrom(valueType) && value instanceof Map) {

            final Map map = (Map)value;
            
            final String refCol = Stateoforigin_.stateoforigin.getName();
            
            final Object refVal;
            if((refVal = map.get(refCol)) != null) {
                
                final Stateoforigin stateOfOrigin = super.search(Stateoforigin.class, refCol, refVal, null);
                
                if(stateOfOrigin != null) {
                   
                    final List<Localgovernmentarea> lgaList = stateOfOrigin.getLocalgovernmentareaList();

                    String prefix = this.getContext().getConfig()
                            .getProperty(ConfigNames.DEFAULT_LOCALGOVERNMENTAREA_PREFIX);
                    
                    if(prefix != null) {
                        
                        prefix = prefix.trim().toLowerCase();
                    
                        for(Localgovernmentarea lga : lgaList) {

                            final String sval = lga.getLocalgovernmentarea().trim().toLowerCase();

                            if(sval.startsWith(prefix)) {
                                return (T)lga;
                            }
                        }
                    }
                }
            }
        }
        
        return super.search(valueType, column, value, outputIfNone);
    }
}
