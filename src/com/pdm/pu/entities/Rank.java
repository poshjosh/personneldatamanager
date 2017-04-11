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
@Table(name = "rank")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rank.findAll", query = "SELECT r FROM Rank r"),
    @NamedQuery(name = "Rank.findByRankid", query = "SELECT r FROM Rank r WHERE r.rankid = :rankid"),
    @NamedQuery(name = "Rank.findByRank", query = "SELECT r FROM Rank r WHERE r.rank = :rank"),
    @NamedQuery(name = "Rank.findByAbbreviation", query = "SELECT r FROM Rank r WHERE r.abbreviation = :abbreviation")})
public class Rank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "rankid")
    private Short rankid;
    @Basic(optional = false)
    @Column(name = "rank")
    private String rank;
    @Basic(optional = false)
    @Column(name = "abbreviation")
    private String abbreviation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rank", fetch = FetchType.LAZY)
    private List<Personneldata> personneldataList;

    public Rank() {
    }

    public Rank(Short rankid) {
        this.rankid = rankid;
    }

    public Rank(Short rankid, String rank, String abbreviation) {
        this.rankid = rankid;
        this.rank = rank;
        this.abbreviation = abbreviation;
    }

    public Short getRankid() {
        return rankid;
    }

    public void setRankid(Short rankid) {
        this.rankid = rankid;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @XmlTransient
    public List<Personneldata> getPersonneldataList() {
        return personneldataList;
    }

    public void setPersonneldataList(List<Personneldata> personneldataList) {
        this.personneldataList = personneldataList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rankid != null ? rankid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rank)) {
            return false;
        }
        Rank other = (Rank) object;
        if ((this.rankid == null && other.rankid != null) || (this.rankid != null && !this.rankid.equals(other.rankid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Rank[ rankid=" + rankid + " ]";
    }

}
