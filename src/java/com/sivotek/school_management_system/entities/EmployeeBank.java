/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "employee_bank", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeBank.findAll", query = "SELECT e FROM EmployeeBank e")
    , @NamedQuery(name = "EmployeeBank.findByEmployeeBankId", query = "SELECT e FROM EmployeeBank e WHERE e.employeeBankId = :employeeBankId")
    , @NamedQuery(name = "EmployeeBank.findByBankName", query = "SELECT e FROM EmployeeBank e WHERE e.bankName = :bankName")
    , @NamedQuery(name = "EmployeeBank.findByBranchName", query = "SELECT e FROM EmployeeBank e WHERE e.branchName = :branchName")
    , @NamedQuery(name = "EmployeeBank.findByAccountName", query = "SELECT e FROM EmployeeBank e WHERE e.accountName = :accountName")
    , @NamedQuery(name = "EmployeeBank.findByAccountNumber", query = "SELECT e FROM EmployeeBank e WHERE e.accountNumber = :accountNumber")
    , @NamedQuery(name = "EmployeeBank.findBySynchStatus", query = "SELECT e FROM EmployeeBank e WHERE e.synchStatus = :synchStatus")})
public class EmployeeBank implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "employee_bank_id", nullable = false)
    private Long employeeBankId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "bank_name", nullable = false, length = 300)
    private String bankName;
    @Size(max = 300)
    @Column(name = "branch_name", length = 300)
    private String branchName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "account_name", nullable = false, length = 300)
    private String accountName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "account_number", nullable = false, length = 300)
    private String accountNumber;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;

    public EmployeeBank() {
    }

    public EmployeeBank(Long employeeBankId) {
        this.employeeBankId = employeeBankId;
    }

    public EmployeeBank(Long employeeBankId, String bankName, String accountName, String accountNumber) {
        this.employeeBankId = employeeBankId;
        this.bankName = bankName;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
    }

    public Long getEmployeeBankId() {
        return employeeBankId;
    }

    public void setEmployeeBankId(Long employeeBankId) {
        this.employeeBankId = employeeBankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeBankId != null ? employeeBankId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeBank)) {
            return false;
        }
        EmployeeBank other = (EmployeeBank) object;
        if ((this.employeeBankId == null && other.employeeBankId != null) || (this.employeeBankId != null && !this.employeeBankId.equals(other.employeeBankId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.EmployeeBank[ employeeBankId=" + employeeBankId + " ]";
    }
    
}
