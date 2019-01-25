/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MY USER
 */
@Entity
@Table(name = "company_branch", catalog = "sivotek_school_management_system_v1_2", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"branch_id"})
    , @UniqueConstraint(columnNames = {"server_mac"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompanyBranch.findAll", query = "SELECT c FROM CompanyBranch c")
    , @NamedQuery(name = "CompanyBranch.findByBranchId", query = "SELECT c FROM CompanyBranch c WHERE c.branchId = :branchId")
    , @NamedQuery(name = "CompanyBranch.findByPubkey", query = "SELECT c FROM CompanyBranch c WHERE c.pubkey = :pubkey")
    , @NamedQuery(name = "CompanyBranch.findByBranchName", query = "SELECT c FROM CompanyBranch c WHERE c.branchName = :branchName")
    , @NamedQuery(name = "CompanyBranch.findByServerMac", query = "SELECT c FROM CompanyBranch c WHERE c.serverMac = :serverMac")
    , @NamedQuery(name = "CompanyBranch.findByCompanyAppSereverAuthCode", query = "SELECT c FROM CompanyBranch c WHERE c.companyAppSereverAuthCode = :companyAppSereverAuthCode")
    , @NamedQuery(name = "CompanyBranch.findBySynchStatus", query = "SELECT c FROM CompanyBranch c WHERE c.synchStatus = :synchStatus")
    , @NamedQuery(name = "CompanyBranch.findByCreateddate", query = "SELECT c FROM CompanyBranch c WHERE c.createddate = :createddate")
    , @NamedQuery(name = "CompanyBranch.findByChangeddate", query = "SELECT c FROM CompanyBranch c WHERE c.changeddate = :changeddate")})
