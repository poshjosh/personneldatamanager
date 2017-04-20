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
import com.bc.appcore.jpa.SearchContext;
import com.bc.appcore.parameter.ParameterException;
import com.bc.appcore.parameter.ParameterNotFoundException;
import com.bc.jpa.dao.BuilderForSelect;
import com.bc.jpa.dao.BuilderForSelectImpl;
import com.bc.jpa.dao.SelectDao;
import com.bc.jpa.search.SearchResults;
import com.bc.jpa.util.SelectDaoBuilderImpl;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Airmansdata_;
import com.pdm.pu.entities.Gender;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personneldata_;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Chinomso Bassey Ikwuagwu on Apr 1, 2017 2:38:14 PM
 */
public class Search implements Action<App, SearchResults> {

    private static final Logger logger = Logger.getLogger(Search.class.getName());
    
    @Override
    public SearchResults execute(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Class resultType = (Class)params.get(ParamNames.RESULT_TYPE);
        Objects.requireNonNull(resultType);
        
        final String textToFind = this.getTextToFind(params);
        
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Result type: {0}, query: {1}", new Object[]{resultType, null});
        }
        
        final boolean hasQuery = textToFind != null && !textToFind.isEmpty();
        
        final Class entityType = (Class)app.getAttributes().get(ParamNames.RESULT_TYPE);
        
        final SearchContext searchContext = app.getSearchContext(resultType);
        
        final SelectDao dao;
        
