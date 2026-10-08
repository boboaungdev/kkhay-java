# K Khay Java & Kotlin SDK ☕

Official Java, Kotlin, and Android SDK for the **[K Khay Sovereign Crypto Payment Gateway](https://kkhay.com)**.

Accept non-custodial and custodial crypto payments (USDT, USDC, BNB, ETH on BSC, Polygon, Arbitrum, Base, Ethereum) in **Java**, **Kotlin**, **Spring Boot**, **Quarkus**, **Micronaut**, and **Android** applications.

[![Maven Central](https://img.shields.io/maven-central/v/com.kkhay/kkhay.svg)](https://central.sonatype.com/artifact/com.kkhay/kkhay)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 📦 Installation

### Maven
Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.kkhay</groupId>
    <artifactId>kkhay</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle (Kotlin DSL)
Add the dependency to your `build.gradle.kts`:

```kotlin
implementation("com.kkhay:kkhay:1.0.0")
```

### Gradle (Groovy)
Add the dependency to your `build.gradle`:

```groovy
implementation 'com.kkhay:kkhay:1.0.0'
```

---

## ⚡ Quick Start

### 1. Initialize the Client

```java
import com.kkhay.KkhayClient;

// Standard client using system defaults
KkhayClient client = new KkhayClient("kkhay_live_your_api_key");

// Or using the fluent builder
KkhayClient client = KkhayClient.builder()
    .apiKey("kkhay_live_your_api_key")
    .timeout(Duration.ofSeconds(15))
    .build();
```

---

### 2. Create a Payment Invoice

```java
import com.kkhay.model.CreateInvoiceRequest;
import com.kkhay.model.Invoice;
import java.math.BigDecimal;

CreateInvoiceRequest request = CreateInvoiceRequest.builder()
    .priceAmount(new BigDecimal("49.99"))
    .priceCurrency("USD")
    .payNetwork("bsc")
    .payToken("USDT")
    .orderId("ORDER-1001")
    .title("E-Commerce Order #1001")
    .customerEmail("buyer@example.com")
    .redirectUrl("https://myshop.com/orders/1001/success")
    .cancelUrl("https://myshop.com/orders/1001/cancel")
    .build();

Invoice invoice = client.createInvoice(request);

System.out.println("Invoice ID: " + invoice.getId());
System.out.println("Checkout URL: " + invoice.getHostedUrl());
```

---

### 3. Retrieve an Invoice

```java
Invoice invoice = client.getInvoice("inv_abc123");
System.out.println("Invoice Status: " + invoice.getStatus());
```

---

### 4. List Invoices with Pagination

```java
import com.kkhay.model.ListInvoicesRequest;
import com.kkhay.model.ListInvoicesResponse;

ListInvoicesRequest query = ListInvoicesRequest.builder()
    .status("paid")
    .limit(20)
    .build();

ListInvoicesResponse response = client.listInvoices(query);
for (Invoice inv : response.getItems()) {
    System.out.println(inv.getId() + " - " + inv.getStatus());
}
```

---

### 5. Asynchronous Non-Blocking Calls

Every method has a non-blocking `Async` counterpart returning `CompletableFuture`:

```java
client.createInvoiceAsync(request)
    .thenAccept(invoice -> {
        System.out.println("Created async invoice: " + invoice.getId());
    })
    .exceptionally(ex -> {
        System.err.println("Failed: " + ex.getMessage());
        return null;
    });
```

---

## 🔐 Webhook / IPN Verification (Spring Boot Controller)

Verify incoming Instant Payment Notifications (IPN) with constant-time HMAC-SHA256 signature verification:

```java
import com.kkhay.Webhook;
import com.kkhay.model.WebhookEvent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class KkhayWebhookController {

    private final String ipnSecret = System.getenv("KKHAY_IPN_SECRET");

    @PostMapping("/kkhay")
    public ResponseEntity<String> handleWebhook(
            @RequestBody String rawPayload,
            @RequestHeader("x-kkhay-signature") String signature) {

        // Constant-time HMAC-SHA256 signature check & parsing
        if (!Webhook.verifySignature(rawPayload, signature, ipnSecret)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
        }

        WebhookEvent event = Webhook.parseEvent(rawPayload, signature, ipnSecret);

        if ("payment.finished".equals(event.getEvent())) {
            String orderId = event.getOrderId();
            String txHash = event.getTxHash();
            // Fulfill your order in your database
            System.out.println("Order " + orderId + " paid! Tx: " + txHash);
        }

        return ResponseEntity.ok("OK");
    }
}
```

---

## 🧪 Kotlin Usage

Because the SDK is built with clean Java conventions, Kotlin developers enjoy seamless syntax:

```kotlin
import com.kkhay.KkhayClient
import com.kkhay.model.CreateInvoiceRequest
import java.math.BigDecimal

val client = KkhayClient("kkhay_live_...")

val request = CreateInvoiceRequest.builder()
    .priceAmount(BigDecimal("29.99"))
    .priceCurrency("USD")
    .payNetwork("bsc")
    .payToken("USDT")
    .orderId("KOTLIN-1")
    .build()

val invoice = client.createInvoice(request)
println("Checkout: ${invoice.hostedUrl}")
```

---

## 📄 License

MIT © [K Khay](https://kkhay.com)

