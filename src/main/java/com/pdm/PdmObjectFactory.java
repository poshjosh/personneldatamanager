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

import com.bc.appbase.App;
import com.bc.appbase.ObjectFactoryBase;
import com.pdm.jpa.PdmSelectionContext;
import com.bc.appcore.jpa.SelectionContext;
import com.bc.appcore.jpa.EntityStructureFactory;
import com.bc.appbase.ui.builder.MatchEntries;
import com.bc.appbase.xls.SheetProcessorContext;
import com.bc.appcore.ObjectFactory;
import com.bc.appcore.user.User;
import com.pdm.jpa.PdmEntityStructureFactory;
import com.pdm.pu.entities.Appointment;
import com.pdm.ui.builder.PdmMatchEntries;
import com.pdm.xls.PdmSheetProcessorContext;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 4:15:56 PM
 */
public class PdmObjectFactory extends ObjectFactoryBase {

    public PdmObjectFactory(App app) {
        super(app);
    }

    @Override
    public <T> T doGetOrException(Class<T> type) throws Exception {
        Object output;
        if(type.equals(ObjectFactory.class)){
            
            output = new PdmObjectFactory(this.getApp());
            
        }else if(type.equals(MatchEntries.class)){
            
            output = new PdmMatchEntries();
            
        }else if(type.equals(User.class)){
            
            output = new PdmUserImpl(getContext().getDao().find(Appointment.class, 1));
            
        }else if(type.equals(EntityStructureFactory.class)){
            
            output = new PdmEntityStructureFactory(this.getContext(), this);

        }else if(type.equals(SelectionContext.class)){
            
            output = new PdmSelectionContext(this.getContext());
            
        }else if(type.equals(SheetProcessorContext.class)){
            
            output = new PdmSheetProcessorContext(this.getApp());
            
        }else{
            
            output = super.doGetOrException(type);
        }  
        
        return (T)output;
    }
}
