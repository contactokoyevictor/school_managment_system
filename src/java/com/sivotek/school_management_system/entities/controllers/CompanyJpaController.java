/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.Company;
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
public class CompanyJpaController implements Serializable {

    public CompanyJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Company company) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (company.getCompanyBranchCollection() == null) {
            company.setCompanyBranchCollection(new ArrayList<CompanyBranch>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<CompanyBranch> attachedCompanyBranchCollection = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionCompanyBranchToAttach : company.getCompanyBranchCollection()) {
                companyBranchCollectionCompanyBranchToAttach = em.getReference(companyBranchCollectionCompanyBranchToAttach.getClass(), companyBranchCollectionCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollection.add(companyBranchCollectionCompanyBranchToAttach);
            }
            company.setCompanyBranchCollection(attachedCompanyBranchCollection);
            em.persist(company);
            for (CompanyBranch companyBranchCollectionCompanyBranch : company.getCompanyBranchCollection()) {
                Company oldCompanyIdOfCompanyBranchCollectionCompanyBranch = companyBranchCollectionCompanyBranch.getCompanyId();
                companyBranchCollectionCompanyBranch.setCompanyId(company);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
                if (oldCompanyIdOfCompanyBranchCollectionCompanyBranch != null) {
                    oldCompanyIdOfCompanyBranchCollectionCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionCompanyBranch);
                    oldCompanyIdOfCompanyBranchCollectionCompanyBranch = em.merge(oldCompanyIdOfCompanyBranchCollectionCompanyBranch);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCompany(company.getCompanyId()) != null) {
                throw new PreexistingEntityException("Company " + company + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Company company) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Company persistentCompany = em.find(Company.class, company.getCompanyId());
            Collection<CompanyBranch> companyBranchCollectionOld = persistentCompany.getCompanyBranchCollection();
            Collection<CompanyBranch> companyBranchCollectionNew = company.getCompanyBranchCollection();
            Collection<CompanyBranch> attachedCompanyBranchCollectionNew = new ArrayList<CompanyBranch>();
            for (CompanyBranch companyBranchCollectionNewCompanyBranchToAttach : companyBranchCollectionNew) {
                companyBranchCollectionNewCompanyBranchToAttach = em.getReference(companyBranchCollectionNewCompanyBranchToAttach.getClass(), companyBranchCollectionNewCompanyBranchToAttach.getBranchId());
                attachedCompanyBranchCollectionNew.add(companyBranchCollectionNewCompanyBranchToAttach);
            }
            companyBranchCollectionNew = attachedCompanyBranchCollectionNew;
            company.setCompanyBranchCollection(companyBranchCollectionNew);
            company = em.merge(company);
            for (CompanyBranch companyBranchCollectionOldCompanyBranch : companyBranchCollectionOld) {
                if (!companyBranchCollectionNew.contains(companyBranchCollectionOldCompanyBranch)) {
                    companyBranchCollectionOldCompanyBranch.setCompanyId(null);
                    companyBranchCollectionOldCompanyBranch = em.merge(companyBranchCollectionOldCompanyBranch);
                }
            }
            for (CompanyBranch companyBranchCollectionNewCompanyBranch : companyBranchCollectionNew) {
                if (!companyBranchCollectionOld.contains(companyBranchCollectionNewCompanyBranch)) {
                    Company oldCompanyIdOfCompanyBranchCollectionNewCompanyBranch = companyBranchCollectionNewCompanyBranch.getCompanyId();
                    companyBranchCollectionNewCompanyBranch.setCompanyId(company);
                    companyBranchCollectionNewCompanyBranch = em.merge(companyBranchCollectionNewCompanyBranch);
                    if (oldCompanyIdOfCompanyBranchCollectionNewCompanyBranch != null && !oldCompanyIdOfCompanyBranchCollectionNewCompanyBranch.equals(company)) {
                        oldCompanyIdOfCompanyBranchCollectionNewCompanyBranch.getCompanyBranchCollection().remove(companyBranchCollectionNewCompanyBranch);
                        oldCompanyIdOfCompanyBranchCollectionNewCompanyBranch = em.merge(oldCompanyIdOfCompanyBranchCollectionNewCompanyBranch);
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
                Long id = company.getCompanyId();
                if (findCompany(id) == null) {
                    throw new NonexistentEntityException("The company with id " + id + " no longer exists.");
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
            Company company;
            try {
                company = em.getReference(Company.class, id);
                company.getCompanyId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The company with id " + id + " no longer exists.", enfe);
            }
            Collection<CompanyBranch> companyBranchCollection = company.getCompanyBranchCollection();
            for (CompanyBranch companyBranchCollectionCompanyBranch : companyBranchCollection) {
                companyBranchCollectionCompanyBranch.setCompanyId(null);
                companyBranchCollectionCompanyBranch = em.merge(companyBranchCollectionCompanyBranch);
            }
            em.remove(company);
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

    public List<Company> findCompanyEntities() {
        return findCompanyEntities(true, -1, -1);
    }

    public List<Company> findCompanyEntities(int maxResults, int firstResult) {
        return findCompanyEntities(false, maxResults, firstResult);
    }

    private List<Company> findCompanyEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Company.class));
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

    public Company findCompany(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Company.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompanyCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Company> rt = cq.from(Company.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
