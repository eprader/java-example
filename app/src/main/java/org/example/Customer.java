package org.example;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Customer {
    private final UUID id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private CreditScore creditScore;
    private Authenticator authenticator;
    Customer(String firstName, String lastName, Date dateOfBirth, CreditScore creditScore) throws NullPointerException{
        if (dateOfBirth == null) {
            throw new NullPointerException("`dateOfBirth` must not be null!");
        }

        this.id = UUID.randomUUID();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.dateOfBirth = dateOfBirth;
        this.setCreditScore(creditScore);
        this.authenticator = new SingleFactorAuthenticator();
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public CreditScore getCreditScore() {
        return creditScore;
    }

    public void setFirstName(String firstName) throws NullPointerException {
        if (firstName == null) {
            throw new NullPointerException("`firstName` must not be null!");
        }

        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws NullPointerException {
        if (lastName == null) {
            throw new NullPointerException("`lastName` must not be null!");
        }

        this.lastName = lastName;
    }

    public void setCreditScore(CreditScore creditScore) throws NullPointerException {
        if (creditScore == null) {
            throw new NullPointerException("`creditScore` must not be null!");
        }

        this.creditScore = creditScore;
    }

    public Authenticator getAuthenticator() {
        return authenticator;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + this.getId() +
                ", firstName='" + this.getFirstName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", dateOfBirth=" + this.getDateOfBirth() +
                ", creditScore=" + this.getCreditScore() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
