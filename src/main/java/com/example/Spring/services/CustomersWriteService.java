package com.example.Spring.services;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mappers.CustomerMapper;
import com.example.Spring.repositories.CustomersRepository;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
public class CustomersWriteService {
    private final CustomersRepository repository;
    private final CustomerMapper customerMapper;
    private final Validator validator;
    @Transactional(rollbackFor = Exception.class)
    public void saveCustomer(CustomersDTO customersDTO) {
        var validate = validator.validate(customersDTO);
        // Выбросим исключение на сильно, даже если валидатор не пуст.
        if(!validate.isEmpty()) {
            throw new IllegalArgumentException("Error");
        }
        // Тут не выбрасываем иключение, так как сам валидатор может быть не пустым, частично отдаст false
        if(validate.isEmpty()) {
            throw new IllegalArgumentException("Not error");
        }
        repository.save(customerMapper.saveCustomer(customersDTO));
    }
}
