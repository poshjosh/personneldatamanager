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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Chinomso Bassey Ikwuagwu on Jun 6, 2017 9:09:31 PM
 */
@Entity
@Table(name = "courseattended")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courseattended.findAll", query = "SELECT c FROM Courseattended c"),
    @NamedQuery(name = "Courseattended.findByCourseattendedid", query = "SELECT c FROM Courseattended c WHERE c.courseattendedid = :courseattendedid")})
public class Courseattended implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "courseattendedid")
    private Integer courseattendedid;
    @JoinColumn(name = "personneldata", referencedColumnName = "personneldataid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personneldata personneldata;
    @JoinColumn(name = "course", referencedColumnName = "courseid")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    public Courseattended() {
    }

    public Courseattended(Integer courseattendedid) {
        this.courseattendedid = courseattendedid;
    }

    public Integer getCourseattendedid() {
        return courseattendedid;
    }

    public void setCourseattendedid(Integer courseattendedid) {
        this.courseattendedid = courseattendedid;
    }

    public Personneldata getPersonneldata() {
        return personneldata;
    }

    public void setPersonneldata(Personneldata personneldata) {
        this.personneldata = personneldata;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseattendedid != null ? courseattendedid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Courseattended)) {
            return false;
        }
        Courseattended other = (Courseattended) object;
        if ((this.courseattendedid == null && other.courseattendedid != null) || (this.courseattendedid != null && !this.courseattendedid.equals(other.courseattendedid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pdm.pu.entities.Courseattended[ courseattendedid=" + courseattendedid + " ]";
    }

}
