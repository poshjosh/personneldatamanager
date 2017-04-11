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
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:13:09 PM
 */
@Entity
@Table(name = "airmansdata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Airmansdata.findAll", query = "SELECT a FROM Airmansdata a"),
    @NamedQuery(name = "Airmansdata.findByAirmansdataid", query = "SELECT a FROM Airmansdata a WHERE a.airmansdataid = :airmansdataid"),
    @NamedQuery(name = "Airmansdata.findByDateofenlistment", query = "SELECT a FROM Airmansdata a WHERE a.dateofenlistment = :dateofenlistment")})
public class Airmansdata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "airmansdataid")
    private Integer airmansdataid;
    @Basic(optional = false)
    @Column(name = "dateofenlistment")
    @Temporal(TemporalType.DATE)
    private Date dateofenlistment;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airmansdata", fetch = FetchType.LAZY)
    private List<Airmansposting> airmanspostingList;
    @JoinColumn(name = "personneldata", referencedColumnName = "personneldataid")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Personneldata personneldata;
    @JoinColumn(name = "trade", referencedColumnName = "tradeid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Trade trade;
    @JoinColumn(name = "grade", referencedColumnName = "gradeid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Grade grade;

    public Airmansdata() {
    }

    public Airmansdata(Integer airmansdataid) {
        this.airmansdataid = airmansdataid;
    }

    public Airmansdata(Integer airmansdataid, Date dateofenlistment) {
        this.airmansdataid = airmansdataid;
        this.dateofenlistment = dateofenlistment;
    }

    public Integer getAirmansdataid() {
        return airmansdataid;
    }

    public void setAirmansdataid(Integer airmansdataid) {
        this.airmansdataid = airmansdataid;
    }

    public Date getDateofenlistment() {
        return dateofenlistment;
    }

    public void setDateofenlistment(Date dateofenlistment) {
        this.dateofenlistment = dateofenlistment;
    }

    @XmlTransient
    public List<Airmansposting> getAirmanspostingList() {
        return airmanspostingList;
    }

    public void setAirmanspostingList(List<Airmansposting> airmanspostingList) {
        this.airmanspostingList = airmanspostingList;
    }

    public Personneldata getPersonneldata() {
        return personneldata;
    }

    public void setPersonneldata(Personneldata personneldata) {
        this.personneldata = personneldata;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (airmansdataid != null ? airmansdataid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Airmansdata)) {
            return false;
        }
        Airmansdata other = (Airmansdata) object;
        if ((this.airmansdataid == null && other.airmansdataid != null) || (this.airmansdataid != null && !this.airmansdataid.equals(other.airmansdataid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Airmansdata[ airmansdataid=" + airmansdataid + " ]";
    }

}
