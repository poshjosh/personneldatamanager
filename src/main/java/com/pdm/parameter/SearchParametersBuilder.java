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

package com.pdm.parameter;

import com.bc.appcore.parameter.ParameterException;
import com.bc.appbase.parameter.ParametersBuilder;
import com.pdm.ui.SearchPanel;
import java.util.HashMap;
import java.util.Map;
import com.bc.appcore.AppCore;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 2:34:47 PM
 */
public class SearchParametersBuilder implements ParametersBuilder<SearchPanel> {
    
    public static final String CLOSED_TASKS = "closedtasks";
    public static final String DEADLINE_FROM = "deadlineFrom";
    public static final String DEADLINE_TO = "deadlineTo";
    

    private AppCore app;
    
    private SearchPanel searchPanel;
    
    @Override
    public ParametersBuilder<SearchPanel> context(AppCore app) {
        this.app = app;
        return this;
    }

    @Override
    public ParametersBuilder<SearchPanel> with(SearchPanel SearchPanel) {
        this.searchPanel = SearchPanel;
        return this;
    }
    
    @Override
    public Map<String, Object> build() throws ParameterException {
        
//        final Logger logger = Logger.getLogger(this.getClass().getName());
        
        final Map<String, Object> params = new HashMap();
        
        final String text = searchPanel.getSearchTextField().getText();
        
        if(!this.isNullOrEmpty(text)) {
            params.put("query", text);
        }else{
            throw new ParameterException("You did not enter any word(s) to search for");
        }
        
        return params;
    }

    private boolean isNullOrEmpty(Object text) {
        return text == null || "".equals(text);
    }
}

