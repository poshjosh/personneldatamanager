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

import com.bc.appbase.AppLauncher;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.AppContext;
import com.bc.appcore.jpa.PersistenceContextManagerImpl;
import com.pdm.pu.entities.Officersdata;
import java.util.Properties;
import java.util.function.Function;
import com.bc.appcore.jpa.PersistenceContextManager;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Chinomso Bassey Ikwuagwu on May 5, 2017 11:58:15 PM
 */
public class AppLauncherImpl extends AppLauncher<PdmApp> {

    private transient static final Logger logger = Logger.getLogger(AppLauncherImpl.class.getName());

    public AppLauncherImpl(boolean productionMode) {
        
        final Class entityType = Officersdata.class;
        
        final Function<AppContext, Optional<PdmApp>> createAppFromContext = (appContext) -> {
            PdmApp app;
            try{
                app = new PdmAppImpl(appContext);
                app.getAttributes().put(ParamNames.ENTITY_TYPE, entityType);
            }catch(Exception e) {
                logger.log(Level.WARNING, "", e);
                app = null;
            }    
            return Optional.ofNullable(app);
        };    
        
        this.entityType(entityType)
                .processLogUIForTitle("HQ NAF PERSONNEL DATA MANAGER")
                .productionMode(productionMode)
                .enableSync(false)
                .appId("bcpdm")
                .createAppFromContext(createAppFromContext);
    }
    
    @Override
    public PersistenceContextManager getPersistenceContextManager() {
        return new PersistenceContextManagerImpl();
    }

    @Override
    protected Properties loadAuthProperties() {
        return new Properties();
    }
}
