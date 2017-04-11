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
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:13:09 PM
 */
@Entity
@Table(name = "personneldata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personneldata.findAll", query = "SELECT p FROM Personneldata p"),
    @NamedQuery(name = "Personneldata.findByPersonneldataid", query = "SELECT p FROM Personneldata p WHERE p.personneldataid = :personneldataid"),
    @NamedQuery(name = "Personneldata.findByServicenumber", query = "SELECT p FROM Personneldata p WHERE p.servicenumber = :servicenumber"),
    @NamedQuery(name = "Personneldata.findByFirstname", query = "SELECT p FROM Personneldata p WHERE p.firstname = :firstname"),
    @NamedQuery(name = "Personneldata.findByMiddlename", query = "SELECT p FROM Personneldata p WHERE p.middlename = :middlename"),
    @NamedQuery(name = "Personneldata.findBySurname", query = "SELECT p FROM Personneldata p WHERE p.surname = :surname"),
    @NamedQuery(name = "Personneldata.findBySeniority", query = "SELECT p FROM Personneldata p WHERE p.seniority = :seniority"),
    @NamedQuery(name = "Personneldata.findByDateofbirth", query = "SELECT p FROM Personneldata p WHERE p.dateofbirth = :dateofbirth"),
    @NamedQuery(name = "Personneldata.findByLocalgovernmentarea", query = "SELECT p FROM Personneldata p WHERE p.localgovernmentarea = :localgovernmentarea")})
public class Personneldata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personneldataid")
    private Integer personneldataid;
    @Basic(optional = false)
    @Column(name = "servicenumber")
    private String servicenumber;
    @Basic(optional = false)
    @Column(name = "firstname")
    private String firstname;
    @Basic(optional = false)
    @Column(name = "middlename")
    private String middlename;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "seniority")
    @Temporal(TemporalType.DATE)
    private Date seniority;
    @Basic(optional = false)
    @Column(name = "dateofbirth")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Basic(optional = false)
    @Column(name = "localgovernmentarea")
    private String localgovernmentarea;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personneldata", fetch = FetchType.LAZY)
    private Officersdata officersdata;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personneldata", fetch = FetchType.LAZY)
    private Airmansdata airmansdata;
    @JoinColumn(name = "rank", referencedColumnName = "rankid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rank rank;
    @JoinColumn(name = "appointment", referencedColumnName = "appointmentid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Appointment appointment;
    @JoinColumn(name = "gender", referencedColumnName = "genderid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gender gender;
    @JoinColumn(name = "stateoforigin", referencedColumnName = "stateoforiginid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stateoforigin stateoforigin;

    public Personneldata() {
    }

    public Personneldata(Integer personneldataid) {
        this.personneldataid = personneldataid;
    }

    public Personneldata(Integer personneldataid, String servicenumber, String firstname, String middlename, String surname, Date seniority, Date dateofbirth, String localgovernmentarea) {
        this.personneldataid = personneldataid;
        this.servicenumber = servicenumber;
        this.firstname = firstname;
        this.middlename = middlename;
        this.surname = surname;
        this.seniority = seniority;
        this.dateofbirth = dateofbirth;
        this.localgovernmentarea = localgovernmentarea;
    }

    public Integer getPersonneldataid() {
        return personneldataid;
    }

    public void setPersonneldataid(Integer personneldataid) {
        this.personneldataid = personneldataid;
    }

    public String getServicenumber() {
        return servicenumber;
    }

    public void setServicenumber(String servicenumber) {
        this.servicenumber = servicenumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getSeniority() {
        return seniority;
    }

    public void setSeniority(Date seniority) {
        this.seniority = seniority;
    }

    public Date getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getLocalgovernmentarea() {
        return localgovernmentarea;
    }

    public void setLocalgovernmentarea(String localgovernmentarea) {
        this.localgovernmentarea = localgovernmentarea;
    }

    public Officersdata getOfficersdata() {
        return officersdata;
    }

    public void setOfficersdata(Officersdata officersdata) {
        this.officersdata = officersdata;
    }

    public Airmansdata getAirmansdata() {
        return airmansdata;
    }

    public void setAirmansdata(Airmansdata airmansdata) {
        this.airmansdata = airmansdata;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Stateoforigin getStateoforigin() {
        return stateoforigin;
    }

    public void setStateoforigin(Stateoforigin stateoforigin) {
        this.stateoforigin = stateoforigin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personneldataid != null ? personneldataid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personneldata)) {
            return false;
        }
        Personneldata other = (Personneldata) object;
        if ((this.personneldataid == null && other.personneldataid != null) || (this.personneldataid != null && !this.personneldataid.equals(other.personneldataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Personneldata[ personneldataid=" + personneldataid + " ]";
    }

}
