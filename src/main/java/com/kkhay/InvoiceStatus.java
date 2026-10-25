package com.kkhay;

/**
 * Invoice status constants matching the PostgreSQL GatewayInvoiceStatus enum.
 */
public final class InvoiceStatus {
    public static final String WAITING = "WAITING";
    public static final String PAID = "PAID";
    public static final String EXPIRED = "EXPIRED";
    public static final String CANCELED = "CANCELED";

    private InvoiceStatus() {}
}

