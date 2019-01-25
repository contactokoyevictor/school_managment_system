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
@Table(name = "branch_phone", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"phone_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchPhone.findAll", query = "SELECT b FROM BranchPhone b")
    , @NamedQuery(name = "BranchPhone.findByPhoneId", query = "SELECT b FROM BranchPhone b WHERE b.phoneId = :phoneId")
    , @NamedQuery(name = "BranchPhone.findBySynchStatus", query = "SELECT b FROM BranchPhone b WHERE b.synchStatus = :synchStatus")
    , @NamedQuery(name = "BranchPhone.findByCreateddate", query = "SELECT b FROM BranchPhone b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BranchPhone.findByChangeddate", query = "SELECT b FROM BranchPhone b WHERE b.changeddate = :changeddate")})
public class BranchPhone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "phone_id", nullable = false)
    private Long phoneId;
    @Lob
    @Size(max = 65535)
    @Column(name = "phone_line1", length = 65535)
    private String phoneLine1;
    @Lob
    @Size(max = 65535)
    @Column(name = "phone_line2", length = 65535)
    private String phoneLine2;
    @Lob
    @Size(max = 65535)
    @Column(name = "phone_line3", length = 65535)
    private String phoneLine3;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @OneToMany(mappedBy = "phoneId")
    private Collection<CompanyBranch> companyBranchCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public BranchPhone() {
    }

    public BranchPhone(Long phoneId) {
        this.phoneId = phoneId;
    }

    public Long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Long phoneId) {
        this.phoneId = phoneId;
    }

    public String getPhoneLine1() {
        return phoneLine1;
    }

    public void setPhoneLine1(String phoneLine1) {
        this.phoneLine1 = phoneLine1;
    }

    public String getPhoneLine2() {
        return phoneLine2;
    }

    public void setPhoneLine2(String phoneLine2) {
        this.phoneLine2 = phoneLine2;
    }

    public String getPhoneLine3() {
        return phoneLine3;
    }

    public void setPhoneLine3(String phoneLine3) {
        this.phoneLine3 = phoneLine3;
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
        hash += (phoneId != null ? phoneId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchPhone)) {
            return false;
        }
        BranchPhone other = (BranchPhone) object;
        if ((this.phoneId == null && other.phoneId != null) || (this.phoneId != null && !this.phoneId.equals(other.phoneId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BranchPhone[ phoneId=" + phoneId + " ]";
    }
    
}
