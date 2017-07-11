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
@Table(name = "unit")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Unit.findAll", query = "SELECT u FROM Unit u"),
    @NamedQuery(name = "Unit.findByUnitid", query = "SELECT u FROM Unit u WHERE u.unitid = :unitid"),
    @NamedQuery(name = "Unit.findByUnit", query = "SELECT u FROM Unit u WHERE u.unit = :unit"),
    @NamedQuery(name = "Unit.findByAbbreviation", query = "SELECT u FROM Unit u WHERE u.abbreviation = :abbreviation"),
    @NamedQuery(name = "Unit.findByUnitsortorder", query = "SELECT u FROM Unit u WHERE u.unitsortorder = :unitsortorder")})
public class Unit implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "unitid")
    private Integer unitid;
    @Basic(optional = false)
    @Column(name = "unit")
    private String unit;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "unitsortorder")
    private Integer unitsortorder;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "unit", fetch = FetchType.LAZY)
    private List<Personnelposting> personnelpostingList;
    @JoinColumn(name = "unittype", referencedColumnName = "unittypeid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Unittype unittype;
    @OneToMany(mappedBy = "parentunit", fetch = FetchType.LAZY)
    private List<Unit> unitList;
    @JoinColumn(name = "parentunit", referencedColumnName = "unitid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Unit parentunit;

    public Unit() {
    }

    public Unit(Integer unitid) {
        this.unitid = unitid;
    }

    public Unit(Integer unitid, String unit, String abbreviation) {
        this.unitid = unitid;
        this.unit = unit;
        this.abbreviation = abbreviation;
    }

    public Integer getUnitid() {
        return unitid;
    }

    public void setUnitid(Integer unitid) {
        this.unitid = unitid;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Integer getUnitsortorder() {
        return unitsortorder;
    }

    public void setUnitsortorder(Integer unitsortorder) {
        this.unitsortorder = unitsortorder;
    }

    @XmlTransient
    public List<Personnelposting> getPersonnelpostingList() {
        return personnelpostingList;
    }

    public void setPersonnelpostingList(List<Personnelposting> personnelpostingList) {
        this.personnelpostingList = personnelpostingList;
    }

    public Unittype getUnittype() {
        return unittype;
    }

    public void setUnittype(Unittype unittype) {
        this.unittype = unittype;
    }

    @XmlTransient
    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    public Unit getParentunit() {
        return parentunit;
    }

    public void setParentunit(Unit parentunit) {
        this.parentunit = parentunit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitid != null ? unitid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Unit)) {
            return false;
        }
        Unit other = (Unit) object;
        if ((this.unitid == null && other.unitid != null) || (this.unitid != null && !this.unitid.equals(other.unitid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Unit[ unitid=" + unitid + " ]";
    }

}
