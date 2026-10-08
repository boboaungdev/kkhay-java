package com.kkhay;

import java.time.Duration;

/**
 * Main entrypoint for the K Khay Java SDK.
 * Convenient alias for {@link KkhayClient}.
 */
public class Kkhay extends KkhayClient {
    public Kkhay(String apiKey) {
        super(apiKey);
    }

    public Kkhay(String apiKey, String baseUrl) {
        super(apiKey, baseUrl);
    }

    public Kkhay(String apiKey, String baseUrl, Duration timeout) {
        super(apiKey, baseUrl, timeout);
    }
}
