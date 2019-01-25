/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.Company;
import com.sivotek.school_management_system.entities.BranchAddress;
import com.sivotek.school_management_system.entities.BranchPhone;
import com.sivotek.school_management_system.entities.BranchEmail;
import com.sivotek.school_management_system.entities.BranchFax;
import com.sivotek.school_management_system.entities.Subject;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.EmployeeAward;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.PrincipalTerminalExamComment;
import com.sivotek.school_management_system.entities.UtilityBillDetails;
import com.sivotek.school_management_system.entities.ScratchCardType;
import com.sivotek.school_management_system.entities.BankInstitution;
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.EmployeePayroll;
import com.sivotek.school_management_system.entities.BankAccountBalance;
import com.sivotek.school_management_system.entities.BankAccount;
import com.sivotek.school_management_system.entities.SubjectAttendance;
import com.sivotek.school_management_system.entities.AccountTransaction;
import com.sivotek.school_management_system.entities.AuditVault;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrder;
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
import com.sivotek.school_management_system.entities.TransactionCancellation;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrderDetails;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
import com.sivotek.school_management_system.entities.BankAccountPrivilege;
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.PurchaseOrder;
import com.sivotek.school_management_system.entities.Grade;
import com.sivotek.school_management_system.entities.Designation;
import com.sivotek.school_management_system.entities.ExamClassPosition;
import com.sivotek.school_management_system.entities.EmployeeBank;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.ScratchCardUnitPrice;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Department;
import com.sivotek.school_management_system.entities.Guardian;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.ScratchCard;
import com.sivotek.school_management_system.entities.BehavouralTrait;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.MiscellaneousExpense;
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.BehavouralTraitRatingKeys;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.EmployeeAttendance;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author acer
 */
