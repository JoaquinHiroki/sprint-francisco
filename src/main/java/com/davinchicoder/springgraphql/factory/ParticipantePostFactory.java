package com.davinchicoder.springgraphql.factory;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class ParticipantePostFactory extends PostFactory {

    @Override
    public Post createPost(PostDto postDto) {
        return Post.builder()
            .title(postDto.getTitle())
            .imageUrl(postDto.getImageUrl())
            .topic("PARTICIPANTES_2026")
            .confederation(postDto.getConfederation())
            .fifaRanking(postDto.getFifaRanking())
            .coach(postDto.getCoach())
            .group(postDto.getGroup())
            .build();
    }
}
