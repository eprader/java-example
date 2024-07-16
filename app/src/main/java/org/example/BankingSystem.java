package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BankingSystem {
    private Set<BankAccount> managedAccounts;
    private List<Transaction> transactionHistory;

    public BankingSystem() {
        this.managedAccounts = new HashSet<>();
        this.transactionHistory = new ArrayList<>();
    }
    public void addBankAccount(BankAccount bankAccount) throws NullPointerException {
        if (bankAccount == null) {
            throw new NullPointerException("`bankAccount` must not be null!");
        }

        this.managedAccounts.add(bankAccount);
    }

    public void removeBankAccount(BankAccount bankAccount) {
        this.managedAccounts.remove(bankAccount);
    }

    public Set<BankAccount> getManagedAccounts() {
        return new HashSet<>(this.managedAccounts);
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(this.transactionHistory);
    }

    public void transfer(BigInteger amount, BankAccount source, BankAccount target) throws NullPointerException, TransactionException{
        // NOTE: Throws `NullPointerException` for arguments, therefore null does not need to be handled here.
        Transaction transaction = new Transaction(source, target, amount);

        source.getOwner().getAuthenticator().authenticateTransaction(transaction);
        // TODO: Potentially create more detailed Exceptions to enable error handling.
        if (!transaction.isAuthenticated()) {
            throw new TransactionException("Failed to authenticate transaction");
        }
        System.out.println("Authenticated");

        System.out.println("check");
        BigInteger newSourceBalanceInCent = source.getBalanceInCent().subtract(amount);
        if(!source.isValidBalance(newSourceBalanceInCent)) {
            throw new TransactionException("Source account can not fulfill payment.");
        }

        source.setBalanceInCent(newSourceBalanceInCent);
        target.setBalanceInCent(target.getBalanceInCent().add(amount));

        this.transactionHistory.add(transaction);
    }

    @Override
    public String toString() {
        return "BankingSystem{" +
                "managedAccounts=" + managedAccounts +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}
