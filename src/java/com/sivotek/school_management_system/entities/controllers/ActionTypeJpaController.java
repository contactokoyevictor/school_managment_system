/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.ActionType;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.AuditVault;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author acer
 */
public class ActionTypeJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(ActionTypeJpaController.class.getName());
      
    public ActionTypeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ActionTypeJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }



    public void create(ActionType actionType) throws RollbackFailureException, Exception {
        if (actionType.getAuditVaultCollection() == null) {
            actionType.setAuditVaultCollection(new ArrayList<AuditVault>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<AuditVault> attachedAuditVaultCollection = new ArrayList<AuditVault>();
            for (AuditVault auditVaultCollectionAuditVaultToAttach : actionType.getAuditVaultCollection()) {
                auditVaultCollectionAuditVaultToAttach = em.getReference(auditVaultCollectionAuditVaultToAttach.getClass(), auditVaultCollectionAuditVaultToAttach.getId());
                attachedAuditVaultCollection.add(auditVaultCollectionAuditVaultToAttach);
            }
            actionType.setAuditVaultCollection(attachedAuditVaultCollection);
            em.persist(actionType);
            for (AuditVault auditVaultCollectionAuditVault : actionType.getAuditVaultCollection()) {
                ActionType oldActionTypeIdOfAuditVaultCollectionAuditVault = auditVaultCollectionAuditVault.getActionTypeId();
                auditVaultCollectionAuditVault.setActionTypeId(actionType);
                auditVaultCollectionAuditVault = em.merge(auditVaultCollectionAuditVault);
                if (oldActionTypeIdOfAuditVaultCollectionAuditVault != null) {
                    oldActionTypeIdOfAuditVaultCollectionAuditVault.getAuditVaultCollection().remove(auditVaultCollectionAuditVault);
                    oldActionTypeIdOfAuditVaultCollectionAuditVault = em.merge(oldActionTypeIdOfAuditVaultCollectionAuditVault);
                }
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

    public void edit(ActionType actionType) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ActionType persistentActionType = em.find(ActionType.class, actionType.getId());
            Collection<AuditVault> auditVaultCollectionOld = persistentActionType.getAuditVaultCollection();
            Collection<AuditVault> auditVaultCollectionNew = actionType.getAuditVaultCollection();
            List<String> illegalOrphanMessages = null;
            for (AuditVault auditVaultCollectionOldAuditVault : auditVaultCollectionOld) {
                if (!auditVaultCollectionNew.contains(auditVaultCollectionOldAuditVault)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AuditVault " + auditVaultCollectionOldAuditVault + " since its actionTypeId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<AuditVault> attachedAuditVaultCollectionNew = new ArrayList<AuditVault>();
            for (AuditVault auditVaultCollectionNewAuditVaultToAttach : auditVaultCollectionNew) {
                auditVaultCollectionNewAuditVaultToAttach = em.getReference(auditVaultCollectionNewAuditVaultToAttach.getClass(), auditVaultCollectionNewAuditVaultToAttach.getId());
                attachedAuditVaultCollectionNew.add(auditVaultCollectionNewAuditVaultToAttach);
            }
            auditVaultCollectionNew = attachedAuditVaultCollectionNew;
            actionType.setAuditVaultCollection(auditVaultCollectionNew);
            actionType = em.merge(actionType);
            for (AuditVault auditVaultCollectionNewAuditVault : auditVaultCollectionNew) {
                if (!auditVaultCollectionOld.contains(auditVaultCollectionNewAuditVault)) {
                    ActionType oldActionTypeIdOfAuditVaultCollectionNewAuditVault = auditVaultCollectionNewAuditVault.getActionTypeId();
                    auditVaultCollectionNewAuditVault.setActionTypeId(actionType);
                    auditVaultCollectionNewAuditVault = em.merge(auditVaultCollectionNewAuditVault);
                    if (oldActionTypeIdOfAuditVaultCollectionNewAuditVault != null && !oldActionTypeIdOfAuditVaultCollectionNewAuditVault.equals(actionType)) {
                        oldActionTypeIdOfAuditVaultCollectionNewAuditVault.getAuditVaultCollection().remove(auditVaultCollectionNewAuditVault);
                        oldActionTypeIdOfAuditVaultCollectionNewAuditVault = em.merge(oldActionTypeIdOfAuditVaultCollectionNewAuditVault);
                    }
                }
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
                Long id = actionType.getId();
                if (findActionType(id) == null) {
                    throw new NonexistentEntityException("The actionType with id " + id + " no longer exists.");
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
            em = getEntityManager();
            em.getTransaction().begin();
            ActionType actionType;
            try {
                actionType = em.getReference(ActionType.class, id);
                actionType.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The actionType with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<AuditVault> auditVaultCollectionOrphanCheck = actionType.getAuditVaultCollection();
            for (AuditVault auditVaultCollectionOrphanCheckAuditVault : auditVaultCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ActionType (" + actionType + ") cannot be destroyed since the AuditVault " + auditVaultCollectionOrphanCheckAuditVault + " in its auditVaultCollection field has a non-nullable actionTypeId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(actionType);
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

    public List<ActionType> findActionTypeEntities() {
        return findActionTypeEntities(true, -1, -1);
    }

    public List<ActionType> findActionTypeEntities(int maxResults, int firstResult) {
        return findActionTypeEntities(false, maxResults, firstResult);
    }

    private List<ActionType> findActionTypeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ActionType.class));
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

    public ActionType findActionType(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ActionType.class, id);
        } finally {
            em.close();
        }
    }

    public int getActionTypeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ActionType> rt = cq.from(ActionType.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public ActionType findByName(String name) 
    {
        EntityManager em = getEntityManager();
        try {
            Query findByName = em.createNamedQuery("ActionType.findByName");
            findByName.setParameter("name", name);
            ActionType actionType = null;
            actionType = (ActionType) findByName.getSingleResult();
            return actionType;
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             ActionType actionType = new ActionType();
             return actionType;
        } finally {
            em.close();
        }
    }
}
