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
import com.sivotek.school_management_system.entities.AccountTransaction;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.TransactionCancellation;
import com.sivotek.school_management_system.entities.TransactionCancellationProof;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
import com.sivotek.school_management_system.entities.controllers.exceptions.NonexistentEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.PreexistingEntityException;
import com.sivotek.school_management_system.entities.controllers.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author MY USER
 */
public class TransactionCancellationJpaController implements Serializable {

    public TransactionCancellationJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionCancellation transactionCancellation) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (transactionCancellation.getTransactionCancellationProofCollection() == null) {
            transactionCancellation.setTransactionCancellationProofCollection(new ArrayList<TransactionCancellationProof>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            AccountTransaction transactionId = transactionCancellation.getTransactionId();
            if (transactionId != null) {
                transactionId = em.getReference(transactionId.getClass(), transactionId.getId());
                transactionCancellation.setTransactionId(transactionId);
            }
            CompanyBranch branchId = transactionCancellation.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                transactionCancellation.setBranchId(branchId);
            }
            Employee createdByEmployeeId = transactionCancellation.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId = em.getReference(createdByEmployeeId.getClass(), createdByEmployeeId.getEmployeeId());
                transactionCancellation.setCreatedByEmployeeId(createdByEmployeeId);
            }
            Collection<TransactionCancellationProof> attachedTransactionCancellationProofCollection = new ArrayList<TransactionCancellationProof>();
            for (TransactionCancellationProof transactionCancellationProofCollectionTransactionCancellationProofToAttach : transactionCancellation.getTransactionCancellationProofCollection()) {
                transactionCancellationProofCollectionTransactionCancellationProofToAttach = em.getReference(transactionCancellationProofCollectionTransactionCancellationProofToAttach.getClass(), transactionCancellationProofCollectionTransactionCancellationProofToAttach.getId());
                attachedTransactionCancellationProofCollection.add(transactionCancellationProofCollectionTransactionCancellationProofToAttach);
            }
            transactionCancellation.setTransactionCancellationProofCollection(attachedTransactionCancellationProofCollection);
            em.persist(transactionCancellation);
            if (transactionId != null) {
                transactionId.getTransactionCancellationCollection().add(transactionCancellation);
                transactionId = em.merge(transactionId);
            }
            if (branchId != null) {
                branchId.getTransactionCancellationCollection().add(transactionCancellation);
                branchId = em.merge(branchId);
            }
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getTransactionCancellationCollection().add(transactionCancellation);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            for (TransactionCancellationProof transactionCancellationProofCollectionTransactionCancellationProof : transactionCancellation.getTransactionCancellationProofCollection()) {
                TransactionCancellation oldCancellationIdOfTransactionCancellationProofCollectionTransactionCancellationProof = transactionCancellationProofCollectionTransactionCancellationProof.getCancellationId();
                transactionCancellationProofCollectionTransactionCancellationProof.setCancellationId(transactionCancellation);
                transactionCancellationProofCollectionTransactionCancellationProof = em.merge(transactionCancellationProofCollectionTransactionCancellationProof);
                if (oldCancellationIdOfTransactionCancellationProofCollectionTransactionCancellationProof != null) {
                    oldCancellationIdOfTransactionCancellationProofCollectionTransactionCancellationProof.getTransactionCancellationProofCollection().remove(transactionCancellationProofCollectionTransactionCancellationProof);
                    oldCancellationIdOfTransactionCancellationProofCollectionTransactionCancellationProof = em.merge(oldCancellationIdOfTransactionCancellationProofCollectionTransactionCancellationProof);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTransactionCancellation(transactionCancellation.getId()) != null) {
                throw new PreexistingEntityException("TransactionCancellation " + transactionCancellation + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionCancellation transactionCancellation) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TransactionCancellation persistentTransactionCancellation = em.find(TransactionCancellation.class, transactionCancellation.getId());
            AccountTransaction transactionIdOld = persistentTransactionCancellation.getTransactionId();
            AccountTransaction transactionIdNew = transactionCancellation.getTransactionId();
            CompanyBranch branchIdOld = persistentTransactionCancellation.getBranchId();
            CompanyBranch branchIdNew = transactionCancellation.getBranchId();
            Employee createdByEmployeeIdOld = persistentTransactionCancellation.getCreatedByEmployeeId();
            Employee createdByEmployeeIdNew = transactionCancellation.getCreatedByEmployeeId();
            Collection<TransactionCancellationProof> transactionCancellationProofCollectionOld = persistentTransactionCancellation.getTransactionCancellationProofCollection();
            Collection<TransactionCancellationProof> transactionCancellationProofCollectionNew = transactionCancellation.getTransactionCancellationProofCollection();
            List<String> illegalOrphanMessages = null;
            for (TransactionCancellationProof transactionCancellationProofCollectionOldTransactionCancellationProof : transactionCancellationProofCollectionOld) {
                if (!transactionCancellationProofCollectionNew.contains(transactionCancellationProofCollectionOldTransactionCancellationProof)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TransactionCancellationProof " + transactionCancellationProofCollectionOldTransactionCancellationProof + " since its cancellationId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (transactionIdNew != null) {
                transactionIdNew = em.getReference(transactionIdNew.getClass(), transactionIdNew.getId());
                transactionCancellation.setTransactionId(transactionIdNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                transactionCancellation.setBranchId(branchIdNew);
            }
            if (createdByEmployeeIdNew != null) {
                createdByEmployeeIdNew = em.getReference(createdByEmployeeIdNew.getClass(), createdByEmployeeIdNew.getEmployeeId());
                transactionCancellation.setCreatedByEmployeeId(createdByEmployeeIdNew);
            }
            Collection<TransactionCancellationProof> attachedTransactionCancellationProofCollectionNew = new ArrayList<TransactionCancellationProof>();
            for (TransactionCancellationProof transactionCancellationProofCollectionNewTransactionCancellationProofToAttach : transactionCancellationProofCollectionNew) {
                transactionCancellationProofCollectionNewTransactionCancellationProofToAttach = em.getReference(transactionCancellationProofCollectionNewTransactionCancellationProofToAttach.getClass(), transactionCancellationProofCollectionNewTransactionCancellationProofToAttach.getId());
                attachedTransactionCancellationProofCollectionNew.add(transactionCancellationProofCollectionNewTransactionCancellationProofToAttach);
            }
            transactionCancellationProofCollectionNew = attachedTransactionCancellationProofCollectionNew;
            transactionCancellation.setTransactionCancellationProofCollection(transactionCancellationProofCollectionNew);
            transactionCancellation = em.merge(transactionCancellation);
            if (transactionIdOld != null && !transactionIdOld.equals(transactionIdNew)) {
                transactionIdOld.getTransactionCancellationCollection().remove(transactionCancellation);
                transactionIdOld = em.merge(transactionIdOld);
            }
            if (transactionIdNew != null && !transactionIdNew.equals(transactionIdOld)) {
                transactionIdNew.getTransactionCancellationCollection().add(transactionCancellation);
                transactionIdNew = em.merge(transactionIdNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getTransactionCancellationCollection().remove(transactionCancellation);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getTransactionCancellationCollection().add(transactionCancellation);
                branchIdNew = em.merge(branchIdNew);
            }
            if (createdByEmployeeIdOld != null && !createdByEmployeeIdOld.equals(createdByEmployeeIdNew)) {
                createdByEmployeeIdOld.getTransactionCancellationCollection().remove(transactionCancellation);
                createdByEmployeeIdOld = em.merge(createdByEmployeeIdOld);
            }
            if (createdByEmployeeIdNew != null && !createdByEmployeeIdNew.equals(createdByEmployeeIdOld)) {
                createdByEmployeeIdNew.getTransactionCancellationCollection().add(transactionCancellation);
                createdByEmployeeIdNew = em.merge(createdByEmployeeIdNew);
            }
            for (TransactionCancellationProof transactionCancellationProofCollectionNewTransactionCancellationProof : transactionCancellationProofCollectionNew) {
                if (!transactionCancellationProofCollectionOld.contains(transactionCancellationProofCollectionNewTransactionCancellationProof)) {
                    TransactionCancellation oldCancellationIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof = transactionCancellationProofCollectionNewTransactionCancellationProof.getCancellationId();
                    transactionCancellationProofCollectionNewTransactionCancellationProof.setCancellationId(transactionCancellation);
                    transactionCancellationProofCollectionNewTransactionCancellationProof = em.merge(transactionCancellationProofCollectionNewTransactionCancellationProof);
                    if (oldCancellationIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof != null && !oldCancellationIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof.equals(transactionCancellation)) {
                        oldCancellationIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof.getTransactionCancellationProofCollection().remove(transactionCancellationProofCollectionNewTransactionCancellationProof);
                        oldCancellationIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof = em.merge(oldCancellationIdOfTransactionCancellationProofCollectionNewTransactionCancellationProof);
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
                Long id = transactionCancellation.getId();
                if (findTransactionCancellation(id) == null) {
                    throw new NonexistentEntityException("The transactionCancellation with id " + id + " no longer exists.");
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
            TransactionCancellation transactionCancellation;
            try {
                transactionCancellation = em.getReference(TransactionCancellation.class, id);
                transactionCancellation.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionCancellation with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TransactionCancellationProof> transactionCancellationProofCollectionOrphanCheck = transactionCancellation.getTransactionCancellationProofCollection();
            for (TransactionCancellationProof transactionCancellationProofCollectionOrphanCheckTransactionCancellationProof : transactionCancellationProofCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TransactionCancellation (" + transactionCancellation + ") cannot be destroyed since the TransactionCancellationProof " + transactionCancellationProofCollectionOrphanCheckTransactionCancellationProof + " in its transactionCancellationProofCollection field has a non-nullable cancellationId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            AccountTransaction transactionId = transactionCancellation.getTransactionId();
            if (transactionId != null) {
                transactionId.getTransactionCancellationCollection().remove(transactionCancellation);
                transactionId = em.merge(transactionId);
            }
            CompanyBranch branchId = transactionCancellation.getBranchId();
            if (branchId != null) {
                branchId.getTransactionCancellationCollection().remove(transactionCancellation);
                branchId = em.merge(branchId);
            }
            Employee createdByEmployeeId = transactionCancellation.getCreatedByEmployeeId();
            if (createdByEmployeeId != null) {
                createdByEmployeeId.getTransactionCancellationCollection().remove(transactionCancellation);
                createdByEmployeeId = em.merge(createdByEmployeeId);
            }
            em.remove(transactionCancellation);
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

    public List<TransactionCancellation> findTransactionCancellationEntities() {
        return findTransactionCancellationEntities(true, -1, -1);
    }

    public List<TransactionCancellation> findTransactionCancellationEntities(int maxResults, int firstResult) {
        return findTransactionCancellationEntities(false, maxResults, firstResult);
    }

    private List<TransactionCancellation> findTransactionCancellationEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionCancellation.class));
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

    public TransactionCancellation findTransactionCancellation(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionCancellation.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionCancellationCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionCancellation> rt = cq.from(TransactionCancellation.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
