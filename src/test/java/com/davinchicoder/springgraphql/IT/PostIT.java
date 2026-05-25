package com.davinchicoder.springgraphql.IT;

import com.davinchicoder.springgraphql.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetAllPosts() {
        String query = """
                query {
                    getAllPosts {
                        id
                        title
                        topic
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("data.getAllPosts")
                .entityList(Post.class)
                .hasSize(15);
    }

    @Test
    void shouldCreatePost() {
        String mutation = """
                mutation {
                    savePost(postDto: {
                        title: "United States",
                        topic: "PARTICIPANTES_2026",
                        confederation: "CONCACAF",
                        fifaRanking: 14,
                        coach: "Mauricio Pochettino",
                        group: "F"
                    }) {
                        id
                        title
                        topic
                        confederation
                        fifaRanking
                    }
                }
                """;

        graphQlTester.document(mutation)
                .execute()
                .path("data.savePost")
                .entity(Post.class)
                .satisfies(post -> {
                    assertNotNull(post.getId());
                    assertEquals("United States", post.getTitle());
                    assertEquals("PARTICIPANTES_2026", post.getTopic());
                    assertEquals("CONCACAF", post.getConfederation());
                });
    }
}
