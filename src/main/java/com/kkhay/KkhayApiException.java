package com.kkhay;

/**
 * Exception thrown when the K Khay API returns an error response.
 */
public class KkhayApiException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;
    private final Object details;

    public KkhayApiException(int statusCode, String message, String errorCode, Object details) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.details = details;
    }

    public KkhayApiException(int statusCode, String message) {
        this(statusCode, message, null, null);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Object getDetails() {
        return details;
    }

    @Override
    public String toString() {
        if (errorCode != null && !errorCode.isEmpty()) {
            return "KkhayApiException [HTTP " + statusCode + " - " + errorCode + "]: " + getMessage();
        }
        return "KkhayApiException [HTTP " + statusCode + "]: " + getMessage();
    }
}

