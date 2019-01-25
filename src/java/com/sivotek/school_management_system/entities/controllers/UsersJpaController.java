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
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.Student;
import com.sivotek.school_management_system.entities.Guardian;
import com.sivotek.school_management_system.entities.Users;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author acer
 */
public class UsersJpaController implements Serializable {

   private static final Logger log = Logger.getLogger(UsersJpaController.class.getName());
      
    public UsersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public UsersJpaController(){
        try{  
             emf = Persistence.createEntityManagerFactory("school_management_systemPU");
        }
        catch(Exception ex){
        System.out.println("-------Error occoured during JNDI Lookup-------:{0}"+ex.getMessage());
        log.log(Level.ERROR,"-------Error occoured during JNDI Lookup-------:{0}"+new Date(), ex.getCause());
       }
        
    }

    public void create(Users users) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CompanyBranch branchId = users.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                users.setBranchId(branchId);
            }
            Employee employeeId = users.getEmployeeId();
            if (employeeId != null) {
                employeeId = em.getReference(employeeId.getClass(), employeeId.getEmployeeId());
                users.setEmployeeId(employeeId);
            }
            Student studentId = users.getStudentId();
            if (studentId != null) {
                studentId = em.getReference(studentId.getClass(), studentId.getStudentId());
                users.setStudentId(studentId);
            }
            Guardian guardianId = users.getGuardianId();
            if (guardianId != null) {
                guardianId = em.getReference(guardianId.getClass(), guardianId.getGuardianId());
                users.setGuardianId(guardianId);
            }
            em.persist(users);
            if (branchId != null) {
                branchId.getUsersCollection().add(users);
                branchId = em.merge(branchId);
            }
            if (employeeId != null) {
                employeeId.getUsersCollection().add(users);
                employeeId = em.merge(employeeId);
            }
            if (studentId != null) {
                studentId.getUsersCollection().add(users);
                studentId = em.merge(studentId);
            }
            if (guardianId != null) {
                guardianId.getUsersCollection().add(users);
                guardianId = em.merge(guardianId);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findUsers(users.getId()) != null) {
                throw new PreexistingEntityException("Users " + users + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Users users) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Users persistentUsers = em.find(Users.class, users.getId());
            CompanyBranch branchIdOld = persistentUsers.getBranchId();
            CompanyBranch branchIdNew = users.getBranchId();
            Employee employeeIdOld = persistentUsers.getEmployeeId();
            Employee employeeIdNew = users.getEmployeeId();
            Student studentIdOld = persistentUsers.getStudentId();
            Student studentIdNew = users.getStudentId();
            Guardian guardianIdOld = persistentUsers.getGuardianId();
            Guardian guardianIdNew = users.getGuardianId();
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                users.setBranchId(branchIdNew);
            }
            if (employeeIdNew != null) {
                employeeIdNew = em.getReference(employeeIdNew.getClass(), employeeIdNew.getEmployeeId());
                users.setEmployeeId(employeeIdNew);
            }
            if (studentIdNew != null) {
                studentIdNew = em.getReference(studentIdNew.getClass(), studentIdNew.getStudentId());
                users.setStudentId(studentIdNew);
            }
            if (guardianIdNew != null) {
                guardianIdNew = em.getReference(guardianIdNew.getClass(), guardianIdNew.getGuardianId());
                users.setGuardianId(guardianIdNew);
            }
            users = em.merge(users);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getUsersCollection().remove(users);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getUsersCollection().add(users);
                branchIdNew = em.merge(branchIdNew);
            }
            if (employeeIdOld != null && !employeeIdOld.equals(employeeIdNew)) {
                employeeIdOld.getUsersCollection().remove(users);
                employeeIdOld = em.merge(employeeIdOld);
            }
            if (employeeIdNew != null && !employeeIdNew.equals(employeeIdOld)) {
                employeeIdNew.getUsersCollection().add(users);
                employeeIdNew = em.merge(employeeIdNew);
            }
            if (studentIdOld != null && !studentIdOld.equals(studentIdNew)) {
                studentIdOld.getUsersCollection().remove(users);
                studentIdOld = em.merge(studentIdOld);
            }
            if (studentIdNew != null && !studentIdNew.equals(studentIdOld)) {
                studentIdNew.getUsersCollection().add(users);
                studentIdNew = em.merge(studentIdNew);
            }
            if (guardianIdOld != null && !guardianIdOld.equals(guardianIdNew)) {
                guardianIdOld.getUsersCollection().remove(users);
                guardianIdOld = em.merge(guardianIdOld);
            }
            if (guardianIdNew != null && !guardianIdNew.equals(guardianIdOld)) {
                guardianIdNew.getUsersCollection().add(users);
                guardianIdNew = em.merge(guardianIdNew);
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
                Long id = users.getId();
                if (findUsers(id) == null) {
                    throw new NonexistentEntityException("The users with id " + id + " no longer exists.");
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
            em = getEntityManager();
            em.getTransaction().begin();
            Users users;
            try {
                users = em.getReference(Users.class, id);
                users.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The users with id " + id + " no longer exists.", enfe);
            }
            CompanyBranch branchId = users.getBranchId();
            if (branchId != null) {
                branchId.getUsersCollection().remove(users);
                branchId = em.merge(branchId);
            }
            Employee employeeId = users.getEmployeeId();
            if (employeeId != null) {
                employeeId.getUsersCollection().remove(users);
                employeeId = em.merge(employeeId);
            }
            Student studentId = users.getStudentId();
            if (studentId != null) {
                studentId.getUsersCollection().remove(users);
                studentId = em.merge(studentId);
            }
            Guardian guardianId = users.getGuardianId();
            if (guardianId != null) {
                guardianId.getUsersCollection().remove(users);
                guardianId = em.merge(guardianId);
            }
            em.remove(users);
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

    public List<Users> findUsersEntities() {
        return findUsersEntities(true, -1, -1);
    }

    public List<Users> findUsersEntities(int maxResults, int firstResult) {
        return findUsersEntities(false, maxResults, firstResult);
    }

    private List<Users> findUsersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Users.class));
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

    public Users findUsers(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Users.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Users> rt = cq.from(Users.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Users findByUsername_Passwd(String username, String password) 
    {
        Users users = new Users();
        EntityManager em = getEntityManager();
        try {
            Query findByUsername_Passwd = em.createNamedQuery("Users.findByUsername_Passwd");
            findByUsername_Passwd.setParameter("username", username);
            findByUsername_Passwd.setParameter("password", password);
            users = new Users();
            users = (Users) findByUsername_Passwd.getSingleResult();
            return users;
        }catch(Exception ex){
             System.out.println("-------Exception Occoured-------"+ex.getMessage());
             log.log(Level.ERROR,"-------Exception Occoured-------:{0}"+new Date(), ex.getCause());
             users = new Users();
             return users;
        } finally {
            em.close();
        }
    }
}
