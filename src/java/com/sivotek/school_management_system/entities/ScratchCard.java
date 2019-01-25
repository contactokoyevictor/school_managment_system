/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
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
@Table(name = "scratch_card", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"card_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScratchCard.findAll", query = "SELECT s FROM ScratchCard s")
    , @NamedQuery(name = "ScratchCard.findByCardId", query = "SELECT s FROM ScratchCard s WHERE s.cardId = :cardId")
    , @NamedQuery(name = "ScratchCard.findByCard", query = "SELECT s FROM ScratchCard s WHERE s.card = :card")
    , @NamedQuery(name = "ScratchCard.findByStatus", query = "SELECT s FROM ScratchCard s WHERE s.status = :status")
    , @NamedQuery(name = "ScratchCard.findBySynchStatus", query = "SELECT s FROM ScratchCard s WHERE s.synchStatus = :synchStatus")
    , @NamedQuery(name = "ScratchCard.findByCreateddate", query = "SELECT s FROM ScratchCard s WHERE s.createddate = :createddate")
    , @NamedQuery(name = "ScratchCard.findByChangeddate", query = "SELECT s FROM ScratchCard s WHERE s.changeddate = :changeddate")})
public class ScratchCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_id", nullable = false)
    private Long cardId;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long card;
    private Boolean status;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "scratchCard")
    private StudentScratchCard studentScratchCard;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "card_type_id", referencedColumnName = "card_type_id", nullable = false)
    @ManyToOne(optional = false)
    private ScratchCardType cardTypeId;

    public ScratchCard() {
    }

    public ScratchCard(Long cardId) {
        this.cardId = cardId;
    }

    public ScratchCard(Long cardId, long card) {
        this.cardId = cardId;
        this.card = card;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public long getCard() {
        return card;
    }

    public void setCard(long card) {
        this.card = card;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public StudentScratchCard getStudentScratchCard() {
        return studentScratchCard;
    }

    public void setStudentScratchCard(StudentScratchCard studentScratchCard) {
        this.studentScratchCard = studentScratchCard;
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
        hash += (cardId != null ? cardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScratchCard)) {
            return false;
        }
        ScratchCard other = (ScratchCard) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ScratchCard[ cardId=" + cardId + " ]";
    }
    
}
