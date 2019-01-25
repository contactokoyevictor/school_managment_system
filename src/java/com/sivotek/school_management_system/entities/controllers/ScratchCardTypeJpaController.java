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
import com.sivotek.school_management_system.entities.ScratchCardUnitPrice;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.ScratchCard;
import com.sivotek.school_management_system.entities.ScratchCardType;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
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
public class ScratchCardTypeJpaController implements Serializable {

    public ScratchCardTypeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScratchCardType scratchCardType) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (scratchCardType.getScratchCardUnitPriceCollection() == null) {
            scratchCardType.setScratchCardUnitPriceCollection(new ArrayList<ScratchCardUnitPrice>());
        }
        if (scratchCardType.getScratchCardCollection() == null) {
            scratchCardType.setScratchCardCollection(new ArrayList<ScratchCard>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = scratchCardType.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                scratchCardType.setBranchId(branchId);
            }
            Collection<ScratchCardUnitPrice> attachedScratchCardUnitPriceCollection = new ArrayList<ScratchCardUnitPrice>();
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach : scratchCardType.getScratchCardUnitPriceCollection()) {
                scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach = em.getReference(scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach.getClass(), scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach.getUnitId());
                attachedScratchCardUnitPriceCollection.add(scratchCardUnitPriceCollectionScratchCardUnitPriceToAttach);
            }
            scratchCardType.setScratchCardUnitPriceCollection(attachedScratchCardUnitPriceCollection);
            Collection<ScratchCard> attachedScratchCardCollection = new ArrayList<ScratchCard>();
            for (ScratchCard scratchCardCollectionScratchCardToAttach : scratchCardType.getScratchCardCollection()) {
                scratchCardCollectionScratchCardToAttach = em.getReference(scratchCardCollectionScratchCardToAttach.getClass(), scratchCardCollectionScratchCardToAttach.getCardId());
                attachedScratchCardCollection.add(scratchCardCollectionScratchCardToAttach);
            }
            scratchCardType.setScratchCardCollection(attachedScratchCardCollection);
            em.persist(scratchCardType);
            if (branchId != null) {
                branchId.getScratchCardTypeCollection().add(scratchCardType);
                branchId = em.merge(branchId);
            }
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionScratchCardUnitPrice : scratchCardType.getScratchCardUnitPriceCollection()) {
                ScratchCardType oldCardTypeIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice = scratchCardUnitPriceCollectionScratchCardUnitPrice.getCardTypeId();
                scratchCardUnitPriceCollectionScratchCardUnitPrice.setCardTypeId(scratchCardType);
                scratchCardUnitPriceCollectionScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionScratchCardUnitPrice);
                if (oldCardTypeIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice != null) {
                    oldCardTypeIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice.getScratchCardUnitPriceCollection().remove(scratchCardUnitPriceCollectionScratchCardUnitPrice);
                    oldCardTypeIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice = em.merge(oldCardTypeIdOfScratchCardUnitPriceCollectionScratchCardUnitPrice);
                }
            }
            for (ScratchCard scratchCardCollectionScratchCard : scratchCardType.getScratchCardCollection()) {
                ScratchCardType oldCardTypeIdOfScratchCardCollectionScratchCard = scratchCardCollectionScratchCard.getCardTypeId();
                scratchCardCollectionScratchCard.setCardTypeId(scratchCardType);
                scratchCardCollectionScratchCard = em.merge(scratchCardCollectionScratchCard);
                if (oldCardTypeIdOfScratchCardCollectionScratchCard != null) {
                    oldCardTypeIdOfScratchCardCollectionScratchCard.getScratchCardCollection().remove(scratchCardCollectionScratchCard);
                    oldCardTypeIdOfScratchCardCollectionScratchCard = em.merge(oldCardTypeIdOfScratchCardCollectionScratchCard);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findScratchCardType(scratchCardType.getCardTypeId()) != null) {
                throw new PreexistingEntityException("ScratchCardType " + scratchCardType + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScratchCardType scratchCardType) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ScratchCardType persistentScratchCardType = em.find(ScratchCardType.class, scratchCardType.getCardTypeId());
            CompanyBranch branchIdOld = persistentScratchCardType.getBranchId();
            CompanyBranch branchIdNew = scratchCardType.getBranchId();
            Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollectionOld = persistentScratchCardType.getScratchCardUnitPriceCollection();
            Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollectionNew = scratchCardType.getScratchCardUnitPriceCollection();
            Collection<ScratchCard> scratchCardCollectionOld = persistentScratchCardType.getScratchCardCollection();
            Collection<ScratchCard> scratchCardCollectionNew = scratchCardType.getScratchCardCollection();
            List<String> illegalOrphanMessages = null;
            for (ScratchCard scratchCardCollectionOldScratchCard : scratchCardCollectionOld) {
                if (!scratchCardCollectionNew.contains(scratchCardCollectionOldScratchCard)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ScratchCard " + scratchCardCollectionOldScratchCard + " since its cardTypeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                scratchCardType.setBranchId(branchIdNew);
            }
            Collection<ScratchCardUnitPrice> attachedScratchCardUnitPriceCollectionNew = new ArrayList<ScratchCardUnitPrice>();
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach : scratchCardUnitPriceCollectionNew) {
                scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach = em.getReference(scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach.getClass(), scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach.getUnitId());
                attachedScratchCardUnitPriceCollectionNew.add(scratchCardUnitPriceCollectionNewScratchCardUnitPriceToAttach);
            }
            scratchCardUnitPriceCollectionNew = attachedScratchCardUnitPriceCollectionNew;
            scratchCardType.setScratchCardUnitPriceCollection(scratchCardUnitPriceCollectionNew);
            Collection<ScratchCard> attachedScratchCardCollectionNew = new ArrayList<ScratchCard>();
            for (ScratchCard scratchCardCollectionNewScratchCardToAttach : scratchCardCollectionNew) {
                scratchCardCollectionNewScratchCardToAttach = em.getReference(scratchCardCollectionNewScratchCardToAttach.getClass(), scratchCardCollectionNewScratchCardToAttach.getCardId());
                attachedScratchCardCollectionNew.add(scratchCardCollectionNewScratchCardToAttach);
            }
            scratchCardCollectionNew = attachedScratchCardCollectionNew;
            scratchCardType.setScratchCardCollection(scratchCardCollectionNew);
            scratchCardType = em.merge(scratchCardType);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getScratchCardTypeCollection().remove(scratchCardType);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getScratchCardTypeCollection().add(scratchCardType);
                branchIdNew = em.merge(branchIdNew);
            }
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionOldScratchCardUnitPrice : scratchCardUnitPriceCollectionOld) {
                if (!scratchCardUnitPriceCollectionNew.contains(scratchCardUnitPriceCollectionOldScratchCardUnitPrice)) {
                    scratchCardUnitPriceCollectionOldScratchCardUnitPrice.setCardTypeId(null);
                    scratchCardUnitPriceCollectionOldScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionOldScratchCardUnitPrice);
                }
            }
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionNewScratchCardUnitPrice : scratchCardUnitPriceCollectionNew) {
                if (!scratchCardUnitPriceCollectionOld.contains(scratchCardUnitPriceCollectionNewScratchCardUnitPrice)) {
                    ScratchCardType oldCardTypeIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice = scratchCardUnitPriceCollectionNewScratchCardUnitPrice.getCardTypeId();
                    scratchCardUnitPriceCollectionNewScratchCardUnitPrice.setCardTypeId(scratchCardType);
                    scratchCardUnitPriceCollectionNewScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionNewScratchCardUnitPrice);
                    if (oldCardTypeIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice != null && !oldCardTypeIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice.equals(scratchCardType)) {
                        oldCardTypeIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice.getScratchCardUnitPriceCollection().remove(scratchCardUnitPriceCollectionNewScratchCardUnitPrice);
                        oldCardTypeIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice = em.merge(oldCardTypeIdOfScratchCardUnitPriceCollectionNewScratchCardUnitPrice);
                    }
                }
            }
            for (ScratchCard scratchCardCollectionNewScratchCard : scratchCardCollectionNew) {
                if (!scratchCardCollectionOld.contains(scratchCardCollectionNewScratchCard)) {
                    ScratchCardType oldCardTypeIdOfScratchCardCollectionNewScratchCard = scratchCardCollectionNewScratchCard.getCardTypeId();
                    scratchCardCollectionNewScratchCard.setCardTypeId(scratchCardType);
                    scratchCardCollectionNewScratchCard = em.merge(scratchCardCollectionNewScratchCard);
                    if (oldCardTypeIdOfScratchCardCollectionNewScratchCard != null && !oldCardTypeIdOfScratchCardCollectionNewScratchCard.equals(scratchCardType)) {
                        oldCardTypeIdOfScratchCardCollectionNewScratchCard.getScratchCardCollection().remove(scratchCardCollectionNewScratchCard);
                        oldCardTypeIdOfScratchCardCollectionNewScratchCard = em.merge(oldCardTypeIdOfScratchCardCollectionNewScratchCard);
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
                Long id = scratchCardType.getCardTypeId();
                if (findScratchCardType(id) == null) {
                    throw new NonexistentEntityException("The scratchCardType with id " + id + " no longer exists.");
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
            ScratchCardType scratchCardType;
            try {
                scratchCardType = em.getReference(ScratchCardType.class, id);
                scratchCardType.getCardTypeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scratchCardType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ScratchCard> scratchCardCollectionOrphanCheck = scratchCardType.getScratchCardCollection();
            for (ScratchCard scratchCardCollectionOrphanCheckScratchCard : scratchCardCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ScratchCardType (" + scratchCardType + ") cannot be destroyed since the ScratchCard " + scratchCardCollectionOrphanCheckScratchCard + " in its scratchCardCollection field has a non-nullable cardTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = scratchCardType.getBranchId();
            if (branchId != null) {
                branchId.getScratchCardTypeCollection().remove(scratchCardType);
                branchId = em.merge(branchId);
            }
            Collection<ScratchCardUnitPrice> scratchCardUnitPriceCollection = scratchCardType.getScratchCardUnitPriceCollection();
            for (ScratchCardUnitPrice scratchCardUnitPriceCollectionScratchCardUnitPrice : scratchCardUnitPriceCollection) {
                scratchCardUnitPriceCollectionScratchCardUnitPrice.setCardTypeId(null);
                scratchCardUnitPriceCollectionScratchCardUnitPrice = em.merge(scratchCardUnitPriceCollectionScratchCardUnitPrice);
            }
            em.remove(scratchCardType);
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

    public List<ScratchCardType> findScratchCardTypeEntities() {
        return findScratchCardTypeEntities(true, -1, -1);
    }

    public List<ScratchCardType> findScratchCardTypeEntities(int maxResults, int firstResult) {
        return findScratchCardTypeEntities(false, maxResults, firstResult);
    }

    private List<ScratchCardType> findScratchCardTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScratchCardType.class));
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

    public ScratchCardType findScratchCardType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScratchCardType.class, id);
        } finally {
            em.close();
        }
    }

    public int getScratchCardTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScratchCardType> rt = cq.from(ScratchCardType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
