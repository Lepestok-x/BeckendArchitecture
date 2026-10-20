package com.example.Spring.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record CustomersDTO(
          @NotNull(message = "Contact name: not empty")
          @Size(min = 5, max = 10)
          String contactName,
          @NotNull(message = "Address not empty")
          @Size(min = 12, max = 20)
          String address,
          @NotNull(message = "City not empty")
          @Size(min = 8, max = 20)
          String city,
          @NotNull(message = "Code not empty")
          @Size(min = 5, max = 25)
          String postalCode,
          @NotNull(message = "Country not empty")
          @Size(min = 10, max = 15)
          String country) {
    /*
       immutable class
    */
}
