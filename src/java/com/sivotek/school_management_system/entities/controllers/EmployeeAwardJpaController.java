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
import com.sivotek.school_management_system.entities.EmployeeAward;
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
public class EmployeeAwardJpaController implements Serializable {

    public EmployeeAwardJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeeAward employeeAward) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = employeeAward.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                employeeAward.setBranchId(branchId);
            }
            Employee employeeId = employeeAward.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                employeeAward.setEmployeeId(employeeId);
            }
            em.persist(employeeAward);
            if (branchId != null) {
                branchId.getEmployeeAwardCollection().add(employeeAward);
                branchId = em.merge(branchId);
            }
            if (employeeId != null) {
                employeeId.getEmployeeAwardCollection().add(employeeAward);
                employeeId = em.merge(employeeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployeeAward(employeeAward.getEmployeeAwardId()) != null) {
                throw new PreexistingEntityException("EmployeeAward " + employeeAward + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeeAward employeeAward) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmployeeAward persistentEmployeeAward = em.find(EmployeeAward.class, employeeAward.getEmployeeAwardId());
            CompanyBranch branchIdOld = persistentEmployeeAward.getBranchId();
            CompanyBranch branchIdNew = employeeAward.getBranchId();
            Employee employeeIdOld = persistentEmployeeAward.getEmployeeId();
            Employee employeeIdNew = employeeAward.getEmployeeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                employeeAward.setBranchId(branchIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                employeeAward.setEmployeeId(employeeIdNew);
            }
            employeeAward = em.merge(employeeAward);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getEmployeeAwardCollection().remove(employeeAward);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getEmployeeAwardCollection().add(employeeAward);
                branchIdNew = em.merge(branchIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getEmployeeAwardCollection().remove(employeeAward);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getEmployeeAwardCollection().add(employeeAward);
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
                Long id = employeeAward.getEmployeeAwardId();
                if (findEmployeeAward(id) == null) {
                    throw new NonexistentEntityException("The employeeAward with id " + id + " no longer exists.");
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
            EmployeeAward employeeAward;
            try {
                employeeAward = em.getReference(EmployeeAward.class, id);
                employeeAward.getEmployeeAwardId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeeAward with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = employeeAward.getBranchId();
            if (branchId != null) {
                branchId.getEmployeeAwardCollection().remove(employeeAward);
                branchId = em.merge(branchId);
            }
            Employee employeeId = employeeAward.getEmployeeId();
            if (employeeId != null) {
                employeeId.getEmployeeAwardCollection().remove(employeeAward);
                employeeId = em.merge(employeeId);
            }
            em.remove(employeeAward);
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

    public List<EmployeeAward> findEmployeeAwardEntities() {
        return findEmployeeAwardEntities(true, -1, -1);
    }

    public List<EmployeeAward> findEmployeeAwardEntities(int maxResults, int firstResult) {
        return findEmployeeAwardEntities(false, maxResults, firstResult);
    }

    private List<EmployeeAward> findEmployeeAwardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeeAward.class));
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

    public EmployeeAward findEmployeeAward(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeeAward.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeAwardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeeAward> rt = cq.from(EmployeeAward.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
