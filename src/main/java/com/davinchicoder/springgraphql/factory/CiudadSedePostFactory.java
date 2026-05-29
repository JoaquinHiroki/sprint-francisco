package com.davinchicoder.springgraphql.factory;

import com.davinchicoder.springgraphql.dto.PostDto;
import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class CiudadSedePostFactory extends PostFactory {

    @Override
    public Post createPost(PostDto postDto) {
        return Post.builder()
            .title(postDto.getTitle())
            .imageUrl(postDto.getImageUrl())
            .topic("CIUDADES_SEDE_2026")
            .country(postDto.getCountry())
            .stadiumName(postDto.getStadiumName())
            .stadiumCapacity(postDto.getStadiumCapacity())
            .region(postDto.getRegion())
            .build();
    }
}
