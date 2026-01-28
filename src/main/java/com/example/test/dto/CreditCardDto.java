package com.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Credit card payment information")
public class CreditCardDto {

  @Schema(description = "Card number", example = "4111111111111111")
  private String cardNumber;

  @Schema(description = "Cardholder name", example = "John Doe")
  private String holderName;

  @Schema(description = "Expiry month", example = "12")
  private String expiryMonth;

  @Schema(description = "Expiry year", example = "2028")
  private String expiryYear;

  // Getters and setters
  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getHolderName() {
    return holderName;
  }

  public void setHolderName(String holderName) {
    this.holderName = holderName;
  }

  public String getExpiryMonth() {
    return expiryMonth;
  }

  public void setExpiryMonth(String expiryMonth) {
    this.expiryMonth = expiryMonth;
  }

  public String getExpiryYear() {
    return expiryYear;
  }

  public void setExpiryYear(String expiryYear) {
    this.expiryYear = expiryYear;
  }
}
