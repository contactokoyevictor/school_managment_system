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
import com.sivotek.school_management_system.entities.BankAccountPrivilege;
import com.sivotek.school_management_system.entities.Employee;
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
public class BankAccountPrivilegeJpaController implements Serializable {

    public BankAccountPrivilegeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BankAccountPrivilege bankAccountPrivilege) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = bankAccountPrivilege.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                bankAccountPrivilege.setBranchId(branchId);
            }
            BankAccount accountId = bankAccountPrivilege.getAccountId();
            if (accountId != null) {
                accountId = em.getReference(accountId.getClass(), accountId.getId());
                bankAccountPrivilege.setAccountId(accountId);
            }
            Employee employeeId = bankAccountPrivilege.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                bankAccountPrivilege.setEmployeeId(employeeId);
            }
            Employee createdBy = bankAccountPrivilege.getCreatedBy();
            if (createdBy != null) {
                createdBy = em.getReference(createdBy.getClass(), createdBy.getEmployeeId());
                bankAccountPrivilege.setCreatedBy(createdBy);
            }
            Employee lastModifiedBy = bankAccountPrivilege.getLastModifiedBy();
            if (lastModifiedBy != null) {
                lastModifiedBy = em.getReference(lastModifiedBy.getClass(), lastModifiedBy.getEmployeeId());
                bankAccountPrivilege.setLastModifiedBy(lastModifiedBy);
            }
            em.persist(bankAccountPrivilege);
            if (branchId != null) {
                branchId.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                branchId = em.merge(branchId);
            }
            if (accountId != null) {
                accountId.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                accountId = em.merge(accountId);
            }
            if (employeeId != null) {
                employeeId.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                employeeId = em.merge(employeeId);
            }
            if (createdBy != null) {
                createdBy.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                createdBy = em.merge(createdBy);
            }
            if (lastModifiedBy != null) {
                lastModifiedBy.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                lastModifiedBy = em.merge(lastModifiedBy);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBankAccountPrivilege(bankAccountPrivilege.getId()) != null) {
                throw new PreexistingEntityException("BankAccountPrivilege " + bankAccountPrivilege + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BankAccountPrivilege bankAccountPrivilege) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BankAccountPrivilege persistentBankAccountPrivilege = em.find(BankAccountPrivilege.class, bankAccountPrivilege.getId());
            CompanyBranch branchIdOld = persistentBankAccountPrivilege.getBranchId();
            CompanyBranch branchIdNew = bankAccountPrivilege.getBranchId();
            BankAccount accountIdOld = persistentBankAccountPrivilege.getAccountId();
            BankAccount accountIdNew = bankAccountPrivilege.getAccountId();
            Employee employeeIdOld = persistentBankAccountPrivilege.getEmployeeId();
            Employee employeeIdNew = bankAccountPrivilege.getEmployeeId();
            Employee createdByOld = persistentBankAccountPrivilege.getCreatedBy();
            Employee createdByNew = bankAccountPrivilege.getCreatedBy();
            Employee lastModifiedByOld = persistentBankAccountPrivilege.getLastModifiedBy();
            Employee lastModifiedByNew = bankAccountPrivilege.getLastModifiedBy();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                bankAccountPrivilege.setBranchId(branchIdNew);
            }
            if (accountIdNew != null) {
                accountIdNew = em.getReference(accountIdNew.getClass(), accountIdNew.getId());
                bankAccountPrivilege.setAccountId(accountIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                bankAccountPrivilege.setEmployeeId(employeeIdNew);
            }
            if (createdByNew != null) {
                createdByNew = em.getReference(createdByNew.getClass(), createdByNew.getEmployeeId());
                bankAccountPrivilege.setCreatedBy(createdByNew);
            }
            if (lastModifiedByNew != null) {
                lastModifiedByNew = em.getReference(lastModifiedByNew.getClass(), lastModifiedByNew.getEmployeeId());
                bankAccountPrivilege.setLastModifiedBy(lastModifiedByNew);
            }
            bankAccountPrivilege = em.merge(bankAccountPrivilege);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                branchIdNew = em.merge(branchIdNew);
            }
            if (accountIdOld != null && !accountIdOld.equals(accountIdNew)) {
                accountIdOld.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                accountIdOld = em.merge(accountIdOld);
            }
            if (accountIdNew != null && !accountIdNew.equals(accountIdOld)) {
                accountIdNew.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                accountIdNew = em.merge(accountIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                employeeIdNew = em.merge(employeeIdNew);
            }
            if (createdByOld != null && !createdByOld.equals(createdByNew)) {
                createdByOld.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                createdByOld = em.merge(createdByOld);
            }
            if (createdByNew != null && !createdByNew.equals(createdByOld)) {
                createdByNew.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                createdByNew = em.merge(createdByNew);
            }
            if (lastModifiedByOld != null && !lastModifiedByOld.equals(lastModifiedByNew)) {
                lastModifiedByOld.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                lastModifiedByOld = em.merge(lastModifiedByOld);
            }
            if (lastModifiedByNew != null && !lastModifiedByNew.equals(lastModifiedByOld)) {
                lastModifiedByNew.getBankAccountPrivilegeCollection().add(bankAccountPrivilege);
                lastModifiedByNew = em.merge(lastModifiedByNew);
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
                Long id = bankAccountPrivilege.getId();
                if (findBankAccountPrivilege(id) == null) {
                    throw new NonexistentEntityException("The bankAccountPrivilege with id " + id + " no longer exists.");
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
            BankAccountPrivilege bankAccountPrivilege;
            try {
                bankAccountPrivilege = em.getReference(BankAccountPrivilege.class, id);
                bankAccountPrivilege.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bankAccountPrivilege with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = bankAccountPrivilege.getBranchId();
            if (branchId != null) {
                branchId.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                branchId = em.merge(branchId);
            }
            BankAccount accountId = bankAccountPrivilege.getAccountId();
            if (accountId != null) {
                accountId.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                accountId = em.merge(accountId);
            }
            Employee employeeId = bankAccountPrivilege.getEmployeeId();
            if (employeeId != null) {
                employeeId.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                employeeId = em.merge(employeeId);
            }
            Employee createdBy = bankAccountPrivilege.getCreatedBy();
            if (createdBy != null) {
                createdBy.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                createdBy = em.merge(createdBy);
            }
            Employee lastModifiedBy = bankAccountPrivilege.getLastModifiedBy();
            if (lastModifiedBy != null) {
                lastModifiedBy.getBankAccountPrivilegeCollection().remove(bankAccountPrivilege);
                lastModifiedBy = em.merge(lastModifiedBy);
            }
            em.remove(bankAccountPrivilege);
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

    public List<BankAccountPrivilege> findBankAccountPrivilegeEntities() {
        return findBankAccountPrivilegeEntities(true, -1, -1);
    }

    public List<BankAccountPrivilege> findBankAccountPrivilegeEntities(int maxResults, int firstResult) {
        return findBankAccountPrivilegeEntities(false, maxResults, firstResult);
    }

    private List<BankAccountPrivilege> findBankAccountPrivilegeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BankAccountPrivilege.class));
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

    public BankAccountPrivilege findBankAccountPrivilege(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BankAccountPrivilege.class, id);
        } finally {
            em.close();
        }
    }

    public int getBankAccountPrivilegeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BankAccountPrivilege> rt = cq.from(BankAccountPrivilege.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
