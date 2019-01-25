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
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s")
    , @NamedQuery(name = "Section.findBySectionId", query = "SELECT s FROM Section s WHERE s.sectionId = :sectionId")
    , @NamedQuery(name = "Section.findByCreationDate", query = "SELECT s FROM Section s WHERE s.creationDate = :creationDate")
    , @NamedQuery(name = "Section.findByLastModified", query = "SELECT s FROM Section s WHERE s.lastModified = :lastModified")
    , @NamedQuery(name = "Section.findBySynchStatus", query = "SELECT s FROM Section s WHERE s.synchStatus = :synchStatus")})
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "section_id", nullable = false)
    private Long sectionId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String name;
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
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    @ManyToOne
    private Term termId;
    @JoinColumn(name = "class_category_id", referencedColumnName = "category_id")
    @ManyToOne
    private ClassCategory classCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<StudentBehavouralTrait> studentBehavouralTraitCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<ExamClassPosition> examClassPositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sectionId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @OneToMany(mappedBy = "sectionId")
    private Collection<Student> studentCollection;
    @OneToMany(mappedBy = "sectionId")
    private Collection<StudentDailyAttendance> studentDailyAttendanceCollection;
    @OneToMany(mappedBy = "sectionId")
    private Collection<ClassRoutine> classRoutineCollection;
    @OneToMany(mappedBy = "sectionId")
    private Collection<ExamMark> examMarkCollection;
    @OneToMany(mappedBy = "sectionId")
    private Collection<CaMark> caMarkCollection;

    public Section() {
    }

    public Section(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Section(Long sectionId, String name) {
        this.sectionId = sectionId;
        this.name = name;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Term getTermId() {
        return termId;
    }

    public void setTermId(Term termId) {
        this.termId = termId;
    }

    public ClassCategory getClassCategoryId() {
        return classCategoryId;
    }

    public void setClassCategoryId(ClassCategory classCategoryId) {
        this.classCategoryId = classCategoryId;
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
        hash += (sectionId != null ? sectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.sectionId == null && other.sectionId != null) || (this.sectionId != null && !this.sectionId.equals(other.sectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Section[ sectionId=" + sectionId + " ]";
    }
    
}
