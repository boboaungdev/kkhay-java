package com.kkhay;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class KkhayClientTest {

    @Test
    public void testClientInitialization() {
        KkhayClient client = new KkhayClient("kkhay_live_test_123");
        assertEquals("kkhay_live_test_123", client.getApiKey());
        assertEquals("https://api.kkhay.com", client.getBaseUrl());
        assertEquals(Duration.ofSeconds(30), client.getTimeout());

        Kkhay kkhay = new Kkhay("kkhay_live_test_123");
        assertEquals("kkhay_live_test_123", kkhay.getApiKey());
    }

    @Test
    public void testClientBuilder() {
        KkhayClient client = KkhayClient.builder()
                .apiKey("kkhay_test_key")
                .baseUrl("https://custom.kkhay.com/api")
                .timeout(Duration.ofSeconds(15))
                .build();

        assertEquals("kkhay_test_key", client.getApiKey());
        assertEquals("https://custom.kkhay.com/api", client.getBaseUrl());
        assertEquals(Duration.ofSeconds(15), client.getTimeout());
    }

    @Test
    public void testClientThrowsOnMissingApiKey() {
        assertThrows(IllegalArgumentException.class, () -> new KkhayClient(null));
        assertThrows(IllegalArgumentException.class, () -> new KkhayClient(""));
        assertThrows(IllegalArgumentException.class, () -> new KkhayClient("   "));
    }

    @Test
    public void testCreateInvoiceRequestBuilder() {
        CreateInvoiceRequest request = CreateInvoiceRequest.builder()
                .amount(new BigDecimal("49.99"))
                .currency("USD")
                .payNetwork("bsc")
                .payToken("USDT")
                .orderId("ORD-101")
                .title("Pro Subscription")
                .customerName("Alice Smith")
                .customerEmail("user@example.com")
                .redirectUrl("https://example.com/success")
                .cancelUrl("https://example.com/cancel")
                .build();

        assertEquals(new BigDecimal("49.99"), request.getAmount());
        assertEquals(new BigDecimal("49.99"), request.getPriceAmount());
        assertEquals("USD", request.getCurrency());
        assertEquals("USD", request.getPriceCurrency());
        assertEquals("bsc", request.getPayNetwork());
        assertEquals("USDT", request.getPayToken());
        assertEquals("ORD-101", request.getOrderId());
        assertEquals("Pro Subscription", request.getTitle());
        assertEquals("Alice Smith", request.getCustomerName());
    }

    @Test
    public void testCreateInvoiceRequestValidation() {
        // Missing amount
        assertThrows(IllegalArgumentException.class, () -> {
            CreateInvoiceRequest.builder()
                    .payNetwork("bsc")
                    .payToken("USDT")
                    .build();
        });

        // Missing network
        assertThrows(IllegalArgumentException.class, () -> {
            CreateInvoiceRequest.builder()
                    .amount(10.0)
                    .payToken("USDT")
                    .build();
        });

        // Missing token
        assertThrows(IllegalArgumentException.class, () -> {
            CreateInvoiceRequest.builder()
                    .amount(10.0)
                    .payNetwork("bsc")
                    .build();
        });
    }

    @Test
    public void testListInvoicesRequestBuilder() {
        ListInvoicesRequest request = ListInvoicesRequest.builder()
                .page(2)
                .limit(20)
                .status(InvoiceStatus.PAID)
                .type(InvoiceType.PAYMENT)
                .search("ORD-101")
                .build();

        Map<String, String> queryParams = request.toQueryParams();
        assertEquals("2", queryParams.get("page"));
        assertEquals("20", queryParams.get("limit"));
        assertEquals("PAID", queryParams.get("status"));
        assertEquals("PAYMENT", queryParams.get("type"));
        assertEquals("ORD-101", queryParams.get("search"));
    }

    @Test
    public void testInvoiceConstants() {
        assertEquals("WAITING", InvoiceStatus.WAITING);
        assertEquals("PAID", InvoiceStatus.PAID);
        assertEquals("EXPIRED", InvoiceStatus.EXPIRED);
        assertEquals("CANCELED", InvoiceStatus.CANCELED);

        assertEquals("PAYMENT", InvoiceType.PAYMENT);
        assertEquals("DONATION", InvoiceType.DONATION);
        assertEquals("SUBSCRIPTION", InvoiceType.SUBSCRIPTION);
    }
}
