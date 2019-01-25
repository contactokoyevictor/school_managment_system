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
@Table(name = "transaction_cancellation", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionCancellation.findAll", query = "SELECT t FROM TransactionCancellation t")
    , @NamedQuery(name = "TransactionCancellation.findById", query = "SELECT t FROM TransactionCancellation t WHERE t.id = :id")
    , @NamedQuery(name = "TransactionCancellation.findByCancellationDate", query = "SELECT t FROM TransactionCancellation t WHERE t.cancellationDate = :cancellationDate")
    , @NamedQuery(name = "TransactionCancellation.findBySynchStatus", query = "SELECT t FROM TransactionCancellation t WHERE t.synchStatus = :synchStatus")})
public class TransactionCancellation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String reason;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancellation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancellationDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "transaction_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private AccountTransaction transactionId;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "created_by_employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee createdByEmployeeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cancellationId")
    private Collection<TransactionCancellationProof> transactionCancellationProofCollection;

    public TransactionCancellation() {
    }

    public TransactionCancellation(Long id) {
        this.id = id;
    }

    public TransactionCancellation(Long id, String reason, Date cancellationDate) {
        this.id = id;
        this.reason = reason;
        this.cancellationDate = cancellationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getCancellationDate() {
        return cancellationDate;
    }

    public void setCancellationDate(Date cancellationDate) {
        this.cancellationDate = cancellationDate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public AccountTransaction getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(AccountTransaction transactionId) {
        this.transactionId = transactionId;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public Employee getCreatedByEmployeeId() {
        return createdByEmployeeId;
    }

    public void setCreatedByEmployeeId(Employee createdByEmployeeId) {
        this.createdByEmployeeId = createdByEmployeeId;
    }

    @XmlTransient
    public Collection<TransactionCancellationProof> getTransactionCancellationProofCollection() {
        return transactionCancellationProofCollection;
    }

    public void setTransactionCancellationProofCollection(Collection<TransactionCancellationProof> transactionCancellationProofCollection) {
        this.transactionCancellationProofCollection = transactionCancellationProofCollection;
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
        if (!(object instanceof TransactionCancellation)) {
            return false;
        }
        TransactionCancellation other = (TransactionCancellation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.TransactionCancellation[ id=" + id + " ]";
    }
    
    
    
}
