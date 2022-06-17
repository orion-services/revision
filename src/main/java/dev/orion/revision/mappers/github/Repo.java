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
package dev.orion.revision.mappers.github;

public class Repo {

    
    private String id;

    private Repo parent;
    private Repo source;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Repo getParent() {
        return parent;
    }

    public void setParent(Repo parent) {
        this.parent = parent;
    }

    public Repo getSource() {
        return source;
    }

    public void setSource(Repo source) {
        this.source = source;
    }

    
    
}
