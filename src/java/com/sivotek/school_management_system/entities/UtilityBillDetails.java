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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "utility_bill_details", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UtilityBillDetails.findAll", query = "SELECT u FROM UtilityBillDetails u")
    , @NamedQuery(name = "UtilityBillDetails.findById", query = "SELECT u FROM UtilityBillDetails u WHERE u.id = :id")
    , @NamedQuery(name = "UtilityBillDetails.findByOrganisation", query = "SELECT u FROM UtilityBillDetails u WHERE u.organisation = :organisation")
    , @NamedQuery(name = "UtilityBillDetails.findByAmountToPay", query = "SELECT u FROM UtilityBillDetails u WHERE u.amountToPay = :amountToPay")
    , @NamedQuery(name = "UtilityBillDetails.findByAmountPaid", query = "SELECT u FROM UtilityBillDetails u WHERE u.amountPaid = :amountPaid")
    , @NamedQuery(name = "UtilityBillDetails.findByPeriodFrom", query = "SELECT u FROM UtilityBillDetails u WHERE u.periodFrom = :periodFrom")
    , @NamedQuery(name = "UtilityBillDetails.findByPeriodTo", query = "SELECT u FROM UtilityBillDetails u WHERE u.periodTo = :periodTo")
    , @NamedQuery(name = "UtilityBillDetails.findByDueDate", query = "SELECT u FROM UtilityBillDetails u WHERE u.dueDate = :dueDate")
    , @NamedQuery(name = "UtilityBillDetails.findByPaymentDate", query = "SELECT u FROM UtilityBillDetails u WHERE u.paymentDate = :paymentDate")
    , @NamedQuery(name = "UtilityBillDetails.findByCreatedDate", query = "SELECT u FROM UtilityBillDetails u WHERE u.createdDate = :createdDate")
    , @NamedQuery(name = "UtilityBillDetails.findByCreatedByEmployeeId", query = "SELECT u FROM UtilityBillDetails u WHERE u.createdByEmployeeId = :createdByEmployeeId")
    , @NamedQuery(name = "UtilityBillDetails.findBySynchStatus", query = "SELECT u FROM UtilityBillDetails u WHERE u.synchStatus = :synchStatus")})
public class UtilityBillDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String organisation;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_to_pay", precision = 22)
    private Double amountToPay;
    @Column(name = "amount_paid", precision = 22)
    private Double amountPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "period_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date periodFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "period_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date periodTo;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
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
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id")
    @ManyToOne
    private Invoice invoiceId;
    @JoinColumn(name = "utility_type_id", referencedColumnName = "id")
    @ManyToOne
    private BusinessSubcategoryServices utilityTypeId;
    @JoinColumn(name = "payment_method", referencedColumnName = "id")
    @ManyToOne
    private PaymentMethod paymentMethod;

    public UtilityBillDetails() {
    }

    public UtilityBillDetails(Long id) {
        this.id = id;
    }

    public UtilityBillDetails(Long id, String organisation, Date periodFrom, Date periodTo) {
        this.id = id;
        this.organisation = organisation;
        this.periodFrom = periodFrom;
        this.periodTo = periodTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public Double getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Double amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPeriodFrom() {
        return periodFrom;
    }

    public void setPeriodFrom(Date periodFrom) {
        this.periodFrom = periodFrom;
    }

    public Date getPeriodTo() {
        return periodTo;
    }

    public void setPeriodTo(Date periodTo) {
        this.periodTo = periodTo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public Invoice getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Invoice invoiceId) {
        this.invoiceId = invoiceId;
    }

    public BusinessSubcategoryServices getUtilityTypeId() {
        return utilityTypeId;
    }

    public void setUtilityTypeId(BusinessSubcategoryServices utilityTypeId) {
        this.utilityTypeId = utilityTypeId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
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
        if (!(object instanceof UtilityBillDetails)) {
            return false;
        }
        UtilityBillDetails other = (UtilityBillDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.UtilityBillDetails[ id=" + id + " ]";
    }
    
}
