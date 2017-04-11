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
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.bc.appcore.parameter.ParameterNotFoundException;
import com.bc.jpa.dao.BuilderForSelect;
import com.bc.jpa.search.SearchResults;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 2:38:14 PM
 */
public class Search implements Action<App, SearchResults> {

    @Override
    public SearchResults execute(App app, Map<String, Object> params) throws TaskExecutionException {
        
        try{

            final String query = (String)params.get("query");
            
            if(query == null || query.trim().isEmpty()) {
                throw new ParameterNotFoundException("query");
            }

            final BuilderForSelect<Officersdata> dao = app
                    .getJpaContext()
                    .getBuilderForSelect(Officersdata.class)
                    .search(query, Officersdata_.course.getName());

            return app.getSearchContext(Officersdata.class).getSearchResults(dao);

        }catch(ParameterException e) {
            
            throw new TaskExecutionException(e);
        }
    }
}
