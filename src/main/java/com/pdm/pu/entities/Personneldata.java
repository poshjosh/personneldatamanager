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
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:33 PM
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
    @NamedQuery(name = "Personneldata.findByMobilephonenumber1", query = "SELECT p FROM Personneldata p WHERE p.mobilephonenumber1 = :mobilephonenumber1"),
    @NamedQuery(name = "Personneldata.findByMobilephonenumber2", query = "SELECT p FROM Personneldata p WHERE p.mobilephonenumber2 = :mobilephonenumber2"),
    @NamedQuery(name = "Personneldata.findByEmailaddress", query = "SELECT p FROM Personneldata p WHERE p.emailaddress = :emailaddress")})
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
    @Column(name = "middlename")
    private String middlename;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Column(name = "seniority")
    @Temporal(TemporalType.DATE)
    private Date seniority;
    @Column(name = "dateofbirth")
    @Temporal(TemporalType.DATE)
    private Date dateofbirth;
    @Column(name = "mobilephonenumber1")
    private String mobilephonenumber1;
    @Column(name = "mobilephonenumber2")
    private String mobilephonenumber2;
    @Column(name = "emailaddress")
    private String emailaddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneldata", fetch = FetchType.LAZY)
    private List<Courseattended> courseattendedList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneldata", fetch = FetchType.LAZY)
    private List<Personnelposting> personnelpostingList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personneldata", fetch = FetchType.LAZY)
    private Officersdata officersdata;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "personneldata", fetch = FetchType.LAZY)
    private Airmansdata airmansdata;
    @JoinColumn(name = "rank", referencedColumnName = "rankid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Rank rank;
    @JoinColumn(name = "gender", referencedColumnName = "genderid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Gender gender;
    @JoinColumn(name = "localgovernmentarea", referencedColumnName = "localgovernmentareaid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Localgovernmentarea localgovernmentarea;

    public Personneldata() {
    }

    public Personneldata(Integer personneldataid) {
        this.personneldataid = personneldataid;
    }

    public Personneldata(Integer personneldataid, String servicenumber, String firstname, String surname) {
        this.personneldataid = personneldataid;
        this.servicenumber = servicenumber;
        this.firstname = firstname;
        this.surname = surname;
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

    public String getMobilephonenumber1() {
        return mobilephonenumber1;
    }

    public void setMobilephonenumber1(String mobilephonenumber1) {
        this.mobilephonenumber1 = mobilephonenumber1;
    }

    public String getMobilephonenumber2() {
        return mobilephonenumber2;
    }

    public void setMobilephonenumber2(String mobilephonenumber2) {
        this.mobilephonenumber2 = mobilephonenumber2;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    @XmlTransient
    public List<Courseattended> getCourseattendedList() {
        return courseattendedList;
    }

    public void setCourseattendedList(List<Courseattended> courseattendedList) {
        this.courseattendedList = courseattendedList;
    }

    @XmlTransient
    public List<Personnelposting> getPersonnelpostingList() {
        return personnelpostingList;
    }

    public void setPersonnelpostingList(List<Personnelposting> personnelpostingList) {
        this.personnelpostingList = personnelpostingList;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Localgovernmentarea getLocalgovernmentarea() {
        return localgovernmentarea;
    }

    public void setLocalgovernmentarea(Localgovernmentarea localgovernmentarea) {
        this.localgovernmentarea = localgovernmentarea;
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
