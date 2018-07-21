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

package com.pdm.xls;

import com.bc.appbase.App;
import com.bc.appbase.xls.MatchExcelToDatabaseColumnsPrompt;
import com.bc.appbase.xls.impl.MatchExcelToDatabaseColumnsPromptImpl;
import com.bc.appbase.xls.impl.SheetProcessorContextImpl;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Officersdata;
import java.util.function.Predicate;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 3, 2017 10:58:49 AM
 */
public class PdmSheetProcessorContext extends SheetProcessorContextImpl {
    
    public static class PdmMatchExcelToDatabaseColumnsPrompt extends MatchExcelToDatabaseColumnsPromptImpl {
        @Override
        public Predicate<Class> getRecursionFilter() {
            return super.getRecursionFilter().and(this.getCustomFilter());
        }
        @Override
        public Predicate<Class> getTypeFilter() {
            return super.getTypeFilter().and(this.getCustomFilter());
        }
        public Predicate<Class> getCustomFilter() {
            final Predicate<Class> test = (cls) -> {
                if(Airmansdata.class.isAssignableFrom(cls)) {
                    return !Officersdata.class.isAssignableFrom(cls);
                }else if(Officersdata.class.isAssignableFrom(cls)) {
                    return !Airmansdata.class.isAssignableFrom(cls);
                }else{
                    return true;
                }
            };
            return test;
        }
    }

    public PdmSheetProcessorContext(App app) {
        super(app);
    }

    
    @Override
    public MatchExcelToDatabaseColumnsPrompt getMatchExcelToDatabaseColumnsPrompt() {
        return new PdmMatchExcelToDatabaseColumnsPrompt();
    }
}
