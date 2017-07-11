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
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:32 PM
 */
@Entity
@Table(name = "unittype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unittype.findAll", query = "SELECT u FROM Unittype u"),
    @NamedQuery(name = "Unittype.findByUnittypeid", query = "SELECT u FROM Unittype u WHERE u.unittypeid = :unittypeid"),
    @NamedQuery(name = "Unittype.findByUnittype", query = "SELECT u FROM Unittype u WHERE u.unittype = :unittype"),
    @NamedQuery(name = "Unittype.findByAbbreviation", query = "SELECT u FROM Unittype u WHERE u.abbreviation = :abbreviation")})
public class Unittype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unittypeid")
    private Short unittypeid;
    @Basic(optional = false)
    @Column(name = "unittype")
    private String unittype;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unittype", fetch = FetchType.LAZY)
    private List<Unit> unitList;

    public Unittype() {
    }

    public Unittype(Short unittypeid) {
        this.unittypeid = unittypeid;
    }

    public Unittype(Short unittypeid, String unittype, String abbreviation) {
        this.unittypeid = unittypeid;
        this.unittype = unittype;
        this.abbreviation = abbreviation;
    }

    public Short getUnittypeid() {
        return unittypeid;
    }

    public void setUnittypeid(Short unittypeid) {
        this.unittypeid = unittypeid;
    }

    public String getUnittype() {
        return unittype;
    }

    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unittypeid != null ? unittypeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unittype)) {
            return false;
        }
        Unittype other = (Unittype) object;
        if ((this.unittypeid == null && other.unittypeid != null) || (this.unittypeid != null && !this.unittypeid.equals(other.unittypeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Unittype[ unittypeid=" + unittypeid + " ]";
    }

}
