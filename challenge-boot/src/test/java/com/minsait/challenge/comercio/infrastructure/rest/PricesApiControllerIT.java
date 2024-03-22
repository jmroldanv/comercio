package com.minsait.challenge.comercio.infrastructure.rest;

import com.minsait.challenge.comercio.infrastructure.rest.apimodel.model.PriceDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class PricesApiControllerIT {

    @Value("${openapi.comercio.base-path:}")
    private String basePath;

    @LocalServerPort private int port;

    @Autowired private TestRestTemplate restTemplate;

    @ParameterizedTest(
            name = "Test con RestTemplate - fecha {1}, producto {2}, marca {3}; precio {4}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testWithRestTemplate(String date, int productId, int brandId, double expectedPrice) {
        log.debug(
                "Test con RestTemplate - fecha {}, producto {}, marca {}; precio {}",
                date,
                productId,
                brandId,
                expectedPrice);
        UriComponentsBuilder urlBuilder =
                UriComponentsBuilder.fromHttpUrl(getUrl())
                        .queryParam("applicationDate", date)
                        .queryParam("productId", productId)
                        .queryParam("brandId", brandId);
        ResponseEntity<PriceDetailsDto> response =
                restTemplate.getForEntity(urlBuilder.toUriString(), PriceDetailsDto.class);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
        assertEquals(expectedPrice, response.getBody().getPrice());
    }

    @ParameterizedTest(
            name = "Test con RestClient - fecha {1}, producto {2}, marca {3}; precio {4}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void testWithRestClient(String date, int productId, int brandId, double expectedPrice) {
        log.debug(
                "Test con RestClient - fecha {}, producto {}, marca {}; precio {}",
                date,
                productId,
                brandId,
                expectedPrice);
        UriComponentsBuilder urlBuilder =
                UriComponentsBuilder.fromHttpUrl(getUrl())
                        .queryParam("applicationDate", date)
                        .queryParam("productId", productId)
                        .queryParam("brandId", brandId);
        RestClient restClient = RestClient.builder(restTemplate.getRestTemplate()).build();
        ResponseEntity<PriceDetailsDto> response =
                restClient
                        .get()
                        .uri(urlBuilder.toUriString())
                        .retrieve()
                        .toEntity(PriceDetailsDto.class);

        assertNotNull(response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(brandId, response.getBody().getBrandId());
        assertEquals(productId, response.getBody().getProductId());
        assertEquals(expectedPrice, response.getBody().getPrice());
    }

    private String getUrl() {
        return "http://localhost:" + port + basePath + "/prices";
    }
}
