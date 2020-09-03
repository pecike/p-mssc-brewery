package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(CustomerController.API_V1_CUSTOMERS)
public class CustomerController {

    protected static final String API_V1_CUSTOMERS = "/api/v1/customers";
    final private CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable UUID customerId) {
        CustomerDto customer = customerService.getCustomerById(customerId);
        return ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveNewCustomer(@RequestBody CustomerDto customer) {
        CustomerDto newCustomer = customerService.saveNewCustomer(customer);
        final URI location = URI.create(API_V1_CUSTOMERS + "/" + newCustomer.getId());
        return created(location).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable UUID customerId,
                                               @RequestBody CustomerDto customer) {
        customerService.updateCustomerById(customerId, customer);
        return noContent().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        customerService.deleteCustomerById(customerId);
        return noContent().build();
    }
}



