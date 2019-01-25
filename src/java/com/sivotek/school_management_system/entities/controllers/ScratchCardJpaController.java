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
import com.sivotek.school_management_system.entities.StudentScratchCard;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.ScratchCard;
import com.sivotek.school_management_system.entities.ScratchCardType;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class ScratchCardJpaController implements Serializable {

    public ScratchCardJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScratchCard scratchCard) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StudentScratchCard studentScratchCard = scratchCard.getStudentScratchCard();
            if (studentScratchCard != null) {
                studentScratchCard = em.getReference(studentScratchCard.getClass(), studentScratchCard.getCardId());
                scratchCard.setStudentScratchCard(studentScratchCard);
            }
            CompanyBranch branchId = scratchCard.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                scratchCard.setBranchId(branchId);
            }
            ScratchCardType cardTypeId = scratchCard.getCardTypeId();
            if (cardTypeId != null) {
                cardTypeId = em.getReference(cardTypeId.getClass(), cardTypeId.getCardTypeId());
                scratchCard.setCardTypeId(cardTypeId);
            }
            em.persist(scratchCard);
            if (studentScratchCard != null) {
                ScratchCard oldScratchCardOfStudentScratchCard = studentScratchCard.getScratchCard();
                if (oldScratchCardOfStudentScratchCard != null) {
                    oldScratchCardOfStudentScratchCard.setStudentScratchCard(null);
                    oldScratchCardOfStudentScratchCard = em.merge(oldScratchCardOfStudentScratchCard);
                }
                studentScratchCard.setScratchCard(scratchCard);
                studentScratchCard = em.merge(studentScratchCard);
            }
            if (branchId != null) {
                branchId.getScratchCardCollection().add(scratchCard);
                branchId = em.merge(branchId);
            }
            if (cardTypeId != null) {
                cardTypeId.getScratchCardCollection().add(scratchCard);
                cardTypeId = em.merge(cardTypeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findScratchCard(scratchCard.getCardId()) != null) {
                throw new PreexistingEntityException("ScratchCard " + scratchCard + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScratchCard scratchCard) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ScratchCard persistentScratchCard = em.find(ScratchCard.class, scratchCard.getCardId());
            StudentScratchCard studentScratchCardOld = persistentScratchCard.getStudentScratchCard();
            StudentScratchCard studentScratchCardNew = scratchCard.getStudentScratchCard();
            CompanyBranch branchIdOld = persistentScratchCard.getBranchId();
            CompanyBranch branchIdNew = scratchCard.getBranchId();
            ScratchCardType cardTypeIdOld = persistentScratchCard.getCardTypeId();
            ScratchCardType cardTypeIdNew = scratchCard.getCardTypeId();
            List<String> illegalOrphanMessages = null;
            if (studentScratchCardOld != null && !studentScratchCardOld.equals(studentScratchCardNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain StudentScratchCard " + studentScratchCardOld + " since its scratchCard field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (studentScratchCardNew != null) {
                studentScratchCardNew = em.getReference(studentScratchCardNew.getClass(), studentScratchCardNew.getCardId());
                scratchCard.setStudentScratchCard(studentScratchCardNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                scratchCard.setBranchId(branchIdNew);
            }
            if (cardTypeIdNew != null) {
                cardTypeIdNew = em.getReference(cardTypeIdNew.getClass(), cardTypeIdNew.getCardTypeId());
                scratchCard.setCardTypeId(cardTypeIdNew);
            }
            scratchCard = em.merge(scratchCard);
            if (studentScratchCardNew != null && !studentScratchCardNew.equals(studentScratchCardOld)) {
                ScratchCard oldScratchCardOfStudentScratchCard = studentScratchCardNew.getScratchCard();
                if (oldScratchCardOfStudentScratchCard != null) {
                    oldScratchCardOfStudentScratchCard.setStudentScratchCard(null);
                    oldScratchCardOfStudentScratchCard = em.merge(oldScratchCardOfStudentScratchCard);
                }
                studentScratchCardNew.setScratchCard(scratchCard);
                studentScratchCardNew = em.merge(studentScratchCardNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getScratchCardCollection().remove(scratchCard);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getScratchCardCollection().add(scratchCard);
                branchIdNew = em.merge(branchIdNew);
            }
            if (cardTypeIdOld != null && !cardTypeIdOld.equals(cardTypeIdNew)) {
                cardTypeIdOld.getScratchCardCollection().remove(scratchCard);
                cardTypeIdOld = em.merge(cardTypeIdOld);
            }
            if (cardTypeIdNew != null && !cardTypeIdNew.equals(cardTypeIdOld)) {
                cardTypeIdNew.getScratchCardCollection().add(scratchCard);
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
                Long id = scratchCard.getCardId();
                if (findScratchCard(id) == null) {
                    throw new NonexistentEntityException("The scratchCard with id " + id + " no longer exists.");
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
            ScratchCard scratchCard;
            try {
                scratchCard = em.getReference(ScratchCard.class, id);
                scratchCard.getCardId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scratchCard with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            StudentScratchCard studentScratchCardOrphanCheck = scratchCard.getStudentScratchCard();
            if (studentScratchCardOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ScratchCard (" + scratchCard + ") cannot be destroyed since the StudentScratchCard " + studentScratchCardOrphanCheck + " in its studentScratchCard field has a non-nullable scratchCard field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = scratchCard.getBranchId();
            if (branchId != null) {
                branchId.getScratchCardCollection().remove(scratchCard);
                branchId = em.merge(branchId);
            }
            ScratchCardType cardTypeId = scratchCard.getCardTypeId();
            if (cardTypeId != null) {
                cardTypeId.getScratchCardCollection().remove(scratchCard);
                cardTypeId = em.merge(cardTypeId);
            }
            em.remove(scratchCard);
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

    public List<ScratchCard> findScratchCardEntities() {
        return findScratchCardEntities(true, -1, -1);
    }

    public List<ScratchCard> findScratchCardEntities(int maxResults, int firstResult) {
        return findScratchCardEntities(false, maxResults, firstResult);
    }

    private List<ScratchCard> findScratchCardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScratchCard.class));
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

    public ScratchCard findScratchCard(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScratchCard.class, id);
        } finally {
            em.close();
        }
    }

    public int getScratchCardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScratchCard> rt = cq.from(ScratchCard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
