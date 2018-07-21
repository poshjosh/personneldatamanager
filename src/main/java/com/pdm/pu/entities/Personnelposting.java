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

package com.pdm.pu.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:32 PM
 */
@Entity
@Table(name = "personnelposting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personnelposting.findAll", query = "SELECT p FROM Personnelposting p"),
    @NamedQuery(name = "Personnelposting.findByPersonnelpostingid", query = "SELECT p FROM Personnelposting p WHERE p.personnelpostingid = :personnelpostingid"),
    @NamedQuery(name = "Personnelposting.findByDatetakenonstrength", query = "SELECT p FROM Personnelposting p WHERE p.datetakenonstrength = :datetakenonstrength")})
public class Personnelposting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personnelpostingid")
    private Integer personnelpostingid;
    @Column(name = "datetakenonstrength")
    @Temporal(TemporalType.DATE)
    private Date datetakenonstrength;
    @JoinColumn(name = "personneldata", referencedColumnName = "personneldataid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personneldata personneldata;
    @JoinColumn(name = "unit", referencedColumnName = "unitid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unit unit;
    @JoinColumn(name = "appointment", referencedColumnName = "appointmentid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

    public Personnelposting() {
    }

    public Personnelposting(Integer personnelpostingid) {
        this.personnelpostingid = personnelpostingid;
    }

    public Integer getPersonnelpostingid() {
        return personnelpostingid;
    }

    public void setPersonnelpostingid(Integer personnelpostingid) {
        this.personnelpostingid = personnelpostingid;
    }

    public Date getDatetakenonstrength() {
        return datetakenonstrength;
    }

    public void setDatetakenonstrength(Date datetakenonstrength) {
        this.datetakenonstrength = datetakenonstrength;
    }

    public Personneldata getPersonneldata() {
        return personneldata;
    }

    public void setPersonneldata(Personneldata personneldata) {
        this.personneldata = personneldata;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personnelpostingid != null ? personnelpostingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personnelposting)) {
            return false;
        }
        Personnelposting other = (Personnelposting) object;
        if ((this.personnelpostingid == null && other.personnelpostingid != null) || (this.personnelpostingid != null && !this.personnelpostingid.equals(other.personnelpostingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Personnelposting[ personnelpostingid=" + personnelpostingid + " ]";
    }

}
