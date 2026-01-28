package com.example.test.controller;

import com.example.test.dto.BankTransferDto;
import com.example.test.dto.CreditCardDto;
import com.example.test.dto.PaymentDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@Tag(name = "Payments", description = "Payment processing endpoints demonstrating OpenAPI 3.1 composition schemas")
public class PaymentController {

  @GetMapping("/{id}")
  @Operation(summary = "Get payment by ID", description = "Returns payment details with oneOf composition schema and types=['object', 'null']")
  public PaymentDto getPayment(@PathVariable String id) {
    PaymentDto payment = new PaymentDto();
    payment.setPaymentId(id);
    payment.setAmount(10000L);
    payment.setCurrency("USD");

    CreditCardDto card = new CreditCardDto();
    card.setCardNumber("4111111111111111");
    card.setHolderName("John Doe");
    card.setExpiryMonth("12");
    card.setExpiryYear("2028");
    payment.setPaymentMethod(card);

    return payment;
  }

  @PostMapping
  @Operation(summary = "Create new payment", description = "Creates payment with either credit card or bank transfer method")
  public PaymentDto createPayment(@RequestBody PaymentDto payment) {
    payment.setPaymentId("pay_" + System.currentTimeMillis());
    return payment;
  }

  @GetMapping("/bank-transfer/{id}")
  @Operation(summary = "Get payment with bank transfer", description = "Example showing bank transfer as payment method")
  public PaymentDto getPaymentWithBankTransfer(@PathVariable String id) {
    PaymentDto payment = new PaymentDto();
    payment.setPaymentId(id);
    payment.setAmount(25000L);
    payment.setCurrency("EUR");

    BankTransferDto bankTransfer = new BankTransferDto();
    bankTransfer.setAccountNumber("123456789");
    bankTransfer.setRoutingNumber("987654321");
    bankTransfer.setBankName("First National Bank");
    bankTransfer.setAccountHolder("Jane Smith");
    payment.setPaymentMethod(bankTransfer);

    return payment;
  }
}
