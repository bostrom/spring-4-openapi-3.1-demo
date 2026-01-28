package com.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payment status enumeration")
public enum PaymentStatusDto {

  @Schema(description = "Payment is pending")
  PENDING,

  @Schema(description = "Payment is being processed")
  PROCESSING,

  @Schema(description = "Payment completed successfully")
  COMPLETED,

  @Schema(description = "Payment failed")
  FAILED,

  @Schema(description = "Payment was refunded")
  REFUNDED,

  @Schema(description = "Payment was cancelled")
  CANCELLED
}
