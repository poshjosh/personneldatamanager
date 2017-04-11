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

import com.bc.appcore.jpa.AbstractSelectionContext;
import com.pdm.pu.entities.Appointment;
import com.pdm.pu.entities.Appointment_;
import com.pdm.pu.entities.Appointmenttype;
import com.pdm.pu.entities.Appointmenttype_;
import com.pdm.pu.entities.Commissiontype;
import com.pdm.pu.entities.Commissiontype_;
import com.pdm.pu.entities.Gender;
import com.pdm.pu.entities.Gender_;
import com.pdm.pu.entities.Grade;
import com.pdm.pu.entities.Grade_;
import com.pdm.pu.entities.Rank;
import com.pdm.pu.entities.Rank_;
import com.pdm.pu.entities.Speciality;
import com.pdm.pu.entities.Speciality_;
import com.pdm.pu.entities.Stateoforigin;
import com.pdm.pu.entities.Stateoforigin_;
import com.pdm.pu.entities.Trade;
import com.pdm.pu.entities.Trade_;
import com.pdm.pu.entities.Unit;
import com.pdm.pu.entities.Unit_;
import com.pdm.pu.entities.Unittype;
import com.pdm.pu.entities.Unittype_;
import com.pdm.PdmApp;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 8:41:23 PM
 */
public class PdmSelectionContext extends AbstractSelectionContext {

    public PdmSelectionContext(PdmApp app) {
        super(app);
    }
    
    @Override
    public String getSelectionColumn(Class entityType, String outputIfNone) {
        final String columnName;
        if(entityType == Appointment.class) {
            columnName = Appointment_.abbreviation.getName();
        }else if(entityType == Appointmenttype.class) {
            columnName = Appointmenttype_.appointmenttype.getName();
        }else if(entityType == Commissiontype.class) {
            columnName = Commissiontype_.abbreviation.getName();
        }else if(entityType == Gender.class) {
            columnName = Gender_.gender.getName();
        }else if(entityType == Grade.class) {
            columnName = Grade_.grade.getName();
        }else if(entityType == Rank.class) {
            columnName = Rank_.abbreviation.getName();
        }else if(entityType == Speciality.class) {
            columnName = Speciality_.speciality.getName();
        }else if(entityType == Stateoforigin.class) {
            columnName = Stateoforigin_.stateoforigin.getName();
        }else if(entityType == Trade.class) {
            columnName = Trade_.trade.getName();
        }else if(entityType == Unit.class) {
            columnName = Unit_.abbreviation.getName();
        }else if(entityType == Unittype.class) {
            columnName = Unittype_.unittype.getName();
        } else{
            columnName = outputIfNone;
        }
        return columnName;
    }
}
