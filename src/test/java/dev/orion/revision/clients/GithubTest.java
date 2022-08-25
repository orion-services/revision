package dev.orion.revision.clients;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class GithubTest {

    @Test
    @DisplayName("Test get User")
    @Order(1)
    public void testGetUser() {
        RestAssured.port = Integer.valueOf(443);
        given().when()
        .get("https://api.github.com/users/rpmhub")
        .then()
        .assertThat()
        .body("login", equalTo("rpmhub"));
    }

    @Test
    @DisplayName("Test get Repository")
    @Order(2)
    public void testGetRepo() {
        RestAssured.port = Integer.valueOf(443);
        given().when()
        .get("https://api.github.com/repos/rpmhub/cpw2-web-storage")
        .then()
        .assertThat()
        .body("name", equalTo("cpw2-web-storage"))
        .body("owner.login", equalTo("rpmhub"));
    }

}
