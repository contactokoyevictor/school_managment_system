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
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
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
public class FormMasterTerminalExamCommentJpaController implements Serializable {

    public FormMasterTerminalExamCommentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(FormMasterTerminalExamComment formMasterTerminalExamComment) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = formMasterTerminalExamComment.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                formMasterTerminalExamComment.setBranchId(branchId);
            }
            Student studentId = formMasterTerminalExamComment.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                formMasterTerminalExamComment.setStudentId(studentId);
            }
            Class classId = formMasterTerminalExamComment.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                formMasterTerminalExamComment.setClassId(classId);
            }
            Term termId = formMasterTerminalExamComment.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                formMasterTerminalExamComment.setTermId(termId);
            }
            Section sectionId = formMasterTerminalExamComment.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                formMasterTerminalExamComment.setSectionId(sectionId);
            }
            Exam examId = formMasterTerminalExamComment.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                formMasterTerminalExamComment.setExamId(examId);
            }
            em.persist(formMasterTerminalExamComment);
            if (branchId != null) {
                branchId.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                branchId = em.merge(branchId);
            }
            if (studentId != null) {
                studentId.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                studentId = em.merge(studentId);
            }
            if (classId != null) {
                classId.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                sectionId = em.merge(sectionId);
            }
            if (examId != null) {
                examId.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                examId = em.merge(examId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findFormMasterTerminalExamComment(formMasterTerminalExamComment.getId()) != null) {
                throw new PreexistingEntityException("FormMasterTerminalExamComment " + formMasterTerminalExamComment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(FormMasterTerminalExamComment formMasterTerminalExamComment) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            FormMasterTerminalExamComment persistentFormMasterTerminalExamComment = em.find(FormMasterTerminalExamComment.class, formMasterTerminalExamComment.getId());
            CompanyBranch branchIdOld = persistentFormMasterTerminalExamComment.getBranchId();
            CompanyBranch branchIdNew = formMasterTerminalExamComment.getBranchId();
            Student studentIdOld = persistentFormMasterTerminalExamComment.getStudentId();
            Student studentIdNew = formMasterTerminalExamComment.getStudentId();
            Class classIdOld = persistentFormMasterTerminalExamComment.getClassId();
            Class classIdNew = formMasterTerminalExamComment.getClassId();
            Term termIdOld = persistentFormMasterTerminalExamComment.getTermId();
            Term termIdNew = formMasterTerminalExamComment.getTermId();
            Section sectionIdOld = persistentFormMasterTerminalExamComment.getSectionId();
            Section sectionIdNew = formMasterTerminalExamComment.getSectionId();
            Exam examIdOld = persistentFormMasterTerminalExamComment.getExamId();
            Exam examIdNew = formMasterTerminalExamComment.getExamId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                formMasterTerminalExamComment.setBranchId(branchIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                formMasterTerminalExamComment.setStudentId(studentIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                formMasterTerminalExamComment.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                formMasterTerminalExamComment.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                formMasterTerminalExamComment.setSectionId(sectionIdNew);
            }
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                formMasterTerminalExamComment.setExamId(examIdNew);
            }
            formMasterTerminalExamComment = em.merge(formMasterTerminalExamComment);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                branchIdNew = em.merge(branchIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                studentIdNew = em.merge(studentIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getFormMasterTerminalExamCommentCollection().add(formMasterTerminalExamComment);
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
                Long id = formMasterTerminalExamComment.getId();
                if (findFormMasterTerminalExamComment(id) == null) {
                    throw new NonexistentEntityException("The formMasterTerminalExamComment with id " + id + " no longer exists.");
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
            FormMasterTerminalExamComment formMasterTerminalExamComment;
            try {
                formMasterTerminalExamComment = em.getReference(FormMasterTerminalExamComment.class, id);
                formMasterTerminalExamComment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formMasterTerminalExamComment with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = formMasterTerminalExamComment.getBranchId();
            if (branchId != null) {
                branchId.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                branchId = em.merge(branchId);
            }
            Student studentId = formMasterTerminalExamComment.getStudentId();
            if (studentId != null) {
                studentId.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                studentId = em.merge(studentId);
            }
            Class classId = formMasterTerminalExamComment.getClassId();
            if (classId != null) {
                classId.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                classId = em.merge(classId);
            }
            Term termId = formMasterTerminalExamComment.getTermId();
            if (termId != null) {
                termId.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                termId = em.merge(termId);
            }
            Section sectionId = formMasterTerminalExamComment.getSectionId();
            if (sectionId != null) {
                sectionId.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                sectionId = em.merge(sectionId);
            }
            Exam examId = formMasterTerminalExamComment.getExamId();
            if (examId != null) {
                examId.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamComment);
                examId = em.merge(examId);
            }
            em.remove(formMasterTerminalExamComment);
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

    public List<FormMasterTerminalExamComment> findFormMasterTerminalExamCommentEntities() {
        return findFormMasterTerminalExamCommentEntities(true, -1, -1);
    }

    public List<FormMasterTerminalExamComment> findFormMasterTerminalExamCommentEntities(int maxResults, int firstResult) {
        return findFormMasterTerminalExamCommentEntities(false, maxResults, firstResult);
    }

    private List<FormMasterTerminalExamComment> findFormMasterTerminalExamCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(FormMasterTerminalExamComment.class));
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

    public FormMasterTerminalExamComment findFormMasterTerminalExamComment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(FormMasterTerminalExamComment.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormMasterTerminalExamCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<FormMasterTerminalExamComment> rt = cq.from(FormMasterTerminalExamComment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
