package dev.orion.revision;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@QuarkusTest
class RevisionServiceTest {

/*
 * @TestHTTPEndpoint(RevisionService.class)
 */


    @Test
    @DisplayName("Test empty input")
    @Order(1)
    void empty() {
        RestAssured.baseURI = "http://localhost:8080";
        given()
            .formParam("githubProfileURL", "")
            .formParam("moodleProfileURL", "")
            .formParam("moodleAssignURL", "")
            .when().post("/check")
            .then()
            .statusCode(400)
            .body(containsString("exception"));
    }

    @Test
    @DisplayName("Test wrong Github user")
    @Order(2)
    void wrongUser() {
        RestAssured.baseURI = "http://localhost:8080";
        given()
            .formParam("githubProfileURL", "https://github.com")
            .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
            .formParam("moodleAssignURL", "https://moodle.poa.ifrs.edu.br")
            .when().post("/check")
            .then()
            .statusCode(400)
            .body(is("{\"Message\":\"Não foi possível completar a sua requisição, possivelmente você informou um usuário do Github inexistente\"}"));
     }
     
    @Test
    @DisplayName("Send data with success")
    @Order(2)
    void sucessfull() {
        RestAssured.baseURI = "http://localhost:8080";
        given()
            .formParam("githubProfileURL", "https://github.com/graziellarodrigues")
            .formParam("moodleProfileURL", "http://www.moodledeteste.kinghost.net/moodle/user/profile.php?id=4")
            .formParam("moodleAssignURL", "http://www.moodledeteste.kinghost.net/moodle/mod/assign/view.php?id=2")
            .when().post("/check")
            .then()
            .statusCode(200)
            .body(is("{\"Message\":\"Tarefa enviada com sucesso!\"}"));
    }
 

    /*     
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