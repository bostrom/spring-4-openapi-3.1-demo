package com.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Bank transfer payment details")
public class BankTransferDto {

  @Schema(description = "Account number", example = "123456789")
  private String accountNumber;

  @Schema(description = "Routing number", example = "987654321")
  private String routingNumber;

  @Schema(description = "Bank name", example = "First National Bank")
  private String bankName;

  @Schema(description = "Account holder name", example = "Jane Smith")
  private String accountHolder;

  // Getters and setters
  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getRoutingNumber() {
    return routingNumber;
  }

  public void setRoutingNumber(String routingNumber) {
    this.routingNumber = routingNumber;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }
}
