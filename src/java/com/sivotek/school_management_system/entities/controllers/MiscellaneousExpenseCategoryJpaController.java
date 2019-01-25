/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.MiscellaneousExpenseCategory;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class MiscellaneousExpenseCategoryJpaController implements Serializable {

    public MiscellaneousExpenseCategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MiscellaneousExpenseCategory miscellaneousExpenseCategory) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(miscellaneousExpenseCategory);
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

    public void edit(MiscellaneousExpenseCategory miscellaneousExpenseCategory) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            miscellaneousExpenseCategory = em.merge(miscellaneousExpenseCategory);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = miscellaneousExpenseCategory.getId();
                if (findMiscellaneousExpenseCategory(id) == null) {
                    throw new NonexistentEntityException("The miscellaneousExpenseCategory with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            MiscellaneousExpenseCategory miscellaneousExpenseCategory;
            try {
                miscellaneousExpenseCategory = em.getReference(MiscellaneousExpenseCategory.class, id);
                miscellaneousExpenseCategory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The miscellaneousExpenseCategory with id " + id + " no longer exists.", enfe);
            }
            em.remove(miscellaneousExpenseCategory);
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

    public List<MiscellaneousExpenseCategory> findMiscellaneousExpenseCategoryEntities() {
        return findMiscellaneousExpenseCategoryEntities(true, -1, -1);
    }

    public List<MiscellaneousExpenseCategory> findMiscellaneousExpenseCategoryEntities(int maxResults, int firstResult) {
        return findMiscellaneousExpenseCategoryEntities(false, maxResults, firstResult);
    }

    private List<MiscellaneousExpenseCategory> findMiscellaneousExpenseCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MiscellaneousExpenseCategory.class));
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

    public MiscellaneousExpenseCategory findMiscellaneousExpenseCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MiscellaneousExpenseCategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getMiscellaneousExpenseCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MiscellaneousExpenseCategory> rt = cq.from(MiscellaneousExpenseCategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
