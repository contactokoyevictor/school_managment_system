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
import com.sivotek.school_management_system.entities.PurchaseOrder;
import com.sivotek.school_management_system.entities.PurchaseOrderDetails;
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
public class PurchaseOrderJpaController implements Serializable {

    public PurchaseOrderJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PurchaseOrder purchaseOrder) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (purchaseOrder.getPurchaseOrderDetailsCollection() == null) {
            purchaseOrder.setPurchaseOrderDetailsCollection(new ArrayList<PurchaseOrderDetails>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = purchaseOrder.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                purchaseOrder.setBranchId(branchId);
            }
            Invoice invoiceId = purchaseOrder.getInvoiceId();
            if (invoiceId != null) {
                invoiceId = em.getReference(invoiceId.getClass(), invoiceId.getId());
                purchaseOrder.setInvoiceId(invoiceId);
            }
            PaymentMethod paymentMethod = purchaseOrder.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod = em.getReference(paymentMethod.getClass(), paymentMethod.getId());
                purchaseOrder.setPaymentMethod(paymentMethod);
            }
            Collection<PurchaseOrderDetails> attachedPurchaseOrderDetailsCollection = new ArrayList<PurchaseOrderDetails>();
            for (PurchaseOrderDetails purchaseOrderDetailsCollectionPurchaseOrderDetailsToAttach : purchaseOrder.getPurchaseOrderDetailsCollection()) {
                purchaseOrderDetailsCollectionPurchaseOrderDetailsToAttach = em.getReference(purchaseOrderDetailsCollectionPurchaseOrderDetailsToAttach.getClass(), purchaseOrderDetailsCollectionPurchaseOrderDetailsToAttach.getId());
                attachedPurchaseOrderDetailsCollection.add(purchaseOrderDetailsCollectionPurchaseOrderDetailsToAttach);
            }
            purchaseOrder.setPurchaseOrderDetailsCollection(attachedPurchaseOrderDetailsCollection);
            em.persist(purchaseOrder);
            if (branchId != null) {
                branchId.getPurchaseOrderCollection().add(purchaseOrder);
                branchId = em.merge(branchId);
            }
            if (invoiceId != null) {
                invoiceId.getPurchaseOrderCollection().add(purchaseOrder);
                invoiceId = em.merge(invoiceId);
            }
            if (paymentMethod != null) {
                paymentMethod.getPurchaseOrderCollection().add(purchaseOrder);
                paymentMethod = em.merge(paymentMethod);
            }
            for (PurchaseOrderDetails purchaseOrderDetailsCollectionPurchaseOrderDetails : purchaseOrder.getPurchaseOrderDetailsCollection()) {
                PurchaseOrder oldOrderIdOfPurchaseOrderDetailsCollectionPurchaseOrderDetails = purchaseOrderDetailsCollectionPurchaseOrderDetails.getOrderId();
                purchaseOrderDetailsCollectionPurchaseOrderDetails.setOrderId(purchaseOrder);
                purchaseOrderDetailsCollectionPurchaseOrderDetails = em.merge(purchaseOrderDetailsCollectionPurchaseOrderDetails);
                if (oldOrderIdOfPurchaseOrderDetailsCollectionPurchaseOrderDetails != null) {
                    oldOrderIdOfPurchaseOrderDetailsCollectionPurchaseOrderDetails.getPurchaseOrderDetailsCollection().remove(purchaseOrderDetailsCollectionPurchaseOrderDetails);
                    oldOrderIdOfPurchaseOrderDetailsCollectionPurchaseOrderDetails = em.merge(oldOrderIdOfPurchaseOrderDetailsCollectionPurchaseOrderDetails);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPurchaseOrder(purchaseOrder.getId()) != null) {
                throw new PreexistingEntityException("PurchaseOrder " + purchaseOrder + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PurchaseOrder purchaseOrder) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PurchaseOrder persistentPurchaseOrder = em.find(PurchaseOrder.class, purchaseOrder.getId());
            CompanyBranch branchIdOld = persistentPurchaseOrder.getBranchId();
            CompanyBranch branchIdNew = purchaseOrder.getBranchId();
            Invoice invoiceIdOld = persistentPurchaseOrder.getInvoiceId();
            Invoice invoiceIdNew = purchaseOrder.getInvoiceId();
            PaymentMethod paymentMethodOld = persistentPurchaseOrder.getPaymentMethod();
            PaymentMethod paymentMethodNew = purchaseOrder.getPaymentMethod();
            Collection<PurchaseOrderDetails> purchaseOrderDetailsCollectionOld = persistentPurchaseOrder.getPurchaseOrderDetailsCollection();
            Collection<PurchaseOrderDetails> purchaseOrderDetailsCollectionNew = purchaseOrder.getPurchaseOrderDetailsCollection();
            List<String> illegalOrphanMessages = null;
            for (PurchaseOrderDetails purchaseOrderDetailsCollectionOldPurchaseOrderDetails : purchaseOrderDetailsCollectionOld) {
                if (!purchaseOrderDetailsCollectionNew.contains(purchaseOrderDetailsCollectionOldPurchaseOrderDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PurchaseOrderDetails " + purchaseOrderDetailsCollectionOldPurchaseOrderDetails + " since its orderId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                purchaseOrder.setBranchId(branchIdNew);
            }
            if (invoiceIdNew != null) {
                invoiceIdNew = em.getReference(invoiceIdNew.getClass(), invoiceIdNew.getId());
                purchaseOrder.setInvoiceId(invoiceIdNew);
            }
            if (paymentMethodNew != null) {
                paymentMethodNew = em.getReference(paymentMethodNew.getClass(), paymentMethodNew.getId());
                purchaseOrder.setPaymentMethod(paymentMethodNew);
            }
            Collection<PurchaseOrderDetails> attachedPurchaseOrderDetailsCollectionNew = new ArrayList<PurchaseOrderDetails>();
            for (PurchaseOrderDetails purchaseOrderDetailsCollectionNewPurchaseOrderDetailsToAttach : purchaseOrderDetailsCollectionNew) {
                purchaseOrderDetailsCollectionNewPurchaseOrderDetailsToAttach = em.getReference(purchaseOrderDetailsCollectionNewPurchaseOrderDetailsToAttach.getClass(), purchaseOrderDetailsCollectionNewPurchaseOrderDetailsToAttach.getId());
                attachedPurchaseOrderDetailsCollectionNew.add(purchaseOrderDetailsCollectionNewPurchaseOrderDetailsToAttach);
            }
            purchaseOrderDetailsCollectionNew = attachedPurchaseOrderDetailsCollectionNew;
            purchaseOrder.setPurchaseOrderDetailsCollection(purchaseOrderDetailsCollectionNew);
            purchaseOrder = em.merge(purchaseOrder);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getPurchaseOrderCollection().remove(purchaseOrder);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getPurchaseOrderCollection().add(purchaseOrder);
                branchIdNew = em.merge(branchIdNew);
            }
            if (invoiceIdOld != null && !invoiceIdOld.equals(invoiceIdNew)) {
                invoiceIdOld.getPurchaseOrderCollection().remove(purchaseOrder);
                invoiceIdOld = em.merge(invoiceIdOld);
            }
            if (invoiceIdNew != null && !invoiceIdNew.equals(invoiceIdOld)) {
                invoiceIdNew.getPurchaseOrderCollection().add(purchaseOrder);
                invoiceIdNew = em.merge(invoiceIdNew);
            }
            if (paymentMethodOld != null && !paymentMethodOld.equals(paymentMethodNew)) {
                paymentMethodOld.getPurchaseOrderCollection().remove(purchaseOrder);
                paymentMethodOld = em.merge(paymentMethodOld);
            }
            if (paymentMethodNew != null && !paymentMethodNew.equals(paymentMethodOld)) {
                paymentMethodNew.getPurchaseOrderCollection().add(purchaseOrder);
                paymentMethodNew = em.merge(paymentMethodNew);
            }
            for (PurchaseOrderDetails purchaseOrderDetailsCollectionNewPurchaseOrderDetails : purchaseOrderDetailsCollectionNew) {
                if (!purchaseOrderDetailsCollectionOld.contains(purchaseOrderDetailsCollectionNewPurchaseOrderDetails)) {
                    PurchaseOrder oldOrderIdOfPurchaseOrderDetailsCollectionNewPurchaseOrderDetails = purchaseOrderDetailsCollectionNewPurchaseOrderDetails.getOrderId();
                    purchaseOrderDetailsCollectionNewPurchaseOrderDetails.setOrderId(purchaseOrder);
                    purchaseOrderDetailsCollectionNewPurchaseOrderDetails = em.merge(purchaseOrderDetailsCollectionNewPurchaseOrderDetails);
                    if (oldOrderIdOfPurchaseOrderDetailsCollectionNewPurchaseOrderDetails != null && !oldOrderIdOfPurchaseOrderDetailsCollectionNewPurchaseOrderDetails.equals(purchaseOrder)) {
                        oldOrderIdOfPurchaseOrderDetailsCollectionNewPurchaseOrderDetails.getPurchaseOrderDetailsCollection().remove(purchaseOrderDetailsCollectionNewPurchaseOrderDetails);
                        oldOrderIdOfPurchaseOrderDetailsCollectionNewPurchaseOrderDetails = em.merge(oldOrderIdOfPurchaseOrderDetailsCollectionNewPurchaseOrderDetails);
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
                Long id = purchaseOrder.getId();
                if (findPurchaseOrder(id) == null) {
                    throw new NonexistentEntityException("The purchaseOrder with id " + id + " no longer exists.");
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
            PurchaseOrder purchaseOrder;
            try {
                purchaseOrder = em.getReference(PurchaseOrder.class, id);
                purchaseOrder.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The purchaseOrder with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PurchaseOrderDetails> purchaseOrderDetailsCollectionOrphanCheck = purchaseOrder.getPurchaseOrderDetailsCollection();
            for (PurchaseOrderDetails purchaseOrderDetailsCollectionOrphanCheckPurchaseOrderDetails : purchaseOrderDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PurchaseOrder (" + purchaseOrder + ") cannot be destroyed since the PurchaseOrderDetails " + purchaseOrderDetailsCollectionOrphanCheckPurchaseOrderDetails + " in its purchaseOrderDetailsCollection field has a non-nullable orderId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = purchaseOrder.getBranchId();
            if (branchId != null) {
                branchId.getPurchaseOrderCollection().remove(purchaseOrder);
                branchId = em.merge(branchId);
            }
            Invoice invoiceId = purchaseOrder.getInvoiceId();
            if (invoiceId != null) {
                invoiceId.getPurchaseOrderCollection().remove(purchaseOrder);
                invoiceId = em.merge(invoiceId);
            }
            PaymentMethod paymentMethod = purchaseOrder.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod.getPurchaseOrderCollection().remove(purchaseOrder);
                paymentMethod = em.merge(paymentMethod);
            }
            em.remove(purchaseOrder);
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

    public List<PurchaseOrder> findPurchaseOrderEntities() {
        return findPurchaseOrderEntities(true, -1, -1);
    }

    public List<PurchaseOrder> findPurchaseOrderEntities(int maxResults, int firstResult) {
        return findPurchaseOrderEntities(false, maxResults, firstResult);
    }

    private List<PurchaseOrder> findPurchaseOrderEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PurchaseOrder.class));
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

    public PurchaseOrder findPurchaseOrder(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PurchaseOrder.class, id);
        } finally {
            em.close();
        }
    }

    public int getPurchaseOrderCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PurchaseOrder> rt = cq.from(PurchaseOrder.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
