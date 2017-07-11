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
import com.bc.appbase.xls.impl.TextCellSpliter;
import com.bc.appcore.util.SingleElementFixedSizeList;
import com.pdm.PdmApp;
import com.pdm.PdmAppLauncher;
import com.pdm.pu.entities.Airmansdata_;
import com.pdm.pu.entities.Course_;
import com.pdm.pu.entities.Gender_;
import com.pdm.pu.entities.Personneldata_;
import com.pdm.pu.entities.Stateoforigin_;
import com.pdm.pu.entities.Unit_;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import jxl.Cell;
import com.bc.appbase.xls.SheetProcessorContext;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import org.junit.Test;
import com.pdm.ui.actions.PdmActionCommands;

/**
 * @author Chinomso Bassey Ikwuagwu on May 9, 2017 1:58:30 PM
 */
public class ImportSheetDataTest {

    @Test
    public void execute() {
        
        final boolean productionMode = false;
        
        final PdmAppLauncher launcher = new PdmAppLauncher(false, productionMode){
            @Override
            public PdmApp launch(String[] args) {
                
                final PdmApp app = super.launch(args);
                
                try{
                    
                    this.waitTillCompletion();
                
                    postLaunch(app);
                    
                }catch(InterruptedException e) {
                    e.printStackTrace();
                }
                
                return app;
            }
        };
        
        try{
            launcher.launch(new String[0]);
        }catch(Throwable t) {
            launcher.showErrorMessageAndExit(t);
        }
    }
    
    public void postLaunch(App app) {
        
        app.registerDefault(SheetProcessorContext.class, () -> new SheetToDatabaseContextTestMode(app));
        
        try{
            app.getAction(PdmActionCommands.IMPORT_SHEET_DATA).execute(app, Collections.EMPTY_MAP);
        }catch(ParameterException | TaskExecutionException e) {
            throw new RuntimeException(e);
        }finally{
            app.deregisterDefault(SheetProcessorContext.class);
        }
    }
    
    public Map<Integer, List<String>> getExcelToDbColumns() {
        
        Map<Integer, List<String>> excelToDbColumns = new LinkedHashMap();
        excelToDbColumns.put(1, Collections.singletonList(Personneldata_.servicenumber.getName()));
        excelToDbColumns.put(2, Collections.singletonList(Personneldata_.rank.getName()));
        excelToDbColumns.put(3, Arrays.asList(Personneldata_.surname.getName(), 
                Personneldata_.firstname.getName(), Personneldata_.middlename.getName()));
        excelToDbColumns.put(4, Collections.singletonList(Airmansdata_.trade.getName()));
        excelToDbColumns.put(5, Collections.singletonList(Airmansdata_.grade.getName()));
        excelToDbColumns.put(6, Collections.singletonList(Unit_.unit.getName()));
        excelToDbColumns.put(7, new SingleElementFixedSizeList(Course_.coursetitle.getName(), 100)); 
        excelToDbColumns.put(8, Collections.singletonList(Personneldata_.mobilephonenumber1.getName()));    
//        excelToDbColumns.put(9, Collections.singletonList(Personneldata_.localgovernmentarea.getName()));
        excelToDbColumns.put(9, Collections.singletonList(Stateoforigin_.stateoforigin.getName()));        
        excelToDbColumns.put(10, Collections.singletonList(Gender_.gender.getName()));
        
        return excelToDbColumns;
    }
    
    public Map<Integer, Function<Cell, List<Cell>>> getCellSpliters() {
        
        final Map<Integer, Function<Cell, List<Cell>>> spliters = new HashMap(4, 0.75f);
        spliters.put(3, new TextCellSpliter("\\s{1,}"));
        spliters.put(7, new TextCellSpliter(",\\s{0,}"));
        
        return spliters;
    }
}
