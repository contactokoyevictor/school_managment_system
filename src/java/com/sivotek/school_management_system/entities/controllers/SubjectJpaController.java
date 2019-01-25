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
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.SubjectAttendance;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.Subject;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author acer
 */
public class SubjectJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(SubjectJpaController.class.getName());
      
    public SubjectJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public SubjectJpaController(){
        try{  
             
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }


    public void create(Subject subject) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (subject.getSubjectAttendanceCollection() == null) {
            subject.setSubjectAttendanceCollection(new ArrayList<SubjectAttendance>());
        }
        if (subject.getClassSubjectsCollection() == null) {
            subject.setClassSubjectsCollection(new ArrayList<ClassSubjects>());
        }
        if (subject.getClassRoutineCollection() == null) {
            subject.setClassRoutineCollection(new ArrayList<ClassRoutine>());
        }
        if (subject.getExamMarkCollection() == null) {
            subject.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (subject.getCaMarkCollection() == null) {
            subject.setCaMarkCollection(new ArrayList<CaMark>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = subject.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                subject.setBranchId(branchId);
            }
            ClassCategory classCategoryId = subject.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId = em.getReference(classCategoryId.getClass(), classCategoryId.getCategoryId());
                subject.setClassCategoryId(classCategoryId);
            }
            AcademicYears academicYear = subject.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                subject.setAcademicYear(academicYear);
            }
            Collection<SubjectAttendance> attachedSubjectAttendanceCollection = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendanceToAttach : subject.getSubjectAttendanceCollection()) {
                subjectAttendanceCollectionSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollection.add(subjectAttendanceCollectionSubjectAttendanceToAttach);
            }
            subject.setSubjectAttendanceCollection(attachedSubjectAttendanceCollection);
            Collection<ClassSubjects> attachedClassSubjectsCollection = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionClassSubjectsToAttach : subject.getClassSubjectsCollection()) {
                classSubjectsCollectionClassSubjectsToAttach = em.getReference(classSubjectsCollectionClassSubjectsToAttach.getClass(), classSubjectsCollectionClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollection.add(classSubjectsCollectionClassSubjectsToAttach);
            }
            subject.setClassSubjectsCollection(attachedClassSubjectsCollection);
            Collection<ClassRoutine> attachedClassRoutineCollection = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionClassRoutineToAttach : subject.getClassRoutineCollection()) {
                classRoutineCollectionClassRoutineToAttach = em.getReference(classRoutineCollectionClassRoutineToAttach.getClass(), classRoutineCollectionClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollection.add(classRoutineCollectionClassRoutineToAttach);
            }
            subject.setClassRoutineCollection(attachedClassRoutineCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : subject.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            subject.setExamMarkCollection(attachedExamMarkCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : subject.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            subject.setCaMarkCollection(attachedCaMarkCollection);
            em.persist(subject);
            if (branchId != null) {
                branchId.getSubjectCollection().add(subject);
                branchId = em.merge(branchId);
            }
            if (classCategoryId != null) {
                classCategoryId.getSubjectCollection().add(subject);
                classCategoryId = em.merge(classCategoryId);
            }
            if (academicYear != null) {
                academicYear.getSubjectCollection().add(subject);
                academicYear = em.merge(academicYear);
            }
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendance : subject.getSubjectAttendanceCollection()) {
                Subject oldSubjectIdOfSubjectAttendanceCollectionSubjectAttendance = subjectAttendanceCollectionSubjectAttendance.getSubjectId();
                subjectAttendanceCollectionSubjectAttendance.setSubjectId(subject);
                subjectAttendanceCollectionSubjectAttendance = em.merge(subjectAttendanceCollectionSubjectAttendance);
                if (oldSubjectIdOfSubjectAttendanceCollectionSubjectAttendance != null) {
                    oldSubjectIdOfSubjectAttendanceCollectionSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionSubjectAttendance);
                    oldSubjectIdOfSubjectAttendanceCollectionSubjectAttendance = em.merge(oldSubjectIdOfSubjectAttendanceCollectionSubjectAttendance);
                }
            }
            for (ClassSubjects classSubjectsCollectionClassSubjects : subject.getClassSubjectsCollection()) {
                Subject oldSubjectIdOfClassSubjectsCollectionClassSubjects = classSubjectsCollectionClassSubjects.getSubjectId();
                classSubjectsCollectionClassSubjects.setSubjectId(subject);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
                if (oldSubjectIdOfClassSubjectsCollectionClassSubjects != null) {
                    oldSubjectIdOfClassSubjectsCollectionClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionClassSubjects);
                    oldSubjectIdOfClassSubjectsCollectionClassSubjects = em.merge(oldSubjectIdOfClassSubjectsCollectionClassSubjects);
                }
            }
            for (ClassRoutine classRoutineCollectionClassRoutine : subject.getClassRoutineCollection()) {
                Subject oldSubjectIdOfClassRoutineCollectionClassRoutine = classRoutineCollectionClassRoutine.getSubjectId();
                classRoutineCollectionClassRoutine.setSubjectId(subject);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
                if (oldSubjectIdOfClassRoutineCollectionClassRoutine != null) {
                    oldSubjectIdOfClassRoutineCollectionClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionClassRoutine);
                    oldSubjectIdOfClassRoutineCollectionClassRoutine = em.merge(oldSubjectIdOfClassRoutineCollectionClassRoutine);
                }
            }
            for (ExamMark examMarkCollectionExamMark : subject.getExamMarkCollection()) {
                Subject oldSubjectIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getSubjectId();
                examMarkCollectionExamMark.setSubjectId(subject);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldSubjectIdOfExamMarkCollectionExamMark != null) {
                    oldSubjectIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldSubjectIdOfExamMarkCollectionExamMark = em.merge(oldSubjectIdOfExamMarkCollectionExamMark);
                }
            }
            for (CaMark caMarkCollectionCaMark : subject.getCaMarkCollection()) {
                Subject oldSubjectIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getSubjectId();
                caMarkCollectionCaMark.setSubjectId(subject);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldSubjectIdOfCaMarkCollectionCaMark != null) {
                    oldSubjectIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldSubjectIdOfCaMarkCollectionCaMark = em.merge(oldSubjectIdOfCaMarkCollectionCaMark);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSubject(subject.getSubjectId()) != null) {
                throw new PreexistingEntityException("Subject " + subject + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Subject subject) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subject persistentSubject = em.find(Subject.class, subject.getSubjectId());
            CompanyBranch branchIdOld = persistentSubject.getBranchId();
            CompanyBranch branchIdNew = subject.getBranchId();
            ClassCategory classCategoryIdOld = persistentSubject.getClassCategoryId();
            ClassCategory classCategoryIdNew = subject.getClassCategoryId();
            AcademicYears academicYearOld = persistentSubject.getAcademicYear();
            AcademicYears academicYearNew = subject.getAcademicYear();
            Collection<SubjectAttendance> subjectAttendanceCollectionOld = persistentSubject.getSubjectAttendanceCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionNew = subject.getSubjectAttendanceCollection();
            Collection<ClassSubjects> classSubjectsCollectionOld = persistentSubject.getClassSubjectsCollection();
            Collection<ClassSubjects> classSubjectsCollectionNew = subject.getClassSubjectsCollection();
            Collection<ClassRoutine> classRoutineCollectionOld = persistentSubject.getClassRoutineCollection();
            Collection<ClassRoutine> classRoutineCollectionNew = subject.getClassRoutineCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentSubject.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = subject.getExamMarkCollection();
            Collection<CaMark> caMarkCollectionOld = persistentSubject.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = subject.getCaMarkCollection();
            List<String> illegalOrphanMessages = null;
            for (SubjectAttendance subjectAttendanceCollectionOldSubjectAttendance : subjectAttendanceCollectionOld) {
                if (!subjectAttendanceCollectionNew.contains(subjectAttendanceCollectionOldSubjectAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubjectAttendance " + subjectAttendanceCollectionOldSubjectAttendance + " since its subjectId field is not nullable.");
                }
            }
            for (ClassSubjects classSubjectsCollectionOldClassSubjects : classSubjectsCollectionOld) {
                if (!classSubjectsCollectionNew.contains(classSubjectsCollectionOldClassSubjects)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassSubjects " + classSubjectsCollectionOldClassSubjects + " since its subjectId field is not nullable.");
                }
            }
            for (ClassRoutine classRoutineCollectionOldClassRoutine : classRoutineCollectionOld) {
                if (!classRoutineCollectionNew.contains(classRoutineCollectionOldClassRoutine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassRoutine " + classRoutineCollectionOldClassRoutine + " since its subjectId field is not nullable.");
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamMark " + examMarkCollectionOldExamMark + " since its subjectId field is not nullable.");
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaMark " + caMarkCollectionOldCaMark + " since its subjectId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                subject.setBranchId(branchIdNew);
            }
            if (classCategoryIdNew != null) {
                classCategoryIdNew = em.getReference(classCategoryIdNew.getClass(), classCategoryIdNew.getCategoryId());
                subject.setClassCategoryId(classCategoryIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                subject.setAcademicYear(academicYearNew);
            }
            Collection<SubjectAttendance> attachedSubjectAttendanceCollectionNew = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendanceToAttach : subjectAttendanceCollectionNew) {
                subjectAttendanceCollectionNewSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionNewSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionNewSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollectionNew.add(subjectAttendanceCollectionNewSubjectAttendanceToAttach);
            }
            subjectAttendanceCollectionNew = attachedSubjectAttendanceCollectionNew;
            subject.setSubjectAttendanceCollection(subjectAttendanceCollectionNew);
            Collection<ClassSubjects> attachedClassSubjectsCollectionNew = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionNewClassSubjectsToAttach : classSubjectsCollectionNew) {
                classSubjectsCollectionNewClassSubjectsToAttach = em.getReference(classSubjectsCollectionNewClassSubjectsToAttach.getClass(), classSubjectsCollectionNewClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollectionNew.add(classSubjectsCollectionNewClassSubjectsToAttach);
            }
            classSubjectsCollectionNew = attachedClassSubjectsCollectionNew;
            subject.setClassSubjectsCollection(classSubjectsCollectionNew);
            Collection<ClassRoutine> attachedClassRoutineCollectionNew = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionNewClassRoutineToAttach : classRoutineCollectionNew) {
                classRoutineCollectionNewClassRoutineToAttach = em.getReference(classRoutineCollectionNewClassRoutineToAttach.getClass(), classRoutineCollectionNewClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollectionNew.add(classRoutineCollectionNewClassRoutineToAttach);
            }
            classRoutineCollectionNew = attachedClassRoutineCollectionNew;
            subject.setClassRoutineCollection(classRoutineCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            subject.setExamMarkCollection(examMarkCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            subject.setCaMarkCollection(caMarkCollectionNew);
            subject = em.merge(subject);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getSubjectCollection().remove(subject);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getSubjectCollection().add(subject);
                branchIdNew = em.merge(branchIdNew);
            }
            if (classCategoryIdOld != null && !classCategoryIdOld.equals(classCategoryIdNew)) {
                classCategoryIdOld.getSubjectCollection().remove(subject);
                classCategoryIdOld = em.merge(classCategoryIdOld);
            }
            if (classCategoryIdNew != null && !classCategoryIdNew.equals(classCategoryIdOld)) {
                classCategoryIdNew.getSubjectCollection().add(subject);
                classCategoryIdNew = em.merge(classCategoryIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getSubjectCollection().remove(subject);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getSubjectCollection().add(subject);
                academicYearNew = em.merge(academicYearNew);
            }
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendance : subjectAttendanceCollectionNew) {
                if (!subjectAttendanceCollectionOld.contains(subjectAttendanceCollectionNewSubjectAttendance)) {
                    Subject oldSubjectIdOfSubjectAttendanceCollectionNewSubjectAttendance = subjectAttendanceCollectionNewSubjectAttendance.getSubjectId();
                    subjectAttendanceCollectionNewSubjectAttendance.setSubjectId(subject);
                    subjectAttendanceCollectionNewSubjectAttendance = em.merge(subjectAttendanceCollectionNewSubjectAttendance);
                    if (oldSubjectIdOfSubjectAttendanceCollectionNewSubjectAttendance != null && !oldSubjectIdOfSubjectAttendanceCollectionNewSubjectAttendance.equals(subject)) {
                        oldSubjectIdOfSubjectAttendanceCollectionNewSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionNewSubjectAttendance);
                        oldSubjectIdOfSubjectAttendanceCollectionNewSubjectAttendance = em.merge(oldSubjectIdOfSubjectAttendanceCollectionNewSubjectAttendance);
                    }
                }
            }
            for (ClassSubjects classSubjectsCollectionNewClassSubjects : classSubjectsCollectionNew) {
                if (!classSubjectsCollectionOld.contains(classSubjectsCollectionNewClassSubjects)) {
                    Subject oldSubjectIdOfClassSubjectsCollectionNewClassSubjects = classSubjectsCollectionNewClassSubjects.getSubjectId();
                    classSubjectsCollectionNewClassSubjects.setSubjectId(subject);
                    classSubjectsCollectionNewClassSubjects = em.merge(classSubjectsCollectionNewClassSubjects);
                    if (oldSubjectIdOfClassSubjectsCollectionNewClassSubjects != null && !oldSubjectIdOfClassSubjectsCollectionNewClassSubjects.equals(subject)) {
                        oldSubjectIdOfClassSubjectsCollectionNewClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionNewClassSubjects);
                        oldSubjectIdOfClassSubjectsCollectionNewClassSubjects = em.merge(oldSubjectIdOfClassSubjectsCollectionNewClassSubjects);
                    }
                }
            }
            for (ClassRoutine classRoutineCollectionNewClassRoutine : classRoutineCollectionNew) {
                if (!classRoutineCollectionOld.contains(classRoutineCollectionNewClassRoutine)) {
                    Subject oldSubjectIdOfClassRoutineCollectionNewClassRoutine = classRoutineCollectionNewClassRoutine.getSubjectId();
                    classRoutineCollectionNewClassRoutine.setSubjectId(subject);
                    classRoutineCollectionNewClassRoutine = em.merge(classRoutineCollectionNewClassRoutine);
                    if (oldSubjectIdOfClassRoutineCollectionNewClassRoutine != null && !oldSubjectIdOfClassRoutineCollectionNewClassRoutine.equals(subject)) {
                        oldSubjectIdOfClassRoutineCollectionNewClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionNewClassRoutine);
                        oldSubjectIdOfClassRoutineCollectionNewClassRoutine = em.merge(oldSubjectIdOfClassRoutineCollectionNewClassRoutine);
                    }
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    Subject oldSubjectIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getSubjectId();
                    examMarkCollectionNewExamMark.setSubjectId(subject);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldSubjectIdOfExamMarkCollectionNewExamMark != null && !oldSubjectIdOfExamMarkCollectionNewExamMark.equals(subject)) {
                        oldSubjectIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldSubjectIdOfExamMarkCollectionNewExamMark = em.merge(oldSubjectIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    Subject oldSubjectIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getSubjectId();
                    caMarkCollectionNewCaMark.setSubjectId(subject);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldSubjectIdOfCaMarkCollectionNewCaMark != null && !oldSubjectIdOfCaMarkCollectionNewCaMark.equals(subject)) {
                        oldSubjectIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldSubjectIdOfCaMarkCollectionNewCaMark = em.merge(oldSubjectIdOfCaMarkCollectionNewCaMark);
                    }
                }
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
                Long id = subject.getSubjectId();
                if (findSubject(id) == null) {
                    throw new NonexistentEntityException("The subject with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Subject subject;
            try {
                subject = em.getReference(Subject.class, id);
                subject.getSubjectId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The subject with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SubjectAttendance> subjectAttendanceCollectionOrphanCheck = subject.getSubjectAttendanceCollection();
            for (SubjectAttendance subjectAttendanceCollectionOrphanCheckSubjectAttendance : subjectAttendanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subject (" + subject + ") cannot be destroyed since the SubjectAttendance " + subjectAttendanceCollectionOrphanCheckSubjectAttendance + " in its subjectAttendanceCollection field has a non-nullable subjectId field.");
            }
            Collection<ClassSubjects> classSubjectsCollectionOrphanCheck = subject.getClassSubjectsCollection();
            for (ClassSubjects classSubjectsCollectionOrphanCheckClassSubjects : classSubjectsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subject (" + subject + ") cannot be destroyed since the ClassSubjects " + classSubjectsCollectionOrphanCheckClassSubjects + " in its classSubjectsCollection field has a non-nullable subjectId field.");
            }
            Collection<ClassRoutine> classRoutineCollectionOrphanCheck = subject.getClassRoutineCollection();
            for (ClassRoutine classRoutineCollectionOrphanCheckClassRoutine : classRoutineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subject (" + subject + ") cannot be destroyed since the ClassRoutine " + classRoutineCollectionOrphanCheckClassRoutine + " in its classRoutineCollection field has a non-nullable subjectId field.");
            }
            Collection<ExamMark> examMarkCollectionOrphanCheck = subject.getExamMarkCollection();
            for (ExamMark examMarkCollectionOrphanCheckExamMark : examMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subject (" + subject + ") cannot be destroyed since the ExamMark " + examMarkCollectionOrphanCheckExamMark + " in its examMarkCollection field has a non-nullable subjectId field.");
            }
            Collection<CaMark> caMarkCollectionOrphanCheck = subject.getCaMarkCollection();
            for (CaMark caMarkCollectionOrphanCheckCaMark : caMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Subject (" + subject + ") cannot be destroyed since the CaMark " + caMarkCollectionOrphanCheckCaMark + " in its caMarkCollection field has a non-nullable subjectId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = subject.getBranchId();
            if (branchId != null) {
                branchId.getSubjectCollection().remove(subject);
                branchId = em.merge(branchId);
            }
            ClassCategory classCategoryId = subject.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId.getSubjectCollection().remove(subject);
                classCategoryId = em.merge(classCategoryId);
            }
            AcademicYears academicYear = subject.getAcademicYear();
            if (academicYear != null) {
                academicYear.getSubjectCollection().remove(subject);
                academicYear = em.merge(academicYear);
            }
            em.remove(subject);
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

    public List<Subject> findSubjectEntities() {
        return findSubjectEntities(true, -1, -1);
    }

    public List<Subject> findSubjectEntities(int maxResults, int firstResult) {
        return findSubjectEntities(false, maxResults, firstResult);
    }

    private List<Subject> findSubjectEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Subject.class));
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

    public Subject findSubject(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Subject.class, id);
        } finally {
            em.close();
        }
    }

    public int getSubjectCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Subject> rt = cq.from(Subject.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Collection<Subject> getSubjects(long branch_id, long academic_year_id, long class_category_id)
    {
        EntityManager em = getEntityManager();
        try{
            Query findByAcademicYearAndCategoryId = em.createNamedQuery("Class.findByAcademicYearAndCategoryId");
            findByAcademicYearAndCategoryId.setParameter("yearId", academic_year_id);
            findByAcademicYearAndCategoryId.setParameter("categoryId", class_category_id);
            
        }catch(Exception ex)
        {
            
        }
        
        return null;
    }
    
    public Collection<com.sivotek.school_management_system.entities.Class> getClassesByAcademicYearAndCategoryId(long academic_year_id, long class_category_id) 
    {
        EntityManager em = getEntityManager();
        try {
            Query findByAcademicYearAndCategoryId = em.createNamedQuery("Class.findByAcademicYearAndCategoryId");
            findByAcademicYearAndCategoryId.setParameter("yearId", academic_year_id);
            findByAcademicYearAndCategoryId.setParameter("categoryId", class_category_id);
            Collection<com.sivotek.school_management_system.entities.Class> class1 = findByAcademicYearAndCategoryId.getResultList();
            return class1;
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             Collection<com.sivotek.school_management_system.entities.Class> class1 = null;
             return class1;
        } finally {
            em.close();
        }
    }
    
    
}
