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
@Table(name = "branch_address", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"address_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchAddress.findAll", query = "SELECT b FROM BranchAddress b")
    , @NamedQuery(name = "BranchAddress.findByAddressId", query = "SELECT b FROM BranchAddress b WHERE b.addressId = :addressId")
    , @NamedQuery(name = "BranchAddress.findBySynchStatus", query = "SELECT b FROM BranchAddress b WHERE b.synchStatus = :synchStatus")
    , @NamedQuery(name = "BranchAddress.findByCreateddate", query = "SELECT b FROM BranchAddress b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BranchAddress.findByChangeddate", query = "SELECT b FROM BranchAddress b WHERE b.changeddate = :changeddate")})
public class BranchAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "address_id", nullable = false)
    private Long addressId;
    @Lob
    @Size(max = 65535)
    @Column(name = "address_line", length = 65535)
    private String addressLine;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @OneToMany(mappedBy = "addressId")
    private Collection<CompanyBranch> companyBranchCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public BranchAddress() {
    }

    public BranchAddress(Long addressId) {
        this.addressId = addressId;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
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

    @XmlTransient
    public Collection<CompanyBranch> getCompanyBranchCollection() {
        return companyBranchCollection;
    }

    public void setCompanyBranchCollection(Collection<CompanyBranch> companyBranchCollection) {
        this.companyBranchCollection = companyBranchCollection;
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
        hash += (addressId != null ? addressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchAddress)) {
            return false;
        }
        BranchAddress other = (BranchAddress) object;
        if ((this.addressId == null && other.addressId != null) || (this.addressId != null && !this.addressId.equals(other.addressId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BranchAddress[ addressId=" + addressId + " ]";
    }
    
}
