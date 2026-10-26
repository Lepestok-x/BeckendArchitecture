package com.example.Spring;

import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.exception_class.ResourceNotFoundException;
import com.example.Spring.mappers.CustomerMapper;
import com.example.Spring.repositories.CustomersRepository;
import com.example.Spring.services.CustomersReadService;
import com.example.Spring.services.CustomersWriteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;

@Tag("Service")
@DisplayName("Service base data - test")
@ExtendWith(MockitoExtension.class)
public class TestService {
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private CustomersRepository repository;
    @InjectMocks
    private CustomersWriteService writeService;
    @InjectMocks
    private CustomersReadService readService;
    @Test
    void service() {
        Data data = new Data();
        CustomersDTO expected = new CustomersDTO(data.contactName(), data.address(), data.city(), data.postalCode(), data.country());
        Customers actual = new Customers(expected);
        // Обучаем мок, для записи данных.
        when(customerMapper.writeCustomer(expected)).thenReturn(actual);
        when(repository.save(any(Customers.class))).thenAnswer(answer -> {
            Customers customer = answer.getArgument(0);
            customer.setId(1L);
            return customer;
        });
        writeService.saveCustomer(expected);
        assertEquals(1L, actual.getId(), "Ид не совпадает с ожидающими данными");
        assertEquals(expected.address(), actual.getAddress(), "Адресс не совпадают с актуальными данными");
        assertEquals(expected.city(), actual.getCity(), "Город не совпадает с актуальными данными");
        assertEquals(expected.contactName(), actual.getContactName(), "Контактные имена, не совпадают с ожидающими данными");
        assertEquals(expected.country(), actual.getCountry(), "Странна не совпадает с актуальными данным");
        assertEquals(expected.postalCode(), actual.getPostalCode(), "Код не совпадает с актуальным кодом");
        verify(repository).save(any(Customers.class));

        CustomersDTO dto_l = new CustomersDTO(data.contactName(), data.address(), data.city(), data.postalCode(), data.country());
        Customers customers = new Customers(dto_l);
        long id = 1L;
        customers.setId(id);
        given(repository.findById(id)).willReturn(Optional.of(customers));
        assertThrows(IllegalArgumentException.class, () ->  {
            readService.customer(0L);
        });
        Customers dataCustomers = readService.customer(id);
        assertNotNull(dataCustomers);
        assertEquals(1L, customers.getId(), "Ид не корректен");
        assertEquals(dto_l.address(), dataCustomers.getAddress(), "Адресс не совпадают с актуальными данными");
        assertEquals(dto_l.city(), dataCustomers.getCity(), "Город не совпадает с актуальными данными");
        assertEquals(dto_l.contactName(), dataCustomers.getContactName(), "Контактные имена, не совпадают с ожидающими данными");
        assertEquals(dto_l.country(), dataCustomers.getCountry(), "Странна не совпадает с актуальными данным");
        assertEquals(dto_l.postalCode(), dataCustomers.getPostalCode(), "Код не совпадает с актуальным кодом");
        assertThrows(ResourceNotFoundException.class, () -> {
            readService.customer(2L);
        });

    }
}
