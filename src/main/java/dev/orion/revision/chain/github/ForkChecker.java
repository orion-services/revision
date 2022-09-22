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



package dev.orion.revision.chain.github;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

import dev.orion.revision.chain.AbstractChecker;
import dev.orion.revision.chain.Checker;
import dev.orion.revision.exceptions.RevisionServiceException;
import dev.orion.revision.mappers.github.Repository;
import dev.orion.revision.mappers.moodle.ListCourse;
import dev.orion.revision.mappers.moodle.Module;

/** 
 * Check if repository is forked from original repository 
 * 
 * @author Alexandre de Mesquita Fabian
 * @version May. 2022
*/

@ApplicationScoped
public class ForkChecker extends AbstractChecker implements Checker  {
    
    private static final Logger LOGGER = Logger.getLogger(ForkChecker.class.getName());

    @Override
    public boolean check(Map<String, String> input) {
        LOGGER.info("ForkChecker");

        boolean result = false;
        String githubLogin = getGithubLogin(input.get("githubProfileURL"));
        
        String moodleAssign = input.get("moodleAssign");
        Module module = getCourseModule(moodleAssign);
        ListCourse courses = getMoodleCourse(module);
        String intro = this.getAssignIntro(courses, moodleAssign);
        Repository repo = github.getRepo(githubLogin, this.getAssignConfig(intro).get("repo"));
        
        if(repo.getParent().getId().equalsIgnoreCase(repo.getSource().getId())){
            
            LOGGER.info("É um fork do repositório original e não de outro fork");
            result = this.getNextChecker().check(input);
        } else {
            String message = messages.getString("ForkChecker.repo");
            LOGGER.log(Level.WARNING, message);
            throw new RevisionServiceException(message, Response.Status.BAD_REQUEST);
        }


        return result;
    }
}
