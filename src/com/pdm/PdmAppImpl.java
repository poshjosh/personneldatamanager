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

package com.pdm;

import com.bc.appbase.AbstractApp;
import com.bc.appbase.App;
import com.bc.appcore.AppContext;
import com.bc.appbase.parameter.SelectedRecordsParametersBuilder;
import com.bc.appbase.ui.MainFrame;
import com.bc.appbase.ui.SearchResultsPanel;
import com.bc.appbase.ui.UIContext;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.html.HtmlBuilder;
import com.bc.appcore.jpa.model.ResultModel;
import com.bc.appcore.parameter.ParametersBuilder;
import com.pdm.jpa.PdmResultModel;
import com.pdm.parameter.SearchParametersBuilder;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Officersdata;
import com.pdm.ui.PdmMainFrame;
import com.pdm.ui.PdmUIContext;
import com.pdm.ui.PdmUIContextImpl;
import com.pdm.ui.SearchPanel;
import java.text.DateFormat;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.swing.ImageIcon;
import com.bc.appcore.ObjectFactory;
import com.bc.appcore.predicates.AcceptAll;
import com.pdm.jpa.EntityInsertOrderComparatorImpl;
import com.pdm.pu.entities.Appointment;
import com.pdm.ui.actions.PdmActionCommands;
import java.util.Comparator;
import java.util.function.Predicate;
import javax.swing.JFrame;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 25, 2017 8:54:05 AM
 */
public class PdmAppImpl extends AbstractApp implements PdmApp {

    public PdmAppImpl(AppContext appContext) {
        super(appContext);
    }

    @Override
    public ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
    
    @Override
    public EntityManager getEntityManager(Class resultType) {
        return this.getJpaContext().getEntityManager(resultType);
    }
    
    @Override
    public PdmUIContext getUIContext() {
        return (PdmUIContext)super.getUIContext();
    }

    @Override
    public <T> HtmlBuilder<T> getHtmlBuilder(Class<T> entityType) {
        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected ObjectFactory createObjectFactory() {
        return new PdmObjectFactory(this);
    }
    
    @Override
    protected MainFrame createMainFrame() {
        return new PdmMainFrame();
    }
    
    @Override
    protected UIContext createUIContext(App app, ImageIcon imageIcon, JFrame mainFrame) {
        return new PdmUIContextImpl(this, imageIcon, mainFrame);
    }

    @Override
    protected String getImageIconDescription(URL url) {
        return "NAF Logo";
    }
    
    @Override
    protected URL getIconURL() {
        return this.getClass().getResource("naflogo.jpg");
    }
    
    @Override
    public Comparator getEntityOrderComparator() {
        return new EntityInsertOrderComparatorImpl(this);
    }

    @Override
    public Class getUserEntityType() {
        return Appointment.class;
    }

    @Override
    public Predicate<String> getPersistenceUnitNameTest() {
        return new AcceptAll();
    }

    @Override
    public <T> ResultModel<T> getResultModel(Class<T> entityType, ResultModel<T> outputIfNone) {
        
        entityType = this.getEntityType(entityType);
        
        final String [] entityColumnNames = this.getColumnnames(entityType);
        final String serialColumnName = this.getConfig().getString(ConfigNames.COLUMNNAMES_SERIAL);
        final int serialColumnIndex = serialColumnName == null ? -1 : 0;
        final List<String> resultColumnNames = new ArrayList(entityColumnNames.length + 1);
        if(serialColumnName != null && !serialColumnName.trim().isEmpty()) {
            resultColumnNames.add(serialColumnName);
        }
        resultColumnNames.addAll(Arrays.asList(entityColumnNames));
        return new PdmResultModel(this, entityType, resultColumnNames, serialColumnIndex);
    }
    
    public Class getEntityType(Class entityType) {
        if(entityType == null) {
            entityType = (Class)this.getAttributes().get(ParamNames.ENTITY_TYPE);
        }
        Objects.requireNonNull(entityType);
        if(!this.getSupportedEntityTypes().contains(entityType)) {
            throw new IllegalArgumentException(String.valueOf(entityType));
        }
        return entityType;
    }
    
    @Override
    public Set<Class> getSupportedEntityTypes() {
        return new HashSet(Arrays.asList(Officersdata.class, Airmansdata.class));
    }
    
    public String[] getColumnnames(Class entityType) {
        final String key = "columnNames."+entityType.getName();
        String [] columnNames = this.getConfig().getArray(key, (String[])null);
        if(columnNames == null) {
            columnNames = this.getConfig().getArray("columnNames."+entityType.getSimpleName(), columnNames);
        }
        Objects.requireNonNull(columnNames);
        return columnNames;
    }
    

    @Override
    public <T> ParametersBuilder<T> getParametersBuilder(T source, String actionCommand) {
        
        final ParametersBuilder builder;
        
        if(PdmActionCommands.DISPLAY_EDIT_SELECTED_ENTITIES_UIS.equals(actionCommand) ||
                PdmActionCommands.DISPLAY_ADD_PERSONNELPOSTING_UI.equals(actionCommand) ||
                PdmActionCommands.DISPLAY_ADD_COURSEATTENDED_UI.equals(actionCommand)) {
            builder = new SelectedRecordsParametersBuilder();
        }else if(source instanceof SearchPanel && PdmActionCommands.SEARCH_AND_DISPLAY_RESULTS.equals(actionCommand)) {
            builder = new SearchParametersBuilder();
        }else if(source instanceof SearchResultsPanel && 
                PdmActionCommands.DISPLAY_MULTIPLE_RECORDS.equals(actionCommand)) {    
            builder = new SelectedRecordsParametersBuilder();
        }else{
            builder = super.getParametersBuilder(source, actionCommand);
        }
        
        builder.context(this).with(source);
        
        return builder;
    }

    @Override
    public DateFormat getDateTimeFormat() {
        return new com.pdm.util.DateTimeFormat(this);
    }

    @Override
    public DateFormat getDateFormat() {
        return new com.pdm.util.DateFormat(this);
    }
}
