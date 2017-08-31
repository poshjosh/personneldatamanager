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

import com.bc.appcore.AbstractUser;
import com.pdm.pu.entities.Appointment;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.security.auth.login.LoginException;

/**
 * @author Chinomso Bassey Ikwuagwu on Aug 17, 2017 6:40:02 PM
 */
public class PdmUserImpl extends AbstractUser implements PdmUser, Serializable {

    private final Appointment appointment;
    
    public PdmUserImpl(Appointment appointment) {
        this.appointment = Objects.requireNonNull(appointment);
    }

    @Override
    public boolean create(Map params) throws LoginException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean login(String email, String name, char[] pass) throws LoginException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean login(Map params) throws LoginException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Appointment getAppointment() {
        return appointment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.appointment);
        hash = 89 * hash + super.hashCode();
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PdmUserImpl other = (PdmUserImpl) obj;
        if (!Objects.equals(this.isLoggedIn(), other.isLoggedIn())) {
            return false;
        }
        if (!Objects.equals(this.getName(), other.getName())) {
            return false;
        }
        if (!Objects.equals(this.appointment, other.appointment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + "Name=" + this.getName() + ", loggedIn=" + this.isLoggedIn() + ", appointment=" + (appointment==null?null:appointment.getAbbreviation()) + '}';
    }
}
