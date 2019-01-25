/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.AcademicYears;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.Subject;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.Grade;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.BehavouralTrait;
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
public class AcademicYearsJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(AcademicYearsJpaController.class.getName());
      
    public AcademicYearsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AcademicYearsJpaController(){
        try{  
             
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(AcademicYears academicYears) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (academicYears.getSubjectCollection() == null) {
            academicYears.setSubjectCollection(new ArrayList<Subject>());
        }
        if (academicYears.getExamCollection() == null) {
            academicYears.setExamCollection(new ArrayList<Exam>());
        }
        if (academicYears.getGradeCollection() == null) {
            academicYears.setGradeCollection(new ArrayList<Grade>());
        }
        if (academicYears.getSchoolFeeInvoiceDetailsCollection() == null) {
            academicYears.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (academicYears.getStudentCollection() == null) {
            academicYears.setStudentCollection(new ArrayList<Student>());
        }
        if (academicYears.getStudentDailyAttendanceCollection() == null) {
            academicYears.setStudentDailyAttendanceCollection(new ArrayList<StudentDailyAttendance>());
        }
        if (academicYears.getTermCollection() == null) {
            academicYears.setTermCollection(new ArrayList<Term>());
        }
        if (academicYears.getClassCollection() == null) {
            academicYears.setClassCollection(new ArrayList<Class>());
        }
        if (academicYears.getBehavouralTraitCollection() == null) {
            academicYears.setBehavouralTraitCollection(new ArrayList<BehavouralTrait>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = academicYears.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                academicYears.setBranchId(branchId);
            }
            Collection<Subject> attachedSubjectCollection = new ArrayList<Subject>();
            for (Subject subjectCollectionSubjectToAttach : academicYears.getSubjectCollection()) {
                subjectCollectionSubjectToAttach = em.getReference(subjectCollectionSubjectToAttach.getClass(), subjectCollectionSubjectToAttach.getSubjectId());
                attachedSubjectCollection.add(subjectCollectionSubjectToAttach);
            }
            academicYears.setSubjectCollection(attachedSubjectCollection);
            Collection<Exam> attachedExamCollection = new ArrayList<Exam>();
            for (Exam examCollectionExamToAttach : academicYears.getExamCollection()) {
                examCollectionExamToAttach = em.getReference(examCollectionExamToAttach.getClass(), examCollectionExamToAttach.getExamId());
                attachedExamCollection.add(examCollectionExamToAttach);
            }
            academicYears.setExamCollection(attachedExamCollection);
            Collection<Grade> attachedGradeCollection = new ArrayList<Grade>();
            for (Grade gradeCollectionGradeToAttach : academicYears.getGradeCollection()) {
                gradeCollectionGradeToAttach = em.getReference(gradeCollectionGradeToAttach.getClass(), gradeCollectionGradeToAttach.getGradeId());
                attachedGradeCollection.add(gradeCollectionGradeToAttach);
            }
            academicYears.setGradeCollection(attachedGradeCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : academicYears.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            academicYears.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : academicYears.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            academicYears.setStudentCollection(attachedStudentCollection);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollection = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendanceToAttach : academicYears.getStudentDailyAttendanceCollection()) {
                studentDailyAttendanceCollectionStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollection.add(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach);
            }
            academicYears.setStudentDailyAttendanceCollection(attachedStudentDailyAttendanceCollection);
            Collection<Term> attachedTermCollection = new ArrayList<Term>();
            for (Term termCollectionTermToAttach : academicYears.getTermCollection()) {
                termCollectionTermToAttach = em.getReference(termCollectionTermToAttach.getClass(), termCollectionTermToAttach.getTermId());
                attachedTermCollection.add(termCollectionTermToAttach);
            }
            academicYears.setTermCollection(attachedTermCollection);
            Collection<Class> attachedClassCollection = new ArrayList<Class>();
            for (Class classCollectionClassToAttach : academicYears.getClassCollection()) {
                classCollectionClassToAttach = em.getReference(classCollectionClassToAttach.getClass(), classCollectionClassToAttach.getClassId());
                attachedClassCollection.add(classCollectionClassToAttach);
            }
            academicYears.setClassCollection(attachedClassCollection);
            Collection<BehavouralTrait> attachedBehavouralTraitCollection = new ArrayList<BehavouralTrait>();
            for (BehavouralTrait behavouralTraitCollectionBehavouralTraitToAttach : academicYears.getBehavouralTraitCollection()) {
                behavouralTraitCollectionBehavouralTraitToAttach = em.getReference(behavouralTraitCollectionBehavouralTraitToAttach.getClass(), behavouralTraitCollectionBehavouralTraitToAttach.getId());
                attachedBehavouralTraitCollection.add(behavouralTraitCollectionBehavouralTraitToAttach);
            }
            academicYears.setBehavouralTraitCollection(attachedBehavouralTraitCollection);
            em.persist(academicYears);
            if (branchId != null) {
                branchId.getAcademicYearsCollection().add(academicYears);
                branchId = em.merge(branchId);
            }
            for (Subject subjectCollectionSubject : academicYears.getSubjectCollection()) {
                AcademicYears oldAcademicYearOfSubjectCollectionSubject = subjectCollectionSubject.getAcademicYear();
                subjectCollectionSubject.setAcademicYear(academicYears);
                subjectCollectionSubject = em.merge(subjectCollectionSubject);
                if (oldAcademicYearOfSubjectCollectionSubject != null) {
                    oldAcademicYearOfSubjectCollectionSubject.getSubjectCollection().remove(subjectCollectionSubject);
                    oldAcademicYearOfSubjectCollectionSubject = em.merge(oldAcademicYearOfSubjectCollectionSubject);
                }
            }
            for (Exam examCollectionExam : academicYears.getExamCollection()) {
                AcademicYears oldAcademicYearOfExamCollectionExam = examCollectionExam.getAcademicYear();
                examCollectionExam.setAcademicYear(academicYears);
                examCollectionExam = em.merge(examCollectionExam);
                if (oldAcademicYearOfExamCollectionExam != null) {
                    oldAcademicYearOfExamCollectionExam.getExamCollection().remove(examCollectionExam);
                    oldAcademicYearOfExamCollectionExam = em.merge(oldAcademicYearOfExamCollectionExam);
                }
            }
            for (Grade gradeCollectionGrade : academicYears.getGradeCollection()) {
                AcademicYears oldAcademicYearOfGradeCollectionGrade = gradeCollectionGrade.getAcademicYear();
                gradeCollectionGrade.setAcademicYear(academicYears);
                gradeCollectionGrade = em.merge(gradeCollectionGrade);
                if (oldAcademicYearOfGradeCollectionGrade != null) {
                    oldAcademicYearOfGradeCollectionGrade.getGradeCollection().remove(gradeCollectionGrade);
                    oldAcademicYearOfGradeCollectionGrade = em.merge(oldAcademicYearOfGradeCollectionGrade);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : academicYears.getSchoolFeeInvoiceDetailsCollection()) {
                AcademicYears oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getAcademicYear();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setAcademicYear(academicYears);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            for (Student studentCollectionStudent : academicYears.getStudentCollection()) {
                AcademicYears oldAcademicYearOfStudentCollectionStudent = studentCollectionStudent.getAcademicYear();
                studentCollectionStudent.setAcademicYear(academicYears);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldAcademicYearOfStudentCollectionStudent != null) {
                    oldAcademicYearOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldAcademicYearOfStudentCollectionStudent = em.merge(oldAcademicYearOfStudentCollectionStudent);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : academicYears.getStudentDailyAttendanceCollection()) {
                AcademicYears oldAcademicYearOfStudentDailyAttendanceCollectionStudentDailyAttendance = studentDailyAttendanceCollectionStudentDailyAttendance.getAcademicYear();
                studentDailyAttendanceCollectionStudentDailyAttendance.setAcademicYear(academicYears);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
                if (oldAcademicYearOfStudentDailyAttendanceCollectionStudentDailyAttendance != null) {
                    oldAcademicYearOfStudentDailyAttendanceCollectionStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionStudentDailyAttendance);
                    oldAcademicYearOfStudentDailyAttendanceCollectionStudentDailyAttendance = em.merge(oldAcademicYearOfStudentDailyAttendanceCollectionStudentDailyAttendance);
                }
            }
            for (Term termCollectionTerm : academicYears.getTermCollection()) {
                AcademicYears oldAcademicYearOfTermCollectionTerm = termCollectionTerm.getAcademicYear();
                termCollectionTerm.setAcademicYear(academicYears);
                termCollectionTerm = em.merge(termCollectionTerm);
                if (oldAcademicYearOfTermCollectionTerm != null) {
                    oldAcademicYearOfTermCollectionTerm.getTermCollection().remove(termCollectionTerm);
                    oldAcademicYearOfTermCollectionTerm = em.merge(oldAcademicYearOfTermCollectionTerm);
                }
            }
            for (Class classCollectionClass : academicYears.getClassCollection()) {
                AcademicYears oldAcademicYearOfClassCollectionClass = classCollectionClass.getAcademicYear();
                classCollectionClass.setAcademicYear(academicYears);
                classCollectionClass = em.merge(classCollectionClass);
                if (oldAcademicYearOfClassCollectionClass != null) {
                    oldAcademicYearOfClassCollectionClass.getClassCollection().remove(classCollectionClass);
                    oldAcademicYearOfClassCollectionClass = em.merge(oldAcademicYearOfClassCollectionClass);
                }
            }
            for (BehavouralTrait behavouralTraitCollectionBehavouralTrait : academicYears.getBehavouralTraitCollection()) {
                AcademicYears oldAcademicYearOfBehavouralTraitCollectionBehavouralTrait = behavouralTraitCollectionBehavouralTrait.getAcademicYear();
                behavouralTraitCollectionBehavouralTrait.setAcademicYear(academicYears);
                behavouralTraitCollectionBehavouralTrait = em.merge(behavouralTraitCollectionBehavouralTrait);
                if (oldAcademicYearOfBehavouralTraitCollectionBehavouralTrait != null) {
                    oldAcademicYearOfBehavouralTraitCollectionBehavouralTrait.getBehavouralTraitCollection().remove(behavouralTraitCollectionBehavouralTrait);
                    oldAcademicYearOfBehavouralTraitCollectionBehavouralTrait = em.merge(oldAcademicYearOfBehavouralTraitCollectionBehavouralTrait);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAcademicYears(academicYears.getYearId()) != null) {
                throw new PreexistingEntityException("AcademicYears " + academicYears + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AcademicYears academicYears) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AcademicYears persistentAcademicYears = em.find(AcademicYears.class, academicYears.getYearId());
            CompanyBranch branchIdOld = persistentAcademicYears.getBranchId();
            CompanyBranch branchIdNew = academicYears.getBranchId();
            Collection<Subject> subjectCollectionOld = persistentAcademicYears.getSubjectCollection();
            Collection<Subject> subjectCollectionNew = academicYears.getSubjectCollection();
            Collection<Exam> examCollectionOld = persistentAcademicYears.getExamCollection();
            Collection<Exam> examCollectionNew = academicYears.getExamCollection();
            Collection<Grade> gradeCollectionOld = persistentAcademicYears.getGradeCollection();
            Collection<Grade> gradeCollectionNew = academicYears.getGradeCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentAcademicYears.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = academicYears.getSchoolFeeInvoiceDetailsCollection();
            Collection<Student> studentCollectionOld = persistentAcademicYears.getStudentCollection();
            Collection<Student> studentCollectionNew = academicYears.getStudentCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOld = persistentAcademicYears.getStudentDailyAttendanceCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionNew = academicYears.getStudentDailyAttendanceCollection();
            Collection<Term> termCollectionOld = persistentAcademicYears.getTermCollection();
            Collection<Term> termCollectionNew = academicYears.getTermCollection();
            Collection<Class> classCollectionOld = persistentAcademicYears.getClassCollection();
            Collection<Class> classCollectionNew = academicYears.getClassCollection();
            Collection<BehavouralTrait> behavouralTraitCollectionOld = persistentAcademicYears.getBehavouralTraitCollection();
            Collection<BehavouralTrait> behavouralTraitCollectionNew = academicYears.getBehavouralTraitCollection();
            List<String> illegalOrphanMessages = null;
            for (Exam examCollectionOldExam : examCollectionOld) {
                if (!examCollectionNew.contains(examCollectionOldExam)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Exam " + examCollectionOldExam + " since its academicYear field is not nullable.");
                }
            }
            for (Grade gradeCollectionOldGrade : gradeCollectionOld) {
                if (!gradeCollectionNew.contains(gradeCollectionOldGrade)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grade " + gradeCollectionOldGrade + " since its academicYear field is not nullable.");
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails + " since its academicYear field is not nullable.");
                }
            }
            for (Term termCollectionOldTerm : termCollectionOld) {
                if (!termCollectionNew.contains(termCollectionOldTerm)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Term " + termCollectionOldTerm + " since its academicYear field is not nullable.");
                }
            }
            for (BehavouralTrait behavouralTraitCollectionOldBehavouralTrait : behavouralTraitCollectionOld) {
                if (!behavouralTraitCollectionNew.contains(behavouralTraitCollectionOldBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BehavouralTrait " + behavouralTraitCollectionOldBehavouralTrait + " since its academicYear field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                academicYears.setBranchId(branchIdNew);
            }
            Collection<Subject> attachedSubjectCollectionNew = new ArrayList<Subject>();
            for (Subject subjectCollectionNewSubjectToAttach : subjectCollectionNew) {
                subjectCollectionNewSubjectToAttach = em.getReference(subjectCollectionNewSubjectToAttach.getClass(), subjectCollectionNewSubjectToAttach.getSubjectId());
                attachedSubjectCollectionNew.add(subjectCollectionNewSubjectToAttach);
            }
            subjectCollectionNew = attachedSubjectCollectionNew;
            academicYears.setSubjectCollection(subjectCollectionNew);
            Collection<Exam> attachedExamCollectionNew = new ArrayList<Exam>();
            for (Exam examCollectionNewExamToAttach : examCollectionNew) {
                examCollectionNewExamToAttach = em.getReference(examCollectionNewExamToAttach.getClass(), examCollectionNewExamToAttach.getExamId());
                attachedExamCollectionNew.add(examCollectionNewExamToAttach);
            }
            examCollectionNew = attachedExamCollectionNew;
            academicYears.setExamCollection(examCollectionNew);
            Collection<Grade> attachedGradeCollectionNew = new ArrayList<Grade>();
            for (Grade gradeCollectionNewGradeToAttach : gradeCollectionNew) {
                gradeCollectionNewGradeToAttach = em.getReference(gradeCollectionNewGradeToAttach.getClass(), gradeCollectionNewGradeToAttach.getGradeId());
                attachedGradeCollectionNew.add(gradeCollectionNewGradeToAttach);
            }
            gradeCollectionNew = attachedGradeCollectionNew;
            academicYears.setGradeCollection(gradeCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            academicYears.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            academicYears.setStudentCollection(studentCollectionNew);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollectionNew = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach : studentDailyAttendanceCollectionNew) {
                studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollectionNew.add(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach);
            }
            studentDailyAttendanceCollectionNew = attachedStudentDailyAttendanceCollectionNew;
            academicYears.setStudentDailyAttendanceCollection(studentDailyAttendanceCollectionNew);
            Collection<Term> attachedTermCollectionNew = new ArrayList<Term>();
            for (Term termCollectionNewTermToAttach : termCollectionNew) {
                termCollectionNewTermToAttach = em.getReference(termCollectionNewTermToAttach.getClass(), termCollectionNewTermToAttach.getTermId());
                attachedTermCollectionNew.add(termCollectionNewTermToAttach);
            }
            termCollectionNew = attachedTermCollectionNew;
            academicYears.setTermCollection(termCollectionNew);
            Collection<Class> attachedClassCollectionNew = new ArrayList<Class>();
            for (Class classCollectionNewClassToAttach : classCollectionNew) {
                classCollectionNewClassToAttach = em.getReference(classCollectionNewClassToAttach.getClass(), classCollectionNewClassToAttach.getClassId());
                attachedClassCollectionNew.add(classCollectionNewClassToAttach);
            }
            classCollectionNew = attachedClassCollectionNew;
            academicYears.setClassCollection(classCollectionNew);
            Collection<BehavouralTrait> attachedBehavouralTraitCollectionNew = new ArrayList<BehavouralTrait>();
            for (BehavouralTrait behavouralTraitCollectionNewBehavouralTraitToAttach : behavouralTraitCollectionNew) {
                behavouralTraitCollectionNewBehavouralTraitToAttach = em.getReference(behavouralTraitCollectionNewBehavouralTraitToAttach.getClass(), behavouralTraitCollectionNewBehavouralTraitToAttach.getId());
                attachedBehavouralTraitCollectionNew.add(behavouralTraitCollectionNewBehavouralTraitToAttach);
            }
            behavouralTraitCollectionNew = attachedBehavouralTraitCollectionNew;
            academicYears.setBehavouralTraitCollection(behavouralTraitCollectionNew);
            academicYears = em.merge(academicYears);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getAcademicYearsCollection().remove(academicYears);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getAcademicYearsCollection().add(academicYears);
                branchIdNew = em.merge(branchIdNew);
            }
            for (Subject subjectCollectionOldSubject : subjectCollectionOld) {
                if (!subjectCollectionNew.contains(subjectCollectionOldSubject)) {
                    subjectCollectionOldSubject.setAcademicYear(null);
                    subjectCollectionOldSubject = em.merge(subjectCollectionOldSubject);
                }
            }
            for (Subject subjectCollectionNewSubject : subjectCollectionNew) {
                if (!subjectCollectionOld.contains(subjectCollectionNewSubject)) {
                    AcademicYears oldAcademicYearOfSubjectCollectionNewSubject = subjectCollectionNewSubject.getAcademicYear();
                    subjectCollectionNewSubject.setAcademicYear(academicYears);
                    subjectCollectionNewSubject = em.merge(subjectCollectionNewSubject);
                    if (oldAcademicYearOfSubjectCollectionNewSubject != null && !oldAcademicYearOfSubjectCollectionNewSubject.equals(academicYears)) {
                        oldAcademicYearOfSubjectCollectionNewSubject.getSubjectCollection().remove(subjectCollectionNewSubject);
                        oldAcademicYearOfSubjectCollectionNewSubject = em.merge(oldAcademicYearOfSubjectCollectionNewSubject);
                    }
                }
            }
            for (Exam examCollectionNewExam : examCollectionNew) {
                if (!examCollectionOld.contains(examCollectionNewExam)) {
                    AcademicYears oldAcademicYearOfExamCollectionNewExam = examCollectionNewExam.getAcademicYear();
                    examCollectionNewExam.setAcademicYear(academicYears);
                    examCollectionNewExam = em.merge(examCollectionNewExam);
                    if (oldAcademicYearOfExamCollectionNewExam != null && !oldAcademicYearOfExamCollectionNewExam.equals(academicYears)) {
                        oldAcademicYearOfExamCollectionNewExam.getExamCollection().remove(examCollectionNewExam);
                        oldAcademicYearOfExamCollectionNewExam = em.merge(oldAcademicYearOfExamCollectionNewExam);
                    }
                }
            }
            for (Grade gradeCollectionNewGrade : gradeCollectionNew) {
                if (!gradeCollectionOld.contains(gradeCollectionNewGrade)) {
                    AcademicYears oldAcademicYearOfGradeCollectionNewGrade = gradeCollectionNewGrade.getAcademicYear();
                    gradeCollectionNewGrade.setAcademicYear(academicYears);
                    gradeCollectionNewGrade = em.merge(gradeCollectionNewGrade);
                    if (oldAcademicYearOfGradeCollectionNewGrade != null && !oldAcademicYearOfGradeCollectionNewGrade.equals(academicYears)) {
                        oldAcademicYearOfGradeCollectionNewGrade.getGradeCollection().remove(gradeCollectionNewGrade);
                        oldAcademicYearOfGradeCollectionNewGrade = em.merge(oldAcademicYearOfGradeCollectionNewGrade);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    AcademicYears oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getAcademicYear();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setAcademicYear(academicYears);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(academicYears)) {
                        oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldAcademicYearOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setAcademicYear(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    AcademicYears oldAcademicYearOfStudentCollectionNewStudent = studentCollectionNewStudent.getAcademicYear();
                    studentCollectionNewStudent.setAcademicYear(academicYears);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldAcademicYearOfStudentCollectionNewStudent != null && !oldAcademicYearOfStudentCollectionNewStudent.equals(academicYears)) {
                        oldAcademicYearOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldAcademicYearOfStudentCollectionNewStudent = em.merge(oldAcademicYearOfStudentCollectionNewStudent);
                    }
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionOldStudentDailyAttendance : studentDailyAttendanceCollectionOld) {
                if (!studentDailyAttendanceCollectionNew.contains(studentDailyAttendanceCollectionOldStudentDailyAttendance)) {
                    studentDailyAttendanceCollectionOldStudentDailyAttendance.setAcademicYear(null);
                    studentDailyAttendanceCollectionOldStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionOldStudentDailyAttendance);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendance : studentDailyAttendanceCollectionNew) {
                if (!studentDailyAttendanceCollectionOld.contains(studentDailyAttendanceCollectionNewStudentDailyAttendance)) {
                    AcademicYears oldAcademicYearOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = studentDailyAttendanceCollectionNewStudentDailyAttendance.getAcademicYear();
                    studentDailyAttendanceCollectionNewStudentDailyAttendance.setAcademicYear(academicYears);
                    studentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                    if (oldAcademicYearOfStudentDailyAttendanceCollectionNewStudentDailyAttendance != null && !oldAcademicYearOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.equals(academicYears)) {
                        oldAcademicYearOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                        oldAcademicYearOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(oldAcademicYearOfStudentDailyAttendanceCollectionNewStudentDailyAttendance);
                    }
                }
            }
            for (Term termCollectionNewTerm : termCollectionNew) {
                if (!termCollectionOld.contains(termCollectionNewTerm)) {
                    AcademicYears oldAcademicYearOfTermCollectionNewTerm = termCollectionNewTerm.getAcademicYear();
                    termCollectionNewTerm.setAcademicYear(academicYears);
                    termCollectionNewTerm = em.merge(termCollectionNewTerm);
                    if (oldAcademicYearOfTermCollectionNewTerm != null && !oldAcademicYearOfTermCollectionNewTerm.equals(academicYears)) {
                        oldAcademicYearOfTermCollectionNewTerm.getTermCollection().remove(termCollectionNewTerm);
                        oldAcademicYearOfTermCollectionNewTerm = em.merge(oldAcademicYearOfTermCollectionNewTerm);
                    }
                }
            }
            for (Class classCollectionOldClass : classCollectionOld) {
                if (!classCollectionNew.contains(classCollectionOldClass)) {
                    classCollectionOldClass.setAcademicYear(null);
                    classCollectionOldClass = em.merge(classCollectionOldClass);
                }
            }
            for (Class classCollectionNewClass : classCollectionNew) {
                if (!classCollectionOld.contains(classCollectionNewClass)) {
                    AcademicYears oldAcademicYearOfClassCollectionNewClass = classCollectionNewClass.getAcademicYear();
                    classCollectionNewClass.setAcademicYear(academicYears);
                    classCollectionNewClass = em.merge(classCollectionNewClass);
                    if (oldAcademicYearOfClassCollectionNewClass != null && !oldAcademicYearOfClassCollectionNewClass.equals(academicYears)) {
                        oldAcademicYearOfClassCollectionNewClass.getClassCollection().remove(classCollectionNewClass);
                        oldAcademicYearOfClassCollectionNewClass = em.merge(oldAcademicYearOfClassCollectionNewClass);
                    }
                }
            }
            for (BehavouralTrait behavouralTraitCollectionNewBehavouralTrait : behavouralTraitCollectionNew) {
                if (!behavouralTraitCollectionOld.contains(behavouralTraitCollectionNewBehavouralTrait)) {
                    AcademicYears oldAcademicYearOfBehavouralTraitCollectionNewBehavouralTrait = behavouralTraitCollectionNewBehavouralTrait.getAcademicYear();
                    behavouralTraitCollectionNewBehavouralTrait.setAcademicYear(academicYears);
                    behavouralTraitCollectionNewBehavouralTrait = em.merge(behavouralTraitCollectionNewBehavouralTrait);
                    if (oldAcademicYearOfBehavouralTraitCollectionNewBehavouralTrait != null && !oldAcademicYearOfBehavouralTraitCollectionNewBehavouralTrait.equals(academicYears)) {
                        oldAcademicYearOfBehavouralTraitCollectionNewBehavouralTrait.getBehavouralTraitCollection().remove(behavouralTraitCollectionNewBehavouralTrait);
                        oldAcademicYearOfBehavouralTraitCollectionNewBehavouralTrait = em.merge(oldAcademicYearOfBehavouralTraitCollectionNewBehavouralTrait);
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
                Long id = academicYears.getYearId();
                if (findAcademicYears(id) == null) {
                    throw new NonexistentEntityException("The academicYears with id " + id + " no longer exists.");
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
            AcademicYears academicYears;
            try {
                academicYears = em.getReference(AcademicYears.class, id);
                academicYears.getYearId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The academicYears with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Exam> examCollectionOrphanCheck = academicYears.getExamCollection();
            for (Exam examCollectionOrphanCheckExam : examCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AcademicYears (" + academicYears + ") cannot be destroyed since the Exam " + examCollectionOrphanCheckExam + " in its examCollection field has a non-nullable academicYear field.");
            }
            Collection<Grade> gradeCollectionOrphanCheck = academicYears.getGradeCollection();
            for (Grade gradeCollectionOrphanCheckGrade : gradeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AcademicYears (" + academicYears + ") cannot be destroyed since the Grade " + gradeCollectionOrphanCheckGrade + " in its gradeCollection field has a non-nullable academicYear field.");
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOrphanCheck = academicYears.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AcademicYears (" + academicYears + ") cannot be destroyed since the SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails + " in its schoolFeeInvoiceDetailsCollection field has a non-nullable academicYear field.");
            }
            Collection<Term> termCollectionOrphanCheck = academicYears.getTermCollection();
            for (Term termCollectionOrphanCheckTerm : termCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AcademicYears (" + academicYears + ") cannot be destroyed since the Term " + termCollectionOrphanCheckTerm + " in its termCollection field has a non-nullable academicYear field.");
            }
            Collection<BehavouralTrait> behavouralTraitCollectionOrphanCheck = academicYears.getBehavouralTraitCollection();
            for (BehavouralTrait behavouralTraitCollectionOrphanCheckBehavouralTrait : behavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This AcademicYears (" + academicYears + ") cannot be destroyed since the BehavouralTrait " + behavouralTraitCollectionOrphanCheckBehavouralTrait + " in its behavouralTraitCollection field has a non-nullable academicYear field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = academicYears.getBranchId();
            if (branchId != null) {
                branchId.getAcademicYearsCollection().remove(academicYears);
                branchId = em.merge(branchId);
            }
            Collection<Subject> subjectCollection = academicYears.getSubjectCollection();
            for (Subject subjectCollectionSubject : subjectCollection) {
                subjectCollectionSubject.setAcademicYear(null);
                subjectCollectionSubject = em.merge(subjectCollectionSubject);
            }
            Collection<Student> studentCollection = academicYears.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setAcademicYear(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<StudentDailyAttendance> studentDailyAttendanceCollection = academicYears.getStudentDailyAttendanceCollection();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : studentDailyAttendanceCollection) {
                studentDailyAttendanceCollectionStudentDailyAttendance.setAcademicYear(null);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
            }
            Collection<Class> classCollection = academicYears.getClassCollection();
            for (Class classCollectionClass : classCollection) {
                classCollectionClass.setAcademicYear(null);
                classCollectionClass = em.merge(classCollectionClass);
            }
            em.remove(academicYears);
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

    public List<AcademicYears> findAcademicYearsEntities() {
        return findAcademicYearsEntities(true, -1, -1);
    }

    public List<AcademicYears> findAcademicYearsEntities(int maxResults, int firstResult) {
        return findAcademicYearsEntities(false, maxResults, firstResult);
    }

    private List<AcademicYears> findAcademicYearsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AcademicYears.class));
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

    public AcademicYears findAcademicYears(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AcademicYears.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcademicYearsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AcademicYears> rt = cq.from(AcademicYears.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Collection<AcademicYears> findByBranchId(CompanyBranch companyBranch) 
    {
        EntityManager em = getEntityManager();
        try {
            Query findByBranchId = em.createNamedQuery("AcademicYears.findByBranchId");
            findByBranchId.setParameter("branchId", companyBranch);
            Collection<AcademicYears> academicYears = new ArrayList<>();
            academicYears = findByBranchId.getResultList();
            return academicYears;
        }catch(Exception ex){
             System.out.println("-------Exception Occoured-------"+ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             Collection<AcademicYears> academicYears = new ArrayList<>();
             return academicYears;
        } finally {
            em.close();
        }
    }
}
