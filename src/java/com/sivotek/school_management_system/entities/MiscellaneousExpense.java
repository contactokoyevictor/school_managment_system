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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "miscellaneous_expense", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"invoice_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MiscellaneousExpense.findAll", query = "SELECT m FROM MiscellaneousExpense m")
    , @NamedQuery(name = "MiscellaneousExpense.findById", query = "SELECT m FROM MiscellaneousExpense m WHERE m.id = :id")
    , @NamedQuery(name = "MiscellaneousExpense.findByInvoiceId", query = "SELECT m FROM MiscellaneousExpense m WHERE m.invoiceId = :invoiceId")
    , @NamedQuery(name = "MiscellaneousExpense.findByExpenseByEmployeeId", query = "SELECT m FROM MiscellaneousExpense m WHERE m.expenseByEmployeeId = :expenseByEmployeeId")
    , @NamedQuery(name = "MiscellaneousExpense.findByDueDate", query = "SELECT m FROM MiscellaneousExpense m WHERE m.dueDate = :dueDate")
    , @NamedQuery(name = "MiscellaneousExpense.findByGrandTotal", query = "SELECT m FROM MiscellaneousExpense m WHERE m.grandTotal = :grandTotal")
    , @NamedQuery(name = "MiscellaneousExpense.findByAmountPaid", query = "SELECT m FROM MiscellaneousExpense m WHERE m.amountPaid = :amountPaid")
    , @NamedQuery(name = "MiscellaneousExpense.findByPaymentType", query = "SELECT m FROM MiscellaneousExpense m WHERE m.paymentType = :paymentType")
    , @NamedQuery(name = "MiscellaneousExpense.findByPaymentDate", query = "SELECT m FROM MiscellaneousExpense m WHERE m.paymentDate = :paymentDate")
    , @NamedQuery(name = "MiscellaneousExpense.findByCreatedByEmployeeId", query = "SELECT m FROM MiscellaneousExpense m WHERE m.createdByEmployeeId = :createdByEmployeeId")
    , @NamedQuery(name = "MiscellaneousExpense.findByCreateddate", query = "SELECT m FROM MiscellaneousExpense m WHERE m.createddate = :createddate")
    , @NamedQuery(name = "MiscellaneousExpense.findByModifieddate", query = "SELECT m FROM MiscellaneousExpense m WHERE m.modifieddate = :modifieddate")
    , @NamedQuery(name = "MiscellaneousExpense.findBySynchStatus", query = "SELECT m FROM MiscellaneousExpense m WHERE m.synchStatus = :synchStatus")})
public class MiscellaneousExpense implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "invoice_id", nullable = false)
    private long invoiceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expense_by_employee_id", nullable = false)
    private long expenseByEmployeeId;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "grand_total", precision = 22)
    private Double grandTotal;
    @Column(name = "amount_paid", precision = 22)
    private Double amountPaid;
    @Size(max = 255)
    @Column(name = "payment_type", length = 255)
    private String paymentType;
    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Column(name = "created_by_employee_id")
    private BigInteger createdByEmployeeId;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;

    public MiscellaneousExpense() {
    }

    public MiscellaneousExpense(Long id) {
        this.id = id;
    }

    public MiscellaneousExpense(Long id, long invoiceId, long expenseByEmployeeId) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.expenseByEmployeeId = expenseByEmployeeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public long getExpenseByEmployeeId() {
        return expenseByEmployeeId;
    }

    public void setExpenseByEmployeeId(long expenseByEmployeeId) {
        this.expenseByEmployeeId = expenseByEmployeeId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigInteger getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(BigInteger createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
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

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
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
        if (!(object instanceof MiscellaneousExpense)) {
            return false;
        }
        MiscellaneousExpense other = (MiscellaneousExpense) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.MiscellaneousExpense[ id=" + id + " ]";
    }
    
}
