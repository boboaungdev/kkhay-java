package com.kkhay.model;

import com.google.gson.annotations.SerializedName;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Request payload for creating a new K Khay payment invoice.
 */
public class CreateInvoiceRequest {
    @SerializedName("price_amount")
    private BigDecimal priceAmount;

    @SerializedName("price_currency")
    private String priceCurrency;

    @SerializedName("pay_network")
    private String payNetwork;

    @SerializedName("pay_token")
    private String payToken;

    @SerializedName("order_id")
    private String orderId;

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

    @SerializedName("ipn_callback_url")
    private String ipnCallbackUrl;

    @SerializedName("metadata")
    private Map<String, Object> metadata;

    public CreateInvoiceRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    public BigDecimal getPriceAmount() { return priceAmount; }
    public void setPriceAmount(BigDecimal priceAmount) { this.priceAmount = priceAmount; }

    public String getPriceCurrency() { return priceCurrency; }
    public void setPriceCurrency(String priceCurrency) { this.priceCurrency = priceCurrency; }

    public String getPayNetwork() { return payNetwork; }
    public void setPayNetwork(String payNetwork) { this.payNetwork = payNetwork; }

    public String getPayToken() { return payToken; }
    public void setPayToken(String payToken) { this.payToken = payToken; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

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

    public String getIpnCallbackUrl() { return ipnCallbackUrl; }
    public void setIpnCallbackUrl(String ipnCallbackUrl) { this.ipnCallbackUrl = ipnCallbackUrl; }

    public Map<String, Object> getMetadata() { return metadata; }
    public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }

    public static class Builder {
        private final CreateInvoiceRequest request = new CreateInvoiceRequest();

        public Builder priceAmount(BigDecimal priceAmount) {
            request.setPriceAmount(priceAmount);
            return this;
        }

        public Builder priceAmount(double priceAmount) {
            request.setPriceAmount(BigDecimal.valueOf(priceAmount));
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

        public Builder orderId(String orderId) {
            request.setOrderId(orderId);
            return this;
        }

        public Builder title(String title) {
            request.setTitle(title);
            return this;
        }

        public Builder description(String description) {
            request.setDescription(description);
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
            if (request.getPriceAmount() == null) {
                throw new IllegalArgumentException("priceAmount is required");
            }
            if (request.getPriceCurrency() == null || request.getPriceCurrency().trim().isEmpty()) {
                throw new IllegalArgumentException("priceCurrency is required");
            }
            return request;
        }
    }
}

