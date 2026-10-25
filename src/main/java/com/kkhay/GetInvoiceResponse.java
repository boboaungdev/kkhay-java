package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Response payload containing the requested invoice and transaction records.
 */
public class GetInvoiceResponse {
    @SerializedName("ok")
    private boolean ok;

    @SerializedName("invoice")
    private Invoice invoice;

    @SerializedName("payments")
    private List<PaymentRecord> payments = new ArrayList<>();

    public GetInvoiceResponse() {}

    public boolean isOk() { return ok; }
    public void setOk(boolean ok) { this.ok = ok; }

    public Invoice getInvoice() { return invoice; }
    public void setInvoice(Invoice invoice) { this.invoice = invoice; }

    public List<PaymentRecord> getPayments() { return payments; }
    public void setPayments(List<PaymentRecord> payments) { this.payments = payments; }
}

