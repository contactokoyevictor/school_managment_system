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
import com.sivotek.school_management_system.entities.Section;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.PrincipalTerminalExamComment;
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
import com.sivotek.school_management_system.entities.ClassSubjects;
import com.sivotek.school_management_system.entities.ExamClassPosition;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.Term;
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
public class TermJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(TermJpaController.class.getName());
      
    public TermJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TermJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(Term term) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (term.getSectionCollection() == null) {
            term.setSectionCollection(new ArrayList<Section>());
        }
        if (term.getPrincipalTerminalExamCommentCollection() == null) {
            term.setPrincipalTerminalExamCommentCollection(new ArrayList<PrincipalTerminalExamComment>());
        }
        if (term.getFormMasterTerminalExamCommentCollection() == null) {
            term.setFormMasterTerminalExamCommentCollection(new ArrayList<FormMasterTerminalExamComment>());
        }
        if (term.getExamCollection() == null) {
            term.setExamCollection(new ArrayList<Exam>());
        }
        if (term.getStudentBehavouralTraitCollection() == null) {
            term.setStudentBehavouralTraitCollection(new ArrayList<StudentBehavouralTrait>());
        }
        if (term.getClassSubjectsCollection() == null) {
            term.setClassSubjectsCollection(new ArrayList<ClassSubjects>());
        }
        if (term.getExamClassPositionCollection() == null) {
            term.setExamClassPositionCollection(new ArrayList<ExamClassPosition>());
        }
        if (term.getSchoolFeeInvoiceDetailsCollection() == null) {
            term.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (term.getStudentCollection() == null) {
            term.setStudentCollection(new ArrayList<Student>());
        }
        if (term.getStudentDailyAttendanceCollection() == null) {
            term.setStudentDailyAttendanceCollection(new ArrayList<StudentDailyAttendance>());
        }
        if (term.getClassRoutineCollection() == null) {
            term.setClassRoutineCollection(new ArrayList<ClassRoutine>());
        }
        if (term.getExamMarkCollection() == null) {
            term.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (term.getCaMarkCollection() == null) {
            term.setCaMarkCollection(new ArrayList<CaMark>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = term.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                term.setBranchId(branchId);
            }
            AcademicYears academicYear = term.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                term.setAcademicYear(academicYear);
            }
            Collection<Section> attachedSectionCollection = new ArrayList<Section>();
            for (Section sectionCollectionSectionToAttach : term.getSectionCollection()) {
                sectionCollectionSectionToAttach = em.getReference(sectionCollectionSectionToAttach.getClass(), sectionCollectionSectionToAttach.getSectionId());
                attachedSectionCollection.add(sectionCollectionSectionToAttach);
            }
            term.setSectionCollection(attachedSectionCollection);
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollection = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach : term.getPrincipalTerminalExamCommentCollection()) {
                principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollection.add(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach);
            }
            term.setPrincipalTerminalExamCommentCollection(attachedPrincipalTerminalExamCommentCollection);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollection = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach : term.getFormMasterTerminalExamCommentCollection()) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollection.add(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach);
            }
            term.setFormMasterTerminalExamCommentCollection(attachedFormMasterTerminalExamCommentCollection);
            Collection<Exam> attachedExamCollection = new ArrayList<Exam>();
            for (Exam examCollectionExamToAttach : term.getExamCollection()) {
                examCollectionExamToAttach = em.getReference(examCollectionExamToAttach.getClass(), examCollectionExamToAttach.getExamId());
                attachedExamCollection.add(examCollectionExamToAttach);
            }
            term.setExamCollection(attachedExamCollection);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollection = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTraitToAttach : term.getStudentBehavouralTraitCollection()) {
                studentBehavouralTraitCollectionStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollection.add(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach);
            }
            term.setStudentBehavouralTraitCollection(attachedStudentBehavouralTraitCollection);
            Collection<ClassSubjects> attachedClassSubjectsCollection = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionClassSubjectsToAttach : term.getClassSubjectsCollection()) {
                classSubjectsCollectionClassSubjectsToAttach = em.getReference(classSubjectsCollectionClassSubjectsToAttach.getClass(), classSubjectsCollectionClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollection.add(classSubjectsCollectionClassSubjectsToAttach);
            }
            term.setClassSubjectsCollection(attachedClassSubjectsCollection);
            Collection<ExamClassPosition> attachedExamClassPositionCollection = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionExamClassPositionToAttach : term.getExamClassPositionCollection()) {
                examClassPositionCollectionExamClassPositionToAttach = em.getReference(examClassPositionCollectionExamClassPositionToAttach.getClass(), examClassPositionCollectionExamClassPositionToAttach.getId());
                attachedExamClassPositionCollection.add(examClassPositionCollectionExamClassPositionToAttach);
            }
            term.setExamClassPositionCollection(attachedExamClassPositionCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : term.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            term.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : term.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            term.setStudentCollection(attachedStudentCollection);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollection = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendanceToAttach : term.getStudentDailyAttendanceCollection()) {
                studentDailyAttendanceCollectionStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollection.add(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach);
            }
            term.setStudentDailyAttendanceCollection(attachedStudentDailyAttendanceCollection);
            Collection<ClassRoutine> attachedClassRoutineCollection = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionClassRoutineToAttach : term.getClassRoutineCollection()) {
                classRoutineCollectionClassRoutineToAttach = em.getReference(classRoutineCollectionClassRoutineToAttach.getClass(), classRoutineCollectionClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollection.add(classRoutineCollectionClassRoutineToAttach);
            }
            term.setClassRoutineCollection(attachedClassRoutineCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : term.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            term.setExamMarkCollection(attachedExamMarkCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : term.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            term.setCaMarkCollection(attachedCaMarkCollection);
            em.persist(term);
            if (branchId != null) {
                branchId.getTermCollection().add(term);
                branchId = em.merge(branchId);
            }
            if (academicYear != null) {
                academicYear.getTermCollection().add(term);
                academicYear = em.merge(academicYear);
            }
            for (Section sectionCollectionSection : term.getSectionCollection()) {
                Term oldTermIdOfSectionCollectionSection = sectionCollectionSection.getTermId();
                sectionCollectionSection.setTermId(term);
                sectionCollectionSection = em.merge(sectionCollectionSection);
                if (oldTermIdOfSectionCollectionSection != null) {
                    oldTermIdOfSectionCollectionSection.getSectionCollection().remove(sectionCollectionSection);
                    oldTermIdOfSectionCollectionSection = em.merge(oldTermIdOfSectionCollectionSection);
                }
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamComment : term.getPrincipalTerminalExamCommentCollection()) {
                Term oldTermIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = principalTerminalExamCommentCollectionPrincipalTerminalExamComment.getTermId();
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment.setTermId(term);
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                if (oldTermIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment != null) {
                    oldTermIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                    oldTermIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(oldTermIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : term.getFormMasterTerminalExamCommentCollection()) {
                Term oldTermIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getTermId();
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setTermId(term);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                if (oldTermIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment != null) {
                    oldTermIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                    oldTermIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(oldTermIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                }
            }
            for (Exam examCollectionExam : term.getExamCollection()) {
                Term oldTermIdOfExamCollectionExam = examCollectionExam.getTermId();
                examCollectionExam.setTermId(term);
                examCollectionExam = em.merge(examCollectionExam);
                if (oldTermIdOfExamCollectionExam != null) {
                    oldTermIdOfExamCollectionExam.getExamCollection().remove(examCollectionExam);
                    oldTermIdOfExamCollectionExam = em.merge(oldTermIdOfExamCollectionExam);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : term.getStudentBehavouralTraitCollection()) {
                Term oldTermIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = studentBehavouralTraitCollectionStudentBehavouralTrait.getTermId();
                studentBehavouralTraitCollectionStudentBehavouralTrait.setTermId(term);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
                if (oldTermIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait != null) {
                    oldTermIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionStudentBehavouralTrait);
                    oldTermIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(oldTermIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait);
                }
            }
            for (ClassSubjects classSubjectsCollectionClassSubjects : term.getClassSubjectsCollection()) {
                Term oldTermIdOfClassSubjectsCollectionClassSubjects = classSubjectsCollectionClassSubjects.getTermId();
                classSubjectsCollectionClassSubjects.setTermId(term);
                classSubjectsCollectionClassSubjects = em.merge(classSubjectsCollectionClassSubjects);
                if (oldTermIdOfClassSubjectsCollectionClassSubjects != null) {
                    oldTermIdOfClassSubjectsCollectionClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionClassSubjects);
                    oldTermIdOfClassSubjectsCollectionClassSubjects = em.merge(oldTermIdOfClassSubjectsCollectionClassSubjects);
                }
            }
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : term.getExamClassPositionCollection()) {
                Term oldTermIdOfExamClassPositionCollectionExamClassPosition = examClassPositionCollectionExamClassPosition.getTermId();
                examClassPositionCollectionExamClassPosition.setTermId(term);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
                if (oldTermIdOfExamClassPositionCollectionExamClassPosition != null) {
                    oldTermIdOfExamClassPositionCollectionExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionExamClassPosition);
                    oldTermIdOfExamClassPositionCollectionExamClassPosition = em.merge(oldTermIdOfExamClassPositionCollectionExamClassPosition);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : term.getSchoolFeeInvoiceDetailsCollection()) {
                Term oldTermIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getTermId();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setTermId(term);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldTermIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldTermIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldTermIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldTermIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            for (Student studentCollectionStudent : term.getStudentCollection()) {
                Term oldTermIdOfStudentCollectionStudent = studentCollectionStudent.getTermId();
                studentCollectionStudent.setTermId(term);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldTermIdOfStudentCollectionStudent != null) {
                    oldTermIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldTermIdOfStudentCollectionStudent = em.merge(oldTermIdOfStudentCollectionStudent);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : term.getStudentDailyAttendanceCollection()) {
                Term oldTermIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = studentDailyAttendanceCollectionStudentDailyAttendance.getTermId();
                studentDailyAttendanceCollectionStudentDailyAttendance.setTermId(term);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
                if (oldTermIdOfStudentDailyAttendanceCollectionStudentDailyAttendance != null) {
                    oldTermIdOfStudentDailyAttendanceCollectionStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionStudentDailyAttendance);
                    oldTermIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = em.merge(oldTermIdOfStudentDailyAttendanceCollectionStudentDailyAttendance);
                }
            }
            for (ClassRoutine classRoutineCollectionClassRoutine : term.getClassRoutineCollection()) {
                Term oldTermIdOfClassRoutineCollectionClassRoutine = classRoutineCollectionClassRoutine.getTermId();
                classRoutineCollectionClassRoutine.setTermId(term);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
                if (oldTermIdOfClassRoutineCollectionClassRoutine != null) {
                    oldTermIdOfClassRoutineCollectionClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionClassRoutine);
                    oldTermIdOfClassRoutineCollectionClassRoutine = em.merge(oldTermIdOfClassRoutineCollectionClassRoutine);
                }
            }
            for (ExamMark examMarkCollectionExamMark : term.getExamMarkCollection()) {
                Term oldTermIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getTermId();
                examMarkCollectionExamMark.setTermId(term);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldTermIdOfExamMarkCollectionExamMark != null) {
                    oldTermIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldTermIdOfExamMarkCollectionExamMark = em.merge(oldTermIdOfExamMarkCollectionExamMark);
                }
            }
            for (CaMark caMarkCollectionCaMark : term.getCaMarkCollection()) {
                Term oldTermIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getTermId();
                caMarkCollectionCaMark.setTermId(term);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldTermIdOfCaMarkCollectionCaMark != null) {
                    oldTermIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldTermIdOfCaMarkCollectionCaMark = em.merge(oldTermIdOfCaMarkCollectionCaMark);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTerm(term.getTermId()) != null) {
                throw new PreexistingEntityException("Term " + term + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Term term) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Term persistentTerm = em.find(Term.class, term.getTermId());
            CompanyBranch branchIdOld = persistentTerm.getBranchId();
            CompanyBranch branchIdNew = term.getBranchId();
            AcademicYears academicYearOld = persistentTerm.getAcademicYear();
            AcademicYears academicYearNew = term.getAcademicYear();
            Collection<Section> sectionCollectionOld = persistentTerm.getSectionCollection();
            Collection<Section> sectionCollectionNew = term.getSectionCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOld = persistentTerm.getPrincipalTerminalExamCommentCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionNew = term.getPrincipalTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOld = persistentTerm.getFormMasterTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionNew = term.getFormMasterTerminalExamCommentCollection();
            Collection<Exam> examCollectionOld = persistentTerm.getExamCollection();
            Collection<Exam> examCollectionNew = term.getExamCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOld = persistentTerm.getStudentBehavouralTraitCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionNew = term.getStudentBehavouralTraitCollection();
            Collection<ClassSubjects> classSubjectsCollectionOld = persistentTerm.getClassSubjectsCollection();
            Collection<ClassSubjects> classSubjectsCollectionNew = term.getClassSubjectsCollection();
            Collection<ExamClassPosition> examClassPositionCollectionOld = persistentTerm.getExamClassPositionCollection();
            Collection<ExamClassPosition> examClassPositionCollectionNew = term.getExamClassPositionCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentTerm.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = term.getSchoolFeeInvoiceDetailsCollection();
            Collection<Student> studentCollectionOld = persistentTerm.getStudentCollection();
            Collection<Student> studentCollectionNew = term.getStudentCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOld = persistentTerm.getStudentDailyAttendanceCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionNew = term.getStudentDailyAttendanceCollection();
            Collection<ClassRoutine> classRoutineCollectionOld = persistentTerm.getClassRoutineCollection();
            Collection<ClassRoutine> classRoutineCollectionNew = term.getClassRoutineCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentTerm.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = term.getExamMarkCollection();
            Collection<CaMark> caMarkCollectionOld = persistentTerm.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = term.getCaMarkCollection();
            List<String> illegalOrphanMessages = null;
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOld) {
                if (!principalTerminalExamCommentCollectionNew.contains(principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment + " since its termId field is not nullable.");
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOld) {
                if (!formMasterTerminalExamCommentCollectionNew.contains(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment + " since its termId field is not nullable.");
                }
            }
            for (Exam examCollectionOldExam : examCollectionOld) {
                if (!examCollectionNew.contains(examCollectionOldExam)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Exam " + examCollectionOldExam + " since its termId field is not nullable.");
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOldStudentBehavouralTrait : studentBehavouralTraitCollectionOld) {
                if (!studentBehavouralTraitCollectionNew.contains(studentBehavouralTraitCollectionOldStudentBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentBehavouralTrait " + studentBehavouralTraitCollectionOldStudentBehavouralTrait + " since its termId field is not nullable.");
                }
            }
            for (ClassSubjects classSubjectsCollectionOldClassSubjects : classSubjectsCollectionOld) {
                if (!classSubjectsCollectionNew.contains(classSubjectsCollectionOldClassSubjects)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClassSubjects " + classSubjectsCollectionOldClassSubjects + " since its termId field is not nullable.");
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails + " since its termId field is not nullable.");
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Student " + studentCollectionOldStudent + " since its termId field is not nullable.");
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamMark " + examMarkCollectionOldExamMark + " since its termId field is not nullable.");
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaMark " + caMarkCollectionOldCaMark + " since its termId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                term.setBranchId(branchIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                term.setAcademicYear(academicYearNew);
            }
            Collection<Section> attachedSectionCollectionNew = new ArrayList<Section>();
            for (Section sectionCollectionNewSectionToAttach : sectionCollectionNew) {
                sectionCollectionNewSectionToAttach = em.getReference(sectionCollectionNewSectionToAttach.getClass(), sectionCollectionNewSectionToAttach.getSectionId());
                attachedSectionCollectionNew.add(sectionCollectionNewSectionToAttach);
            }
            sectionCollectionNew = attachedSectionCollectionNew;
            term.setSectionCollection(sectionCollectionNew);
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollectionNew = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach : principalTerminalExamCommentCollectionNew) {
                principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollectionNew.add(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach);
            }
            principalTerminalExamCommentCollectionNew = attachedPrincipalTerminalExamCommentCollectionNew;
            term.setPrincipalTerminalExamCommentCollection(principalTerminalExamCommentCollectionNew);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollectionNew = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach : formMasterTerminalExamCommentCollectionNew) {
                formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollectionNew.add(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach);
            }
            formMasterTerminalExamCommentCollectionNew = attachedFormMasterTerminalExamCommentCollectionNew;
            term.setFormMasterTerminalExamCommentCollection(formMasterTerminalExamCommentCollectionNew);
            Collection<Exam> attachedExamCollectionNew = new ArrayList<Exam>();
            for (Exam examCollectionNewExamToAttach : examCollectionNew) {
                examCollectionNewExamToAttach = em.getReference(examCollectionNewExamToAttach.getClass(), examCollectionNewExamToAttach.getExamId());
                attachedExamCollectionNew.add(examCollectionNewExamToAttach);
            }
            examCollectionNew = attachedExamCollectionNew;
            term.setExamCollection(examCollectionNew);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollectionNew = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach : studentBehavouralTraitCollectionNew) {
                studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollectionNew.add(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach);
            }
            studentBehavouralTraitCollectionNew = attachedStudentBehavouralTraitCollectionNew;
            term.setStudentBehavouralTraitCollection(studentBehavouralTraitCollectionNew);
            Collection<ClassSubjects> attachedClassSubjectsCollectionNew = new ArrayList<ClassSubjects>();
            for (ClassSubjects classSubjectsCollectionNewClassSubjectsToAttach : classSubjectsCollectionNew) {
                classSubjectsCollectionNewClassSubjectsToAttach = em.getReference(classSubjectsCollectionNewClassSubjectsToAttach.getClass(), classSubjectsCollectionNewClassSubjectsToAttach.getClassSubjectId());
                attachedClassSubjectsCollectionNew.add(classSubjectsCollectionNewClassSubjectsToAttach);
            }
            classSubjectsCollectionNew = attachedClassSubjectsCollectionNew;
            term.setClassSubjectsCollection(classSubjectsCollectionNew);
            Collection<ExamClassPosition> attachedExamClassPositionCollectionNew = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionNewExamClassPositionToAttach : examClassPositionCollectionNew) {
                examClassPositionCollectionNewExamClassPositionToAttach = em.getReference(examClassPositionCollectionNewExamClassPositionToAttach.getClass(), examClassPositionCollectionNewExamClassPositionToAttach.getId());
                attachedExamClassPositionCollectionNew.add(examClassPositionCollectionNewExamClassPositionToAttach);
            }
            examClassPositionCollectionNew = attachedExamClassPositionCollectionNew;
            term.setExamClassPositionCollection(examClassPositionCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            term.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            term.setStudentCollection(studentCollectionNew);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollectionNew = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach : studentDailyAttendanceCollectionNew) {
                studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollectionNew.add(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach);
            }
            studentDailyAttendanceCollectionNew = attachedStudentDailyAttendanceCollectionNew;
            term.setStudentDailyAttendanceCollection(studentDailyAttendanceCollectionNew);
            Collection<ClassRoutine> attachedClassRoutineCollectionNew = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionNewClassRoutineToAttach : classRoutineCollectionNew) {
                classRoutineCollectionNewClassRoutineToAttach = em.getReference(classRoutineCollectionNewClassRoutineToAttach.getClass(), classRoutineCollectionNewClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollectionNew.add(classRoutineCollectionNewClassRoutineToAttach);
            }
            classRoutineCollectionNew = attachedClassRoutineCollectionNew;
            term.setClassRoutineCollection(classRoutineCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            term.setExamMarkCollection(examMarkCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            term.setCaMarkCollection(caMarkCollectionNew);
            term = em.merge(term);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getTermCollection().remove(term);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getTermCollection().add(term);
                branchIdNew = em.merge(branchIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getTermCollection().remove(term);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getTermCollection().add(term);
                academicYearNew = em.merge(academicYearNew);
            }
            for (Section sectionCollectionOldSection : sectionCollectionOld) {
                if (!sectionCollectionNew.contains(sectionCollectionOldSection)) {
                    sectionCollectionOldSection.setTermId(null);
                    sectionCollectionOldSection = em.merge(sectionCollectionOldSection);
                }
            }
            for (Section sectionCollectionNewSection : sectionCollectionNew) {
                if (!sectionCollectionOld.contains(sectionCollectionNewSection)) {
                    Term oldTermIdOfSectionCollectionNewSection = sectionCollectionNewSection.getTermId();
                    sectionCollectionNewSection.setTermId(term);
                    sectionCollectionNewSection = em.merge(sectionCollectionNewSection);
                    if (oldTermIdOfSectionCollectionNewSection != null && !oldTermIdOfSectionCollectionNewSection.equals(term)) {
                        oldTermIdOfSectionCollectionNewSection.getSectionCollection().remove(sectionCollectionNewSection);
                        oldTermIdOfSectionCollectionNewSection = em.merge(oldTermIdOfSectionCollectionNewSection);
                    }
                }
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment : principalTerminalExamCommentCollectionNew) {
                if (!principalTerminalExamCommentCollectionOld.contains(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment)) {
                    Term oldTermIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getTermId();
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.setTermId(term);
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    if (oldTermIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment != null && !oldTermIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.equals(term)) {
                        oldTermIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                        oldTermIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(oldTermIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    }
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionNew) {
                if (!formMasterTerminalExamCommentCollectionOld.contains(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment)) {
                    Term oldTermIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getTermId();
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.setTermId(term);
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    if (oldTermIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment != null && !oldTermIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.equals(term)) {
                        oldTermIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                        oldTermIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(oldTermIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    }
                }
            }
            for (Exam examCollectionNewExam : examCollectionNew) {
                if (!examCollectionOld.contains(examCollectionNewExam)) {
                    Term oldTermIdOfExamCollectionNewExam = examCollectionNewExam.getTermId();
                    examCollectionNewExam.setTermId(term);
                    examCollectionNewExam = em.merge(examCollectionNewExam);
                    if (oldTermIdOfExamCollectionNewExam != null && !oldTermIdOfExamCollectionNewExam.equals(term)) {
                        oldTermIdOfExamCollectionNewExam.getExamCollection().remove(examCollectionNewExam);
                        oldTermIdOfExamCollectionNewExam = em.merge(oldTermIdOfExamCollectionNewExam);
                    }
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTrait : studentBehavouralTraitCollectionNew) {
                if (!studentBehavouralTraitCollectionOld.contains(studentBehavouralTraitCollectionNewStudentBehavouralTrait)) {
                    Term oldTermIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = studentBehavouralTraitCollectionNewStudentBehavouralTrait.getTermId();
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait.setTermId(term);
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    if (oldTermIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait != null && !oldTermIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.equals(term)) {
                        oldTermIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                        oldTermIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(oldTermIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    }
                }
            }
            for (ClassSubjects classSubjectsCollectionNewClassSubjects : classSubjectsCollectionNew) {
                if (!classSubjectsCollectionOld.contains(classSubjectsCollectionNewClassSubjects)) {
                    Term oldTermIdOfClassSubjectsCollectionNewClassSubjects = classSubjectsCollectionNewClassSubjects.getTermId();
                    classSubjectsCollectionNewClassSubjects.setTermId(term);
                    classSubjectsCollectionNewClassSubjects = em.merge(classSubjectsCollectionNewClassSubjects);
                    if (oldTermIdOfClassSubjectsCollectionNewClassSubjects != null && !oldTermIdOfClassSubjectsCollectionNewClassSubjects.equals(term)) {
                        oldTermIdOfClassSubjectsCollectionNewClassSubjects.getClassSubjectsCollection().remove(classSubjectsCollectionNewClassSubjects);
                        oldTermIdOfClassSubjectsCollectionNewClassSubjects = em.merge(oldTermIdOfClassSubjectsCollectionNewClassSubjects);
                    }
                }
            }
            for (ExamClassPosition examClassPositionCollectionOldExamClassPosition : examClassPositionCollectionOld) {
                if (!examClassPositionCollectionNew.contains(examClassPositionCollectionOldExamClassPosition)) {
                    examClassPositionCollectionOldExamClassPosition.setTermId(null);
                    examClassPositionCollectionOldExamClassPosition = em.merge(examClassPositionCollectionOldExamClassPosition);
                }
            }
            for (ExamClassPosition examClassPositionCollectionNewExamClassPosition : examClassPositionCollectionNew) {
                if (!examClassPositionCollectionOld.contains(examClassPositionCollectionNewExamClassPosition)) {
                    Term oldTermIdOfExamClassPositionCollectionNewExamClassPosition = examClassPositionCollectionNewExamClassPosition.getTermId();
                    examClassPositionCollectionNewExamClassPosition.setTermId(term);
                    examClassPositionCollectionNewExamClassPosition = em.merge(examClassPositionCollectionNewExamClassPosition);
                    if (oldTermIdOfExamClassPositionCollectionNewExamClassPosition != null && !oldTermIdOfExamClassPositionCollectionNewExamClassPosition.equals(term)) {
                        oldTermIdOfExamClassPositionCollectionNewExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionNewExamClassPosition);
                        oldTermIdOfExamClassPositionCollectionNewExamClassPosition = em.merge(oldTermIdOfExamClassPositionCollectionNewExamClassPosition);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    Term oldTermIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getTermId();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setTermId(term);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldTermIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldTermIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(term)) {
                        oldTermIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldTermIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldTermIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    Term oldTermIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getTermId();
                    studentCollectionNewStudent.setTermId(term);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldTermIdOfStudentCollectionNewStudent != null && !oldTermIdOfStudentCollectionNewStudent.equals(term)) {
                        oldTermIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldTermIdOfStudentCollectionNewStudent = em.merge(oldTermIdOfStudentCollectionNewStudent);
                    }
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionOldStudentDailyAttendance : studentDailyAttendanceCollectionOld) {
                if (!studentDailyAttendanceCollectionNew.contains(studentDailyAttendanceCollectionOldStudentDailyAttendance)) {
                    studentDailyAttendanceCollectionOldStudentDailyAttendance.setTermId(null);
                    studentDailyAttendanceCollectionOldStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionOldStudentDailyAttendance);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendance : studentDailyAttendanceCollectionNew) {
                if (!studentDailyAttendanceCollectionOld.contains(studentDailyAttendanceCollectionNewStudentDailyAttendance)) {
                    Term oldTermIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = studentDailyAttendanceCollectionNewStudentDailyAttendance.getTermId();
                    studentDailyAttendanceCollectionNewStudentDailyAttendance.setTermId(term);
                    studentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                    if (oldTermIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance != null && !oldTermIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.equals(term)) {
                        oldTermIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                        oldTermIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(oldTermIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance);
                    }
                }
            }
            for (ClassRoutine classRoutineCollectionOldClassRoutine : classRoutineCollectionOld) {
                if (!classRoutineCollectionNew.contains(classRoutineCollectionOldClassRoutine)) {
                    classRoutineCollectionOldClassRoutine.setTermId(null);
                    classRoutineCollectionOldClassRoutine = em.merge(classRoutineCollectionOldClassRoutine);
                }
            }
            for (ClassRoutine classRoutineCollectionNewClassRoutine : classRoutineCollectionNew) {
                if (!classRoutineCollectionOld.contains(classRoutineCollectionNewClassRoutine)) {
                    Term oldTermIdOfClassRoutineCollectionNewClassRoutine = classRoutineCollectionNewClassRoutine.getTermId();
                    classRoutineCollectionNewClassRoutine.setTermId(term);
                    classRoutineCollectionNewClassRoutine = em.merge(classRoutineCollectionNewClassRoutine);
                    if (oldTermIdOfClassRoutineCollectionNewClassRoutine != null && !oldTermIdOfClassRoutineCollectionNewClassRoutine.equals(term)) {
                        oldTermIdOfClassRoutineCollectionNewClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionNewClassRoutine);
                        oldTermIdOfClassRoutineCollectionNewClassRoutine = em.merge(oldTermIdOfClassRoutineCollectionNewClassRoutine);
                    }
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    Term oldTermIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getTermId();
                    examMarkCollectionNewExamMark.setTermId(term);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldTermIdOfExamMarkCollectionNewExamMark != null && !oldTermIdOfExamMarkCollectionNewExamMark.equals(term)) {
                        oldTermIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldTermIdOfExamMarkCollectionNewExamMark = em.merge(oldTermIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    Term oldTermIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getTermId();
                    caMarkCollectionNewCaMark.setTermId(term);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldTermIdOfCaMarkCollectionNewCaMark != null && !oldTermIdOfCaMarkCollectionNewCaMark.equals(term)) {
                        oldTermIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldTermIdOfCaMarkCollectionNewCaMark = em.merge(oldTermIdOfCaMarkCollectionNewCaMark);
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
                Long id = term.getTermId();
                if (findTerm(id) == null) {
                    throw new NonexistentEntityException("The term with id " + id + " no longer exists.");
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
            Term term;
            try {
                term = em.getReference(Term.class, id);
                term.getTermId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The term with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOrphanCheck = term.getPrincipalTerminalExamCommentCollection();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment + " in its principalTerminalExamCommentCollection field has a non-nullable termId field.");
            }
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOrphanCheck = term.getFormMasterTerminalExamCommentCollection();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment + " in its formMasterTerminalExamCommentCollection field has a non-nullable termId field.");
            }
            Collection<Exam> examCollectionOrphanCheck = term.getExamCollection();
            for (Exam examCollectionOrphanCheckExam : examCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the Exam " + examCollectionOrphanCheckExam + " in its examCollection field has a non-nullable termId field.");
            }
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOrphanCheck = term.getStudentBehavouralTraitCollection();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait : studentBehavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the StudentBehavouralTrait " + studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait + " in its studentBehavouralTraitCollection field has a non-nullable termId field.");
            }
            Collection<ClassSubjects> classSubjectsCollectionOrphanCheck = term.getClassSubjectsCollection();
            for (ClassSubjects classSubjectsCollectionOrphanCheckClassSubjects : classSubjectsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the ClassSubjects " + classSubjectsCollectionOrphanCheckClassSubjects + " in its classSubjectsCollection field has a non-nullable termId field.");
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOrphanCheck = term.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails + " in its schoolFeeInvoiceDetailsCollection field has a non-nullable termId field.");
            }
            Collection<Student> studentCollectionOrphanCheck = term.getStudentCollection();
            for (Student studentCollectionOrphanCheckStudent : studentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the Student " + studentCollectionOrphanCheckStudent + " in its studentCollection field has a non-nullable termId field.");
            }
            Collection<ExamMark> examMarkCollectionOrphanCheck = term.getExamMarkCollection();
            for (ExamMark examMarkCollectionOrphanCheckExamMark : examMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the ExamMark " + examMarkCollectionOrphanCheckExamMark + " in its examMarkCollection field has a non-nullable termId field.");
            }
            Collection<CaMark> caMarkCollectionOrphanCheck = term.getCaMarkCollection();
            for (CaMark caMarkCollectionOrphanCheckCaMark : caMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Term (" + term + ") cannot be destroyed since the CaMark " + caMarkCollectionOrphanCheckCaMark + " in its caMarkCollection field has a non-nullable termId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = term.getBranchId();
            if (branchId != null) {
                branchId.getTermCollection().remove(term);
                branchId = em.merge(branchId);
            }
            AcademicYears academicYear = term.getAcademicYear();
            if (academicYear != null) {
                academicYear.getTermCollection().remove(term);
                academicYear = em.merge(academicYear);
            }
            Collection<Section> sectionCollection = term.getSectionCollection();
            for (Section sectionCollectionSection : sectionCollection) {
                sectionCollectionSection.setTermId(null);
                sectionCollectionSection = em.merge(sectionCollectionSection);
            }
            Collection<ExamClassPosition> examClassPositionCollection = term.getExamClassPositionCollection();
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : examClassPositionCollection) {
                examClassPositionCollectionExamClassPosition.setTermId(null);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
            }
            Collection<StudentDailyAttendance> studentDailyAttendanceCollection = term.getStudentDailyAttendanceCollection();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : studentDailyAttendanceCollection) {
                studentDailyAttendanceCollectionStudentDailyAttendance.setTermId(null);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
            }
            Collection<ClassRoutine> classRoutineCollection = term.getClassRoutineCollection();
            for (ClassRoutine classRoutineCollectionClassRoutine : classRoutineCollection) {
                classRoutineCollectionClassRoutine.setTermId(null);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
            }
            em.remove(term);
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

    public List<Term> findTermEntities() {
        return findTermEntities(true, -1, -1);
    }

    public List<Term> findTermEntities(int maxResults, int firstResult) {
        return findTermEntities(false, maxResults, firstResult);
    }

    private List<Term> findTermEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Term.class));
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

    public Term findTerm(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Term.class, id);
        } finally {
            em.close();
        }
    }

    public int getTermCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Term> rt = cq.from(Term.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
