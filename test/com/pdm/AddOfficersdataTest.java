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

import com.bc.appbase.ui.EntryPanel;
import com.pdm.pu.entities.Appointment;
import com.pdm.pu.entities.Commissiontype;
import com.pdm.pu.entities.Gender;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Rank;
import com.pdm.pu.entities.Speciality;
import com.pdm.pu.entities.Stateoforigin;
import java.awt.Component;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.bc.appbase.App;
import com.bc.appbase.ui.ComponentModel;
import com.bc.appcore.actions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.bc.appcore.predicates.Equals;
import com.bc.util.JsonBuilder;
import com.pdm.pu.entities.Personneldata_;
import com.pdm.ui.actions.PdmActionCommands;
import com.pdm.util.MapFromEntityBuilder;
import java.awt.Container;
import java.io.IOException;
import java.util.function.Predicate;
import javax.swing.JPanel;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:25:56 PM
 */
public class AddOfficersdataTest {

    @Test
    public void testAll() throws ParameterException, TaskExecutionException {
        
        final PdmAppImpl app = TestApp.getInstance();
        
        final Container ui = (Container)app.getAction(PdmActionCommands.DISPLAY_ADD_OFFICERDATA_UI).execute(app, Collections.EMPTY_MAP);
   
        final ComponentModel cm = app.get(ComponentModel.class);
        
        final Map<String, Object> data = this.getData1(app);
        
        try{
            JsonBuilder jsonBuilder = new JsonBuilder(true, true, "  ");
            jsonBuilder.appendJSONString(data, System.out);
        }catch(IOException e) { e.printStackTrace(); }
        
        this.update((JPanel)ui, cm, data);
        
        app.getAction(PdmActionCommands.ADD_OFFICERSDATA).execute(
                app, Collections.singletonMap(ui.getClass().getName(), ui));
    }
    
    public void update(JPanel ui, ComponentModel cm, Map<String, Object> data) throws ParameterException, TaskExecutionException {
System.out.println("= = = = = == = Building component named: "+ui.getName()+", of type: "+ui.getClass().getName());

        final int count = ui.getComponentCount();
        
        for(int i=0; i<count; i++) {
            
            final Component c = ui.getComponent(i);
            
System.out.println("Found component named: "+c.getName()+", of type: "+c.getClass().getName());            
            
            if(c instanceof EntryPanel) {
                
                EntryPanel entryPanel = (EntryPanel)c;
                Component component = entryPanel.getEntryComponent();

                Object value = data.get(component.getName());
System.out.println("----------------------------------------");                
System.out.println("Setting: "+component.getName()+" to: "+value+" in UI component: "+component.getClass().getSimpleName());            
                cm.setValue(component, value);
            }else if(c instanceof JPanel) {
                
                this.update((JPanel)c, cm, data);
            }
        }    
    }
    
    private Map<String, Object> getData1(App app) {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final Map<String, Object> od = new HashMap();
        od.put("commissiontype", app.getDao().find(Commissiontype.class, (short)2));
        od.put("course", "37");
        cal.set(1990, 8, 22);
        od.put("dateofcommission", cal.getTime());
        od.put("appointment", app.getDao().find(Appointment.class, 7));
        cal.set(1966, 9, 3);
        od.put("dateofbirth", cal.getTime());
        od.put("firstname", "Bello");
        od.put("gender", app.getDao().find(Gender.class, (short)2));
        od.put("localgovernmentarea", "LGANKA");
        od.put("middlename", "Ade");
//        pd.setOfficersdata(od);
        od.put("rank", app.getDao().find(Rank.class, (short)4));
        cal.set(2015, 8, 19);
        od.put("seniority", cal.getTime());
        od.put("servicenumber", "2197");
        od.put("stateoforigin", app.getDao().find(Stateoforigin.class, (short)1));
        od.put("surname", "Nwosu");
//        od.setPersonneldata(pd);
        od.put("speciality", app.getDao().find(Speciality.class, (short)1));
        return od;
    }
    
    private Map<String, Object> getData(App app) {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final Map<String, Object> od = new HashMap();
        od.put("commissiontype", app.getDao().find(Commissiontype.class, (short)1));
        od.put("course", "49");
        cal.set(2002, 8, 19);
        od.put("dateofcommission", cal.getTime());
        od.put("appointment", app.getDao().find(Appointment.class, 2));
        cal.set(1978, 4, 9);
        od.put("dateofbirth", cal.getTime());
        od.put("firstname", "Chinomso");
        od.put("gender", app.getDao().find(Gender.class, (short)1));
        od.put("localgovernmentarea", "BENDE");
        od.put("middlename", "Bassey");
//        pd.setOfficersdata(od);
        od.put("rank", app.getDao().find(Rank.class, (short)6));
        cal.set(2015, 8, 19);
        od.put("seniority", cal.getTime());
        od.put("servicenumber", "2597");
        od.put("stateoforigin", app.getDao().find(Stateoforigin.class, (short)1));
        od.put("surname", "Ikwuagwu");
//        od.setPersonneldata(pd);
        od.put("speciality", app.getDao().find(Speciality.class, (short)1));
        return od;
    }
    
    private Map<String, Object> getData_old(App app) {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        final Officersdata od = new Officersdata();
        od.setCommissiontype(app.getDao().find(Commissiontype.class, (short)1));
        od.setCourse("49");
        cal.set(2002, 8, 19);
        od.setDateofcommission(cal.getTime());
        final Personneldata pd = new Personneldata();
        pd.setAppointment(app.getDao().find(Appointment.class, 2));
        cal.set(1978, 4, 9);
        pd.setDateofbirth(cal.getTime());
        pd.setFirstname("Chinomso");
        pd.setGender(app.getDao().find(Gender.class, (short)1));
        pd.setLocalgovernmentarea("BENDE");
        pd.setMiddlename("Bassey");
        pd.setOfficersdata(od);
        pd.setRank(app.getDao().find(Rank.class, (short)6));
        cal.set(2015, 8, 19);
        pd.setSeniority(cal.getTime());
        pd.setServicenumber("2597");
        pd.setStateoforigin(app.getDao().find(Stateoforigin.class, (short)1));
        pd.setSurname("Ikwuagwu");
        od.setPersonneldata(pd);
        od.setSpeciality(app.getDao().find(Speciality.class, (short)1));
        
        final Predicate<String> columnNameTest = new Equals(Personneldata_.airmansdata.getName()).negate();
        
        final Map map = new MapFromEntityBuilder((PdmApp)app, columnNameTest)
                .source(od)
                .build();
        return map;
    }
}
