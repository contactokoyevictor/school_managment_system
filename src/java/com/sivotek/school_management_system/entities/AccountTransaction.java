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
@Table(name = "account_transaction", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountTransaction.findAll", query = "SELECT a FROM AccountTransaction a")
    , @NamedQuery(name = "AccountTransaction.findById", query = "SELECT a FROM AccountTransaction a WHERE a.id = :id")
    , @NamedQuery(name = "AccountTransaction.findByTransactionCode", query = "SELECT a FROM AccountTransaction a WHERE a.transactionCode = :transactionCode")
    , @NamedQuery(name = "AccountTransaction.findByTransactionType", query = "SELECT a FROM AccountTransaction a WHERE a.transactionType = :transactionType")
    , @NamedQuery(name = "AccountTransaction.findByAmountPaid", query = "SELECT a FROM AccountTransaction a WHERE a.amountPaid = :amountPaid")
    , @NamedQuery(name = "AccountTransaction.findByPaymentDate", query = "SELECT a FROM AccountTransaction a WHERE a.paymentDate = :paymentDate")
    , @NamedQuery(name = "AccountTransaction.findByPaymentMethodId", query = "SELECT a FROM AccountTransaction a WHERE a.paymentMethodId = :paymentMethodId")
    , @NamedQuery(name = "AccountTransaction.findByReceiptnumber", query = "SELECT a FROM AccountTransaction a WHERE a.receiptnumber = :receiptnumber")
    , @NamedQuery(name = "AccountTransaction.findByTransactionDate", query = "SELECT a FROM AccountTransaction a WHERE a.transactionDate = :transactionDate")
    , @NamedQuery(name = "AccountTransaction.findByTransactionStatus", query = "SELECT a FROM AccountTransaction a WHERE a.transactionStatus = :transactionStatus")
    , @NamedQuery(name = "AccountTransaction.findByCreateddate", query = "SELECT a FROM AccountTransaction a WHERE a.createddate = :createddate")
    , @NamedQuery(name = "AccountTransaction.findBySynchStatus", query = "SELECT a FROM AccountTransaction a WHERE a.synchStatus = :synchStatus")})
public class AccountTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "transaction_code", nullable = false, length = 255)
    private String transactionCode;
    @Size(max = 12)
    @Column(name = "transaction_type", length = 12)
    private String transactionType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "amount_paid", precision = 22)
    private Double amountPaid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "payment_method_id", nullable = false)
    private long paymentMethodId;
    @Size(max = 255)
    @Column(name = "Receipt_number", length = 255)
    private String receiptnumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "transaction_status", nullable = false)
    private boolean transactionStatus;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Invoice invoiceId;
    @JoinColumn(name = "bank_account_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BankAccount bankAccountId;
    @JoinColumn(name = "created_by_employee_id", referencedColumnName = "employee_id")
    @ManyToOne
    private Employee createdByEmployeeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "transactionId")
    private Collection<TransactionCancellation> transactionCancellationCollection;

    public AccountTransaction() {
    }

    public AccountTransaction(Long id) {
        this.id = id;
    }

    public AccountTransaction(Long id, String transactionCode, Date paymentDate, long paymentMethodId, Date transactionDate, boolean transactionStatus, Date createddate) {
        this.id = id;
        this.transactionCode = transactionCode;
        this.paymentDate = paymentDate;
        this.paymentMethodId = paymentMethodId;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.createddate = createddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getReceiptnumber() {
        return receiptnumber;
    }

    public void setReceiptnumber(String receiptnumber) {
        this.receiptnumber = receiptnumber;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public boolean getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(boolean transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
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

    public BankAccount getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(BankAccount bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Employee getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Employee createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    @XmlTransient
    public Collection<TransactionCancellation> getTransactionCancellationCollection() {
        return transactionCancellationCollection;
    }

    public void setTransactionCancellationCollection(Collection<TransactionCancellation> transactionCancellationCollection) {
        this.transactionCancellationCollection = transactionCancellationCollection;
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
        if (!(object instanceof AccountTransaction)) {
            return false;
        }
        AccountTransaction other = (AccountTransaction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.AccountTransaction[ id=" + id + " ]";
    }
    
}
