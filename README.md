Recommended Editor: [![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)](https://www.jetbrains.com/help/idea/installation-guide.html)

Build tool: [![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=whiteI)](https://docs.gradle.org/8.6/userguide/userguide.htmlj)

Diagrams: [Mermaid](https://mermaid.js.org/intro/)
```mermaid
---
title: Transaction System with 2FA
---
classDiagram
    class Customer {
        - id : UUID
        - firstName : String
        - lastName : String
        - dateOfBirth : Date
        - creditScore : CreditScore

        Customer(firstName : String, lastName : String, dateOfBirth : Date, creditScore : CreditScore)
        + getId() UUID
        + getFirstName() String
        + setFirstName(firstName : String) 
        + getLastName() String
        + setLastName(lastName : String) 
        + getDateOfBirth() Date
        + getCreditScore() CreditScore
        + setCreditScore(creditScore : CreditScore)
    }

    Customer "1..1" *-- "0..*" CreditScore : has

    class CreditScore {
        <<enumeration>>
        LOW
        MEDIUM
        HIGH
    }


```