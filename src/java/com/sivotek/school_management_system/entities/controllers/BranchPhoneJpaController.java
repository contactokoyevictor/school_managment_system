/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.BranchPhone;
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
public class BranchPhoneJpaController implements Serializable {

    public BranchPhoneJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BranchPhone branchPhone) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (branchPhone.getCompanyBranchCollection() == null) {
            branchPhone.setCompanyBranchCollection(new ArrayList<CompanyBranch>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = branchPhone.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                branchPhone.setBranchId(branchId);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollection = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionCompanyBranchToAttach : branchPhone.getCompanyBranchCollection()) {
                companyBranchCollectionCompanyBranchToAttach = em.getReference(companyBranchCollectionCompanyBranchToAttach.getClass(), companyBranchCollectionCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollection.add(companyBranchCollectionCompanyBranchToAttach);
            }
            branchPhone.setCompanyBranchCollection(attachedCompanyBranchCollection);
            em.persist(branchPhone);
            if (branchId != null) {
                BranchPhone oldPhoneIdOfBranchId = branchId.getPhoneId();
                if (oldPhoneIdOfBranchId != null) {
                    oldPhoneIdOfBranchId.setBranchId(null);
                    oldPhoneIdOfBranchId = em.merge(oldPhoneIdOfBranchId);
                }
                branchId.setPhoneId(branchPhone);
                branchId = em.merge(branchId);
            }
            for (CompanyBranch companyBranchCollectionCompanyBranch : branchPhone.getCompanyBranchCollection()) {
                BranchPhone oldPhoneIdOfCompanyBranchCollectionCompanyBranch = companyBranchCollectionCompanyBranch.getPhoneId();
                companyBranchCollectionCompanyBranch.setPhoneId(branchPhone);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
                if (oldPhoneIdOfCompanyBranchCollectionCompanyBranch != null) {
                    oldPhoneIdOfCompanyBranchCollectionCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionCompanyBranch);
                    oldPhoneIdOfCompanyBranchCollectionCompanyBranch = em.merge(oldPhoneIdOfCompanyBranchCollectionCompanyBranch);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBranchPhone(branchPhone.getPhoneId()) != null) {
                throw new PreexistingEntityException("BranchPhone " + branchPhone + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BranchPhone branchPhone) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BranchPhone persistentBranchPhone = em.find(BranchPhone.class, branchPhone.getPhoneId());
            CompanyBranch branchIdOld = persistentBranchPhone.getBranchId();
            CompanyBranch branchIdNew = branchPhone.getBranchId();
            Collection<CompanyBranch> companyBranchCollectionOld = persistentBranchPhone.getCompanyBranchCollection();
            Collection<CompanyBranch> companyBranchCollectionNew = branchPhone.getCompanyBranchCollection();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                branchPhone.setBranchId(branchIdNew);
            }
            Collection<CompanyBranch> attachedCompanyBranchCollectionNew = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionNewCompanyBranchToAttach : companyBranchCollectionNew) {
                companyBranchCollectionNewCompanyBranchToAttach = em.getReference(companyBranchCollectionNewCompanyBranchToAttach.getClass(), companyBranchCollectionNewCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollectionNew.add(companyBranchCollectionNewCompanyBranchToAttach);
            }
            companyBranchCollectionNew = attachedCompanyBranchCollectionNew;
            branchPhone.setCompanyBranchCollection(companyBranchCollectionNew);
            branchPhone = em.merge(branchPhone);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.setPhoneId(null);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                BranchPhone oldPhoneIdOfBranchId = branchIdNew.getPhoneId();
                if (oldPhoneIdOfBranchId != null) {
                    oldPhoneIdOfBranchId.setBranchId(null);
                    oldPhoneIdOfBranchId = em.merge(oldPhoneIdOfBranchId);
                }
                branchIdNew.setPhoneId(branchPhone);
                branchIdNew = em.merge(branchIdNew);
            }
            for (CompanyBranch companyBranchCollectionOldCompanyBranch : companyBranchCollectionOld) {
                if (!companyBranchCollectionNew.contains(companyBranchCollectionOldCompanyBranch)) {
                    companyBranchCollectionOldCompanyBranch.setPhoneId(null);
                    companyBranchCollectionOldCompanyBranch = em.merge(companyBranchCollectionOldCompanyBranch);
                }
            }
            for (CompanyBranch companyBranchCollectionNewCompanyBranch : companyBranchCollectionNew) {
                if (!companyBranchCollectionOld.contains(companyBranchCollectionNewCompanyBranch)) {
                    BranchPhone oldPhoneIdOfCompanyBranchCollectionNewCompanyBranch = companyBranchCollectionNewCompanyBranch.getPhoneId();
                    companyBranchCollectionNewCompanyBranch.setPhoneId(branchPhone);
                    companyBranchCollectionNewCompanyBranch = em.merge(companyBranchCollectionNewCompanyBranch);
                    if (oldPhoneIdOfCompanyBranchCollectionNewCompanyBranch != null && !oldPhoneIdOfCompanyBranchCollectionNewCompanyBranch.equals(branchPhone)) {
                        oldPhoneIdOfCompanyBranchCollectionNewCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionNewCompanyBranch);
                        oldPhoneIdOfCompanyBranchCollectionNewCompanyBranch = em.merge(oldPhoneIdOfCompanyBranchCollectionNewCompanyBranch);
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
                Long id = branchPhone.getPhoneId();
                if (findBranchPhone(id) == null) {
                    throw new NonexistentEntityException("The branchPhone with id " + id + " no longer exists.");
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
            BranchPhone branchPhone;
            try {
                branchPhone = em.getReference(BranchPhone.class, id);
                branchPhone.getPhoneId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The branchPhone with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = branchPhone.getBranchId();
            if (branchId != null) {
                branchId.setPhoneId(null);
                branchId = em.merge(branchId);
            }
            Collection<CompanyBranch> companyBranchCollection = branchPhone.getCompanyBranchCollection();
            for (CompanyBranch companyBranchCollectionCompanyBranch : companyBranchCollection) {
                companyBranchCollectionCompanyBranch.setPhoneId(null);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
            }
            em.remove(branchPhone);
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

    public List<BranchPhone> findBranchPhoneEntities() {
        return findBranchPhoneEntities(true, -1, -1);
    }

    public List<BranchPhone> findBranchPhoneEntities(int maxResults, int firstResult) {
        return findBranchPhoneEntities(false, maxResults, firstResult);
    }

    private List<BranchPhone> findBranchPhoneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BranchPhone.class));
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

    public BranchPhone findBranchPhone(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BranchPhone.class, id);
        } finally {
            em.close();
        }
    }

    public int getBranchPhoneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BranchPhone> rt = cq.from(BranchPhone.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
