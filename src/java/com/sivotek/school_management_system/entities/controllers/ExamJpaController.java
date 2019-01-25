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
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.FormMasterTerminalExamComment;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.StudentBehavouralTrait;
import com.sivotek.school_management_system.entities.ExamClassPosition;
import com.sivotek.school_management_system.entities.ExamMark;
import com.sivotek.school_management_system.entities.CaMark;
import com.sivotek.school_management_system.entities.Exam;
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
public class ExamJpaController implements Serializable {

    public ExamJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Exam exam) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (exam.getFormMasterTerminalExamCommentCollection() == null) {
            exam.setFormMasterTerminalExamCommentCollection(new ArrayList<FormMasterTerminalExamComment>());
        }
        if (exam.getStudentBehavouralTraitCollection() == null) {
            exam.setStudentBehavouralTraitCollection(new ArrayList<StudentBehavouralTrait>());
        }
        if (exam.getExamClassPositionCollection() == null) {
            exam.setExamClassPositionCollection(new ArrayList<ExamClassPosition>());
        }
        if (exam.getExamMarkCollection() == null) {
            exam.setExamMarkCollection(new ArrayList<ExamMark>());
        }
        if (exam.getCaMarkCollection() == null) {
            exam.setCaMarkCollection(new ArrayList<CaMark>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = exam.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                exam.setBranchId(branchId);
            }
            AcademicYears academicYear = exam.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                exam.setAcademicYear(academicYear);
            }
            ClassCategory classCategoryId = exam.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId = em.getReference(classCategoryId.getClass(), classCategoryId.getCategoryId());
                exam.setClassCategoryId(classCategoryId);
            }
            Term termId = exam.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                exam.setTermId(termId);
            }
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollection = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach : exam.getFormMasterTerminalExamCommentCollection()) {
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollection.add(formMasterTerminalExamCommentCollectionFormMasterTerminalExamCommentToAttach);
            }
            exam.setFormMasterTerminalExamCommentCollection(attachedFormMasterTerminalExamCommentCollection);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollection = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTraitToAttach : exam.getStudentBehavouralTraitCollection()) {
                studentBehavouralTraitCollectionStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollection.add(studentBehavouralTraitCollectionStudentBehavouralTraitToAttach);
            }
            exam.setStudentBehavouralTraitCollection(attachedStudentBehavouralTraitCollection);
            Collection<ExamClassPosition> attachedExamClassPositionCollection = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionExamClassPositionToAttach : exam.getExamClassPositionCollection()) {
                examClassPositionCollectionExamClassPositionToAttach = em.getReference(examClassPositionCollectionExamClassPositionToAttach.getClass(), examClassPositionCollectionExamClassPositionToAttach.getId());
                attachedExamClassPositionCollection.add(examClassPositionCollectionExamClassPositionToAttach);
            }
            exam.setExamClassPositionCollection(attachedExamClassPositionCollection);
            Collection<ExamMark> attachedExamMarkCollection = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionExamMarkToAttach : exam.getExamMarkCollection()) {
                examMarkCollectionExamMarkToAttach = em.getReference(examMarkCollectionExamMarkToAttach.getClass(), examMarkCollectionExamMarkToAttach.getMarkId());
                attachedExamMarkCollection.add(examMarkCollectionExamMarkToAttach);
            }
            exam.setExamMarkCollection(attachedExamMarkCollection);
            Collection<CaMark> attachedCaMarkCollection = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionCaMarkToAttach : exam.getCaMarkCollection()) {
                caMarkCollectionCaMarkToAttach = em.getReference(caMarkCollectionCaMarkToAttach.getClass(), caMarkCollectionCaMarkToAttach.getCaId());
                attachedCaMarkCollection.add(caMarkCollectionCaMarkToAttach);
            }
            exam.setCaMarkCollection(attachedCaMarkCollection);
            em.persist(exam);
            if (branchId != null) {
                branchId.getExamCollection().add(exam);
                branchId = em.merge(branchId);
            }
            if (academicYear != null) {
                academicYear.getExamCollection().add(exam);
                academicYear = em.merge(academicYear);
            }
            if (classCategoryId != null) {
                classCategoryId.getExamCollection().add(exam);
                classCategoryId = em.merge(classCategoryId);
            }
            if (termId != null) {
                termId.getExamCollection().add(exam);
                termId = em.merge(termId);
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment : exam.getFormMasterTerminalExamCommentCollection()) {
                Exam oldExamIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getExamId();
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.setExamId(exam);
                formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                if (oldExamIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment != null) {
                    oldExamIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                    oldExamIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment = em.merge(oldExamIdOfFormMasterTerminalExamCommentCollectionFormMasterTerminalExamComment);
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionStudentBehavouralTrait : exam.getStudentBehavouralTraitCollection()) {
                Exam oldExamIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = studentBehavouralTraitCollectionStudentBehavouralTrait.getExamId();
                studentBehavouralTraitCollectionStudentBehavouralTrait.setExamId(exam);
                studentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionStudentBehavouralTrait);
                if (oldExamIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait != null) {
                    oldExamIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionStudentBehavouralTrait);
                    oldExamIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait = em.merge(oldExamIdOfStudentBehavouralTraitCollectionStudentBehavouralTrait);
                }
            }
            for (ExamClassPosition examClassPositionCollectionExamClassPosition : exam.getExamClassPositionCollection()) {
                Exam oldExamIdOfExamClassPositionCollectionExamClassPosition = examClassPositionCollectionExamClassPosition.getExamId();
                examClassPositionCollectionExamClassPosition.setExamId(exam);
                examClassPositionCollectionExamClassPosition = em.merge(examClassPositionCollectionExamClassPosition);
                if (oldExamIdOfExamClassPositionCollectionExamClassPosition != null) {
                    oldExamIdOfExamClassPositionCollectionExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionExamClassPosition);
                    oldExamIdOfExamClassPositionCollectionExamClassPosition = em.merge(oldExamIdOfExamClassPositionCollectionExamClassPosition);
                }
            }
            for (ExamMark examMarkCollectionExamMark : exam.getExamMarkCollection()) {
                Exam oldExamIdOfExamMarkCollectionExamMark = examMarkCollectionExamMark.getExamId();
                examMarkCollectionExamMark.setExamId(exam);
                examMarkCollectionExamMark = em.merge(examMarkCollectionExamMark);
                if (oldExamIdOfExamMarkCollectionExamMark != null) {
                    oldExamIdOfExamMarkCollectionExamMark.getExamMarkCollection().remove(examMarkCollectionExamMark);
                    oldExamIdOfExamMarkCollectionExamMark = em.merge(oldExamIdOfExamMarkCollectionExamMark);
                }
            }
            for (CaMark caMarkCollectionCaMark : exam.getCaMarkCollection()) {
                Exam oldExamIdOfCaMarkCollectionCaMark = caMarkCollectionCaMark.getExamId();
                caMarkCollectionCaMark.setExamId(exam);
                caMarkCollectionCaMark = em.merge(caMarkCollectionCaMark);
                if (oldExamIdOfCaMarkCollectionCaMark != null) {
                    oldExamIdOfCaMarkCollectionCaMark.getCaMarkCollection().remove(caMarkCollectionCaMark);
                    oldExamIdOfCaMarkCollectionCaMark = em.merge(oldExamIdOfCaMarkCollectionCaMark);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findExam(exam.getExamId()) != null) {
                throw new PreexistingEntityException("Exam " + exam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Exam exam) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Exam persistentExam = em.find(Exam.class, exam.getExamId());
            CompanyBranch branchIdOld = persistentExam.getBranchId();
            CompanyBranch branchIdNew = exam.getBranchId();
            AcademicYears academicYearOld = persistentExam.getAcademicYear();
            AcademicYears academicYearNew = exam.getAcademicYear();
            ClassCategory classCategoryIdOld = persistentExam.getClassCategoryId();
            ClassCategory classCategoryIdNew = exam.getClassCategoryId();
            Term termIdOld = persistentExam.getTermId();
            Term termIdNew = exam.getTermId();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOld = persistentExam.getFormMasterTerminalExamCommentCollection();
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionNew = exam.getFormMasterTerminalExamCommentCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOld = persistentExam.getStudentBehavouralTraitCollection();
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionNew = exam.getStudentBehavouralTraitCollection();
            Collection<ExamClassPosition> examClassPositionCollectionOld = persistentExam.getExamClassPositionCollection();
            Collection<ExamClassPosition> examClassPositionCollectionNew = exam.getExamClassPositionCollection();
            Collection<ExamMark> examMarkCollectionOld = persistentExam.getExamMarkCollection();
            Collection<ExamMark> examMarkCollectionNew = exam.getExamMarkCollection();
            Collection<CaMark> caMarkCollectionOld = persistentExam.getCaMarkCollection();
            Collection<CaMark> caMarkCollectionNew = exam.getCaMarkCollection();
            List<String> illegalOrphanMessages = null;
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOld) {
                if (!formMasterTerminalExamCommentCollectionNew.contains(formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOldFormMasterTerminalExamComment + " since its examId field is not nullable.");
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOldStudentBehavouralTrait : studentBehavouralTraitCollectionOld) {
                if (!studentBehavouralTraitCollectionNew.contains(studentBehavouralTraitCollectionOldStudentBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain StudentBehavouralTrait " + studentBehavouralTraitCollectionOldStudentBehavouralTrait + " since its examId field is not nullable.");
                }
            }
            for (ExamClassPosition examClassPositionCollectionOldExamClassPosition : examClassPositionCollectionOld) {
                if (!examClassPositionCollectionNew.contains(examClassPositionCollectionOldExamClassPosition)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamClassPosition " + examClassPositionCollectionOldExamClassPosition + " since its examId field is not nullable.");
                }
            }
            for (ExamMark examMarkCollectionOldExamMark : examMarkCollectionOld) {
                if (!examMarkCollectionNew.contains(examMarkCollectionOldExamMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ExamMark " + examMarkCollectionOldExamMark + " since its examId field is not nullable.");
                }
            }
            for (CaMark caMarkCollectionOldCaMark : caMarkCollectionOld) {
                if (!caMarkCollectionNew.contains(caMarkCollectionOldCaMark)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CaMark " + caMarkCollectionOldCaMark + " since its examId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                exam.setBranchId(branchIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                exam.setAcademicYear(academicYearNew);
            }
            if (classCategoryIdNew != null) {
                classCategoryIdNew = em.getReference(classCategoryIdNew.getClass(), classCategoryIdNew.getCategoryId());
                exam.setClassCategoryId(classCategoryIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                exam.setTermId(termIdNew);
            }
            Collection<FormMasterTerminalExamComment> attachedFormMasterTerminalExamCommentCollectionNew = new ArrayList<FormMasterTerminalExamComment>();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach : formMasterTerminalExamCommentCollectionNew) {
                formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach = em.getReference(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getClass(), formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach.getId());
                attachedFormMasterTerminalExamCommentCollectionNew.add(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamCommentToAttach);
            }
            formMasterTerminalExamCommentCollectionNew = attachedFormMasterTerminalExamCommentCollectionNew;
            exam.setFormMasterTerminalExamCommentCollection(formMasterTerminalExamCommentCollectionNew);
            Collection<StudentBehavouralTrait> attachedStudentBehavouralTraitCollectionNew = new ArrayList<StudentBehavouralTrait>();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach : studentBehavouralTraitCollectionNew) {
                studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach = em.getReference(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getClass(), studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach.getId());
                attachedStudentBehavouralTraitCollectionNew.add(studentBehavouralTraitCollectionNewStudentBehavouralTraitToAttach);
            }
            studentBehavouralTraitCollectionNew = attachedStudentBehavouralTraitCollectionNew;
            exam.setStudentBehavouralTraitCollection(studentBehavouralTraitCollectionNew);
            Collection<ExamClassPosition> attachedExamClassPositionCollectionNew = new ArrayList<ExamClassPosition>();
            for (ExamClassPosition examClassPositionCollectionNewExamClassPositionToAttach : examClassPositionCollectionNew) {
                examClassPositionCollectionNewExamClassPositionToAttach = em.getReference(examClassPositionCollectionNewExamClassPositionToAttach.getClass(), examClassPositionCollectionNewExamClassPositionToAttach.getId());
                attachedExamClassPositionCollectionNew.add(examClassPositionCollectionNewExamClassPositionToAttach);
            }
            examClassPositionCollectionNew = attachedExamClassPositionCollectionNew;
            exam.setExamClassPositionCollection(examClassPositionCollectionNew);
            Collection<ExamMark> attachedExamMarkCollectionNew = new ArrayList<ExamMark>();
            for (ExamMark examMarkCollectionNewExamMarkToAttach : examMarkCollectionNew) {
                examMarkCollectionNewExamMarkToAttach = em.getReference(examMarkCollectionNewExamMarkToAttach.getClass(), examMarkCollectionNewExamMarkToAttach.getMarkId());
                attachedExamMarkCollectionNew.add(examMarkCollectionNewExamMarkToAttach);
            }
            examMarkCollectionNew = attachedExamMarkCollectionNew;
            exam.setExamMarkCollection(examMarkCollectionNew);
            Collection<CaMark> attachedCaMarkCollectionNew = new ArrayList<CaMark>();
            for (CaMark caMarkCollectionNewCaMarkToAttach : caMarkCollectionNew) {
                caMarkCollectionNewCaMarkToAttach = em.getReference(caMarkCollectionNewCaMarkToAttach.getClass(), caMarkCollectionNewCaMarkToAttach.getCaId());
                attachedCaMarkCollectionNew.add(caMarkCollectionNewCaMarkToAttach);
            }
            caMarkCollectionNew = attachedCaMarkCollectionNew;
            exam.setCaMarkCollection(caMarkCollectionNew);
            exam = em.merge(exam);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getExamCollection().remove(exam);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getExamCollection().add(exam);
                branchIdNew = em.merge(branchIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getExamCollection().remove(exam);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getExamCollection().add(exam);
                academicYearNew = em.merge(academicYearNew);
            }
            if (classCategoryIdOld != null && !classCategoryIdOld.equals(classCategoryIdNew)) {
                classCategoryIdOld.getExamCollection().remove(exam);
                classCategoryIdOld = em.merge(classCategoryIdOld);
            }
            if (classCategoryIdNew != null && !classCategoryIdNew.equals(classCategoryIdOld)) {
                classCategoryIdNew.getExamCollection().add(exam);
                classCategoryIdNew = em.merge(classCategoryIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getExamCollection().remove(exam);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getExamCollection().add(exam);
                termIdNew = em.merge(termIdNew);
            }
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionNew) {
                if (!formMasterTerminalExamCommentCollectionOld.contains(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment)) {
                    Exam oldExamIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getExamId();
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.setExamId(exam);
                    formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    if (oldExamIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment != null && !oldExamIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.equals(exam)) {
                        oldExamIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment.getFormMasterTerminalExamCommentCollection().remove(formMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                        oldExamIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment = em.merge(oldExamIdOfFormMasterTerminalExamCommentCollectionNewFormMasterTerminalExamComment);
                    }
                }
            }
            for (StudentBehavouralTrait studentBehavouralTraitCollectionNewStudentBehavouralTrait : studentBehavouralTraitCollectionNew) {
                if (!studentBehavouralTraitCollectionOld.contains(studentBehavouralTraitCollectionNewStudentBehavouralTrait)) {
                    Exam oldExamIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = studentBehavouralTraitCollectionNewStudentBehavouralTrait.getExamId();
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait.setExamId(exam);
                    studentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    if (oldExamIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait != null && !oldExamIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.equals(exam)) {
                        oldExamIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait.getStudentBehavouralTraitCollection().remove(studentBehavouralTraitCollectionNewStudentBehavouralTrait);
                        oldExamIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait = em.merge(oldExamIdOfStudentBehavouralTraitCollectionNewStudentBehavouralTrait);
                    }
                }
            }
            for (ExamClassPosition examClassPositionCollectionNewExamClassPosition : examClassPositionCollectionNew) {
                if (!examClassPositionCollectionOld.contains(examClassPositionCollectionNewExamClassPosition)) {
                    Exam oldExamIdOfExamClassPositionCollectionNewExamClassPosition = examClassPositionCollectionNewExamClassPosition.getExamId();
                    examClassPositionCollectionNewExamClassPosition.setExamId(exam);
                    examClassPositionCollectionNewExamClassPosition = em.merge(examClassPositionCollectionNewExamClassPosition);
                    if (oldExamIdOfExamClassPositionCollectionNewExamClassPosition != null && !oldExamIdOfExamClassPositionCollectionNewExamClassPosition.equals(exam)) {
                        oldExamIdOfExamClassPositionCollectionNewExamClassPosition.getExamClassPositionCollection().remove(examClassPositionCollectionNewExamClassPosition);
                        oldExamIdOfExamClassPositionCollectionNewExamClassPosition = em.merge(oldExamIdOfExamClassPositionCollectionNewExamClassPosition);
                    }
                }
            }
            for (ExamMark examMarkCollectionNewExamMark : examMarkCollectionNew) {
                if (!examMarkCollectionOld.contains(examMarkCollectionNewExamMark)) {
                    Exam oldExamIdOfExamMarkCollectionNewExamMark = examMarkCollectionNewExamMark.getExamId();
                    examMarkCollectionNewExamMark.setExamId(exam);
                    examMarkCollectionNewExamMark = em.merge(examMarkCollectionNewExamMark);
                    if (oldExamIdOfExamMarkCollectionNewExamMark != null && !oldExamIdOfExamMarkCollectionNewExamMark.equals(exam)) {
                        oldExamIdOfExamMarkCollectionNewExamMark.getExamMarkCollection().remove(examMarkCollectionNewExamMark);
                        oldExamIdOfExamMarkCollectionNewExamMark = em.merge(oldExamIdOfExamMarkCollectionNewExamMark);
                    }
                }
            }
            for (CaMark caMarkCollectionNewCaMark : caMarkCollectionNew) {
                if (!caMarkCollectionOld.contains(caMarkCollectionNewCaMark)) {
                    Exam oldExamIdOfCaMarkCollectionNewCaMark = caMarkCollectionNewCaMark.getExamId();
                    caMarkCollectionNewCaMark.setExamId(exam);
                    caMarkCollectionNewCaMark = em.merge(caMarkCollectionNewCaMark);
                    if (oldExamIdOfCaMarkCollectionNewCaMark != null && !oldExamIdOfCaMarkCollectionNewCaMark.equals(exam)) {
                        oldExamIdOfCaMarkCollectionNewCaMark.getCaMarkCollection().remove(caMarkCollectionNewCaMark);
                        oldExamIdOfCaMarkCollectionNewCaMark = em.merge(oldExamIdOfCaMarkCollectionNewCaMark);
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
                Long id = exam.getExamId();
                if (findExam(id) == null) {
                    throw new NonexistentEntityException("The exam with id " + id + " no longer exists.");
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
            Exam exam;
            try {
                exam = em.getReference(Exam.class, id);
                exam.getExamId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The exam with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<FormMasterTerminalExamComment> formMasterTerminalExamCommentCollectionOrphanCheck = exam.getFormMasterTerminalExamCommentCollection();
            for (FormMasterTerminalExamComment formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment : formMasterTerminalExamCommentCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exam (" + exam + ") cannot be destroyed since the FormMasterTerminalExamComment " + formMasterTerminalExamCommentCollectionOrphanCheckFormMasterTerminalExamComment + " in its formMasterTerminalExamCommentCollection field has a non-nullable examId field.");
            }
            Collection<StudentBehavouralTrait> studentBehavouralTraitCollectionOrphanCheck = exam.getStudentBehavouralTraitCollection();
            for (StudentBehavouralTrait studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait : studentBehavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exam (" + exam + ") cannot be destroyed since the StudentBehavouralTrait " + studentBehavouralTraitCollectionOrphanCheckStudentBehavouralTrait + " in its studentBehavouralTraitCollection field has a non-nullable examId field.");
            }
            Collection<ExamClassPosition> examClassPositionCollectionOrphanCheck = exam.getExamClassPositionCollection();
            for (ExamClassPosition examClassPositionCollectionOrphanCheckExamClassPosition : examClassPositionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exam (" + exam + ") cannot be destroyed since the ExamClassPosition " + examClassPositionCollectionOrphanCheckExamClassPosition + " in its examClassPositionCollection field has a non-nullable examId field.");
            }
            Collection<ExamMark> examMarkCollectionOrphanCheck = exam.getExamMarkCollection();
            for (ExamMark examMarkCollectionOrphanCheckExamMark : examMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exam (" + exam + ") cannot be destroyed since the ExamMark " + examMarkCollectionOrphanCheckExamMark + " in its examMarkCollection field has a non-nullable examId field.");
            }
            Collection<CaMark> caMarkCollectionOrphanCheck = exam.getCaMarkCollection();
            for (CaMark caMarkCollectionOrphanCheckCaMark : caMarkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Exam (" + exam + ") cannot be destroyed since the CaMark " + caMarkCollectionOrphanCheckCaMark + " in its caMarkCollection field has a non-nullable examId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = exam.getBranchId();
            if (branchId != null) {
                branchId.getExamCollection().remove(exam);
                branchId = em.merge(branchId);
            }
            AcademicYears academicYear = exam.getAcademicYear();
            if (academicYear != null) {
                academicYear.getExamCollection().remove(exam);
                academicYear = em.merge(academicYear);
            }
            ClassCategory classCategoryId = exam.getClassCategoryId();
            if (classCategoryId != null) {
                classCategoryId.getExamCollection().remove(exam);
                classCategoryId = em.merge(classCategoryId);
            }
            Term termId = exam.getTermId();
            if (termId != null) {
                termId.getExamCollection().remove(exam);
                termId = em.merge(termId);
            }
            em.remove(exam);
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

    public List<Exam> findExamEntities() {
        return findExamEntities(true, -1, -1);
    }

    public List<Exam> findExamEntities(int maxResults, int firstResult) {
        return findExamEntities(false, maxResults, firstResult);
    }

    private List<Exam> findExamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Exam.class));
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

    public Exam findExam(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Exam.class, id);
        } finally {
            em.close();
        }
    }

    public int getExamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Exam> rt = cq.from(Exam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
