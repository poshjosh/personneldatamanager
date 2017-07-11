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

import com.bc.appcore.predicates.IsSubClass;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Chinomso Bassey Ikwuagwu on May 17, 2017 12:07:01 AM
 */
public class Aaa {
    
    public static void main(String [] args) {
System.out.println(new IsSubClass(Collection.class).test(List.class));
//        final ReflectionUtil reflection = new ReflectionUtil();
        final List<String> list = new ArrayList<>();
        
        final Class<List<String>> listType = (Class<List<String>>)list.getClass();
System.out.println("GenericSuperclass: "+listType.getGenericSuperclass());
System.out.println("TypeParameters: "+(listType.getTypeParameters()==null?null:Arrays.asList(listType.getTypeParameters())));
System.out.println("GenericInterfaces: "+(listType.getGenericInterfaces()==null?null:Arrays.asList(listType.getGenericInterfaces())));
System.out.println("Component type: "+listType.getComponentType());
final TypeVariable tv = listType.getTypeParameters()[0];
//System.out.println("TypeVariable.name: "+tv.getName());
//System.out.println("TypeVariable.typeName: "+tv.getTypeName());
System.out.println("TypeVariable.class: "+tv.getClass());
System.out.println("TypeVariable.genericDeclaration: "+tv.getGenericDeclaration());
GenericDeclaration gd = tv.getGenericDeclaration();
System.out.println("TypeVariable.genericDeclaration.class: " + Arrays.toString(gd.getTypeParameters()));
    }
}
