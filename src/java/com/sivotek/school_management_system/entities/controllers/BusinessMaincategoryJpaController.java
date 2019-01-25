/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.BusinessMaincategory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.BusinessSubcategory;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
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
public class BusinessMaincategoryJpaController implements Serializable {

    public BusinessMaincategoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BusinessMaincategory businessMaincategory) throws RollbackFailureException, Exception {
        if (businessMaincategory.getBusinessSubcategoryCollection() == null) {
            businessMaincategory.setBusinessSubcategoryCollection(new ArrayList<BusinessSubcategory>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<BusinessSubcategory> attachedBusinessSubcategoryCollection = new ArrayList<BusinessSubcategory>();
            for (BusinessSubcategory businessSubcategoryCollectionBusinessSubcategoryToAttach : businessMaincategory.getBusinessSubcategoryCollection()) {
                businessSubcategoryCollectionBusinessSubcategoryToAttach = em.getReference(businessSubcategoryCollectionBusinessSubcategoryToAttach.getClass(), businessSubcategoryCollectionBusinessSubcategoryToAttach.getId());
                attachedBusinessSubcategoryCollection.add(businessSubcategoryCollectionBusinessSubcategoryToAttach);
            }
            businessMaincategory.setBusinessSubcategoryCollection(attachedBusinessSubcategoryCollection);
            em.persist(businessMaincategory);
            for (BusinessSubcategory businessSubcategoryCollectionBusinessSubcategory : businessMaincategory.getBusinessSubcategoryCollection()) {
                BusinessMaincategory oldMainCategoryIdOfBusinessSubcategoryCollectionBusinessSubcategory = businessSubcategoryCollectionBusinessSubcategory.getMainCategoryId();
                businessSubcategoryCollectionBusinessSubcategory.setMainCategoryId(businessMaincategory);
                businessSubcategoryCollectionBusinessSubcategory = em.merge(businessSubcategoryCollectionBusinessSubcategory);
                if (oldMainCategoryIdOfBusinessSubcategoryCollectionBusinessSubcategory != null) {
                    oldMainCategoryIdOfBusinessSubcategoryCollectionBusinessSubcategory.getBusinessSubcategoryCollection().remove(businessSubcategoryCollectionBusinessSubcategory);
                    oldMainCategoryIdOfBusinessSubcategoryCollectionBusinessSubcategory = em.merge(oldMainCategoryIdOfBusinessSubcategoryCollectionBusinessSubcategory);
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

    public void edit(BusinessMaincategory businessMaincategory) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BusinessMaincategory persistentBusinessMaincategory = em.find(BusinessMaincategory.class, businessMaincategory.getId());
            Collection<BusinessSubcategory> businessSubcategoryCollectionOld = persistentBusinessMaincategory.getBusinessSubcategoryCollection();
            Collection<BusinessSubcategory> businessSubcategoryCollectionNew = businessMaincategory.getBusinessSubcategoryCollection();
            List<String> illegalOrphanMessages = null;
            for (BusinessSubcategory businessSubcategoryCollectionOldBusinessSubcategory : businessSubcategoryCollectionOld) {
                if (!businessSubcategoryCollectionNew.contains(businessSubcategoryCollectionOldBusinessSubcategory)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BusinessSubcategory " + businessSubcategoryCollectionOldBusinessSubcategory + " since its mainCategoryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<BusinessSubcategory> attachedBusinessSubcategoryCollectionNew = new ArrayList<BusinessSubcategory>();
            for (BusinessSubcategory businessSubcategoryCollectionNewBusinessSubcategoryToAttach : businessSubcategoryCollectionNew) {
                businessSubcategoryCollectionNewBusinessSubcategoryToAttach = em.getReference(businessSubcategoryCollectionNewBusinessSubcategoryToAttach.getClass(), businessSubcategoryCollectionNewBusinessSubcategoryToAttach.getId());
                attachedBusinessSubcategoryCollectionNew.add(businessSubcategoryCollectionNewBusinessSubcategoryToAttach);
            }
            businessSubcategoryCollectionNew = attachedBusinessSubcategoryCollectionNew;
            businessMaincategory.setBusinessSubcategoryCollection(businessSubcategoryCollectionNew);
            businessMaincategory = em.merge(businessMaincategory);
            for (BusinessSubcategory businessSubcategoryCollectionNewBusinessSubcategory : businessSubcategoryCollectionNew) {
                if (!businessSubcategoryCollectionOld.contains(businessSubcategoryCollectionNewBusinessSubcategory)) {
                    BusinessMaincategory oldMainCategoryIdOfBusinessSubcategoryCollectionNewBusinessSubcategory = businessSubcategoryCollectionNewBusinessSubcategory.getMainCategoryId();
                    businessSubcategoryCollectionNewBusinessSubcategory.setMainCategoryId(businessMaincategory);
                    businessSubcategoryCollectionNewBusinessSubcategory = em.merge(businessSubcategoryCollectionNewBusinessSubcategory);
                    if (oldMainCategoryIdOfBusinessSubcategoryCollectionNewBusinessSubcategory != null && !oldMainCategoryIdOfBusinessSubcategoryCollectionNewBusinessSubcategory.equals(businessMaincategory)) {
                        oldMainCategoryIdOfBusinessSubcategoryCollectionNewBusinessSubcategory.getBusinessSubcategoryCollection().remove(businessSubcategoryCollectionNewBusinessSubcategory);
                        oldMainCategoryIdOfBusinessSubcategoryCollectionNewBusinessSubcategory = em.merge(oldMainCategoryIdOfBusinessSubcategoryCollectionNewBusinessSubcategory);
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
                Integer id = businessMaincategory.getId();
                if (findBusinessMaincategory(id) == null) {
                    throw new NonexistentEntityException("The businessMaincategory with id " + id + " no longer exists.");
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
            BusinessMaincategory businessMaincategory;
            try {
                businessMaincategory = em.getReference(BusinessMaincategory.class, id);
                businessMaincategory.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The businessMaincategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BusinessSubcategory> businessSubcategoryCollectionOrphanCheck = businessMaincategory.getBusinessSubcategoryCollection();
            for (BusinessSubcategory businessSubcategoryCollectionOrphanCheckBusinessSubcategory : businessSubcategoryCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BusinessMaincategory (" + businessMaincategory + ") cannot be destroyed since the BusinessSubcategory " + businessSubcategoryCollectionOrphanCheckBusinessSubcategory + " in its businessSubcategoryCollection field has a non-nullable mainCategoryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(businessMaincategory);
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

    public List<BusinessMaincategory> findBusinessMaincategoryEntities() {
        return findBusinessMaincategoryEntities(true, -1, -1);
    }

    public List<BusinessMaincategory> findBusinessMaincategoryEntities(int maxResults, int firstResult) {
        return findBusinessMaincategoryEntities(false, maxResults, firstResult);
    }

    private List<BusinessMaincategory> findBusinessMaincategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BusinessMaincategory.class));
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

    public BusinessMaincategory findBusinessMaincategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BusinessMaincategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getBusinessMaincategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BusinessMaincategory> rt = cq.from(BusinessMaincategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
