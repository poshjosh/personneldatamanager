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
import com.bc.appbase.ui.SearchResultsPanel;
import com.bc.appcore.html.HtmlBuilder;
import com.bc.appcore.jpa.model.ResultModel;
import com.bc.appcore.jpa.model.ResultModelImpl;
import com.bc.appcore.parameter.ParametersBuilder;
import com.bc.appcore.util.Settings;
import com.bc.config.Config;
import com.bc.config.ConfigService;
import com.bc.jpa.JpaContext;
import com.bc.jpa.sync.JpaSync;
import com.bc.jpa.sync.SlaveUpdates;
import com.pdm.parameter.SearchParametersBuilder;
import com.pdm.parameter.SelectedRecordsParametersBuilder;
import com.pdm.pu.entities.Officersdata;
import com.pdm.ui.PdmMainFrame;
import com.pdm.ui.PdmUIContext;
import com.pdm.ui.PdmUIContextImpl;
import com.pdm.ui.SearchPanel;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.concurrent.ExecutorService;
import javax.swing.JPanel;
import com.pdm.ui.actions.PdmActionCommands;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 25, 2017 8:54:05 AM
 */
public class PdmAppImpl extends AbstractApp implements PdmApp {

    public PdmAppImpl(Path workingDir, ConfigService configService, Config config, Settings settings, 
            JpaContext jpaContext, ExecutorService dataOutputService, SlaveUpdates slaveUpdates, JpaSync jpaSync) {
        super(workingDir, configService, config, settings, jpaContext, dataOutputService, slaveUpdates, jpaSync);
    }

    @Override
    public EntityManager getEntityManager() {
        final Class anyClassInDb = com.pdm.pu.entities.Personneldata.class;
        return this.getJpaContext().getEntityManager(anyClassInDb);
    }
    
    @Override
    public PdmUIContext getUIContext() {
        return (PdmUIContext)super.getUIContext();
    }

    @Override
    public <T> HtmlBuilder<T> getHtmlBuilder(Class<T> entityType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init() {
        this.init(new PdmObjectFactory(this));
        final PdmMainFrame mainFrame = new PdmMainFrame();
        this.init(new PdmUIContextImpl(this, null, mainFrame));
        mainFrame.init(this);
    }

    @Override
    public <T> ResultModel<T> getResultModel(Class<T> entityType, ResultModel<T> outputIfNone) {
        final Class defaultType = Officersdata.class;
        if(entityType == null) {
            entityType = defaultType;
        }
        final String [] entityColumnNames = this.getColumnnames(entityType);
        final String serialColumnName = this.getConfig().getString(ConfigNames.COLUMNNAMES_SERIAL);
        final int serialColumnIndex = serialColumnName == null ? -1 : 0;
        final List<String> resultColumnNames = new ArrayList(entityColumnNames.length + 1);
        if(serialColumnName != null) {
            resultColumnNames.add(serialColumnName);
        }
        resultColumnNames.addAll(Arrays.asList(entityColumnNames));
        return new ResultModelImpl(this, entityType, resultColumnNames, serialColumnIndex);
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
        if(source instanceof JPanel && PdmActionCommands.ADD_OFFICERSDATA.equals(actionCommand)) {
            builder = this.get(ParametersBuilder.class);
        }else if(source instanceof SearchPanel && PdmActionCommands.SEARCH_AND_DISPLAY_RESULTS.equals(actionCommand)) {
            builder = new SearchParametersBuilder();
        }else if(source instanceof SearchResultsPanel && 
                (PdmActionCommands.DISPLAY_RECORDS.equals(actionCommand) ||
                PdmActionCommands.DELETE_RECORDS.equals(actionCommand))) {    
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
