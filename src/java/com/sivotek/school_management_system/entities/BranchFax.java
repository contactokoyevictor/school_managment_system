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
@Table(name = "branch_fax", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"fax_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchFax.findAll", query = "SELECT b FROM BranchFax b")
    , @NamedQuery(name = "BranchFax.findByFaxId", query = "SELECT b FROM BranchFax b WHERE b.faxId = :faxId")
    , @NamedQuery(name = "BranchFax.findBySynchStatus", query = "SELECT b FROM BranchFax b WHERE b.synchStatus = :synchStatus")
    , @NamedQuery(name = "BranchFax.findByCreateddate", query = "SELECT b FROM BranchFax b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BranchFax.findByChangeddate", query = "SELECT b FROM BranchFax b WHERE b.changeddate = :changeddate")})
public class BranchFax implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "fax_id", nullable = false)
    private Long faxId;
    @Lob
    @Size(max = 65535)
    @Column(name = "fax_line1", length = 65535)
    private String faxLine1;
    @Lob
    @Size(max = 65535)
    @Column(name = "fax_line2", length = 65535)
    private String faxLine2;
    @Lob
    @Size(max = 65535)
    @Column(name = "fax_line3", length = 65535)
    private String faxLine3;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @OneToMany(mappedBy = "faxId")
    private Collection<CompanyBranch> companyBranchCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public BranchFax() {
    }

    public BranchFax(Long faxId) {
        this.faxId = faxId;
    }

    public Long getFaxId() {
        return faxId;
    }

    public void setFaxId(Long faxId) {
        this.faxId = faxId;
    }

    public String getFaxLine1() {
        return faxLine1;
    }

    public void setFaxLine1(String faxLine1) {
        this.faxLine1 = faxLine1;
    }

    public String getFaxLine2() {
        return faxLine2;
    }

    public void setFaxLine2(String faxLine2) {
        this.faxLine2 = faxLine2;
    }

    public String getFaxLine3() {
        return faxLine3;
    }

    public void setFaxLine3(String faxLine3) {
        this.faxLine3 = faxLine3;
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
        hash += (faxId != null ? faxId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchFax)) {
            return false;
        }
        BranchFax other = (BranchFax) object;
        if ((this.faxId == null && other.faxId != null) || (this.faxId != null && !this.faxId.equals(other.faxId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BranchFax[ faxId=" + faxId + " ]";
    }
    
}
