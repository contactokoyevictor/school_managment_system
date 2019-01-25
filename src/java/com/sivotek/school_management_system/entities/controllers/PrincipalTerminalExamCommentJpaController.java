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
import com.sivotek.school_management_system.entities.PrincipalTerminalExamComment;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
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
public class PrincipalTerminalExamCommentJpaController implements Serializable {

    public PrincipalTerminalExamCommentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PrincipalTerminalExamComment principalTerminalExamComment) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = principalTerminalExamComment.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                principalTerminalExamComment.setBranchId(branchId);
            }
            Student studentId = principalTerminalExamComment.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                principalTerminalExamComment.setStudentId(studentId);
            }
            Class classId = principalTerminalExamComment.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                principalTerminalExamComment.setClassId(classId);
            }
            Term termId = principalTerminalExamComment.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                principalTerminalExamComment.setTermId(termId);
            }
            Section sectionId = principalTerminalExamComment.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                principalTerminalExamComment.setSectionId(sectionId);
            }
            em.persist(principalTerminalExamComment);
            if (branchId != null) {
                branchId.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                branchId = em.merge(branchId);
            }
            if (studentId != null) {
                studentId.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                studentId = em.merge(studentId);
            }
            if (classId != null) {
                classId.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                sectionId = em.merge(sectionId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findPrincipalTerminalExamComment(principalTerminalExamComment.getId()) != null) {
                throw new PreexistingEntityException("PrincipalTerminalExamComment " + principalTerminalExamComment + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PrincipalTerminalExamComment principalTerminalExamComment) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            PrincipalTerminalExamComment persistentPrincipalTerminalExamComment = em.find(PrincipalTerminalExamComment.class, principalTerminalExamComment.getId());
            CompanyBranch branchIdOld = persistentPrincipalTerminalExamComment.getBranchId();
            CompanyBranch branchIdNew = principalTerminalExamComment.getBranchId();
            Student studentIdOld = persistentPrincipalTerminalExamComment.getStudentId();
            Student studentIdNew = principalTerminalExamComment.getStudentId();
            Class classIdOld = persistentPrincipalTerminalExamComment.getClassId();
            Class classIdNew = principalTerminalExamComment.getClassId();
            Term termIdOld = persistentPrincipalTerminalExamComment.getTermId();
            Term termIdNew = principalTerminalExamComment.getTermId();
            Section sectionIdOld = persistentPrincipalTerminalExamComment.getSectionId();
            Section sectionIdNew = principalTerminalExamComment.getSectionId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                principalTerminalExamComment.setBranchId(branchIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                principalTerminalExamComment.setStudentId(studentIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                principalTerminalExamComment.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                principalTerminalExamComment.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                principalTerminalExamComment.setSectionId(sectionIdNew);
            }
            principalTerminalExamComment = em.merge(principalTerminalExamComment);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                branchIdNew = em.merge(branchIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                studentIdNew = em.merge(studentIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getPrincipalTerminalExamCommentCollection().add(principalTerminalExamComment);
                sectionIdNew = em.merge(sectionIdNew);
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
                Long id = principalTerminalExamComment.getId();
                if (findPrincipalTerminalExamComment(id) == null) {
                    throw new NonexistentEntityException("The principalTerminalExamComment with id " + id + " no longer exists.");
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
            PrincipalTerminalExamComment principalTerminalExamComment;
            try {
                principalTerminalExamComment = em.getReference(PrincipalTerminalExamComment.class, id);
                principalTerminalExamComment.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The principalTerminalExamComment with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = principalTerminalExamComment.getBranchId();
            if (branchId != null) {
                branchId.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                branchId = em.merge(branchId);
            }
            Student studentId = principalTerminalExamComment.getStudentId();
            if (studentId != null) {
                studentId.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                studentId = em.merge(studentId);
            }
            Class classId = principalTerminalExamComment.getClassId();
            if (classId != null) {
                classId.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                classId = em.merge(classId);
            }
            Term termId = principalTerminalExamComment.getTermId();
            if (termId != null) {
                termId.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                termId = em.merge(termId);
            }
            Section sectionId = principalTerminalExamComment.getSectionId();
            if (sectionId != null) {
                sectionId.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamComment);
                sectionId = em.merge(sectionId);
            }
            em.remove(principalTerminalExamComment);
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

    public List<PrincipalTerminalExamComment> findPrincipalTerminalExamCommentEntities() {
        return findPrincipalTerminalExamCommentEntities(true, -1, -1);
    }

    public List<PrincipalTerminalExamComment> findPrincipalTerminalExamCommentEntities(int maxResults, int firstResult) {
        return findPrincipalTerminalExamCommentEntities(false, maxResults, firstResult);
    }

    private List<PrincipalTerminalExamComment> findPrincipalTerminalExamCommentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PrincipalTerminalExamComment.class));
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

    public PrincipalTerminalExamComment findPrincipalTerminalExamComment(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PrincipalTerminalExamComment.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrincipalTerminalExamCommentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PrincipalTerminalExamComment> rt = cq.from(PrincipalTerminalExamComment.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
