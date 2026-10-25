package com.kkhay;

/**
 * Webhook event types dispatched by K Khay Gateway Instant Payment Notifications (IPN).
 */
public final class WebhookEventType {
    public static final String PAYMENT_PAID = "payment.paid";
    public static final String PAYMENT_FINISHED = "payment.finished";
    public static final String PAYMENT_CONFIRMING = "payment.confirming";
    public static final String PAYMENT_FAILED = "payment.failed";
    public static final String INVOICE_CANCELED = "invoice.canceled";

    private WebhookEventType() {}
}

