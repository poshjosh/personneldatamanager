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
@Table(name = "speciality")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Speciality.findAll", query = "SELECT s FROM Speciality s"),
    @NamedQuery(name = "Speciality.findBySpecialityid", query = "SELECT s FROM Speciality s WHERE s.specialityid = :specialityid"),
    @NamedQuery(name = "Speciality.findBySpeciality", query = "SELECT s FROM Speciality s WHERE s.speciality = :speciality"),
    @NamedQuery(name = "Speciality.findByAbbreviation", query = "SELECT s FROM Speciality s WHERE s.abbreviation = :abbreviation")})
public class Speciality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "specialityid")
    private Short specialityid;
    @Basic(optional = false)
    @Column(name = "speciality")
    private String speciality;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "speciality", fetch = FetchType.LAZY)
    private List<Officersdata> officersdataList;

    public Speciality() {
    }

    public Speciality(Short specialityid) {
        this.specialityid = specialityid;
    }

    public Speciality(Short specialityid, String speciality, String abbreviation) {
        this.specialityid = specialityid;
        this.speciality = speciality;
        this.abbreviation = abbreviation;
    }

    public Short getSpecialityid() {
        return specialityid;
    }

    public void setSpecialityid(Short specialityid) {
        this.specialityid = specialityid;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public List<Officersdata> getOfficersdataList() {
        return officersdataList;
    }

    public void setOfficersdataList(List<Officersdata> officersdataList) {
        this.officersdataList = officersdataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (specialityid != null ? specialityid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Speciality)) {
            return false;
        }
        Speciality other = (Speciality) object;
        if ((this.specialityid == null && other.specialityid != null) || (this.specialityid != null && !this.specialityid.equals(other.specialityid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Speciality[ specialityid=" + specialityid + " ]";
    }

}
