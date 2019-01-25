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
import com.sivotek.school_management_system.entities.ScratchCard;
import com.sivotek.school_management_system.entities.StudentScratchCard;
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
public class StudentScratchCardJpaController implements Serializable {

    public StudentScratchCardJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentScratchCard studentScratchCard) throws IllegalOrphanException, PreexistingEntityException, RollbackFailureException, Exception {
        List<String> illegalOrphanMessages = null;
        ScratchCard scratchCardOrphanCheck = studentScratchCard.getScratchCard();
        if (scratchCardOrphanCheck != null) {
            StudentScratchCard oldStudentScratchCardOfScratchCard = scratchCardOrphanCheck.getStudentScratchCard();
            if (oldStudentScratchCardOfScratchCard != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The ScratchCard " + scratchCardOrphanCheck + " already has an item of type StudentScratchCard whose scratchCard column cannot be null. Please make another selection for the scratchCard field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ScratchCard scratchCard = studentScratchCard.getScratchCard();
            if (scratchCard != null) {
                scratchCard = em.getReference(scratchCard.getClass(), scratchCard.getCardId());
                studentScratchCard.setScratchCard(scratchCard);
            }
            em.persist(studentScratchCard);
            if (scratchCard != null) {
                scratchCard.setStudentScratchCard(studentScratchCard);
                scratchCard = em.merge(scratchCard);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStudentScratchCard(studentScratchCard.getCardId()) != null) {
                throw new PreexistingEntityException("StudentScratchCard " + studentScratchCard + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentScratchCard studentScratchCard) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StudentScratchCard persistentStudentScratchCard = em.find(StudentScratchCard.class, studentScratchCard.getCardId());
            ScratchCard scratchCardOld = persistentStudentScratchCard.getScratchCard();
            ScratchCard scratchCardNew = studentScratchCard.getScratchCard();
            List<String> illegalOrphanMessages = null;
            if (scratchCardNew != null && !scratchCardNew.equals(scratchCardOld)) {
                StudentScratchCard oldStudentScratchCardOfScratchCard = scratchCardNew.getStudentScratchCard();
                if (oldStudentScratchCardOfScratchCard != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The ScratchCard " + scratchCardNew + " already has an item of type StudentScratchCard whose scratchCard column cannot be null. Please make another selection for the scratchCard field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (scratchCardNew != null) {
                scratchCardNew = em.getReference(scratchCardNew.getClass(), scratchCardNew.getCardId());
                studentScratchCard.setScratchCard(scratchCardNew);
            }
            studentScratchCard = em.merge(studentScratchCard);
            if (scratchCardOld != null && !scratchCardOld.equals(scratchCardNew)) {
                scratchCardOld.setStudentScratchCard(null);
                scratchCardOld = em.merge(scratchCardOld);
            }
            if (scratchCardNew != null && !scratchCardNew.equals(scratchCardOld)) {
                scratchCardNew.setStudentScratchCard(studentScratchCard);
                scratchCardNew = em.merge(scratchCardNew);
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
                Long id = studentScratchCard.getCardId();
                if (findStudentScratchCard(id) == null) {
                    throw new NonexistentEntityException("The studentScratchCard with id " + id + " no longer exists.");
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
            StudentScratchCard studentScratchCard;
            try {
                studentScratchCard = em.getReference(StudentScratchCard.class, id);
                studentScratchCard.getCardId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentScratchCard with id " + id + " no longer exists.", enfe);
            }
            ScratchCard scratchCard = studentScratchCard.getScratchCard();
            if (scratchCard != null) {
                scratchCard.setStudentScratchCard(null);
                scratchCard = em.merge(scratchCard);
            }
            em.remove(studentScratchCard);
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

    public List<StudentScratchCard> findStudentScratchCardEntities() {
        return findStudentScratchCardEntities(true, -1, -1);
    }

    public List<StudentScratchCard> findStudentScratchCardEntities(int maxResults, int firstResult) {
        return findStudentScratchCardEntities(false, maxResults, firstResult);
    }

    private List<StudentScratchCard> findStudentScratchCardEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentScratchCard.class));
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

    public StudentScratchCard findStudentScratchCard(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentScratchCard.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentScratchCardCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentScratchCard> rt = cq.from(StudentScratchCard.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
