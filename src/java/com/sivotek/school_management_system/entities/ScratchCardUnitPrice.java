/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "scratch_card_unit_price", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"unit_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScratchCardUnitPrice.findAll", query = "SELECT s FROM ScratchCardUnitPrice s")
    , @NamedQuery(name = "ScratchCardUnitPrice.findByUnitId", query = "SELECT s FROM ScratchCardUnitPrice s WHERE s.unitId = :unitId")
    , @NamedQuery(name = "ScratchCardUnitPrice.findByPricePerUnit", query = "SELECT s FROM ScratchCardUnitPrice s WHERE s.pricePerUnit = :pricePerUnit")
    , @NamedQuery(name = "ScratchCardUnitPrice.findBySynchStatus", query = "SELECT s FROM ScratchCardUnitPrice s WHERE s.synchStatus = :synchStatus")
    , @NamedQuery(name = "ScratchCardUnitPrice.findByCreateddate", query = "SELECT s FROM ScratchCardUnitPrice s WHERE s.createddate = :createddate")
    , @NamedQuery(name = "ScratchCardUnitPrice.findByChangeddate", query = "SELECT s FROM ScratchCardUnitPrice s WHERE s.changeddate = :changeddate")})
public class ScratchCardUnitPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "unit_id", nullable = false)
    private Long unitId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_per_unit", nullable = false)
    private double pricePerUnit;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "card_type_id", referencedColumnName = "card_type_id")
    @ManyToOne
    private ScratchCardType cardTypeId;

    public ScratchCardUnitPrice() {
    }

    public ScratchCardUnitPrice(Long unitId) {
        this.unitId = unitId;
    }

    public ScratchCardUnitPrice(Long unitId, double pricePerUnit) {
        this.unitId = unitId;
        this.pricePerUnit = pricePerUnit;
    }

    public Long getUnitId() {
        return unitId;
    }

    public void setUnitId(Long unitId) {
        this.unitId = unitId;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
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

    public Date getChangeddate() {
        return changeddate;
    }

    public void setChangeddate(Date changeddate) {
        this.changeddate = changeddate;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public ScratchCardType getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(ScratchCardType cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (unitId != null ? unitId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScratchCardUnitPrice)) {
            return false;
        }
        ScratchCardUnitPrice other = (ScratchCardUnitPrice) object;
        if ((this.unitId == null && other.unitId != null) || (this.unitId != null && !this.unitId.equals(other.unitId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ScratchCardUnitPrice[ unitId=" + unitId + " ]";
    }
    
}
