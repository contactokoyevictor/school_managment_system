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
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.BehavouralTrait;
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.BehavouralTraitRatingKeys;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
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
public class BehavouralTraitJpaController implements Serializable {

    public BehavouralTraitJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BehavouralTrait behavouralTrait) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (behavouralTrait.getBehavouralTraitRatingKeysCollection() == null) {
            behavouralTrait.setBehavouralTraitRatingKeysCollection(new ArrayList<BehavouralTraitRatingKeys>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = behavouralTrait.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                behavouralTrait.setBranchId(branchId);
            }
            AcademicYears academicYear = behavouralTrait.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                behavouralTrait.setAcademicYear(academicYear);
            }
            ClassCategory classCategoryId = behavouralTrait.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId = em.getReference(classCategoryId.getClass(), classCategoryId.getCategoryId());
                behavouralTrait.setClassCategoryId(classCategoryId);
            }
            Collection<BehavouralTraitRatingKeys> attachedBehavouralTraitRatingKeysCollection = new ArrayList<BehavouralTraitRatingKeys>();
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach : behavouralTrait.getBehavouralTraitRatingKeysCollection()) {
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach = em.getReference(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach.getClass(), behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach.getId());
                attachedBehavouralTraitRatingKeysCollection.add(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeysToAttach);
            }
            behavouralTrait.setBehavouralTraitRatingKeysCollection(attachedBehavouralTraitRatingKeysCollection);
            em.persist(behavouralTrait);
            if (branchId != null) {
                branchId.getBehavouralTraitCollection().add(behavouralTrait);
                branchId = em.merge(branchId);
            }
            if (academicYear != null) {
                academicYear.getBehavouralTraitCollection().add(behavouralTrait);
                academicYear = em.merge(academicYear);
            }
            if (classCategoryId != null) {
                classCategoryId.getBehavouralTraitCollection().add(behavouralTrait);
                classCategoryId = em.merge(classCategoryId);
            }
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys : behavouralTrait.getBehavouralTraitRatingKeysCollection()) {
                BehavouralTrait oldTraitIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.getTraitId();
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.setTraitId(behavouralTrait);
                behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
                if (oldTraitIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys != null) {
                    oldTraitIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
                    oldTraitIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys = em.merge(oldTraitIdOfBehavouralTraitRatingKeysCollectionBehavouralTraitRatingKeys);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBehavouralTrait(behavouralTrait.getId()) != null) {
                throw new PreexistingEntityException("BehavouralTrait " + behavouralTrait + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BehavouralTrait behavouralTrait) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BehavouralTrait persistentBehavouralTrait = em.find(BehavouralTrait.class, behavouralTrait.getId());
            CompanyBranch branchIdOld = persistentBehavouralTrait.getBranchId();
            CompanyBranch branchIdNew = behavouralTrait.getBranchId();
            AcademicYears academicYearOld = persistentBehavouralTrait.getAcademicYear();
            AcademicYears academicYearNew = behavouralTrait.getAcademicYear();
            ClassCategory classCategoryIdOld = persistentBehavouralTrait.getClassCategoryId();
            ClassCategory classCategoryIdNew = behavouralTrait.getClassCategoryId();
            Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollectionOld = persistentBehavouralTrait.getBehavouralTraitRatingKeysCollection();
            Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollectionNew = behavouralTrait.getBehavouralTraitRatingKeysCollection();
            List<String> illegalOrphanMessages = null;
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys : behavouralTraitRatingKeysCollectionOld) {
                if (!behavouralTraitRatingKeysCollectionNew.contains(behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BehavouralTraitRatingKeys " + behavouralTraitRatingKeysCollectionOldBehavouralTraitRatingKeys + " since its traitId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                behavouralTrait.setBranchId(branchIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                behavouralTrait.setAcademicYear(academicYearNew);
            }
            if (classCategoryIdNew != null) {
                classCategoryIdNew = em.getReference(classCategoryIdNew.getClass(), classCategoryIdNew.getCategoryId());
                behavouralTrait.setClassCategoryId(classCategoryIdNew);
            }
            Collection<BehavouralTraitRatingKeys> attachedBehavouralTraitRatingKeysCollectionNew = new ArrayList<BehavouralTraitRatingKeys>();
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach : behavouralTraitRatingKeysCollectionNew) {
                behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach = em.getReference(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach.getClass(), behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach.getId());
                attachedBehavouralTraitRatingKeysCollectionNew.add(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeysToAttach);
            }
            behavouralTraitRatingKeysCollectionNew = attachedBehavouralTraitRatingKeysCollectionNew;
            behavouralTrait.setBehavouralTraitRatingKeysCollection(behavouralTraitRatingKeysCollectionNew);
            behavouralTrait = em.merge(behavouralTrait);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getBehavouralTraitCollection().remove(behavouralTrait);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getBehavouralTraitCollection().add(behavouralTrait);
                branchIdNew = em.merge(branchIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getBehavouralTraitCollection().remove(behavouralTrait);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getBehavouralTraitCollection().add(behavouralTrait);
                academicYearNew = em.merge(academicYearNew);
            }
            if (classCategoryIdOld != null && !classCategoryIdOld.equals(classCategoryIdNew)) {
                classCategoryIdOld.getBehavouralTraitCollection().remove(behavouralTrait);
                classCategoryIdOld = em.merge(classCategoryIdOld);
            }
            if (classCategoryIdNew != null && !classCategoryIdNew.equals(classCategoryIdOld)) {
                classCategoryIdNew.getBehavouralTraitCollection().add(behavouralTrait);
                classCategoryIdNew = em.merge(classCategoryIdNew);
            }
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys : behavouralTraitRatingKeysCollectionNew) {
                if (!behavouralTraitRatingKeysCollectionOld.contains(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys)) {
                    BehavouralTrait oldTraitIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys = behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.getTraitId();
                    behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.setTraitId(behavouralTrait);
                    behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys = em.merge(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys);
                    if (oldTraitIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys != null && !oldTraitIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.equals(behavouralTrait)) {
                        oldTraitIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys.getBehavouralTraitRatingKeysCollection().remove(behavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys);
                        oldTraitIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys = em.merge(oldTraitIdOfBehavouralTraitRatingKeysCollectionNewBehavouralTraitRatingKeys);
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
                Long id = behavouralTrait.getId();
                if (findBehavouralTrait(id) == null) {
                    throw new NonexistentEntityException("The behavouralTrait with id " + id + " no longer exists.");
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
            BehavouralTrait behavouralTrait;
            try {
                behavouralTrait = em.getReference(BehavouralTrait.class, id);
                behavouralTrait.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The behavouralTrait with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BehavouralTraitRatingKeys> behavouralTraitRatingKeysCollectionOrphanCheck = behavouralTrait.getBehavouralTraitRatingKeysCollection();
            for (BehavouralTraitRatingKeys behavouralTraitRatingKeysCollectionOrphanCheckBehavouralTraitRatingKeys : behavouralTraitRatingKeysCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BehavouralTrait (" + behavouralTrait + ") cannot be destroyed since the BehavouralTraitRatingKeys " + behavouralTraitRatingKeysCollectionOrphanCheckBehavouralTraitRatingKeys + " in its behavouralTraitRatingKeysCollection field has a non-nullable traitId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = behavouralTrait.getBranchId();
            if (branchId != null) {
                branchId.getBehavouralTraitCollection().remove(behavouralTrait);
                branchId = em.merge(branchId);
            }
            AcademicYears academicYear = behavouralTrait.getAcademicYear();
            if (academicYear != null) {
                academicYear.getBehavouralTraitCollection().remove(behavouralTrait);
                academicYear = em.merge(academicYear);
            }
            ClassCategory classCategoryId = behavouralTrait.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId.getBehavouralTraitCollection().remove(behavouralTrait);
                classCategoryId = em.merge(classCategoryId);
            }
            em.remove(behavouralTrait);
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

    public List<BehavouralTrait> findBehavouralTraitEntities() {
        return findBehavouralTraitEntities(true, -1, -1);
    }

    public List<BehavouralTrait> findBehavouralTraitEntities(int maxResults, int firstResult) {
        return findBehavouralTraitEntities(false, maxResults, firstResult);
    }

    private List<BehavouralTrait> findBehavouralTraitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BehavouralTrait.class));
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

    public BehavouralTrait findBehavouralTrait(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BehavouralTrait.class, id);
        } finally {
            em.close();
        }
    }

    public int getBehavouralTraitCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BehavouralTrait> rt = cq.from(BehavouralTrait.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
