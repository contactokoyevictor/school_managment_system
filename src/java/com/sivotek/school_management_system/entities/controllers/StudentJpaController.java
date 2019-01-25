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
import com.sivotek.school_management_system.entities.Countries;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.Guardian;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.States;
import com.sivotek.school_management_system.entities.Cities;
import com.sivotek.school_management_system.entities.PrincipalTerminalExamComment;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.SubjectAttendance;
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
import com.sivotek.school_management_system.entities.ExamClassPosition;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
import com.sivotek.school_management_system.entities.StudentDailyAttendance;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
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
public class StudentJpaController implements Serializable {

    public StudentJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Student student) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (student.getPrincipalTerminalExamCommentCollection() == null) {
            student.setPrincipalTerminalExamCommentCollection(new ArrayList<PrincipalTerminalExamComment>());
        }
        if (student.getSubjectAttendanceCollection() == null) {
            student.setSubjectAttendanceCollection(new ArrayList<SubjectAttendance>());
        }
        if (student.getFormMasterTerminalExamCommentCollection() == null) {
            student.setFormMasterTerminalExamCommentCollection(new ArrayList<FormMasterTerminalExamComment>());
        }
        if (student.getUsersCollection() == null) {
            student.setUsersCollection(new ArrayList<Users>());
        }
        if (student.getStudentBehavouralTraitCollection() == null) {
            student.setStudentBehavouralTraitCollection(new ArrayList<StudentBehavouralTrait>());
        }
        if (student.getExamClassPositionCollection() == null) {
            student.setExamClassPositionCollection(new ArrayList<ExamClassPosition>());
        }
        if (student.getSchoolFeeInvoiceDetailsCollection() == null) {
            student.setSchoolFeeInvoiceDetailsCollection(new ArrayList<SchoolFeeInvoiceDetails>());
        }
        if (student.getStudentDailyAttendanceCollection() == null) {
            student.setStudentDailyAttendanceCollection(new ArrayList<StudentDailyAttendance>());
        }
        if (student.getExamMarkCollection() == null) {
            student.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (student.getCaMarkCollection() == null) {
            student.setCaMarkCollection(new ArrayList<CaMark>());
        }
        if (student.getInvoiceCollection() == null) {
            student.setInvoiceCollection(new ArrayList<Invoice>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Countries nationalityId = student.getNationalityId();
            if (nationalityId != null) {
                nationalityId = em.getReference(nationalityId.getClass(), nationalityId.getId());
                student.setNationalityId(nationalityId);
            }
            CompanyBranch branchId = student.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                student.setBranchId(branchId);
            }
            AcademicYears academicYear = student.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                student.setAcademicYear(academicYear);
            }
            Guardian guardianId = student.getGuardianId();
            if (guardianId != null) {
                guardianId = em.getReference(guardianId.getClass(), guardianId.getGuardianId());
                student.setGuardianId(guardianId);
            }
            Class classId = student.getClassId();
            if (classId != null) {
                classId = em.getReference(classId.getClass(), classId.getClassId());
                student.setClassId(classId);
            }
            Term termId = student.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                student.setTermId(termId);
            }
            Section sectionId = student.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                student.setSectionId(sectionId);
            }
            States stateOfOrigin = student.getStateOfOrigin();
            if (stateOfOrigin != null) {
                stateOfOrigin = em.getReference(stateOfOrigin.getClass(), stateOfOrigin.getId());
                student.setStateOfOrigin(stateOfOrigin);
            }
            Cities addressCityId = student.getAddressCityId();
            if (addressCityId != null) {
                addressCityId = em.getReference(addressCityId.getClass(), addressCityId.getId());
                student.setAddressCityId(addressCityId);
            }
            States addressStateId = student.getAddressStateId();
            if (addressStateId != null) {
                addressStateId = em.getReference(addressStateId.getClass(), addressStateId.getId());
                student.setAddressStateId(addressStateId);
            }
            Countries addressCountryId = student.getAddressCountryId();
            if (addressCountryId != null) {
                addressCountryId = em.getReference(addressCountryId.getClass(), addressCountryId.getId());
                student.setAddressCountryId(addressCountryId);
            }
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollection = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach : student.getPrincipalTerminalExamCommentCollection()) {
                principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollection.add(principalTerminalExamCommentCollectionPrincipalTerminalExamCommentToAttach);
            }
            student.setPrincipalTerminalExamCommentCollection(attachedPrincipalTerminalExamCommentCollection);
            Collection<SubjectAttendance> attachedSubjectAttendanceCollection = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendanceToAttach : student.getSubjectAttendanceCollection()) {
                subjectAttendanceCollectionSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollection.add(subjectAttendanceCollectionSubjectAttendanceToAttach);
            }
            student.setSubjectAttendanceCollection(attachedSubjectAttendanceCollection);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollection = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach : student.getFormMasterTerminalExamCommentCollection()) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollection.add(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach);
            }
            student.setFormMasterTerminalExamCommentCollection(attachedFormMasterTerminalExamCommentCollection);
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : student.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getId());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            student.setUsersCollection(attachedUsersCollection);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollection = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTraitToAttach : student.getStudentBehavouralTraitCollection()) {
                studentBehavouralTraitCollectionStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollection.add(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach);
            }
            student.setStudentBehavouralTraitCollection(attachedStudentBehavouralTraitCollection);
            Collection<ExamClassPosition> attachedExamClassPositionCollection = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionExamClassPositionToAttach : student.getExamClassPositionCollection()) {
                examClassPositionCollectionExamClassPositionToAttach = em.getReference(examClassPositionCollectionExamClassPositionToAttach.getClass(), examClassPositionCollectionExamClassPositionToAttach.getId());
                attachedExamClassPositionCollection.add(examClassPositionCollectionExamClassPositionToAttach);
            }
            student.setExamClassPositionCollection(attachedExamClassPositionCollection);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollection = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach : student.getSchoolFeeInvoiceDetailsCollection()) {
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollection.add(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetailsToAttach);
            }
            student.setSchoolFeeInvoiceDetailsCollection(attachedSchoolFeeInvoiceDetailsCollection);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollection = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendanceToAttach : student.getStudentDailyAttendanceCollection()) {
                studentDailyAttendanceCollectionStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollection.add(studentDailyAttendanceCollectionStudentDailyAttendanceToAttach);
            }
            student.setStudentDailyAttendanceCollection(attachedStudentDailyAttendanceCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : student.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            student.setExamMarkCollection(attachedExamMarkCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : student.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            student.setCaMarkCollection(attachedCaMarkCollection);
            Collection<Invoice> attachedInvoiceCollection = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionInvoiceToAttach : student.getInvoiceCollection()) {
                invoiceCollectionInvoiceToAttach = em.getReference(invoiceCollectionInvoiceToAttach.getClass(), invoiceCollectionInvoiceToAttach.getId());
                attachedInvoiceCollection.add(invoiceCollectionInvoiceToAttach);
            }
            student.setInvoiceCollection(attachedInvoiceCollection);
            em.persist(student);
            if (nationalityId != null) {
                nationalityId.getStudentCollection().add(student);
                nationalityId = em.merge(nationalityId);
            }
            if (branchId != null) {
                branchId.getStudentCollection().add(student);
                branchId = em.merge(branchId);
            }
            if (academicYear != null) {
                academicYear.getStudentCollection().add(student);
                academicYear = em.merge(academicYear);
            }
            if (guardianId != null) {
                guardianId.getStudentCollection().add(student);
                guardianId = em.merge(guardianId);
            }
            if (classId != null) {
                classId.getStudentCollection().add(student);
                classId = em.merge(classId);
            }
            if (termId != null) {
                termId.getStudentCollection().add(student);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getStudentCollection().add(student);
                sectionId = em.merge(sectionId);
            }
            if (stateOfOrigin != null) {
                stateOfOrigin.getStudentCollection().add(student);
                stateOfOrigin = em.merge(stateOfOrigin);
            }
            if (addressCityId != null) {
                addressCityId.getStudentCollection().add(student);
                addressCityId = em.merge(addressCityId);
            }
            if (addressStateId != null) {
                addressStateId.getStudentCollection().add(student);
                addressStateId = em.merge(addressStateId);
            }
            if (addressCountryId != null) {
                addressCountryId.getStudentCollection().add(student);
                addressCountryId = em.merge(addressCountryId);
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionPrincipalTerminalExamComment : student.getPrincipalTerminalExamCommentCollection()) {
                Student oldStudentIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = principalTerminalExamCommentCollectionPrincipalTerminalExamComment.getStudentId();
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment.setStudentId(student);
                principalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                if (oldStudentIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment != null) {
                    oldStudentIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                    oldStudentIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment = em.merge(oldStudentIdOfPrincipalTerminalExamCommentCollectionPrincipalTerminalExamComment);
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionSubjectAttendance : student.getSubjectAttendanceCollection()) {
                Student oldStudentIdOfSubjectAttendanceCollectionSubjectAttendance = subjectAttendanceCollectionSubjectAttendance.getStudentId();
                subjectAttendanceCollectionSubjectAttendance.setStudentId(student);
                subjectAttendanceCollectionSubjectAttendance = em.merge(subjectAttendanceCollectionSubjectAttendance);
                if (oldStudentIdOfSubjectAttendanceCollectionSubjectAttendance != null) {
                    oldStudentIdOfSubjectAttendanceCollectionSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionSubjectAttendance);
                    oldStudentIdOfSubjectAttendanceCollectionSubjectAttendance = em.merge(oldStudentIdOfSubjectAttendanceCollectionSubjectAttendance);
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : student.getFormMasterTerminalExamCommentCollection()) {
                Student oldStudentIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getStudentId();
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setStudentId(student);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                if (oldStudentIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment != null) {
                    oldStudentIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                    oldStudentIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(oldStudentIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                }
            }
            for (Users usersCollectionUsers : student.getUsersCollection()) {
                Student oldStudentIdOfUsersCollectionUsers = usersCollectionUsers.getStudentId();
                usersCollectionUsers.setStudentId(student);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldStudentIdOfUsersCollectionUsers != null) {
                    oldStudentIdOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldStudentIdOfUsersCollectionUsers = em.merge(oldStudentIdOfUsersCollectionUsers);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : student.getStudentBehavouralTraitCollection()) {
                Student oldStudentIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = studentBehavouralTraitCollectionStudentBehavouralTrait.getStudentId();
                studentBehavouralTraitCollectionStudentBehavouralTrait.setStudentId(student);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
                if (oldStudentIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait != null) {
                    oldStudentIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionStudentBehavouralTrait);
                    oldStudentIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(oldStudentIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait);
                }
            }
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : student.getExamClassPositionCollection()) {
                Student oldStudentIdOfExamClassPositionCollectionExamClassPosition = examClassPositionCollectionExamClassPosition.getStudentId();
                examClassPositionCollectionExamClassPosition.setStudentId(student);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
                if (oldStudentIdOfExamClassPositionCollectionExamClassPosition != null) {
                    oldStudentIdOfExamClassPositionCollectionExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionExamClassPosition);
                    oldStudentIdOfExamClassPositionCollectionExamClassPosition = em.merge(oldStudentIdOfExamClassPositionCollectionExamClassPosition);
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails : student.getSchoolFeeInvoiceDetailsCollection()) {
                Student oldStudentIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getStudentId();
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.setStudentId(student);
                schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                if (oldStudentIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails != null) {
                    oldStudentIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                    oldStudentIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails = em.merge(oldStudentIdOfSchoolFeeInvoiceDetailsCollectionSchoolFeeInvoiceDetails);
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionStudentDailyAttendance : student.getStudentDailyAttendanceCollection()) {
                Student oldStudentIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = studentDailyAttendanceCollectionStudentDailyAttendance.getStudentId();
                studentDailyAttendanceCollectionStudentDailyAttendance.setStudentId(student);
                studentDailyAttendanceCollectionStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionStudentDailyAttendance);
                if (oldStudentIdOfStudentDailyAttendanceCollectionStudentDailyAttendance != null) {
                    oldStudentIdOfStudentDailyAttendanceCollectionStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionStudentDailyAttendance);
                    oldStudentIdOfStudentDailyAttendanceCollectionStudentDailyAttendance = em.merge(oldStudentIdOfStudentDailyAttendanceCollectionStudentDailyAttendance);
                }
            }
            for (ExamMark examMarkCollectionExamMark : student.getExamMarkCollection()) {
                Student oldStudentIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getStudentId();
                examMarkCollectionExamMark.setStudentId(student);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldStudentIdOfExamMarkCollectionExamMark != null) {
                    oldStudentIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldStudentIdOfExamMarkCollectionExamMark = em.merge(oldStudentIdOfExamMarkCollectionExamMark);
                }
            }
            for (CaMark caMarkCollectionCaMark : student.getCaMarkCollection()) {
                Student oldStudentIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getStudentId();
                caMarkCollectionCaMark.setStudentId(student);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldStudentIdOfCaMarkCollectionCaMark != null) {
                    oldStudentIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldStudentIdOfCaMarkCollectionCaMark = em.merge(oldStudentIdOfCaMarkCollectionCaMark);
                }
            }
            for (Invoice invoiceCollectionInvoice : student.getInvoiceCollection()) {
                Student oldStudentIdOfInvoiceCollectionInvoice = invoiceCollectionInvoice.getStudentId();
                invoiceCollectionInvoice.setStudentId(student);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
                if (oldStudentIdOfInvoiceCollectionInvoice != null) {
                    oldStudentIdOfInvoiceCollectionInvoice.getInvoiceCollection().remove(invoiceCollectionInvoice);
                    oldStudentIdOfInvoiceCollectionInvoice = em.merge(oldStudentIdOfInvoiceCollectionInvoice);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findStudent(student.getStudentId()) != null) {
                throw new PreexistingEntityException("Student " + student + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Student student) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Student persistentStudent = em.find(Student.class, student.getStudentId());
            Countries nationalityIdOld = persistentStudent.getNationalityId();
            Countries nationalityIdNew = student.getNationalityId();
            CompanyBranch branchIdOld = persistentStudent.getBranchId();
            CompanyBranch branchIdNew = student.getBranchId();
            AcademicYears academicYearOld = persistentStudent.getAcademicYear();
            AcademicYears academicYearNew = student.getAcademicYear();
            Guardian guardianIdOld = persistentStudent.getGuardianId();
            Guardian guardianIdNew = student.getGuardianId();
            Class classIdOld = persistentStudent.getClassId();
            Class classIdNew = student.getClassId();
            Term termIdOld = persistentStudent.getTermId();
            Term termIdNew = student.getTermId();
            Section sectionIdOld = persistentStudent.getSectionId();
            Section sectionIdNew = student.getSectionId();
            States stateOfOriginOld = persistentStudent.getStateOfOrigin();
            States stateOfOriginNew = student.getStateOfOrigin();
            Cities addressCityIdOld = persistentStudent.getAddressCityId();
            Cities addressCityIdNew = student.getAddressCityId();
            States addressStateIdOld = persistentStudent.getAddressStateId();
            States addressStateIdNew = student.getAddressStateId();
            Countries addressCountryIdOld = persistentStudent.getAddressCountryId();
            Countries addressCountryIdNew = student.getAddressCountryId();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOld = persistentStudent.getPrincipalTerminalExamCommentCollection();
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionNew = student.getPrincipalTerminalExamCommentCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionOld = persistentStudent.getSubjectAttendanceCollection();
            Collection<SubjectAttendance> subjectAttendanceCollectionNew = student.getSubjectAttendanceCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOld = persistentStudent.getFormMasterTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionNew = student.getFormMasterTerminalExamCommentCollection();
            Collection<Users> usersCollectionOld = persistentStudent.getUsersCollection();
            Collection<Users> usersCollectionNew = student.getUsersCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOld = persistentStudent.getStudentBehavouralTraitCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionNew = student.getStudentBehavouralTraitCollection();
            Collection<ExamClassPosition> examClassPositionCollectionOld = persistentStudent.getExamClassPositionCollection();
            Collection<ExamClassPosition> examClassPositionCollectionNew = student.getExamClassPositionCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOld = persistentStudent.getSchoolFeeInvoiceDetailsCollection();
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionNew = student.getSchoolFeeInvoiceDetailsCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOld = persistentStudent.getStudentDailyAttendanceCollection();
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionNew = student.getStudentDailyAttendanceCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentStudent.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = student.getExamMarkCollection();
            Collection<CaMark> caMarkCollectionOld = persistentStudent.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = student.getCaMarkCollection();
            Collection<Invoice> invoiceCollectionOld = persistentStudent.getInvoiceCollection();
            Collection<Invoice> invoiceCollectionNew = student.getInvoiceCollection();
            List<String> illegalOrphanMessages = null;
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOld) {
                if (!principalTerminalExamCommentCollectionNew.contains(principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOldPrincipalTerminalExamComment + " since its studentId field is not nullable.");
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionOldSubjectAttendance : subjectAttendanceCollectionOld) {
                if (!subjectAttendanceCollectionNew.contains(subjectAttendanceCollectionOldSubjectAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SubjectAttendance " + subjectAttendanceCollectionOldSubjectAttendance + " since its studentId field is not nullable.");
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOld) {
                if (!formMasterTerminalExamCommentCollectionNew.contains(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment + " since its studentId field is not nullable.");
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOldStudentBehavouralTrait : studentBehavouralTraitCollectionOld) {
                if (!studentBehavouralTraitCollectionNew.contains(studentBehavouralTraitCollectionOldStudentBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentBehavouralTrait " + studentBehavouralTraitCollectionOldStudentBehavouralTrait + " since its studentId field is not nullable.");
                }
            }
            for (ExamClassPosition examClassPositionCollectionOldExamClassPosition : examClassPositionCollectionOld) {
                if (!examClassPositionCollectionNew.contains(examClassPositionCollectionOldExamClassPosition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamClassPosition " + examClassPositionCollectionOldExamClassPosition + " since its studentId field is not nullable.");
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOld) {
                if (!schoolFeeInvoiceDetailsCollectionNew.contains(schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOldSchoolFeeInvoiceDetails + " since its studentId field is not nullable.");
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionOldStudentDailyAttendance : studentDailyAttendanceCollectionOld) {
                if (!studentDailyAttendanceCollectionNew.contains(studentDailyAttendanceCollectionOldStudentDailyAttendance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentDailyAttendance " + studentDailyAttendanceCollectionOldStudentDailyAttendance + " since its studentId field is not nullable.");
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamMark " + examMarkCollectionOldExamMark + " since its studentId field is not nullable.");
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaMark " + caMarkCollectionOldCaMark + " since its studentId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (nationalityIdNew != null) {
                nationalityIdNew = em.getReference(nationalityIdNew.getClass(), nationalityIdNew.getId());
                student.setNationalityId(nationalityIdNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                student.setBranchId(branchIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                student.setAcademicYear(academicYearNew);
            }
            if (guardianIdNew != null) {
                guardianIdNew = em.getReference(guardianIdNew.getClass(), guardianIdNew.getGuardianId());
                student.setGuardianId(guardianIdNew);
            }
            if (classIdNew != null) {
                classIdNew = em.getReference(classIdNew.getClass(), classIdNew.getClassId());
                student.setClassId(classIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                student.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                student.setSectionId(sectionIdNew);
            }
            if (stateOfOriginNew != null) {
                stateOfOriginNew = em.getReference(stateOfOriginNew.getClass(), stateOfOriginNew.getId());
                student.setStateOfOrigin(stateOfOriginNew);
            }
            if (addressCityIdNew != null) {
                addressCityIdNew = em.getReference(addressCityIdNew.getClass(), addressCityIdNew.getId());
                student.setAddressCityId(addressCityIdNew);
            }
            if (addressStateIdNew != null) {
                addressStateIdNew = em.getReference(addressStateIdNew.getClass(), addressStateIdNew.getId());
                student.setAddressStateId(addressStateIdNew);
            }
            if (addressCountryIdNew != null) {
                addressCountryIdNew = em.getReference(addressCountryIdNew.getClass(), addressCountryIdNew.getId());
                student.setAddressCountryId(addressCountryIdNew);
            }
            Collection<PrincipalTerminalExamComment> attachedPrincipalTerminalExamCommentCollectionNew = new ArrayList<PrincipalTerminalExamComment>();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach : principalTerminalExamCommentCollectionNew) {
                principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach = em.getReference(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getClass(), principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach.getId());
                attachedPrincipalTerminalExamCommentCollectionNew.add(principalTerminalExamCommentCollectionNewPrincipalTerminalExamCommentToAttach);
            }
            principalTerminalExamCommentCollectionNew = attachedPrincipalTerminalExamCommentCollectionNew;
            student.setPrincipalTerminalExamCommentCollection(principalTerminalExamCommentCollectionNew);
            Collection<SubjectAttendance> attachedSubjectAttendanceCollectionNew = new ArrayList<SubjectAttendance>();
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendanceToAttach : subjectAttendanceCollectionNew) {
                subjectAttendanceCollectionNewSubjectAttendanceToAttach = em.getReference(subjectAttendanceCollectionNewSubjectAttendanceToAttach.getClass(), subjectAttendanceCollectionNewSubjectAttendanceToAttach.getAttendanceId());
                attachedSubjectAttendanceCollectionNew.add(subjectAttendanceCollectionNewSubjectAttendanceToAttach);
            }
            subjectAttendanceCollectionNew = attachedSubjectAttendanceCollectionNew;
            student.setSubjectAttendanceCollection(subjectAttendanceCollectionNew);
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollectionNew = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach : formMasterTerminalExamCommentCollectionNew) {
                formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollectionNew.add(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach);
            }
            formMasterTerminalExamCommentCollectionNew = attachedFormMasterTerminalExamCommentCollectionNew;
            student.setFormMasterTerminalExamCommentCollection(formMasterTerminalExamCommentCollectionNew);
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getId());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            student.setUsersCollection(usersCollectionNew);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollectionNew = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach : studentBehavouralTraitCollectionNew) {
                studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollectionNew.add(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach);
            }
            studentBehavouralTraitCollectionNew = attachedStudentBehavouralTraitCollectionNew;
            student.setStudentBehavouralTraitCollection(studentBehavouralTraitCollectionNew);
            Collection<ExamClassPosition> attachedExamClassPositionCollectionNew = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionNewExamClassPositionToAttach : examClassPositionCollectionNew) {
                examClassPositionCollectionNewExamClassPositionToAttach = em.getReference(examClassPositionCollectionNewExamClassPositionToAttach.getClass(), examClassPositionCollectionNewExamClassPositionToAttach.getId());
                attachedExamClassPositionCollectionNew.add(examClassPositionCollectionNewExamClassPositionToAttach);
            }
            examClassPositionCollectionNew = attachedExamClassPositionCollectionNew;
            student.setExamClassPositionCollection(examClassPositionCollectionNew);
            Collection<SchoolFeeInvoiceDetails> attachedSchoolFeeInvoiceDetailsCollectionNew = new ArrayList<SchoolFeeInvoiceDetails>();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach : schoolFeeInvoiceDetailsCollectionNew) {
                schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach = em.getReference(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getClass(), schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach.getId());
                attachedSchoolFeeInvoiceDetailsCollectionNew.add(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetailsToAttach);
            }
            schoolFeeInvoiceDetailsCollectionNew = attachedSchoolFeeInvoiceDetailsCollectionNew;
            student.setSchoolFeeInvoiceDetailsCollection(schoolFeeInvoiceDetailsCollectionNew);
            Collection<StudentDailyAttendance> attachedStudentDailyAttendanceCollectionNew = new ArrayList<StudentDailyAttendance>();
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach : studentDailyAttendanceCollectionNew) {
                studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach = em.getReference(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getClass(), studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach.getAttendanceId());
                attachedStudentDailyAttendanceCollectionNew.add(studentDailyAttendanceCollectionNewStudentDailyAttendanceToAttach);
            }
            studentDailyAttendanceCollectionNew = attachedStudentDailyAttendanceCollectionNew;
            student.setStudentDailyAttendanceCollection(studentDailyAttendanceCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            student.setExamMarkCollection(examMarkCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            student.setCaMarkCollection(caMarkCollectionNew);
            Collection<Invoice> attachedInvoiceCollectionNew = new ArrayList<Invoice>();
            for (Invoice invoiceCollectionNewInvoiceToAttach : invoiceCollectionNew) {
                invoiceCollectionNewInvoiceToAttach = em.getReference(invoiceCollectionNewInvoiceToAttach.getClass(), invoiceCollectionNewInvoiceToAttach.getId());
                attachedInvoiceCollectionNew.add(invoiceCollectionNewInvoiceToAttach);
            }
            invoiceCollectionNew = attachedInvoiceCollectionNew;
            student.setInvoiceCollection(invoiceCollectionNew);
            student = em.merge(student);
            if (nationalityIdOld != null && !nationalityIdOld.equals(nationalityIdNew)) {
                nationalityIdOld.getStudentCollection().remove(student);
                nationalityIdOld = em.merge(nationalityIdOld);
            }
            if (nationalityIdNew != null && !nationalityIdNew.equals(nationalityIdOld)) {
                nationalityIdNew.getStudentCollection().add(student);
                nationalityIdNew = em.merge(nationalityIdNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getStudentCollection().remove(student);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getStudentCollection().add(student);
                branchIdNew = em.merge(branchIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getStudentCollection().remove(student);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getStudentCollection().add(student);
                academicYearNew = em.merge(academicYearNew);
            }
            if (guardianIdOld != null && !guardianIdOld.equals(guardianIdNew)) {
                guardianIdOld.getStudentCollection().remove(student);
                guardianIdOld = em.merge(guardianIdOld);
            }
            if (guardianIdNew != null && !guardianIdNew.equals(guardianIdOld)) {
                guardianIdNew.getStudentCollection().add(student);
                guardianIdNew = em.merge(guardianIdNew);
            }
            if (classIdOld != null && !classIdOld.equals(classIdNew)) {
                classIdOld.getStudentCollection().remove(student);
                classIdOld = em.merge(classIdOld);
            }
            if (classIdNew != null && !classIdNew.equals(classIdOld)) {
                classIdNew.getStudentCollection().add(student);
                classIdNew = em.merge(classIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getStudentCollection().remove(student);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getStudentCollection().add(student);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getStudentCollection().remove(student);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getStudentCollection().add(student);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (stateOfOriginOld != null && !stateOfOriginOld.equals(stateOfOriginNew)) {
                stateOfOriginOld.getStudentCollection().remove(student);
                stateOfOriginOld = em.merge(stateOfOriginOld);
            }
            if (stateOfOriginNew != null && !stateOfOriginNew.equals(stateOfOriginOld)) {
                stateOfOriginNew.getStudentCollection().add(student);
                stateOfOriginNew = em.merge(stateOfOriginNew);
            }
            if (addressCityIdOld != null && !addressCityIdOld.equals(addressCityIdNew)) {
                addressCityIdOld.getStudentCollection().remove(student);
                addressCityIdOld = em.merge(addressCityIdOld);
            }
            if (addressCityIdNew != null && !addressCityIdNew.equals(addressCityIdOld)) {
                addressCityIdNew.getStudentCollection().add(student);
                addressCityIdNew = em.merge(addressCityIdNew);
            }
            if (addressStateIdOld != null && !addressStateIdOld.equals(addressStateIdNew)) {
                addressStateIdOld.getStudentCollection().remove(student);
                addressStateIdOld = em.merge(addressStateIdOld);
            }
            if (addressStateIdNew != null && !addressStateIdNew.equals(addressStateIdOld)) {
                addressStateIdNew.getStudentCollection().add(student);
                addressStateIdNew = em.merge(addressStateIdNew);
            }
            if (addressCountryIdOld != null && !addressCountryIdOld.equals(addressCountryIdNew)) {
                addressCountryIdOld.getStudentCollection().remove(student);
                addressCountryIdOld = em.merge(addressCountryIdOld);
            }
            if (addressCountryIdNew != null && !addressCountryIdNew.equals(addressCountryIdOld)) {
                addressCountryIdNew.getStudentCollection().add(student);
                addressCountryIdNew = em.merge(addressCountryIdNew);
            }
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment : principalTerminalExamCommentCollectionNew) {
                if (!principalTerminalExamCommentCollectionOld.contains(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment)) {
                    Student oldStudentIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getStudentId();
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.setStudentId(student);
                    principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    if (oldStudentIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment != null && !oldStudentIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.equals(student)) {
                        oldStudentIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment.getPrincipalTerminalExamCommentCollection().remove(principalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                        oldStudentIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment = em.merge(oldStudentIdOfPrincipalTerminalExamCommentCollectionNewPrincipalTerminalExamComment);
                    }
                }
            }
            for (SubjectAttendance subjectAttendanceCollectionNewSubjectAttendance : subjectAttendanceCollectionNew) {
                if (!subjectAttendanceCollectionOld.contains(subjectAttendanceCollectionNewSubjectAttendance)) {
                    Student oldStudentIdOfSubjectAttendanceCollectionNewSubjectAttendance = subjectAttendanceCollectionNewSubjectAttendance.getStudentId();
                    subjectAttendanceCollectionNewSubjectAttendance.setStudentId(student);
                    subjectAttendanceCollectionNewSubjectAttendance = em.merge(subjectAttendanceCollectionNewSubjectAttendance);
                    if (oldStudentIdOfSubjectAttendanceCollectionNewSubjectAttendance != null && !oldStudentIdOfSubjectAttendanceCollectionNewSubjectAttendance.equals(student)) {
                        oldStudentIdOfSubjectAttendanceCollectionNewSubjectAttendance.getSubjectAttendanceCollection().remove(subjectAttendanceCollectionNewSubjectAttendance);
                        oldStudentIdOfSubjectAttendanceCollectionNewSubjectAttendance = em.merge(oldStudentIdOfSubjectAttendanceCollectionNewSubjectAttendance);
                    }
                }
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionNew) {
                if (!formMasterTerminalExamCommentCollectionOld.contains(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment)) {
                    Student oldStudentIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getStudentId();
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.setStudentId(student);
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    if (oldStudentIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment != null && !oldStudentIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.equals(student)) {
                        oldStudentIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                        oldStudentIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(oldStudentIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    }
                }
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    usersCollectionOldUsers.setStudentId(null);
                    usersCollectionOldUsers = em.merge(usersCollectionOldUsers);
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    Student oldStudentIdOfUsersCollectionNewUsers = usersCollectionNewUsers.getStudentId();
                    usersCollectionNewUsers.setStudentId(student);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldStudentIdOfUsersCollectionNewUsers != null && !oldStudentIdOfUsersCollectionNewUsers.equals(student)) {
                        oldStudentIdOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldStudentIdOfUsersCollectionNewUsers = em.merge(oldStudentIdOfUsersCollectionNewUsers);
                    }
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTrait : studentBehavouralTraitCollectionNew) {
                if (!studentBehavouralTraitCollectionOld.contains(studentBehavouralTraitCollectionNewStudentBehavouralTrait)) {
                    Student oldStudentIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = studentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentId();
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait.setStudentId(student);
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    if (oldStudentIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait != null && !oldStudentIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.equals(student)) {
                        oldStudentIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                        oldStudentIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(oldStudentIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    }
                }
            }
            for (ExamClassPosition examClassPositionCollectionNewExamClassPosition : examClassPositionCollectionNew) {
                if (!examClassPositionCollectionOld.contains(examClassPositionCollectionNewExamClassPosition)) {
                    Student oldStudentIdOfExamClassPositionCollectionNewExamClassPosition = examClassPositionCollectionNewExamClassPosition.getStudentId();
                    examClassPositionCollectionNewExamClassPosition.setStudentId(student);
                    examClassPositionCollectionNewExamClassPosition = em.merge(examClassPositionCollectionNewExamClassPosition);
                    if (oldStudentIdOfExamClassPositionCollectionNewExamClassPosition != null && !oldStudentIdOfExamClassPositionCollectionNewExamClassPosition.equals(student)) {
                        oldStudentIdOfExamClassPositionCollectionNewExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionNewExamClassPosition);
                        oldStudentIdOfExamClassPositionCollectionNewExamClassPosition = em.merge(oldStudentIdOfExamClassPositionCollectionNewExamClassPosition);
                    }
                }
            }
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionNew) {
                if (!schoolFeeInvoiceDetailsCollectionOld.contains(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails)) {
                    Student oldStudentIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getStudentId();
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.setStudentId(student);
                    schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    if (oldStudentIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails != null && !oldStudentIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.equals(student)) {
                        oldStudentIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                        oldStudentIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails = em.merge(oldStudentIdOfSchoolFeeInvoiceDetailsCollectionNewSchoolFeeInvoiceDetails);
                    }
                }
            }
            for (StudentDailyAttendance studentDailyAttendanceCollectionNewStudentDailyAttendance : studentDailyAttendanceCollectionNew) {
                if (!studentDailyAttendanceCollectionOld.contains(studentDailyAttendanceCollectionNewStudentDailyAttendance)) {
                    Student oldStudentIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = studentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentId();
                    studentDailyAttendanceCollectionNewStudentDailyAttendance.setStudentId(student);
                    studentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                    if (oldStudentIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance != null && !oldStudentIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.equals(student)) {
                        oldStudentIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance.getStudentDailyAttendanceCollection().remove(studentDailyAttendanceCollectionNewStudentDailyAttendance);
                        oldStudentIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance = em.merge(oldStudentIdOfStudentDailyAttendanceCollectionNewStudentDailyAttendance);
                    }
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    Student oldStudentIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getStudentId();
                    examMarkCollectionNewExamMark.setStudentId(student);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldStudentIdOfExamMarkCollectionNewExamMark != null && !oldStudentIdOfExamMarkCollectionNewExamMark.equals(student)) {
                        oldStudentIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldStudentIdOfExamMarkCollectionNewExamMark = em.merge(oldStudentIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    Student oldStudentIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getStudentId();
                    caMarkCollectionNewCaMark.setStudentId(student);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldStudentIdOfCaMarkCollectionNewCaMark != null && !oldStudentIdOfCaMarkCollectionNewCaMark.equals(student)) {
                        oldStudentIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldStudentIdOfCaMarkCollectionNewCaMark = em.merge(oldStudentIdOfCaMarkCollectionNewCaMark);
                    }
                }
            }
            for (Invoice invoiceCollectionOldInvoice : invoiceCollectionOld) {
                if (!invoiceCollectionNew.contains(invoiceCollectionOldInvoice)) {
                    invoiceCollectionOldInvoice.setStudentId(null);
                    invoiceCollectionOldInvoice = em.merge(invoiceCollectionOldInvoice);
                }
            }
            for (Invoice invoiceCollectionNewInvoice : invoiceCollectionNew) {
                if (!invoiceCollectionOld.contains(invoiceCollectionNewInvoice)) {
                    Student oldStudentIdOfInvoiceCollectionNewInvoice = invoiceCollectionNewInvoice.getStudentId();
                    invoiceCollectionNewInvoice.setStudentId(student);
                    invoiceCollectionNewInvoice = em.merge(invoiceCollectionNewInvoice);
                    if (oldStudentIdOfInvoiceCollectionNewInvoice != null && !oldStudentIdOfInvoiceCollectionNewInvoice.equals(student)) {
                        oldStudentIdOfInvoiceCollectionNewInvoice.getInvoiceCollection().remove(invoiceCollectionNewInvoice);
                        oldStudentIdOfInvoiceCollectionNewInvoice = em.merge(oldStudentIdOfInvoiceCollectionNewInvoice);
                    }
                }
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
                Long id = student.getStudentId();
                if (findStudent(id) == null) {
                    throw new NonexistentEntityException("The student with id " + id + " no longer exists.");
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
            utx.begin();
            em = getEntityManager();
            Student student;
            try {
                student = em.getReference(Student.class, id);
                student.getStudentId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The student with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PrincipalTerminalExamComment> principalTerminalExamCommentCollectionOrphanCheck = student.getPrincipalTerminalExamCommentCollection();
            for (PrincipalTerminalExamComment principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment : principalTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the PrincipalTerminalExamComment " + principalTerminalExamCommentCollectionOrphanCheckPrincipalTerminalExamComment + " in its principalTerminalExamCommentCollection field has a non-nullable studentId field.");
            }
            Collection<SubjectAttendance> subjectAttendanceCollectionOrphanCheck = student.getSubjectAttendanceCollection();
            for (SubjectAttendance subjectAttendanceCollectionOrphanCheckSubjectAttendance : subjectAttendanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the SubjectAttendance " + subjectAttendanceCollectionOrphanCheckSubjectAttendance + " in its subjectAttendanceCollection field has a non-nullable studentId field.");
            }
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOrphanCheck = student.getFormMasterTerminalExamCommentCollection();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment + " in its formMasterTerminalExamCommentCollection field has a non-nullable studentId field.");
            }
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOrphanCheck = student.getStudentBehavouralTraitCollection();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait : studentBehavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the StudentBehavouralTrait " + studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait + " in its studentBehavouralTraitCollection field has a non-nullable studentId field.");
            }
            Collection<ExamClassPosition> examClassPositionCollectionOrphanCheck = student.getExamClassPositionCollection();
            for (ExamClassPosition examClassPositionCollectionOrphanCheckExamClassPosition : examClassPositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the ExamClassPosition " + examClassPositionCollectionOrphanCheckExamClassPosition + " in its examClassPositionCollection field has a non-nullable studentId field.");
            }
            Collection<SchoolFeeInvoiceDetails> schoolFeeInvoiceDetailsCollectionOrphanCheck = student.getSchoolFeeInvoiceDetailsCollection();
            for (SchoolFeeInvoiceDetails schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails : schoolFeeInvoiceDetailsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetailsCollectionOrphanCheckSchoolFeeInvoiceDetails + " in its schoolFeeInvoiceDetailsCollection field has a non-nullable studentId field.");
            }
            Collection<StudentDailyAttendance> studentDailyAttendanceCollectionOrphanCheck = student.getStudentDailyAttendanceCollection();
            for (StudentDailyAttendance studentDailyAttendanceCollectionOrphanCheckStudentDailyAttendance : studentDailyAttendanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the StudentDailyAttendance " + studentDailyAttendanceCollectionOrphanCheckStudentDailyAttendance + " in its studentDailyAttendanceCollection field has a non-nullable studentId field.");
            }
            Collection<ExamMark> examMarkCollectionOrphanCheck = student.getExamMarkCollection();
            for (ExamMark examMarkCollectionOrphanCheckExamMark : examMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the ExamMark " + examMarkCollectionOrphanCheckExamMark + " in its examMarkCollection field has a non-nullable studentId field.");
            }
            Collection<CaMark> caMarkCollectionOrphanCheck = student.getCaMarkCollection();
            for (CaMark caMarkCollectionOrphanCheckCaMark : caMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Student (" + student + ") cannot be destroyed since the CaMark " + caMarkCollectionOrphanCheckCaMark + " in its caMarkCollection field has a non-nullable studentId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Countries nationalityId = student.getNationalityId();
            if (nationalityId != null) {
                nationalityId.getStudentCollection().remove(student);
                nationalityId = em.merge(nationalityId);
            }
            CompanyBranch branchId = student.getBranchId();
            if (branchId != null) {
                branchId.getStudentCollection().remove(student);
                branchId = em.merge(branchId);
            }
            AcademicYears academicYear = student.getAcademicYear();
            if (academicYear != null) {
                academicYear.getStudentCollection().remove(student);
                academicYear = em.merge(academicYear);
            }
            Guardian guardianId = student.getGuardianId();
            if (guardianId != null) {
                guardianId.getStudentCollection().remove(student);
                guardianId = em.merge(guardianId);
            }
            Class classId = student.getClassId();
            if (classId != null) {
                classId.getStudentCollection().remove(student);
                classId = em.merge(classId);
            }
            Term termId = student.getTermId();
            if (termId != null) {
                termId.getStudentCollection().remove(student);
                termId = em.merge(termId);
            }
            Section sectionId = student.getSectionId();
            if (sectionId != null) {
                sectionId.getStudentCollection().remove(student);
                sectionId = em.merge(sectionId);
            }
            States stateOfOrigin = student.getStateOfOrigin();
            if (stateOfOrigin != null) {
                stateOfOrigin.getStudentCollection().remove(student);
                stateOfOrigin = em.merge(stateOfOrigin);
            }
            Cities addressCityId = student.getAddressCityId();
            if (addressCityId != null) {
                addressCityId.getStudentCollection().remove(student);
                addressCityId = em.merge(addressCityId);
            }
            States addressStateId = student.getAddressStateId();
            if (addressStateId != null) {
                addressStateId.getStudentCollection().remove(student);
                addressStateId = em.merge(addressStateId);
            }
            Countries addressCountryId = student.getAddressCountryId();
            if (addressCountryId != null) {
                addressCountryId.getStudentCollection().remove(student);
                addressCountryId = em.merge(addressCountryId);
            }
            Collection<Users> usersCollection = student.getUsersCollection();
            for (Users usersCollectionUsers : usersCollection) {
                usersCollectionUsers.setStudentId(null);
                usersCollectionUsers = em.merge(usersCollectionUsers);
            }
            Collection<Invoice> invoiceCollection = student.getInvoiceCollection();
            for (Invoice invoiceCollectionInvoice : invoiceCollection) {
                invoiceCollectionInvoice.setStudentId(null);
                invoiceCollectionInvoice = em.merge(invoiceCollectionInvoice);
            }
            em.remove(student);
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

    public List<Student> findStudentEntities() {
        return findStudentEntities(true, -1, -1);
    }

    public List<Student> findStudentEntities(int maxResults, int firstResult) {
        return findStudentEntities(false, maxResults, firstResult);
    }

    private List<Student> findStudentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Student.class));
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

    public Student findStudent(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Student.class, id);
        } finally {
            em.close();
        }
    }

    public int getStudentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Student> rt = cq.from(Student.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
