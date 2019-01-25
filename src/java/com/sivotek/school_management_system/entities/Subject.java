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
    @UniqueConstraint(columnNames = {"name", "code", "class_category_id", "academic_year"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s")
    , @NamedQuery(name = "Subject.findBySubjectId", query = "SELECT s FROM Subject s WHERE s.subjectId = :subjectId")
    , @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name")
    , @NamedQuery(name = "Subject.findByCode", query = "SELECT s FROM Subject s WHERE s.code = :code")
    , @NamedQuery(name = "Subject.findByCreateddate", query = "SELECT s FROM Subject s WHERE s.createddate = :createddate")
    , @NamedQuery(name = "Subject.findByModifieddate", query = "SELECT s FROM Subject s WHERE s.modifieddate = :modifieddate")
    , @NamedQuery(name = "Subject.findBySynchStatus", query = "SELECT s FROM Subject s WHERE s.synchStatus = :synchStatus")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;
    @Size(max = 255)
    @Column(length = 255)
    private String name;
    @Size(max = 255)
    @Column(length = 255)
    private String code;
    @Lob
    @Size(max = 65535)
    @Column(name = "nick_name", length = 65535)
    private String nickName;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "class_category_id", referencedColumnName = "category_id", nullable = false)
    @ManyToOne(optional = false)
    private ClassCategory classCategoryId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id")
    @ManyToOne
    private AcademicYears academicYear;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<SubjectAttendance> subjectAttendanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<ClassSubjects> classSubjectsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<ClassRoutine> classRoutineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<ExamMark> examMarkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Collection<CaMark> caMarkCollection;

    public Subject() {
    }

    public Subject(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Subject(Long subjectId, Date createddate) {
        this.subjectId = subjectId;
        this.createddate = createddate;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public ClassCategory getClassCategoryId() {
        return classCategoryId;
    }

    public void setClassCategoryId(ClassCategory classCategoryId) {
        this.classCategoryId = classCategoryId;
    }

    public AcademicYears getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYears academicYear) {
        this.academicYear = academicYear;
    }

    @XmlTransient
    public Collection<SubjectAttendance> getSubjectAttendanceCollection() {
        return subjectAttendanceCollection;
    }

    public void setSubjectAttendanceCollection(Collection<SubjectAttendance> subjectAttendanceCollection) {
        this.subjectAttendanceCollection = subjectAttendanceCollection;
    }

    @XmlTransient
    public Collection<ClassSubjects> getClassSubjectsCollection() {
        return classSubjectsCollection;
    }

    public void setClassSubjectsCollection(Collection<ClassSubjects> classSubjectsCollection) {
        this.classSubjectsCollection = classSubjectsCollection;
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
        hash += (subjectId != null ? subjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.subjectId == null && other.subjectId != null) || (this.subjectId != null && !this.subjectId.equals(other.subjectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Subject[ subjectId=" + subjectId + " ]";
    }
    
}
