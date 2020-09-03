package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {
    CustomerDto getCustomerById(UUID id);

    CustomerDto saveNewCustomer(CustomerDto customer);

    void updateCustomerById(UUID customerId, CustomerDto customer);

    void deleteCustomerById(UUID customerId);
}
