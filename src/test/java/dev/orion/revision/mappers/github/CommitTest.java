package dev.orion.revision.mappers.github;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.orion.revision.clients.Github;
import dev.orion.revision.mappers.moodle.User;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CommitTest {
    
    Commit commit;
    User user;

    @Inject
    @RestClient
    protected Github github;

    @Test
    @DisplayName("Commit Mapper Test Login")
    @Order(1)
    void testgetUser(){
       user = github.getUser("rpmhub");
        Assertions.assertEquals("rpmhub", user.getLogin());
    } 

    @Test
    @DisplayName("Commit Mapper Test Login")
    @Order(2)
    void testCommitgetAuthorgetLogin(){
       commit = github.getCommit("rpmhub", "cpw2-web-storage", "beed0461c687a09fb9bbb5a43d9a1c12b47c0b26");
        Assertions.assertEquals("rodrigoprestesmachado", commit.getAuthor().getLogin());
    } 

    @Test
    @DisplayName("Commit Mapper Test Files")
    @Order(3)
    void testCommitgetFilesSize(){
       commit = github.getCommit("rpmhub", "cpw2-web-storage", "beed0461c687a09fb9bbb5a43d9a1c12b47c0b26");
        Assertions.assertEquals(3, commit.getFiles().size());
    } 
    
}
