/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"company_app_serever_auth_code"})
    , @UniqueConstraint(columnNames = {"pubkey"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c")
    , @NamedQuery(name = "Company.findByCompanyId", query = "SELECT c FROM Company c WHERE c.companyId = :companyId")
    , @NamedQuery(name = "Company.findByPubkey", query = "SELECT c FROM Company c WHERE c.pubkey = :pubkey")
    , @NamedQuery(name = "Company.findByCompanyName", query = "SELECT c FROM Company c WHERE c.companyName = :companyName")
    , @NamedQuery(name = "Company.findByCompanyShortName", query = "SELECT c FROM Company c WHERE c.companyShortName = :companyShortName")
    , @NamedQuery(name = "Company.findByCompanyMotto", query = "SELECT c FROM Company c WHERE c.companyMotto = :companyMotto")
    , @NamedQuery(name = "Company.findByCompanyWeb", query = "SELECT c FROM Company c WHERE c.companyWeb = :companyWeb")
    , @NamedQuery(name = "Company.findByCompanyAppSereverUrl", query = "SELECT c FROM Company c WHERE c.companyAppSereverUrl = :companyAppSereverUrl")
    , @NamedQuery(name = "Company.findByCompanyLogoExtension", query = "SELECT c FROM Company c WHERE c.companyLogoExtension = :companyLogoExtension")
    , @NamedQuery(name = "Company.findByCompanyAppSereverAuthCode", query = "SELECT c FROM Company c WHERE c.companyAppSereverAuthCode = :companyAppSereverAuthCode")
    , @NamedQuery(name = "Company.findBySynchStatus", query = "SELECT c FROM Company c WHERE c.synchStatus = :synchStatus")
    , @NamedQuery(name = "Company.findByCreateddate", query = "SELECT c FROM Company c WHERE c.createddate = :createddate")
    , @NamedQuery(name = "Company.findByChangeddate", query = "SELECT c FROM Company c WHERE c.changeddate = :changeddate")})
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "company_id", nullable = false)
    private Long companyId;
    private BigInteger pubkey;
    @Size(max = 255)
    @Column(name = "company_name", length = 255)
    private String companyName;
    @Size(max = 255)
    @Column(name = "company_short_name", length = 255)
    private String companyShortName;
    @Size(max = 255)
    @Column(name = "company_motto", length = 255)
    private String companyMotto;
    @Size(max = 255)
    @Column(name = "company_web", length = 255)
    private String companyWeb;
    @Size(max = 255)
    @Column(name = "company_app_serever_url", length = 255)
    private String companyAppSereverUrl;
    @Lob
    @Column(name = "company_logo")
    private byte[] companyLogo;
    @Size(max = 6)
    @Column(name = "company_logo_extension", length = 6)
    private String companyLogoExtension;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "company_app_serever_auth_code", nullable = false, length = 255)
    private String companyAppSereverAuthCode;
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
    @OneToMany(mappedBy = "companyId")
    private Collection<CompanyBranch> companyBranchCollection;

    public Company() {
    }

    public Company(Long companyId) {
        this.companyId = companyId;
    }

    public Company(Long companyId, String companyAppSereverAuthCode) {
        this.companyId = companyId;
        this.companyAppSereverAuthCode = companyAppSereverAuthCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public BigInteger getPubkey() {
        return pubkey;
    }

    public void setPubkey(BigInteger pubkey) {
        this.pubkey = pubkey;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyMotto() {
        return companyMotto;
    }

    public void setCompanyMotto(String companyMotto) {
        this.companyMotto = companyMotto;
    }

    public String getCompanyWeb() {
        return companyWeb;
    }

    public void setCompanyWeb(String companyWeb) {
        this.companyWeb = companyWeb;
    }

    public String getCompanyAppSereverUrl() {
        return companyAppSereverUrl;
    }

    public void setCompanyAppSereverUrl(String companyAppSereverUrl) {
        this.companyAppSereverUrl = companyAppSereverUrl;
    }

    public byte[] getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(byte[] companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyLogoExtension() {
        return companyLogoExtension;
    }

    public void setCompanyLogoExtension(String companyLogoExtension) {
        this.companyLogoExtension = companyLogoExtension;
    }

    public String getCompanyAppSereverAuthCode() {
        return companyAppSereverAuthCode;
    }

    public void setCompanyAppSereverAuthCode(String companyAppSereverAuthCode) {
        this.companyAppSereverAuthCode = companyAppSereverAuthCode;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyId != null ? companyId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyId == null && other.companyId != null) || (this.companyId != null && !this.companyId.equals(other.companyId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Company[ companyId=" + companyId + " ]";
    }
    
}
