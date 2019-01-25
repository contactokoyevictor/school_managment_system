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
import com.sivotek.school_management_system.entities.ScratchCardType;
import com.sivotek.school_management_system.entities.ScratchCardUnitPrice;
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
public class ScratchCardUnitPriceJpaController implements Serializable {

    public ScratchCardUnitPriceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScratchCardUnitPrice scratchCardUnitPrice) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = scratchCardUnitPrice.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                scratchCardUnitPrice.setBranchId(branchId);
            }
            ScratchCardType cardTypeId = scratchCardUnitPrice.getCardTypeId();
            if (cardTypeId != null) {
                cardTypeId = em.getReference(cardTypeId.getClass(), cardTypeId.getCardTypeId());
                scratchCardUnitPrice.setCardTypeId(cardTypeId);
            }
            em.persist(scratchCardUnitPrice);
            if (branchId != null) {
                branchId.getScratchCardUnitPriceCollection().add(scratchCardUnitPrice);
                branchId = em.merge(branchId);
            }
            if (cardTypeId != null) {
                cardTypeId.getScratchCardUnitPriceCollection().add(scratchCardUnitPrice);
                cardTypeId = em.merge(cardTypeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findScratchCardUnitPrice(scratchCardUnitPrice.getUnitId()) != null) {
                throw new PreexistingEntityException("ScratchCardUnitPrice " + scratchCardUnitPrice + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScratchCardUnitPrice scratchCardUnitPrice) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ScratchCardUnitPrice persistentScratchCardUnitPrice = em.find(ScratchCardUnitPrice.class, scratchCardUnitPrice.getUnitId());
            CompanyBranch branchIdOld = persistentScratchCardUnitPrice.getBranchId();
            CompanyBranch branchIdNew = scratchCardUnitPrice.getBranchId();
            ScratchCardType cardTypeIdOld = persistentScratchCardUnitPrice.getCardTypeId();
            ScratchCardType cardTypeIdNew = scratchCardUnitPrice.getCardTypeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                scratchCardUnitPrice.setBranchId(branchIdNew);
            }
            if (cardTypeIdNew != null) {
                cardTypeIdNew = em.getReference(cardTypeIdNew.getClass(), cardTypeIdNew.getCardTypeId());
                scratchCardUnitPrice.setCardTypeId(cardTypeIdNew);
            }
            scratchCardUnitPrice = em.merge(scratchCardUnitPrice);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getScratchCardUnitPriceCollection().remove(scratchCardUnitPrice);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getScratchCardUnitPriceCollection().add(scratchCardUnitPrice);
                branchIdNew = em.merge(branchIdNew);
            }
            if (cardTypeIdOld != null && !cardTypeIdOld.equals(cardTypeIdNew)) {
                cardTypeIdOld.getScratchCardUnitPriceCollection().remove(scratchCardUnitPrice);
                cardTypeIdOld = em.merge(cardTypeIdOld);
            }
            if (cardTypeIdNew != null && !cardTypeIdNew.equals(cardTypeIdOld)) {
                cardTypeIdNew.getScratchCardUnitPriceCollection().add(scratchCardUnitPrice);
                cardTypeIdNew = em.merge(cardTypeIdNew);
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
                Long id = scratchCardUnitPrice.getUnitId();
                if (findScratchCardUnitPrice(id) == null) {
                    throw new NonexistentEntityException("The scratchCardUnitPrice with id " + id + " no longer exists.");
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
            ScratchCardUnitPrice scratchCardUnitPrice;
            try {
                scratchCardUnitPrice = em.getReference(ScratchCardUnitPrice.class, id);
                scratchCardUnitPrice.getUnitId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scratchCardUnitPrice with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = scratchCardUnitPrice.getBranchId();
            if (branchId != null) {
                branchId.getScratchCardUnitPriceCollection().remove(scratchCardUnitPrice);
                branchId = em.merge(branchId);
            }
            ScratchCardType cardTypeId = scratchCardUnitPrice.getCardTypeId();
            if (cardTypeId != null) {
                cardTypeId.getScratchCardUnitPriceCollection().remove(scratchCardUnitPrice);
                cardTypeId = em.merge(cardTypeId);
            }
            em.remove(scratchCardUnitPrice);
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

    public List<ScratchCardUnitPrice> findScratchCardUnitPriceEntities() {
        return findScratchCardUnitPriceEntities(true, -1, -1);
    }

    public List<ScratchCardUnitPrice> findScratchCardUnitPriceEntities(int maxResults, int firstResult) {
        return findScratchCardUnitPriceEntities(false, maxResults, firstResult);
    }

    private List<ScratchCardUnitPrice> findScratchCardUnitPriceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScratchCardUnitPrice.class));
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

    public ScratchCardUnitPrice findScratchCardUnitPrice(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScratchCardUnitPrice.class, id);
        } finally {
            em.close();
        }
    }

    public int getScratchCardUnitPriceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScratchCardUnitPrice> rt = cq.from(ScratchCardUnitPrice.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
