package com.example.Spring.controllers;

import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mappers.CustomerMapper;
import com.example.Spring.services.CustomersReadService;
import com.example.Spring.services.CustomersWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomersController {
    private final CustomersWriteService writeService;
    private final CustomersReadService readService;
    private final CustomerMapper customerMapper;

    @GetMapping("/{id}")
    private ResponseEntity<CustomersDTO> customers(@PathVariable Long id) {
        Customers customers = readService.customer(id);
        return ResponseEntity.ok(customerMapper.toDTO(customers));
    }
    @PostMapping("/create")
    private ResponseEntity<HttpStatus> createCustomers(@Validated @RequestBody CustomersDTO customersDTO) {
        writeService.saveCustomer(customersDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
