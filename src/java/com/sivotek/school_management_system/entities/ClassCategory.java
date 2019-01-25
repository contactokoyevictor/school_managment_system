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
@Table(name = "class_category", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassCategory.findAll", query = "SELECT c FROM ClassCategory c")
    , @NamedQuery(name = "ClassCategory.findByCategoryId", query = "SELECT c FROM ClassCategory c WHERE c.categoryId = :categoryId")
    , @NamedQuery(name = "ClassCategory.findByCreateddate", query = "SELECT c FROM ClassCategory c WHERE c.createddate = :createddate")
    , @NamedQuery(name = "ClassCategory.findByModifiedDate", query = "SELECT c FROM ClassCategory c WHERE c.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "ClassCategory.findBySynchStatus", query = "SELECT c FROM ClassCategory c WHERE c.synchStatus = :synchStatus")})
public class ClassCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "category_id", nullable = false)
    private Long categoryId;
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
    @Column(name = "nick_name", nullable = false, length = 65535)
    private String nickName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classCategoryId")
    private Collection<Subject> subjectCollection;
    @OneToMany(mappedBy = "classCategoryId")
    private Collection<Section> sectionCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classCategoryId")
    private Collection<Exam> examCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classCategoryId")
    private Collection<Grade> gradeCollection;
    @OneToMany(mappedBy = "classCategoryId")
    private Collection<Class> classCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classCategoryId")
    private Collection<BehavouralTrait> behavouralTraitCollection;

    public ClassCategory() {
    }

    public ClassCategory(Long categoryId) {
        this.categoryId = categoryId;
    }

    public ClassCategory(Long categoryId, String name, String nickName) {
        this.categoryId = categoryId;
        this.name = name;
        this.nickName = nickName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Collection<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(Collection<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }

    @XmlTransient
    public Collection<Section> getSectionCollection() {
        return sectionCollection;
    }

    public void setSectionCollection(Collection<Section> sectionCollection) {
        this.sectionCollection = sectionCollection;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @XmlTransient
    public Collection<Grade> getGradeCollection() {
        return gradeCollection;
    }

    public void setGradeCollection(Collection<Grade> gradeCollection) {
        this.gradeCollection = gradeCollection;
    }

    @XmlTransient
    public Collection<Class> getClassCollection() {
        return classCollection;
    }

    public void setClassCollection(Collection<Class> classCollection) {
        this.classCollection = classCollection;
    }

    @XmlTransient
    public Collection<BehavouralTrait> getBehavouralTraitCollection() {
        return behavouralTraitCollection;
    }

    public void setBehavouralTraitCollection(Collection<BehavouralTrait> behavouralTraitCollection) {
        this.behavouralTraitCollection = behavouralTraitCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryId != null ? categoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassCategory)) {
            return false;
        }
        ClassCategory other = (ClassCategory) object;
        if ((this.categoryId == null && other.categoryId != null) || (this.categoryId != null && !this.categoryId.equals(other.categoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ClassCategory[ categoryId=" + categoryId + " ]";
    }
    
}
