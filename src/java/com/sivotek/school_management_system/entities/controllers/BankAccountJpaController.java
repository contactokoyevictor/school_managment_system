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
import com.sivotek.school_management_system.entities.BankInstitution;
import com.sivotek.school_management_system.entities.CompanyBranch;
import com.sivotek.school_management_system.entities.BankAccountBalance;
import java.util.ArrayList;
import java.util.Collection;
import com.sivotek.school_management_system.entities.AccountTransaction;
import com.sivotek.school_management_system.entities.BankAccount;
import com.sivotek.school_management_system.entities.BankAccountPrivilege;
import com.sivotek.school_management_system.entities.controllers.exceptions.IllegalOrphanException;
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
public class BankAccountJpaController implements Serializable {

    public BankAccountJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BankAccount bankAccount) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (bankAccount.getBankAccountBalanceCollection() == null) {
            bankAccount.setBankAccountBalanceCollection(new ArrayList<BankAccountBalance>());
        }
        if (bankAccount.getAccountTransactionCollection() == null) {
            bankAccount.setAccountTransactionCollection(new ArrayList<AccountTransaction>());
        }
        if (bankAccount.getBankAccountPrivilegeCollection() == null) {
            bankAccount.setBankAccountPrivilegeCollection(new ArrayList<BankAccountPrivilege>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BankInstitution institutionId = bankAccount.getInstitutionId();
            if (institutionId != null) {
                institutionId = em.getReference(institutionId.getClass(), institutionId.getId());
                bankAccount.setInstitutionId(institutionId);
            }
            CompanyBranch branchId = bankAccount.getBranchId();
            if (branchId != null) {
                branchId = em.getReference(branchId.getClass(), branchId.getBranchId());
                bankAccount.setBranchId(branchId);
            }
            Collection<BankAccountBalance> attachedBankAccountBalanceCollection = new ArrayList<BankAccountBalance>();
            for (BankAccountBalance bankAccountBalanceCollectionBankAccountBalanceToAttach : bankAccount.getBankAccountBalanceCollection()) {
                bankAccountBalanceCollectionBankAccountBalanceToAttach = em.getReference(bankAccountBalanceCollectionBankAccountBalanceToAttach.getClass(), bankAccountBalanceCollectionBankAccountBalanceToAttach.getId());
                attachedBankAccountBalanceCollection.add(bankAccountBalanceCollectionBankAccountBalanceToAttach);
            }
            bankAccount.setBankAccountBalanceCollection(attachedBankAccountBalanceCollection);
            Collection<AccountTransaction> attachedAccountTransactionCollection = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionAccountTransactionToAttach : bankAccount.getAccountTransactionCollection()) {
                accountTransactionCollectionAccountTransactionToAttach = em.getReference(accountTransactionCollectionAccountTransactionToAttach.getClass(), accountTransactionCollectionAccountTransactionToAttach.getId());
                attachedAccountTransactionCollection.add(accountTransactionCollectionAccountTransactionToAttach);
            }
            bankAccount.setAccountTransactionCollection(attachedAccountTransactionCollection);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollection = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach : bankAccount.getBankAccountPrivilegeCollection()) {
                bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollection.add(bankAccountPrivilegeCollectionBankAccountPrivilegeToAttach);
            }
            bankAccount.setBankAccountPrivilegeCollection(attachedBankAccountPrivilegeCollection);
            em.persist(bankAccount);
            if (institutionId != null) {
                institutionId.getBankAccountCollection().add(bankAccount);
                institutionId = em.merge(institutionId);
            }
            if (branchId != null) {
                branchId.getBankAccountCollection().add(bankAccount);
                branchId = em.merge(branchId);
            }
            for (BankAccountBalance bankAccountBalanceCollectionBankAccountBalance : bankAccount.getBankAccountBalanceCollection()) {
                BankAccount oldAccountIdOfBankAccountBalanceCollectionBankAccountBalance = bankAccountBalanceCollectionBankAccountBalance.getAccountId();
                bankAccountBalanceCollectionBankAccountBalance.setAccountId(bankAccount);
                bankAccountBalanceCollectionBankAccountBalance = em.merge(bankAccountBalanceCollectionBankAccountBalance);
                if (oldAccountIdOfBankAccountBalanceCollectionBankAccountBalance != null) {
                    oldAccountIdOfBankAccountBalanceCollectionBankAccountBalance.getBankAccountBalanceCollection().remove(bankAccountBalanceCollectionBankAccountBalance);
                    oldAccountIdOfBankAccountBalanceCollectionBankAccountBalance = em.merge(oldAccountIdOfBankAccountBalanceCollectionBankAccountBalance);
                }
            }
            for (AccountTransaction accountTransactionCollectionAccountTransaction : bankAccount.getAccountTransactionCollection()) {
                BankAccount oldBankAccountIdOfAccountTransactionCollectionAccountTransaction = accountTransactionCollectionAccountTransaction.getBankAccountId();
                accountTransactionCollectionAccountTransaction.setBankAccountId(bankAccount);
                accountTransactionCollectionAccountTransaction = em.merge(accountTransactionCollectionAccountTransaction);
                if (oldBankAccountIdOfAccountTransactionCollectionAccountTransaction != null) {
                    oldBankAccountIdOfAccountTransactionCollectionAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionAccountTransaction);
                    oldBankAccountIdOfAccountTransactionCollectionAccountTransaction = em.merge(oldBankAccountIdOfAccountTransactionCollectionAccountTransaction);
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionBankAccountPrivilege : bankAccount.getBankAccountPrivilegeCollection()) {
                BankAccount oldAccountIdOfBankAccountPrivilegeCollectionBankAccountPrivilege = bankAccountPrivilegeCollectionBankAccountPrivilege.getAccountId();
                bankAccountPrivilegeCollectionBankAccountPrivilege.setAccountId(bankAccount);
                bankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionBankAccountPrivilege);
                if (oldAccountIdOfBankAccountPrivilegeCollectionBankAccountPrivilege != null) {
                    oldAccountIdOfBankAccountPrivilegeCollectionBankAccountPrivilege.getBankAccountPrivilegeCollection().remove(bankAccountPrivilegeCollectionBankAccountPrivilege);
                    oldAccountIdOfBankAccountPrivilegeCollectionBankAccountPrivilege = em.merge(oldAccountIdOfBankAccountPrivilegeCollectionBankAccountPrivilege);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findBankAccount(bankAccount.getId()) != null) {
                throw new PreexistingEntityException("BankAccount " + bankAccount + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BankAccount bankAccount) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            BankAccount persistentBankAccount = em.find(BankAccount.class, bankAccount.getId());
            BankInstitution institutionIdOld = persistentBankAccount.getInstitutionId();
            BankInstitution institutionIdNew = bankAccount.getInstitutionId();
            CompanyBranch branchIdOld = persistentBankAccount.getBranchId();
            CompanyBranch branchIdNew = bankAccount.getBranchId();
            Collection<BankAccountBalance> bankAccountBalanceCollectionOld = persistentBankAccount.getBankAccountBalanceCollection();
            Collection<BankAccountBalance> bankAccountBalanceCollectionNew = bankAccount.getBankAccountBalanceCollection();
            Collection<AccountTransaction> accountTransactionCollectionOld = persistentBankAccount.getAccountTransactionCollection();
            Collection<AccountTransaction> accountTransactionCollectionNew = bankAccount.getAccountTransactionCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionOld = persistentBankAccount.getBankAccountPrivilegeCollection();
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionNew = bankAccount.getBankAccountPrivilegeCollection();
            List<String> illegalOrphanMessages = null;
            for (BankAccountBalance bankAccountBalanceCollectionOldBankAccountBalance : bankAccountBalanceCollectionOld) {
                if (!bankAccountBalanceCollectionNew.contains(bankAccountBalanceCollectionOldBankAccountBalance)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BankAccountBalance " + bankAccountBalanceCollectionOldBankAccountBalance + " since its accountId field is not nullable.");
                }
            }
            for (AccountTransaction accountTransactionCollectionOldAccountTransaction : accountTransactionCollectionOld) {
                if (!accountTransactionCollectionNew.contains(accountTransactionCollectionOldAccountTransaction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AccountTransaction " + accountTransactionCollectionOldAccountTransaction + " since its bankAccountId field is not nullable.");
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionOldBankAccountPrivilege : bankAccountPrivilegeCollectionOld) {
                if (!bankAccountPrivilegeCollectionNew.contains(bankAccountPrivilegeCollectionOldBankAccountPrivilege)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain BankAccountPrivilege " + bankAccountPrivilegeCollectionOldBankAccountPrivilege + " since its accountId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (institutionIdNew != null) {
                institutionIdNew = em.getReference(institutionIdNew.getClass(), institutionIdNew.getId());
                bankAccount.setInstitutionId(institutionIdNew);
            }
            if (branchIdNew != null) {
                branchIdNew = em.getReference(branchIdNew.getClass(), branchIdNew.getBranchId());
                bankAccount.setBranchId(branchIdNew);
            }
            Collection<BankAccountBalance> attachedBankAccountBalanceCollectionNew = new ArrayList<BankAccountBalance>();
            for (BankAccountBalance bankAccountBalanceCollectionNewBankAccountBalanceToAttach : bankAccountBalanceCollectionNew) {
                bankAccountBalanceCollectionNewBankAccountBalanceToAttach = em.getReference(bankAccountBalanceCollectionNewBankAccountBalanceToAttach.getClass(), bankAccountBalanceCollectionNewBankAccountBalanceToAttach.getId());
                attachedBankAccountBalanceCollectionNew.add(bankAccountBalanceCollectionNewBankAccountBalanceToAttach);
            }
            bankAccountBalanceCollectionNew = attachedBankAccountBalanceCollectionNew;
            bankAccount.setBankAccountBalanceCollection(bankAccountBalanceCollectionNew);
            Collection<AccountTransaction> attachedAccountTransactionCollectionNew = new ArrayList<AccountTransaction>();
            for (AccountTransaction accountTransactionCollectionNewAccountTransactionToAttach : accountTransactionCollectionNew) {
                accountTransactionCollectionNewAccountTransactionToAttach = em.getReference(accountTransactionCollectionNewAccountTransactionToAttach.getClass(), accountTransactionCollectionNewAccountTransactionToAttach.getId());
                attachedAccountTransactionCollectionNew.add(accountTransactionCollectionNewAccountTransactionToAttach);
            }
            accountTransactionCollectionNew = attachedAccountTransactionCollectionNew;
            bankAccount.setAccountTransactionCollection(accountTransactionCollectionNew);
            Collection<BankAccountPrivilege> attachedBankAccountPrivilegeCollectionNew = new ArrayList<BankAccountPrivilege>();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach : bankAccountPrivilegeCollectionNew) {
                bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach = em.getReference(bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach.getClass(), bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach.getId());
                attachedBankAccountPrivilegeCollectionNew.add(bankAccountPrivilegeCollectionNewBankAccountPrivilegeToAttach);
            }
            bankAccountPrivilegeCollectionNew = attachedBankAccountPrivilegeCollectionNew;
            bankAccount.setBankAccountPrivilegeCollection(bankAccountPrivilegeCollectionNew);
            bankAccount = em.merge(bankAccount);
            if (institutionIdOld != null && !institutionIdOld.equals(institutionIdNew)) {
                institutionIdOld.getBankAccountCollection().remove(bankAccount);
                institutionIdOld = em.merge(institutionIdOld);
            }
            if (institutionIdNew != null && !institutionIdNew.equals(institutionIdOld)) {
                institutionIdNew.getBankAccountCollection().add(bankAccount);
                institutionIdNew = em.merge(institutionIdNew);
            }
            if (branchIdOld != null && !branchIdOld.equals(branchIdNew)) {
                branchIdOld.getBankAccountCollection().remove(bankAccount);
                branchIdOld = em.merge(branchIdOld);
            }
            if (branchIdNew != null && !branchIdNew.equals(branchIdOld)) {
                branchIdNew.getBankAccountCollection().add(bankAccount);
                branchIdNew = em.merge(branchIdNew);
            }
            for (BankAccountBalance bankAccountBalanceCollectionNewBankAccountBalance : bankAccountBalanceCollectionNew) {
                if (!bankAccountBalanceCollectionOld.contains(bankAccountBalanceCollectionNewBankAccountBalance)) {
                    BankAccount oldAccountIdOfBankAccountBalanceCollectionNewBankAccountBalance = bankAccountBalanceCollectionNewBankAccountBalance.getAccountId();
                    bankAccountBalanceCollectionNewBankAccountBalance.setAccountId(bankAccount);
                    bankAccountBalanceCollectionNewBankAccountBalance = em.merge(bankAccountBalanceCollectionNewBankAccountBalance);
                    if (oldAccountIdOfBankAccountBalanceCollectionNewBankAccountBalance != null && !oldAccountIdOfBankAccountBalanceCollectionNewBankAccountBalance.equals(bankAccount)) {
                        oldAccountIdOfBankAccountBalanceCollectionNewBankAccountBalance.getBankAccountBalanceCollection().remove(bankAccountBalanceCollectionNewBankAccountBalance);
                        oldAccountIdOfBankAccountBalanceCollectionNewBankAccountBalance = em.merge(oldAccountIdOfBankAccountBalanceCollectionNewBankAccountBalance);
                    }
                }
            }
            for (AccountTransaction accountTransactionCollectionNewAccountTransaction : accountTransactionCollectionNew) {
                if (!accountTransactionCollectionOld.contains(accountTransactionCollectionNewAccountTransaction)) {
                    BankAccount oldBankAccountIdOfAccountTransactionCollectionNewAccountTransaction = accountTransactionCollectionNewAccountTransaction.getBankAccountId();
                    accountTransactionCollectionNewAccountTransaction.setBankAccountId(bankAccount);
                    accountTransactionCollectionNewAccountTransaction = em.merge(accountTransactionCollectionNewAccountTransaction);
                    if (oldBankAccountIdOfAccountTransactionCollectionNewAccountTransaction != null && !oldBankAccountIdOfAccountTransactionCollectionNewAccountTransaction.equals(bankAccount)) {
                        oldBankAccountIdOfAccountTransactionCollectionNewAccountTransaction.getAccountTransactionCollection().remove(accountTransactionCollectionNewAccountTransaction);
                        oldBankAccountIdOfAccountTransactionCollectionNewAccountTransaction = em.merge(oldBankAccountIdOfAccountTransactionCollectionNewAccountTransaction);
                    }
                }
            }
            for (BankAccountPrivilege bankAccountPrivilegeCollectionNewBankAccountPrivilege : bankAccountPrivilegeCollectionNew) {
                if (!bankAccountPrivilegeCollectionOld.contains(bankAccountPrivilegeCollectionNewBankAccountPrivilege)) {
                    BankAccount oldAccountIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege = bankAccountPrivilegeCollectionNewBankAccountPrivilege.getAccountId();
                    bankAccountPrivilegeCollectionNewBankAccountPrivilege.setAccountId(bankAccount);
                    bankAccountPrivilegeCollectionNewBankAccountPrivilege = em.merge(bankAccountPrivilegeCollectionNewBankAccountPrivilege);
                    if (oldAccountIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege != null && !oldAccountIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege.equals(bankAccount)) {
                        oldAccountIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege.getBankAccountPrivilegeCollection().remove(bankAccountPrivilegeCollectionNewBankAccountPrivilege);
                        oldAccountIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege = em.merge(oldAccountIdOfBankAccountPrivilegeCollectionNewBankAccountPrivilege);
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
                Long id = bankAccount.getId();
                if (findBankAccount(id) == null) {
                    throw new NonexistentEntityException("The bankAccount with id " + id + " no longer exists.");
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
            BankAccount bankAccount;
            try {
                bankAccount = em.getReference(BankAccount.class, id);
                bankAccount.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bankAccount with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<BankAccountBalance> bankAccountBalanceCollectionOrphanCheck = bankAccount.getBankAccountBalanceCollection();
            for (BankAccountBalance bankAccountBalanceCollectionOrphanCheckBankAccountBalance : bankAccountBalanceCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BankAccount (" + bankAccount + ") cannot be destroyed since the BankAccountBalance " + bankAccountBalanceCollectionOrphanCheckBankAccountBalance + " in its bankAccountBalanceCollection field has a non-nullable accountId field.");
            }
            Collection<AccountTransaction> accountTransactionCollectionOrphanCheck = bankAccount.getAccountTransactionCollection();
            for (AccountTransaction accountTransactionCollectionOrphanCheckAccountTransaction : accountTransactionCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BankAccount (" + bankAccount + ") cannot be destroyed since the AccountTransaction " + accountTransactionCollectionOrphanCheckAccountTransaction + " in its accountTransactionCollection field has a non-nullable bankAccountId field.");
            }
            Collection<BankAccountPrivilege> bankAccountPrivilegeCollectionOrphanCheck = bankAccount.getBankAccountPrivilegeCollection();
            for (BankAccountPrivilege bankAccountPrivilegeCollectionOrphanCheckBankAccountPrivilege : bankAccountPrivilegeCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This BankAccount (" + bankAccount + ") cannot be destroyed since the BankAccountPrivilege " + bankAccountPrivilegeCollectionOrphanCheckBankAccountPrivilege + " in its bankAccountPrivilegeCollection field has a non-nullable accountId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            BankInstitution institutionId = bankAccount.getInstitutionId();
            if (institutionId != null) {
                institutionId.getBankAccountCollection().remove(bankAccount);
                institutionId = em.merge(institutionId);
            }
            CompanyBranch branchId = bankAccount.getBranchId();
            if (branchId != null) {
                branchId.getBankAccountCollection().remove(bankAccount);
                branchId = em.merge(branchId);
            }
            em.remove(bankAccount);
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

    public List<BankAccount> findBankAccountEntities() {
        return findBankAccountEntities(true, -1, -1);
    }

    public List<BankAccount> findBankAccountEntities(int maxResults, int firstResult) {
        return findBankAccountEntities(false, maxResults, firstResult);
    }

    private List<BankAccount> findBankAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BankAccount.class));
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

    public BankAccount findBankAccount(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BankAccount.class, id);
        } finally {
            em.close();
        }
    }

    public int getBankAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BankAccount> rt = cq.from(BankAccount.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
