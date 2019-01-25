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
@Table(name = "exam_class_position", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExamClassPosition.findAll", query = "SELECT e FROM ExamClassPosition e")
    , @NamedQuery(name = "ExamClassPosition.findById", query = "SELECT e FROM ExamClassPosition e WHERE e.id = :id")
    , @NamedQuery(name = "ExamClassPosition.findByGpa", query = "SELECT e FROM ExamClassPosition e WHERE e.gpa = :gpa")
    , @NamedQuery(name = "ExamClassPosition.findByPosition", query = "SELECT e FROM ExamClassPosition e WHERE e.position = :position")
    , @NamedQuery(name = "ExamClassPosition.findByCreateddate", query = "SELECT e FROM ExamClassPosition e WHERE e.createddate = :createddate")
    , @NamedQuery(name = "ExamClassPosition.findByChangeddate", query = "SELECT e FROM ExamClassPosition e WHERE e.changeddate = :changeddate")
    , @NamedQuery(name = "ExamClassPosition.findBySynchStatus", query = "SELECT e FROM ExamClassPosition e WHERE e.synchStatus = :synchStatus")})
public class ExamClassPosition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private double gpa;
    private Integer position;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id", nullable = false)
    @ManyToOne(optional = false)
    private Student studentId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    @ManyToOne
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id", nullable = false)
    @ManyToOne(optional = false)
    private Section sectionId;
    @JoinColumn(name = "exam_id", referencedColumnName = "exam_id", nullable = false)
    @ManyToOne(optional = false)
    private Exam examId;

    public ExamClassPosition() {
    }

    public ExamClassPosition(Long id) {
        this.id = id;
    }

    public ExamClassPosition(Long id, double gpa) {
        this.id = id;
        this.gpa = gpa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
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

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExamClassPosition)) {
            return false;
        }
        ExamClassPosition other = (ExamClassPosition) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ExamClassPosition[ id=" + id + " ]";
    }
    
}
