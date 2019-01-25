/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.MiscellaneousExpenseDetails;
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
public class MiscellaneousExpenseDetailsJpaController implements Serializable {

    public MiscellaneousExpenseDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MiscellaneousExpenseDetails miscellaneousExpenseDetails) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(miscellaneousExpenseDetails);
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

    public void edit(MiscellaneousExpenseDetails miscellaneousExpenseDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            miscellaneousExpenseDetails = em.merge(miscellaneousExpenseDetails);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = miscellaneousExpenseDetails.getId();
                if (findMiscellaneousExpenseDetails(id) == null) {
                    throw new NonexistentEntityException("The miscellaneousExpenseDetails with id " + id + " no longer exists.");
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
            MiscellaneousExpenseDetails miscellaneousExpenseDetails;
            try {
                miscellaneousExpenseDetails = em.getReference(MiscellaneousExpenseDetails.class, id);
                miscellaneousExpenseDetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The miscellaneousExpenseDetails with id " + id + " no longer exists.", enfe);
            }
            em.remove(miscellaneousExpenseDetails);
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

    public List<MiscellaneousExpenseDetails> findMiscellaneousExpenseDetailsEntities() {
        return findMiscellaneousExpenseDetailsEntities(true, -1, -1);
    }

    public List<MiscellaneousExpenseDetails> findMiscellaneousExpenseDetailsEntities(int maxResults, int firstResult) {
        return findMiscellaneousExpenseDetailsEntities(false, maxResults, firstResult);
    }

    private List<MiscellaneousExpenseDetails> findMiscellaneousExpenseDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MiscellaneousExpenseDetails.class));
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

    public MiscellaneousExpenseDetails findMiscellaneousExpenseDetails(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MiscellaneousExpenseDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getMiscellaneousExpenseDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MiscellaneousExpenseDetails> rt = cq.from(MiscellaneousExpenseDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
