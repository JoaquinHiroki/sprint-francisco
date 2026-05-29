package com.davinchicoder.springgraphql.repository;

import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private final List<Post> POSTS = new ArrayList<>(

        List.of(

            Post.builder()
                .id(1L)
                .title("Argentina")
                .topic("PARTICIPANTES_2026")
                .confederation("CONMEBOL")
                .fifaRanking(1)
                .coach("Lionel Scaloni")
                .group("J")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(2L)
                .title("France")
                .topic("PARTICIPANTES_2026")
                .confederation("UEFA")
                .fifaRanking(2)
                .coach("Didier Deschamps")
                .group("I")
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(3L)
                .title("Spain")
                .topic("PARTICIPANTES_2026")
                .confederation("UEFA")
                .fifaRanking(3)
                .coach("Luis de la Fuente")
                .group("H")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(4L)
                .title("Brazil")
                .topic("PARTICIPANTES_2026")
                .confederation("CONMEBOL")
                .fifaRanking(5)
                .coach("Ancelotti")
                .group("C")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(5L)
                .title("Mexico")
                .topic("PARTICIPANTES_2026")
                .confederation("CONCACAF")
                .fifaRanking(15)
                .coach("Javier Aguirre")
                .group("A")
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(6L)
                .title("Brazil")
                .topic("CAMPEONES")
                .championYear(2002)
                .titlesCount(5)
                .lastFinal("Germany")
                .goalsInFinals(18)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(7L)
                .title("Germany")
                .topic("CAMPEONES")
                .championYear(2014)
                .titlesCount(4)
                .lastFinal("Argentina")
                .goalsInFinals(18)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(8L)
                .title("Argentina")
                .topic("CAMPEONES")
                .championYear(2022)
                .titlesCount(3)
                .lastFinal("France")
                .goalsInFinals(15)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(9L)
                .title("France")
                .topic("CAMPEONES")
                .championYear(2018)
                .titlesCount(2)
                .lastFinal("Croatia")
                .goalsInFinals(12)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(10L)
                .title("Spain")
                .topic("CAMPEONES")
                .championYear(2010)
                .titlesCount(1)
                .lastFinal("Netherlands")
                .goalsInFinals(8)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(11L)
                .title("New York / New Jersey")
                .topic("CIUDADES_SEDE_2026")
                .country("USA")
                .stadiumName("MetLife Stadium")
                .stadiumCapacity(82500)
                .region("Northeast")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(12L)
                .title("Los Angeles")
                .topic("CIUDADES_SEDE_2026")
                .country("USA")
                .stadiumName("SoFi Stadium")
                .stadiumCapacity(70240)
                .region("West")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(13L)
                .title("Dallas")
                .topic("CIUDADES_SEDE_2026")
                .country("USA")
                .stadiumName("AT&T Stadium")
                .stadiumCapacity(80000)
                .region("South")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(14L)
                .title("Mexico City")
                .topic("CIUDADES_SEDE_2026")
                .country("Mexico")
                .stadiumName("Estadio Azteca")
                .stadiumCapacity(87000)
                .region("Central")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build(),

            Post.builder()
                .id(15L)
                .title("Toronto")
                .topic("CIUDADES_SEDE_2026")
                .country("Canada")
                .stadiumName("BMO Field")
                .stadiumCapacity(45000)
                .region("East")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build()
        )
    );

    public List<Post> getPostsByTopic(String topic) {
        return POSTS.stream()
                .filter(post -> post.getDeletedAt() == null)
                .filter(post -> post.getTopic().equalsIgnoreCase(topic))
                .toList();
    }

    public List<Post> getRecentPosts(int count, int offset) {
        return POSTS.stream()
                .filter(post -> post.getDeletedAt() == null)
                .toList()
                .subList(offset, Math.min(offset + count, POSTS.size()));
    }

    public Post save(Post post) {
        post.setId(this.getNextId());
        post.setCreatedAt(LocalDateTime.now());
        POSTS.add(post);
        return post;
    }

    public Optional<Post> delete(Long id) {
        Optional<Post> postToDelete = POSTS.stream()
                .filter(post -> post.getId().equals(id))
                .findFirst();
        postToDelete.ifPresent(post -> post.setDeletedAt(LocalDateTime.now()));
        return postToDelete;
    }

    public Optional<Post> getById(Long id) {
        return POSTS.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    public List<Post> getAll() {
        return POSTS.stream().filter(post -> post.getDeletedAt() == null).toList();
    }

    private Long getNextId() {
        return POSTS.stream().mapToLong(Post::getId).max().orElse(0L) + 1L;
    }
}
