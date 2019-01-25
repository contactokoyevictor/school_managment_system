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
import com.sivotek.school_management_system.entities.PurchaseOrder;
import com.sivotek.school_management_system.entities.PurchaseOrderDetails;
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
public class PurchaseOrderDetailsJpaController implements Serializable {

    public PurchaseOrderDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PurchaseOrderDetails purchaseOrderDetails) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PurchaseOrder orderId = purchaseOrderDetails.getOrderId();
            if (orderId != null) {
                orderId = em.getReference(orderId.getClass(), orderId.getId());
                purchaseOrderDetails.setOrderId(orderId);
            }
            em.persist(purchaseOrderDetails);
            if (orderId != null) {
                orderId.getPurchaseOrderDetailsCollection().add(purchaseOrderDetails);
                orderId = em.merge(orderId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPurchaseOrderDetails(purchaseOrderDetails.getId()) != null) {
                throw new PreexistingEntityException("PurchaseOrderDetails " + purchaseOrderDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PurchaseOrderDetails purchaseOrderDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PurchaseOrderDetails persistentPurchaseOrderDetails = em.find(PurchaseOrderDetails.class, purchaseOrderDetails.getId());
            PurchaseOrder orderIdOld = persistentPurchaseOrderDetails.getOrderId();
            PurchaseOrder orderIdNew = purchaseOrderDetails.getOrderId();
            if (orderIdNew != null) {
                orderIdNew = em.getReference(orderIdNew.getClass(), orderIdNew.getId());
                purchaseOrderDetails.setOrderId(orderIdNew);
            }
            purchaseOrderDetails = em.merge(purchaseOrderDetails);
            if (orderIdOld != null && !orderIdOld.equals(orderIdNew)) {
                orderIdOld.getPurchaseOrderDetailsCollection().remove(purchaseOrderDetails);
                orderIdOld = em.merge(orderIdOld);
            }
            if (orderIdNew != null && !orderIdNew.equals(orderIdOld)) {
                orderIdNew.getPurchaseOrderDetailsCollection().add(purchaseOrderDetails);
                orderIdNew = em.merge(orderIdNew);
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
                Long id = purchaseOrderDetails.getId();
                if (findPurchaseOrderDetails(id) == null) {
                    throw new NonexistentEntityException("The purchaseOrderDetails with id " + id + " no longer exists.");
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
            PurchaseOrderDetails purchaseOrderDetails;
            try {
                purchaseOrderDetails = em.getReference(PurchaseOrderDetails.class, id);
                purchaseOrderDetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The purchaseOrderDetails with id " + id + " no longer exists.", enfe);
            }
            PurchaseOrder orderId = purchaseOrderDetails.getOrderId();
            if (orderId != null) {
                orderId.getPurchaseOrderDetailsCollection().remove(purchaseOrderDetails);
                orderId = em.merge(orderId);
            }
            em.remove(purchaseOrderDetails);
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

    public List<PurchaseOrderDetails> findPurchaseOrderDetailsEntities() {
        return findPurchaseOrderDetailsEntities(true, -1, -1);
    }

    public List<PurchaseOrderDetails> findPurchaseOrderDetailsEntities(int maxResults, int firstResult) {
        return findPurchaseOrderDetailsEntities(false, maxResults, firstResult);
    }

    private List<PurchaseOrderDetails> findPurchaseOrderDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PurchaseOrderDetails.class));
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

    public PurchaseOrderDetails findPurchaseOrderDetails(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PurchaseOrderDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getPurchaseOrderDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PurchaseOrderDetails> rt = cq.from(PurchaseOrderDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
