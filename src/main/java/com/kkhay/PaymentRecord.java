package com.kkhay;

import com.google.gson.annotations.SerializedName;

/**
 * Details on-chain transaction hashes, confirmations, and settlement status.
 */
public class PaymentRecord {
    @SerializedName("id")
    private String id;

    @SerializedName("invoiceId")
    private String invoiceId;

    @SerializedName("txHash")
    private String txHash;

    @SerializedName("fromAddress")
    private String fromAddress;

    @SerializedName("amountReceived")
    private String amountReceived;

    @SerializedName("confirmations")
    private int confirmations;

    @SerializedName("status")
    private String status;

    @SerializedName("forwardedTxHash")
    private String forwardedTxHash;

    @SerializedName("createdAt")
    private String createdAt;

    public PaymentRecord() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getTxHash() { return txHash; }
    public void setTxHash(String txHash) { this.txHash = txHash; }

    public String getFromAddress() { return fromAddress; }
    public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }

    public String getAmountReceived() { return amountReceived; }
    public void setAmountReceived(String amountReceived) { this.amountReceived = amountReceived; }

    public int getConfirmations() { return confirmations; }
    public void setConfirmations(int confirmations) { this.confirmations = confirmations; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getForwardedTxHash() { return forwardedTxHash; }
    public void setForwardedTxHash(String forwardedTxHash) { this.forwardedTxHash = forwardedTxHash; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}

