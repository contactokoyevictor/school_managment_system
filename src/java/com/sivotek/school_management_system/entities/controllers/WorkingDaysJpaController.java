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
import com.sivotek.school_management_system.entities.Days;
import com.sivotek.school_management_system.entities.WorkingDays;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class WorkingDaysJpaController implements Serializable {

    public WorkingDaysJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(WorkingDays workingDays) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Days dayId = workingDays.getDayId();
            if (dayId != null) {
                dayId = em.getReference(dayId.getClass(), dayId.getDayId());
                workingDays.setDayId(dayId);
            }
            em.persist(workingDays);
            if (dayId != null) {
                dayId.getWorkingDaysCollection().add(workingDays);
                dayId = em.merge(dayId);
            }
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

    public void edit(WorkingDays workingDays) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            WorkingDays persistentWorkingDays = em.find(WorkingDays.class, workingDays.getWorkingDayId());
            Days dayIdOld = persistentWorkingDays.getDayId();
            Days dayIdNew = workingDays.getDayId();
            if (dayIdNew != null) {
                dayIdNew = em.getReference(dayIdNew.getClass(), dayIdNew.getDayId());
                workingDays.setDayId(dayIdNew);
            }
            workingDays = em.merge(workingDays);
            if (dayIdOld != null && !dayIdOld.equals(dayIdNew)) {
                dayIdOld.getWorkingDaysCollection().remove(workingDays);
                dayIdOld = em.merge(dayIdOld);
            }
            if (dayIdNew != null && !dayIdNew.equals(dayIdOld)) {
                dayIdNew.getWorkingDaysCollection().add(workingDays);
                dayIdNew = em.merge(dayIdNew);
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
                Integer id = workingDays.getWorkingDayId();
                if (findWorkingDays(id) == null) {
                    throw new NonexistentEntityException("The workingDays with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            WorkingDays workingDays;
            try {
                workingDays = em.getReference(WorkingDays.class, id);
                workingDays.getWorkingDayId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The workingDays with id " + id + " no longer exists.", enfe);
            }
            Days dayId = workingDays.getDayId();
            if (dayId != null) {
                dayId.getWorkingDaysCollection().remove(workingDays);
                dayId = em.merge(dayId);
            }
            em.remove(workingDays);
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

    public List<WorkingDays> findWorkingDaysEntities() {
        return findWorkingDaysEntities(true, -1, -1);
    }

    public List<WorkingDays> findWorkingDaysEntities(int maxResults, int firstResult) {
        return findWorkingDaysEntities(false, maxResults, firstResult);
    }

    private List<WorkingDays> findWorkingDaysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(WorkingDays.class));
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

    public WorkingDays findWorkingDays(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(WorkingDays.class, id);
        } finally {
            em.close();
        }
    }

    public int getWorkingDaysCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<WorkingDays> rt = cq.from(WorkingDays.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
