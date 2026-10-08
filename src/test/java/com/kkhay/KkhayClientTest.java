package com.kkhay;

import com.kkhay.model.CreateInvoiceRequest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class KkhayClientTest {

    @Test
    public void testClientInitialization() {
        KkhayClient client = new KkhayClient("kkhay_live_test_123");
        assertEquals("kkhay_live_test_123", client.getApiKey());
        assertEquals("https://api.kkhay.com", client.getBaseUrl());
        assertEquals(Duration.ofSeconds(30), client.getTimeout());
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
                .priceAmount(new BigDecimal("49.99"))
                .priceCurrency("USD")
                .payNetwork("bsc")
                .payToken("USDT")
                .orderId("ORD-101")
                .title("Pro Subscription")
                .customerEmail("user@example.com")
                .redirectUrl("https://example.com/success")
                .cancelUrl("https://example.com/cancel")
                .build();

        assertEquals(new BigDecimal("49.99"), request.getPriceAmount());
        assertEquals("USD", request.getPriceCurrency());
        assertEquals("bsc", request.getPayNetwork());
        assertEquals("USDT", request.getPayToken());
        assertEquals("ORD-101", request.getOrderId());
        assertEquals("Pro Subscription", request.getTitle());
    }

    @Test
    public void testCreateInvoiceRequestValidation() {
        assertThrows(IllegalArgumentException.class, () -> {
            CreateInvoiceRequest.builder()
                    .priceCurrency("USD")
                    .build();
        });

        assertThrows(IllegalArgumentException.class, () -> {
            CreateInvoiceRequest.builder()
                    .priceAmount(10.0)
                    .build();
        });
    }
}

