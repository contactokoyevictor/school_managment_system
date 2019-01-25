/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "salary_payment_details", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SalaryPaymentDetails.findAll", query = "SELECT s FROM SalaryPaymentDetails s")
    , @NamedQuery(name = "SalaryPaymentDetails.findBySalaryPaymentId", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.salaryPaymentId = :salaryPaymentId")
    , @NamedQuery(name = "SalaryPaymentDetails.findByBranchId", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.branchId = :branchId")
    , @NamedQuery(name = "SalaryPaymentDetails.findByEmployeeId", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.employeeId = :employeeId")
    , @NamedQuery(name = "SalaryPaymentDetails.findByInvoiceNumber", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.invoiceNumber = :invoiceNumber")
    , @NamedQuery(name = "SalaryPaymentDetails.findByBasicSalary", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.basicSalary = :basicSalary")
    , @NamedQuery(name = "SalaryPaymentDetails.findByHouseRentAllowance", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.houseRentAllowance = :houseRentAllowance")
    , @NamedQuery(name = "SalaryPaymentDetails.findByMedicalAllowance", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.medicalAllowance = :medicalAllowance")
    , @NamedQuery(name = "SalaryPaymentDetails.findBySpecialAllowance", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.specialAllowance = :specialAllowance")
    , @NamedQuery(name = "SalaryPaymentDetails.findByFuelAllowance", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.fuelAllowance = :fuelAllowance")
    , @NamedQuery(name = "SalaryPaymentDetails.findByPhoneBillAllowance", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.phoneBillAllowance = :phoneBillAllowance")
    , @NamedQuery(name = "SalaryPaymentDetails.findByOtherAllowance", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.otherAllowance = :otherAllowance")
    , @NamedQuery(name = "SalaryPaymentDetails.findByTaxDeduction", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.taxDeduction = :taxDeduction")
    , @NamedQuery(name = "SalaryPaymentDetails.findByProvidentFund", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.providentFund = :providentFund")
    , @NamedQuery(name = "SalaryPaymentDetails.findByOtherDeduction", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.otherDeduction = :otherDeduction")
    , @NamedQuery(name = "SalaryPaymentDetails.findByPaymentForMonth", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.paymentForMonth = :paymentForMonth")
    , @NamedQuery(name = "SalaryPaymentDetails.findByAwardAmount", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.awardAmount = :awardAmount")
    , @NamedQuery(name = "SalaryPaymentDetails.findByFineDeduction", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.fineDeduction = :fineDeduction")
    , @NamedQuery(name = "SalaryPaymentDetails.findByPaymentMethod", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.paymentMethod = :paymentMethod")
    , @NamedQuery(name = "SalaryPaymentDetails.findByPaymentDate", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.paymentDate = :paymentDate")
    , @NamedQuery(name = "SalaryPaymentDetails.findByCreatedDate", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.createdDate = :createdDate")
    , @NamedQuery(name = "SalaryPaymentDetails.findByCreatedByEmployeeId", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.createdByEmployeeId = :createdByEmployeeId")
    , @NamedQuery(name = "SalaryPaymentDetails.findBySynchStatus", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.synchStatus = :synchStatus")
    , @NamedQuery(name = "SalaryPaymentDetails.findByIsSuperAdmin", query = "SELECT s FROM SalaryPaymentDetails s WHERE s.isSuperAdmin = :isSuperAdmin")})
public class SalaryPaymentDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "salary_payment_id", nullable = false)
    private Long salaryPaymentId;
    @Column(name = "branch_id")
    private BigInteger branchId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "employee_id", nullable = false)
    private long employeeId;
    @Column(name = "invoice_number")
    private BigInteger invoiceNumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "basic_salary", nullable = false)
    private int basicSalary;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "house_rent_allowance", nullable = false, length = 200)
    private String houseRentAllowance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "medical_allowance", nullable = false, length = 200)
    private String medicalAllowance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "special_allowance", nullable = false, length = 200)
    private String specialAllowance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fuel_allowance", nullable = false, length = 200)
    private String fuelAllowance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "phone_bill_allowance", nullable = false, length = 200)
    private String phoneBillAllowance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "other_allowance", nullable = false, length = 200)
    private String otherAllowance;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tax_deduction", nullable = false, length = 200)
    private String taxDeduction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "provident_fund", nullable = false, length = 200)
    private String providentFund;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "other_deduction", nullable = false, length = 200)
    private String otherDeduction;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "payment_for_month", nullable = false, length = 100)
    private String paymentForMonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "award_amount", nullable = false)
    private int awardAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "fine_deduction", nullable = false, length = 200)
    private String fineDeduction;
    @Column(name = "payment_method")
    private BigInteger paymentMethod;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String comments;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "created_by_employee_id")
    private BigInteger createdByEmployeeId;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    private Boolean isSuperAdmin;

    public SalaryPaymentDetails() {
    }

    public SalaryPaymentDetails(Long salaryPaymentId) {
        this.salaryPaymentId = salaryPaymentId;
    }

    public SalaryPaymentDetails(Long salaryPaymentId, long employeeId, int basicSalary, String houseRentAllowance, String medicalAllowance, String specialAllowance, String fuelAllowance, String phoneBillAllowance, String otherAllowance, String taxDeduction, String providentFund, String otherDeduction, String paymentForMonth, int awardAmount, String fineDeduction) {
        this.salaryPaymentId = salaryPaymentId;
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.houseRentAllowance = houseRentAllowance;
        this.medicalAllowance = medicalAllowance;
        this.specialAllowance = specialAllowance;
        this.fuelAllowance = fuelAllowance;
        this.phoneBillAllowance = phoneBillAllowance;
        this.otherAllowance = otherAllowance;
        this.taxDeduction = taxDeduction;
        this.providentFund = providentFund;
        this.otherDeduction = otherDeduction;
        this.paymentForMonth = paymentForMonth;
        this.awardAmount = awardAmount;
        this.fineDeduction = fineDeduction;
    }

    public Long getSalaryPaymentId() {
        return salaryPaymentId;
    }

    public void setSalaryPaymentId(Long salaryPaymentId) {
        this.salaryPaymentId = salaryPaymentId;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public BigInteger getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(BigInteger invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public String getPaymentForMonth() {
        return paymentForMonth;
    }

    public void setPaymentForMonth(String paymentForMonth) {
        this.paymentForMonth = paymentForMonth;
    }

    public int getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(int awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getFineDeduction() {
        return fineDeduction;
    }

    public void setFineDeduction(String fineDeduction) {
        this.fineDeduction = fineDeduction;
    }

    public BigInteger getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(BigInteger paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigInteger getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(BigInteger createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public Boolean getIsSuperAdmin() {
        return isSuperAdmin;
    }

    public void setIsSuperAdmin(Boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salaryPaymentId != null ? salaryPaymentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalaryPaymentDetails)) {
            return false;
        }
        SalaryPaymentDetails other = (SalaryPaymentDetails) object;
        if ((this.salaryPaymentId == null && other.salaryPaymentId != null) || (this.salaryPaymentId != null && !this.salaryPaymentId.equals(other.salaryPaymentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.SalaryPaymentDetails[ salaryPaymentId=" + salaryPaymentId + " ]";
    }
    
}
