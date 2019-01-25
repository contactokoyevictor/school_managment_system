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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "exam_mark", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamMark.findAll", query = "SELECT e FROM ExamMark e")
    , @NamedQuery(name = "ExamMark.findByMarkId", query = "SELECT e FROM ExamMark e WHERE e.markId = :markId")
    , @NamedQuery(name = "ExamMark.findByMarkObtained", query = "SELECT e FROM ExamMark e WHERE e.markObtained = :markObtained")
    , @NamedQuery(name = "ExamMark.findByMarkTotal", query = "SELECT e FROM ExamMark e WHERE e.markTotal = :markTotal")
    , @NamedQuery(name = "ExamMark.findBySynchStatus", query = "SELECT e FROM ExamMark e WHERE e.synchStatus = :synchStatus")})
public class ExamMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark_id", nullable = false)
    private Long markId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "mark_obtained", precision = 22)
    private Double markObtained;
    @Column(name = "mark_total", precision = 22)
    private Double markTotal;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String comment;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    @ManyToOne(optional = false)
    private Subject subjectId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    @ManyToOne
    private Section sectionId;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", nullable = false)
    @ManyToOne(optional = false)
    private Exam examId;

    public ExamMark() {
    }

    public ExamMark(Long markId) {
        this.markId = markId;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
    }

    public Double getMarkObtained() {
        return markObtained;
    }

    public void setMarkObtained(Double markObtained) {
        this.markObtained = markObtained;
    }

    public Double getMarkTotal() {
        return markTotal;
    }

    public void setMarkTotal(Double markTotal) {
        this.markTotal = markTotal;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
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

    public Exam getExamId() {
        return examId;
    }

    public void setExamId(Exam examId) {
        this.examId = examId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (markId != null ? markId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamMark)) {
            return false;
        }
        ExamMark other = (ExamMark) object;
        if ((this.markId == null && other.markId != null) || (this.markId != null && !this.markId.equals(other.markId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ExamMark[ markId=" + markId + " ]";
    }
    
}
