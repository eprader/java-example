package org.example;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private final String IBAN;
    private Customer owner;
    private BigInteger balanceInCent;

    public BankAccount(Customer owner) throws NullPointerException {
        Random random = new Random();
        int randomThreeDigits = random.nextInt(999) + 100;

        // TODO: Possibly implement a random IBAN generator.
        this.IBAN = "AT151947049836676" + randomThreeDigits;
        this.setOwner(owner);
        this.setBalanceInCent(BigInteger.ZERO);
    }

    public String getIBAN() {
        return IBAN;
    }

    public Customer getOwner() {
        return owner;
    }

    public BigInteger getBalanceInCent() {
        return balanceInCent;
    }

    public void setOwner(Customer owner) throws NullPointerException {
        if (owner == null) {
            throw new NullPointerException("`owner` must not be null!");
        }

        this.owner = owner;
    }

    public void setBalanceInCent(BigInteger balanceInCent) throws  NullPointerException, IllegalArgumentException {
        if (balanceInCent == null) {
           throw new NullPointerException("`balanceInCent` must not be null!");
        }
        if (!this.isValidBalance(balanceInCent)) {
            throw new IllegalArgumentException("`balanceInCent` can not go lower than " + this.getAllowedCredit().negate() + "!");
        }

        this.balanceInCent = balanceInCent;
    }

    public boolean isValidBalance(BigInteger balanceInCent) {
       if (balanceInCent.compareTo(BigInteger.ZERO) >= 0) {
           return true;
       }
       return this.getAllowedCredit().negate().compareTo(balanceInCent) <= 0;
    }

    private BigInteger getAllowedCredit() {
        BigInteger allowedCredit = BigInteger.ZERO;

        switch (this.owner.getCreditScore()) {
            case LOW -> allowedCredit = BigInteger.valueOf(1_000);
            case MEDIUM -> allowedCredit = BigInteger.valueOf(10_000);
            case HIGH -> allowedCredit = BigInteger.valueOf(100_000);
            // NOTE: `owner.creditScore` will be `null` here
            default -> allowedCredit = BigInteger.ZERO;
        }

        return allowedCredit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(getIBAN(), that.getIBAN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIBAN());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "IBAN='" + IBAN + '\'' +
                ", owner=" + owner +
                ", balanceInCent=" + balanceInCent +
                '}';
    }
}
