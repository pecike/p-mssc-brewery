package guru.springframework.msscbrewery.web.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Value
@Builder
public class CustomerDto {

    UUID id;

    @NotBlank
    @Size(min = 3, max = 100)
    String name;
}
