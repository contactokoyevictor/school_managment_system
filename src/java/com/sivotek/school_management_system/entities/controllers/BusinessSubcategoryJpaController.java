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
import com.sivotek.school_management_system.entities.BusinessMaincategory;
import com.sivotek.school_management_system.entities.BusinessSubcategory;
import com.sivotek.school_management_system.entities.BusinessSubcategoryServices;
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
public class BusinessSubcategoryJpaController implements Serializable {

    public BusinessSubcategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BusinessSubcategory businessSubcategory) throws RollbackFailureException, Exception {
        if (businessSubcategory.getBusinessSubcategoryServicesCollection() == null) {
            businessSubcategory.setBusinessSubcategoryServicesCollection(new ArrayList<BusinessSubcategoryServices>());
        }
        if (businessSubcategory.getInvoiceCollection() == null) {
            businessSubcategory.setInvoiceCollection(new ArrayList<Invoice>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BusinessMaincategory mainCategoryId = businessSubcategory.getMainCategoryId();
            if (mainCategoryId != null) {
                mainCategoryId = em.getReference(mainCategoryId.getClass(), mainCategoryId.getId());
                businessSubcategory.setMainCategoryId(mainCategoryId);
            }
            Collection<BusinessSubcategoryServices> attachedBusinessSubcategoryServicesCollection = new ArrayList<BusinessSubcategoryServices>();
            for (BusinessSubcategoryServices businessSubcategoryServicesCollectionBusinessSubcategoryServicesToAttach : businessSubcategory.getBusinessSubcategoryServicesCollection()) {
                businessSubcategoryServicesCollectionBusinessSubcategoryServicesToAttach = em.getReference(businessSubcategoryServicesCollectionBusinessSubcategoryServicesToAttach.getClass(), businessSubcategoryServicesCollectionBusinessSubcategoryServicesToAttach.getId());
                attachedBusinessSubcategoryServicesCollection.add(businessSubcategoryServicesCollectionBusinessSubcategoryServicesToAttach);
            }
            businessSubcategory.setBusinessSubcategoryServicesCollection(attachedBusinessSubcategoryServicesCollection);
            Collection<Invoice> attachedInvoiceCollection = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionInvoiceToAttach : businessSubcategory.getInvoiceCollection()) {
                invoiceCollectionInvoiceToAttach = em.getReference(invoiceCollectionInvoiceToAttach.getClass(), invoiceCollectionInvoiceToAttach.getId());
                attachedInvoiceCollection.add(invoiceCollectionInvoiceToAttach);
            }
            businessSubcategory.setInvoiceCollection(attachedInvoiceCollection);
            em.persist(businessSubcategory);
            if (mainCategoryId != null) {
                mainCategoryId.getBusinessSubcategoryCollection().add(businessSubcategory);
                mainCategoryId = em.merge(mainCategoryId);
            }
            for (BusinessSubcategoryServices businessSubcategoryServicesCollectionBusinessSubcategoryServices : businessSubcategory.getBusinessSubcategoryServicesCollection()) {
                BusinessSubcategory oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionBusinessSubcategoryServices = businessSubcategoryServicesCollectionBusinessSubcategoryServices.getBusinessSubcategoryId();
                businessSubcategoryServicesCollectionBusinessSubcategoryServices.setBusinessSubcategoryId(businessSubcategory);
                businessSubcategoryServicesCollectionBusinessSubcategoryServices = em.merge(businessSubcategoryServicesCollectionBusinessSubcategoryServices);
                if (oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionBusinessSubcategoryServices != null) {
                    oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionBusinessSubcategoryServices.getBusinessSubcategoryServicesCollection().remove(businessSubcategoryServicesCollectionBusinessSubcategoryServices);
                    oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionBusinessSubcategoryServices = em.merge(oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionBusinessSubcategoryServices);
                }
            }
            for (Invoice invoiceCollectionInvoice : businessSubcategory.getInvoiceCollection()) {
                BusinessSubcategory oldBusinessSubcategoryIdOfInvoiceCollectionInvoice = invoiceCollectionInvoice.getBusinessSubcategoryId();
                invoiceCollectionInvoice.setBusinessSubcategoryId(businessSubcategory);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
                if (oldBusinessSubcategoryIdOfInvoiceCollectionInvoice != null) {
                    oldBusinessSubcategoryIdOfInvoiceCollectionInvoice.getInvoiceCollection().remove(invoiceCollectionInvoice);
                    oldBusinessSubcategoryIdOfInvoiceCollectionInvoice = em.merge(oldBusinessSubcategoryIdOfInvoiceCollectionInvoice);
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

    public void edit(BusinessSubcategory businessSubcategory) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BusinessSubcategory persistentBusinessSubcategory = em.find(BusinessSubcategory.class, businessSubcategory.getId());
            BusinessMaincategory mainCategoryIdOld = persistentBusinessSubcategory.getMainCategoryId();
            BusinessMaincategory mainCategoryIdNew = businessSubcategory.getMainCategoryId();
            Collection<BusinessSubcategoryServices> businessSubcategoryServicesCollectionOld = persistentBusinessSubcategory.getBusinessSubcategoryServicesCollection();
            Collection<BusinessSubcategoryServices> businessSubcategoryServicesCollectionNew = businessSubcategory.getBusinessSubcategoryServicesCollection();
            Collection<Invoice> invoiceCollectionOld = persistentBusinessSubcategory.getInvoiceCollection();
            Collection<Invoice> invoiceCollectionNew = businessSubcategory.getInvoiceCollection();
            List<String> illegalOrphanMessages = null;
            for (BusinessSubcategoryServices businessSubcategoryServicesCollectionOldBusinessSubcategoryServices : businessSubcategoryServicesCollectionOld) {
                if (!businessSubcategoryServicesCollectionNew.contains(businessSubcategoryServicesCollectionOldBusinessSubcategoryServices)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BusinessSubcategoryServices " + businessSubcategoryServicesCollectionOldBusinessSubcategoryServices + " since its businessSubcategoryId field is not nullable.");
                }
            }
            for (Invoice invoiceCollectionOldInvoice : invoiceCollectionOld) {
                if (!invoiceCollectionNew.contains(invoiceCollectionOldInvoice)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Invoice " + invoiceCollectionOldInvoice + " since its businessSubcategoryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (mainCategoryIdNew != null) {
                mainCategoryIdNew = em.getReference(mainCategoryIdNew.getClass(), mainCategoryIdNew.getId());
                businessSubcategory.setMainCategoryId(mainCategoryIdNew);
            }
            Collection<BusinessSubcategoryServices> attachedBusinessSubcategoryServicesCollectionNew = new ArrayList<BusinessSubcategoryServices>();
            for (BusinessSubcategoryServices businessSubcategoryServicesCollectionNewBusinessSubcategoryServicesToAttach : businessSubcategoryServicesCollectionNew) {
                businessSubcategoryServicesCollectionNewBusinessSubcategoryServicesToAttach = em.getReference(businessSubcategoryServicesCollectionNewBusinessSubcategoryServicesToAttach.getClass(), businessSubcategoryServicesCollectionNewBusinessSubcategoryServicesToAttach.getId());
                attachedBusinessSubcategoryServicesCollectionNew.add(businessSubcategoryServicesCollectionNewBusinessSubcategoryServicesToAttach);
            }
            businessSubcategoryServicesCollectionNew = attachedBusinessSubcategoryServicesCollectionNew;
            businessSubcategory.setBusinessSubcategoryServicesCollection(businessSubcategoryServicesCollectionNew);
            Collection<Invoice> attachedInvoiceCollectionNew = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionNewInvoiceToAttach : invoiceCollectionNew) {
                invoiceCollectionNewInvoiceToAttach = em.getReference(invoiceCollectionNewInvoiceToAttach.getClass(), invoiceCollectionNewInvoiceToAttach.getId());
                attachedInvoiceCollectionNew.add(invoiceCollectionNewInvoiceToAttach);
            }
            invoiceCollectionNew = attachedInvoiceCollectionNew;
            businessSubcategory.setInvoiceCollection(invoiceCollectionNew);
            businessSubcategory = em.merge(businessSubcategory);
            if (mainCategoryIdOld != null && !mainCategoryIdOld.equals(mainCategoryIdNew)) {
                mainCategoryIdOld.getBusinessSubcategoryCollection().remove(businessSubcategory);
                mainCategoryIdOld = em.merge(mainCategoryIdOld);
            }
            if (mainCategoryIdNew != null && !mainCategoryIdNew.equals(mainCategoryIdOld)) {
                mainCategoryIdNew.getBusinessSubcategoryCollection().add(businessSubcategory);
                mainCategoryIdNew = em.merge(mainCategoryIdNew);
            }
            for (BusinessSubcategoryServices businessSubcategoryServicesCollectionNewBusinessSubcategoryServices : businessSubcategoryServicesCollectionNew) {
                if (!businessSubcategoryServicesCollectionOld.contains(businessSubcategoryServicesCollectionNewBusinessSubcategoryServices)) {
                    BusinessSubcategory oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionNewBusinessSubcategoryServices = businessSubcategoryServicesCollectionNewBusinessSubcategoryServices.getBusinessSubcategoryId();
                    businessSubcategoryServicesCollectionNewBusinessSubcategoryServices.setBusinessSubcategoryId(businessSubcategory);
                    businessSubcategoryServicesCollectionNewBusinessSubcategoryServices = em.merge(businessSubcategoryServicesCollectionNewBusinessSubcategoryServices);
                    if (oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionNewBusinessSubcategoryServices != null && !oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionNewBusinessSubcategoryServices.equals(businessSubcategory)) {
                        oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionNewBusinessSubcategoryServices.getBusinessSubcategoryServicesCollection().remove(businessSubcategoryServicesCollectionNewBusinessSubcategoryServices);
                        oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionNewBusinessSubcategoryServices = em.merge(oldBusinessSubcategoryIdOfBusinessSubcategoryServicesCollectionNewBusinessSubcategoryServices);
                    }
                }
            }
            for (Invoice invoiceCollectionNewInvoice : invoiceCollectionNew) {
                if (!invoiceCollectionOld.contains(invoiceCollectionNewInvoice)) {
                    BusinessSubcategory oldBusinessSubcategoryIdOfInvoiceCollectionNewInvoice = invoiceCollectionNewInvoice.getBusinessSubcategoryId();
                    invoiceCollectionNewInvoice.setBusinessSubcategoryId(businessSubcategory);
                    invoiceCollectionNewInvoice = em.merge(invoiceCollectionNewInvoice);
                    if (oldBusinessSubcategoryIdOfInvoiceCollectionNewInvoice != null && !oldBusinessSubcategoryIdOfInvoiceCollectionNewInvoice.equals(businessSubcategory)) {
                        oldBusinessSubcategoryIdOfInvoiceCollectionNewInvoice.getInvoiceCollection().remove(invoiceCollectionNewInvoice);
                        oldBusinessSubcategoryIdOfInvoiceCollectionNewInvoice = em.merge(oldBusinessSubcategoryIdOfInvoiceCollectionNewInvoice);
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
                Integer id = businessSubcategory.getId();
                if (findBusinessSubcategory(id) == null) {
                    throw new NonexistentEntityException("The businessSubcategory with id " + id + " no longer exists.");
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
            BusinessSubcategory businessSubcategory;
            try {
                businessSubcategory = em.getReference(BusinessSubcategory.class, id);
                businessSubcategory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The businessSubcategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BusinessSubcategoryServices> businessSubcategoryServicesCollectionOrphanCheck = businessSubcategory.getBusinessSubcategoryServicesCollection();
            for (BusinessSubcategoryServices businessSubcategoryServicesCollectionOrphanCheckBusinessSubcategoryServices : businessSubcategoryServicesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BusinessSubcategory (" + businessSubcategory + ") cannot be destroyed since the BusinessSubcategoryServices " + businessSubcategoryServicesCollectionOrphanCheckBusinessSubcategoryServices + " in its businessSubcategoryServicesCollection field has a non-nullable businessSubcategoryId field.");
            }
            Collection<Invoice> invoiceCollectionOrphanCheck = businessSubcategory.getInvoiceCollection();
            for (Invoice invoiceCollectionOrphanCheckInvoice : invoiceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BusinessSubcategory (" + businessSubcategory + ") cannot be destroyed since the Invoice " + invoiceCollectionOrphanCheckInvoice + " in its invoiceCollection field has a non-nullable businessSubcategoryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BusinessMaincategory mainCategoryId = businessSubcategory.getMainCategoryId();
            if (mainCategoryId != null) {
                mainCategoryId.getBusinessSubcategoryCollection().remove(businessSubcategory);
                mainCategoryId = em.merge(mainCategoryId);
            }
            em.remove(businessSubcategory);
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

    public List<BusinessSubcategory> findBusinessSubcategoryEntities() {
        return findBusinessSubcategoryEntities(true, -1, -1);
    }

    public List<BusinessSubcategory> findBusinessSubcategoryEntities(int maxResults, int firstResult) {
        return findBusinessSubcategoryEntities(false, maxResults, firstResult);
    }

    private List<BusinessSubcategory> findBusinessSubcategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BusinessSubcategory.class));
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

    public BusinessSubcategory findBusinessSubcategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BusinessSubcategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getBusinessSubcategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BusinessSubcategory> rt = cq.from(BusinessSubcategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
