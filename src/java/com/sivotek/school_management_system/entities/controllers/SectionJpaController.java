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
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.ClassCategory;
import com.sivotek.school_management_system.entities.PrincipalTerminalExamComment;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
import com.sivotek.school_management_system.entities.ExamClassPosition;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
import com.sivotek.school_management_system.entities.ClassRoutine;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.Section;
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
public class SectionJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(SectionJpaController.class.getName());
      
    public SectionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public SectionJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(Section section) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (section.getPrincipalTerminalExamCommentCollection() == null) {
            section.setPrincipalTerminalExamCommentCollection(new ArrayList<PrincipalTerminalExamComment>());
        }
        if (section.getFormMasterTerminalExamCommentCollection() == null) {
            section.setFormMasterTerminalExamCommentCollection(new ArrayList<FormMasterTerminalExamComment>());
        }
        if (section.getStudentBehavouralTraitCollection() == null) {
            section.setStudentBehavouralTraitCollection(new ArrayList<StudentBehavouralTrait>());
        }
        if (section.getExamClassPositionCollection() == null) {
            section.setExamClassPositionCollection(new ArrayList<ExamClassPosition>());
        }
        if (section.getSchoolFeeInvoiceDetailsCollection() == null) {
            section.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (section.getStudentCollection() == null) {
            section.setStudentCollection(new ArrayList<Student>());
        }
        if (section.getStudentDailyAttendanceCollection() == null) {
            section.setStudentDailyAttendanceCollection(new ArrayList<StudentDailyAttendance>());
        }
        if (section.getClassRoutineCollection() == null) {
            section.setClassRoutineCollection(new ArrayList<ClassRoutine>());
        }
        if (section.getExamMarkCollection() == null) {
            section.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (section.getCaMarkCollection() == null) {
            section.setCaMarkCollection(new ArrayList<CaMark>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = section.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                section.setBranchId(branchId);
            }
            Term termId = section.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                section.setTermId(termId);
            }
            ClassCategory classCategoryId = section.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId = em.getReference(classCategoryId.getClass(), classCategoryId.getCategoryId());
                section.setClassCategoryId(classCategoryId);
            }
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollection = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach : section.getPrincipalTerminalExamCommentCollection()) {
                principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollection.add(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach);
            }
            section.setPrincipalTerminalExamCommentCollection(attachedPrincipalTerminalExamCommentCollection);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollection = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach : section.getFormMasterTerminalExamCommentCollection()) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollection.add(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach);
            }
            section.setFormMasterTerminalExamCommentCollection(attachedFormMasterTerminalExamCommentCollection);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollection = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTraitToAttach : section.getStudentBehavouralTraitCollection()) {
                studentBehavouralTraitCollectionStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollection.add(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach);
            }
            section.setStudentBehavouralTraitCollection(attachedStudentBehavouralTraitCollection);
            Collection<ExamClassPosition> attachedExamClassPositionCollection = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionExamClassPositionToAttach : section.getExamClassPositionCollection()) {
                examClassPositionCollectionExamClassPositionToAttach = em.getReference(examClassPositionCollectionExamClassPositionToAttach.getClass(), examClassPositionCollectionExamClassPositionToAttach.getId());
                attachedExamClassPositionCollection.add(examClassPositionCollectionExamClassPositionToAttach);
            }
            section.setExamClassPositionCollection(attachedExamClassPositionCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : section.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            section.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : section.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            section.setStudentCollection(attachedStudentCollection);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollection = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendanceToAttach : section.getStudentDailyAttendanceCollection()) {
                studentDailyAttendanceCollectionStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollection.add(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach);
            }
            section.setStudentDailyAttendanceCollection(attachedStudentDailyAttendanceCollection);
            Collection<ClassRoutine> attachedClassRoutineCollection = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionClassRoutineToAttach : section.getClassRoutineCollection()) {
                classRoutineCollectionClassRoutineToAttach = em.getReference(classRoutineCollectionClassRoutineToAttach.getClass(), classRoutineCollectionClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollection.add(classRoutineCollectionClassRoutineToAttach);
            }
            section.setClassRoutineCollection(attachedClassRoutineCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : section.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            section.setExamMarkCollection(attachedExamMarkCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : section.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            section.setCaMarkCollection(attachedCaMarkCollection);
            em.persist(section);
            if (branchId != null) {
                branchId.getSectionCollection().add(section);
                branchId = em.merge(branchId);
            }
            if (termId != null) {
                termId.getSectionCollection().add(section);
                termId = em.merge(termId);
            }
            if (classCategoryId != null) {
                classCategoryId.getSectionCollection().add(section);
                classCategoryId = em.merge(classCategoryId);
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamComment : section.getPrincipalTerminalExamCommentCollection()) {
                Section oldSectionIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = principalTerminalExamCommentCollectionPrincipalTerminalExamComment.getSectionId();
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment.setSectionId(section);
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                if (oldSectionIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment != null) {
                    oldSectionIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                    oldSectionIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(oldSectionIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : section.getFormMasterTerminalExamCommentCollection()) {
                Section oldSectionIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getSectionId();
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setSectionId(section);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                if (oldSectionIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment != null) {
                    oldSectionIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                    oldSectionIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(oldSectionIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : section.getStudentBehavouralTraitCollection()) {
                Section oldSectionIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = studentBehavouralTraitCollectionStudentBehavouralTrait.getSectionId();
                studentBehavouralTraitCollectionStudentBehavouralTrait.setSectionId(section);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
                if (oldSectionIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait != null) {
                    oldSectionIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionStudentBehavouralTrait);
                    oldSectionIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(oldSectionIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait);
                }
            }
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : section.getExamClassPositionCollection()) {
                Section oldSectionIdOfExamClassPositionCollectionExamClassPosition = examClassPositionCollectionExamClassPosition.getSectionId();
                examClassPositionCollectionExamClassPosition.setSectionId(section);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
                if (oldSectionIdOfExamClassPositionCollectionExamClassPosition != null) {
                    oldSectionIdOfExamClassPositionCollectionExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionExamClassPosition);
                    oldSectionIdOfExamClassPositionCollectionExamClassPosition = em.merge(oldSectionIdOfExamClassPositionCollectionExamClassPosition);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : section.getSchoolFeeInvoiceDetailsCollection()) {
                Section oldSectionIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSectionId();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setSectionId(section);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldSectionIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldSectionIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldSectionIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldSectionIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            for (Student studentCollectionStudent : section.getStudentCollection()) {
                Section oldSectionIdOfStudentCollectionStudent = studentCollectionStudent.getSectionId();
                studentCollectionStudent.setSectionId(section);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldSectionIdOfStudentCollectionStudent != null) {
                    oldSectionIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldSectionIdOfStudentCollectionStudent = em.merge(oldSectionIdOfStudentCollectionStudent);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : section.getStudentDailyAttendanceCollection()) {
                Section oldSectionIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = studentDailyAttendanceCollectionStudentDailyAttendance.getSectionId();
                studentDailyAttendanceCollectionStudentDailyAttendance.setSectionId(section);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
                if (oldSectionIdOfStudentDailyAttendanceCollectionStudentDailyAttendance != null) {
                    oldSectionIdOfStudentDailyAttendanceCollectionStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionStudentDailyAttendance);
                    oldSectionIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = em.merge(oldSectionIdOfStudentDailyAttendanceCollectionStudentDailyAttendance);
                }
            }
            for (ClassRoutine classRoutineCollectionClassRoutine : section.getClassRoutineCollection()) {
                Section oldSectionIdOfClassRoutineCollectionClassRoutine = classRoutineCollectionClassRoutine.getSectionId();
                classRoutineCollectionClassRoutine.setSectionId(section);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
                if (oldSectionIdOfClassRoutineCollectionClassRoutine != null) {
                    oldSectionIdOfClassRoutineCollectionClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionClassRoutine);
                    oldSectionIdOfClassRoutineCollectionClassRoutine = em.merge(oldSectionIdOfClassRoutineCollectionClassRoutine);
                }
            }
            for (ExamMark examMarkCollectionExamMark : section.getExamMarkCollection()) {
                Section oldSectionIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getSectionId();
                examMarkCollectionExamMark.setSectionId(section);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldSectionIdOfExamMarkCollectionExamMark != null) {
                    oldSectionIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldSectionIdOfExamMarkCollectionExamMark = em.merge(oldSectionIdOfExamMarkCollectionExamMark);
                }
            }
            for (CaMark caMarkCollectionCaMark : section.getCaMarkCollection()) {
                Section oldSectionIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getSectionId();
                caMarkCollectionCaMark.setSectionId(section);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldSectionIdOfCaMarkCollectionCaMark != null) {
                    oldSectionIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldSectionIdOfCaMarkCollectionCaMark = em.merge(oldSectionIdOfCaMarkCollectionCaMark);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSection(section.getSectionId()) != null) {
                throw new PreexistingEntityException("Section " + section + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Section section) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Section persistentSection = em.find(Section.class, section.getSectionId());
            CompanyBranch branchIdOld = persistentSection.getBranchId();
            CompanyBranch branchIdNew = section.getBranchId();
            Term termIdOld = persistentSection.getTermId();
            Term termIdNew = section.getTermId();
            ClassCategory classCategoryIdOld = persistentSection.getClassCategoryId();
            ClassCategory classCategoryIdNew = section.getClassCategoryId();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOld = persistentSection.getPrincipalTerminalExamCommentCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionNew = section.getPrincipalTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOld = persistentSection.getFormMasterTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionNew = section.getFormMasterTerminalExamCommentCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOld = persistentSection.getStudentBehavouralTraitCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionNew = section.getStudentBehavouralTraitCollection();
            Collection<ExamClassPosition> examClassPositionCollectionOld = persistentSection.getExamClassPositionCollection();
            Collection<ExamClassPosition> examClassPositionCollectionNew = section.getExamClassPositionCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentSection.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = section.getSchoolFeeInvoiceDetailsCollection();
            Collection<Student> studentCollectionOld = persistentSection.getStudentCollection();
            Collection<Student> studentCollectionNew = section.getStudentCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOld = persistentSection.getStudentDailyAttendanceCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionNew = section.getStudentDailyAttendanceCollection();
            Collection<ClassRoutine> classRoutineCollectionOld = persistentSection.getClassRoutineCollection();
            Collection<ClassRoutine> classRoutineCollectionNew = section.getClassRoutineCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentSection.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = section.getExamMarkCollection();
            Collection<CaMark> caMarkCollectionOld = persistentSection.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = section.getCaMarkCollection();
            List<String> illegalOrphanMessages = null;
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOld) {
                if (!principalTerminalExamCommentCollectionNew.contains(principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment + " since its sectionId field is not nullable.");
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOld) {
                if (!formMasterTerminalExamCommentCollectionNew.contains(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment + " since its sectionId field is not nullable.");
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOldStudentBehavouralTrait : studentBehavouralTraitCollectionOld) {
                if (!studentBehavouralTraitCollectionNew.contains(studentBehavouralTraitCollectionOldStudentBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentBehavouralTrait " + studentBehavouralTraitCollectionOldStudentBehavouralTrait + " since its sectionId field is not nullable.");
                }
            }
            for (ExamClassPosition examClassPositionCollectionOldExamClassPosition : examClassPositionCollectionOld) {
                if (!examClassPositionCollectionNew.contains(examClassPositionCollectionOldExamClassPosition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamClassPosition " + examClassPositionCollectionOldExamClassPosition + " since its sectionId field is not nullable.");
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails + " since its sectionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                section.setBranchId(branchIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                section.setTermId(termIdNew);
            }
            if (classCategoryIdNew != null) {
                classCategoryIdNew = em.getReference(classCategoryIdNew.getClass(), classCategoryIdNew.getCategoryId());
                section.setClassCategoryId(classCategoryIdNew);
            }
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollectionNew = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach : principalTerminalExamCommentCollectionNew) {
                principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollectionNew.add(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach);
            }
            principalTerminalExamCommentCollectionNew = attachedPrincipalTerminalExamCommentCollectionNew;
            section.setPrincipalTerminalExamCommentCollection(principalTerminalExamCommentCollectionNew);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollectionNew = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach : formMasterTerminalExamCommentCollectionNew) {
                formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollectionNew.add(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach);
            }
            formMasterTerminalExamCommentCollectionNew = attachedFormMasterTerminalExamCommentCollectionNew;
            section.setFormMasterTerminalExamCommentCollection(formMasterTerminalExamCommentCollectionNew);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollectionNew = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach : studentBehavouralTraitCollectionNew) {
                studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollectionNew.add(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach);
            }
            studentBehavouralTraitCollectionNew = attachedStudentBehavouralTraitCollectionNew;
            section.setStudentBehavouralTraitCollection(studentBehavouralTraitCollectionNew);
            Collection<ExamClassPosition> attachedExamClassPositionCollectionNew = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionNewExamClassPositionToAttach : examClassPositionCollectionNew) {
                examClassPositionCollectionNewExamClassPositionToAttach = em.getReference(examClassPositionCollectionNewExamClassPositionToAttach.getClass(), examClassPositionCollectionNewExamClassPositionToAttach.getId());
                attachedExamClassPositionCollectionNew.add(examClassPositionCollectionNewExamClassPositionToAttach);
            }
            examClassPositionCollectionNew = attachedExamClassPositionCollectionNew;
            section.setExamClassPositionCollection(examClassPositionCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            section.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            section.setStudentCollection(studentCollectionNew);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollectionNew = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach : studentDailyAttendanceCollectionNew) {
                studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollectionNew.add(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach);
            }
            studentDailyAttendanceCollectionNew = attachedStudentDailyAttendanceCollectionNew;
            section.setStudentDailyAttendanceCollection(studentDailyAttendanceCollectionNew);
            Collection<ClassRoutine> attachedClassRoutineCollectionNew = new ArrayList<ClassRoutine>();
            for (ClassRoutine classRoutineCollectionNewClassRoutineToAttach : classRoutineCollectionNew) {
                classRoutineCollectionNewClassRoutineToAttach = em.getReference(classRoutineCollectionNewClassRoutineToAttach.getClass(), classRoutineCollectionNewClassRoutineToAttach.getClassRoutineId());
                attachedClassRoutineCollectionNew.add(classRoutineCollectionNewClassRoutineToAttach);
            }
            classRoutineCollectionNew = attachedClassRoutineCollectionNew;
            section.setClassRoutineCollection(classRoutineCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            section.setExamMarkCollection(examMarkCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            section.setCaMarkCollection(caMarkCollectionNew);
            section = em.merge(section);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getSectionCollection().remove(section);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getSectionCollection().add(section);
                branchIdNew = em.merge(branchIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getSectionCollection().remove(section);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getSectionCollection().add(section);
                termIdNew = em.merge(termIdNew);
            }
            if (classCategoryIdOld != null && !classCategoryIdOld.equals(classCategoryIdNew)) {
                classCategoryIdOld.getSectionCollection().remove(section);
                classCategoryIdOld = em.merge(classCategoryIdOld);
            }
            if (classCategoryIdNew != null && !classCategoryIdNew.equals(classCategoryIdOld)) {
                classCategoryIdNew.getSectionCollection().add(section);
                classCategoryIdNew = em.merge(classCategoryIdNew);
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment : principalTerminalExamCommentCollectionNew) {
                if (!principalTerminalExamCommentCollectionOld.contains(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment)) {
                    Section oldSectionIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getSectionId();
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.setSectionId(section);
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    if (oldSectionIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment != null && !oldSectionIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.equals(section)) {
                        oldSectionIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                        oldSectionIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(oldSectionIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    }
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionNew) {
                if (!formMasterTerminalExamCommentCollectionOld.contains(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment)) {
                    Section oldSectionIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getSectionId();
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.setSectionId(section);
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    if (oldSectionIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment != null && !oldSectionIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.equals(section)) {
                        oldSectionIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                        oldSectionIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(oldSectionIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    }
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTrait : studentBehavouralTraitCollectionNew) {
                if (!studentBehavouralTraitCollectionOld.contains(studentBehavouralTraitCollectionNewStudentBehavouralTrait)) {
                    Section oldSectionIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = studentBehavouralTraitCollectionNewStudentBehavouralTrait.getSectionId();
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait.setSectionId(section);
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    if (oldSectionIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait != null && !oldSectionIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.equals(section)) {
                        oldSectionIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                        oldSectionIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(oldSectionIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    }
                }
            }
            for (ExamClassPosition examClassPositionCollectionNewExamClassPosition : examClassPositionCollectionNew) {
                if (!examClassPositionCollectionOld.contains(examClassPositionCollectionNewExamClassPosition)) {
                    Section oldSectionIdOfExamClassPositionCollectionNewExamClassPosition = examClassPositionCollectionNewExamClassPosition.getSectionId();
                    examClassPositionCollectionNewExamClassPosition.setSectionId(section);
                    examClassPositionCollectionNewExamClassPosition = em.merge(examClassPositionCollectionNewExamClassPosition);
                    if (oldSectionIdOfExamClassPositionCollectionNewExamClassPosition != null && !oldSectionIdOfExamClassPositionCollectionNewExamClassPosition.equals(section)) {
                        oldSectionIdOfExamClassPositionCollectionNewExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionNewExamClassPosition);
                        oldSectionIdOfExamClassPositionCollectionNewExamClassPosition = em.merge(oldSectionIdOfExamClassPositionCollectionNewExamClassPosition);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    Section oldSectionIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSectionId();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setSectionId(section);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldSectionIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldSectionIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(section)) {
                        oldSectionIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldSectionIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldSectionIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setSectionId(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    Section oldSectionIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getSectionId();
                    studentCollectionNewStudent.setSectionId(section);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldSectionIdOfStudentCollectionNewStudent != null && !oldSectionIdOfStudentCollectionNewStudent.equals(section)) {
                        oldSectionIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldSectionIdOfStudentCollectionNewStudent = em.merge(oldSectionIdOfStudentCollectionNewStudent);
                    }
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionOldStudentDailyAttendance : studentDailyAttendanceCollectionOld) {
                if (!studentDailyAttendanceCollectionNew.contains(studentDailyAttendanceCollectionOldStudentDailyAttendance)) {
                    studentDailyAttendanceCollectionOldStudentDailyAttendance.setSectionId(null);
                    studentDailyAttendanceCollectionOldStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionOldStudentDailyAttendance);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendance : studentDailyAttendanceCollectionNew) {
                if (!studentDailyAttendanceCollectionOld.contains(studentDailyAttendanceCollectionNewStudentDailyAttendance)) {
                    Section oldSectionIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = studentDailyAttendanceCollectionNewStudentDailyAttendance.getSectionId();
                    studentDailyAttendanceCollectionNewStudentDailyAttendance.setSectionId(section);
                    studentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                    if (oldSectionIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance != null && !oldSectionIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.equals(section)) {
                        oldSectionIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                        oldSectionIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(oldSectionIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance);
                    }
                }
            }
            for (ClassRoutine classRoutineCollectionOldClassRoutine : classRoutineCollectionOld) {
                if (!classRoutineCollectionNew.contains(classRoutineCollectionOldClassRoutine)) {
                    classRoutineCollectionOldClassRoutine.setSectionId(null);
                    classRoutineCollectionOldClassRoutine = em.merge(classRoutineCollectionOldClassRoutine);
                }
            }
            for (ClassRoutine classRoutineCollectionNewClassRoutine : classRoutineCollectionNew) {
                if (!classRoutineCollectionOld.contains(classRoutineCollectionNewClassRoutine)) {
                    Section oldSectionIdOfClassRoutineCollectionNewClassRoutine = classRoutineCollectionNewClassRoutine.getSectionId();
                    classRoutineCollectionNewClassRoutine.setSectionId(section);
                    classRoutineCollectionNewClassRoutine = em.merge(classRoutineCollectionNewClassRoutine);
                    if (oldSectionIdOfClassRoutineCollectionNewClassRoutine != null && !oldSectionIdOfClassRoutineCollectionNewClassRoutine.equals(section)) {
                        oldSectionIdOfClassRoutineCollectionNewClassRoutine.getClassRoutineCollection().remove(classRoutineCollectionNewClassRoutine);
                        oldSectionIdOfClassRoutineCollectionNewClassRoutine = em.merge(oldSectionIdOfClassRoutineCollectionNewClassRoutine);
                    }
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    examMarkCollectionOldExamMark.setSectionId(null);
                    examMarkCollectionOldExamMark = em.merge(examMarkCollectionOldExamMark);
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    Section oldSectionIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getSectionId();
                    examMarkCollectionNewExamMark.setSectionId(section);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldSectionIdOfExamMarkCollectionNewExamMark != null && !oldSectionIdOfExamMarkCollectionNewExamMark.equals(section)) {
                        oldSectionIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldSectionIdOfExamMarkCollectionNewExamMark = em.merge(oldSectionIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    caMarkCollectionOldCaMark.setSectionId(null);
                    caMarkCollectionOldCaMark = em.merge(caMarkCollectionOldCaMark);
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    Section oldSectionIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getSectionId();
                    caMarkCollectionNewCaMark.setSectionId(section);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldSectionIdOfCaMarkCollectionNewCaMark != null && !oldSectionIdOfCaMarkCollectionNewCaMark.equals(section)) {
                        oldSectionIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldSectionIdOfCaMarkCollectionNewCaMark = em.merge(oldSectionIdOfCaMarkCollectionNewCaMark);
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
                Long id = section.getSectionId();
                if (findSection(id) == null) {
                    throw new NonexistentEntityException("The section with id " + id + " no longer exists.");
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
            Section section;
            try {
                section = em.getReference(Section.class, id);
                section.getSectionId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The section with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOrphanCheck = section.getPrincipalTerminalExamCommentCollection();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Section (" + section + ") cannot be destroyed since the PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment + " in its principalTerminalExamCommentCollection field has a non-nullable sectionId field.");
            }
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOrphanCheck = section.getFormMasterTerminalExamCommentCollection();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Section (" + section + ") cannot be destroyed since the FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment + " in its formMasterTerminalExamCommentCollection field has a non-nullable sectionId field.");
            }
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOrphanCheck = section.getStudentBehavouralTraitCollection();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait : studentBehavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Section (" + section + ") cannot be destroyed since the StudentBehavouralTrait " + studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait + " in its studentBehavouralTraitCollection field has a non-nullable sectionId field.");
            }
            Collection<ExamClassPosition> examClassPositionCollectionOrphanCheck = section.getExamClassPositionCollection();
            for (ExamClassPosition examClassPositionCollectionOrphanCheckExamClassPosition : examClassPositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Section (" + section + ") cannot be destroyed since the ExamClassPosition " + examClassPositionCollectionOrphanCheckExamClassPosition + " in its examClassPositionCollection field has a non-nullable sectionId field.");
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOrphanCheck = section.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Section (" + section + ") cannot be destroyed since the SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails + " in its schoolFeeInvoiceDetailsCollection field has a non-nullable sectionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = section.getBranchId();
            if (branchId != null) {
                branchId.getSectionCollection().remove(section);
                branchId = em.merge(branchId);
            }
            Term termId = section.getTermId();
            if (termId != null) {
                termId.getSectionCollection().remove(section);
                termId = em.merge(termId);
            }
            ClassCategory classCategoryId = section.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId.getSectionCollection().remove(section);
                classCategoryId = em.merge(classCategoryId);
            }
            Collection<Student> studentCollection = section.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setSectionId(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<StudentDailyAttendance> studentDailyAttendanceCollection = section.getStudentDailyAttendanceCollection();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : studentDailyAttendanceCollection) {
                studentDailyAttendanceCollectionStudentDailyAttendance.setSectionId(null);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
            }
            Collection<ClassRoutine> classRoutineCollection = section.getClassRoutineCollection();
            for (ClassRoutine classRoutineCollectionClassRoutine : classRoutineCollection) {
                classRoutineCollectionClassRoutine.setSectionId(null);
                classRoutineCollectionClassRoutine = em.merge(classRoutineCollectionClassRoutine);
            }
            Collection<ExamMark> examMarkCollection = section.getExamMarkCollection();
            for (ExamMark examMarkCollectionExamMark : examMarkCollection) {
                examMarkCollectionExamMark.setSectionId(null);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
            }
            Collection<CaMark> caMarkCollection = section.getCaMarkCollection();
            for (CaMark caMarkCollectionCaMark : caMarkCollection) {
                caMarkCollectionCaMark.setSectionId(null);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
            }
            em.remove(section);
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

    public List<Section> findSectionEntities() {
        return findSectionEntities(true, -1, -1);
    }

    public List<Section> findSectionEntities(int maxResults, int firstResult) {
        return findSectionEntities(false, maxResults, firstResult);
    }

    private List<Section> findSectionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Section.class));
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

    public Section findSection(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Section.class, id);
        } finally {
            em.close();
        }
    }

    public int getSectionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Section> rt = cq.from(Section.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
