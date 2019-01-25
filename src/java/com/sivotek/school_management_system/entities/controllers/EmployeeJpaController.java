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
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.Countries;
import com.sivotek.school_management_system.entities.Cities;
import com.sivotek.school_management_system.entities.Designation;
import com.sivotek.school_management_system.entities.EmployeeAward;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.EmployeePayroll;
import com.sivotek.school_management_system.entities.AccountTransaction;
import com.sivotek.school_management_system.entities.TransactionCancellation;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.BankAccountPrivilege;
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.EmployeeBank;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.TransactionCancellationProof;
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.EmployeeAttendance;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
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
public class EmployeeJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(EmployeeJpaController.class.getName());
      
    public EmployeeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public EmployeeJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(Employee employee) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (employee.getEmployeeAwardCollection() == null) {
            employee.setEmployeeAwardCollection(new ArrayList<EmployeeAward>());
        }
        if (employee.getEmployeePayrollCollection() == null) {
            employee.setEmployeePayrollCollection(new ArrayList<EmployeePayroll>());
        }
        if (employee.getAccountTransactionCollection() == null) {
            employee.setAccountTransactionCollection(new ArrayList<AccountTransaction>());
        }
        if (employee.getTransactionCancellationCollection() == null) {
            employee.setTransactionCancellationCollection(new ArrayList<TransactionCancellation>());
        }
        if (employee.getUsersCollection() == null) {
            employee.setUsersCollection(new ArrayList<Users>());
        }
        if (employee.getBankAccountPrivilegeCollection() == null) {
            employee.setBankAccountPrivilegeCollection(new ArrayList<BankAccountPrivilege>());
        }
        if (employee.getBankAccountPrivilegeCollection1() == null) {
            employee.setBankAccountPrivilegeCollection1(new ArrayList<BankAccountPrivilege>());
        }
        if (employee.getBankAccountPrivilegeCollection2() == null) {
            employee.setBankAccountPrivilegeCollection2(new ArrayList<BankAccountPrivilege>());
        }
        if (employee.getClassSubjectsCollection() == null) {
            employee.setClassSubjectsCollection(new ArrayList<ClassSubjects>());
        }
        if (employee.getEmployeeBankCollection() == null) {
            employee.setEmployeeBankCollection(new ArrayList<EmployeeBank>());
        }
        if (employee.getSchoolFeeInvoiceDetailsCollection() == null) {
            employee.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (employee.getSchoolFeeInvoiceDetailsCollection1() == null) {
            employee.setSchoolFeeInvoiceDetailsCollection1(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (employee.getClassCollection() == null) {
            employee.setClassCollection(new ArrayList<Class>());
        }
        if (employee.getTransactionCancellationProofCollection() == null) {
            employee.setTransactionCancellationProofCollection(new ArrayList<TransactionCancellationProof>());
        }
        if (employee.getInvoiceCollection() == null) {
            employee.setInvoiceCollection(new ArrayList<Invoice>());
        }
        if (employee.getInvoiceCollection1() == null) {
            employee.setInvoiceCollection1(new ArrayList<Invoice>());
        }
        if (employee.getEmployeeAttendanceCollection() == null) {
            employee.setEmployeeAttendanceCollection(new ArrayList<EmployeeAttendance>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = employee.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                employee.setBranchId(branchId);
            }
            Countries nationality = employee.getNationality();
            if (nationality != null) {
                nationality = em.getReference(nationality.getClass(), nationality.getId());
                employee.setNationality(nationality);
            }
            Cities city = employee.getCity();
            if (city != null) {
                city = em.getReference(city.getClass(), city.getId());
                employee.setCity(city);
            }
            Countries countryId = employee.getCountryId();
            if (countryId != null) {
                countryId = em.getReference(countryId.getClass(), countryId.getId());
                employee.setCountryId(countryId);
            }
            Designation designationId = employee.getDesignationId();
            if (designationId != null) {
                designationId = em.getReference(designationId.getClass(), designationId.getDesignationId());
                employee.setDesignationId(designationId);
            }
            Collection<EmployeeAward> attachedEmployeeAwardCollection = new ArrayList<EmployeeAward>();
            for (EmployeeAward employeeAwardCollectionEmployeeAwardToAttach : employee.getEmployeeAwardCollection()) {
                employeeAwardCollectionEmployeeAwardToAttach = em.getReference(employeeAwardCollectionEmployeeAwardToAttach.getClass(), employeeAwardCollectionEmployeeAwardToAttach.getEmployeeAwardId());
                attachedEmployeeAwardCollection.add(employeeAwardCollectionEmployeeAwardToAttach);
            }
            employee.setEmployeeAwardCollection(attachedEmployeeAwardCollection);
            Collection<EmployeePayroll> attachedEmployeePayrollCollection = new ArrayList<EmployeePayroll>();
            for (EmployeePayroll employeePayrollCollectionEmployeePayrollToAttach : employee.getEmployeePayrollCollection()) {
                employeePayrollCollectionEmployeePayrollToAttach = em.getReference(employeePayrollCollectionEmployeePayrollToAttach.getClass(), employeePayrollCollectionEmployeePayrollToAttach.getPayrollId());
                attachedEmployeePayrollCollection.add(employeePayrollCollectionEmployeePayrollToAttach);
            }
            employee.setEmployeePayrollCollection(attachedEmployeePayrollCollection);
            Collection<AccountTransaction> attachedAccountTransactionCollection = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionAccountTransactionToAttach : employee.getAccountTransactionCollection()) {
                accountTransactionCollectionAccountTransactionToAttach = em.getReference(accountTransactionCollectionAccountTransactionToAttach.getClass(), accountTransactionCollectionAccountTransactionToAttach.getId());
                attachedAccountTransactionCollection.add(accountTransactionCollectionAccountTransactionToAttach);
            }
            employee.setAccountTransactionCollection(attachedAccountTransactionCollection);
            Collection<TransactionCancellation> attachedTransactionCancellationCollection = new ArrayList<TransactionCancellation>();
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellationToAttach : employee.getTransactionCancellationCollection()) {
                transactionCancellationCollectionTransactionCancellationToAttach = em.getReference(transactionCancellationCollectionTransactionCancellationToAttach.getClass(), transactionCancellationCollectionTransactionCancellationToAttach.getId());
                attachedTransactionCancellationCollection.add(transactionCancellationCollectionTransactionCancellationToAttach);
            }
            employee.setTransactionCancellationCollection(attachedTransactionCancellationCollection);
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : employee.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getId());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            employee.setUsersCollection(attachedUsersCollection);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach : employee.getBankAccountPrivilegeCollection()) {
                bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection.add(bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach);
            }
            employee.setBankAccountPrivilegeCollection(attachedBankAccountPrivilegeCollection);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection1 = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollection1BankAccountPrivilegeToAttach : employee.getBankAccountPrivilegeCollection1()) {
                bankAccountPrivilegeCollection1BankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollection1BankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollection1BankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection1.add(bankAccountPrivilegeCollection1BankAccountPrivilegeToAttach);
            }
            employee.setBankAccountPrivilegeCollection1(attachedBankAccountPrivilegeCollection1);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection2 = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollection2BankAccountPrivilegeToAttach : employee.getBankAccountPrivilegeCollection2()) {
                bankAccountPrivilegeCollection2BankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollection2BankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollection2BankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection2.add(bankAccountPrivilegeCollection2BankAccountPrivilegeToAttach);
            }
            employee.setBankAccountPrivilegeCollection2(attachedBankAccountPrivilegeCollection2);
            Collection<ClassSubjects> attachedClassSubjectsCollection = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionClassSubjectsToAttach : employee.getClassSubjectsCollection()) {
                classSubjectsCollectionClassSubjectsToAttach = em.getReference(classSubjectsCollectionClassSubjectsToAttach.getClass(), classSubjectsCollectionClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollection.add(classSubjectsCollectionClassSubjectsToAttach);
            }
            employee.setClassSubjectsCollection(attachedClassSubjectsCollection);
            Collection<EmployeeBank> attachedEmployeeBankCollection = new ArrayList<EmployeeBank>();
            for (EmployeeBank employeeBankCollectionEmployeeBankToAttach : employee.getEmployeeBankCollection()) {
                employeeBankCollectionEmployeeBankToAttach = em.getReference(employeeBankCollectionEmployeeBankToAttach.getClass(), employeeBankCollectionEmployeeBankToAttach.getEmployeeBankId());
                attachedEmployeeBankCollection.add(employeeBankCollectionEmployeeBankToAttach);
            }
            employee.setEmployeeBankCollection(attachedEmployeeBankCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : employee.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            employee.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection1 = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetailsToAttach : employee.getSchoolFeeInvoiceDetailsCollection1()) {
                schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection1.add(schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetailsToAttach);
            }
            employee.setSchoolFeeInvoiceDetailsCollection1(attachedSchoolFeeInvoiceDetailsCollection1);
            Collection<Class> attachedClassCollection = new ArrayList<Class>();
            for (Class classCollectionClassToAttach : employee.getClassCollection()) {
                classCollectionClassToAttach = em.getReference(classCollectionClassToAttach.getClass(), classCollectionClassToAttach.getClassId());
                attachedClassCollection.add(classCollectionClassToAttach);
            }
            employee.setClassCollection(attachedClassCollection);
            Collection<TransactionCancellationProof> attachedTransactionCancellationProofCollection = new ArrayList<TransactionCancellationProof>();
            for (TransactionCancellationProof transactionCancellationProofCollectionTransactionCancellationProofToAttach : employee.getTransactionCancellationProofCollection()) {
                transactionCancellationProofCollectionTransactionCancellationProofToAttach = em.getReference(transactionCancellationProofCollectionTransactionCancellationProofToAttach.getClass(), transactionCancellationProofCollectionTransactionCancellationProofToAttach.getId());
                attachedTransactionCancellationProofCollection.add(transactionCancellationProofCollectionTransactionCancellationProofToAttach);
            }
            employee.setTransactionCancellationProofCollection(attachedTransactionCancellationProofCollection);
            Collection<Invoice> attachedInvoiceCollection = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionInvoiceToAttach : employee.getInvoiceCollection()) {
                invoiceCollectionInvoiceToAttach = em.getReference(invoiceCollectionInvoiceToAttach.getClass(), invoiceCollectionInvoiceToAttach.getId());
                attachedInvoiceCollection.add(invoiceCollectionInvoiceToAttach);
            }
            employee.setInvoiceCollection(attachedInvoiceCollection);
            Collection<Invoice> attachedInvoiceCollection1 = new ArrayList<Invoice>();
            for (Invoice invoiceCollection1InvoiceToAttach : employee.getInvoiceCollection1()) {
                invoiceCollection1InvoiceToAttach = em.getReference(invoiceCollection1InvoiceToAttach.getClass(), invoiceCollection1InvoiceToAttach.getId());
                attachedInvoiceCollection1.add(invoiceCollection1InvoiceToAttach);
            }
            employee.setInvoiceCollection1(attachedInvoiceCollection1);
            Collection<EmployeeAttendance> attachedEmployeeAttendanceCollection = new ArrayList<EmployeeAttendance>();
            for (EmployeeAttendance employeeAttendanceCollectionEmployeeAttendanceToAttach : employee.getEmployeeAttendanceCollection()) {
                employeeAttendanceCollectionEmployeeAttendanceToAttach = em.getReference(employeeAttendanceCollectionEmployeeAttendanceToAttach.getClass(), employeeAttendanceCollectionEmployeeAttendanceToAttach.getAttendanceId());
                attachedEmployeeAttendanceCollection.add(employeeAttendanceCollectionEmployeeAttendanceToAttach);
            }
            employee.setEmployeeAttendanceCollection(attachedEmployeeAttendanceCollection);
            em.persist(employee);
            if (branchId != null) {
                branchId.getEmployeeCollection().add(employee);
                branchId = em.merge(branchId);
            }
            if (nationality != null) {
                nationality.getEmployeeCollection().add(employee);
                nationality = em.merge(nationality);
            }
            if (city != null) {
                city.getEmployeeCollection().add(employee);
                city = em.merge(city);
            }
            if (countryId != null) {
                countryId.getEmployeeCollection().add(employee);
                countryId = em.merge(countryId);
            }
            if (designationId != null) {
                designationId.getEmployeeCollection().add(employee);
                designationId = em.merge(designationId);
            }
            for (EmployeeAward employeeAwardCollectionEmployeeAward : employee.getEmployeeAwardCollection()) {
                Employee oldEmployeeIdOfEmployeeAwardCollectionEmployeeAward = employeeAwardCollectionEmployeeAward.getEmployeeId();
                employeeAwardCollectionEmployeeAward.setEmployeeId(employee);
                employeeAwardCollectionEmployeeAward = em.merge(employeeAwardCollectionEmployeeAward);
                if (oldEmployeeIdOfEmployeeAwardCollectionEmployeeAward != null) {
                    oldEmployeeIdOfEmployeeAwardCollectionEmployeeAward.getEmployeeAwardCollection().remove(employeeAwardCollectionEmployeeAward);
                    oldEmployeeIdOfEmployeeAwardCollectionEmployeeAward = em.merge(oldEmployeeIdOfEmployeeAwardCollectionEmployeeAward);
                }
            }
            for (EmployeePayroll employeePayrollCollectionEmployeePayroll : employee.getEmployeePayrollCollection()) {
                Employee oldEmployeeIdOfEmployeePayrollCollectionEmployeePayroll = employeePayrollCollectionEmployeePayroll.getEmployeeId();
                employeePayrollCollectionEmployeePayroll.setEmployeeId(employee);
                employeePayrollCollectionEmployeePayroll = em.merge(employeePayrollCollectionEmployeePayroll);
                if (oldEmployeeIdOfEmployeePayrollCollectionEmployeePayroll != null) {
                    oldEmployeeIdOfEmployeePayrollCollectionEmployeePayroll.getEmployeePayrollCollection().remove(employeePayrollCollectionEmployeePayroll);
                    oldEmployeeIdOfEmployeePayrollCollectionEmployeePayroll = em.merge(oldEmployeeIdOfEmployeePayrollCollectionEmployeePayroll);
                }
            }
            for (AccountTransaction accountTransactionCollectionAccountTransaction : employee.getAccountTransactionCollection()) {
                Employee oldCreatedByEmployeeIdOfAccountTransactionCollectionAccountTransaction = accountTransactionCollectionAccountTransaction.getCreatedByEmployeeId();
                accountTransactionCollectionAccountTransaction.setCreatedByEmployeeId(employee);
                accountTransactionCollectionAccountTransaction = em.merge(accountTransactionCollectionAccountTransaction);
                if (oldCreatedByEmployeeIdOfAccountTransactionCollectionAccountTransaction != null) {
                    oldCreatedByEmployeeIdOfAccountTransactionCollectionAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionAccountTransaction);
                    oldCreatedByEmployeeIdOfAccountTransactionCollectionAccountTransaction = em.merge(oldCreatedByEmployeeIdOfAccountTransactionCollectionAccountTransaction);
                }
            }
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellation : employee.getTransactionCancellationCollection()) {
                Employee oldCreatedByEmployeeIdOfTransactionCancellationCollectionTransactionCancellation = transactionCancellationCollectionTransactionCancellation.getCreatedByEmployeeId();
                transactionCancellationCollectionTransactionCancellation.setCreatedByEmployeeId(employee);
                transactionCancellationCollectionTransactionCancellation = em.merge(transactionCancellationCollectionTransactionCancellation);
                if (oldCreatedByEmployeeIdOfTransactionCancellationCollectionTransactionCancellation != null) {
                    oldCreatedByEmployeeIdOfTransactionCancellationCollectionTransactionCancellation.getTransactionCancellationCollection().remove(transactionCancellationCollectionTransactionCancellation);
                    oldCreatedByEmployeeIdOfTransactionCancellationCollectionTransactionCancellation = em.merge(oldCreatedByEmployeeIdOfTransactionCancellationCollectionTransactionCancellation);
                }
            }
            for (Users usersCollectionUsers : employee.getUsersCollection()) {
                Employee oldEmployeeIdOfUsersCollectionUsers = usersCollectionUsers.getEmployeeId();
                usersCollectionUsers.setEmployeeId(employee);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldEmployeeIdOfUsersCollectionUsers != null) {
                    oldEmployeeIdOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldEmployeeIdOfUsersCollectionUsers = em.merge(oldEmployeeIdOfUsersCollectionUsers);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilege : employee.getBankAccountPrivilegeCollection()) {
                Employee oldEmployeeIdOfBankAccountPrivilegeCollectionBankAccountPrivilege = bankAccountPrivilegeCollectionBankAccountPrivilege.getEmployeeId();
                bankAccountPrivilegeCollectionBankAccountPrivilege.setEmployeeId(employee);
                bankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionBankAccountPrivilege);
                if (oldEmployeeIdOfBankAccountPrivilegeCollectionBankAccountPrivilege != null) {
                    oldEmployeeIdOfBankAccountPrivilegeCollectionBankAccountPrivilege.getBankAccountPrivilegeCollection().remove(bankAccountPrivilegeCollectionBankAccountPrivilege);
                    oldEmployeeIdOfBankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(oldEmployeeIdOfBankAccountPrivilegeCollectionBankAccountPrivilege);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollection1BankAccountPrivilege : employee.getBankAccountPrivilegeCollection1()) {
                Employee oldCreatedByOfBankAccountPrivilegeCollection1BankAccountPrivilege = bankAccountPrivilegeCollection1BankAccountPrivilege.getCreatedBy();
                bankAccountPrivilegeCollection1BankAccountPrivilege.setCreatedBy(employee);
                bankAccountPrivilegeCollection1BankAccountPrivilege = em.merge(bankAccountPrivilegeCollection1BankAccountPrivilege);
                if (oldCreatedByOfBankAccountPrivilegeCollection1BankAccountPrivilege != null) {
                    oldCreatedByOfBankAccountPrivilegeCollection1BankAccountPrivilege.getBankAccountPrivilegeCollection1().remove(bankAccountPrivilegeCollection1BankAccountPrivilege);
                    oldCreatedByOfBankAccountPrivilegeCollection1BankAccountPrivilege = em.merge(oldCreatedByOfBankAccountPrivilegeCollection1BankAccountPrivilege);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollection2BankAccountPrivilege : employee.getBankAccountPrivilegeCollection2()) {
                Employee oldLastModifiedByOfBankAccountPrivilegeCollection2BankAccountPrivilege = bankAccountPrivilegeCollection2BankAccountPrivilege.getLastModifiedBy();
                bankAccountPrivilegeCollection2BankAccountPrivilege.setLastModifiedBy(employee);
                bankAccountPrivilegeCollection2BankAccountPrivilege = em.merge(bankAccountPrivilegeCollection2BankAccountPrivilege);
                if (oldLastModifiedByOfBankAccountPrivilegeCollection2BankAccountPrivilege != null) {
                    oldLastModifiedByOfBankAccountPrivilegeCollection2BankAccountPrivilege.getBankAccountPrivilegeCollection2().remove(bankAccountPrivilegeCollection2BankAccountPrivilege);
                    oldLastModifiedByOfBankAccountPrivilegeCollection2BankAccountPrivilege = em.merge(oldLastModifiedByOfBankAccountPrivilegeCollection2BankAccountPrivilege);
                }
            }
            for (ClassSubjects classSubjectsCollectionClassSubjects : employee.getClassSubjectsCollection()) {
                Employee oldTeacherIdOfClassSubjectsCollectionClassSubjects = classSubjectsCollectionClassSubjects.getTeacherId();
                classSubjectsCollectionClassSubjects.setTeacherId(employee);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
                if (oldTeacherIdOfClassSubjectsCollectionClassSubjects != null) {
                    oldTeacherIdOfClassSubjectsCollectionClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionClassSubjects);
                    oldTeacherIdOfClassSubjectsCollectionClassSubjects = em.merge(oldTeacherIdOfClassSubjectsCollectionClassSubjects);
                }
            }
            for (EmployeeBank employeeBankCollectionEmployeeBank : employee.getEmployeeBankCollection()) {
                Employee oldEmployeeIdOfEmployeeBankCollectionEmployeeBank = employeeBankCollectionEmployeeBank.getEmployeeId();
                employeeBankCollectionEmployeeBank.setEmployeeId(employee);
                employeeBankCollectionEmployeeBank = em.merge(employeeBankCollectionEmployeeBank);
                if (oldEmployeeIdOfEmployeeBankCollectionEmployeeBank != null) {
                    oldEmployeeIdOfEmployeeBankCollectionEmployeeBank.getEmployeeBankCollection().remove(employeeBankCollectionEmployeeBank);
                    oldEmployeeIdOfEmployeeBankCollectionEmployeeBank = em.merge(oldEmployeeIdOfEmployeeBankCollectionEmployeeBank);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : employee.getSchoolFeeInvoiceDetailsCollection()) {
                Employee oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getCreatedByEmployeeId();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setCreatedByEmployeeId(employee);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails : employee.getSchoolFeeInvoiceDetailsCollection1()) {
                Employee oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails.getLastModifiedEmployeeId();
                schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails.setLastModifiedEmployeeId(employee);
                schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails);
                if (oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails != null) {
                    oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection1().remove(schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails);
                    oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails = em.merge(oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails);
                }
            }
            for (Class classCollectionClass : employee.getClassCollection()) {
                Employee oldTeacherIdOfClassCollectionClass = classCollectionClass.getTeacherId();
                classCollectionClass.setTeacherId(employee);
                classCollectionClass = em.merge(classCollectionClass);
                if (oldTeacherIdOfClassCollectionClass != null) {
                    oldTeacherIdOfClassCollectionClass.getClassCollection().remove(classCollectionClass);
                    oldTeacherIdOfClassCollectionClass = em.merge(oldTeacherIdOfClassCollectionClass);
                }
            }
            for (TransactionCancellationProof transactionCancellationProofCollectionTransactionCancellationProof : employee.getTransactionCancellationProofCollection()) {
                Employee oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionTransactionCancellationProof = transactionCancellationProofCollectionTransactionCancellationProof.getUploadedByEmployeeId();
                transactionCancellationProofCollectionTransactionCancellationProof.setUploadedByEmployeeId(employee);
                transactionCancellationProofCollectionTransactionCancellationProof = em.merge(transactionCancellationProofCollectionTransactionCancellationProof);
                if (oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionTransactionCancellationProof != null) {
                    oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionTransactionCancellationProof.getTransactionCancellationProofCollection().remove(transactionCancellationProofCollectionTransactionCancellationProof);
                    oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionTransactionCancellationProof = em.merge(oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionTransactionCancellationProof);
                }
            }
            for (Invoice invoiceCollectionInvoice : employee.getInvoiceCollection()) {
                Employee oldEmployeeIdOfInvoiceCollectionInvoice = invoiceCollectionInvoice.getEmployeeId();
                invoiceCollectionInvoice.setEmployeeId(employee);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
                if (oldEmployeeIdOfInvoiceCollectionInvoice != null) {
                    oldEmployeeIdOfInvoiceCollectionInvoice.getInvoiceCollection().remove(invoiceCollectionInvoice);
                    oldEmployeeIdOfInvoiceCollectionInvoice = em.merge(oldEmployeeIdOfInvoiceCollectionInvoice);
                }
            }
            for (Invoice invoiceCollection1Invoice : employee.getInvoiceCollection1()) {
                Employee oldCreatedByEmployeeIdOfInvoiceCollection1Invoice = invoiceCollection1Invoice.getCreatedByEmployeeId();
                invoiceCollection1Invoice.setCreatedByEmployeeId(employee);
                invoiceCollection1Invoice = em.merge(invoiceCollection1Invoice);
                if (oldCreatedByEmployeeIdOfInvoiceCollection1Invoice != null) {
                    oldCreatedByEmployeeIdOfInvoiceCollection1Invoice.getInvoiceCollection1().remove(invoiceCollection1Invoice);
                    oldCreatedByEmployeeIdOfInvoiceCollection1Invoice = em.merge(oldCreatedByEmployeeIdOfInvoiceCollection1Invoice);
                }
            }
            for (EmployeeAttendance employeeAttendanceCollectionEmployeeAttendance : employee.getEmployeeAttendanceCollection()) {
                Employee oldEmployeeIdOfEmployeeAttendanceCollectionEmployeeAttendance = employeeAttendanceCollectionEmployeeAttendance.getEmployeeId();
                employeeAttendanceCollectionEmployeeAttendance.setEmployeeId(employee);
                employeeAttendanceCollectionEmployeeAttendance = em.merge(employeeAttendanceCollectionEmployeeAttendance);
                if (oldEmployeeIdOfEmployeeAttendanceCollectionEmployeeAttendance != null) {
                    oldEmployeeIdOfEmployeeAttendanceCollectionEmployeeAttendance.getEmployeeAttendanceCollection().remove(employeeAttendanceCollectionEmployeeAttendance);
                    oldEmployeeIdOfEmployeeAttendanceCollectionEmployeeAttendance = em.merge(oldEmployeeIdOfEmployeeAttendanceCollectionEmployeeAttendance);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployee(employee.getEmployeeId()) != null) {
                throw new PreexistingEntityException("Employee " + employee + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee persistentEmployee = em.find(Employee.class, employee.getEmployeeId());
            CompanyBranch branchIdOld = persistentEmployee.getBranchId();
            CompanyBranch branchIdNew = employee.getBranchId();
            Countries nationalityOld = persistentEmployee.getNationality();
            Countries nationalityNew = employee.getNationality();
            Cities cityOld = persistentEmployee.getCity();
            Cities cityNew = employee.getCity();
            Countries countryIdOld = persistentEmployee.getCountryId();
            Countries countryIdNew = employee.getCountryId();
            Designation designationIdOld = persistentEmployee.getDesignationId();
            Designation designationIdNew = employee.getDesignationId();
            Collection<EmployeeAward> employeeAwardCollectionOld = persistentEmployee.getEmployeeAwardCollection();
            Collection<EmployeeAward> employeeAwardCollectionNew = employee.getEmployeeAwardCollection();
            Collection<EmployeePayroll> employeePayrollCollectionOld = persistentEmployee.getEmployeePayrollCollection();
            Collection<EmployeePayroll> employeePayrollCollectionNew = employee.getEmployeePayrollCollection();
            Collection<AccountTransaction> accountTransactionCollectionOld = persistentEmployee.getAccountTransactionCollection();
            Collection<AccountTransaction> accountTransactionCollectionNew = employee.getAccountTransactionCollection();
            Collection<TransactionCancellation> transactionCancellationCollectionOld = persistentEmployee.getTransactionCancellationCollection();
            Collection<TransactionCancellation> transactionCancellationCollectionNew = employee.getTransactionCancellationCollection();
            Collection<Users> usersCollectionOld = persistentEmployee.getUsersCollection();
            Collection<Users> usersCollectionNew = employee.getUsersCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionOld = persistentEmployee.getBankAccountPrivilegeCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionNew = employee.getBankAccountPrivilegeCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection1Old = persistentEmployee.getBankAccountPrivilegeCollection1();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection1New = employee.getBankAccountPrivilegeCollection1();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection2Old = persistentEmployee.getBankAccountPrivilegeCollection2();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection2New = employee.getBankAccountPrivilegeCollection2();
            Collection<ClassSubjects> classSubjectsCollectionOld = persistentEmployee.getClassSubjectsCollection();
            Collection<ClassSubjects> classSubjectsCollectionNew = employee.getClassSubjectsCollection();
            Collection<EmployeeBank> employeeBankCollectionOld = persistentEmployee.getEmployeeBankCollection();
            Collection<EmployeeBank> employeeBankCollectionNew = employee.getEmployeeBankCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentEmployee.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = employee.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection1Old = persistentEmployee.getSchoolFeeInvoiceDetailsCollection1();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection1New = employee.getSchoolFeeInvoiceDetailsCollection1();
            Collection<Class> classCollectionOld = persistentEmployee.getClassCollection();
            Collection<Class> classCollectionNew = employee.getClassCollection();
            Collection<TransactionCancellationProof> transactionCancellationProofCollectionOld = persistentEmployee.getTransactionCancellationProofCollection();
            Collection<TransactionCancellationProof> transactionCancellationProofCollectionNew = employee.getTransactionCancellationProofCollection();
            Collection<Invoice> invoiceCollectionOld = persistentEmployee.getInvoiceCollection();
            Collection<Invoice> invoiceCollectionNew = employee.getInvoiceCollection();
            Collection<Invoice> invoiceCollection1Old = persistentEmployee.getInvoiceCollection1();
            Collection<Invoice> invoiceCollection1New = employee.getInvoiceCollection1();
            Collection<EmployeeAttendance> employeeAttendanceCollectionOld = persistentEmployee.getEmployeeAttendanceCollection();
            Collection<EmployeeAttendance> employeeAttendanceCollectionNew = employee.getEmployeeAttendanceCollection();
            List<String> illegalOrphanMessages = null;
            for (EmployeeAward employeeAwardCollectionOldEmployeeAward : employeeAwardCollectionOld) {
                if (!employeeAwardCollectionNew.contains(employeeAwardCollectionOldEmployeeAward)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeAward " + employeeAwardCollectionOldEmployeeAward + " since its employeeId field is not nullable.");
                }
            }
            for (EmployeePayroll employeePayrollCollectionOldEmployeePayroll : employeePayrollCollectionOld) {
                if (!employeePayrollCollectionNew.contains(employeePayrollCollectionOldEmployeePayroll)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeePayroll " + employeePayrollCollectionOldEmployeePayroll + " since its employeeId field is not nullable.");
                }
            }
            for (TransactionCancellation transactionCancellationCollectionOldTransactionCancellation : transactionCancellationCollectionOld) {
                if (!transactionCancellationCollectionNew.contains(transactionCancellationCollectionOldTransactionCancellation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransactionCancellation " + transactionCancellationCollectionOldTransactionCancellation + " since its createdByEmployeeId field is not nullable.");
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionOldBankAccountPrivilege : bankAccountPrivilegeCollectionOld) {
                if (!bankAccountPrivilegeCollectionNew.contains(bankAccountPrivilegeCollectionOldBankAccountPrivilege)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BankAccountPrivilege " + bankAccountPrivilegeCollectionOldBankAccountPrivilege + " since its employeeId field is not nullable.");
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollection1OldBankAccountPrivilege : bankAccountPrivilegeCollection1Old) {
                if (!bankAccountPrivilegeCollection1New.contains(bankAccountPrivilegeCollection1OldBankAccountPrivilege)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BankAccountPrivilege " + bankAccountPrivilegeCollection1OldBankAccountPrivilege + " since its createdBy field is not nullable.");
                }
            }
            for (EmployeeBank employeeBankCollectionOldEmployeeBank : employeeBankCollectionOld) {
                if (!employeeBankCollectionNew.contains(employeeBankCollectionOldEmployeeBank)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeBank " + employeeBankCollectionOldEmployeeBank + " since its employeeId field is not nullable.");
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails + " since its createdByEmployeeId field is not nullable.");
                }
            }
            for (TransactionCancellationProof transactionCancellationProofCollectionOldTransactionCancellationProof : transactionCancellationProofCollectionOld) {
                if (!transactionCancellationProofCollectionNew.contains(transactionCancellationProofCollectionOldTransactionCancellationProof)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransactionCancellationProof " + transactionCancellationProofCollectionOldTransactionCancellationProof + " since its uploadedByEmployeeId field is not nullable.");
                }
            }
            for (EmployeeAttendance employeeAttendanceCollectionOldEmployeeAttendance : employeeAttendanceCollectionOld) {
                if (!employeeAttendanceCollectionNew.contains(employeeAttendanceCollectionOldEmployeeAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain EmployeeAttendance " + employeeAttendanceCollectionOldEmployeeAttendance + " since its employeeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                employee.setBranchId(branchIdNew);
            }
            if (nationalityNew != null) {
                nationalityNew = em.getReference(nationalityNew.getClass(), nationalityNew.getId());
                employee.setNationality(nationalityNew);
            }
            if (cityNew != null) {
                cityNew = em.getReference(cityNew.getClass(), cityNew.getId());
                employee.setCity(cityNew);
            }
            if (countryIdNew != null) {
                countryIdNew = em.getReference(countryIdNew.getClass(), countryIdNew.getId());
                employee.setCountryId(countryIdNew);
            }
            if (designationIdNew != null) {
                designationIdNew = em.getReference(designationIdNew.getClass(), designationIdNew.getDesignationId());
                employee.setDesignationId(designationIdNew);
            }
            Collection<EmployeeAward> attachedEmployeeAwardCollectionNew = new ArrayList<EmployeeAward>();
            for (EmployeeAward employeeAwardCollectionNewEmployeeAwardToAttach : employeeAwardCollectionNew) {
                employeeAwardCollectionNewEmployeeAwardToAttach = em.getReference(employeeAwardCollectionNewEmployeeAwardToAttach.getClass(), employeeAwardCollectionNewEmployeeAwardToAttach.getEmployeeAwardId());
                attachedEmployeeAwardCollectionNew.add(employeeAwardCollectionNewEmployeeAwardToAttach);
            }
            employeeAwardCollectionNew = attachedEmployeeAwardCollectionNew;
            employee.setEmployeeAwardCollection(employeeAwardCollectionNew);
            Collection<EmployeePayroll> attachedEmployeePayrollCollectionNew = new ArrayList<EmployeePayroll>();
            for (EmployeePayroll employeePayrollCollectionNewEmployeePayrollToAttach : employeePayrollCollectionNew) {
                employeePayrollCollectionNewEmployeePayrollToAttach = em.getReference(employeePayrollCollectionNewEmployeePayrollToAttach.getClass(), employeePayrollCollectionNewEmployeePayrollToAttach.getPayrollId());
                attachedEmployeePayrollCollectionNew.add(employeePayrollCollectionNewEmployeePayrollToAttach);
            }
            employeePayrollCollectionNew = attachedEmployeePayrollCollectionNew;
            employee.setEmployeePayrollCollection(employeePayrollCollectionNew);
            Collection<AccountTransaction> attachedAccountTransactionCollectionNew = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionNewAccountTransactionToAttach : accountTransactionCollectionNew) {
                accountTransactionCollectionNewAccountTransactionToAttach = em.getReference(accountTransactionCollectionNewAccountTransactionToAttach.getClass(), accountTransactionCollectionNewAccountTransactionToAttach.getId());
                attachedAccountTransactionCollectionNew.add(accountTransactionCollectionNewAccountTransactionToAttach);
            }
            accountTransactionCollectionNew = attachedAccountTransactionCollectionNew;
            employee.setAccountTransactionCollection(accountTransactionCollectionNew);
            Collection<TransactionCancellation> attachedTransactionCancellationCollectionNew = new ArrayList<TransactionCancellation>();
            for (TransactionCancellation transactionCancellationCollectionNewTransactionCancellationToAttach : transactionCancellationCollectionNew) {
                transactionCancellationCollectionNewTransactionCancellationToAttach = em.getReference(transactionCancellationCollectionNewTransactionCancellationToAttach.getClass(), transactionCancellationCollectionNewTransactionCancellationToAttach.getId());
                attachedTransactionCancellationCollectionNew.add(transactionCancellationCollectionNewTransactionCancellationToAttach);
            }
            transactionCancellationCollectionNew = attachedTransactionCancellationCollectionNew;
            employee.setTransactionCancellationCollection(transactionCancellationCollectionNew);
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getId());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            employee.setUsersCollection(usersCollectionNew);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollectionNew = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach : bankAccountPrivilegeCollectionNew) {
                bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollectionNew.add(bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach);
            }
            bankAccountPrivilegeCollectionNew = attachedBankAccountPrivilegeCollectionNew;
            employee.setBankAccountPrivilegeCollection(bankAccountPrivilegeCollectionNew);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection1New = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollection1NewBankAccountPrivilegeToAttach : bankAccountPrivilegeCollection1New) {
                bankAccountPrivilegeCollection1NewBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollection1NewBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollection1NewBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection1New.add(bankAccountPrivilegeCollection1NewBankAccountPrivilegeToAttach);
            }
            bankAccountPrivilegeCollection1New = attachedBankAccountPrivilegeCollection1New;
            employee.setBankAccountPrivilegeCollection1(bankAccountPrivilegeCollection1New);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection2New = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollection2NewBankAccountPrivilegeToAttach : bankAccountPrivilegeCollection2New) {
                bankAccountPrivilegeCollection2NewBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollection2NewBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollection2NewBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection2New.add(bankAccountPrivilegeCollection2NewBankAccountPrivilegeToAttach);
            }
            bankAccountPrivilegeCollection2New = attachedBankAccountPrivilegeCollection2New;
            employee.setBankAccountPrivilegeCollection2(bankAccountPrivilegeCollection2New);
            Collection<ClassSubjects> attachedClassSubjectsCollectionNew = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionNewClassSubjectsToAttach : classSubjectsCollectionNew) {
                classSubjectsCollectionNewClassSubjectsToAttach = em.getReference(classSubjectsCollectionNewClassSubjectsToAttach.getClass(), classSubjectsCollectionNewClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollectionNew.add(classSubjectsCollectionNewClassSubjectsToAttach);
            }
            classSubjectsCollectionNew = attachedClassSubjectsCollectionNew;
            employee.setClassSubjectsCollection(classSubjectsCollectionNew);
            Collection<EmployeeBank> attachedEmployeeBankCollectionNew = new ArrayList<EmployeeBank>();
            for (EmployeeBank employeeBankCollectionNewEmployeeBankToAttach : employeeBankCollectionNew) {
                employeeBankCollectionNewEmployeeBankToAttach = em.getReference(employeeBankCollectionNewEmployeeBankToAttach.getClass(), employeeBankCollectionNewEmployeeBankToAttach.getEmployeeBankId());
                attachedEmployeeBankCollectionNew.add(employeeBankCollectionNewEmployeeBankToAttach);
            }
            employeeBankCollectionNew = attachedEmployeeBankCollectionNew;
            employee.setEmployeeBankCollection(employeeBankCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            employee.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection1New = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollection1New) {
                schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection1New.add(schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollection1New = attachedSchoolFeeInvoiceDetailsCollection1New;
            employee.setSchoolFeeInvoiceDetailsCollection1(schoolFeeInvoiceDetailsCollection1New);
            Collection<Class> attachedClassCollectionNew = new ArrayList<Class>();
            for (Class classCollectionNewClassToAttach : classCollectionNew) {
                classCollectionNewClassToAttach = em.getReference(classCollectionNewClassToAttach.getClass(), classCollectionNewClassToAttach.getClassId());
                attachedClassCollectionNew.add(classCollectionNewClassToAttach);
            }
            classCollectionNew = attachedClassCollectionNew;
            employee.setClassCollection(classCollectionNew);
            Collection<TransactionCancellationProof> attachedTransactionCancellationProofCollectionNew = new ArrayList<TransactionCancellationProof>();
            for (TransactionCancellationProof transactionCancellationProofCollectionNewTransactionCancellationProofToAttach : transactionCancellationProofCollectionNew) {
                transactionCancellationProofCollectionNewTransactionCancellationProofToAttach = em.getReference(transactionCancellationProofCollectionNewTransactionCancellationProofToAttach.getClass(), transactionCancellationProofCollectionNewTransactionCancellationProofToAttach.getId());
                attachedTransactionCancellationProofCollectionNew.add(transactionCancellationProofCollectionNewTransactionCancellationProofToAttach);
            }
            transactionCancellationProofCollectionNew = attachedTransactionCancellationProofCollectionNew;
            employee.setTransactionCancellationProofCollection(transactionCancellationProofCollectionNew);
            Collection<Invoice> attachedInvoiceCollectionNew = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionNewInvoiceToAttach : invoiceCollectionNew) {
                invoiceCollectionNewInvoiceToAttach = em.getReference(invoiceCollectionNewInvoiceToAttach.getClass(), invoiceCollectionNewInvoiceToAttach.getId());
                attachedInvoiceCollectionNew.add(invoiceCollectionNewInvoiceToAttach);
            }
            invoiceCollectionNew = attachedInvoiceCollectionNew;
            employee.setInvoiceCollection(invoiceCollectionNew);
            Collection<Invoice> attachedInvoiceCollection1New = new ArrayList<Invoice>();
            for (Invoice invoiceCollection1NewInvoiceToAttach : invoiceCollection1New) {
                invoiceCollection1NewInvoiceToAttach = em.getReference(invoiceCollection1NewInvoiceToAttach.getClass(), invoiceCollection1NewInvoiceToAttach.getId());
                attachedInvoiceCollection1New.add(invoiceCollection1NewInvoiceToAttach);
            }
            invoiceCollection1New = attachedInvoiceCollection1New;
            employee.setInvoiceCollection1(invoiceCollection1New);
            Collection<EmployeeAttendance> attachedEmployeeAttendanceCollectionNew = new ArrayList<EmployeeAttendance>();
            for (EmployeeAttendance employeeAttendanceCollectionNewEmployeeAttendanceToAttach : employeeAttendanceCollectionNew) {
                employeeAttendanceCollectionNewEmployeeAttendanceToAttach = em.getReference(employeeAttendanceCollectionNewEmployeeAttendanceToAttach.getClass(), employeeAttendanceCollectionNewEmployeeAttendanceToAttach.getAttendanceId());
                attachedEmployeeAttendanceCollectionNew.add(employeeAttendanceCollectionNewEmployeeAttendanceToAttach);
            }
            employeeAttendanceCollectionNew = attachedEmployeeAttendanceCollectionNew;
            employee.setEmployeeAttendanceCollection(employeeAttendanceCollectionNew);
            employee = em.merge(employee);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getEmployeeCollection().remove(employee);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getEmployeeCollection().add(employee);
                branchIdNew = em.merge(branchIdNew);
            }
            if (nationalityOld != null && !nationalityOld.equals(nationalityNew)) {
                nationalityOld.getEmployeeCollection().remove(employee);
                nationalityOld = em.merge(nationalityOld);
            }
            if (nationalityNew != null && !nationalityNew.equals(nationalityOld)) {
                nationalityNew.getEmployeeCollection().add(employee);
                nationalityNew = em.merge(nationalityNew);
            }
            if (cityOld != null && !cityOld.equals(cityNew)) {
                cityOld.getEmployeeCollection().remove(employee);
                cityOld = em.merge(cityOld);
            }
            if (cityNew != null && !cityNew.equals(cityOld)) {
                cityNew.getEmployeeCollection().add(employee);
                cityNew = em.merge(cityNew);
            }
            if (countryIdOld != null && !countryIdOld.equals(countryIdNew)) {
                countryIdOld.getEmployeeCollection().remove(employee);
                countryIdOld = em.merge(countryIdOld);
            }
            if (countryIdNew != null && !countryIdNew.equals(countryIdOld)) {
                countryIdNew.getEmployeeCollection().add(employee);
                countryIdNew = em.merge(countryIdNew);
            }
            if (designationIdOld != null && !designationIdOld.equals(designationIdNew)) {
                designationIdOld.getEmployeeCollection().remove(employee);
                designationIdOld = em.merge(designationIdOld);
            }
            if (designationIdNew != null && !designationIdNew.equals(designationIdOld)) {
                designationIdNew.getEmployeeCollection().add(employee);
                designationIdNew = em.merge(designationIdNew);
            }
            for (EmployeeAward employeeAwardCollectionNewEmployeeAward : employeeAwardCollectionNew) {
                if (!employeeAwardCollectionOld.contains(employeeAwardCollectionNewEmployeeAward)) {
                    Employee oldEmployeeIdOfEmployeeAwardCollectionNewEmployeeAward = employeeAwardCollectionNewEmployeeAward.getEmployeeId();
                    employeeAwardCollectionNewEmployeeAward.setEmployeeId(employee);
                    employeeAwardCollectionNewEmployeeAward = em.merge(employeeAwardCollectionNewEmployeeAward);
                    if (oldEmployeeIdOfEmployeeAwardCollectionNewEmployeeAward != null && !oldEmployeeIdOfEmployeeAwardCollectionNewEmployeeAward.equals(employee)) {
                        oldEmployeeIdOfEmployeeAwardCollectionNewEmployeeAward.getEmployeeAwardCollection().remove(employeeAwardCollectionNewEmployeeAward);
                        oldEmployeeIdOfEmployeeAwardCollectionNewEmployeeAward = em.merge(oldEmployeeIdOfEmployeeAwardCollectionNewEmployeeAward);
                    }
                }
            }
            for (EmployeePayroll employeePayrollCollectionNewEmployeePayroll : employeePayrollCollectionNew) {
                if (!employeePayrollCollectionOld.contains(employeePayrollCollectionNewEmployeePayroll)) {
                    Employee oldEmployeeIdOfEmployeePayrollCollectionNewEmployeePayroll = employeePayrollCollectionNewEmployeePayroll.getEmployeeId();
                    employeePayrollCollectionNewEmployeePayroll.setEmployeeId(employee);
                    employeePayrollCollectionNewEmployeePayroll = em.merge(employeePayrollCollectionNewEmployeePayroll);
                    if (oldEmployeeIdOfEmployeePayrollCollectionNewEmployeePayroll != null && !oldEmployeeIdOfEmployeePayrollCollectionNewEmployeePayroll.equals(employee)) {
                        oldEmployeeIdOfEmployeePayrollCollectionNewEmployeePayroll.getEmployeePayrollCollection().remove(employeePayrollCollectionNewEmployeePayroll);
                        oldEmployeeIdOfEmployeePayrollCollectionNewEmployeePayroll = em.merge(oldEmployeeIdOfEmployeePayrollCollectionNewEmployeePayroll);
                    }
                }
            }
            for (AccountTransaction accountTransactionCollectionOldAccountTransaction : accountTransactionCollectionOld) {
                if (!accountTransactionCollectionNew.contains(accountTransactionCollectionOldAccountTransaction)) {
                    accountTransactionCollectionOldAccountTransaction.setCreatedByEmployeeId(null);
                    accountTransactionCollectionOldAccountTransaction = em.merge(accountTransactionCollectionOldAccountTransaction);
                }
            }
            for (AccountTransaction accountTransactionCollectionNewAccountTransaction : accountTransactionCollectionNew) {
                if (!accountTransactionCollectionOld.contains(accountTransactionCollectionNewAccountTransaction)) {
                    Employee oldCreatedByEmployeeIdOfAccountTransactionCollectionNewAccountTransaction = accountTransactionCollectionNewAccountTransaction.getCreatedByEmployeeId();
                    accountTransactionCollectionNewAccountTransaction.setCreatedByEmployeeId(employee);
                    accountTransactionCollectionNewAccountTransaction = em.merge(accountTransactionCollectionNewAccountTransaction);
                    if (oldCreatedByEmployeeIdOfAccountTransactionCollectionNewAccountTransaction != null && !oldCreatedByEmployeeIdOfAccountTransactionCollectionNewAccountTransaction.equals(employee)) {
                        oldCreatedByEmployeeIdOfAccountTransactionCollectionNewAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionNewAccountTransaction);
                        oldCreatedByEmployeeIdOfAccountTransactionCollectionNewAccountTransaction = em.merge(oldCreatedByEmployeeIdOfAccountTransactionCollectionNewAccountTransaction);
                    }
                }
            }
            for (TransactionCancellation transactionCancellationCollectionNewTransactionCancellation : transactionCancellationCollectionNew) {
                if (!transactionCancellationCollectionOld.contains(transactionCancellationCollectionNewTransactionCancellation)) {
                    Employee oldCreatedByEmployeeIdOfTransactionCancellationCollectionNewTransactionCancellation = transactionCancellationCollectionNewTransactionCancellation.getCreatedByEmployeeId();
                    transactionCancellationCollectionNewTransactionCancellation.setCreatedByEmployeeId(employee);
                    transactionCancellationCollectionNewTransactionCancellation = em.merge(transactionCancellationCollectionNewTransactionCancellation);
                    if (oldCreatedByEmployeeIdOfTransactionCancellationCollectionNewTransactionCancellation != null && !oldCreatedByEmployeeIdOfTransactionCancellationCollectionNewTransactionCancellation.equals(employee)) {
                        oldCreatedByEmployeeIdOfTransactionCancellationCollectionNewTransactionCancellation.getTransactionCancellationCollection().remove(transactionCancellationCollectionNewTransactionCancellation);
                        oldCreatedByEmployeeIdOfTransactionCancellationCollectionNewTransactionCancellation = em.merge(oldCreatedByEmployeeIdOfTransactionCancellationCollectionNewTransactionCancellation);
                    }
                }
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    usersCollectionOldUsers.setEmployeeId(null);
                    usersCollectionOldUsers = em.merge(usersCollectionOldUsers);
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    Employee oldEmployeeIdOfUsersCollectionNewUsers = usersCollectionNewUsers.getEmployeeId();
                    usersCollectionNewUsers.setEmployeeId(employee);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldEmployeeIdOfUsersCollectionNewUsers != null && !oldEmployeeIdOfUsersCollectionNewUsers.equals(employee)) {
                        oldEmployeeIdOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldEmployeeIdOfUsersCollectionNewUsers = em.merge(oldEmployeeIdOfUsersCollectionNewUsers);
                    }
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionNewBankAccountPrivilege : bankAccountPrivilegeCollectionNew) {
                if (!bankAccountPrivilegeCollectionOld.contains(bankAccountPrivilegeCollectionNewBankAccountPrivilege)) {
                    Employee oldEmployeeIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege = bankAccountPrivilegeCollectionNewBankAccountPrivilege.getEmployeeId();
                    bankAccountPrivilegeCollectionNewBankAccountPrivilege.setEmployeeId(employee);
                    bankAccountPrivilegeCollectionNewBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionNewBankAccountPrivilege);
                    if (oldEmployeeIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege != null && !oldEmployeeIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege.equals(employee)) {
                        oldEmployeeIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege.getBankAccountPrivilegeCollection().remove(bankAccountPrivilegeCollectionNewBankAccountPrivilege);
                        oldEmployeeIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege = em.merge(oldEmployeeIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege);
                    }
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollection1NewBankAccountPrivilege : bankAccountPrivilegeCollection1New) {
                if (!bankAccountPrivilegeCollection1Old.contains(bankAccountPrivilegeCollection1NewBankAccountPrivilege)) {
                    Employee oldCreatedByOfBankAccountPrivilegeCollection1NewBankAccountPrivilege = bankAccountPrivilegeCollection1NewBankAccountPrivilege.getCreatedBy();
                    bankAccountPrivilegeCollection1NewBankAccountPrivilege.setCreatedBy(employee);
                    bankAccountPrivilegeCollection1NewBankAccountPrivilege = em.merge(bankAccountPrivilegeCollection1NewBankAccountPrivilege);
                    if (oldCreatedByOfBankAccountPrivilegeCollection1NewBankAccountPrivilege != null && !oldCreatedByOfBankAccountPrivilegeCollection1NewBankAccountPrivilege.equals(employee)) {
                        oldCreatedByOfBankAccountPrivilegeCollection1NewBankAccountPrivilege.getBankAccountPrivilegeCollection1().remove(bankAccountPrivilegeCollection1NewBankAccountPrivilege);
                        oldCreatedByOfBankAccountPrivilegeCollection1NewBankAccountPrivilege = em.merge(oldCreatedByOfBankAccountPrivilegeCollection1NewBankAccountPrivilege);
                    }
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollection2OldBankAccountPrivilege : bankAccountPrivilegeCollection2Old) {
                if (!bankAccountPrivilegeCollection2New.contains(bankAccountPrivilegeCollection2OldBankAccountPrivilege)) {
                    bankAccountPrivilegeCollection2OldBankAccountPrivilege.setLastModifiedBy(null);
                    bankAccountPrivilegeCollection2OldBankAccountPrivilege = em.merge(bankAccountPrivilegeCollection2OldBankAccountPrivilege);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollection2NewBankAccountPrivilege : bankAccountPrivilegeCollection2New) {
                if (!bankAccountPrivilegeCollection2Old.contains(bankAccountPrivilegeCollection2NewBankAccountPrivilege)) {
                    Employee oldLastModifiedByOfBankAccountPrivilegeCollection2NewBankAccountPrivilege = bankAccountPrivilegeCollection2NewBankAccountPrivilege.getLastModifiedBy();
                    bankAccountPrivilegeCollection2NewBankAccountPrivilege.setLastModifiedBy(employee);
                    bankAccountPrivilegeCollection2NewBankAccountPrivilege = em.merge(bankAccountPrivilegeCollection2NewBankAccountPrivilege);
                    if (oldLastModifiedByOfBankAccountPrivilegeCollection2NewBankAccountPrivilege != null && !oldLastModifiedByOfBankAccountPrivilegeCollection2NewBankAccountPrivilege.equals(employee)) {
                        oldLastModifiedByOfBankAccountPrivilegeCollection2NewBankAccountPrivilege.getBankAccountPrivilegeCollection2().remove(bankAccountPrivilegeCollection2NewBankAccountPrivilege);
                        oldLastModifiedByOfBankAccountPrivilegeCollection2NewBankAccountPrivilege = em.merge(oldLastModifiedByOfBankAccountPrivilegeCollection2NewBankAccountPrivilege);
                    }
                }
            }
            for (ClassSubjects classSubjectsCollectionOldClassSubjects : classSubjectsCollectionOld) {
                if (!classSubjectsCollectionNew.contains(classSubjectsCollectionOldClassSubjects)) {
                    classSubjectsCollectionOldClassSubjects.setTeacherId(null);
                    classSubjectsCollectionOldClassSubjects = em.merge(classSubjectsCollectionOldClassSubjects);
                }
            }
            for (ClassSubjects classSubjectsCollectionNewClassSubjects : classSubjectsCollectionNew) {
                if (!classSubjectsCollectionOld.contains(classSubjectsCollectionNewClassSubjects)) {
                    Employee oldTeacherIdOfClassSubjectsCollectionNewClassSubjects = classSubjectsCollectionNewClassSubjects.getTeacherId();
                    classSubjectsCollectionNewClassSubjects.setTeacherId(employee);
                    classSubjectsCollectionNewClassSubjects = em.merge(classSubjectsCollectionNewClassSubjects);
                    if (oldTeacherIdOfClassSubjectsCollectionNewClassSubjects != null && !oldTeacherIdOfClassSubjectsCollectionNewClassSubjects.equals(employee)) {
                        oldTeacherIdOfClassSubjectsCollectionNewClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionNewClassSubjects);
                        oldTeacherIdOfClassSubjectsCollectionNewClassSubjects = em.merge(oldTeacherIdOfClassSubjectsCollectionNewClassSubjects);
                    }
                }
            }
            for (EmployeeBank employeeBankCollectionNewEmployeeBank : employeeBankCollectionNew) {
                if (!employeeBankCollectionOld.contains(employeeBankCollectionNewEmployeeBank)) {
                    Employee oldEmployeeIdOfEmployeeBankCollectionNewEmployeeBank = employeeBankCollectionNewEmployeeBank.getEmployeeId();
                    employeeBankCollectionNewEmployeeBank.setEmployeeId(employee);
                    employeeBankCollectionNewEmployeeBank = em.merge(employeeBankCollectionNewEmployeeBank);
                    if (oldEmployeeIdOfEmployeeBankCollectionNewEmployeeBank != null && !oldEmployeeIdOfEmployeeBankCollectionNewEmployeeBank.equals(employee)) {
                        oldEmployeeIdOfEmployeeBankCollectionNewEmployeeBank.getEmployeeBankCollection().remove(employeeBankCollectionNewEmployeeBank);
                        oldEmployeeIdOfEmployeeBankCollectionNewEmployeeBank = em.merge(oldEmployeeIdOfEmployeeBankCollectionNewEmployeeBank);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    Employee oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getCreatedByEmployeeId();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setCreatedByEmployeeId(employee);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(employee)) {
                        oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldCreatedByEmployeeIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollection1OldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollection1Old) {
                if (!schoolFeeInvoiceDetailsCollection1New.contains(schoolFeeInvoiceDetailsCollection1OldSchoolFeeInvoiceDetails)) {
                    schoolFeeInvoiceDetailsCollection1OldSchoolFeeInvoiceDetails.setLastModifiedEmployeeId(null);
                    schoolFeeInvoiceDetailsCollection1OldSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollection1OldSchoolFeeInvoiceDetails);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollection1New) {
                if (!schoolFeeInvoiceDetailsCollection1Old.contains(schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails)) {
                    Employee oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails.getLastModifiedEmployeeId();
                    schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails.setLastModifiedEmployeeId(employee);
                    schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails);
                    if (oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails != null && !oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails.equals(employee)) {
                        oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection1().remove(schoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails);
                        oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails = em.merge(oldLastModifiedEmployeeIdOfSchoolFeeInvoiceDetailsCollection1NewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (Class classCollectionOldClass : classCollectionOld) {
                if (!classCollectionNew.contains(classCollectionOldClass)) {
                    classCollectionOldClass.setTeacherId(null);
                    classCollectionOldClass = em.merge(classCollectionOldClass);
                }
            }
            for (Class classCollectionNewClass : classCollectionNew) {
                if (!classCollectionOld.contains(classCollectionNewClass)) {
                    Employee oldTeacherIdOfClassCollectionNewClass = classCollectionNewClass.getTeacherId();
                    classCollectionNewClass.setTeacherId(employee);
                    classCollectionNewClass = em.merge(classCollectionNewClass);
                    if (oldTeacherIdOfClassCollectionNewClass != null && !oldTeacherIdOfClassCollectionNewClass.equals(employee)) {
                        oldTeacherIdOfClassCollectionNewClass.getClassCollection().remove(classCollectionNewClass);
                        oldTeacherIdOfClassCollectionNewClass = em.merge(oldTeacherIdOfClassCollectionNewClass);
                    }
                }
            }
            for (TransactionCancellationProof transactionCancellationProofCollectionNewTransactionCancellationProof : transactionCancellationProofCollectionNew) {
                if (!transactionCancellationProofCollectionOld.contains(transactionCancellationProofCollectionNewTransactionCancellationProof)) {
                    Employee oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof = transactionCancellationProofCollectionNewTransactionCancellationProof.getUploadedByEmployeeId();
                    transactionCancellationProofCollectionNewTransactionCancellationProof.setUploadedByEmployeeId(employee);
                    transactionCancellationProofCollectionNewTransactionCancellationProof = em.merge(transactionCancellationProofCollectionNewTransactionCancellationProof);
                    if (oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof != null && !oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof.equals(employee)) {
                        oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof.getTransactionCancellationProofCollection().remove(transactionCancellationProofCollectionNewTransactionCancellationProof);
                        oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof = em.merge(oldUploadedByEmployeeIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof);
                    }
                }
            }
            for (Invoice invoiceCollectionOldInvoice : invoiceCollectionOld) {
                if (!invoiceCollectionNew.contains(invoiceCollectionOldInvoice)) {
                    invoiceCollectionOldInvoice.setEmployeeId(null);
                    invoiceCollectionOldInvoice = em.merge(invoiceCollectionOldInvoice);
                }
            }
            for (Invoice invoiceCollectionNewInvoice : invoiceCollectionNew) {
                if (!invoiceCollectionOld.contains(invoiceCollectionNewInvoice)) {
                    Employee oldEmployeeIdOfInvoiceCollectionNewInvoice = invoiceCollectionNewInvoice.getEmployeeId();
                    invoiceCollectionNewInvoice.setEmployeeId(employee);
                    invoiceCollectionNewInvoice = em.merge(invoiceCollectionNewInvoice);
                    if (oldEmployeeIdOfInvoiceCollectionNewInvoice != null && !oldEmployeeIdOfInvoiceCollectionNewInvoice.equals(employee)) {
                        oldEmployeeIdOfInvoiceCollectionNewInvoice.getInvoiceCollection().remove(invoiceCollectionNewInvoice);
                        oldEmployeeIdOfInvoiceCollectionNewInvoice = em.merge(oldEmployeeIdOfInvoiceCollectionNewInvoice);
                    }
                }
            }
            for (Invoice invoiceCollection1OldInvoice : invoiceCollection1Old) {
                if (!invoiceCollection1New.contains(invoiceCollection1OldInvoice)) {
                    invoiceCollection1OldInvoice.setCreatedByEmployeeId(null);
                    invoiceCollection1OldInvoice = em.merge(invoiceCollection1OldInvoice);
                }
            }
            for (Invoice invoiceCollection1NewInvoice : invoiceCollection1New) {
                if (!invoiceCollection1Old.contains(invoiceCollection1NewInvoice)) {
                    Employee oldCreatedByEmployeeIdOfInvoiceCollection1NewInvoice = invoiceCollection1NewInvoice.getCreatedByEmployeeId();
                    invoiceCollection1NewInvoice.setCreatedByEmployeeId(employee);
                    invoiceCollection1NewInvoice = em.merge(invoiceCollection1NewInvoice);
                    if (oldCreatedByEmployeeIdOfInvoiceCollection1NewInvoice != null && !oldCreatedByEmployeeIdOfInvoiceCollection1NewInvoice.equals(employee)) {
                        oldCreatedByEmployeeIdOfInvoiceCollection1NewInvoice.getInvoiceCollection1().remove(invoiceCollection1NewInvoice);
                        oldCreatedByEmployeeIdOfInvoiceCollection1NewInvoice = em.merge(oldCreatedByEmployeeIdOfInvoiceCollection1NewInvoice);
                    }
                }
            }
            for (EmployeeAttendance employeeAttendanceCollectionNewEmployeeAttendance : employeeAttendanceCollectionNew) {
                if (!employeeAttendanceCollectionOld.contains(employeeAttendanceCollectionNewEmployeeAttendance)) {
                    Employee oldEmployeeIdOfEmployeeAttendanceCollectionNewEmployeeAttendance = employeeAttendanceCollectionNewEmployeeAttendance.getEmployeeId();
                    employeeAttendanceCollectionNewEmployeeAttendance.setEmployeeId(employee);
                    employeeAttendanceCollectionNewEmployeeAttendance = em.merge(employeeAttendanceCollectionNewEmployeeAttendance);
                    if (oldEmployeeIdOfEmployeeAttendanceCollectionNewEmployeeAttendance != null && !oldEmployeeIdOfEmployeeAttendanceCollectionNewEmployeeAttendance.equals(employee)) {
                        oldEmployeeIdOfEmployeeAttendanceCollectionNewEmployeeAttendance.getEmployeeAttendanceCollection().remove(employeeAttendanceCollectionNewEmployeeAttendance);
                        oldEmployeeIdOfEmployeeAttendanceCollectionNewEmployeeAttendance = em.merge(oldEmployeeIdOfEmployeeAttendanceCollectionNewEmployeeAttendance);
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
                Long id = employee.getEmployeeId();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getEmployeeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<EmployeeAward> employeeAwardCollectionOrphanCheck = employee.getEmployeeAwardCollection();
            for (EmployeeAward employeeAwardCollectionOrphanCheckEmployeeAward : employeeAwardCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeAward " + employeeAwardCollectionOrphanCheckEmployeeAward + " in its employeeAwardCollection field has a non-nullable employeeId field.");
            }
            Collection<EmployeePayroll> employeePayrollCollectionOrphanCheck = employee.getEmployeePayrollCollection();
            for (EmployeePayroll employeePayrollCollectionOrphanCheckEmployeePayroll : employeePayrollCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeePayroll " + employeePayrollCollectionOrphanCheckEmployeePayroll + " in its employeePayrollCollection field has a non-nullable employeeId field.");
            }
            Collection<TransactionCancellation> transactionCancellationCollectionOrphanCheck = employee.getTransactionCancellationCollection();
            for (TransactionCancellation transactionCancellationCollectionOrphanCheckTransactionCancellation : transactionCancellationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the TransactionCancellation " + transactionCancellationCollectionOrphanCheckTransactionCancellation + " in its transactionCancellationCollection field has a non-nullable createdByEmployeeId field.");
            }
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionOrphanCheck = employee.getBankAccountPrivilegeCollection();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionOrphanCheckBankAccountPrivilege : bankAccountPrivilegeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the BankAccountPrivilege " + bankAccountPrivilegeCollectionOrphanCheckBankAccountPrivilege + " in its bankAccountPrivilegeCollection field has a non-nullable employeeId field.");
            }
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection1OrphanCheck = employee.getBankAccountPrivilegeCollection1();
            for (BankAccountPrivilege bankAccountPrivilegeCollection1OrphanCheckBankAccountPrivilege : bankAccountPrivilegeCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the BankAccountPrivilege " + bankAccountPrivilegeCollection1OrphanCheckBankAccountPrivilege + " in its bankAccountPrivilegeCollection1 field has a non-nullable createdBy field.");
            }
            Collection<EmployeeBank> employeeBankCollectionOrphanCheck = employee.getEmployeeBankCollection();
            for (EmployeeBank employeeBankCollectionOrphanCheckEmployeeBank : employeeBankCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeBank " + employeeBankCollectionOrphanCheckEmployeeBank + " in its employeeBankCollection field has a non-nullable employeeId field.");
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOrphanCheck = employee.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails + " in its schoolFeeInvoiceDetailsCollection field has a non-nullable createdByEmployeeId field.");
            }
            Collection<TransactionCancellationProof> transactionCancellationProofCollectionOrphanCheck = employee.getTransactionCancellationProofCollection();
            for (TransactionCancellationProof transactionCancellationProofCollectionOrphanCheckTransactionCancellationProof : transactionCancellationProofCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the TransactionCancellationProof " + transactionCancellationProofCollectionOrphanCheckTransactionCancellationProof + " in its transactionCancellationProofCollection field has a non-nullable uploadedByEmployeeId field.");
            }
            Collection<EmployeeAttendance> employeeAttendanceCollectionOrphanCheck = employee.getEmployeeAttendanceCollection();
            for (EmployeeAttendance employeeAttendanceCollectionOrphanCheckEmployeeAttendance : employeeAttendanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the EmployeeAttendance " + employeeAttendanceCollectionOrphanCheckEmployeeAttendance + " in its employeeAttendanceCollection field has a non-nullable employeeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = employee.getBranchId();
            if (branchId != null) {
                branchId.getEmployeeCollection().remove(employee);
                branchId = em.merge(branchId);
            }
            Countries nationality = employee.getNationality();
            if (nationality != null) {
                nationality.getEmployeeCollection().remove(employee);
                nationality = em.merge(nationality);
            }
            Cities city = employee.getCity();
            if (city != null) {
                city.getEmployeeCollection().remove(employee);
                city = em.merge(city);
            }
            Countries countryId = employee.getCountryId();
            if (countryId != null) {
                countryId.getEmployeeCollection().remove(employee);
                countryId = em.merge(countryId);
            }
            Designation designationId = employee.getDesignationId();
            if (designationId != null) {
                designationId.getEmployeeCollection().remove(employee);
                designationId = em.merge(designationId);
            }
            Collection<AccountTransaction> accountTransactionCollection = employee.getAccountTransactionCollection();
            for (AccountTransaction accountTransactionCollectionAccountTransaction : accountTransactionCollection) {
                accountTransactionCollectionAccountTransaction.setCreatedByEmployeeId(null);
                accountTransactionCollectionAccountTransaction = em.merge(accountTransactionCollectionAccountTransaction);
            }
            Collection<Users> usersCollection = employee.getUsersCollection();
            for (Users usersCollectionUsers : usersCollection) {
                usersCollectionUsers.setEmployeeId(null);
                usersCollectionUsers = em.merge(usersCollectionUsers);
            }
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollection2 = employee.getBankAccountPrivilegeCollection2();
            for (BankAccountPrivilege bankAccountPrivilegeCollection2BankAccountPrivilege : bankAccountPrivilegeCollection2) {
                bankAccountPrivilegeCollection2BankAccountPrivilege.setLastModifiedBy(null);
                bankAccountPrivilegeCollection2BankAccountPrivilege = em.merge(bankAccountPrivilegeCollection2BankAccountPrivilege);
            }
            Collection<ClassSubjects> classSubjectsCollection = employee.getClassSubjectsCollection();
            for (ClassSubjects classSubjectsCollectionClassSubjects : classSubjectsCollection) {
                classSubjectsCollectionClassSubjects.setTeacherId(null);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection1 = employee.getSchoolFeeInvoiceDetailsCollection1();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollection1) {
                schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails.setLastModifiedEmployeeId(null);
                schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollection1SchoolFeeInvoiceDetails);
            }
            Collection<Class> classCollection = employee.getClassCollection();
            for (Class classCollectionClass : classCollection) {
                classCollectionClass.setTeacherId(null);
                classCollectionClass = em.merge(classCollectionClass);
            }
            Collection<Invoice> invoiceCollection = employee.getInvoiceCollection();
            for (Invoice invoiceCollectionInvoice : invoiceCollection) {
                invoiceCollectionInvoice.setEmployeeId(null);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
            }
            Collection<Invoice> invoiceCollection1 = employee.getInvoiceCollection1();
            for (Invoice invoiceCollection1Invoice : invoiceCollection1) {
                invoiceCollection1Invoice.setCreatedByEmployeeId(null);
                invoiceCollection1Invoice = em.merge(invoiceCollection1Invoice);
            }
            em.remove(employee);
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

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
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

    public Employee findEmployee(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Collection<Employee> get_list_of_teachers(long branch_id)
    {
       String ORG_QUERY = "SELECT emp.*, ds.*"
               + " FROM employee AS emp"
               + " JOIN designation AS ds ON ds.designation_id = emp.designation_id"
               + " WHERE lower(ds.name) = 'educator' OR lower(ds.name) = 'tutor'"
               + " OR lower(ds.name) = 'pedagogue' OR lower(ds.name) = 'schoolteacher'"
               + " OR lower(ds.name) = 'schoolmaster' OR lower(ds.name) = 'schoolmistress'"
               + " OR lower(ds.name) = 'master' OR lower(ds.name) = 'mistress'"
               + " OR lower(ds.name) = 'educationalist' OR lower(ds.name) = 'educationist'"
               + " OR lower(ds.name) = 'coach' OR lower(ds.name) = 'trainer'"
               + " OR lower(ds.name) = 'lecturer' OR lower(ds.name) = 'professor'"
               + " OR lower(ds.name) = 'don' OR lower(ds.name) = 'mentor'"
               + " OR lower(ds.name) = 'counsellor' OR lower(ds.name) = 'sophist'"
               + " OR lower(ds.name) = 'headmaster'"
               + " OR lower(ds.name) = 'headmistress' OR lower(ds.name) = 'dean'"
               + " OR lower(ds.name) = 'rector' OR lower(ds.name) = 'chancellor'"
               + " OR lower(ds.name) = 'president' OR lower(ds.name) = 'provost'"
               + " OR lower(ds.name) = 'teacher' AND emp.status = '1' OR emp.status ='2'"
               + " AND ds.branch_id = emp.branch_id "
               + " AND emp.branch_id = ?";
        
       EntityManager em = getEntityManager();
        try {
            Query findTeachersByBranchId = em.createNativeQuery(ORG_QUERY, Employee.class);
            findTeachersByBranchId.setParameter(1, branch_id);
            Collection<Employee> employeelist = new ArrayList<Employee>();
            employeelist = findTeachersByBranchId.getResultList();
            return employeelist;
        }catch(Exception ex){
             System.out.println("-------Exception Occoured-------"+ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             Collection<Employee> employeelist = new ArrayList<>();
             return employeelist;
        } finally {
            em.close();
        }
    }
}
