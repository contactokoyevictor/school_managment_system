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
import com.sivotek.school_management_system.entities.BankAccount;
import com.sivotek.school_management_system.entities.BankAccountBalance;
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
public class BankAccountBalanceJpaController implements Serializable {

    public BankAccountBalanceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BankAccountBalance bankAccountBalance) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = bankAccountBalance.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                bankAccountBalance.setBranchId(branchId);
            }
            BankAccount accountId = bankAccountBalance.getAccountId();
            if (accountId != null) {
                accountId = em.getReference(accountId.getClass(), accountId.getId());
                bankAccountBalance.setAccountId(accountId);
            }
            em.persist(bankAccountBalance);
            if (branchId != null) {
                branchId.getBankAccountBalanceCollection().add(bankAccountBalance);
                branchId = em.merge(branchId);
            }
            if (accountId != null) {
                accountId.getBankAccountBalanceCollection().add(bankAccountBalance);
                accountId = em.merge(accountId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBankAccountBalance(bankAccountBalance.getId()) != null) {
                throw new PreexistingEntityException("BankAccountBalance " + bankAccountBalance + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BankAccountBalance bankAccountBalance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BankAccountBalance persistentBankAccountBalance = em.find(BankAccountBalance.class, bankAccountBalance.getId());
            CompanyBranch branchIdOld = persistentBankAccountBalance.getBranchId();
            CompanyBranch branchIdNew = bankAccountBalance.getBranchId();
            BankAccount accountIdOld = persistentBankAccountBalance.getAccountId();
            BankAccount accountIdNew = bankAccountBalance.getAccountId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                bankAccountBalance.setBranchId(branchIdNew);
            }
            if (accountIdNew != null) {
                accountIdNew = em.getReference(accountIdNew.getClass(), accountIdNew.getId());
                bankAccountBalance.setAccountId(accountIdNew);
            }
            bankAccountBalance = em.merge(bankAccountBalance);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getBankAccountBalanceCollection().remove(bankAccountBalance);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getBankAccountBalanceCollection().add(bankAccountBalance);
                branchIdNew = em.merge(branchIdNew);
            }
            if (accountIdOld != null && !accountIdOld.equals(accountIdNew)) {
                accountIdOld.getBankAccountBalanceCollection().remove(bankAccountBalance);
                accountIdOld = em.merge(accountIdOld);
            }
            if (accountIdNew != null && !accountIdNew.equals(accountIdOld)) {
                accountIdNew.getBankAccountBalanceCollection().add(bankAccountBalance);
                accountIdNew = em.merge(accountIdNew);
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
                Long id = bankAccountBalance.getId();
                if (findBankAccountBalance(id) == null) {
                    throw new NonexistentEntityException("The bankAccountBalance with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            BankAccountBalance bankAccountBalance;
            try {
                bankAccountBalance = em.getReference(BankAccountBalance.class, id);
                bankAccountBalance.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bankAccountBalance with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = bankAccountBalance.getBranchId();
            if (branchId != null) {
                branchId.getBankAccountBalanceCollection().remove(bankAccountBalance);
                branchId = em.merge(branchId);
            }
            BankAccount accountId = bankAccountBalance.getAccountId();
            if (accountId != null) {
                accountId.getBankAccountBalanceCollection().remove(bankAccountBalance);
                accountId = em.merge(accountId);
            }
            em.remove(bankAccountBalance);
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

    public List<BankAccountBalance> findBankAccountBalanceEntities() {
        return findBankAccountBalanceEntities(true, -1, -1);
    }

    public List<BankAccountBalance> findBankAccountBalanceEntities(int maxResults, int firstResult) {
        return findBankAccountBalanceEntities(false, maxResults, firstResult);
    }

    private List<BankAccountBalance> findBankAccountBalanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BankAccountBalance.class));
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

    public BankAccountBalance findBankAccountBalance(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BankAccountBalance.class, id);
        } finally {
            em.close();
        }
    }

    public int getBankAccountBalanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BankAccountBalance> rt = cq.from(BankAccountBalance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
