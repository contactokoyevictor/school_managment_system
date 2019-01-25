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
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
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
public class StudentBehavouralTraitJpaController implements Serializable {

    public StudentBehavouralTraitJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StudentBehavouralTrait studentBehavouralTrait) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = studentBehavouralTrait.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                studentBehavouralTrait.setBranchId(branchId);
            }
            Exam examId = studentBehavouralTrait.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                studentBehavouralTrait.setExamId(examId);
            }
            Class classId = studentBehavouralTrait.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                studentBehavouralTrait.setClassId(classId);
            }
            Term termId = studentBehavouralTrait.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                studentBehavouralTrait.setTermId(termId);
            }
            Section sectionId = studentBehavouralTrait.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                studentBehavouralTrait.setSectionId(sectionId);
            }
            Student studentId = studentBehavouralTrait.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                studentBehavouralTrait.setStudentId(studentId);
            }
            em.persist(studentBehavouralTrait);
            if (branchId != null) {
                branchId.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                branchId = em.merge(branchId);
            }
            if (examId != null) {
                examId.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                examId = em.merge(examId);
            }
            if (classId != null) {
                classId.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                sectionId = em.merge(sectionId);
            }
            if (studentId != null) {
                studentId.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                studentId = em.merge(studentId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStudentBehavouralTrait(studentBehavouralTrait.getId()) != null) {
                throw new PreexistingEntityException("StudentBehavouralTrait " + studentBehavouralTrait + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StudentBehavouralTrait studentBehavouralTrait) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            StudentBehavouralTrait persistentStudentBehavouralTrait = em.find(StudentBehavouralTrait.class, studentBehavouralTrait.getId());
            CompanyBranch branchIdOld = persistentStudentBehavouralTrait.getBranchId();
            CompanyBranch branchIdNew = studentBehavouralTrait.getBranchId();
            Exam examIdOld = persistentStudentBehavouralTrait.getExamId();
            Exam examIdNew = studentBehavouralTrait.getExamId();
            Class classIdOld = persistentStudentBehavouralTrait.getClassId();
            Class classIdNew = studentBehavouralTrait.getClassId();
            Term termIdOld = persistentStudentBehavouralTrait.getTermId();
            Term termIdNew = studentBehavouralTrait.getTermId();
            Section sectionIdOld = persistentStudentBehavouralTrait.getSectionId();
            Section sectionIdNew = studentBehavouralTrait.getSectionId();
            Student studentIdOld = persistentStudentBehavouralTrait.getStudentId();
            Student studentIdNew = studentBehavouralTrait.getStudentId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                studentBehavouralTrait.setBranchId(branchIdNew);
            }
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                studentBehavouralTrait.setExamId(examIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                studentBehavouralTrait.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                studentBehavouralTrait.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                studentBehavouralTrait.setSectionId(sectionIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                studentBehavouralTrait.setStudentId(studentIdNew);
            }
            studentBehavouralTrait = em.merge(studentBehavouralTrait);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                branchIdNew = em.merge(branchIdNew);
            }
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                examIdNew = em.merge(examIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getStudentBehavouralTraitCollection().add(studentBehavouralTrait);
                studentIdNew = em.merge(studentIdNew);
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
                Long id = studentBehavouralTrait.getId();
                if (findStudentBehavouralTrait(id) == null) {
                    throw new NonexistentEntityException("The studentBehavouralTrait with id " + id + " no longer exists.");
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
            StudentBehavouralTrait studentBehavouralTrait;
            try {
                studentBehavouralTrait = em.getReference(StudentBehavouralTrait.class, id);
                studentBehavouralTrait.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The studentBehavouralTrait with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = studentBehavouralTrait.getBranchId();
            if (branchId != null) {
                branchId.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                branchId = em.merge(branchId);
            }
            Exam examId = studentBehavouralTrait.getExamId();
            if (examId != null) {
                examId.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                examId = em.merge(examId);
            }
            Class classId = studentBehavouralTrait.getClassId();
            if (classId != null) {
                classId.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                classId = em.merge(classId);
            }
            Term termId = studentBehavouralTrait.getTermId();
            if (termId != null) {
                termId.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                termId = em.merge(termId);
            }
            Section sectionId = studentBehavouralTrait.getSectionId();
            if (sectionId != null) {
                sectionId.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                sectionId = em.merge(sectionId);
            }
            Student studentId = studentBehavouralTrait.getStudentId();
            if (studentId != null) {
                studentId.getStudentBehavouralTraitCollection().remove(studentBehavouralTrait);
                studentId = em.merge(studentId);
            }
            em.remove(studentBehavouralTrait);
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

    public List<StudentBehavouralTrait> findStudentBehavouralTraitEntities() {
        return findStudentBehavouralTraitEntities(true, -1, -1);
    }

    public List<StudentBehavouralTrait> findStudentBehavouralTraitEntities(int maxResults, int firstResult) {
        return findStudentBehavouralTraitEntities(false, maxResults, firstResult);
    }

    private List<StudentBehavouralTrait> findStudentBehavouralTraitEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StudentBehavouralTrait.class));
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

    public StudentBehavouralTrait findStudentBehavouralTrait(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StudentBehavouralTrait.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentBehavouralTraitCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StudentBehavouralTrait> rt = cq.from(StudentBehavouralTrait.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
