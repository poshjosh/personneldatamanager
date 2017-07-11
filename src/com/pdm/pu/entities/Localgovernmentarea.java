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
@Table(name = "localgovernmentarea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Localgovernmentarea.findAll", query = "SELECT l FROM Localgovernmentarea l"),
    @NamedQuery(name = "Localgovernmentarea.findByLocalgovernmentareaid", query = "SELECT l FROM Localgovernmentarea l WHERE l.localgovernmentareaid = :localgovernmentareaid"),
    @NamedQuery(name = "Localgovernmentarea.findByLocalgovernmentarea", query = "SELECT l FROM Localgovernmentarea l WHERE l.localgovernmentarea = :localgovernmentarea")})
public class Localgovernmentarea implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "localgovernmentareaid")
    private Integer localgovernmentareaid;
    @Basic(optional = false)
    @Column(name = "localgovernmentarea")
    private String localgovernmentarea;
    @JoinColumn(name = "stateoforigin", referencedColumnName = "stateoforiginid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Stateoforigin stateoforigin;
    @OneToMany(mappedBy = "localgovernmentarea", fetch = FetchType.LAZY)
    private List<Personneldata> personneldataList;

    public Localgovernmentarea() {
    }

    public Localgovernmentarea(Integer localgovernmentareaid) {
        this.localgovernmentareaid = localgovernmentareaid;
    }

    public Localgovernmentarea(Integer localgovernmentareaid, String localgovernmentarea) {
        this.localgovernmentareaid = localgovernmentareaid;
        this.localgovernmentarea = localgovernmentarea;
    }

    public Integer getLocalgovernmentareaid() {
        return localgovernmentareaid;
    }

    public void setLocalgovernmentareaid(Integer localgovernmentareaid) {
        this.localgovernmentareaid = localgovernmentareaid;
    }

    public String getLocalgovernmentarea() {
        return localgovernmentarea;
    }

    public void setLocalgovernmentarea(String localgovernmentarea) {
        this.localgovernmentarea = localgovernmentarea;
    }

    public Stateoforigin getStateoforigin() {
        return stateoforigin;
    }

    public void setStateoforigin(Stateoforigin stateoforigin) {
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
        hash += (localgovernmentareaid != null ? localgovernmentareaid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Localgovernmentarea)) {
            return false;
        }
        Localgovernmentarea other = (Localgovernmentarea) object;
        if ((this.localgovernmentareaid == null && other.localgovernmentareaid != null) || (this.localgovernmentareaid != null && !this.localgovernmentareaid.equals(other.localgovernmentareaid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Localgovernmentarea[ localgovernmentareaid=" + localgovernmentareaid + " ]";
    }

}