public class CompanyBranchJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(CompanyBranchJpaController.class.getName());
      
    public CompanyBranchJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CompanyBranchJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(CompanyBranch companyBranch) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (companyBranch.getSubjectCollection() == null) {
            companyBranch.setSubjectCollection(new ArrayList<Subject>());
        }
        if (companyBranch.getEmployeeAwardCollection() == null) {
            companyBranch.setEmployeeAwardCollection(new ArrayList<EmployeeAward>());
        }
        if (companyBranch.getSectionCollection() == null) {
            companyBranch.setSectionCollection(new ArrayList<Section>());
        }
        if (companyBranch.getEmployeeCollection() == null) {
            companyBranch.setEmployeeCollection(new ArrayList<Employee>());
        }
        if (companyBranch.getPrincipalTerminalExamCommentCollection() == null) {
            companyBranch.setPrincipalTerminalExamCommentCollection(new ArrayList<PrincipalTerminalExamComment>());
        }
        if (companyBranch.getUtilityBillDetailsCollection() == null) {
            companyBranch.setUtilityBillDetailsCollection(new ArrayList<UtilityBillDetails>());
        }
        if (companyBranch.getScratchCardTypeCollection() == null) {
            companyBranch.setScratchCardTypeCollection(new ArrayList<ScratchCardType>());
        }
        if (companyBranch.getBankInstitutionCollection() == null) {
            companyBranch.setBankInstitutionCollection(new ArrayList<BankInstitution>());
        }
        if (companyBranch.getClassCategoryCollection() == null) {
            companyBranch.setClassCategoryCollection(new ArrayList<ClassCategory>());
        }
        if (companyBranch.getEmployeePayrollCollection() == null) {
            companyBranch.setEmployeePayrollCollection(new ArrayList<EmployeePayroll>());
        }
        if (companyBranch.getBankAccountBalanceCollection() == null) {
            companyBranch.setBankAccountBalanceCollection(new ArrayList<BankAccountBalance>());
        }
        if (companyBranch.getBankAccountCollection() == null) {
            companyBranch.setBankAccountCollection(new ArrayList<BankAccount>());
        }
        if (companyBranch.getSubjectAttendanceCollection() == null) {
            companyBranch.setSubjectAttendanceCollection(new ArrayList<SubjectAttendance>());
        }
        if (companyBranch.getAccountTransactionCollection() == null) {
            companyBranch.setAccountTransactionCollection(new ArrayList<AccountTransaction>());
        }
        if (companyBranch.getAuditVaultCollection() == null) {
            companyBranch.setAuditVaultCollection(new ArrayList<AuditVault>());
        }
        if (companyBranch.getScratchCardSalesOrderCollection() == null) {
            companyBranch.setScratchCardSalesOrderCollection(new ArrayList<ScratchCardSalesOrder>());
        }
        if (companyBranch.getBranchFaxCollection() == null) {
            companyBranch.setBranchFaxCollection(new ArrayList<BranchFax>());
        }
        if (companyBranch.getFormMasterTerminalExamCommentCollection() == null) {
            companyBranch.setFormMasterTerminalExamCommentCollection(new ArrayList<FormMasterTerminalExamComment>());
        }
        if (companyBranch.getTransactionCancellationCollection() == null) {
            companyBranch.setTransactionCancellationCollection(new ArrayList<TransactionCancellation>());
        }
        if (companyBranch.getUsersCollection() == null) {
            companyBranch.setUsersCollection(new ArrayList<Users>());
        }
        if (companyBranch.getBranchEmailCollection() == null) {
            companyBranch.setBranchEmailCollection(new ArrayList<BranchEmail>());
        }
        if (companyBranch.getExamCollection() == null) {
            companyBranch.setExamCollection(new ArrayList<Exam>());
        }
        if (companyBranch.getScratchCardSalesOrderDetailsCollection() == null) {
            companyBranch.setScratchCardSalesOrderDetailsCollection(new ArrayList<ScratchCardSalesOrderDetails>());
        }
        if (companyBranch.getStudentBehavouralTraitCollection() == null) {
            companyBranch.setStudentBehavouralTraitCollection(new ArrayList<StudentBehavouralTrait>());
        }
        if (companyBranch.getBankAccountPrivilegeCollection() == null) {
            companyBranch.setBankAccountPrivilegeCollection(new ArrayList<BankAccountPrivilege>());
        }
        if (companyBranch.getClassSubjectsCollection() == null) {
            companyBranch.setClassSubjectsCollection(new ArrayList<ClassSubjects>());
        }
        if (companyBranch.getPurchaseOrderCollection() == null) {
            companyBranch.setPurchaseOrderCollection(new ArrayList<PurchaseOrder>());
        }
        if (companyBranch.getGradeCollection() == null) {
            companyBranch.setGradeCollection(new ArrayList<Grade>());
        }
        if (companyBranch.getBranchPhoneCollection() == null) {
            companyBranch.setBranchPhoneCollection(new ArrayList<BranchPhone>());
        }
        if (companyBranch.getDesignationCollection() == null) {
            companyBranch.setDesignationCollection(new ArrayList<Designation>());
        }
        if (companyBranch.getExamClassPositionCollection() == null) {
            companyBranch.setExamClassPositionCollection(new ArrayList<ExamClassPosition>());
        }
        if (companyBranch.getEmployeeBankCollection() == null) {
            companyBranch.setEmployeeBankCollection(new ArrayList<EmployeeBank>());
        }
        if (companyBranch.getSchoolFeeInvoiceDetailsCollection() == null) {
            companyBranch.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (companyBranch.getStudentCollection() == null) {
            companyBranch.setStudentCollection(new ArrayList<Student>());
        }
        if (companyBranch.getScratchCardUnitPriceCollection() == null) {
            companyBranch.setScratchCardUnitPriceCollection(new ArrayList<ScratchCardUnitPrice>());
        }
        if (companyBranch.getAcademicYearsCollection() == null) {
            companyBranch.setAcademicYearsCollection(new ArrayList<AcademicYears>());
        }
        if (companyBranch.getStudentDailyAttendanceCollection() == null) {
            companyBranch.setStudentDailyAttendanceCollection(new ArrayList<StudentDailyAttendance>());
        }
        if (companyBranch.getClassRoutineCollection() == null) {
            companyBranch.setClassRoutineCollection(new ArrayList<ClassRoutine>());
        }
        if (companyBranch.getExamMarkCollection() == null) {
            companyBranch.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (companyBranch.getTermCollection() == null) {
            companyBranch.setTermCollection(new ArrayList<Term>());
        }
        if (companyBranch.getDepartmentCollection() == null) {
            companyBranch.setDepartmentCollection(new ArrayList<Department>());
        }
        if (companyBranch.getGuardianCollection() == null) {
            companyBranch.setGuardianCollection(new ArrayList<Guardian>());
        }
        if (companyBranch.getClassCollection() == null) {
            companyBranch.setClassCollection(new ArrayList<Class>());
        }
        if (companyBranch.getScratchCardCollection() == null) {
            companyBranch.setScratchCardCollection(new ArrayList<ScratchCard>());
        }
        if (companyBranch.getBehavouralTraitCollection() == null) {
            companyBranch.setBehavouralTraitCollection(new ArrayList<BehavouralTrait>());
        }
        if (companyBranch.getCaMarkCollection() == null) {
            companyBranch.setCaMarkCollection(new ArrayList<CaMark>());
        }
        if (companyBranch.getBranchAddressCollection() == null) {
            companyBranch.setBranchAddressCollection(new ArrayList<BranchAddress>());
        }
        if (companyBranch.getMiscellaneousExpenseCollection() == null) {
            companyBranch.setMiscellaneousExpenseCollection(new ArrayList<MiscellaneousExpense>());
        }
        if (companyBranch.getInvoiceCollection() == null) {
            companyBranch.setInvoiceCollection(new ArrayList<Invoice>());
        }
        if (companyBranch.getBehavouralTraitRatingKeysCollection() == null) {
            companyBranch.setBehavouralTraitRatingKeysCollection(new ArrayList<BehavouralTraitRatingKeys>());
        }
        if (companyBranch.getEmployeeAttendanceCollection() == null) {
            companyBranch.setEmployeeAttendanceCollection(new ArrayList<EmployeeAttendance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Company companyId = companyBranch.getCompanyId();
            if (companyId != null) {
                companyId = em.getReference(companyId.getClass(), companyId.getCompanyId());
                companyBranch.setCompanyId(companyId);
            }
            BranchAddress addressId = companyBranch.getAddressId();
            if (addressId != null) {
                addressId = em.getReference(addressId.getClass(), addressId.getAddressId());
                companyBranch.setAddressId(addressId);
            }
            BranchPhone phoneId = companyBranch.getPhoneId();
            if (phoneId != null) {
                phoneId = em.getReference(phoneId.getClass(), phoneId.getPhoneId());
                companyBranch.setPhoneId(phoneId);
            }
            BranchEmail emailId = companyBranch.getEmailId();
            if (emailId != null) {
                emailId = em.getReference(emailId.getClass(), emailId.getEmailId());
                companyBranch.setEmailId(emailId);
            }
            BranchFax faxId = companyBranch.getFaxId();
            if (faxId != null) {
                faxId = em.getReference(faxId.getClass(), faxId.getFaxId());
                companyBranch.setFaxId(faxId);
            }
            Collection<Subject> attachedSubjectCollection = new ArrayList<Subject>();
            for (Subject subjectCollectionSubjectToAttach : companyBranch.getSubjectCollection()) {
                subjectCollectionSubjectToAttach = em.getReference(subjectCollectionSubjectToAttach.getClass(), subjectCollectionSubjectToAttach.getSubjectId());
                attachedSubjectCollection.add(subjectCollectionSubjectToAttach);
            }
            companyBranch.setSubjectCollection(attachedSubjectCollection);
            Collection<EmployeeAward> attachedEmployeeAwardCollection = new ArrayList<EmployeeAward>();
            for (EmployeeAward employeeAwardCollectionEmployeeAwardToAttach : companyBranch.getEmployeeAwardCollection()) {
                employeeAwardCollectionEmployeeAwardToAttach = em.getReference(employeeAwardCollectionEmployeeAwardToAttach.getClass(), employeeAwardCollectionEmployeeAwardToAttach.getEmployeeAwardId());
                attachedEmployeeAwardCollection.add(employeeAwardCollectionEmployeeAwardToAttach);
            }
            companyBranch.setEmployeeAwardCollection(attachedEmployeeAwardCollection);
            Collection<Section> attachedSectionCollection = new ArrayList<Section>();
            for (Section sectionCollectionSectionToAttach : companyBranch.getSectionCollection()) {
                sectionCollectionSectionToAttach = em.getReference(sectionCollectionSectionToAttach.getClass(), sectionCollectionSectionToAttach.getSectionId());
                attachedSectionCollection.add(sectionCollectionSectionToAttach);
            }
            companyBranch.setSectionCollection(attachedSectionCollection);
            Collection<Employee> attachedEmployeeCollection = new ArrayList<Employee>();
            for (Employee employeeCollectionEmployeeToAttach : companyBranch.getEmployeeCollection()) {
                employeeCollectionEmployeeToAttach = em.getReference(employeeCollectionEmployeeToAttach.getClass(), employeeCollectionEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollection.add(employeeCollectionEmployeeToAttach);
            }
            companyBranch.setEmployeeCollection(attachedEmployeeCollection);
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollection = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach : companyBranch.getPrincipalTerminalExamCommentCollection()) {
                principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollection.add(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach);
            }
            companyBranch.setPrincipalTerminalExamCommentCollection(attachedPrincipalTerminalExamCommentCollection);
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollection = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetailsToAttach : companyBranch.getUtilityBillDetailsCollection()) {
                utilityBillDetailsCollectionUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollection.add(utilityBillDetailsCollectionUtilityBillDetailsToAttach);
            }
            companyBranch.setUtilityBillDetailsCollection(attachedUtilityBillDetailsCollection);
            Collection<ScratchCardType> attachedScratchCardTypeCollection = new ArrayList<ScratchCardType>();
            for (ScratchCardType scratchCardTypeCollectionScratchCardTypeToAttach : companyBranch.getScratchCardTypeCollection()) {
                scratchCardTypeCollectionScratchCardTypeToAttach = em.getReference(scratchCardTypeCollectionScratchCardTypeToAttach.getClass(), scratchCardTypeCollectionScratchCardTypeToAttach.getCardTypeId());
                attachedScratchCardTypeCollection.add(scratchCardTypeCollectionScratchCardTypeToAttach);
            }
            companyBranch.setScratchCardTypeCollection(attachedScratchCardTypeCollection);
            Collection<BankInstitution> attachedBankInstitutionCollection = new ArrayList<BankInstitution>();
            for (BankInstitution bankInstitutionCollectionBankInstitutionToAttach : companyBranch.getBankInstitutionCollection()) {
                bankInstitutionCollectionBankInstitutionToAttach = em.getReference(bankInstitutionCollectionBankInstitutionToAttach.getClass(), bankInstitutionCollectionBankInstitutionToAttach.getId());
                attachedBankInstitutionCollection.add(bankInstitutionCollectionBankInstitutionToAttach);
            }
            companyBranch.setBankInstitutionCollection(attachedBankInstitutionCollection);
            Collection<ClassCategory> attachedClassCategoryCollection = new ArrayList<ClassCategory>();
            for (ClassCategory classCategoryCollectionClassCategoryToAttach : companyBranch.getClassCategoryCollection()) {
                classCategoryCollectionClassCategoryToAttach = em.getReference(classCategoryCollectionClassCategoryToAttach.getClass(), classCategoryCollectionClassCategoryToAttach.getCategoryId());
                attachedClassCategoryCollection.add(classCategoryCollectionClassCategoryToAttach);
            }
            companyBranch.setClassCategoryCollection(attachedClassCategoryCollection);
            Collection<EmployeePayroll> attachedEmployeePayrollCollection = new ArrayList<EmployeePayroll>();
            for (EmployeePayroll employeePayrollCollectionEmployeePayrollToAttach : companyBranch.getEmployeePayrollCollection()) {
                employeePayrollCollectionEmployeePayrollToAttach = em.getReference(employeePayrollCollectionEmployeePayrollToAttach.getClass(), employeePayrollCollectionEmployeePayrollToAttach.getPayrollId());
                attachedEmployeePayrollCollection.add(employeePayrollCollectionEmployeePayrollToAttach);
            }
            companyBranch.setEmployeePayrollCollection(attachedEmployeePayrollCollection);
            Collection<BankAccountBalance> attachedBankAccountBalanceCollection = new ArrayList<BankAccountBalance>();
            for (BankAccountBalance bankAccountBalanceCollectionBankAccountBalanceToAttach : companyBranch.getBankAccountBalanceCollection()) {
                bankAccountBalanceCollectionBankAccountBalanceToAttach = em.getReference(bankAccountBalanceCollectionBankAccountBalanceToAttach.getClass(), bankAccountBalanceCollectionBankAccountBalanceToAttach.getId());
                attachedBankAccountBalanceCollection.add(bankAccountBalanceCollectionBankAccountBalanceToAttach);
            }
            companyBranch.setBankAccountBalanceCollection(attachedBankAccountBalanceCollection);
            Collection<BankAccount> attachedBankAccountCollection = new ArrayList<BankAccount>();
            for (BankAccount bankAccountCollectionBankAccountToAttach : companyBranch.getBankAccountCollection()) {
                bankAccountCollectionBankAccountToAttach = em.getReference(bankAccountCollectionBankAccountToAttach.getClass(), bankAccountCollectionBankAccountToAttach.getId());
                attachedBankAccountCollection.add(bankAccountCollectionBankAccountToAttach);
            }
            companyBranch.setBankAccountCollection(attachedBankAccountCollection);
            Collection<SubjectAttendance> attachedSubjectAttendanceCollection = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendanceToAttach : companyBranch.getSubjectAttendanceCollection()) {
                subjectAttendanceCollectionSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollection.add(subjectAttendanceCollectionSubjectAttendanceToAttach);
            }
            companyBranch.setSubjectAttendanceCollection(attachedSubjectAttendanceCollection);
            Collection<AccountTransaction> attachedAccountTransactionCollection = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionAccountTransactionToAttach : companyBranch.getAccountTransactionCollection()) {
                accountTransactionCollectionAccountTransactionToAttach = em.getReference(accountTransactionCollectionAccountTransactionToAttach.getClass(), accountTransactionCollectionAccountTransactionToAttach.getId());
                attachedAccountTransactionCollection.add(accountTransactionCollectionAccountTransactionToAttach);
            }
            companyBranch.setAccountTransactionCollection(attachedAccountTransactionCollection);
            Collection<AuditVault> attachedAuditVaultCollection = new ArrayList<AuditVault>();
            for (AuditVault auditVaultCollectionAuditVaultToAttach : companyBranch.getAuditVaultCollection()) {
                auditVaultCollectionAuditVaultToAttach = em.getReference(auditVaultCollectionAuditVaultToAttach.getClass(), auditVaultCollectionAuditVaultToAttach.getId());
                attachedAuditVaultCollection.add(auditVaultCollectionAuditVaultToAttach);
            }
            companyBranch.setAuditVaultCollection(attachedAuditVaultCollection);
            Collection<ScratchCardSalesOrder> attachedScratchCardSalesOrderCollection = new ArrayList<ScratchCardSalesOrder>();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach : companyBranch.getScratchCardSalesOrderCollection()) {
                scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach = em.getReference(scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach.getClass(), scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach.getId());
                attachedScratchCardSalesOrderCollection.add(scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach);
            }
            companyBranch.setScratchCardSalesOrderCollection(attachedScratchCardSalesOrderCollection);
            Collection<BranchFax> attachedBranchFaxCollection = new ArrayList<BranchFax>();
            for (BranchFax branchFaxCollectionBranchFaxToAttach : companyBranch.getBranchFaxCollection()) {
                branchFaxCollectionBranchFaxToAttach = em.getReference(branchFaxCollectionBranchFaxToAttach.getClass(), branchFaxCollectionBranchFaxToAttach.getFaxId());
                attachedBranchFaxCollection.add(branchFaxCollectionBranchFaxToAttach);
            }
            companyBranch.setBranchFaxCollection(attachedBranchFaxCollection);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollection = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach : companyBranch.getFormMasterTerminalExamCommentCollection()) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollection.add(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach);
            }
            companyBranch.setFormMasterTerminalExamCommentCollection(attachedFormMasterTerminalExamCommentCollection);
            Collection<TransactionCancellation> attachedTransactionCancellationCollection = new ArrayList<TransactionCancellation>();
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellationToAttach : companyBranch.getTransactionCancellationCollection()) {
                transactionCancellationCollectionTransactionCancellationToAttach = em.getReference(transactionCancellationCollectionTransactionCancellationToAttach.getClass(), transactionCancellationCollectionTransactionCancellationToAttach.getId());
                attachedTransactionCancellationCollection.add(transactionCancellationCollectionTransactionCancellationToAttach);
            }
            companyBranch.setTransactionCancellationCollection(attachedTransactionCancellationCollection);
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : companyBranch.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getId());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            companyBranch.setUsersCollection(attachedUsersCollection);
            Collection<BranchEmail> attachedBranchEmailCollection = new ArrayList<BranchEmail>();
            for (BranchEmail branchEmailCollectionBranchEmailToAttach : companyBranch.getBranchEmailCollection()) {
                branchEmailCollectionBranchEmailToAttach = em.getReference(branchEmailCollectionBranchEmailToAttach.getClass(), branchEmailCollectionBranchEmailToAttach.getEmailId());
                attachedBranchEmailCollection.add(branchEmailCollectionBranchEmailToAttach);
            }
            companyBranch.setBranchEmailCollection(attachedBranchEmailCollection);
            Collection<Exam> attachedExamCollection = new ArrayList<Exam>();
            for (Exam examCollectionExamToAttach : companyBranch.getExamCollection()) {
                examCollectionExamToAttach = em.getReference(examCollectionExamToAttach.getClass(), examCollectionExamToAttach.getExamId());
                attachedExamCollection.add(examCollectionExamToAttach);
            }
            companyBranch.setExamCollection(attachedExamCollection);
            Collection<ScratchCardSalesOrderDetails> attachedScratchCardSalesOrderDetailsCollection = new ArrayList<ScratchCardSalesOrderDetails>();
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach : companyBranch.getScratchCardSalesOrderDetailsCollection()) {
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach = em.getReference(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach.getClass(), scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach.getId());
                attachedScratchCardSalesOrderDetailsCollection.add(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach);
            }
            companyBranch.setScratchCardSalesOrderDetailsCollection(attachedScratchCardSalesOrderDetailsCollection);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollection = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTraitToAttach : companyBranch.getStudentBehavouralTraitCollection()) {
                studentBehavouralTraitCollectionStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollection.add(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach);
            }
            companyBranch.setStudentBehavouralTraitCollection(attachedStudentBehavouralTraitCollection);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach : companyBranch.getBankAccountPrivilegeCollection()) {
                bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection.add(bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach);
            }
            companyBranch.setBankAccountPrivilegeCollection(attachedBankAccountPrivilegeCollection);
            Collection<ClassSubjects> attachedClassSubjectsCollection = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionClassSubjectsToAttach : companyBranch.getClassSubjectsCollection()) {
                classSubjectsCollectionClassSubjectsToAttach = em.getReference(classSubjectsCollectionClassSubjectsToAttach.getClass(), classSubjectsCollectionClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollection.add(classSubjectsCollectionClassSubjectsToAttach);
            }
            companyBranch.setClassSubjectsCollection(attachedClassSubjectsCollection);
            Collection<PurchaseOrder> attachedPurchaseOrderCollection = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrderToAttach : companyBranch.getPurchaseOrderCollection()) {
                purchaseOrderCollectionPurchaseOrderToAttach = em.getReference(purchaseOrderCollectionPurchaseOrderToAttach.getClass(), purchaseOrderCollectionPurchaseOrderToAttach.getId());
                attachedPurchaseOrderCollection.add(purchaseOrderCollectionPurchaseOrderToAttach);
            }
            companyBranch.setPurchaseOrderCollection(attachedPurchaseOrderCollection);
            Collection<Grade> attachedGradeCollection = new ArrayList<Grade>();
            for (Grade gradeCollectionGradeToAttach : companyBranch.getGradeCollection()) {
                gradeCollectionGradeToAttach = em.getReference(gradeCollectionGradeToAttach.getClass(), gradeCollectionGradeToAttach.getGradeId());
                attachedGradeCollection.add(gradeCollectionGradeToAttach);
            }
            companyBranch.setGradeCollection(attachedGradeCollection);
            Collection<BranchPhone> attachedBranchPhoneCollection = new ArrayList<BranchPhone>();
            for (BranchPhone branchPhoneCollectionBranchPhoneToAttach : companyBranch.getBranchPhoneCollection()) {
                branchPhoneCollectionBranchPhoneToAttach = em.getReference(branchPhoneCollectionBranchPhoneToAttach.getClass(), branchPhoneCollectionBranchPhoneToAttach.getPhoneId());
                attachedBranchPhoneCollection.add(branchPhoneCollectionBranchPhoneToAttach);
            }
            companyBranch.setBranchPhoneCollection(attachedBranchPhoneCollection);
            Collection<Designation> attachedDesignationCollection = new ArrayList<Designation>();
            for (Designation designationCollectionDesignationToAttach : companyBranch.getDesignationCollection()) {
                designationCollectionDesignationToAttach = em.getReference(designationCollectionDesignationToAttach.getClass(), designationCollectionDesignationToAttach.getDesignationId());
                attachedDesignationCollection.add(designationCollectionDesignationToAttach);
            }
            companyBranch.setDesignationCollection(attachedDesignationCollection);
            Collection<ExamClassPosition> attachedExamClassPositionCollection = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionExamClassPositionToAttach : companyBranch.getExamClassPositionCollection()) {
                examClassPositionCollectionExamClassPositionToAttach = em.getReference(examClassPositionCollectionExamClassPositionToAttach.getClass(), examClassPositionCollectionExamClassPositionToAttach.getId());
                attachedExamClassPositionCollection.add(examClassPositionCollectionExamClassPositionToAttach);
            }
            companyBranch.setExamClassPositionCollection(attachedExamClassPositionCollection);
            Collection<EmployeeBank> attachedEmployeeBankCollection = new ArrayList<EmployeeBank>();
            for (EmployeeBank employeeBankCollectionEmployeeBankToAttach : companyBranch.getEmployeeBankCollection()) {
                employeeBankCollectionEmployeeBankToAttach = em.getReference(employeeBankCollectionEmployeeBankToAttach.getClass(), employeeBankCollectionEmployeeBankToAttach.getEmployeeBankId());
                attachedEmployeeBankCollection.add(employeeBankCollectionEmployeeBankToAttach);
            }
            companyBranch.setEmployeeBankCollection(attachedEmployeeBankCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : companyBranch.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            companyBranch.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : companyBranch.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            companyBranch.setStudentCollection(attachedStudentCollection);
            Collection<ScratchCardUnitPrice> attachedScratchCardUnitPriceCollection = new ArrayList<ScratchCardUnitPrice>();
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach : companyBranch.getScratchCardUnitPriceCollection()) {
                scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach = em.getReference(scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach.getClass(), scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach.getUnitId());
                attachedScratchCardUnitPriceCollection.add(scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach);
            }
            companyBranch.setScratchCardUnitPriceCollection(attachedScratchCardUnitPriceCollection);
            Collection<AcademicYears> attachedAcademicYearsCollection = new ArrayList<AcademicYears>();
            for (AcademicYears academicYearsCollectionAcademicYearsToAttach : companyBranch.getAcademicYearsCollection()) {
                academicYearsCollectionAcademicYearsToAttach = em.getReference(academicYearsCollectionAcademicYearsToAttach.getClass(), academicYearsCollectionAcademicYearsToAttach.getYearId());
                attachedAcademicYearsCollection.add(academicYearsCollectionAcademicYearsToAttach);
            }
            companyBranch.setAcademicYearsCollection(attachedAcademicYearsCollection);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollection = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendanceToAttach : companyBranch.getStudentDailyAttendanceCollection()) {
                studentDailyAttendanceCollectionStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollection.add(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach);
            }
            companyBranch.setStudentDailyAttendanceCollection(attachedStudentDailyAttendanceCollection);
            Collection<ClassRoutine> attachedClassRoutineCollection = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionClassRoutineToAttach : companyBranch.getClassRoutineCollection()) {
                classRoutineCollectionClassRoutineToAttach = em.getReference(classRoutineCollectionClassRoutineToAttach.getClass(), classRoutineCollectionClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollection.add(classRoutineCollectionClassRoutineToAttach);
            }
            companyBranch.setClassRoutineCollection(attachedClassRoutineCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : companyBranch.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            companyBranch.setExamMarkCollection(attachedExamMarkCollection);
            Collection<Term> attachedTermCollection = new ArrayList<Term>();
            for (Term termCollectionTermToAttach : companyBranch.getTermCollection()) {
                termCollectionTermToAttach = em.getReference(termCollectionTermToAttach.getClass(), termCollectionTermToAttach.getTermId());
                attachedTermCollection.add(termCollectionTermToAttach);
            }
            companyBranch.setTermCollection(attachedTermCollection);
            Collection<Department> attachedDepartmentCollection = new ArrayList<Department>();
            for (Department departmentCollectionDepartmentToAttach : companyBranch.getDepartmentCollection()) {
                departmentCollectionDepartmentToAttach = em.getReference(departmentCollectionDepartmentToAttach.getClass(), departmentCollectionDepartmentToAttach.getDepartmentId());
                attachedDepartmentCollection.add(departmentCollectionDepartmentToAttach);
            }
            companyBranch.setDepartmentCollection(attachedDepartmentCollection);
            Collection<Guardian> attachedGuardianCollection = new ArrayList<Guardian>();
            for (Guardian guardianCollectionGuardianToAttach : companyBranch.getGuardianCollection()) {
                guardianCollectionGuardianToAttach = em.getReference(guardianCollectionGuardianToAttach.getClass(), guardianCollectionGuardianToAttach.getGuardianId());
                attachedGuardianCollection.add(guardianCollectionGuardianToAttach);
            }
            companyBranch.setGuardianCollection(attachedGuardianCollection);
            Collection<Class> attachedClassCollection = new ArrayList<Class>();
            for (Class classCollectionClassToAttach : companyBranch.getClassCollection()) {
                classCollectionClassToAttach = em.getReference(classCollectionClassToAttach.getClass(), classCollectionClassToAttach.getClassId());
                attachedClassCollection.add(classCollectionClassToAttach);
            }
            companyBranch.setClassCollection(attachedClassCollection);
            Collection<ScratchCard> attachedScratchCardCollection = new ArrayList<ScratchCard>();
            for (ScratchCard scratchCardCollectionScratchCardToAttach : companyBranch.getScratchCardCollection()) {
                scratchCardCollectionScratchCardToAttach = em.getReference(scratchCardCollectionScratchCardToAttach.getClass(), scratchCardCollectionScratchCardToAttach.getCardId());
                attachedScratchCardCollection.add(scratchCardCollectionScratchCardToAttach);
            }
            companyBranch.setScratchCardCollection(attachedScratchCardCollection);
            Collection<BehavouralTrait> attachedBehavouralTraitCollection = new ArrayList<BehavouralTrait>();
            for (BehavouralTrait behavouralTraitCollectionBehavouralTraitToAttach : companyBranch.getBehavouralTraitCollection()) {
                behavouralTraitCollectionBehavouralTraitToAttach = em.getReference(behavouralTraitCollectionBehavouralTraitToAttach.getClass(), behavouralTraitCollectionBehavouralTraitToAttach.getId());
                attachedBehavouralTraitCollection.add(behavouralTraitCollectionBehavouralTraitToAttach);
            }
            companyBranch.setBehavouralTraitCollection(attachedBehavouralTraitCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : companyBranch.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            companyBranch.setCaMarkCollection(attachedCaMarkCollection);
            Collection<BranchAddress> attachedBranchAddressCollection = new ArrayList<BranchAddress>();
            for (BranchAddress branchAddressCollectionBranchAddressToAttach : companyBranch.getBranchAddressCollection()) {
                branchAddressCollectionBranchAddressToAttach = em.getReference(branchAddressCollectionBranchAddressToAttach.getClass(), branchAddressCollectionBranchAddressToAttach.getAddressId());
                attachedBranchAddressCollection.add(branchAddressCollectionBranchAddressToAttach);
            }
            companyBranch.setBranchAddressCollection(attachedBranchAddressCollection);
            Collection<MiscellaneousExpense> attachedMiscellaneousExpenseCollection = new ArrayList<MiscellaneousExpense>();
            for (MiscellaneousExpense miscellaneousExpenseCollectionMiscellaneousExpenseToAttach : companyBranch.getMiscellaneousExpenseCollection()) {
                miscellaneousExpenseCollectionMiscellaneousExpenseToAttach = em.getReference(miscellaneousExpenseCollectionMiscellaneousExpenseToAttach.getClass(), miscellaneousExpenseCollectionMiscellaneousExpenseToAttach.getId());
                attachedMiscellaneousExpenseCollection.add(miscellaneousExpenseCollectionMiscellaneousExpenseToAttach);
            }
            companyBranch.setMiscellaneousExpenseCollection(attachedMiscellaneousExpenseCollection);
            Collection<Invoice> attachedInvoiceCollection = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionInvoiceToAttach : companyBranch.getInvoiceCollection()) {
                invoiceCollectionInvoiceToAttach = em.getReference(invoiceCollectionInvoiceToAttach.getClass(), invoiceCollectionInvoiceToAttach.getId());
                attachedInvoiceCollection.add(invoiceCollectionInvoiceToAttach);
            }
            companyBranch.setInvoiceCollection(attachedInvoiceCollection);
            Collection<BehavouralTraitRatingKeys> attachedBehavouralTraitRatingKeysCollection = new ArrayList<BehavouralTraitRatingKeys>();
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach : companyBranch.getBehavouralTraitRatingKeysCollection()) {
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach = em.getReference(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach.getClass(), behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach.getId());
                attachedBehavouralTraitRatingKeysCollection.add(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach);
            }
            companyBranch.setBehavouralTraitRatingKeysCollection(attachedBehavouralTraitRatingKeysCollection);
            Collection<EmployeeAttendance> attachedEmployeeAttendanceCollection = new ArrayList<EmployeeAttendance>();
            for (EmployeeAttendance employeeAttendanceCollectionEmployeeAttendanceToAttach : companyBranch.getEmployeeAttendanceCollection()) {
                employeeAttendanceCollectionEmployeeAttendanceToAttach = em.getReference(employeeAttendanceCollectionEmployeeAttendanceToAttach.getClass(), employeeAttendanceCollectionEmployeeAttendanceToAttach.getAttendanceId());
                attachedEmployeeAttendanceCollection.add(employeeAttendanceCollectionEmployeeAttendanceToAttach);
            }
            companyBranch.setEmployeeAttendanceCollection(attachedEmployeeAttendanceCollection);
            em.persist(companyBranch);
            if (companyId != null) {
                companyId.getCompanyBranchCollection().add(companyBranch);
                companyId = em.merge(companyId);
            }
            if (addressId != null) {
                addressId.getCompanyBranchCollection().add(companyBranch);
                addressId = em.merge(addressId);
            }
            if (phoneId != null) {
                phoneId.getCompanyBranchCollection().add(companyBranch);
                phoneId = em.merge(phoneId);
            }
            if (emailId != null) {
                emailId.getCompanyBranchCollection().add(companyBranch);
                emailId = em.merge(emailId);
            }
            if (faxId != null) {
                faxId.getCompanyBranchCollection().add(companyBranch);
                faxId = em.merge(faxId);
            }
            for (Subject subjectCollectionSubject : companyBranch.getSubjectCollection()) {
                CompanyBranch oldBranchIdOfSubjectCollectionSubject = subjectCollectionSubject.getBranchId();
                subjectCollectionSubject.setBranchId(companyBranch);
                subjectCollectionSubject = em.merge(subjectCollectionSubject);
                if (oldBranchIdOfSubjectCollectionSubject != null) {
                    oldBranchIdOfSubjectCollectionSubject.getSubjectCollection().remove(subjectCollectionSubject);
                    oldBranchIdOfSubjectCollectionSubject = em.merge(oldBranchIdOfSubjectCollectionSubject);
                }
            }
            for (EmployeeAward employeeAwardCollectionEmployeeAward : companyBranch.getEmployeeAwardCollection()) {
                CompanyBranch oldBranchIdOfEmployeeAwardCollectionEmployeeAward = employeeAwardCollectionEmployeeAward.getBranchId();
                employeeAwardCollectionEmployeeAward.setBranchId(companyBranch);
                employeeAwardCollectionEmployeeAward = em.merge(employeeAwardCollectionEmployeeAward);
                if (oldBranchIdOfEmployeeAwardCollectionEmployeeAward != null) {
                    oldBranchIdOfEmployeeAwardCollectionEmployeeAward.getEmployeeAwardCollection().remove(employeeAwardCollectionEmployeeAward);
                    oldBranchIdOfEmployeeAwardCollectionEmployeeAward = em.merge(oldBranchIdOfEmployeeAwardCollectionEmployeeAward);
                }
            }
            for (Section sectionCollectionSection : companyBranch.getSectionCollection()) {
                CompanyBranch oldBranchIdOfSectionCollectionSection = sectionCollectionSection.getBranchId();
                sectionCollectionSection.setBranchId(companyBranch);
                sectionCollectionSection = em.merge(sectionCollectionSection);
                if (oldBranchIdOfSectionCollectionSection != null) {
                    oldBranchIdOfSectionCollectionSection.getSectionCollection().remove(sectionCollectionSection);
                    oldBranchIdOfSectionCollectionSection = em.merge(oldBranchIdOfSectionCollectionSection);
                }
            }
            for (Employee employeeCollectionEmployee : companyBranch.getEmployeeCollection()) {
                CompanyBranch oldBranchIdOfEmployeeCollectionEmployee = employeeCollectionEmployee.getBranchId();
                employeeCollectionEmployee.setBranchId(companyBranch);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
                if (oldBranchIdOfEmployeeCollectionEmployee != null) {
                    oldBranchIdOfEmployeeCollectionEmployee.getEmployeeCollection().remove(employeeCollectionEmployee);
                    oldBranchIdOfEmployeeCollectionEmployee = em.merge(oldBranchIdOfEmployeeCollectionEmployee);
                }
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamComment : companyBranch.getPrincipalTerminalExamCommentCollection()) {
                CompanyBranch oldBranchIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = principalTerminalExamCommentCollectionPrincipalTerminalExamComment.getBranchId();
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment.setBranchId(companyBranch);
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                if (oldBranchIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment != null) {
                    oldBranchIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                    oldBranchIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(oldBranchIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                }
            }
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : companyBranch.getUtilityBillDetailsCollection()) {
                CompanyBranch oldBranchIdOfUtilityBillDetailsCollectionUtilityBillDetails = utilityBillDetailsCollectionUtilityBillDetails.getBranchId();
                utilityBillDetailsCollectionUtilityBillDetails.setBranchId(companyBranch);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
                if (oldBranchIdOfUtilityBillDetailsCollectionUtilityBillDetails != null) {
                    oldBranchIdOfUtilityBillDetailsCollectionUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionUtilityBillDetails);
                    oldBranchIdOfUtilityBillDetailsCollectionUtilityBillDetails = em.merge(oldBranchIdOfUtilityBillDetailsCollectionUtilityBillDetails);
                }
            }
            for (ScratchCardType scratchCardTypeCollectionScratchCardType : companyBranch.getScratchCardTypeCollection()) {
                CompanyBranch oldBranchIdOfScratchCardTypeCollectionScratchCardType = scratchCardTypeCollectionScratchCardType.getBranchId();
                scratchCardTypeCollectionScratchCardType.setBranchId(companyBranch);
                scratchCardTypeCollectionScratchCardType = em.merge(scratchCardTypeCollectionScratchCardType);
                if (oldBranchIdOfScratchCardTypeCollectionScratchCardType != null) {
                    oldBranchIdOfScratchCardTypeCollectionScratchCardType.getScratchCardTypeCollection().remove(scratchCardTypeCollectionScratchCardType);
                    oldBranchIdOfScratchCardTypeCollectionScratchCardType = em.merge(oldBranchIdOfScratchCardTypeCollectionScratchCardType);
                }
            }
            for (BankInstitution bankInstitutionCollectionBankInstitution : companyBranch.getBankInstitutionCollection()) {
                CompanyBranch oldBranchIdOfBankInstitutionCollectionBankInstitution = bankInstitutionCollectionBankInstitution.getBranchId();
                bankInstitutionCollectionBankInstitution.setBranchId(companyBranch);
                bankInstitutionCollectionBankInstitution = em.merge(bankInstitutionCollectionBankInstitution);
                if (oldBranchIdOfBankInstitutionCollectionBankInstitution != null) {
                    oldBranchIdOfBankInstitutionCollectionBankInstitution.getBankInstitutionCollection().remove(bankInstitutionCollectionBankInstitution);
                    oldBranchIdOfBankInstitutionCollectionBankInstitution = em.merge(oldBranchIdOfBankInstitutionCollectionBankInstitution);
                }
            }
            for (ClassCategory classCategoryCollectionClassCategory : companyBranch.getClassCategoryCollection()) {
                CompanyBranch oldBranchIdOfClassCategoryCollectionClassCategory = classCategoryCollectionClassCategory.getBranchId();
                classCategoryCollectionClassCategory.setBranchId(companyBranch);
                classCategoryCollectionClassCategory = em.merge(classCategoryCollectionClassCategory);
                if (oldBranchIdOfClassCategoryCollectionClassCategory != null) {
                    oldBranchIdOfClassCategoryCollectionClassCategory.getClassCategoryCollection().remove(classCategoryCollectionClassCategory);
                    oldBranchIdOfClassCategoryCollectionClassCategory = em.merge(oldBranchIdOfClassCategoryCollectionClassCategory);
                }
            }
            for (EmployeePayroll employeePayrollCollectionEmployeePayroll : companyBranch.getEmployeePayrollCollection()) {
                CompanyBranch oldBranchIdOfEmployeePayrollCollectionEmployeePayroll = employeePayrollCollectionEmployeePayroll.getBranchId();
                employeePayrollCollectionEmployeePayroll.setBranchId(companyBranch);
                employeePayrollCollectionEmployeePayroll = em.merge(employeePayrollCollectionEmployeePayroll);
                if (oldBranchIdOfEmployeePayrollCollectionEmployeePayroll != null) {
                    oldBranchIdOfEmployeePayrollCollectionEmployeePayroll.getEmployeePayrollCollection().remove(employeePayrollCollectionEmployeePayroll);
                    oldBranchIdOfEmployeePayrollCollectionEmployeePayroll = em.merge(oldBranchIdOfEmployeePayrollCollectionEmployeePayroll);
                }
            }
            for (BankAccountBalance bankAccountBalanceCollectionBankAccountBalance : companyBranch.getBankAccountBalanceCollection()) {
                CompanyBranch oldBranchIdOfBankAccountBalanceCollectionBankAccountBalance = bankAccountBalanceCollectionBankAccountBalance.getBranchId();
                bankAccountBalanceCollectionBankAccountBalance.setBranchId(companyBranch);
                bankAccountBalanceCollectionBankAccountBalance = em.merge(bankAccountBalanceCollectionBankAccountBalance);
                if (oldBranchIdOfBankAccountBalanceCollectionBankAccountBalance != null) {
                    oldBranchIdOfBankAccountBalanceCollectionBankAccountBalance.getBankAccountBalanceCollection().remove(bankAccountBalanceCollectionBankAccountBalance);
                    oldBranchIdOfBankAccountBalanceCollectionBankAccountBalance = em.merge(oldBranchIdOfBankAccountBalanceCollectionBankAccountBalance);
                }
            }
            for (BankAccount bankAccountCollectionBankAccount : companyBranch.getBankAccountCollection()) {
                CompanyBranch oldBranchIdOfBankAccountCollectionBankAccount = bankAccountCollectionBankAccount.getBranchId();
                bankAccountCollectionBankAccount.setBranchId(companyBranch);
                bankAccountCollectionBankAccount = em.merge(bankAccountCollectionBankAccount);
                if (oldBranchIdOfBankAccountCollectionBankAccount != null) {
                    oldBranchIdOfBankAccountCollectionBankAccount.getBankAccountCollection().remove(bankAccountCollectionBankAccount);
                    oldBranchIdOfBankAccountCollectionBankAccount = em.merge(oldBranchIdOfBankAccountCollectionBankAccount);
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendance : companyBranch.getSubjectAttendanceCollection()) {
                CompanyBranch oldBranchIdOfSubjectAttendanceCollectionSubjectAttendance = subjectAttendanceCollectionSubjectAttendance.getBranchId();
                subjectAttendanceCollectionSubjectAttendance.setBranchId(companyBranch);
                subjectAttendanceCollectionSubjectAttendance = em.merge(subjectAttendanceCollectionSubjectAttendance);
                if (oldBranchIdOfSubjectAttendanceCollectionSubjectAttendance != null) {
                    oldBranchIdOfSubjectAttendanceCollectionSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionSubjectAttendance);
                    oldBranchIdOfSubjectAttendanceCollectionSubjectAttendance = em.merge(oldBranchIdOfSubjectAttendanceCollectionSubjectAttendance);
                }
            }
            for (AccountTransaction accountTransactionCollectionAccountTransaction : companyBranch.getAccountTransactionCollection()) {
                CompanyBranch oldBranchIdOfAccountTransactionCollectionAccountTransaction = accountTransactionCollectionAccountTransaction.getBranchId();
                accountTransactionCollectionAccountTransaction.setBranchId(companyBranch);
                accountTransactionCollectionAccountTransaction = em.merge(accountTransactionCollectionAccountTransaction);
                if (oldBranchIdOfAccountTransactionCollectionAccountTransaction != null) {
                    oldBranchIdOfAccountTransactionCollectionAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionAccountTransaction);
                    oldBranchIdOfAccountTransactionCollectionAccountTransaction = em.merge(oldBranchIdOfAccountTransactionCollectionAccountTransaction);
                }
            }
            for (AuditVault auditVaultCollectionAuditVault : companyBranch.getAuditVaultCollection()) {
                CompanyBranch oldBranchIdOfAuditVaultCollectionAuditVault = auditVaultCollectionAuditVault.getBranchId();
                auditVaultCollectionAuditVault.setBranchId(companyBranch);
                auditVaultCollectionAuditVault = em.merge(auditVaultCollectionAuditVault);
                if (oldBranchIdOfAuditVaultCollectionAuditVault != null) {
                    oldBranchIdOfAuditVaultCollectionAuditVault.getAuditVaultCollection().remove(auditVaultCollectionAuditVault);
                    oldBranchIdOfAuditVaultCollectionAuditVault = em.merge(oldBranchIdOfAuditVaultCollectionAuditVault);
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrder : companyBranch.getScratchCardSalesOrderCollection()) {
                CompanyBranch oldBranchIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder = scratchCardSalesOrderCollectionScratchCardSalesOrder.getBranchId();
                scratchCardSalesOrderCollectionScratchCardSalesOrder.setBranchId(companyBranch);
                scratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionScratchCardSalesOrder);
                if (oldBranchIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder != null) {
                    oldBranchIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrderCollectionScratchCardSalesOrder);
                    oldBranchIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(oldBranchIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder);
                }
            }
            for (BranchFax branchFaxCollectionBranchFax : companyBranch.getBranchFaxCollection()) {
                CompanyBranch oldBranchIdOfBranchFaxCollectionBranchFax = branchFaxCollectionBranchFax.getBranchId();
                branchFaxCollectionBranchFax.setBranchId(companyBranch);
                branchFaxCollectionBranchFax = em.merge(branchFaxCollectionBranchFax);
                if (oldBranchIdOfBranchFaxCollectionBranchFax != null) {
                    oldBranchIdOfBranchFaxCollectionBranchFax.getBranchFaxCollection().remove(branchFaxCollectionBranchFax);
                    oldBranchIdOfBranchFaxCollectionBranchFax = em.merge(oldBranchIdOfBranchFaxCollectionBranchFax);
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : companyBranch.getFormMasterTerminalExamCommentCollection()) {
                CompanyBranch oldBranchIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getBranchId();
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setBranchId(companyBranch);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                if (oldBranchIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment != null) {
                    oldBranchIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                    oldBranchIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(oldBranchIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                }
            }
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellation : companyBranch.getTransactionCancellationCollection()) {
                CompanyBranch oldBranchIdOfTransactionCancellationCollectionTransactionCancellation = transactionCancellationCollectionTransactionCancellation.getBranchId();
                transactionCancellationCollectionTransactionCancellation.setBranchId(companyBranch);
                transactionCancellationCollectionTransactionCancellation = em.merge(transactionCancellationCollectionTransactionCancellation);
                if (oldBranchIdOfTransactionCancellationCollectionTransactionCancellation != null) {
                    oldBranchIdOfTransactionCancellationCollectionTransactionCancellation.getTransactionCancellationCollection().remove(transactionCancellationCollectionTransactionCancellation);
                    oldBranchIdOfTransactionCancellationCollectionTransactionCancellation = em.merge(oldBranchIdOfTransactionCancellationCollectionTransactionCancellation);
                }
            }
            for (Users usersCollectionUsers : companyBranch.getUsersCollection()) {
                CompanyBranch oldBranchIdOfUsersCollectionUsers = usersCollectionUsers.getBranchId();
                usersCollectionUsers.setBranchId(companyBranch);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldBranchIdOfUsersCollectionUsers != null) {
                    oldBranchIdOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldBranchIdOfUsersCollectionUsers = em.merge(oldBranchIdOfUsersCollectionUsers);
                }
            }
            for (BranchEmail branchEmailCollectionBranchEmail : companyBranch.getBranchEmailCollection()) {
                CompanyBranch oldBranchIdOfBranchEmailCollectionBranchEmail = branchEmailCollectionBranchEmail.getBranchId();
                branchEmailCollectionBranchEmail.setBranchId(companyBranch);
                branchEmailCollectionBranchEmail = em.merge(branchEmailCollectionBranchEmail);
                if (oldBranchIdOfBranchEmailCollectionBranchEmail != null) {
                    oldBranchIdOfBranchEmailCollectionBranchEmail.getBranchEmailCollection().remove(branchEmailCollectionBranchEmail);
                    oldBranchIdOfBranchEmailCollectionBranchEmail = em.merge(oldBranchIdOfBranchEmailCollectionBranchEmail);
                }
            }
            for (Exam examCollectionExam : companyBranch.getExamCollection()) {
                CompanyBranch oldBranchIdOfExamCollectionExam = examCollectionExam.getBranchId();
                examCollectionExam.setBranchId(companyBranch);
                examCollectionExam = em.merge(examCollectionExam);
                if (oldBranchIdOfExamCollectionExam != null) {
                    oldBranchIdOfExamCollectionExam.getExamCollection().remove(examCollectionExam);
                    oldBranchIdOfExamCollectionExam = em.merge(oldBranchIdOfExamCollectionExam);
                }
            }
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails : companyBranch.getScratchCardSalesOrderDetailsCollection()) {
                CompanyBranch oldBranchIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.getBranchId();
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.setBranchId(companyBranch);
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
                if (oldBranchIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails != null) {
                    oldBranchIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
                    oldBranchIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = em.merge(oldBranchIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : companyBranch.getStudentBehavouralTraitCollection()) {
                CompanyBranch oldBranchIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = studentBehavouralTraitCollectionStudentBehavouralTrait.getBranchId();
                studentBehavouralTraitCollectionStudentBehavouralTrait.setBranchId(companyBranch);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
                if (oldBranchIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait != null) {
                    oldBranchIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionStudentBehavouralTrait);
                    oldBranchIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(oldBranchIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilege : companyBranch.getBankAccountPrivilegeCollection()) {
                CompanyBranch oldBranchIdOfBankAccountPrivilegeCollectionBankAccountPrivilege = bankAccountPrivilegeCollectionBankAccountPrivilege.getBranchId();
                bankAccountPrivilegeCollectionBankAccountPrivilege.setBranchId(companyBranch);
                bankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionBankAccountPrivilege);
                if (oldBranchIdOfBankAccountPrivilegeCollectionBankAccountPrivilege != null) {
                    oldBranchIdOfBankAccountPrivilegeCollectionBankAccountPrivilege.getBankAccountPrivilegeCollection().remove(bankAccountPrivilegeCollectionBankAccountPrivilege);
                    oldBranchIdOfBankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(oldBranchIdOfBankAccountPrivilegeCollectionBankAccountPrivilege);
                }
            }
            for (ClassSubjects classSubjectsCollectionClassSubjects : companyBranch.getClassSubjectsCollection()) {
                CompanyBranch oldBranchIdOfClassSubjectsCollectionClassSubjects = classSubjectsCollectionClassSubjects.getBranchId();
                classSubjectsCollectionClassSubjects.setBranchId(companyBranch);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
                if (oldBranchIdOfClassSubjectsCollectionClassSubjects != null) {
                    oldBranchIdOfClassSubjectsCollectionClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionClassSubjects);
                    oldBranchIdOfClassSubjectsCollectionClassSubjects = em.merge(oldBranchIdOfClassSubjectsCollectionClassSubjects);
                }
            }
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrder : companyBranch.getPurchaseOrderCollection()) {
                CompanyBranch oldBranchIdOfPurchaseOrderCollectionPurchaseOrder = purchaseOrderCollectionPurchaseOrder.getBranchId();
                purchaseOrderCollectionPurchaseOrder.setBranchId(companyBranch);
                purchaseOrderCollectionPurchaseOrder = em.merge(purchaseOrderCollectionPurchaseOrder);
                if (oldBranchIdOfPurchaseOrderCollectionPurchaseOrder != null) {
                    oldBranchIdOfPurchaseOrderCollectionPurchaseOrder.getPurchaseOrderCollection().remove(purchaseOrderCollectionPurchaseOrder);
                    oldBranchIdOfPurchaseOrderCollectionPurchaseOrder = em.merge(oldBranchIdOfPurchaseOrderCollectionPurchaseOrder);
                }
            }
            for (Grade gradeCollectionGrade : companyBranch.getGradeCollection()) {
                CompanyBranch oldBranchIdOfGradeCollectionGrade = gradeCollectionGrade.getBranchId();
                gradeCollectionGrade.setBranchId(companyBranch);
                gradeCollectionGrade = em.merge(gradeCollectionGrade);
                if (oldBranchIdOfGradeCollectionGrade != null) {
                    oldBranchIdOfGradeCollectionGrade.getGradeCollection().remove(gradeCollectionGrade);
                    oldBranchIdOfGradeCollectionGrade = em.merge(oldBranchIdOfGradeCollectionGrade);
                }
            }
            for (BranchPhone branchPhoneCollectionBranchPhone : companyBranch.getBranchPhoneCollection()) {
                CompanyBranch oldBranchIdOfBranchPhoneCollectionBranchPhone = branchPhoneCollectionBranchPhone.getBranchId();
                branchPhoneCollectionBranchPhone.setBranchId(companyBranch);
                branchPhoneCollectionBranchPhone = em.merge(branchPhoneCollectionBranchPhone);
                if (oldBranchIdOfBranchPhoneCollectionBranchPhone != null) {
                    oldBranchIdOfBranchPhoneCollectionBranchPhone.getBranchPhoneCollection().remove(branchPhoneCollectionBranchPhone);
                    oldBranchIdOfBranchPhoneCollectionBranchPhone = em.merge(oldBranchIdOfBranchPhoneCollectionBranchPhone);
                }
            }
            for (Designation designationCollectionDesignation : companyBranch.getDesignationCollection()) {
                CompanyBranch oldBranchIdOfDesignationCollectionDesignation = designationCollectionDesignation.getBranchId();
                designationCollectionDesignation.setBranchId(companyBranch);
                designationCollectionDesignation = em.merge(designationCollectionDesignation);
                if (oldBranchIdOfDesignationCollectionDesignation != null) {
                    oldBranchIdOfDesignationCollectionDesignation.getDesignationCollection().remove(designationCollectionDesignation);
                    oldBranchIdOfDesignationCollectionDesignation = em.merge(oldBranchIdOfDesignationCollectionDesignation);
                }
            }
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : companyBranch.getExamClassPositionCollection()) {
                CompanyBranch oldBranchIdOfExamClassPositionCollectionExamClassPosition = examClassPositionCollectionExamClassPosition.getBranchId();
                examClassPositionCollectionExamClassPosition.setBranchId(companyBranch);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
                if (oldBranchIdOfExamClassPositionCollectionExamClassPosition != null) {
                    oldBranchIdOfExamClassPositionCollectionExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionExamClassPosition);
                    oldBranchIdOfExamClassPositionCollectionExamClassPosition = em.merge(oldBranchIdOfExamClassPositionCollectionExamClassPosition);
                }
            }
            for (EmployeeBank employeeBankCollectionEmployeeBank : companyBranch.getEmployeeBankCollection()) {
                CompanyBranch oldBranchIdOfEmployeeBankCollectionEmployeeBank = employeeBankCollectionEmployeeBank.getBranchId();
                employeeBankCollectionEmployeeBank.setBranchId(companyBranch);
                employeeBankCollectionEmployeeBank = em.merge(employeeBankCollectionEmployeeBank);
                if (oldBranchIdOfEmployeeBankCollectionEmployeeBank != null) {
                    oldBranchIdOfEmployeeBankCollectionEmployeeBank.getEmployeeBankCollection().remove(employeeBankCollectionEmployeeBank);
                    oldBranchIdOfEmployeeBankCollectionEmployeeBank = em.merge(oldBranchIdOfEmployeeBankCollectionEmployeeBank);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : companyBranch.getSchoolFeeInvoiceDetailsCollection()) {
                CompanyBranch oldBranchIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getBranchId();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setBranchId(companyBranch);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldBranchIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldBranchIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldBranchIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldBranchIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            for (Student studentCollectionStudent : companyBranch.getStudentCollection()) {
                CompanyBranch oldBranchIdOfStudentCollectionStudent = studentCollectionStudent.getBranchId();
                studentCollectionStudent.setBranchId(companyBranch);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldBranchIdOfStudentCollectionStudent != null) {
                    oldBranchIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldBranchIdOfStudentCollectionStudent = em.merge(oldBranchIdOfStudentCollectionStudent);
                }
            }
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionScratchCardUnitPrice : companyBranch.getScratchCardUnitPriceCollection()) {
                CompanyBranch oldBranchIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice = scratchCardUnitPriceCollectionScratchCardUnitPrice.getBranchId();
                scratchCardUnitPriceCollectionScratchCardUnitPrice.setBranchId(companyBranch);
                scratchCardUnitPriceCollectionScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionScratchCardUnitPrice);
                if (oldBranchIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice != null) {
                    oldBranchIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice.getScratchCardUnitPriceCollection().remove(scratchCardUnitPriceCollectionScratchCardUnitPrice);
                    oldBranchIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice = em.merge(oldBranchIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice);
                }
            }
            for (AcademicYears academicYearsCollectionAcademicYears : companyBranch.getAcademicYearsCollection()) {
                CompanyBranch oldBranchIdOfAcademicYearsCollectionAcademicYears = academicYearsCollectionAcademicYears.getBranchId();
                academicYearsCollectionAcademicYears.setBranchId(companyBranch);
                academicYearsCollectionAcademicYears = em.merge(academicYearsCollectionAcademicYears);
                if (oldBranchIdOfAcademicYearsCollectionAcademicYears != null) {
                    oldBranchIdOfAcademicYearsCollectionAcademicYears.getAcademicYearsCollection().remove(academicYearsCollectionAcademicYears);
                    oldBranchIdOfAcademicYearsCollectionAcademicYears = em.merge(oldBranchIdOfAcademicYearsCollectionAcademicYears);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : companyBranch.getStudentDailyAttendanceCollection()) {
                CompanyBranch oldBranchIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = studentDailyAttendanceCollectionStudentDailyAttendance.getBranchId();
                studentDailyAttendanceCollectionStudentDailyAttendance.setBranchId(companyBranch);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
                if (oldBranchIdOfStudentDailyAttendanceCollectionStudentDailyAttendance != null) {
                    oldBranchIdOfStudentDailyAttendanceCollectionStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionStudentDailyAttendance);
                    oldBranchIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = em.merge(oldBranchIdOfStudentDailyAttendanceCollectionStudentDailyAttendance);
                }
            }
            for (ClassRoutine classRoutineCollectionClassRoutine : companyBranch.getClassRoutineCollection()) {
                CompanyBranch oldBranchIdOfClassRoutineCollectionClassRoutine = classRoutineCollectionClassRoutine.getBranchId();
                classRoutineCollectionClassRoutine.setBranchId(companyBranch);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
                if (oldBranchIdOfClassRoutineCollectionClassRoutine != null) {
                    oldBranchIdOfClassRoutineCollectionClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionClassRoutine);
                    oldBranchIdOfClassRoutineCollectionClassRoutine = em.merge(oldBranchIdOfClassRoutineCollectionClassRoutine);
                }
            }
            for (ExamMark examMarkCollectionExamMark : companyBranch.getExamMarkCollection()) {
                CompanyBranch oldBranchIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getBranchId();
                examMarkCollectionExamMark.setBranchId(companyBranch);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldBranchIdOfExamMarkCollectionExamMark != null) {
                    oldBranchIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldBranchIdOfExamMarkCollectionExamMark = em.merge(oldBranchIdOfExamMarkCollectionExamMark);
                }
            }
            for (Term termCollectionTerm : companyBranch.getTermCollection()) {
                CompanyBranch oldBranchIdOfTermCollectionTerm = termCollectionTerm.getBranchId();
                termCollectionTerm.setBranchId(companyBranch);
                termCollectionTerm = em.merge(termCollectionTerm);
                if (oldBranchIdOfTermCollectionTerm != null) {
                    oldBranchIdOfTermCollectionTerm.getTermCollection().remove(termCollectionTerm);
                    oldBranchIdOfTermCollectionTerm = em.merge(oldBranchIdOfTermCollectionTerm);
                }
            }
            for (Department departmentCollectionDepartment : companyBranch.getDepartmentCollection()) {
                CompanyBranch oldBranchIdOfDepartmentCollectionDepartment = departmentCollectionDepartment.getBranchId();
                departmentCollectionDepartment.setBranchId(companyBranch);
                departmentCollectionDepartment = em.merge(departmentCollectionDepartment);
                if (oldBranchIdOfDepartmentCollectionDepartment != null) {
                    oldBranchIdOfDepartmentCollectionDepartment.getDepartmentCollection().remove(departmentCollectionDepartment);
                    oldBranchIdOfDepartmentCollectionDepartment = em.merge(oldBranchIdOfDepartmentCollectionDepartment);
                }
            }
            for (Guardian guardianCollectionGuardian : companyBranch.getGuardianCollection()) {
                CompanyBranch oldBranchIdOfGuardianCollectionGuardian = guardianCollectionGuardian.getBranchId();
                guardianCollectionGuardian.setBranchId(companyBranch);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
                if (oldBranchIdOfGuardianCollectionGuardian != null) {
                    oldBranchIdOfGuardianCollectionGuardian.getGuardianCollection().remove(guardianCollectionGuardian);
                    oldBranchIdOfGuardianCollectionGuardian = em.merge(oldBranchIdOfGuardianCollectionGuardian);
                }
            }
            for (Class classCollectionClass : companyBranch.getClassCollection()) {
                CompanyBranch oldBranchIdOfClassCollectionClass = classCollectionClass.getBranchId();
                classCollectionClass.setBranchId(companyBranch);
                classCollectionClass = em.merge(classCollectionClass);
                if (oldBranchIdOfClassCollectionClass != null) {
                    oldBranchIdOfClassCollectionClass.getClassCollection().remove(classCollectionClass);
                    oldBranchIdOfClassCollectionClass = em.merge(oldBranchIdOfClassCollectionClass);
                }
            }
            for (ScratchCard scratchCardCollectionScratchCard : companyBranch.getScratchCardCollection()) {
                CompanyBranch oldBranchIdOfScratchCardCollectionScratchCard = scratchCardCollectionScratchCard.getBranchId();
                scratchCardCollectionScratchCard.setBranchId(companyBranch);
                scratchCardCollectionScratchCard = em.merge(scratchCardCollectionScratchCard);
                if (oldBranchIdOfScratchCardCollectionScratchCard != null) {
                    oldBranchIdOfScratchCardCollectionScratchCard.getScratchCardCollection().remove(scratchCardCollectionScratchCard);
                    oldBranchIdOfScratchCardCollectionScratchCard = em.merge(oldBranchIdOfScratchCardCollectionScratchCard);
                }
            }
            for (BehavouralTrait behavouralTraitCollectionBehavouralTrait : companyBranch.getBehavouralTraitCollection()) {
                CompanyBranch oldBranchIdOfBehavouralTraitCollectionBehavouralTrait = behavouralTraitCollectionBehavouralTrait.getBranchId();
                behavouralTraitCollectionBehavouralTrait.setBranchId(companyBranch);
                behavouralTraitCollectionBehavouralTrait = em.merge(behavouralTraitCollectionBehavouralTrait);
                if (oldBranchIdOfBehavouralTraitCollectionBehavouralTrait != null) {
                    oldBranchIdOfBehavouralTraitCollectionBehavouralTrait.getBehavouralTraitCollection().remove(behavouralTraitCollectionBehavouralTrait);
                    oldBranchIdOfBehavouralTraitCollectionBehavouralTrait = em.merge(oldBranchIdOfBehavouralTraitCollectionBehavouralTrait);
                }
            }
            for (CaMark caMarkCollectionCaMark : companyBranch.getCaMarkCollection()) {
                CompanyBranch oldBranchIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getBranchId();
                caMarkCollectionCaMark.setBranchId(companyBranch);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldBranchIdOfCaMarkCollectionCaMark != null) {
                    oldBranchIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldBranchIdOfCaMarkCollectionCaMark = em.merge(oldBranchIdOfCaMarkCollectionCaMark);
                }
            }
            for (BranchAddress branchAddressCollectionBranchAddress : companyBranch.getBranchAddressCollection()) {
                CompanyBranch oldBranchIdOfBranchAddressCollectionBranchAddress = branchAddressCollectionBranchAddress.getBranchId();
                branchAddressCollectionBranchAddress.setBranchId(companyBranch);
                branchAddressCollectionBranchAddress = em.merge(branchAddressCollectionBranchAddress);
                if (oldBranchIdOfBranchAddressCollectionBranchAddress != null) {
                    oldBranchIdOfBranchAddressCollectionBranchAddress.getBranchAddressCollection().remove(branchAddressCollectionBranchAddress);
                    oldBranchIdOfBranchAddressCollectionBranchAddress = em.merge(oldBranchIdOfBranchAddressCollectionBranchAddress);
                }
            }
            for (MiscellaneousExpense miscellaneousExpenseCollectionMiscellaneousExpense : companyBranch.getMiscellaneousExpenseCollection()) {
                CompanyBranch oldBranchIdOfMiscellaneousExpenseCollectionMiscellaneousExpense = miscellaneousExpenseCollectionMiscellaneousExpense.getBranchId();
                miscellaneousExpenseCollectionMiscellaneousExpense.setBranchId(companyBranch);
                miscellaneousExpenseCollectionMiscellaneousExpense = em.merge(miscellaneousExpenseCollectionMiscellaneousExpense);
                if (oldBranchIdOfMiscellaneousExpenseCollectionMiscellaneousExpense != null) {
                    oldBranchIdOfMiscellaneousExpenseCollectionMiscellaneousExpense.getMiscellaneousExpenseCollection().remove(miscellaneousExpenseCollectionMiscellaneousExpense);
                    oldBranchIdOfMiscellaneousExpenseCollectionMiscellaneousExpense = em.merge(oldBranchIdOfMiscellaneousExpenseCollectionMiscellaneousExpense);
                }
            }
            for (Invoice invoiceCollectionInvoice : companyBranch.getInvoiceCollection()) {
                CompanyBranch oldBranchIdOfInvoiceCollectionInvoice = invoiceCollectionInvoice.getBranchId();
                invoiceCollectionInvoice.setBranchId(companyBranch);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
                if (oldBranchIdOfInvoiceCollectionInvoice != null) {
                    oldBranchIdOfInvoiceCollectionInvoice.getInvoiceCollection().remove(invoiceCollectionInvoice);
                    oldBranchIdOfInvoiceCollectionInvoice = em.merge(oldBranchIdOfInvoiceCollectionInvoice);
                }
            }
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys : companyBranch.getBehavouralTraitRatingKeysCollection()) {
                CompanyBranch oldBranchIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.getBranchId();
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.setBranchId(companyBranch);
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
                if (oldBranchIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys != null) {
                    oldBranchIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
                    oldBranchIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = em.merge(oldBranchIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
                }
            }
            for (EmployeeAttendance employeeAttendanceCollectionEmployeeAttendance : companyBranch.getEmployeeAttendanceCollection()) {
                CompanyBranch oldBranchIdOfEmployeeAttendanceCollectionEmployeeAttendance = employeeAttendanceCollectionEmployeeAttendance.getBranchId();
                employeeAttendanceCollectionEmployeeAttendance.setBranchId(companyBranch);
                employeeAttendanceCollectionEmployeeAttendance = em.merge(employeeAttendanceCollectionEmployeeAttendance);
                if (oldBranchIdOfEmployeeAttendanceCollectionEmployeeAttendance != null) {
                    oldBranchIdOfEmployeeAttendanceCollectionEmployeeAttendance.getEmployeeAttendanceCollection().remove(employeeAttendanceCollectionEmployeeAttendance);
                    oldBranchIdOfEmployeeAttendanceCollectionEmployeeAttendance = em.merge(oldBranchIdOfEmployeeAttendanceCollectionEmployeeAttendance);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompanyBranch(companyBranch.getBranchId()) != null) {
                throw new PreexistingEntityException("CompanyBranch " + companyBranch + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CompanyBranch companyBranch) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch persistentCompanyBranch = em.find(CompanyBranch.class, companyBranch.getBranchId());
            Company companyIdOld = persistentCompanyBranch.getCompanyId();
            Company companyIdNew = companyBranch.getCompanyId();
            BranchAddress addressIdOld = persistentCompanyBranch.getAddressId();
            BranchAddress addressIdNew = companyBranch.getAddressId();
            BranchPhone phoneIdOld = persistentCompanyBranch.getPhoneId();
            BranchPhone phoneIdNew = companyBranch.getPhoneId();
            BranchEmail emailIdOld = persistentCompanyBranch.getEmailId();
            BranchEmail emailIdNew = companyBranch.getEmailId();
            BranchFax faxIdOld = persistentCompanyBranch.getFaxId();
            BranchFax faxIdNew = companyBranch.getFaxId();
            Collection<Subject> subjectCollectionOld = persistentCompanyBranch.getSubjectCollection();
            Collection<Subject> subjectCollectionNew = companyBranch.getSubjectCollection();
            Collection<EmployeeAward> employeeAwardCollectionOld = persistentCompanyBranch.getEmployeeAwardCollection();
            Collection<EmployeeAward> employeeAwardCollectionNew = companyBranch.getEmployeeAwardCollection();
            Collection<Section> sectionCollectionOld = persistentCompanyBranch.getSectionCollection();
            Collection<Section> sectionCollectionNew = companyBranch.getSectionCollection();
            Collection<Employee> employeeCollectionOld = persistentCompanyBranch.getEmployeeCollection();
            Collection<Employee> employeeCollectionNew = companyBranch.getEmployeeCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOld = persistentCompanyBranch.getPrincipalTerminalExamCommentCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionNew = companyBranch.getPrincipalTerminalExamCommentCollection();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionOld = persistentCompanyBranch.getUtilityBillDetailsCollection();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionNew = companyBranch.getUtilityBillDetailsCollection();
            Collection<ScratchCardType> scratchCardTypeCollectionOld = persistentCompanyBranch.getScratchCardTypeCollection();
            Collection<ScratchCardType> scratchCardTypeCollectionNew = companyBranch.getScratchCardTypeCollection();
            Collection<BankInstitution> bankInstitutionCollectionOld = persistentCompanyBranch.getBankInstitutionCollection();
            Collection<BankInstitution> bankInstitutionCollectionNew = companyBranch.getBankInstitutionCollection();
            Collection<ClassCategory> classCategoryCollectionOld = persistentCompanyBranch.getClassCategoryCollection();
            Collection<ClassCategory> classCategoryCollectionNew = companyBranch.getClassCategoryCollection();
            Collection<EmployeePayroll> employeePayrollCollectionOld = persistentCompanyBranch.getEmployeePayrollCollection();
            Collection<EmployeePayroll> employeePayrollCollectionNew = companyBranch.getEmployeePayrollCollection();
            Collection<BankAccountBalance> bankAccountBalanceCollectionOld = persistentCompanyBranch.getBankAccountBalanceCollection();
            Collection<BankAccountBalance> bankAccountBalanceCollectionNew = companyBranch.getBankAccountBalanceCollection();
            Collection<BankAccount> bankAccountCollectionOld = persistentCompanyBranch.getBankAccountCollection();
            Collection<BankAccount> bankAccountCollectionNew = companyBranch.getBankAccountCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionOld = persistentCompanyBranch.getSubjectAttendanceCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionNew = companyBranch.getSubjectAttendanceCollection();
            Collection<AccountTransaction> accountTransactionCollectionOld = persistentCompanyBranch.getAccountTransactionCollection();
            Collection<AccountTransaction> accountTransactionCollectionNew = companyBranch.getAccountTransactionCollection();
            Collection<AuditVault> auditVaultCollectionOld = persistentCompanyBranch.getAuditVaultCollection();
            Collection<AuditVault> auditVaultCollectionNew = companyBranch.getAuditVaultCollection();
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionOld = persistentCompanyBranch.getScratchCardSalesOrderCollection();
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionNew = companyBranch.getScratchCardSalesOrderCollection();
            Collection<BranchFax> branchFaxCollectionOld = persistentCompanyBranch.getBranchFaxCollection();
            Collection<BranchFax> branchFaxCollectionNew = companyBranch.getBranchFaxCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOld = persistentCompanyBranch.getFormMasterTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionNew = companyBranch.getFormMasterTerminalExamCommentCollection();
            Collection<TransactionCancellation> transactionCancellationCollectionOld = persistentCompanyBranch.getTransactionCancellationCollection();
            Collection<TransactionCancellation> transactionCancellationCollectionNew = companyBranch.getTransactionCancellationCollection();
            Collection<Users> usersCollectionOld = persistentCompanyBranch.getUsersCollection();
            Collection<Users> usersCollectionNew = companyBranch.getUsersCollection();
            Collection<BranchEmail> branchEmailCollectionOld = persistentCompanyBranch.getBranchEmailCollection();
            Collection<BranchEmail> branchEmailCollectionNew = companyBranch.getBranchEmailCollection();
            Collection<Exam> examCollectionOld = persistentCompanyBranch.getExamCollection();
            Collection<Exam> examCollectionNew = companyBranch.getExamCollection();
            Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollectionOld = persistentCompanyBranch.getScratchCardSalesOrderDetailsCollection();
            Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollectionNew = companyBranch.getScratchCardSalesOrderDetailsCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOld = persistentCompanyBranch.getStudentBehavouralTraitCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionNew = companyBranch.getStudentBehavouralTraitCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionOld = persistentCompanyBranch.getBankAccountPrivilegeCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionNew = companyBranch.getBankAccountPrivilegeCollection();
            Collection<ClassSubjects> classSubjectsCollectionOld = persistentCompanyBranch.getClassSubjectsCollection();
            Collection<ClassSubjects> classSubjectsCollectionNew = companyBranch.getClassSubjectsCollection();
            Collection<PurchaseOrder> purchaseOrderCollectionOld = persistentCompanyBranch.getPurchaseOrderCollection();
            Collection<PurchaseOrder> purchaseOrderCollectionNew = companyBranch.getPurchaseOrderCollection();
            Collection<Grade> gradeCollectionOld = persistentCompanyBranch.getGradeCollection();
            Collection<Grade> gradeCollectionNew = companyBranch.getGradeCollection();
            Collection<BranchPhone> branchPhoneCollectionOld = persistentCompanyBranch.getBranchPhoneCollection();
            Collection<BranchPhone> branchPhoneCollectionNew = companyBranch.getBranchPhoneCollection();
            Collection<Designation> designationCollectionOld = persistentCompanyBranch.getDesignationCollection();
            Collection<Designation> designationCollectionNew = companyBranch.getDesignationCollection();
            Collection<ExamClassPosition> examClassPositionCollectionOld = persistentCompanyBranch.getExamClassPositionCollection();
            Collection<ExamClassPosition> examClassPositionCollectionNew = companyBranch.getExamClassPositionCollection();
            Collection<EmployeeBank> employeeBankCollectionOld = persistentCompanyBranch.getEmployeeBankCollection();
            Collection<EmployeeBank> employeeBankCollectionNew = companyBranch.getEmployeeBankCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentCompanyBranch.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = companyBranch.getSchoolFeeInvoiceDetailsCollection();
            Collection<Student> studentCollectionOld = persistentCompanyBranch.getStudentCollection();
            Collection<Student> studentCollectionNew = companyBranch.getStudentCollection();
            Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollectionOld = persistentCompanyBranch.getScratchCardUnitPriceCollection();
            Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollectionNew = companyBranch.getScratchCardUnitPriceCollection();
            Collection<AcademicYears> academicYearsCollectionOld = persistentCompanyBranch.getAcademicYearsCollection();
            Collection<AcademicYears> academicYearsCollectionNew = companyBranch.getAcademicYearsCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOld = persistentCompanyBranch.getStudentDailyAttendanceCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionNew = companyBranch.getStudentDailyAttendanceCollection();
            Collection<ClassRoutine> classRoutineCollectionOld = persistentCompanyBranch.getClassRoutineCollection();
            Collection<ClassRoutine> classRoutineCollectionNew = companyBranch.getClassRoutineCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentCompanyBranch.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = companyBranch.getExamMarkCollection();
            Collection<Term> termCollectionOld = persistentCompanyBranch.getTermCollection();
            Collection<Term> termCollectionNew = companyBranch.getTermCollection();
            Collection<Department> departmentCollectionOld = persistentCompanyBranch.getDepartmentCollection();
            Collection<Department> departmentCollectionNew = companyBranch.getDepartmentCollection();
            Collection<Guardian> guardianCollectionOld = persistentCompanyBranch.getGuardianCollection();
            Collection<Guardian> guardianCollectionNew = companyBranch.getGuardianCollection();
            Collection<Class> classCollectionOld = persistentCompanyBranch.getClassCollection();
            Collection<Class> classCollectionNew = companyBranch.getClassCollection();
            Collection<ScratchCard> scratchCardCollectionOld = persistentCompanyBranch.getScratchCardCollection();
            Collection<ScratchCard> scratchCardCollectionNew = companyBranch.getScratchCardCollection();
            Collection<BehavouralTrait> behavouralTraitCollectionOld = persistentCompanyBranch.getBehavouralTraitCollection();
            Collection<BehavouralTrait> behavouralTraitCollectionNew = companyBranch.getBehavouralTraitCollection();
            Collection<CaMark> caMarkCollectionOld = persistentCompanyBranch.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = companyBranch.getCaMarkCollection();
            Collection<BranchAddress> branchAddressCollectionOld = persistentCompanyBranch.getBranchAddressCollection();
            Collection<BranchAddress> branchAddressCollectionNew = companyBranch.getBranchAddressCollection();
            Collection<MiscellaneousExpense> miscellaneousExpenseCollectionOld = persistentCompanyBranch.getMiscellaneousExpenseCollection();
            Collection<MiscellaneousExpense> miscellaneousExpenseCollectionNew = companyBranch.getMiscellaneousExpenseCollection();
            Collection<Invoice> invoiceCollectionOld = persistentCompanyBranch.getInvoiceCollection();
            Collection<Invoice> invoiceCollectionNew = companyBranch.getInvoiceCollection();
            Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollectionOld = persistentCompanyBranch.getBehavouralTraitRatingKeysCollection();
            Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollectionNew = companyBranch.getBehavouralTraitRatingKeysCollection();
            Collection<EmployeeAttendance> employeeAttendanceCollectionOld = persistentCompanyBranch.getEmployeeAttendanceCollection();
            Collection<EmployeeAttendance> employeeAttendanceCollectionNew = companyBranch.getEmployeeAttendanceCollection();
            if (companyIdNew != null) {
                companyIdNew = em.getReference(companyIdNew.getClass(), companyIdNew.getCompanyId());
                companyBranch.setCompanyId(companyIdNew);
            }
            if (addressIdNew != null) {
                addressIdNew = em.getReference(addressIdNew.getClass(), addressIdNew.getAddressId());
                companyBranch.setAddressId(addressIdNew);
            }
            if (phoneIdNew != null) {
                phoneIdNew = em.getReference(phoneIdNew.getClass(), phoneIdNew.getPhoneId());
                companyBranch.setPhoneId(phoneIdNew);
            }
            if (emailIdNew != null) {
                emailIdNew = em.getReference(emailIdNew.getClass(), emailIdNew.getEmailId());
                companyBranch.setEmailId(emailIdNew);
            }
            if (faxIdNew != null) {
                faxIdNew = em.getReference(faxIdNew.getClass(), faxIdNew.getFaxId());
                companyBranch.setFaxId(faxIdNew);
            }
            Collection<Subject> attachedSubjectCollectionNew = new ArrayList<Subject>();
            for (Subject subjectCollectionNewSubjectToAttach : subjectCollectionNew) {
                subjectCollectionNewSubjectToAttach = em.getReference(subjectCollectionNewSubjectToAttach.getClass(), subjectCollectionNewSubjectToAttach.getSubjectId());
                attachedSubjectCollectionNew.add(subjectCollectionNewSubjectToAttach);
            }
            subjectCollectionNew = attachedSubjectCollectionNew;
            companyBranch.setSubjectCollection(subjectCollectionNew);
            Collection<EmployeeAward> attachedEmployeeAwardCollectionNew = new ArrayList<EmployeeAward>();
            for (EmployeeAward employeeAwardCollectionNewEmployeeAwardToAttach : employeeAwardCollectionNew) {
                employeeAwardCollectionNewEmployeeAwardToAttach = em.getReference(employeeAwardCollectionNewEmployeeAwardToAttach.getClass(), employeeAwardCollectionNewEmployeeAwardToAttach.getEmployeeAwardId());
                attachedEmployeeAwardCollectionNew.add(employeeAwardCollectionNewEmployeeAwardToAttach);
            }
            employeeAwardCollectionNew = attachedEmployeeAwardCollectionNew;
            companyBranch.setEmployeeAwardCollection(employeeAwardCollectionNew);
            Collection<Section> attachedSectionCollectionNew = new ArrayList<Section>();
            for (Section sectionCollectionNewSectionToAttach : sectionCollectionNew) {
                sectionCollectionNewSectionToAttach = em.getReference(sectionCollectionNewSectionToAttach.getClass(), sectionCollectionNewSectionToAttach.getSectionId());
                attachedSectionCollectionNew.add(sectionCollectionNewSectionToAttach);
            }
            sectionCollectionNew = attachedSectionCollectionNew;
            companyBranch.setSectionCollection(sectionCollectionNew);
            Collection<Employee> attachedEmployeeCollectionNew = new ArrayList<Employee>();
            for (Employee employeeCollectionNewEmployeeToAttach : employeeCollectionNew) {
                employeeCollectionNewEmployeeToAttach = em.getReference(employeeCollectionNewEmployeeToAttach.getClass(), employeeCollectionNewEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollectionNew.add(employeeCollectionNewEmployeeToAttach);
            }
            employeeCollectionNew = attachedEmployeeCollectionNew;
            companyBranch.setEmployeeCollection(employeeCollectionNew);
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollectionNew = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach : principalTerminalExamCommentCollectionNew) {
                principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollectionNew.add(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach);
            }
            principalTerminalExamCommentCollectionNew = attachedPrincipalTerminalExamCommentCollectionNew;
            companyBranch.setPrincipalTerminalExamCommentCollection(principalTerminalExamCommentCollectionNew);
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollectionNew = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetailsToAttach : utilityBillDetailsCollectionNew) {
                utilityBillDetailsCollectionNewUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollectionNew.add(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach);
            }
            utilityBillDetailsCollectionNew = attachedUtilityBillDetailsCollectionNew;
            companyBranch.setUtilityBillDetailsCollection(utilityBillDetailsCollectionNew);
            Collection<ScratchCardType> attachedScratchCardTypeCollectionNew = new ArrayList<ScratchCardType>();
            for (ScratchCardType scratchCardTypeCollectionNewScratchCardTypeToAttach : scratchCardTypeCollectionNew) {
                scratchCardTypeCollectionNewScratchCardTypeToAttach = em.getReference(scratchCardTypeCollectionNewScratchCardTypeToAttach.getClass(), scratchCardTypeCollectionNewScratchCardTypeToAttach.getCardTypeId());
                attachedScratchCardTypeCollectionNew.add(scratchCardTypeCollectionNewScratchCardTypeToAttach);
            }
            scratchCardTypeCollectionNew = attachedScratchCardTypeCollectionNew;
            companyBranch.setScratchCardTypeCollection(scratchCardTypeCollectionNew);
            Collection<BankInstitution> attachedBankInstitutionCollectionNew = new ArrayList<BankInstitution>();
            for (BankInstitution bankInstitutionCollectionNewBankInstitutionToAttach : bankInstitutionCollectionNew) {
                bankInstitutionCollectionNewBankInstitutionToAttach = em.getReference(bankInstitutionCollectionNewBankInstitutionToAttach.getClass(), bankInstitutionCollectionNewBankInstitutionToAttach.getId());
                attachedBankInstitutionCollectionNew.add(bankInstitutionCollectionNewBankInstitutionToAttach);
            }
            bankInstitutionCollectionNew = attachedBankInstitutionCollectionNew;
            companyBranch.setBankInstitutionCollection(bankInstitutionCollectionNew);
            Collection<ClassCategory> attachedClassCategoryCollectionNew = new ArrayList<ClassCategory>();
            for (ClassCategory classCategoryCollectionNewClassCategoryToAttach : classCategoryCollectionNew) {
                classCategoryCollectionNewClassCategoryToAttach = em.getReference(classCategoryCollectionNewClassCategoryToAttach.getClass(), classCategoryCollectionNewClassCategoryToAttach.getCategoryId());
                attachedClassCategoryCollectionNew.add(classCategoryCollectionNewClassCategoryToAttach);
            }
            classCategoryCollectionNew = attachedClassCategoryCollectionNew;
            companyBranch.setClassCategoryCollection(classCategoryCollectionNew);
            Collection<EmployeePayroll> attachedEmployeePayrollCollectionNew = new ArrayList<EmployeePayroll>();
            for (EmployeePayroll employeePayrollCollectionNewEmployeePayrollToAttach : employeePayrollCollectionNew) {
                employeePayrollCollectionNewEmployeePayrollToAttach = em.getReference(employeePayrollCollectionNewEmployeePayrollToAttach.getClass(), employeePayrollCollectionNewEmployeePayrollToAttach.getPayrollId());
                attachedEmployeePayrollCollectionNew.add(employeePayrollCollectionNewEmployeePayrollToAttach);
            }
            employeePayrollCollectionNew = attachedEmployeePayrollCollectionNew;
            companyBranch.setEmployeePayrollCollection(employeePayrollCollectionNew);
            Collection<BankAccountBalance> attachedBankAccountBalanceCollectionNew = new ArrayList<BankAccountBalance>();
            for (BankAccountBalance bankAccountBalanceCollectionNewBankAccountBalanceToAttach : bankAccountBalanceCollectionNew) {
                bankAccountBalanceCollectionNewBankAccountBalanceToAttach = em.getReference(bankAccountBalanceCollectionNewBankAccountBalanceToAttach.getClass(), bankAccountBalanceCollectionNewBankAccountBalanceToAttach.getId());
                attachedBankAccountBalanceCollectionNew.add(bankAccountBalanceCollectionNewBankAccountBalanceToAttach);
            }
            bankAccountBalanceCollectionNew = attachedBankAccountBalanceCollectionNew;
            companyBranch.setBankAccountBalanceCollection(bankAccountBalanceCollectionNew);
            Collection<BankAccount> attachedBankAccountCollectionNew = new ArrayList<BankAccount>();
            for (BankAccount bankAccountCollectionNewBankAccountToAttach : bankAccountCollectionNew) {
                bankAccountCollectionNewBankAccountToAttach = em.getReference(bankAccountCollectionNewBankAccountToAttach.getClass(), bankAccountCollectionNewBankAccountToAttach.getId());
                attachedBankAccountCollectionNew.add(bankAccountCollectionNewBankAccountToAttach);
            }
            bankAccountCollectionNew = attachedBankAccountCollectionNew;
            companyBranch.setBankAccountCollection(bankAccountCollectionNew);
            Collection<SubjectAttendance> attachedSubjectAttendanceCollectionNew = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendanceToAttach : subjectAttendanceCollectionNew) {
                subjectAttendanceCollectionNewSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionNewSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionNewSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollectionNew.add(subjectAttendanceCollectionNewSubjectAttendanceToAttach);
            }
            subjectAttendanceCollectionNew = attachedSubjectAttendanceCollectionNew;
            companyBranch.setSubjectAttendanceCollection(subjectAttendanceCollectionNew);
            Collection<AccountTransaction> attachedAccountTransactionCollectionNew = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionNewAccountTransactionToAttach : accountTransactionCollectionNew) {
                accountTransactionCollectionNewAccountTransactionToAttach = em.getReference(accountTransactionCollectionNewAccountTransactionToAttach.getClass(), accountTransactionCollectionNewAccountTransactionToAttach.getId());
                attachedAccountTransactionCollectionNew.add(accountTransactionCollectionNewAccountTransactionToAttach);
            }
            accountTransactionCollectionNew = attachedAccountTransactionCollectionNew;
            companyBranch.setAccountTransactionCollection(accountTransactionCollectionNew);
            Collection<AuditVault> attachedAuditVaultCollectionNew = new ArrayList<AuditVault>();
            for (AuditVault auditVaultCollectionNewAuditVaultToAttach : auditVaultCollectionNew) {
                auditVaultCollectionNewAuditVaultToAttach = em.getReference(auditVaultCollectionNewAuditVaultToAttach.getClass(), auditVaultCollectionNewAuditVaultToAttach.getId());
                attachedAuditVaultCollectionNew.add(auditVaultCollectionNewAuditVaultToAttach);
            }
            auditVaultCollectionNew = attachedAuditVaultCollectionNew;
            companyBranch.setAuditVaultCollection(auditVaultCollectionNew);
            Collection<ScratchCardSalesOrder> attachedScratchCardSalesOrderCollectionNew = new ArrayList<ScratchCardSalesOrder>();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach : scratchCardSalesOrderCollectionNew) {
                scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach = em.getReference(scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach.getClass(), scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach.getId());
                attachedScratchCardSalesOrderCollectionNew.add(scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach);
            }
            scratchCardSalesOrderCollectionNew = attachedScratchCardSalesOrderCollectionNew;
            companyBranch.setScratchCardSalesOrderCollection(scratchCardSalesOrderCollectionNew);
            Collection<BranchFax> attachedBranchFaxCollectionNew = new ArrayList<BranchFax>();
            for (BranchFax branchFaxCollectionNewBranchFaxToAttach : branchFaxCollectionNew) {
                branchFaxCollectionNewBranchFaxToAttach = em.getReference(branchFaxCollectionNewBranchFaxToAttach.getClass(), branchFaxCollectionNewBranchFaxToAttach.getFaxId());
                attachedBranchFaxCollectionNew.add(branchFaxCollectionNewBranchFaxToAttach);
            }
            branchFaxCollectionNew = attachedBranchFaxCollectionNew;
            companyBranch.setBranchFaxCollection(branchFaxCollectionNew);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollectionNew = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach : formMasterTerminalExamCommentCollectionNew) {
                formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollectionNew.add(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach);
            }
            formMasterTerminalExamCommentCollectionNew = attachedFormMasterTerminalExamCommentCollectionNew;
            companyBranch.setFormMasterTerminalExamCommentCollection(formMasterTerminalExamCommentCollectionNew);
            Collection<TransactionCancellation> attachedTransactionCancellationCollectionNew = new ArrayList<TransactionCancellation>();
            for (TransactionCancellation transactionCancellationCollectionNewTransactionCancellationToAttach : transactionCancellationCollectionNew) {
                transactionCancellationCollectionNewTransactionCancellationToAttach = em.getReference(transactionCancellationCollectionNewTransactionCancellationToAttach.getClass(), transactionCancellationCollectionNewTransactionCancellationToAttach.getId());
                attachedTransactionCancellationCollectionNew.add(transactionCancellationCollectionNewTransactionCancellationToAttach);
            }
            transactionCancellationCollectionNew = attachedTransactionCancellationCollectionNew;
            companyBranch.setTransactionCancellationCollection(transactionCancellationCollectionNew);
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getId());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            companyBranch.setUsersCollection(usersCollectionNew);
            Collection<BranchEmail> attachedBranchEmailCollectionNew = new ArrayList<BranchEmail>();
            for (BranchEmail branchEmailCollectionNewBranchEmailToAttach : branchEmailCollectionNew) {
                branchEmailCollectionNewBranchEmailToAttach = em.getReference(branchEmailCollectionNewBranchEmailToAttach.getClass(), branchEmailCollectionNewBranchEmailToAttach.getEmailId());
                attachedBranchEmailCollectionNew.add(branchEmailCollectionNewBranchEmailToAttach);
            }
            branchEmailCollectionNew = attachedBranchEmailCollectionNew;
            companyBranch.setBranchEmailCollection(branchEmailCollectionNew);
            Collection<Exam> attachedExamCollectionNew = new ArrayList<Exam>();
            for (Exam examCollectionNewExamToAttach : examCollectionNew) {
                examCollectionNewExamToAttach = em.getReference(examCollectionNewExamToAttach.getClass(), examCollectionNewExamToAttach.getExamId());
                attachedExamCollectionNew.add(examCollectionNewExamToAttach);
            }
            examCollectionNew = attachedExamCollectionNew;
            companyBranch.setExamCollection(examCollectionNew);
            Collection<ScratchCardSalesOrderDetails> attachedScratchCardSalesOrderDetailsCollectionNew = new ArrayList<ScratchCardSalesOrderDetails>();
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach : scratchCardSalesOrderDetailsCollectionNew) {
                scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach = em.getReference(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach.getClass(), scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach.getId());
                attachedScratchCardSalesOrderDetailsCollectionNew.add(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach);
            }
            scratchCardSalesOrderDetailsCollectionNew = attachedScratchCardSalesOrderDetailsCollectionNew;
            companyBranch.setScratchCardSalesOrderDetailsCollection(scratchCardSalesOrderDetailsCollectionNew);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollectionNew = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach : studentBehavouralTraitCollectionNew) {
                studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollectionNew.add(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach);
            }
            studentBehavouralTraitCollectionNew = attachedStudentBehavouralTraitCollectionNew;
            companyBranch.setStudentBehavouralTraitCollection(studentBehavouralTraitCollectionNew);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollectionNew = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach : bankAccountPrivilegeCollectionNew) {
                bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollectionNew.add(bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach);
            }
            bankAccountPrivilegeCollectionNew = attachedBankAccountPrivilegeCollectionNew;
            companyBranch.setBankAccountPrivilegeCollection(bankAccountPrivilegeCollectionNew);
            Collection<ClassSubjects> attachedClassSubjectsCollectionNew = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionNewClassSubjectsToAttach : classSubjectsCollectionNew) {
                classSubjectsCollectionNewClassSubjectsToAttach = em.getReference(classSubjectsCollectionNewClassSubjectsToAttach.getClass(), classSubjectsCollectionNewClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollectionNew.add(classSubjectsCollectionNewClassSubjectsToAttach);
            }
            classSubjectsCollectionNew = attachedClassSubjectsCollectionNew;
            companyBranch.setClassSubjectsCollection(classSubjectsCollectionNew);
            Collection<PurchaseOrder> attachedPurchaseOrderCollectionNew = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderCollectionNewPurchaseOrderToAttach : purchaseOrderCollectionNew) {
                purchaseOrderCollectionNewPurchaseOrderToAttach = em.getReference(purchaseOrderCollectionNewPurchaseOrderToAttach.getClass(), purchaseOrderCollectionNewPurchaseOrderToAttach.getId());
                attachedPurchaseOrderCollectionNew.add(purchaseOrderCollectionNewPurchaseOrderToAttach);
            }
            purchaseOrderCollectionNew = attachedPurchaseOrderCollectionNew;
            companyBranch.setPurchaseOrderCollection(purchaseOrderCollectionNew);
            Collection<Grade> attachedGradeCollectionNew = new ArrayList<Grade>();
            for (Grade gradeCollectionNewGradeToAttach : gradeCollectionNew) {
                gradeCollectionNewGradeToAttach = em.getReference(gradeCollectionNewGradeToAttach.getClass(), gradeCollectionNewGradeToAttach.getGradeId());
                attachedGradeCollectionNew.add(gradeCollectionNewGradeToAttach);
            }
            gradeCollectionNew = attachedGradeCollectionNew;
            companyBranch.setGradeCollection(gradeCollectionNew);
            Collection<BranchPhone> attachedBranchPhoneCollectionNew = new ArrayList<BranchPhone>();
            for (BranchPhone branchPhoneCollectionNewBranchPhoneToAttach : branchPhoneCollectionNew) {
                branchPhoneCollectionNewBranchPhoneToAttach = em.getReference(branchPhoneCollectionNewBranchPhoneToAttach.getClass(), branchPhoneCollectionNewBranchPhoneToAttach.getPhoneId());
                attachedBranchPhoneCollectionNew.add(branchPhoneCollectionNewBranchPhoneToAttach);
            }
            branchPhoneCollectionNew = attachedBranchPhoneCollectionNew;
            companyBranch.setBranchPhoneCollection(branchPhoneCollectionNew);
            Collection<Designation> attachedDesignationCollectionNew = new ArrayList<Designation>();
            for (Designation designationCollectionNewDesignationToAttach : designationCollectionNew) {
                designationCollectionNewDesignationToAttach = em.getReference(designationCollectionNewDesignationToAttach.getClass(), designationCollectionNewDesignationToAttach.getDesignationId());
                attachedDesignationCollectionNew.add(designationCollectionNewDesignationToAttach);
            }
            designationCollectionNew = attachedDesignationCollectionNew;
            companyBranch.setDesignationCollection(designationCollectionNew);
            Collection<ExamClassPosition> attachedExamClassPositionCollectionNew = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionNewExamClassPositionToAttach : examClassPositionCollectionNew) {
                examClassPositionCollectionNewExamClassPositionToAttach = em.getReference(examClassPositionCollectionNewExamClassPositionToAttach.getClass(), examClassPositionCollectionNewExamClassPositionToAttach.getId());
                attachedExamClassPositionCollectionNew.add(examClassPositionCollectionNewExamClassPositionToAttach);
            }
            examClassPositionCollectionNew = attachedExamClassPositionCollectionNew;
            companyBranch.setExamClassPositionCollection(examClassPositionCollectionNew);
            Collection<EmployeeBank> attachedEmployeeBankCollectionNew = new ArrayList<EmployeeBank>();
            for (EmployeeBank employeeBankCollectionNewEmployeeBankToAttach : employeeBankCollectionNew) {
                employeeBankCollectionNewEmployeeBankToAttach = em.getReference(employeeBankCollectionNewEmployeeBankToAttach.getClass(), employeeBankCollectionNewEmployeeBankToAttach.getEmployeeBankId());
                attachedEmployeeBankCollectionNew.add(employeeBankCollectionNewEmployeeBankToAttach);
            }
            employeeBankCollectionNew = attachedEmployeeBankCollectionNew;
            companyBranch.setEmployeeBankCollection(employeeBankCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            companyBranch.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            companyBranch.setStudentCollection(studentCollectionNew);
            Collection<ScratchCardUnitPrice> attachedScratchCardUnitPriceCollectionNew = new ArrayList<ScratchCardUnitPrice>();
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach : scratchCardUnitPriceCollectionNew) {
                scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach = em.getReference(scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach.getClass(), scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach.getUnitId());
                attachedScratchCardUnitPriceCollectionNew.add(scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach);
            }
            scratchCardUnitPriceCollectionNew = attachedScratchCardUnitPriceCollectionNew;
            companyBranch.setScratchCardUnitPriceCollection(scratchCardUnitPriceCollectionNew);
            Collection<AcademicYears> attachedAcademicYearsCollectionNew = new ArrayList<AcademicYears>();
            for (AcademicYears academicYearsCollectionNewAcademicYearsToAttach : academicYearsCollectionNew) {
                academicYearsCollectionNewAcademicYearsToAttach = em.getReference(academicYearsCollectionNewAcademicYearsToAttach.getClass(), academicYearsCollectionNewAcademicYearsToAttach.getYearId());
                attachedAcademicYearsCollectionNew.add(academicYearsCollectionNewAcademicYearsToAttach);
            }
            academicYearsCollectionNew = attachedAcademicYearsCollectionNew;
            companyBranch.setAcademicYearsCollection(academicYearsCollectionNew);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollectionNew = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach : studentDailyAttendanceCollectionNew) {
                studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollectionNew.add(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach);
            }
            studentDailyAttendanceCollectionNew = attachedStudentDailyAttendanceCollectionNew;
            companyBranch.setStudentDailyAttendanceCollection(studentDailyAttendanceCollectionNew);
            Collection<ClassRoutine> attachedClassRoutineCollectionNew = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionNewClassRoutineToAttach : classRoutineCollectionNew) {
                classRoutineCollectionNewClassRoutineToAttach = em.getReference(classRoutineCollectionNewClassRoutineToAttach.getClass(), classRoutineCollectionNewClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollectionNew.add(classRoutineCollectionNewClassRoutineToAttach);
            }
            classRoutineCollectionNew = attachedClassRoutineCollectionNew;
            companyBranch.setClassRoutineCollection(classRoutineCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            companyBranch.setExamMarkCollection(examMarkCollectionNew);
            Collection<Term> attachedTermCollectionNew = new ArrayList<Term>();
            for (Term termCollectionNewTermToAttach : termCollectionNew) {
                termCollectionNewTermToAttach = em.getReference(termCollectionNewTermToAttach.getClass(), termCollectionNewTermToAttach.getTermId());
                attachedTermCollectionNew.add(termCollectionNewTermToAttach);
            }
            termCollectionNew = attachedTermCollectionNew;
            companyBranch.setTermCollection(termCollectionNew);
            Collection<Department> attachedDepartmentCollectionNew = new ArrayList<Department>();
            for (Department departmentCollectionNewDepartmentToAttach : departmentCollectionNew) {
                departmentCollectionNewDepartmentToAttach = em.getReference(departmentCollectionNewDepartmentToAttach.getClass(), departmentCollectionNewDepartmentToAttach.getDepartmentId());
                attachedDepartmentCollectionNew.add(departmentCollectionNewDepartmentToAttach);
            }
            departmentCollectionNew = attachedDepartmentCollectionNew;
            companyBranch.setDepartmentCollection(departmentCollectionNew);
            Collection<Guardian> attachedGuardianCollectionNew = new ArrayList<Guardian>();
            for (Guardian guardianCollectionNewGuardianToAttach : guardianCollectionNew) {
                guardianCollectionNewGuardianToAttach = em.getReference(guardianCollectionNewGuardianToAttach.getClass(), guardianCollectionNewGuardianToAttach.getGuardianId());
                attachedGuardianCollectionNew.add(guardianCollectionNewGuardianToAttach);
            }
            guardianCollectionNew = attachedGuardianCollectionNew;
            companyBranch.setGuardianCollection(guardianCollectionNew);
            Collection<Class> attachedClassCollectionNew = new ArrayList<Class>();
            for (Class classCollectionNewClassToAttach : classCollectionNew) {
                classCollectionNewClassToAttach = em.getReference(classCollectionNewClassToAttach.getClass(), classCollectionNewClassToAttach.getClassId());
                attachedClassCollectionNew.add(classCollectionNewClassToAttach);
            }
            classCollectionNew = attachedClassCollectionNew;
            companyBranch.setClassCollection(classCollectionNew);
            Collection<ScratchCard> attachedScratchCardCollectionNew = new ArrayList<ScratchCard>();
            for (ScratchCard scratchCardCollectionNewScratchCardToAttach : scratchCardCollectionNew) {
                scratchCardCollectionNewScratchCardToAttach = em.getReference(scratchCardCollectionNewScratchCardToAttach.getClass(), scratchCardCollectionNewScratchCardToAttach.getCardId());
                attachedScratchCardCollectionNew.add(scratchCardCollectionNewScratchCardToAttach);
            }
            scratchCardCollectionNew = attachedScratchCardCollectionNew;
            companyBranch.setScratchCardCollection(scratchCardCollectionNew);
            Collection<BehavouralTrait> attachedBehavouralTraitCollectionNew = new ArrayList<BehavouralTrait>();
            for (BehavouralTrait behavouralTraitCollectionNewBehavouralTraitToAttach : behavouralTraitCollectionNew) {
                behavouralTraitCollectionNewBehavouralTraitToAttach = em.getReference(behavouralTraitCollectionNewBehavouralTraitToAttach.getClass(), behavouralTraitCollectionNewBehavouralTraitToAttach.getId());
                attachedBehavouralTraitCollectionNew.add(behavouralTraitCollectionNewBehavouralTraitToAttach);
            }
            behavouralTraitCollectionNew = attachedBehavouralTraitCollectionNew;
            companyBranch.setBehavouralTraitCollection(behavouralTraitCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            companyBranch.setCaMarkCollection(caMarkCollectionNew);
            Collection<BranchAddress> attachedBranchAddressCollectionNew = new ArrayList<BranchAddress>();
            for (BranchAddress branchAddressCollectionNewBranchAddressToAttach : branchAddressCollectionNew) {
                branchAddressCollectionNewBranchAddressToAttach = em.getReference(branchAddressCollectionNewBranchAddressToAttach.getClass(), branchAddressCollectionNewBranchAddressToAttach.getAddressId());
                attachedBranchAddressCollectionNew.add(branchAddressCollectionNewBranchAddressToAttach);
            }
            branchAddressCollectionNew = attachedBranchAddressCollectionNew;
            companyBranch.setBranchAddressCollection(branchAddressCollectionNew);
            Collection<MiscellaneousExpense> attachedMiscellaneousExpenseCollectionNew = new ArrayList<MiscellaneousExpense>();
            for (MiscellaneousExpense miscellaneousExpenseCollectionNewMiscellaneousExpenseToAttach : miscellaneousExpenseCollectionNew) {
                miscellaneousExpenseCollectionNewMiscellaneousExpenseToAttach = em.getReference(miscellaneousExpenseCollectionNewMiscellaneousExpenseToAttach.getClass(), miscellaneousExpenseCollectionNewMiscellaneousExpenseToAttach.getId());
                attachedMiscellaneousExpenseCollectionNew.add(miscellaneousExpenseCollectionNewMiscellaneousExpenseToAttach);
            }
            miscellaneousExpenseCollectionNew = attachedMiscellaneousExpenseCollectionNew;
            companyBranch.setMiscellaneousExpenseCollection(miscellaneousExpenseCollectionNew);
            Collection<Invoice> attachedInvoiceCollectionNew = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionNewInvoiceToAttach : invoiceCollectionNew) {
                invoiceCollectionNewInvoiceToAttach = em.getReference(invoiceCollectionNewInvoiceToAttach.getClass(), invoiceCollectionNewInvoiceToAttach.getId());
                attachedInvoiceCollectionNew.add(invoiceCollectionNewInvoiceToAttach);
            }
            invoiceCollectionNew = attachedInvoiceCollectionNew;
            companyBranch.setInvoiceCollection(invoiceCollectionNew);
            Collection<BehavouralTraitRatingKeys> attachedBehavouralTraitRatingKeysCollectionNew = new ArrayList<BehavouralTraitRatingKeys>();
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach : behavouralTraitRatingKeysCollectionNew) {
                behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach = em.getReference(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach.getClass(), behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach.getId());
                attachedBehavouralTraitRatingKeysCollectionNew.add(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach);
            }
            behavouralTraitRatingKeysCollectionNew = attachedBehavouralTraitRatingKeysCollectionNew;
            companyBranch.setBehavouralTraitRatingKeysCollection(behavouralTraitRatingKeysCollectionNew);
            Collection<EmployeeAttendance> attachedEmployeeAttendanceCollectionNew = new ArrayList<EmployeeAttendance>();
            for (EmployeeAttendance employeeAttendanceCollectionNewEmployeeAttendanceToAttach : employeeAttendanceCollectionNew) {
                employeeAttendanceCollectionNewEmployeeAttendanceToAttach = em.getReference(employeeAttendanceCollectionNewEmployeeAttendanceToAttach.getClass(), employeeAttendanceCollectionNewEmployeeAttendanceToAttach.getAttendanceId());
                attachedEmployeeAttendanceCollectionNew.add(employeeAttendanceCollectionNewEmployeeAttendanceToAttach);
            }
            employeeAttendanceCollectionNew = attachedEmployeeAttendanceCollectionNew;
            companyBranch.setEmployeeAttendanceCollection(employeeAttendanceCollectionNew);
            companyBranch = em.merge(companyBranch);
            if (companyIdOld != null && !companyIdOld.equals(companyIdNew)) {
                companyIdOld.getCompanyBranchCollection().remove(companyBranch);
                companyIdOld = em.merge(companyIdOld);
            }
            if (companyIdNew != null && !companyIdNew.equals(companyIdOld)) {
                companyIdNew.getCompanyBranchCollection().add(companyBranch);
                companyIdNew = em.merge(companyIdNew);
            }
            if (addressIdOld != null && !addressIdOld.equals(addressIdNew)) {
                addressIdOld.getCompanyBranchCollection().remove(companyBranch);
                addressIdOld = em.merge(addressIdOld);
            }
            if (addressIdNew != null && !addressIdNew.equals(addressIdOld)) {
                addressIdNew.getCompanyBranchCollection().add(companyBranch);
                addressIdNew = em.merge(addressIdNew);
            }
            if (phoneIdOld != null && !phoneIdOld.equals(phoneIdNew)) {
                phoneIdOld.getCompanyBranchCollection().remove(companyBranch);
                phoneIdOld = em.merge(phoneIdOld);
            }
            if (phoneIdNew != null && !phoneIdNew.equals(phoneIdOld)) {
                phoneIdNew.getCompanyBranchCollection().add(companyBranch);
                phoneIdNew = em.merge(phoneIdNew);
            }
            if (emailIdOld != null && !emailIdOld.equals(emailIdNew)) {
                emailIdOld.getCompanyBranchCollection().remove(companyBranch);
                emailIdOld = em.merge(emailIdOld);
            }
            if (emailIdNew != null && !emailIdNew.equals(emailIdOld)) {
                emailIdNew.getCompanyBranchCollection().add(companyBranch);
                emailIdNew = em.merge(emailIdNew);
            }
            if (faxIdOld != null && !faxIdOld.equals(faxIdNew)) {
                faxIdOld.getCompanyBranchCollection().remove(companyBranch);
                faxIdOld = em.merge(faxIdOld);
            }
            if (faxIdNew != null && !faxIdNew.equals(faxIdOld)) {
                faxIdNew.getCompanyBranchCollection().add(companyBranch);
                faxIdNew = em.merge(faxIdNew);
            }
            for (Subject subjectCollectionOldSubject : subjectCollectionOld) {
                if (!subjectCollectionNew.contains(subjectCollectionOldSubject)) {
                    subjectCollectionOldSubject.setBranchId(null);
                    subjectCollectionOldSubject = em.merge(subjectCollectionOldSubject);
                }
            }
            for (Subject subjectCollectionNewSubject : subjectCollectionNew) {
                if (!subjectCollectionOld.contains(subjectCollectionNewSubject)) {
                    CompanyBranch oldBranchIdOfSubjectCollectionNewSubject = subjectCollectionNewSubject.getBranchId();
                    subjectCollectionNewSubject.setBranchId(companyBranch);
                    subjectCollectionNewSubject = em.merge(subjectCollectionNewSubject);
                    if (oldBranchIdOfSubjectCollectionNewSubject != null && !oldBranchIdOfSubjectCollectionNewSubject.equals(companyBranch)) {
                        oldBranchIdOfSubjectCollectionNewSubject.getSubjectCollection().remove(subjectCollectionNewSubject);
                        oldBranchIdOfSubjectCollectionNewSubject = em.merge(oldBranchIdOfSubjectCollectionNewSubject);
                    }
                }
            }
            for (EmployeeAward employeeAwardCollectionOldEmployeeAward : employeeAwardCollectionOld) {
                if (!employeeAwardCollectionNew.contains(employeeAwardCollectionOldEmployeeAward)) {
                    employeeAwardCollectionOldEmployeeAward.setBranchId(null);
                    employeeAwardCollectionOldEmployeeAward = em.merge(employeeAwardCollectionOldEmployeeAward);
                }
            }
            for (EmployeeAward employeeAwardCollectionNewEmployeeAward : employeeAwardCollectionNew) {
                if (!employeeAwardCollectionOld.contains(employeeAwardCollectionNewEmployeeAward)) {
                    CompanyBranch oldBranchIdOfEmployeeAwardCollectionNewEmployeeAward = employeeAwardCollectionNewEmployeeAward.getBranchId();
                    employeeAwardCollectionNewEmployeeAward.setBranchId(companyBranch);
                    employeeAwardCollectionNewEmployeeAward = em.merge(employeeAwardCollectionNewEmployeeAward);
                    if (oldBranchIdOfEmployeeAwardCollectionNewEmployeeAward != null && !oldBranchIdOfEmployeeAwardCollectionNewEmployeeAward.equals(companyBranch)) {
                        oldBranchIdOfEmployeeAwardCollectionNewEmployeeAward.getEmployeeAwardCollection().remove(employeeAwardCollectionNewEmployeeAward);
                        oldBranchIdOfEmployeeAwardCollectionNewEmployeeAward = em.merge(oldBranchIdOfEmployeeAwardCollectionNewEmployeeAward);
                    }
                }
            }
            for (Section sectionCollectionOldSection : sectionCollectionOld) {
                if (!sectionCollectionNew.contains(sectionCollectionOldSection)) {
                    sectionCollectionOldSection.setBranchId(null);
                    sectionCollectionOldSection = em.merge(sectionCollectionOldSection);
                }
            }
            for (Section sectionCollectionNewSection : sectionCollectionNew) {
                if (!sectionCollectionOld.contains(sectionCollectionNewSection)) {
                    CompanyBranch oldBranchIdOfSectionCollectionNewSection = sectionCollectionNewSection.getBranchId();
                    sectionCollectionNewSection.setBranchId(companyBranch);
                    sectionCollectionNewSection = em.merge(sectionCollectionNewSection);
                    if (oldBranchIdOfSectionCollectionNewSection != null && !oldBranchIdOfSectionCollectionNewSection.equals(companyBranch)) {
                        oldBranchIdOfSectionCollectionNewSection.getSectionCollection().remove(sectionCollectionNewSection);
                        oldBranchIdOfSectionCollectionNewSection = em.merge(oldBranchIdOfSectionCollectionNewSection);
                    }
                }
            }
            for (Employee employeeCollectionOldEmployee : employeeCollectionOld) {
                if (!employeeCollectionNew.contains(employeeCollectionOldEmployee)) {
                    employeeCollectionOldEmployee.setBranchId(null);
                    employeeCollectionOldEmployee = em.merge(employeeCollectionOldEmployee);
                }
            }
            for (Employee employeeCollectionNewEmployee : employeeCollectionNew) {
                if (!employeeCollectionOld.contains(employeeCollectionNewEmployee)) {
                    CompanyBranch oldBranchIdOfEmployeeCollectionNewEmployee = employeeCollectionNewEmployee.getBranchId();
                    employeeCollectionNewEmployee.setBranchId(companyBranch);
                    employeeCollectionNewEmployee = em.merge(employeeCollectionNewEmployee);
                    if (oldBranchIdOfEmployeeCollectionNewEmployee != null && !oldBranchIdOfEmployeeCollectionNewEmployee.equals(companyBranch)) {
                        oldBranchIdOfEmployeeCollectionNewEmployee.getEmployeeCollection().remove(employeeCollectionNewEmployee);
                        oldBranchIdOfEmployeeCollectionNewEmployee = em.merge(oldBranchIdOfEmployeeCollectionNewEmployee);
                    }
                }
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOld) {
                if (!principalTerminalExamCommentCollectionNew.contains(principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment)) {
                    principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment.setBranchId(null);
                    principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment);
                }
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment : principalTerminalExamCommentCollectionNew) {
                if (!principalTerminalExamCommentCollectionOld.contains(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment)) {
                    CompanyBranch oldBranchIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getBranchId();
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.setBranchId(companyBranch);
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    if (oldBranchIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment != null && !oldBranchIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.equals(companyBranch)) {
                        oldBranchIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                        oldBranchIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(oldBranchIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    }
                }
            }
            for (UtilityBillDetails utilityBillDetailsCollectionOldUtilityBillDetails : utilityBillDetailsCollectionOld) {
                if (!utilityBillDetailsCollectionNew.contains(utilityBillDetailsCollectionOldUtilityBillDetails)) {
                    utilityBillDetailsCollectionOldUtilityBillDetails.setBranchId(null);
                    utilityBillDetailsCollectionOldUtilityBillDetails = em.merge(utilityBillDetailsCollectionOldUtilityBillDetails);
                }
            }
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetails : utilityBillDetailsCollectionNew) {
                if (!utilityBillDetailsCollectionOld.contains(utilityBillDetailsCollectionNewUtilityBillDetails)) {
                    CompanyBranch oldBranchIdOfUtilityBillDetailsCollectionNewUtilityBillDetails = utilityBillDetailsCollectionNewUtilityBillDetails.getBranchId();
                    utilityBillDetailsCollectionNewUtilityBillDetails.setBranchId(companyBranch);
                    utilityBillDetailsCollectionNewUtilityBillDetails = em.merge(utilityBillDetailsCollectionNewUtilityBillDetails);
                    if (oldBranchIdOfUtilityBillDetailsCollectionNewUtilityBillDetails != null && !oldBranchIdOfUtilityBillDetailsCollectionNewUtilityBillDetails.equals(companyBranch)) {
                        oldBranchIdOfUtilityBillDetailsCollectionNewUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionNewUtilityBillDetails);
                        oldBranchIdOfUtilityBillDetailsCollectionNewUtilityBillDetails = em.merge(oldBranchIdOfUtilityBillDetailsCollectionNewUtilityBillDetails);
                    }
                }
            }
            for (ScratchCardType scratchCardTypeCollectionOldScratchCardType : scratchCardTypeCollectionOld) {
                if (!scratchCardTypeCollectionNew.contains(scratchCardTypeCollectionOldScratchCardType)) {
                    scratchCardTypeCollectionOldScratchCardType.setBranchId(null);
                    scratchCardTypeCollectionOldScratchCardType = em.merge(scratchCardTypeCollectionOldScratchCardType);
                }
            }
            for (ScratchCardType scratchCardTypeCollectionNewScratchCardType : scratchCardTypeCollectionNew) {
                if (!scratchCardTypeCollectionOld.contains(scratchCardTypeCollectionNewScratchCardType)) {
                    CompanyBranch oldBranchIdOfScratchCardTypeCollectionNewScratchCardType = scratchCardTypeCollectionNewScratchCardType.getBranchId();
                    scratchCardTypeCollectionNewScratchCardType.setBranchId(companyBranch);
                    scratchCardTypeCollectionNewScratchCardType = em.merge(scratchCardTypeCollectionNewScratchCardType);
                    if (oldBranchIdOfScratchCardTypeCollectionNewScratchCardType != null && !oldBranchIdOfScratchCardTypeCollectionNewScratchCardType.equals(companyBranch)) {
                        oldBranchIdOfScratchCardTypeCollectionNewScratchCardType.getScratchCardTypeCollection().remove(scratchCardTypeCollectionNewScratchCardType);
                        oldBranchIdOfScratchCardTypeCollectionNewScratchCardType = em.merge(oldBranchIdOfScratchCardTypeCollectionNewScratchCardType);
                    }
                }
            }
            for (BankInstitution bankInstitutionCollectionOldBankInstitution : bankInstitutionCollectionOld) {
                if (!bankInstitutionCollectionNew.contains(bankInstitutionCollectionOldBankInstitution)) {
                    bankInstitutionCollectionOldBankInstitution.setBranchId(null);
                    bankInstitutionCollectionOldBankInstitution = em.merge(bankInstitutionCollectionOldBankInstitution);
                }
            }
            for (BankInstitution bankInstitutionCollectionNewBankInstitution : bankInstitutionCollectionNew) {
                if (!bankInstitutionCollectionOld.contains(bankInstitutionCollectionNewBankInstitution)) {
                    CompanyBranch oldBranchIdOfBankInstitutionCollectionNewBankInstitution = bankInstitutionCollectionNewBankInstitution.getBranchId();
                    bankInstitutionCollectionNewBankInstitution.setBranchId(companyBranch);
                    bankInstitutionCollectionNewBankInstitution = em.merge(bankInstitutionCollectionNewBankInstitution);
                    if (oldBranchIdOfBankInstitutionCollectionNewBankInstitution != null && !oldBranchIdOfBankInstitutionCollectionNewBankInstitution.equals(companyBranch)) {
                        oldBranchIdOfBankInstitutionCollectionNewBankInstitution.getBankInstitutionCollection().remove(bankInstitutionCollectionNewBankInstitution);
                        oldBranchIdOfBankInstitutionCollectionNewBankInstitution = em.merge(oldBranchIdOfBankInstitutionCollectionNewBankInstitution);
                    }
                }
            }
            for (ClassCategory classCategoryCollectionOldClassCategory : classCategoryCollectionOld) {
                if (!classCategoryCollectionNew.contains(classCategoryCollectionOldClassCategory)) {
                    classCategoryCollectionOldClassCategory.setBranchId(null);
                    classCategoryCollectionOldClassCategory = em.merge(classCategoryCollectionOldClassCategory);
                }
            }
            for (ClassCategory classCategoryCollectionNewClassCategory : classCategoryCollectionNew) {
                if (!classCategoryCollectionOld.contains(classCategoryCollectionNewClassCategory)) {
                    CompanyBranch oldBranchIdOfClassCategoryCollectionNewClassCategory = classCategoryCollectionNewClassCategory.getBranchId();
                    classCategoryCollectionNewClassCategory.setBranchId(companyBranch);
                    classCategoryCollectionNewClassCategory = em.merge(classCategoryCollectionNewClassCategory);
                    if (oldBranchIdOfClassCategoryCollectionNewClassCategory != null && !oldBranchIdOfClassCategoryCollectionNewClassCategory.equals(companyBranch)) {
                        oldBranchIdOfClassCategoryCollectionNewClassCategory.getClassCategoryCollection().remove(classCategoryCollectionNewClassCategory);
                        oldBranchIdOfClassCategoryCollectionNewClassCategory = em.merge(oldBranchIdOfClassCategoryCollectionNewClassCategory);
                    }
                }
            }
            for (EmployeePayroll employeePayrollCollectionOldEmployeePayroll : employeePayrollCollectionOld) {
                if (!employeePayrollCollectionNew.contains(employeePayrollCollectionOldEmployeePayroll)) {
                    employeePayrollCollectionOldEmployeePayroll.setBranchId(null);
                    employeePayrollCollectionOldEmployeePayroll = em.merge(employeePayrollCollectionOldEmployeePayroll);
                }
            }
            for (EmployeePayroll employeePayrollCollectionNewEmployeePayroll : employeePayrollCollectionNew) {
                if (!employeePayrollCollectionOld.contains(employeePayrollCollectionNewEmployeePayroll)) {
                    CompanyBranch oldBranchIdOfEmployeePayrollCollectionNewEmployeePayroll = employeePayrollCollectionNewEmployeePayroll.getBranchId();
                    employeePayrollCollectionNewEmployeePayroll.setBranchId(companyBranch);
                    employeePayrollCollectionNewEmployeePayroll = em.merge(employeePayrollCollectionNewEmployeePayroll);
                    if (oldBranchIdOfEmployeePayrollCollectionNewEmployeePayroll != null && !oldBranchIdOfEmployeePayrollCollectionNewEmployeePayroll.equals(companyBranch)) {
                        oldBranchIdOfEmployeePayrollCollectionNewEmployeePayroll.getEmployeePayrollCollection().remove(employeePayrollCollectionNewEmployeePayroll);
                        oldBranchIdOfEmployeePayrollCollectionNewEmployeePayroll = em.merge(oldBranchIdOfEmployeePayrollCollectionNewEmployeePayroll);
                    }
                }
            }
            for (BankAccountBalance bankAccountBalanceCollectionOldBankAccountBalance : bankAccountBalanceCollectionOld) {
                if (!bankAccountBalanceCollectionNew.contains(bankAccountBalanceCollectionOldBankAccountBalance)) {
                    bankAccountBalanceCollectionOldBankAccountBalance.setBranchId(null);
                    bankAccountBalanceCollectionOldBankAccountBalance = em.merge(bankAccountBalanceCollectionOldBankAccountBalance);
                }
            }
            for (BankAccountBalance bankAccountBalanceCollectionNewBankAccountBalance : bankAccountBalanceCollectionNew) {
                if (!bankAccountBalanceCollectionOld.contains(bankAccountBalanceCollectionNewBankAccountBalance)) {
                    CompanyBranch oldBranchIdOfBankAccountBalanceCollectionNewBankAccountBalance = bankAccountBalanceCollectionNewBankAccountBalance.getBranchId();
                    bankAccountBalanceCollectionNewBankAccountBalance.setBranchId(companyBranch);
                    bankAccountBalanceCollectionNewBankAccountBalance = em.merge(bankAccountBalanceCollectionNewBankAccountBalance);
                    if (oldBranchIdOfBankAccountBalanceCollectionNewBankAccountBalance != null && !oldBranchIdOfBankAccountBalanceCollectionNewBankAccountBalance.equals(companyBranch)) {
                        oldBranchIdOfBankAccountBalanceCollectionNewBankAccountBalance.getBankAccountBalanceCollection().remove(bankAccountBalanceCollectionNewBankAccountBalance);
                        oldBranchIdOfBankAccountBalanceCollectionNewBankAccountBalance = em.merge(oldBranchIdOfBankAccountBalanceCollectionNewBankAccountBalance);
                    }
                }
            }
            for (BankAccount bankAccountCollectionOldBankAccount : bankAccountCollectionOld) {
                if (!bankAccountCollectionNew.contains(bankAccountCollectionOldBankAccount)) {
                    bankAccountCollectionOldBankAccount.setBranchId(null);
                    bankAccountCollectionOldBankAccount = em.merge(bankAccountCollectionOldBankAccount);
                }
            }
            for (BankAccount bankAccountCollectionNewBankAccount : bankAccountCollectionNew) {
                if (!bankAccountCollectionOld.contains(bankAccountCollectionNewBankAccount)) {
                    CompanyBranch oldBranchIdOfBankAccountCollectionNewBankAccount = bankAccountCollectionNewBankAccount.getBranchId();
                    bankAccountCollectionNewBankAccount.setBranchId(companyBranch);
                    bankAccountCollectionNewBankAccount = em.merge(bankAccountCollectionNewBankAccount);
                    if (oldBranchIdOfBankAccountCollectionNewBankAccount != null && !oldBranchIdOfBankAccountCollectionNewBankAccount.equals(companyBranch)) {
                        oldBranchIdOfBankAccountCollectionNewBankAccount.getBankAccountCollection().remove(bankAccountCollectionNewBankAccount);
                        oldBranchIdOfBankAccountCollectionNewBankAccount = em.merge(oldBranchIdOfBankAccountCollectionNewBankAccount);
                    }
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionOldSubjectAttendance : subjectAttendanceCollectionOld) {
                if (!subjectAttendanceCollectionNew.contains(subjectAttendanceCollectionOldSubjectAttendance)) {
                    subjectAttendanceCollectionOldSubjectAttendance.setBranchId(null);
                    subjectAttendanceCollectionOldSubjectAttendance = em.merge(subjectAttendanceCollectionOldSubjectAttendance);
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendance : subjectAttendanceCollectionNew) {
                if (!subjectAttendanceCollectionOld.contains(subjectAttendanceCollectionNewSubjectAttendance)) {
                    CompanyBranch oldBranchIdOfSubjectAttendanceCollectionNewSubjectAttendance = subjectAttendanceCollectionNewSubjectAttendance.getBranchId();
                    subjectAttendanceCollectionNewSubjectAttendance.setBranchId(companyBranch);
                    subjectAttendanceCollectionNewSubjectAttendance = em.merge(subjectAttendanceCollectionNewSubjectAttendance);
                    if (oldBranchIdOfSubjectAttendanceCollectionNewSubjectAttendance != null && !oldBranchIdOfSubjectAttendanceCollectionNewSubjectAttendance.equals(companyBranch)) {
                        oldBranchIdOfSubjectAttendanceCollectionNewSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionNewSubjectAttendance);
                        oldBranchIdOfSubjectAttendanceCollectionNewSubjectAttendance = em.merge(oldBranchIdOfSubjectAttendanceCollectionNewSubjectAttendance);
                    }
                }
            }
            for (AccountTransaction accountTransactionCollectionOldAccountTransaction : accountTransactionCollectionOld) {
                if (!accountTransactionCollectionNew.contains(accountTransactionCollectionOldAccountTransaction)) {
                    accountTransactionCollectionOldAccountTransaction.setBranchId(null);
                    accountTransactionCollectionOldAccountTransaction = em.merge(accountTransactionCollectionOldAccountTransaction);
                }
            }
            for (AccountTransaction accountTransactionCollectionNewAccountTransaction : accountTransactionCollectionNew) {
                if (!accountTransactionCollectionOld.contains(accountTransactionCollectionNewAccountTransaction)) {
                    CompanyBranch oldBranchIdOfAccountTransactionCollectionNewAccountTransaction = accountTransactionCollectionNewAccountTransaction.getBranchId();
                    accountTransactionCollectionNewAccountTransaction.setBranchId(companyBranch);
                    accountTransactionCollectionNewAccountTransaction = em.merge(accountTransactionCollectionNewAccountTransaction);
                    if (oldBranchIdOfAccountTransactionCollectionNewAccountTransaction != null && !oldBranchIdOfAccountTransactionCollectionNewAccountTransaction.equals(companyBranch)) {
                        oldBranchIdOfAccountTransactionCollectionNewAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionNewAccountTransaction);
                        oldBranchIdOfAccountTransactionCollectionNewAccountTransaction = em.merge(oldBranchIdOfAccountTransactionCollectionNewAccountTransaction);
                    }
                }
            }
            for (AuditVault auditVaultCollectionOldAuditVault : auditVaultCollectionOld) {
                if (!auditVaultCollectionNew.contains(auditVaultCollectionOldAuditVault)) {
                    auditVaultCollectionOldAuditVault.setBranchId(null);
                    auditVaultCollectionOldAuditVault = em.merge(auditVaultCollectionOldAuditVault);
                }
            }
            for (AuditVault auditVaultCollectionNewAuditVault : auditVaultCollectionNew) {
                if (!auditVaultCollectionOld.contains(auditVaultCollectionNewAuditVault)) {
                    CompanyBranch oldBranchIdOfAuditVaultCollectionNewAuditVault = auditVaultCollectionNewAuditVault.getBranchId();
                    auditVaultCollectionNewAuditVault.setBranchId(companyBranch);
                    auditVaultCollectionNewAuditVault = em.merge(auditVaultCollectionNewAuditVault);
                    if (oldBranchIdOfAuditVaultCollectionNewAuditVault != null && !oldBranchIdOfAuditVaultCollectionNewAuditVault.equals(companyBranch)) {
                        oldBranchIdOfAuditVaultCollectionNewAuditVault.getAuditVaultCollection().remove(auditVaultCollectionNewAuditVault);
                        oldBranchIdOfAuditVaultCollectionNewAuditVault = em.merge(oldBranchIdOfAuditVaultCollectionNewAuditVault);
                    }
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionOldScratchCardSalesOrder : scratchCardSalesOrderCollectionOld) {
                if (!scratchCardSalesOrderCollectionNew.contains(scratchCardSalesOrderCollectionOldScratchCardSalesOrder)) {
                    scratchCardSalesOrderCollectionOldScratchCardSalesOrder.setBranchId(null);
                    scratchCardSalesOrderCollectionOldScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionOldScratchCardSalesOrder);
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionNewScratchCardSalesOrder : scratchCardSalesOrderCollectionNew) {
                if (!scratchCardSalesOrderCollectionOld.contains(scratchCardSalesOrderCollectionNewScratchCardSalesOrder)) {
                    CompanyBranch oldBranchIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder = scratchCardSalesOrderCollectionNewScratchCardSalesOrder.getBranchId();
                    scratchCardSalesOrderCollectionNewScratchCardSalesOrder.setBranchId(companyBranch);
                    scratchCardSalesOrderCollectionNewScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                    if (oldBranchIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder != null && !oldBranchIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder.equals(companyBranch)) {
                        oldBranchIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                        oldBranchIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder = em.merge(oldBranchIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                    }
                }
            }
            for (BranchFax branchFaxCollectionOldBranchFax : branchFaxCollectionOld) {
                if (!branchFaxCollectionNew.contains(branchFaxCollectionOldBranchFax)) {
                    branchFaxCollectionOldBranchFax.setBranchId(null);
                    branchFaxCollectionOldBranchFax = em.merge(branchFaxCollectionOldBranchFax);
                }
            }
            for (BranchFax branchFaxCollectionNewBranchFax : branchFaxCollectionNew) {
                if (!branchFaxCollectionOld.contains(branchFaxCollectionNewBranchFax)) {
                    CompanyBranch oldBranchIdOfBranchFaxCollectionNewBranchFax = branchFaxCollectionNewBranchFax.getBranchId();
                    branchFaxCollectionNewBranchFax.setBranchId(companyBranch);
                    branchFaxCollectionNewBranchFax = em.merge(branchFaxCollectionNewBranchFax);
                    if (oldBranchIdOfBranchFaxCollectionNewBranchFax != null && !oldBranchIdOfBranchFaxCollectionNewBranchFax.equals(companyBranch)) {
                        oldBranchIdOfBranchFaxCollectionNewBranchFax.getBranchFaxCollection().remove(branchFaxCollectionNewBranchFax);
                        oldBranchIdOfBranchFaxCollectionNewBranchFax = em.merge(oldBranchIdOfBranchFaxCollectionNewBranchFax);
                    }
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOld) {
                if (!formMasterTerminalExamCommentCollectionNew.contains(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment)) {
                    formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment.setBranchId(null);
                    formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment);
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionNew) {
                if (!formMasterTerminalExamCommentCollectionOld.contains(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment)) {
                    CompanyBranch oldBranchIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getBranchId();
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.setBranchId(companyBranch);
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    if (oldBranchIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment != null && !oldBranchIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.equals(companyBranch)) {
                        oldBranchIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                        oldBranchIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(oldBranchIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    }
                }
            }
            for (TransactionCancellation transactionCancellationCollectionOldTransactionCancellation : transactionCancellationCollectionOld) {
                if (!transactionCancellationCollectionNew.contains(transactionCancellationCollectionOldTransactionCancellation)) {
                    transactionCancellationCollectionOldTransactionCancellation.setBranchId(null);
                    transactionCancellationCollectionOldTransactionCancellation = em.merge(transactionCancellationCollectionOldTransactionCancellation);
                }
            }
            for (TransactionCancellation transactionCancellationCollectionNewTransactionCancellation : transactionCancellationCollectionNew) {
                if (!transactionCancellationCollectionOld.contains(transactionCancellationCollectionNewTransactionCancellation)) {
                    CompanyBranch oldBranchIdOfTransactionCancellationCollectionNewTransactionCancellation = transactionCancellationCollectionNewTransactionCancellation.getBranchId();
                    transactionCancellationCollectionNewTransactionCancellation.setBranchId(companyBranch);
                    transactionCancellationCollectionNewTransactionCancellation = em.merge(transactionCancellationCollectionNewTransactionCancellation);
                    if (oldBranchIdOfTransactionCancellationCollectionNewTransactionCancellation != null && !oldBranchIdOfTransactionCancellationCollectionNewTransactionCancellation.equals(companyBranch)) {
                        oldBranchIdOfTransactionCancellationCollectionNewTransactionCancellation.getTransactionCancellationCollection().remove(transactionCancellationCollectionNewTransactionCancellation);
                        oldBranchIdOfTransactionCancellationCollectionNewTransactionCancellation = em.merge(oldBranchIdOfTransactionCancellationCollectionNewTransactionCancellation);
                    }
                }
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    usersCollectionOldUsers.setBranchId(null);
                    usersCollectionOldUsers = em.merge(usersCollectionOldUsers);
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    CompanyBranch oldBranchIdOfUsersCollectionNewUsers = usersCollectionNewUsers.getBranchId();
                    usersCollectionNewUsers.setBranchId(companyBranch);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldBranchIdOfUsersCollectionNewUsers != null && !oldBranchIdOfUsersCollectionNewUsers.equals(companyBranch)) {
                        oldBranchIdOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldBranchIdOfUsersCollectionNewUsers = em.merge(oldBranchIdOfUsersCollectionNewUsers);
                    }
                }
            }
            for (BranchEmail branchEmailCollectionOldBranchEmail : branchEmailCollectionOld) {
                if (!branchEmailCollectionNew.contains(branchEmailCollectionOldBranchEmail)) {
                    branchEmailCollectionOldBranchEmail.setBranchId(null);
                    branchEmailCollectionOldBranchEmail = em.merge(branchEmailCollectionOldBranchEmail);
                }
            }
            for (BranchEmail branchEmailCollectionNewBranchEmail : branchEmailCollectionNew) {
                if (!branchEmailCollectionOld.contains(branchEmailCollectionNewBranchEmail)) {
                    CompanyBranch oldBranchIdOfBranchEmailCollectionNewBranchEmail = branchEmailCollectionNewBranchEmail.getBranchId();
                    branchEmailCollectionNewBranchEmail.setBranchId(companyBranch);
                    branchEmailCollectionNewBranchEmail = em.merge(branchEmailCollectionNewBranchEmail);
                    if (oldBranchIdOfBranchEmailCollectionNewBranchEmail != null && !oldBranchIdOfBranchEmailCollectionNewBranchEmail.equals(companyBranch)) {
                        oldBranchIdOfBranchEmailCollectionNewBranchEmail.getBranchEmailCollection().remove(branchEmailCollectionNewBranchEmail);
                        oldBranchIdOfBranchEmailCollectionNewBranchEmail = em.merge(oldBranchIdOfBranchEmailCollectionNewBranchEmail);
                    }
                }
            }
            for (Exam examCollectionOldExam : examCollectionOld) {
                if (!examCollectionNew.contains(examCollectionOldExam)) {
                    examCollectionOldExam.setBranchId(null);
                    examCollectionOldExam = em.merge(examCollectionOldExam);
                }
            }
            for (Exam examCollectionNewExam : examCollectionNew) {
                if (!examCollectionOld.contains(examCollectionNewExam)) {
                    CompanyBranch oldBranchIdOfExamCollectionNewExam = examCollectionNewExam.getBranchId();
                    examCollectionNewExam.setBranchId(companyBranch);
                    examCollectionNewExam = em.merge(examCollectionNewExam);
                    if (oldBranchIdOfExamCollectionNewExam != null && !oldBranchIdOfExamCollectionNewExam.equals(companyBranch)) {
                        oldBranchIdOfExamCollectionNewExam.getExamCollection().remove(examCollectionNewExam);
                        oldBranchIdOfExamCollectionNewExam = em.merge(oldBranchIdOfExamCollectionNewExam);
                    }
                }
            }
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails : scratchCardSalesOrderDetailsCollectionOld) {
                if (!scratchCardSalesOrderDetailsCollectionNew.contains(scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails)) {
                    scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails.setBranchId(null);
                    scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails);
                }
            }
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails : scratchCardSalesOrderDetailsCollectionNew) {
                if (!scratchCardSalesOrderDetailsCollectionOld.contains(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails)) {
                    CompanyBranch oldBranchIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails = scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.getBranchId();
                    scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.setBranchId(companyBranch);
                    scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails);
                    if (oldBranchIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails != null && !oldBranchIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.equals(companyBranch)) {
                        oldBranchIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails);
                        oldBranchIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails = em.merge(oldBranchIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails);
                    }
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOldStudentBehavouralTrait : studentBehavouralTraitCollectionOld) {
                if (!studentBehavouralTraitCollectionNew.contains(studentBehavouralTraitCollectionOldStudentBehavouralTrait)) {
                    studentBehavouralTraitCollectionOldStudentBehavouralTrait.setBranchId(null);
                    studentBehavouralTraitCollectionOldStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionOldStudentBehavouralTrait);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTrait : studentBehavouralTraitCollectionNew) {
                if (!studentBehavouralTraitCollectionOld.contains(studentBehavouralTraitCollectionNewStudentBehavouralTrait)) {
                    CompanyBranch oldBranchIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = studentBehavouralTraitCollectionNewStudentBehavouralTrait.getBranchId();
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait.setBranchId(companyBranch);
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    if (oldBranchIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait != null && !oldBranchIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.equals(companyBranch)) {
                        oldBranchIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                        oldBranchIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(oldBranchIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    }
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionOldBankAccountPrivilege : bankAccountPrivilegeCollectionOld) {
                if (!bankAccountPrivilegeCollectionNew.contains(bankAccountPrivilegeCollectionOldBankAccountPrivilege)) {
                    bankAccountPrivilegeCollectionOldBankAccountPrivilege.setBranchId(null);
                    bankAccountPrivilegeCollectionOldBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionOldBankAccountPrivilege);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionNewBankAccountPrivilege : bankAccountPrivilegeCollectionNew) {
                if (!bankAccountPrivilegeCollectionOld.contains(bankAccountPrivilegeCollectionNewBankAccountPrivilege)) {
                    CompanyBranch oldBranchIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege = bankAccountPrivilegeCollectionNewBankAccountPrivilege.getBranchId();
                    bankAccountPrivilegeCollectionNewBankAccountPrivilege.setBranchId(companyBranch);
                    bankAccountPrivilegeCollectionNewBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionNewBankAccountPrivilege);
                    if (oldBranchIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege != null && !oldBranchIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege.equals(companyBranch)) {
                        oldBranchIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege.getBankAccountPrivilegeCollection().remove(bankAccountPrivilegeCollectionNewBankAccountPrivilege);
                        oldBranchIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege = em.merge(oldBranchIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege);
                    }
                }
            }
            for (ClassSubjects classSubjectsCollectionOldClassSubjects : classSubjectsCollectionOld) {
                if (!classSubjectsCollectionNew.contains(classSubjectsCollectionOldClassSubjects)) {
                    classSubjectsCollectionOldClassSubjects.setBranchId(null);
                    classSubjectsCollectionOldClassSubjects = em.merge(classSubjectsCollectionOldClassSubjects);
                }
            }
            for (ClassSubjects classSubjectsCollectionNewClassSubjects : classSubjectsCollectionNew) {
                if (!classSubjectsCollectionOld.contains(classSubjectsCollectionNewClassSubjects)) {
                    CompanyBranch oldBranchIdOfClassSubjectsCollectionNewClassSubjects = classSubjectsCollectionNewClassSubjects.getBranchId();
                    classSubjectsCollectionNewClassSubjects.setBranchId(companyBranch);
                    classSubjectsCollectionNewClassSubjects = em.merge(classSubjectsCollectionNewClassSubjects);
                    if (oldBranchIdOfClassSubjectsCollectionNewClassSubjects != null && !oldBranchIdOfClassSubjectsCollectionNewClassSubjects.equals(companyBranch)) {
                        oldBranchIdOfClassSubjectsCollectionNewClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionNewClassSubjects);
                        oldBranchIdOfClassSubjectsCollectionNewClassSubjects = em.merge(oldBranchIdOfClassSubjectsCollectionNewClassSubjects);
                    }
                }
            }
            for (PurchaseOrder purchaseOrderCollectionOldPurchaseOrder : purchaseOrderCollectionOld) {
                if (!purchaseOrderCollectionNew.contains(purchaseOrderCollectionOldPurchaseOrder)) {
                    purchaseOrderCollectionOldPurchaseOrder.setBranchId(null);
                    purchaseOrderCollectionOldPurchaseOrder = em.merge(purchaseOrderCollectionOldPurchaseOrder);
                }
            }
            for (PurchaseOrder purchaseOrderCollectionNewPurchaseOrder : purchaseOrderCollectionNew) {
                if (!purchaseOrderCollectionOld.contains(purchaseOrderCollectionNewPurchaseOrder)) {
                    CompanyBranch oldBranchIdOfPurchaseOrderCollectionNewPurchaseOrder = purchaseOrderCollectionNewPurchaseOrder.getBranchId();
                    purchaseOrderCollectionNewPurchaseOrder.setBranchId(companyBranch);
                    purchaseOrderCollectionNewPurchaseOrder = em.merge(purchaseOrderCollectionNewPurchaseOrder);
                    if (oldBranchIdOfPurchaseOrderCollectionNewPurchaseOrder != null && !oldBranchIdOfPurchaseOrderCollectionNewPurchaseOrder.equals(companyBranch)) {
                        oldBranchIdOfPurchaseOrderCollectionNewPurchaseOrder.getPurchaseOrderCollection().remove(purchaseOrderCollectionNewPurchaseOrder);
                        oldBranchIdOfPurchaseOrderCollectionNewPurchaseOrder = em.merge(oldBranchIdOfPurchaseOrderCollectionNewPurchaseOrder);
                    }
                }
            }
            for (Grade gradeCollectionOldGrade : gradeCollectionOld) {
                if (!gradeCollectionNew.contains(gradeCollectionOldGrade)) {
                    gradeCollectionOldGrade.setBranchId(null);
                    gradeCollectionOldGrade = em.merge(gradeCollectionOldGrade);
                }
            }
            for (Grade gradeCollectionNewGrade : gradeCollectionNew) {
                if (!gradeCollectionOld.contains(gradeCollectionNewGrade)) {
                    CompanyBranch oldBranchIdOfGradeCollectionNewGrade = gradeCollectionNewGrade.getBranchId();
                    gradeCollectionNewGrade.setBranchId(companyBranch);
                    gradeCollectionNewGrade = em.merge(gradeCollectionNewGrade);
                    if (oldBranchIdOfGradeCollectionNewGrade != null && !oldBranchIdOfGradeCollectionNewGrade.equals(companyBranch)) {
                        oldBranchIdOfGradeCollectionNewGrade.getGradeCollection().remove(gradeCollectionNewGrade);
                        oldBranchIdOfGradeCollectionNewGrade = em.merge(oldBranchIdOfGradeCollectionNewGrade);
                    }
                }
            }
            for (BranchPhone branchPhoneCollectionOldBranchPhone : branchPhoneCollectionOld) {
                if (!branchPhoneCollectionNew.contains(branchPhoneCollectionOldBranchPhone)) {
                    branchPhoneCollectionOldBranchPhone.setBranchId(null);
                    branchPhoneCollectionOldBranchPhone = em.merge(branchPhoneCollectionOldBranchPhone);
                }
            }
            for (BranchPhone branchPhoneCollectionNewBranchPhone : branchPhoneCollectionNew) {
                if (!branchPhoneCollectionOld.contains(branchPhoneCollectionNewBranchPhone)) {
                    CompanyBranch oldBranchIdOfBranchPhoneCollectionNewBranchPhone = branchPhoneCollectionNewBranchPhone.getBranchId();
                    branchPhoneCollectionNewBranchPhone.setBranchId(companyBranch);
                    branchPhoneCollectionNewBranchPhone = em.merge(branchPhoneCollectionNewBranchPhone);
                    if (oldBranchIdOfBranchPhoneCollectionNewBranchPhone != null && !oldBranchIdOfBranchPhoneCollectionNewBranchPhone.equals(companyBranch)) {
                        oldBranchIdOfBranchPhoneCollectionNewBranchPhone.getBranchPhoneCollection().remove(branchPhoneCollectionNewBranchPhone);
                        oldBranchIdOfBranchPhoneCollectionNewBranchPhone = em.merge(oldBranchIdOfBranchPhoneCollectionNewBranchPhone);
                    }
                }
            }
            for (Designation designationCollectionOldDesignation : designationCollectionOld) {
                if (!designationCollectionNew.contains(designationCollectionOldDesignation)) {
                    designationCollectionOldDesignation.setBranchId(null);
                    designationCollectionOldDesignation = em.merge(designationCollectionOldDesignation);
                }
            }
            for (Designation designationCollectionNewDesignation : designationCollectionNew) {
                if (!designationCollectionOld.contains(designationCollectionNewDesignation)) {
                    CompanyBranch oldBranchIdOfDesignationCollectionNewDesignation = designationCollectionNewDesignation.getBranchId();
                    designationCollectionNewDesignation.setBranchId(companyBranch);
                    designationCollectionNewDesignation = em.merge(designationCollectionNewDesignation);
                    if (oldBranchIdOfDesignationCollectionNewDesignation != null && !oldBranchIdOfDesignationCollectionNewDesignation.equals(companyBranch)) {
                        oldBranchIdOfDesignationCollectionNewDesignation.getDesignationCollection().remove(designationCollectionNewDesignation);
                        oldBranchIdOfDesignationCollectionNewDesignation = em.merge(oldBranchIdOfDesignationCollectionNewDesignation);
                    }
                }
            }
            for (ExamClassPosition examClassPositionCollectionOldExamClassPosition : examClassPositionCollectionOld) {
                if (!examClassPositionCollectionNew.contains(examClassPositionCollectionOldExamClassPosition)) {
                    examClassPositionCollectionOldExamClassPosition.setBranchId(null);
                    examClassPositionCollectionOldExamClassPosition = em.merge(examClassPositionCollectionOldExamClassPosition);
                }
            }
            for (ExamClassPosition examClassPositionCollectionNewExamClassPosition : examClassPositionCollectionNew) {
                if (!examClassPositionCollectionOld.contains(examClassPositionCollectionNewExamClassPosition)) {
                    CompanyBranch oldBranchIdOfExamClassPositionCollectionNewExamClassPosition = examClassPositionCollectionNewExamClassPosition.getBranchId();
                    examClassPositionCollectionNewExamClassPosition.setBranchId(companyBranch);
                    examClassPositionCollectionNewExamClassPosition = em.merge(examClassPositionCollectionNewExamClassPosition);
                    if (oldBranchIdOfExamClassPositionCollectionNewExamClassPosition != null && !oldBranchIdOfExamClassPositionCollectionNewExamClassPosition.equals(companyBranch)) {
                        oldBranchIdOfExamClassPositionCollectionNewExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionNewExamClassPosition);
                        oldBranchIdOfExamClassPositionCollectionNewExamClassPosition = em.merge(oldBranchIdOfExamClassPositionCollectionNewExamClassPosition);
                    }
                }
            }
            for (EmployeeBank employeeBankCollectionOldEmployeeBank : employeeBankCollectionOld) {
                if (!employeeBankCollectionNew.contains(employeeBankCollectionOldEmployeeBank)) {
                    employeeBankCollectionOldEmployeeBank.setBranchId(null);
                    employeeBankCollectionOldEmployeeBank = em.merge(employeeBankCollectionOldEmployeeBank);
                }
            }
            for (EmployeeBank employeeBankCollectionNewEmployeeBank : employeeBankCollectionNew) {
                if (!employeeBankCollectionOld.contains(employeeBankCollectionNewEmployeeBank)) {
                    CompanyBranch oldBranchIdOfEmployeeBankCollectionNewEmployeeBank = employeeBankCollectionNewEmployeeBank.getBranchId();
                    employeeBankCollectionNewEmployeeBank.setBranchId(companyBranch);
                    employeeBankCollectionNewEmployeeBank = em.merge(employeeBankCollectionNewEmployeeBank);
                    if (oldBranchIdOfEmployeeBankCollectionNewEmployeeBank != null && !oldBranchIdOfEmployeeBankCollectionNewEmployeeBank.equals(companyBranch)) {
                        oldBranchIdOfEmployeeBankCollectionNewEmployeeBank.getEmployeeBankCollection().remove(employeeBankCollectionNewEmployeeBank);
                        oldBranchIdOfEmployeeBankCollectionNewEmployeeBank = em.merge(oldBranchIdOfEmployeeBankCollectionNewEmployeeBank);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails.setBranchId(null);
                    schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    CompanyBranch oldBranchIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getBranchId();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setBranchId(companyBranch);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldBranchIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldBranchIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(companyBranch)) {
                        oldBranchIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldBranchIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldBranchIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setBranchId(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    CompanyBranch oldBranchIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getBranchId();
                    studentCollectionNewStudent.setBranchId(companyBranch);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldBranchIdOfStudentCollectionNewStudent != null && !oldBranchIdOfStudentCollectionNewStudent.equals(companyBranch)) {
                        oldBranchIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldBranchIdOfStudentCollectionNewStudent = em.merge(oldBranchIdOfStudentCollectionNewStudent);
                    }
                }
            }
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionOldScratchCardUnitPrice : scratchCardUnitPriceCollectionOld) {
                if (!scratchCardUnitPriceCollectionNew.contains(scratchCardUnitPriceCollectionOldScratchCardUnitPrice)) {
                    scratchCardUnitPriceCollectionOldScratchCardUnitPrice.setBranchId(null);
                    scratchCardUnitPriceCollectionOldScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionOldScratchCardUnitPrice);
                }
            }
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionNewScratchCardUnitPrice : scratchCardUnitPriceCollectionNew) {
                if (!scratchCardUnitPriceCollectionOld.contains(scratchCardUnitPriceCollectionNewScratchCardUnitPrice)) {
                    CompanyBranch oldBranchIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice = scratchCardUnitPriceCollectionNewScratchCardUnitPrice.getBranchId();
                    scratchCardUnitPriceCollectionNewScratchCardUnitPrice.setBranchId(companyBranch);
                    scratchCardUnitPriceCollectionNewScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionNewScratchCardUnitPrice);
                    if (oldBranchIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice != null && !oldBranchIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice.equals(companyBranch)) {
                        oldBranchIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice.getScratchCardUnitPriceCollection().remove(scratchCardUnitPriceCollectionNewScratchCardUnitPrice);
                        oldBranchIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice = em.merge(oldBranchIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice);
                    }
                }
            }
            for (AcademicYears academicYearsCollectionOldAcademicYears : academicYearsCollectionOld) {
                if (!academicYearsCollectionNew.contains(academicYearsCollectionOldAcademicYears)) {
                    academicYearsCollectionOldAcademicYears.setBranchId(null);
                    academicYearsCollectionOldAcademicYears = em.merge(academicYearsCollectionOldAcademicYears);
                }
            }
            for (AcademicYears academicYearsCollectionNewAcademicYears : academicYearsCollectionNew) {
                if (!academicYearsCollectionOld.contains(academicYearsCollectionNewAcademicYears)) {
                    CompanyBranch oldBranchIdOfAcademicYearsCollectionNewAcademicYears = academicYearsCollectionNewAcademicYears.getBranchId();
                    academicYearsCollectionNewAcademicYears.setBranchId(companyBranch);
                    academicYearsCollectionNewAcademicYears = em.merge(academicYearsCollectionNewAcademicYears);
                    if (oldBranchIdOfAcademicYearsCollectionNewAcademicYears != null && !oldBranchIdOfAcademicYearsCollectionNewAcademicYears.equals(companyBranch)) {
                        oldBranchIdOfAcademicYearsCollectionNewAcademicYears.getAcademicYearsCollection().remove(academicYearsCollectionNewAcademicYears);
                        oldBranchIdOfAcademicYearsCollectionNewAcademicYears = em.merge(oldBranchIdOfAcademicYearsCollectionNewAcademicYears);
                    }
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionOldStudentDailyAttendance : studentDailyAttendanceCollectionOld) {
                if (!studentDailyAttendanceCollectionNew.contains(studentDailyAttendanceCollectionOldStudentDailyAttendance)) {
                    studentDailyAttendanceCollectionOldStudentDailyAttendance.setBranchId(null);
                    studentDailyAttendanceCollectionOldStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionOldStudentDailyAttendance);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendance : studentDailyAttendanceCollectionNew) {
                if (!studentDailyAttendanceCollectionOld.contains(studentDailyAttendanceCollectionNewStudentDailyAttendance)) {
                    CompanyBranch oldBranchIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = studentDailyAttendanceCollectionNewStudentDailyAttendance.getBranchId();
                    studentDailyAttendanceCollectionNewStudentDailyAttendance.setBranchId(companyBranch);
                    studentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                    if (oldBranchIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance != null && !oldBranchIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.equals(companyBranch)) {
                        oldBranchIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                        oldBranchIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(oldBranchIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance);
                    }
                }
            }
            for (ClassRoutine classRoutineCollectionOldClassRoutine : classRoutineCollectionOld) {
                if (!classRoutineCollectionNew.contains(classRoutineCollectionOldClassRoutine)) {
                    classRoutineCollectionOldClassRoutine.setBranchId(null);
                    classRoutineCollectionOldClassRoutine = em.merge(classRoutineCollectionOldClassRoutine);
                }
            }
            for (ClassRoutine classRoutineCollectionNewClassRoutine : classRoutineCollectionNew) {
                if (!classRoutineCollectionOld.contains(classRoutineCollectionNewClassRoutine)) {
                    CompanyBranch oldBranchIdOfClassRoutineCollectionNewClassRoutine = classRoutineCollectionNewClassRoutine.getBranchId();
                    classRoutineCollectionNewClassRoutine.setBranchId(companyBranch);
                    classRoutineCollectionNewClassRoutine = em.merge(classRoutineCollectionNewClassRoutine);
                    if (oldBranchIdOfClassRoutineCollectionNewClassRoutine != null && !oldBranchIdOfClassRoutineCollectionNewClassRoutine.equals(companyBranch)) {
                        oldBranchIdOfClassRoutineCollectionNewClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionNewClassRoutine);
                        oldBranchIdOfClassRoutineCollectionNewClassRoutine = em.merge(oldBranchIdOfClassRoutineCollectionNewClassRoutine);
                    }
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    examMarkCollectionOldExamMark.setBranchId(null);
                    examMarkCollectionOldExamMark = em.merge(examMarkCollectionOldExamMark);
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    CompanyBranch oldBranchIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getBranchId();
                    examMarkCollectionNewExamMark.setBranchId(companyBranch);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldBranchIdOfExamMarkCollectionNewExamMark != null && !oldBranchIdOfExamMarkCollectionNewExamMark.equals(companyBranch)) {
                        oldBranchIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldBranchIdOfExamMarkCollectionNewExamMark = em.merge(oldBranchIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (Term termCollectionOldTerm : termCollectionOld) {
                if (!termCollectionNew.contains(termCollectionOldTerm)) {
                    termCollectionOldTerm.setBranchId(null);
                    termCollectionOldTerm = em.merge(termCollectionOldTerm);
                }
            }
            for (Term termCollectionNewTerm : termCollectionNew) {
                if (!termCollectionOld.contains(termCollectionNewTerm)) {
                    CompanyBranch oldBranchIdOfTermCollectionNewTerm = termCollectionNewTerm.getBranchId();
                    termCollectionNewTerm.setBranchId(companyBranch);
                    termCollectionNewTerm = em.merge(termCollectionNewTerm);
                    if (oldBranchIdOfTermCollectionNewTerm != null && !oldBranchIdOfTermCollectionNewTerm.equals(companyBranch)) {
                        oldBranchIdOfTermCollectionNewTerm.getTermCollection().remove(termCollectionNewTerm);
                        oldBranchIdOfTermCollectionNewTerm = em.merge(oldBranchIdOfTermCollectionNewTerm);
                    }
                }
            }
            for (Department departmentCollectionOldDepartment : departmentCollectionOld) {
                if (!departmentCollectionNew.contains(departmentCollectionOldDepartment)) {
                    departmentCollectionOldDepartment.setBranchId(null);
                    departmentCollectionOldDepartment = em.merge(departmentCollectionOldDepartment);
                }
            }
            for (Department departmentCollectionNewDepartment : departmentCollectionNew) {
                if (!departmentCollectionOld.contains(departmentCollectionNewDepartment)) {
                    CompanyBranch oldBranchIdOfDepartmentCollectionNewDepartment = departmentCollectionNewDepartment.getBranchId();
                    departmentCollectionNewDepartment.setBranchId(companyBranch);
                    departmentCollectionNewDepartment = em.merge(departmentCollectionNewDepartment);
                    if (oldBranchIdOfDepartmentCollectionNewDepartment != null && !oldBranchIdOfDepartmentCollectionNewDepartment.equals(companyBranch)) {
                        oldBranchIdOfDepartmentCollectionNewDepartment.getDepartmentCollection().remove(departmentCollectionNewDepartment);
                        oldBranchIdOfDepartmentCollectionNewDepartment = em.merge(oldBranchIdOfDepartmentCollectionNewDepartment);
                    }
                }
            }
            for (Guardian guardianCollectionOldGuardian : guardianCollectionOld) {
                if (!guardianCollectionNew.contains(guardianCollectionOldGuardian)) {
                    guardianCollectionOldGuardian.setBranchId(null);
                    guardianCollectionOldGuardian = em.merge(guardianCollectionOldGuardian);
                }
            }
            for (Guardian guardianCollectionNewGuardian : guardianCollectionNew) {
                if (!guardianCollectionOld.contains(guardianCollectionNewGuardian)) {
                    CompanyBranch oldBranchIdOfGuardianCollectionNewGuardian = guardianCollectionNewGuardian.getBranchId();
                    guardianCollectionNewGuardian.setBranchId(companyBranch);
                    guardianCollectionNewGuardian = em.merge(guardianCollectionNewGuardian);
                    if (oldBranchIdOfGuardianCollectionNewGuardian != null && !oldBranchIdOfGuardianCollectionNewGuardian.equals(companyBranch)) {
                        oldBranchIdOfGuardianCollectionNewGuardian.getGuardianCollection().remove(guardianCollectionNewGuardian);
                        oldBranchIdOfGuardianCollectionNewGuardian = em.merge(oldBranchIdOfGuardianCollectionNewGuardian);
                    }
                }
            }
            for (Class classCollectionOldClass : classCollectionOld) {
                if (!classCollectionNew.contains(classCollectionOldClass)) {
                    classCollectionOldClass.setBranchId(null);
                    classCollectionOldClass = em.merge(classCollectionOldClass);
                }
            }
            for (Class classCollectionNewClass : classCollectionNew) {
                if (!classCollectionOld.contains(classCollectionNewClass)) {
                    CompanyBranch oldBranchIdOfClassCollectionNewClass = classCollectionNewClass.getBranchId();
                    classCollectionNewClass.setBranchId(companyBranch);
                    classCollectionNewClass = em.merge(classCollectionNewClass);
                    if (oldBranchIdOfClassCollectionNewClass != null && !oldBranchIdOfClassCollectionNewClass.equals(companyBranch)) {
                        oldBranchIdOfClassCollectionNewClass.getClassCollection().remove(classCollectionNewClass);
                        oldBranchIdOfClassCollectionNewClass = em.merge(oldBranchIdOfClassCollectionNewClass);
                    }
                }
            }
            for (ScratchCard scratchCardCollectionOldScratchCard : scratchCardCollectionOld) {
                if (!scratchCardCollectionNew.contains(scratchCardCollectionOldScratchCard)) {
                    scratchCardCollectionOldScratchCard.setBranchId(null);
                    scratchCardCollectionOldScratchCard = em.merge(scratchCardCollectionOldScratchCard);
                }
            }
            for (ScratchCard scratchCardCollectionNewScratchCard : scratchCardCollectionNew) {
                if (!scratchCardCollectionOld.contains(scratchCardCollectionNewScratchCard)) {
                    CompanyBranch oldBranchIdOfScratchCardCollectionNewScratchCard = scratchCardCollectionNewScratchCard.getBranchId();
                    scratchCardCollectionNewScratchCard.setBranchId(companyBranch);
                    scratchCardCollectionNewScratchCard = em.merge(scratchCardCollectionNewScratchCard);
                    if (oldBranchIdOfScratchCardCollectionNewScratchCard != null && !oldBranchIdOfScratchCardCollectionNewScratchCard.equals(companyBranch)) {
                        oldBranchIdOfScratchCardCollectionNewScratchCard.getScratchCardCollection().remove(scratchCardCollectionNewScratchCard);
                        oldBranchIdOfScratchCardCollectionNewScratchCard = em.merge(oldBranchIdOfScratchCardCollectionNewScratchCard);
                    }
                }
            }
            for (BehavouralTrait behavouralTraitCollectionOldBehavouralTrait : behavouralTraitCollectionOld) {
                if (!behavouralTraitCollectionNew.contains(behavouralTraitCollectionOldBehavouralTrait)) {
                    behavouralTraitCollectionOldBehavouralTrait.setBranchId(null);
                    behavouralTraitCollectionOldBehavouralTrait = em.merge(behavouralTraitCollectionOldBehavouralTrait);
                }
            }
            for (BehavouralTrait behavouralTraitCollectionNewBehavouralTrait : behavouralTraitCollectionNew) {
                if (!behavouralTraitCollectionOld.contains(behavouralTraitCollectionNewBehavouralTrait)) {
                    CompanyBranch oldBranchIdOfBehavouralTraitCollectionNewBehavouralTrait = behavouralTraitCollectionNewBehavouralTrait.getBranchId();
                    behavouralTraitCollectionNewBehavouralTrait.setBranchId(companyBranch);
                    behavouralTraitCollectionNewBehavouralTrait = em.merge(behavouralTraitCollectionNewBehavouralTrait);
                    if (oldBranchIdOfBehavouralTraitCollectionNewBehavouralTrait != null && !oldBranchIdOfBehavouralTraitCollectionNewBehavouralTrait.equals(companyBranch)) {
                        oldBranchIdOfBehavouralTraitCollectionNewBehavouralTrait.getBehavouralTraitCollection().remove(behavouralTraitCollectionNewBehavouralTrait);
                        oldBranchIdOfBehavouralTraitCollectionNewBehavouralTrait = em.merge(oldBranchIdOfBehavouralTraitCollectionNewBehavouralTrait);
                    }
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    caMarkCollectionOldCaMark.setBranchId(null);
                    caMarkCollectionOldCaMark = em.merge(caMarkCollectionOldCaMark);
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    CompanyBranch oldBranchIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getBranchId();
                    caMarkCollectionNewCaMark.setBranchId(companyBranch);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldBranchIdOfCaMarkCollectionNewCaMark != null && !oldBranchIdOfCaMarkCollectionNewCaMark.equals(companyBranch)) {
                        oldBranchIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldBranchIdOfCaMarkCollectionNewCaMark = em.merge(oldBranchIdOfCaMarkCollectionNewCaMark);
                    }
                }
            }
            for (BranchAddress branchAddressCollectionOldBranchAddress : branchAddressCollectionOld) {
                if (!branchAddressCollectionNew.contains(branchAddressCollectionOldBranchAddress)) {
                    branchAddressCollectionOldBranchAddress.setBranchId(null);
                    branchAddressCollectionOldBranchAddress = em.merge(branchAddressCollectionOldBranchAddress);
                }
            }
            for (BranchAddress branchAddressCollectionNewBranchAddress : branchAddressCollectionNew) {
                if (!branchAddressCollectionOld.contains(branchAddressCollectionNewBranchAddress)) {
                    CompanyBranch oldBranchIdOfBranchAddressCollectionNewBranchAddress = branchAddressCollectionNewBranchAddress.getBranchId();
                    branchAddressCollectionNewBranchAddress.setBranchId(companyBranch);
                    branchAddressCollectionNewBranchAddress = em.merge(branchAddressCollectionNewBranchAddress);
                    if (oldBranchIdOfBranchAddressCollectionNewBranchAddress != null && !oldBranchIdOfBranchAddressCollectionNewBranchAddress.equals(companyBranch)) {
                        oldBranchIdOfBranchAddressCollectionNewBranchAddress.getBranchAddressCollection().remove(branchAddressCollectionNewBranchAddress);
                        oldBranchIdOfBranchAddressCollectionNewBranchAddress = em.merge(oldBranchIdOfBranchAddressCollectionNewBranchAddress);
                    }
                }
            }
            for (MiscellaneousExpense miscellaneousExpenseCollectionOldMiscellaneousExpense : miscellaneousExpenseCollectionOld) {
                if (!miscellaneousExpenseCollectionNew.contains(miscellaneousExpenseCollectionOldMiscellaneousExpense)) {
                    miscellaneousExpenseCollectionOldMiscellaneousExpense.setBranchId(null);
                    miscellaneousExpenseCollectionOldMiscellaneousExpense = em.merge(miscellaneousExpenseCollectionOldMiscellaneousExpense);
                }
            }
            for (MiscellaneousExpense miscellaneousExpenseCollectionNewMiscellaneousExpense : miscellaneousExpenseCollectionNew) {
                if (!miscellaneousExpenseCollectionOld.contains(miscellaneousExpenseCollectionNewMiscellaneousExpense)) {
                    CompanyBranch oldBranchIdOfMiscellaneousExpenseCollectionNewMiscellaneousExpense = miscellaneousExpenseCollectionNewMiscellaneousExpense.getBranchId();
                    miscellaneousExpenseCollectionNewMiscellaneousExpense.setBranchId(companyBranch);
                    miscellaneousExpenseCollectionNewMiscellaneousExpense = em.merge(miscellaneousExpenseCollectionNewMiscellaneousExpense);
                    if (oldBranchIdOfMiscellaneousExpenseCollectionNewMiscellaneousExpense != null && !oldBranchIdOfMiscellaneousExpenseCollectionNewMiscellaneousExpense.equals(companyBranch)) {
                        oldBranchIdOfMiscellaneousExpenseCollectionNewMiscellaneousExpense.getMiscellaneousExpenseCollection().remove(miscellaneousExpenseCollectionNewMiscellaneousExpense);
                        oldBranchIdOfMiscellaneousExpenseCollectionNewMiscellaneousExpense = em.merge(oldBranchIdOfMiscellaneousExpenseCollectionNewMiscellaneousExpense);
                    }
                }
            }
            for (Invoice invoiceCollectionOldInvoice : invoiceCollectionOld) {
                if (!invoiceCollectionNew.contains(invoiceCollectionOldInvoice)) {
                    invoiceCollectionOldInvoice.setBranchId(null);
                    invoiceCollectionOldInvoice = em.merge(invoiceCollectionOldInvoice);
                }
            }
            for (Invoice invoiceCollectionNewInvoice : invoiceCollectionNew) {
                if (!invoiceCollectionOld.contains(invoiceCollectionNewInvoice)) {
                    CompanyBranch oldBranchIdOfInvoiceCollectionNewInvoice = invoiceCollectionNewInvoice.getBranchId();
                    invoiceCollectionNewInvoice.setBranchId(companyBranch);
                    invoiceCollectionNewInvoice = em.merge(invoiceCollectionNewInvoice);
                    if (oldBranchIdOfInvoiceCollectionNewInvoice != null && !oldBranchIdOfInvoiceCollectionNewInvoice.equals(companyBranch)) {
                        oldBranchIdOfInvoiceCollectionNewInvoice.getInvoiceCollection().remove(invoiceCollectionNewInvoice);
                        oldBranchIdOfInvoiceCollectionNewInvoice = em.merge(oldBranchIdOfInvoiceCollectionNewInvoice);
                    }
                }
            }
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys : behavouralTraitRatingKeysCollectionOld) {
                if (!behavouralTraitRatingKeysCollectionNew.contains(behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys)) {
                    behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys.setBranchId(null);
                    behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys);
                }
            }
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys : behavouralTraitRatingKeysCollectionNew) {
                if (!behavouralTraitRatingKeysCollectionOld.contains(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys)) {
                    CompanyBranch oldBranchIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys = behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.getBranchId();
                    behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.setBranchId(companyBranch);
                    behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys);
                    if (oldBranchIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys != null && !oldBranchIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.equals(companyBranch)) {
                        oldBranchIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys);
                        oldBranchIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys = em.merge(oldBranchIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys);
                    }
                }
            }
            for (EmployeeAttendance employeeAttendanceCollectionOldEmployeeAttendance : employeeAttendanceCollectionOld) {
                if (!employeeAttendanceCollectionNew.contains(employeeAttendanceCollectionOldEmployeeAttendance)) {
                    employeeAttendanceCollectionOldEmployeeAttendance.setBranchId(null);
                    employeeAttendanceCollectionOldEmployeeAttendance = em.merge(employeeAttendanceCollectionOldEmployeeAttendance);
                }
            }
            for (EmployeeAttendance employeeAttendanceCollectionNewEmployeeAttendance : employeeAttendanceCollectionNew) {
                if (!employeeAttendanceCollectionOld.contains(employeeAttendanceCollectionNewEmployeeAttendance)) {
                    CompanyBranch oldBranchIdOfEmployeeAttendanceCollectionNewEmployeeAttendance = employeeAttendanceCollectionNewEmployeeAttendance.getBranchId();
                    employeeAttendanceCollectionNewEmployeeAttendance.setBranchId(companyBranch);
                    employeeAttendanceCollectionNewEmployeeAttendance = em.merge(employeeAttendanceCollectionNewEmployeeAttendance);
                    if (oldBranchIdOfEmployeeAttendanceCollectionNewEmployeeAttendance != null && !oldBranchIdOfEmployeeAttendanceCollectionNewEmployeeAttendance.equals(companyBranch)) {
                        oldBranchIdOfEmployeeAttendanceCollectionNewEmployeeAttendance.getEmployeeAttendanceCollection().remove(employeeAttendanceCollectionNewEmployeeAttendance);
                        oldBranchIdOfEmployeeAttendanceCollectionNewEmployeeAttendance = em.merge(oldBranchIdOfEmployeeAttendanceCollectionNewEmployeeAttendance);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = companyBranch.getBranchId();
                if (findCompanyBranch(id) == null) {
                    throw new NonexistentEntityException("The companyBranch with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch companyBranch;
            try {
                companyBranch = em.getReference(CompanyBranch.class, id);
                companyBranch.getBranchId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The companyBranch with id " + id + " no longer exists.", enfe);
            }
            Company companyId = companyBranch.getCompanyId();
            if (companyId != null) {
                companyId.getCompanyBranchCollection().remove(companyBranch);
                companyId = em.merge(companyId);
            }
            BranchAddress addressId = companyBranch.getAddressId();
            if (addressId != null) {
                addressId.getCompanyBranchCollection().remove(companyBranch);
                addressId = em.merge(addressId);
            }
            BranchPhone phoneId = companyBranch.getPhoneId();
            if (phoneId != null) {
                phoneId.getCompanyBranchCollection().remove(companyBranch);
                phoneId = em.merge(phoneId);
            }
            BranchEmail emailId = companyBranch.getEmailId();
            if (emailId != null) {
                emailId.getCompanyBranchCollection().remove(companyBranch);
                emailId = em.merge(emailId);
            }
            BranchFax faxId = companyBranch.getFaxId();
            if (faxId != null) {
                faxId.getCompanyBranchCollection().remove(companyBranch);
                faxId = em.merge(faxId);
            }
            Collection<Subject> subjectCollection = companyBranch.getSubjectCollection();
            for (Subject subjectCollectionSubject : subjectCollection) {
                subjectCollectionSubject.setBranchId(null);
                subjectCollectionSubject = em.merge(subjectCollectionSubject);
            }
            Collection<EmployeeAward> employeeAwardCollection = companyBranch.getEmployeeAwardCollection();
            for (EmployeeAward employeeAwardCollectionEmployeeAward : employeeAwardCollection) {
                employeeAwardCollectionEmployeeAward.setBranchId(null);
                employeeAwardCollectionEmployeeAward = em.merge(employeeAwardCollectionEmployeeAward);
            }
            Collection<Section> sectionCollection = companyBranch.getSectionCollection();
            for (Section sectionCollectionSection : sectionCollection) {
                sectionCollectionSection.setBranchId(null);
                sectionCollectionSection = em.merge(sectionCollectionSection);
            }
            Collection<Employee> employeeCollection = companyBranch.getEmployeeCollection();
            for (Employee employeeCollectionEmployee : employeeCollection) {
                employeeCollectionEmployee.setBranchId(null);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
            }
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollection = companyBranch.getPrincipalTerminalExamCommentCollection();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamComment : principalTerminalExamCommentCollection) {
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment.setBranchId(null);
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
            }
            Collection<UtilityBillDetails> utilityBillDetailsCollection = companyBranch.getUtilityBillDetailsCollection();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : utilityBillDetailsCollection) {
                utilityBillDetailsCollectionUtilityBillDetails.setBranchId(null);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
            }
            Collection<ScratchCardType> scratchCardTypeCollection = companyBranch.getScratchCardTypeCollection();
            for (ScratchCardType scratchCardTypeCollectionScratchCardType : scratchCardTypeCollection) {
                scratchCardTypeCollectionScratchCardType.setBranchId(null);
                scratchCardTypeCollectionScratchCardType = em.merge(scratchCardTypeCollectionScratchCardType);
            }
            Collection<BankInstitution> bankInstitutionCollection = companyBranch.getBankInstitutionCollection();
            for (BankInstitution bankInstitutionCollectionBankInstitution : bankInstitutionCollection) {
                bankInstitutionCollectionBankInstitution.setBranchId(null);
                bankInstitutionCollectionBankInstitution = em.merge(bankInstitutionCollectionBankInstitution);
            }
            Collection<ClassCategory> classCategoryCollection = companyBranch.getClassCategoryCollection();
            for (ClassCategory classCategoryCollectionClassCategory : classCategoryCollection) {
                classCategoryCollectionClassCategory.setBranchId(null);
                classCategoryCollectionClassCategory = em.merge(classCategoryCollectionClassCategory);
            }
            Collection<EmployeePayroll> employeePayrollCollection = companyBranch.getEmployeePayrollCollection();
            for (EmployeePayroll employeePayrollCollectionEmployeePayroll : employeePayrollCollection) {
                employeePayrollCollectionEmployeePayroll.setBranchId(null);
                employeePayrollCollectionEmployeePayroll = em.merge(employeePayrollCollectionEmployeePayroll);
            }
            Collection<BankAccountBalance> bankAccountBalanceCollection = companyBranch.getBankAccountBalanceCollection();
            for (BankAccountBalance bankAccountBalanceCollectionBankAccountBalance : bankAccountBalanceCollection) {
                bankAccountBalanceCollectionBankAccountBalance.setBranchId(null);
                bankAccountBalanceCollectionBankAccountBalance = em.merge(bankAccountBalanceCollectionBankAccountBalance);
            }
            Collection<BankAccount> bankAccountCollection = companyBranch.getBankAccountCollection();
            for (BankAccount bankAccountCollectionBankAccount : bankAccountCollection) {
                bankAccountCollectionBankAccount.setBranchId(null);
                bankAccountCollectionBankAccount = em.merge(bankAccountCollectionBankAccount);
            }
            Collection<SubjectAttendance> subjectAttendanceCollection = companyBranch.getSubjectAttendanceCollection();
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendance : subjectAttendanceCollection) {
                subjectAttendanceCollectionSubjectAttendance.setBranchId(null);
                subjectAttendanceCollectionSubjectAttendance = em.merge(subjectAttendanceCollectionSubjectAttendance);
            }
            Collection<AccountTransaction> accountTransactionCollection = companyBranch.getAccountTransactionCollection();
            for (AccountTransaction accountTransactionCollectionAccountTransaction : accountTransactionCollection) {
                accountTransactionCollectionAccountTransaction.setBranchId(null);
                accountTransactionCollectionAccountTransaction = em.merge(accountTransactionCollectionAccountTransaction);
            }
            Collection<AuditVault> auditVaultCollection = companyBranch.getAuditVaultCollection();
            for (AuditVault auditVaultCollectionAuditVault : auditVaultCollection) {
                auditVaultCollectionAuditVault.setBranchId(null);
                auditVaultCollectionAuditVault = em.merge(auditVaultCollectionAuditVault);
            }
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection = companyBranch.getScratchCardSalesOrderCollection();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrder : scratchCardSalesOrderCollection) {
                scratchCardSalesOrderCollectionScratchCardSalesOrder.setBranchId(null);
                scratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionScratchCardSalesOrder);
            }
            Collection<BranchFax> branchFaxCollection = companyBranch.getBranchFaxCollection();
            for (BranchFax branchFaxCollectionBranchFax : branchFaxCollection) {
                branchFaxCollectionBranchFax.setBranchId(null);
                branchFaxCollectionBranchFax = em.merge(branchFaxCollectionBranchFax);
            }
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollection = companyBranch.getFormMasterTerminalExamCommentCollection();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : formMasterTerminalExamCommentCollection) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setBranchId(null);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
            }
            Collection<TransactionCancellation> transactionCancellationCollection = companyBranch.getTransactionCancellationCollection();
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellation : transactionCancellationCollection) {
                transactionCancellationCollectionTransactionCancellation.setBranchId(null);
                transactionCancellationCollectionTransactionCancellation = em.merge(transactionCancellationCollectionTransactionCancellation);
            }
            Collection<Users> usersCollection = companyBranch.getUsersCollection();
            for (Users usersCollectionUsers : usersCollection) {
                usersCollectionUsers.setBranchId(null);
                usersCollectionUsers = em.merge(usersCollectionUsers);
            }
            Collection<BranchEmail> branchEmailCollection = companyBranch.getBranchEmailCollection();
            for (BranchEmail branchEmailCollectionBranchEmail : branchEmailCollection) {
                branchEmailCollectionBranchEmail.setBranchId(null);
                branchEmailCollectionBranchEmail = em.merge(branchEmailCollectionBranchEmail);
            }
            Collection<Exam> examCollection = companyBranch.getExamCollection();
            for (Exam examCollectionExam : examCollection) {
                examCollectionExam.setBranchId(null);
                examCollectionExam = em.merge(examCollectionExam);
            }
            Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollection = companyBranch.getScratchCardSalesOrderDetailsCollection();
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails : scratchCardSalesOrderDetailsCollection) {
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.setBranchId(null);
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
            }
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollection = companyBranch.getStudentBehavouralTraitCollection();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : studentBehavouralTraitCollection) {
                studentBehavouralTraitCollectionStudentBehavouralTrait.setBranchId(null);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
            }
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection = companyBranch.getBankAccountPrivilegeCollection();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilege : bankAccountPrivilegeCollection) {
                bankAccountPrivilegeCollectionBankAccountPrivilege.setBranchId(null);
                bankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionBankAccountPrivilege);
            }
            Collection<ClassSubjects> classSubjectsCollection = companyBranch.getClassSubjectsCollection();
            for (ClassSubjects classSubjectsCollectionClassSubjects : classSubjectsCollection) {
                classSubjectsCollectionClassSubjects.setBranchId(null);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
            }
            Collection<PurchaseOrder> purchaseOrderCollection = companyBranch.getPurchaseOrderCollection();
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrder : purchaseOrderCollection) {
                purchaseOrderCollectionPurchaseOrder.setBranchId(null);
                purchaseOrderCollectionPurchaseOrder = em.merge(purchaseOrderCollectionPurchaseOrder);
            }
            Collection<Grade> gradeCollection = companyBranch.getGradeCollection();
            for (Grade gradeCollectionGrade : gradeCollection) {
                gradeCollectionGrade.setBranchId(null);
                gradeCollectionGrade = em.merge(gradeCollectionGrade);
            }
            Collection<BranchPhone> branchPhoneCollection = companyBranch.getBranchPhoneCollection();
            for (BranchPhone branchPhoneCollectionBranchPhone : branchPhoneCollection) {
                branchPhoneCollectionBranchPhone.setBranchId(null);
                branchPhoneCollectionBranchPhone = em.merge(branchPhoneCollectionBranchPhone);
            }
            Collection<Designation> designationCollection = companyBranch.getDesignationCollection();
            for (Designation designationCollectionDesignation : designationCollection) {
                designationCollectionDesignation.setBranchId(null);
                designationCollectionDesignation = em.merge(designationCollectionDesignation);
            }
            Collection<ExamClassPosition> examClassPositionCollection = companyBranch.getExamClassPositionCollection();
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : examClassPositionCollection) {
                examClassPositionCollectionExamClassPosition.setBranchId(null);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
            }
            Collection<EmployeeBank> employeeBankCollection = companyBranch.getEmployeeBankCollection();
            for (EmployeeBank employeeBankCollectionEmployeeBank : employeeBankCollection) {
                employeeBankCollectionEmployeeBank.setBranchId(null);
                employeeBankCollectionEmployeeBank = em.merge(employeeBankCollectionEmployeeBank);
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection = companyBranch.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollection) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setBranchId(null);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
            }
            Collection<Student> studentCollection = companyBranch.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setBranchId(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollection = companyBranch.getScratchCardUnitPriceCollection();
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionScratchCardUnitPrice : scratchCardUnitPriceCollection) {
                scratchCardUnitPriceCollectionScratchCardUnitPrice.setBranchId(null);
                scratchCardUnitPriceCollectionScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionScratchCardUnitPrice);
            }
            Collection<AcademicYears> academicYearsCollection = companyBranch.getAcademicYearsCollection();
            for (AcademicYears academicYearsCollectionAcademicYears : academicYearsCollection) {
                academicYearsCollectionAcademicYears.setBranchId(null);
                academicYearsCollectionAcademicYears = em.merge(academicYearsCollectionAcademicYears);
            }
            Collection<StudentDailyAttendance> studentDailyAttendanceCollection = companyBranch.getStudentDailyAttendanceCollection();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : studentDailyAttendanceCollection) {
                studentDailyAttendanceCollectionStudentDailyAttendance.setBranchId(null);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
            }
            Collection<ClassRoutine> classRoutineCollection = companyBranch.getClassRoutineCollection();
            for (ClassRoutine classRoutineCollectionClassRoutine : classRoutineCollection) {
                classRoutineCollectionClassRoutine.setBranchId(null);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
            }
            Collection<ExamMark> examMarkCollection = companyBranch.getExamMarkCollection();
            for (ExamMark examMarkCollectionExamMark : examMarkCollection) {
                examMarkCollectionExamMark.setBranchId(null);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
            }
            Collection<Term> termCollection = companyBranch.getTermCollection();
            for (Term termCollectionTerm : termCollection) {
                termCollectionTerm.setBranchId(null);
                termCollectionTerm = em.merge(termCollectionTerm);
            }
            Collection<Department> departmentCollection = companyBranch.getDepartmentCollection();
            for (Department departmentCollectionDepartment : departmentCollection) {
                departmentCollectionDepartment.setBranchId(null);
                departmentCollectionDepartment = em.merge(departmentCollectionDepartment);
            }
            Collection<Guardian> guardianCollection = companyBranch.getGuardianCollection();
            for (Guardian guardianCollectionGuardian : guardianCollection) {
                guardianCollectionGuardian.setBranchId(null);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
            }
            Collection<Class> classCollection = companyBranch.getClassCollection();
            for (Class classCollectionClass : classCollection) {
                classCollectionClass.setBranchId(null);
                classCollectionClass = em.merge(classCollectionClass);
            }
            Collection<ScratchCard> scratchCardCollection = companyBranch.getScratchCardCollection();
            for (ScratchCard scratchCardCollectionScratchCard : scratchCardCollection) {
                scratchCardCollectionScratchCard.setBranchId(null);
                scratchCardCollectionScratchCard = em.merge(scratchCardCollectionScratchCard);
            }
            Collection<BehavouralTrait> behavouralTraitCollection = companyBranch.getBehavouralTraitCollection();
            for (BehavouralTrait behavouralTraitCollectionBehavouralTrait : behavouralTraitCollection) {
                behavouralTraitCollectionBehavouralTrait.setBranchId(null);
                behavouralTraitCollectionBehavouralTrait = em.merge(behavouralTraitCollectionBehavouralTrait);
            }
            Collection<CaMark> caMarkCollection = companyBranch.getCaMarkCollection();
            for (CaMark caMarkCollectionCaMark : caMarkCollection) {
                caMarkCollectionCaMark.setBranchId(null);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
            }
            Collection<BranchAddress> branchAddressCollection = companyBranch.getBranchAddressCollection();
            for (BranchAddress branchAddressCollectionBranchAddress : branchAddressCollection) {
                branchAddressCollectionBranchAddress.setBranchId(null);
                branchAddressCollectionBranchAddress = em.merge(branchAddressCollectionBranchAddress);
            }
            Collection<MiscellaneousExpense> miscellaneousExpenseCollection = companyBranch.getMiscellaneousExpenseCollection();
            for (MiscellaneousExpense miscellaneousExpenseCollectionMiscellaneousExpense : miscellaneousExpenseCollection) {
                miscellaneousExpenseCollectionMiscellaneousExpense.setBranchId(null);
                miscellaneousExpenseCollectionMiscellaneousExpense = em.merge(miscellaneousExpenseCollectionMiscellaneousExpense);
            }
            Collection<Invoice> invoiceCollection = companyBranch.getInvoiceCollection();
            for (Invoice invoiceCollectionInvoice : invoiceCollection) {
                invoiceCollectionInvoice.setBranchId(null);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
            }
            Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollection = companyBranch.getBehavouralTraitRatingKeysCollection();
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys : behavouralTraitRatingKeysCollection) {
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.setBranchId(null);
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
            }
            Collection<EmployeeAttendance> employeeAttendanceCollection = companyBranch.getEmployeeAttendanceCollection();
            for (EmployeeAttendance employeeAttendanceCollectionEmployeeAttendance : employeeAttendanceCollection) {
                employeeAttendanceCollectionEmployeeAttendance.setBranchId(null);
                employeeAttendanceCollectionEmployeeAttendance = em.merge(employeeAttendanceCollectionEmployeeAttendance);
            }
            em.remove(companyBranch);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CompanyBranch> findCompanyBranchEntities() {
        return findCompanyBranchEntities(true, -1, -1);
    }

    public List<CompanyBranch> findCompanyBranchEntities(int maxResults, int firstResult) {
        return findCompanyBranchEntities(false, maxResults, firstResult);
    }

    private List<CompanyBranch> findCompanyBranchEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompanyBranch.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CompanyBranch findCompanyBranch(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompanyBranch.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyBranchCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompanyBranch> rt = cq.from(CompanyBranch.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public CompanyBranch findByBranchId(long branch_id) 
    {
        EntityManager em = getEntityManager();
        try {
            Query findByBranchId = em.createNamedQuery("CompanyBranch.findByBranchId");
            findByBranchId.setParameter("branchId", branch_id);
            CompanyBranch companyBranch = new CompanyBranch();
            companyBranch = (CompanyBranch) findByBranchId.getSingleResult();
            return companyBranch;
        }catch(Exception ex){
             System.out.println("-------Exception Occoured-------"+ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             CompanyBranch companyBranch = new CompanyBranch();
             return companyBranch;
        } finally {
            em.close();
        }
    }
}
