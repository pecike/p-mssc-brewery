package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.MsscBreweryApplication;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static guru.springframework.msscbrewery.web.controller.CustomerController.API_V1_CUSTOMERS;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = {MsscBreweryApplication.class})
class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void saveNewCustomer_shouldReturnBadRequest_whenCustomerDtoIsInvalid() throws Exception {
        final CustomerDto customer = CustomerDto.builder()
                .id(UUID.randomUUID())
                .name("12")
                .build();
        final String customerJson = mapper.writeValueAsString(customer);

        mockMvc.perform(post(API_V1_CUSTOMERS)
        .contentType(MediaType.APPLICATION_JSON)
        .content(customerJson))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name", is("size must be between 3 and 100")));
    }
}