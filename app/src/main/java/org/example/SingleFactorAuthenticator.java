package org.example;

import java.time.Instant;
import java.util.Date;

public class SingleFactorAuthenticator implements Authenticator {
    @Override
    public void authenticateTransaction(Transaction transaction) throws NullPointerException {
        if (transaction == null) {
           throw new NullPointerException("`transaction` must not be null!");
        }

        if (!transaction.isAuthenticated()) {
            transaction.setAuthentication(
                    "{" +
                    "authenticationmethod=SFA," +
                    "time=" + Instant.now() +
                    "}"
            );
        }
    }
}
