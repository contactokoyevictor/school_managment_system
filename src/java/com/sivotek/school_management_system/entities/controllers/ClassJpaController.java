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
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.PrincipalTerminalExamComment;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.SubjectAttendance;
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.ExamClassPosition;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.Class;
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
public class ClassJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(ClassJpaController.class.getName());
      
    public ClassJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
     public ClassJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(Class class1) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (class1.getPrincipalTerminalExamCommentCollection() == null) {
            class1.setPrincipalTerminalExamCommentCollection(new ArrayList<PrincipalTerminalExamComment>());
        }
        if (class1.getSubjectAttendanceCollection() == null) {
            class1.setSubjectAttendanceCollection(new ArrayList<SubjectAttendance>());
        }
        if (class1.getFormMasterTerminalExamCommentCollection() == null) {
            class1.setFormMasterTerminalExamCommentCollection(new ArrayList<FormMasterTerminalExamComment>());
        }
        if (class1.getStudentBehavouralTraitCollection() == null) {
            class1.setStudentBehavouralTraitCollection(new ArrayList<StudentBehavouralTrait>());
        }
        if (class1.getClassSubjectsCollection() == null) {
            class1.setClassSubjectsCollection(new ArrayList<ClassSubjects>());
        }
        if (class1.getExamClassPositionCollection() == null) {
            class1.setExamClassPositionCollection(new ArrayList<ExamClassPosition>());
        }
        if (class1.getStudentCollection() == null) {
            class1.setStudentCollection(new ArrayList<Student>());
        }
        if (class1.getStudentDailyAttendanceCollection() == null) {
            class1.setStudentDailyAttendanceCollection(new ArrayList<StudentDailyAttendance>());
        }
        if (class1.getClassRoutineCollection() == null) {
            class1.setClassRoutineCollection(new ArrayList<ClassRoutine>());
        }
        if (class1.getExamMarkCollection() == null) {
            class1.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (class1.getCaMarkCollection() == null) {
            class1.setCaMarkCollection(new ArrayList<CaMark>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = class1.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                class1.setBranchId(branchId);
            }
            AcademicYears academicYear = class1.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                class1.setAcademicYear(academicYear);
            }
            ClassCategory classCategoryId = class1.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId = em.getReference(classCategoryId.getClass(), classCategoryId.getCategoryId());
                class1.setClassCategoryId(classCategoryId);
            }
            Employee teacherId = class1.getTeacherId();
            if (teacherId != null) {
                teacherId = em.getReference(teacherId.getClass(), teacherId.getEmployeeId());
                class1.setTeacherId(teacherId);
            }
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollection = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach : class1.getPrincipalTerminalExamCommentCollection()) {
                principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollection.add(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach);
            }
            class1.setPrincipalTerminalExamCommentCollection(attachedPrincipalTerminalExamCommentCollection);
            Collection<SubjectAttendance> attachedSubjectAttendanceCollection = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendanceToAttach : class1.getSubjectAttendanceCollection()) {
                subjectAttendanceCollectionSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollection.add(subjectAttendanceCollectionSubjectAttendanceToAttach);
            }
            class1.setSubjectAttendanceCollection(attachedSubjectAttendanceCollection);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollection = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach : class1.getFormMasterTerminalExamCommentCollection()) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollection.add(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach);
            }
            class1.setFormMasterTerminalExamCommentCollection(attachedFormMasterTerminalExamCommentCollection);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollection = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTraitToAttach : class1.getStudentBehavouralTraitCollection()) {
                studentBehavouralTraitCollectionStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollection.add(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach);
            }
            class1.setStudentBehavouralTraitCollection(attachedStudentBehavouralTraitCollection);
            Collection<ClassSubjects> attachedClassSubjectsCollection = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionClassSubjectsToAttach : class1.getClassSubjectsCollection()) {
                classSubjectsCollectionClassSubjectsToAttach = em.getReference(classSubjectsCollectionClassSubjectsToAttach.getClass(), classSubjectsCollectionClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollection.add(classSubjectsCollectionClassSubjectsToAttach);
            }
            class1.setClassSubjectsCollection(attachedClassSubjectsCollection);
            Collection<ExamClassPosition> attachedExamClassPositionCollection = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionExamClassPositionToAttach : class1.getExamClassPositionCollection()) {
                examClassPositionCollectionExamClassPositionToAttach = em.getReference(examClassPositionCollectionExamClassPositionToAttach.getClass(), examClassPositionCollectionExamClassPositionToAttach.getId());
                attachedExamClassPositionCollection.add(examClassPositionCollectionExamClassPositionToAttach);
            }
            class1.setExamClassPositionCollection(attachedExamClassPositionCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : class1.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            class1.setStudentCollection(attachedStudentCollection);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollection = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendanceToAttach : class1.getStudentDailyAttendanceCollection()) {
                studentDailyAttendanceCollectionStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollection.add(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach);
            }
            class1.setStudentDailyAttendanceCollection(attachedStudentDailyAttendanceCollection);
            Collection<ClassRoutine> attachedClassRoutineCollection = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionClassRoutineToAttach : class1.getClassRoutineCollection()) {
                classRoutineCollectionClassRoutineToAttach = em.getReference(classRoutineCollectionClassRoutineToAttach.getClass(), classRoutineCollectionClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollection.add(classRoutineCollectionClassRoutineToAttach);
            }
            class1.setClassRoutineCollection(attachedClassRoutineCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : class1.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            class1.setExamMarkCollection(attachedExamMarkCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : class1.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            class1.setCaMarkCollection(attachedCaMarkCollection);
            em.persist(class1);
            if (branchId != null) {
                branchId.getClassCollection().add(class1);
                branchId = em.merge(branchId);
            }
            if (academicYear != null) {
                academicYear.getClassCollection().add(class1);
                academicYear = em.merge(academicYear);
            }
            if (classCategoryId != null) {
                classCategoryId.getClassCollection().add(class1);
                classCategoryId = em.merge(classCategoryId);
            }
            if (teacherId != null) {
                teacherId.getClassCollection().add(class1);
                teacherId = em.merge(teacherId);
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamComment : class1.getPrincipalTerminalExamCommentCollection()) {
                Class oldClassIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = principalTerminalExamCommentCollectionPrincipalTerminalExamComment.getClassId();
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment.setClassId(class1);
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                if (oldClassIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment != null) {
                    oldClassIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                    oldClassIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(oldClassIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendance : class1.getSubjectAttendanceCollection()) {
                Class oldClassIdOfSubjectAttendanceCollectionSubjectAttendance = subjectAttendanceCollectionSubjectAttendance.getClassId();
                subjectAttendanceCollectionSubjectAttendance.setClassId(class1);
                subjectAttendanceCollectionSubjectAttendance = em.merge(subjectAttendanceCollectionSubjectAttendance);
                if (oldClassIdOfSubjectAttendanceCollectionSubjectAttendance != null) {
                    oldClassIdOfSubjectAttendanceCollectionSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionSubjectAttendance);
                    oldClassIdOfSubjectAttendanceCollectionSubjectAttendance = em.merge(oldClassIdOfSubjectAttendanceCollectionSubjectAttendance);
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : class1.getFormMasterTerminalExamCommentCollection()) {
                Class oldClassIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getClassId();
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setClassId(class1);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                if (oldClassIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment != null) {
                    oldClassIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                    oldClassIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(oldClassIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : class1.getStudentBehavouralTraitCollection()) {
                Class oldClassIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = studentBehavouralTraitCollectionStudentBehavouralTrait.getClassId();
                studentBehavouralTraitCollectionStudentBehavouralTrait.setClassId(class1);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
                if (oldClassIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait != null) {
                    oldClassIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionStudentBehavouralTrait);
                    oldClassIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(oldClassIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait);
                }
            }
            for (ClassSubjects classSubjectsCollectionClassSubjects : class1.getClassSubjectsCollection()) {
                Class oldClassIdOfClassSubjectsCollectionClassSubjects = classSubjectsCollectionClassSubjects.getClassId();
                classSubjectsCollectionClassSubjects.setClassId(class1);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
                if (oldClassIdOfClassSubjectsCollectionClassSubjects != null) {
                    oldClassIdOfClassSubjectsCollectionClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionClassSubjects);
                    oldClassIdOfClassSubjectsCollectionClassSubjects = em.merge(oldClassIdOfClassSubjectsCollectionClassSubjects);
                }
            }
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : class1.getExamClassPositionCollection()) {
                Class oldClassIdOfExamClassPositionCollectionExamClassPosition = examClassPositionCollectionExamClassPosition.getClassId();
                examClassPositionCollectionExamClassPosition.setClassId(class1);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
                if (oldClassIdOfExamClassPositionCollectionExamClassPosition != null) {
                    oldClassIdOfExamClassPositionCollectionExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionExamClassPosition);
                    oldClassIdOfExamClassPositionCollectionExamClassPosition = em.merge(oldClassIdOfExamClassPositionCollectionExamClassPosition);
                }
            }
            for (Student studentCollectionStudent : class1.getStudentCollection()) {
                Class oldClassIdOfStudentCollectionStudent = studentCollectionStudent.getClassId();
                studentCollectionStudent.setClassId(class1);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldClassIdOfStudentCollectionStudent != null) {
                    oldClassIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldClassIdOfStudentCollectionStudent = em.merge(oldClassIdOfStudentCollectionStudent);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : class1.getStudentDailyAttendanceCollection()) {
                Class oldClassIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = studentDailyAttendanceCollectionStudentDailyAttendance.getClassId();
                studentDailyAttendanceCollectionStudentDailyAttendance.setClassId(class1);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
                if (oldClassIdOfStudentDailyAttendanceCollectionStudentDailyAttendance != null) {
                    oldClassIdOfStudentDailyAttendanceCollectionStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionStudentDailyAttendance);
                    oldClassIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = em.merge(oldClassIdOfStudentDailyAttendanceCollectionStudentDailyAttendance);
                }
            }
            for (ClassRoutine classRoutineCollectionClassRoutine : class1.getClassRoutineCollection()) {
                Class oldClassIdOfClassRoutineCollectionClassRoutine = classRoutineCollectionClassRoutine.getClassId();
                classRoutineCollectionClassRoutine.setClassId(class1);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
                if (oldClassIdOfClassRoutineCollectionClassRoutine != null) {
                    oldClassIdOfClassRoutineCollectionClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionClassRoutine);
                    oldClassIdOfClassRoutineCollectionClassRoutine = em.merge(oldClassIdOfClassRoutineCollectionClassRoutine);
                }
            }
            for (ExamMark examMarkCollectionExamMark : class1.getExamMarkCollection()) {
                Class oldClassIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getClassId();
                examMarkCollectionExamMark.setClassId(class1);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldClassIdOfExamMarkCollectionExamMark != null) {
                    oldClassIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldClassIdOfExamMarkCollectionExamMark = em.merge(oldClassIdOfExamMarkCollectionExamMark);
                }
            }
            for (CaMark caMarkCollectionCaMark : class1.getCaMarkCollection()) {
                Class oldClassIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getClassId();
                caMarkCollectionCaMark.setClassId(class1);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldClassIdOfCaMarkCollectionCaMark != null) {
                    oldClassIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldClassIdOfCaMarkCollectionCaMark = em.merge(oldClassIdOfCaMarkCollectionCaMark);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClass(class1.getClassId()) != null) {
                throw new PreexistingEntityException("Class " + class1 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Class class1) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Class persistentClass = em.find(Class.class, class1.getClassId());
            CompanyBranch branchIdOld = persistentClass.getBranchId();
            CompanyBranch branchIdNew = class1.getBranchId();
            AcademicYears academicYearOld = persistentClass.getAcademicYear();
            AcademicYears academicYearNew = class1.getAcademicYear();
            ClassCategory classCategoryIdOld = persistentClass.getClassCategoryId();
            ClassCategory classCategoryIdNew = class1.getClassCategoryId();
            Employee teacherIdOld = persistentClass.getTeacherId();
            Employee teacherIdNew = class1.getTeacherId();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOld = persistentClass.getPrincipalTerminalExamCommentCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionNew = class1.getPrincipalTerminalExamCommentCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionOld = persistentClass.getSubjectAttendanceCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionNew = class1.getSubjectAttendanceCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOld = persistentClass.getFormMasterTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionNew = class1.getFormMasterTerminalExamCommentCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOld = persistentClass.getStudentBehavouralTraitCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionNew = class1.getStudentBehavouralTraitCollection();
            Collection<ClassSubjects> classSubjectsCollectionOld = persistentClass.getClassSubjectsCollection();
            Collection<ClassSubjects> classSubjectsCollectionNew = class1.getClassSubjectsCollection();
            Collection<ExamClassPosition> examClassPositionCollectionOld = persistentClass.getExamClassPositionCollection();
            Collection<ExamClassPosition> examClassPositionCollectionNew = class1.getExamClassPositionCollection();
            Collection<Student> studentCollectionOld = persistentClass.getStudentCollection();
            Collection<Student> studentCollectionNew = class1.getStudentCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOld = persistentClass.getStudentDailyAttendanceCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionNew = class1.getStudentDailyAttendanceCollection();
            Collection<ClassRoutine> classRoutineCollectionOld = persistentClass.getClassRoutineCollection();
            Collection<ClassRoutine> classRoutineCollectionNew = class1.getClassRoutineCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentClass.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = class1.getExamMarkCollection();
            Collection<CaMark> caMarkCollectionOld = persistentClass.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = class1.getCaMarkCollection();
            List<String> illegalOrphanMessages = null;
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOld) {
                if (!principalTerminalExamCommentCollectionNew.contains(principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment + " since its classId field is not nullable.");
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionOldSubjectAttendance : subjectAttendanceCollectionOld) {
                if (!subjectAttendanceCollectionNew.contains(subjectAttendanceCollectionOldSubjectAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubjectAttendance " + subjectAttendanceCollectionOldSubjectAttendance + " since its classId field is not nullable.");
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOld) {
                if (!formMasterTerminalExamCommentCollectionNew.contains(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment + " since its classId field is not nullable.");
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOldStudentBehavouralTrait : studentBehavouralTraitCollectionOld) {
                if (!studentBehavouralTraitCollectionNew.contains(studentBehavouralTraitCollectionOldStudentBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentBehavouralTrait " + studentBehavouralTraitCollectionOldStudentBehavouralTrait + " since its classId field is not nullable.");
                }
            }
            for (ExamClassPosition examClassPositionCollectionOldExamClassPosition : examClassPositionCollectionOld) {
                if (!examClassPositionCollectionNew.contains(examClassPositionCollectionOldExamClassPosition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamClassPosition " + examClassPositionCollectionOldExamClassPosition + " since its classId field is not nullable.");
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Student " + studentCollectionOldStudent + " since its classId field is not nullable.");
                }
            }
            for (ClassRoutine classRoutineCollectionOldClassRoutine : classRoutineCollectionOld) {
                if (!classRoutineCollectionNew.contains(classRoutineCollectionOldClassRoutine)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassRoutine " + classRoutineCollectionOldClassRoutine + " since its classId field is not nullable.");
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamMark " + examMarkCollectionOldExamMark + " since its classId field is not nullable.");
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaMark " + caMarkCollectionOldCaMark + " since its classId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                class1.setBranchId(branchIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                class1.setAcademicYear(academicYearNew);
            }
            if (classCategoryIdNew != null) {
                classCategoryIdNew = em.getReference(classCategoryIdNew.getClass(), classCategoryIdNew.getCategoryId());
                class1.setClassCategoryId(classCategoryIdNew);
            }
            if (teacherIdNew != null) {
                teacherIdNew = em.getReference(teacherIdNew.getClass(), teacherIdNew.getEmployeeId());
                class1.setTeacherId(teacherIdNew);
            }
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollectionNew = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach : principalTerminalExamCommentCollectionNew) {
                principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollectionNew.add(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach);
            }
            principalTerminalExamCommentCollectionNew = attachedPrincipalTerminalExamCommentCollectionNew;
            class1.setPrincipalTerminalExamCommentCollection(principalTerminalExamCommentCollectionNew);
            Collection<SubjectAttendance> attachedSubjectAttendanceCollectionNew = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendanceToAttach : subjectAttendanceCollectionNew) {
                subjectAttendanceCollectionNewSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionNewSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionNewSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollectionNew.add(subjectAttendanceCollectionNewSubjectAttendanceToAttach);
            }
            subjectAttendanceCollectionNew = attachedSubjectAttendanceCollectionNew;
            class1.setSubjectAttendanceCollection(subjectAttendanceCollectionNew);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollectionNew = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach : formMasterTerminalExamCommentCollectionNew) {
                formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollectionNew.add(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach);
            }
            formMasterTerminalExamCommentCollectionNew = attachedFormMasterTerminalExamCommentCollectionNew;
            class1.setFormMasterTerminalExamCommentCollection(formMasterTerminalExamCommentCollectionNew);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollectionNew = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach : studentBehavouralTraitCollectionNew) {
                studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollectionNew.add(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach);
            }
            studentBehavouralTraitCollectionNew = attachedStudentBehavouralTraitCollectionNew;
            class1.setStudentBehavouralTraitCollection(studentBehavouralTraitCollectionNew);
            Collection<ClassSubjects> attachedClassSubjectsCollectionNew = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionNewClassSubjectsToAttach : classSubjectsCollectionNew) {
                classSubjectsCollectionNewClassSubjectsToAttach = em.getReference(classSubjectsCollectionNewClassSubjectsToAttach.getClass(), classSubjectsCollectionNewClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollectionNew.add(classSubjectsCollectionNewClassSubjectsToAttach);
            }
            classSubjectsCollectionNew = attachedClassSubjectsCollectionNew;
            class1.setClassSubjectsCollection(classSubjectsCollectionNew);
            Collection<ExamClassPosition> attachedExamClassPositionCollectionNew = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionNewExamClassPositionToAttach : examClassPositionCollectionNew) {
                examClassPositionCollectionNewExamClassPositionToAttach = em.getReference(examClassPositionCollectionNewExamClassPositionToAttach.getClass(), examClassPositionCollectionNewExamClassPositionToAttach.getId());
                attachedExamClassPositionCollectionNew.add(examClassPositionCollectionNewExamClassPositionToAttach);
            }
            examClassPositionCollectionNew = attachedExamClassPositionCollectionNew;
            class1.setExamClassPositionCollection(examClassPositionCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            class1.setStudentCollection(studentCollectionNew);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollectionNew = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach : studentDailyAttendanceCollectionNew) {
                studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollectionNew.add(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach);
            }
            studentDailyAttendanceCollectionNew = attachedStudentDailyAttendanceCollectionNew;
            class1.setStudentDailyAttendanceCollection(studentDailyAttendanceCollectionNew);
            Collection<ClassRoutine> attachedClassRoutineCollectionNew = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionNewClassRoutineToAttach : classRoutineCollectionNew) {
                classRoutineCollectionNewClassRoutineToAttach = em.getReference(classRoutineCollectionNewClassRoutineToAttach.getClass(), classRoutineCollectionNewClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollectionNew.add(classRoutineCollectionNewClassRoutineToAttach);
            }
            classRoutineCollectionNew = attachedClassRoutineCollectionNew;
            class1.setClassRoutineCollection(classRoutineCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            class1.setExamMarkCollection(examMarkCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            class1.setCaMarkCollection(caMarkCollectionNew);
            class1 = em.merge(class1);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getClassCollection().remove(class1);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getClassCollection().add(class1);
                branchIdNew = em.merge(branchIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getClassCollection().remove(class1);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getClassCollection().add(class1);
                academicYearNew = em.merge(academicYearNew);
            }
            if (classCategoryIdOld != null && !classCategoryIdOld.equals(classCategoryIdNew)) {
                classCategoryIdOld.getClassCollection().remove(class1);
                classCategoryIdOld = em.merge(classCategoryIdOld);
            }
            if (classCategoryIdNew != null && !classCategoryIdNew.equals(classCategoryIdOld)) {
                classCategoryIdNew.getClassCollection().add(class1);
                classCategoryIdNew = em.merge(classCategoryIdNew);
            }
            if (teacherIdOld != null && !teacherIdOld.equals(teacherIdNew)) {
                teacherIdOld.getClassCollection().remove(class1);
                teacherIdOld = em.merge(teacherIdOld);
            }
            if (teacherIdNew != null && !teacherIdNew.equals(teacherIdOld)) {
                teacherIdNew.getClassCollection().add(class1);
                teacherIdNew = em.merge(teacherIdNew);
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment : principalTerminalExamCommentCollectionNew) {
                if (!principalTerminalExamCommentCollectionOld.contains(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment)) {
                    Class oldClassIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getClassId();
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.setClassId(class1);
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    if (oldClassIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment != null && !oldClassIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.equals(class1)) {
                        oldClassIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                        oldClassIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(oldClassIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    }
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendance : subjectAttendanceCollectionNew) {
                if (!subjectAttendanceCollectionOld.contains(subjectAttendanceCollectionNewSubjectAttendance)) {
                    Class oldClassIdOfSubjectAttendanceCollectionNewSubjectAttendance = subjectAttendanceCollectionNewSubjectAttendance.getClassId();
                    subjectAttendanceCollectionNewSubjectAttendance.setClassId(class1);
                    subjectAttendanceCollectionNewSubjectAttendance = em.merge(subjectAttendanceCollectionNewSubjectAttendance);
                    if (oldClassIdOfSubjectAttendanceCollectionNewSubjectAttendance != null && !oldClassIdOfSubjectAttendanceCollectionNewSubjectAttendance.equals(class1)) {
                        oldClassIdOfSubjectAttendanceCollectionNewSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionNewSubjectAttendance);
                        oldClassIdOfSubjectAttendanceCollectionNewSubjectAttendance = em.merge(oldClassIdOfSubjectAttendanceCollectionNewSubjectAttendance);
                    }
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionNew) {
                if (!formMasterTerminalExamCommentCollectionOld.contains(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment)) {
                    Class oldClassIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getClassId();
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.setClassId(class1);
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    if (oldClassIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment != null && !oldClassIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.equals(class1)) {
                        oldClassIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                        oldClassIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(oldClassIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    }
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTrait : studentBehavouralTraitCollectionNew) {
                if (!studentBehavouralTraitCollectionOld.contains(studentBehavouralTraitCollectionNewStudentBehavouralTrait)) {
                    Class oldClassIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = studentBehavouralTraitCollectionNewStudentBehavouralTrait.getClassId();
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait.setClassId(class1);
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    if (oldClassIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait != null && !oldClassIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.equals(class1)) {
                        oldClassIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                        oldClassIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(oldClassIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    }
                }
            }
            for (ClassSubjects classSubjectsCollectionOldClassSubjects : classSubjectsCollectionOld) {
                if (!classSubjectsCollectionNew.contains(classSubjectsCollectionOldClassSubjects)) {
                    classSubjectsCollectionOldClassSubjects.setClassId(null);
                    classSubjectsCollectionOldClassSubjects = em.merge(classSubjectsCollectionOldClassSubjects);
                }
            }
            for (ClassSubjects classSubjectsCollectionNewClassSubjects : classSubjectsCollectionNew) {
                if (!classSubjectsCollectionOld.contains(classSubjectsCollectionNewClassSubjects)) {
                    Class oldClassIdOfClassSubjectsCollectionNewClassSubjects = classSubjectsCollectionNewClassSubjects.getClassId();
                    classSubjectsCollectionNewClassSubjects.setClassId(class1);
                    classSubjectsCollectionNewClassSubjects = em.merge(classSubjectsCollectionNewClassSubjects);
                    if (oldClassIdOfClassSubjectsCollectionNewClassSubjects != null && !oldClassIdOfClassSubjectsCollectionNewClassSubjects.equals(class1)) {
                        oldClassIdOfClassSubjectsCollectionNewClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionNewClassSubjects);
                        oldClassIdOfClassSubjectsCollectionNewClassSubjects = em.merge(oldClassIdOfClassSubjectsCollectionNewClassSubjects);
                    }
                }
            }
            for (ExamClassPosition examClassPositionCollectionNewExamClassPosition : examClassPositionCollectionNew) {
                if (!examClassPositionCollectionOld.contains(examClassPositionCollectionNewExamClassPosition)) {
                    Class oldClassIdOfExamClassPositionCollectionNewExamClassPosition = examClassPositionCollectionNewExamClassPosition.getClassId();
                    examClassPositionCollectionNewExamClassPosition.setClassId(class1);
                    examClassPositionCollectionNewExamClassPosition = em.merge(examClassPositionCollectionNewExamClassPosition);
                    if (oldClassIdOfExamClassPositionCollectionNewExamClassPosition != null && !oldClassIdOfExamClassPositionCollectionNewExamClassPosition.equals(class1)) {
                        oldClassIdOfExamClassPositionCollectionNewExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionNewExamClassPosition);
                        oldClassIdOfExamClassPositionCollectionNewExamClassPosition = em.merge(oldClassIdOfExamClassPositionCollectionNewExamClassPosition);
                    }
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    Class oldClassIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getClassId();
                    studentCollectionNewStudent.setClassId(class1);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldClassIdOfStudentCollectionNewStudent != null && !oldClassIdOfStudentCollectionNewStudent.equals(class1)) {
                        oldClassIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldClassIdOfStudentCollectionNewStudent = em.merge(oldClassIdOfStudentCollectionNewStudent);
                    }
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionOldStudentDailyAttendance : studentDailyAttendanceCollectionOld) {
                if (!studentDailyAttendanceCollectionNew.contains(studentDailyAttendanceCollectionOldStudentDailyAttendance)) {
                    studentDailyAttendanceCollectionOldStudentDailyAttendance.setClassId(null);
                    studentDailyAttendanceCollectionOldStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionOldStudentDailyAttendance);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendance : studentDailyAttendanceCollectionNew) {
                if (!studentDailyAttendanceCollectionOld.contains(studentDailyAttendanceCollectionNewStudentDailyAttendance)) {
                    Class oldClassIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = studentDailyAttendanceCollectionNewStudentDailyAttendance.getClassId();
                    studentDailyAttendanceCollectionNewStudentDailyAttendance.setClassId(class1);
                    studentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                    if (oldClassIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance != null && !oldClassIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.equals(class1)) {
                        oldClassIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                        oldClassIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(oldClassIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance);
                    }
                }
            }
            for (ClassRoutine classRoutineCollectionNewClassRoutine : classRoutineCollectionNew) {
                if (!classRoutineCollectionOld.contains(classRoutineCollectionNewClassRoutine)) {
                    Class oldClassIdOfClassRoutineCollectionNewClassRoutine = classRoutineCollectionNewClassRoutine.getClassId();
                    classRoutineCollectionNewClassRoutine.setClassId(class1);
                    classRoutineCollectionNewClassRoutine = em.merge(classRoutineCollectionNewClassRoutine);
                    if (oldClassIdOfClassRoutineCollectionNewClassRoutine != null && !oldClassIdOfClassRoutineCollectionNewClassRoutine.equals(class1)) {
                        oldClassIdOfClassRoutineCollectionNewClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionNewClassRoutine);
                        oldClassIdOfClassRoutineCollectionNewClassRoutine = em.merge(oldClassIdOfClassRoutineCollectionNewClassRoutine);
                    }
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    Class oldClassIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getClassId();
                    examMarkCollectionNewExamMark.setClassId(class1);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldClassIdOfExamMarkCollectionNewExamMark != null && !oldClassIdOfExamMarkCollectionNewExamMark.equals(class1)) {
                        oldClassIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldClassIdOfExamMarkCollectionNewExamMark = em.merge(oldClassIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    Class oldClassIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getClassId();
                    caMarkCollectionNewCaMark.setClassId(class1);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldClassIdOfCaMarkCollectionNewCaMark != null && !oldClassIdOfCaMarkCollectionNewCaMark.equals(class1)) {
                        oldClassIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldClassIdOfCaMarkCollectionNewCaMark = em.merge(oldClassIdOfCaMarkCollectionNewCaMark);
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
                Long id = class1.getClassId();
                if (findClass(id) == null) {
                    throw new NonexistentEntityException("The class with id " + id + " no longer exists.");
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
            Class class1;
            try {
                class1 = em.getReference(Class.class, id);
                class1.getClassId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The class1 with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOrphanCheck = class1.getPrincipalTerminalExamCommentCollection();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment + " in its principalTerminalExamCommentCollection field has a non-nullable classId field.");
            }
            Collection<SubjectAttendance> subjectAttendanceCollectionOrphanCheck = class1.getSubjectAttendanceCollection();
            for (SubjectAttendance subjectAttendanceCollectionOrphanCheckSubjectAttendance : subjectAttendanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the SubjectAttendance " + subjectAttendanceCollectionOrphanCheckSubjectAttendance + " in its subjectAttendanceCollection field has a non-nullable classId field.");
            }
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOrphanCheck = class1.getFormMasterTerminalExamCommentCollection();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment + " in its formMasterTerminalExamCommentCollection field has a non-nullable classId field.");
            }
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOrphanCheck = class1.getStudentBehavouralTraitCollection();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait : studentBehavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the StudentBehavouralTrait " + studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait + " in its studentBehavouralTraitCollection field has a non-nullable classId field.");
            }
            Collection<ExamClassPosition> examClassPositionCollectionOrphanCheck = class1.getExamClassPositionCollection();
            for (ExamClassPosition examClassPositionCollectionOrphanCheckExamClassPosition : examClassPositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the ExamClassPosition " + examClassPositionCollectionOrphanCheckExamClassPosition + " in its examClassPositionCollection field has a non-nullable classId field.");
            }
            Collection<Student> studentCollectionOrphanCheck = class1.getStudentCollection();
            for (Student studentCollectionOrphanCheckStudent : studentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the Student " + studentCollectionOrphanCheckStudent + " in its studentCollection field has a non-nullable classId field.");
            }
            Collection<ClassRoutine> classRoutineCollectionOrphanCheck = class1.getClassRoutineCollection();
            for (ClassRoutine classRoutineCollectionOrphanCheckClassRoutine : classRoutineCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the ClassRoutine " + classRoutineCollectionOrphanCheckClassRoutine + " in its classRoutineCollection field has a non-nullable classId field.");
            }
            Collection<ExamMark> examMarkCollectionOrphanCheck = class1.getExamMarkCollection();
            for (ExamMark examMarkCollectionOrphanCheckExamMark : examMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the ExamMark " + examMarkCollectionOrphanCheckExamMark + " in its examMarkCollection field has a non-nullable classId field.");
            }
            Collection<CaMark> caMarkCollectionOrphanCheck = class1.getCaMarkCollection();
            for (CaMark caMarkCollectionOrphanCheckCaMark : caMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Class (" + class1 + ") cannot be destroyed since the CaMark " + caMarkCollectionOrphanCheckCaMark + " in its caMarkCollection field has a non-nullable classId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = class1.getBranchId();
            if (branchId != null) {
                branchId.getClassCollection().remove(class1);
                branchId = em.merge(branchId);
            }
            AcademicYears academicYear = class1.getAcademicYear();
            if (academicYear != null) {
                academicYear.getClassCollection().remove(class1);
                academicYear = em.merge(academicYear);
            }
            ClassCategory classCategoryId = class1.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId.getClassCollection().remove(class1);
                classCategoryId = em.merge(classCategoryId);
            }
            Employee teacherId = class1.getTeacherId();
            if (teacherId != null) {
                teacherId.getClassCollection().remove(class1);
                teacherId = em.merge(teacherId);
            }
            Collection<ClassSubjects> classSubjectsCollection = class1.getClassSubjectsCollection();
            for (ClassSubjects classSubjectsCollectionClassSubjects : classSubjectsCollection) {
                classSubjectsCollectionClassSubjects.setClassId(null);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
            }
            Collection<StudentDailyAttendance> studentDailyAttendanceCollection = class1.getStudentDailyAttendanceCollection();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : studentDailyAttendanceCollection) {
                studentDailyAttendanceCollectionStudentDailyAttendance.setClassId(null);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
            }
            em.remove(class1);
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

    public List<Class> findClassEntities() {
        return findClassEntities(true, -1, -1);
    }

    public List<Class> findClassEntities(int maxResults, int firstResult) {
        return findClassEntities(false, maxResults, firstResult);
    }

    private List<Class> findClassEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Class.class));
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

    public Class findClass(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Class.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Class> rt = cq.from(Class.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Collection<Class> getClassesByAcademicYearAndCategoryId(long academic_year_id, long class_category_id) 
    {
        EntityManager em = getEntityManager();
        try {
            Query findByAcademicYearAndCategoryId = em.createNamedQuery("Class.findByAcademicYearAndCategoryId");
            findByAcademicYearAndCategoryId.setParameter("yearId", academic_year_id);
            findByAcademicYearAndCategoryId.setParameter("categoryId", class_category_id);
            Collection<Class> class1 = findByAcademicYearAndCategoryId.getResultList();
            return class1;
        }catch(Exception ex){
             System.out.println(ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             Collection<Class> class1 = null;
             return class1;
        } finally {
            em.close();
        }
    }
}
