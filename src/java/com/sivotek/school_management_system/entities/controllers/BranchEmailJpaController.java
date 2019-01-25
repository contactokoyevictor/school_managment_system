/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.BranchEmail;
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
public class BranchEmailJpaController implements Serializable {

    public BranchEmailJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BranchEmail branchEmail) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (branchEmail.getCompanyBranchCollection() == null) {
            branchEmail.setCompanyBranchCollection(new ArrayList<CompanyBranch>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = branchEmail.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                branchEmail.setBranchId(branchId);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollection = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionCompanyBranchToAttach : branchEmail.getCompanyBranchCollection()) {
                companyBranchCollectionCompanyBranchToAttach = em.getReference(companyBranchCollectionCompanyBranchToAttach.getClass(), companyBranchCollectionCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollection.add(companyBranchCollectionCompanyBranchToAttach);
            }
            branchEmail.setCompanyBranchCollection(attachedCompanyBranchCollection);
            em.persist(branchEmail);
            if (branchId != null) {
                BranchEmail oldEmailIdOfBranchId = branchId.getEmailId();
                if (oldEmailIdOfBranchId != null) {
                    oldEmailIdOfBranchId.setBranchId(null);
                    oldEmailIdOfBranchId = em.merge(oldEmailIdOfBranchId);
                }
                branchId.setEmailId(branchEmail);
                branchId = em.merge(branchId);
            }
            for (CompanyBranch companyBranchCollectionCompanyBranch : branchEmail.getCompanyBranchCollection()) {
                BranchEmail oldEmailIdOfCompanyBranchCollectionCompanyBranch = companyBranchCollectionCompanyBranch.getEmailId();
                companyBranchCollectionCompanyBranch.setEmailId(branchEmail);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
                if (oldEmailIdOfCompanyBranchCollectionCompanyBranch != null) {
                    oldEmailIdOfCompanyBranchCollectionCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionCompanyBranch);
                    oldEmailIdOfCompanyBranchCollectionCompanyBranch = em.merge(oldEmailIdOfCompanyBranchCollectionCompanyBranch);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBranchEmail(branchEmail.getEmailId()) != null) {
                throw new PreexistingEntityException("BranchEmail " + branchEmail + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BranchEmail branchEmail) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BranchEmail persistentBranchEmail = em.find(BranchEmail.class, branchEmail.getEmailId());
            CompanyBranch branchIdOld = persistentBranchEmail.getBranchId();
            CompanyBranch branchIdNew = branchEmail.getBranchId();
            Collection<CompanyBranch> companyBranchCollectionOld = persistentBranchEmail.getCompanyBranchCollection();
            Collection<CompanyBranch> companyBranchCollectionNew = branchEmail.getCompanyBranchCollection();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                branchEmail.setBranchId(branchIdNew);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollectionNew = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionNewCompanyBranchToAttach : companyBranchCollectionNew) {
                companyBranchCollectionNewCompanyBranchToAttach = em.getReference(companyBranchCollectionNewCompanyBranchToAttach.getClass(), companyBranchCollectionNewCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollectionNew.add(companyBranchCollectionNewCompanyBranchToAttach);
            }
            companyBranchCollectionNew = attachedCompanyBranchCollectionNew;
            branchEmail.setCompanyBranchCollection(companyBranchCollectionNew);
            branchEmail = em.merge(branchEmail);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.setEmailId(null);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                BranchEmail oldEmailIdOfBranchId = branchIdNew.getEmailId();
                if (oldEmailIdOfBranchId != null) {
                    oldEmailIdOfBranchId.setBranchId(null);
                    oldEmailIdOfBranchId = em.merge(oldEmailIdOfBranchId);
                }
                branchIdNew.setEmailId(branchEmail);
                branchIdNew = em.merge(branchIdNew);
            }
            for (CompanyBranch companyBranchCollectionOldCompanyBranch : companyBranchCollectionOld) {
                if (!companyBranchCollectionNew.contains(companyBranchCollectionOldCompanyBranch)) {
                    companyBranchCollectionOldCompanyBranch.setEmailId(null);
                    companyBranchCollectionOldCompanyBranch = em.merge(companyBranchCollectionOldCompanyBranch);
                }
            }
            for (CompanyBranch companyBranchCollectionNewCompanyBranch : companyBranchCollectionNew) {
                if (!companyBranchCollectionOld.contains(companyBranchCollectionNewCompanyBranch)) {
                    BranchEmail oldEmailIdOfCompanyBranchCollectionNewCompanyBranch = companyBranchCollectionNewCompanyBranch.getEmailId();
                    companyBranchCollectionNewCompanyBranch.setEmailId(branchEmail);
                    companyBranchCollectionNewCompanyBranch = em.merge(companyBranchCollectionNewCompanyBranch);
                    if (oldEmailIdOfCompanyBranchCollectionNewCompanyBranch != null && !oldEmailIdOfCompanyBranchCollectionNewCompanyBranch.equals(branchEmail)) {
                        oldEmailIdOfCompanyBranchCollectionNewCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionNewCompanyBranch);
                        oldEmailIdOfCompanyBranchCollectionNewCompanyBranch = em.merge(oldEmailIdOfCompanyBranchCollectionNewCompanyBranch);
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
                Long id = branchEmail.getEmailId();
                if (findBranchEmail(id) == null) {
                    throw new NonexistentEntityException("The branchEmail with id " + id + " no longer exists.");
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
            BranchEmail branchEmail;
            try {
                branchEmail = em.getReference(BranchEmail.class, id);
                branchEmail.getEmailId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The branchEmail with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = branchEmail.getBranchId();
            if (branchId != null) {
                branchId.setEmailId(null);
                branchId = em.merge(branchId);
            }
            Collection<CompanyBranch> companyBranchCollection = branchEmail.getCompanyBranchCollection();
            for (CompanyBranch companyBranchCollectionCompanyBranch : companyBranchCollection) {
                companyBranchCollectionCompanyBranch.setEmailId(null);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
            }
            em.remove(branchEmail);
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

    public List<BranchEmail> findBranchEmailEntities() {
        return findBranchEmailEntities(true, -1, -1);
    }

    public List<BranchEmail> findBranchEmailEntities(int maxResults, int firstResult) {
        return findBranchEmailEntities(false, maxResults, firstResult);
    }

    private List<BranchEmail> findBranchEmailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BranchEmail.class));
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

    public BranchEmail findBranchEmail(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BranchEmail.class, id);
        } finally {
            em.close();
        }
    }

    public int getBranchEmailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BranchEmail> rt = cq.from(BranchEmail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
