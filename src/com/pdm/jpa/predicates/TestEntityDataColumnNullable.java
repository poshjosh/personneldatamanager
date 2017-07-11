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

package com.pdm.jpa.predicates;

import com.bc.jpa.JpaContext;
import com.bc.jpa.JpaMetaData;
import java.sql.ResultSetMetaData;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 27, 2017 1:50:04 AM
 */
public class TestEntityDataColumnNullable implements Predicate<Map> {

    private final JpaContext jpaContext;
    
    private final Class entityType;

    public TestEntityDataColumnNullable(JpaContext jpaContext, Class entityType) {
        this.jpaContext = jpaContext;
        this.entityType = entityType;
    }

    @Override
    public boolean test(Map data) {
        final boolean output;
        if(data  == null || data.isEmpty()) {
            output = false;
        }else{
            boolean accept = true;
            final JpaMetaData metaData = jpaContext.getMetaData();
            final String idColumnName = metaData.getIdColumnName(entityType);
            final int [] nullables = metaData.getColumnNullables(entityType);
            final String [] cols = metaData.getColumnNames(entityType);
            for(String col : cols) {
                if(idColumnName.equals(col)) {
                    continue;
                }
                final Object val = data.get(col);
                if(val == null) {
                    final int index = metaData.getColumnIndex(entityType, col);
                    if(nullables[index] == ResultSetMetaData.columnNoNulls) {
                        accept = false;
                        break;
                    }
                }
            }
            output = accept;
        }
        return output;
    }
}
