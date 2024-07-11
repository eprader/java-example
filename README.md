Recommended Editor: [![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/help/idea/installation-guide.html)

Build tool: [![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=whiteI)](https://docs.gradle.org/8.6/userguide/userguide.htmlj)

Diagrams: [Mermaid](https://mermaid.js.org/intro/)
```mermaid
---
title: Transaction System with 2FA
---
classDiagram
    class Customer {
        -id : UUID
        -firstName : String
        -lastName : String
        -dateOfBirth : Date
        -creditScore : CreditScore
        -authenticator : Authenticator

        Customer(firstName : String, lastName : String, dateOfBirth : Date, creditScore : CreditScore)
        +getId() UUID
        +getFirstName() String
        +setFirstName(firstName : String) 
        +getLastName() String
        +setLastName(lastName : String) 
        +getDateOfBirth() Date
        +getCreditScore() CreditScore
        +setCreditScore(creditScore : CreditScore)
        +getAuthenticator() Authenticator
        +toString() String
        +equals(o : Object) boolean
        +hashCode() final int
    }

    Customer "1..1" *-- "0..*" CreditScore : has
    Customer "1..1" *-- "1..1" Authenticator : uses

    class CreditScore {
        <<enumeration>>
        LOW
        MEDIUM
        HIGH
    }

    class Authenticator {
        <<interface>> 
        authenticateTransaction(transaction: Transaction)
    }

    Authenticator "0..*" --> "0..*" Transaction : authenticates

    class Transaction {
        -id : UUID
        - source : BankAccount
        -target : BankAccount
        -transferAmount : BigInteger
        -authentication : String 

        +getId() UUID
        +getSource() BankAccount
        +getTarget() BankAccount
        +getTransferAmount() BigInteger
        +isAuthenticated() Boolean
        +getAuthentication() String
        +setAuthentication(authentication : String)
        +toString() String
        +equals(o : Object) boolean
        +hashCode() final int
        +compareTo(T o) int
    }

    note for Transaction "`authentication` field is `null` if not authenticated.
    The authentication string contains the current datetime as well as the authentication method"

    note for Transaction "implements java.lang.Comparable interface and satisfies its contract"

    class BankAccount {
        -IBAN : String
        -owner : Customer
        -balanceInCent : BigInteger

        +getIBAN() String
        +getOwner() Customer
        +setOwner(owner : Customer)
        +getBalanceInCent() BigInteger
        +setBalanceInCent(balance : BigInteger)
        +equals(o : Object) boolean
        +hashCode() final int
    }
```