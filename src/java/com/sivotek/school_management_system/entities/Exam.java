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
    @NamedQuery(name = "Exam.findAll", query = "SELECT e FROM Exam e")
    , @NamedQuery(name = "Exam.findByExamId", query = "SELECT e FROM Exam e WHERE e.examId = :examId")
    , @NamedQuery(name = "Exam.findByName", query = "SELECT e FROM Exam e WHERE e.name = :name")
    , @NamedQuery(name = "Exam.findByDate", query = "SELECT e FROM Exam e WHERE e.date = :date")
    , @NamedQuery(name = "Exam.findByIsPublished", query = "SELECT e FROM Exam e WHERE e.isPublished = :isPublished")
    , @NamedQuery(name = "Exam.findByCreateddate", query = "SELECT e FROM Exam e WHERE e.createddate = :createddate")
    , @NamedQuery(name = "Exam.findByLastModified", query = "SELECT e FROM Exam e WHERE e.lastModified = :lastModified")
    , @NamedQuery(name = "Exam.findBySynchStatus", query = "SELECT e FROM Exam e WHERE e.synchStatus = :synchStatus")})
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "exam_id", nullable = false)
    private Long examId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_published", nullable = false)
    private boolean isPublished;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id", nullable = false)
    @ManyToOne(optional = false)
    private AcademicYears academicYear;
    @JoinColumn(name = "class_category_id", referencedColumnName = "category_id", nullable = false)
    @ManyToOne(optional = false)
    private ClassCategory classCategoryId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<StudentBehavouralTrait> studentBehavouralTraitCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<ExamClassPosition> examClassPositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<ExamMark> examMarkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "examId")
    private Collection<CaMark> caMarkCollection;

    public Exam() {
    }

    public Exam(Long examId) {
        this.examId = examId;
    }

    public Exam(Long examId, String name, Date date, String comment, boolean isPublished, Date createddate) {
        this.examId = examId;
        this.name = name;
        this.date = date;
        this.comment = comment;
        this.isPublished = isPublished;
        this.createddate = createddate;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
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

    @XmlTransient
    public Collection<FormMasterTerminalExamComment> getFormMasterTerminalExamCommentCollection() {
        return formMasterTerminalExamCommentCollection;
    }

    public void setFormMasterTerminalExamCommentCollection(Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection) {
        this.formMasterTerminalExamCommentCollection = formMasterTerminalExamCommentCollection;
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

    public ClassCategory getClassCategoryId() {
        return classCategoryId;
    }

    public void setClassCategoryId(ClassCategory classCategoryId) {
        this.classCategoryId = classCategoryId;
    }

    public Term getTermId() {
        return termId;
    }

    public void setTermId(Term termId) {
        this.termId = termId;
    }

    @XmlTransient
    public Collection<StudentBehavouralTrait> getStudentBehavouralTraitCollection() {
        return studentBehavouralTraitCollection;
    }

    public void setStudentBehavouralTraitCollection(Collection<StudentBehavouralTrait> studentBehavouralTraitCollection) {
        this.studentBehavouralTraitCollection = studentBehavouralTraitCollection;
    }

    @XmlTransient
    public Collection<ExamClassPosition> getExamClassPositionCollection() {
        return examClassPositionCollection;
    }

    public void setExamClassPositionCollection(Collection<ExamClassPosition> examClassPositionCollection) {
        this.examClassPositionCollection = examClassPositionCollection;
    }

    @XmlTransient
    public Collection<ExamMark> getExamMarkCollection() {
        return examMarkCollection;
    }

    public void setExamMarkCollection(Collection<ExamMark> examMarkCollection) {
        this.examMarkCollection = examMarkCollection;
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
        hash += (examId != null ? examId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Exam)) {
            return false;
        }
        Exam other = (Exam) object;
        if ((this.examId == null && other.examId != null) || (this.examId != null && !this.examId.equals(other.examId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Exam[ examId=" + examId + " ]";
    }
    
}
