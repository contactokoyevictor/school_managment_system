/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "payment_method", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentMethod.findAll", query = "SELECT p FROM PaymentMethod p")
    , @NamedQuery(name = "PaymentMethod.findById", query = "SELECT p FROM PaymentMethod p WHERE p.id = :id")
    , @NamedQuery(name = "PaymentMethod.findByName", query = "SELECT p FROM PaymentMethod p WHERE p.name = :name")
    , @NamedQuery(name = "PaymentMethod.findByType", query = "SELECT p FROM PaymentMethod p WHERE p.type = :type")
    , @NamedQuery(name = "PaymentMethod.findBySynchStatus", query = "SELECT p FROM PaymentMethod p WHERE p.synchStatus = :synchStatus")})
public class PaymentMethod implements Serializable {

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
    private String name;
    @Size(max = 34)
    @Column(length = 34)
    private String type;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(mappedBy = "paymentMethod")
    private Collection<UtilityBillDetails> utilityBillDetailsCollection;
    @OneToMany(mappedBy = "paymentMethod")
    private Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection;
    @OneToMany(mappedBy = "paymentMethod")
    private Collection<PurchaseOrder> purchaseOrderCollection;
    @OneToMany(mappedBy = "paymentMethod")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;

    public PaymentMethod() {
    }

    public PaymentMethod(Long id) {
        this.id = id;
    }

    public PaymentMethod(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentMethod)) {
            return false;
        }
        PaymentMethod other = (PaymentMethod) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.PaymentMethod[ id=" + id + " ]";
    }
    
}
