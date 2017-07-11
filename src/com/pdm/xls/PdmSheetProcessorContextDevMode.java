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
import com.bc.appbase.xls.impl.TextCellSpliter;
import java.awt.Container;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import javax.swing.JCheckBox;
import jxl.Cell;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 5, 2017 9:44:48 PM
 */
public class PdmSheetProcessorContextDevMode extends PdmSheetProcessorContext{

    public PdmSheetProcessorContextDevMode(App app) {
        super(app);
    }

    @Override
    public MatchExcelToDatabaseColumnsPrompt getMatchExcelToDatabaseColumnsPrompt() {
        return new PdmMatchExcelToDatabaseColumnsPrompt() {
            @Override
            public Map getSelectionsFromUI(App app, Container ui, Set excelCols, String noSelectionName) {
                final Map selections = new HashMap();
                selections.put("NAF Numb.", "servicenumber");
                selections.put("Phone", "mobilephonenumber1");
                selections.put("Rank", "rank");
                selections.put("State", "stateoforigin");
                selections.put("Full Name", Arrays.asList("surname", "firstname", "middlename"));
                selections.put("Trade Class", "grade");
                selections.put("Cses Attended", "coursetitle");        
                selections.put("Gender", "gender");        
                selections.put("Unit", "unit");    
                selections.put("AFS", "trade");  
                return selections;
            }
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
