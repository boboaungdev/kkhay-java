package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Represents a K Khay payment invoice.
 */
public class Invoice {
    @SerializedName("id")
    private String id;

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

    @SerializedName("status")
    private String status;

    @SerializedName("hosted_url")
    private String hostedUrl;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("customer_email")
    private String customerEmail;

    @SerializedName("redirect_url")
    private String redirectUrl;

    @SerializedName("cancel_url")
    private String cancelUrl;

    @SerializedName("expires_at")
    private String expiresAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("metadata")
    private Map<String, Object> metadata;

    public Invoice() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getHostedUrl() { return hostedUrl; }
    public void setHostedUrl(String hostedUrl) { this.hostedUrl = hostedUrl; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getRedirectUrl() { return redirectUrl; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }

    public String getCancelUrl() { return cancelUrl; }
    public void setCancelUrl(String cancelUrl) { this.cancelUrl = cancelUrl; }

    public String getExpiresAt() { return expiresAt; }
    public void setExpiresAt(String expiresAt) { this.expiresAt = expiresAt; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", priceAmount=" + priceAmount +
                ", priceCurrency='" + priceCurrency + '\'' +
                ", status='" + status + '\'' +
                ", hostedUrl='" + hostedUrl + '\'' +
                '}';
    }
}

