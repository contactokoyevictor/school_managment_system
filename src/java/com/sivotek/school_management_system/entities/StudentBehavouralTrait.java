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
import javax.persistence.Lob;
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
@Table(name = "student_behavoural_trait", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentBehavouralTrait.findAll", query = "SELECT s FROM StudentBehavouralTrait s")
    , @NamedQuery(name = "StudentBehavouralTrait.findById", query = "SELECT s FROM StudentBehavouralTrait s WHERE s.id = :id")
    , @NamedQuery(name = "StudentBehavouralTrait.findByTraitId", query = "SELECT s FROM StudentBehavouralTrait s WHERE s.traitId = :traitId")
    , @NamedQuery(name = "StudentBehavouralTrait.findByCreateddate", query = "SELECT s FROM StudentBehavouralTrait s WHERE s.createddate = :createddate")
    , @NamedQuery(name = "StudentBehavouralTrait.findByChangeddate", query = "SELECT s FROM StudentBehavouralTrait s WHERE s.changeddate = :changeddate")
    , @NamedQuery(name = "StudentBehavouralTrait.findBySynchStatus", query = "SELECT s FROM StudentBehavouralTrait s WHERE s.synchStatus = :synchStatus")})
public class StudentBehavouralTrait implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "trait_id", nullable = false)
    private long traitId;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String rating;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", nullable = false)
    @ManyToOne(optional = false)
    private Exam examId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id", nullable = false)
    @ManyToOne(optional = false)
    private Section sectionId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    @ManyToOne(optional = false)
    private Student studentId;

    public StudentBehavouralTrait() {
    }

    public StudentBehavouralTrait(Long id) {
        this.id = id;
    }

    public StudentBehavouralTrait(Long id, long traitId) {
        this.id = id;
        this.traitId = traitId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTraitId() {
        return traitId;
    }

    public void setTraitId(long traitId) {
        this.traitId = traitId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getChangeddate() {
        return changeddate;
    }

    public void setChangeddate(Date changeddate) {
        this.changeddate = changeddate;
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

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentBehavouralTrait)) {
            return false;
        }
        StudentBehavouralTrait other = (StudentBehavouralTrait) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.StudentBehavouralTrait[ id=" + id + " ]";
    }
    
}
