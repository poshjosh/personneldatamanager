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
import com.bc.appcore.jpa.JpaContextManager;
import com.bc.appcore.jpa.JpaContextManagerImpl;
import com.pdm.pu.entities.Officersdata;
import java.util.Properties;
import java.util.function.Function;

/**
 * @author Chinomso Bassey Ikwuagwu on May 5, 2017 11:58:15 PM
 */
public class AppLauncherImpl extends AppLauncher<PdmApp> {

    public AppLauncherImpl(boolean productionMode) {
        
        final Class entityType = Officersdata.class;
        
        final Function<AppContext, PdmApp> createAppFromContext = (appContext) -> {
            final PdmAppImpl app = new PdmAppImpl(appContext);
            app.getAttributes().put(ParamNames.ENTITY_TYPE, entityType);
            return app;
        };    
        
        this.entityType(entityType)
                .processLogUIForTitle("HQ NAF PERSONNEL DATA MANAGER")
                .productionMode(productionMode)
                .enableSync(false)
                .appId("bcpdm")
                .createAppFromContext(createAppFromContext);
    }
    
    @Override
    public JpaContextManager getJpaContextManager() {
        return new JpaContextManagerImpl();
    }

    @Override
    protected Properties loadAuthProperties() {
        return new Properties();
    }
}
