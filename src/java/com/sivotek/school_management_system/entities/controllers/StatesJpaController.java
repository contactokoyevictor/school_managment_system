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
import com.sivotek.school_management_system.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.Guardian;
import com.sivotek.school_management_system.entities.States;
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
public class StatesJpaController implements Serializable {

    public StatesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(States states) throws RollbackFailureException, Exception {
        if (states.getStudentCollection() == null) {
            states.setStudentCollection(new ArrayList<Student>());
        }
        if (states.getStudentCollection1() == null) {
            states.setStudentCollection1(new ArrayList<Student>());
        }
        if (states.getGuardianCollection() == null) {
            states.setGuardianCollection(new ArrayList<Guardian>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Student> attachedStudentCollection = new ArrayList<Student>();
            for (Student studentCollectionStudentToAttach : states.getStudentCollection()) {
                studentCollectionStudentToAttach = em.getReference(studentCollectionStudentToAttach.getClass(), studentCollectionStudentToAttach.getStudentId());
                attachedStudentCollection.add(studentCollectionStudentToAttach);
            }
            states.setStudentCollection(attachedStudentCollection);
            Collection<Student> attachedStudentCollection1 = new ArrayList<Student>();
            for (Student studentCollection1StudentToAttach : states.getStudentCollection1()) {
                studentCollection1StudentToAttach = em.getReference(studentCollection1StudentToAttach.getClass(), studentCollection1StudentToAttach.getStudentId());
                attachedStudentCollection1.add(studentCollection1StudentToAttach);
            }
            states.setStudentCollection1(attachedStudentCollection1);
            Collection<Guardian> attachedGuardianCollection = new ArrayList<Guardian>();
            for (Guardian guardianCollectionGuardianToAttach : states.getGuardianCollection()) {
                guardianCollectionGuardianToAttach = em.getReference(guardianCollectionGuardianToAttach.getClass(), guardianCollectionGuardianToAttach.getGuardianId());
                attachedGuardianCollection.add(guardianCollectionGuardianToAttach);
            }
            states.setGuardianCollection(attachedGuardianCollection);
            em.persist(states);
            for (Student studentCollectionStudent : states.getStudentCollection()) {
                States oldStateOfOriginOfStudentCollectionStudent = studentCollectionStudent.getStateOfOrigin();
                studentCollectionStudent.setStateOfOrigin(states);
                studentCollectionStudent = em.merge(studentCollectionStudent);
                if (oldStateOfOriginOfStudentCollectionStudent != null) {
                    oldStateOfOriginOfStudentCollectionStudent.getStudentCollection().remove(studentCollectionStudent);
                    oldStateOfOriginOfStudentCollectionStudent = em.merge(oldStateOfOriginOfStudentCollectionStudent);
                }
            }
            for (Student studentCollection1Student : states.getStudentCollection1()) {
                States oldAddressStateIdOfStudentCollection1Student = studentCollection1Student.getAddressStateId();
                studentCollection1Student.setAddressStateId(states);
                studentCollection1Student = em.merge(studentCollection1Student);
                if (oldAddressStateIdOfStudentCollection1Student != null) {
                    oldAddressStateIdOfStudentCollection1Student.getStudentCollection1().remove(studentCollection1Student);
                    oldAddressStateIdOfStudentCollection1Student = em.merge(oldAddressStateIdOfStudentCollection1Student);
                }
            }
            for (Guardian guardianCollectionGuardian : states.getGuardianCollection()) {
                States oldStateOfGuardianCollectionGuardian = guardianCollectionGuardian.getState();
                guardianCollectionGuardian.setState(states);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
                if (oldStateOfGuardianCollectionGuardian != null) {
                    oldStateOfGuardianCollectionGuardian.getGuardianCollection().remove(guardianCollectionGuardian);
                    oldStateOfGuardianCollectionGuardian = em.merge(oldStateOfGuardianCollectionGuardian);
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

    public void edit(States states) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            States persistentStates = em.find(States.class, states.getId());
            Collection<Student> studentCollectionOld = persistentStates.getStudentCollection();
            Collection<Student> studentCollectionNew = states.getStudentCollection();
            Collection<Student> studentCollection1Old = persistentStates.getStudentCollection1();
            Collection<Student> studentCollection1New = states.getStudentCollection1();
            Collection<Guardian> guardianCollectionOld = persistentStates.getGuardianCollection();
            Collection<Guardian> guardianCollectionNew = states.getGuardianCollection();
            Collection<Student> attachedStudentCollectionNew = new ArrayList<Student>();
            for (Student studentCollectionNewStudentToAttach : studentCollectionNew) {
                studentCollectionNewStudentToAttach = em.getReference(studentCollectionNewStudentToAttach.getClass(), studentCollectionNewStudentToAttach.getStudentId());
                attachedStudentCollectionNew.add(studentCollectionNewStudentToAttach);
            }
            studentCollectionNew = attachedStudentCollectionNew;
            states.setStudentCollection(studentCollectionNew);
            Collection<Student> attachedStudentCollection1New = new ArrayList<Student>();
            for (Student studentCollection1NewStudentToAttach : studentCollection1New) {
                studentCollection1NewStudentToAttach = em.getReference(studentCollection1NewStudentToAttach.getClass(), studentCollection1NewStudentToAttach.getStudentId());
                attachedStudentCollection1New.add(studentCollection1NewStudentToAttach);
            }
            studentCollection1New = attachedStudentCollection1New;
            states.setStudentCollection1(studentCollection1New);
            Collection<Guardian> attachedGuardianCollectionNew = new ArrayList<Guardian>();
            for (Guardian guardianCollectionNewGuardianToAttach : guardianCollectionNew) {
                guardianCollectionNewGuardianToAttach = em.getReference(guardianCollectionNewGuardianToAttach.getClass(), guardianCollectionNewGuardianToAttach.getGuardianId());
                attachedGuardianCollectionNew.add(guardianCollectionNewGuardianToAttach);
            }
            guardianCollectionNew = attachedGuardianCollectionNew;
            states.setGuardianCollection(guardianCollectionNew);
            states = em.merge(states);
            for (Student studentCollectionOldStudent : studentCollectionOld) {
                if (!studentCollectionNew.contains(studentCollectionOldStudent)) {
                    studentCollectionOldStudent.setStateOfOrigin(null);
                    studentCollectionOldStudent = em.merge(studentCollectionOldStudent);
                }
            }
            for (Student studentCollectionNewStudent : studentCollectionNew) {
                if (!studentCollectionOld.contains(studentCollectionNewStudent)) {
                    States oldStateOfOriginOfStudentCollectionNewStudent = studentCollectionNewStudent.getStateOfOrigin();
                    studentCollectionNewStudent.setStateOfOrigin(states);
                    studentCollectionNewStudent = em.merge(studentCollectionNewStudent);
                    if (oldStateOfOriginOfStudentCollectionNewStudent != null && !oldStateOfOriginOfStudentCollectionNewStudent.equals(states)) {
                        oldStateOfOriginOfStudentCollectionNewStudent.getStudentCollection().remove(studentCollectionNewStudent);
                        oldStateOfOriginOfStudentCollectionNewStudent = em.merge(oldStateOfOriginOfStudentCollectionNewStudent);
                    }
                }
            }
            for (Student studentCollection1OldStudent : studentCollection1Old) {
                if (!studentCollection1New.contains(studentCollection1OldStudent)) {
                    studentCollection1OldStudent.setAddressStateId(null);
                    studentCollection1OldStudent = em.merge(studentCollection1OldStudent);
                }
            }
            for (Student studentCollection1NewStudent : studentCollection1New) {
                if (!studentCollection1Old.contains(studentCollection1NewStudent)) {
                    States oldAddressStateIdOfStudentCollection1NewStudent = studentCollection1NewStudent.getAddressStateId();
                    studentCollection1NewStudent.setAddressStateId(states);
                    studentCollection1NewStudent = em.merge(studentCollection1NewStudent);
                    if (oldAddressStateIdOfStudentCollection1NewStudent != null && !oldAddressStateIdOfStudentCollection1NewStudent.equals(states)) {
                        oldAddressStateIdOfStudentCollection1NewStudent.getStudentCollection1().remove(studentCollection1NewStudent);
                        oldAddressStateIdOfStudentCollection1NewStudent = em.merge(oldAddressStateIdOfStudentCollection1NewStudent);
                    }
                }
            }
            for (Guardian guardianCollectionOldGuardian : guardianCollectionOld) {
                if (!guardianCollectionNew.contains(guardianCollectionOldGuardian)) {
                    guardianCollectionOldGuardian.setState(null);
                    guardianCollectionOldGuardian = em.merge(guardianCollectionOldGuardian);
                }
            }
            for (Guardian guardianCollectionNewGuardian : guardianCollectionNew) {
                if (!guardianCollectionOld.contains(guardianCollectionNewGuardian)) {
                    States oldStateOfGuardianCollectionNewGuardian = guardianCollectionNewGuardian.getState();
                    guardianCollectionNewGuardian.setState(states);
                    guardianCollectionNewGuardian = em.merge(guardianCollectionNewGuardian);
                    if (oldStateOfGuardianCollectionNewGuardian != null && !oldStateOfGuardianCollectionNewGuardian.equals(states)) {
                        oldStateOfGuardianCollectionNewGuardian.getGuardianCollection().remove(guardianCollectionNewGuardian);
                        oldStateOfGuardianCollectionNewGuardian = em.merge(oldStateOfGuardianCollectionNewGuardian);
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
                Integer id = states.getId();
                if (findStates(id) == null) {
                    throw new NonexistentEntityException("The states with id " + id + " no longer exists.");
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
            States states;
            try {
                states = em.getReference(States.class, id);
                states.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The states with id " + id + " no longer exists.", enfe);
            }
            Collection<Student> studentCollection = states.getStudentCollection();
            for (Student studentCollectionStudent : studentCollection) {
                studentCollectionStudent.setStateOfOrigin(null);
                studentCollectionStudent = em.merge(studentCollectionStudent);
            }
            Collection<Student> studentCollection1 = states.getStudentCollection1();
            for (Student studentCollection1Student : studentCollection1) {
                studentCollection1Student.setAddressStateId(null);
                studentCollection1Student = em.merge(studentCollection1Student);
            }
            Collection<Guardian> guardianCollection = states.getGuardianCollection();
            for (Guardian guardianCollectionGuardian : guardianCollection) {
                guardianCollectionGuardian.setState(null);
                guardianCollectionGuardian = em.merge(guardianCollectionGuardian);
            }
            em.remove(states);
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

    public List<States> findStatesEntities() {
        return findStatesEntities(true, -1, -1);
    }

    public List<States> findStatesEntities(int maxResults, int firstResult) {
        return findStatesEntities(false, maxResults, firstResult);
    }

    private List<States> findStatesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(States.class));
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

    public States findStates(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(States.class, id);
        } finally {
            em.close();
        }
    }

    public int getStatesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<States> rt = cq.from(States.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
