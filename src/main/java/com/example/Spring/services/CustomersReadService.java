package com.example.Spring.services;

import com.example.Spring.database.Customers;
import com.example.Spring.exception_class.ResourceNotFoundException;
import com.example.Spring.repositories.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomersReadService {
    private final CustomersRepository repository;
    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Customers customer(Long id) {
        if(id < 1) throw new IllegalArgumentException(String.format("Index not found: %s", id));
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not found", HttpStatus.NOT_FOUND));
    }
}
