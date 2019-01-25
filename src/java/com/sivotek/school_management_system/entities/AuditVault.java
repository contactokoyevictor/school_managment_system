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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "audit_vault", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditVault.findAll", query = "SELECT a FROM AuditVault a")
    , @NamedQuery(name = "AuditVault.findById", query = "SELECT a FROM AuditVault a WHERE a.id = :id")
    , @NamedQuery(name = "AuditVault.findByUserId", query = "SELECT a FROM AuditVault a WHERE a.userId = :userId")
    , @NamedQuery(name = "AuditVault.findByTableName", query = "SELECT a FROM AuditVault a WHERE a.tableName = :tableName")
    , @NamedQuery(name = "AuditVault.findByLogTime", query = "SELECT a FROM AuditVault a WHERE a.logTime = :logTime")
    , @NamedQuery(name = "AuditVault.findBySynchStatus", query = "SELECT a FROM AuditVault a WHERE a.synchStatus = :synchStatus")})
public class AuditVault implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private long userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(nullable = false, length = 80)
    private String tableName;
    @Lob
    private byte[] rowDataOld;
    @Lob
    private byte[] rowDataNew;
    @Lob
    private byte[] systemInfo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date logTime;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "actionTypeId", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private ActionType actionTypeId;

    public AuditVault() {
    }

    public AuditVault(Integer id) {
        this.id = id;
    }

    public AuditVault(Integer id, long userId, String tableName, Date logTime) {
        this.id = id;
        this.userId = userId;
        this.tableName = tableName;
        this.logTime = logTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public byte[] getRowDataOld() {
        return rowDataOld;
    }

    public void setRowDataOld(byte[] rowDataOld) {
        this.rowDataOld = rowDataOld;
    }

    public byte[] getRowDataNew() {
        return rowDataNew;
    }

    public void setRowDataNew(byte[] rowDataNew) {
        this.rowDataNew = rowDataNew;
    }

    public byte[] getSystemInfo() {
        return systemInfo;
    }

    public void setSystemInfo(byte[] systemInfo) {
        this.systemInfo = systemInfo;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public ActionType getActionTypeId() {
        return actionTypeId;
    }

    public void setActionTypeId(ActionType actionTypeId) {
        this.actionTypeId = actionTypeId;
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
        if (!(object instanceof AuditVault)) {
            return false;
        }
        AuditVault other = (AuditVault) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.AuditVault[ id=" + id + " ]";
    }
    
}
