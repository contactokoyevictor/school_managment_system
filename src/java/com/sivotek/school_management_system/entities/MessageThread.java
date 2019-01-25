/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "message_thread", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessageThread.findAll", query = "SELECT m FROM MessageThread m")
    , @NamedQuery(name = "MessageThread.findByMessageThreadId", query = "SELECT m FROM MessageThread m WHERE m.messageThreadId = :messageThreadId")
    , @NamedQuery(name = "MessageThread.findByBranchId", query = "SELECT m FROM MessageThread m WHERE m.branchId = :branchId")
    , @NamedQuery(name = "MessageThread.findBySynchStatus", query = "SELECT m FROM MessageThread m WHERE m.synchStatus = :synchStatus")})
public class MessageThread implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "message_thread_id", nullable = false)
    private Long messageThreadId;
    @Column(name = "branch_id")
    private BigInteger branchId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "message_thread_code", nullable = false, length = 65535)
    private String messageThreadCode;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String sender;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String reciever;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "last_message_timestamp", nullable = false, length = 65535)
    private String lastMessageTimestamp;
    @Column(name = "synch_status")
    private Boolean synchStatus;

    public MessageThread() {
    }

    public MessageThread(Long messageThreadId) {
        this.messageThreadId = messageThreadId;
    }

    public MessageThread(Long messageThreadId, String messageThreadCode, String sender, String reciever, String lastMessageTimestamp) {
        this.messageThreadId = messageThreadId;
        this.messageThreadCode = messageThreadCode;
        this.sender = sender;
        this.reciever = reciever;
        this.lastMessageTimestamp = lastMessageTimestamp;
    }

    public Long getMessageThreadId() {
        return messageThreadId;
    }

    public void setMessageThreadId(Long messageThreadId) {
        this.messageThreadId = messageThreadId;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public String getMessageThreadCode() {
        return messageThreadCode;
    }

    public void setMessageThreadCode(String messageThreadCode) {
        this.messageThreadCode = messageThreadCode;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getLastMessageTimestamp() {
        return lastMessageTimestamp;
    }

    public void setLastMessageTimestamp(String lastMessageTimestamp) {
        this.lastMessageTimestamp = lastMessageTimestamp;
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
        hash += (messageThreadId != null ? messageThreadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageThread)) {
            return false;
        }
        MessageThread other = (MessageThread) object;
        if ((this.messageThreadId == null && other.messageThreadId != null) || (this.messageThreadId != null && !this.messageThreadId.equals(other.messageThreadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.MessageThread[ messageThreadId=" + messageThreadId + " ]";
    }
    
}
