/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.Countries;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.sivotek.school_management_system.entities.Employee;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.Guardian;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class CountriesJpaController implements Serializable {

    public CountriesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Countries countries) throws RollbackFailureException, Exception {
        if (countries.getEmployeeCollection() == null) {
            countries.setEmployeeCollection(new ArrayList<Employee>());
        }
        if (countries.getEmployeeCollection1() == null) {
            countries.setEmployeeCollection1(new ArrayList<Employee>());
        }
        if (countries.getStudentCollection() == null) {
            countries.setStudentCollection(new ArrayList<Student>());
        }
        if (countries.getStudentCollection1() == null) {
            countries.setStudentCollection1(new ArrayList<Student>());
        }
        if (countries.getGuardianCollection() == null) {
            countries.setGuardianCollection(new ArrayList<Guardian>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Employee> attachedEmployeeCollection = new ArrayList<Employee>();
            for (Employee employeeCollectionEmployeeToAttach : countries.getEmployeeCollection()) {
                employeeCollectionEmployeeToAttach = em.getReference(employeeCollectionEmployeeToAttach.getClass(), employeeCollectionEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollection.add(employeeCollectionEmployeeToAttach);
            }
            countries.setEmployeeCollection(attachedEmployeeCollection);
            Collection<Employee> attachedEmployeeCollection1 = new ArrayList<Employee>();
            for (Employee employeeCollection1EmployeeToAttach : countries.getEmployeeCollection1()) {
                employeeCollection1EmployeeToAttach = em.getReference(employeeCollection1EmployeeToAttach.getClass(), employeeCollection1EmployeeToAttach.getEmployeeId());
                attachedEmployeeCollection1.add(employeeCollection1EmployeeToAttach);
            }
            countries.setEmployeeCollection1(attachedEmployeeCollection1);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : countries.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            countries.setStudentCollection(attachedStudentCollection);
            Collection<Student> attachedStudentCollection1 = new ArrayList<Student>();
            for (Student studentCollection1StudentToAttach : countries.getStudentCollection1()) {
                studentCollection1StudentToAttach = em.getReference(studentCollection1StudentToAttach.getClass(), studentCollection1StudentToAttach.getStudentId());
                attachedStudentCollection1.add(studentCollection1StudentToAttach);
            }
            countries.setStudentCollection1(attachedStudentCollection1);
            Collection<Guardian> attachedGuardianCollection = new ArrayList<Guardian>();
            for (Guardian guardianCollectionGuardianToAttach : countries.getGuardianCollection()) {
                guardianCollectionGuardianToAttach = em.getReference(guardianCollectionGuardianToAttach.getClass(), guardianCollectionGuardianToAttach.getGuardianId());
                attachedGuardianCollection.add(guardianCollectionGuardianToAttach);
            }
            countries.setGuardianCollection(attachedGuardianCollection);
            em.persist(countries);
            for (Employee employeeCollectionEmployee : countries.getEmployeeCollection()) {
                Countries oldNationalityOfEmployeeCollectionEmployee = employeeCollectionEmployee.getNationality();
                employeeCollectionEmployee.setNationality(countries);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
                if (oldNationalityOfEmployeeCollectionEmployee != null) {
                    oldNationalityOfEmployeeCollectionEmployee.getEmployeeCollection().remove(employeeCollectionEmployee);
                    oldNationalityOfEmployeeCollectionEmployee = em.merge(oldNationalityOfEmployeeCollectionEmployee);
                }
            }
            for (Employee employeeCollection1Employee : countries.getEmployeeCollection1()) {
                Countries oldCountryIdOfEmployeeCollection1Employee = employeeCollection1Employee.getCountryId();
                employeeCollection1Employee.setCountryId(countries);
                employeeCollection1Employee = em.merge(employeeCollection1Employee);
                if (oldCountryIdOfEmployeeCollection1Employee != null) {
                    oldCountryIdOfEmployeeCollection1Employee.getEmployeeCollection1().remove(employeeCollection1Employee);
                    oldCountryIdOfEmployeeCollection1Employee = em.merge(oldCountryIdOfEmployeeCollection1Employee);
                }
            }
            for (Student studentCollectionStudent : countries.getStudentCollection()) {
                Countries oldNationalityIdOfStudentCollectionStudent = studentCollectionStudent.getNationalityId();
                studentCollectionStudent.setNationalityId(countries);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldNationalityIdOfStudentCollectionStudent != null) {
                    oldNationalityIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldNationalityIdOfStudentCollectionStudent = em.merge(oldNationalityIdOfStudentCollectionStudent);
                }
            }
            for (Student studentCollection1Student : countries.getStudentCollection1()) {
                Countries oldAddressCountryIdOfStudentCollection1Student = studentCollection1Student.getAddressCountryId();
                studentCollection1Student.setAddressCountryId(countries);
                studentCollection1Student = em.merge(studentCollection1Student);
                if (oldAddressCountryIdOfStudentCollection1Student != null) {
                    oldAddressCountryIdOfStudentCollection1Student.getStudentCollection1().remove(studentCollection1Student);
                    oldAddressCountryIdOfStudentCollection1Student = em.merge(oldAddressCountryIdOfStudentCollection1Student);
                }
            }
            for (Guardian guardianCollectionGuardian : countries.getGuardianCollection()) {
                Countries oldCountryIdOfGuardianCollectionGuardian = guardianCollectionGuardian.getCountryId();
                guardianCollectionGuardian.setCountryId(countries);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
                if (oldCountryIdOfGuardianCollectionGuardian != null) {
                    oldCountryIdOfGuardianCollectionGuardian.getGuardianCollection().remove(guardianCollectionGuardian);
                    oldCountryIdOfGuardianCollectionGuardian = em.merge(oldCountryIdOfGuardianCollectionGuardian);
                }
            }
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

    public void edit(Countries countries) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Countries persistentCountries = em.find(Countries.class, countries.getId());
            Collection<Employee> employeeCollectionOld = persistentCountries.getEmployeeCollection();
            Collection<Employee> employeeCollectionNew = countries.getEmployeeCollection();
            Collection<Employee> employeeCollection1Old = persistentCountries.getEmployeeCollection1();
            Collection<Employee> employeeCollection1New = countries.getEmployeeCollection1();
            Collection<Student> studentCollectionOld = persistentCountries.getStudentCollection();
            Collection<Student> studentCollectionNew = countries.getStudentCollection();
            Collection<Student> studentCollection1Old = persistentCountries.getStudentCollection1();
            Collection<Student> studentCollection1New = countries.getStudentCollection1();
            Collection<Guardian> guardianCollectionOld = persistentCountries.getGuardianCollection();
            Collection<Guardian> guardianCollectionNew = countries.getGuardianCollection();
            Collection<Employee> attachedEmployeeCollectionNew = new ArrayList<Employee>();
            for (Employee employeeCollectionNewEmployeeToAttach : employeeCollectionNew) {
                employeeCollectionNewEmployeeToAttach = em.getReference(employeeCollectionNewEmployeeToAttach.getClass(), employeeCollectionNewEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollectionNew.add(employeeCollectionNewEmployeeToAttach);
            }
            employeeCollectionNew = attachedEmployeeCollectionNew;
            countries.setEmployeeCollection(employeeCollectionNew);
            Collection<Employee> attachedEmployeeCollection1New = new ArrayList<Employee>();
            for (Employee employeeCollection1NewEmployeeToAttach : employeeCollection1New) {
                employeeCollection1NewEmployeeToAttach = em.getReference(employeeCollection1NewEmployeeToAttach.getClass(), employeeCollection1NewEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollection1New.add(employeeCollection1NewEmployeeToAttach);
            }
            employeeCollection1New = attachedEmployeeCollection1New;
            countries.setEmployeeCollection1(employeeCollection1New);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            countries.setStudentCollection(studentCollectionNew);
            Collection<Student> attachedStudentCollection1New = new ArrayList<Student>();
            for (Student studentCollection1NewStudentToAttach : studentCollection1New) {
                studentCollection1NewStudentToAttach = em.getReference(studentCollection1NewStudentToAttach.getClass(), studentCollection1NewStudentToAttach.getStudentId());
                attachedStudentCollection1New.add(studentCollection1NewStudentToAttach);
            }
            studentCollection1New = attachedStudentCollection1New;
            countries.setStudentCollection1(studentCollection1New);
            Collection<Guardian> attachedGuardianCollectionNew = new ArrayList<Guardian>();
            for (Guardian guardianCollectionNewGuardianToAttach : guardianCollectionNew) {
                guardianCollectionNewGuardianToAttach = em.getReference(guardianCollectionNewGuardianToAttach.getClass(), guardianCollectionNewGuardianToAttach.getGuardianId());
                attachedGuardianCollectionNew.add(guardianCollectionNewGuardianToAttach);
            }
            guardianCollectionNew = attachedGuardianCollectionNew;
            countries.setGuardianCollection(guardianCollectionNew);
            countries = em.merge(countries);
            for (Employee employeeCollectionOldEmployee : employeeCollectionOld) {
                if (!employeeCollectionNew.contains(employeeCollectionOldEmployee)) {
                    employeeCollectionOldEmployee.setNationality(null);
                    employeeCollectionOldEmployee = em.merge(employeeCollectionOldEmployee);
                }
            }
            for (Employee employeeCollectionNewEmployee : employeeCollectionNew) {
                if (!employeeCollectionOld.contains(employeeCollectionNewEmployee)) {
                    Countries oldNationalityOfEmployeeCollectionNewEmployee = employeeCollectionNewEmployee.getNationality();
                    employeeCollectionNewEmployee.setNationality(countries);
                    employeeCollectionNewEmployee = em.merge(employeeCollectionNewEmployee);
                    if (oldNationalityOfEmployeeCollectionNewEmployee != null && !oldNationalityOfEmployeeCollectionNewEmployee.equals(countries)) {
                        oldNationalityOfEmployeeCollectionNewEmployee.getEmployeeCollection().remove(employeeCollectionNewEmployee);
                        oldNationalityOfEmployeeCollectionNewEmployee = em.merge(oldNationalityOfEmployeeCollectionNewEmployee);
                    }
                }
            }
            for (Employee employeeCollection1OldEmployee : employeeCollection1Old) {
                if (!employeeCollection1New.contains(employeeCollection1OldEmployee)) {
                    employeeCollection1OldEmployee.setCountryId(null);
                    employeeCollection1OldEmployee = em.merge(employeeCollection1OldEmployee);
                }
            }
            for (Employee employeeCollection1NewEmployee : employeeCollection1New) {
                if (!employeeCollection1Old.contains(employeeCollection1NewEmployee)) {
                    Countries oldCountryIdOfEmployeeCollection1NewEmployee = employeeCollection1NewEmployee.getCountryId();
                    employeeCollection1NewEmployee.setCountryId(countries);
                    employeeCollection1NewEmployee = em.merge(employeeCollection1NewEmployee);
                    if (oldCountryIdOfEmployeeCollection1NewEmployee != null && !oldCountryIdOfEmployeeCollection1NewEmployee.equals(countries)) {
                        oldCountryIdOfEmployeeCollection1NewEmployee.getEmployeeCollection1().remove(employeeCollection1NewEmployee);
                        oldCountryIdOfEmployeeCollection1NewEmployee = em.merge(oldCountryIdOfEmployeeCollection1NewEmployee);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setNationalityId(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    Countries oldNationalityIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getNationalityId();
                    studentCollectionNewStudent.setNationalityId(countries);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldNationalityIdOfStudentCollectionNewStudent != null && !oldNationalityIdOfStudentCollectionNewStudent.equals(countries)) {
                        oldNationalityIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldNationalityIdOfStudentCollectionNewStudent = em.merge(oldNationalityIdOfStudentCollectionNewStudent);
                    }
                }
            }
            for (Student studentCollection1OldStudent : studentCollection1Old) {
                if (!studentCollection1New.contains(studentCollection1OldStudent)) {
                    studentCollection1OldStudent.setAddressCountryId(null);
                    studentCollection1OldStudent = em.merge(studentCollection1OldStudent);
                }
            }
            for (Student studentCollection1NewStudent : studentCollection1New) {
                if (!studentCollection1Old.contains(studentCollection1NewStudent)) {
                    Countries oldAddressCountryIdOfStudentCollection1NewStudent = studentCollection1NewStudent.getAddressCountryId();
                    studentCollection1NewStudent.setAddressCountryId(countries);
                    studentCollection1NewStudent = em.merge(studentCollection1NewStudent);
                    if (oldAddressCountryIdOfStudentCollection1NewStudent != null && !oldAddressCountryIdOfStudentCollection1NewStudent.equals(countries)) {
                        oldAddressCountryIdOfStudentCollection1NewStudent.getStudentCollection1().remove(studentCollection1NewStudent);
                        oldAddressCountryIdOfStudentCollection1NewStudent = em.merge(oldAddressCountryIdOfStudentCollection1NewStudent);
                    }
                }
            }
            for (Guardian guardianCollectionOldGuardian : guardianCollectionOld) {
                if (!guardianCollectionNew.contains(guardianCollectionOldGuardian)) {
                    guardianCollectionOldGuardian.setCountryId(null);
                    guardianCollectionOldGuardian = em.merge(guardianCollectionOldGuardian);
                }
            }
            for (Guardian guardianCollectionNewGuardian : guardianCollectionNew) {
                if (!guardianCollectionOld.contains(guardianCollectionNewGuardian)) {
                    Countries oldCountryIdOfGuardianCollectionNewGuardian = guardianCollectionNewGuardian.getCountryId();
                    guardianCollectionNewGuardian.setCountryId(countries);
                    guardianCollectionNewGuardian = em.merge(guardianCollectionNewGuardian);
                    if (oldCountryIdOfGuardianCollectionNewGuardian != null && !oldCountryIdOfGuardianCollectionNewGuardian.equals(countries)) {
                        oldCountryIdOfGuardianCollectionNewGuardian.getGuardianCollection().remove(guardianCollectionNewGuardian);
                        oldCountryIdOfGuardianCollectionNewGuardian = em.merge(oldCountryIdOfGuardianCollectionNewGuardian);
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
                Integer id = countries.getId();
                if (findCountries(id) == null) {
                    throw new NonexistentEntityException("The countries with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Countries countries;
            try {
                countries = em.getReference(Countries.class, id);
                countries.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The countries with id " + id + " no longer exists.", enfe);
            }
            Collection<Employee> employeeCollection = countries.getEmployeeCollection();
            for (Employee employeeCollectionEmployee : employeeCollection) {
                employeeCollectionEmployee.setNationality(null);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
            }
            Collection<Employee> employeeCollection1 = countries.getEmployeeCollection1();
            for (Employee employeeCollection1Employee : employeeCollection1) {
                employeeCollection1Employee.setCountryId(null);
                employeeCollection1Employee = em.merge(employeeCollection1Employee);
            }
            Collection<Student> studentCollection = countries.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setNationalityId(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<Student> studentCollection1 = countries.getStudentCollection1();
            for (Student studentCollection1Student : studentCollection1) {
                studentCollection1Student.setAddressCountryId(null);
                studentCollection1Student = em.merge(studentCollection1Student);
            }
            Collection<Guardian> guardianCollection = countries.getGuardianCollection();
            for (Guardian guardianCollectionGuardian : guardianCollection) {
                guardianCollectionGuardian.setCountryId(null);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
            }
            em.remove(countries);
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

    public List<Countries> findCountriesEntities() {
        return findCountriesEntities(true, -1, -1);
    }

    public List<Countries> findCountriesEntities(int maxResults, int firstResult) {
        return findCountriesEntities(false, maxResults, firstResult);
    }

    private List<Countries> findCountriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Countries.class));
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

    public Countries findCountries(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Countries.class, id);
        } finally {
            em.close();
        }
    }

    public int getCountriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Countries> rt = cq.from(Countries.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
