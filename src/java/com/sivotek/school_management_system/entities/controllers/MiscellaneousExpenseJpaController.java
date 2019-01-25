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
import com.sivotek.school_management_system.entities.MiscellaneousExpense;
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
public class MiscellaneousExpenseJpaController implements Serializable {

    public MiscellaneousExpenseJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MiscellaneousExpense miscellaneousExpense) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = miscellaneousExpense.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                miscellaneousExpense.setBranchId(branchId);
            }
            em.persist(miscellaneousExpense);
            if (branchId != null) {
                branchId.getMiscellaneousExpenseCollection().add(miscellaneousExpense);
                branchId = em.merge(branchId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findMiscellaneousExpense(miscellaneousExpense.getId()) != null) {
                throw new PreexistingEntityException("MiscellaneousExpense " + miscellaneousExpense + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MiscellaneousExpense miscellaneousExpense) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MiscellaneousExpense persistentMiscellaneousExpense = em.find(MiscellaneousExpense.class, miscellaneousExpense.getId());
            CompanyBranch branchIdOld = persistentMiscellaneousExpense.getBranchId();
            CompanyBranch branchIdNew = miscellaneousExpense.getBranchId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                miscellaneousExpense.setBranchId(branchIdNew);
            }
            miscellaneousExpense = em.merge(miscellaneousExpense);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getMiscellaneousExpenseCollection().remove(miscellaneousExpense);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getMiscellaneousExpenseCollection().add(miscellaneousExpense);
                branchIdNew = em.merge(branchIdNew);
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
                Long id = miscellaneousExpense.getId();
                if (findMiscellaneousExpense(id) == null) {
                    throw new NonexistentEntityException("The miscellaneousExpense with id " + id + " no longer exists.");
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
            MiscellaneousExpense miscellaneousExpense;
            try {
                miscellaneousExpense = em.getReference(MiscellaneousExpense.class, id);
                miscellaneousExpense.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The miscellaneousExpense with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = miscellaneousExpense.getBranchId();
            if (branchId != null) {
                branchId.getMiscellaneousExpenseCollection().remove(miscellaneousExpense);
                branchId = em.merge(branchId);
            }
            em.remove(miscellaneousExpense);
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

    public List<MiscellaneousExpense> findMiscellaneousExpenseEntities() {
        return findMiscellaneousExpenseEntities(true, -1, -1);
    }

    public List<MiscellaneousExpense> findMiscellaneousExpenseEntities(int maxResults, int firstResult) {
        return findMiscellaneousExpenseEntities(false, maxResults, firstResult);
    }

    private List<MiscellaneousExpense> findMiscellaneousExpenseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MiscellaneousExpense.class));
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

    public MiscellaneousExpense findMiscellaneousExpense(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MiscellaneousExpense.class, id);
        } finally {
            em.close();
        }
    }

    public int getMiscellaneousExpenseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MiscellaneousExpense> rt = cq.from(MiscellaneousExpense.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
