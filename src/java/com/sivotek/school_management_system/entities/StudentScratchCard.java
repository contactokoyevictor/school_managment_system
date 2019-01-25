/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "student_scratch_card", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentScratchCard.findAll", query = "SELECT s FROM StudentScratchCard s")
    , @NamedQuery(name = "StudentScratchCard.findByCardId", query = "SELECT s FROM StudentScratchCard s WHERE s.cardId = :cardId")
    , @NamedQuery(name = "StudentScratchCard.findByBranchId", query = "SELECT s FROM StudentScratchCard s WHERE s.branchId = :branchId")
    , @NamedQuery(name = "StudentScratchCard.findByStudentId", query = "SELECT s FROM StudentScratchCard s WHERE s.studentId = :studentId")
    , @NamedQuery(name = "StudentScratchCard.findByFirstLogin", query = "SELECT s FROM StudentScratchCard s WHERE s.firstLogin = :firstLogin")
    , @NamedQuery(name = "StudentScratchCard.findBySynchStatus", query = "SELECT s FROM StudentScratchCard s WHERE s.synchStatus = :synchStatus")})
public class StudentScratchCard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "card_id", nullable = false)
    private Long cardId;
    @Column(name = "branch_id")
    private BigInteger branchId;
    @Column(name = "student_id")
    private BigInteger studentId;
    @Column(name = "first_login")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstLogin;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "card_id", referencedColumnName = "card_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ScratchCard scratchCard;

    public StudentScratchCard() {
    }

    public StudentScratchCard(Long cardId) {
        this.cardId = cardId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public BigInteger getStudentId() {
        return studentId;
    }

    public void setStudentId(BigInteger studentId) {
        this.studentId = studentId;
    }

    public Date getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(Date firstLogin) {
        this.firstLogin = firstLogin;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public ScratchCard getScratchCard() {
        return scratchCard;
    }

    public void setScratchCard(ScratchCard scratchCard) {
        this.scratchCard = scratchCard;
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
        if (!(object instanceof StudentScratchCard)) {
            return false;
        }
        StudentScratchCard other = (StudentScratchCard) object;
        if ((this.cardId == null && other.cardId != null) || (this.cardId != null && !this.cardId.equals(other.cardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.StudentScratchCard[ cardId=" + cardId + " ]";
    }
    
}
