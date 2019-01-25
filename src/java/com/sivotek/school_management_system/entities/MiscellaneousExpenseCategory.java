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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "miscellaneous_expense_category", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MiscellaneousExpenseCategory.findAll", query = "SELECT m FROM MiscellaneousExpenseCategory m")
    , @NamedQuery(name = "MiscellaneousExpenseCategory.findById", query = "SELECT m FROM MiscellaneousExpenseCategory m WHERE m.id = :id")
    , @NamedQuery(name = "MiscellaneousExpenseCategory.findByName", query = "SELECT m FROM MiscellaneousExpenseCategory m WHERE m.name = :name")
    , @NamedQuery(name = "MiscellaneousExpenseCategory.findBySynchStatus", query = "SELECT m FROM MiscellaneousExpenseCategory m WHERE m.synchStatus = :synchStatus")})
public class MiscellaneousExpenseCategory implements Serializable {

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
    @Column(name = "synch_status")
    private Boolean synchStatus;

    public MiscellaneousExpenseCategory() {
    }

    public MiscellaneousExpenseCategory(Integer id) {
        this.id = id;
    }

    public MiscellaneousExpenseCategory(Integer id, String name) {
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

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
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
        if (!(object instanceof MiscellaneousExpenseCategory)) {
            return false;
        }
        MiscellaneousExpenseCategory other = (MiscellaneousExpenseCategory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.MiscellaneousExpenseCategory[ id=" + id + " ]";
    }
    
}
