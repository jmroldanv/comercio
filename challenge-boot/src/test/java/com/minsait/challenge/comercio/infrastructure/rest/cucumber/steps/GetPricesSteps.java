package com.minsait.challenge.comercio.infrastructure.rest.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.minsait.challenge.comercio.infrastructure.rest.apimodel.model.PriceDetailsDto;
import com.minsait.challenge.comercio.infrastructure.rest.cucumber.config.CucumberSpringConfiguration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@CucumberContextConfiguration
@Slf4j
public class GetPricesSteps extends CucumberSpringConfiguration {

    private String date;
    private int productId;
    private int brandId;
    private ResponseEntity<PriceDetailsDto> response;

    @Given("Una petición con fecha {string}")
    public void theDateIs(String date) {
        this.date = date;
    }

    @Given("El identificador de producto es {int}")
    public void theProductIdIs(int productId) {
        this.productId = productId;
    }

    @Given("El identificador de cadena es {int}")
    public void theBrandIdIs(int brandId) {
        this.brandId = brandId;
    }

    @When("Se solicita el precio con esas características")
    public void requestThePrice() {
        UriComponentsBuilder urlBuilder =
                UriComponentsBuilder.fromHttpUrl(getUrl())
                        .queryParam("applicationDate", date)
                        .queryParam("productId", productId)
                        .queryParam("brandId", brandId);
        response = restTemplate.getForEntity(urlBuilder.toUriString(), PriceDetailsDto.class);
    }

    @Then("El código de estado de la respuesta es {int}")
    public void theResponseStatusIs(int expectedStatus) {
        assertNotNull(response);
        assertEquals(expectedStatus, response.getStatusCode().value());
    }

    @Then("El precio es {double}")
    public void thePriceIs(double expectedPrice) {
        log.debug(
                "Test con Cucumber - fecha {}, producto {}, marca {}; precio {}",
                date,
                productId,
                brandId,
                expectedPrice);
        assertNotNull(response.getBody());
        assertEquals(expectedPrice, response.getBody().getPrice());
    }

    private String getUrl() {
        return "http://localhost:" + port + basePath + "/prices";
    }
}
