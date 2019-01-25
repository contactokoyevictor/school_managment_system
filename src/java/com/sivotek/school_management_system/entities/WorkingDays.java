/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "working_days", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WorkingDays.findAll", query = "SELECT w FROM WorkingDays w")
    , @NamedQuery(name = "WorkingDays.findByWorkingDayId", query = "SELECT w FROM WorkingDays w WHERE w.workingDayId = :workingDayId")
    , @NamedQuery(name = "WorkingDays.findByFlag", query = "SELECT w FROM WorkingDays w WHERE w.flag = :flag")
    , @NamedQuery(name = "WorkingDays.findBySynchStatus", query = "SELECT w FROM WorkingDays w WHERE w.synchStatus = :synchStatus")})
public class WorkingDays implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "working_day_id", nullable = false)
    private Integer workingDayId;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean flag;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "day_id", referencedColumnName = "day_id", nullable = false)
    @ManyToOne(optional = false)
    private Days dayId;

    public WorkingDays() {
    }

    public WorkingDays(Integer workingDayId) {
        this.workingDayId = workingDayId;
    }

    public WorkingDays(Integer workingDayId, boolean flag) {
        this.workingDayId = workingDayId;
        this.flag = flag;
    }

    public Integer getWorkingDayId() {
        return workingDayId;
    }

    public void setWorkingDayId(Integer workingDayId) {
        this.workingDayId = workingDayId;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public Days getDayId() {
        return dayId;
    }

    public void setDayId(Days dayId) {
        this.dayId = dayId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workingDayId != null ? workingDayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WorkingDays)) {
            return false;
        }
        WorkingDays other = (WorkingDays) object;
        if ((this.workingDayId == null && other.workingDayId != null) || (this.workingDayId != null && !this.workingDayId.equals(other.workingDayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.WorkingDays[ workingDayId=" + workingDayId + " ]";
    }
    
}
