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
import com.sivotek.school_management_system.entities.Cities;
import com.sivotek.school_management_system.entities.States;
import com.sivotek.school_management_system.entities.Countries;
import com.sivotek.school_management_system.entities.Guardian;
import com.sivotek.school_management_system.entities.Users;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.Student;
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
public class GuardianJpaController implements Serializable {

    public GuardianJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Guardian guardian) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (guardian.getUsersCollection() == null) {
            guardian.setUsersCollection(new ArrayList<Users>());
        }
        if (guardian.getStudentCollection() == null) {
            guardian.setStudentCollection(new ArrayList<Student>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = guardian.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                guardian.setBranchId(branchId);
            }
            Cities city = guardian.getCity();
            if (city != null) {
                city = em.getReference(city.getClass(), city.getId());
                guardian.setCity(city);
            }
            States state = guardian.getState();
            if (state != null) {
                state = em.getReference(state.getClass(), state.getId());
                guardian.setState(state);
            }
            Countries countryId = guardian.getCountryId();
            if (countryId != null) {
                countryId = em.getReference(countryId.getClass(), countryId.getId());
                guardian.setCountryId(countryId);
            }
            Collection<Users> attachedUsersCollection = new ArrayList<Users>();
            for (Users usersCollectionUsersToAttach : guardian.getUsersCollection()) {
                usersCollectionUsersToAttach = em.getReference(usersCollectionUsersToAttach.getClass(), usersCollectionUsersToAttach.getId());
                attachedUsersCollection.add(usersCollectionUsersToAttach);
            }
            guardian.setUsersCollection(attachedUsersCollection);
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : guardian.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            guardian.setStudentCollection(attachedStudentCollection);
            em.persist(guardian);
            if (branchId != null) {
                branchId.getGuardianCollection().add(guardian);
                branchId = em.merge(branchId);
            }
            if (city != null) {
                city.getGuardianCollection().add(guardian);
                city = em.merge(city);
            }
            if (state != null) {
                state.getGuardianCollection().add(guardian);
                state = em.merge(state);
            }
            if (countryId != null) {
                countryId.getGuardianCollection().add(guardian);
                countryId = em.merge(countryId);
            }
            for (Users usersCollectionUsers : guardian.getUsersCollection()) {
                Guardian oldGuardianIdOfUsersCollectionUsers = usersCollectionUsers.getGuardianId();
                usersCollectionUsers.setGuardianId(guardian);
                usersCollectionUsers = em.merge(usersCollectionUsers);
                if (oldGuardianIdOfUsersCollectionUsers != null) {
                    oldGuardianIdOfUsersCollectionUsers.getUsersCollection().remove(usersCollectionUsers);
                    oldGuardianIdOfUsersCollectionUsers = em.merge(oldGuardianIdOfUsersCollectionUsers);
                }
            }
            for (Student studentCollectionStudent : guardian.getStudentCollection()) {
                Guardian oldGuardianIdOfStudentCollectionStudent = studentCollectionStudent.getGuardianId();
                studentCollectionStudent.setGuardianId(guardian);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldGuardianIdOfStudentCollectionStudent != null) {
                    oldGuardianIdOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldGuardianIdOfStudentCollectionStudent = em.merge(oldGuardianIdOfStudentCollectionStudent);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findGuardian(guardian.getGuardianId()) != null) {
                throw new PreexistingEntityException("Guardian " + guardian + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Guardian guardian) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Guardian persistentGuardian = em.find(Guardian.class, guardian.getGuardianId());
            CompanyBranch branchIdOld = persistentGuardian.getBranchId();
            CompanyBranch branchIdNew = guardian.getBranchId();
            Cities cityOld = persistentGuardian.getCity();
            Cities cityNew = guardian.getCity();
            States stateOld = persistentGuardian.getState();
            States stateNew = guardian.getState();
            Countries countryIdOld = persistentGuardian.getCountryId();
            Countries countryIdNew = guardian.getCountryId();
            Collection<Users> usersCollectionOld = persistentGuardian.getUsersCollection();
            Collection<Users> usersCollectionNew = guardian.getUsersCollection();
            Collection<Student> studentCollectionOld = persistentGuardian.getStudentCollection();
            Collection<Student> studentCollectionNew = guardian.getStudentCollection();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                guardian.setBranchId(branchIdNew);
            }
            if (cityNew != null) {
                cityNew = em.getReference(cityNew.getClass(), cityNew.getId());
                guardian.setCity(cityNew);
            }
            if (stateNew != null) {
                stateNew = em.getReference(stateNew.getClass(), stateNew.getId());
                guardian.setState(stateNew);
            }
            if (countryIdNew != null) {
                countryIdNew = em.getReference(countryIdNew.getClass(), countryIdNew.getId());
                guardian.setCountryId(countryIdNew);
            }
            Collection<Users> attachedUsersCollectionNew = new ArrayList<Users>();
            for (Users usersCollectionNewUsersToAttach : usersCollectionNew) {
                usersCollectionNewUsersToAttach = em.getReference(usersCollectionNewUsersToAttach.getClass(), usersCollectionNewUsersToAttach.getId());
                attachedUsersCollectionNew.add(usersCollectionNewUsersToAttach);
            }
            usersCollectionNew = attachedUsersCollectionNew;
            guardian.setUsersCollection(usersCollectionNew);
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            guardian.setStudentCollection(studentCollectionNew);
            guardian = em.merge(guardian);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getGuardianCollection().remove(guardian);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getGuardianCollection().add(guardian);
                branchIdNew = em.merge(branchIdNew);
            }
            if (cityOld != null && !cityOld.equals(cityNew)) {
                cityOld.getGuardianCollection().remove(guardian);
                cityOld = em.merge(cityOld);
            }
            if (cityNew != null && !cityNew.equals(cityOld)) {
                cityNew.getGuardianCollection().add(guardian);
                cityNew = em.merge(cityNew);
            }
            if (stateOld != null && !stateOld.equals(stateNew)) {
                stateOld.getGuardianCollection().remove(guardian);
                stateOld = em.merge(stateOld);
            }
            if (stateNew != null && !stateNew.equals(stateOld)) {
                stateNew.getGuardianCollection().add(guardian);
                stateNew = em.merge(stateNew);
            }
            if (countryIdOld != null && !countryIdOld.equals(countryIdNew)) {
                countryIdOld.getGuardianCollection().remove(guardian);
                countryIdOld = em.merge(countryIdOld);
            }
            if (countryIdNew != null && !countryIdNew.equals(countryIdOld)) {
                countryIdNew.getGuardianCollection().add(guardian);
                countryIdNew = em.merge(countryIdNew);
            }
            for (Users usersCollectionOldUsers : usersCollectionOld) {
                if (!usersCollectionNew.contains(usersCollectionOldUsers)) {
                    usersCollectionOldUsers.setGuardianId(null);
                    usersCollectionOldUsers = em.merge(usersCollectionOldUsers);
                }
            }
            for (Users usersCollectionNewUsers : usersCollectionNew) {
                if (!usersCollectionOld.contains(usersCollectionNewUsers)) {
                    Guardian oldGuardianIdOfUsersCollectionNewUsers = usersCollectionNewUsers.getGuardianId();
                    usersCollectionNewUsers.setGuardianId(guardian);
                    usersCollectionNewUsers = em.merge(usersCollectionNewUsers);
                    if (oldGuardianIdOfUsersCollectionNewUsers != null && !oldGuardianIdOfUsersCollectionNewUsers.equals(guardian)) {
                        oldGuardianIdOfUsersCollectionNewUsers.getUsersCollection().remove(usersCollectionNewUsers);
                        oldGuardianIdOfUsersCollectionNewUsers = em.merge(oldGuardianIdOfUsersCollectionNewUsers);
                    }
                }
            }
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setGuardianId(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    Guardian oldGuardianIdOfStudentCollectionNewStudent = studentCollectionNewStudent.getGuardianId();
                    studentCollectionNewStudent.setGuardianId(guardian);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldGuardianIdOfStudentCollectionNewStudent != null && !oldGuardianIdOfStudentCollectionNewStudent.equals(guardian)) {
                        oldGuardianIdOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldGuardianIdOfStudentCollectionNewStudent = em.merge(oldGuardianIdOfStudentCollectionNewStudent);
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
                Long id = guardian.getGuardianId();
                if (findGuardian(id) == null) {
                    throw new NonexistentEntityException("The guardian with id " + id + " no longer exists.");
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
            Guardian guardian;
            try {
                guardian = em.getReference(Guardian.class, id);
                guardian.getGuardianId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The guardian with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = guardian.getBranchId();
            if (branchId != null) {
                branchId.getGuardianCollection().remove(guardian);
                branchId = em.merge(branchId);
            }
            Cities city = guardian.getCity();
            if (city != null) {
                city.getGuardianCollection().remove(guardian);
                city = em.merge(city);
            }
            States state = guardian.getState();
            if (state != null) {
                state.getGuardianCollection().remove(guardian);
                state = em.merge(state);
            }
            Countries countryId = guardian.getCountryId();
            if (countryId != null) {
                countryId.getGuardianCollection().remove(guardian);
                countryId = em.merge(countryId);
            }
            Collection<Users> usersCollection = guardian.getUsersCollection();
            for (Users usersCollectionUsers : usersCollection) {
                usersCollectionUsers.setGuardianId(null);
                usersCollectionUsers = em.merge(usersCollectionUsers);
            }
            Collection<Student> studentCollection = guardian.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setGuardianId(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            em.remove(guardian);
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

    public List<Guardian> findGuardianEntities() {
        return findGuardianEntities(true, -1, -1);
    }

    public List<Guardian> findGuardianEntities(int maxResults, int firstResult) {
        return findGuardianEntities(false, maxResults, firstResult);
    }

    private List<Guardian> findGuardianEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Guardian.class));
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

    public Guardian findGuardian(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Guardian.class, id);
        } finally {
            em.close();
        }
    }

    public int getGuardianCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Guardian> rt = cq.from(Guardian.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
