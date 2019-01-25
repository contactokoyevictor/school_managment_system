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
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Noticeboard.findAll", query = "SELECT n FROM Noticeboard n")
    , @NamedQuery(name = "Noticeboard.findByNoticeId", query = "SELECT n FROM Noticeboard n WHERE n.noticeId = :noticeId")
    , @NamedQuery(name = "Noticeboard.findByBranchId", query = "SELECT n FROM Noticeboard n WHERE n.branchId = :branchId")
    , @NamedQuery(name = "Noticeboard.findByCreateTimestamp", query = "SELECT n FROM Noticeboard n WHERE n.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "Noticeboard.findBySynchStatus", query = "SELECT n FROM Noticeboard n WHERE n.synchStatus = :synchStatus")})
public class Noticeboard implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "notice_id", nullable = false)
    private Long noticeId;
    @Column(name = "branch_id")
    private BigInteger branchId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "notice_title", nullable = false, length = 65535)
    private String noticeTitle;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(nullable = false, length = 65535)
    private String notice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_timestamp", nullable = false)
    private int createTimestamp;
    @Column(name = "synch_status")
    private Boolean synchStatus;

    public Noticeboard() {
    }

    public Noticeboard(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Noticeboard(Long noticeId, String noticeTitle, String notice, int createTimestamp) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.notice = notice;
        this.createTimestamp = createTimestamp;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public BigInteger getBranchId() {
        return branchId;
    }

    public void setBranchId(BigInteger branchId) {
        this.branchId = branchId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(int createTimestamp) {
        this.createTimestamp = createTimestamp;
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
        hash += (noticeId != null ? noticeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Noticeboard)) {
            return false;
        }
        Noticeboard other = (Noticeboard) object;
        if ((this.noticeId == null && other.noticeId != null) || (this.noticeId != null && !this.noticeId.equals(other.noticeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Noticeboard[ noticeId=" + noticeId + " ]";
    }
    
}
