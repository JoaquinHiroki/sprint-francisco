package com.davinchicoder.springgraphql.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    private String title;
    private String imageUrl;
    private String topic;
    private String confederation;
    private Integer fifaRanking;
    private String coach;
    private String group;
    private Integer championYear;
    private Integer titlesCount;
    private String lastFinal;
    private Integer goalsInFinals;
    private String country;
    private String stadiumName;
    private Integer stadiumCapacity;
    private String region;
}
