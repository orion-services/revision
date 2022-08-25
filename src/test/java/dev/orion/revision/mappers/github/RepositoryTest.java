package dev.orion.revision.mappers.github;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.orion.revision.clients.Github;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class RepositoryTest {
   
    Repository repo;

    @Inject
    @RestClient
    protected Github github;
    
    @Test
    @DisplayName("Repository Mapper Test isFork")
    @Order(1)
    void testRepositoryisFork(){
        repo = github.getRepo("graziellarodrigues", "cpw2-web-storage");
        Assertions.assertEquals(true, repo.isFork());
    } 
    @Test
    @DisplayName("Repository Mapper Test id")
    @Order(2)
    void testRepositoryId(){
        repo = github.getRepo("graziellarodrigues", "cpw2-web-storage");
        Assertions.assertEquals("438989221", repo.getId());
    } 
    @Test
    @DisplayName("Repository Mapper Test Parent")
    @Order(3)
    void testRepositoryParent(){
        repo = github.getRepo("graziellarodrigues", "cpw2-web-storage");
        Assertions.assertEquals("435871324", repo.getParent().getId());
    } 
}
