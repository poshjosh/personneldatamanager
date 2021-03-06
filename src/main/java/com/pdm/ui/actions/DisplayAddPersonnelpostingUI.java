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
import com.bc.appbase.ui.actions.DisplayAddReferenceToSelectedEntitiesUIs;
import com.bc.appbase.ui.actions.ParamNames;
import com.pdm.pu.entities.Personnelposting;
import java.awt.Container;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 29, 2017 9:33:35 AM
 */
public class DisplayAddPersonnelpostingUI extends DisplayAddReferenceToSelectedEntitiesUIs {

    public DisplayAddPersonnelpostingUI() { 
        super(Personnelposting.class);
    }

    @Override
    public Container execute(App app, Map<String, Object> params) {
        
        params = new HashMap(params);
        params.put(ParamNames.TITLE, "Add Personnel Posting/Unit/Appointment");
        params.put(ParamNames.TEXT, " Add ");
        params.put(ParamNames.ACTION_COMMAND, PdmActionCommands.ADD_PERSONNELPOSTING);
        params.put(ParamNames.EDITABLE, Boolean.TRUE);
        
        return super.execute(app, params); 
    }
}