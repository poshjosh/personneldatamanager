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
import com.bc.appbase.xls.SelectFileThenWorksheetPrompt;
import com.bc.appbase.xls.SheetToDatabaseMetaDataBuilder;
import com.bc.appbase.xls.impl.SelectFileThenWorksheetPromptImpl;
import com.bc.appbase.xls.impl.SheetProcessorContextImpl;
import com.bc.appbase.xls.impl.SheetToDatabaseMetaDataBuilderFromUserInputsImpl;
import com.bc.appcore.AppCore;
import com.bc.appcore.exceptions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import java.io.File;
import java.nio.file.Paths;
import jxl.Sheet;
import jxl.Workbook;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 2, 2017 6:26:35 PM
 */
public class SheetToDatabaseContextTestMode extends SheetProcessorContextImpl {

    public SheetToDatabaseContextTestMode(App app) {
        super(app);
    }

    @Override
    public SheetToDatabaseMetaDataBuilder getSheetToDatabaseMetaDataBuilder() {
        return new SheetToDatabaseMetaDataBuilderFromUserInputsImpl(this.getAppContext(), this.getObjectFactory(), this.getUiContext()){
            @Override
            public int[] promptSelectColumns(Sheet sheet, Class entityType, String message, int[] outputIfNone) {
                return outputIfNone;
            }
        };
    }

    @Override
    public SelectFileThenWorksheetPrompt getSelectFileThenWorksheetPrompt() {
        return new SelectFileThenWorksheetPromptImpl(){
            @Override
            public String promptSelectSheetName(AppCore app, Workbook workbook) 
                    throws ParameterException, TaskExecutionException {
                return "AIR SEC LIST";
            }
            @Override
            public File promptSelectExcelFile(AppCore app) 
                    throws ParameterException, TaskExecutionException {
                return Paths.get(System.getProperty("user.home"), "Desktop", "RADAR PER", "combined21apr17.xls").toFile();
            }
        };
    }
}
