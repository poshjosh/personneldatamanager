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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:33 PM
 */
@Entity
@Table(name = "commissiontype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commissiontype.findAll", query = "SELECT c FROM Commissiontype c"),
    @NamedQuery(name = "Commissiontype.findByCommissiontypeid", query = "SELECT c FROM Commissiontype c WHERE c.commissiontypeid = :commissiontypeid"),
    @NamedQuery(name = "Commissiontype.findByCommissiontype", query = "SELECT c FROM Commissiontype c WHERE c.commissiontype = :commissiontype"),
    @NamedQuery(name = "Commissiontype.findByAbbreviation", query = "SELECT c FROM Commissiontype c WHERE c.abbreviation = :abbreviation")})
public class Commissiontype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "commissiontypeid")
    private Short commissiontypeid;
    @Basic(optional = false)
    @Column(name = "commissiontype")
    private String commissiontype;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(mappedBy = "commissiontype", fetch = FetchType.LAZY)
    private List<Officersdata> officersdataList;

    public Commissiontype() {
    }

    public Commissiontype(Short commissiontypeid) {
        this.commissiontypeid = commissiontypeid;
    }

    public Commissiontype(Short commissiontypeid, String commissiontype, String abbreviation) {
        this.commissiontypeid = commissiontypeid;
        this.commissiontype = commissiontype;
        this.abbreviation = abbreviation;
    }

    public Short getCommissiontypeid() {
        return commissiontypeid;
    }

    public void setCommissiontypeid(Short commissiontypeid) {
        this.commissiontypeid = commissiontypeid;
    }

    public String getCommissiontype() {
        return commissiontype;
    }

    public void setCommissiontype(String commissiontype) {
        this.commissiontype = commissiontype;
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
        hash += (commissiontypeid != null ? commissiontypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commissiontype)) {
            return false;
        }
        Commissiontype other = (Commissiontype) object;
        if ((this.commissiontypeid == null && other.commissiontypeid != null) || (this.commissiontypeid != null && !this.commissiontypeid.equals(other.commissiontypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Commissiontype[ commissiontypeid=" + commissiontypeid + " ]";
    }

}
