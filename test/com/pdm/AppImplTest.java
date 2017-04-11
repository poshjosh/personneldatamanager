package com.pdm;

import com.bc.appcore.jpa.model.ResultModel;
import com.pdm.pu.entities.Gender;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Rank;
import com.pdm.pu.entities.Stateoforigin;
import java.util.Date;
import com.bc.appbase.App;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 13, 2017 9:52:58 PM
 */
public class AppImplTest {

    public static void main(String [] args) {
        
        try{
            
            final App app = TestApp.getInstance();

            final ResultModel rm = app.getResultModel(null, null);
            Personneldata pd = new Personneldata();
            pd.setDateofbirth(new Date());
            pd.setFirstname("Chinomso");
            pd.setGender(new Gender((short)1, "Male", "M"));
            pd.setSurname("Ikwuagwu");
            pd.setLocalgovernmentarea("BENDE");
            pd.setMiddlename("Bassey");
            pd.setOfficersdata(new Officersdata(1, "49", new Date()));
            pd.setPersonneldataid(1);
            pd.setRank(new Rank((short)1, "Wing Commander", "Wing Cdr"));
            pd.setSeniority(new Date());
            pd.setServicenumber("2597");
            pd.setStateoforigin(new Stateoforigin((short)1, "Abia"));
            Object value = rm.get(pd, 0, Officersdata_.course.getName());
System.out.println("=======================: " + value);
        }catch(Throwable t) {
            
            throw new RuntimeException(t);
        }
    }
}
