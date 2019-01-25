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
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author MY USER
 */
public class ClassSubjectsJpaController implements Serializable {
    private static final Logger log = Logger.getLogger(ClassSubjectsJpaController.class.getName());
    
    public ClassSubjectsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public ClassSubjectsJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(ClassSubjects classSubjects) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Class classId = classSubjects.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                classSubjects.setClassId(classId);
            }
            Term termId = classSubjects.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                classSubjects.setTermId(termId);
            }
            Subject subjectId = classSubjects.getSubjectId();
            if (subjectId != null) {
                subjectId = em.getReference(subjectId.getClass(), subjectId.getSubjectId());
                classSubjects.setSubjectId(subjectId);
            }
            Employee teacherId = classSubjects.getTeacherId();
            if (teacherId != null) {
                teacherId = em.getReference(teacherId.getClass(), teacherId.getEmployeeId());
                classSubjects.setTeacherId(teacherId);
            }
            CompanyBranch branchId = classSubjects.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                classSubjects.setBranchId(branchId);
            }
            em.persist(classSubjects);
            if (classId != null) {
                classId.getClassSubjectsCollection().add(classSubjects);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getClassSubjectsCollection().add(classSubjects);
                termId = em.merge(termId);
            }
            if (subjectId != null) {
                subjectId.getClassSubjectsCollection().add(classSubjects);
                subjectId = em.merge(subjectId);
            }
            if (teacherId != null) {
                teacherId.getClassSubjectsCollection().add(classSubjects);
                teacherId = em.merge(teacherId);
            }
            if (branchId != null) {
                branchId.getClassSubjectsCollection().add(classSubjects);
                branchId = em.merge(branchId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClassSubjects(classSubjects.getClassSubjectId()) != null) {
                throw new PreexistingEntityException("ClassSubjects " + classSubjects + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClassSubjects classSubjects) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClassSubjects persistentClassSubjects = em.find(ClassSubjects.class, classSubjects.getClassSubjectId());
            Class classIdOld = persistentClassSubjects.getClassId();
            Class classIdNew = classSubjects.getClassId();
            Term termIdOld = persistentClassSubjects.getTermId();
            Term termIdNew = classSubjects.getTermId();
            Subject subjectIdOld = persistentClassSubjects.getSubjectId();
            Subject subjectIdNew = classSubjects.getSubjectId();
            Employee teacherIdOld = persistentClassSubjects.getTeacherId();
            Employee teacherIdNew = classSubjects.getTeacherId();
            CompanyBranch branchIdOld = persistentClassSubjects.getBranchId();
            CompanyBranch branchIdNew = classSubjects.getBranchId();
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                classSubjects.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                classSubjects.setTermId(termIdNew);
            }
            if (subjectIdNew != null) {
                subjectIdNew = em.getReference(subjectIdNew.getClass(), subjectIdNew.getSubjectId());
                classSubjects.setSubjectId(subjectIdNew);
            }
            if (teacherIdNew != null) {
                teacherIdNew = em.getReference(teacherIdNew.getClass(), teacherIdNew.getEmployeeId());
                classSubjects.setTeacherId(teacherIdNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                classSubjects.setBranchId(branchIdNew);
            }
            classSubjects = em.merge(classSubjects);
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getClassSubjectsCollection().remove(classSubjects);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getClassSubjectsCollection().add(classSubjects);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getClassSubjectsCollection().remove(classSubjects);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getClassSubjectsCollection().add(classSubjects);
                termIdNew = em.merge(termIdNew);
            }
            if (subjectIdOld != null && !subjectIdOld.equals(subjectIdNew)) {
                subjectIdOld.getClassSubjectsCollection().remove(classSubjects);
                subjectIdOld = em.merge(subjectIdOld);
            }
            if (subjectIdNew != null && !subjectIdNew.equals(subjectIdOld)) {
                subjectIdNew.getClassSubjectsCollection().add(classSubjects);
                subjectIdNew = em.merge(subjectIdNew);
            }
            if (teacherIdOld != null && !teacherIdOld.equals(teacherIdNew)) {
                teacherIdOld.getClassSubjectsCollection().remove(classSubjects);
                teacherIdOld = em.merge(teacherIdOld);
            }
            if (teacherIdNew != null && !teacherIdNew.equals(teacherIdOld)) {
                teacherIdNew.getClassSubjectsCollection().add(classSubjects);
                teacherIdNew = em.merge(teacherIdNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getClassSubjectsCollection().remove(classSubjects);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getClassSubjectsCollection().add(classSubjects);
                branchIdNew = em.merge(branchIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = classSubjects.getClassSubjectId();
                if (findClassSubjects(id) == null) {
                    throw new NonexistentEntityException("The classSubjects with id " + id + " no longer exists.");
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
            em = getEntityManager();
            em.getTransaction().begin();
            ClassSubjects classSubjects;
            try {
                classSubjects = em.getReference(ClassSubjects.class, id);
                classSubjects.getClassSubjectId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classSubjects with id " + id + " no longer exists.", enfe);
            }
            Class classId = classSubjects.getClassId();
            if (classId != null) {
                classId.getClassSubjectsCollection().remove(classSubjects);
                classId = em.merge(classId);
            }
            Term termId = classSubjects.getTermId();
            if (termId != null) {
                termId.getClassSubjectsCollection().remove(classSubjects);
                termId = em.merge(termId);
            }
            Subject subjectId = classSubjects.getSubjectId();
            if (subjectId != null) {
                subjectId.getClassSubjectsCollection().remove(classSubjects);
                subjectId = em.merge(subjectId);
            }
            Employee teacherId = classSubjects.getTeacherId();
            if (teacherId != null) {
                teacherId.getClassSubjectsCollection().remove(classSubjects);
                teacherId = em.merge(teacherId);
            }
            CompanyBranch branchId = classSubjects.getBranchId();
            if (branchId != null) {
                branchId.getClassSubjectsCollection().remove(classSubjects);
                branchId = em.merge(branchId);
            }
            em.remove(classSubjects);
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
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

    public List<ClassSubjects> findClassSubjectsEntities() {
        return findClassSubjectsEntities(true, -1, -1);
    }

    public List<ClassSubjects> findClassSubjectsEntities(int maxResults, int firstResult) {
        return findClassSubjectsEntities(false, maxResults, firstResult);
    }

    private List<ClassSubjects> findClassSubjectsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassSubjects.class));
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

    public ClassSubjects findClassSubjects(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassSubjects.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassSubjectsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassSubjects> rt = cq.from(ClassSubjects.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    

    
}
