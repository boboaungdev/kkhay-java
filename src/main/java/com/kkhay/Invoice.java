package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Represents a K Khay sovereign crypto payment invoice record.
 */
public class Invoice {
    @SerializedName("id")
    private String id;

    @SerializedName(value = "gatewayId", alternate = {"gateway_id"})
    private String gatewayId;

    @SerializedName("type")
    private String type;

    @SerializedName(value = "orderId", alternate = {"order_id"})
    private String orderId;

    @SerializedName("title")
    private String title;

    @SerializedName(value = "amount", alternate = {"priceAmount", "price_amount"})
    private BigDecimal amount;

    @SerializedName(value = "currency", alternate = {"priceCurrency", "price_currency"})
    private String currency;

    @SerializedName(value = "settlementAsset", alternate = {"settlement_asset"})
    private String settlementAsset;

    @SerializedName(value = "payAmount", alternate = {"pay_amount"})
    private String payAmount;

    @SerializedName(value = "payToken", alternate = {"pay_token"})
    private String payToken;

    @SerializedName(value = "payNetwork", alternate = {"pay_network"})
    private String payNetwork;

    @SerializedName(value = "lockedRate", alternate = {"locked_rate"})
    private String lockedRate;

    @SerializedName(value = "depositAddress", alternate = {"deposit_address"})
    private String depositAddress;

    @SerializedName(value = "derivationIndex", alternate = {"derivation_index"})
    private Integer derivationIndex;

    @SerializedName(value = "payoutAddress", alternate = {"payout_address"})
    private String payoutAddress;

    @SerializedName("status")
    private String status;

    @SerializedName(value = "feeAmount", alternate = {"fee_amount"})
    private String feeAmount;

    @SerializedName(value = "netAmount", alternate = {"net_amount"})
    private String netAmount;

    @SerializedName(value = "customerName", alternate = {"customer_name"})
    private String customerName;

    @SerializedName(value = "customerEmail", alternate = {"customer_email"})
    private String customerEmail;

    @SerializedName(value = "redirectUrl", alternate = {"redirect_url"})
    private String redirectUrl;

    @SerializedName(value = "cancelUrl", alternate = {"cancel_url"})
    private String cancelUrl;

    @SerializedName(value = "ipnCallbackUrl", alternate = {"ipn_callback_url"})
    private String ipnCallbackUrl;

    @SerializedName(value = "paymentMethod", alternate = {"payment_method"})
    private String paymentMethod;

    @SerializedName(value = "settlementMode", alternate = {"settlement_mode"})
    private String settlementMode;

    @SerializedName(value = "hostedUrl", alternate = {"hosted_url"})
    private String hostedUrl;

    @SerializedName("metadata")
    private Map<String, Object> metadata;

    @SerializedName(value = "expiresAt", alternate = {"expires_at"})
    private String expiresAt;

    @SerializedName(value = "createdAt", alternate = {"created_at"})
    private String createdAt;

    @SerializedName(value = "updatedAt", alternate = {"updated_at"})
    private String updatedAt;

    public Invoice() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getGatewayId() { return gatewayId; }
    public void setGatewayId(String gatewayId) { this.gatewayId = gatewayId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getPriceAmount() { return amount; }
    public void setPriceAmount(BigDecimal priceAmount) { this.amount = priceAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getPriceCurrency() { return currency; }
    public void setPriceCurrency(String priceCurrency) { this.currency = priceCurrency; }

    public String getSettlementAsset() { return settlementAsset; }
    public void setSettlementAsset(String settlementAsset) { this.settlementAsset = settlementAsset; }

    public String getPayAmount() { return payAmount; }
    public void setPayAmount(String payAmount) { this.payAmount = payAmount; }

    public String getPayToken() { return payToken; }
    public void setPayToken(String payToken) { this.payToken = payToken; }

    public String getPayNetwork() { return payNetwork; }
    public void setPayNetwork(String payNetwork) { this.payNetwork = payNetwork; }

    public String getLockedRate() { return lockedRate; }
    public void setLockedRate(String lockedRate) { this.lockedRate = lockedRate; }

    public String getDepositAddress() { return depositAddress; }
    public void setDepositAddress(String depositAddress) { this.depositAddress = depositAddress; }

    public Integer getDerivationIndex() { return derivationIndex; }
    public void setDerivationIndex(Integer derivationIndex) { this.derivationIndex = derivationIndex; }

    public String getPayoutAddress() { return payoutAddress; }
    public void setPayoutAddress(String payoutAddress) { this.payoutAddress = payoutAddress; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFeeAmount() { return feeAmount; }
    public void setFeeAmount(String feeAmount) { this.feeAmount = feeAmount; }

    public String getNetAmount() { return netAmount; }
    public void setNetAmount(String netAmount) { this.netAmount = netAmount; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public String getRedirectUrl() { return redirectUrl; }
    public void setRedirectUrl(String redirectUrl) { this.redirectUrl = redirectUrl; }

    public String getCancelUrl() { return cancelUrl; }
    public void setCancelUrl(String cancelUrl) { this.cancelUrl = cancelUrl; }

    public String getIpnCallbackUrl() { return ipnCallbackUrl; }
    public void setIpnCallbackUrl(String ipnCallbackUrl) { this.ipnCallbackUrl = ipnCallbackUrl; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public String getSettlementMode() { return settlementMode; }
    public void setSettlementMode(String settlementMode) { this.settlementMode = settlementMode; }

    public String getHostedUrl() { return hostedUrl; }
    public void setHostedUrl(String hostedUrl) { this.hostedUrl = hostedUrl; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }

    public String getExpiresAt() { return expiresAt; }
    public void setExpiresAt(String expiresAt) { this.expiresAt = expiresAt; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }

    public String getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(String updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Invoice{" +
                "id='" + id + '\'' +
                ", gatewayId='" + gatewayId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", status='" + status + '\'' +
                ", hostedUrl='" + hostedUrl + '\'' +
                '}';
    }
}
