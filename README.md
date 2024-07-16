Recommended Editor: [![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/help/idea/installation-guide.html)

Build tool: [![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=whiteI)](https://docs.gradle.org/current/userguide/userguide.html)

Diagrams: [Mermaid](https://mermaid.js.org/intro/)
```mermaid
---
title: Transaction System with 2FA
---

classDiagram
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
    BankAccount "1..1" -- "1..*" Customer : own
    BankAccount -- CreditScore : decide credit limit

    class BankingSystem {
        -managedAccounts : List~BankAccount~
        -transactionHistory : List~Transaction~

        +transfer(amount : BigInteger, source : BankAccount, target : BankAccount)
        +addBankAccount(account : BankAccount)
        +removeBankAccount(account: BankAccount)
        +getBankAccounts() List~BankAccount~
    }
    BankingSystem "0..*" --> "1..1" BankAccount : manage

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
    Customer "1..1" *-- "1..1" Authenticator : use
    Customer

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
    Authenticator "0..*" --> "0..*" Transaction : authenticate
    Authenticator <|-- SingleFactorAuthenticator
    Authenticator <|-- TwoFactorAuthenticator 

    class SingleFactorAuthenticator

    note for SingleFactorAuthenticator "Implements a no-op as we assume the Customer is
    logged in to perform  a transaction."

    class TwoFactorAuthenticator
    note for TwoFactorAuthenticator "Requires input from `stdin` to authenticate."

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
    Transaction "1..1" --o "0..*" BankingSystem : log

    note for Transaction "`authentication` field is `null` if not authenticated.
    The authentication string contains the current 
    datetime as well as the authentication method"

    note for Transaction "implements java.lang.Comparable interface and satisfies its contract"
