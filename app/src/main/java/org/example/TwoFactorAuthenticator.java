package org.example;

import java.time.Instant;
import java.util.Scanner;

public class TwoFactorAuthenticator implements Authenticator {
    private static final int MAX_ATTEMPTS = 3;
    private final String password;
    public TwoFactorAuthenticator(String password) throws NullPointerException {
        if (password == null) {
           throw new NullPointerException("`password` must not be null!");
        }

        this.password = password;
    }
    @Override
    public void authenticateTransaction(Transaction transaction) throws NullPointerException {
        if (transaction == null) {
            throw new NullPointerException("`transaction` must not be null!");
        }

        Scanner stdIn = new Scanner(System.in);

        int attempts = 0;
        do {
            System.out.print("Please enter your password: ");
            String input = stdIn.nextLine();

            if (input.equals(password)) {
                transaction.setAuthentication(
                        "{" +
                        "authenticationmethod=2FA," +
                        "time=" + Instant.now() +
                        "}"
                );
                break;
            }

            attempts++;
            System.out.println("Invalid. " + (MAX_ATTEMPTS - attempts) + " attempts left.");
        } while (attempts <= 3);
    }
}
