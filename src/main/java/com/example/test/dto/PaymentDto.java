package com.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payment information with composition schema")
public class PaymentDto {

  @Schema(description = "Payment ID", example = "pay_12345")
  private String paymentId;

  @Schema(description = "Payment amount in cents", example = "5000")
  private Long amount;

  @Schema(description = "Currency code", example = "USD")
  private String currency;

  @Schema(description = "Payment method details - can be credit card or bank transfer, or null", types = { "object",
      "null" }, oneOf = { CreditCardDto.class, BankTransferDto.class })
  private Object paymentMethod;

  @Schema(description = "Payment status - can be a status enum or null", types = { "object", "null" }, oneOf = {
      PaymentStatusDto.class })
  private Object status;

  @Schema(description = "Billing address - can be an address object or null", types = { "object", "null" }, oneOf = {
      BillingAddressDto.class })
  private Object billingAddress;

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

  public Object getBillingAddress() {
    return billingAddress;
  }

  public void setBillingAddress(Object billingAddress) {
    this.billingAddress = billingAddress;
  }
}
