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

import com.bc.appbase.ObjectFactoryImpl;
import com.pdm.jpa.PdmSelectionContext;
import com.bc.appcore.exceptions.ObjectFactoryException;
import com.bc.appcore.jpa.SelectionContext;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 4:15:56 PM
 */
public class PdmObjectFactory extends ObjectFactoryImpl {

    public PdmObjectFactory(PdmApp app) {
        super(app);
    }

    @Override
    public <T> T get(Class<T> type) {
        Object output;
        if(type.equals(SelectionContext.class)){
            output = new PdmSelectionContext(this.getApp());
        }else{
            try{
                output = super.get(type);
            }catch(ObjectFactoryException e) {
                throw new RuntimeException(e);
            }
        }  
        return (T)output;
    }

    @Override
    public PdmApp getApp() {
        return (PdmApp)super.getApp(); 
    }
}
