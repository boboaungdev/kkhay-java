package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Request payload for creating a new K Khay payment invoice.
 */
public class CreateInvoiceRequest {
    @SerializedName(value = "amount", alternate = {"priceAmount", "price_amount"})
    private BigDecimal amount;

    @SerializedName(value = "currency", alternate = {"priceCurrency", "price_currency"})
    private String currency;

    @SerializedName(value = "payNetwork", alternate = {"pay_network"})
    private String payNetwork;

    @SerializedName(value = "payToken", alternate = {"pay_token"})
    private String payToken;

    @SerializedName(value = "settlementAsset", alternate = {"settlement_asset"})
    private String settlementAsset;

    @SerializedName("type")
    private String type;

    @SerializedName(value = "orderId", alternate = {"order_id"})
    private String orderId;

    @SerializedName("title")
    private String title;

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

    @SerializedName("metadata")
    private Map<String, Object> metadata;

    public CreateInvoiceRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getPriceAmount() { return amount; }
    public void setPriceAmount(BigDecimal priceAmount) { this.amount = priceAmount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getPriceCurrency() { return currency; }
    public void setPriceCurrency(String priceCurrency) { this.currency = priceCurrency; }

    public String getPayNetwork() { return payNetwork; }
    public void setPayNetwork(String payNetwork) { this.payNetwork = payNetwork; }

    public String getPayToken() { return payToken; }
    public void setPayToken(String payToken) { this.payToken = payToken; }

    public String getSettlementAsset() { return settlementAsset; }
    public void setSettlementAsset(String settlementAsset) { this.settlementAsset = settlementAsset; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

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

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }

    public static class Builder {
        private final CreateInvoiceRequest request = new CreateInvoiceRequest();

        public Builder amount(BigDecimal amount) {
            request.setAmount(amount);
            return this;
        }

        public Builder amount(double amount) {
            request.setAmount(BigDecimal.valueOf(amount));
            return this;
        }

        public Builder priceAmount(BigDecimal priceAmount) {
            request.setPriceAmount(priceAmount);
            return this;
        }

        public Builder priceAmount(double priceAmount) {
            request.setPriceAmount(BigDecimal.valueOf(priceAmount));
            return this;
        }

        public Builder currency(String currency) {
            request.setCurrency(currency);
            return this;
        }

        public Builder priceCurrency(String priceCurrency) {
            request.setPriceCurrency(priceCurrency);
            return this;
        }

        public Builder payNetwork(String payNetwork) {
            request.setPayNetwork(payNetwork);
            return this;
        }

        public Builder payToken(String payToken) {
            request.setPayToken(payToken);
            return this;
        }

        public Builder settlementAsset(String settlementAsset) {
            request.setSettlementAsset(settlementAsset);
            return this;
        }

        public Builder type(String type) {
            request.setType(type);
            return this;
        }

        public Builder orderId(String orderId) {
            request.setOrderId(orderId);
            return this;
        }

        public Builder title(String title) {
            request.setTitle(title);
            return this;
        }

        public Builder customerName(String customerName) {
            request.setCustomerName(customerName);
            return this;
        }

        public Builder customerEmail(String customerEmail) {
            request.setCustomerEmail(customerEmail);
            return this;
        }

        public Builder redirectUrl(String redirectUrl) {
            request.setRedirectUrl(redirectUrl);
            return this;
        }

        public Builder cancelUrl(String cancelUrl) {
            request.setCancelUrl(cancelUrl);
            return this;
        }

        public Builder ipnCallbackUrl(String ipnCallbackUrl) {
            request.setIpnCallbackUrl(ipnCallbackUrl);
            return this;
        }

        public Builder metadata(Map<String, Object> metadata) {
            request.setMetadata(metadata);
            return this;
        }

        public CreateInvoiceRequest build() {
            BigDecimal amt = request.getAmount();
            if (amt == null || amt.compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Amount (or priceAmount) must be greater than 0");
            }
            if (request.getPayNetwork() == null || request.getPayNetwork().trim().isEmpty()) {
                throw new IllegalArgumentException("payNetwork is required (e.g. 'bsc', 'polygon', 'solana')");
            }
            if (request.getPayToken() == null || request.getPayToken().trim().isEmpty()) {
                throw new IllegalArgumentException("payToken is required (e.g. 'USDT', 'USDC', 'BTC', 'ETH')");
            }
            if (request.getCurrency() == null || request.getCurrency().trim().isEmpty()) {
                request.setCurrency("USD");
            }
            if (request.getType() == null || request.getType().trim().isEmpty()) {
                request.setType(InvoiceType.PAYMENT);
            }
            return request;
        }
    }
}
