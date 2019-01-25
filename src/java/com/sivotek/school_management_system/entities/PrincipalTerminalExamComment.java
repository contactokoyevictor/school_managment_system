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
@Table(name = "principal_terminal_exam_comment", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrincipalTerminalExamComment.findAll", query = "SELECT p FROM PrincipalTerminalExamComment p")
    , @NamedQuery(name = "PrincipalTerminalExamComment.findById", query = "SELECT p FROM PrincipalTerminalExamComment p WHERE p.id = :id")
    , @NamedQuery(name = "PrincipalTerminalExamComment.findByExamId", query = "SELECT p FROM PrincipalTerminalExamComment p WHERE p.examId = :examId")
    , @NamedQuery(name = "PrincipalTerminalExamComment.findByCreateddate", query = "SELECT p FROM PrincipalTerminalExamComment p WHERE p.createddate = :createddate")
    , @NamedQuery(name = "PrincipalTerminalExamComment.findByChangeddate", query = "SELECT p FROM PrincipalTerminalExamComment p WHERE p.changeddate = :changeddate")
    , @NamedQuery(name = "PrincipalTerminalExamComment.findBySynchStatus", query = "SELECT p FROM PrincipalTerminalExamComment p WHERE p.synchStatus = :synchStatus")})
public class PrincipalTerminalExamComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "exam_id", nullable = false)
    private long examId;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String comment;
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
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id", nullable = false)
    @ManyToOne(optional = false)
    private Section sectionId;

    public PrincipalTerminalExamComment() {
    }

    public PrincipalTerminalExamComment(Long id) {
        this.id = id;
    }

    public PrincipalTerminalExamComment(Long id, long examId) {
        this.id = id;
        this.examId = examId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getExamId() {
        return examId;
    }

    public void setExamId(long examId) {
        this.examId = examId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrincipalTerminalExamComment)) {
            return false;
        }
        PrincipalTerminalExamComment other = (PrincipalTerminalExamComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.PrincipalTerminalExamComment[ id=" + id + " ]";
    }
    
}
