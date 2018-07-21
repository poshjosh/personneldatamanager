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

package com.pdm.ui.actions;

import com.bc.appbase.App;
import com.bc.appbase.ui.SearchResultsFrame;
import com.bc.appbase.ui.SearchResultsPanel;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.actions.Action;
import com.bc.appcore.exceptions.TaskExecutionException;
import com.bc.appcore.jpa.SearchContext;
import com.bc.appcore.parameter.ParameterException;
import com.bc.jpa.search.SearchResults;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 31, 2017 11:57:53 PM
 */
public class SearchAndDisplayResults implements Action<App, Boolean> {
    
    @Override
    public Boolean execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {

        final Class resultType = (Class)params.get(ParamNames.ENTITY_TYPE);
        
        final SearchResults searchResults = (SearchResults)app.getAction(PdmActionCommands.SEARCH).execute(app, params);
        
        final SearchContext searchContext = app.getSearchContext(resultType);
        
        final SearchResultsFrame frame = new SearchResultsFrame();
        
        frame.init(app, "", false);
        
        frame.loadSearchResults(
                searchContext, searchResults, app.getRandomId(), true);
        
        final SearchResultsPanel panel = frame.getSearchResultsPanel();
        
        panel.getSearchResultsTable().addMouseListener(app.getUIContext().getMouseListener(panel));

        frame.setVisible(true);
        
        return Boolean.TRUE;
    }
}
