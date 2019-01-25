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
@Table(name = "behavoural_trait", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BehavouralTrait.findAll", query = "SELECT b FROM BehavouralTrait b")
    , @NamedQuery(name = "BehavouralTrait.findById", query = "SELECT b FROM BehavouralTrait b WHERE b.id = :id")
    , @NamedQuery(name = "BehavouralTrait.findByCreateddate", query = "SELECT b FROM BehavouralTrait b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BehavouralTrait.findByChangeddate", query = "SELECT b FROM BehavouralTrait b WHERE b.changeddate = :changeddate")
    , @NamedQuery(name = "BehavouralTrait.findBySynchStatus", query = "SELECT b FROM BehavouralTrait b WHERE b.synchStatus = :synchStatus")})
public class BehavouralTrait implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "traitId")
    private Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollection;

    public BehavouralTrait() {
    }

    public BehavouralTrait(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @XmlTransient
    public Collection<BehavouralTraitRatingKeys> getBehavouralTraitRatingKeysCollection() {
        return behavouralTraitRatingKeysCollection;
    }

    public void setBehavouralTraitRatingKeysCollection(Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollection) {
        this.behavouralTraitRatingKeysCollection = behavouralTraitRatingKeysCollection;
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
        if (!(object instanceof BehavouralTrait)) {
            return false;
        }
        BehavouralTrait other = (BehavouralTrait) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BehavouralTrait[ id=" + id + " ]";
    }
    
}
