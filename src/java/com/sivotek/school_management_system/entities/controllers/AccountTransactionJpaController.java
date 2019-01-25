/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.AccountTransaction;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.BankAccount;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.TransactionCancellation;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class AccountTransactionJpaController implements Serializable {

    public AccountTransactionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AccountTransaction accountTransaction) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (accountTransaction.getTransactionCancellationCollection() == null) {
            accountTransaction.setTransactionCancellationCollection(new ArrayList<TransactionCancellation>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = accountTransaction.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                accountTransaction.setBranchId(branchId);
            }
            Invoice invoiceId = accountTransaction.getInvoiceId();
            if (invoiceId != null) {
                invoiceId = em.getReference(invoiceId.getClass(), invoiceId.getId());
                accountTransaction.setInvoiceId(invoiceId);
            }
            BankAccount bankAccountId = accountTransaction.getBankAccountId();
            if (bankAccountId != null) {
                bankAccountId = em.getReference(bankAccountId.getClass(), bankAccountId.getId());
                accountTransaction.setBankAccountId(bankAccountId);
            }
            Employee createdByEmployeeId = accountTransaction.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId = em.getReference(createdByEmployeeId.getClass(), createdByEmployeeId.getEmployeeId());
                accountTransaction.setCreatedByEmployeeId(createdByEmployeeId);
            }
            Collection<TransactionCancellation> attachedTransactionCancellationCollection = new ArrayList<TransactionCancellation>();
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellationToAttach : accountTransaction.getTransactionCancellationCollection()) {
                transactionCancellationCollectionTransactionCancellationToAttach = em.getReference(transactionCancellationCollectionTransactionCancellationToAttach.getClass(), transactionCancellationCollectionTransactionCancellationToAttach.getId());
                attachedTransactionCancellationCollection.add(transactionCancellationCollectionTransactionCancellationToAttach);
            }
            accountTransaction.setTransactionCancellationCollection(attachedTransactionCancellationCollection);
            em.persist(accountTransaction);
            if (branchId != null) {
                branchId.getAccountTransactionCollection().add(accountTransaction);
                branchId = em.merge(branchId);
            }
            if (invoiceId != null) {
                invoiceId.getAccountTransactionCollection().add(accountTransaction);
                invoiceId = em.merge(invoiceId);
            }
            if (bankAccountId != null) {
                bankAccountId.getAccountTransactionCollection().add(accountTransaction);
                bankAccountId = em.merge(bankAccountId);
            }
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getAccountTransactionCollection().add(accountTransaction);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            for (TransactionCancellation transactionCancellationCollectionTransactionCancellation : accountTransaction.getTransactionCancellationCollection()) {
                AccountTransaction oldTransactionIdOfTransactionCancellationCollectionTransactionCancellation = transactionCancellationCollectionTransactionCancellation.getTransactionId();
                transactionCancellationCollectionTransactionCancellation.setTransactionId(accountTransaction);
                transactionCancellationCollectionTransactionCancellation = em.merge(transactionCancellationCollectionTransactionCancellation);
                if (oldTransactionIdOfTransactionCancellationCollectionTransactionCancellation != null) {
                    oldTransactionIdOfTransactionCancellationCollectionTransactionCancellation.getTransactionCancellationCollection().remove(transactionCancellationCollectionTransactionCancellation);
                    oldTransactionIdOfTransactionCancellationCollectionTransactionCancellation = em.merge(oldTransactionIdOfTransactionCancellationCollectionTransactionCancellation);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAccountTransaction(accountTransaction.getId()) != null) {
                throw new PreexistingEntityException("AccountTransaction " + accountTransaction + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AccountTransaction accountTransaction) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            AccountTransaction persistentAccountTransaction = em.find(AccountTransaction.class, accountTransaction.getId());
            CompanyBranch branchIdOld = persistentAccountTransaction.getBranchId();
            CompanyBranch branchIdNew = accountTransaction.getBranchId();
            Invoice invoiceIdOld = persistentAccountTransaction.getInvoiceId();
            Invoice invoiceIdNew = accountTransaction.getInvoiceId();
            BankAccount bankAccountIdOld = persistentAccountTransaction.getBankAccountId();
            BankAccount bankAccountIdNew = accountTransaction.getBankAccountId();
            Employee createdByEmployeeIdOld = persistentAccountTransaction.getCreatedByEmployeeId();
            Employee createdByEmployeeIdNew = accountTransaction.getCreatedByEmployeeId();
            Collection<TransactionCancellation> transactionCancellationCollectionOld = persistentAccountTransaction.getTransactionCancellationCollection();
            Collection<TransactionCancellation> transactionCancellationCollectionNew = accountTransaction.getTransactionCancellationCollection();
            List<String> illegalOrphanMessages = null;
            for (TransactionCancellation transactionCancellationCollectionOldTransactionCancellation : transactionCancellationCollectionOld) {
                if (!transactionCancellationCollectionNew.contains(transactionCancellationCollectionOldTransactionCancellation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransactionCancellation " + transactionCancellationCollectionOldTransactionCancellation + " since its transactionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                accountTransaction.setBranchId(branchIdNew);
            }
            if (invoiceIdNew != null) {
                invoiceIdNew = em.getReference(invoiceIdNew.getClass(), invoiceIdNew.getId());
                accountTransaction.setInvoiceId(invoiceIdNew);
            }
            if (bankAccountIdNew != null) {
                bankAccountIdNew = em.getReference(bankAccountIdNew.getClass(), bankAccountIdNew.getId());
                accountTransaction.setBankAccountId(bankAccountIdNew);
            }
            if (createdByEmployeeIdNew != null) {
                createdByEmployeeIdNew = em.getReference(createdByEmployeeIdNew.getClass(), createdByEmployeeIdNew.getEmployeeId());
                accountTransaction.setCreatedByEmployeeId(createdByEmployeeIdNew);
            }
            Collection<TransactionCancellation> attachedTransactionCancellationCollectionNew = new ArrayList<TransactionCancellation>();
            for (TransactionCancellation transactionCancellationCollectionNewTransactionCancellationToAttach : transactionCancellationCollectionNew) {
                transactionCancellationCollectionNewTransactionCancellationToAttach = em.getReference(transactionCancellationCollectionNewTransactionCancellationToAttach.getClass(), transactionCancellationCollectionNewTransactionCancellationToAttach.getId());
                attachedTransactionCancellationCollectionNew.add(transactionCancellationCollectionNewTransactionCancellationToAttach);
            }
            transactionCancellationCollectionNew = attachedTransactionCancellationCollectionNew;
            accountTransaction.setTransactionCancellationCollection(transactionCancellationCollectionNew);
            accountTransaction = em.merge(accountTransaction);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getAccountTransactionCollection().remove(accountTransaction);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getAccountTransactionCollection().add(accountTransaction);
                branchIdNew = em.merge(branchIdNew);
            }
            if (invoiceIdOld != null && !invoiceIdOld.equals(invoiceIdNew)) {
                invoiceIdOld.getAccountTransactionCollection().remove(accountTransaction);
                invoiceIdOld = em.merge(invoiceIdOld);
            }
            if (invoiceIdNew != null && !invoiceIdNew.equals(invoiceIdOld)) {
                invoiceIdNew.getAccountTransactionCollection().add(accountTransaction);
                invoiceIdNew = em.merge(invoiceIdNew);
            }
            if (bankAccountIdOld != null && !bankAccountIdOld.equals(bankAccountIdNew)) {
                bankAccountIdOld.getAccountTransactionCollection().remove(accountTransaction);
                bankAccountIdOld = em.merge(bankAccountIdOld);
            }
            if (bankAccountIdNew != null && !bankAccountIdNew.equals(bankAccountIdOld)) {
                bankAccountIdNew.getAccountTransactionCollection().add(accountTransaction);
                bankAccountIdNew = em.merge(bankAccountIdNew);
            }
            if (createdByEmployeeIdOld != null && !createdByEmployeeIdOld.equals(createdByEmployeeIdNew)) {
                createdByEmployeeIdOld.getAccountTransactionCollection().remove(accountTransaction);
                createdByEmployeeIdOld = em.merge(createdByEmployeeIdOld);
            }
            if (createdByEmployeeIdNew != null && !createdByEmployeeIdNew.equals(createdByEmployeeIdOld)) {
                createdByEmployeeIdNew.getAccountTransactionCollection().add(accountTransaction);
                createdByEmployeeIdNew = em.merge(createdByEmployeeIdNew);
            }
            for (TransactionCancellation transactionCancellationCollectionNewTransactionCancellation : transactionCancellationCollectionNew) {
                if (!transactionCancellationCollectionOld.contains(transactionCancellationCollectionNewTransactionCancellation)) {
                    AccountTransaction oldTransactionIdOfTransactionCancellationCollectionNewTransactionCancellation = transactionCancellationCollectionNewTransactionCancellation.getTransactionId();
                    transactionCancellationCollectionNewTransactionCancellation.setTransactionId(accountTransaction);
                    transactionCancellationCollectionNewTransactionCancellation = em.merge(transactionCancellationCollectionNewTransactionCancellation);
                    if (oldTransactionIdOfTransactionCancellationCollectionNewTransactionCancellation != null && !oldTransactionIdOfTransactionCancellationCollectionNewTransactionCancellation.equals(accountTransaction)) {
                        oldTransactionIdOfTransactionCancellationCollectionNewTransactionCancellation.getTransactionCancellationCollection().remove(transactionCancellationCollectionNewTransactionCancellation);
                        oldTransactionIdOfTransactionCancellationCollectionNewTransactionCancellation = em.merge(oldTransactionIdOfTransactionCancellationCollectionNewTransactionCancellation);
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
                Long id = accountTransaction.getId();
                if (findAccountTransaction(id) == null) {
                    throw new NonexistentEntityException("The accountTransaction with id " + id + " no longer exists.");
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
            AccountTransaction accountTransaction;
            try {
                accountTransaction = em.getReference(AccountTransaction.class, id);
                accountTransaction.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The accountTransaction with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TransactionCancellation> transactionCancellationCollectionOrphanCheck = accountTransaction.getTransactionCancellationCollection();
            for (TransactionCancellation transactionCancellationCollectionOrphanCheckTransactionCancellation : transactionCancellationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AccountTransaction (" + accountTransaction + ") cannot be destroyed since the TransactionCancellation " + transactionCancellationCollectionOrphanCheckTransactionCancellation + " in its transactionCancellationCollection field has a non-nullable transactionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = accountTransaction.getBranchId();
            if (branchId != null) {
                branchId.getAccountTransactionCollection().remove(accountTransaction);
                branchId = em.merge(branchId);
            }
            Invoice invoiceId = accountTransaction.getInvoiceId();
            if (invoiceId != null) {
                invoiceId.getAccountTransactionCollection().remove(accountTransaction);
                invoiceId = em.merge(invoiceId);
            }
            BankAccount bankAccountId = accountTransaction.getBankAccountId();
            if (bankAccountId != null) {
                bankAccountId.getAccountTransactionCollection().remove(accountTransaction);
                bankAccountId = em.merge(bankAccountId);
            }
            Employee createdByEmployeeId = accountTransaction.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getAccountTransactionCollection().remove(accountTransaction);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            em.remove(accountTransaction);
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

    public List<AccountTransaction> findAccountTransactionEntities() {
        return findAccountTransactionEntities(true, -1, -1);
    }

    public List<AccountTransaction> findAccountTransactionEntities(int maxResults, int firstResult) {
        return findAccountTransactionEntities(false, maxResults, firstResult);
    }

    private List<AccountTransaction> findAccountTransactionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AccountTransaction.class));
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

    public AccountTransaction findAccountTransaction(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AccountTransaction.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountTransactionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AccountTransaction> rt = cq.from(AccountTransaction.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
