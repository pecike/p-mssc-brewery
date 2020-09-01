package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {
    @Override
    public BeerDto getBeerById(final UUID beerId) {
        return BeerDto.builder()
                .id(beerId)
                .name("Galaxy Cat")
                .style("Pale Ale")
                .build();
    }
}
