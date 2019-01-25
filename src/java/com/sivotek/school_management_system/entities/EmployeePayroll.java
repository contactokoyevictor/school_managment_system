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
@Table(name = "employee_payroll", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeePayroll.findAll", query = "SELECT e FROM EmployeePayroll e")
    , @NamedQuery(name = "EmployeePayroll.findByPayrollId", query = "SELECT e FROM EmployeePayroll e WHERE e.payrollId = :payrollId")
    , @NamedQuery(name = "EmployeePayroll.findByBasicSalary", query = "SELECT e FROM EmployeePayroll e WHERE e.basicSalary = :basicSalary")
    , @NamedQuery(name = "EmployeePayroll.findByHouseRentAllowance", query = "SELECT e FROM EmployeePayroll e WHERE e.houseRentAllowance = :houseRentAllowance")
    , @NamedQuery(name = "EmployeePayroll.findByMedicalAllowance", query = "SELECT e FROM EmployeePayroll e WHERE e.medicalAllowance = :medicalAllowance")
    , @NamedQuery(name = "EmployeePayroll.findBySpecialAllowance", query = "SELECT e FROM EmployeePayroll e WHERE e.specialAllowance = :specialAllowance")
    , @NamedQuery(name = "EmployeePayroll.findByFuelAllowance", query = "SELECT e FROM EmployeePayroll e WHERE e.fuelAllowance = :fuelAllowance")
    , @NamedQuery(name = "EmployeePayroll.findByPhoneBillAllowance", query = "SELECT e FROM EmployeePayroll e WHERE e.phoneBillAllowance = :phoneBillAllowance")
    , @NamedQuery(name = "EmployeePayroll.findByOtherAllowance", query = "SELECT e FROM EmployeePayroll e WHERE e.otherAllowance = :otherAllowance")
    , @NamedQuery(name = "EmployeePayroll.findByTaxDeduction", query = "SELECT e FROM EmployeePayroll e WHERE e.taxDeduction = :taxDeduction")
    , @NamedQuery(name = "EmployeePayroll.findByProvidentFund", query = "SELECT e FROM EmployeePayroll e WHERE e.providentFund = :providentFund")
    , @NamedQuery(name = "EmployeePayroll.findByOtherDeduction", query = "SELECT e FROM EmployeePayroll e WHERE e.otherDeduction = :otherDeduction")
    , @NamedQuery(name = "EmployeePayroll.findByEmploymentType", query = "SELECT e FROM EmployeePayroll e WHERE e.employmentType = :employmentType")
    , @NamedQuery(name = "EmployeePayroll.findBySynchStatus", query = "SELECT e FROM EmployeePayroll e WHERE e.synchStatus = :synchStatus")})
public class EmployeePayroll implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "payroll_id", nullable = false)
    private Long payrollId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "basic_salary", nullable = false)
    private int basicSalary;
    @Size(max = 200)
    @Column(name = "house_rent_allowance", length = 200)
    private String houseRentAllowance;
    @Size(max = 200)
    @Column(name = "medical_allowance", length = 200)
    private String medicalAllowance;
    @Size(max = 200)
    @Column(name = "special_allowance", length = 200)
    private String specialAllowance;
    @Size(max = 200)
    @Column(name = "fuel_allowance", length = 200)
    private String fuelAllowance;
    @Size(max = 200)
    @Column(name = "phone_bill_allowance", length = 200)
    private String phoneBillAllowance;
    @Size(max = 200)
    @Column(name = "other_allowance", length = 200)
    private String otherAllowance;
    @Size(max = 200)
    @Column(name = "tax_deduction", length = 200)
    private String taxDeduction;
    @Size(max = 200)
    @Column(name = "provident_fund", length = 200)
    private String providentFund;
    @Size(max = 200)
    @Column(name = "other_deduction", length = 200)
    private String otherDeduction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "employment_type", nullable = false)
    private boolean employmentType;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;

    public EmployeePayroll() {
    }

    public EmployeePayroll(Long payrollId) {
        this.payrollId = payrollId;
    }

    public EmployeePayroll(Long payrollId, int basicSalary, boolean employmentType) {
        this.payrollId = payrollId;
        this.basicSalary = basicSalary;
        this.employmentType = employmentType;
    }

    public Long getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(Long payrollId) {
        this.payrollId = payrollId;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

    public String getHouseRentAllowance() {
        return houseRentAllowance;
    }

    public void setHouseRentAllowance(String houseRentAllowance) {
        this.houseRentAllowance = houseRentAllowance;
    }

    public String getMedicalAllowance() {
        return medicalAllowance;
    }

    public void setMedicalAllowance(String medicalAllowance) {
        this.medicalAllowance = medicalAllowance;
    }

    public String getSpecialAllowance() {
        return specialAllowance;
    }

    public void setSpecialAllowance(String specialAllowance) {
        this.specialAllowance = specialAllowance;
    }

    public String getFuelAllowance() {
        return fuelAllowance;
    }

    public void setFuelAllowance(String fuelAllowance) {
        this.fuelAllowance = fuelAllowance;
    }

    public String getPhoneBillAllowance() {
        return phoneBillAllowance;
    }

    public void setPhoneBillAllowance(String phoneBillAllowance) {
        this.phoneBillAllowance = phoneBillAllowance;
    }

    public String getOtherAllowance() {
        return otherAllowance;
    }

    public void setOtherAllowance(String otherAllowance) {
        this.otherAllowance = otherAllowance;
    }

    public String getTaxDeduction() {
        return taxDeduction;
    }

    public void setTaxDeduction(String taxDeduction) {
        this.taxDeduction = taxDeduction;
    }

    public String getProvidentFund() {
        return providentFund;
    }

    public void setProvidentFund(String providentFund) {
        this.providentFund = providentFund;
    }

    public String getOtherDeduction() {
        return otherDeduction;
    }

    public void setOtherDeduction(String otherDeduction) {
        this.otherDeduction = otherDeduction;
    }

    public boolean getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(boolean employmentType) {
        this.employmentType = employmentType;
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
        hash += (payrollId != null ? payrollId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeePayroll)) {
            return false;
        }
        EmployeePayroll other = (EmployeePayroll) object;
        if ((this.payrollId == null && other.payrollId != null) || (this.payrollId != null && !this.payrollId.equals(other.payrollId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.EmployeePayroll[ payrollId=" + payrollId + " ]";
    }
    
}
