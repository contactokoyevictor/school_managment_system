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
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.ExamClassPosition;
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
public class ExamClassPositionJpaController implements Serializable {

    public ExamClassPositionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ExamClassPosition examClassPosition) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = examClassPosition.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                examClassPosition.setBranchId(branchId);
            }
            Student studentId = examClassPosition.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                examClassPosition.setStudentId(studentId);
            }
            Class classId = examClassPosition.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                examClassPosition.setClassId(classId);
            }
            Term termId = examClassPosition.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                examClassPosition.setTermId(termId);
            }
            Section sectionId = examClassPosition.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                examClassPosition.setSectionId(sectionId);
            }
            Exam examId = examClassPosition.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                examClassPosition.setExamId(examId);
            }
            em.persist(examClassPosition);
            if (branchId != null) {
                branchId.getExamClassPositionCollection().add(examClassPosition);
                branchId = em.merge(branchId);
            }
            if (studentId != null) {
                studentId.getExamClassPositionCollection().add(examClassPosition);
                studentId = em.merge(studentId);
            }
            if (classId != null) {
                classId.getExamClassPositionCollection().add(examClassPosition);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getExamClassPositionCollection().add(examClassPosition);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getExamClassPositionCollection().add(examClassPosition);
                sectionId = em.merge(sectionId);
            }
            if (examId != null) {
                examId.getExamClassPositionCollection().add(examClassPosition);
                examId = em.merge(examId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findExamClassPosition(examClassPosition.getId()) != null) {
                throw new PreexistingEntityException("ExamClassPosition " + examClassPosition + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ExamClassPosition examClassPosition) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ExamClassPosition persistentExamClassPosition = em.find(ExamClassPosition.class, examClassPosition.getId());
            CompanyBranch branchIdOld = persistentExamClassPosition.getBranchId();
            CompanyBranch branchIdNew = examClassPosition.getBranchId();
            Student studentIdOld = persistentExamClassPosition.getStudentId();
            Student studentIdNew = examClassPosition.getStudentId();
            Class classIdOld = persistentExamClassPosition.getClassId();
            Class classIdNew = examClassPosition.getClassId();
            Term termIdOld = persistentExamClassPosition.getTermId();
            Term termIdNew = examClassPosition.getTermId();
            Section sectionIdOld = persistentExamClassPosition.getSectionId();
            Section sectionIdNew = examClassPosition.getSectionId();
            Exam examIdOld = persistentExamClassPosition.getExamId();
            Exam examIdNew = examClassPosition.getExamId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                examClassPosition.setBranchId(branchIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                examClassPosition.setStudentId(studentIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                examClassPosition.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                examClassPosition.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                examClassPosition.setSectionId(sectionIdNew);
            }
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                examClassPosition.setExamId(examIdNew);
            }
            examClassPosition = em.merge(examClassPosition);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getExamClassPositionCollection().remove(examClassPosition);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getExamClassPositionCollection().add(examClassPosition);
                branchIdNew = em.merge(branchIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getExamClassPositionCollection().remove(examClassPosition);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getExamClassPositionCollection().add(examClassPosition);
                studentIdNew = em.merge(studentIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getExamClassPositionCollection().remove(examClassPosition);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getExamClassPositionCollection().add(examClassPosition);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getExamClassPositionCollection().remove(examClassPosition);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getExamClassPositionCollection().add(examClassPosition);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getExamClassPositionCollection().remove(examClassPosition);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getExamClassPositionCollection().add(examClassPosition);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getExamClassPositionCollection().remove(examClassPosition);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getExamClassPositionCollection().add(examClassPosition);
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
                Long id = examClassPosition.getId();
                if (findExamClassPosition(id) == null) {
                    throw new NonexistentEntityException("The examClassPosition with id " + id + " no longer exists.");
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
            ExamClassPosition examClassPosition;
            try {
                examClassPosition = em.getReference(ExamClassPosition.class, id);
                examClassPosition.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The examClassPosition with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = examClassPosition.getBranchId();
            if (branchId != null) {
                branchId.getExamClassPositionCollection().remove(examClassPosition);
                branchId = em.merge(branchId);
            }
            Student studentId = examClassPosition.getStudentId();
            if (studentId != null) {
                studentId.getExamClassPositionCollection().remove(examClassPosition);
                studentId = em.merge(studentId);
            }
            Class classId = examClassPosition.getClassId();
            if (classId != null) {
                classId.getExamClassPositionCollection().remove(examClassPosition);
                classId = em.merge(classId);
            }
            Term termId = examClassPosition.getTermId();
            if (termId != null) {
                termId.getExamClassPositionCollection().remove(examClassPosition);
                termId = em.merge(termId);
            }
            Section sectionId = examClassPosition.getSectionId();
            if (sectionId != null) {
                sectionId.getExamClassPositionCollection().remove(examClassPosition);
                sectionId = em.merge(sectionId);
            }
            Exam examId = examClassPosition.getExamId();
            if (examId != null) {
                examId.getExamClassPositionCollection().remove(examClassPosition);
                examId = em.merge(examId);
            }
            em.remove(examClassPosition);
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

    public List<ExamClassPosition> findExamClassPositionEntities() {
        return findExamClassPositionEntities(true, -1, -1);
    }

    public List<ExamClassPosition> findExamClassPositionEntities(int maxResults, int firstResult) {
        return findExamClassPositionEntities(false, maxResults, firstResult);
    }

    private List<ExamClassPosition> findExamClassPositionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ExamClassPosition.class));
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

    public ExamClassPosition findExamClassPosition(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ExamClassPosition.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamClassPositionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ExamClassPosition> rt = cq.from(ExamClassPosition.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
