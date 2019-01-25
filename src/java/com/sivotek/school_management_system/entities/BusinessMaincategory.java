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
@Table(name = "business_maincategory", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessMaincategory.findAll", query = "SELECT b FROM BusinessMaincategory b")
    , @NamedQuery(name = "BusinessMaincategory.findById", query = "SELECT b FROM BusinessMaincategory b WHERE b.id = :id")
    , @NamedQuery(name = "BusinessMaincategory.findByName", query = "SELECT b FROM BusinessMaincategory b WHERE b.name = :name")
    , @NamedQuery(name = "BusinessMaincategory.findByCreateddate", query = "SELECT b FROM BusinessMaincategory b WHERE b.createddate = :createddate")
    , @NamedQuery(name = "BusinessMaincategory.findByChangedate", query = "SELECT b FROM BusinessMaincategory b WHERE b.changedate = :changedate")
    , @NamedQuery(name = "BusinessMaincategory.findBySynchStatus", query = "SELECT b FROM BusinessMaincategory b WHERE b.synchStatus = :synchStatus")})
public class BusinessMaincategory implements Serializable {

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
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changedate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mainCategoryId")
    private Collection<BusinessSubcategory> businessSubcategoryCollection;

    public BusinessMaincategory() {
    }

    public BusinessMaincategory(Integer id) {
        this.id = id;
    }

    public BusinessMaincategory(Integer id, String name) {
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
    public Collection<BusinessSubcategory> getBusinessSubcategoryCollection() {
        return businessSubcategoryCollection;
    }

    public void setBusinessSubcategoryCollection(Collection<BusinessSubcategory> businessSubcategoryCollection) {
        this.businessSubcategoryCollection = businessSubcategoryCollection;
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
        if (!(object instanceof BusinessMaincategory)) {
            return false;
        }
        BusinessMaincategory other = (BusinessMaincategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.BusinessMaincategory[ id=" + id + " ]";
    }
    
}
