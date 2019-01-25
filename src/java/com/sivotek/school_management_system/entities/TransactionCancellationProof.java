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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "transaction_cancellation_proof", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransactionCancellationProof.findAll", query = "SELECT t FROM TransactionCancellationProof t")
    , @NamedQuery(name = "TransactionCancellationProof.findById", query = "SELECT t FROM TransactionCancellationProof t WHERE t.id = :id")
    , @NamedQuery(name = "TransactionCancellationProof.findByBranchId", query = "SELECT t FROM TransactionCancellationProof t WHERE t.branchId = :branchId")
    , @NamedQuery(name = "TransactionCancellationProof.findByDocumentName", query = "SELECT t FROM TransactionCancellationProof t WHERE t.documentName = :documentName")
    , @NamedQuery(name = "TransactionCancellationProof.findByDocumentExtension", query = "SELECT t FROM TransactionCancellationProof t WHERE t.documentExtension = :documentExtension")
    , @NamedQuery(name = "TransactionCancellationProof.findByDocumentSize", query = "SELECT t FROM TransactionCancellationProof t WHERE t.documentSize = :documentSize")
    , @NamedQuery(name = "TransactionCancellationProof.findByUploadDate", query = "SELECT t FROM TransactionCancellationProof t WHERE t.uploadDate = :uploadDate")
    , @NamedQuery(name = "TransactionCancellationProof.findBySynchStatus", query = "SELECT t FROM TransactionCancellationProof t WHERE t.synchStatus = :synchStatus")})
public class TransactionCancellationProof implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Long id;
    @Column(name = "branch_id")
    private BigInteger branchId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "document_name", nullable = false, length = 255)
    private String documentName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "document_extension", nullable = false, length = 10)
    private String documentExtension;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "document_content", nullable = false)
    private byte[] documentContent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "document_size", nullable = false, length = 25)
    private String documentSize;
    @Basic(optional = false)
    @NotNull
    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "cancellation_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private TransactionCancellation cancellationId;
    @JoinColumn(name = "uploaded_by_employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee uploadedByEmployeeId;

    public TransactionCancellationProof() {
    }

    public TransactionCancellationProof(Long id) {
        this.id = id;
    }

    public TransactionCancellationProof(Long id, String documentName, String documentExtension, byte[] documentContent, String documentSize, Date uploadDate) {
        this.id = id;
        this.documentName = documentName;
        this.documentExtension = documentExtension;
        this.documentContent = documentContent;
        this.documentSize = documentSize;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentExtension() {
        return documentExtension;
    }

    public void setDocumentExtension(String documentExtension) {
        this.documentExtension = documentExtension;
    }

    public byte[] getDocumentContent() {
        return documentContent;
    }

    public void setDocumentContent(byte[] documentContent) {
        this.documentContent = documentContent;
    }

    public String getDocumentSize() {
        return documentSize;
    }

    public void setDocumentSize(String documentSize) {
        this.documentSize = documentSize;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public TransactionCancellation getCancellationId() {
        return cancellationId;
    }

    public void setCancellationId(TransactionCancellation cancellationId) {
        this.cancellationId = cancellationId;
    }

    public Employee getUploadedByEmployeeId() {
        return uploadedByEmployeeId;
    }

    public void setUploadedByEmployeeId(Employee uploadedByEmployeeId) {
        this.uploadedByEmployeeId = uploadedByEmployeeId;
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
        if (!(object instanceof TransactionCancellationProof)) {
            return false;
        }
        TransactionCancellationProof other = (TransactionCancellationProof) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.TransactionCancellationProof[ id=" + id + " ]";
    }
    
}
