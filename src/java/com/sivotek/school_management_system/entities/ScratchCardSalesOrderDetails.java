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
@Table(name = "scratch_card_sales_order_details", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScratchCardSalesOrderDetails.findAll", query = "SELECT s FROM ScratchCardSalesOrderDetails s")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findById", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.id = :id")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findByProductName", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.productName = :productName")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findByDescription", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.description = :description")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findByUnitPrice", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.unitPrice = :unitPrice")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findByQuantity", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findByVatntax", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.vatntax = :vatntax")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findByTotal", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.total = :total")
    , @NamedQuery(name = "ScratchCardSalesOrderDetails.findBySynchStatus", query = "SELECT s FROM ScratchCardSalesOrderDetails s WHERE s.synchStatus = :synchStatus")})
public class ScratchCardSalesOrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "product_name", nullable = false, length = 255)
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_price", precision = 22)
    private Double unitPrice;
    @Column(precision = 22)
    private Double quantity;
    @Column(precision = 22)
    private Double vatntax;
    @Column(precision = 22)
    private Double total;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "sales_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ScratchCardSalesOrder salesId;

    public ScratchCardSalesOrderDetails() {
    }

    public ScratchCardSalesOrderDetails(Long id) {
        this.id = id;
    }

    public ScratchCardSalesOrderDetails(Long id, String productName, String description) {
        this.id = id;
        this.productName = productName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getVatntax() {
        return vatntax;
    }

    public void setVatntax(Double vatntax) {
        this.vatntax = vatntax;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public ScratchCardSalesOrder getSalesId() {
        return salesId;
    }

    public void setSalesId(ScratchCardSalesOrder salesId) {
        this.salesId = salesId;
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
        if (!(object instanceof ScratchCardSalesOrderDetails)) {
            return false;
        }
        ScratchCardSalesOrderDetails other = (ScratchCardSalesOrderDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ScratchCardSalesOrderDetails[ id=" + id + " ]";
    }
    
}
