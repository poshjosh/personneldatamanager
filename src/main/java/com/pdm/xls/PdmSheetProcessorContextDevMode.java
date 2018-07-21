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
import com.bc.appbase.ui.builder.MatchEntries;
import com.bc.appbase.xls.MatchExcelToDatabaseColumnsPrompt;
import com.bc.appbase.xls.impl.TextCellSpliter;
import com.pdm.ui.builder.PdmMatchEntries;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import javax.swing.JCheckBox;
import jxl.Cell;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 5, 2017 9:44:48 PM
 */
public class PdmSheetProcessorContextDevMode extends PdmSheetProcessorContext{

    private final App app;
    
    public PdmSheetProcessorContextDevMode(App app) {
        super(app);
        this.app = Objects.requireNonNull(app);
    }

    @Override
    public MatchExcelToDatabaseColumnsPrompt getMatchExcelToDatabaseColumnsPrompt() {
        
        this.app.registerDefault(MatchEntries.class, () -> new PdmMatchEntries());
        
        return new PdmMatchExcelToDatabaseColumnsPrompt() {
            @Override
            public Function<Cell, List<Cell>> getSpliter(int excelColIndex, 
                    Object key, Object val, JCheckBox checkBox, Function<Cell, List<Cell>> outputIfNone) {
                if(val instanceof Collection) {
//System.out.println("Excel column: "+excelColIndex+'='+excelCol+", has text spliter with SPACE as separator. @"+this.getClass());                
                    return new TextCellSpliter("\\s{1,}");
                }else if("Cses Attended".equals(key)){
//System.out.println("Excel column: "+excelColIndex+'='+excelCol+", has text spliter with COMMA as separator. @"+this.getClass());                    
                    return new TextCellSpliter(",\\s{0,}");
                }
                return outputIfNone;
            }
        };
    }

}
