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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "ca_mark", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CaMark.findAll", query = "SELECT c FROM CaMark c")
    , @NamedQuery(name = "CaMark.findByCaId", query = "SELECT c FROM CaMark c WHERE c.caId = :caId")
    , @NamedQuery(name = "CaMark.findByFirstTest", query = "SELECT c FROM CaMark c WHERE c.firstTest = :firstTest")
    , @NamedQuery(name = "CaMark.findByFirstAssignmt", query = "SELECT c FROM CaMark c WHERE c.firstAssignmt = :firstAssignmt")
    , @NamedQuery(name = "CaMark.findBySecondTest", query = "SELECT c FROM CaMark c WHERE c.secondTest = :secondTest")
    , @NamedQuery(name = "CaMark.findBySecondAssignmt", query = "SELECT c FROM CaMark c WHERE c.secondAssignmt = :secondAssignmt")
    , @NamedQuery(name = "CaMark.findBySynchStatus", query = "SELECT c FROM CaMark c WHERE c.synchStatus = :synchStatus")})
public class CaMark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ca_id", nullable = false)
    private Long caId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "first_test", precision = 22)
    private Double firstTest;
    @Column(name = "first_assignmt", precision = 22)
    private Double firstAssignmt;
    @Column(name = "second_test", precision = 22)
    private Double secondTest;
    @Column(name = "second_assignmt", precision = 22)
    private Double secondAssignmt;
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

    public CaMark() {
    }

    public CaMark(Long caId) {
        this.caId = caId;
    }

    public Long getCaId() {
        return caId;
    }

    public void setCaId(Long caId) {
        this.caId = caId;
    }

    public Double getFirstTest() {
        return firstTest;
    }

    public void setFirstTest(Double firstTest) {
        this.firstTest = firstTest;
    }

    public Double getFirstAssignmt() {
        return firstAssignmt;
    }

    public void setFirstAssignmt(Double firstAssignmt) {
        this.firstAssignmt = firstAssignmt;
    }

    public Double getSecondTest() {
        return secondTest;
    }

    public void setSecondTest(Double secondTest) {
        this.secondTest = secondTest;
    }

    public Double getSecondAssignmt() {
        return secondAssignmt;
    }

    public void setSecondAssignmt(Double secondAssignmt) {
        this.secondAssignmt = secondAssignmt;
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
        hash += (caId != null ? caId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CaMark)) {
            return false;
        }
        CaMark other = (CaMark) object;
        if ((this.caId == null && other.caId != null) || (this.caId != null && !this.caId.equals(other.caId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.CaMark[ caId=" + caId + " ]";
    }
    
}
