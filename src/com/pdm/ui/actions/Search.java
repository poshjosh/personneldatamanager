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
import com.bc.appcore.exceptions.TaskExecutionException;
import com.bc.appcore.jpa.SearchContext;
import com.bc.appcore.parameter.ParameterException;
import com.bc.jpa.dao.SelectDao;
import com.bc.jpa.search.SearchResults;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Airmansdata_;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personneldata_;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.bc.jpa.dao.Select;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 2:38:14 PM
 */
public class Search implements Action<App, SearchResults> {

    private static final Logger logger = Logger.getLogger(Search.class.getName());
    
    @Override
    public SearchResults execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Class entityType = this.getEntityType(app, params);
        
        final String textToFind = this.getTextToFind(params, null);
        
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Result type: {0}, query: {1}", new Object[]{entityType, textToFind});
        }
        
        final boolean hasQuery = textToFind != null && !textToFind.isEmpty();
        
        final SearchContext searchContext = app.getSearchContext(entityType);
        
        final SelectDao dao;
        
        if(hasQuery) {
        
            dao = this.doExecute(app, params);
            
        }else{
            
            dao = searchContext.getSelectDao();
        }        
        
        
        return searchContext.getSearchResults(dao);
    }
    
    public Class getEntityType(App app, Map<String, Object> params) {
        final Class entityType;
        final Class param = (Class)params.get(ParamNames.ENTITY_TYPE);
        if(param != null) {
            entityType = param;
        }else{
            entityType = (Class)app.getAttributes().get(ParamNames.ENTITY_TYPE);
        }
        Objects.requireNonNull(entityType);
        return entityType;
    }
    
    public SelectDao doExecute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Class<?> resultType = this.getEntityType(app, params);
        
        final String textToFind = this.getTextToFind(params, null);
        
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Result type: {0}, query: {1}", new Object[]{resultType, textToFind});
        }
        
        final Select dao = app.getActivePersistenceUnitContext()
                .getDao().forSelect(resultType).from(resultType);
        
        dao.distinct(true);
        
        final boolean hasQuery = textToFind != null && !textToFind.isEmpty();
        
        dao.from(resultType); 
        
        if(hasQuery) {

            final String [] names;
            final String joinColumn;
            if(Officersdata.class.equals(resultType)) {
                names = new String[]{Officersdata_.courseonentry.getName()};
                joinColumn = Officersdata_.personneldata.getName();
            }else if (Airmansdata.class.equals(resultType)){
                names = new String[0];
                joinColumn = Airmansdata_.personneldata.getName();
            }else{
                throw new UnsupportedOperationException("Unexpected result type: "+resultType); 
            }   
            
            if(names.length > 0) {
                dao.search(textToFind, names);
            }
            
            if(joinColumn != null) {
                dao.join(joinColumn, Personneldata.class);
                dao.search(Personneldata.class, textToFind, 
                        Personneldata_.emailaddress.getName(), Personneldata_.firstname.getName(),
                        Personneldata_.middlename.getName(), Personneldata_.servicenumber.getName(),
                        Personneldata_.surname.getName());
            }
        }
        
        dao.orderBy(this.getOrders(app, resultType));
        
        return dao;
    }
    
    public String getQuery(Map<String, Object> params, String outputIfNone) {
        final String textToFind = this.getTextToFind(params, null);
        return textToFind == null ? outputIfNone : '%' + textToFind + '%';
    }
    
    public String getTextToFind(Map<String, Object> params, String outputIfNone) {
        final String textToFind = (String)params.get("query");
        return textToFind;
    }
    
    public Map getOrders(App app, Class resultType) {
        final Map orders = new LinkedHashMap();
        if (Officersdata.class.equals(resultType) || Airmansdata.class.equals(resultType)) {
            orders.put(Personneldata_.rank.getName(), "DESC");
            orders.put(Personneldata_.servicenumber.getName(), "DESC");
        } else {
            final String idColumnName = app.getActivePersistenceUnitContext().getMetaData().getIdColumnName(resultType);
            orders.put(idColumnName, "DESC");
        }
        return orders;
    }
}
/**
 * 
    
    public void searchOfficersdata(CriteriaBuilder cb, From<?, Officersdata> from, List<Predicate> likes, String toFind) {
        if(likes != null && from != null) {
            likes.add(cb.like(from.get(Officersdata_.courseonentry.getName()), toFind));
            
//            final Join<Officersdata, Commissiontype> joinCt = from.join(Officersdata_.commissiontype);
//            likes.add(cb.like(joinCt.getOrException(Commissiontype_.abbreviation.getName()), toFind));
//            likes.add(cb.like(joinCt.getOrException(Commissiontype_.commissiontype.getName()), toFind));
            
//            final Join<Officersdata, Speciality> joinSty = from.join(Officersdata_.speciality);
//            likes.add(cb.like(joinSty.getOrException(Speciality_.abbreviation.getName()), toFind));
//            likes.add(cb.like(joinSty.getOrException(Speciality_.speciality.getName()), toFind));
        }
    }
    
    public void searchAirmansdata(CriteriaBuilder cb, From<?, Airmansdata> from, List<Predicate> likes, String toFind) {
        if(likes != null && from != null) {
//            final Join<Airmansdata, Grade> adGrade = from.join(Airmansdata_.grade);
//            likes.add(cb.like(adGrade.getOrException(Grade_.grade.getName()), toFind));
            
//            final Join<Airmansdata, Trade> adTrade = from.join(Airmansdata_.trade);
//            likes.add(cb.like(adTrade.getOrException(Trade_.abbreviation.getName()), toFind));
//            likes.add(cb.like(adTrade.getOrException(Trade_.trade.getName()), toFind));
        }
    }
    
    public void searchPersonneldata(CriteriaBuilder cb, From<?, Personneldata> from, List<Predicate> likes, String toFind) {
        if(likes != null && from != null) {
            likes.add(cb.like(from.get(Personneldata_.firstname.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.localgovernmentarea.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.middlename.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.servicenumber.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.surname.getName()), toFind));
            
//            final Join<Personneldata, Personnelposting> persdataPosting = from.join(Personneldata_.personnelpostingList, JoinType.LEFT);
//            final Join<Personnelposting, Appointment> perspostingAppt = persdataPosting.join(Personnelposting_.appointment);
//            likes.add(cb.like(perspostingAppt.getOrException(Appointment_.abbreviation.getName()), toFind));
//            likes.add(cb.like(perspostingAppt.getOrException(Appointment_.appointment.getName()), toFind));

//            final Join<Personneldata, Gender> persdataGender = from.join(Personneldata_.gender);
//            likes.add(cb.like(persdataGender.getOrException(Gender_.abbreviation.getName()), toFind));
//            likes.add(cb.like(persdataGender.getOrException(Gender_.gender.getName()), toFind));

//            final Join<Personneldata, Rank> persdataRank = from.join(Personneldata_.rank);
//            likes.add(cb.like(persdataRank.getOrException(Rank_.abbreviation.getName()), toFind));
//            likes.add(cb.like(persdataRank.getOrException(Rank_.rank.getName()), toFind));

//            final Join<Personneldata, Localgovernmentarea> persdataLga = from.join(Personneldata_.localgovernmentarea);
//            likes.add(cb.like(persdataLga.getOrException(Localgovernmentarea_.localgovernmentarea.getName()), toFind));
        }
    }
    
    public void where(CriteriaBuilder cb, CriteriaQuery cq, List<Predicate> likes) {
        
        if(likes == null || likes.isEmpty()) {
            return;
        }
        
        final Predicate where;
        if(likes.size() == 1) {
            where = likes.get(0);
        }else{
            where = cb.or(likes.toArray(new Predicate[0]));
        }
        
        cq.where(where);
    }

    public <T> void orderBy(App app, CriteriaBuilder cb, Class<T> resultType, CriteriaQuery<T> cq, From<?, T> root) {
        
        final String idColumnName = app.getJpaContext().getMetaData().getIdColumnName(resultType);
        
        cq.orderBy(cb.desc(root.get(idColumnName))); 
    }
  
            dao = new SelectDaoBuilderImpl(){
                @Override
                public Join getJoin(Class type, From from, Class colType, Join outputIfNone) {
                    if(type == Personneldata.class) {
                        if(colType == Airmansdata.class) {
                            return from.join(Personneldata_.airmansdata);
                        }else if(colType == Officersdata.class) {
                            return from.join(Personneldata_.officersdata);
                        }
                    }
                    return super.getJoin(type, from, colType, outputIfNone);
                }
            }
                    .jpaContext(app.getJpaContext())
                    .resultType(entityType)
                    .textToFind(textToFind)
                    .typesToSearch(Arrays.asList(String.class, entityType))
                    .build();
 * 
 * 
 */