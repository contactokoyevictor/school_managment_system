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
import javax.persistence.Lob;
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
@Table(name = "action_type", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActionType.findAll", query = "SELECT a FROM ActionType a")
    , @NamedQuery(name = "ActionType.findById", query = "SELECT a FROM ActionType a WHERE a.id = :id")
    , @NamedQuery(name = "ActionType.findByName", query = "SELECT a FROM ActionType a WHERE a.name = :name")
    , @NamedQuery(name = "ActionType.findByCreated", query = "SELECT a FROM ActionType a WHERE a.created = :created")
    , @NamedQuery(name = "ActionType.findBySynchStatus", query = "SELECT a FROM ActionType a WHERE a.synchStatus = :synchStatus")})
public class ActionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(nullable = false, length = 50)
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actionTypeId")
    private Collection<AuditVault> auditVaultCollection;

    public ActionType() {
    }

    public ActionType(Long id) {
        this.id = id;
    }

    public ActionType(Long id, String name, String description, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<AuditVault> getAuditVaultCollection() {
        return auditVaultCollection;
    }

    public void setAuditVaultCollection(Collection<AuditVault> auditVaultCollection) {
        this.auditVaultCollection = auditVaultCollection;
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
        if (!(object instanceof ActionType)) {
            return false;
        }
        ActionType other = (ActionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.ActionType[ id=" + id + " ]";
    }
    
}
