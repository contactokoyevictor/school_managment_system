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
import com.sivotek.school_management_system.entities.BusinessSubcategoryServices;
import com.sivotek.school_management_system.entities.PaymentMethod;
import com.sivotek.school_management_system.entities.UtilityBillDetails;
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
public class UtilityBillDetailsJpaController implements Serializable {

    public UtilityBillDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UtilityBillDetails utilityBillDetails) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = utilityBillDetails.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                utilityBillDetails.setBranchId(branchId);
            }
            Invoice invoiceId = utilityBillDetails.getInvoiceId();
            if (invoiceId != null) {
                invoiceId = em.getReference(invoiceId.getClass(), invoiceId.getId());
                utilityBillDetails.setInvoiceId(invoiceId);
            }
            BusinessSubcategoryServices utilityTypeId = utilityBillDetails.getUtilityTypeId();
            if (utilityTypeId != null) {
                utilityTypeId = em.getReference(utilityTypeId.getClass(), utilityTypeId.getId());
                utilityBillDetails.setUtilityTypeId(utilityTypeId);
            }
            PaymentMethod paymentMethod = utilityBillDetails.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod = em.getReference(paymentMethod.getClass(), paymentMethod.getId());
                utilityBillDetails.setPaymentMethod(paymentMethod);
            }
            em.persist(utilityBillDetails);
            if (branchId != null) {
                branchId.getUtilityBillDetailsCollection().add(utilityBillDetails);
                branchId = em.merge(branchId);
            }
            if (invoiceId != null) {
                invoiceId.getUtilityBillDetailsCollection().add(utilityBillDetails);
                invoiceId = em.merge(invoiceId);
            }
            if (utilityTypeId != null) {
                utilityTypeId.getUtilityBillDetailsCollection().add(utilityBillDetails);
                utilityTypeId = em.merge(utilityTypeId);
            }
            if (paymentMethod != null) {
                paymentMethod.getUtilityBillDetailsCollection().add(utilityBillDetails);
                paymentMethod = em.merge(paymentMethod);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUtilityBillDetails(utilityBillDetails.getId()) != null) {
                throw new PreexistingEntityException("UtilityBillDetails " + utilityBillDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UtilityBillDetails utilityBillDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            UtilityBillDetails persistentUtilityBillDetails = em.find(UtilityBillDetails.class, utilityBillDetails.getId());
            CompanyBranch branchIdOld = persistentUtilityBillDetails.getBranchId();
            CompanyBranch branchIdNew = utilityBillDetails.getBranchId();
            Invoice invoiceIdOld = persistentUtilityBillDetails.getInvoiceId();
            Invoice invoiceIdNew = utilityBillDetails.getInvoiceId();
            BusinessSubcategoryServices utilityTypeIdOld = persistentUtilityBillDetails.getUtilityTypeId();
            BusinessSubcategoryServices utilityTypeIdNew = utilityBillDetails.getUtilityTypeId();
            PaymentMethod paymentMethodOld = persistentUtilityBillDetails.getPaymentMethod();
            PaymentMethod paymentMethodNew = utilityBillDetails.getPaymentMethod();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                utilityBillDetails.setBranchId(branchIdNew);
            }
            if (invoiceIdNew != null) {
                invoiceIdNew = em.getReference(invoiceIdNew.getClass(), invoiceIdNew.getId());
                utilityBillDetails.setInvoiceId(invoiceIdNew);
            }
            if (utilityTypeIdNew != null) {
                utilityTypeIdNew = em.getReference(utilityTypeIdNew.getClass(), utilityTypeIdNew.getId());
                utilityBillDetails.setUtilityTypeId(utilityTypeIdNew);
            }
            if (paymentMethodNew != null) {
                paymentMethodNew = em.getReference(paymentMethodNew.getClass(), paymentMethodNew.getId());
                utilityBillDetails.setPaymentMethod(paymentMethodNew);
            }
            utilityBillDetails = em.merge(utilityBillDetails);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getUtilityBillDetailsCollection().add(utilityBillDetails);
                branchIdNew = em.merge(branchIdNew);
            }
            if (invoiceIdOld != null && !invoiceIdOld.equals(invoiceIdNew)) {
                invoiceIdOld.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                invoiceIdOld = em.merge(invoiceIdOld);
            }
            if (invoiceIdNew != null && !invoiceIdNew.equals(invoiceIdOld)) {
                invoiceIdNew.getUtilityBillDetailsCollection().add(utilityBillDetails);
                invoiceIdNew = em.merge(invoiceIdNew);
            }
            if (utilityTypeIdOld != null && !utilityTypeIdOld.equals(utilityTypeIdNew)) {
                utilityTypeIdOld.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                utilityTypeIdOld = em.merge(utilityTypeIdOld);
            }
            if (utilityTypeIdNew != null && !utilityTypeIdNew.equals(utilityTypeIdOld)) {
                utilityTypeIdNew.getUtilityBillDetailsCollection().add(utilityBillDetails);
                utilityTypeIdNew = em.merge(utilityTypeIdNew);
            }
            if (paymentMethodOld != null && !paymentMethodOld.equals(paymentMethodNew)) {
                paymentMethodOld.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                paymentMethodOld = em.merge(paymentMethodOld);
            }
            if (paymentMethodNew != null && !paymentMethodNew.equals(paymentMethodOld)) {
                paymentMethodNew.getUtilityBillDetailsCollection().add(utilityBillDetails);
                paymentMethodNew = em.merge(paymentMethodNew);
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
                Long id = utilityBillDetails.getId();
                if (findUtilityBillDetails(id) == null) {
                    throw new NonexistentEntityException("The utilityBillDetails with id " + id + " no longer exists.");
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
            UtilityBillDetails utilityBillDetails;
            try {
                utilityBillDetails = em.getReference(UtilityBillDetails.class, id);
                utilityBillDetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilityBillDetails with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = utilityBillDetails.getBranchId();
            if (branchId != null) {
                branchId.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                branchId = em.merge(branchId);
            }
            Invoice invoiceId = utilityBillDetails.getInvoiceId();
            if (invoiceId != null) {
                invoiceId.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                invoiceId = em.merge(invoiceId);
            }
            BusinessSubcategoryServices utilityTypeId = utilityBillDetails.getUtilityTypeId();
            if (utilityTypeId != null) {
                utilityTypeId.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                utilityTypeId = em.merge(utilityTypeId);
            }
            PaymentMethod paymentMethod = utilityBillDetails.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod.getUtilityBillDetailsCollection().remove(utilityBillDetails);
                paymentMethod = em.merge(paymentMethod);
            }
            em.remove(utilityBillDetails);
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

    public List<UtilityBillDetails> findUtilityBillDetailsEntities() {
        return findUtilityBillDetailsEntities(true, -1, -1);
    }

    public List<UtilityBillDetails> findUtilityBillDetailsEntities(int maxResults, int firstResult) {
        return findUtilityBillDetailsEntities(false, maxResults, firstResult);
    }

    private List<UtilityBillDetails> findUtilityBillDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UtilityBillDetails.class));
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

    public UtilityBillDetails findUtilityBillDetails(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UtilityBillDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilityBillDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UtilityBillDetails> rt = cq.from(UtilityBillDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
