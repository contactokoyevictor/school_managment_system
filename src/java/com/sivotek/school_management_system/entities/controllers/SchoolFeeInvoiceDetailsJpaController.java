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
import com.sivotek.school_management_system.entities.Invoice;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.Term;
import com.sivotek.school_management_system.entities.Section;
import com.sivotek.school_management_system.entities.AcademicYears;
import com.sivotek.school_management_system.entities.PaymentMethod;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.SchoolFeeInvoiceDetails;
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
public class SchoolFeeInvoiceDetailsJpaController implements Serializable {

    public SchoolFeeInvoiceDetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SchoolFeeInvoiceDetails schoolFeeInvoiceDetails) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = schoolFeeInvoiceDetails.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                schoolFeeInvoiceDetails.setBranchId(branchId);
            }
            Invoice invoiceId = schoolFeeInvoiceDetails.getInvoiceId();
            if (invoiceId != null) {
                invoiceId = em.getReference(invoiceId.getClass(), invoiceId.getId());
                schoolFeeInvoiceDetails.setInvoiceId(invoiceId);
            }
            Student studentId = schoolFeeInvoiceDetails.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                schoolFeeInvoiceDetails.setStudentId(studentId);
            }
            Term termId = schoolFeeInvoiceDetails.getTermId();
            if (termId != null) {
                termId = em.getReference(termId.getClass(), termId.getTermId());
                schoolFeeInvoiceDetails.setTermId(termId);
            }
            Section sectionId = schoolFeeInvoiceDetails.getSectionId();
            if (sectionId != null) {
                sectionId = em.getReference(sectionId.getClass(), sectionId.getSectionId());
                schoolFeeInvoiceDetails.setSectionId(sectionId);
            }
            AcademicYears academicYear = schoolFeeInvoiceDetails.getAcademicYear();
            if (academicYear != null) {
                academicYear = em.getReference(academicYear.getClass(), academicYear.getYearId());
                schoolFeeInvoiceDetails.setAcademicYear(academicYear);
            }
            PaymentMethod paymentMethod = schoolFeeInvoiceDetails.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod = em.getReference(paymentMethod.getClass(), paymentMethod.getId());
                schoolFeeInvoiceDetails.setPaymentMethod(paymentMethod);
            }
            Employee createdByEmployeeId = schoolFeeInvoiceDetails.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId = em.getReference(createdByEmployeeId.getClass(), createdByEmployeeId.getEmployeeId());
                schoolFeeInvoiceDetails.setCreatedByEmployeeId(createdByEmployeeId);
            }
            Employee lastModifiedEmployeeId = schoolFeeInvoiceDetails.getLastModifiedEmployeeId();
            if (lastModifiedEmployeeId != null) {
                lastModifiedEmployeeId = em.getReference(lastModifiedEmployeeId.getClass(), lastModifiedEmployeeId.getEmployeeId());
                schoolFeeInvoiceDetails.setLastModifiedEmployeeId(lastModifiedEmployeeId);
            }
            em.persist(schoolFeeInvoiceDetails);
            if (branchId != null) {
                branchId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                branchId = em.merge(branchId);
            }
            if (invoiceId != null) {
                invoiceId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                invoiceId = em.merge(invoiceId);
            }
            if (studentId != null) {
                studentId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                studentId = em.merge(studentId);
            }
            if (termId != null) {
                termId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                termId = em.merge(termId);
            }
            if (sectionId != null) {
                sectionId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                sectionId = em.merge(sectionId);
            }
            if (academicYear != null) {
                academicYear.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                academicYear = em.merge(academicYear);
            }
            if (paymentMethod != null) {
                paymentMethod.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                paymentMethod = em.merge(paymentMethod);
            }
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            if (lastModifiedEmployeeId != null) {
                lastModifiedEmployeeId.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                lastModifiedEmployeeId = em.merge(lastModifiedEmployeeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findSchoolFeeInvoiceDetails(schoolFeeInvoiceDetails.getId()) != null) {
                throw new PreexistingEntityException("SchoolFeeInvoiceDetails " + schoolFeeInvoiceDetails + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SchoolFeeInvoiceDetails schoolFeeInvoiceDetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SchoolFeeInvoiceDetails persistentSchoolFeeInvoiceDetails = em.find(SchoolFeeInvoiceDetails.class, schoolFeeInvoiceDetails.getId());
            CompanyBranch branchIdOld = persistentSchoolFeeInvoiceDetails.getBranchId();
            CompanyBranch branchIdNew = schoolFeeInvoiceDetails.getBranchId();
            Invoice invoiceIdOld = persistentSchoolFeeInvoiceDetails.getInvoiceId();
            Invoice invoiceIdNew = schoolFeeInvoiceDetails.getInvoiceId();
            Student studentIdOld = persistentSchoolFeeInvoiceDetails.getStudentId();
            Student studentIdNew = schoolFeeInvoiceDetails.getStudentId();
            Term termIdOld = persistentSchoolFeeInvoiceDetails.getTermId();
            Term termIdNew = schoolFeeInvoiceDetails.getTermId();
            Section sectionIdOld = persistentSchoolFeeInvoiceDetails.getSectionId();
            Section sectionIdNew = schoolFeeInvoiceDetails.getSectionId();
            AcademicYears academicYearOld = persistentSchoolFeeInvoiceDetails.getAcademicYear();
            AcademicYears academicYearNew = schoolFeeInvoiceDetails.getAcademicYear();
            PaymentMethod paymentMethodOld = persistentSchoolFeeInvoiceDetails.getPaymentMethod();
            PaymentMethod paymentMethodNew = schoolFeeInvoiceDetails.getPaymentMethod();
            Employee createdByEmployeeIdOld = persistentSchoolFeeInvoiceDetails.getCreatedByEmployeeId();
            Employee createdByEmployeeIdNew = schoolFeeInvoiceDetails.getCreatedByEmployeeId();
            Employee lastModifiedEmployeeIdOld = persistentSchoolFeeInvoiceDetails.getLastModifiedEmployeeId();
            Employee lastModifiedEmployeeIdNew = schoolFeeInvoiceDetails.getLastModifiedEmployeeId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                schoolFeeInvoiceDetails.setBranchId(branchIdNew);
            }
            if (invoiceIdNew != null) {
                invoiceIdNew = em.getReference(invoiceIdNew.getClass(), invoiceIdNew.getId());
                schoolFeeInvoiceDetails.setInvoiceId(invoiceIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                schoolFeeInvoiceDetails.setStudentId(studentIdNew);
            }
            if (termIdNew != null) {
                termIdNew = em.getReference(termIdNew.getClass(), termIdNew.getTermId());
                schoolFeeInvoiceDetails.setTermId(termIdNew);
            }
            if (sectionIdNew != null) {
                sectionIdNew = em.getReference(sectionIdNew.getClass(), sectionIdNew.getSectionId());
                schoolFeeInvoiceDetails.setSectionId(sectionIdNew);
            }
            if (academicYearNew != null) {
                academicYearNew = em.getReference(academicYearNew.getClass(), academicYearNew.getYearId());
                schoolFeeInvoiceDetails.setAcademicYear(academicYearNew);
            }
            if (paymentMethodNew != null) {
                paymentMethodNew = em.getReference(paymentMethodNew.getClass(), paymentMethodNew.getId());
                schoolFeeInvoiceDetails.setPaymentMethod(paymentMethodNew);
            }
            if (createdByEmployeeIdNew != null) {
                createdByEmployeeIdNew = em.getReference(createdByEmployeeIdNew.getClass(), createdByEmployeeIdNew.getEmployeeId());
                schoolFeeInvoiceDetails.setCreatedByEmployeeId(createdByEmployeeIdNew);
            }
            if (lastModifiedEmployeeIdNew != null) {
                lastModifiedEmployeeIdNew = em.getReference(lastModifiedEmployeeIdNew.getClass(), lastModifiedEmployeeIdNew.getEmployeeId());
                schoolFeeInvoiceDetails.setLastModifiedEmployeeId(lastModifiedEmployeeIdNew);
            }
            schoolFeeInvoiceDetails = em.merge(schoolFeeInvoiceDetails);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                branchIdNew = em.merge(branchIdNew);
            }
            if (invoiceIdOld != null && !invoiceIdOld.equals(invoiceIdNew)) {
                invoiceIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                invoiceIdOld = em.merge(invoiceIdOld);
            }
            if (invoiceIdNew != null && !invoiceIdNew.equals(invoiceIdOld)) {
                invoiceIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                invoiceIdNew = em.merge(invoiceIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                studentIdNew = em.merge(studentIdNew);
            }
            if (termIdOld != null && !termIdOld.equals(termIdNew)) {
                termIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                termIdOld = em.merge(termIdOld);
            }
            if (termIdNew != null && !termIdNew.equals(termIdOld)) {
                termIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                termIdNew = em.merge(termIdNew);
            }
            if (sectionIdOld != null && !sectionIdOld.equals(sectionIdNew)) {
                sectionIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                sectionIdOld = em.merge(sectionIdOld);
            }
            if (sectionIdNew != null && !sectionIdNew.equals(sectionIdOld)) {
                sectionIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                sectionIdNew = em.merge(sectionIdNew);
            }
            if (academicYearOld != null && !academicYearOld.equals(academicYearNew)) {
                academicYearOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                academicYearOld = em.merge(academicYearOld);
            }
            if (academicYearNew != null && !academicYearNew.equals(academicYearOld)) {
                academicYearNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                academicYearNew = em.merge(academicYearNew);
            }
            if (paymentMethodOld != null && !paymentMethodOld.equals(paymentMethodNew)) {
                paymentMethodOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                paymentMethodOld = em.merge(paymentMethodOld);
            }
            if (paymentMethodNew != null && !paymentMethodNew.equals(paymentMethodOld)) {
                paymentMethodNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                paymentMethodNew = em.merge(paymentMethodNew);
            }
            if (createdByEmployeeIdOld != null && !createdByEmployeeIdOld.equals(createdByEmployeeIdNew)) {
                createdByEmployeeIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                createdByEmployeeIdOld = em.merge(createdByEmployeeIdOld);
            }
            if (createdByEmployeeIdNew != null && !createdByEmployeeIdNew.equals(createdByEmployeeIdOld)) {
                createdByEmployeeIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                createdByEmployeeIdNew = em.merge(createdByEmployeeIdNew);
            }
            if (lastModifiedEmployeeIdOld != null && !lastModifiedEmployeeIdOld.equals(lastModifiedEmployeeIdNew)) {
                lastModifiedEmployeeIdOld.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                lastModifiedEmployeeIdOld = em.merge(lastModifiedEmployeeIdOld);
            }
            if (lastModifiedEmployeeIdNew != null && !lastModifiedEmployeeIdNew.equals(lastModifiedEmployeeIdOld)) {
                lastModifiedEmployeeIdNew.getSchoolFeeInvoiceDetailsCollection().add(schoolFeeInvoiceDetails);
                lastModifiedEmployeeIdNew = em.merge(lastModifiedEmployeeIdNew);
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
                Long id = schoolFeeInvoiceDetails.getId();
                if (findSchoolFeeInvoiceDetails(id) == null) {
                    throw new NonexistentEntityException("The schoolFeeInvoiceDetails with id " + id + " no longer exists.");
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
            SchoolFeeInvoiceDetails schoolFeeInvoiceDetails;
            try {
                schoolFeeInvoiceDetails = em.getReference(SchoolFeeInvoiceDetails.class, id);
                schoolFeeInvoiceDetails.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The schoolFeeInvoiceDetails with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = schoolFeeInvoiceDetails.getBranchId();
            if (branchId != null) {
                branchId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                branchId = em.merge(branchId);
            }
            Invoice invoiceId = schoolFeeInvoiceDetails.getInvoiceId();
            if (invoiceId != null) {
                invoiceId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                invoiceId = em.merge(invoiceId);
            }
            Student studentId = schoolFeeInvoiceDetails.getStudentId();
            if (studentId != null) {
                studentId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                studentId = em.merge(studentId);
            }
            Term termId = schoolFeeInvoiceDetails.getTermId();
            if (termId != null) {
                termId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                termId = em.merge(termId);
            }
            Section sectionId = schoolFeeInvoiceDetails.getSectionId();
            if (sectionId != null) {
                sectionId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                sectionId = em.merge(sectionId);
            }
            AcademicYears academicYear = schoolFeeInvoiceDetails.getAcademicYear();
            if (academicYear != null) {
                academicYear.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                academicYear = em.merge(academicYear);
            }
            PaymentMethod paymentMethod = schoolFeeInvoiceDetails.getPaymentMethod();
            if (paymentMethod != null) {
                paymentMethod.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                paymentMethod = em.merge(paymentMethod);
            }
            Employee createdByEmployeeId = schoolFeeInvoiceDetails.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            Employee lastModifiedEmployeeId = schoolFeeInvoiceDetails.getLastModifiedEmployeeId();
            if (lastModifiedEmployeeId != null) {
                lastModifiedEmployeeId.getSchoolFeeInvoiceDetailsCollection().remove(schoolFeeInvoiceDetails);
                lastModifiedEmployeeId = em.merge(lastModifiedEmployeeId);
            }
            em.remove(schoolFeeInvoiceDetails);
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

    public List<SchoolFeeInvoiceDetails> findSchoolFeeInvoiceDetailsEntities() {
        return findSchoolFeeInvoiceDetailsEntities(true, -1, -1);
    }

    public List<SchoolFeeInvoiceDetails> findSchoolFeeInvoiceDetailsEntities(int maxResults, int firstResult) {
        return findSchoolFeeInvoiceDetailsEntities(false, maxResults, firstResult);
    }

    private List<SchoolFeeInvoiceDetails> findSchoolFeeInvoiceDetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SchoolFeeInvoiceDetails.class));
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

    public SchoolFeeInvoiceDetails findSchoolFeeInvoiceDetails(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SchoolFeeInvoiceDetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getSchoolFeeInvoiceDetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SchoolFeeInvoiceDetails> rt = cq.from(SchoolFeeInvoiceDetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
