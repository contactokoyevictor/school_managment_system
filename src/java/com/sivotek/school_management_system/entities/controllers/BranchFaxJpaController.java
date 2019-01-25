/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.BranchFax;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.CompanyBranch;
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
public class BranchFaxJpaController implements Serializable {

    public BranchFaxJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BranchFax branchFax) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (branchFax.getCompanyBranchCollection() == null) {
            branchFax.setCompanyBranchCollection(new ArrayList<CompanyBranch>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = branchFax.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                branchFax.setBranchId(branchId);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollection = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionCompanyBranchToAttach : branchFax.getCompanyBranchCollection()) {
                companyBranchCollectionCompanyBranchToAttach = em.getReference(companyBranchCollectionCompanyBranchToAttach.getClass(), companyBranchCollectionCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollection.add(companyBranchCollectionCompanyBranchToAttach);
            }
            branchFax.setCompanyBranchCollection(attachedCompanyBranchCollection);
            em.persist(branchFax);
            if (branchId != null) {
                BranchFax oldFaxIdOfBranchId = branchId.getFaxId();
                if (oldFaxIdOfBranchId != null) {
                    oldFaxIdOfBranchId.setBranchId(null);
                    oldFaxIdOfBranchId = em.merge(oldFaxIdOfBranchId);
                }
                branchId.setFaxId(branchFax);
                branchId = em.merge(branchId);
            }
            for (CompanyBranch companyBranchCollectionCompanyBranch : branchFax.getCompanyBranchCollection()) {
                BranchFax oldFaxIdOfCompanyBranchCollectionCompanyBranch = companyBranchCollectionCompanyBranch.getFaxId();
                companyBranchCollectionCompanyBranch.setFaxId(branchFax);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
                if (oldFaxIdOfCompanyBranchCollectionCompanyBranch != null) {
                    oldFaxIdOfCompanyBranchCollectionCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionCompanyBranch);
                    oldFaxIdOfCompanyBranchCollectionCompanyBranch = em.merge(oldFaxIdOfCompanyBranchCollectionCompanyBranch);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBranchFax(branchFax.getFaxId()) != null) {
                throw new PreexistingEntityException("BranchFax " + branchFax + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BranchFax branchFax) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BranchFax persistentBranchFax = em.find(BranchFax.class, branchFax.getFaxId());
            CompanyBranch branchIdOld = persistentBranchFax.getBranchId();
            CompanyBranch branchIdNew = branchFax.getBranchId();
            Collection<CompanyBranch> companyBranchCollectionOld = persistentBranchFax.getCompanyBranchCollection();
            Collection<CompanyBranch> companyBranchCollectionNew = branchFax.getCompanyBranchCollection();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                branchFax.setBranchId(branchIdNew);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollectionNew = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionNewCompanyBranchToAttach : companyBranchCollectionNew) {
                companyBranchCollectionNewCompanyBranchToAttach = em.getReference(companyBranchCollectionNewCompanyBranchToAttach.getClass(), companyBranchCollectionNewCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollectionNew.add(companyBranchCollectionNewCompanyBranchToAttach);
            }
            companyBranchCollectionNew = attachedCompanyBranchCollectionNew;
            branchFax.setCompanyBranchCollection(companyBranchCollectionNew);
            branchFax = em.merge(branchFax);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.setFaxId(null);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                BranchFax oldFaxIdOfBranchId = branchIdNew.getFaxId();
                if (oldFaxIdOfBranchId != null) {
                    oldFaxIdOfBranchId.setBranchId(null);
                    oldFaxIdOfBranchId = em.merge(oldFaxIdOfBranchId);
                }
                branchIdNew.setFaxId(branchFax);
                branchIdNew = em.merge(branchIdNew);
            }
            for (CompanyBranch companyBranchCollectionOldCompanyBranch : companyBranchCollectionOld) {
                if (!companyBranchCollectionNew.contains(companyBranchCollectionOldCompanyBranch)) {
                    companyBranchCollectionOldCompanyBranch.setFaxId(null);
                    companyBranchCollectionOldCompanyBranch = em.merge(companyBranchCollectionOldCompanyBranch);
                }
            }
            for (CompanyBranch companyBranchCollectionNewCompanyBranch : companyBranchCollectionNew) {
                if (!companyBranchCollectionOld.contains(companyBranchCollectionNewCompanyBranch)) {
                    BranchFax oldFaxIdOfCompanyBranchCollectionNewCompanyBranch = companyBranchCollectionNewCompanyBranch.getFaxId();
                    companyBranchCollectionNewCompanyBranch.setFaxId(branchFax);
                    companyBranchCollectionNewCompanyBranch = em.merge(companyBranchCollectionNewCompanyBranch);
                    if (oldFaxIdOfCompanyBranchCollectionNewCompanyBranch != null && !oldFaxIdOfCompanyBranchCollectionNewCompanyBranch.equals(branchFax)) {
                        oldFaxIdOfCompanyBranchCollectionNewCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionNewCompanyBranch);
                        oldFaxIdOfCompanyBranchCollectionNewCompanyBranch = em.merge(oldFaxIdOfCompanyBranchCollectionNewCompanyBranch);
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
                Long id = branchFax.getFaxId();
                if (findBranchFax(id) == null) {
                    throw new NonexistentEntityException("The branchFax with id " + id + " no longer exists.");
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
            BranchFax branchFax;
            try {
                branchFax = em.getReference(BranchFax.class, id);
                branchFax.getFaxId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The branchFax with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = branchFax.getBranchId();
            if (branchId != null) {
                branchId.setFaxId(null);
                branchId = em.merge(branchId);
            }
            Collection<CompanyBranch> companyBranchCollection = branchFax.getCompanyBranchCollection();
            for (CompanyBranch companyBranchCollectionCompanyBranch : companyBranchCollection) {
                companyBranchCollectionCompanyBranch.setFaxId(null);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
            }
            em.remove(branchFax);
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

    public List<BranchFax> findBranchFaxEntities() {
        return findBranchFaxEntities(true, -1, -1);
    }

    public List<BranchFax> findBranchFaxEntities(int maxResults, int firstResult) {
        return findBranchFaxEntities(false, maxResults, firstResult);
    }

    private List<BranchFax> findBranchFaxEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BranchFax.class));
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

    public BranchFax findBranchFax(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BranchFax.class, id);
        } finally {
            em.close();
        }
    }

    public int getBranchFaxCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BranchFax> rt = cq.from(BranchFax.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
