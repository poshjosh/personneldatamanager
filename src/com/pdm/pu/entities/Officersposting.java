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
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:13:08 PM
 */
@Entity
@Table(name = "officersposting")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Officersposting.findAll", query = "SELECT o FROM Officersposting o"),
    @NamedQuery(name = "Officersposting.findByOfficerspostingid", query = "SELECT o FROM Officersposting o WHERE o.officerspostingid = :officerspostingid"),
    @NamedQuery(name = "Officersposting.findByDatetakenonstrength", query = "SELECT o FROM Officersposting o WHERE o.datetakenonstrength = :datetakenonstrength")})
public class Officersposting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "officerspostingid")
    private Integer officerspostingid;
    @Column(name = "datetakenonstrength")
    @Temporal(TemporalType.DATE)
    private Date datetakenonstrength;
    @JoinColumn(name = "officersdata", referencedColumnName = "officersdataid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Officersdata officersdata;
    @JoinColumn(name = "unit", referencedColumnName = "unitid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unit unit;
    @JoinColumn(name = "appointment", referencedColumnName = "appointmentid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment appointment;

    public Officersposting() {
    }

    public Officersposting(Integer officerspostingid) {
        this.officerspostingid = officerspostingid;
    }

    public Integer getOfficerspostingid() {
        return officerspostingid;
    }

    public void setOfficerspostingid(Integer officerspostingid) {
        this.officerspostingid = officerspostingid;
    }

    public Date getDatetakenonstrength() {
        return datetakenonstrength;
    }

    public void setDatetakenonstrength(Date datetakenonstrength) {
        this.datetakenonstrength = datetakenonstrength;
    }

    public Officersdata getOfficersdata() {
        return officersdata;
    }

    public void setOfficersdata(Officersdata officersdata) {
        this.officersdata = officersdata;
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
        hash += (officerspostingid != null ? officerspostingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Officersposting)) {
            return false;
        }
        Officersposting other = (Officersposting) object;
        if ((this.officerspostingid == null && other.officerspostingid != null) || (this.officerspostingid != null && !this.officerspostingid.equals(other.officerspostingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Officersposting[ officerspostingid=" + officerspostingid + " ]";
    }

}
