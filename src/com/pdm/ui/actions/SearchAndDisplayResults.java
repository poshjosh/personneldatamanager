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
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.jpa.SearchContext;
import com.bc.appcore.parameter.ParameterException;
import com.bc.jpa.search.SearchResults;
import com.pdm.pu.entities.Personneldata;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 31, 2017 11:57:53 PM
 */
public class SearchAndDisplayResults implements Action<App, Boolean> {
    
    private static final AtomicInteger UID = new AtomicInteger();

    @Override
    public Boolean execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {

        final Class resultType = Personneldata.class;
        
        params = new HashMap(params);
        params.put(ParamNames.RESULT_TYPE, resultType);
        
        final SearchResults searchResults = (SearchResults)app.getAction(PdmActionCommands.SEARCH).execute(app, params);
        
        final SearchContext searchContext = app.getSearchContext(resultType);
        
        final int numberOfPagesToDisplay = 1;
        
        final String paginationMessage = searchContext.getPaginationMessage(
                searchResults, numberOfPagesToDisplay, true, false);
        
        final SearchResultsFrame frame = app.getUIContext().createSearchResultsFrame(
                searchContext, searchResults, 
                Long.toHexString(System.currentTimeMillis()) + '_' + UID.getAndIncrement(), 
                0, numberOfPagesToDisplay, paginationMessage, true);
        
        app.getUIContext().positionHalfScreenRight(frame);
        
        frame.pack();
        
        frame.setVisible(true);
        
        return Boolean.TRUE;
    }
}
