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
import com.bc.appbase.FilenamesDefault;
import com.bc.appbase.FilenamesDevMode;
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.util.ExpirableCache;
import com.bc.config.Config;
import com.bc.config.ConfigService;
import com.bc.jpa.JpaContext;
import com.bc.jpa.sync.JpaSync;
import com.bc.jpa.sync.SlaveUpdates;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Unit;
import java.nio.file.Paths;
import java.util.Properties;
import com.bc.appcore.Filenames;

/**
 * @author Chinomso Bassey Ikwuagwu on May 5, 2017 11:58:15 PM
 */
public class PdmAppLauncher extends AppLauncher<PdmApp> {
    
    private final Class currentEntityType;

    public PdmAppLauncher(boolean enableSync, boolean productionMode) {
        this(enableSync, productionMode, Paths.get(System.getProperty("user.home"), FileNames.ROOT).toString());
    }
    
    public PdmAppLauncher(boolean enableSync, boolean productionMode, String workingDir) {
        this(enableSync, productionMode ? 
                new FilenamesProductionMode(workingDir) :  new FilenamesDevMode(workingDir));
    }
    
    public PdmAppLauncher(boolean enableSync, Filenames filenames) {
        super(enableSync, Officersdata.class, 
                new FilenamesDefault(filenames.getWorkingDir()), filenames, 
                new String[]{
                        Paths.get(filenames.getWorkingDir(), FileNames.CONFIGS).toString(),
                        Paths.get(filenames.getWorkingDir(), FileNames.LOGS).toString()
                }, 
                null,
                "META-INF/properties/settings.properties",
                "PERSONNEL DATA MANAGER"
        );
        this.currentEntityType = Officersdata.class;
    }

    @Override
    public boolean isInstalled(Config config) {
        return config.getBoolean(ConfigNames.INSTALLED);
    }

    @Override
    public void setInstalled(Config config, boolean installed) {
        config.setBoolean(ConfigNames.INSTALLED, true);
    }

    @Override
    public String getLookAndFeel(Config config) {
        return config.getString(ConfigNames.LOOK_AND_FEEL, "Nimbus");
    }

    @Override
    public String getPersistenceFile(Config config) {
        return config.getString(ConfigNames.PERSISTENCE_FILE);
    }

    @Override
    public PdmApp createApplication(Filenames filenames, ConfigService configService, Config config, Properties settingsConfig, JpaContext jpaContext, SlaveUpdates slaveUpdates, JpaSync jpaSync, ExpirableCache expirableCache) {
        final PdmAppImpl app = new PdmAppImpl(
                filenames, configService, config, settingsConfig, 
                jpaContext, slaveUpdates, jpaSync, expirableCache
        );
        app.getAttributes().put(ParamNames.ENTITY_TYPE, this.currentEntityType);
        return app;
    }

    @Override
    public void validateJpaContext(JpaContext jpaContext) {
        jpaContext.getBuilderForSelect(Unit.class).getResultsAndClose();
    }

    public Class getCurrentEntityType() {
        return currentEntityType;
    }
}
