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

import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Airmansdata_;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 3:56:17 PM
 */
public class SelectedIdsList extends HashMap<Class, List> {

    public SelectedIdsList(Map params) {
        super(4, 0.75f);
        final Class entityType;
        List idsList = (List)params.get(Officersdata_.officersdataid.getName()+"List");
        if(idsList == null) {
            entityType = Airmansdata.class;
            idsList = (List)params.get(Airmansdata_.airmansdataid.getName()+"List"); 
        }else{
            entityType = Officersdata.class;
        }
        super.put(entityType, idsList);
    }

    @Override
    public void replaceAll(BiFunction function) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List replace(Class key, List value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean replace(Class key, List oldValue, List newValue) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List putIfAbsent(Class key, List value) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void putAll(Map m) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public List put(Class key, List value) {
        throw new UnsupportedOperationException("Not supported");
    }
}
