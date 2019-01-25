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
import javax.persistence.Lob;
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
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invoice.findAll", query = "SELECT i FROM Invoice i")
    , @NamedQuery(name = "Invoice.findById", query = "SELECT i FROM Invoice i WHERE i.id = :id")
    , @NamedQuery(name = "Invoice.findByInvoiceNumber", query = "SELECT i FROM Invoice i WHERE i.invoiceNumber = :invoiceNumber")
    , @NamedQuery(name = "Invoice.findByAmount", query = "SELECT i FROM Invoice i WHERE i.amount = :amount")
    , @NamedQuery(name = "Invoice.findByAmountDue", query = "SELECT i FROM Invoice i WHERE i.amountDue = :amountDue")
    , @NamedQuery(name = "Invoice.findByDueDate", query = "SELECT i FROM Invoice i WHERE i.dueDate = :dueDate")
    , @NamedQuery(name = "Invoice.findByAmountPaid", query = "SELECT i FROM Invoice i WHERE i.amountPaid = :amountPaid")
    , @NamedQuery(name = "Invoice.findByType", query = "SELECT i FROM Invoice i WHERE i.type = :type")
    , @NamedQuery(name = "Invoice.findByRaisedFor", query = "SELECT i FROM Invoice i WHERE i.raisedFor = :raisedFor")
    , @NamedQuery(name = "Invoice.findByCreateddate", query = "SELECT i FROM Invoice i WHERE i.createddate = :createddate")
    , @NamedQuery(name = "Invoice.findByModifieddate", query = "SELECT i FROM Invoice i WHERE i.modifieddate = :modifieddate")
    , @NamedQuery(name = "Invoice.findBySynchStatus", query = "SELECT i FROM Invoice i WHERE i.synchStatus = :synchStatus")})
public class Invoice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "invoice_number", nullable = false)
    private long invoiceNumber;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double amount;
    @Column(name = "amount_due", precision = 22)
    private Double amountDue;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "amount_paid", precision = 22)
    private Double amountPaid;
    @Size(max = 24)
    @Column(length = 24)
    private String type;
    @Lob
    @Size(max = 65535)
    @Column(name = "payment_term", length = 65535)
    private String paymentTerm;
    @Size(max = 13)
    @Column(name = "raised_for", length = 13)
    private String raisedFor;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(mappedBy = "invoiceId")
    private Collection<UtilityBillDetails> utilityBillDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
    private Collection<AccountTransaction> accountTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
    private Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
    private Collection<PurchaseOrder> purchaseOrderCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invoiceId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "business_subcategory_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BusinessSubcategory businessSubcategoryId;
    @JoinColumn(name = "business_subcategory_service_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BusinessSubcategoryServices businessSubcategoryServiceId;
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee employeeId;
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    @ManyToOne
    private Student studentId;
    @JoinColumn(name = "created_by_employee_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee createdByEmployeeId;

    public Invoice() {
    }

    public Invoice(Long id) {
        this.id = id;
    }

    public Invoice(Long id, long invoiceNumber) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(long invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPaymentTerm() {
        return paymentTerm;
    }

    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public String getRaisedFor() {
        return raisedFor;
    }

    public void setRaisedFor(String raisedFor) {
        this.raisedFor = raisedFor;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<UtilityBillDetails> getUtilityBillDetailsCollection() {
        return utilityBillDetailsCollection;
    }

    public void setUtilityBillDetailsCollection(Collection<UtilityBillDetails> utilityBillDetailsCollection) {
        this.utilityBillDetailsCollection = utilityBillDetailsCollection;
    }

    @XmlTransient
    public Collection<AccountTransaction> getAccountTransactionCollection() {
        return accountTransactionCollection;
    }

    public void setAccountTransactionCollection(Collection<AccountTransaction> accountTransactionCollection) {
        this.accountTransactionCollection = accountTransactionCollection;
    }

    @XmlTransient
    public Collection<ScratchCardSalesOrder> getScratchCardSalesOrderCollection() {
        return scratchCardSalesOrderCollection;
    }

    public void setScratchCardSalesOrderCollection(Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection) {
        this.scratchCardSalesOrderCollection = scratchCardSalesOrderCollection;
    }

    @XmlTransient
    public Collection<PurchaseOrder> getPurchaseOrderCollection() {
        return purchaseOrderCollection;
    }

    public void setPurchaseOrderCollection(Collection<PurchaseOrder> purchaseOrderCollection) {
        this.purchaseOrderCollection = purchaseOrderCollection;
    }

    @XmlTransient
    public Collection<SchoolFeeInvoiceDetails> getSchoolFeeInvoiceDetailsCollection() {
        return schoolFeeInvoiceDetailsCollection;
    }

    public void setSchoolFeeInvoiceDetailsCollection(Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection) {
        this.schoolFeeInvoiceDetailsCollection = schoolFeeInvoiceDetailsCollection;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public BusinessSubcategory getBusinessSubcategoryId() {
        return businessSubcategoryId;
    }

    public void setBusinessSubcategoryId(BusinessSubcategory businessSubcategoryId) {
        this.businessSubcategoryId = businessSubcategoryId;
    }

    public BusinessSubcategoryServices getBusinessSubcategoryServiceId() {
        return businessSubcategoryServiceId;
    }

    public void setBusinessSubcategoryServiceId(BusinessSubcategoryServices businessSubcategoryServiceId) {
        this.businessSubcategoryServiceId = businessSubcategoryServiceId;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Employee getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Employee createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
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
        if (!(object instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Invoice[ id=" + id + " ]";
    }
    
}
