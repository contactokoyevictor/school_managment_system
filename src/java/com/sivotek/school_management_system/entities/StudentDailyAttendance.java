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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "student_daily_attendance", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentDailyAttendance.findAll", query = "SELECT s FROM StudentDailyAttendance s")
    , @NamedQuery(name = "StudentDailyAttendance.findByAttendanceId", query = "SELECT s FROM StudentDailyAttendance s WHERE s.attendanceId = :attendanceId")
    , @NamedQuery(name = "StudentDailyAttendance.findByStatus", query = "SELECT s FROM StudentDailyAttendance s WHERE s.status = :status")
    , @NamedQuery(name = "StudentDailyAttendance.findByDate", query = "SELECT s FROM StudentDailyAttendance s WHERE s.date = :date")
    , @NamedQuery(name = "StudentDailyAttendance.findByCreationDate", query = "SELECT s FROM StudentDailyAttendance s WHERE s.creationDate = :creationDate")
    , @NamedQuery(name = "StudentDailyAttendance.findByCreatedBy", query = "SELECT s FROM StudentDailyAttendance s WHERE s.createdBy = :createdBy")
    , @NamedQuery(name = "StudentDailyAttendance.findByLoginType", query = "SELECT s FROM StudentDailyAttendance s WHERE s.loginType = :loginType")
    , @NamedQuery(name = "StudentDailyAttendance.findByLastModified", query = "SELECT s FROM StudentDailyAttendance s WHERE s.lastModified = :lastModified")
    , @NamedQuery(name = "StudentDailyAttendance.findByModifiedBy", query = "SELECT s FROM StudentDailyAttendance s WHERE s.modifiedBy = :modifiedBy")
    , @NamedQuery(name = "StudentDailyAttendance.findByLoginType2", query = "SELECT s FROM StudentDailyAttendance s WHERE s.loginType2 = :loginType2")
    , @NamedQuery(name = "StudentDailyAttendance.findBySynchStatus", query = "SELECT s FROM StudentDailyAttendance s WHERE s.synchStatus = :synchStatus")})
public class StudentDailyAttendance implements Serializable {

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
    @Column(name = "created_by")
    private BigInteger createdBy;
    @Size(max = 60)
    @Column(name = "login_type", length = 60)
    private String loginType;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "modified_by")
    private BigInteger modifiedBy;
    @Size(max = 60)
    @Column(name = "login_type2", length = 60)
    private String loginType2;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    @ManyToOne
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    @ManyToOne
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    @ManyToOne
    private Section sectionId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id")
    @ManyToOne
    private AcademicYears academicYear;

    public StudentDailyAttendance() {
    }

    public StudentDailyAttendance(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public StudentDailyAttendance(Long attendanceId, int status, Date date) {
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

    public BigInteger getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(BigInteger createdBy) {
        this.createdBy = createdBy;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
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

    public String getLoginType2() {
        return loginType2;
    }

    public void setLoginType2(String loginType2) {
        this.loginType2 = loginType2;
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

    public Term getTermId() {
        return termId;
    }

    public void setTermId(Term termId) {
        this.termId = termId;
    }

    public Section getSectionId() {
        return sectionId;
    }

    public void setSectionId(Section sectionId) {
        this.sectionId = sectionId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public AcademicYears getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYears academicYear) {
        this.academicYear = academicYear;
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
        if (!(object instanceof StudentDailyAttendance)) {
            return false;
        }
        StudentDailyAttendance other = (StudentDailyAttendance) object;
        if ((this.attendanceId == null && other.attendanceId != null) || (this.attendanceId != null && !this.attendanceId.equals(other.attendanceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.StudentDailyAttendance[ attendanceId=" + attendanceId + " ]";
    }
    
}
