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
@Table(name = "qualification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qualification.findAll", query = "SELECT q FROM Qualification q"),
    @NamedQuery(name = "Qualification.findByQualificationid", query = "SELECT q FROM Qualification q WHERE q.qualificationid = :qualificationid"),
    @NamedQuery(name = "Qualification.findByQualification", query = "SELECT q FROM Qualification q WHERE q.qualification = :qualification"),
    @NamedQuery(name = "Qualification.findByDescription", query = "SELECT q FROM Qualification q WHERE q.description = :description")})
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "qualificationid")
    private Integer qualificationid;
    @Basic(optional = false)
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "qualification", fetch = FetchType.LAZY)
    private List<Course> courseList;

    public Qualification() {
    }

    public Qualification(Integer qualificationid) {
        this.qualificationid = qualificationid;
    }

    public Qualification(Integer qualificationid, String qualification) {
        this.qualificationid = qualificationid;
        this.qualification = qualification;
    }

    public Integer getQualificationid() {
        return qualificationid;
    }

    public void setQualificationid(Integer qualificationid) {
        this.qualificationid = qualificationid;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (qualificationid != null ? qualificationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qualification)) {
            return false;
        }
        Qualification other = (Qualification) object;
        if ((this.qualificationid == null && other.qualificationid != null) || (this.qualificationid != null && !this.qualificationid.equals(other.qualificationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Qualification[ qualificationid=" + qualificationid + " ]";
    }

}
