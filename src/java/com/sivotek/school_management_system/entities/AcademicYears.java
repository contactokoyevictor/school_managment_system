/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "academic_years", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcademicYears.findAll", query = "SELECT a FROM AcademicYears a")
    , @NamedQuery(name = "AcademicYears.findByYearId", query = "SELECT a FROM AcademicYears a WHERE a.yearId = :yearId")
    , @NamedQuery(name = "AcademicYears.findByBranchId", query = "SELECT a FROM AcademicYears a WHERE a.branchId = :branchId")   
    , @NamedQuery(name = "AcademicYears.findByName", query = "SELECT a FROM AcademicYears a WHERE a.name = :name")
    , @NamedQuery(name = "AcademicYears.findByStatus", query = "SELECT a FROM AcademicYears a WHERE a.status = :status")
    , @NamedQuery(name = "AcademicYears.findByStartFrom", query = "SELECT a FROM AcademicYears a WHERE a.startFrom = :startFrom")
    , @NamedQuery(name = "AcademicYears.findByEndIn", query = "SELECT a FROM AcademicYears a WHERE a.endIn = :endIn")
    , @NamedQuery(name = "AcademicYears.findByCreateddate", query = "SELECT a FROM AcademicYears a WHERE a.createddate = :createddate")
    , @NamedQuery(name = "AcademicYears.findByModifiedDate", query = "SELECT a FROM AcademicYears a WHERE a.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "AcademicYears.findBySynchStatus", query = "SELECT a FROM AcademicYears a WHERE a.synchStatus = :synchStatus")})
public class AcademicYears implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "year_id", nullable = false)
    private Long yearId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String status;
    @Column(name = "start_from")
    @Temporal(TemporalType.DATE)
    private Date startFrom;
    @Column(name = "end_in")
    @Temporal(TemporalType.DATE)
    private Date endIn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(mappedBy = "academicYear")
    private Collection<Subject> subjectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicYear")
    private Collection<Exam> examCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicYear")
    private Collection<Grade> gradeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicYear")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @OneToMany(mappedBy = "academicYear")
    private Collection<Student> studentCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @OneToMany(mappedBy = "academicYear")
    private Collection<StudentDailyAttendance> studentDailyAttendanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicYear")
    private Collection<Term> termCollection;
    @OneToMany(mappedBy = "academicYear")
    private Collection<Class> classCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "academicYear")
    private Collection<BehavouralTrait> behavouralTraitCollection;

    public AcademicYears() {
    }

    public AcademicYears(Long yearId) {
        this.yearId = yearId;
    }

    public AcademicYears(Long yearId, String name, String status) {
        this.yearId = yearId;
        this.name = name;
        this.status = status;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(Date startFrom) {
        this.startFrom = startFrom;
    }

    public Date getEndIn() {
        return endIn;
    }

    public void setEndIn(Date endIn) {
        this.endIn = endIn;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(Collection<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @XmlTransient
    public Collection<Grade> getGradeCollection() {
        return gradeCollection;
    }

    public void setGradeCollection(Collection<Grade> gradeCollection) {
        this.gradeCollection = gradeCollection;
    }

    @XmlTransient
    public Collection<SchoolFeeInvoiceDetails> getSchoolFeeInvoiceDetailsCollection() {
        return schoolFeeInvoiceDetailsCollection;
    }

    public void setSchoolFeeInvoiceDetailsCollection(Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection) {
        this.schoolFeeInvoiceDetailsCollection = schoolFeeInvoiceDetailsCollection;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    @XmlTransient
    public Collection<StudentDailyAttendance> getStudentDailyAttendanceCollection() {
        return studentDailyAttendanceCollection;
    }

    public void setStudentDailyAttendanceCollection(Collection<StudentDailyAttendance> studentDailyAttendanceCollection) {
        this.studentDailyAttendanceCollection = studentDailyAttendanceCollection;
    }

    @XmlTransient
    public Collection<Term> getTermCollection() {
        return termCollection;
    }

    public void setTermCollection(Collection<Term> termCollection) {
        this.termCollection = termCollection;
    }

    @XmlTransient
    public Collection<Class> getClassCollection() {
        return classCollection;
    }

    public void setClassCollection(Collection<Class> classCollection) {
        this.classCollection = classCollection;
    }

    @XmlTransient
    public Collection<BehavouralTrait> getBehavouralTraitCollection() {
        return behavouralTraitCollection;
    }

    public void setBehavouralTraitCollection(Collection<BehavouralTrait> behavouralTraitCollection) {
        this.behavouralTraitCollection = behavouralTraitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (yearId != null ? yearId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcademicYears)) {
            return false;
        }
        AcademicYears other = (AcademicYears) object;
        if ((this.yearId == null && other.yearId != null) || (this.yearId != null && !this.yearId.equals(other.yearId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.AcademicYears[ yearId=" + yearId + " ]";
    }
    
}
