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
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:32 PM
 */
@Entity
@Table(name = "trade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Trade.findAll", query = "SELECT t FROM Trade t"),
    @NamedQuery(name = "Trade.findByTradeid", query = "SELECT t FROM Trade t WHERE t.tradeid = :tradeid"),
    @NamedQuery(name = "Trade.findByTrade", query = "SELECT t FROM Trade t WHERE t.trade = :trade"),
    @NamedQuery(name = "Trade.findByAbbreviation", query = "SELECT t FROM Trade t WHERE t.abbreviation = :abbreviation")})
public class Trade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tradeid")
    private Short tradeid;
    @Basic(optional = false)
    @Column(name = "trade")
    private String trade;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(mappedBy = "trade", fetch = FetchType.LAZY)
    private List<Airmansdata> airmansdataList;

    public Trade() {
    }

    public Trade(Short tradeid) {
        this.tradeid = tradeid;
    }

    public Trade(Short tradeid, String trade, String abbreviation) {
        this.tradeid = tradeid;
        this.trade = trade;
        this.abbreviation = abbreviation;
    }

    public Short getTradeid() {
        return tradeid;
    }

    public void setTradeid(Short tradeid) {
        this.tradeid = tradeid;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public List<Airmansdata> getAirmansdataList() {
        return airmansdataList;
    }

    public void setAirmansdataList(List<Airmansdata> airmansdataList) {
        this.airmansdataList = airmansdataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeid != null ? tradeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trade)) {
            return false;
        }
        Trade other = (Trade) object;
        if ((this.tradeid == null && other.tradeid != null) || (this.tradeid != null && !this.tradeid.equals(other.tradeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Trade[ tradeid=" + tradeid + " ]";
    }

}
