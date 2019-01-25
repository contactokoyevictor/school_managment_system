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
import com.sivotek.school_management_system.entities.EmployeeAttendance;
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
public class EmployeeAttendanceJpaController implements Serializable {

    public EmployeeAttendanceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EmployeeAttendance employeeAttendance) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = employeeAttendance.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                employeeAttendance.setBranchId(branchId);
            }
            Employee employeeId = employeeAttendance.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                employeeAttendance.setEmployeeId(employeeId);
            }
            em.persist(employeeAttendance);
            if (branchId != null) {
                branchId.getEmployeeAttendanceCollection().add(employeeAttendance);
                branchId = em.merge(branchId);
            }
            if (employeeId != null) {
                employeeId.getEmployeeAttendanceCollection().add(employeeAttendance);
                employeeId = em.merge(employeeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployeeAttendance(employeeAttendance.getAttendanceId()) != null) {
                throw new PreexistingEntityException("EmployeeAttendance " + employeeAttendance + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EmployeeAttendance employeeAttendance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            EmployeeAttendance persistentEmployeeAttendance = em.find(EmployeeAttendance.class, employeeAttendance.getAttendanceId());
            CompanyBranch branchIdOld = persistentEmployeeAttendance.getBranchId();
            CompanyBranch branchIdNew = employeeAttendance.getBranchId();
            Employee employeeIdOld = persistentEmployeeAttendance.getEmployeeId();
            Employee employeeIdNew = employeeAttendance.getEmployeeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                employeeAttendance.setBranchId(branchIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                employeeAttendance.setEmployeeId(employeeIdNew);
            }
            employeeAttendance = em.merge(employeeAttendance);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getEmployeeAttendanceCollection().remove(employeeAttendance);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getEmployeeAttendanceCollection().add(employeeAttendance);
                branchIdNew = em.merge(branchIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getEmployeeAttendanceCollection().remove(employeeAttendance);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getEmployeeAttendanceCollection().add(employeeAttendance);
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
                Long id = employeeAttendance.getAttendanceId();
                if (findEmployeeAttendance(id) == null) {
                    throw new NonexistentEntityException("The employeeAttendance with id " + id + " no longer exists.");
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
            EmployeeAttendance employeeAttendance;
            try {
                employeeAttendance = em.getReference(EmployeeAttendance.class, id);
                employeeAttendance.getAttendanceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employeeAttendance with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = employeeAttendance.getBranchId();
            if (branchId != null) {
                branchId.getEmployeeAttendanceCollection().remove(employeeAttendance);
                branchId = em.merge(branchId);
            }
            Employee employeeId = employeeAttendance.getEmployeeId();
            if (employeeId != null) {
                employeeId.getEmployeeAttendanceCollection().remove(employeeAttendance);
                employeeId = em.merge(employeeId);
            }
            em.remove(employeeAttendance);
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

    public List<EmployeeAttendance> findEmployeeAttendanceEntities() {
        return findEmployeeAttendanceEntities(true, -1, -1);
    }

    public List<EmployeeAttendance> findEmployeeAttendanceEntities(int maxResults, int firstResult) {
        return findEmployeeAttendanceEntities(false, maxResults, firstResult);
    }

    private List<EmployeeAttendance> findEmployeeAttendanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EmployeeAttendance.class));
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

    public EmployeeAttendance findEmployeeAttendance(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmployeeAttendance.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeAttendanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EmployeeAttendance> rt = cq.from(EmployeeAttendance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
