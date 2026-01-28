package com.example.test.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Billing address information")
public class BillingAddressDto {

  @Schema(description = "Street address", example = "123 Main St")
  private String street;

  @Schema(description = "City", example = "San Francisco")
  private String city;

  @Schema(description = "State or province", example = "CA")
  private String state;

  @Schema(description = "Postal code", example = "94105")
  private String postalCode;

  @Schema(description = "Country code", example = "US")
  private String country;

  // Getters and setters
  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }
}
