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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "class_routine", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassRoutine.findAll", query = "SELECT c FROM ClassRoutine c")
    , @NamedQuery(name = "ClassRoutine.findByClassRoutineId", query = "SELECT c FROM ClassRoutine c WHERE c.classRoutineId = :classRoutineId")
    , @NamedQuery(name = "ClassRoutine.findByStartingTime", query = "SELECT c FROM ClassRoutine c WHERE c.startingTime = :startingTime")
    , @NamedQuery(name = "ClassRoutine.findByEndingTime", query = "SELECT c FROM ClassRoutine c WHERE c.endingTime = :endingTime")
    , @NamedQuery(name = "ClassRoutine.findByTimeStart", query = "SELECT c FROM ClassRoutine c WHERE c.timeStart = :timeStart")
    , @NamedQuery(name = "ClassRoutine.findByTimeStartAmpm", query = "SELECT c FROM ClassRoutine c WHERE c.timeStartAmpm = :timeStartAmpm")
    , @NamedQuery(name = "ClassRoutine.findByTimeEnd", query = "SELECT c FROM ClassRoutine c WHERE c.timeEnd = :timeEnd")
    , @NamedQuery(name = "ClassRoutine.findByTimeEndAmpm", query = "SELECT c FROM ClassRoutine c WHERE c.timeEndAmpm = :timeEndAmpm")
    , @NamedQuery(name = "ClassRoutine.findByTimeStartMin", query = "SELECT c FROM ClassRoutine c WHERE c.timeStartMin = :timeStartMin")
    , @NamedQuery(name = "ClassRoutine.findByTimeEndMin", query = "SELECT c FROM ClassRoutine c WHERE c.timeEndMin = :timeEndMin")
    , @NamedQuery(name = "ClassRoutine.findByCreateddate", query = "SELECT c FROM ClassRoutine c WHERE c.createddate = :createddate")
    , @NamedQuery(name = "ClassRoutine.findByChangeddate", query = "SELECT c FROM ClassRoutine c WHERE c.changeddate = :changeddate")
    , @NamedQuery(name = "ClassRoutine.findBySynchStatus", query = "SELECT c FROM ClassRoutine c WHERE c.synchStatus = :synchStatus")})
public class ClassRoutine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "class_routine_id", nullable = false)
    private Long classRoutineId;
    @Column(name = "starting_time")
    @Temporal(TemporalType.TIME)
    private Date startingTime;
    @Column(name = "ending_time")
    @Temporal(TemporalType.TIME)
    private Date endingTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_start", nullable = false)
    private int timeStart;
    @Column(name = "time_start_ampm")
    private Integer timeStartAmpm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_end", nullable = false)
    private int timeEnd;
    @Column(name = "time_end_ampm")
    private Integer timeEndAmpm;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_start_min", nullable = false)
    private int timeStartMin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time_end_min", nullable = false)
    private int timeEndMin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classRoutineId")
    private Collection<SubjectAttendance> subjectAttendanceCollection;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id", nullable = false)
    @ManyToOne(optional = false)
    private Class classId;
    @JoinColumn(name = "term_id", referencedColumnName = "term_id")
    @ManyToOne
    private Term termId;
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    @ManyToOne
    private Section sectionId;
    @JoinColumn(name = "subject_id", referencedColumnName = "subject_id", nullable = false)
    @ManyToOne(optional = false)
    private Subject subjectId;
    @JoinColumn(name = "day", referencedColumnName = "day_id", nullable = false)
    @ManyToOne(optional = false)
    private Days day;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public ClassRoutine() {
    }

    public ClassRoutine(Long classRoutineId) {
        this.classRoutineId = classRoutineId;
    }

    public ClassRoutine(Long classRoutineId, int timeStart, int timeEnd, int timeStartMin, int timeEndMin) {
        this.classRoutineId = classRoutineId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.timeStartMin = timeStartMin;
        this.timeEndMin = timeEndMin;
    }

    public Long getClassRoutineId() {
        return classRoutineId;
    }

    public void setClassRoutineId(Long classRoutineId) {
        this.classRoutineId = classRoutineId;
    }

    public Date getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(Date startingTime) {
        this.startingTime = startingTime;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getTimeStartAmpm() {
        return timeStartAmpm;
    }

    public void setTimeStartAmpm(Integer timeStartAmpm) {
        this.timeStartAmpm = timeStartAmpm;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Integer getTimeEndAmpm() {
        return timeEndAmpm;
    }

    public void setTimeEndAmpm(Integer timeEndAmpm) {
        this.timeEndAmpm = timeEndAmpm;
    }

    public int getTimeStartMin() {
        return timeStartMin;
    }

    public void setTimeStartMin(int timeStartMin) {
        this.timeStartMin = timeStartMin;
    }

    public int getTimeEndMin() {
        return timeEndMin;
    }

    public void setTimeEndMin(int timeEndMin) {
        this.timeEndMin = timeEndMin;
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

    @XmlTransient
    public Collection<SubjectAttendance> getSubjectAttendanceCollection() {
        return subjectAttendanceCollection;
    }

    public void setSubjectAttendanceCollection(Collection<SubjectAttendance> subjectAttendanceCollection) {
        this.subjectAttendanceCollection = subjectAttendanceCollection;
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

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    public Days getDay() {
        return day;
    }

    public void setDay(Days day) {
        this.day = day;
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
        hash += (classRoutineId != null ? classRoutineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassRoutine)) {
            return false;
        }
        ClassRoutine other = (ClassRoutine) object;
        if ((this.classRoutineId == null && other.classRoutineId != null) || (this.classRoutineId != null && !this.classRoutineId.equals(other.classRoutineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ClassRoutine[ classRoutineId=" + classRoutineId + " ]";
    }
    
}
