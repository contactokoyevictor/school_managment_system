/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Holiday.findAll", query = "SELECT h FROM Holiday h")
    , @NamedQuery(name = "Holiday.findByHolidayId", query = "SELECT h FROM Holiday h WHERE h.holidayId = :holidayId")
    , @NamedQuery(name = "Holiday.findByEventName", query = "SELECT h FROM Holiday h WHERE h.eventName = :eventName")
    , @NamedQuery(name = "Holiday.findByStartDate", query = "SELECT h FROM Holiday h WHERE h.startDate = :startDate")
    , @NamedQuery(name = "Holiday.findByEndDate", query = "SELECT h FROM Holiday h WHERE h.endDate = :endDate")
    , @NamedQuery(name = "Holiday.findBySynchStatus", query = "SELECT h FROM Holiday h WHERE h.synchStatus = :synchStatus")})
public class Holiday implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "holiday_id", nullable = false)
    private Long holidayId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "event_name", nullable = false, length = 200)
    private String eventName;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;

    public Holiday() {
    }

    public Holiday(Long holidayId) {
        this.holidayId = holidayId;
    }

    public Holiday(Long holidayId, String eventName, String description, Date startDate, Date endDate) {
        this.holidayId = holidayId;
        this.eventName = eventName;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (holidayId != null ? holidayId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Holiday)) {
            return false;
        }
        Holiday other = (Holiday) object;
        if ((this.holidayId == null && other.holidayId != null) || (this.holidayId != null && !this.holidayId.equals(other.holidayId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Holiday[ holidayId=" + holidayId + " ]";
    }
    
}
