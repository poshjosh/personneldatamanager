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

import com.bc.jpa.JpaContext;
import com.bc.jpa.JpaContextImpl;
import com.bc.jpa.JpaMetaData;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Chinomso Bassey Ikwuagwu on May 19, 2017 4:40:03 PM
 */
public class EntityRelationTest {

    private final JpaContext jpaContext;

    public EntityRelationTest() throws URISyntaxException {
        final URL persistenceURL = Thread.currentThread().getContextClassLoader().getResource("META-INF/persistence.xml");
        final URI persistenceURI = persistenceURL.toURI();
        this.jpaContext = new JpaContextImpl(persistenceURI, null);
    }
    
    public static void main(String [] args) {
        try{
            final EntityRelationTest test = new EntityRelationTest();
            final Set<Class> types = test.getEntityClasses();
            for(Class type : types) {
                final Map<Class, String> refTypes = test.jpaContext.getMetaData().getReferenceTypes(type);
                final Map<String, String> refTypeNames = test.format(refTypes);
System.out.println("  Reference types of "+type.getSimpleName() + ":\t" + refTypeNames);    
                final Map<Class, String> refingTypes = test.jpaContext.getMetaData().getReferencing(type);
                final Map<String, String> refingTypeNames = test.format(refingTypes);
System.out.println("Referencing types of "+type.getSimpleName() + ":\t" + refingTypeNames);                
            }
        }catch(Throwable t) {
            t.printStackTrace();
        }
    }
    
    public Map<String, String> format(Map<Class, String> input) {
        final Map<String, String> output = new LinkedHashMap(input.size());
        input.keySet().stream().forEach((key) -> output.put(key.getSimpleName(), input.get(key)));
        input.forEach((key, val) -> output.put(key.getSimpleName(), val));
        return output;
    }
    
    public Set<Class> getEntityClasses() {
        final JpaMetaData metaData = this.jpaContext.getMetaData();
        return metaData.getEntityClasses(new HashSet(Arrays.asList(metaData.getPersistenceUnitNames())));
    }
}
