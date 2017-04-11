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
 * @author Chinomso Bassey Ikwuagwu on Mar 29, 2017 10:13:08 PM
 */
@Entity
@Table(name = "stateoforigin")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stateoforigin.findAll", query = "SELECT s FROM Stateoforigin s"),
    @NamedQuery(name = "Stateoforigin.findByStateoforiginid", query = "SELECT s FROM Stateoforigin s WHERE s.stateoforiginid = :stateoforiginid"),
    @NamedQuery(name = "Stateoforigin.findByStateoforigin", query = "SELECT s FROM Stateoforigin s WHERE s.stateoforigin = :stateoforigin")})
public class Stateoforigin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "stateoforiginid")
    private Short stateoforiginid;
    @Basic(optional = false)
    @Column(name = "stateoforigin")
    private String stateoforigin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "stateoforigin", fetch = FetchType.LAZY)
    private List<Personneldata> personneldataList;

    public Stateoforigin() {
    }

    public Stateoforigin(Short stateoforiginid) {
        this.stateoforiginid = stateoforiginid;
    }

    public Stateoforigin(Short stateoforiginid, String stateoforigin) {
        this.stateoforiginid = stateoforiginid;
        this.stateoforigin = stateoforigin;
    }

    public Short getStateoforiginid() {
        return stateoforiginid;
    }

    public void setStateoforiginid(Short stateoforiginid) {
        this.stateoforiginid = stateoforiginid;
    }

    public String getStateoforigin() {
        return stateoforigin;
    }

    public void setStateoforigin(String stateoforigin) {
        this.stateoforigin = stateoforigin;
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
        hash += (stateoforiginid != null ? stateoforiginid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stateoforigin)) {
            return false;
        }
        Stateoforigin other = (Stateoforigin) object;
        if ((this.stateoforiginid == null && other.stateoforiginid != null) || (this.stateoforiginid != null && !this.stateoforiginid.equals(other.stateoforiginid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Stateoforigin[ stateoforiginid=" + stateoforiginid + " ]";
    }

}
