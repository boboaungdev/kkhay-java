package com.kkhay;

/**
 * Exception thrown when a K Khay webhook IPN signature cannot be verified.
 */
public class SignatureVerificationException extends RuntimeException {
    public SignatureVerificationException(String message) {
        super(message);
    }

    public SignatureVerificationException() {
        super("Invalid K Khay webhook signature: Request rejected.");
    }
}

