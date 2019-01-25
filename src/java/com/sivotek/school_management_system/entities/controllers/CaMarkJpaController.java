/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.CaMark;
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
public class CaMarkJpaController implements Serializable {

    public CaMarkJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CaMark caMark) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = caMark.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                caMark.setBranchId(branchId);
            }
            Student studentId = caMark.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                caMark.setStudentId(studentId);
            }
            Subject subjectId = caMark.getSubjectId();
            if (subjectId != null) {
                subjectId = em.getReference(subjectId.getClass(), subjectId.getSubjectId());
                caMark.setSubjectId(subjectId);
            }
            Class classId = caMark.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                caMark.setClassId(classId);
            }
            Term termId = caMark.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                caMark.setTermId(termId);
            }
            Section sectionId = caMark.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                caMark.setSectionId(sectionId);
            }
            Exam examId = caMark.getExamId();
            if (examId != null) {
                examId = em.getReference(examId.getClass(), examId.getExamId());
                caMark.setExamId(examId);
            }
            em.persist(caMark);
            if (branchId != null) {
                branchId.getCaMarkCollection().add(caMark);
                branchId = em.merge(branchId);
            }
            if (studentId != null) {
                studentId.getCaMarkCollection().add(caMark);
                studentId = em.merge(studentId);
            }
            if (subjectId != null) {
                subjectId.getCaMarkCollection().add(caMark);
                subjectId = em.merge(subjectId);
            }
            if (classId != null) {
                classId.getCaMarkCollection().add(caMark);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getCaMarkCollection().add(caMark);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getCaMarkCollection().add(caMark);
                sectionId = em.merge(sectionId);
            }
            if (examId != null) {
                examId.getCaMarkCollection().add(caMark);
                examId = em.merge(examId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCaMark(caMark.getCaId()) != null) {
                throw new PreexistingEntityException("CaMark " + caMark + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CaMark caMark) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CaMark persistentCaMark = em.find(CaMark.class, caMark.getCaId());
            CompanyBranch branchIdOld = persistentCaMark.getBranchId();
            CompanyBranch branchIdNew = caMark.getBranchId();
            Student studentIdOld = persistentCaMark.getStudentId();
            Student studentIdNew = caMark.getStudentId();
            Subject subjectIdOld = persistentCaMark.getSubjectId();
            Subject subjectIdNew = caMark.getSubjectId();
            Class classIdOld = persistentCaMark.getClassId();
            Class classIdNew = caMark.getClassId();
            Term termIdOld = persistentCaMark.getTermId();
            Term termIdNew = caMark.getTermId();
            Section sectionIdOld = persistentCaMark.getSectionId();
            Section sectionIdNew = caMark.getSectionId();
            Exam examIdOld = persistentCaMark.getExamId();
            Exam examIdNew = caMark.getExamId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                caMark.setBranchId(branchIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                caMark.setStudentId(studentIdNew);
            }
            if (subjectIdNew != null) {
                subjectIdNew = em.getReference(subjectIdNew.getClass(), subjectIdNew.getSubjectId());
                caMark.setSubjectId(subjectIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                caMark.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                caMark.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                caMark.setSectionId(sectionIdNew);
            }
            if (examIdNew != null) {
                examIdNew = em.getReference(examIdNew.getClass(), examIdNew.getExamId());
                caMark.setExamId(examIdNew);
            }
            caMark = em.merge(caMark);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getCaMarkCollection().remove(caMark);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getCaMarkCollection().add(caMark);
                branchIdNew = em.merge(branchIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getCaMarkCollection().remove(caMark);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getCaMarkCollection().add(caMark);
                studentIdNew = em.merge(studentIdNew);
            }
            if (subjectIdOld != null && !subjectIdOld.equals(subjectIdNew)) {
                subjectIdOld.getCaMarkCollection().remove(caMark);
                subjectIdOld = em.merge(subjectIdOld);
            }
            if (subjectIdNew != null && !subjectIdNew.equals(subjectIdOld)) {
                subjectIdNew.getCaMarkCollection().add(caMark);
                subjectIdNew = em.merge(subjectIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getCaMarkCollection().remove(caMark);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getCaMarkCollection().add(caMark);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getCaMarkCollection().remove(caMark);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getCaMarkCollection().add(caMark);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getCaMarkCollection().remove(caMark);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getCaMarkCollection().add(caMark);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (examIdOld != null && !examIdOld.equals(examIdNew)) {
                examIdOld.getCaMarkCollection().remove(caMark);
                examIdOld = em.merge(examIdOld);
            }
            if (examIdNew != null && !examIdNew.equals(examIdOld)) {
                examIdNew.getCaMarkCollection().add(caMark);
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
                Long id = caMark.getCaId();
                if (findCaMark(id) == null) {
                    throw new NonexistentEntityException("The caMark with id " + id + " no longer exists.");
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
            CaMark caMark;
            try {
                caMark = em.getReference(CaMark.class, id);
                caMark.getCaId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caMark with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = caMark.getBranchId();
            if (branchId != null) {
                branchId.getCaMarkCollection().remove(caMark);
                branchId = em.merge(branchId);
            }
            Student studentId = caMark.getStudentId();
            if (studentId != null) {
                studentId.getCaMarkCollection().remove(caMark);
                studentId = em.merge(studentId);
            }
            Subject subjectId = caMark.getSubjectId();
            if (subjectId != null) {
                subjectId.getCaMarkCollection().remove(caMark);
                subjectId = em.merge(subjectId);
            }
            Class classId = caMark.getClassId();
            if (classId != null) {
                classId.getCaMarkCollection().remove(caMark);
                classId = em.merge(classId);
            }
            Term termId = caMark.getTermId();
            if (termId != null) {
                termId.getCaMarkCollection().remove(caMark);
                termId = em.merge(termId);
            }
            Section sectionId = caMark.getSectionId();
            if (sectionId != null) {
                sectionId.getCaMarkCollection().remove(caMark);
                sectionId = em.merge(sectionId);
            }
            Exam examId = caMark.getExamId();
            if (examId != null) {
                examId.getCaMarkCollection().remove(caMark);
                examId = em.merge(examId);
            }
            em.remove(caMark);
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

    public List<CaMark> findCaMarkEntities() {
        return findCaMarkEntities(true, -1, -1);
    }

    public List<CaMark> findCaMarkEntities(int maxResults, int firstResult) {
        return findCaMarkEntities(false, maxResults, firstResult);
    }

    private List<CaMark> findCaMarkEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CaMark.class));
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

    public CaMark findCaMark(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CaMark.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaMarkCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CaMark> rt = cq.from(CaMark.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
