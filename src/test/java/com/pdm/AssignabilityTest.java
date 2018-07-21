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

package com.pdm;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Chinomso Bassey Ikwuagwu on May 25, 2017 8:10:29 PM
 */
public class AssignabilityTest {

    public static void main(String [] args) {
        
        final Collection col = new ArrayList();

        final Class cls = col.getClass();
System.out.println("Class: "+cls);
System.out.println("x: "+Collection.class.isAssignableFrom(cls));
System.out.println("Superclass: "+cls.getSuperclass());
System.out.println("Generic superclass: "+cls.getGenericSuperclass());
        final Class [] ixs = cls.getInterfaces();
        for(Class ix : ixs) {
System.out.println("Interface: "+ix);                
        }
        final Type [] txs = cls.getGenericInterfaces();
        for(Type tx : txs) {
System.out.println("Genericinterface: "+tx);                
        }
    }
}
