package com.kkhay;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility for verifying HMAC-SHA256 signatures and parsing incoming K Khay IPN webhooks.
 */
public final class Webhook {
    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final Gson GSON = new Gson();

    private Webhook() {}

    /**
     * Verifies the HMAC-SHA256 signature from the {@code x-kkhay-signature} HTTP header using constant-time comparison.
     *
     * @param payload   The raw UTF-8 request body string.
     * @param signature The signature string from the {@code x-kkhay-signature} header.
     * @param ipnSecret Your merchant IPN webhook secret key.
     * @return {@code true} if the signature is valid; {@code false} otherwise.
     */
    public static boolean verifySignature(String payload, String signature, String ipnSecret) {
        if (payload == null || signature == null || ipnSecret == null) {
            return false;
        }

        String trimmedSig = signature.trim();
        String trimmedSecret = ipnSecret.trim();

        if (trimmedSig.isEmpty() || trimmedSecret.isEmpty()) {
            return false;
        }

        try {
            Mac mac = Mac.getInstance(HMAC_SHA256);
            SecretKeySpec secretKey = new SecretKeySpec(trimmedSecret.getBytes(StandardCharsets.UTF_8), HMAC_SHA256);
            mac.init(secretKey);
            byte[] hmacBytes = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));

            String expectedHex = bytesToHex(hmacBytes);
            byte[] expectedBytes = expectedHex.getBytes(StandardCharsets.UTF_8);
            byte[] providedBytes = trimmedSig.toLowerCase().getBytes(StandardCharsets.UTF_8);

            // Constant-time comparison to prevent timing attacks
            return MessageDigest.isEqual(expectedBytes, providedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            return false;
        }
    }

    /**
     * Validates and deserializes an incoming webhook request body.
     *
     * @param payload   The raw request body string.
     * @param signature The signature from the {@code x-kkhay-signature} header.
     * @param ipnSecret Your merchant IPN webhook secret key.
     * @return The parsed {@link WebhookEvent}.
     * @throws SignatureVerificationException If signature verification fails.
     */
    public static WebhookEvent parseEvent(String payload, String signature, String ipnSecret) {
        if (!verifySignature(payload, signature, ipnSecret)) {
            throw new SignatureVerificationException();
        }

        try {
            return GSON.fromJson(payload, WebhookEvent.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Failed to parse webhook JSON payload", e);
        }
    }

    /**
     * Deserializes a pre-verified webhook JSON payload.
     *
     * @param payload The raw JSON string.
     * @return The parsed {@link WebhookEvent}.
     */
    public static WebhookEvent parseEvent(String payload) {
        try {
            return GSON.fromJson(payload, WebhookEvent.class);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Failed to parse webhook JSON payload", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

