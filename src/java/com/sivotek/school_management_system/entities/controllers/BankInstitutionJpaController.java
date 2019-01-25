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
import com.sivotek.school_management_system.entities.BankAccount;
import com.sivotek.school_management_system.entities.BankInstitution;
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
public class BankInstitutionJpaController implements Serializable {

    public BankInstitutionJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BankInstitution bankInstitution) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (bankInstitution.getBankAccountCollection() == null) {
            bankInstitution.setBankAccountCollection(new ArrayList<BankAccount>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            CompanyBranch branchId = bankInstitution.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                bankInstitution.setBranchId(branchId);
            }
            Collection<BankAccount> attachedBankAccountCollection = new ArrayList<BankAccount>();
            for (BankAccount bankAccountCollectionBankAccountToAttach : bankInstitution.getBankAccountCollection()) {
                bankAccountCollectionBankAccountToAttach = em.getReference(bankAccountCollectionBankAccountToAttach.getClass(), bankAccountCollectionBankAccountToAttach.getId());
                attachedBankAccountCollection.add(bankAccountCollectionBankAccountToAttach);
            }
            bankInstitution.setBankAccountCollection(attachedBankAccountCollection);
            em.persist(bankInstitution);
            if (branchId != null) {
                branchId.getBankInstitutionCollection().add(bankInstitution);
                branchId = em.merge(branchId);
            }
            for (BankAccount bankAccountCollectionBankAccount : bankInstitution.getBankAccountCollection()) {
                BankInstitution oldInstitutionIdOfBankAccountCollectionBankAccount = bankAccountCollectionBankAccount.getInstitutionId();
                bankAccountCollectionBankAccount.setInstitutionId(bankInstitution);
                bankAccountCollectionBankAccount = em.merge(bankAccountCollectionBankAccount);
                if (oldInstitutionIdOfBankAccountCollectionBankAccount != null) {
                    oldInstitutionIdOfBankAccountCollectionBankAccount.getBankAccountCollection().remove(bankAccountCollectionBankAccount);
                    oldInstitutionIdOfBankAccountCollectionBankAccount = em.merge(oldInstitutionIdOfBankAccountCollectionBankAccount);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBankInstitution(bankInstitution.getId()) != null) {
                throw new PreexistingEntityException("BankInstitution " + bankInstitution + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BankInstitution bankInstitution) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BankInstitution persistentBankInstitution = em.find(BankInstitution.class, bankInstitution.getId());
            CompanyBranch branchIdOld = persistentBankInstitution.getBranchId();
            CompanyBranch branchIdNew = bankInstitution.getBranchId();
            Collection<BankAccount> bankAccountCollectionOld = persistentBankInstitution.getBankAccountCollection();
            Collection<BankAccount> bankAccountCollectionNew = bankInstitution.getBankAccountCollection();
            List<String> illegalOrphanMessages = null;
            for (BankAccount bankAccountCollectionOldBankAccount : bankAccountCollectionOld) {
                if (!bankAccountCollectionNew.contains(bankAccountCollectionOldBankAccount)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BankAccount " + bankAccountCollectionOldBankAccount + " since its institutionId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                bankInstitution.setBranchId(branchIdNew);
            }
            Collection<BankAccount> attachedBankAccountCollectionNew = new ArrayList<BankAccount>();
            for (BankAccount bankAccountCollectionNewBankAccountToAttach : bankAccountCollectionNew) {
                bankAccountCollectionNewBankAccountToAttach = em.getReference(bankAccountCollectionNewBankAccountToAttach.getClass(), bankAccountCollectionNewBankAccountToAttach.getId());
                attachedBankAccountCollectionNew.add(bankAccountCollectionNewBankAccountToAttach);
            }
            bankAccountCollectionNew = attachedBankAccountCollectionNew;
            bankInstitution.setBankAccountCollection(bankAccountCollectionNew);
            bankInstitution = em.merge(bankInstitution);
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getBankInstitutionCollection().remove(bankInstitution);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getBankInstitutionCollection().add(bankInstitution);
                branchIdNew = em.merge(branchIdNew);
            }
            for (BankAccount bankAccountCollectionNewBankAccount : bankAccountCollectionNew) {
                if (!bankAccountCollectionOld.contains(bankAccountCollectionNewBankAccount)) {
                    BankInstitution oldInstitutionIdOfBankAccountCollectionNewBankAccount = bankAccountCollectionNewBankAccount.getInstitutionId();
                    bankAccountCollectionNewBankAccount.setInstitutionId(bankInstitution);
                    bankAccountCollectionNewBankAccount = em.merge(bankAccountCollectionNewBankAccount);
                    if (oldInstitutionIdOfBankAccountCollectionNewBankAccount != null && !oldInstitutionIdOfBankAccountCollectionNewBankAccount.equals(bankInstitution)) {
                        oldInstitutionIdOfBankAccountCollectionNewBankAccount.getBankAccountCollection().remove(bankAccountCollectionNewBankAccount);
                        oldInstitutionIdOfBankAccountCollectionNewBankAccount = em.merge(oldInstitutionIdOfBankAccountCollectionNewBankAccount);
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
                Long id = bankInstitution.getId();
                if (findBankInstitution(id) == null) {
                    throw new NonexistentEntityException("The bankInstitution with id " + id + " no longer exists.");
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
            BankInstitution bankInstitution;
            try {
                bankInstitution = em.getReference(BankInstitution.class, id);
                bankInstitution.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bankInstitution with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BankAccount> bankAccountCollectionOrphanCheck = bankInstitution.getBankAccountCollection();
            for (BankAccount bankAccountCollectionOrphanCheckBankAccount : bankAccountCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BankInstitution (" + bankInstitution + ") cannot be destroyed since the BankAccount " + bankAccountCollectionOrphanCheckBankAccount + " in its bankAccountCollection field has a non-nullable institutionId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CompanyBranch branchId = bankInstitution.getBranchId();
            if (branchId != null) {
                branchId.getBankInstitutionCollection().remove(bankInstitution);
                branchId = em.merge(branchId);
            }
            em.remove(bankInstitution);
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

    public List<BankInstitution> findBankInstitutionEntities() {
        return findBankInstitutionEntities(true, -1, -1);
    }

    public List<BankInstitution> findBankInstitutionEntities(int maxResults, int firstResult) {
        return findBankInstitutionEntities(false, maxResults, firstResult);
    }

    private List<BankInstitution> findBankInstitutionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BankInstitution.class));
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

    public BankInstitution findBankInstitution(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BankInstitution.class, id);
        } finally {
            em.close();
        }
    }

    public int getBankInstitutionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BankInstitution> rt = cq.from(BankInstitution.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
