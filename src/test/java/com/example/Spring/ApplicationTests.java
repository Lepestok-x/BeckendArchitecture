package com.example.Spring;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ApplicationTests {
    @Mock
    private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
        mockMvc.perform(post("").contentType("application/json"))
            .andReturn();
	}
}
