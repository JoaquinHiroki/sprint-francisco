package com.davinchicoder.springgraphql.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Post {

    private Long id;
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

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
