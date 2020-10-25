package guru.springframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbrewery.MsscBreweryApplication;
import guru.springframework.msscbrewery.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(BeerController.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {MsscBreweryApplication.class})
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void getBeer() throws Exception {
        final UUID beerId = UUID.randomUUID();

        mockMvc.perform(get(BeerController.API_V1_BEERS + "/" + beerId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{" +
                        "\"id\":\"" + beerId + "\"," +
                        "\"name\":\"Galaxy Cat\"," +
                        "\"style\":\"Pale Ale\"," +
                        "\"upc\":null" +
                        "}"));
    }

    @Test
    void saveNewBeer() throws Exception {
        final BeerDto beerDto = getBasicBeerDto().build();
        final String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(post(BeerController.API_V1_BEERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void saveNewBeer_shouldReturnBadRequest_whenBeerDtoIsInvalid() throws Exception {
        final BeerDto beerDto = getBasicBeerDto()
                .name("")
                .build();
        final String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(post(BeerController.API_V1_BEERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name", is("must not be blank")));
    }

    @Test
    void updateBeer() throws Exception {
        final UUID beerId = UUID.randomUUID();
        final BeerDto beerDto = getBasicBeerDto()
                .build();
        final String beerDtoJson = mapper.writeValueAsString(beerDto);

        mockMvc.perform(put(BeerController.API_V1_BEERS + "/" + beerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        final UUID beerId = UUID.randomUUID();

        mockMvc.perform(delete(BeerController.API_V1_BEERS + "/" + beerId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    BeerDto.BeerDtoBuilder getBasicBeerDto() {
        return BeerDto.builder()
                .name("Test beer")
                .style("Lager")
                .upc(123456789L);
    }
}