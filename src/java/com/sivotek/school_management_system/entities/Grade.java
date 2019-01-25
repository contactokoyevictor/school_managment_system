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
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grade.findAll", query = "SELECT g FROM Grade g")
    , @NamedQuery(name = "Grade.findByGradeId", query = "SELECT g FROM Grade g WHERE g.gradeId = :gradeId")
    , @NamedQuery(name = "Grade.findByName", query = "SELECT g FROM Grade g WHERE g.name = :name")
    , @NamedQuery(name = "Grade.findByMarkFrom", query = "SELECT g FROM Grade g WHERE g.markFrom = :markFrom")
    , @NamedQuery(name = "Grade.findByMarkUpto", query = "SELECT g FROM Grade g WHERE g.markUpto = :markUpto")
    , @NamedQuery(name = "Grade.findByCreateddate", query = "SELECT g FROM Grade g WHERE g.createddate = :createddate")
    , @NamedQuery(name = "Grade.findByLastModified", query = "SELECT g FROM Grade g WHERE g.lastModified = :lastModified")
    , @NamedQuery(name = "Grade.findBySynchStatus", query = "SELECT g FROM Grade g WHERE g.synchStatus = :synchStatus")})
public class Grade implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "grade_id", nullable = false)
    private Long gradeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "grade_point", nullable = false, length = 65535)
    private String gradePoint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark_from", nullable = false)
    private int markFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark_upto", nullable = false)
    private int markUpto;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "academic_year", referencedColumnName = "year_id", nullable = false)
    @ManyToOne(optional = false)
    private AcademicYears academicYear;
    @JoinColumn(name = "class_category_id", referencedColumnName = "category_id", nullable = false)
    @ManyToOne(optional = false)
    private ClassCategory classCategoryId;

    public Grade() {
    }

    public Grade(Long gradeId) {
        this.gradeId = gradeId;
    }

    public Grade(Long gradeId, String name, String gradePoint, int markFrom, int markUpto, String comment, Date createddate) {
        this.gradeId = gradeId;
        this.name = name;
        this.gradePoint = gradePoint;
        this.markFrom = markFrom;
        this.markUpto = markUpto;
        this.comment = comment;
        this.createddate = createddate;
    }

    public Long getGradeId() {
        return gradeId;
    }

    public void setGradeId(Long gradeId) {
        this.gradeId = gradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGradePoint() {
        return gradePoint;
    }

    public void setGradePoint(String gradePoint) {
        this.gradePoint = gradePoint;
    }

    public int getMarkFrom() {
        return markFrom;
    }

    public void setMarkFrom(int markFrom) {
        this.markFrom = markFrom;
    }

    public int getMarkUpto() {
        return markUpto;
    }

    public void setMarkUpto(int markUpto) {
        this.markUpto = markUpto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gradeId != null ? gradeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grade)) {
            return false;
        }
        Grade other = (Grade) object;
        if ((this.gradeId == null && other.gradeId != null) || (this.gradeId != null && !this.gradeId.equals(other.gradeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Grade[ gradeId=" + gradeId + " ]";
    }
    
}
