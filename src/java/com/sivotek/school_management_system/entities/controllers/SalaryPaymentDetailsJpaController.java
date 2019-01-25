/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.SalaryPaymentDetails;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
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
public class SalaryPaymentDetailsJpaController implements Serializable {

    public SalaryPaymentDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SalaryPaymentDetails salaryPaymentDetails) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(salaryPaymentDetails);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSalaryPaymentDetails(salaryPaymentDetails.getSalaryPaymentId()) != null) {
                throw new PreexistingEntityException("SalaryPaymentDetails " + salaryPaymentDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SalaryPaymentDetails salaryPaymentDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            salaryPaymentDetails = em.merge(salaryPaymentDetails);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = salaryPaymentDetails.getSalaryPaymentId();
                if (findSalaryPaymentDetails(id) == null) {
                    throw new NonexistentEntityException("The salaryPaymentDetails with id " + id + " no longer exists.");
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
            SalaryPaymentDetails salaryPaymentDetails;
            try {
                salaryPaymentDetails = em.getReference(SalaryPaymentDetails.class, id);
                salaryPaymentDetails.getSalaryPaymentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The salaryPaymentDetails with id " + id + " no longer exists.", enfe);
            }
            em.remove(salaryPaymentDetails);
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

    public List<SalaryPaymentDetails> findSalaryPaymentDetailsEntities() {
        return findSalaryPaymentDetailsEntities(true, -1, -1);
    }

    public List<SalaryPaymentDetails> findSalaryPaymentDetailsEntities(int maxResults, int firstResult) {
        return findSalaryPaymentDetailsEntities(false, maxResults, firstResult);
    }

    private List<SalaryPaymentDetails> findSalaryPaymentDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SalaryPaymentDetails.class));
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

    public SalaryPaymentDetails findSalaryPaymentDetails(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SalaryPaymentDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getSalaryPaymentDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SalaryPaymentDetails> rt = cq.from(SalaryPaymentDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
