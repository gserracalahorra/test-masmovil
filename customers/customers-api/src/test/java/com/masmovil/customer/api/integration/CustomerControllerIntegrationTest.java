package com.masmovil.customer.api.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ConfigurationProperties
public class CustomerControllerIntegrationTest {

    @LocalServerPort
    int port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Test
    public void findCustomerByIdTest() {
        given().port(port).get(contextPath + "/customer/1").then()
                .assertThat()
                .statusCode(is(200))
                .body("name", equalTo("Guillem"))
                .body("surname", equalTo("Serra Calahorra"))
                .body("email", equalTo("guillem_serra@hotmail.com"));
    }

    @Test
    public void findCustomerByIdResourceNotFoundTest() {
        given().port(port).get(contextPath + "/customer/6").then()
                .assertThat()
                .statusCode(is(404));
    }

}