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
@Table(name = "business_subcategory", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessSubcategory.findAll", query = "SELECT b FROM BusinessSubcategory b")
    , @NamedQuery(name = "BusinessSubcategory.findById", query = "SELECT b FROM BusinessSubcategory b WHERE b.id = :id")
    , @NamedQuery(name = "BusinessSubcategory.findByName", query = "SELECT b FROM BusinessSubcategory b WHERE b.name = :name")
    , @NamedQuery(name = "BusinessSubcategory.findByType", query = "SELECT b FROM BusinessSubcategory b WHERE b.type = :type")
    , @NamedQuery(name = "BusinessSubcategory.findByCreateddate", query = "SELECT b FROM BusinessSubcategory b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BusinessSubcategory.findByChangedate", query = "SELECT b FROM BusinessSubcategory b WHERE b.changedate = :changedate")
    , @NamedQuery(name = "BusinessSubcategory.findBySynchStatus", query = "SELECT b FROM BusinessSubcategory b WHERE b.synchStatus = :synchStatus")})
public class BusinessSubcategory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;
    @Size(max = 24)
    @Column(length = 24)
    private String type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessSubcategoryId")
    private Collection<BusinessSubcategoryServices> businessSubcategoryServicesCollection;
    @JoinColumn(name = "main_category_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private BusinessMaincategory mainCategoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "businessSubcategoryId")
    private Collection<Invoice> invoiceCollection;

    public BusinessSubcategory() {
    }

    public BusinessSubcategory(Integer id) {
        this.id = id;
    }

    public BusinessSubcategory(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @XmlTransient
    public Collection<BusinessSubcategoryServices> getBusinessSubcategoryServicesCollection() {
        return businessSubcategoryServicesCollection;
    }

    public void setBusinessSubcategoryServicesCollection(Collection<BusinessSubcategoryServices> businessSubcategoryServicesCollection) {
        this.businessSubcategoryServicesCollection = businessSubcategoryServicesCollection;
    }

    public BusinessMaincategory getMainCategoryId() {
        return mainCategoryId;
    }

    public void setMainCategoryId(BusinessMaincategory mainCategoryId) {
        this.mainCategoryId = mainCategoryId;
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
        if (!(object instanceof BusinessSubcategory)) {
            return false;
        }
        BusinessSubcategory other = (BusinessSubcategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BusinessSubcategory[ id=" + id + " ]";
    }
    
}
