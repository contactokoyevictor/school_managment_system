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
import com.sivotek.school_management_system.entities.ScratchCardSalesOrder;
import com.sivotek.school_management_system.entities.ScratchCardSalesOrderDetails;
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
public class ScratchCardSalesOrderDetailsJpaController implements Serializable {

    public ScratchCardSalesOrderDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ScratchCardSalesOrderDetails scratchCardSalesOrderDetails) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = scratchCardSalesOrderDetails.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                scratchCardSalesOrderDetails.setBranchId(branchId);
            }
            ScratchCardSalesOrder salesId = scratchCardSalesOrderDetails.getSalesId();
            if (salesId != null) {
                salesId = em.getReference(salesId.getClass(), salesId.getId());
                scratchCardSalesOrderDetails.setSalesId(salesId);
            }
            em.persist(scratchCardSalesOrderDetails);
            if (branchId != null) {
                branchId.getScratchCardSalesOrderDetailsCollection().add(scratchCardSalesOrderDetails);
                branchId = em.merge(branchId);
            }
            if (salesId != null) {
                salesId.getScratchCardSalesOrderDetailsCollection().add(scratchCardSalesOrderDetails);
                salesId = em.merge(salesId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findScratchCardSalesOrderDetails(scratchCardSalesOrderDetails.getId()) != null) {
                throw new PreexistingEntityException("ScratchCardSalesOrderDetails " + scratchCardSalesOrderDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ScratchCardSalesOrderDetails scratchCardSalesOrderDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ScratchCardSalesOrderDetails persistentScratchCardSalesOrderDetails = em.find(ScratchCardSalesOrderDetails.class, scratchCardSalesOrderDetails.getId());
            CompanyBranch branchIdOld = persistentScratchCardSalesOrderDetails.getBranchId();
            CompanyBranch branchIdNew = scratchCardSalesOrderDetails.getBranchId();
            ScratchCardSalesOrder salesIdOld = persistentScratchCardSalesOrderDetails.getSalesId();
            ScratchCardSalesOrder salesIdNew = scratchCardSalesOrderDetails.getSalesId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                scratchCardSalesOrderDetails.setBranchId(branchIdNew);
            }
            if (salesIdNew != null) {
                salesIdNew = em.getReference(salesIdNew.getClass(), salesIdNew.getId());
                scratchCardSalesOrderDetails.setSalesId(salesIdNew);
            }
            scratchCardSalesOrderDetails = em.merge(scratchCardSalesOrderDetails);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetails);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getScratchCardSalesOrderDetailsCollection().add(scratchCardSalesOrderDetails);
                branchIdNew = em.merge(branchIdNew);
            }
            if (salesIdOld != null && !salesIdOld.equals(salesIdNew)) {
                salesIdOld.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetails);
                salesIdOld = em.merge(salesIdOld);
            }
            if (salesIdNew != null && !salesIdNew.equals(salesIdOld)) {
                salesIdNew.getScratchCardSalesOrderDetailsCollection().add(scratchCardSalesOrderDetails);
                salesIdNew = em.merge(salesIdNew);
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
                Long id = scratchCardSalesOrderDetails.getId();
                if (findScratchCardSalesOrderDetails(id) == null) {
                    throw new NonexistentEntityException("The scratchCardSalesOrderDetails with id " + id + " no longer exists.");
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
            ScratchCardSalesOrderDetails scratchCardSalesOrderDetails;
            try {
                scratchCardSalesOrderDetails = em.getReference(ScratchCardSalesOrderDetails.class, id);
                scratchCardSalesOrderDetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The scratchCardSalesOrderDetails with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = scratchCardSalesOrderDetails.getBranchId();
            if (branchId != null) {
                branchId.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetails);
                branchId = em.merge(branchId);
            }
            ScratchCardSalesOrder salesId = scratchCardSalesOrderDetails.getSalesId();
            if (salesId != null) {
                salesId.getScratchCardSalesOrderDetailsCollection().remove(scratchCardSalesOrderDetails);
                salesId = em.merge(salesId);
            }
            em.remove(scratchCardSalesOrderDetails);
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

    public List<ScratchCardSalesOrderDetails> findScratchCardSalesOrderDetailsEntities() {
        return findScratchCardSalesOrderDetailsEntities(true, -1, -1);
    }

    public List<ScratchCardSalesOrderDetails> findScratchCardSalesOrderDetailsEntities(int maxResults, int firstResult) {
        return findScratchCardSalesOrderDetailsEntities(false, maxResults, firstResult);
    }

    private List<ScratchCardSalesOrderDetails> findScratchCardSalesOrderDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ScratchCardSalesOrderDetails.class));
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

    public ScratchCardSalesOrderDetails findScratchCardSalesOrderDetails(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ScratchCardSalesOrderDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getScratchCardSalesOrderDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ScratchCardSalesOrderDetails> rt = cq.from(ScratchCardSalesOrderDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
