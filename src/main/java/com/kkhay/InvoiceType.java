package com.kkhay;

/**
 * Invoice type constants matching the PostgreSQL GatewayInvoiceType enum.
 */
public final class InvoiceType {
    public static final String PAYMENT = "PAYMENT";
    public static final String DONATION = "DONATION";
    public static final String SUBSCRIPTION = "SUBSCRIPTION";

    private InvoiceType() {}
}

