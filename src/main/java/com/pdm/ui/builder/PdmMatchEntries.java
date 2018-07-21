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

package com.pdm.ui.builder;

import com.bc.appbase.App;
import com.bc.appbase.ui.components.ComponentModel;
import com.bc.appbase.ui.components.ComponentModelImpl;
import com.bc.appbase.ui.DateFromUIBuilder;
import com.bc.appbase.ui.DateUIUpdater;
import com.bc.appbase.ui.JCheckBoxMenuItemListComboBox;
import com.bc.appbase.ui.builder.impl.MatchEntriesImpl;
import com.bc.appcore.util.Selection;
import com.bc.appcore.util.SelectionValues;
import java.awt.Component;
import java.awt.Container;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Chinomso Bassey Ikwuagwu on Sep 5, 2017 11:05:20 PM
 */
public class PdmMatchEntries extends MatchEntriesImpl {

    public PdmMatchEntries() { }

    @Override
    public Map build() {
        
        final Function<SelectionValues, ComponentModel> componentModelProvider = (selectionValues) -> new ComponentModelImpl(
                selectionValues, 
                this.getObjectFactory().getOrException(DateFromUIBuilder.class), 
                this.getObjectFactory().getOrException(DateUIUpdater.class)){
            @Override
            public Component getSelectionComponent(Class valueType, 
                    String name, Object value, List<Selection> selectionList) {
                final JCheckBoxMenuItemListComboBox checkMenuListCombo = new JCheckBoxMenuItemListComboBox(selectionList);
                return checkMenuListCombo;
            }
        };
        this.componentModelProvider(componentModelProvider);
        
        return super.build(); 
    }

//    @Override
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
}
