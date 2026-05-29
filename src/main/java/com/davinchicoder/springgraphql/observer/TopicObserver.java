package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;

import java.util.List;

public interface TopicObserver {

    void onTopicQueried(WorldCupTopic topic, List<Post> results);
}
