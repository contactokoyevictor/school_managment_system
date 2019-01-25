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
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
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
public class StudentDailyAttendanceJpaController implements Serializable {

    public StudentDailyAttendanceJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentDailyAttendance studentDailyAttendance) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = studentDailyAttendance.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                studentDailyAttendance.setBranchId(branchId);
            }
            Class classId = studentDailyAttendance.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                studentDailyAttendance.setClassId(classId);
            }
            Term termId = studentDailyAttendance.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                studentDailyAttendance.setTermId(termId);
            }
            Section sectionId = studentDailyAttendance.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                studentDailyAttendance.setSectionId(sectionId);
            }
            Student studentId = studentDailyAttendance.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                studentDailyAttendance.setStudentId(studentId);
            }
            AcademicYears academicYear = studentDailyAttendance.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                studentDailyAttendance.setAcademicYear(academicYear);
            }
            em.persist(studentDailyAttendance);
            if (branchId != null) {
                branchId.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                branchId = em.merge(branchId);
            }
            if (classId != null) {
                classId.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                sectionId = em.merge(sectionId);
            }
            if (studentId != null) {
                studentId.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                studentId = em.merge(studentId);
            }
            if (academicYear != null) {
                academicYear.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                academicYear = em.merge(academicYear);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStudentDailyAttendance(studentDailyAttendance.getAttendanceId()) != null) {
                throw new PreexistingEntityException("StudentDailyAttendance " + studentDailyAttendance + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentDailyAttendance studentDailyAttendance) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StudentDailyAttendance persistentStudentDailyAttendance = em.find(StudentDailyAttendance.class, studentDailyAttendance.getAttendanceId());
            CompanyBranch branchIdOld = persistentStudentDailyAttendance.getBranchId();
            CompanyBranch branchIdNew = studentDailyAttendance.getBranchId();
            Class classIdOld = persistentStudentDailyAttendance.getClassId();
            Class classIdNew = studentDailyAttendance.getClassId();
            Term termIdOld = persistentStudentDailyAttendance.getTermId();
            Term termIdNew = studentDailyAttendance.getTermId();
            Section sectionIdOld = persistentStudentDailyAttendance.getSectionId();
            Section sectionIdNew = studentDailyAttendance.getSectionId();
            Student studentIdOld = persistentStudentDailyAttendance.getStudentId();
            Student studentIdNew = studentDailyAttendance.getStudentId();
            AcademicYears academicYearOld = persistentStudentDailyAttendance.getAcademicYear();
            AcademicYears academicYearNew = studentDailyAttendance.getAcademicYear();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                studentDailyAttendance.setBranchId(branchIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                studentDailyAttendance.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                studentDailyAttendance.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                studentDailyAttendance.setSectionId(sectionIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                studentDailyAttendance.setStudentId(studentIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                studentDailyAttendance.setAcademicYear(academicYearNew);
            }
            studentDailyAttendance = em.merge(studentDailyAttendance);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                branchIdNew = em.merge(branchIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                studentIdNew = em.merge(studentIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getStudentDailyAttendanceCollection().add(studentDailyAttendance);
                academicYearNew = em.merge(academicYearNew);
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
                Long id = studentDailyAttendance.getAttendanceId();
                if (findStudentDailyAttendance(id) == null) {
                    throw new NonexistentEntityException("The studentDailyAttendance with id " + id + " no longer exists.");
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
            StudentDailyAttendance studentDailyAttendance;
            try {
                studentDailyAttendance = em.getReference(StudentDailyAttendance.class, id);
                studentDailyAttendance.getAttendanceId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentDailyAttendance with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = studentDailyAttendance.getBranchId();
            if (branchId != null) {
                branchId.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                branchId = em.merge(branchId);
            }
            Class classId = studentDailyAttendance.getClassId();
            if (classId != null) {
                classId.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                classId = em.merge(classId);
            }
            Term termId = studentDailyAttendance.getTermId();
            if (termId != null) {
                termId.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                termId = em.merge(termId);
            }
            Section sectionId = studentDailyAttendance.getSectionId();
            if (sectionId != null) {
                sectionId.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                sectionId = em.merge(sectionId);
            }
            Student studentId = studentDailyAttendance.getStudentId();
            if (studentId != null) {
                studentId.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                studentId = em.merge(studentId);
            }
            AcademicYears academicYear = studentDailyAttendance.getAcademicYear();
            if (academicYear != null) {
                academicYear.getStudentDailyAttendanceCollection().remove(studentDailyAttendance);
                academicYear = em.merge(academicYear);
            }
            em.remove(studentDailyAttendance);
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

    public List<StudentDailyAttendance> findStudentDailyAttendanceEntities() {
        return findStudentDailyAttendanceEntities(true, -1, -1);
    }

    public List<StudentDailyAttendance> findStudentDailyAttendanceEntities(int maxResults, int firstResult) {
        return findStudentDailyAttendanceEntities(false, maxResults, firstResult);
    }

    private List<StudentDailyAttendance> findStudentDailyAttendanceEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentDailyAttendance.class));
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

    public StudentDailyAttendance findStudentDailyAttendance(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentDailyAttendance.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentDailyAttendanceCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentDailyAttendance> rt = cq.from(StudentDailyAttendance.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
