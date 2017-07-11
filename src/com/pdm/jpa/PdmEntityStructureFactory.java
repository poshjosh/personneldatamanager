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
import com.bc.appbase.jpa.EntityStructureFactoryImpl;
import com.pdm.pu.entities.Courseattended;
import com.pdm.pu.entities.Officersdata_;
import com.pdm.pu.entities.Personneldata_;
import com.pdm.pu.entities.Personnelposting;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on May 23, 2017 2:54:42 PM
 */
public class PdmEntityStructureFactory extends EntityStructureFactoryImpl {

    public PdmEntityStructureFactory(App app) {
        super(app);
    }

    @Override
    public Map format(Object entity, Map map) {
        
        map = super.format(entity, map);
        
        final Map personneldataMap = ((Map)map.get(Officersdata_.personneldata.getName()));
        
        if(personneldataMap != null) {
            personneldataMap.remove(Personneldata_.airmansdata.getName());
            personneldataMap.remove(Personneldata_.officersdata.getName());        
        }
        
        if(entity instanceof Courseattended || entity instanceof Personnelposting) {
            map.remove(Officersdata_.personneldata.getName());
        }
        
        return map;
    }
}
