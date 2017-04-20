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

package com.pdm.jpa;

import com.bc.appbase.App;
import com.bc.appcore.predicates.Equals;
import com.bc.util.MapBuilder;
import com.pdm.PdmApp;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import java.util.HashMap;
import java.util.function.Predicate;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 11, 2017 7:56:05 PM
 */
public class OfficersdataMap extends HashMap {

    public OfficersdataMap(App app) {
        
        final Officersdata officersdata = new Officersdata();
        final Personneldata personneldata = new Personneldata();
        personneldata.setOfficersdata(officersdata);
        officersdata.setPersonneldata(personneldata);
        
        final Predicate<String> columnNameTest = new Equals("airmansdata").negate();
        
        app.get(MapBuilder.class)
                .methodFilter(new MethodFilterImpl((PdmApp)app, columnNameTest))
                .source(officersdata)
                .target(OfficersdataMap.this)
                .build();
   }
}
