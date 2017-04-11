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

package com.pdm.util;

import com.bc.appcore.predicates.AcceptAll;
import com.bc.jpa.util.MapBuilderForEntity;
import com.pdm.PdmApp;
import java.util.function.Predicate;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 9, 2017 8:59:42 PM
 */
public class MapFromEntityBuilder extends MapBuilderForEntity{

    public MapFromEntityBuilder(PdmApp app) {
        this(app, new AcceptAll());
    }
    
    public MapFromEntityBuilder(PdmApp app, Predicate<String> columnNameTest) {
        this.methodFilter(new MethodFilterImpl(app, columnNameTest))
                .nullsAllowed(true)
                .maxCollectionSize(0)
                .maxDepth(2);
    }
}
