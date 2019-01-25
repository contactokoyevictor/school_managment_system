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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name", "academic_year"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Term.findAll", query = "SELECT t FROM Term t")
    , @NamedQuery(name = "Term.findByTermId", query = "SELECT t FROM Term t WHERE t.termId = :termId")
    , @NamedQuery(name = "Term.findByName", query = "SELECT t FROM Term t WHERE t.name = :name")
    , @NamedQuery(name = "Term.findByStartFrom", query = "SELECT t FROM Term t WHERE t.startFrom = :startFrom")
    , @NamedQuery(name = "Term.findByEndIn", query = "SELECT t FROM Term t WHERE t.endIn = :endIn")
    , @NamedQuery(name = "Term.findByCreateddate", query = "SELECT t FROM Term t WHERE t.createddate = :createddate")
    , @NamedQuery(name = "Term.findByModifiedDate", query = "SELECT t FROM Term t WHERE t.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "Term.findBySynchStatus", query = "SELECT t FROM Term t WHERE t.synchStatus = :synchStatus")})
public class Term implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "term_id", nullable = false)
    private Long termId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nick_name", nullable = false, length = 65535)
    private String nickName;
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
    @OneToMany(mappedBy = "termId")
    private Collection<Section> sectionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<Exam> examCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<StudentBehavouralTrait> studentBehavouralTraitCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<ClassSubjects> classSubjectsCollection;
    @OneToMany(mappedBy = "termId")
    private Collection<ExamClassPosition> examClassPositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<Student> studentCollection;
    @OneToMany(mappedBy = "termId")
    private Collection<StudentDailyAttendance> studentDailyAttendanceCollection;
    @OneToMany(mappedBy = "termId")
    private Collection<ClassRoutine> classRoutineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<ExamMark> examMarkCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id", nullable = false)
    @ManyToOne(optional = false)
    private AcademicYears academicYear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "termId")
    private Collection<CaMark> caMarkCollection;

    public Term() {
    }

    public Term(Long termId) {
        this.termId = termId;
    }

    public Term(Long termId, String name, String nickName) {
        this.termId = termId;
        this.name = name;
        this.nickName = nickName;
    }

    public Long getTermId() {
        return termId;
    }

    public void setTermId(Long termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
    public Collection<Section> getSectionCollection() {
        return sectionCollection;
    }

    public void setSectionCollection(Collection<Section> sectionCollection) {
        this.sectionCollection = sectionCollection;
    }

    @XmlTransient
    public Collection<PrincipalTerminalExamComment> getPrincipalTerminalExamCommentCollection() {
        return principalTerminalExamCommentCollection;
    }

    public void setPrincipalTerminalExamCommentCollection(Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection) {
        this.principalTerminalExamCommentCollection = principalTerminalExamCommentCollection;
    }

    @XmlTransient
    public Collection<FormMasterTerminalExamComment> getFormMasterTerminalExamCommentCollection() {
        return formMasterTerminalExamCommentCollection;
    }

    public void setFormMasterTerminalExamCommentCollection(Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection) {
        this.formMasterTerminalExamCommentCollection = formMasterTerminalExamCommentCollection;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @XmlTransient
    public Collection<StudentBehavouralTrait> getStudentBehavouralTraitCollection() {
        return studentBehavouralTraitCollection;
    }

    public void setStudentBehavouralTraitCollection(Collection<StudentBehavouralTrait> studentBehavouralTraitCollection) {
        this.studentBehavouralTraitCollection = studentBehavouralTraitCollection;
    }

    @XmlTransient
    public Collection<ClassSubjects> getClassSubjectsCollection() {
        return classSubjectsCollection;
    }

    public void setClassSubjectsCollection(Collection<ClassSubjects> classSubjectsCollection) {
        this.classSubjectsCollection = classSubjectsCollection;
    }

    @XmlTransient
    public Collection<ExamClassPosition> getExamClassPositionCollection() {
        return examClassPositionCollection;
    }

    public void setExamClassPositionCollection(Collection<ExamClassPosition> examClassPositionCollection) {
        this.examClassPositionCollection = examClassPositionCollection;
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

    @XmlTransient
    public Collection<StudentDailyAttendance> getStudentDailyAttendanceCollection() {
        return studentDailyAttendanceCollection;
    }

    public void setStudentDailyAttendanceCollection(Collection<StudentDailyAttendance> studentDailyAttendanceCollection) {
        this.studentDailyAttendanceCollection = studentDailyAttendanceCollection;
    }

    @XmlTransient
    public Collection<ClassRoutine> getClassRoutineCollection() {
        return classRoutineCollection;
    }

    public void setClassRoutineCollection(Collection<ClassRoutine> classRoutineCollection) {
        this.classRoutineCollection = classRoutineCollection;
    }

    @XmlTransient
    public Collection<ExamMark> getExamMarkCollection() {
        return examMarkCollection;
    }

    public void setExamMarkCollection(Collection<ExamMark> examMarkCollection) {
        this.examMarkCollection = examMarkCollection;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public AcademicYears getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYears academicYear) {
        this.academicYear = academicYear;
    }

    @XmlTransient
    public Collection<CaMark> getCaMarkCollection() {
        return caMarkCollection;
    }

    public void setCaMarkCollection(Collection<CaMark> caMarkCollection) {
        this.caMarkCollection = caMarkCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (termId != null ? termId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Term)) {
            return false;
        }
        Term other = (Term) object;
        if ((this.termId == null && other.termId != null) || (this.termId != null && !this.termId.equals(other.termId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Term[ termId=" + termId + " ]";
    }
    
}
