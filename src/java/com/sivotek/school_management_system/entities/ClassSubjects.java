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
@Table(name = "class_subjects", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassSubjects.findAll", query = "SELECT c FROM ClassSubjects c")
    , @NamedQuery(name = "ClassSubjects.findByClassSubjectId", query = "SELECT c FROM ClassSubjects c WHERE c.classSubjectId = :classSubjectId")
    , @NamedQuery(name = "ClassSubjects.findByCreateddate", query = "SELECT c FROM ClassSubjects c WHERE c.createddate = :createddate")
    , @NamedQuery(name = "ClassSubjects.findByModifieddate", query = "SELECT c FROM ClassSubjects c WHERE c.modifieddate = :modifieddate")
    , @NamedQuery(name = "ClassSubjects.findByStatus", query = "SELECT c FROM ClassSubjects c WHERE c.status = :status")
    , @NamedQuery(name = "ClassSubjects.findBySynchStatus", query = "SELECT c FROM ClassSubjects c WHERE c.synchStatus = :synchStatus")})
public class ClassSubjects implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_subject_id", nullable = false)
    private Long classSubjectId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    private Boolean status;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    @ManyToOne
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id", nullable = false)
    @ManyToOne(optional = false)
    private Term termId;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    @ManyToOne(optional = false)
    private Subject subjectId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee teacherId;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public ClassSubjects() {
    }

    public ClassSubjects(Long classSubjectId) {
        this.classSubjectId = classSubjectId;
    }

    public Long getClassSubjectId() {
        return classSubjectId;
    }

    public void setClassSubjectId(Long classSubjectId) {
        this.classSubjectId = classSubjectId;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
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

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Employee getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Employee teacherId) {
        this.teacherId = teacherId;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classSubjectId != null ? classSubjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassSubjects)) {
            return false;
        }
        ClassSubjects other = (ClassSubjects) object;
        if ((this.classSubjectId == null && other.classSubjectId != null) || (this.classSubjectId != null && !this.classSubjectId.equals(other.classSubjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ClassSubjects[ classSubjectId=" + classSubjectId + " ]";
    }
    
}
