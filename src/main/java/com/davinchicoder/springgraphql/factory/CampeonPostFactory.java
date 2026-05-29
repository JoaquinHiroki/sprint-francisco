package com.davinchicoder.springgraphql.factory;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CampeonPostFactory extends PostFactory {

    @Override
    public Post createPost(PostDto postDto) {
        return Post.builder()
            .title(postDto.getTitle())
            .imageUrl(postDto.getImageUrl())
            .topic("CAMPEONES")
            .championYear(postDto.getChampionYear())
            .titlesCount(postDto.getTitlesCount())
            .lastFinal(postDto.getLastFinal())
            .goalsInFinals(postDto.getGoalsInFinals())
            .build();
    }
}
