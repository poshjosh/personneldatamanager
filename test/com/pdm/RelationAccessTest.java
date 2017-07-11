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

import com.bc.appcore.util.RelationAccessImpl;
import com.pdm.pu.entities.Officersdata;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Personnelposting;
import com.pdm.pu.entities.Rank;
import com.pdm.pu.entities.Speciality;
import com.pdm.pu.entities.Unit;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import javax.persistence.Entity;

/**
 * @author Chinomso Bassey Ikwuagwu on May 21, 2017 9:49:52 PM
 */
public class RelationAccessTest {
    
    public static void main(String [] args) {
        
        final RelationAccessImpl ra = new RelationAccessImpl();
        Officersdata od = new Officersdata(1);
        Personneldata pd = new Personneldata(2);
        od.setPersonneldata(pd);
        Rank rankd = new Rank((short)3);
        pd.setRank(rankd);
        Speciality s = new Speciality((short)4);
        od.setSpeciality(s);
        final Unit unit1 = new Unit(5);
        final Personnelposting pp1 = new Personnelposting(1);
        pp1.setUnit(unit1);
        final Unit unit2 = new Unit(6);
        final Personnelposting pp2 = new Personnelposting(2);
        pp2.setUnit(unit2);
        final List ppList = Arrays.asList(pp1, pp2);
        pd.setPersonnelpostingList(ppList);
        final Predicate<Class> isEntity = (cls) -> { return cls.getAnnotation(Entity.class) != null; };
System.out.println(ra.getDistinctChildren(od, Unit.class, isEntity, true));
System.out.println(ra.getDistinctChildren(pd, Unit.class, isEntity, true)); 
System.out.println(ra.getDistinctChildren(pp1, Unit.class, isEntity, false));        
System.out.println(ra.getDistinctChildren(pp2, Unit.class, isEntity, false));                
        
final Object found = ra.getDistinctChildren(od, Rank.class, isEntity, true);
System.out.println("Find rank. recurse: true. found: "+found);
final Object found1 = ra.getDistinctChildren(od, Rank.class, isEntity, false);
System.out.println("Find rank. recurse: false. found: "+found1);
final Object found2 = ra.getDistinctChildren(od, Personneldata.class, isEntity, true);
System.out.println("Find Personneldata. recurse: true. found: "+found2);
final Object found3 = ra.getDistinctChildren(od, Personneldata.class, isEntity, false);
System.out.println("Find Personneldata. recurse: false. found: "+found3);

System.out.println(ra.getDistinctChildren(od, Speciality.class, isEntity, true));        
System.out.println(ra.getDistinctChildren(pd, Speciality.class, isEntity, true));        
    }
}
