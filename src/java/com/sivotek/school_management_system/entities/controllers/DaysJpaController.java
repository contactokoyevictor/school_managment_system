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
import com.sivotek.school_management_system.entities.WorkingDays;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.Days;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
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
public class DaysJpaController implements Serializable {

    public DaysJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Days days) throws RollbackFailureException, Exception {
        if (days.getWorkingDaysCollection() == null) {
            days.setWorkingDaysCollection(new ArrayList<WorkingDays>());
        }
        if (days.getClassRoutineCollection() == null) {
            days.setClassRoutineCollection(new ArrayList<ClassRoutine>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<WorkingDays> attachedWorkingDaysCollection = new ArrayList<WorkingDays>();
            for (WorkingDays workingDaysCollectionWorkingDaysToAttach : days.getWorkingDaysCollection()) {
                workingDaysCollectionWorkingDaysToAttach = em.getReference(workingDaysCollectionWorkingDaysToAttach.getClass(), workingDaysCollectionWorkingDaysToAttach.getWorkingDayId());
                attachedWorkingDaysCollection.add(workingDaysCollectionWorkingDaysToAttach);
            }
            days.setWorkingDaysCollection(attachedWorkingDaysCollection);
            Collection<ClassRoutine> attachedClassRoutineCollection = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionClassRoutineToAttach : days.getClassRoutineCollection()) {
                classRoutineCollectionClassRoutineToAttach = em.getReference(classRoutineCollectionClassRoutineToAttach.getClass(), classRoutineCollectionClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollection.add(classRoutineCollectionClassRoutineToAttach);
            }
            days.setClassRoutineCollection(attachedClassRoutineCollection);
            em.persist(days);
            for (WorkingDays workingDaysCollectionWorkingDays : days.getWorkingDaysCollection()) {
                Days oldDayIdOfWorkingDaysCollectionWorkingDays = workingDaysCollectionWorkingDays.getDayId();
                workingDaysCollectionWorkingDays.setDayId(days);
                workingDaysCollectionWorkingDays = em.merge(workingDaysCollectionWorkingDays);
                if (oldDayIdOfWorkingDaysCollectionWorkingDays != null) {
                    oldDayIdOfWorkingDaysCollectionWorkingDays.getWorkingDaysCollection().remove(workingDaysCollectionWorkingDays);
                    oldDayIdOfWorkingDaysCollectionWorkingDays = em.merge(oldDayIdOfWorkingDaysCollectionWorkingDays);
                }
            }
            for (ClassRoutine classRoutineCollectionClassRoutine : days.getClassRoutineCollection()) {
                Days oldDayOfClassRoutineCollectionClassRoutine = classRoutineCollectionClassRoutine.getDay();
                classRoutineCollectionClassRoutine.setDay(days);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
                if (oldDayOfClassRoutineCollectionClassRoutine != null) {
                    oldDayOfClassRoutineCollectionClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionClassRoutine);
                    oldDayOfClassRoutineCollectionClassRoutine = em.merge(oldDayOfClassRoutineCollectionClassRoutine);
                }
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

    public void edit(Days days) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Days persistentDays = em.find(Days.class, days.getDayId());
            Collection<WorkingDays> workingDaysCollectionOld = persistentDays.getWorkingDaysCollection();
            Collection<WorkingDays> workingDaysCollectionNew = days.getWorkingDaysCollection();
            Collection<ClassRoutine> classRoutineCollectionOld = persistentDays.getClassRoutineCollection();
            Collection<ClassRoutine> classRoutineCollectionNew = days.getClassRoutineCollection();
            List<String> illegalOrphanMessages = null;
            for (WorkingDays workingDaysCollectionOldWorkingDays : workingDaysCollectionOld) {
                if (!workingDaysCollectionNew.contains(workingDaysCollectionOldWorkingDays)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain WorkingDays " + workingDaysCollectionOldWorkingDays + " since its dayId field is not nullable.");
                }
            }
            for (ClassRoutine classRoutineCollectionOldClassRoutine : classRoutineCollectionOld) {
                if (!classRoutineCollectionNew.contains(classRoutineCollectionOldClassRoutine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassRoutine " + classRoutineCollectionOldClassRoutine + " since its day field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<WorkingDays> attachedWorkingDaysCollectionNew = new ArrayList<WorkingDays>();
            for (WorkingDays workingDaysCollectionNewWorkingDaysToAttach : workingDaysCollectionNew) {
                workingDaysCollectionNewWorkingDaysToAttach = em.getReference(workingDaysCollectionNewWorkingDaysToAttach.getClass(), workingDaysCollectionNewWorkingDaysToAttach.getWorkingDayId());
                attachedWorkingDaysCollectionNew.add(workingDaysCollectionNewWorkingDaysToAttach);
            }
            workingDaysCollectionNew = attachedWorkingDaysCollectionNew;
            days.setWorkingDaysCollection(workingDaysCollectionNew);
            Collection<ClassRoutine> attachedClassRoutineCollectionNew = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionNewClassRoutineToAttach : classRoutineCollectionNew) {
                classRoutineCollectionNewClassRoutineToAttach = em.getReference(classRoutineCollectionNewClassRoutineToAttach.getClass(), classRoutineCollectionNewClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollectionNew.add(classRoutineCollectionNewClassRoutineToAttach);
            }
            classRoutineCollectionNew = attachedClassRoutineCollectionNew;
            days.setClassRoutineCollection(classRoutineCollectionNew);
            days = em.merge(days);
            for (WorkingDays workingDaysCollectionNewWorkingDays : workingDaysCollectionNew) {
                if (!workingDaysCollectionOld.contains(workingDaysCollectionNewWorkingDays)) {
                    Days oldDayIdOfWorkingDaysCollectionNewWorkingDays = workingDaysCollectionNewWorkingDays.getDayId();
                    workingDaysCollectionNewWorkingDays.setDayId(days);
                    workingDaysCollectionNewWorkingDays = em.merge(workingDaysCollectionNewWorkingDays);
                    if (oldDayIdOfWorkingDaysCollectionNewWorkingDays != null && !oldDayIdOfWorkingDaysCollectionNewWorkingDays.equals(days)) {
                        oldDayIdOfWorkingDaysCollectionNewWorkingDays.getWorkingDaysCollection().remove(workingDaysCollectionNewWorkingDays);
                        oldDayIdOfWorkingDaysCollectionNewWorkingDays = em.merge(oldDayIdOfWorkingDaysCollectionNewWorkingDays);
                    }
                }
            }
            for (ClassRoutine classRoutineCollectionNewClassRoutine : classRoutineCollectionNew) {
                if (!classRoutineCollectionOld.contains(classRoutineCollectionNewClassRoutine)) {
                    Days oldDayOfClassRoutineCollectionNewClassRoutine = classRoutineCollectionNewClassRoutine.getDay();
                    classRoutineCollectionNewClassRoutine.setDay(days);
                    classRoutineCollectionNewClassRoutine = em.merge(classRoutineCollectionNewClassRoutine);
                    if (oldDayOfClassRoutineCollectionNewClassRoutine != null && !oldDayOfClassRoutineCollectionNewClassRoutine.equals(days)) {
                        oldDayOfClassRoutineCollectionNewClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionNewClassRoutine);
                        oldDayOfClassRoutineCollectionNewClassRoutine = em.merge(oldDayOfClassRoutineCollectionNewClassRoutine);
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
                Integer id = days.getDayId();
                if (findDays(id) == null) {
                    throw new NonexistentEntityException("The days with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Days days;
            try {
                days = em.getReference(Days.class, id);
                days.getDayId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The days with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<WorkingDays> workingDaysCollectionOrphanCheck = days.getWorkingDaysCollection();
            for (WorkingDays workingDaysCollectionOrphanCheckWorkingDays : workingDaysCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Days (" + days + ") cannot be destroyed since the WorkingDays " + workingDaysCollectionOrphanCheckWorkingDays + " in its workingDaysCollection field has a non-nullable dayId field.");
            }
            Collection<ClassRoutine> classRoutineCollectionOrphanCheck = days.getClassRoutineCollection();
            for (ClassRoutine classRoutineCollectionOrphanCheckClassRoutine : classRoutineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Days (" + days + ") cannot be destroyed since the ClassRoutine " + classRoutineCollectionOrphanCheckClassRoutine + " in its classRoutineCollection field has a non-nullable day field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(days);
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

    public List<Days> findDaysEntities() {
        return findDaysEntities(true, -1, -1);
    }

    public List<Days> findDaysEntities(int maxResults, int firstResult) {
        return findDaysEntities(false, maxResults, firstResult);
    }

    private List<Days> findDaysEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Days.class));
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

    public Days findDays(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Days.class, id);
        } finally {
            em.close();
        }
    }

    public int getDaysCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Days> rt = cq.from(Days.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
