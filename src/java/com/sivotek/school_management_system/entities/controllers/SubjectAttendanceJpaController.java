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
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.SubjectAttendance;
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
public class SubjectAttendanceJpaController implements Serializable {

    public SubjectAttendanceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SubjectAttendance subjectAttendance) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = subjectAttendance.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                subjectAttendance.setBranchId(branchId);
            }
            Class classId = subjectAttendance.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                subjectAttendance.setClassId(classId);
            }
            Subject subjectId = subjectAttendance.getSubjectId();
            if (subjectId != null) {
                subjectId = em.getReference(subjectId.getClass(), subjectId.getSubjectId());
                subjectAttendance.setSubjectId(subjectId);
            }
            Student studentId = subjectAttendance.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                subjectAttendance.setStudentId(studentId);
            }
            ClassRoutine classRoutineId = subjectAttendance.getClassRoutineId();
            if (classRoutineId != null) {
                classRoutineId = em.getReference(classRoutineId.getClass(), classRoutineId.getClassRoutineId());
                subjectAttendance.setClassRoutineId(classRoutineId);
            }
            em.persist(subjectAttendance);
            if (branchId != null) {
                branchId.getSubjectAttendanceCollection().add(subjectAttendance);
                branchId = em.merge(branchId);
            }
            if (classId != null) {
                classId.getSubjectAttendanceCollection().add(subjectAttendance);
                classId = em.merge(classId);
            }
            if (subjectId != null) {
                subjectId.getSubjectAttendanceCollection().add(subjectAttendance);
                subjectId = em.merge(subjectId);
            }
            if (studentId != null) {
                studentId.getSubjectAttendanceCollection().add(subjectAttendance);
                studentId = em.merge(studentId);
            }
            if (classRoutineId != null) {
                classRoutineId.getSubjectAttendanceCollection().add(subjectAttendance);
                classRoutineId = em.merge(classRoutineId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSubjectAttendance(subjectAttendance.getAttendanceId()) != null) {
                throw new PreexistingEntityException("SubjectAttendance " + subjectAttendance + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SubjectAttendance subjectAttendance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SubjectAttendance persistentSubjectAttendance = em.find(SubjectAttendance.class, subjectAttendance.getAttendanceId());
            CompanyBranch branchIdOld = persistentSubjectAttendance.getBranchId();
            CompanyBranch branchIdNew = subjectAttendance.getBranchId();
            Class classIdOld = persistentSubjectAttendance.getClassId();
            Class classIdNew = subjectAttendance.getClassId();
            Subject subjectIdOld = persistentSubjectAttendance.getSubjectId();
            Subject subjectIdNew = subjectAttendance.getSubjectId();
            Student studentIdOld = persistentSubjectAttendance.getStudentId();
            Student studentIdNew = subjectAttendance.getStudentId();
            ClassRoutine classRoutineIdOld = persistentSubjectAttendance.getClassRoutineId();
            ClassRoutine classRoutineIdNew = subjectAttendance.getClassRoutineId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                subjectAttendance.setBranchId(branchIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                subjectAttendance.setClassId(classIdNew);
            }
            if (subjectIdNew != null) {
                subjectIdNew = em.getReference(subjectIdNew.getClass(), subjectIdNew.getSubjectId());
                subjectAttendance.setSubjectId(subjectIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                subjectAttendance.setStudentId(studentIdNew);
            }
            if (classRoutineIdNew != null) {
                classRoutineIdNew = em.getReference(classRoutineIdNew.getClass(), classRoutineIdNew.getClassRoutineId());
                subjectAttendance.setClassRoutineId(classRoutineIdNew);
            }
            subjectAttendance = em.merge(subjectAttendance);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getSubjectAttendanceCollection().remove(subjectAttendance);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getSubjectAttendanceCollection().add(subjectAttendance);
                branchIdNew = em.merge(branchIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getSubjectAttendanceCollection().remove(subjectAttendance);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getSubjectAttendanceCollection().add(subjectAttendance);
                classIdNew = em.merge(classIdNew);
            }
            if (subjectIdOld != null && !subjectIdOld.equals(subjectIdNew)) {
                subjectIdOld.getSubjectAttendanceCollection().remove(subjectAttendance);
                subjectIdOld = em.merge(subjectIdOld);
            }
            if (subjectIdNew != null && !subjectIdNew.equals(subjectIdOld)) {
                subjectIdNew.getSubjectAttendanceCollection().add(subjectAttendance);
                subjectIdNew = em.merge(subjectIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getSubjectAttendanceCollection().remove(subjectAttendance);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getSubjectAttendanceCollection().add(subjectAttendance);
                studentIdNew = em.merge(studentIdNew);
            }
            if (classRoutineIdOld != null && !classRoutineIdOld.equals(classRoutineIdNew)) {
                classRoutineIdOld.getSubjectAttendanceCollection().remove(subjectAttendance);
                classRoutineIdOld = em.merge(classRoutineIdOld);
            }
            if (classRoutineIdNew != null && !classRoutineIdNew.equals(classRoutineIdOld)) {
                classRoutineIdNew.getSubjectAttendanceCollection().add(subjectAttendance);
                classRoutineIdNew = em.merge(classRoutineIdNew);
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
                Long id = subjectAttendance.getAttendanceId();
                if (findSubjectAttendance(id) == null) {
                    throw new NonexistentEntityException("The subjectAttendance with id " + id + " no longer exists.");
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
            SubjectAttendance subjectAttendance;
            try {
                subjectAttendance = em.getReference(SubjectAttendance.class, id);
                subjectAttendance.getAttendanceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subjectAttendance with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = subjectAttendance.getBranchId();
            if (branchId != null) {
                branchId.getSubjectAttendanceCollection().remove(subjectAttendance);
                branchId = em.merge(branchId);
            }
            Class classId = subjectAttendance.getClassId();
            if (classId != null) {
                classId.getSubjectAttendanceCollection().remove(subjectAttendance);
                classId = em.merge(classId);
            }
            Subject subjectId = subjectAttendance.getSubjectId();
            if (subjectId != null) {
                subjectId.getSubjectAttendanceCollection().remove(subjectAttendance);
                subjectId = em.merge(subjectId);
            }
            Student studentId = subjectAttendance.getStudentId();
            if (studentId != null) {
                studentId.getSubjectAttendanceCollection().remove(subjectAttendance);
                studentId = em.merge(studentId);
            }
            ClassRoutine classRoutineId = subjectAttendance.getClassRoutineId();
            if (classRoutineId != null) {
                classRoutineId.getSubjectAttendanceCollection().remove(subjectAttendance);
                classRoutineId = em.merge(classRoutineId);
            }
            em.remove(subjectAttendance);
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

    public List<SubjectAttendance> findSubjectAttendanceEntities() {
        return findSubjectAttendanceEntities(true, -1, -1);
    }

    public List<SubjectAttendance> findSubjectAttendanceEntities(int maxResults, int firstResult) {
        return findSubjectAttendanceEntities(false, maxResults, firstResult);
    }

    private List<SubjectAttendance> findSubjectAttendanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SubjectAttendance.class));
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

    public SubjectAttendance findSubjectAttendance(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SubjectAttendance.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubjectAttendanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SubjectAttendance> rt = cq.from(SubjectAttendance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
