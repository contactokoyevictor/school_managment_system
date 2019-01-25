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
@Table(name = "purchase_order", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrder.findAll", query = "SELECT p FROM PurchaseOrder p")
    , @NamedQuery(name = "PurchaseOrder.findById", query = "SELECT p FROM PurchaseOrder p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseOrder.findBySupplierRef", query = "SELECT p FROM PurchaseOrder p WHERE p.supplierRef = :supplierRef")
    , @NamedQuery(name = "PurchaseOrder.findByOrderDate", query = "SELECT p FROM PurchaseOrder p WHERE p.orderDate = :orderDate")
    , @NamedQuery(name = "PurchaseOrder.findByDeliveryPeriodFrom", query = "SELECT p FROM PurchaseOrder p WHERE p.deliveryPeriodFrom = :deliveryPeriodFrom")
    , @NamedQuery(name = "PurchaseOrder.findByDeliveryPeriodTo", query = "SELECT p FROM PurchaseOrder p WHERE p.deliveryPeriodTo = :deliveryPeriodTo")
    , @NamedQuery(name = "PurchaseOrder.findByOrderDeliveryCost", query = "SELECT p FROM PurchaseOrder p WHERE p.orderDeliveryCost = :orderDeliveryCost")
    , @NamedQuery(name = "PurchaseOrder.findByOrderTotalVatntax", query = "SELECT p FROM PurchaseOrder p WHERE p.orderTotalVatntax = :orderTotalVatntax")
    , @NamedQuery(name = "PurchaseOrder.findByOrderSubtotal", query = "SELECT p FROM PurchaseOrder p WHERE p.orderSubtotal = :orderSubtotal")
    , @NamedQuery(name = "PurchaseOrder.findByOrderGrandTotal", query = "SELECT p FROM PurchaseOrder p WHERE p.orderGrandTotal = :orderGrandTotal")
    , @NamedQuery(name = "PurchaseOrder.findByAmountPaid", query = "SELECT p FROM PurchaseOrder p WHERE p.amountPaid = :amountPaid")
    , @NamedQuery(name = "PurchaseOrder.findByPaymentDate", query = "SELECT p FROM PurchaseOrder p WHERE p.paymentDate = :paymentDate")
    , @NamedQuery(name = "PurchaseOrder.findByCreatedByEmployeeId", query = "SELECT p FROM PurchaseOrder p WHERE p.createdByEmployeeId = :createdByEmployeeId")
    , @NamedQuery(name = "PurchaseOrder.findByCreateddate", query = "SELECT p FROM PurchaseOrder p WHERE p.createddate = :createddate")
    , @NamedQuery(name = "PurchaseOrder.findByModifieddate", query = "SELECT p FROM PurchaseOrder p WHERE p.modifieddate = :modifieddate")
    , @NamedQuery(name = "PurchaseOrder.findBySynchStatus", query = "SELECT p FROM PurchaseOrder p WHERE p.synchStatus = :synchStatus")})
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "supplier_ref", nullable = false, length = 255)
    private String supplierRef;
    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;
    @Column(name = "delivery_period_from")
    @Temporal(TemporalType.DATE)
    private Date deliveryPeriodFrom;
    @Column(name = "delivery_period_to")
    @Temporal(TemporalType.DATE)
    private Date deliveryPeriodTo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "order_delivery_cost", precision = 22)
    private Double orderDeliveryCost;
    @Column(name = "order_total_vatntax", precision = 22)
    private Double orderTotalVatntax;
    @Column(name = "order_subtotal", precision = 22)
    private Double orderSubtotal;
    @Column(name = "order_grand_total", precision = 22)
    private Double orderGrandTotal;
    @Column(name = "amount_paid", precision = 22)
    private Double amountPaid;
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_by_employee_id", nullable = false)
    private long createdByEmployeeId;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifieddate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderId")
    private Collection<PurchaseOrderDetails> purchaseOrderDetailsCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "invoice_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private Invoice invoiceId;
    @JoinColumn(name = "payment_method", referencedColumnName = "id")
    @ManyToOne
    private PaymentMethod paymentMethod;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Long id) {
        this.id = id;
    }

    public PurchaseOrder(Long id, String supplierRef, long createdByEmployeeId, Date createddate) {
        this.id = id;
        this.supplierRef = supplierRef;
        this.createdByEmployeeId = createdByEmployeeId;
        this.createddate = createddate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSupplierRef() {
        return supplierRef;
    }

    public void setSupplierRef(String supplierRef) {
        this.supplierRef = supplierRef;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryPeriodFrom() {
        return deliveryPeriodFrom;
    }

    public void setDeliveryPeriodFrom(Date deliveryPeriodFrom) {
        this.deliveryPeriodFrom = deliveryPeriodFrom;
    }

    public Date getDeliveryPeriodTo() {
        return deliveryPeriodTo;
    }

    public void setDeliveryPeriodTo(Date deliveryPeriodTo) {
        this.deliveryPeriodTo = deliveryPeriodTo;
    }

    public Double getOrderDeliveryCost() {
        return orderDeliveryCost;
    }

    public void setOrderDeliveryCost(Double orderDeliveryCost) {
        this.orderDeliveryCost = orderDeliveryCost;
    }

    public Double getOrderTotalVatntax() {
        return orderTotalVatntax;
    }

    public void setOrderTotalVatntax(Double orderTotalVatntax) {
        this.orderTotalVatntax = orderTotalVatntax;
    }

    public Double getOrderSubtotal() {
        return orderSubtotal;
    }

    public void setOrderSubtotal(Double orderSubtotal) {
        this.orderSubtotal = orderSubtotal;
    }

    public Double getOrderGrandTotal() {
        return orderGrandTotal;
    }

    public void setOrderGrandTotal(Double orderGrandTotal) {
        this.orderGrandTotal = orderGrandTotal;
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

    public long getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(long createdByEmployeeId) {
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

    @XmlTransient
    public Collection<PurchaseOrderDetails> getPurchaseOrderDetailsCollection() {
        return purchaseOrderDetailsCollection;
    }

    public void setPurchaseOrderDetailsCollection(Collection<PurchaseOrderDetails> purchaseOrderDetailsCollection) {
        this.purchaseOrderDetailsCollection = purchaseOrderDetailsCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.PurchaseOrder[ id=" + id + " ]";
    }
    
}
