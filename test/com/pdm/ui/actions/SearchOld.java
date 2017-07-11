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
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.actions.Action;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.bc.appcore.parameter.ParameterNotFoundException;
import com.bc.jpa.dao.BuilderForSelect;
import com.bc.jpa.search.SearchResults;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Airmansdata_;
import com.pdm.pu.entities.Appointment;
import com.pdm.pu.entities.Appointment_;
import com.pdm.pu.entities.Commissiontype;
import com.pdm.pu.entities.Commissiontype_;
import com.pdm.pu.entities.Gender;
import com.pdm.pu.entities.Gender_;
import com.pdm.pu.entities.Grade;
import com.pdm.pu.entities.Grade_;
import com.pdm.pu.entities.Localgovernmentarea;
import com.pdm.pu.entities.Localgovernmentarea_;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personneldata_;
import com.pdm.pu.entities.Personnelposting;
import com.pdm.pu.entities.Personnelposting_;
import com.pdm.pu.entities.Rank;
import com.pdm.pu.entities.Rank_;
import com.pdm.pu.entities.Speciality;
import com.pdm.pu.entities.Speciality_;
import com.pdm.pu.entities.Trade;
import com.pdm.pu.entities.Trade_;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.criteria.JoinType;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 16, 2017 10:41:31 PM
 */
public class SearchOld implements Action<App, SearchResults> {

    private static final Logger logger = Logger.getLogger(SearchOld.class.getName());
    
    @Override
    public SearchResults execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Class resultType = (Class)params.get(ParamNames.ENTITY_TYPE);
        final String query = (String)params.get("query");

        if(query == null || query.trim().isEmpty()) {
            throw new ParameterNotFoundException("query");
        }
        
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Result type: {0}, query: {1}", new Object[]{resultType, query});
        }
        
        final BuilderForSelect<?> dao;
        final String joinColumn;
        
        if(resultType == Officersdata.class) {
            
            final BuilderForSelect<Officersdata> typeDao = app
                    .getJpaContext()
                    .getBuilderForSelect(resultType);
            
            typeDao.distinct(true).from(resultType);
            
            this.searchOfficersdata(typeDao, query);
            
            dao = typeDao;
            joinColumn = Officersdata_.personneldata.getName();
            
        }else if (resultType == Airmansdata.class){
            
            final BuilderForSelect<Airmansdata> typeDao = app
                    .getJpaContext()
                    .getBuilderForSelect(resultType);
            
            typeDao.distinct(true).from(resultType);
            
            this.searchAirmansdata(typeDao, query);
            
            dao = typeDao;
            joinColumn = Airmansdata_.personneldata.getName();
            
        }else{
            
            throw new UnsupportedOperationException("Unexpected result type: "+resultType);
        }
                
        this.searchPersonneldata(dao, resultType, joinColumn, query);
        
        return app.getSearchContext(resultType).getSearchResults(dao);
    }
    
    public void searchOfficersdata(BuilderForSelect<Officersdata> dao, String query) {
        dao.search(Officersdata.class, query, 
                Officersdata_.courseonentry.getName())
        .join(Officersdata.class, Officersdata_.commissiontype.getName(), Commissiontype.class)
        .search(Commissiontype.class, query, 
                Commissiontype_.abbreviation.getName(), Commissiontype_.commissiontype.getName())
        .join(Officersdata.class, Officersdata_.speciality.getName(), Speciality.class)
        .search(Speciality.class, query, 
                Speciality_.abbreviation.getName(), Speciality_.speciality.getName());
    }
    
    public void searchAirmansdata(BuilderForSelect<Airmansdata> dao, String query) {
        dao.join(Airmansdata.class, Airmansdata_.grade.getName(), Grade.class)
        .search(Grade.class, query, Grade_.grade.getName())
        .join(Airmansdata.class, Airmansdata_.trade.getName(), Trade.class)
        .search(Trade.class, query, 
                Trade_.abbreviation.getName(), Trade_.trade.getName());
    }

    public <T> void searchPersonneldata(BuilderForSelect<T> dao, Class<T> fromType, String joinColumn, String query) {
        dao.join(fromType, joinColumn, Personneldata.class)
        .search(Personneldata.class, query,        
                Personneldata_.firstname.getName(), Personneldata_.localgovernmentarea.getName(),
                Personneldata_.middlename.getName(), Personneldata_.servicenumber.getName(),
                Personneldata_.surname.getName()
        )
        .join(Personneldata.class, Personneldata_.personnelpostingList.getName(), JoinType.LEFT, Personnelposting.class)
        .join(Personnelposting.class, Personnelposting_.appointment.getName(), Appointment.class)
        .search(Appointment.class, query, 
                Appointment_.abbreviation.getName(), Appointment_.appointment.getName())
        .join(Personneldata.class, Personneldata_.gender.getName(), Gender.class)
        .search(Gender.class, query, 
                Gender_.abbreviation.getName(), Gender_.gender.getName())
        .join(Personneldata.class, Personneldata_.rank.getName(), Rank.class)
        .search(Rank.class, query, 
                Rank_.abbreviation.getName(), Rank_.rank.getName())
        .join(Personneldata.class, Personneldata_.localgovernmentarea.getName(), Localgovernmentarea.class)
        .search(Localgovernmentarea.class, query, Localgovernmentarea_.localgovernmentarea.getName());
        
    }
}
