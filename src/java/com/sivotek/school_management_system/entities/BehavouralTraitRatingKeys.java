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
@Table(name = "behavoural_trait_rating_keys", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BehavouralTraitRatingKeys.findAll", query = "SELECT b FROM BehavouralTraitRatingKeys b")
    , @NamedQuery(name = "BehavouralTraitRatingKeys.findById", query = "SELECT b FROM BehavouralTraitRatingKeys b WHERE b.id = :id")
    , @NamedQuery(name = "BehavouralTraitRatingKeys.findByKey", query = "SELECT b FROM BehavouralTraitRatingKeys b WHERE b.key = :key")
    , @NamedQuery(name = "BehavouralTraitRatingKeys.findByCreateddate", query = "SELECT b FROM BehavouralTraitRatingKeys b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BehavouralTraitRatingKeys.findByChangeddate", query = "SELECT b FROM BehavouralTraitRatingKeys b WHERE b.changeddate = :changeddate")
    , @NamedQuery(name = "BehavouralTraitRatingKeys.findBySynchStatus", query = "SELECT b FROM BehavouralTraitRatingKeys b WHERE b.synchStatus = :synchStatus")})
public class BehavouralTraitRatingKeys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    private Integer key;
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
    @JoinColumn(name = "trait_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BehavouralTrait traitId;

    public BehavouralTraitRatingKeys() {
    }

    public BehavouralTraitRatingKeys(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
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

    public BehavouralTrait getTraitId() {
        return traitId;
    }

    public void setTraitId(BehavouralTrait traitId) {
        this.traitId = traitId;
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
        if (!(object instanceof BehavouralTraitRatingKeys)) {
            return false;
        }
        BehavouralTraitRatingKeys other = (BehavouralTraitRatingKeys) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BehavouralTraitRatingKeys[ id=" + id + " ]";
    }
    
}
