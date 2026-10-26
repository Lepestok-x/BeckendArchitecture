package com.example.Spring.services;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mappers.CustomerMapper;
import com.example.Spring.repositories.CustomersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CustomersWriteService {
    private final CustomersRepository repository;
    private final CustomerMapper customerMapper;
    @Transactional(rollbackFor = Exception.class)
    public void saveCustomer(CustomersDTO customersDTO) {
        repository.save(customerMapper.writeCustomer(customersDTO));
    }
}
