/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "employee_attendance", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeAttendance.findAll", query = "SELECT e FROM EmployeeAttendance e")
    , @NamedQuery(name = "EmployeeAttendance.findByAttendanceId", query = "SELECT e FROM EmployeeAttendance e WHERE e.attendanceId = :attendanceId")
    , @NamedQuery(name = "EmployeeAttendance.findByStatus", query = "SELECT e FROM EmployeeAttendance e WHERE e.status = :status")
    , @NamedQuery(name = "EmployeeAttendance.findByDate", query = "SELECT e FROM EmployeeAttendance e WHERE e.date = :date")
    , @NamedQuery(name = "EmployeeAttendance.findByCreationDate", query = "SELECT e FROM EmployeeAttendance e WHERE e.creationDate = :creationDate")
    , @NamedQuery(name = "EmployeeAttendance.findByCreatedBy", query = "SELECT e FROM EmployeeAttendance e WHERE e.createdBy = :createdBy")
    , @NamedQuery(name = "EmployeeAttendance.findByLastModified", query = "SELECT e FROM EmployeeAttendance e WHERE e.lastModified = :lastModified")
    , @NamedQuery(name = "EmployeeAttendance.findByModifiedBy", query = "SELECT e FROM EmployeeAttendance e WHERE e.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "EmployeeAttendance.findBySynchStatus", query = "SELECT e FROM EmployeeAttendance e WHERE e.synchStatus = :synchStatus")})
public class EmployeeAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "attendance_id", nullable = false)
    private Long attendanceId;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "created_by")
    private BigInteger createdBy;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "modified_by")
    private BigInteger modifiedBy;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;

    public EmployeeAttendance() {
    }

    public EmployeeAttendance(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public EmployeeAttendance(Long attendanceId, boolean status, Date date) {
        this.attendanceId = attendanceId;
        this.status = status;
        this.date = date;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public BigInteger getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(BigInteger modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (attendanceId != null ? attendanceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeAttendance)) {
            return false;
        }
        EmployeeAttendance other = (EmployeeAttendance) object;
        if ((this.attendanceId == null && other.attendanceId != null) || (this.attendanceId != null && !this.attendanceId.equals(other.attendanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.EmployeeAttendance[ attendanceId=" + attendanceId + " ]";
    }
    
}
