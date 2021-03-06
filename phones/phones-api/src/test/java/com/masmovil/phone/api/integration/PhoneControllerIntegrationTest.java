package com.masmovil.phone.api.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ConfigurationProperties
public class PhoneControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    public void findCatalogueTest() {
         given().port(port).get(contextPath + "/catalogue").then()
                 .assertThat()
                        .statusCode(is(200));
    }

}