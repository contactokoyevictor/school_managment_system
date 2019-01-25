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
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c")
    , @NamedQuery(name = "Class.findByClassId", query = "SELECT c FROM Class c WHERE c.classId = :classId")
    , @NamedQuery(name = "Class.findByAcademicYearAndCategoryId", query = "SELECT c FROM Class c WHERE c.academicYear.yearId = :yearId AND c.classCategoryId.categoryId = :categoryId")
    , @NamedQuery(name = "Class.findByCreateddate", query = "SELECT c FROM Class c WHERE c.createddate = :createddate")
    , @NamedQuery(name = "Class.findByModifiedDate", query = "SELECT c FROM Class c WHERE c.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "Class.findBySynchStatus", query = "SELECT c FROM Class c WHERE c.synchStatus = :synchStatus")})
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_id", nullable = false)
    private Long classId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "name_numeric", nullable = false, length = 65535)
    private String nameNumeric;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<SubjectAttendance> subjectAttendanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<StudentBehavouralTrait> studentBehavouralTraitCollection;
    @OneToMany(mappedBy = "classId")
    private Collection<ClassSubjects> classSubjectsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<ExamClassPosition> examClassPositionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<Student> studentCollection;
    @OneToMany(mappedBy = "classId")
    private Collection<StudentDailyAttendance> studentDailyAttendanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<ClassRoutine> classRoutineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<ExamMark> examMarkCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id")
    @ManyToOne
    private AcademicYears academicYear;
    @JoinColumn(name = "class_category_id", referencedColumnName = "category_id")
    @ManyToOne
    private ClassCategory classCategoryId;
    @JoinColumn(name = "teacher_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee teacherId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    private Collection<CaMark> caMarkCollection;

    public Class() {
    }

    public Class(Long classId) {
        this.classId = classId;
    }

    public Class(Long classId, String name, String nameNumeric) {
        this.classId = classId;
        this.name = name;
        this.nameNumeric = nameNumeric;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameNumeric() {
        return nameNumeric;
    }

    public void setNameNumeric(String nameNumeric) {
        this.nameNumeric = nameNumeric;
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
    public Collection<PrincipalTerminalExamComment> getPrincipalTerminalExamCommentCollection() {
        return principalTerminalExamCommentCollection;
    }

    public void setPrincipalTerminalExamCommentCollection(Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection) {
        this.principalTerminalExamCommentCollection = principalTerminalExamCommentCollection;
    }

    @XmlTransient
    public Collection<SubjectAttendance> getSubjectAttendanceCollection() {
        return subjectAttendanceCollection;
    }

    public void setSubjectAttendanceCollection(Collection<SubjectAttendance> subjectAttendanceCollection) {
        this.subjectAttendanceCollection = subjectAttendanceCollection;
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

    public ClassCategory getClassCategoryId() {
        return classCategoryId;
    }

    public void setClassCategoryId(ClassCategory classCategoryId) {
        this.classCategoryId = classCategoryId;
    }

    public Employee getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Employee teacherId) {
        this.teacherId = teacherId;
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
        hash += (classId != null ? classId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.classId == null && other.classId != null) || (this.classId != null && !this.classId.equals(other.classId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Class[ classId=" + classId + " ]";
    }
    
}
