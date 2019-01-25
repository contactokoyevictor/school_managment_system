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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "bank_account_privilege", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankAccountPrivilege.findAll", query = "SELECT b FROM BankAccountPrivilege b")
    , @NamedQuery(name = "BankAccountPrivilege.findById", query = "SELECT b FROM BankAccountPrivilege b WHERE b.id = :id")
    , @NamedQuery(name = "BankAccountPrivilege.findByCanDebit", query = "SELECT b FROM BankAccountPrivilege b WHERE b.canDebit = :canDebit")
    , @NamedQuery(name = "BankAccountPrivilege.findByCanCredit", query = "SELECT b FROM BankAccountPrivilege b WHERE b.canCredit = :canCredit")
    , @NamedQuery(name = "BankAccountPrivilege.findByCanTransfer", query = "SELECT b FROM BankAccountPrivilege b WHERE b.canTransfer = :canTransfer")
    , @NamedQuery(name = "BankAccountPrivilege.findByCanRollback", query = "SELECT b FROM BankAccountPrivilege b WHERE b.canRollback = :canRollback")
    , @NamedQuery(name = "BankAccountPrivilege.findByCreatedDate", query = "SELECT b FROM BankAccountPrivilege b WHERE b.createdDate = :createdDate")
    , @NamedQuery(name = "BankAccountPrivilege.findByLastModified", query = "SELECT b FROM BankAccountPrivilege b WHERE b.lastModified = :lastModified")
    , @NamedQuery(name = "BankAccountPrivilege.findBySynchStatus", query = "SELECT b FROM BankAccountPrivilege b WHERE b.synchStatus = :synchStatus")})
public class BankAccountPrivilege implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "can_debit", nullable = false)
    private boolean canDebit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "can_credit", nullable = false)
    private boolean canCredit;
    @Basic(optional = false)
    @NotNull
    @Column(name = "can_transfer", nullable = false)
    private boolean canTransfer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "can_rollback", nullable = false)
    private boolean canRollback;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "last_modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BankAccount accountId;
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;
    @JoinColumn(name = "created_by", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee createdBy;
    @JoinColumn(name = "last_modified_by", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee lastModifiedBy;

    public BankAccountPrivilege() {
    }

    public BankAccountPrivilege(Long id) {
        this.id = id;
    }

    public BankAccountPrivilege(Long id, boolean canDebit, boolean canCredit, boolean canTransfer, boolean canRollback, Date createdDate) {
        this.id = id;
        this.canDebit = canDebit;
        this.canCredit = canCredit;
        this.canTransfer = canTransfer;
        this.canRollback = canRollback;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getCanDebit() {
        return canDebit;
    }

    public void setCanDebit(boolean canDebit) {
        this.canDebit = canDebit;
    }

    public boolean getCanCredit() {
        return canCredit;
    }

    public void setCanCredit(boolean canCredit) {
        this.canCredit = canCredit;
    }

    public boolean getCanTransfer() {
        return canTransfer;
    }

    public void setCanTransfer(boolean canTransfer) {
        this.canTransfer = canTransfer;
    }

    public boolean getCanRollback() {
        return canRollback;
    }

    public void setCanRollback(boolean canRollback) {
        this.canRollback = canRollback;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public BankAccount getAccountId() {
        return accountId;
    }

    public void setAccountId(BankAccount accountId) {
        this.accountId = accountId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Employee getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Employee createdBy) {
        this.createdBy = createdBy;
    }

    public Employee getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Employee lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
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
        if (!(object instanceof BankAccountPrivilege)) {
            return false;
        }
        BankAccountPrivilege other = (BankAccountPrivilege) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BankAccountPrivilege[ id=" + id + " ]";
    }
    
}
