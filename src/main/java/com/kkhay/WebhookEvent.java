package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

/**
 * Represents a verified incoming IPN webhook event from K Khay.
 */
public class WebhookEvent {
    @SerializedName("event")
    private String event;

    @SerializedName("invoice_id")
    private String invoiceId;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("price_amount")
    private BigDecimal priceAmount;

    @SerializedName("price_currency")
    private String priceCurrency;

    @SerializedName("pay_amount")
    private String payAmount;

    @SerializedName("pay_token")
    private String payToken;

    @SerializedName("pay_network")
    private String payNetwork;

    @SerializedName("deposit_address")
    private String depositAddress;

    @SerializedName("tx_hash")
    private String txHash;

    @SerializedName("status")
    private String status;

    @SerializedName("timestamp")
    private String timestamp;

    public WebhookEvent() {}

    public String getEvent() { return event; }
    public void setEvent(String event) { this.event = event; }

    public String getInvoiceId() { return invoiceId; }
    public void setInvoiceId(String invoiceId) { this.invoiceId = invoiceId; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public BigDecimal getPriceAmount() { return priceAmount; }
    public void setPriceAmount(BigDecimal priceAmount) { this.priceAmount = priceAmount; }

    public String getPriceCurrency() { return priceCurrency; }
    public void setPriceCurrency(String priceCurrency) { this.priceCurrency = priceCurrency; }

    public String getPayAmount() { return payAmount; }
    public void setPayAmount(String payAmount) { this.payAmount = payAmount; }

    public String getPayToken() { return payToken; }
    public void setPayToken(String payToken) { this.payToken = payToken; }

    public String getPayNetwork() { return payNetwork; }
    public void setPayNetwork(String payNetwork) { this.payNetwork = payNetwork; }

    public String getDepositAddress() { return depositAddress; }
    public void setDepositAddress(String depositAddress) { this.depositAddress = depositAddress; }

    public String getTxHash() { return txHash; }
    public void setTxHash(String txHash) { this.txHash = txHash; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return "WebhookEvent{" +
                "event='" + event + '\'' +
                ", invoiceId='" + invoiceId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

