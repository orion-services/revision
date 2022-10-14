package dev.orion.revision.mappers.github;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import dev.orion.revision.clients.Github;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class CommitDataTest {

    CommitData commitData;    

    @Inject
    @RestClient
    protected Github github;

    @Test
    @DisplayName("CommitData Mapper Test getSha")
    @Order(1)
    void testgetSha(){
        List<CommitData> userCommits = new ArrayList<>();
        userCommits = github.getCommits("rpmhub", "cpw2-web-storage");
        Assertions.assertEquals("03da80ba5d57425e7124b44b0932abe778faea35", userCommits.get(0).getSha());
    } 

    @Test
    @DisplayName("CommitData Mapper Test getAutor")
    @Order(2)
    void testgetAuthor(){
        List<CommitData> userCommits = new ArrayList<>();
        userCommits = github.getCommits("rpmhub", "cpw2-web-storage");
        Assertions.assertEquals("rodrigoprestesmachado", userCommits.get(0).getAuthor().getLogin());
    } 

}
