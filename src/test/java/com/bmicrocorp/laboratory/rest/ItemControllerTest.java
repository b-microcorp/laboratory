package com.bmicrocorp.laboratory.rest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.LaboratoryTest;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerTest extends LaboratoryTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void testGetItem() {
        webClient.get().uri("/item/1").header(HttpHeaders.AUTHORIZATION, LaboratoryTest.BASIC_AUTH)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1);
    }

    @Test
    public void testGetItems() {
        webClient.get().uri("/items").header(HttpHeaders.AUTHORIZATION, LaboratoryTest.BASIC_AUTH)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(2)
                .jsonPath("$[0].id").isEqualTo(1);
    }

    @Test
    public void testGetRangedItems() {
        var res = webClient.get().uri("/items/range/2.0/80").header(HttpHeaders.AUTHORIZATION, LaboratoryTest.BASIC_AUTH)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.length()").isEqualTo(1)
                .jsonPath("$[0].id").isEqualTo(2)
                .jsonPath("$[0].price").isEqualTo(3.8).returnResult();
        // Check deeper
        assertThat("[{\"id\":2,\"title\":\"A second item\",\"price\":3.8,\"desc\":\"A new Testing item registered with a custom price\"}]")
            .isEqualTo(new String(res.getResponseBody()));
    }
}
