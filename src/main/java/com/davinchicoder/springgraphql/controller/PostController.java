package com.davinchicoder.springgraphql.controller;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;
import com.davinchicoder.springgraphql.exception.PostNotFound;
import com.davinchicoder.springgraphql.mapper.PostMapper;
import com.davinchicoder.springgraphql.observer.WorldCupTopic;
import com.davinchicoder.springgraphql.observer.WorldCupTopicSubject;
import com.davinchicoder.springgraphql.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final WorldCupTopicSubject topicSubject;

    /**
     * Query dinámico: filtra por tópico y notifica al observer correspondiente.
     * Valores válidos: PARTICIPANTES_2026 | CAMPEONES | CIUDADES_SEDE_2026
     * Cada tópico expone campos distintos — el cliente decide cuáles solicita.
     */
    @QueryMapping
    public List<Post> getPostsByTopic(@Argument String topic) {
        WorldCupTopic worldCupTopic = WorldCupTopic.valueOf(topic.toUpperCase());
        List<Post> results = postRepository.getPostsByTopic(topic);
        topicSubject.notifyObservers(worldCupTopic, results);
        return results;
    }

    /** Obtiene publicaciones recientes con paginación (count, offset). */
    @QueryMapping
    public List<Post> getRecentPosts(@Argument int count, @Argument int offset) {
        return postRepository.getRecentPosts(count, offset);
    }

    /** Obtiene una entrada por su ID o lanza PostNotFound. */
    @QueryMapping
    public Post getPostById(@Argument Long id) {
        return postRepository.getById(id).orElseThrow(PostNotFound::new);
    }

    /** Obtiene todas las entradas. */
    @QueryMapping
    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    /** Elimina una entrada por ID o lanza PostNotFound. */
    @MutationMapping
    public Post deletePostById(@Argument Long id) {
        return postRepository.delete(id).orElseThrow(PostNotFound::new);
    }

    /** Guarda una nueva entrada a partir del DTO. */
    @MutationMapping
    public Post savePost(@Argument PostDto postDto) {
        Post post = postMapper.apply(postDto);
        return postRepository.save(post);
    }
}
