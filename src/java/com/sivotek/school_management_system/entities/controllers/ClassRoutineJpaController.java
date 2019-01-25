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
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.Days;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.SubjectAttendance;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
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
public class ClassRoutineJpaController implements Serializable {

    public ClassRoutineJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClassRoutine classRoutine) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (classRoutine.getSubjectAttendanceCollection() == null) {
            classRoutine.setSubjectAttendanceCollection(new ArrayList<SubjectAttendance>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Class classId = classRoutine.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                classRoutine.setClassId(classId);
            }
            Term termId = classRoutine.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                classRoutine.setTermId(termId);
            }
            Section sectionId = classRoutine.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                classRoutine.setSectionId(sectionId);
            }
            Subject subjectId = classRoutine.getSubjectId();
            if (subjectId != null) {
                subjectId = em.getReference(subjectId.getClass(), subjectId.getSubjectId());
                classRoutine.setSubjectId(subjectId);
            }
            Days day = classRoutine.getDay();
            if (day != null) {
                day = em.getReference(day.getClass(), day.getDayId());
                classRoutine.setDay(day);
            }
            CompanyBranch branchId = classRoutine.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                classRoutine.setBranchId(branchId);
            }
            Collection<SubjectAttendance> attachedSubjectAttendanceCollection = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendanceToAttach : classRoutine.getSubjectAttendanceCollection()) {
                subjectAttendanceCollectionSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollection.add(subjectAttendanceCollectionSubjectAttendanceToAttach);
            }
            classRoutine.setSubjectAttendanceCollection(attachedSubjectAttendanceCollection);
            em.persist(classRoutine);
            if (classId != null) {
                classId.getClassRoutineCollection().add(classRoutine);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getClassRoutineCollection().add(classRoutine);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getClassRoutineCollection().add(classRoutine);
                sectionId = em.merge(sectionId);
            }
            if (subjectId != null) {
                subjectId.getClassRoutineCollection().add(classRoutine);
                subjectId = em.merge(subjectId);
            }
            if (day != null) {
                day.getClassRoutineCollection().add(classRoutine);
                day = em.merge(day);
            }
            if (branchId != null) {
                branchId.getClassRoutineCollection().add(classRoutine);
                branchId = em.merge(branchId);
            }
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendance : classRoutine.getSubjectAttendanceCollection()) {
                ClassRoutine oldClassRoutineIdOfSubjectAttendanceCollectionSubjectAttendance = subjectAttendanceCollectionSubjectAttendance.getClassRoutineId();
                subjectAttendanceCollectionSubjectAttendance.setClassRoutineId(classRoutine);
                subjectAttendanceCollectionSubjectAttendance = em.merge(subjectAttendanceCollectionSubjectAttendance);
                if (oldClassRoutineIdOfSubjectAttendanceCollectionSubjectAttendance != null) {
                    oldClassRoutineIdOfSubjectAttendanceCollectionSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionSubjectAttendance);
                    oldClassRoutineIdOfSubjectAttendanceCollectionSubjectAttendance = em.merge(oldClassRoutineIdOfSubjectAttendanceCollectionSubjectAttendance);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClassRoutine(classRoutine.getClassRoutineId()) != null) {
                throw new PreexistingEntityException("ClassRoutine " + classRoutine + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClassRoutine classRoutine) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ClassRoutine persistentClassRoutine = em.find(ClassRoutine.class, classRoutine.getClassRoutineId());
            Class classIdOld = persistentClassRoutine.getClassId();
            Class classIdNew = classRoutine.getClassId();
            Term termIdOld = persistentClassRoutine.getTermId();
            Term termIdNew = classRoutine.getTermId();
            Section sectionIdOld = persistentClassRoutine.getSectionId();
            Section sectionIdNew = classRoutine.getSectionId();
            Subject subjectIdOld = persistentClassRoutine.getSubjectId();
            Subject subjectIdNew = classRoutine.getSubjectId();
            Days dayOld = persistentClassRoutine.getDay();
            Days dayNew = classRoutine.getDay();
            CompanyBranch branchIdOld = persistentClassRoutine.getBranchId();
            CompanyBranch branchIdNew = classRoutine.getBranchId();
            Collection<SubjectAttendance> subjectAttendanceCollectionOld = persistentClassRoutine.getSubjectAttendanceCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionNew = classRoutine.getSubjectAttendanceCollection();
            List<String> illegalOrphanMessages = null;
            for (SubjectAttendance subjectAttendanceCollectionOldSubjectAttendance : subjectAttendanceCollectionOld) {
                if (!subjectAttendanceCollectionNew.contains(subjectAttendanceCollectionOldSubjectAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubjectAttendance " + subjectAttendanceCollectionOldSubjectAttendance + " since its classRoutineId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                classRoutine.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                classRoutine.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                classRoutine.setSectionId(sectionIdNew);
            }
            if (subjectIdNew != null) {
                subjectIdNew = em.getReference(subjectIdNew.getClass(), subjectIdNew.getSubjectId());
                classRoutine.setSubjectId(subjectIdNew);
            }
            if (dayNew != null) {
                dayNew = em.getReference(dayNew.getClass(), dayNew.getDayId());
                classRoutine.setDay(dayNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                classRoutine.setBranchId(branchIdNew);
            }
            Collection<SubjectAttendance> attachedSubjectAttendanceCollectionNew = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendanceToAttach : subjectAttendanceCollectionNew) {
                subjectAttendanceCollectionNewSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionNewSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionNewSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollectionNew.add(subjectAttendanceCollectionNewSubjectAttendanceToAttach);
            }
            subjectAttendanceCollectionNew = attachedSubjectAttendanceCollectionNew;
            classRoutine.setSubjectAttendanceCollection(subjectAttendanceCollectionNew);
            classRoutine = em.merge(classRoutine);
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getClassRoutineCollection().remove(classRoutine);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getClassRoutineCollection().add(classRoutine);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getClassRoutineCollection().remove(classRoutine);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getClassRoutineCollection().add(classRoutine);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getClassRoutineCollection().remove(classRoutine);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getClassRoutineCollection().add(classRoutine);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (subjectIdOld != null && !subjectIdOld.equals(subjectIdNew)) {
                subjectIdOld.getClassRoutineCollection().remove(classRoutine);
                subjectIdOld = em.merge(subjectIdOld);
            }
            if (subjectIdNew != null && !subjectIdNew.equals(subjectIdOld)) {
                subjectIdNew.getClassRoutineCollection().add(classRoutine);
                subjectIdNew = em.merge(subjectIdNew);
            }
            if (dayOld != null && !dayOld.equals(dayNew)) {
                dayOld.getClassRoutineCollection().remove(classRoutine);
                dayOld = em.merge(dayOld);
            }
            if (dayNew != null && !dayNew.equals(dayOld)) {
                dayNew.getClassRoutineCollection().add(classRoutine);
                dayNew = em.merge(dayNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getClassRoutineCollection().remove(classRoutine);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getClassRoutineCollection().add(classRoutine);
                branchIdNew = em.merge(branchIdNew);
            }
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendance : subjectAttendanceCollectionNew) {
                if (!subjectAttendanceCollectionOld.contains(subjectAttendanceCollectionNewSubjectAttendance)) {
                    ClassRoutine oldClassRoutineIdOfSubjectAttendanceCollectionNewSubjectAttendance = subjectAttendanceCollectionNewSubjectAttendance.getClassRoutineId();
                    subjectAttendanceCollectionNewSubjectAttendance.setClassRoutineId(classRoutine);
                    subjectAttendanceCollectionNewSubjectAttendance = em.merge(subjectAttendanceCollectionNewSubjectAttendance);
                    if (oldClassRoutineIdOfSubjectAttendanceCollectionNewSubjectAttendance != null && !oldClassRoutineIdOfSubjectAttendanceCollectionNewSubjectAttendance.equals(classRoutine)) {
                        oldClassRoutineIdOfSubjectAttendanceCollectionNewSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionNewSubjectAttendance);
                        oldClassRoutineIdOfSubjectAttendanceCollectionNewSubjectAttendance = em.merge(oldClassRoutineIdOfSubjectAttendanceCollectionNewSubjectAttendance);
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
                Long id = classRoutine.getClassRoutineId();
                if (findClassRoutine(id) == null) {
                    throw new NonexistentEntityException("The classRoutine with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            ClassRoutine classRoutine;
            try {
                classRoutine = em.getReference(ClassRoutine.class, id);
                classRoutine.getClassRoutineId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classRoutine with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SubjectAttendance> subjectAttendanceCollectionOrphanCheck = classRoutine.getSubjectAttendanceCollection();
            for (SubjectAttendance subjectAttendanceCollectionOrphanCheckSubjectAttendance : subjectAttendanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassRoutine (" + classRoutine + ") cannot be destroyed since the SubjectAttendance " + subjectAttendanceCollectionOrphanCheckSubjectAttendance + " in its subjectAttendanceCollection field has a non-nullable classRoutineId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Class classId = classRoutine.getClassId();
            if (classId != null) {
                classId.getClassRoutineCollection().remove(classRoutine);
                classId = em.merge(classId);
            }
            Term termId = classRoutine.getTermId();
            if (termId != null) {
                termId.getClassRoutineCollection().remove(classRoutine);
                termId = em.merge(termId);
            }
            Section sectionId = classRoutine.getSectionId();
            if (sectionId != null) {
                sectionId.getClassRoutineCollection().remove(classRoutine);
                sectionId = em.merge(sectionId);
            }
            Subject subjectId = classRoutine.getSubjectId();
            if (subjectId != null) {
                subjectId.getClassRoutineCollection().remove(classRoutine);
                subjectId = em.merge(subjectId);
            }
            Days day = classRoutine.getDay();
            if (day != null) {
                day.getClassRoutineCollection().remove(classRoutine);
                day = em.merge(day);
            }
            CompanyBranch branchId = classRoutine.getBranchId();
            if (branchId != null) {
                branchId.getClassRoutineCollection().remove(classRoutine);
                branchId = em.merge(branchId);
            }
            em.remove(classRoutine);
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

    public List<ClassRoutine> findClassRoutineEntities() {
        return findClassRoutineEntities(true, -1, -1);
    }

    public List<ClassRoutine> findClassRoutineEntities(int maxResults, int firstResult) {
        return findClassRoutineEntities(false, maxResults, firstResult);
    }

    private List<ClassRoutine> findClassRoutineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassRoutine.class));
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

    public ClassRoutine findClassRoutine(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassRoutine.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassRoutineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassRoutine> rt = cq.from(ClassRoutine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