public class CompanyBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "branch_id", nullable = false)
    private Long branchId;
    private BigInteger pubkey;
    @Size(max = 255)
    @Column(name = "branch_name", length = 255)
    private String branchName;
    @Size(max = 255)
    @Column(name = "server_mac", length = 255)
    private String serverMac;
    @Size(max = 255)
    @Column(name = "company_app_serever_auth_code", length = 255)
    private String companyAppSereverAuthCode;
    @Column(name = "synch_status")
    private Boolean synchStatus;
    @Lob
    @Size(max = 65535)
    @Column(length = 65535)
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createddate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeddate;
    @OneToMany(mappedBy = "branchId")
    private Collection<Subject> subjectCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<EmployeeAward> employeeAwardCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Section> sectionCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Employee> employeeCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<UtilityBillDetails> utilityBillDetailsCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ScratchCardType> scratchCardTypeCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BankInstitution> bankInstitutionCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ClassCategory> classCategoryCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<EmployeePayroll> employeePayrollCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BankAccountBalance> bankAccountBalanceCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BankAccount> bankAccountCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<SubjectAttendance> subjectAttendanceCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<AccountTransaction> accountTransactionCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<AuditVault> auditVaultCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection;
    @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    @ManyToOne
    private Company companyId;
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    @ManyToOne
    private BranchAddress addressId;
    @JoinColumn(name = "phone_id", referencedColumnName = "phone_id")
    @ManyToOne
    private BranchPhone phoneId;
    @JoinColumn(name = "email_id", referencedColumnName = "email_id")
    @ManyToOne
    private BranchEmail emailId;
    @JoinColumn(name = "fax_id", referencedColumnName = "fax_id")
    @ManyToOne
    private BranchFax faxId;
    @OneToMany(mappedBy = "branchId")
    private Collection<BranchFax> branchFaxCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<TransactionCancellation> transactionCancellationCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BranchEmail> branchEmailCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Exam> examCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<StudentBehavouralTrait> studentBehavouralTraitCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BankAccountPrivilege> bankAccountPrivilegeCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ClassSubjects> classSubjectsCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<PurchaseOrder> purchaseOrderCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Grade> gradeCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BranchPhone> branchPhoneCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Designation> designationCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ExamClassPosition> examClassPositionCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<EmployeeBank> employeeBankCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Student> studentCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<AcademicYears> academicYearsCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<StudentDailyAttendance> studentDailyAttendanceCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ClassRoutine> classRoutineCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ExamMark> examMarkCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Term> termCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Department> departmentCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Guardian> guardianCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Class> classCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<ScratchCard> scratchCardCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BehavouralTrait> behavouralTraitCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<CaMark> caMarkCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BranchAddress> branchAddressCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<MiscellaneousExpense> miscellaneousExpenseCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<Invoice> invoiceCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollection;
    @OneToMany(mappedBy = "branchId")
    private Collection<EmployeeAttendance> employeeAttendanceCollection;

    public CompanyBranch() {
    }

    public CompanyBranch(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public BigInteger getPubkey() {
        return pubkey;
    }

    public void setPubkey(BigInteger pubkey) {
        this.pubkey = pubkey;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getServerMac() {
        return serverMac;
    }

    public void setServerMac(String serverMac) {
        this.serverMac = serverMac;
    }

    public String getCompanyAppSereverAuthCode() {
        return companyAppSereverAuthCode;
    }

    public void setCompanyAppSereverAuthCode(String companyAppSereverAuthCode) {
        this.companyAppSereverAuthCode = companyAppSereverAuthCode;
    }

    public Boolean getSynchStatus() {
        return synchStatus;
    }

    public void setSynchStatus(Boolean synchStatus) {
        this.synchStatus = synchStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Date createddate) {
        this.createddate = createddate;
    }

    public Date getChangeddate() {
        return changeddate;
    }

    public void setChangeddate(Date changeddate) {
        this.changeddate = changeddate;
    }

    @XmlTransient
    public Collection<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(Collection<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }

    @XmlTransient
    public Collection<EmployeeAward> getEmployeeAwardCollection() {
        return employeeAwardCollection;
    }

    public void setEmployeeAwardCollection(Collection<EmployeeAward> employeeAwardCollection) {
        this.employeeAwardCollection = employeeAwardCollection;
    }

    @XmlTransient
    public Collection<Section> getSectionCollection() {
        return sectionCollection;
    }

    public void setSectionCollection(Collection<Section> sectionCollection) {
        this.sectionCollection = sectionCollection;
    }

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @XmlTransient
    public Collection<PrincipalTerminalExamComment> getPrincipalTerminalExamCommentCollection() {
        return principalTerminalExamCommentCollection;
    }

    public void setPrincipalTerminalExamCommentCollection(Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection) {
        this.principalTerminalExamCommentCollection = principalTerminalExamCommentCollection;
    }

    @XmlTransient
    public Collection<UtilityBillDetails> getUtilityBillDetailsCollection() {
        return utilityBillDetailsCollection;
    }

    public void setUtilityBillDetailsCollection(Collection<UtilityBillDetails> utilityBillDetailsCollection) {
        this.utilityBillDetailsCollection = utilityBillDetailsCollection;
    }

    @XmlTransient
    public Collection<ScratchCardType> getScratchCardTypeCollection() {
        return scratchCardTypeCollection;
    }

    public void setScratchCardTypeCollection(Collection<ScratchCardType> scratchCardTypeCollection) {
        this.scratchCardTypeCollection = scratchCardTypeCollection;
    }

    @XmlTransient
    public Collection<BankInstitution> getBankInstitutionCollection() {
        return bankInstitutionCollection;
    }

    public void setBankInstitutionCollection(Collection<BankInstitution> bankInstitutionCollection) {
        this.bankInstitutionCollection = bankInstitutionCollection;
    }

    @XmlTransient
    public Collection<ClassCategory> getClassCategoryCollection() {
        return classCategoryCollection;
    }

    public void setClassCategoryCollection(Collection<ClassCategory> classCategoryCollection) {
        this.classCategoryCollection = classCategoryCollection;
    }

    @XmlTransient
    public Collection<EmployeePayroll> getEmployeePayrollCollection() {
        return employeePayrollCollection;
    }

    public void setEmployeePayrollCollection(Collection<EmployeePayroll> employeePayrollCollection) {
        this.employeePayrollCollection = employeePayrollCollection;
    }

    @XmlTransient
    public Collection<BankAccountBalance> getBankAccountBalanceCollection() {
        return bankAccountBalanceCollection;
    }

    public void setBankAccountBalanceCollection(Collection<BankAccountBalance> bankAccountBalanceCollection) {
        this.bankAccountBalanceCollection = bankAccountBalanceCollection;
    }

    @XmlTransient
    public Collection<BankAccount> getBankAccountCollection() {
        return bankAccountCollection;
    }

    public void setBankAccountCollection(Collection<BankAccount> bankAccountCollection) {
        this.bankAccountCollection = bankAccountCollection;
    }

    @XmlTransient
    public Collection<SubjectAttendance> getSubjectAttendanceCollection() {
        return subjectAttendanceCollection;
    }

    public void setSubjectAttendanceCollection(Collection<SubjectAttendance> subjectAttendanceCollection) {
        this.subjectAttendanceCollection = subjectAttendanceCollection;
    }

    @XmlTransient
    public Collection<AccountTransaction> getAccountTransactionCollection() {
        return accountTransactionCollection;
    }

    public void setAccountTransactionCollection(Collection<AccountTransaction> accountTransactionCollection) {
        this.accountTransactionCollection = accountTransactionCollection;
    }

    @XmlTransient
    public Collection<AuditVault> getAuditVaultCollection() {
        return auditVaultCollection;
    }

    public void setAuditVaultCollection(Collection<AuditVault> auditVaultCollection) {
        this.auditVaultCollection = auditVaultCollection;
    }

    @XmlTransient
    public Collection<ScratchCardSalesOrder> getScratchCardSalesOrderCollection() {
        return scratchCardSalesOrderCollection;
    }

    public void setScratchCardSalesOrderCollection(Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection) {
        this.scratchCardSalesOrderCollection = scratchCardSalesOrderCollection;
    }

    public Company getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Company companyId) {
        this.companyId = companyId;
    }

    public BranchAddress getAddressId() {
        return addressId;
    }

    public void setAddressId(BranchAddress addressId) {
        this.addressId = addressId;
    }

    public BranchPhone getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(BranchPhone phoneId) {
        this.phoneId = phoneId;
    }

    public BranchEmail getEmailId() {
        return emailId;
    }

    public void setEmailId(BranchEmail emailId) {
        this.emailId = emailId;
    }

    public BranchFax getFaxId() {
        return faxId;
    }

    public void setFaxId(BranchFax faxId) {
        this.faxId = faxId;
    }

    @XmlTransient
    public Collection<BranchFax> getBranchFaxCollection() {
        return branchFaxCollection;
    }

    public void setBranchFaxCollection(Collection<BranchFax> branchFaxCollection) {
        this.branchFaxCollection = branchFaxCollection;
    }

    @XmlTransient
    public Collection<FormMasterTerminalExamComment> getFormMasterTerminalExamCommentCollection() {
        return formMasterTerminalExamCommentCollection;
    }

    public void setFormMasterTerminalExamCommentCollection(Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection) {
        this.formMasterTerminalExamCommentCollection = formMasterTerminalExamCommentCollection;
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
    public Collection<BranchEmail> getBranchEmailCollection() {
        return branchEmailCollection;
    }

    public void setBranchEmailCollection(Collection<BranchEmail> branchEmailCollection) {
        this.branchEmailCollection = branchEmailCollection;
    }

    @XmlTransient
    public Collection<Exam> getExamCollection() {
        return examCollection;
    }

    public void setExamCollection(Collection<Exam> examCollection) {
        this.examCollection = examCollection;
    }

    @XmlTransient
    public Collection<ScratchCardSalesOrderDetails> getScratchCardSalesOrderDetailsCollection() {
        return scratchCardSalesOrderDetailsCollection;
    }

    public void setScratchCardSalesOrderDetailsCollection(Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollection) {
        this.scratchCardSalesOrderDetailsCollection = scratchCardSalesOrderDetailsCollection;
    }

    @XmlTransient
    public Collection<StudentBehavouralTrait> getStudentBehavouralTraitCollection() {
        return studentBehavouralTraitCollection;
    }

    public void setStudentBehavouralTraitCollection(Collection<StudentBehavouralTrait> studentBehavouralTraitCollection) {
        this.studentBehavouralTraitCollection = studentBehavouralTraitCollection;
    }

    @XmlTransient
    public Collection<BankAccountPrivilege> getBankAccountPrivilegeCollection() {
        return bankAccountPrivilegeCollection;
    }

    public void setBankAccountPrivilegeCollection(Collection<BankAccountPrivilege> bankAccountPrivilegeCollection) {
        this.bankAccountPrivilegeCollection = bankAccountPrivilegeCollection;
    }

    @XmlTransient
    public Collection<ClassSubjects> getClassSubjectsCollection() {
        return classSubjectsCollection;
    }

    public void setClassSubjectsCollection(Collection<ClassSubjects> classSubjectsCollection) {
        this.classSubjectsCollection = classSubjectsCollection;
    }

    @XmlTransient
    public Collection<PurchaseOrder> getPurchaseOrderCollection() {
        return purchaseOrderCollection;
    }

    public void setPurchaseOrderCollection(Collection<PurchaseOrder> purchaseOrderCollection) {
        this.purchaseOrderCollection = purchaseOrderCollection;
    }

    @XmlTransient
    public Collection<Grade> getGradeCollection() {
        return gradeCollection;
    }

    public void setGradeCollection(Collection<Grade> gradeCollection) {
        this.gradeCollection = gradeCollection;
    }

    @XmlTransient
    public Collection<BranchPhone> getBranchPhoneCollection() {
        return branchPhoneCollection;
    }

    public void setBranchPhoneCollection(Collection<BranchPhone> branchPhoneCollection) {
        this.branchPhoneCollection = branchPhoneCollection;
    }

    @XmlTransient
    public Collection<Designation> getDesignationCollection() {
        return designationCollection;
    }

    public void setDesignationCollection(Collection<Designation> designationCollection) {
        this.designationCollection = designationCollection;
    }

    @XmlTransient
    public Collection<ExamClassPosition> getExamClassPositionCollection() {
        return examClassPositionCollection;
    }

    public void setExamClassPositionCollection(Collection<ExamClassPosition> examClassPositionCollection) {
        this.examClassPositionCollection = examClassPositionCollection;
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
    public Collection<Student> getStudentCollection() {
        return studentCollection;
    }

    public void setStudentCollection(Collection<Student> studentCollection) {
        this.studentCollection = studentCollection;
    }

    @XmlTransient
    public Collection<ScratchCardUnitPrice> getScratchCardUnitPriceCollection() {
        return scratchCardUnitPriceCollection;
    }

    public void setScratchCardUnitPriceCollection(Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollection) {
        this.scratchCardUnitPriceCollection = scratchCardUnitPriceCollection;
    }

    @XmlTransient
    public Collection<AcademicYears> getAcademicYearsCollection() {
        return academicYearsCollection;
    }

    public void setAcademicYearsCollection(Collection<AcademicYears> academicYearsCollection) {
        this.academicYearsCollection = academicYearsCollection;
    }

    @XmlTransient
    public Collection<StudentDailyAttendance> getStudentDailyAttendanceCollection() {
        return studentDailyAttendanceCollection;
    }

    public void setStudentDailyAttendanceCollection(Collection<StudentDailyAttendance> studentDailyAttendanceCollection) {
        this.studentDailyAttendanceCollection = studentDailyAttendanceCollection;
    }

    @XmlTransient
    public Collection<ClassRoutine> getClassRoutineCollection() {
        return classRoutineCollection;
    }

    public void setClassRoutineCollection(Collection<ClassRoutine> classRoutineCollection) {
        this.classRoutineCollection = classRoutineCollection;
    }

    @XmlTransient
    public Collection<ExamMark> getExamMarkCollection() {
        return examMarkCollection;
    }

    public void setExamMarkCollection(Collection<ExamMark> examMarkCollection) {
        this.examMarkCollection = examMarkCollection;
    }

    @XmlTransient
    public Collection<Term> getTermCollection() {
        return termCollection;
    }

    public void setTermCollection(Collection<Term> termCollection) {
        this.termCollection = termCollection;
    }

    @XmlTransient
    public Collection<Department> getDepartmentCollection() {
        return departmentCollection;
    }

    public void setDepartmentCollection(Collection<Department> departmentCollection) {
        this.departmentCollection = departmentCollection;
    }

    @XmlTransient
    public Collection<Guardian> getGuardianCollection() {
        return guardianCollection;
    }

    public void setGuardianCollection(Collection<Guardian> guardianCollection) {
        this.guardianCollection = guardianCollection;
    }

    @XmlTransient
    public Collection<Class> getClassCollection() {
        return classCollection;
    }

    public void setClassCollection(Collection<Class> classCollection) {
        this.classCollection = classCollection;
    }

    @XmlTransient
    public Collection<ScratchCard> getScratchCardCollection() {
        return scratchCardCollection;
    }

    public void setScratchCardCollection(Collection<ScratchCard> scratchCardCollection) {
        this.scratchCardCollection = scratchCardCollection;
    }

    @XmlTransient
    public Collection<BehavouralTrait> getBehavouralTraitCollection() {
        return behavouralTraitCollection;
    }

    public void setBehavouralTraitCollection(Collection<BehavouralTrait> behavouralTraitCollection) {
        this.behavouralTraitCollection = behavouralTraitCollection;
    }

    @XmlTransient
    public Collection<CaMark> getCaMarkCollection() {
        return caMarkCollection;
    }

    public void setCaMarkCollection(Collection<CaMark> caMarkCollection) {
        this.caMarkCollection = caMarkCollection;
    }

    @XmlTransient
    public Collection<BranchAddress> getBranchAddressCollection() {
        return branchAddressCollection;
    }

    public void setBranchAddressCollection(Collection<BranchAddress> branchAddressCollection) {
        this.branchAddressCollection = branchAddressCollection;
    }

    @XmlTransient
    public Collection<MiscellaneousExpense> getMiscellaneousExpenseCollection() {
        return miscellaneousExpenseCollection;
    }

    public void setMiscellaneousExpenseCollection(Collection<MiscellaneousExpense> miscellaneousExpenseCollection) {
        this.miscellaneousExpenseCollection = miscellaneousExpenseCollection;
    }

    @XmlTransient
    public Collection<Invoice> getInvoiceCollection() {
        return invoiceCollection;
    }

    public void setInvoiceCollection(Collection<Invoice> invoiceCollection) {
        this.invoiceCollection = invoiceCollection;
    }

    @XmlTransient
    public Collection<BehavouralTraitRatingKeys> getBehavouralTraitRatingKeysCollection() {
        return behavouralTraitRatingKeysCollection;
    }

    public void setBehavouralTraitRatingKeysCollection(Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollection) {
        this.behavouralTraitRatingKeysCollection = behavouralTraitRatingKeysCollection;
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
        hash += (branchId != null ? branchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompanyBranch)) {
            return false;
        }
        CompanyBranch other = (CompanyBranch) object;
        if ((this.branchId == null && other.branchId != null) || (this.branchId != null && !this.branchId.equals(other.branchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sivotek.school_management_system.entities.CompanyBranch[ branchId=" + branchId + " ]";
    }
    
}
