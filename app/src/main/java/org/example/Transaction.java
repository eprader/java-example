package org.example;

import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

public class Transaction implements Comparable<Transaction>{
    private final UUID id;
    private final BankAccount source;
    private final BankAccount target;
    private final BigInteger transferAmount;
    private String authentication;

    public Transaction(BankAccount source, BankAccount target, BigInteger transferAmount) throws NullPointerException, IllegalArgumentException {
        if (source == null) {
            throw new NullPointerException("`source` must not be null!");
        }
        if (target == null) {
            throw new NullPointerException("`target` must not be null!");
        }
        if (transferAmount == null) {
            throw new NullPointerException("`transferAmount` must not be null!");
        }
        if(transferAmount.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("`transferAmount` must not be negative!");
        }

        this.id = UUID.randomUUID();
        this.source = source;
        this.target = target;
        this.transferAmount = transferAmount;

        // INFO: `authentication` is null if the transaction was not authenticated yet.
        this.setAuthentication(null);
    }

    public UUID getId() {
        return id;
    }

    public BankAccount getSource() {
        return source;
    }

    public BankAccount getTarget() {
        return target;
    }

    public BigInteger getTransferAmount() {
        return transferAmount;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getAuthentication() {
        return authentication;
    }

    public boolean isAuthenticated() {
        // INFO: If authentication is null it is interpreted as not authenticated.
        return !(this.authentication == null);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", source=" + source +
                ", target=" + target +
                ", transferAmount=" + transferAmount +
                ", authentication='" + authentication + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


    @Override
    public int compareTo(Transaction o) throws NullPointerException{
        if (o == null) {
           throw new NullPointerException("`o` must not be null!");
        }

        if (this.equals(o)) {
            return 0;
        }

        return this.getTransferAmount().compareTo(o.getTransferAmount());
    }
}
