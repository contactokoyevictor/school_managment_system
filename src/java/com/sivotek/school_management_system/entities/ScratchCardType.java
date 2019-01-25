/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "scratch_card_type", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"card_type_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScratchCardType.findAll", query = "SELECT s FROM ScratchCardType s")
    , @NamedQuery(name = "ScratchCardType.findByCardTypeId", query = "SELECT s FROM ScratchCardType s WHERE s.cardTypeId = :cardTypeId")
    , @NamedQuery(name = "ScratchCardType.findByTypeName", query = "SELECT s FROM ScratchCardType s WHERE s.typeName = :typeName")
    , @NamedQuery(name = "ScratchCardType.findByValidityInDays", query = "SELECT s FROM ScratchCardType s WHERE s.validityInDays = :validityInDays")
    , @NamedQuery(name = "ScratchCardType.findByNumberOfUseage", query = "SELECT s FROM ScratchCardType s WHERE s.numberOfUseage = :numberOfUseage")
    , @NamedQuery(name = "ScratchCardType.findByUseageCount", query = "SELECT s FROM ScratchCardType s WHERE s.useageCount = :useageCount")
    , @NamedQuery(name = "ScratchCardType.findBySynchStatus", query = "SELECT s FROM ScratchCardType s WHERE s.synchStatus = :synchStatus")
    , @NamedQuery(name = "ScratchCardType.findByCreateddate", query = "SELECT s FROM ScratchCardType s WHERE s.createddate = :createddate")
    , @NamedQuery(name = "ScratchCardType.findByChangeddate", query = "SELECT s FROM ScratchCardType s WHERE s.changeddate = :changeddate")})
public class ScratchCardType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_type_id", nullable = false)
    private Long cardTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 17)
    @Column(name = "type_name", nullable = false, length = 17)
    private String typeName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "validity_in_days", nullable = false)
    private long validityInDays;
    @Column(name = "number_of_useage")
    private BigInteger numberOfUseage;
    @Column(name = "useage_count")
    private BigInteger useageCount;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @OneToMany(mappedBy = "cardTypeId")
    private Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cardTypeId")
    private Collection<ScratchCard> scratchCardCollection;

    public ScratchCardType() {
    }

    public ScratchCardType(Long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public ScratchCardType(Long cardTypeId, String typeName, long validityInDays) {
        this.cardTypeId = cardTypeId;
        this.typeName = typeName;
        this.validityInDays = validityInDays;
    }

    public Long getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Long cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public long getValidityInDays() {
        return validityInDays;
    }

    public void setValidityInDays(long validityInDays) {
        this.validityInDays = validityInDays;
    }

    public BigInteger getNumberOfUseage() {
        return numberOfUseage;
    }

    public void setNumberOfUseage(BigInteger numberOfUseage) {
        this.numberOfUseage = numberOfUseage;
    }

    public BigInteger getUseageCount() {
        return useageCount;
    }

    public void setUseageCount(BigInteger useageCount) {
        this.useageCount = useageCount;
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

    @XmlTransient
    public Collection<ScratchCardUnitPrice> getScratchCardUnitPriceCollection() {
        return scratchCardUnitPriceCollection;
    }

    public void setScratchCardUnitPriceCollection(Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollection) {
        this.scratchCardUnitPriceCollection = scratchCardUnitPriceCollection;
    }

    @XmlTransient
    public Collection<ScratchCard> getScratchCardCollection() {
        return scratchCardCollection;
    }

    public void setScratchCardCollection(Collection<ScratchCard> scratchCardCollection) {
        this.scratchCardCollection = scratchCardCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cardTypeId != null ? cardTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScratchCardType)) {
            return false;
        }
        ScratchCardType other = (ScratchCardType) object;
        if ((this.cardTypeId == null && other.cardTypeId != null) || (this.cardTypeId != null && !this.cardTypeId.equals(other.cardTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ScratchCardType[ cardTypeId=" + cardTypeId + " ]";
    }
    
}
