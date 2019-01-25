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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "business_subcategory_services", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessSubcategoryServices.findAll", query = "SELECT b FROM BusinessSubcategoryServices b")
    , @NamedQuery(name = "BusinessSubcategoryServices.findById", query = "SELECT b FROM BusinessSubcategoryServices b WHERE b.id = :id")
    , @NamedQuery(name = "BusinessSubcategoryServices.findByAttributeName", query = "SELECT b FROM BusinessSubcategoryServices b WHERE b.attributeName = :attributeName")
    , @NamedQuery(name = "BusinessSubcategoryServices.findByType", query = "SELECT b FROM BusinessSubcategoryServices b WHERE b.type = :type")
    , @NamedQuery(name = "BusinessSubcategoryServices.findByCreateddate", query = "SELECT b FROM BusinessSubcategoryServices b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BusinessSubcategoryServices.findByChangedate", query = "SELECT b FROM BusinessSubcategoryServices b WHERE b.changedate = :changedate")
    , @NamedQuery(name = "BusinessSubcategoryServices.findBySynchStatus", query = "SELECT b FROM BusinessSubcategoryServices b WHERE b.synchStatus = :synchStatus")})
public class BusinessSubcategoryServices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "attribute_name", nullable = false, length = 255)
    private String attributeName;
    @Size(max = 24)
    @Column(length = 24)
    private String type;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "business_subcategory_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BusinessSubcategory businessSubcategoryId;
    @OneToMany(mappedBy = "utilityTypeId")
    private Collection<UtilityBillDetails> utilityBillDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessSubcategoryServiceId")
    private Collection<Invoice> invoiceCollection;

    public BusinessSubcategoryServices() {
    }

    public BusinessSubcategoryServices(Integer id) {
        this.id = id;
    }

    public BusinessSubcategoryServices(Integer id, String attributeName) {
        this.id = id;
        this.attributeName = attributeName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getChangedate() {
        return changedate;
    }

    public void setChangedate(Date changedate) {
        this.changedate = changedate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public BusinessSubcategory getBusinessSubcategoryId() {
        return businessSubcategoryId;
    }

    public void setBusinessSubcategoryId(BusinessSubcategory businessSubcategoryId) {
        this.businessSubcategoryId = businessSubcategoryId;
    }

    @XmlTransient
    public Collection<UtilityBillDetails> getUtilityBillDetailsCollection() {
        return utilityBillDetailsCollection;
    }

    public void setUtilityBillDetailsCollection(Collection<UtilityBillDetails> utilityBillDetailsCollection) {
        this.utilityBillDetailsCollection = utilityBillDetailsCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
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
        if (!(object instanceof BusinessSubcategoryServices)) {
            return false;
        }
        BusinessSubcategoryServices other = (BusinessSubcategoryServices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BusinessSubcategoryServices[ id=" + id + " ]";
    }
    
}
