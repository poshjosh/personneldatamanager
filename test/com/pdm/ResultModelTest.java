package com.pdm;

import com.pdm.pu.entities.Gender;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Officersdata_;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Rank;
import java.util.Date;
import com.bc.appbase.App;
import com.pdm.pu.entities.Localgovernmentarea;
import com.bc.appcore.jpa.model.EntityResultModel;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 13, 2017 9:52:58 PM
 */
public class ResultModelTest {

    public static void main(String [] args) {
        
        try{
            
            final App app = TestApp.getInstance(true);

            final EntityResultModel rm = app.getResultModel(null, null);
            Personneldata pd = new Personneldata();
            pd.setDateofbirth(new Date());
            pd.setFirstname("Chinomso");
            pd.setGender(new Gender((short)1, "Male", "M"));
            pd.setSurname("Ikwuagwu");
            pd.setLocalgovernmentarea(new Localgovernmentarea(1, "BENDE"));
            pd.setMiddlename("Bassey");
            pd.setOfficersdata(new Officersdata(1));
            pd.setPersonneldataid(1);
            pd.setRank(new Rank((short)1, "Wing Commander", "Wing Cdr"));
            pd.setSeniority(new Date());
            pd.setServicenumber("2597");
            final int col = rm.getColumnNames().indexOf(Officersdata_.courseonentry.getName());
            Object value = rm.get(pd, 0, col);
System.out.println("=======================: " + value);
        }catch(Throwable t) {
            
            throw new RuntimeException(t);
        }
    }
}
