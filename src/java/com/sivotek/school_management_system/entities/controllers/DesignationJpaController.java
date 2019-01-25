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
import com.sivotek.school_management_system.entities.Department;
import com.sivotek.school_management_system.entities.Designation;
import com.sivotek.school_management_system.entities.Employee;
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
public class DesignationJpaController implements Serializable {

    public DesignationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Designation designation) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (designation.getEmployeeCollection() == null) {
            designation.setEmployeeCollection(new ArrayList<Employee>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = designation.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                designation.setBranchId(branchId);
            }
            Department departmentId = designation.getDepartmentId();
            if (departmentId != null) {
                departmentId = em.getReference(departmentId.getClass(), departmentId.getDepartmentId());
                designation.setDepartmentId(departmentId);
            }
            Collection<Employee> attachedEmployeeCollection = new ArrayList<Employee>();
            for (Employee employeeCollectionEmployeeToAttach : designation.getEmployeeCollection()) {
                employeeCollectionEmployeeToAttach = em.getReference(employeeCollectionEmployeeToAttach.getClass(), employeeCollectionEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollection.add(employeeCollectionEmployeeToAttach);
            }
            designation.setEmployeeCollection(attachedEmployeeCollection);
            em.persist(designation);
            if (branchId != null) {
                branchId.getDesignationCollection().add(designation);
                branchId = em.merge(branchId);
            }
            if (departmentId != null) {
                departmentId.getDesignationCollection().add(designation);
                departmentId = em.merge(departmentId);
            }
            for (Employee employeeCollectionEmployee : designation.getEmployeeCollection()) {
                Designation oldDesignationIdOfEmployeeCollectionEmployee = employeeCollectionEmployee.getDesignationId();
                employeeCollectionEmployee.setDesignationId(designation);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
                if (oldDesignationIdOfEmployeeCollectionEmployee != null) {
                    oldDesignationIdOfEmployeeCollectionEmployee.getEmployeeCollection().remove(employeeCollectionEmployee);
                    oldDesignationIdOfEmployeeCollectionEmployee = em.merge(oldDesignationIdOfEmployeeCollectionEmployee);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDesignation(designation.getDesignationId()) != null) {
                throw new PreexistingEntityException("Designation " + designation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Designation designation) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Designation persistentDesignation = em.find(Designation.class, designation.getDesignationId());
            CompanyBranch branchIdOld = persistentDesignation.getBranchId();
            CompanyBranch branchIdNew = designation.getBranchId();
            Department departmentIdOld = persistentDesignation.getDepartmentId();
            Department departmentIdNew = designation.getDepartmentId();
            Collection<Employee> employeeCollectionOld = persistentDesignation.getEmployeeCollection();
            Collection<Employee> employeeCollectionNew = designation.getEmployeeCollection();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                designation.setBranchId(branchIdNew);
            }
            if (departmentIdNew != null) {
                departmentIdNew = em.getReference(departmentIdNew.getClass(), departmentIdNew.getDepartmentId());
                designation.setDepartmentId(departmentIdNew);
            }
            Collection<Employee> attachedEmployeeCollectionNew = new ArrayList<Employee>();
            for (Employee employeeCollectionNewEmployeeToAttach : employeeCollectionNew) {
                employeeCollectionNewEmployeeToAttach = em.getReference(employeeCollectionNewEmployeeToAttach.getClass(), employeeCollectionNewEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollectionNew.add(employeeCollectionNewEmployeeToAttach);
            }
            employeeCollectionNew = attachedEmployeeCollectionNew;
            designation.setEmployeeCollection(employeeCollectionNew);
            designation = em.merge(designation);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getDesignationCollection().remove(designation);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getDesignationCollection().add(designation);
                branchIdNew = em.merge(branchIdNew);
            }
            if (departmentIdOld != null && !departmentIdOld.equals(departmentIdNew)) {
                departmentIdOld.getDesignationCollection().remove(designation);
                departmentIdOld = em.merge(departmentIdOld);
            }
            if (departmentIdNew != null && !departmentIdNew.equals(departmentIdOld)) {
                departmentIdNew.getDesignationCollection().add(designation);
                departmentIdNew = em.merge(departmentIdNew);
            }
            for (Employee employeeCollectionOldEmployee : employeeCollectionOld) {
                if (!employeeCollectionNew.contains(employeeCollectionOldEmployee)) {
                    employeeCollectionOldEmployee.setDesignationId(null);
                    employeeCollectionOldEmployee = em.merge(employeeCollectionOldEmployee);
                }
            }
            for (Employee employeeCollectionNewEmployee : employeeCollectionNew) {
                if (!employeeCollectionOld.contains(employeeCollectionNewEmployee)) {
                    Designation oldDesignationIdOfEmployeeCollectionNewEmployee = employeeCollectionNewEmployee.getDesignationId();
                    employeeCollectionNewEmployee.setDesignationId(designation);
                    employeeCollectionNewEmployee = em.merge(employeeCollectionNewEmployee);
                    if (oldDesignationIdOfEmployeeCollectionNewEmployee != null && !oldDesignationIdOfEmployeeCollectionNewEmployee.equals(designation)) {
                        oldDesignationIdOfEmployeeCollectionNewEmployee.getEmployeeCollection().remove(employeeCollectionNewEmployee);
                        oldDesignationIdOfEmployeeCollectionNewEmployee = em.merge(oldDesignationIdOfEmployeeCollectionNewEmployee);
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
                Long id = designation.getDesignationId();
                if (findDesignation(id) == null) {
                    throw new NonexistentEntityException("The designation with id " + id + " no longer exists.");
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
            Designation designation;
            try {
                designation = em.getReference(Designation.class, id);
                designation.getDesignationId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The designation with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = designation.getBranchId();
            if (branchId != null) {
                branchId.getDesignationCollection().remove(designation);
                branchId = em.merge(branchId);
            }
            Department departmentId = designation.getDepartmentId();
            if (departmentId != null) {
                departmentId.getDesignationCollection().remove(designation);
                departmentId = em.merge(departmentId);
            }
            Collection<Employee> employeeCollection = designation.getEmployeeCollection();
            for (Employee employeeCollectionEmployee : employeeCollection) {
                employeeCollectionEmployee.setDesignationId(null);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
            }
            em.remove(designation);
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

    public List<Designation> findDesignationEntities() {
        return findDesignationEntities(true, -1, -1);
    }

    public List<Designation> findDesignationEntities(int maxResults, int firstResult) {
        return findDesignationEntities(false, maxResults, firstResult);
    }

    private List<Designation> findDesignationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Designation.class));
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

    public Designation findDesignation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Designation.class, id);
        } finally {
            em.close();
        }
    }

    public int getDesignationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Designation> rt = cq.from(Designation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
