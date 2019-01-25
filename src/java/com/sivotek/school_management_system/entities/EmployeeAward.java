/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "employee_award", catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeAward.findAll", query = "SELECT e FROM EmployeeAward e")
    , @NamedQuery(name = "EmployeeAward.findByEmployeeAwardId", query = "SELECT e FROM EmployeeAward e WHERE e.employeeAwardId = :employeeAwardId")
    , @NamedQuery(name = "EmployeeAward.findByAwardName", query = "SELECT e FROM EmployeeAward e WHERE e.awardName = :awardName")
    , @NamedQuery(name = "EmployeeAward.findByGiftItem", query = "SELECT e FROM EmployeeAward e WHERE e.giftItem = :giftItem")
    , @NamedQuery(name = "EmployeeAward.findByAwardAmount", query = "SELECT e FROM EmployeeAward e WHERE e.awardAmount = :awardAmount")
    , @NamedQuery(name = "EmployeeAward.findByAwardDate", query = "SELECT e FROM EmployeeAward e WHERE e.awardDate = :awardDate")
    , @NamedQuery(name = "EmployeeAward.findByViewStatus", query = "SELECT e FROM EmployeeAward e WHERE e.viewStatus = :viewStatus")
    , @NamedQuery(name = "EmployeeAward.findByNotifyMe", query = "SELECT e FROM EmployeeAward e WHERE e.notifyMe = :notifyMe")
    , @NamedQuery(name = "EmployeeAward.findBySynchStatus", query = "SELECT e FROM EmployeeAward e WHERE e.synchStatus = :synchStatus")})
public class EmployeeAward implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "employee_award_id", nullable = false)
    private Long employeeAwardId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "award_name", nullable = false, length = 100)
    private String awardName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "gift_item", nullable = false, length = 300)
    private String giftItem;
    @Basic(optional = false)
    @NotNull
    @Column(name = "award_amount", nullable = false)
    private int awardAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "award_date", nullable = false, length = 10)
    private String awardDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "view_status", nullable = false)
    private boolean viewStatus;
    @Basic(optional = false)
    @NotNull
    @Column(name = "notify_me", nullable = false)
    private boolean notifyMe;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeId;

    public EmployeeAward() {
    }

    public EmployeeAward(Long employeeAwardId) {
        this.employeeAwardId = employeeAwardId;
    }

    public EmployeeAward(Long employeeAwardId, String awardName, String giftItem, int awardAmount, String awardDate, boolean viewStatus, boolean notifyMe) {
        this.employeeAwardId = employeeAwardId;
        this.awardName = awardName;
        this.giftItem = giftItem;
        this.awardAmount = awardAmount;
        this.awardDate = awardDate;
        this.viewStatus = viewStatus;
        this.notifyMe = notifyMe;
    }

    public Long getEmployeeAwardId() {
        return employeeAwardId;
    }

    public void setEmployeeAwardId(Long employeeAwardId) {
        this.employeeAwardId = employeeAwardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getGiftItem() {
        return giftItem;
    }

    public void setGiftItem(String giftItem) {
        this.giftItem = giftItem;
    }

    public int getAwardAmount() {
        return awardAmount;
    }

    public void setAwardAmount(int awardAmount) {
        this.awardAmount = awardAmount;
    }

    public String getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(String awardDate) {
        this.awardDate = awardDate;
    }

    public boolean getViewStatus() {
        return viewStatus;
    }

    public void setViewStatus(boolean viewStatus) {
        this.viewStatus = viewStatus;
    }

    public boolean getNotifyMe() {
        return notifyMe;
    }

    public void setNotifyMe(boolean notifyMe) {
        this.notifyMe = notifyMe;
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

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeAwardId != null ? employeeAwardId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeAward)) {
            return false;
        }
        EmployeeAward other = (EmployeeAward) object;
        if ((this.employeeAwardId == null && other.employeeAwardId != null) || (this.employeeAwardId != null && !this.employeeAwardId.equals(other.employeeAwardId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.EmployeeAward[ employeeAwardId=" + employeeAwardId + " ]";
    }
    
}
