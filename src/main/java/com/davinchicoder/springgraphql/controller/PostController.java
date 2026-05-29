package com.davinchicoder.springgraphql.controller;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;
import com.davinchicoder.springgraphql.exception.PostNotFound;
import com.davinchicoder.springgraphql.factory.PostFactory;
import com.davinchicoder.springgraphql.factory.PostFactoryProvider;
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
    private final PostFactoryProvider postFactoryProvider;
    private final WorldCupTopicSubject topicSubject;

    @QueryMapping
    public List<Post> getPostsByTopic(@Argument String topic) {
        WorldCupTopic worldCupTopic = WorldCupTopic.valueOf(topic.toUpperCase());
        List<Post> results = postRepository.getPostsByTopic(topic);
        topicSubject.notifyObservers(worldCupTopic, results);
        return results;
    }

    @QueryMapping
    public List<Post> getRecentPosts(@Argument int count, @Argument int offset) {
        return postRepository.getRecentPosts(count, offset);
    }

    @QueryMapping
    public Post getPostById(@Argument Long id) {
        return postRepository.getById(id).orElseThrow(PostNotFound::new);
    }

    @QueryMapping
    public List<Post> getAllPosts() {
        return postRepository.getAll();
    }

    @MutationMapping
    public Post deletePostById(@Argument Long id) {
        return postRepository.delete(id).orElseThrow(PostNotFound::new);
    }

    @MutationMapping
    public Post savePost(@Argument PostDto postDto) {
        PostFactory factory = postFactoryProvider.getFactory(postDto.getTopic());
        Post post = factory.createPost(postDto);
        return postRepository.save(post);
    }
}
