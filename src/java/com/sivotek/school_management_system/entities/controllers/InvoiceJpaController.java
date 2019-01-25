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
import com.sivotek.school_management_system.entities.BusinessSubcategory;
import com.sivotek.school_management_system.entities.BusinessSubcategoryServices;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.UtilityBillDetails;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.AccountTransaction;
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrder;
import com.sivotek.school_management_system.entities.PurchaseOrder;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class InvoiceJpaController implements Serializable {

    public InvoiceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Invoice invoice) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (invoice.getUtilityBillDetailsCollection() == null) {
            invoice.setUtilityBillDetailsCollection(new ArrayList<UtilityBillDetails>());
        }
        if (invoice.getAccountTransactionCollection() == null) {
            invoice.setAccountTransactionCollection(new ArrayList<AccountTransaction>());
        }
        if (invoice.getScratchCardSalesOrderCollection() == null) {
            invoice.setScratchCardSalesOrderCollection(new ArrayList<ScratchCardSalesOrder>());
        }
        if (invoice.getPurchaseOrderCollection() == null) {
            invoice.setPurchaseOrderCollection(new ArrayList<PurchaseOrder>());
        }
        if (invoice.getSchoolFeeInvoiceDetailsCollection() == null) {
            invoice.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = invoice.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                invoice.setBranchId(branchId);
            }
            BusinessSubcategory businessSubcategoryId = invoice.getBusinessSubcategoryId();
            if (businessSubcategoryId != null) {
                businessSubcategoryId = em.getReference(businessSubcategoryId.getClass(), businessSubcategoryId.getId());
                invoice.setBusinessSubcategoryId(businessSubcategoryId);
            }
            BusinessSubcategoryServices businessSubcategoryServiceId = invoice.getBusinessSubcategoryServiceId();
            if (businessSubcategoryServiceId != null) {
                businessSubcategoryServiceId = em.getReference(businessSubcategoryServiceId.getClass(), businessSubcategoryServiceId.getId());
                invoice.setBusinessSubcategoryServiceId(businessSubcategoryServiceId);
            }
            Employee employeeId = invoice.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                invoice.setEmployeeId(employeeId);
            }
            Student studentId = invoice.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                invoice.setStudentId(studentId);
            }
            Employee createdByEmployeeId = invoice.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId = em.getReference(createdByEmployeeId.getClass(), createdByEmployeeId.getEmployeeId());
                invoice.setCreatedByEmployeeId(createdByEmployeeId);
            }
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollection = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetailsToAttach : invoice.getUtilityBillDetailsCollection()) {
                utilityBillDetailsCollectionUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollection.add(utilityBillDetailsCollectionUtilityBillDetailsToAttach);
            }
            invoice.setUtilityBillDetailsCollection(attachedUtilityBillDetailsCollection);
            Collection<AccountTransaction> attachedAccountTransactionCollection = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionAccountTransactionToAttach : invoice.getAccountTransactionCollection()) {
                accountTransactionCollectionAccountTransactionToAttach = em.getReference(accountTransactionCollectionAccountTransactionToAttach.getClass(), accountTransactionCollectionAccountTransactionToAttach.getId());
                attachedAccountTransactionCollection.add(accountTransactionCollectionAccountTransactionToAttach);
            }
            invoice.setAccountTransactionCollection(attachedAccountTransactionCollection);
            Collection<ScratchCardSalesOrder> attachedScratchCardSalesOrderCollection = new ArrayList<ScratchCardSalesOrder>();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach : invoice.getScratchCardSalesOrderCollection()) {
                scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach = em.getReference(scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach.getClass(), scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach.getId());
                attachedScratchCardSalesOrderCollection.add(scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach);
            }
            invoice.setScratchCardSalesOrderCollection(attachedScratchCardSalesOrderCollection);
            Collection<PurchaseOrder> attachedPurchaseOrderCollection = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrderToAttach : invoice.getPurchaseOrderCollection()) {
                purchaseOrderCollectionPurchaseOrderToAttach = em.getReference(purchaseOrderCollectionPurchaseOrderToAttach.getClass(), purchaseOrderCollectionPurchaseOrderToAttach.getId());
                attachedPurchaseOrderCollection.add(purchaseOrderCollectionPurchaseOrderToAttach);
            }
            invoice.setPurchaseOrderCollection(attachedPurchaseOrderCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : invoice.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            invoice.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            em.persist(invoice);
            if (branchId != null) {
                branchId.getInvoiceCollection().add(invoice);
                branchId = em.merge(branchId);
            }
            if (businessSubcategoryId != null) {
                businessSubcategoryId.getInvoiceCollection().add(invoice);
                businessSubcategoryId = em.merge(businessSubcategoryId);
            }
            if (businessSubcategoryServiceId != null) {
                businessSubcategoryServiceId.getInvoiceCollection().add(invoice);
                businessSubcategoryServiceId = em.merge(businessSubcategoryServiceId);
            }
            if (employeeId != null) {
                employeeId.getInvoiceCollection().add(invoice);
                employeeId = em.merge(employeeId);
            }
            if (studentId != null) {
                studentId.getInvoiceCollection().add(invoice);
                studentId = em.merge(studentId);
            }
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getInvoiceCollection().add(invoice);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : invoice.getUtilityBillDetailsCollection()) {
                Invoice oldInvoiceIdOfUtilityBillDetailsCollectionUtilityBillDetails = utilityBillDetailsCollectionUtilityBillDetails.getInvoiceId();
                utilityBillDetailsCollectionUtilityBillDetails.setInvoiceId(invoice);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
                if (oldInvoiceIdOfUtilityBillDetailsCollectionUtilityBillDetails != null) {
                    oldInvoiceIdOfUtilityBillDetailsCollectionUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionUtilityBillDetails);
                    oldInvoiceIdOfUtilityBillDetailsCollectionUtilityBillDetails = em.merge(oldInvoiceIdOfUtilityBillDetailsCollectionUtilityBillDetails);
                }
            }
            for (AccountTransaction accountTransactionCollectionAccountTransaction : invoice.getAccountTransactionCollection()) {
                Invoice oldInvoiceIdOfAccountTransactionCollectionAccountTransaction = accountTransactionCollectionAccountTransaction.getInvoiceId();
                accountTransactionCollectionAccountTransaction.setInvoiceId(invoice);
                accountTransactionCollectionAccountTransaction = em.merge(accountTransactionCollectionAccountTransaction);
                if (oldInvoiceIdOfAccountTransactionCollectionAccountTransaction != null) {
                    oldInvoiceIdOfAccountTransactionCollectionAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionAccountTransaction);
                    oldInvoiceIdOfAccountTransactionCollectionAccountTransaction = em.merge(oldInvoiceIdOfAccountTransactionCollectionAccountTransaction);
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrder : invoice.getScratchCardSalesOrderCollection()) {
                Invoice oldInvoiceIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder = scratchCardSalesOrderCollectionScratchCardSalesOrder.getInvoiceId();
                scratchCardSalesOrderCollectionScratchCardSalesOrder.setInvoiceId(invoice);
                scratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionScratchCardSalesOrder);
                if (oldInvoiceIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder != null) {
                    oldInvoiceIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrderCollectionScratchCardSalesOrder);
                    oldInvoiceIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(oldInvoiceIdOfScratchCardSalesOrderCollectionScratchCardSalesOrder);
                }
            }
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrder : invoice.getPurchaseOrderCollection()) {
                Invoice oldInvoiceIdOfPurchaseOrderCollectionPurchaseOrder = purchaseOrderCollectionPurchaseOrder.getInvoiceId();
                purchaseOrderCollectionPurchaseOrder.setInvoiceId(invoice);
                purchaseOrderCollectionPurchaseOrder = em.merge(purchaseOrderCollectionPurchaseOrder);
                if (oldInvoiceIdOfPurchaseOrderCollectionPurchaseOrder != null) {
                    oldInvoiceIdOfPurchaseOrderCollectionPurchaseOrder.getPurchaseOrderCollection().remove(purchaseOrderCollectionPurchaseOrder);
                    oldInvoiceIdOfPurchaseOrderCollectionPurchaseOrder = em.merge(oldInvoiceIdOfPurchaseOrderCollectionPurchaseOrder);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : invoice.getSchoolFeeInvoiceDetailsCollection()) {
                Invoice oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getInvoiceId();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setInvoiceId(invoice);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findInvoice(invoice.getId()) != null) {
                throw new PreexistingEntityException("Invoice " + invoice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Invoice invoice) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Invoice persistentInvoice = em.find(Invoice.class, invoice.getId());
            CompanyBranch branchIdOld = persistentInvoice.getBranchId();
            CompanyBranch branchIdNew = invoice.getBranchId();
            BusinessSubcategory businessSubcategoryIdOld = persistentInvoice.getBusinessSubcategoryId();
            BusinessSubcategory businessSubcategoryIdNew = invoice.getBusinessSubcategoryId();
            BusinessSubcategoryServices businessSubcategoryServiceIdOld = persistentInvoice.getBusinessSubcategoryServiceId();
            BusinessSubcategoryServices businessSubcategoryServiceIdNew = invoice.getBusinessSubcategoryServiceId();
            Employee employeeIdOld = persistentInvoice.getEmployeeId();
            Employee employeeIdNew = invoice.getEmployeeId();
            Student studentIdOld = persistentInvoice.getStudentId();
            Student studentIdNew = invoice.getStudentId();
            Employee createdByEmployeeIdOld = persistentInvoice.getCreatedByEmployeeId();
            Employee createdByEmployeeIdNew = invoice.getCreatedByEmployeeId();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionOld = persistentInvoice.getUtilityBillDetailsCollection();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionNew = invoice.getUtilityBillDetailsCollection();
            Collection<AccountTransaction> accountTransactionCollectionOld = persistentInvoice.getAccountTransactionCollection();
            Collection<AccountTransaction> accountTransactionCollectionNew = invoice.getAccountTransactionCollection();
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionOld = persistentInvoice.getScratchCardSalesOrderCollection();
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionNew = invoice.getScratchCardSalesOrderCollection();
            Collection<PurchaseOrder> purchaseOrderCollectionOld = persistentInvoice.getPurchaseOrderCollection();
            Collection<PurchaseOrder> purchaseOrderCollectionNew = invoice.getPurchaseOrderCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentInvoice.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = invoice.getSchoolFeeInvoiceDetailsCollection();
            List<String> illegalOrphanMessages = null;
            for (AccountTransaction accountTransactionCollectionOldAccountTransaction : accountTransactionCollectionOld) {
                if (!accountTransactionCollectionNew.contains(accountTransactionCollectionOldAccountTransaction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AccountTransaction " + accountTransactionCollectionOldAccountTransaction + " since its invoiceId field is not nullable.");
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionOldScratchCardSalesOrder : scratchCardSalesOrderCollectionOld) {
                if (!scratchCardSalesOrderCollectionNew.contains(scratchCardSalesOrderCollectionOldScratchCardSalesOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ScratchCardSalesOrder " + scratchCardSalesOrderCollectionOldScratchCardSalesOrder + " since its invoiceId field is not nullable.");
                }
            }
            for (PurchaseOrder purchaseOrderCollectionOldPurchaseOrder : purchaseOrderCollectionOld) {
                if (!purchaseOrderCollectionNew.contains(purchaseOrderCollectionOldPurchaseOrder)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PurchaseOrder " + purchaseOrderCollectionOldPurchaseOrder + " since its invoiceId field is not nullable.");
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails + " since its invoiceId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                invoice.setBranchId(branchIdNew);
            }
            if (businessSubcategoryIdNew != null) {
                businessSubcategoryIdNew = em.getReference(businessSubcategoryIdNew.getClass(), businessSubcategoryIdNew.getId());
                invoice.setBusinessSubcategoryId(businessSubcategoryIdNew);
            }
            if (businessSubcategoryServiceIdNew != null) {
                businessSubcategoryServiceIdNew = em.getReference(businessSubcategoryServiceIdNew.getClass(), businessSubcategoryServiceIdNew.getId());
                invoice.setBusinessSubcategoryServiceId(businessSubcategoryServiceIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                invoice.setEmployeeId(employeeIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                invoice.setStudentId(studentIdNew);
            }
            if (createdByEmployeeIdNew != null) {
                createdByEmployeeIdNew = em.getReference(createdByEmployeeIdNew.getClass(), createdByEmployeeIdNew.getEmployeeId());
                invoice.setCreatedByEmployeeId(createdByEmployeeIdNew);
            }
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollectionNew = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetailsToAttach : utilityBillDetailsCollectionNew) {
                utilityBillDetailsCollectionNewUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollectionNew.add(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach);
            }
            utilityBillDetailsCollectionNew = attachedUtilityBillDetailsCollectionNew;
            invoice.setUtilityBillDetailsCollection(utilityBillDetailsCollectionNew);
            Collection<AccountTransaction> attachedAccountTransactionCollectionNew = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionNewAccountTransactionToAttach : accountTransactionCollectionNew) {
                accountTransactionCollectionNewAccountTransactionToAttach = em.getReference(accountTransactionCollectionNewAccountTransactionToAttach.getClass(), accountTransactionCollectionNewAccountTransactionToAttach.getId());
                attachedAccountTransactionCollectionNew.add(accountTransactionCollectionNewAccountTransactionToAttach);
            }
            accountTransactionCollectionNew = attachedAccountTransactionCollectionNew;
            invoice.setAccountTransactionCollection(accountTransactionCollectionNew);
            Collection<ScratchCardSalesOrder> attachedScratchCardSalesOrderCollectionNew = new ArrayList<ScratchCardSalesOrder>();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach : scratchCardSalesOrderCollectionNew) {
                scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach = em.getReference(scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach.getClass(), scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach.getId());
                attachedScratchCardSalesOrderCollectionNew.add(scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach);
            }
            scratchCardSalesOrderCollectionNew = attachedScratchCardSalesOrderCollectionNew;
            invoice.setScratchCardSalesOrderCollection(scratchCardSalesOrderCollectionNew);
            Collection<PurchaseOrder> attachedPurchaseOrderCollectionNew = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderCollectionNewPurchaseOrderToAttach : purchaseOrderCollectionNew) {
                purchaseOrderCollectionNewPurchaseOrderToAttach = em.getReference(purchaseOrderCollectionNewPurchaseOrderToAttach.getClass(), purchaseOrderCollectionNewPurchaseOrderToAttach.getId());
                attachedPurchaseOrderCollectionNew.add(purchaseOrderCollectionNewPurchaseOrderToAttach);
            }
            purchaseOrderCollectionNew = attachedPurchaseOrderCollectionNew;
            invoice.setPurchaseOrderCollection(purchaseOrderCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            invoice.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            invoice = em.merge(invoice);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getInvoiceCollection().remove(invoice);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getInvoiceCollection().add(invoice);
                branchIdNew = em.merge(branchIdNew);
            }
            if (businessSubcategoryIdOld != null && !businessSubcategoryIdOld.equals(businessSubcategoryIdNew)) {
                businessSubcategoryIdOld.getInvoiceCollection().remove(invoice);
                businessSubcategoryIdOld = em.merge(businessSubcategoryIdOld);
            }
            if (businessSubcategoryIdNew != null && !businessSubcategoryIdNew.equals(businessSubcategoryIdOld)) {
                businessSubcategoryIdNew.getInvoiceCollection().add(invoice);
                businessSubcategoryIdNew = em.merge(businessSubcategoryIdNew);
            }
            if (businessSubcategoryServiceIdOld != null && !businessSubcategoryServiceIdOld.equals(businessSubcategoryServiceIdNew)) {
                businessSubcategoryServiceIdOld.getInvoiceCollection().remove(invoice);
                businessSubcategoryServiceIdOld = em.merge(businessSubcategoryServiceIdOld);
            }
            if (businessSubcategoryServiceIdNew != null && !businessSubcategoryServiceIdNew.equals(businessSubcategoryServiceIdOld)) {
                businessSubcategoryServiceIdNew.getInvoiceCollection().add(invoice);
                businessSubcategoryServiceIdNew = em.merge(businessSubcategoryServiceIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getInvoiceCollection().remove(invoice);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getInvoiceCollection().add(invoice);
                employeeIdNew = em.merge(employeeIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getInvoiceCollection().remove(invoice);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getInvoiceCollection().add(invoice);
                studentIdNew = em.merge(studentIdNew);
            }
            if (createdByEmployeeIdOld != null && !createdByEmployeeIdOld.equals(createdByEmployeeIdNew)) {
                createdByEmployeeIdOld.getInvoiceCollection().remove(invoice);
                createdByEmployeeIdOld = em.merge(createdByEmployeeIdOld);
            }
            if (createdByEmployeeIdNew != null && !createdByEmployeeIdNew.equals(createdByEmployeeIdOld)) {
                createdByEmployeeIdNew.getInvoiceCollection().add(invoice);
                createdByEmployeeIdNew = em.merge(createdByEmployeeIdNew);
            }
            for (UtilityBillDetails utilityBillDetailsCollectionOldUtilityBillDetails : utilityBillDetailsCollectionOld) {
                if (!utilityBillDetailsCollectionNew.contains(utilityBillDetailsCollectionOldUtilityBillDetails)) {
                    utilityBillDetailsCollectionOldUtilityBillDetails.setInvoiceId(null);
                    utilityBillDetailsCollectionOldUtilityBillDetails = em.merge(utilityBillDetailsCollectionOldUtilityBillDetails);
                }
            }
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetails : utilityBillDetailsCollectionNew) {
                if (!utilityBillDetailsCollectionOld.contains(utilityBillDetailsCollectionNewUtilityBillDetails)) {
                    Invoice oldInvoiceIdOfUtilityBillDetailsCollectionNewUtilityBillDetails = utilityBillDetailsCollectionNewUtilityBillDetails.getInvoiceId();
                    utilityBillDetailsCollectionNewUtilityBillDetails.setInvoiceId(invoice);
                    utilityBillDetailsCollectionNewUtilityBillDetails = em.merge(utilityBillDetailsCollectionNewUtilityBillDetails);
                    if (oldInvoiceIdOfUtilityBillDetailsCollectionNewUtilityBillDetails != null && !oldInvoiceIdOfUtilityBillDetailsCollectionNewUtilityBillDetails.equals(invoice)) {
                        oldInvoiceIdOfUtilityBillDetailsCollectionNewUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionNewUtilityBillDetails);
                        oldInvoiceIdOfUtilityBillDetailsCollectionNewUtilityBillDetails = em.merge(oldInvoiceIdOfUtilityBillDetailsCollectionNewUtilityBillDetails);
                    }
                }
            }
            for (AccountTransaction accountTransactionCollectionNewAccountTransaction : accountTransactionCollectionNew) {
                if (!accountTransactionCollectionOld.contains(accountTransactionCollectionNewAccountTransaction)) {
                    Invoice oldInvoiceIdOfAccountTransactionCollectionNewAccountTransaction = accountTransactionCollectionNewAccountTransaction.getInvoiceId();
                    accountTransactionCollectionNewAccountTransaction.setInvoiceId(invoice);
                    accountTransactionCollectionNewAccountTransaction = em.merge(accountTransactionCollectionNewAccountTransaction);
                    if (oldInvoiceIdOfAccountTransactionCollectionNewAccountTransaction != null && !oldInvoiceIdOfAccountTransactionCollectionNewAccountTransaction.equals(invoice)) {
                        oldInvoiceIdOfAccountTransactionCollectionNewAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionNewAccountTransaction);
                        oldInvoiceIdOfAccountTransactionCollectionNewAccountTransaction = em.merge(oldInvoiceIdOfAccountTransactionCollectionNewAccountTransaction);
                    }
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionNewScratchCardSalesOrder : scratchCardSalesOrderCollectionNew) {
                if (!scratchCardSalesOrderCollectionOld.contains(scratchCardSalesOrderCollectionNewScratchCardSalesOrder)) {
                    Invoice oldInvoiceIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder = scratchCardSalesOrderCollectionNewScratchCardSalesOrder.getInvoiceId();
                    scratchCardSalesOrderCollectionNewScratchCardSalesOrder.setInvoiceId(invoice);
                    scratchCardSalesOrderCollectionNewScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                    if (oldInvoiceIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder != null && !oldInvoiceIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder.equals(invoice)) {
                        oldInvoiceIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                        oldInvoiceIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder = em.merge(oldInvoiceIdOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                    }
                }
            }
            for (PurchaseOrder purchaseOrderCollectionNewPurchaseOrder : purchaseOrderCollectionNew) {
                if (!purchaseOrderCollectionOld.contains(purchaseOrderCollectionNewPurchaseOrder)) {
                    Invoice oldInvoiceIdOfPurchaseOrderCollectionNewPurchaseOrder = purchaseOrderCollectionNewPurchaseOrder.getInvoiceId();
                    purchaseOrderCollectionNewPurchaseOrder.setInvoiceId(invoice);
                    purchaseOrderCollectionNewPurchaseOrder = em.merge(purchaseOrderCollectionNewPurchaseOrder);
                    if (oldInvoiceIdOfPurchaseOrderCollectionNewPurchaseOrder != null && !oldInvoiceIdOfPurchaseOrderCollectionNewPurchaseOrder.equals(invoice)) {
                        oldInvoiceIdOfPurchaseOrderCollectionNewPurchaseOrder.getPurchaseOrderCollection().remove(purchaseOrderCollectionNewPurchaseOrder);
                        oldInvoiceIdOfPurchaseOrderCollectionNewPurchaseOrder = em.merge(oldInvoiceIdOfPurchaseOrderCollectionNewPurchaseOrder);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    Invoice oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getInvoiceId();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setInvoiceId(invoice);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(invoice)) {
                        oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldInvoiceIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = invoice.getId();
                if (findInvoice(id) == null) {
                    throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            Invoice invoice;
            try {
                invoice = em.getReference(Invoice.class, id);
                invoice.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The invoice with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<AccountTransaction> accountTransactionCollectionOrphanCheck = invoice.getAccountTransactionCollection();
            for (AccountTransaction accountTransactionCollectionOrphanCheckAccountTransaction : accountTransactionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the AccountTransaction " + accountTransactionCollectionOrphanCheckAccountTransaction + " in its accountTransactionCollection field has a non-nullable invoiceId field.");
            }
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionOrphanCheck = invoice.getScratchCardSalesOrderCollection();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionOrphanCheckScratchCardSalesOrder : scratchCardSalesOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the ScratchCardSalesOrder " + scratchCardSalesOrderCollectionOrphanCheckScratchCardSalesOrder + " in its scratchCardSalesOrderCollection field has a non-nullable invoiceId field.");
            }
            Collection<PurchaseOrder> purchaseOrderCollectionOrphanCheck = invoice.getPurchaseOrderCollection();
            for (PurchaseOrder purchaseOrderCollectionOrphanCheckPurchaseOrder : purchaseOrderCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the PurchaseOrder " + purchaseOrderCollectionOrphanCheckPurchaseOrder + " in its purchaseOrderCollection field has a non-nullable invoiceId field.");
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOrphanCheck = invoice.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Invoice (" + invoice + ") cannot be destroyed since the SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails + " in its schoolFeeInvoiceDetailsCollection field has a non-nullable invoiceId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = invoice.getBranchId();
            if (branchId != null) {
                branchId.getInvoiceCollection().remove(invoice);
                branchId = em.merge(branchId);
            }
            BusinessSubcategory businessSubcategoryId = invoice.getBusinessSubcategoryId();
            if (businessSubcategoryId != null) {
                businessSubcategoryId.getInvoiceCollection().remove(invoice);
                businessSubcategoryId = em.merge(businessSubcategoryId);
            }
            BusinessSubcategoryServices businessSubcategoryServiceId = invoice.getBusinessSubcategoryServiceId();
            if (businessSubcategoryServiceId != null) {
                businessSubcategoryServiceId.getInvoiceCollection().remove(invoice);
                businessSubcategoryServiceId = em.merge(businessSubcategoryServiceId);
            }
            Employee employeeId = invoice.getEmployeeId();
            if (employeeId != null) {
                employeeId.getInvoiceCollection().remove(invoice);
                employeeId = em.merge(employeeId);
            }
            Student studentId = invoice.getStudentId();
            if (studentId != null) {
                studentId.getInvoiceCollection().remove(invoice);
                studentId = em.merge(studentId);
            }
            Employee createdByEmployeeId = invoice.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getInvoiceCollection().remove(invoice);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            Collection<UtilityBillDetails> utilityBillDetailsCollection = invoice.getUtilityBillDetailsCollection();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : utilityBillDetailsCollection) {
                utilityBillDetailsCollectionUtilityBillDetails.setInvoiceId(null);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
            }
            em.remove(invoice);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
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

    public List<Invoice> findInvoiceEntities() {
        return findInvoiceEntities(true, -1, -1);
    }

    public List<Invoice> findInvoiceEntities(int maxResults, int firstResult) {
        return findInvoiceEntities(false, maxResults, firstResult);
    }

    private List<Invoice> findInvoiceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Invoice.class));
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

    public Invoice findInvoice(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Invoice.class, id);
        } finally {
            em.close();
        }
    }

    public int getInvoiceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Invoice> rt = cq.from(Invoice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
