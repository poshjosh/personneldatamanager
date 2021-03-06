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
import com.bc.appbase.ui.actions.CreateAndUpdateEntityFromUI;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.exceptions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.pdm.pu.entities.Personnelposting;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 29, 2017 10:14:41 AM
 */
public class AddPersonnelpostingFromUI extends CreateAndUpdateEntityFromUI<Object> {

    public AddPersonnelpostingFromUI() { }

    @Override
    public Object execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        params = new HashMap(params);
        params.put(ParamNames.ENTITY_TYPE, Personnelposting.class);
        
        return super.execute(app, params); 
    }
}
