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
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.ExamMark;
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
public class ExamMarkJpaController implements Serializable {

    public ExamMarkJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamMark examMark) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = examMark.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                examMark.setBranchId(branchId);
            }
            Student studentId = examMark.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                examMark.setStudentId(studentId);
            }
            Subject subjectId = examMark.getSubjectId();
            if (subjectId != null) {
                subjectId = em.getReference(subjectId.getClass(), subjectId.getSubjectId());
                examMark.setSubjectId(subjectId);
            }
            Class classId = examMark.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                examMark.setClassId(classId);
            }
            Term termId = examMark.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                examMark.setTermId(termId);
            }
            Section sectionId = examMark.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                examMark.setSectionId(sectionId);
            }
            Exam examId = examMark.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                examMark.setExamId(examId);
            }
            em.persist(examMark);
            if (branchId != null) {
                branchId.getExamMarkCollection().add(examMark);
                branchId = em.merge(branchId);
            }
            if (studentId != null) {
                studentId.getExamMarkCollection().add(examMark);
                studentId = em.merge(studentId);
            }
            if (subjectId != null) {
                subjectId.getExamMarkCollection().add(examMark);
                subjectId = em.merge(subjectId);
            }
            if (classId != null) {
                classId.getExamMarkCollection().add(examMark);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getExamMarkCollection().add(examMark);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getExamMarkCollection().add(examMark);
                sectionId = em.merge(sectionId);
            }
            if (examId != null) {
                examId.getExamMarkCollection().add(examMark);
                examId = em.merge(examId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findExamMark(examMark.getMarkId()) != null) {
                throw new PreexistingEntityException("ExamMark " + examMark + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamMark examMark) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ExamMark persistentExamMark = em.find(ExamMark.class, examMark.getMarkId());
            CompanyBranch branchIdOld = persistentExamMark.getBranchId();
            CompanyBranch branchIdNew = examMark.getBranchId();
            Student studentIdOld = persistentExamMark.getStudentId();
            Student studentIdNew = examMark.getStudentId();
            Subject subjectIdOld = persistentExamMark.getSubjectId();
            Subject subjectIdNew = examMark.getSubjectId();
            Class classIdOld = persistentExamMark.getClassId();
            Class classIdNew = examMark.getClassId();
            Term termIdOld = persistentExamMark.getTermId();
            Term termIdNew = examMark.getTermId();
            Section sectionIdOld = persistentExamMark.getSectionId();
            Section sectionIdNew = examMark.getSectionId();
            Exam examIdOld = persistentExamMark.getExamId();
            Exam examIdNew = examMark.getExamId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                examMark.setBranchId(branchIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                examMark.setStudentId(studentIdNew);
            }
            if (subjectIdNew != null) {
                subjectIdNew = em.getReference(subjectIdNew.getClass(), subjectIdNew.getSubjectId());
                examMark.setSubjectId(subjectIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                examMark.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                examMark.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                examMark.setSectionId(sectionIdNew);
            }
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                examMark.setExamId(examIdNew);
            }
            examMark = em.merge(examMark);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getExamMarkCollection().remove(examMark);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getExamMarkCollection().add(examMark);
                branchIdNew = em.merge(branchIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getExamMarkCollection().remove(examMark);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getExamMarkCollection().add(examMark);
                studentIdNew = em.merge(studentIdNew);
            }
            if (subjectIdOld != null && !subjectIdOld.equals(subjectIdNew)) {
                subjectIdOld.getExamMarkCollection().remove(examMark);
                subjectIdOld = em.merge(subjectIdOld);
            }
            if (subjectIdNew != null && !subjectIdNew.equals(subjectIdOld)) {
                subjectIdNew.getExamMarkCollection().add(examMark);
                subjectIdNew = em.merge(subjectIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getExamMarkCollection().remove(examMark);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getExamMarkCollection().add(examMark);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getExamMarkCollection().remove(examMark);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getExamMarkCollection().add(examMark);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getExamMarkCollection().remove(examMark);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getExamMarkCollection().add(examMark);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getExamMarkCollection().remove(examMark);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getExamMarkCollection().add(examMark);
                examIdNew = em.merge(examIdNew);
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
                Long id = examMark.getMarkId();
                if (findExamMark(id) == null) {
                    throw new NonexistentEntityException("The examMark with id " + id + " no longer exists.");
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
            ExamMark examMark;
            try {
                examMark = em.getReference(ExamMark.class, id);
                examMark.getMarkId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examMark with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = examMark.getBranchId();
            if (branchId != null) {
                branchId.getExamMarkCollection().remove(examMark);
                branchId = em.merge(branchId);
            }
            Student studentId = examMark.getStudentId();
            if (studentId != null) {
                studentId.getExamMarkCollection().remove(examMark);
                studentId = em.merge(studentId);
            }
            Subject subjectId = examMark.getSubjectId();
            if (subjectId != null) {
                subjectId.getExamMarkCollection().remove(examMark);
                subjectId = em.merge(subjectId);
            }
            Class classId = examMark.getClassId();
            if (classId != null) {
                classId.getExamMarkCollection().remove(examMark);
                classId = em.merge(classId);
            }
            Term termId = examMark.getTermId();
            if (termId != null) {
                termId.getExamMarkCollection().remove(examMark);
                termId = em.merge(termId);
            }
            Section sectionId = examMark.getSectionId();
            if (sectionId != null) {
                sectionId.getExamMarkCollection().remove(examMark);
                sectionId = em.merge(sectionId);
            }
            Exam examId = examMark.getExamId();
            if (examId != null) {
                examId.getExamMarkCollection().remove(examMark);
                examId = em.merge(examId);
            }
            em.remove(examMark);
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

    public List<ExamMark> findExamMarkEntities() {
        return findExamMarkEntities(true, -1, -1);
    }

    public List<ExamMark> findExamMarkEntities(int maxResults, int firstResult) {
        return findExamMarkEntities(false, maxResults, firstResult);
    }

    private List<ExamMark> findExamMarkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExamMark.class));
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

    public ExamMark findExamMark(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamMark.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamMarkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamMark> rt = cq.from(ExamMark.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
