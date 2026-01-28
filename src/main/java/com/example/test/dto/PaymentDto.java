package com.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payment information demonstrating OpenAPI 3.1 nullable types with composition schemas")
public class PaymentDto {

  @Schema(description = "Payment ID", example = "pay_12345")
  private String paymentId;

  @Schema(description = "Payment amount in cents", example = "5000")
  private Long amount;

  @Schema(description = "Currency code", example = "USD")
  private String currency;

  // ============================================================================
  // PATTERN 1: Polymorphic field - multiple types in oneOf
  // ============================================================================
  // ✅ CORRECT: Use Object type with oneOf when you need to express multiple
  // possible types
  // Result: Clean spec with types array and oneOf, no superfluous $ref
  @Schema(description = "Payment method details - can be credit card or bank transfer, or null", types = { "object",
      "null" }, oneOf = { CreditCardDto.class, BankTransferDto.class })
  private Object paymentMethod;

  // ============================================================================
  // PATTERN 2: Single type with oneOf (enum example)
  // ============================================================================
  // ✅ CORRECT: Using Object with single-item oneOf works but requires casting in
  // Java
  // Result: Clean spec with types array and oneOf
  @Schema(description = "Payment status - can be a status enum or null", types = { "object", "null" }, oneOf = {
      PaymentStatusDto.class })
  private Object status;

  // ============================================================================
  // PATTERN 3: Typed property without oneOf
  // ============================================================================
  // ✅ CORRECT: Use typed property with just types array for single-type nullable
  // fields
  // Result: Clean spec with types array and $ref, best for type-safe Java code
  @Schema(description = "Billing address - can be an address object or null", types = { "object", "null" })
  private BillingAddressDto billingAddress;

  // ============================================================================
  // ANTI-PATTERN: Typed property WITH oneOf of same type
  // ============================================================================
  // ❌ WRONG: This creates a superfluous $ref alongside the oneOf
  // This will produce:
  // type: [object, null]
  // $ref: "#/components/schemas/BillingAddressDto" <-- SUPERFLUOUS!
  // oneOf:
  // - $ref: "#/components/schemas/BillingAddressDto"
  @Schema(description = "Shipping address (ANTI-PATTERN: will create superfluous $ref)", types = { "object",
      "null" }, oneOf = { BillingAddressDto.class })
  private BillingAddressDto shippingAddress;

  // Getters and setters
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public Object getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(Object paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Object getStatus() {
    return status;
  }

  public void setStatus(Object status) {
    this.status = status;
  }

  public BillingAddressDto getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(BillingAddressDto billingAddress) {
    this.billingAddress = billingAddress;
  }

  public BillingAddressDto getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(BillingAddressDto shippingAddress) {
    this.shippingAddress = shippingAddress;
  }
}
