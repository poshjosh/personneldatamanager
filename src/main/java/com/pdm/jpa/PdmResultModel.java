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

package com.pdm.jpa;

import com.bc.appbase.App;
import com.bc.appbase.ui.PromptException;
import com.bc.appcore.typeprovider.TypeProvider;
import com.bc.appcore.jpa.model.EntityResultModelImpl;
import com.bc.appcore.util.Pair;
import com.pdm.PdmApp;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personnelposting;
import com.pdm.pu.entities.Personnelposting_;
import com.pdm.pu.entities.Unit_;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * @author Chinomso Bassey Ikwuagwu on May 20, 2017 7:53:25 PM
 */
public class PdmResultModel<T> extends EntityResultModelImpl<T>{
    
    private static final Logger logger = Logger.getLogger(PdmResultModel.class.getName());

    public PdmResultModel(App app, Class<T> coreEntityType, 
            List<String> columnNames) {
        super(app, coreEntityType, columnNames,
                (col, val) -> true, new PromptException(app.getUIContext()));
    }

    public PdmResultModel(App app, Class<T> coreEntityType, 
            List<String> columnNames, 
            TypeProvider typeProvider) {
        super(app, coreEntityType, columnNames, typeProvider, 
                (col, val) -> true, new PromptException(app.getUIContext()));
    }
    
    @Override
    public Pair<Object, String> getEntityRelation(
            T entity, int rowIndex, int columnIndex, Object value) {
        final Pair<Object, String> relation;
        final String columnName = this.getColumnName(columnIndex);
        if(Unit_.unit.getName().equals(columnName)) {
            final Personnelposting lastPosting = this.getLastPosting(entity, columnName, null);
            Objects.requireNonNull(lastPosting);
            relation = new Pair(lastPosting, Personnelposting_.unit.getName());
        }else{
            relation = super.getEntityRelation(entity, rowIndex, columnIndex, value);
        }
        return relation;
    }

    @Override
    public Object set(T entity, int rowIndex, int columnIndex, Object value) {
        final Object previousValue;
        final String columnName = this.getColumnName(columnIndex);
        if(Unit_.unit.getName().equals(columnName)) {
            final Personnelposting lastPosting = this.getLastPosting(entity, columnName, null);
            Objects.requireNonNull(lastPosting);
            previousValue = lastPosting.getUnit();
            this.update(rowIndex, columnIndex, 
                    entity, columnName, previousValue, 
                    lastPosting, Personnelposting_.unit.getName(), value);
        }else{
            previousValue = super.set(entity, rowIndex, columnIndex, value);
        }
        return previousValue;
    }

    @Override
    public Object get(T entity, int rowIndex, int columnIndex) {
        final Object value;
        final String columnName = this.getColumnName(columnIndex);
        if(Unit_.unit.getName().equals(columnName)) {
            final Personnelposting lastPosting = this.getLastPosting(entity, columnName, null);
            value = lastPosting == null ? null : lastPosting.getUnit();
        }else{
            value = super.get(entity, rowIndex, columnIndex);
        }
        return value;
    }
    
    public Personnelposting getLastPosting(T entity, String columnName, Personnelposting outputIfNone) {
        final Personneldata persdata = this.getPersonneldata(entity);
        return this.getLastPosting(persdata, columnName, outputIfNone);
    }
    
    public Personnelposting getLastPosting(Personneldata persdata, String columnName, Personnelposting outputIfNone) {
        final int pos = this.getPos(columnName);
        final List<Personnelposting> perspostingList = persdata.getPersonnelpostingList();
        final Personnelposting persposting = this.getFromEnd(perspostingList, pos, 1);
        return persposting == null ? outputIfNone : persposting;
    }
    
    public Personneldata getPersonneldata(T entity) {
        final Personneldata persdata;
        if(entity instanceof Personneldata) {
            persdata = (Personneldata)entity;
        }else if(entity instanceof Officersdata) {
            persdata = ((Officersdata)entity).getPersonneldata();
        }else if(entity instanceof Airmansdata) {
            persdata = ((Airmansdata)entity).getPersonneldata();
        }else{
            throw new IllegalArgumentException(String.valueOf(entity));
        }
        return persdata;
    }
    
    @Override
    public int getPos(String columnName) {
        if(Unit_.unit.getName().equals(columnName)) {
            return 0;
        }else{
            throw new UnsupportedOperationException("Unexpected column name: "+columnName); 
        }
    }
    
    @Override
    public PdmApp getApp() {
        return (PdmApp)super.getApp(); 
    }
}