        if(hasQuery) {
        
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
                    .resultType(resultType)
                    .textToFind(textToFind)
                    .typesToSearch(Arrays.asList(String.class, resultType, entityType))
                    .build();
            
        }else{
            
            dao = searchContext.getSelectDao();
        }        
        
        
        return searchContext.getSearchResults(dao);
    }
    
    public SearchResults executeOld2(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Class resultType = (Class)params.get(ParamNames.RESULT_TYPE);
        Objects.requireNonNull(resultType);
        
        final String query = this.getQuery(params, null);
        
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Result type: {0}, query: {1}", new Object[]{resultType, null});
        }
        
        final boolean hasQuery = query != null && !query.isEmpty();
        
        final EntityManager em = app.getJpaContext().getEntityManager(resultType);

        final BuilderForSelect dao = resultType == null ? 
                new BuilderForSelectImpl(em) : new BuilderForSelectImpl(em, resultType);
        
        final CriteriaBuilder cb = dao.getCriteriaBuilder();
        
        final CriteriaQuery cq = dao.getCriteriaQuery();
        
        cq.distinct(true);
        
        final List<Predicate> likes = !hasQuery ? null : new ArrayList();
        
        final Root root = cq.from(resultType); 
        
        this.searchPersonneldata(cb, root, likes, query);
        
        if(Officersdata.class.equals(app.getAttributes().get(ParamNames.RESULT_TYPE))) {
            
            final Join<Personneldata, Officersdata> joinOffrsdata = root.join(Personneldata_.officersdata);            
            
            this.searchOfficersdata(cb, joinOffrsdata, likes, query);
            
        }else if (Airmansdata.class.equals(app.getAttributes().get(ParamNames.RESULT_TYPE))){

            final Join<Personneldata, Airmansdata> joinAirmansdata = root.join(Personneldata_.airmansdata);            
            
            this.searchAirmansdata(cb, joinAirmansdata, likes, query);
            
        }else{
            
            throw new UnsupportedOperationException("Unexpected result type: "+resultType);
        }
             
        this.where(cb, cq, likes);

        this.orderBy(app, cb, resultType, cq, root);
        
        return app.getSearchContext(resultType).getSearchResults(dao);
    }

    public SearchResults executeOld(App app, Map<String, Object> params) 
            throws ParameterException, TaskExecutionException {
        
        final Class resultType = (Class)params.get(ParamNames.RESULT_TYPE);
        Objects.requireNonNull(resultType);
        
        final String query = this.getQuery(params, null);
        
        if(logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Result type: {0}, query: {1}", new Object[]{resultType, null});
        }
        
        final boolean hasQuery = query != null && !query.isEmpty();
        
        Objects.requireNonNull(resultType);
        
        final EntityManager em = app.getEntityManager(resultType);

        final BuilderForSelect dao = new BuilderForSelectImpl(em, resultType);
        
        final CriteriaBuilder cb = dao.getCriteriaBuilder();
        
        final CriteriaQuery cq = dao.getCriteriaQuery();
        
        cq.distinct(true);
        
        final List<Predicate> likes = !hasQuery ? null : new ArrayList();
        
        final Root root = cq.from(resultType); 
        
        final String joinColumn;
        
        if(resultType == Officersdata.class) {
            
            this.searchOfficersdata(cb, root, likes, query);
            joinColumn = Officersdata_.personneldata.getName();
            
        }else if (resultType == Airmansdata.class){

            this.searchAirmansdata(cb, root, likes, query);
            joinColumn = Airmansdata_.personneldata.getName();
            
        }else{
            
            throw new UnsupportedOperationException("Unexpected result type: "+resultType);
        }
             
        final Join<?, Personneldata> joinPersdata = root.join(joinColumn);
        
        this.searchPersonneldata(cb, joinPersdata, likes, query);
        
        this.where(cb, cq, likes);

        this.orderBy(app, cb, resultType, cq, root);
        
        return app.getSearchContext(resultType).getSearchResults(dao);
    }
    
    public void searchOfficersdata(CriteriaBuilder cb, From<?, Officersdata> from, List<Predicate> likes, String toFind) {
        if(likes != null && from != null) {
            likes.add(cb.like(from.get(Officersdata_.course.getName()), toFind));
            
//            final Join<Officersdata, Commissiontype> joinCt = from.join(Officersdata_.commissiontype);
//            likes.add(cb.like(joinCt.get(Commissiontype_.abbreviation.getName()), toFind));
//            likes.add(cb.like(joinCt.get(Commissiontype_.commissiontype.getName()), toFind));
            
//            final Join<Officersdata, Speciality> joinSty = from.join(Officersdata_.speciality);
//            likes.add(cb.like(joinSty.get(Speciality_.abbreviation.getName()), toFind));
//            likes.add(cb.like(joinSty.get(Speciality_.speciality.getName()), toFind));
        }
    }
    
    public void searchAirmansdata(CriteriaBuilder cb, From<?, Airmansdata> from, List<Predicate> likes, String toFind) {
        if(likes != null && from != null) {
//            final Join<Airmansdata, Grade> adGrade = from.join(Airmansdata_.grade);
//            likes.add(cb.like(adGrade.get(Grade_.grade.getName()), toFind));
            
//            final Join<Airmansdata, Trade> adTrade = from.join(Airmansdata_.trade);
//            likes.add(cb.like(adTrade.get(Trade_.abbreviation.getName()), toFind));
//            likes.add(cb.like(adTrade.get(Trade_.trade.getName()), toFind));
        }
    }
    
    public void searchPersonneldata(CriteriaBuilder cb, From<?, Personneldata> from, List<Predicate> likes, String toFind) {
        if(likes != null && from != null) {
            likes.add(cb.like(from.get(Personneldata_.firstname.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.localgovernmentarea.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.middlename.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.servicenumber.getName()), toFind));
            likes.add(cb.like(from.get(Personneldata_.surname.getName()), toFind));
            
//            final Join<Personneldata, Appointment> persdataAppt = from.join(Personneldata_.appointment);
//            likes.add(cb.like(persdataAppt.get(Appointment_.abbreviation.getName()), toFind));
//            likes.add(cb.like(persdataAppt.get(Appointment_.appointment.getName()), toFind));

//            final Join<Personneldata, Gender> persdataGender = from.join(Personneldata_.gender);
//            likes.add(cb.like(persdataGender.get(Gender_.abbreviation.getName()), toFind));
//            likes.add(cb.like(persdataGender.get(Gender_.gender.getName()), toFind));

//            final Join<Personneldata, Rank> persdataRank = from.join(Personneldata_.rank);
//            likes.add(cb.like(persdataRank.get(Rank_.abbreviation.getName()), toFind));
//            likes.add(cb.like(persdataRank.get(Rank_.rank.getName()), toFind));

//            final Join<Personneldata, Stateoforigin> persdataStateoforigin = from.join(Personneldata_.stateoforigin);
//            likes.add(cb.like(persdataStateoforigin.get(Stateoforigin_.stateoforigin.getName()), toFind));
        }
    }
    
    public String getQuery(Map<String, Object> params, String outputIfNone) throws ParameterNotFoundException {
        final String textToFind = this.getTextToFind(params);
        return textToFind == null ? outputIfNone : '%' + textToFind + '%';
    }
    
    public String getTextToFind(Map<String, Object> params) throws ParameterNotFoundException {
        
        final String textToFind = (String)params.get("query");

        if(textToFind == null || textToFind.trim().isEmpty()) {
            throw new ParameterNotFoundException("query");
        }
        
        return textToFind;
    }
    
    public void where(CriteriaBuilder cb, CriteriaQuery cq, List<Predicate> likes) {
        
        final List<Predicate> where = new ArrayList<>();
        
        if(likes != null) {
            where.add(cb.or(likes.toArray(new Predicate[0])));
        }
        
        if(!where.isEmpty()) {
            cq.where( cb.and(where.toArray(new Predicate[0])) );
        }
    }

    public <T> void orderBy(App app, CriteriaBuilder cb, Class<T> resultType, CriteriaQuery<T> cq, From<?, T> root) {
        
        final String idColumnName = app.getJpaContext().getMetaData().getIdColumnName(resultType);
        
        cq.orderBy(cb.desc(root.get(idColumnName))); 
    }
}
