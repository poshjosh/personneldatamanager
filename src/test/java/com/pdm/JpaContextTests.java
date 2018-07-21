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

import com.bc.jpa.context.JpaContext;
import com.bc.jpa.context.JpaContextImpl;
import com.pdm.pu.entities.Localgovernmentarea;
import com.pdm.pu.entities.Personneldata;
import com.pdm.pu.entities.Stateoforigin;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.junit.Test;
import com.bc.jpa.dao.Select;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 5, 2017 12:08:19 PM
 */
public class JpaContextTests {

    private final JpaContext jpa;

    public JpaContextTests() {
        final URL persistenceURL = Thread.currentThread().getContextClassLoader().getResource("META-INF/persistence.xml");
        try{
            final URI persistenceURI = persistenceURL.toURI();
            this.jpa = new JpaContextImpl(persistenceURI, null);
        }catch(URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void repopulateLgas() {
        
        try(Select<Localgovernmentarea> dao = this.jpa.getDaoForSelect(Localgovernmentarea.class)) {
            
            final List<Localgovernmentarea> resultList = dao.createQuery().getResultList();
          
            final Iterator<Localgovernmentarea> iter = resultList.iterator();
            
            final String empty = "";
            
            Stateoforigin previous = null;
            
            int i = 0;
            
            while(iter.hasNext()) {
                final Localgovernmentarea lga = iter.next();
                final Stateoforigin current = lga.getStateoforigin();
                Objects.requireNonNull(current);
                boolean startedNewState = !current.equals(previous);
//(1, 1, 'Aba South')                
                if(previous != null && startedNewState) {
                    this.print(false, ++i, previous.getStateoforiginid(), "Other-"+previous.getStateoforigin(), ", ");
                }  
                final String name = lga.getLocalgovernmentarea().replace("'", "''");
//                final String suffix = iter.hasNext() ? ", " : empty;
                final String suffix = ", ";
                this.print(startedNewState, ++i, current.getStateoforiginid(), name, suffix);
                previous = current;
            }
            if(previous != null) {
                this.print(false, ++i, previous.getStateoforiginid(), "Other-"+previous.getStateoforigin(), empty);
            }
System.out.println();            
        }
    }
    private void print(boolean startedNewState, Object lgaid, Short stateid, String name, String suffix) {
        final String prefix = this.getPrefix(startedNewState);
        this.print(prefix, lgaid, stateid, name, suffix);
    }
    private String getPrefix(boolean startedNewState) {
        return startedNewState ? "\n" : "";
    }
    private void print(String prefix, Object lgaid, Object stateid, String name, String suffix) {
        System.out.print(prefix+"("+lgaid+", "+stateid+", '"+name+"')"+suffix);
    }
}
