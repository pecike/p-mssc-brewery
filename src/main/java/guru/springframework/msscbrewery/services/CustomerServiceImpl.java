package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(final UUID id) {
        return CustomerDto.builder()
                .id(id)
                .name("John Doe")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(final CustomerDto customer) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .build();
    }

    @Override
    public void updateCustomerById(final UUID customerId, final CustomerDto customer) {
        // nothing yet
    }

    @Override
    public void deleteCustomerById(final UUID customerId) {
        // nothing yet
    }
}
