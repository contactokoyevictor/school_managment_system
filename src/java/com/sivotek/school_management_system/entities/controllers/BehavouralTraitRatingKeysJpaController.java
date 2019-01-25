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
import com.sivotek.school_management_system.entities.BehavouralTrait;
import com.sivotek.school_management_system.entities.BehavouralTraitRatingKeys;
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
public class BehavouralTraitRatingKeysJpaController implements Serializable {

    public BehavouralTraitRatingKeysJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BehavouralTraitRatingKeys behavouralTraitRatingKeys) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = behavouralTraitRatingKeys.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                behavouralTraitRatingKeys.setBranchId(branchId);
            }
            BehavouralTrait traitId = behavouralTraitRatingKeys.getTraitId();
            if (traitId != null) {
                traitId = em.getReference(traitId.getClass(), traitId.getId());
                behavouralTraitRatingKeys.setTraitId(traitId);
            }
            em.persist(behavouralTraitRatingKeys);
            if (branchId != null) {
                branchId.getBehavouralTraitRatingKeysCollection().add(behavouralTraitRatingKeys);
                branchId = em.merge(branchId);
            }
            if (traitId != null) {
                traitId.getBehavouralTraitRatingKeysCollection().add(behavouralTraitRatingKeys);
                traitId = em.merge(traitId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBehavouralTraitRatingKeys(behavouralTraitRatingKeys.getId()) != null) {
                throw new PreexistingEntityException("BehavouralTraitRatingKeys " + behavouralTraitRatingKeys + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BehavouralTraitRatingKeys behavouralTraitRatingKeys) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BehavouralTraitRatingKeys persistentBehavouralTraitRatingKeys = em.find(BehavouralTraitRatingKeys.class, behavouralTraitRatingKeys.getId());
            CompanyBranch branchIdOld = persistentBehavouralTraitRatingKeys.getBranchId();
            CompanyBranch branchIdNew = behavouralTraitRatingKeys.getBranchId();
            BehavouralTrait traitIdOld = persistentBehavouralTraitRatingKeys.getTraitId();
            BehavouralTrait traitIdNew = behavouralTraitRatingKeys.getTraitId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                behavouralTraitRatingKeys.setBranchId(branchIdNew);
            }
            if (traitIdNew != null) {
                traitIdNew = em.getReference(traitIdNew.getClass(), traitIdNew.getId());
                behavouralTraitRatingKeys.setTraitId(traitIdNew);
            }
            behavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeys);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeys);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getBehavouralTraitRatingKeysCollection().add(behavouralTraitRatingKeys);
                branchIdNew = em.merge(branchIdNew);
            }
            if (traitIdOld != null && !traitIdOld.equals(traitIdNew)) {
                traitIdOld.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeys);
                traitIdOld = em.merge(traitIdOld);
            }
            if (traitIdNew != null && !traitIdNew.equals(traitIdOld)) {
                traitIdNew.getBehavouralTraitRatingKeysCollection().add(behavouralTraitRatingKeys);
                traitIdNew = em.merge(traitIdNew);
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
                Long id = behavouralTraitRatingKeys.getId();
                if (findBehavouralTraitRatingKeys(id) == null) {
                    throw new NonexistentEntityException("The behavouralTraitRatingKeys with id " + id + " no longer exists.");
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
            BehavouralTraitRatingKeys behavouralTraitRatingKeys;
            try {
                behavouralTraitRatingKeys = em.getReference(BehavouralTraitRatingKeys.class, id);
                behavouralTraitRatingKeys.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The behavouralTraitRatingKeys with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = behavouralTraitRatingKeys.getBranchId();
            if (branchId != null) {
                branchId.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeys);
                branchId = em.merge(branchId);
            }
            BehavouralTrait traitId = behavouralTraitRatingKeys.getTraitId();
            if (traitId != null) {
                traitId.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeys);
                traitId = em.merge(traitId);
            }
            em.remove(behavouralTraitRatingKeys);
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

    public List<BehavouralTraitRatingKeys> findBehavouralTraitRatingKeysEntities() {
        return findBehavouralTraitRatingKeysEntities(true, -1, -1);
    }

    public List<BehavouralTraitRatingKeys> findBehavouralTraitRatingKeysEntities(int maxResults, int firstResult) {
        return findBehavouralTraitRatingKeysEntities(false, maxResults, firstResult);
    }

    private List<BehavouralTraitRatingKeys> findBehavouralTraitRatingKeysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BehavouralTraitRatingKeys.class));
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

    public BehavouralTraitRatingKeys findBehavouralTraitRatingKeys(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BehavouralTraitRatingKeys.class, id);
        } finally {
            em.close();
        }
    }

    public int getBehavouralTraitRatingKeysCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BehavouralTraitRatingKeys> rt = cq.from(BehavouralTraitRatingKeys.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
