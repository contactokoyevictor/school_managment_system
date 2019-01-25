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
@Table(name = "subject_attendance", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectAttendance.findAll", query = "SELECT s FROM SubjectAttendance s")
    , @NamedQuery(name = "SubjectAttendance.findByAttendanceId", query = "SELECT s FROM SubjectAttendance s WHERE s.attendanceId = :attendanceId")
    , @NamedQuery(name = "SubjectAttendance.findByStatus", query = "SELECT s FROM SubjectAttendance s WHERE s.status = :status")
    , @NamedQuery(name = "SubjectAttendance.findByDate", query = "SELECT s FROM SubjectAttendance s WHERE s.date = :date")
    , @NamedQuery(name = "SubjectAttendance.findByCreationDate", query = "SELECT s FROM SubjectAttendance s WHERE s.creationDate = :creationDate")
    , @NamedQuery(name = "SubjectAttendance.findByLastModified", query = "SELECT s FROM SubjectAttendance s WHERE s.lastModified = :lastModified")
    , @NamedQuery(name = "SubjectAttendance.findBySynchStatus", query = "SELECT s FROM SubjectAttendance s WHERE s.synchStatus = :synchStatus")})
public class SubjectAttendance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "attendance_id", nullable = false)
    private Long attendanceId;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int status;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    @ManyToOne(optional = false)
    private Subject subjectId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "class_routine_id", referencedColumnName = "class_routine_id", nullable = false)
    @ManyToOne(optional = false)
    private ClassRoutine classRoutineId;

    public SubjectAttendance() {
    }

    public SubjectAttendance(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public SubjectAttendance(Long attendanceId, int status, Date date) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
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

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public ClassRoutine getClassRoutineId() {
        return classRoutineId;
    }

    public void setClassRoutineId(ClassRoutine classRoutineId) {
        this.classRoutineId = classRoutineId;
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
        if (!(object instanceof SubjectAttendance)) {
            return false;
        }
        SubjectAttendance other = (SubjectAttendance) object;
        if ((this.attendanceId == null && other.attendanceId != null) || (this.attendanceId != null && !this.attendanceId.equals(other.attendanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.SubjectAttendance[ attendanceId=" + attendanceId + " ]";
    }
    
}
