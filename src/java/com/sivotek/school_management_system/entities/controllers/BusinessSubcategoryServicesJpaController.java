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
import com.sivotek.school_management_system.entities.BusinessSubcategory;
import com.sivotek.school_management_system.entities.BusinessSubcategoryServices;
import com.sivotek.school_management_system.entities.UtilityBillDetails;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class BusinessSubcategoryServicesJpaController implements Serializable {

    public BusinessSubcategoryServicesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BusinessSubcategoryServices businessSubcategoryServices) throws RollbackFailureException, Exception {
        if (businessSubcategoryServices.getUtilityBillDetailsCollection() == null) {
            businessSubcategoryServices.setUtilityBillDetailsCollection(new ArrayList<UtilityBillDetails>());
        }
        if (businessSubcategoryServices.getInvoiceCollection() == null) {
            businessSubcategoryServices.setInvoiceCollection(new ArrayList<Invoice>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BusinessSubcategory businessSubcategoryId = businessSubcategoryServices.getBusinessSubcategoryId();
            if (businessSubcategoryId != null) {
                businessSubcategoryId = em.getReference(businessSubcategoryId.getClass(), businessSubcategoryId.getId());
                businessSubcategoryServices.setBusinessSubcategoryId(businessSubcategoryId);
            }
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollection = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetailsToAttach : businessSubcategoryServices.getUtilityBillDetailsCollection()) {
                utilityBillDetailsCollectionUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollection.add(utilityBillDetailsCollectionUtilityBillDetailsToAttach);
            }
            businessSubcategoryServices.setUtilityBillDetailsCollection(attachedUtilityBillDetailsCollection);
            Collection<Invoice> attachedInvoiceCollection = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionInvoiceToAttach : businessSubcategoryServices.getInvoiceCollection()) {
                invoiceCollectionInvoiceToAttach = em.getReference(invoiceCollectionInvoiceToAttach.getClass(), invoiceCollectionInvoiceToAttach.getId());
                attachedInvoiceCollection.add(invoiceCollectionInvoiceToAttach);
            }
            businessSubcategoryServices.setInvoiceCollection(attachedInvoiceCollection);
            em.persist(businessSubcategoryServices);
            if (businessSubcategoryId != null) {
                businessSubcategoryId.getBusinessSubcategoryServicesCollection().add(businessSubcategoryServices);
                businessSubcategoryId = em.merge(businessSubcategoryId);
            }
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : businessSubcategoryServices.getUtilityBillDetailsCollection()) {
                BusinessSubcategoryServices oldUtilityTypeIdOfUtilityBillDetailsCollectionUtilityBillDetails = utilityBillDetailsCollectionUtilityBillDetails.getUtilityTypeId();
                utilityBillDetailsCollectionUtilityBillDetails.setUtilityTypeId(businessSubcategoryServices);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
                if (oldUtilityTypeIdOfUtilityBillDetailsCollectionUtilityBillDetails != null) {
                    oldUtilityTypeIdOfUtilityBillDetailsCollectionUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionUtilityBillDetails);
                    oldUtilityTypeIdOfUtilityBillDetailsCollectionUtilityBillDetails = em.merge(oldUtilityTypeIdOfUtilityBillDetailsCollectionUtilityBillDetails);
                }
            }
            for (Invoice invoiceCollectionInvoice : businessSubcategoryServices.getInvoiceCollection()) {
                BusinessSubcategoryServices oldBusinessSubcategoryServiceIdOfInvoiceCollectionInvoice = invoiceCollectionInvoice.getBusinessSubcategoryServiceId();
                invoiceCollectionInvoice.setBusinessSubcategoryServiceId(businessSubcategoryServices);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
                if (oldBusinessSubcategoryServiceIdOfInvoiceCollectionInvoice != null) {
                    oldBusinessSubcategoryServiceIdOfInvoiceCollectionInvoice.getInvoiceCollection().remove(invoiceCollectionInvoice);
                    oldBusinessSubcategoryServiceIdOfInvoiceCollectionInvoice = em.merge(oldBusinessSubcategoryServiceIdOfInvoiceCollectionInvoice);
                }
            }
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

    public void edit(BusinessSubcategoryServices businessSubcategoryServices) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BusinessSubcategoryServices persistentBusinessSubcategoryServices = em.find(BusinessSubcategoryServices.class, businessSubcategoryServices.getId());
            BusinessSubcategory businessSubcategoryIdOld = persistentBusinessSubcategoryServices.getBusinessSubcategoryId();
            BusinessSubcategory businessSubcategoryIdNew = businessSubcategoryServices.getBusinessSubcategoryId();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionOld = persistentBusinessSubcategoryServices.getUtilityBillDetailsCollection();
            Collection<UtilityBillDetails> utilityBillDetailsCollectionNew = businessSubcategoryServices.getUtilityBillDetailsCollection();
            Collection<Invoice> invoiceCollectionOld = persistentBusinessSubcategoryServices.getInvoiceCollection();
            Collection<Invoice> invoiceCollectionNew = businessSubcategoryServices.getInvoiceCollection();
            List<String> illegalOrphanMessages = null;
            for (Invoice invoiceCollectionOldInvoice : invoiceCollectionOld) {
                if (!invoiceCollectionNew.contains(invoiceCollectionOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceCollectionOldInvoice + " since its businessSubcategoryServiceId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (businessSubcategoryIdNew != null) {
                businessSubcategoryIdNew = em.getReference(businessSubcategoryIdNew.getClass(), businessSubcategoryIdNew.getId());
                businessSubcategoryServices.setBusinessSubcategoryId(businessSubcategoryIdNew);
            }
            Collection<UtilityBillDetails> attachedUtilityBillDetailsCollectionNew = new ArrayList<UtilityBillDetails>();
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetailsToAttach : utilityBillDetailsCollectionNew) {
                utilityBillDetailsCollectionNewUtilityBillDetailsToAttach = em.getReference(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getClass(), utilityBillDetailsCollectionNewUtilityBillDetailsToAttach.getId());
                attachedUtilityBillDetailsCollectionNew.add(utilityBillDetailsCollectionNewUtilityBillDetailsToAttach);
            }
            utilityBillDetailsCollectionNew = attachedUtilityBillDetailsCollectionNew;
            businessSubcategoryServices.setUtilityBillDetailsCollection(utilityBillDetailsCollectionNew);
            Collection<Invoice> attachedInvoiceCollectionNew = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionNewInvoiceToAttach : invoiceCollectionNew) {
                invoiceCollectionNewInvoiceToAttach = em.getReference(invoiceCollectionNewInvoiceToAttach.getClass(), invoiceCollectionNewInvoiceToAttach.getId());
                attachedInvoiceCollectionNew.add(invoiceCollectionNewInvoiceToAttach);
            }
            invoiceCollectionNew = attachedInvoiceCollectionNew;
            businessSubcategoryServices.setInvoiceCollection(invoiceCollectionNew);
            businessSubcategoryServices = em.merge(businessSubcategoryServices);
            if (businessSubcategoryIdOld != null && !businessSubcategoryIdOld.equals(businessSubcategoryIdNew)) {
                businessSubcategoryIdOld.getBusinessSubcategoryServicesCollection().remove(businessSubcategoryServices);
                businessSubcategoryIdOld = em.merge(businessSubcategoryIdOld);
            }
            if (businessSubcategoryIdNew != null && !businessSubcategoryIdNew.equals(businessSubcategoryIdOld)) {
                businessSubcategoryIdNew.getBusinessSubcategoryServicesCollection().add(businessSubcategoryServices);
                businessSubcategoryIdNew = em.merge(businessSubcategoryIdNew);
            }
            for (UtilityBillDetails utilityBillDetailsCollectionOldUtilityBillDetails : utilityBillDetailsCollectionOld) {
                if (!utilityBillDetailsCollectionNew.contains(utilityBillDetailsCollectionOldUtilityBillDetails)) {
                    utilityBillDetailsCollectionOldUtilityBillDetails.setUtilityTypeId(null);
                    utilityBillDetailsCollectionOldUtilityBillDetails = em.merge(utilityBillDetailsCollectionOldUtilityBillDetails);
                }
            }
            for (UtilityBillDetails utilityBillDetailsCollectionNewUtilityBillDetails : utilityBillDetailsCollectionNew) {
                if (!utilityBillDetailsCollectionOld.contains(utilityBillDetailsCollectionNewUtilityBillDetails)) {
                    BusinessSubcategoryServices oldUtilityTypeIdOfUtilityBillDetailsCollectionNewUtilityBillDetails = utilityBillDetailsCollectionNewUtilityBillDetails.getUtilityTypeId();
                    utilityBillDetailsCollectionNewUtilityBillDetails.setUtilityTypeId(businessSubcategoryServices);
                    utilityBillDetailsCollectionNewUtilityBillDetails = em.merge(utilityBillDetailsCollectionNewUtilityBillDetails);
                    if (oldUtilityTypeIdOfUtilityBillDetailsCollectionNewUtilityBillDetails != null && !oldUtilityTypeIdOfUtilityBillDetailsCollectionNewUtilityBillDetails.equals(businessSubcategoryServices)) {
                        oldUtilityTypeIdOfUtilityBillDetailsCollectionNewUtilityBillDetails.getUtilityBillDetailsCollection().remove(utilityBillDetailsCollectionNewUtilityBillDetails);
                        oldUtilityTypeIdOfUtilityBillDetailsCollectionNewUtilityBillDetails = em.merge(oldUtilityTypeIdOfUtilityBillDetailsCollectionNewUtilityBillDetails);
                    }
                }
            }
            for (Invoice invoiceCollectionNewInvoice : invoiceCollectionNew) {
                if (!invoiceCollectionOld.contains(invoiceCollectionNewInvoice)) {
                    BusinessSubcategoryServices oldBusinessSubcategoryServiceIdOfInvoiceCollectionNewInvoice = invoiceCollectionNewInvoice.getBusinessSubcategoryServiceId();
                    invoiceCollectionNewInvoice.setBusinessSubcategoryServiceId(businessSubcategoryServices);
                    invoiceCollectionNewInvoice = em.merge(invoiceCollectionNewInvoice);
                    if (oldBusinessSubcategoryServiceIdOfInvoiceCollectionNewInvoice != null && !oldBusinessSubcategoryServiceIdOfInvoiceCollectionNewInvoice.equals(businessSubcategoryServices)) {
                        oldBusinessSubcategoryServiceIdOfInvoiceCollectionNewInvoice.getInvoiceCollection().remove(invoiceCollectionNewInvoice);
                        oldBusinessSubcategoryServiceIdOfInvoiceCollectionNewInvoice = em.merge(oldBusinessSubcategoryServiceIdOfInvoiceCollectionNewInvoice);
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
                Integer id = businessSubcategoryServices.getId();
                if (findBusinessSubcategoryServices(id) == null) {
                    throw new NonexistentEntityException("The businessSubcategoryServices with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BusinessSubcategoryServices businessSubcategoryServices;
            try {
                businessSubcategoryServices = em.getReference(BusinessSubcategoryServices.class, id);
                businessSubcategoryServices.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The businessSubcategoryServices with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Invoice> invoiceCollectionOrphanCheck = businessSubcategoryServices.getInvoiceCollection();
            for (Invoice invoiceCollectionOrphanCheckInvoice : invoiceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BusinessSubcategoryServices (" + businessSubcategoryServices + ") cannot be destroyed since the Invoice " + invoiceCollectionOrphanCheckInvoice + " in its invoiceCollection field has a non-nullable businessSubcategoryServiceId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BusinessSubcategory businessSubcategoryId = businessSubcategoryServices.getBusinessSubcategoryId();
            if (businessSubcategoryId != null) {
                businessSubcategoryId.getBusinessSubcategoryServicesCollection().remove(businessSubcategoryServices);
                businessSubcategoryId = em.merge(businessSubcategoryId);
            }
            Collection<UtilityBillDetails> utilityBillDetailsCollection = businessSubcategoryServices.getUtilityBillDetailsCollection();
            for (UtilityBillDetails utilityBillDetailsCollectionUtilityBillDetails : utilityBillDetailsCollection) {
                utilityBillDetailsCollectionUtilityBillDetails.setUtilityTypeId(null);
                utilityBillDetailsCollectionUtilityBillDetails = em.merge(utilityBillDetailsCollectionUtilityBillDetails);
            }
            em.remove(businessSubcategoryServices);
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

    public List<BusinessSubcategoryServices> findBusinessSubcategoryServicesEntities() {
        return findBusinessSubcategoryServicesEntities(true, -1, -1);
    }

    public List<BusinessSubcategoryServices> findBusinessSubcategoryServicesEntities(int maxResults, int firstResult) {
        return findBusinessSubcategoryServicesEntities(false, maxResults, firstResult);
    }

    private List<BusinessSubcategoryServices> findBusinessSubcategoryServicesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BusinessSubcategoryServices.class));
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

    public BusinessSubcategoryServices findBusinessSubcategoryServices(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BusinessSubcategoryServices.class, id);
        } finally {
            em.close();
        }
    }

    public int getBusinessSubcategoryServicesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BusinessSubcategoryServices> rt = cq.from(BusinessSubcategoryServices.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
