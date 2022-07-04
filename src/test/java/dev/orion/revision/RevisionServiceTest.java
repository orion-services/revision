package dev.orion.revision;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(RevisionService.class) 
class RevisionServiceTest {

    @Test
    @DisplayName("Test empty input")
    @Order(1)
    void empty() {
        given()
            .formParam("githubProfileURL", "")
            .formParam("moodleProfileURL", "")
            .formParam("moodleAssign", "")
            .when().post()
            .then()
            .statusCode(400);
            //.body(is("Rodrigo Prestes Machado"))
    }

    @Test
    @DisplayName("Test wrong user")
    @Order(2)
    void wrong() {
         given()
            .formParam("githubProfileURL", "https://github.com")
            .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
            .formParam("moodleAssign", "https://moodle.poa.ifrs.edu.br")
            .when().post()
            .then()
            .statusCode(400);
     }

    // @Test
    // @DisplayName("Test wrong moodle")
    // @Order(3)
    // public void wrongMoodle() {
    //     given()
    //             .formParam("githubProfileURL", "https://github.com/rodrigoprestesmachado")
    //             .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
    //             .formParam("moodleAssign", "https://moodle.poa.ifrs.edu.br")
    //             .when().post("/verify")
    //             .then()
    //             .statusCode(400);
    // }

}