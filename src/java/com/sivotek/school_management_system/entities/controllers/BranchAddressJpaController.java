/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.BranchAddress;
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
public class BranchAddressJpaController implements Serializable {

    public BranchAddressJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BranchAddress branchAddress) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (branchAddress.getCompanyBranchCollection() == null) {
            branchAddress.setCompanyBranchCollection(new ArrayList<CompanyBranch>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = branchAddress.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                branchAddress.setBranchId(branchId);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollection = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionCompanyBranchToAttach : branchAddress.getCompanyBranchCollection()) {
                companyBranchCollectionCompanyBranchToAttach = em.getReference(companyBranchCollectionCompanyBranchToAttach.getClass(), companyBranchCollectionCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollection.add(companyBranchCollectionCompanyBranchToAttach);
            }
            branchAddress.setCompanyBranchCollection(attachedCompanyBranchCollection);
            em.persist(branchAddress);
            if (branchId != null) {
                BranchAddress oldAddressIdOfBranchId = branchId.getAddressId();
                if (oldAddressIdOfBranchId != null) {
                    oldAddressIdOfBranchId.setBranchId(null);
                    oldAddressIdOfBranchId = em.merge(oldAddressIdOfBranchId);
                }
                branchId.setAddressId(branchAddress);
                branchId = em.merge(branchId);
            }
            for (CompanyBranch companyBranchCollectionCompanyBranch : branchAddress.getCompanyBranchCollection()) {
                BranchAddress oldAddressIdOfCompanyBranchCollectionCompanyBranch = companyBranchCollectionCompanyBranch.getAddressId();
                companyBranchCollectionCompanyBranch.setAddressId(branchAddress);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
                if (oldAddressIdOfCompanyBranchCollectionCompanyBranch != null) {
                    oldAddressIdOfCompanyBranchCollectionCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionCompanyBranch);
                    oldAddressIdOfCompanyBranchCollectionCompanyBranch = em.merge(oldAddressIdOfCompanyBranchCollectionCompanyBranch);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBranchAddress(branchAddress.getAddressId()) != null) {
                throw new PreexistingEntityException("BranchAddress " + branchAddress + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BranchAddress branchAddress) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BranchAddress persistentBranchAddress = em.find(BranchAddress.class, branchAddress.getAddressId());
            CompanyBranch branchIdOld = persistentBranchAddress.getBranchId();
            CompanyBranch branchIdNew = branchAddress.getBranchId();
            Collection<CompanyBranch> companyBranchCollectionOld = persistentBranchAddress.getCompanyBranchCollection();
            Collection<CompanyBranch> companyBranchCollectionNew = branchAddress.getCompanyBranchCollection();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                branchAddress.setBranchId(branchIdNew);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollectionNew = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionNewCompanyBranchToAttach : companyBranchCollectionNew) {
                companyBranchCollectionNewCompanyBranchToAttach = em.getReference(companyBranchCollectionNewCompanyBranchToAttach.getClass(), companyBranchCollectionNewCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollectionNew.add(companyBranchCollectionNewCompanyBranchToAttach);
            }
            companyBranchCollectionNew = attachedCompanyBranchCollectionNew;
            branchAddress.setCompanyBranchCollection(companyBranchCollectionNew);
            branchAddress = em.merge(branchAddress);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.setAddressId(null);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                BranchAddress oldAddressIdOfBranchId = branchIdNew.getAddressId();
                if (oldAddressIdOfBranchId != null) {
                    oldAddressIdOfBranchId.setBranchId(null);
                    oldAddressIdOfBranchId = em.merge(oldAddressIdOfBranchId);
                }
                branchIdNew.setAddressId(branchAddress);
                branchIdNew = em.merge(branchIdNew);
            }
            for (CompanyBranch companyBranchCollectionOldCompanyBranch : companyBranchCollectionOld) {
                if (!companyBranchCollectionNew.contains(companyBranchCollectionOldCompanyBranch)) {
                    companyBranchCollectionOldCompanyBranch.setAddressId(null);
                    companyBranchCollectionOldCompanyBranch = em.merge(companyBranchCollectionOldCompanyBranch);
                }
            }
            for (CompanyBranch companyBranchCollectionNewCompanyBranch : companyBranchCollectionNew) {
                if (!companyBranchCollectionOld.contains(companyBranchCollectionNewCompanyBranch)) {
                    BranchAddress oldAddressIdOfCompanyBranchCollectionNewCompanyBranch = companyBranchCollectionNewCompanyBranch.getAddressId();
                    companyBranchCollectionNewCompanyBranch.setAddressId(branchAddress);
                    companyBranchCollectionNewCompanyBranch = em.merge(companyBranchCollectionNewCompanyBranch);
                    if (oldAddressIdOfCompanyBranchCollectionNewCompanyBranch != null && !oldAddressIdOfCompanyBranchCollectionNewCompanyBranch.equals(branchAddress)) {
                        oldAddressIdOfCompanyBranchCollectionNewCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionNewCompanyBranch);
                        oldAddressIdOfCompanyBranchCollectionNewCompanyBranch = em.merge(oldAddressIdOfCompanyBranchCollectionNewCompanyBranch);
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
                Long id = branchAddress.getAddressId();
                if (findBranchAddress(id) == null) {
                    throw new NonexistentEntityException("The branchAddress with id " + id + " no longer exists.");
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
            BranchAddress branchAddress;
            try {
                branchAddress = em.getReference(BranchAddress.class, id);
                branchAddress.getAddressId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The branchAddress with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = branchAddress.getBranchId();
            if (branchId != null) {
                branchId.setAddressId(null);
                branchId = em.merge(branchId);
            }
            Collection<CompanyBranch> companyBranchCollection = branchAddress.getCompanyBranchCollection();
            for (CompanyBranch companyBranchCollectionCompanyBranch : companyBranchCollection) {
                companyBranchCollectionCompanyBranch.setAddressId(null);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
            }
            em.remove(branchAddress);
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

    public List<BranchAddress> findBranchAddressEntities() {
        return findBranchAddressEntities(true, -1, -1);
    }

    public List<BranchAddress> findBranchAddressEntities(int maxResults, int firstResult) {
        return findBranchAddressEntities(false, maxResults, firstResult);
    }

    private List<BranchAddress> findBranchAddressEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BranchAddress.class));
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

    public BranchAddress findBranchAddress(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BranchAddress.class, id);
        } finally {
            em.close();
        }
    }

    public int getBranchAddressCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BranchAddress> rt = cq.from(BranchAddress.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
