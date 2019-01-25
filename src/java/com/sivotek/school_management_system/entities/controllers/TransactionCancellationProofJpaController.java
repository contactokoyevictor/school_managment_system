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
import com.sivotek.school_management_system.entities.TransactionCancellation;
import com.sivotek.school_management_system.entities.Employee;
import com.sivotek.school_management_system.entities.TransactionCancellationProof;
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
public class TransactionCancellationProofJpaController implements Serializable {

    public TransactionCancellationProofJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TransactionCancellationProof transactionCancellationProof) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TransactionCancellation cancellationId = transactionCancellationProof.getCancellationId();
            if (cancellationId != null) {
                cancellationId = em.getReference(cancellationId.getClass(), cancellationId.getId());
                transactionCancellationProof.setCancellationId(cancellationId);
            }
            Employee uploadedByEmployeeId = transactionCancellationProof.getUploadedByEmployeeId();
            if (uploadedByEmployeeId != null) {
                uploadedByEmployeeId = em.getReference(uploadedByEmployeeId.getClass(), uploadedByEmployeeId.getEmployeeId());
                transactionCancellationProof.setUploadedByEmployeeId(uploadedByEmployeeId);
            }
            em.persist(transactionCancellationProof);
            if (cancellationId != null) {
                cancellationId.getTransactionCancellationProofCollection().add(transactionCancellationProof);
                cancellationId = em.merge(cancellationId);
            }
            if (uploadedByEmployeeId != null) {
                uploadedByEmployeeId.getTransactionCancellationProofCollection().add(transactionCancellationProof);
                uploadedByEmployeeId = em.merge(uploadedByEmployeeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTransactionCancellationProof(transactionCancellationProof.getId()) != null) {
                throw new PreexistingEntityException("TransactionCancellationProof " + transactionCancellationProof + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TransactionCancellationProof transactionCancellationProof) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TransactionCancellationProof persistentTransactionCancellationProof = em.find(TransactionCancellationProof.class, transactionCancellationProof.getId());
            TransactionCancellation cancellationIdOld = persistentTransactionCancellationProof.getCancellationId();
            TransactionCancellation cancellationIdNew = transactionCancellationProof.getCancellationId();
            Employee uploadedByEmployeeIdOld = persistentTransactionCancellationProof.getUploadedByEmployeeId();
            Employee uploadedByEmployeeIdNew = transactionCancellationProof.getUploadedByEmployeeId();
            if (cancellationIdNew != null) {
                cancellationIdNew = em.getReference(cancellationIdNew.getClass(), cancellationIdNew.getId());
                transactionCancellationProof.setCancellationId(cancellationIdNew);
            }
            if (uploadedByEmployeeIdNew != null) {
                uploadedByEmployeeIdNew = em.getReference(uploadedByEmployeeIdNew.getClass(), uploadedByEmployeeIdNew.getEmployeeId());
                transactionCancellationProof.setUploadedByEmployeeId(uploadedByEmployeeIdNew);
            }
            transactionCancellationProof = em.merge(transactionCancellationProof);
            if (cancellationIdOld != null && !cancellationIdOld.equals(cancellationIdNew)) {
                cancellationIdOld.getTransactionCancellationProofCollection().remove(transactionCancellationProof);
                cancellationIdOld = em.merge(cancellationIdOld);
            }
            if (cancellationIdNew != null && !cancellationIdNew.equals(cancellationIdOld)) {
                cancellationIdNew.getTransactionCancellationProofCollection().add(transactionCancellationProof);
                cancellationIdNew = em.merge(cancellationIdNew);
            }
            if (uploadedByEmployeeIdOld != null && !uploadedByEmployeeIdOld.equals(uploadedByEmployeeIdNew)) {
                uploadedByEmployeeIdOld.getTransactionCancellationProofCollection().remove(transactionCancellationProof);
                uploadedByEmployeeIdOld = em.merge(uploadedByEmployeeIdOld);
            }
            if (uploadedByEmployeeIdNew != null && !uploadedByEmployeeIdNew.equals(uploadedByEmployeeIdOld)) {
                uploadedByEmployeeIdNew.getTransactionCancellationProofCollection().add(transactionCancellationProof);
                uploadedByEmployeeIdNew = em.merge(uploadedByEmployeeIdNew);
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
                Long id = transactionCancellationProof.getId();
                if (findTransactionCancellationProof(id) == null) {
                    throw new NonexistentEntityException("The transactionCancellationProof with id " + id + " no longer exists.");
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
            TransactionCancellationProof transactionCancellationProof;
            try {
                transactionCancellationProof = em.getReference(TransactionCancellationProof.class, id);
                transactionCancellationProof.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The transactionCancellationProof with id " + id + " no longer exists.", enfe);
            }
            TransactionCancellation cancellationId = transactionCancellationProof.getCancellationId();
            if (cancellationId != null) {
                cancellationId.getTransactionCancellationProofCollection().remove(transactionCancellationProof);
                cancellationId = em.merge(cancellationId);
            }
            Employee uploadedByEmployeeId = transactionCancellationProof.getUploadedByEmployeeId();
            if (uploadedByEmployeeId != null) {
                uploadedByEmployeeId.getTransactionCancellationProofCollection().remove(transactionCancellationProof);
                uploadedByEmployeeId = em.merge(uploadedByEmployeeId);
            }
            em.remove(transactionCancellationProof);
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

    public List<TransactionCancellationProof> findTransactionCancellationProofEntities() {
        return findTransactionCancellationProofEntities(true, -1, -1);
    }

    public List<TransactionCancellationProof> findTransactionCancellationProofEntities(int maxResults, int firstResult) {
        return findTransactionCancellationProofEntities(false, maxResults, firstResult);
    }

    private List<TransactionCancellationProof> findTransactionCancellationProofEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TransactionCancellationProof.class));
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

    public TransactionCancellationProof findTransactionCancellationProof(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TransactionCancellationProof.class, id);
        } finally {
            em.close();
        }
    }

    public int getTransactionCancellationProofCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TransactionCancellationProof> rt = cq.from(TransactionCancellationProof.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
