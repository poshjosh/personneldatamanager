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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 1, 2017 6:25:22 PM
 */
public class StreamTests {

    public static void main(String [] args) {
        final Map map = new HashMap();
        map.put(0, "Zero");
        map.put(1, null);
        map.put(2, "Two");
        map.put(3, "Three");
        map.put(4, null);
        map.values().removeIf((val) -> val == null);
System.out.println(map);
        map.values().removeIf((val) -> "Three".equals(val));
System.out.println(map);        
    }
}
