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

import com.bc.appbase.jpa.EntityStructureFactoryImpl;
import com.bc.util.JsonFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on May 23, 2017 9:56:40 PM
 */
public class EntityStructureFactoryTest {

    public static void main(String [] args) {
        
        final PdmApp app = TestApp.getInstance(true);
        
        final EntityStructureFactoryImpl esf = new EntityStructureFactoryImpl(app);
        
        Map root = new LinkedHashMap();
        final Object key = "Map Key";
        root.put("0.Boolean", Boolean.TRUE);
        root.put("1.Number", 1);
        root.put("2.String", "Some Text");
        root.put("3.Collection", new ArrayList(Arrays.asList(Collections.singletonMap("n", Collections.singletonMap("A", "B")))));
        root.put("4.Collection.empty", Collections.EMPTY_LIST);
        root.put("5.Collection.nested.empty", Collections.singleton(Collections.singletonMap(key, null)));
        root.put("6.Map", Collections.singletonMap(key, "RubiCon"));
        root.put("7.Map.nested", Collections.singletonMap("Nest Map Key", Collections.singletonMap("y", null)));
        
        final Map toAdd = Collections.singletonMap("A", "C");
        final Map.Entry<Object, Collection> entry = esf.getFirstCollectionContainingStructure(root, toAdd, null);
        if(esf.add(entry.getValue(), toAdd)) {
System.out.println(entry.getValue());
            return;
        }
        
        final Map xParent = Collections.singletonMap("n", Collections.singletonMap("A", "B"));
System.out.println("XParent: "+xParent);   
        Map.Entry<Object, Collection> y = esf.getFirstCollectionContainingStructure(root, xParent, null);
System.out.println("XParent collection: "+y.getValue());        
        final Map x = (Map)xParent.get("n");
System.out.println("X: "+x);        
        y = esf.getFirstCollectionContainingStructure(root, x, null);
System.out.println("X collection: "+y.getValue());                
        final Map fmck = esf.getFirstMapContainingKeyWithNonMapValue(root, "Map Key", null);
System.out.println("First Map containing key: "+key+"\nMap: "+fmck);        
        
        final Map.Entry<Object, Collection> fccs = esf.getFirstCollectionContainingStructure(root, fmck, null);
System.out.println("First Collection containing structure: "+fmck.keySet()+"\nCollection: "+fccs); 
System.out.println();
System.out.println("Copied structure: " + esf.copyStructure(root, false));
        
System.out.println("Copied structure nested:\n" + esf.copyStructure(root, true));

        root = esf.removeEmptyStructures(root);
        
        final String str = new JsonFormat(true, true, "  ").toJSONString(root);
System.out.println("After removing empty containers: \n"+str);
    }
}
