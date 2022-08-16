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

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class MoodleMock implements QuarkusTestResourceLifecycleManager {

    private WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(8089);
        wireMockServer.start();
        configureFor(8089);

        LinkedHashMap<String, String> map1 = new LinkedHashMap<>();
        map1.put("wstoken", "8328d31ad34d4695c924fe8d2e1b9d02");
        map1.put("wsfunction", "core_user_get_users");
        map1.put("moodlewsrestformat", "json");
        map1.put("criteria%5B0%5D%5Bkey%5D", "id");
        map1.put("criteria%5B0%5D%5Bvalue%5D", "4");

        stubFor(post(urlEqualTo("/webservice/rest/server.php"))
            .withRequestBody(equalTo(WireMockUtil.toFormUrlEncoded(map1)))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"users\":[{\"id\":4,\"username\":\"ws_aluno\",\"firstname\":\"Nome\",\"lastname\":\"Sobrenome\",\"fullname\": \"Nome Sobrenome\",\"email\":\"aluno@email.com\",\"department\": \"\",\"firstaccess\": 1652907241,\"lastaccess\":1652912238,\"auth\":\"manual\",\"suspended\":false,\"confirmed\":true,\"lang\":\"en\",\"theme\": \"\",\"timezone\":\"America/Sao_Paulo\",\"mailformat\":1,\"country\":\"BR\",\"warnings\":[]}")
                .withStatus(200)));

        stubFor(post(urlEqualTo("/webservice/rest/server.php"))
            .withRequestBody(containing("core_course_get_enrolled_users_by_cmid"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"users\":[{\"id\":4,\"profileimage\":\"http://localhost/theme/image.php/boost/core/1651608355/u/f1\",\"fullname\":\"Graziella Rodrigues\",\"firstname\":\"Graziella\",\"lastname\":\"Rodrigues\"},{\"id\":6,\"profileimage\":\"http://localhost/theme/image.php/boost/core/1651608355/u/f1\",\"fullname\":\"wsProfessor\",\"firstname\":\"ws\",\"lastname\":\"Professor\"},{\"id\":7,\"profileimage\":\"http://localhost/theme/image.php/boost/core/1651608355/u/f1\",\"fullname\":\"Alexandre Fabian\",\"firstname\":\"Alexandre\",\"lastname\":\"Fabian\"}],\"warnings\":[]}")
                .withStatus(200)));  

        stubFor(post(urlEqualTo("/webservice/rest/server.php"))
            .withRequestBody(containing("core_course_get_course_module"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"cm\":{\"id\":2,\"course\":2,\"module\":1,\"name\":\"Primeiro teste\",\"modname\":\"assign\",\"instance\":1,\"section\":1,\"sectionnum\":0,\"groupmode\":0,\"groupingid\":0,\"completion\":1,\"idnumber\":\"\",\"added\":1652471392,\"score\":0,\"indent\":0,\"visible\":1,\"visibleoncoursepage\":1,\"visibleold\":1,\"completiongradeitemnumber\":null,\"completionview\":0,\"completionexpected\":0,\"showdescription\":0,\"availability\":null,\"grade\":100,\"gradepass\":\"0.00\",\"gradecat\":1,\"advancedgrading\":[{\"area\":\"submissions\",\"method\":null}]},\"warnings\":[]}")
                .withStatus(200))); 

        stubFor(post(urlEqualTo("/webservice/rest/server.php"))
            .withRequestBody(containing("mod_assign_get_assignments"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"courses\":[{\"id\":2,\"fullname\":\"Curso teste ws\",\"shortname\":\"test_ws\",\"timemodified\":1652471215,\"assignments\":[{\"id\":1,\"cmid\":2,\"course\":2,\"name\":\"Primeiro teste\",\"nosubmissions\":0,\"submissiondrafts\":0,\"sendnotifications\":0,\"sendlatenotifications\":0,\"sendstudentnotifications\":1,\"duedate\":1684551600,\"allowsubmissionsfromdate\":1652410800,\"grade\":100,\"timemodified\":1653163463,\"completionsubmit\":1,\"cutoffdate\":0,\"gradingduedate\":1685156400,\"teamsubmission\":0,\"requireallteammemberssubmit\":0,\"teamsubmissiongroupingid\":0,\"blindmarking\":0,\"hidegrader\":0,\"revealidentities\":0,\"attemptreopenmethod\":\"none\",\"maxattempts\":-1,\"markingworkflow\":0,\"markingallocation\":0,\"requiresubmissionstatement\":0,\"preventsubmissionnotingroup\":0,\"configs\":[{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"enabled\",\"value\":\"1\"},{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"maxfilesubmissions\",\"value\":\"20\"},{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"maxsubmissionsizebytes\",\"value\":\"41943040\"},{\"plugin\":\"file\",\"subtype\":\"assignsubmission\",\"name\":\"filetypeslist\",\"value\":\"\"},{\"plugin\":\"comments\",\"subtype\":\"assignsubmission\",\"name\":\"enabled\",\"value\":\"1\"},{\"plugin\":\"comments\",\"subtype\":\"assignfeedback\",\"name\":\"enabled\",\"value\":\"1\"},{\"plugin\":\"comments\",\"subtype\":\"assignfeedback\",\"name\":\"commentinline\",\"value\":\"0\"}],\"intro\":\"<p dir=\\\"ltr\\\" style=\\\"text-align: left;\\\">sduhsaduash</p>\\r\\n<p dir=\\\"ltr\\\" style=\\\"text-align: left;\\\">asdhasudhasd</p>\\r\\n<p dir=\\\"ltr\\\" style=\\\"text-align: left;\\\"><br></p>\\r\\n<!--\\r\\n    repo: \\\"cpw2-web-storage\\\"\\r\\n    workflow: \\\"npm-test.yml\\\"\\r\\n    test-file: \\\"correcao.js\\\"\\r\\n-->\",\"introformat\":1,\"introfiles\":[],\"introattachments\":[]}]}],\"warnings\":[]}")
                .withStatus(200))); 

        stubFor(post(urlEqualTo("/webservice/rest/server.php"))
            .withRequestBody(containing("mod_assign_save_grade"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("")
                .withStatus(200)));               

            return Collections.singletonMap("dev.orion.revision.clients.Moodle/mp-rest/url", wireMockServer.baseUrl());
            
    }

    @Override
    public void stop(){
        if (null != wireMockServer) {
            wireMockServer.stop();
        }
    }

    public static class WireMockUtil {

        public static String toFormUrlEncoded(LinkedHashMap<String, String> map) {
            if (map == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = map.get(key);
                appendFormUrlEncoded(key, value, sb);
                if (it.hasNext()) {
                    sb.append('&');
                }
            }
            return sb.toString();
        }

        public static String toFormUrlEncoded(String key, String value) {
            StringBuilder sb = new StringBuilder();
            appendFormUrlEncoded(key, value, sb);
            return sb.toString();
        }

        public static void appendFormUrlEncoded(String key, String value, StringBuilder sb) {
            sb.append(key).append('=');
            if (value != null) {
                sb.append(value);
            }
        }
    }
}
