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
@Table(name = "scratch_card_sales_order", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScratchCardSalesOrder.findAll", query = "SELECT s FROM ScratchCardSalesOrder s")
    , @NamedQuery(name = "ScratchCardSalesOrder.findById", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.id = :id")
    , @NamedQuery(name = "ScratchCardSalesOrder.findByMerchant", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.merchant = :merchant")
    , @NamedQuery(name = "ScratchCardSalesOrder.findBySalesTotalVatntax", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.salesTotalVatntax = :salesTotalVatntax")
    , @NamedQuery(name = "ScratchCardSalesOrder.findBySalesSubtotal", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.salesSubtotal = :salesSubtotal")
    , @NamedQuery(name = "ScratchCardSalesOrder.findBySalesGrandTotal", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.salesGrandTotal = :salesGrandTotal")
    , @NamedQuery(name = "ScratchCardSalesOrder.findByAmountPaid", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.amountPaid = :amountPaid")
    , @NamedQuery(name = "ScratchCardSalesOrder.findByPaymentDate", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.paymentDate = :paymentDate")
    , @NamedQuery(name = "ScratchCardSalesOrder.findBySynchStatus", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.synchStatus = :synchStatus")
    , @NamedQuery(name = "ScratchCardSalesOrder.findByCreateddate", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.createddate = :createddate")
    , @NamedQuery(name = "ScratchCardSalesOrder.findByModifieddate", query = "SELECT s FROM ScratchCardSalesOrder s WHERE s.modifieddate = :modifieddate")})
public class ScratchCardSalesOrder implements Serializable {

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
    private String merchant;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "sales_total_vatntax", precision = 22)
    private Double salesTotalVatntax;
    @Column(name = "sales_subtotal", precision = 22)
    private Double salesSubtotal;
    @Column(name = "sales_grand_total", precision = 22)
    private Double salesGrandTotal;
    @Column(name = "amount_paid", precision = 22)
    private Double amountPaid;
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Invoice invoiceId;
    @JoinColumn(name = "payment_method", referencedColumnName = "id")
    @ManyToOne
    private PaymentMethod paymentMethod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "salesId")
    private Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollection;

    public ScratchCardSalesOrder() {
    }

    public ScratchCardSalesOrder(Long id) {
        this.id = id;
    }

    public ScratchCardSalesOrder(Long id, String merchant) {
        this.id = id;
        this.merchant = merchant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Double getSalesTotalVatntax() {
        return salesTotalVatntax;
    }

    public void setSalesTotalVatntax(Double salesTotalVatntax) {
        this.salesTotalVatntax = salesTotalVatntax;
    }

    public Double getSalesSubtotal() {
        return salesSubtotal;
    }

    public void setSalesSubtotal(Double salesSubtotal) {
        this.salesSubtotal = salesSubtotal;
    }

    public Double getSalesGrandTotal() {
        return salesGrandTotal;
    }

    public void setSalesGrandTotal(Double salesGrandTotal) {
        this.salesGrandTotal = salesGrandTotal;
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

    public Date getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Date modifieddate) {
        this.modifieddate = modifieddate;
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @XmlTransient
    public Collection<ScratchCardSalesOrderDetails> getScratchCardSalesOrderDetailsCollection() {
        return scratchCardSalesOrderDetailsCollection;
    }

    public void setScratchCardSalesOrderDetailsCollection(Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollection) {
        this.scratchCardSalesOrderDetailsCollection = scratchCardSalesOrderDetailsCollection;
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
        if (!(object instanceof ScratchCardSalesOrder)) {
            return false;
        }
        ScratchCardSalesOrder other = (ScratchCardSalesOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ScratchCardSalesOrder[ id=" + id + " ]";
    }
    
}
