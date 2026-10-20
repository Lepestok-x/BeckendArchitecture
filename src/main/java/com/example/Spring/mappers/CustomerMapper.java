package com.example.Spring.mappers;

import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface CustomerMapper {
    CustomersDTO toDTO(Customers customers);
    Customers saveCustomer(CustomersDTO customersDTO);
}
