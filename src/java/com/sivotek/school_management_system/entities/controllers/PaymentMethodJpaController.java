/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.PaymentMethod;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.UtilityBillDetails;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrder;
import com.sivotek.school_management_system.entities.PurchaseOrder;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
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
public class PaymentMethodJpaController implements Serializable {

    public PaymentMethodJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaymentMethod paymentMethod) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (paymentMethod.getUtilityBillDetailsCollection() == null) {
            paymentMethod.setUtilityBillDetailsCollection(new ArrayList<UtilityBillDetails>());
        }
        if (paymentMethod.getScratchCardSalesOrderCollection() == null) {
            paymentMethod.setScratchCardSalesOrderCollection(new ArrayList<ScratchCardSalesOrder>());
        }
        if (paymentMethod.getPurchaseOrderCollection() == null) {
            paymentMethod.setPurchaseOrderCollection(new ArrayList<PurchaseOrder>());
        }
        if (paymentMethod.getSchoolFeeInvoiceDetailsCollection() == null) {
            paymentMethod.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollection = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetailsToAttach : paymentMethod.getUtilityBillDetailsCollection()) {
                utilityBillDetailsCollectionUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollection.add(utilityBillDetailsCollectionUtilityBillDetailsToAttach);
            }
            paymentMethod.setUtilityBillDetailsCollection(attachedUtilityBillDetailsCollection);
            Collection<ScratchCardSalesOrder> attachedScratchCardSalesOrderCollection = new ArrayList<ScratchCardSalesOrder>();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach : paymentMethod.getScratchCardSalesOrderCollection()) {
                scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach = em.getReference(scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach.getClass(), scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach.getId());
                attachedScratchCardSalesOrderCollection.add(scratchCardSalesOrderCollectionScratchCardSalesOrderToAttach);
            }
            paymentMethod.setScratchCardSalesOrderCollection(attachedScratchCardSalesOrderCollection);
            Collection<PurchaseOrder> attachedPurchaseOrderCollection = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrderToAttach : paymentMethod.getPurchaseOrderCollection()) {
                purchaseOrderCollectionPurchaseOrderToAttach = em.getReference(purchaseOrderCollectionPurchaseOrderToAttach.getClass(), purchaseOrderCollectionPurchaseOrderToAttach.getId());
                attachedPurchaseOrderCollection.add(purchaseOrderCollectionPurchaseOrderToAttach);
            }
            paymentMethod.setPurchaseOrderCollection(attachedPurchaseOrderCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : paymentMethod.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            paymentMethod.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            em.persist(paymentMethod);
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : paymentMethod.getUtilityBillDetailsCollection()) {
                PaymentMethod oldPaymentMethodOfUtilityBillDetailsCollectionUtilityBillDetails = utilityBillDetailsCollectionUtilityBillDetails.getPaymentMethod();
                utilityBillDetailsCollectionUtilityBillDetails.setPaymentMethod(paymentMethod);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
                if (oldPaymentMethodOfUtilityBillDetailsCollectionUtilityBillDetails != null) {
                    oldPaymentMethodOfUtilityBillDetailsCollectionUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionUtilityBillDetails);
                    oldPaymentMethodOfUtilityBillDetailsCollectionUtilityBillDetails = em.merge(oldPaymentMethodOfUtilityBillDetailsCollectionUtilityBillDetails);
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrder : paymentMethod.getScratchCardSalesOrderCollection()) {
                PaymentMethod oldPaymentMethodOfScratchCardSalesOrderCollectionScratchCardSalesOrder = scratchCardSalesOrderCollectionScratchCardSalesOrder.getPaymentMethod();
                scratchCardSalesOrderCollectionScratchCardSalesOrder.setPaymentMethod(paymentMethod);
                scratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionScratchCardSalesOrder);
                if (oldPaymentMethodOfScratchCardSalesOrderCollectionScratchCardSalesOrder != null) {
                    oldPaymentMethodOfScratchCardSalesOrderCollectionScratchCardSalesOrder.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrderCollectionScratchCardSalesOrder);
                    oldPaymentMethodOfScratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(oldPaymentMethodOfScratchCardSalesOrderCollectionScratchCardSalesOrder);
                }
            }
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrder : paymentMethod.getPurchaseOrderCollection()) {
                PaymentMethod oldPaymentMethodOfPurchaseOrderCollectionPurchaseOrder = purchaseOrderCollectionPurchaseOrder.getPaymentMethod();
                purchaseOrderCollectionPurchaseOrder.setPaymentMethod(paymentMethod);
                purchaseOrderCollectionPurchaseOrder = em.merge(purchaseOrderCollectionPurchaseOrder);
                if (oldPaymentMethodOfPurchaseOrderCollectionPurchaseOrder != null) {
                    oldPaymentMethodOfPurchaseOrderCollectionPurchaseOrder.getPurchaseOrderCollection().remove(purchaseOrderCollectionPurchaseOrder);
                    oldPaymentMethodOfPurchaseOrderCollectionPurchaseOrder = em.merge(oldPaymentMethodOfPurchaseOrderCollectionPurchaseOrder);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : paymentMethod.getSchoolFeeInvoiceDetailsCollection()) {
                PaymentMethod oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getPaymentMethod();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setPaymentMethod(paymentMethod);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPaymentMethod(paymentMethod.getId()) != null) {
                throw new PreexistingEntityException("PaymentMethod " + paymentMethod + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaymentMethod paymentMethod) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PaymentMethod persistentPaymentMethod = em.find(PaymentMethod.class, paymentMethod.getId());
            Collection<UtilityBillDetails> utilityBillDetailsCollectionOld = persistentPaymentMethod.getUtilityBillDetailsCollection();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionNew = paymentMethod.getUtilityBillDetailsCollection();
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionOld = persistentPaymentMethod.getScratchCardSalesOrderCollection();
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollectionNew = paymentMethod.getScratchCardSalesOrderCollection();
            Collection<PurchaseOrder> purchaseOrderCollectionOld = persistentPaymentMethod.getPurchaseOrderCollection();
            Collection<PurchaseOrder> purchaseOrderCollectionNew = paymentMethod.getPurchaseOrderCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentPaymentMethod.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = paymentMethod.getSchoolFeeInvoiceDetailsCollection();
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollectionNew = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetailsToAttach : utilityBillDetailsCollectionNew) {
                utilityBillDetailsCollectionNewUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollectionNew.add(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach);
            }
            utilityBillDetailsCollectionNew = attachedUtilityBillDetailsCollectionNew;
            paymentMethod.setUtilityBillDetailsCollection(utilityBillDetailsCollectionNew);
            Collection<ScratchCardSalesOrder> attachedScratchCardSalesOrderCollectionNew = new ArrayList<ScratchCardSalesOrder>();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach : scratchCardSalesOrderCollectionNew) {
                scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach = em.getReference(scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach.getClass(), scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach.getId());
                attachedScratchCardSalesOrderCollectionNew.add(scratchCardSalesOrderCollectionNewScratchCardSalesOrderToAttach);
            }
            scratchCardSalesOrderCollectionNew = attachedScratchCardSalesOrderCollectionNew;
            paymentMethod.setScratchCardSalesOrderCollection(scratchCardSalesOrderCollectionNew);
            Collection<PurchaseOrder> attachedPurchaseOrderCollectionNew = new ArrayList<PurchaseOrder>();
            for (PurchaseOrder purchaseOrderCollectionNewPurchaseOrderToAttach : purchaseOrderCollectionNew) {
                purchaseOrderCollectionNewPurchaseOrderToAttach = em.getReference(purchaseOrderCollectionNewPurchaseOrderToAttach.getClass(), purchaseOrderCollectionNewPurchaseOrderToAttach.getId());
                attachedPurchaseOrderCollectionNew.add(purchaseOrderCollectionNewPurchaseOrderToAttach);
            }
            purchaseOrderCollectionNew = attachedPurchaseOrderCollectionNew;
            paymentMethod.setPurchaseOrderCollection(purchaseOrderCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            paymentMethod.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            paymentMethod = em.merge(paymentMethod);
            for (UtilityBillDetails utilityBillDetailsCollectionOldUtilityBillDetails : utilityBillDetailsCollectionOld) {
                if (!utilityBillDetailsCollectionNew.contains(utilityBillDetailsCollectionOldUtilityBillDetails)) {
                    utilityBillDetailsCollectionOldUtilityBillDetails.setPaymentMethod(null);
                    utilityBillDetailsCollectionOldUtilityBillDetails = em.merge(utilityBillDetailsCollectionOldUtilityBillDetails);
                }
            }
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetails : utilityBillDetailsCollectionNew) {
                if (!utilityBillDetailsCollectionOld.contains(utilityBillDetailsCollectionNewUtilityBillDetails)) {
                    PaymentMethod oldPaymentMethodOfUtilityBillDetailsCollectionNewUtilityBillDetails = utilityBillDetailsCollectionNewUtilityBillDetails.getPaymentMethod();
                    utilityBillDetailsCollectionNewUtilityBillDetails.setPaymentMethod(paymentMethod);
                    utilityBillDetailsCollectionNewUtilityBillDetails = em.merge(utilityBillDetailsCollectionNewUtilityBillDetails);
                    if (oldPaymentMethodOfUtilityBillDetailsCollectionNewUtilityBillDetails != null && !oldPaymentMethodOfUtilityBillDetailsCollectionNewUtilityBillDetails.equals(paymentMethod)) {
                        oldPaymentMethodOfUtilityBillDetailsCollectionNewUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionNewUtilityBillDetails);
                        oldPaymentMethodOfUtilityBillDetailsCollectionNewUtilityBillDetails = em.merge(oldPaymentMethodOfUtilityBillDetailsCollectionNewUtilityBillDetails);
                    }
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionOldScratchCardSalesOrder : scratchCardSalesOrderCollectionOld) {
                if (!scratchCardSalesOrderCollectionNew.contains(scratchCardSalesOrderCollectionOldScratchCardSalesOrder)) {
                    scratchCardSalesOrderCollectionOldScratchCardSalesOrder.setPaymentMethod(null);
                    scratchCardSalesOrderCollectionOldScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionOldScratchCardSalesOrder);
                }
            }
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionNewScratchCardSalesOrder : scratchCardSalesOrderCollectionNew) {
                if (!scratchCardSalesOrderCollectionOld.contains(scratchCardSalesOrderCollectionNewScratchCardSalesOrder)) {
                    PaymentMethod oldPaymentMethodOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder = scratchCardSalesOrderCollectionNewScratchCardSalesOrder.getPaymentMethod();
                    scratchCardSalesOrderCollectionNewScratchCardSalesOrder.setPaymentMethod(paymentMethod);
                    scratchCardSalesOrderCollectionNewScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                    if (oldPaymentMethodOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder != null && !oldPaymentMethodOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder.equals(paymentMethod)) {
                        oldPaymentMethodOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder.getScratchCardSalesOrderCollection().remove(scratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                        oldPaymentMethodOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder = em.merge(oldPaymentMethodOfScratchCardSalesOrderCollectionNewScratchCardSalesOrder);
                    }
                }
            }
            for (PurchaseOrder purchaseOrderCollectionOldPurchaseOrder : purchaseOrderCollectionOld) {
                if (!purchaseOrderCollectionNew.contains(purchaseOrderCollectionOldPurchaseOrder)) {
                    purchaseOrderCollectionOldPurchaseOrder.setPaymentMethod(null);
                    purchaseOrderCollectionOldPurchaseOrder = em.merge(purchaseOrderCollectionOldPurchaseOrder);
                }
            }
            for (PurchaseOrder purchaseOrderCollectionNewPurchaseOrder : purchaseOrderCollectionNew) {
                if (!purchaseOrderCollectionOld.contains(purchaseOrderCollectionNewPurchaseOrder)) {
                    PaymentMethod oldPaymentMethodOfPurchaseOrderCollectionNewPurchaseOrder = purchaseOrderCollectionNewPurchaseOrder.getPaymentMethod();
                    purchaseOrderCollectionNewPurchaseOrder.setPaymentMethod(paymentMethod);
                    purchaseOrderCollectionNewPurchaseOrder = em.merge(purchaseOrderCollectionNewPurchaseOrder);
                    if (oldPaymentMethodOfPurchaseOrderCollectionNewPurchaseOrder != null && !oldPaymentMethodOfPurchaseOrderCollectionNewPurchaseOrder.equals(paymentMethod)) {
                        oldPaymentMethodOfPurchaseOrderCollectionNewPurchaseOrder.getPurchaseOrderCollection().remove(purchaseOrderCollectionNewPurchaseOrder);
                        oldPaymentMethodOfPurchaseOrderCollectionNewPurchaseOrder = em.merge(oldPaymentMethodOfPurchaseOrderCollectionNewPurchaseOrder);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails.setPaymentMethod(null);
                    schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    PaymentMethod oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getPaymentMethod();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setPaymentMethod(paymentMethod);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(paymentMethod)) {
                        oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldPaymentMethodOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
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
                Long id = paymentMethod.getId();
                if (findPaymentMethod(id) == null) {
                    throw new NonexistentEntityException("The paymentMethod with id " + id + " no longer exists.");
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
            PaymentMethod paymentMethod;
            try {
                paymentMethod = em.getReference(PaymentMethod.class, id);
                paymentMethod.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paymentMethod with id " + id + " no longer exists.", enfe);
            }
            Collection<UtilityBillDetails> utilityBillDetailsCollection = paymentMethod.getUtilityBillDetailsCollection();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : utilityBillDetailsCollection) {
                utilityBillDetailsCollectionUtilityBillDetails.setPaymentMethod(null);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
            }
            Collection<ScratchCardSalesOrder> scratchCardSalesOrderCollection = paymentMethod.getScratchCardSalesOrderCollection();
            for (ScratchCardSalesOrder scratchCardSalesOrderCollectionScratchCardSalesOrder : scratchCardSalesOrderCollection) {
                scratchCardSalesOrderCollectionScratchCardSalesOrder.setPaymentMethod(null);
                scratchCardSalesOrderCollectionScratchCardSalesOrder = em.merge(scratchCardSalesOrderCollectionScratchCardSalesOrder);
            }
            Collection<PurchaseOrder> purchaseOrderCollection = paymentMethod.getPurchaseOrderCollection();
            for (PurchaseOrder purchaseOrderCollectionPurchaseOrder : purchaseOrderCollection) {
                purchaseOrderCollectionPurchaseOrder.setPaymentMethod(null);
                purchaseOrderCollectionPurchaseOrder = em.merge(purchaseOrderCollectionPurchaseOrder);
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollection = paymentMethod.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollection) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setPaymentMethod(null);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
            }
            em.remove(paymentMethod);
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

    public List<PaymentMethod> findPaymentMethodEntities() {
        return findPaymentMethodEntities(true, -1, -1);
    }

    public List<PaymentMethod> findPaymentMethodEntities(int maxResults, int firstResult) {
        return findPaymentMethodEntities(false, maxResults, firstResult);
    }

    private List<PaymentMethod> findPaymentMethodEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaymentMethod.class));
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

    public PaymentMethod findPaymentMethod(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaymentMethod.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaymentMethodCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaymentMethod> rt = cq.from(PaymentMethod.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
