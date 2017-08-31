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

import com.pdm.pu.entities.Officersdata;
import java.util.Map;
import com.bc.appbase.App;
import com.bc.appbase.ui.actions.CreateAndUpdateEntityFromUI;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.exceptions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import java.util.HashMap;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 4:08:54 PM
 */
public class AddOfficersdataFromUI extends CreateAndUpdateEntityFromUI<Object> {

    public AddOfficersdataFromUI() { }

    @Override
    public Object execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        params = new HashMap(params);
        params.put(ParamNames.ENTITY_TYPE, Officersdata.class);
        
        return super.execute(app, params); 
    }
}
