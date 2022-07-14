package dev.orion.revision;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
            .formParam("moodleAssignURL", "")
            .when().post()
            .then()
            .statusCode(400);
            //.body(is("Rodrigo Prestes Machado"))
    }

    /*     
    @Test
    @DisplayName("Test wrong user")
    @Order(2)
    void wrong() {
        given()
            //.accept(ContentType.JSON)
            .formParam("githubProfileURL", "https://github.com")
            .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
            .formParam("moodleAssignURL", "https://moodle.poa.ifrs.edu.br")
            .when().post()
            .then()
            //.header("Content-Type", "application/json")
            .statusCode(400);
     }
     
    @Test
    @DisplayName("Test wrong Moodle user")
    @Order(2)
    void wrongMoodleUser() {
         given()
            .formParam("githubProfileURL", "https://github.com/amfabian")
            .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
            .formParam("moodleAssignURL", "http://localhost/mod/assign/view.php?id=2")
            .when().post()
            .then()
            .statusCode(400);
     }

    @Test
    @DisplayName("Test wrong Github user")
    @Order(2)
    void wrong() {
         given()
            .formParam("githubProfileURL", "https://github.com")
            .formParam("moodleProfileURL", "http://localhost/user/profile.php?id=4")
            .formParam("moodleAssignURL", "http://localhost/mod/assign/view.php?id=2")
            .when().post()
            .then()
            .statusCode(400);
     }

     @Test
    @DisplayName("Test wrong Assign")
    @Order(2)
    void wrongAssign() {
         given()
            .formParam("githubProfileURL", "https://github.com/graziellarodrigues")
            .formParam("moodleProfileURL", "http://localhost/user/profile.php?id=4")
            .formParam("moodleAssignURL", "https://moodle.poa.ifrs.edu.br")
            .when().post()
            .then()
            .statusCode(400);
    }
             
     */

    // @Test
    // @DisplayName("Test wrong moodle")
    // @Order(3)
    // public void wrongMoodle() {
    //     given()
    //             .formParam("githubProfileURL", "https://github.com/rodrigoprestesmachado")
    //             .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
    //             .formParam("moodleAssignURL", "https://moodle.poa.ifrs.edu.br")
    //             .when().post("/verify")
    //             .then()
    //             .statusCode(400);
    // }
}