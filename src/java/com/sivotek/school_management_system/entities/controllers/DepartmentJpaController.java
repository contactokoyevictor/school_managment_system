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
public class DepartmentJpaController implements Serializable {

    public DepartmentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Department department) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (department.getDesignationCollection() == null) {
            department.setDesignationCollection(new ArrayList<Designation>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = department.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                department.setBranchId(branchId);
            }
            Collection<Designation> attachedDesignationCollection = new ArrayList<Designation>();
            for (Designation designationCollectionDesignationToAttach : department.getDesignationCollection()) {
                designationCollectionDesignationToAttach = em.getReference(designationCollectionDesignationToAttach.getClass(), designationCollectionDesignationToAttach.getDesignationId());
                attachedDesignationCollection.add(designationCollectionDesignationToAttach);
            }
            department.setDesignationCollection(attachedDesignationCollection);
            em.persist(department);
            if (branchId != null) {
                branchId.getDepartmentCollection().add(department);
                branchId = em.merge(branchId);
            }
            for (Designation designationCollectionDesignation : department.getDesignationCollection()) {
                Department oldDepartmentIdOfDesignationCollectionDesignation = designationCollectionDesignation.getDepartmentId();
                designationCollectionDesignation.setDepartmentId(department);
                designationCollectionDesignation = em.merge(designationCollectionDesignation);
                if (oldDepartmentIdOfDesignationCollectionDesignation != null) {
                    oldDepartmentIdOfDesignationCollectionDesignation.getDesignationCollection().remove(designationCollectionDesignation);
                    oldDepartmentIdOfDesignationCollectionDesignation = em.merge(oldDepartmentIdOfDesignationCollectionDesignation);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findDepartment(department.getDepartmentId()) != null) {
                throw new PreexistingEntityException("Department " + department + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Department department) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Department persistentDepartment = em.find(Department.class, department.getDepartmentId());
            CompanyBranch branchIdOld = persistentDepartment.getBranchId();
            CompanyBranch branchIdNew = department.getBranchId();
            Collection<Designation> designationCollectionOld = persistentDepartment.getDesignationCollection();
            Collection<Designation> designationCollectionNew = department.getDesignationCollection();
            List<String> illegalOrphanMessages = null;
            for (Designation designationCollectionOldDesignation : designationCollectionOld) {
                if (!designationCollectionNew.contains(designationCollectionOldDesignation)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Designation " + designationCollectionOldDesignation + " since its departmentId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                department.setBranchId(branchIdNew);
            }
            Collection<Designation> attachedDesignationCollectionNew = new ArrayList<Designation>();
            for (Designation designationCollectionNewDesignationToAttach : designationCollectionNew) {
                designationCollectionNewDesignationToAttach = em.getReference(designationCollectionNewDesignationToAttach.getClass(), designationCollectionNewDesignationToAttach.getDesignationId());
                attachedDesignationCollectionNew.add(designationCollectionNewDesignationToAttach);
            }
            designationCollectionNew = attachedDesignationCollectionNew;
            department.setDesignationCollection(designationCollectionNew);
            department = em.merge(department);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getDepartmentCollection().remove(department);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getDepartmentCollection().add(department);
                branchIdNew = em.merge(branchIdNew);
            }
            for (Designation designationCollectionNewDesignation : designationCollectionNew) {
                if (!designationCollectionOld.contains(designationCollectionNewDesignation)) {
                    Department oldDepartmentIdOfDesignationCollectionNewDesignation = designationCollectionNewDesignation.getDepartmentId();
                    designationCollectionNewDesignation.setDepartmentId(department);
                    designationCollectionNewDesignation = em.merge(designationCollectionNewDesignation);
                    if (oldDepartmentIdOfDesignationCollectionNewDesignation != null && !oldDepartmentIdOfDesignationCollectionNewDesignation.equals(department)) {
                        oldDepartmentIdOfDesignationCollectionNewDesignation.getDesignationCollection().remove(designationCollectionNewDesignation);
                        oldDepartmentIdOfDesignationCollectionNewDesignation = em.merge(oldDepartmentIdOfDesignationCollectionNewDesignation);
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
                Long id = department.getDepartmentId();
                if (findDepartment(id) == null) {
                    throw new NonexistentEntityException("The department with id " + id + " no longer exists.");
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
            Department department;
            try {
                department = em.getReference(Department.class, id);
                department.getDepartmentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The department with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Designation> designationCollectionOrphanCheck = department.getDesignationCollection();
            for (Designation designationCollectionOrphanCheckDesignation : designationCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Department (" + department + ") cannot be destroyed since the Designation " + designationCollectionOrphanCheckDesignation + " in its designationCollection field has a non-nullable departmentId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = department.getBranchId();
            if (branchId != null) {
                branchId.getDepartmentCollection().remove(department);
                branchId = em.merge(branchId);
            }
            em.remove(department);
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

    public List<Department> findDepartmentEntities() {
        return findDepartmentEntities(true, -1, -1);
    }

    public List<Department> findDepartmentEntities(int maxResults, int firstResult) {
        return findDepartmentEntities(false, maxResults, firstResult);
    }

    private List<Department> findDepartmentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Department.class));
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

    public Department findDepartment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Department.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartmentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Department> rt = cq.from(Department.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
