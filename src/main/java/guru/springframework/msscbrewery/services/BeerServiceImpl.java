package guru.springframework.msscbrewery.services;

import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
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

    @Override
    public BeerDto saveNewBeer(final BeerDto beerDto) {
        return BeerDto.builder()
                .id(beerDto.getId())
                .name(beerDto.getName())
                .style(beerDto.getStyle())
                .upc(beerDto.getUpc())
                .build();
    }

    @Override
    public void updateBeerById(final UUID beerId, final BeerDto beerDto) {
        log.debug("Updating beer with id {}", beerId);
    }

    @Override
    public void deleteBeerById(final UUID beerId) {
        log.debug("Deleting beer with id {}", beerId);
    }

}
