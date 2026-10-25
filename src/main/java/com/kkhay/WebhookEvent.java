package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;

/**
 * Represents a verified incoming IPN webhook event from K Khay Gateway.
 */
public class WebhookEvent {
    @SerializedName("event")
    private String event;

    @SerializedName(value = "invoice_id", alternate = {"invoiceId"})
    private String invoiceId;

    @SerializedName(value = "gateway_id", alternate = {"gatewayId"})
    private String gatewayId;

    @SerializedName(value = "invoice_type", alternate = {"invoiceType"})
    private String invoiceType;

    @SerializedName(value = "order_id", alternate = {"orderId"})
    private String orderId;

    @SerializedName(value = "amount", alternate = {"price_amount", "priceAmount"})
    private BigDecimal amount;

    @SerializedName(value = "currency", alternate = {"price_currency", "priceCurrency"})
    private String currency;

    @SerializedName(value = "pay_amount", alternate = {"payAmount"})
    private String payAmount;

    @SerializedName(value = "pay_token", alternate = {"payToken"})
    private String payToken;

    @SerializedName(value = "pay_network", alternate = {"payNetwork"})
    private String payNetwork;

    @SerializedName(value = "deposit_address", alternate = {"depositAddress"})
    private String depositAddress;

    @SerializedName(value = "tx_hash", alternate = {"txHash"})
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

    public String getGatewayId() { return gatewayId; }
    public void setGatewayId(String gatewayId) { this.gatewayId = gatewayId; }

    public String getInvoiceType() { return invoiceType; }
    public void setInvoiceType(String invoiceType) { this.invoiceType = invoiceType; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getPriceAmount() { return amount; }
    public void setPriceAmount(BigDecimal priceAmount) { this.amount = priceAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getPriceCurrency() { return currency; }
    public void setPriceCurrency(String priceCurrency) { this.currency = priceCurrency; }

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
                ", gatewayId='" + gatewayId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
