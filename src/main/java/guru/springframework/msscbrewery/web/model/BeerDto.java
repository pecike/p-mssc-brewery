package guru.springframework.msscbrewery.web.model;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Value
@Builder
public class BeerDto {
    @Null UUID id;
    @NotBlank String name;
    @NotBlank String style;
    @Positive Long upc;
}
