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
import com.bc.appcore.predicates.MethodHasReturnType;
import com.bc.util.MapBuilder;
import com.pdm.PdmApp;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 9, 2017 9:05:24 PM
 */
public class MethodFilterImpl implements MapBuilder.MethodFilter {

    private final PdmApp app;
    private final Predicate<String> columnNameTest;
    private final Predicate<Method> methodHasCollectionReturnType;

    public MethodFilterImpl(PdmApp app) {
        this(app, new AcceptAll());
    }
    
    public MethodFilterImpl(PdmApp app, Predicate<String> columnnameTest) {
        this.app = Objects.requireNonNull(app);
        this.columnNameTest = Objects.requireNonNull(columnnameTest);
        this.methodHasCollectionReturnType = new MethodHasReturnType(Collection.class);
    }

    @Override
    public boolean accept(Method method, String columnName) {
        final String idColumnName = app.getJpaContext().getMetaData().getIdColumnName(method.getDeclaringClass());
        return columnNameTest.test(columnName) && !idColumnName.equals(columnName) && !methodHasCollectionReturnType.test(method);
    }
}
