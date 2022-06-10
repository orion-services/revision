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

import java.util.Map;
/**
 * The interface od a checker
 *
 * @author Rodrigo Prestes Machado
 * @version Jan. 2022
 */
public interface Checker {

    public boolean check(Map<String, String> input);

    public Checker getNextChecker();

    public void setNextChecker(Checker checker) ;

}
