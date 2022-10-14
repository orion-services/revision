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
import dev.orion.revision.mappers.moodle.ListUser;
import dev.orion.revision.mappers.moodle.User;

/**
 * Check if the student is enrolled in the course
 *
 * @author Paulo Serpa Antunes
 * @version May. 2022
 */
@ApplicationScoped
public class EnrolledChecker extends AbstractChecker implements Checker {

  private static final Logger LOGGER = Logger.getLogger(EnrolledChecker.class.getName());

  @Override
  public boolean check(Map<String, String> input) {
    LOGGER.info("EnrolledChecker");

    boolean result = false;

    // Get Moodle user
    ListUser mUsers = getMoodleUser(input.get("moodleProfile"));
    // Get list of enrolled students
    ListUser mCourseUsers = getMoodleEnrolledUsers(input.get("moodleAssign"));

    boolean enrolled = false;
    
    for (User user : mCourseUsers) {
      // Verifies if the user are enrolled in this course module
      if (mUsers.getFirstUserName().equalsIgnoreCase(user.getFullname())) {
        enrolled = true;        
        break;
      }
    }

    if (enrolled) {
      LOGGER.info("O aluno " + mUsers.getFirstUserName() + " está matrículado no curso");
      result = this.getNextChecker().check(input);
    } else {
      String message = messages.getString("EnrolledChecker.notEnrolled");
      LOGGER.log(Level.WARNING, message);
      throw new RevisionServiceException(message, Response.Status.BAD_REQUEST);
    }
    
    return result;
  }
}
