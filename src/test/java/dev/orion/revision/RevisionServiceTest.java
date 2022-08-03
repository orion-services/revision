package dev.orion.revision;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.google.inject.Inject;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;


@QuarkusTest

class RevisionServiceTest {

    @Inject
    RevisionService service;

    @TestHTTPEndpoint(RevisionService.class) 
    
    @Test
    @DisplayName("Test q input")
    @Order(1)
    void empty() {
        given()
            .formParam("githubProfileURL", "")
            .formParam("moodleProfileURL", "")
            .formParam("moodleAssignURL", "")
            .when().post()
            .then()
            .statusCode(400);
            
            

            //.body("parameterViolations", (equalTo("<[{constraintType=PARAMETER, path=check.githubProfileURL, message=não deve estar em branco, value=}, {constraintType=PARAMETER, path=check.moodleProfileURL, message=não deve estar em branco, value=}, {constraintType=PARAMETER, path=check.moodleAssignURL, message=não deve estar em branco, value=}]>")) );
            
    }
    // @TestHTTPEndpoint(RevisionService.class) 
    // @Test
    // @DisplayName("Test wrongs inputs")
    // @Order(2)
    // void wrongUser() {
    //      given()
    //         //.accept(ContentType.JSON)
    //         .formParam("githubProfileURL", "https://github.com")
    //         .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
    //         .formParam("moodleAssignURL", "https://moodle.poa.ifrs.edu.br")
    //         .when().post()
    //         .then()
    //         .statusCode(500);
    //        // .body(is("Rodrigo Prestes Machado"));
            
    //  }

     @Test
    @DisplayName("Test wrong Moodle user")
    @Order(3)
    void wrongMoodleUser() {
         given()
            .formParam("githubProfileURL", "https://github.com/amfabian")
            .formParam("moodleProfileURL", "https://moodle.poa.ifrs.edu.br")
            .formParam("moodleAssignURL", "http://localhost/mod/assign/view.php?id=2")
            .when().post()
            .then()
            .statusCode(404);
     }

    @Test
    @DisplayName("Test wrong Github user")
    @Order(4)
    void wrongGithubUser() {
         given()
            .formParam("githubProfileURL", "https://github.com")
            .formParam("moodleProfileURL", "http://localhost/user/profile.php?id=4")
            .formParam("moodleAssignURL", "http://localhost/mod/assign/view.php?id=2")
            .when().post()
            .then()
            .statusCode(404);
     }

    //  @Test
    // @DisplayName("Test wrong Assign")
    // @Order(5)
    // void wrongAssign() {
    //      given()
    //         .formParam("githubProfileURL", "https://github.com/graziellarodrigues")
    //         .formParam("moodleProfileURL", "http://localhosts/user/profile.php?id=4")
    //         .formParam("moodleAssignURL", "https://moodle.poa.ifrs.edu.br")
    //         .when().post()
    //         .then()
    //         .statusCode(404);
    // }
             
     
    // @Test
    // @DisplayName("Test wrong moodle")ss
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