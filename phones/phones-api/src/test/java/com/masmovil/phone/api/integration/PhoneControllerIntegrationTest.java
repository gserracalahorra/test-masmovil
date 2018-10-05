package com.masmovil.phone.api.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Test
    public void findCatalogueTest() {
         given().port(port).get("/phone/catalogue").then()
                 .assertThat()
                        .statusCode(is(200));
    }

}