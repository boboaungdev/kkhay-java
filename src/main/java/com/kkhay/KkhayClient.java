package com.kkhay;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Official Java &amp; Kotlin client for the K Khay Sovereign Crypto Payment Gateway.
 */
public class KkhayClient {
    public static final String DEFAULT_BASE_URL = "https://api.kkhay.com";
    public static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(30);
    private static final String USER_AGENT = "kkhay-java/1.0.0";
    private static final Gson GSON = new Gson();

    private final String apiKey;
    private final String baseUrl;
    private final Duration timeout;
    private final HttpClient httpClient;

    public KkhayClient(String apiKey) {
        this(apiKey, DEFAULT_BASE_URL, DEFAULT_TIMEOUT);
    }

    public KkhayClient(String apiKey, String baseUrl) {
        this(apiKey, baseUrl, DEFAULT_TIMEOUT);
    }

    public KkhayClient(String apiKey, String baseUrl, Duration timeout) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("KkhayClient: apiKey is required");
        }
        this.apiKey = apiKey.trim();
        this.baseUrl = (baseUrl != null && !baseUrl.trim().isEmpty())
                ? baseUrl.trim().replaceAll("/+$", "")
                : DEFAULT_BASE_URL;
        this.timeout = timeout != null ? timeout : DEFAULT_TIMEOUT;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    KkhayClient(String apiKey, String baseUrl, Duration timeout, HttpClient httpClient) {
        this.apiKey = apiKey.trim();
        this.baseUrl = baseUrl.trim().replaceAll("/+$", "");
        this.timeout = timeout != null ? timeout : DEFAULT_TIMEOUT;
        this.httpClient = httpClient;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getApiKey() { return apiKey; }
    public String getBaseUrl() { return baseUrl; }
    public Duration getTimeout() { return timeout; }

    /**
     * Creates a new crypto payment invoice.
     *
     * @param request The invoice creation parameters.
     * @return The created {@link Invoice}.
     * @throws KkhayApiException If the API returns an error.
     */
    public Invoice createInvoice(CreateInvoiceRequest request) {
        return createInvoiceAsync(request).join();
    }

    /**
     * Asynchronously creates a new crypto payment invoice.
     *
     * @param request The invoice creation parameters.
     * @return A {@link CompletableFuture} returning the created {@link Invoice}.
     */
    public CompletableFuture<Invoice> createInvoiceAsync(CreateInvoiceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("CreateInvoiceRequest cannot be null");
        }

        String jsonBody = GSON.toJson(request);
        HttpRequest httpRequest = newRequestBuilder("/v1/merchant/invoices")
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody, StandardCharsets.UTF_8))
                .build();

        return sendAsync(httpRequest, Invoice.class);
    }

    /**
     * Retrieves an invoice by its unique ID.
     *
     * @param invoiceId The unique invoice identifier.
     * @return The {@link Invoice}.
     * @throws KkhayApiException If the invoice does not exist or the API returns an error.
     */
    public Invoice getInvoice(String invoiceId) {
        return getInvoiceAsync(invoiceId).join();
    }

    /**
     * Asynchronously retrieves an invoice by its unique ID.
     *
     * @param invoiceId The unique invoice identifier.
     * @return A {@link CompletableFuture} returning the {@link Invoice}.
     */
    public CompletableFuture<Invoice> getInvoiceAsync(String invoiceId) {
        if (invoiceId == null || invoiceId.trim().isEmpty()) {
            throw new IllegalArgumentException("invoiceId is required");
        }

        String path = "/v1/merchant/invoices/" + URLEncoder.encode(invoiceId.trim(), StandardCharsets.UTF_8);
        HttpRequest httpRequest = newRequestBuilder(path)
                .GET()
                .build();

        return sendAsync(httpRequest, Invoice.class);
    }

    /**
     * Lists merchant invoices with optional filters and pagination.
     *
     * @param request The query filter parameters.
     * @return A {@link ListInvoicesResponse}.
     */
    public ListInvoicesResponse listInvoices(ListInvoicesRequest request) {
        return listInvoicesAsync(request).join();
    }

    /**
     * Convenience method to list invoices with default parameters.
     *
     * @return A {@link ListInvoicesResponse}.
     */
    public ListInvoicesResponse listInvoices() {
        return listInvoices(new ListInvoicesRequest());
    }

    /**
     * Asynchronously lists merchant invoices with optional filters and pagination.
     *
     * @param request The query filter parameters.
     * @return A {@link CompletableFuture} returning the {@link ListInvoicesResponse}.
     */
    public CompletableFuture<ListInvoicesResponse> listInvoicesAsync(ListInvoicesRequest request) {
        StringBuilder pathBuilder = new StringBuilder("/v1/merchant/invoices");

        if (request != null) {
            Map<String, String> queryParams = request.toQueryParams();
            if (!queryParams.isEmpty()) {
                pathBuilder.append("?");
                boolean first = true;
                for (Map.Entry<String, String> entry : queryParams.entrySet()) {
                    if (!first) pathBuilder.append("&");
                    pathBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
                    first = false;
                }
            }
        }

        HttpRequest httpRequest = newRequestBuilder(pathBuilder.toString())
                .GET()
                .build();

        return sendAsync(httpRequest, ListInvoicesResponse.class);
    }

    /**
     * Convenience method to asynchronously list invoices with default parameters.
     *
     * @return A {@link CompletableFuture} returning the {@link ListInvoicesResponse}.
     */
    public CompletableFuture<ListInvoicesResponse> listInvoicesAsync() {
        return listInvoicesAsync(new ListInvoicesRequest());
    }

    private HttpRequest.Builder newRequestBuilder(String path) {
        String fullUrl = buildUrl(path);
        return HttpRequest.newBuilder()
                .uri(URI.create(fullUrl))
                .timeout(timeout)
                .header("x-api-key", apiKey)
                .header("Accept", "application/json")
                .header("User-Agent", USER_AGENT);
    }

    private String buildUrl(String path) {
        String cleanPath = path.startsWith("/") ? path : "/" + path;
        if (baseUrl.endsWith("/api") || baseUrl.contains("api.")) {
            return baseUrl + cleanPath;
        } else {
            return baseUrl + "/api" + cleanPath;
        }
    }

    private <T> CompletableFuture<T> sendAsync(HttpRequest httpRequest, Class<T> responseType) {
        return httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenApply(response -> {
                    int statusCode = response.statusCode();
                    String body = response.body();

                    if (statusCode >= 400) {
                        handleError(statusCode, body);
                    }

                    try {
                        return GSON.fromJson(body, responseType);
                    } catch (Exception e) {
                        throw new KkhayApiException(statusCode, "Failed to parse API response: " + e.getMessage());
                    }
                });
    }

    private void handleError(int statusCode, String body) {
        String message = "HTTP " + statusCode + " error";
        String errorCode = null;
        Object details = null;

        if (body != null && !body.trim().isEmpty()) {
            try {
                JsonElement element = JsonParser.parseString(body);
                if (element.isJsonObject()) {
                    JsonObject obj = element.getAsJsonObject();
                    if (obj.has("message")) {
                        message = obj.get("message").getAsString();
                    } else if (obj.has("error")) {
                        message = obj.get("error").getAsString();
                    }
                    if (obj.has("code")) {
                        errorCode = obj.get("code").getAsString();
                    }
                    if (obj.has("details")) {
                        details = obj.get("details");
                    }
                }
            } catch (Exception ignored) {
                message = body;
            }
        }

        throw new KkhayApiException(statusCode, message, errorCode, details);
    }

    public static class Builder {
        private String apiKey;
        private String baseUrl = DEFAULT_BASE_URL;
        private Duration timeout = DEFAULT_TIMEOUT;
        private HttpClient httpClient;

        public Builder apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public Builder httpClient(HttpClient httpClient) {
            this.httpClient = httpClient;
            return this;
        }

        public KkhayClient build() {
            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalArgumentException("apiKey is required");
            }
            if (httpClient != null) {
                return new KkhayClient(apiKey, baseUrl, timeout, httpClient);
            }
            return new KkhayClient(apiKey, baseUrl, timeout);
        }
    }
}

