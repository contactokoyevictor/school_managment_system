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
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.Grade;
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
public class GradeJpaController implements Serializable {

    public GradeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Grade grade) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = grade.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                grade.setBranchId(branchId);
            }
            AcademicYears academicYear = grade.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                grade.setAcademicYear(academicYear);
            }
            ClassCategory classCategoryId = grade.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId = em.getReference(classCategoryId.getClass(), classCategoryId.getCategoryId());
                grade.setClassCategoryId(classCategoryId);
            }
            em.persist(grade);
            if (branchId != null) {
                branchId.getGradeCollection().add(grade);
                branchId = em.merge(branchId);
            }
            if (academicYear != null) {
                academicYear.getGradeCollection().add(grade);
                academicYear = em.merge(academicYear);
            }
            if (classCategoryId != null) {
                classCategoryId.getGradeCollection().add(grade);
                classCategoryId = em.merge(classCategoryId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGrade(grade.getGradeId()) != null) {
                throw new PreexistingEntityException("Grade " + grade + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Grade grade) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Grade persistentGrade = em.find(Grade.class, grade.getGradeId());
            CompanyBranch branchIdOld = persistentGrade.getBranchId();
            CompanyBranch branchIdNew = grade.getBranchId();
            AcademicYears academicYearOld = persistentGrade.getAcademicYear();
            AcademicYears academicYearNew = grade.getAcademicYear();
            ClassCategory classCategoryIdOld = persistentGrade.getClassCategoryId();
            ClassCategory classCategoryIdNew = grade.getClassCategoryId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                grade.setBranchId(branchIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                grade.setAcademicYear(academicYearNew);
            }
            if (classCategoryIdNew != null) {
                classCategoryIdNew = em.getReference(classCategoryIdNew.getClass(), classCategoryIdNew.getCategoryId());
                grade.setClassCategoryId(classCategoryIdNew);
            }
            grade = em.merge(grade);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getGradeCollection().remove(grade);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getGradeCollection().add(grade);
                branchIdNew = em.merge(branchIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getGradeCollection().remove(grade);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getGradeCollection().add(grade);
                academicYearNew = em.merge(academicYearNew);
            }
            if (classCategoryIdOld != null && !classCategoryIdOld.equals(classCategoryIdNew)) {
                classCategoryIdOld.getGradeCollection().remove(grade);
                classCategoryIdOld = em.merge(classCategoryIdOld);
            }
            if (classCategoryIdNew != null && !classCategoryIdNew.equals(classCategoryIdOld)) {
                classCategoryIdNew.getGradeCollection().add(grade);
                classCategoryIdNew = em.merge(classCategoryIdNew);
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
                Long id = grade.getGradeId();
                if (findGrade(id) == null) {
                    throw new NonexistentEntityException("The grade with id " + id + " no longer exists.");
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
            Grade grade;
            try {
                grade = em.getReference(Grade.class, id);
                grade.getGradeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The grade with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = grade.getBranchId();
            if (branchId != null) {
                branchId.getGradeCollection().remove(grade);
                branchId = em.merge(branchId);
            }
            AcademicYears academicYear = grade.getAcademicYear();
            if (academicYear != null) {
                academicYear.getGradeCollection().remove(grade);
                academicYear = em.merge(academicYear);
            }
            ClassCategory classCategoryId = grade.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId.getGradeCollection().remove(grade);
                classCategoryId = em.merge(classCategoryId);
            }
            em.remove(grade);
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

    public List<Grade> findGradeEntities() {
        return findGradeEntities(true, -1, -1);
    }

    public List<Grade> findGradeEntities(int maxResults, int firstResult) {
        return findGradeEntities(false, maxResults, firstResult);
    }

    private List<Grade> findGradeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Grade.class));
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

    public Grade findGrade(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Grade.class, id);
        } finally {
            em.close();
        }
    }

    public int getGradeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Grade> rt = cq.from(Grade.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
