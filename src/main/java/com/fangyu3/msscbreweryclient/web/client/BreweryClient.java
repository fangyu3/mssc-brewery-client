package com.fangyu3.msscbreweryclient.web.client;

import com.fangyu3.msscbreweryclient.web.model.BeerDto;
import com.fangyu3.msscbreweryclient.web.model.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;

@Component
@ConfigurationProperties(prefix = "sfg.brewery",ignoreUnknownFields = false)
public class BreweryClient {
    private final String BEER_PATH_V1 = "/api/v1/beer/";
    private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
    private String apihost;
    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        // Spring inject restTemplateBuilder which we use to create restTemplate object
        this.restTemplate = restTemplateBuilder.build();
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

    public BeerDto getBeerById(UUID id) {
        return restTemplate.getForObject(apihost+BEER_PATH_V1+id, BeerDto.class);
    }

    public URI saveBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(apihost+BEER_PATH_V1,beerDto);
    }

    public void updateBeer(UUID id, BeerDto beerDto) {
        restTemplate.put(apihost+BEER_PATH_V1+id, beerDto);
    }

    public void deleteBeer(UUID id) {
        restTemplate.delete(apihost+BEER_PATH_V1+id);
    }

    public CustomerDto geCustomerById(UUID id) {
        return restTemplate.getForObject(apihost+CUSTOMER_PATH_V1+id, CustomerDto.class);
    }

    public URI saveCustomer(CustomerDto customerDto) {
        return restTemplate.postForLocation(apihost+CUSTOMER_PATH_V1,customerDto);
    }

    public void updateCustomer(UUID id, CustomerDto customerDto) {
        restTemplate.put(apihost+CUSTOMER_PATH_V1+id, customerDto);
    }

    public void deleteCustomer(UUID id) {
        restTemplate.delete(apihost+CUSTOMER_PATH_V1+id);
    }

}
