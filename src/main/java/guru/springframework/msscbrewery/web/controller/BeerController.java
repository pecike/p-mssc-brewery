package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.BeerService;
import guru.springframework.msscbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.*;

@Slf4j
@RestController
@RequestMapping(BeerController.API_V1_BEERS)
public class BeerController {

    protected static final String API_V1_BEERS = "/api/v1/beers";
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {
        log.info("GET beer with id {}", beerId);

        BeerDto beer = beerService.getBeerById(beerId);
        return ok(beer);
    }

    @PostMapping
    public ResponseEntity<Void> saveNewBeer(@RequestBody BeerDto beerDto) {
        log.info("POST new beer {}", beerDto);

        BeerDto newBeer = beerService.saveNewBeer(beerDto);
        final URI location = URI.create(API_V1_BEERS + "/" + newBeer.getId());
        return created(location).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(@PathVariable UUID beerId,
                                           @RequestBody BeerDto beerDto) {
        log.info("PUT exiting beer {}", beerDto);

        beerService.updateBeerById(beerId, beerDto);
        return noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable UUID beerId) {
        log.info("DELETE beer with id {}", beerId);

        beerService.deleteBeerById(beerId);
        return noContent().build();
    }
}

