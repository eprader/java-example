package org.example;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        BankingSystem system = new BankingSystem();

        Customer customer1 = new Customer("John", "Doe", Date.from(Instant.now()), CreditScore.MEDIUM);
        BankAccount account1 = new BankAccount(customer1);
        system.addBankAccount(account1);

        Customer customer2 = new Customer("Jane", "Doe", Date.from(Instant.now()), CreditScore.LOW);
        BankAccount account2 = new BankAccount(customer2);
        system.addBankAccount(account2);

        system.transfer(BigInteger.valueOf(100_000), account1, account2);

        System.out.println(system);


    }
}
