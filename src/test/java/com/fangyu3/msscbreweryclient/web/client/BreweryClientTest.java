package com.fangyu3.msscbreweryclient.web.client;

import com.fangyu3.msscbreweryclient.web.model.BeerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BreweryClientTest {

    @Autowired
    BreweryClient breweryClient;

    @Test
    void getBeerByIdTest() {
        assertNotNull(breweryClient.getBeerById(UUID.randomUUID()));
    }

    @Test
    void saveBeerTest() {
        URI uri = breweryClient.saveBeer(BeerDto.builder().build());

        assertNotNull(uri);

        System.out.println(uri);
    }

    @Test
    void updateBeerTest() {
        breweryClient.updateBeer(UUID.randomUUID(), BeerDto.builder().build());
    }

    @Test
    void deleteBeerTest() {
        breweryClient.deleteBeer(UUID.randomUUID());
    }
}