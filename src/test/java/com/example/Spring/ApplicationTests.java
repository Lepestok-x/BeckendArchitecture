package com.example.Spring;

import com.example.Spring.database.Customers;
import com.example.Spring.dto.CustomersDTO;
import com.example.Spring.mappers.CustomerMapper;
import com.example.Spring.repositories.CustomersRepository;
import com.example.Spring.services.CustomersWriteService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@Tag("Mockito")
@ExtendWith(MockitoExtension.class)
class ApplicationTests {
    @Mock
    private CustomersRepository customersRepository;
    @Mock
    private CustomerMapper customerMapper;
    @InjectMocks
    private CustomersWriteService writeService;
    private Validator validator;

    @BeforeEach
    void initValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
        ReflectionTestUtils.setField(writeService, "validator", validator);
    }

	@Test
	void contextLoads() throws Exception {
        CustomersDTO customersDTO = new CustomersDTO("N1111", "N2111", "N3111", "N4", "N5111");
        Customers after = new Customers();
        Customers before = new Customers();
        after.setId(1L);
        when(customerMapper.writeCustomer(customersDTO)).thenReturn(before);
        when(customersRepository.save(any(Customers.class))).thenReturn(after);
        assertDoesNotThrow(() -> writeService.saveCustomer(customersDTO));
        verify(customersRepository).save(before);

	}
    @Disabled("Этот тест лучше перенести в специальный класс, для проверки ошибок")
    @Test
    void contextError() {
        CustomersDTO customersDTO = new CustomersDTO("", "N2", "N3", "N4", "N5");
        assertThrows(IllegalArgumentException.class, () -> writeService.saveCustomer(customersDTO));
    }
}
