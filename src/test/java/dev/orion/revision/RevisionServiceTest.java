package dev.orion.revision;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;

import dev.orion.revision.clients.MoodleMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;


@QuarkusTest
@QuarkusTestResource(MoodleMock.class)
class RevisionServiceTest {

    @Inject
    RevisionService service;

    @Test
    @TestHTTPEndpoint(RevisionService.class) 
    @DisplayName("Test empty input")
    @Order(1)
    void empty() {
        given()
            .formParam("githubProfileURL", "")
            .formParam("moodleProfile", "")
            .formParam("moodleAssign", "")
            .when().post()
            .then()
            .statusCode(400)
            .body(containsString("exception"));            
    }

    @Test
    @TestHTTPEndpoint(RevisionService.class) 
    @DisplayName("Test wrong Github user")
    @Order(2)
    void wrongUser() {
         given()
            .formParam("githubProfileURL", "https://github.com/fakeuser")
            .formParam("moodleProfile", "5")
            .formParam("moodleAssign", "2")
            .when().post()
            .then()
            .statusCode(400)
            .body(is("{\"Message\":\"Não foi possível completar a sua requisição, possivelmente você informou um usuário do Github inexistente\"}"));
     }

     
    @Test
    @DisplayName("Test wrong Moodle user")
    @Order(3)
    void wrongMoodleUser() {
         given()
            .formParam("githubProfileURL", "https://github.com/amfabian")
            .formParam("moodleProfile", "15")
            .formParam("moodleAssign", "2")
            .when().post()
            .then()
            .statusCode(404);
     }

    /*

    @Test
    @DisplayName("Test wrong Assign")
    @Order(4)
    void wrongAssign() {
         given()
            .formParam("githubProfileURL", "https://github.com/graziellarodrigues")
            .formParam("moodleProfile", "4")
            .formParam("moodleAssign", "2")
            .when().post()
            .then()
            .statusCode(404);
    }
             
    @Test
    @DisplayName("Test wrong moodle")
    @Order(3)
    public void wrongMoodle() {
        given()
                .formParam("githubProfileURL", "https://github.com/rodrigoprestesmachado")
                .formParam("moodleProfile", "5")
                .formParam("moodleAssign", "2")
                .when().post("/verify")
                .then()
                .statusCode(404);
    }
 
    */

}