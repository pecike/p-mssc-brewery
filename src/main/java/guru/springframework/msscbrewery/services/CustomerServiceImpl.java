package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
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
    public CustomerDto saveNewCustomer(final CustomerDto customerDto) {
        return CustomerDto.builder()
                .id(UUID.randomUUID())
                .name(customerDto.getName())
                .build();
    }

    @Override
    public void updateCustomerById(final UUID customerId, final CustomerDto customerDto) {
        log.debug("Updating customer with id {}", customerId);
    }

    @Override
    public void deleteCustomerById(final UUID customerId) {
        log.debug("Deleting customer with id {}", customerId);
    }
}
