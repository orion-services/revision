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

package dev.orion.revision.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.yaml.snakeyaml.Yaml;

import dev.orion.revision.clients.Github;
import dev.orion.revision.clients.Moodle;
import dev.orion.revision.mappers.github.Author;
import dev.orion.revision.mappers.github.CommitData;
import dev.orion.revision.mappers.moodle.Assign;
import dev.orion.revision.mappers.moodle.ListCourse;
import dev.orion.revision.mappers.moodle.ListUser;
import dev.orion.revision.mappers.moodle.Module;
import dev.orion.revision.mappers.moodle.User;
/**
 * Implements a base for a checker
 *
 * @author Rodrigo Prestes Machado
 * @version Jan. 2022
 */
public abstract class AbstractChecker {

    /** Store the next checker in the chain */
    protected Checker nextChecker;

    /* REST Clients */
    @Inject
    @RestClient
    protected Github github;

    @Inject
    @RestClient
    protected Moodle moodle;

    /** Configurations */
    @ConfigProperty(name = "moodle.client.wstoken")
    protected String MOODLE_TOKEN;

    @ConfigProperty(name = "moodle.client.moodlewsrestformat")
    protected String MOODLE_JSON_FORMAT;

    @ConfigProperty(name = "moodle.client.wsfunction.users")
    protected String MOODLE_USERS;

    @ConfigProperty(name = "moodle.client.wsfunction.module")
    protected String MOODLE_MODULE;

    @ConfigProperty(name = "moodle.client.wsfunction.enrolled")
    protected String MOODLE_ENROLLED;

    @ConfigProperty(name = "moodle.client.wsfunction.assign")
    protected String MOODLE_ASSIGN;

    @ConfigProperty(name = "moodle.client.wsfunction.grade")
    protected String MOODLE_GRADE;

    /** localized messages */
    protected static ResourceBundle messages;

    /**
     * Discovers the assign intro of a course
     *
     * @param courses :  A list of course (with only one course)
     * @param moodleAssignURL : The URL of the assign
     *
     * @return The intro (description) of one assign
     */
    protected String getAssignIntro(ListCourse courses, String moodleAssignURL) {
        String intro = null;
        List<Assign> assigns = courses.getCourses().get(0).getAssignments();
        Integer idAssign = Integer.valueOf(this.getMoodleId(moodleAssignURL));
        for (Assign assign : assigns) {
            if (assign.getCmid() == idAssign ) {
                intro = assign.getIntro();
                break;
            }
        }
        return intro;
    }

    /**
     * Extracts the YAML from the assign's intro (description). The YAML has
     * information about the repository and the workflow of the original
     * exercise.
     *
     * Example of current YAML format to add in Moodle assign:
     *
     *  <!--
     *      repo: "cpw2-web-storage"
     *      workflow: "npm-test.yml"
     *      test-file: "correcao.js"
     *  -->
     *
     * In this first version the service has the limitation of only one
     * test file
     *
     * @param intro :  The HTML of the assign intro
     * @return A map with 'repo' and 'workflow' keys
     */
    protected Map<String, String> getAssignConfig(String intro) {
        Map<String, String> map = null;
        Document doc = Jsoup.parse(intro);
        Element body = doc.body();

        for (int i = 0; i < body.childNodeSize(); i++) {
            if (body.childNode(i).nodeName().equals("#comment")) {
                Node comment = body.childNode(i);
                String text = comment.outerHtml();
                text = text.substring(4, text.length() - 4);

                Yaml yaml = new Yaml();
                map = yaml.load(text);
            }
        }
        return map;
    }

    /**
     * Returns a list of commits of the user
     *
     * @param commits : A list of commits
     * @param login :  The github login name
     *
     * @return A list of CommitData objects
     */
    protected List<CommitData> checkCommits(List<CommitData> commits, String login) {
        List<CommitData> userCommits = new ArrayList<>();
        for (CommitData commit : commits) {
            Author author = commit.getAuthor();
            if(author != null && author.getLogin().equalsIgnoreCase(login)){
                userCommits.add(commit);
            }
        }
        return userCommits;
    }

    /**
     * Returns a user from Github
     *
     * @param githubProfileURL :  The URL of a profile in Github
     *
     * @return An User object
     */
    protected User getGithubUser(String githubProfileURL){
        return github.getUser(getGithubLogin(githubProfileURL));
    }

    /**
     * Returns a list of users from Moodle
     *
     * @param moodleProfileURL : The URL of a profile in Moodle
     *
     * @return Return a ListUser object from Moodle
     */
    protected ListUser getMoodleUser(String moodleProfileURL){
        return moodle.getUser(MOODLE_TOKEN, MOODLE_USERS, MOODLE_JSON_FORMAT, "id",
        this.getMoodleId(moodleProfileURL));
    }

    /**
     * Returns a list of users from Moodle enrolled in a Course
     *
     * @param courseModule : A course module object
     *
     * @return Return a list of users enrolled in a course module from Moodle
     */
    protected ListUser getMoodleEnrolledUsers(Module courseModule){
        return moodle.getEnrolled(MOODLE_TOKEN, MOODLE_ENROLLED, MOODLE_JSON_FORMAT, courseModule.getCm().getCourse());
    }

    /**
     * Module in this case will be the assign
     * This step discoveries the course id and the instance id (real data base id)
     * The instance id is necessary to update the grade
     *
     * @param moodleAssignURL : The URL of an assign
     *
     * @return Returns the curse module object in Moodle
     */
    protected Module getCourseModule(String moodleAssignURL){
        return moodle.getModule(MOODLE_TOKEN, MOODLE_MODULE, MOODLE_JSON_FORMAT,
                this.getMoodleId(moodleAssignURL));
    }

    /**
     * Returns the courses and the assigns
     * We need this step to retrieve the assign intro (description)
     *
     * @param courseModule : A course module object
     *
     * @return A lists of courses (with only one course)
     */
    protected ListCourse getMoodleCourse(Module courseModule){
        return moodle.getCourses(MOODLE_TOKEN, MOODLE_ASSIGN, MOODLE_JSON_FORMAT,
            courseModule.getCm().getCourse());
    }

    /**
     * Extracts the 'id' at the end of a Moodle URL
     *
     * @param url : URL from Moodle with an 'id' at the end
     *
     * @return A Moodle 'id' in String format
     */
    protected String getMoodleId(String url) {
        int index = url.lastIndexOf('=');
        return url.substring(index + 1);
    }

    /**
     * Extracts the github login from profile URL
     *
     * @param url : github profile URL
     *
     * @return The github login
     */
    protected String getGithubLogin(String url) {
        int index = url.lastIndexOf('/');
        return url.substring(index + 1);
    }

    public Checker getNextChecker() {
        return nextChecker;
    }

    public void setNextChecker(Checker checker) {
        this.nextChecker = checker;
    }

    /**
     * Sets the localization of the strings
     *
     * @param The HTTP Content-Language of the request
     */
    public static ResourceBundle setLocation(String language) {
        Locale locale;
        if (language != null) {
            locale = language.contains("en") ? new Locale("en", "US") : new Locale("pt", "BR");
        } else {
            locale = new Locale("pt", "BR");

        }
        messages = ResourceBundle.getBundle("Messages", locale);
        return messages;
    }
}
