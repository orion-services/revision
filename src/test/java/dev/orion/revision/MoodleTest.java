/**
 * Copyright 2022 Orion @ https://github.com/orion-services/revision
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.orion.revision;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
@QuarkusTestResource(MoodleMock.class)
class MoodleTest {

    @Test
    @DisplayName("Test Moodle core_user_get_users")
    @Order(1)
    void testGetUser() {
        given()
        .formParam("wstoken", "8328d31ad34d4695c924fe8d2e1b9d02")
        .formParam("wsfunction", "core_user_get_users")
        .formParam("moodlewsrestformat", "json")
        .formParam("criteria[0][key]", "id")
        .formParam("criteria[0][value]", "4")
        .when().post("http://localhost:8089/webservice/rest/server.php")
        .then()
        .body(is("{\"users\":[{\"id\":4,\"username\":\"ws_aluno\",\"firstname\":\"Nome\",\"lastname\":\"Sobrenome\",\"fullname\": \"Nome Sobrenome\",\"email\":\"aluno@email.com\",\"department\": \"\",\"firstaccess\": 1652907241,\"lastaccess\":1652912238,\"auth\":\"manual\",\"suspended\":false,\"confirmed\":true,\"lang\":\"en\",\"theme\": \"\",\"timezone\":\"America/Sao_Paulo\",\"mailformat\":1,\"country\":\"BR\",\"warnings\":[]}"))
        .statusCode(200);
    }

    @Test
    @DisplayName("Test Moodle core_course_get_enrolled_users_by_cmid")
    @Order(2)
    void testGetEnrolled() {
        given()
        .formParam("wstoken", "8328d31ad34d4695c924fe8d2e1b9d02")
        .formParam("wsfunction", "core_course_get_enrolled_users_by_cmid")
        .formParam("moodlewsrestformat", "json")
        .formParam("cmid", "2")
        .when().post("http://localhost:8089/webservice/rest/server.php")
        .then()
        .body(is("{\"users\":[{\"id\":4,\"profileimage\":\"http://localhost/theme/image.php/boost/core/1651608355/u/f1\",\"fullname\":\"Graziella Rodrigues\",\"firstname\":\"Graziella\",\"lastname\":\"Rodrigues\"},{\"id\":6,\"profileimage\":\"http://localhost/theme/image.php/boost/core/1651608355/u/f1\",\"fullname\":\"wsProfessor\",\"firstname\":\"ws\",\"lastname\":\"Professor\"},{\"id\":7,\"profileimage\":\"http://localhost/theme/image.php/boost/core/1651608355/u/f1\",\"fullname\":\"Alexandre Fabian\",\"firstname\":\"Alexandre\",\"lastname\":\"Fabian\"}],\"warnings\":[]}"))
        .statusCode(200);
    }

    @Test
    @DisplayName("Test Moodle core_course_get_course_module")
    @Order(3)
    void testGetModule() {
        given()
        .formParam("wstoken", "8328d31ad34d4695c924fe8d2e1b9d02")
        .formParam("wsfunction", "core_course_get_course_module")
        .formParam("moodlewsrestformat", "json")
        .formParam("courseids[0]", "2")
        .when().post("http://localhost:8089/webservice/rest/server.php")
        .then()
        .body(is("{\"cm\":{\"id\":2,\"course\":2,\"module\":1,\"name\":\"Primeiro teste\",\"modname\":\"assign\",\"instance\":1,\"section\":1,\"sectionnum\":0,\"groupmode\":0,\"groupingid\":0,\"completion\":1,\"idnumber\":\"\",\"added\":1652471392,\"score\":0,\"indent\":0,\"visible\":1,\"visibleoncoursepage\":1,\"visibleold\":1,\"completiongradeitemnumber\":null,\"completionview\":0,\"completionexpected\":0,\"showdescription\":0,\"availability\":null,\"grade\":100,\"gradepass\":\"0.00\",\"gradecat\":1,\"advancedgrading\":[{\"area\":\"submissions\",\"method\":null}]},\"warnings\":[]}"))
        .statusCode(200);
    }

    @Test
    @DisplayName("Test Moodle mod_assign_get_assignments")
    @Order(4)
    void testGetCourses() {
        given()
        .formParam("wstoken", "8328d31ad34d4695c924fe8d2e1b9d02")
        .formParam("wsfunction", "mod_assign_get_assignments")
        .formParam("moodlewsrestformat", "json")
        .formParam("courseids[0]", "2")
        .when().post("http://localhost:8089/webservice/rest/server.php")
        .then()
        .body(is("{\"courses\":[{\"id\":2,\"fullname\":\"Curso teste ws\",\"shortname\":\"test_ws\",\"timemodified\":1652471215,\"assignments\":[{\"id\":1,\"cmid\":2,\"course\":2,\"name\":\"Primeiro teste\",\"nosubmissions\":0,\"submissiondrafts\":0,\"sendnotifications\":0,\"sendlatenotifications\":0,\"sendstudentnotifications\":1,\"duedate\":1684551600,\"allowsubmissionsfromdate\":1652410800,\"grade\":100,\"timemodified\":1653163463,\"completionsubmit\":1,\"cutoffdate\":0,\"gradingduedate\":1685156400,\"teamsubmission\":0,\"requireallteammemberssubmit\":0,\"teamsubmissiongroupingid\":0,\"blindmarking\":0,\"hidegrader\":0,\"revealidentities\":0,\"attemptreopenmethod\":\"none\",\"maxattempts\":-1,\"markingworkflow\":0,\"markingallocation\":0,\"requiresubmissionstatement\":0,\"preventsubmissionnotingroup\":0,\"configs\":[{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"enabled\",\"value\":\"1\"},{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"maxfilesubmissions\",\"value\":\"20\"},{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"maxsubmissionsizebytes\",\"value\":\"41943040\"},{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"filetypeslist\",\"value\":\"\"},{\"plugin\":\"comments\",\"subtype\":\"assignsubmission\",\"name\":\"enabled\",\"value\":\"1\"},{\"plugin\":\"comments\",\"subtype\":\"assignfeedback\",\"name\":\"enabled\",\"value\":\"1\"},{\"plugin\":\"comments\",\"subtype\":\"assignfeedback\",\"name\":\"commentinline\",\"value\":\"0\"}],\"intro\":\"<p dir=\\\"ltr\\\" style=\\\"text-align: left;\\\">sduhsaduash</p>\\r\\n<p dir=\\\"ltr\\\" style=\\\"text-align: left;\\\">asdhasudhasd</p>\\r\\n<p dir=\\\"ltr\\\" style=\\\"text-align: left;\\\"><br></p>\\r\\n<!--\\r\\n    repo: \\\"cpw2-web-storage\\\"\\r\\n    workflow: \\\"npm-test.yml\\\"\\r\\n    test-file: \\\"correcao.js\\\"\\r\\n-->\",\"introformat\":1,\"introfiles\":[],\"introattachments\":[]}]}],\"warnings\":[]}"))
        .statusCode(200);
    }

    @Test
    @DisplayName("Test Moodle mod_assign_save_grade")
    @Order(5)
    void testUpdateGrade() {
        given()
        .formParam("wstoken", "8328d31ad34d4695c924fe8d2e1b9d02")
        .formParam("wsfunction", "mod_assign_save_grade")
        .formParam("moodlewsrestformat", "json")
        .formParam("assignmentid", "1")
        .formParam("userid", "4")
        .formParam("grade", "6")
        .formParam("attemptnumber", "-1")
        .formParam("addattempt", "1")
        .formParam("workflowstate", "rpm")
        .formParam("applytoall", "1")
        .formParam("plugindata[assignfeedbackcomments_editor][text]", "3a695b63-d9d4-43bb-b8aa-df9351a8c51f")
        .formParam("plugindata[assignfeedbackcomments_editor][format]", "2")
        .when().post("http://localhost:8089/webservice/rest/server.php")
        .then()
        .body(is(""))
        .statusCode(200);
    }     
}