/*
 * SIVOTEK SCHOOL  MANAGEMENT SYSTEM.
 * DEVELOPED BY OKOYE VICTOR FOR SIVOTEK SOLUTIONS LTD.
 * ALL RIGHT RESERVED 2016
 */
package com.sivotek.school_management_system.entities.controllers;

import com.sivotek.school_management_system.entities.Cities;
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
public class CitiesJpaController implements Serializable {

    public CitiesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cities cities) throws RollbackFailureException, Exception {
        if (cities.getEmployeeCollection() == null) {
            cities.setEmployeeCollection(new ArrayList<Employee>());
        }
        if (cities.getStudentCollection() == null) {
            cities.setStudentCollection(new ArrayList<Student>());
        }
        if (cities.getGuardianCollection() == null) {
            cities.setGuardianCollection(new ArrayList<Guardian>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Employee> attachedEmployeeCollection = new ArrayList<Employee>();
            for (Employee employeeCollectionEmployeeToAttach : cities.getEmployeeCollection()) {
                employeeCollectionEmployeeToAttach = em.getReference(employeeCollectionEmployeeToAttach.getClass(), employeeCollectionEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollection.add(employeeCollectionEmployeeToAttach);
            }
            cities.setEmployeeCollection(attachedEmployeeCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : cities.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            cities.setStudentCollection(attachedStudentCollection);
            Collection<Guardian> attachedGuardianCollection = new ArrayList<Guardian>();
            for (Guardian guardianCollectionGuardianToAttach : cities.getGuardianCollection()) {
                guardianCollectionGuardianToAttach = em.getReference(guardianCollectionGuardianToAttach.getClass(), guardianCollectionGuardianToAttach.getGuardianId());
                attachedGuardianCollection.add(guardianCollectionGuardianToAttach);
            }
            cities.setGuardianCollection(attachedGuardianCollection);
            em.persist(cities);
            for (Employee employeeCollectionEmployee : cities.getEmployeeCollection()) {
                Cities oldCityOfEmployeeCollectionEmployee = employeeCollectionEmployee.getCity();
                employeeCollectionEmployee.setCity(cities);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
                if (oldCityOfEmployeeCollectionEmployee != null) {
                    oldCityOfEmployeeCollectionEmployee.getEmployeeCollection().remove(employeeCollectionEmployee);
                    oldCityOfEmployeeCollectionEmployee = em.merge(oldCityOfEmployeeCollectionEmployee);
                }
            }
            for (Student studentCollectionStudent : cities.getStudentCollection()) {
                Cities oldAddressCityIdOfStudentCollectionStudent = studentCollectionStudent.getAddressCityId();
                studentCollectionStudent.setAddressCityId(cities);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldAddressCityIdOfStudentCollectionStudent != null) {
                    oldAddressCityIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldAddressCityIdOfStudentCollectionStudent = em.merge(oldAddressCityIdOfStudentCollectionStudent);
                }
            }
            for (Guardian guardianCollectionGuardian : cities.getGuardianCollection()) {
                Cities oldCityOfGuardianCollectionGuardian = guardianCollectionGuardian.getCity();
                guardianCollectionGuardian.setCity(cities);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
                if (oldCityOfGuardianCollectionGuardian != null) {
                    oldCityOfGuardianCollectionGuardian.getGuardianCollection().remove(guardianCollectionGuardian);
                    oldCityOfGuardianCollectionGuardian = em.merge(oldCityOfGuardianCollectionGuardian);
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

    public void edit(Cities cities) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Cities persistentCities = em.find(Cities.class, cities.getId());
            Collection<Employee> employeeCollectionOld = persistentCities.getEmployeeCollection();
            Collection<Employee> employeeCollectionNew = cities.getEmployeeCollection();
            Collection<Student> studentCollectionOld = persistentCities.getStudentCollection();
            Collection<Student> studentCollectionNew = cities.getStudentCollection();
            Collection<Guardian> guardianCollectionOld = persistentCities.getGuardianCollection();
            Collection<Guardian> guardianCollectionNew = cities.getGuardianCollection();
            Collection<Employee> attachedEmployeeCollectionNew = new ArrayList<Employee>();
            for (Employee employeeCollectionNewEmployeeToAttach : employeeCollectionNew) {
                employeeCollectionNewEmployeeToAttach = em.getReference(employeeCollectionNewEmployeeToAttach.getClass(), employeeCollectionNewEmployeeToAttach.getEmployeeId());
                attachedEmployeeCollectionNew.add(employeeCollectionNewEmployeeToAttach);
            }
            employeeCollectionNew = attachedEmployeeCollectionNew;
            cities.setEmployeeCollection(employeeCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            cities.setStudentCollection(studentCollectionNew);
            Collection<Guardian> attachedGuardianCollectionNew = new ArrayList<Guardian>();
            for (Guardian guardianCollectionNewGuardianToAttach : guardianCollectionNew) {
                guardianCollectionNewGuardianToAttach = em.getReference(guardianCollectionNewGuardianToAttach.getClass(), guardianCollectionNewGuardianToAttach.getGuardianId());
                attachedGuardianCollectionNew.add(guardianCollectionNewGuardianToAttach);
            }
            guardianCollectionNew = attachedGuardianCollectionNew;
            cities.setGuardianCollection(guardianCollectionNew);
            cities = em.merge(cities);
            for (Employee employeeCollectionOldEmployee : employeeCollectionOld) {
                if (!employeeCollectionNew.contains(employeeCollectionOldEmployee)) {
                    employeeCollectionOldEmployee.setCity(null);
                    employeeCollectionOldEmployee = em.merge(employeeCollectionOldEmployee);
                }
            }
            for (Employee employeeCollectionNewEmployee : employeeCollectionNew) {
                if (!employeeCollectionOld.contains(employeeCollectionNewEmployee)) {
                    Cities oldCityOfEmployeeCollectionNewEmployee = employeeCollectionNewEmployee.getCity();
                    employeeCollectionNewEmployee.setCity(cities);
                    employeeCollectionNewEmployee = em.merge(employeeCollectionNewEmployee);
                    if (oldCityOfEmployeeCollectionNewEmployee != null && !oldCityOfEmployeeCollectionNewEmployee.equals(cities)) {
                        oldCityOfEmployeeCollectionNewEmployee.getEmployeeCollection().remove(employeeCollectionNewEmployee);
                        oldCityOfEmployeeCollectionNewEmployee = em.merge(oldCityOfEmployeeCollectionNewEmployee);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setAddressCityId(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    Cities oldAddressCityIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getAddressCityId();
                    studentCollectionNewStudent.setAddressCityId(cities);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldAddressCityIdOfStudentCollectionNewStudent != null && !oldAddressCityIdOfStudentCollectionNewStudent.equals(cities)) {
                        oldAddressCityIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldAddressCityIdOfStudentCollectionNewStudent = em.merge(oldAddressCityIdOfStudentCollectionNewStudent);
                    }
                }
            }
            for (Guardian guardianCollectionOldGuardian : guardianCollectionOld) {
                if (!guardianCollectionNew.contains(guardianCollectionOldGuardian)) {
                    guardianCollectionOldGuardian.setCity(null);
                    guardianCollectionOldGuardian = em.merge(guardianCollectionOldGuardian);
                }
            }
            for (Guardian guardianCollectionNewGuardian : guardianCollectionNew) {
                if (!guardianCollectionOld.contains(guardianCollectionNewGuardian)) {
                    Cities oldCityOfGuardianCollectionNewGuardian = guardianCollectionNewGuardian.getCity();
                    guardianCollectionNewGuardian.setCity(cities);
                    guardianCollectionNewGuardian = em.merge(guardianCollectionNewGuardian);
                    if (oldCityOfGuardianCollectionNewGuardian != null && !oldCityOfGuardianCollectionNewGuardian.equals(cities)) {
                        oldCityOfGuardianCollectionNewGuardian.getGuardianCollection().remove(guardianCollectionNewGuardian);
                        oldCityOfGuardianCollectionNewGuardian = em.merge(oldCityOfGuardianCollectionNewGuardian);
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
                Integer id = cities.getId();
                if (findCities(id) == null) {
                    throw new NonexistentEntityException("The cities with id " + id + " no longer exists.");
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
            Cities cities;
            try {
                cities = em.getReference(Cities.class, id);
                cities.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cities with id " + id + " no longer exists.", enfe);
            }
            Collection<Employee> employeeCollection = cities.getEmployeeCollection();
            for (Employee employeeCollectionEmployee : employeeCollection) {
                employeeCollectionEmployee.setCity(null);
                employeeCollectionEmployee = em.merge(employeeCollectionEmployee);
            }
            Collection<Student> studentCollection = cities.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setAddressCityId(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<Guardian> guardianCollection = cities.getGuardianCollection();
            for (Guardian guardianCollectionGuardian : guardianCollection) {
                guardianCollectionGuardian.setCity(null);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
            }
            em.remove(cities);
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

    public List<Cities> findCitiesEntities() {
        return findCitiesEntities(true, -1, -1);
    }

    public List<Cities> findCitiesEntities(int maxResults, int firstResult) {
        return findCitiesEntities(false, maxResults, firstResult);
    }

    private List<Cities> findCitiesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cities.class));
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

    public Cities findCities(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cities.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitiesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cities> rt = cq.from(Cities.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
