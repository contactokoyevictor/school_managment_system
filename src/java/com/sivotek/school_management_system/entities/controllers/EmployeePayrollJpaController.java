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
import com.sivotek.school_management_system.entities.EmployeePayroll;
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
public class EmployeePayrollJpaController implements Serializable {

    public EmployeePayrollJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeePayroll employeePayroll) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = employeePayroll.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                employeePayroll.setBranchId(branchId);
            }
            Employee employeeId = employeePayroll.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                employeePayroll.setEmployeeId(employeeId);
            }
            em.persist(employeePayroll);
            if (branchId != null) {
                branchId.getEmployeePayrollCollection().add(employeePayroll);
                branchId = em.merge(branchId);
            }
            if (employeeId != null) {
                employeeId.getEmployeePayrollCollection().add(employeePayroll);
                employeeId = em.merge(employeeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployeePayroll(employeePayroll.getPayrollId()) != null) {
                throw new PreexistingEntityException("EmployeePayroll " + employeePayroll + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeePayroll employeePayroll) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmployeePayroll persistentEmployeePayroll = em.find(EmployeePayroll.class, employeePayroll.getPayrollId());
            CompanyBranch branchIdOld = persistentEmployeePayroll.getBranchId();
            CompanyBranch branchIdNew = employeePayroll.getBranchId();
            Employee employeeIdOld = persistentEmployeePayroll.getEmployeeId();
            Employee employeeIdNew = employeePayroll.getEmployeeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                employeePayroll.setBranchId(branchIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                employeePayroll.setEmployeeId(employeeIdNew);
            }
            employeePayroll = em.merge(employeePayroll);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getEmployeePayrollCollection().remove(employeePayroll);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getEmployeePayrollCollection().add(employeePayroll);
                branchIdNew = em.merge(branchIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getEmployeePayrollCollection().remove(employeePayroll);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getEmployeePayrollCollection().add(employeePayroll);
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
                Long id = employeePayroll.getPayrollId();
                if (findEmployeePayroll(id) == null) {
                    throw new NonexistentEntityException("The employeePayroll with id " + id + " no longer exists.");
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
            EmployeePayroll employeePayroll;
            try {
                employeePayroll = em.getReference(EmployeePayroll.class, id);
                employeePayroll.getPayrollId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeePayroll with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = employeePayroll.getBranchId();
            if (branchId != null) {
                branchId.getEmployeePayrollCollection().remove(employeePayroll);
                branchId = em.merge(branchId);
            }
            Employee employeeId = employeePayroll.getEmployeeId();
            if (employeeId != null) {
                employeeId.getEmployeePayrollCollection().remove(employeePayroll);
                employeeId = em.merge(employeeId);
            }
            em.remove(employeePayroll);
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

    public List<EmployeePayroll> findEmployeePayrollEntities() {
        return findEmployeePayrollEntities(true, -1, -1);
    }

    public List<EmployeePayroll> findEmployeePayrollEntities(int maxResults, int firstResult) {
        return findEmployeePayrollEntities(false, maxResults, firstResult);
    }

    private List<EmployeePayroll> findEmployeePayrollEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeePayroll.class));
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

    public EmployeePayroll findEmployeePayroll(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeePayroll.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeePayrollCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeePayroll> rt = cq.from(EmployeePayroll.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
