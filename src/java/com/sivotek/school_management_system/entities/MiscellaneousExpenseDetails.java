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
import javax.persistence.Lob;
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
@Table(name = "miscellaneous_expense_details", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MiscellaneousExpenseDetails.findAll", query = "SELECT m FROM MiscellaneousExpenseDetails m")
    , @NamedQuery(name = "MiscellaneousExpenseDetails.findById", query = "SELECT m FROM MiscellaneousExpenseDetails m WHERE m.id = :id")
    , @NamedQuery(name = "MiscellaneousExpenseDetails.findByExpenseId", query = "SELECT m FROM MiscellaneousExpenseDetails m WHERE m.expenseId = :expenseId")
    , @NamedQuery(name = "MiscellaneousExpenseDetails.findByExpenseCategory", query = "SELECT m FROM MiscellaneousExpenseDetails m WHERE m.expenseCategory = :expenseCategory")
    , @NamedQuery(name = "MiscellaneousExpenseDetails.findByTotal", query = "SELECT m FROM MiscellaneousExpenseDetails m WHERE m.total = :total")
    , @NamedQuery(name = "MiscellaneousExpenseDetails.findBySynchStatus", query = "SELECT m FROM MiscellaneousExpenseDetails m WHERE m.synchStatus = :synchStatus")})
public class MiscellaneousExpenseDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expense_id", nullable = false)
    private int expenseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expense_category", nullable = false)
    private int expenseCategory;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String purpose;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double total;
    @Column(name = "synch_status")
    private Boolean synchStatus;

    public MiscellaneousExpenseDetails() {
    }

    public MiscellaneousExpenseDetails(Integer id) {
        this.id = id;
    }

    public MiscellaneousExpenseDetails(Integer id, int expenseId, int expenseCategory) {
        this.id = id;
        this.expenseId = expenseId;
        this.expenseCategory = expenseCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getExpenseCategory() {
        return expenseCategory;
    }

    public void setExpenseCategory(int expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MiscellaneousExpenseDetails)) {
            return false;
        }
        MiscellaneousExpenseDetails other = (MiscellaneousExpenseDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.MiscellaneousExpenseDetails[ id=" + id + " ]";
    }
    
}
