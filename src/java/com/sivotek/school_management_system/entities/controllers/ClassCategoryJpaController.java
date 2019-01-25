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
import com.sivotek.school_management_system.entities.Subject;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.Exam;
import com.sivotek.school_management_system.entities.Grade;
import com.sivotek.school_management_system.entities.Class;
import com.sivotek.school_management_system.entities.BehavouralTrait;
import com.sivotek.school_management_system.entities.ClassCategory;
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
 * @author MY USER
 */
public class ClassCategoryJpaController implements Serializable {

    private static final Logger log = Logger.getLogger(ClassCategoryJpaController.class.getName());
      
    public ClassCategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public ClassCategoryJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(ClassCategory classCategory) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (classCategory.getSubjectCollection() == null) {
            classCategory.setSubjectCollection(new ArrayList<Subject>());
        }
        if (classCategory.getSectionCollection() == null) {
            classCategory.setSectionCollection(new ArrayList<Section>());
        }
        if (classCategory.getExamCollection() == null) {
            classCategory.setExamCollection(new ArrayList<Exam>());
        }
        if (classCategory.getGradeCollection() == null) {
            classCategory.setGradeCollection(new ArrayList<Grade>());
        }
        if (classCategory.getClassCollection() == null) {
            classCategory.setClassCollection(new ArrayList<Class>());
        }
        if (classCategory.getBehavouralTraitCollection() == null) {
            classCategory.setBehavouralTraitCollection(new ArrayList<BehavouralTrait>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = classCategory.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                classCategory.setBranchId(branchId);
            }
            Collection<Subject> attachedSubjectCollection = new ArrayList<Subject>();
            for (Subject subjectCollectionSubjectToAttach : classCategory.getSubjectCollection()) {
                subjectCollectionSubjectToAttach = em.getReference(subjectCollectionSubjectToAttach.getClass(), subjectCollectionSubjectToAttach.getSubjectId());
                attachedSubjectCollection.add(subjectCollectionSubjectToAttach);
            }
            classCategory.setSubjectCollection(attachedSubjectCollection);
            Collection<Section> attachedSectionCollection = new ArrayList<Section>();
            for (Section sectionCollectionSectionToAttach : classCategory.getSectionCollection()) {
                sectionCollectionSectionToAttach = em.getReference(sectionCollectionSectionToAttach.getClass(), sectionCollectionSectionToAttach.getSectionId());
                attachedSectionCollection.add(sectionCollectionSectionToAttach);
            }
            classCategory.setSectionCollection(attachedSectionCollection);
            Collection<Exam> attachedExamCollection = new ArrayList<Exam>();
            for (Exam examCollectionExamToAttach : classCategory.getExamCollection()) {
                examCollectionExamToAttach = em.getReference(examCollectionExamToAttach.getClass(), examCollectionExamToAttach.getExamId());
                attachedExamCollection.add(examCollectionExamToAttach);
            }
            classCategory.setExamCollection(attachedExamCollection);
            Collection<Grade> attachedGradeCollection = new ArrayList<Grade>();
            for (Grade gradeCollectionGradeToAttach : classCategory.getGradeCollection()) {
                gradeCollectionGradeToAttach = em.getReference(gradeCollectionGradeToAttach.getClass(), gradeCollectionGradeToAttach.getGradeId());
                attachedGradeCollection.add(gradeCollectionGradeToAttach);
            }
            classCategory.setGradeCollection(attachedGradeCollection);
            Collection<Class> attachedClassCollection = new ArrayList<Class>();
            for (Class classCollectionClassToAttach : classCategory.getClassCollection()) {
                classCollectionClassToAttach = em.getReference(classCollectionClassToAttach.getClass(), classCollectionClassToAttach.getClassId());
                attachedClassCollection.add(classCollectionClassToAttach);
            }
            classCategory.setClassCollection(attachedClassCollection);
            Collection<BehavouralTrait> attachedBehavouralTraitCollection = new ArrayList<BehavouralTrait>();
            for (BehavouralTrait behavouralTraitCollectionBehavouralTraitToAttach : classCategory.getBehavouralTraitCollection()) {
                behavouralTraitCollectionBehavouralTraitToAttach = em.getReference(behavouralTraitCollectionBehavouralTraitToAttach.getClass(), behavouralTraitCollectionBehavouralTraitToAttach.getId());
                attachedBehavouralTraitCollection.add(behavouralTraitCollectionBehavouralTraitToAttach);
            }
            classCategory.setBehavouralTraitCollection(attachedBehavouralTraitCollection);
            em.persist(classCategory);
            if (branchId != null) {
                branchId.getClassCategoryCollection().add(classCategory);
                branchId = em.merge(branchId);
            }
            for (Subject subjectCollectionSubject : classCategory.getSubjectCollection()) {
                ClassCategory oldClassCategoryIdOfSubjectCollectionSubject = subjectCollectionSubject.getClassCategoryId();
                subjectCollectionSubject.setClassCategoryId(classCategory);
                subjectCollectionSubject = em.merge(subjectCollectionSubject);
                if (oldClassCategoryIdOfSubjectCollectionSubject != null) {
                    oldClassCategoryIdOfSubjectCollectionSubject.getSubjectCollection().remove(subjectCollectionSubject);
                    oldClassCategoryIdOfSubjectCollectionSubject = em.merge(oldClassCategoryIdOfSubjectCollectionSubject);
                }
            }
            for (Section sectionCollectionSection : classCategory.getSectionCollection()) {
                ClassCategory oldClassCategoryIdOfSectionCollectionSection = sectionCollectionSection.getClassCategoryId();
                sectionCollectionSection.setClassCategoryId(classCategory);
                sectionCollectionSection = em.merge(sectionCollectionSection);
                if (oldClassCategoryIdOfSectionCollectionSection != null) {
                    oldClassCategoryIdOfSectionCollectionSection.getSectionCollection().remove(sectionCollectionSection);
                    oldClassCategoryIdOfSectionCollectionSection = em.merge(oldClassCategoryIdOfSectionCollectionSection);
                }
            }
            for (Exam examCollectionExam : classCategory.getExamCollection()) {
                ClassCategory oldClassCategoryIdOfExamCollectionExam = examCollectionExam.getClassCategoryId();
                examCollectionExam.setClassCategoryId(classCategory);
                examCollectionExam = em.merge(examCollectionExam);
                if (oldClassCategoryIdOfExamCollectionExam != null) {
                    oldClassCategoryIdOfExamCollectionExam.getExamCollection().remove(examCollectionExam);
                    oldClassCategoryIdOfExamCollectionExam = em.merge(oldClassCategoryIdOfExamCollectionExam);
                }
            }
            for (Grade gradeCollectionGrade : classCategory.getGradeCollection()) {
                ClassCategory oldClassCategoryIdOfGradeCollectionGrade = gradeCollectionGrade.getClassCategoryId();
                gradeCollectionGrade.setClassCategoryId(classCategory);
                gradeCollectionGrade = em.merge(gradeCollectionGrade);
                if (oldClassCategoryIdOfGradeCollectionGrade != null) {
                    oldClassCategoryIdOfGradeCollectionGrade.getGradeCollection().remove(gradeCollectionGrade);
                    oldClassCategoryIdOfGradeCollectionGrade = em.merge(oldClassCategoryIdOfGradeCollectionGrade);
                }
            }
            for (Class classCollectionClass : classCategory.getClassCollection()) {
                ClassCategory oldClassCategoryIdOfClassCollectionClass = classCollectionClass.getClassCategoryId();
                classCollectionClass.setClassCategoryId(classCategory);
                classCollectionClass = em.merge(classCollectionClass);
                if (oldClassCategoryIdOfClassCollectionClass != null) {
                    oldClassCategoryIdOfClassCollectionClass.getClassCollection().remove(classCollectionClass);
                    oldClassCategoryIdOfClassCollectionClass = em.merge(oldClassCategoryIdOfClassCollectionClass);
                }
            }
            for (BehavouralTrait behavouralTraitCollectionBehavouralTrait : classCategory.getBehavouralTraitCollection()) {
                ClassCategory oldClassCategoryIdOfBehavouralTraitCollectionBehavouralTrait = behavouralTraitCollectionBehavouralTrait.getClassCategoryId();
                behavouralTraitCollectionBehavouralTrait.setClassCategoryId(classCategory);
                behavouralTraitCollectionBehavouralTrait = em.merge(behavouralTraitCollectionBehavouralTrait);
                if (oldClassCategoryIdOfBehavouralTraitCollectionBehavouralTrait != null) {
                    oldClassCategoryIdOfBehavouralTraitCollectionBehavouralTrait.getBehavouralTraitCollection().remove(behavouralTraitCollectionBehavouralTrait);
                    oldClassCategoryIdOfBehavouralTraitCollectionBehavouralTrait = em.merge(oldClassCategoryIdOfBehavouralTraitCollectionBehavouralTrait);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findClassCategory(classCategory.getCategoryId()) != null) {
                throw new PreexistingEntityException("ClassCategory " + classCategory + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClassCategory classCategory) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClassCategory persistentClassCategory = em.find(ClassCategory.class, classCategory.getCategoryId());
            CompanyBranch branchIdOld = persistentClassCategory.getBranchId();
            CompanyBranch branchIdNew = classCategory.getBranchId();
            Collection<Subject> subjectCollectionOld = persistentClassCategory.getSubjectCollection();
            Collection<Subject> subjectCollectionNew = classCategory.getSubjectCollection();
            Collection<Section> sectionCollectionOld = persistentClassCategory.getSectionCollection();
            Collection<Section> sectionCollectionNew = classCategory.getSectionCollection();
            Collection<Exam> examCollectionOld = persistentClassCategory.getExamCollection();
            Collection<Exam> examCollectionNew = classCategory.getExamCollection();
            Collection<Grade> gradeCollectionOld = persistentClassCategory.getGradeCollection();
            Collection<Grade> gradeCollectionNew = classCategory.getGradeCollection();
            Collection<Class> classCollectionOld = persistentClassCategory.getClassCollection();
            Collection<Class> classCollectionNew = classCategory.getClassCollection();
            Collection<BehavouralTrait> behavouralTraitCollectionOld = persistentClassCategory.getBehavouralTraitCollection();
            Collection<BehavouralTrait> behavouralTraitCollectionNew = classCategory.getBehavouralTraitCollection();
            List<String> illegalOrphanMessages = null;
            for (Subject subjectCollectionOldSubject : subjectCollectionOld) {
                if (!subjectCollectionNew.contains(subjectCollectionOldSubject)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Subject " + subjectCollectionOldSubject + " since its classCategoryId field is not nullable.");
                }
            }
            for (Exam examCollectionOldExam : examCollectionOld) {
                if (!examCollectionNew.contains(examCollectionOldExam)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Exam " + examCollectionOldExam + " since its classCategoryId field is not nullable.");
                }
            }
            for (Grade gradeCollectionOldGrade : gradeCollectionOld) {
                if (!gradeCollectionNew.contains(gradeCollectionOldGrade)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grade " + gradeCollectionOldGrade + " since its classCategoryId field is not nullable.");
                }
            }
            for (BehavouralTrait behavouralTraitCollectionOldBehavouralTrait : behavouralTraitCollectionOld) {
                if (!behavouralTraitCollectionNew.contains(behavouralTraitCollectionOldBehavouralTrait)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BehavouralTrait " + behavouralTraitCollectionOldBehavouralTrait + " since its classCategoryId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                classCategory.setBranchId(branchIdNew);
            }
            Collection<Subject> attachedSubjectCollectionNew = new ArrayList<Subject>();
            for (Subject subjectCollectionNewSubjectToAttach : subjectCollectionNew) {
                subjectCollectionNewSubjectToAttach = em.getReference(subjectCollectionNewSubjectToAttach.getClass(), subjectCollectionNewSubjectToAttach.getSubjectId());
                attachedSubjectCollectionNew.add(subjectCollectionNewSubjectToAttach);
            }
            subjectCollectionNew = attachedSubjectCollectionNew;
            classCategory.setSubjectCollection(subjectCollectionNew);
            Collection<Section> attachedSectionCollectionNew = new ArrayList<Section>();
            for (Section sectionCollectionNewSectionToAttach : sectionCollectionNew) {
                sectionCollectionNewSectionToAttach = em.getReference(sectionCollectionNewSectionToAttach.getClass(), sectionCollectionNewSectionToAttach.getSectionId());
                attachedSectionCollectionNew.add(sectionCollectionNewSectionToAttach);
            }
            sectionCollectionNew = attachedSectionCollectionNew;
            classCategory.setSectionCollection(sectionCollectionNew);
            Collection<Exam> attachedExamCollectionNew = new ArrayList<Exam>();
            for (Exam examCollectionNewExamToAttach : examCollectionNew) {
                examCollectionNewExamToAttach = em.getReference(examCollectionNewExamToAttach.getClass(), examCollectionNewExamToAttach.getExamId());
                attachedExamCollectionNew.add(examCollectionNewExamToAttach);
            }
            examCollectionNew = attachedExamCollectionNew;
            classCategory.setExamCollection(examCollectionNew);
            Collection<Grade> attachedGradeCollectionNew = new ArrayList<Grade>();
            for (Grade gradeCollectionNewGradeToAttach : gradeCollectionNew) {
                gradeCollectionNewGradeToAttach = em.getReference(gradeCollectionNewGradeToAttach.getClass(), gradeCollectionNewGradeToAttach.getGradeId());
                attachedGradeCollectionNew.add(gradeCollectionNewGradeToAttach);
            }
            gradeCollectionNew = attachedGradeCollectionNew;
            classCategory.setGradeCollection(gradeCollectionNew);
            Collection<Class> attachedClassCollectionNew = new ArrayList<Class>();
            for (Class classCollectionNewClassToAttach : classCollectionNew) {
                classCollectionNewClassToAttach = em.getReference(classCollectionNewClassToAttach.getClass(), classCollectionNewClassToAttach.getClassId());
                attachedClassCollectionNew.add(classCollectionNewClassToAttach);
            }
            classCollectionNew = attachedClassCollectionNew;
            classCategory.setClassCollection(classCollectionNew);
            Collection<BehavouralTrait> attachedBehavouralTraitCollectionNew = new ArrayList<BehavouralTrait>();
            for (BehavouralTrait behavouralTraitCollectionNewBehavouralTraitToAttach : behavouralTraitCollectionNew) {
                behavouralTraitCollectionNewBehavouralTraitToAttach = em.getReference(behavouralTraitCollectionNewBehavouralTraitToAttach.getClass(), behavouralTraitCollectionNewBehavouralTraitToAttach.getId());
                attachedBehavouralTraitCollectionNew.add(behavouralTraitCollectionNewBehavouralTraitToAttach);
            }
            behavouralTraitCollectionNew = attachedBehavouralTraitCollectionNew;
            classCategory.setBehavouralTraitCollection(behavouralTraitCollectionNew);
            classCategory = em.merge(classCategory);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getClassCategoryCollection().remove(classCategory);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getClassCategoryCollection().add(classCategory);
                branchIdNew = em.merge(branchIdNew);
            }
            for (Subject subjectCollectionNewSubject : subjectCollectionNew) {
                if (!subjectCollectionOld.contains(subjectCollectionNewSubject)) {
                    ClassCategory oldClassCategoryIdOfSubjectCollectionNewSubject = subjectCollectionNewSubject.getClassCategoryId();
                    subjectCollectionNewSubject.setClassCategoryId(classCategory);
                    subjectCollectionNewSubject = em.merge(subjectCollectionNewSubject);
                    if (oldClassCategoryIdOfSubjectCollectionNewSubject != null && !oldClassCategoryIdOfSubjectCollectionNewSubject.equals(classCategory)) {
                        oldClassCategoryIdOfSubjectCollectionNewSubject.getSubjectCollection().remove(subjectCollectionNewSubject);
                        oldClassCategoryIdOfSubjectCollectionNewSubject = em.merge(oldClassCategoryIdOfSubjectCollectionNewSubject);
                    }
                }
            }
            for (Section sectionCollectionOldSection : sectionCollectionOld) {
                if (!sectionCollectionNew.contains(sectionCollectionOldSection)) {
                    sectionCollectionOldSection.setClassCategoryId(null);
                    sectionCollectionOldSection = em.merge(sectionCollectionOldSection);
                }
            }
            for (Section sectionCollectionNewSection : sectionCollectionNew) {
                if (!sectionCollectionOld.contains(sectionCollectionNewSection)) {
                    ClassCategory oldClassCategoryIdOfSectionCollectionNewSection = sectionCollectionNewSection.getClassCategoryId();
                    sectionCollectionNewSection.setClassCategoryId(classCategory);
                    sectionCollectionNewSection = em.merge(sectionCollectionNewSection);
                    if (oldClassCategoryIdOfSectionCollectionNewSection != null && !oldClassCategoryIdOfSectionCollectionNewSection.equals(classCategory)) {
                        oldClassCategoryIdOfSectionCollectionNewSection.getSectionCollection().remove(sectionCollectionNewSection);
                        oldClassCategoryIdOfSectionCollectionNewSection = em.merge(oldClassCategoryIdOfSectionCollectionNewSection);
                    }
                }
            }
            for (Exam examCollectionNewExam : examCollectionNew) {
                if (!examCollectionOld.contains(examCollectionNewExam)) {
                    ClassCategory oldClassCategoryIdOfExamCollectionNewExam = examCollectionNewExam.getClassCategoryId();
                    examCollectionNewExam.setClassCategoryId(classCategory);
                    examCollectionNewExam = em.merge(examCollectionNewExam);
                    if (oldClassCategoryIdOfExamCollectionNewExam != null && !oldClassCategoryIdOfExamCollectionNewExam.equals(classCategory)) {
                        oldClassCategoryIdOfExamCollectionNewExam.getExamCollection().remove(examCollectionNewExam);
                        oldClassCategoryIdOfExamCollectionNewExam = em.merge(oldClassCategoryIdOfExamCollectionNewExam);
                    }
                }
            }
            for (Grade gradeCollectionNewGrade : gradeCollectionNew) {
                if (!gradeCollectionOld.contains(gradeCollectionNewGrade)) {
                    ClassCategory oldClassCategoryIdOfGradeCollectionNewGrade = gradeCollectionNewGrade.getClassCategoryId();
                    gradeCollectionNewGrade.setClassCategoryId(classCategory);
                    gradeCollectionNewGrade = em.merge(gradeCollectionNewGrade);
                    if (oldClassCategoryIdOfGradeCollectionNewGrade != null && !oldClassCategoryIdOfGradeCollectionNewGrade.equals(classCategory)) {
                        oldClassCategoryIdOfGradeCollectionNewGrade.getGradeCollection().remove(gradeCollectionNewGrade);
                        oldClassCategoryIdOfGradeCollectionNewGrade = em.merge(oldClassCategoryIdOfGradeCollectionNewGrade);
                    }
                }
            }
            for (Class classCollectionOldClass : classCollectionOld) {
                if (!classCollectionNew.contains(classCollectionOldClass)) {
                    classCollectionOldClass.setClassCategoryId(null);
                    classCollectionOldClass = em.merge(classCollectionOldClass);
                }
            }
            for (Class classCollectionNewClass : classCollectionNew) {
                if (!classCollectionOld.contains(classCollectionNewClass)) {
                    ClassCategory oldClassCategoryIdOfClassCollectionNewClass = classCollectionNewClass.getClassCategoryId();
                    classCollectionNewClass.setClassCategoryId(classCategory);
                    classCollectionNewClass = em.merge(classCollectionNewClass);
                    if (oldClassCategoryIdOfClassCollectionNewClass != null && !oldClassCategoryIdOfClassCollectionNewClass.equals(classCategory)) {
                        oldClassCategoryIdOfClassCollectionNewClass.getClassCollection().remove(classCollectionNewClass);
                        oldClassCategoryIdOfClassCollectionNewClass = em.merge(oldClassCategoryIdOfClassCollectionNewClass);
                    }
                }
            }
            for (BehavouralTrait behavouralTraitCollectionNewBehavouralTrait : behavouralTraitCollectionNew) {
                if (!behavouralTraitCollectionOld.contains(behavouralTraitCollectionNewBehavouralTrait)) {
                    ClassCategory oldClassCategoryIdOfBehavouralTraitCollectionNewBehavouralTrait = behavouralTraitCollectionNewBehavouralTrait.getClassCategoryId();
                    behavouralTraitCollectionNewBehavouralTrait.setClassCategoryId(classCategory);
                    behavouralTraitCollectionNewBehavouralTrait = em.merge(behavouralTraitCollectionNewBehavouralTrait);
                    if (oldClassCategoryIdOfBehavouralTraitCollectionNewBehavouralTrait != null && !oldClassCategoryIdOfBehavouralTraitCollectionNewBehavouralTrait.equals(classCategory)) {
                        oldClassCategoryIdOfBehavouralTraitCollectionNewBehavouralTrait.getBehavouralTraitCollection().remove(behavouralTraitCollectionNewBehavouralTrait);
                        oldClassCategoryIdOfBehavouralTraitCollectionNewBehavouralTrait = em.merge(oldClassCategoryIdOfBehavouralTraitCollectionNewBehavouralTrait);
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
                Long id = classCategory.getCategoryId();
                if (findClassCategory(id) == null) {
                    throw new NonexistentEntityException("The classCategory with id " + id + " no longer exists.");
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
            ClassCategory classCategory;
            try {
                classCategory = em.getReference(ClassCategory.class, id);
                classCategory.getCategoryId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classCategory with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Subject> subjectCollectionOrphanCheck = classCategory.getSubjectCollection();
            for (Subject subjectCollectionOrphanCheckSubject : subjectCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassCategory (" + classCategory + ") cannot be destroyed since the Subject " + subjectCollectionOrphanCheckSubject + " in its subjectCollection field has a non-nullable classCategoryId field.");
            }
            Collection<Exam> examCollectionOrphanCheck = classCategory.getExamCollection();
            for (Exam examCollectionOrphanCheckExam : examCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassCategory (" + classCategory + ") cannot be destroyed since the Exam " + examCollectionOrphanCheckExam + " in its examCollection field has a non-nullable classCategoryId field.");
            }
            Collection<Grade> gradeCollectionOrphanCheck = classCategory.getGradeCollection();
            for (Grade gradeCollectionOrphanCheckGrade : gradeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassCategory (" + classCategory + ") cannot be destroyed since the Grade " + gradeCollectionOrphanCheckGrade + " in its gradeCollection field has a non-nullable classCategoryId field.");
            }
            Collection<BehavouralTrait> behavouralTraitCollectionOrphanCheck = classCategory.getBehavouralTraitCollection();
            for (BehavouralTrait behavouralTraitCollectionOrphanCheckBehavouralTrait : behavouralTraitCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ClassCategory (" + classCategory + ") cannot be destroyed since the BehavouralTrait " + behavouralTraitCollectionOrphanCheckBehavouralTrait + " in its behavouralTraitCollection field has a non-nullable classCategoryId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = classCategory.getBranchId();
            if (branchId != null) {
                branchId.getClassCategoryCollection().remove(classCategory);
                branchId = em.merge(branchId);
            }
            Collection<Section> sectionCollection = classCategory.getSectionCollection();
            for (Section sectionCollectionSection : sectionCollection) {
                sectionCollectionSection.setClassCategoryId(null);
                sectionCollectionSection = em.merge(sectionCollectionSection);
            }
            Collection<Class> classCollection = classCategory.getClassCollection();
            for (Class classCollectionClass : classCollection) {
                classCollectionClass.setClassCategoryId(null);
                classCollectionClass = em.merge(classCollectionClass);
            }
            em.remove(classCategory);
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

    public List<ClassCategory> findClassCategoryEntities() {
        return findClassCategoryEntities(true, -1, -1);
    }

    public List<ClassCategory> findClassCategoryEntities(int maxResults, int firstResult) {
        return findClassCategoryEntities(false, maxResults, firstResult);
    }

    private List<ClassCategory> findClassCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClassCategory.class));
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

    public ClassCategory findClassCategory(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClassCategory.class, id);
        } finally {
            em.close();
        }
    }

    public int getClassCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClassCategory> rt = cq.from(ClassCategory.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
