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
import com.sivotek.school_management_system.entities.ActionType;
import com.sivotek.school_management_system.entities.AuditVault;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author acer
 */
public class AuditVaultJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(AuditVaultJpaController.class.getName());
    public AuditVaultJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AuditVaultJpaController()
    {
        try{
            emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }catch(Exception ex){
            log.log(Level.ERROR, "----Error occoured during JNDI loopup----:{0} "+new Date(), ex.getCause());
        }
    }

    public void create(AuditVault auditVault) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = auditVault.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                auditVault.setBranchId(branchId);
            }
            ActionType actionTypeId = auditVault.getActionTypeId();
            if (actionTypeId != null) {
                actionTypeId = em.getReference(actionTypeId.getClass(), actionTypeId.getId());
                auditVault.setActionTypeId(actionTypeId);
            }
            em.persist(auditVault);
            if (branchId != null) {
                branchId.getAuditVaultCollection().add(auditVault);
                branchId = em.merge(branchId);
            }
            if (actionTypeId != null) {
                actionTypeId.getAuditVaultCollection().add(auditVault);
                actionTypeId = em.merge(actionTypeId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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

    public void edit(AuditVault auditVault) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AuditVault persistentAuditVault = em.find(AuditVault.class, auditVault.getId());
            CompanyBranch branchIdOld = persistentAuditVault.getBranchId();
            CompanyBranch branchIdNew = auditVault.getBranchId();
            ActionType actionTypeIdOld = persistentAuditVault.getActionTypeId();
            ActionType actionTypeIdNew = auditVault.getActionTypeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                auditVault.setBranchId(branchIdNew);
            }
            if (actionTypeIdNew != null) {
                actionTypeIdNew = em.getReference(actionTypeIdNew.getClass(), actionTypeIdNew.getId());
                auditVault.setActionTypeId(actionTypeIdNew);
            }
            auditVault = em.merge(auditVault);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getAuditVaultCollection().remove(auditVault);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getAuditVaultCollection().add(auditVault);
                branchIdNew = em.merge(branchIdNew);
            }
            if (actionTypeIdOld != null && !actionTypeIdOld.equals(actionTypeIdNew)) {
                actionTypeIdOld.getAuditVaultCollection().remove(auditVault);
                actionTypeIdOld = em.merge(actionTypeIdOld);
            }
            if (actionTypeIdNew != null && !actionTypeIdNew.equals(actionTypeIdOld)) {
                actionTypeIdNew.getAuditVaultCollection().add(auditVault);
                actionTypeIdNew = em.merge(actionTypeIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = auditVault.getId();
                if (findAuditVault(id) == null) {
                    throw new NonexistentEntityException("The auditVault with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AuditVault auditVault;
            try {
                auditVault = em.getReference(AuditVault.class, id);
                auditVault.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auditVault with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = auditVault.getBranchId();
            if (branchId != null) {
                branchId.getAuditVaultCollection().remove(auditVault);
                branchId = em.merge(branchId);
            }
            ActionType actionTypeId = auditVault.getActionTypeId();
            if (actionTypeId != null) {
                actionTypeId.getAuditVaultCollection().remove(auditVault);
                actionTypeId = em.merge(actionTypeId);
            }
            em.remove(auditVault);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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

    public List<AuditVault> findAuditVaultEntities() {
        return findAuditVaultEntities(true, -1, -1);
    }

    public List<AuditVault> findAuditVaultEntities(int maxResults, int firstResult) {
        return findAuditVaultEntities(false, maxResults, firstResult);
    }

    private List<AuditVault> findAuditVaultEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuditVault.class));
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

    public AuditVault findAuditVault(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuditVault.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuditVaultCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuditVault> rt = cq.from(AuditVault.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
