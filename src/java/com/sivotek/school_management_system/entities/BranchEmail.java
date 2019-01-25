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
@Table(name = "branch_email", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BranchEmail.findAll", query = "SELECT b FROM BranchEmail b")
    , @NamedQuery(name = "BranchEmail.findByEmailId", query = "SELECT b FROM BranchEmail b WHERE b.emailId = :emailId")
    , @NamedQuery(name = "BranchEmail.findBySynchStatus", query = "SELECT b FROM BranchEmail b WHERE b.synchStatus = :synchStatus")
    , @NamedQuery(name = "BranchEmail.findByCreateddate", query = "SELECT b FROM BranchEmail b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BranchEmail.findByChangeddate", query = "SELECT b FROM BranchEmail b WHERE b.changeddate = :changeddate")})
public class BranchEmail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "email_id", nullable = false)
    private Long emailId;
    @Lob
    @Size(max = 65535)
    @Column(name = "email_line1", length = 65535)
    private String emailLine1;
    @Lob
    @Size(max = 65535)
    @Column(name = "email_line2", length = 65535)
    private String emailLine2;
    @Lob
    @Size(max = 65535)
    @Column(name = "email_line3", length = 65535)
    private String emailLine3;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @OneToMany(mappedBy = "emailId")
    private Collection<CompanyBranch> companyBranchCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public BranchEmail() {
    }

    public BranchEmail(Long emailId) {
        this.emailId = emailId;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getEmailLine1() {
        return emailLine1;
    }

    public void setEmailLine1(String emailLine1) {
        this.emailLine1 = emailLine1;
    }

    public String getEmailLine2() {
        return emailLine2;
    }

    public void setEmailLine2(String emailLine2) {
        this.emailLine2 = emailLine2;
    }

    public String getEmailLine3() {
        return emailLine3;
    }

    public void setEmailLine3(String emailLine3) {
        this.emailLine3 = emailLine3;
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
        hash += (emailId != null ? emailId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BranchEmail)) {
            return false;
        }
        BranchEmail other = (BranchEmail) object;
        if ((this.emailId == null && other.emailId != null) || (this.emailId != null && !this.emailId.equals(other.emailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BranchEmail[ emailId=" + emailId + " ]";
    }
    
}
