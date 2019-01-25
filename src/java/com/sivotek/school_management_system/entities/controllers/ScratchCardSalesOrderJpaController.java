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
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.PaymentMethod;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrder;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrderDetails;
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
public class ScratchCardSalesOrderJpaController implements Serializable {

    public ScratchCardSalesOrderJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScratchCardSalesOrder scratchCardSalesOrder) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (scratchCardSalesOrder.getScratchCardSalesOrderDetailsCollection() == null) {
            scratchCardSalesOrder.setScratchCardSalesOrderDetailsCollection(new ArrayList<ScratchCardSalesOrderDetails>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = scratchCardSalesOrder.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                scratchCardSalesOrder.setBranchId(branchId);
            }
            Invoice invoiceId = scratchCardSalesOrder.getInvoiceId();
            if (invoiceId != null) {
                invoiceId = em.getReference(invoiceId.getClass(), invoiceId.getId());
                scratchCardSalesOrder.setInvoiceId(invoiceId);
            }
            PaymentMethod paymentMethod = scratchCardSalesOrder.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod = em.getReference(paymentMethod.getClass(), paymentMethod.getId());
                scratchCardSalesOrder.setPaymentMethod(paymentMethod);
            }
            Collection<ScratchCardSalesOrderDetails> attachedScratchCardSalesOrderDetailsCollection = new ArrayList<ScratchCardSalesOrderDetails>();
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach : scratchCardSalesOrder.getScratchCardSalesOrderDetailsCollection()) {
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach = em.getReference(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach.getClass(), scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach.getId());
                attachedScratchCardSalesOrderDetailsCollection.add(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetailsToAttach);
            }
            scratchCardSalesOrder.setScratchCardSalesOrderDetailsCollection(attachedScratchCardSalesOrderDetailsCollection);
            em.persist(scratchCardSalesOrder);
            if (branchId != null) {
                branchId.getScratchCardSalesOrderCollection().add(scratchCardSalesOrder);
                branchId = em.merge(branchId);
            }
            if (invoiceId != null) {
                invoiceId.getScratchCardSalesOrderCollection().add(scratchCardSalesOrder);
                invoiceId = em.merge(invoiceId);
            }
            if (paymentMethod != null) {
                paymentMethod.getScratchCardSalesOrderCollection().add(scratchCardSalesOrder);
                paymentMethod = em.merge(paymentMethod);
            }
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails : scratchCardSalesOrder.getScratchCardSalesOrderDetailsCollection()) {
                ScratchCardSalesOrder oldSalesIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.getSalesId();
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.setSalesId(scratchCardSalesOrder);
                scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
                if (oldSalesIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails != null) {
                    oldSalesIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
                    oldSalesIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails = em.merge(oldSalesIdOfScratchCardSalesOrderDetailsCollectionScratchCardSalesOrderDetails);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findScratchCardSalesOrder(scratchCardSalesOrder.getId()) != null) {
                throw new PreexistingEntityException("ScratchCardSalesOrder " + scratchCardSalesOrder + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScratchCardSalesOrder scratchCardSalesOrder) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ScratchCardSalesOrder persistentScratchCardSalesOrder = em.find(ScratchCardSalesOrder.class, scratchCardSalesOrder.getId());
            CompanyBranch branchIdOld = persistentScratchCardSalesOrder.getBranchId();
            CompanyBranch branchIdNew = scratchCardSalesOrder.getBranchId();
            Invoice invoiceIdOld = persistentScratchCardSalesOrder.getInvoiceId();
            Invoice invoiceIdNew = scratchCardSalesOrder.getInvoiceId();
            PaymentMethod paymentMethodOld = persistentScratchCardSalesOrder.getPaymentMethod();
            PaymentMethod paymentMethodNew = scratchCardSalesOrder.getPaymentMethod();
            Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollectionOld = persistentScratchCardSalesOrder.getScratchCardSalesOrderDetailsCollection();
            Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollectionNew = scratchCardSalesOrder.getScratchCardSalesOrderDetailsCollection();
            List<String> illegalOrphanMessages = null;
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails : scratchCardSalesOrderDetailsCollectionOld) {
                if (!scratchCardSalesOrderDetailsCollectionNew.contains(scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ScratchCardSalesOrderDetails " + scratchCardSalesOrderDetailsCollectionOldScratchCardSalesOrderDetails + " since its salesId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                scratchCardSalesOrder.setBranchId(branchIdNew);
            }
            if (invoiceIdNew != null) {
                invoiceIdNew = em.getReference(invoiceIdNew.getClass(), invoiceIdNew.getId());
                scratchCardSalesOrder.setInvoiceId(invoiceIdNew);
            }
            if (paymentMethodNew != null) {
                paymentMethodNew = em.getReference(paymentMethodNew.getClass(), paymentMethodNew.getId());
                scratchCardSalesOrder.setPaymentMethod(paymentMethodNew);
            }
            Collection<ScratchCardSalesOrderDetails> attachedScratchCardSalesOrderDetailsCollectionNew = new ArrayList<ScratchCardSalesOrderDetails>();
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach : scratchCardSalesOrderDetailsCollectionNew) {
                scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach = em.getReference(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach.getClass(), scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach.getId());
                attachedScratchCardSalesOrderDetailsCollectionNew.add(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetailsToAttach);
            }
            scratchCardSalesOrderDetailsCollectionNew = attachedScratchCardSalesOrderDetailsCollectionNew;
            scratchCardSalesOrder.setScratchCardSalesOrderDetailsCollection(scratchCardSalesOrderDetailsCollectionNew);
            scratchCardSalesOrder = em.merge(scratchCardSalesOrder);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrder);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getScratchCardSalesOrderCollection().add(scratchCardSalesOrder);
                branchIdNew = em.merge(branchIdNew);
            }
            if (invoiceIdOld != null && !invoiceIdOld.equals(invoiceIdNew)) {
                invoiceIdOld.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrder);
                invoiceIdOld = em.merge(invoiceIdOld);
            }
            if (invoiceIdNew != null && !invoiceIdNew.equals(invoiceIdOld)) {
                invoiceIdNew.getScratchCardSalesOrderCollection().add(scratchCardSalesOrder);
                invoiceIdNew = em.merge(invoiceIdNew);
            }
            if (paymentMethodOld != null && !paymentMethodOld.equals(paymentMethodNew)) {
                paymentMethodOld.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrder);
                paymentMethodOld = em.merge(paymentMethodOld);
            }
            if (paymentMethodNew != null && !paymentMethodNew.equals(paymentMethodOld)) {
                paymentMethodNew.getScratchCardSalesOrderCollection().add(scratchCardSalesOrder);
                paymentMethodNew = em.merge(paymentMethodNew);
            }
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails : scratchCardSalesOrderDetailsCollectionNew) {
                if (!scratchCardSalesOrderDetailsCollectionOld.contains(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails)) {
                    ScratchCardSalesOrder oldSalesIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails = scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.getSalesId();
                    scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.setSalesId(scratchCardSalesOrder);
                    scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails);
                    if (oldSalesIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails != null && !oldSalesIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.equals(scratchCardSalesOrder)) {
                        oldSalesIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails);
                        oldSalesIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails = em.merge(oldSalesIdOfScratchCardSalesOrderDetailsCollectionNewScratchCardSalesOrderDetails);
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
                Long id = scratchCardSalesOrder.getId();
                if (findScratchCardSalesOrder(id) == null) {
                    throw new NonexistentEntityException("The scratchCardSalesOrder with id " + id + " no longer exists.");
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
            ScratchCardSalesOrder scratchCardSalesOrder;
            try {
                scratchCardSalesOrder = em.getReference(ScratchCardSalesOrder.class, id);
                scratchCardSalesOrder.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scratchCardSalesOrder with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ScratchCardSalesOrderDetails> scratchCardSalesOrderDetailsCollectionOrphanCheck = scratchCardSalesOrder.getScratchCardSalesOrderDetailsCollection();
            for (ScratchCardSalesOrderDetails scratchCardSalesOrderDetailsCollectionOrphanCheckScratchCardSalesOrderDetails : scratchCardSalesOrderDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ScratchCardSalesOrder (" + scratchCardSalesOrder + ") cannot be destroyed since the ScratchCardSalesOrderDetails " + scratchCardSalesOrderDetailsCollectionOrphanCheckScratchCardSalesOrderDetails + " in its scratchCardSalesOrderDetailsCollection field has a non-nullable salesId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = scratchCardSalesOrder.getBranchId();
            if (branchId != null) {
                branchId.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrder);
                branchId = em.merge(branchId);
            }
            Invoice invoiceId = scratchCardSalesOrder.getInvoiceId();
            if (invoiceId != null) {
                invoiceId.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrder);
                invoiceId = em.merge(invoiceId);
            }
            PaymentMethod paymentMethod = scratchCardSalesOrder.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrder);
                paymentMethod = em.merge(paymentMethod);
            }
            em.remove(scratchCardSalesOrder);
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

    public List<ScratchCardSalesOrder> findScratchCardSalesOrderEntities() {
        return findScratchCardSalesOrderEntities(true, -1, -1);
    }

    public List<ScratchCardSalesOrder> findScratchCardSalesOrderEntities(int maxResults, int firstResult) {
        return findScratchCardSalesOrderEntities(false, maxResults, firstResult);
    }

    private List<ScratchCardSalesOrder> findScratchCardSalesOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScratchCardSalesOrder.class));
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

    public ScratchCardSalesOrder findScratchCardSalesOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScratchCardSalesOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getScratchCardSalesOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScratchCardSalesOrder> rt = cq.from(ScratchCardSalesOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
