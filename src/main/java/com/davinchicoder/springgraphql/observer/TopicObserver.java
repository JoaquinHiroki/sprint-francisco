package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;

import java.util.List;

public interface TopicObserver {

    /**
     * Invocado por el Subject cada vez que se ejecuta una consulta por tópico.
     *
     * @param topic   tópico consultado
     * @param results lista de posts encontrados para ese tópico
     */
    void onTopicQueried(WorldCupTopic topic, List<Post> results);
}
