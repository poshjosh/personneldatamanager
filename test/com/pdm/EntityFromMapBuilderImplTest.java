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
import com.bc.appbase.AppLauncher;
import com.bc.jpa.util.EntityFromMapBuilder;
import com.bc.util.MapBuilder;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personnelposting;
import com.bc.appbase.jpa.EntityFromMapBuilderDataFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chinomso Bassey Ikwuagwu on May 16, 2017 10:19:21 PM
 */
public class EntityFromMapBuilderImplTest {

    public static void main(String [] args) {
        new EntityFromMapBuilderImplTest().execute();
    }
    
    public void execute() {
        
        final boolean productionMode = true;
        
        final AppLauncher<PdmApp> launcher = new PdmAppLauncher(false, productionMode){
            @Override
            public void onLaunchCompleted(PdmApp app) {
                try{
                    EntityFromMapBuilderImplTest.this.onLaunchCompleted(app);
                }catch(RuntimeException e) {
                    e.printStackTrace();
                }
            }
        };
        
        try{
            launcher.launch(new String[0]);
        }catch(Throwable t) {
            launcher.showErrorMessageAndExit(t);
        }
    }
    
    public void onLaunchCompleted(App app) {
        
        final Object entity = this.getEntity();
        
        final Map map = app.getOrException(MapBuilder.class)
                .source(entity)
                .target(new LinkedHashMap())
                .build();
        
System.out.println("Built map:\n"+app.getJsonFormat().toJSONString(map)+". @"+this.getClass());  

        final Map<Map, Object> resultBuffer = new LinkedHashMap();
        
        app.getOrException(EntityFromMapBuilder.class)
                .resultBuffer(resultBuffer)
                .formatter(new EntityFromMapBuilderDataFormatter(app, app.getJpaContext(), app.getUIContext()))
                .source(map)
                .target(entity.getClass())
                .build();
        
System.out.println("Built entities: "+resultBuffer.values()+". @"+this.getClass());        
    }
    
    public Object getEntity() {
        final Officersdata od = new Officersdata();
        final Personneldata pd = new Personneldata();
        od.setPersonneldata(pd);
        pd.setOfficersdata(od);
        if(pd.getPersonnelpostingList() == null) {
            final List<Personnelposting> ppList = new ArrayList<>();
            pd.setPersonnelpostingList(ppList);
        }
        final Personnelposting pp = new Personnelposting();
        pd.getPersonnelpostingList().add(pp);
        return pd;
    }
    
    public Object getEntity_old() {
        final Personneldata pd = new Personneldata();
        if(pd.getPersonnelpostingList() == null) {
            final List<Personnelposting> ppList = new ArrayList<>();
            pd.setPersonnelpostingList(ppList);
        }
        final Personnelposting pp = new Personnelposting();
        pd.getPersonnelpostingList().add(pp);
        return pd;
    }
}
