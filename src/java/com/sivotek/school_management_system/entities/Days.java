/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Days.findAll", query = "SELECT d FROM Days d")
    , @NamedQuery(name = "Days.findByDayId", query = "SELECT d FROM Days d WHERE d.dayId = :dayId")
    , @NamedQuery(name = "Days.findByDay", query = "SELECT d FROM Days d WHERE d.day = :day")
    , @NamedQuery(name = "Days.findBySynchStatus", query = "SELECT d FROM Days d WHERE d.synchStatus = :synchStatus")})
public class Days implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "day_id", nullable = false)
    private Integer dayId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(nullable = false, length = 100)
    private String day;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dayId")
    private Collection<WorkingDays> workingDaysCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "day")
    private Collection<ClassRoutine> classRoutineCollection;

    public Days() {
    }

    public Days(Integer dayId) {
        this.dayId = dayId;
    }

    public Days(Integer dayId, String day) {
        this.dayId = dayId;
        this.day = day;
    }

    public Integer getDayId() {
        return dayId;
    }

    public void setDayId(Integer dayId) {
        this.dayId = dayId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<WorkingDays> getWorkingDaysCollection() {
        return workingDaysCollection;
    }

    public void setWorkingDaysCollection(Collection<WorkingDays> workingDaysCollection) {
        this.workingDaysCollection = workingDaysCollection;
    }

    @XmlTransient
    public Collection<ClassRoutine> getClassRoutineCollection() {
        return classRoutineCollection;
    }

    public void setClassRoutineCollection(Collection<ClassRoutine> classRoutineCollection) {
        this.classRoutineCollection = classRoutineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dayId != null ? dayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Days)) {
            return false;
        }
        Days other = (Days) object;
        if ((this.dayId == null && other.dayId != null) || (this.dayId != null && !this.dayId.equals(other.dayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Days[ dayId=" + dayId + " ]";
    }
    
}
