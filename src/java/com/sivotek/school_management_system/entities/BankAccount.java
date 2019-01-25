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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "bank_account", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankAccount.findAll", query = "SELECT b FROM BankAccount b")
    , @NamedQuery(name = "BankAccount.findById", query = "SELECT b FROM BankAccount b WHERE b.id = :id")
    , @NamedQuery(name = "BankAccount.findByAcoountType", query = "SELECT b FROM BankAccount b WHERE b.acoountType = :acoountType")
    , @NamedQuery(name = "BankAccount.findByName", query = "SELECT b FROM BankAccount b WHERE b.name = :name")
    , @NamedQuery(name = "BankAccount.findByAccountNumber", query = "SELECT b FROM BankAccount b WHERE b.accountNumber = :accountNumber")
    , @NamedQuery(name = "BankAccount.findByCashCode", query = "SELECT b FROM BankAccount b WHERE b.cashCode = :cashCode")
    , @NamedQuery(name = "BankAccount.findByTransactionType", query = "SELECT b FROM BankAccount b WHERE b.transactionType = :transactionType")
    , @NamedQuery(name = "BankAccount.findByStatus", query = "SELECT b FROM BankAccount b WHERE b.status = :status")
    , @NamedQuery(name = "BankAccount.findByCreationDate", query = "SELECT b FROM BankAccount b WHERE b.creationDate = :creationDate")
    , @NamedQuery(name = "BankAccount.findBySynchStatus", query = "SELECT b FROM BankAccount b WHERE b.synchStatus = :synchStatus")})
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "acoount_type", nullable = false, length = 255)
    private String acoountType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "account_number", nullable = false, length = 255)
    private String accountNumber;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "cash_code", nullable = false, length = 255)
    private String cashCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_type", nullable = false)
    private boolean transactionType;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean status;
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<BankAccountBalance> bankAccountBalanceCollection;
    @JoinColumn(name = "institution_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BankInstitution institutionId;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bankAccountId")
    private Collection<AccountTransaction> accountTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountId")
    private Collection<BankAccountPrivilege> bankAccountPrivilegeCollection;

    public BankAccount() {
    }

    public BankAccount(Long id) {
        this.id = id;
    }

    public BankAccount(Long id, String acoountType, String name, String accountNumber, String cashCode, boolean transactionType, boolean status) {
        this.id = id;
        this.acoountType = acoountType;
        this.name = name;
        this.accountNumber = accountNumber;
        this.cashCode = cashCode;
        this.transactionType = transactionType;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAcoountType() {
        return acoountType;
    }

    public void setAcoountType(String acoountType) {
        this.acoountType = acoountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCashCode() {
        return cashCode;
    }

    public void setCashCode(String cashCode) {
        this.cashCode = cashCode;
    }

    public boolean getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(boolean transactionType) {
        this.transactionType = transactionType;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<BankAccountBalance> getBankAccountBalanceCollection() {
        return bankAccountBalanceCollection;
    }

    public void setBankAccountBalanceCollection(Collection<BankAccountBalance> bankAccountBalanceCollection) {
        this.bankAccountBalanceCollection = bankAccountBalanceCollection;
    }

    public BankInstitution getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(BankInstitution institutionId) {
        this.institutionId = institutionId;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    @XmlTransient
    public Collection<AccountTransaction> getAccountTransactionCollection() {
        return accountTransactionCollection;
    }

    public void setAccountTransactionCollection(Collection<AccountTransaction> accountTransactionCollection) {
        this.accountTransactionCollection = accountTransactionCollection;
    }

    @XmlTransient
    public Collection<BankAccountPrivilege> getBankAccountPrivilegeCollection() {
        return bankAccountPrivilegeCollection;
    }

    public void setBankAccountPrivilegeCollection(Collection<BankAccountPrivilege> bankAccountPrivilegeCollection) {
        this.bankAccountPrivilegeCollection = bankAccountPrivilegeCollection;
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
        if (!(object instanceof BankAccount)) {
            return false;
        }
        BankAccount other = (BankAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BankAccount[ id=" + id + " ]";
    }
    
}
