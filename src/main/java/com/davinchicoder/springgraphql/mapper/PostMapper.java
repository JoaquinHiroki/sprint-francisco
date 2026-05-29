package com.davinchicoder.springgraphql.mapper;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostMapper implements Function<PostDto, Post> {

    @Override
    public Post apply(PostDto postDto) {
        return Post.builder()
            .title(postDto.getTitle())
            .imageUrl(postDto.getImageUrl())
            .topic(postDto.getTopic())
            .confederation(postDto.getConfederation())
            .fifaRanking(postDto.getFifaRanking())
            .coach(postDto.getCoach())
            .group(postDto.getGroup())
            .championYear(postDto.getChampionYear())
            .titlesCount(postDto.getTitlesCount())
            .lastFinal(postDto.getLastFinal())
            .goalsInFinals(postDto.getGoalsInFinals())
            .country(postDto.getCountry())
            .stadiumName(postDto.getStadiumName())
            .stadiumCapacity(postDto.getStadiumCapacity())
            .region(postDto.getRegion())
            .build();
    }
}
