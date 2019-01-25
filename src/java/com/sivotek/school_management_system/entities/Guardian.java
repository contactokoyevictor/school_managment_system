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
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Guardian.findAll", query = "SELECT g FROM Guardian g")
    , @NamedQuery(name = "Guardian.findByGuardianId", query = "SELECT g FROM Guardian g WHERE g.guardianId = :guardianId")
    , @NamedQuery(name = "Guardian.findByFirstName", query = "SELECT g FROM Guardian g WHERE g.firstName = :firstName")
    , @NamedQuery(name = "Guardian.findByLastName", query = "SELECT g FROM Guardian g WHERE g.lastName = :lastName")
    , @NamedQuery(name = "Guardian.findByRelation", query = "SELECT g FROM Guardian g WHERE g.relation = :relation")
    , @NamedQuery(name = "Guardian.findByEmail", query = "SELECT g FROM Guardian g WHERE g.email = :email")
    , @NamedQuery(name = "Guardian.findByMobilePhone", query = "SELECT g FROM Guardian g WHERE g.mobilePhone = :mobilePhone")
    , @NamedQuery(name = "Guardian.findByMobilePhone2", query = "SELECT g FROM Guardian g WHERE g.mobilePhone2 = :mobilePhone2")
    , @NamedQuery(name = "Guardian.findByMobilePhone3", query = "SELECT g FROM Guardian g WHERE g.mobilePhone3 = :mobilePhone3")
    , @NamedQuery(name = "Guardian.findByDob", query = "SELECT g FROM Guardian g WHERE g.dob = :dob")
    , @NamedQuery(name = "Guardian.findByOccupation", query = "SELECT g FROM Guardian g WHERE g.occupation = :occupation")
    , @NamedQuery(name = "Guardian.findByIncome", query = "SELECT g FROM Guardian g WHERE g.income = :income")
    , @NamedQuery(name = "Guardian.findByEducation", query = "SELECT g FROM Guardian g WHERE g.education = :education")
    , @NamedQuery(name = "Guardian.findByCreatedAt", query = "SELECT g FROM Guardian g WHERE g.createdAt = :createdAt")
    , @NamedQuery(name = "Guardian.findByUpdatedAt", query = "SELECT g FROM Guardian g WHERE g.updatedAt = :updatedAt")
    , @NamedQuery(name = "Guardian.findByStatus", query = "SELECT g FROM Guardian g WHERE g.status = :status")
    , @NamedQuery(name = "Guardian.findBySynchStatus", query = "SELECT g FROM Guardian g WHERE g.synchStatus = :synchStatus")})
public class Guardian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "guardian_id", nullable = false)
    private Long guardianId;
    @Size(max = 255)
    @Column(name = "first_name", length = 255)
    private String firstName;
    @Size(max = 255)
    @Column(name = "last_name", length = 255)
    private String lastName;
    @Size(max = 255)
    @Column(length = 255)
    private String relation;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(length = 255)
    private String email;
    @Size(max = 255)
    @Column(name = "mobile_phone", length = 255)
    private String mobilePhone;
    @Size(max = 255)
    @Column(name = "mobile_phone2", length = 255)
    private String mobilePhone2;
    @Size(max = 255)
    @Column(name = "mobile_phone3", length = 255)
    private String mobilePhone3;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String address;
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 255)
    @Column(length = 255)
    private String occupation;
    @Size(max = 255)
    @Column(length = 255)
    private String income;
    @Size(max = 255)
    @Column(length = 255)
    private String education;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private Boolean status;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(mappedBy = "guardianId")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "guardianId")
    private Collection<Student> studentCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "city", referencedColumnName = "id")
    @ManyToOne
    private Cities city;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne
    private States state;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
    private Countries countryId;

    public Guardian() {
    }

    public Guardian(Long guardianId) {
        this.guardianId = guardianId;
    }

    public Long getGuardianId() {
        return guardianId;
    }

    public void setGuardianId(Long guardianId) {
        this.guardianId = guardianId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone2() {
        return mobilePhone2;
    }

    public void setMobilePhone2(String mobilePhone2) {
        this.mobilePhone2 = mobilePhone2;
    }

    public String getMobilePhone3() {
        return mobilePhone3;
    }

    public void setMobilePhone3(String mobilePhone3) {
        this.mobilePhone3 = mobilePhone3;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }

    public Countries getCountryId() {
        return countryId;
    }

    public void setCountryId(Countries countryId) {
        this.countryId = countryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (guardianId != null ? guardianId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Guardian)) {
            return false;
        }
        Guardian other = (Guardian) object;
        if ((this.guardianId == null && other.guardianId != null) || (this.guardianId != null && !this.guardianId.equals(other.guardianId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Guardian[ guardianId=" + guardianId + " ]";
    }
    
}
