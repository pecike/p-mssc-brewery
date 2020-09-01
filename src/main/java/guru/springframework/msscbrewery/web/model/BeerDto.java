package guru.springframework.msscbrewery.web.model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BeerDto {
    private UUID id;
    private String name;
    private String style;
    private Long upc;
}
