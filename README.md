<div align="center">

# `kkhay-java`

**Official Java, Kotlin, and Android SDK for K Khay — The Non-Custodial Crypto Payment Gateway**

[![Maven Central](https://img.shields.io/maven-central/v/com.kkhay/kkhay.svg?style=flat-square&color=10b981)](https://central.sonatype.com/artifact/com.kkhay/kkhay)
[![CI](https://github.com/boboaungdev/kkhay-java/actions/workflows/ci.yml/badge.svg?branch=main)](https://github.com/boboaungdev/kkhay-java/actions/workflows/ci.yml)
[![Java](https://img.shields.io/badge/Java-11%20%7C%2017%20%7C%2021%20%7C%2025%20(LTS)-orange.svg?style=flat-square)](https://kkhay.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg?style=flat-square)](LICENSE)

Accept cryptocurrency payments across **190+ blockchain networks** and **500+ tokens** (EVM, Bitcoin, Solana, TON, Tron, and XRPL) with **zero custody** and automatic direct-to-wallet settlement in **Java**, **Kotlin**, **Spring Boot**, **Quarkus**, **Micronaut**, and **Android** applications.

[Gateway Dashboard](https://gateway.kkhay.com) • [API Keys](https://gateway.kkhay.com/dashboard/keys) • [Website](https://kkhay.com)

</div>

---

## ⚡ Key Highlights

- 🛡️ **100% Non-Custodial**: Payments settle directly to your destination blockchain address in seconds. K Khay never holds your private keys or customer funds.
- 🌐 **190+ Networks Supported**: 
  - **EVM Networks**: Ethereum, BNB Smart Chain, Polygon, Arbitrum, Base, Optimism, Avalanche, and 180+ more.
  - **UTXO Networks**: Bitcoin (`btc`), Dogecoin (`doge`), Litecoin (`ltc`), Bitcoin Cash (`bch`).
  - **High-Throughput Chains**: Solana (`sol`), TON (`ton`), Tron (`trx`), XRP Ledger (`xrp`).
- 🪙 **500+ Cryptocurrencies**: Native coins and tokens (USDT, USDC, BTC, ETH, SOL, TON, BNB, DOGE, XRP, TRX, LTC, and more).
- 🔗 **Branded Hosted Checkouts**: Automatic payment links with dynamic QR codes, live blockchain polling, and wallet deep linking.
- ⚡ **Dual Sync & Async APIs**: First-class synchronous methods and non-blocking `CompletableFuture` APIs.
- 🔔 **Cryptographic Webhooks**: Timing-safe HMAC-SHA256 signature verification (`x-kkhay-signature`).
- ☕ **Universal Compatibility**: Supports Java 11+, Java 17, Java 21, Java 25 (LTS), Kotlin, and Android.

---

## 📦 Installation

### Maven
Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.kkhay</groupId>
    <artifactId>kkhay</artifactId>
    <version>1.0.4</version>
</dependency>
```

### Gradle (Kotlin DSL)
Add to your `build.gradle.kts`:

```kotlin
implementation("com.kkhay:kkhay:1.0.4")
```

### Gradle (Groovy)
Add to your `build.gradle`:

```groovy
implementation 'com.kkhay:kkhay:1.0.4'
```

---

## 🚀 Quickstart

### 1. Initialize the Client

Generate your API key in the [K Khay Gateway Dashboard](https://gateway.kkhay.com/dashboard/keys).

```java
import com.kkhay.*;

// Initialize using Kkhay or KkhayClient
Kkhay kkhay = new Kkhay("kkhay_live_your_api_key_here");

// Or using the fluent builder
KkhayClient client = KkhayClient.builder()
    .apiKey(System.getenv("KKHAY_API_KEY"))
    .timeout(Duration.ofSeconds(15))
    .build();
```

---

### 2. Create a Crypto Payment Invoice

```java
import com.kkhay.*;
import java.math.BigDecimal;

CreateInvoiceRequest request = CreateInvoiceRequest.builder()
    .amount(new BigDecimal("49.99"))
    .currency("USD")
    .payNetwork("bsc")       // "bsc", "polygon", "base", "arbitrum", "ethereum", "solana", "bitcoin", "ton"
    .payToken("USDT")        // "USDT", "USDC", "BTC", "ETH", "SOL", "TON"
    .orderId("ORDER-9842")
    .title("Annual Pro Plan")
    .customerName("Alice Smith")
    .customerEmail("alice@example.com")
    .redirectUrl("https://myshop.com/orders/success")
    .cancelUrl("https://myshop.com/cart")
    .build();

Invoice invoice = client.createInvoice(request);

System.out.println("Invoice ID: " + invoice.getId());
System.out.println("Gateway ID: " + invoice.getGatewayId());
System.out.println("Deposit Address: " + invoice.getDepositAddress());
System.out.println("Hosted Checkout URL: " + invoice.getHostedUrl());
```

---

### 3. Retrieve an Invoice & Blockchain Confirmations

```java
Invoice invoice = client.getInvoice("inv_0192a3b4c5d6");
System.out.println("Status: " + invoice.getStatus()); // "WAITING" | "PAID" | "EXPIRED" | "CANCELED"

// Or retrieve full transaction confirmation records
GetInvoiceResponse details = client.getInvoiceDetails("inv_0192a3b4c5d6");
for (PaymentRecord payment : details.getPayments()) {
    System.out.println("Received: " + payment.getAmountReceived() + " on Tx: " + payment.getTxHash());
    System.out.println("Confirmations: " + payment.getConfirmations() + ", Status: " + payment.getStatus());
}
```

---

### 4. List & Filter Invoices

```java
ListInvoicesRequest query = ListInvoicesRequest.builder()
    .page(1)
    .limit(20)
    .status(InvoiceStatus.PAID)
    .type(InvoiceType.PAYMENT)
    .build();

ListInvoicesResponse response = client.listInvoices(query);
System.out.println("Total paid invoices: " + response.getTotalCount());
for (Invoice inv : response.getItems()) {
    System.out.println("- " + inv.getId() + ": " + inv.getAmount() + " " + inv.getCurrency());
}
```

---

### 5. Verify Incoming Webhooks (IPN)

When a customer pays and the transaction is confirmed on-chain, K Khay sends a cryptographically signed `POST` webhook to your server with the `x-kkhay-signature` header.

#### In Spring Boot:

```java
import com.kkhay.*;
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

        try {
            WebhookEvent event = Webhook.parseEvent(rawPayload, signature, ipnSecret);

            if (WebhookEventType.PAYMENT_PAID.equals(event.getEvent()) ||
                WebhookEventType.PAYMENT_FINISHED.equals(event.getEvent())) {
                System.out.println("Payment confirmed for Invoice: " + event.getInvoiceId());
                System.out.println("Gateway ID: " + event.getGatewayId());
                System.out.println("Received " + event.getPayAmount() + " " + event.getPayToken() + " on " + event.getPayNetwork());
                // Fulfill customer's order in your database
            }

            return ResponseEntity.ok("{\"ok\":true}");
        } catch (SignatureVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
        }
    }
}
```

---

## 🌐 Supported Networks & Families

| Network Family | Popular Chains | Supported Tokens |
| :--- | :--- | :--- |
| **EVM (180+ Chains)** | Ethereum, BNB Smart Chain, Polygon, Arbitrum, Base, Optimism, Avalanche | USDT, USDC, ETH, BNB, POL, AVAX, DAI, and all ERC-20s |
| **UTXO** | Bitcoin, Dogecoin, Litecoin, Bitcoin Cash | BTC, DOGE, LTC, BCH |
| **Solana** | Solana Mainnet | SOL, USDT, USDC |
| **TON** | The Open Network | TON, USDT |
| **Tron (TVM)** | Tron Mainnet | TRX, USDT |
| **XRPL** | XRP Ledger | XRP |

---

## 🛡️ Error Handling

The SDK throws structured `KkhayApiException` instances containing HTTP status codes and error details:

```java
try {
    Invoice invoice = client.getInvoice("inv_nonexistent");
} catch (KkhayApiException e) {
    System.err.println("API Error HTTP " + e.getStatusCode() + " [" + e.getErrorCode() + "]: " + e.getMessage());
}
```

---

## 📄 License

MIT © [K Khay](https://kkhay.com)
