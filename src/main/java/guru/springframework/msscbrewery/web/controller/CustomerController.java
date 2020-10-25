package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
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
        log.info("GET customer with id {}", customerId);

        CustomerDto customer = customerService.getCustomerById(customerId);
        return ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> saveNewCustomer(@Valid @RequestBody CustomerDto customerDto) {
        log.info("POST new customer {}", customerDto);

        CustomerDto newCustomer = customerService.saveNewCustomer(customerDto);
        final URI location = URI.create(API_V1_CUSTOMERS + "/" + newCustomer.getId());
        return created(location).build();
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Void> updateCustomer(@PathVariable UUID customerId,
                                               @Valid @RequestBody CustomerDto customerDto) {
        log.info("PUT existing customer {}", customerDto);

        customerService.updateCustomerById(customerId, customerDto);
        return noContent().build();
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable UUID customerId) {
        log.info("DELETE customer with id {}", customerId);

        customerService.deleteCustomerById(customerId);
        return noContent().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationErrorHandler(MethodArgumentNotValidException e) {
        log.warn("Validation error: {}", e.getMessage());

        final Map<String, String> errors = new HashMap<>(e.getBindingResult().getFieldErrorCount());
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}



