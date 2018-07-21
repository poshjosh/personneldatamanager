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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:32 PM
 */
@Entity
@Table(name = "appointment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appointment.findAll", query = "SELECT a FROM Appointment a"),
    @NamedQuery(name = "Appointment.findByAppointmentid", query = "SELECT a FROM Appointment a WHERE a.appointmentid = :appointmentid"),
    @NamedQuery(name = "Appointment.findByAppointment", query = "SELECT a FROM Appointment a WHERE a.appointment = :appointment"),
    @NamedQuery(name = "Appointment.findByAbbreviation", query = "SELECT a FROM Appointment a WHERE a.abbreviation = :abbreviation")})
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "appointmentid")
    private Integer appointmentid;
    @Basic(optional = false)
    @Column(name = "appointment")
    private String appointment;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @JoinColumn(name = "appointmenttype", referencedColumnName = "appointmenttypeid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Appointmenttype appointmenttype;
    @OneToMany(mappedBy = "parentappointment", fetch = FetchType.LAZY)
    private List<Appointment> appointmentList;
    @JoinColumn(name = "parentappointment", referencedColumnName = "appointmentid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment parentappointment;
    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private List<Personnelposting> personnelpostingList;

    public Appointment() {
    }

    public Appointment(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public Appointment(Integer appointmentid, String appointment, String abbreviation) {
        this.appointmentid = appointmentid;
        this.appointment = appointment;
        this.abbreviation = abbreviation;
    }

    public Integer getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(Integer appointmentid) {
        this.appointmentid = appointmentid;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Appointmenttype getAppointmenttype() {
        return appointmenttype;
    }

    public void setAppointmenttype(Appointmenttype appointmenttype) {
        this.appointmenttype = appointmenttype;
    }

    @XmlTransient
    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public Appointment getParentappointment() {
        return parentappointment;
    }

    public void setParentappointment(Appointment parentappointment) {
        this.parentappointment = parentappointment;
    }

    @XmlTransient
    public List<Personnelposting> getPersonnelpostingList() {
        return personnelpostingList;
    }

    public void setPersonnelpostingList(List<Personnelposting> personnelpostingList) {
        this.personnelpostingList = personnelpostingList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appointmentid != null ? appointmentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.appointmentid == null && other.appointmentid != null) || (this.appointmentid != null && !this.appointmentid.equals(other.appointmentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Appointment[ appointmentid=" + appointmentid + " ]";
    }

}
