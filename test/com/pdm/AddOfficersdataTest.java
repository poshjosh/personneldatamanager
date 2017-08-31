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

import com.bc.appbase.ui.FormEntryPanel;
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
import com.bc.appbase.ui.actions.ParamNames;
import com.bc.appcore.exceptions.TaskExecutionException;
import com.bc.appcore.parameter.ParameterException;
import com.bc.util.JsonBuilder;
import com.bc.util.MapBuilder;
import com.pdm.pu.entities.Localgovernmentarea;
import com.pdm.pu.entities.Personnelposting;
import com.pdm.pu.entities.Unit;
import java.awt.Container;
import java.io.IOException;
import javax.swing.JPanel;
import com.pdm.ui.actions.PdmActionCommands;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:25:56 PM
 */
public class AddOfficersdataTest {

    @Test
    public void testAll() throws ParameterException, TaskExecutionException {
        
        final PdmApp app = TestApp.getInstance(true);
        
        app.getAttributes().put(ParamNames.ENTITY_TYPE, Officersdata.class);
        
        final Container ui = (Container)app.getAction(PdmActionCommands.DISPLAY_ADD_CURRENT_ENTITY_TYPE_UI).execute(app, Collections.EMPTY_MAP);
   
        final ComponentModel cm = app.getOrException(ComponentModel.class);
        
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
            
            if(c instanceof FormEntryPanel) {
                
                FormEntryPanel entryPanel = (FormEntryPanel)c;
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
        od.put("commissiontype", app.getDao(Commissiontype.class).find(Commissiontype.class, (short)2));
        od.put("course", "37");
        cal.set(1990, 8, 22);
        od.put("dateofcommission", cal.getTime());
        od.put("appointment", app.getDao(Appointment.class).find(Appointment.class, 7));
        od.put("unit", app.getDao(Unit.class).find(Unit.class, 23));
        cal.set(1966, 9, 3);
        od.put("dateofbirth", cal.getTime());
        od.put("firstname", "Bello");
        od.put("gender", app.getDao(Gender.class).find(Gender.class, (short)2));
        od.put("localgovernmentarea", "LGANKA");
        od.put("middlename", "Ade");
//        pd.setOfficersdata(od);
        od.put("rank", app.getDao(Rank.class).find(Rank.class, (short)4));
        cal.set(2015, 8, 19);
        od.put("seniority", cal.getTime());
        od.put("servicenumber", "2197");
        od.put("stateoforigin", app.getDao(Stateoforigin.class).find(Stateoforigin.class, (short)1));
        od.put("surname", "Nwosu");
//        od.setPersonneldata(pd);
        od.put("speciality", app.getDao(Speciality.class).find(Speciality.class, (short)1));
        return od;
    }
    
    private Map<String, Object> getData(App app) {
        final Map<String, Object> od = new HashMap();
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        Personnelposting pp = new Personnelposting();
        pp.setAppointment(app.getDao(Appointment.class).find(Appointment.class, 2));
        cal.set(2015, 8, 16);
        pp.setDatetakenonstrength(cal.getTime());
//        pp.setPersonneldata(personneldata);
        pp.setUnit(app.getDao(Unit.class).find(Unit.class, 3));
        od.put("commissiontype", app.getDao(Commissiontype.class).find(Commissiontype.class, (short)1));
        od.put("course", "49");
        cal.set(2002, 8, 19);
        od.put("dateofcommission", cal.getTime());
        od.put("appointment", app.getDao(Appointment.class).find(Appointment.class, 2));
        cal.set(1978, 4, 9);
        od.put("dateofbirth", cal.getTime());
        od.put("firstname", "Chinomso");
        od.put("gender", app.getDao(Gender.class).find(Gender.class, (short)1));
        od.put("localgovernmentarea", app.getDao(Localgovernmentarea.class).find(Localgovernmentarea.class, 3));
        od.put("middlename", "Bassey");
//        pd.setOfficersdata(od);
        od.put("rank", app.getDao(Rank.class).find(Rank.class, (short)6));
        cal.set(2015, 8, 19);
        od.put("seniority", cal.getTime());
        od.put("servicenumber", "2597");
        od.put("stateoforigin", app.getDao(Stateoforigin.class).find(Stateoforigin.class, (short)1));
        od.put("surname", "Ikwuagwu");
//        od.setPersonneldata(pd);
        od.put("speciality", app.getDao(Speciality.class).find(Speciality.class, (short)1));
        return od;
    }
    
    private Map<String, Object> getData_old(App app) {
        final Calendar cal = Calendar.getInstance();
        cal.clear();
        Personnelposting persposting = new Personnelposting();
        persposting.setAppointment(app.getDao(Appointment.class).find(Appointment.class, 2));
        cal.set(2015, 8, 16);
        persposting.setDatetakenonstrength(cal.getTime());
        persposting.setUnit(app.getDao(Unit.class).find(Unit.class, 3));
        
        final Officersdata offrsdata = new Officersdata();
        offrsdata.setCommissiontype(app.getDao(Commissiontype.class).find(Commissiontype.class, (short)1));
        offrsdata.setCourseonentry("49");
        cal.set(2002, 8, 19);
        offrsdata.setDateofcommission(cal.getTime());
        
        final Personneldata persdata = new Personneldata();
        persposting.setPersonneldata(persdata);
        
        cal.set(1978, 4, 9);
        persdata.setDateofbirth(cal.getTime());
        persdata.setFirstname("Chinomso");
        persdata.setGender(app.getDao(Gender.class).find(Gender.class, (short)1));
        persdata.setLocalgovernmentarea(app.getDao(Localgovernmentarea.class).find(Localgovernmentarea.class, (short)1));
        persdata.setMiddlename("Bassey");
        persdata.setOfficersdata(offrsdata);
        persdata.setRank(app.getDao(Rank.class).find(Rank.class, (short)6));
        cal.set(2015, 8, 19);
        persdata.setSeniority(cal.getTime());
        persdata.setServicenumber("2597");
        persdata.setSurname("Ikwuagwu");
        offrsdata.setPersonneldata(persdata);
        offrsdata.setSpeciality(app.getDao(Speciality.class).find(Speciality.class, (short)1));
        
        final Map map = app.getOrException(MapBuilder.class)
                .source(offrsdata)
                .build();
        return map;
    }
}
