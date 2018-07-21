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
@Table(name = "grade")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grade.findAll", query = "SELECT g FROM Grade g"),
    @NamedQuery(name = "Grade.findByGradeid", query = "SELECT g FROM Grade g WHERE g.gradeid = :gradeid"),
    @NamedQuery(name = "Grade.findByGrade", query = "SELECT g FROM Grade g WHERE g.grade = :grade")})
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gradeid")
    private Short gradeid;
    @Basic(optional = false)
    @Column(name = "grade")
    private String grade;
    @OneToMany(mappedBy = "grade", fetch = FetchType.LAZY)
    private List<Airmansdata> airmansdataList;

    public Grade() {
    }

    public Grade(Short gradeid) {
        this.gradeid = gradeid;
    }

    public Grade(Short gradeid, String grade) {
        this.gradeid = gradeid;
        this.grade = grade;
    }

    public Short getGradeid() {
        return gradeid;
    }

    public void setGradeid(Short gradeid) {
        this.gradeid = gradeid;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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
        hash += (gradeid != null ? gradeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        if ((this.gradeid == null && other.gradeid != null) || (this.gradeid != null && !this.gradeid.equals(other.gradeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Grade[ gradeid=" + gradeid + " ]";
    }

}
