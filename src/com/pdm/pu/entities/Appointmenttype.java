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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:13:09 PM
 */
@Entity
@Table(name = "appointmenttype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointmenttype.findAll", query = "SELECT a FROM Appointmenttype a"),
    @NamedQuery(name = "Appointmenttype.findByAppointmenttypeid", query = "SELECT a FROM Appointmenttype a WHERE a.appointmenttypeid = :appointmenttypeid"),
    @NamedQuery(name = "Appointmenttype.findByAppointmenttype", query = "SELECT a FROM Appointmenttype a WHERE a.appointmenttype = :appointmenttype"),
    @NamedQuery(name = "Appointmenttype.findByAbbreviation", query = "SELECT a FROM Appointmenttype a WHERE a.abbreviation = :abbreviation")})
public class Appointmenttype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "appointmenttypeid")
    private Short appointmenttypeid;
    @Basic(optional = false)
    @Column(name = "appointmenttype")
    private String appointmenttype;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appointmenttype", fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;

    public Appointmenttype() {
    }

    public Appointmenttype(Short appointmenttypeid) {
        this.appointmenttypeid = appointmenttypeid;
    }

    public Appointmenttype(Short appointmenttypeid, String appointmenttype, String abbreviation) {
        this.appointmenttypeid = appointmenttypeid;
        this.appointmenttype = appointmenttype;
        this.abbreviation = abbreviation;
    }

    public Short getAppointmenttypeid() {
        return appointmenttypeid;
    }

    public void setAppointmenttypeid(Short appointmenttypeid) {
        this.appointmenttypeid = appointmenttypeid;
    }

    public String getAppointmenttype() {
        return appointmenttype;
    }

    public void setAppointmenttype(String appointmenttype) {
        this.appointmenttype = appointmenttype;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmenttypeid != null ? appointmenttypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointmenttype)) {
            return false;
        }
        Appointmenttype other = (Appointmenttype) object;
        if ((this.appointmenttypeid == null && other.appointmenttypeid != null) || (this.appointmenttypeid != null && !this.appointmenttypeid.equals(other.appointmenttypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Appointmenttype[ appointmenttypeid=" + appointmenttypeid + " ]";
    }

}
