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
@Table(catalog = "sivotek_school_management_system_v1_2", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByEmployeeId", query = "SELECT e FROM Employee e WHERE e.employeeId = :employeeId")
    , @NamedQuery(name = "Employee.findByEmploymentId", query = "SELECT e FROM Employee e WHERE e.employmentId = :employmentId")
    , @NamedQuery(name = "Employee.findByFirstName", query = "SELECT e FROM Employee e WHERE e.firstName = :firstName")
    , @NamedQuery(name = "Employee.findByLastName", query = "SELECT e FROM Employee e WHERE e.lastName = :lastName")
    , @NamedQuery(name = "Employee.findByDateOfBirth", query = "SELECT e FROM Employee e WHERE e.dateOfBirth = :dateOfBirth")
    , @NamedQuery(name = "Employee.findByGender", query = "SELECT e FROM Employee e WHERE e.gender = :gender")
    , @NamedQuery(name = "Employee.findByMaratialStatus", query = "SELECT e FROM Employee e WHERE e.maratialStatus = :maratialStatus")
    , @NamedQuery(name = "Employee.findByFatherName", query = "SELECT e FROM Employee e WHERE e.fatherName = :fatherName")
    , @NamedQuery(name = "Employee.findByPassportNumber", query = "SELECT e FROM Employee e WHERE e.passportNumber = :passportNumber")
    , @NamedQuery(name = "Employee.findByPhoto", query = "SELECT e FROM Employee e WHERE e.photo = :photo")
    , @NamedQuery(name = "Employee.findByPhotoAPath", query = "SELECT e FROM Employee e WHERE e.photoAPath = :photoAPath")
    , @NamedQuery(name = "Employee.findByMobile", query = "SELECT e FROM Employee e WHERE e.mobile = :mobile")
    , @NamedQuery(name = "Employee.findByPhone", query = "SELECT e FROM Employee e WHERE e.phone = :phone")
    , @NamedQuery(name = "Employee.findByEmail", query = "SELECT e FROM Employee e WHERE e.email = :email")
    , @NamedQuery(name = "Employee.findByJoiningDate", query = "SELECT e FROM Employee e WHERE e.joiningDate = :joiningDate")
    , @NamedQuery(name = "Employee.findByStatus", query = "SELECT e FROM Employee e WHERE e.status = :status")
    , @NamedQuery(name = "Employee.findBySynchStatus", query = "SELECT e FROM Employee e WHERE e.synchStatus = :synchStatus")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "employment_id", nullable = false, length = 200)
    private String employmentId;
    @Size(max = 100)
    @Column(name = "first_name", length = 100)
    private String firstName;
    @Size(max = 100)
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Size(max = 50)
    @Column(length = 50)
    private String gender;
    @Column(name = "maratial_status")
    private Boolean maratialStatus;
    @Size(max = 100)
    @Column(name = "father_name", length = 100)
    private String fatherName;
    @Size(max = 100)
    @Column(name = "passport_number", length = 100)
    private String passportNumber;
    @Size(max = 150)
    @Column(length = 150)
    private String photo;
    @Size(max = 150)
    @Column(name = "photo_a_path", length = 150)
    private String photoAPath;
    @Lob
    @Size(max = 65535)
    @Column(name = "present_address", length = 65535)
    private String presentAddress;
    @Size(max = 100)
    @Column(length = 100)
    private String mobile;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(length = 100)
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(length = 100)
    private String email;
    @Column(name = "joining_date")
    @Temporal(TemporalType.DATE)
    private Date joiningDate;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean status;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmployeeAward> employeeAwardCollection;
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id")
    @ManyToOne
    private CompanyBranch branchId;
    @JoinColumn(name = "nationality", referencedColumnName = "id")
    @ManyToOne
    private Countries nationality;
    @JoinColumn(name = "city", referencedColumnName = "id")
    @ManyToOne
    private Cities city;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
    private Countries countryId;
    @JoinColumn(name = "designation_id", referencedColumnName = "designation_id")
    @ManyToOne
    private Designation designationId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmployeePayroll> employeePayrollCollection;
    @OneToMany(mappedBy = "createdByEmployeeId")
    private Collection<AccountTransaction> accountTransactionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdByEmployeeId")
    private Collection<TransactionCancellation> transactionCancellationCollection;
    @OneToMany(mappedBy = "employeeId")
    private Collection<Users> usersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<BankAccountPrivilege> bankAccountPrivilegeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private Collection<BankAccountPrivilege> bankAccountPrivilegeCollection1;
    @OneToMany(mappedBy = "lastModifiedBy")
    private Collection<BankAccountPrivilege> bankAccountPrivilegeCollection2;
    @OneToMany(mappedBy = "teacherId")
    private Collection<ClassSubjects> classSubjectsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmployeeBank> employeeBankCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdByEmployeeId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @OneToMany(mappedBy = "lastModifiedEmployeeId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection1;
    @OneToMany(mappedBy = "teacherId")
    private Collection<Class> classCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "uploadedByEmployeeId")
    private Collection<TransactionCancellationProof> transactionCancellationProofCollection;
    @OneToMany(mappedBy = "employeeId")
    private Collection<Invoice> invoiceCollection;
    @OneToMany(mappedBy = "createdByEmployeeId")
    private Collection<Invoice> invoiceCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId")
    private Collection<EmployeeAttendance> employeeAttendanceCollection;

    public Employee() {
    }

    public Employee(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(Long employeeId, String employmentId, boolean status) {
        this.employeeId = employeeId;
        this.employmentId = employmentId;
        this.status = status;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmploymentId() {
        return employmentId;
    }

    public void setEmploymentId(String employmentId) {
        this.employmentId = employmentId;
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getMaratialStatus() {
        return maratialStatus;
    }

    public void setMaratialStatus(Boolean maratialStatus) {
        this.maratialStatus = maratialStatus;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhotoAPath() {
        return photoAPath;
    }

    public void setPhotoAPath(String photoAPath) {
        this.photoAPath = photoAPath;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    @XmlTransient
    public Collection<EmployeeAward> getEmployeeAwardCollection() {
        return employeeAwardCollection;
    }

    public void setEmployeeAwardCollection(Collection<EmployeeAward> employeeAwardCollection) {
        this.employeeAwardCollection = employeeAwardCollection;
    }

    public CompanyBranch getBranchId() {
        return branchId;
    }

    public void setBranchId(CompanyBranch branchId) {
        this.branchId = branchId;
    }

    public Countries getNationality() {
        return nationality;
    }

    public void setNationality(Countries nationality) {
        this.nationality = nationality;
    }

    public Cities getCity() {
        return city;
    }

    public void setCity(Cities city) {
        this.city = city;
    }

    public Countries getCountryId() {
        return countryId;
    }

    public void setCountryId(Countries countryId) {
        this.countryId = countryId;
    }

    public Designation getDesignationId() {
        return designationId;
    }

    public void setDesignationId(Designation designationId) {
        this.designationId = designationId;
    }

    @XmlTransient
    public Collection<EmployeePayroll> getEmployeePayrollCollection() {
        return employeePayrollCollection;
    }

    public void setEmployeePayrollCollection(Collection<EmployeePayroll> employeePayrollCollection) {
        this.employeePayrollCollection = employeePayrollCollection;
    }

    @XmlTransient
    public Collection<AccountTransaction> getAccountTransactionCollection() {
        return accountTransactionCollection;
    }

    public void setAccountTransactionCollection(Collection<AccountTransaction> accountTransactionCollection) {
        this.accountTransactionCollection = accountTransactionCollection;
    }

    @XmlTransient
    public Collection<TransactionCancellation> getTransactionCancellationCollection() {
        return transactionCancellationCollection;
    }

    public void setTransactionCancellationCollection(Collection<TransactionCancellation> transactionCancellationCollection) {
        this.transactionCancellationCollection = transactionCancellationCollection;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @XmlTransient
    public Collection<BankAccountPrivilege> getBankAccountPrivilegeCollection() {
        return bankAccountPrivilegeCollection;
    }

    public void setBankAccountPrivilegeCollection(Collection<BankAccountPrivilege> bankAccountPrivilegeCollection) {
        this.bankAccountPrivilegeCollection = bankAccountPrivilegeCollection;
    }

    @XmlTransient
    public Collection<BankAccountPrivilege> getBankAccountPrivilegeCollection1() {
        return bankAccountPrivilegeCollection1;
    }

    public void setBankAccountPrivilegeCollection1(Collection<BankAccountPrivilege> bankAccountPrivilegeCollection1) {
        this.bankAccountPrivilegeCollection1 = bankAccountPrivilegeCollection1;
    }

    @XmlTransient
    public Collection<BankAccountPrivilege> getBankAccountPrivilegeCollection2() {
        return bankAccountPrivilegeCollection2;
    }

    public void setBankAccountPrivilegeCollection2(Collection<BankAccountPrivilege> bankAccountPrivilegeCollection2) {
        this.bankAccountPrivilegeCollection2 = bankAccountPrivilegeCollection2;
    }

    @XmlTransient
    public Collection<ClassSubjects> getClassSubjectsCollection() {
        return classSubjectsCollection;
    }

    public void setClassSubjectsCollection(Collection<ClassSubjects> classSubjectsCollection) {
        this.classSubjectsCollection = classSubjectsCollection;
    }

    @XmlTransient
    public Collection<EmployeeBank> getEmployeeBankCollection() {
        return employeeBankCollection;
    }

    public void setEmployeeBankCollection(Collection<EmployeeBank> employeeBankCollection) {
        this.employeeBankCollection = employeeBankCollection;
    }

    @XmlTransient
    public Collection<SchoolFeeInvoiceDetails> getSchoolFeeInvoiceDetailsCollection() {
        return schoolFeeInvoiceDetailsCollection;
    }

    public void setSchoolFeeInvoiceDetailsCollection(Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection) {
        this.schoolFeeInvoiceDetailsCollection = schoolFeeInvoiceDetailsCollection;
    }

    @XmlTransient
    public Collection<SchoolFeeInvoiceDetails> getSchoolFeeInvoiceDetailsCollection1() {
        return schoolFeeInvoiceDetailsCollection1;
    }

    public void setSchoolFeeInvoiceDetailsCollection1(Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection1) {
        this.schoolFeeInvoiceDetailsCollection1 = schoolFeeInvoiceDetailsCollection1;
    }

    @XmlTransient
    public Collection<Class> getClassCollection() {
        return classCollection;
    }

    public void setClassCollection(Collection<Class> classCollection) {
        this.classCollection = classCollection;
    }

    @XmlTransient
    public Collection<TransactionCancellationProof> getTransactionCancellationProofCollection() {
        return transactionCancellationProofCollection;
    }

    public void setTransactionCancellationProofCollection(Collection<TransactionCancellationProof> transactionCancellationProofCollection) {
        this.transactionCancellationProofCollection = transactionCancellationProofCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection1() {
        return invoiceCollection1;
    }

    public void setInvoiceCollection1(Collection<Invoice> invoiceCollection1) {
        this.invoiceCollection1 = invoiceCollection1;
    }

    @XmlTransient
    public Collection<EmployeeAttendance> getEmployeeAttendanceCollection() {
        return employeeAttendanceCollection;
    }

    public void setEmployeeAttendanceCollection(Collection<EmployeeAttendance> employeeAttendanceCollection) {
        this.employeeAttendanceCollection = employeeAttendanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employeeId != null ? employeeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.employeeId == null && other.employeeId != null) || (this.employeeId != null && !this.employeeId.equals(other.employeeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.Employee[ employeeId=" + employeeId + " ]";
    }
    
}
