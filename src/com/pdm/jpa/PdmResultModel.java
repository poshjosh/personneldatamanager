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

import com.bc.appcore.AppCore;
import com.bc.appcore.typeprovider.TypeProvider;
import com.bc.appcore.jpa.model.ResultModelImpl;
import com.pdm.PdmApp;
import com.pdm.pu.entities.Airmansdata;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personnelposting;
import com.pdm.pu.entities.Unit;
import com.pdm.pu.entities.Unit_;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Chinomso Bassey Ikwuagwu on May 20, 2017 7:53:25 PM
 */
public class PdmResultModel<T> extends ResultModelImpl<T>{
    
    private static final Logger logger = Logger.getLogger(PdmResultModel.class.getName());

    public PdmResultModel(AppCore app, Class<T> coreEntityType, List<String> columnNames, int serialColumnIndex) {
        super(app, coreEntityType, columnNames, serialColumnIndex);
    }

    public PdmResultModel(AppCore app, Class<T> coreEntityType, List<String> columnNames, int serialColumnIndex, TypeProvider typeProvider, Predicate<String> persistenceUnitTest) {
        super(app, coreEntityType, columnNames, serialColumnIndex, typeProvider, persistenceUnitTest);
    }
    
    @Override
    public Object get(T entity, int rowIndex, String columnName) {
        final Object value;
        if(Unit_.unit.getName().equals(columnName)) {
            final Personneldata task = this.getPersonneldata(entity);
            final Unit lastUnit = this.getLastUnit(task, columnName, null);
            value = lastUnit;
        }else{
            value = super.get(entity, rowIndex, columnName);
        }
        return value;
    }

//    @Override
//    public Object set(T entity, int rowIndex, String columnName, Object value) {
//        return super.set(entity, rowIndex, columnName, value); 
//    }
    
    @Override
    public Pair<Object, String> getEntityRelation(
            T entity, int rowIndex, String columnName, Object value) {
        final Object target;
        if(false && Unit_.unit.getName().equals(columnName)) {
            final Personneldata persdata = this.getPersonneldata(entity);
            Unit unit = this.getLastUnit(persdata, columnName, null);
            Objects.requireNonNull(unit);
            target = unit;
            columnName = Unit_.abbreviation.getName();
        }else{
            final Pair<Object, String> pair = super.getEntityRelation(entity, rowIndex, columnName, value);
            target = pair.key;
            columnName = pair.value;
        }
        return new ResultModelImpl.Pair(target, columnName);
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
    public void update(Object entity, String entityColumn, Object target, String targetColumn, Object targetValue) {
        
        final int response = JOptionPane.showConfirmDialog(
                this.getApp().getUIContext().getMainFrame(), "Update: "+targetColumn+'?', 
                "Update?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if(response == JOptionPane.YES_OPTION) {
            try{
                
                super.update(entity, entityColumn, target, targetColumn, targetValue);
                
            }catch(RuntimeException e) {
                final String msg = "Error updating "+targetColumn;
                logger.log(Level.WARNING, msg, e);
                this.getApp().getUIContext().showErrorMessage(e, msg);
            }
        }
    }
    
    public Unit getLastUnit(Officersdata offrsdata, String columnName, Unit outputIfNone) {
        return this.getLastUnit(offrsdata.getPersonneldata(), columnName, outputIfNone);
    }
    
    public Unit getLastUnit(Airmansdata airmansdata, String columnName, Unit outputIfNone) {
        return this.getLastUnit(airmansdata.getPersonneldata(), columnName, outputIfNone);
    }

    public Unit getLastUnit(Personneldata persdata, String columnName, Unit outputIfNone) {
        final int pos = this.getPos(columnName);
        final List<Personnelposting> perspostingList = persdata.getPersonnelpostingList();
        final Personnelposting persposting = this.getFromEnd(perspostingList, columnName, pos, 1);
        return persposting == null ? outputIfNone : 
                persposting.getUnit() == null ? outputIfNone : persposting.getUnit();
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
