package com.kkhay;

import com.google.gson.Gson;
import com.kkhay.exception.SignatureVerificationException;
import com.kkhay.model.WebhookEvent;
import org.junit.jupiter.api.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class WebhookTest {
    private static final Gson GSON = new Gson();

    private String computeHmacHex(String payload, String secret) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        mac.init(secretKey);
        byte[] bytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Test
    public void testVerifySignatureSuccess() throws Exception {
        String secret = "whsec_test_secret_123";
        Map<String, Object> data = new HashMap<>();
        data.put("event", "payment.finished");
        data.put("invoice_id", "inv_123");
        data.put("pay_amount", "50.00");
        data.put("pay_token", "USDT");
        String payload = GSON.toJson(data);

        String signature = computeHmacHex(payload, secret);

        assertTrue(Webhook.verifySignature(payload, signature, secret));
        assertTrue(Webhook.verifySignature(payload, signature.toUpperCase(), secret)); // Case insensitive
    }

    @Test
    public void testVerifySignatureTamperedPayload() throws Exception {
        String secret = "whsec_test_secret_123";
        String payload = "{\"event\":\"payment.finished\"}";
        String signature = computeHmacHex(payload, secret);

        assertFalse(Webhook.verifySignature(payload + "tampered", signature, secret));
    }

    @Test
    public void testVerifySignatureWrongSecret() throws Exception {
        String secret = "whsec_test_secret_123";
        String payload = "{\"event\":\"payment.finished\"}";
        String signature = computeHmacHex(payload, secret);

        assertFalse(Webhook.verifySignature(payload, signature, "wrong_secret"));
    }

    @Test
    public void testVerifySignatureNullOrEmpty() {
        assertFalse(Webhook.verifySignature(null, "sig", "secret"));
        assertFalse(Webhook.verifySignature("payload", null, "secret"));
        assertFalse(Webhook.verifySignature("payload", "sig", null));
        assertFalse(Webhook.verifySignature("payload", "", "secret"));
        assertFalse(Webhook.verifySignature("payload", "sig", ""));
    }

    @Test
    public void testParseEventSuccess() throws Exception {
        String secret = "whsec_test_secret_123";
        Map<String, Object> data = new HashMap<>();
        data.put("event", "payment.finished");
        data.put("invoice_id", "inv_abc");
        data.put("order_id", "ORD-999");
        data.put("pay_amount", "100.00");
        data.put("pay_token", "USDT");
        String payload = GSON.toJson(data);

        String signature = computeHmacHex(payload, secret);

        WebhookEvent event = Webhook.parseEvent(payload, signature, secret);
        assertNotNull(event);
        assertEquals("payment.finished", event.getEvent());
        assertEquals("inv_abc", event.getInvoiceId());
        assertEquals("ORD-999", event.getOrderId());
        assertEquals("USDT", event.getPayToken());
    }

    @Test
    public void testParseEventInvalidSignatureThrowsException() {
        String secret = "whsec_test_secret_123";
        String payload = "{\"event\":\"payment.finished\"}";
        String invalidSignature = "invalid_hex_signature";

        assertThrows(SignatureVerificationException.class, () -> {
            Webhook.parseEvent(payload, invalidSignature, secret);
        });
    }
}

