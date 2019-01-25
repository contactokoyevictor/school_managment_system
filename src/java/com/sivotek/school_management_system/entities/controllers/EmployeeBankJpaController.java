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
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.EmployeeBank;
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
public class EmployeeBankJpaController implements Serializable {

    public EmployeeBankJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeeBank employeeBank) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = employeeBank.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                employeeBank.setBranchId(branchId);
            }
            Employee employeeId = employeeBank.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                employeeBank.setEmployeeId(employeeId);
            }
            em.persist(employeeBank);
            if (branchId != null) {
                branchId.getEmployeeBankCollection().add(employeeBank);
                branchId = em.merge(branchId);
            }
            if (employeeId != null) {
                employeeId.getEmployeeBankCollection().add(employeeBank);
                employeeId = em.merge(employeeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployeeBank(employeeBank.getEmployeeBankId()) != null) {
                throw new PreexistingEntityException("EmployeeBank " + employeeBank + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeeBank employeeBank) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmployeeBank persistentEmployeeBank = em.find(EmployeeBank.class, employeeBank.getEmployeeBankId());
            CompanyBranch branchIdOld = persistentEmployeeBank.getBranchId();
            CompanyBranch branchIdNew = employeeBank.getBranchId();
            Employee employeeIdOld = persistentEmployeeBank.getEmployeeId();
            Employee employeeIdNew = employeeBank.getEmployeeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                employeeBank.setBranchId(branchIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                employeeBank.setEmployeeId(employeeIdNew);
            }
            employeeBank = em.merge(employeeBank);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getEmployeeBankCollection().remove(employeeBank);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getEmployeeBankCollection().add(employeeBank);
                branchIdNew = em.merge(branchIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getEmployeeBankCollection().remove(employeeBank);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getEmployeeBankCollection().add(employeeBank);
                employeeIdNew = em.merge(employeeIdNew);
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
                Long id = employeeBank.getEmployeeBankId();
                if (findEmployeeBank(id) == null) {
                    throw new NonexistentEntityException("The employeeBank with id " + id + " no longer exists.");
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
            EmployeeBank employeeBank;
            try {
                employeeBank = em.getReference(EmployeeBank.class, id);
                employeeBank.getEmployeeBankId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeeBank with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = employeeBank.getBranchId();
            if (branchId != null) {
                branchId.getEmployeeBankCollection().remove(employeeBank);
                branchId = em.merge(branchId);
            }
            Employee employeeId = employeeBank.getEmployeeId();
            if (employeeId != null) {
                employeeId.getEmployeeBankCollection().remove(employeeBank);
                employeeId = em.merge(employeeId);
            }
            em.remove(employeeBank);
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

    public List<EmployeeBank> findEmployeeBankEntities() {
        return findEmployeeBankEntities(true, -1, -1);
    }

    public List<EmployeeBank> findEmployeeBankEntities(int maxResults, int firstResult) {
        return findEmployeeBankEntities(false, maxResults, firstResult);
    }

    private List<EmployeeBank> findEmployeeBankEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeeBank.class));
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

    public EmployeeBank findEmployeeBank(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeeBank.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeBankCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeeBank> rt = cq.from(EmployeeBank.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
