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
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Objects;

/**
 * @author Chinomso Bassey Ikwuagwu on May 20, 2017 1:22:18 AM
 */
public class EntityInsertOrderComparator implements Comparator {

    private final SelectionContext selectionContext;
    
    public EntityInsertOrderComparator(App app) {
        this(app.getOrException(SelectionContext.class));
    }
    public EntityInsertOrderComparator(SelectionContext selectionContext) {
        this.selectionContext = Objects.requireNonNull(selectionContext);
    }

    @Override
    public int compare(Object o1, Object o2) {
        final Class c1 = o1.getClass();
        final Class c2 = o2.getClass();
        if(this.containsType(c1, c2)) {
            return 1;
        }else if(this.containsType(c2, c1)) {
            return -1;
        }else{
            final int n1 = this.getOrder(c1);
            final int n2 = this.getOrder(c2);
            if(n1 == -1 || n2 == -1) {
                return 0;
            }else{
                return Integer.compare(n1, n2);
            }
        }
    }
    
    public boolean containsType(Class a, Class b) {
        final Field [] fields = a.getDeclaredFields();
        for(Field field : fields) {
            Class fieldType = field.getType();
//            if(Collection.class.isAssignableFrom(fieldType)) {
//                fieldType = (Class)reflection.getGenericTypeArguments(field)[0];
//            }
            if(fieldType.isAssignableFrom(b)) {
                return true;
            }
        }
        return false;
    }
    
    public int getOrder(Class type) {
        
        final int order;
        if(selectionContext.isSelectionType(type)) {
            order = 100;
        }else if(type == Personneldata.class) {
            order = 200;
        }else if(type == Airmansdata.class) {
            order = 400;
        }else if(type == Officersdata.class) {
            order = 500;
        }else{
            order = 300;
        } 
//System.out.println(""+order+", "+type);        
        return order;
    }

    public SelectionContext getSelectionContext() {
        return selectionContext;
    }
}
