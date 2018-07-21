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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:33 PM
 */
@Entity
@Table(name = "officersdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Officersdata.findAll", query = "SELECT o FROM Officersdata o"),
    @NamedQuery(name = "Officersdata.findByOfficersdataid", query = "SELECT o FROM Officersdata o WHERE o.officersdataid = :officersdataid"),
    @NamedQuery(name = "Officersdata.findByCourseonentry", query = "SELECT o FROM Officersdata o WHERE o.courseonentry = :courseonentry"),
    @NamedQuery(name = "Officersdata.findByDateofcommission", query = "SELECT o FROM Officersdata o WHERE o.dateofcommission = :dateofcommission")})
public class Officersdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "officersdataid")
    private Integer officersdataid;
    @Column(name = "courseonentry")
    private String courseonentry;
    @Column(name = "dateofcommission")
    @Temporal(TemporalType.DATE)
    private Date dateofcommission;
    @JoinColumn(name = "personneldata", referencedColumnName = "personneldataid")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Personneldata personneldata;
    @JoinColumn(name = "commissiontype", referencedColumnName = "commissiontypeid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Commissiontype commissiontype;
    @JoinColumn(name = "speciality", referencedColumnName = "specialityid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Speciality speciality;

    public Officersdata() {
    }

    public Officersdata(Integer officersdataid) {
        this.officersdataid = officersdataid;
    }

    public Integer getOfficersdataid() {
        return officersdataid;
    }

    public void setOfficersdataid(Integer officersdataid) {
        this.officersdataid = officersdataid;
    }

    public String getCourseonentry() {
        return courseonentry;
    }

    public void setCourseonentry(String courseonentry) {
        this.courseonentry = courseonentry;
    }

    public Date getDateofcommission() {
        return dateofcommission;
    }

    public void setDateofcommission(Date dateofcommission) {
        this.dateofcommission = dateofcommission;
    }

    public Personneldata getPersonneldata() {
        return personneldata;
    }

    public void setPersonneldata(Personneldata personneldata) {
        this.personneldata = personneldata;
    }

    public Commissiontype getCommissiontype() {
        return commissiontype;
    }

    public void setCommissiontype(Commissiontype commissiontype) {
        this.commissiontype = commissiontype;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (officersdataid != null ? officersdataid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Officersdata)) {
            return false;
        }
        Officersdata other = (Officersdata) object;
        if ((this.officersdataid == null && other.officersdataid != null) || (this.officersdataid != null && !this.officersdataid.equals(other.officersdataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Officersdata[ officersdataid=" + officersdataid + " ]";
    }

}
