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
import com.bc.appcore.jpa.SelectionContext;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Course;
import com.pdm.pu.entities.Courseattended;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personnelposting;

/**
 * @author Chinomso Bassey Ikwuagwu on May 29, 2017 9:02:12 PM
 */
public class EntityInsertOrderComparatorImpl extends EntityInsertOrderComparator {

    public EntityInsertOrderComparatorImpl(App app) {
        super(app);
    }
    public EntityInsertOrderComparatorImpl(SelectionContext selectionContext) {
        super(selectionContext);
    }

    @Override
    public int compare(Object o1, Object o2) {
        final int n1 = this.getOrder(o1.getClass());
        final int n2 = this.getOrder(o2.getClass());
        if(n1 == -1 || n2 == -1) {
            return 0;
        }else{
            return Integer.compare(n1, n2);
        }
    }
    
    @Override
    public int getOrder(Class type) {
       
        final int order;
        if(this.getSelectionContext().isSelectionType(type)) {
            order = 100;
        }else if(type == Course.class) {
            order = 200;
        }else if(type == Personneldata.class) {
            order = 300;
        }else if(type == Courseattended.class) { Personnelposting pp; 
            order = 400;
        }else if(type == Personnelposting.class) {
            order = 500;
        }else if(type == Airmansdata.class) {
            order = 600;
        }else if(type == Officersdata.class) {
            order = 700;
        }else{
            order = -1;
        } 
//System.out.println(""+order+", "+type);        
        return order;
    }
}

