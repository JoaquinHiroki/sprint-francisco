package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;

import java.util.List;

/**
 * Credits: DavinchiCoder
 * Interfaz Subject (Observable): define el contrato para gestionar
 * y notificar observadores registrados.
 */
public interface TopicSubject {

    void registerObserver(TopicObserver observer);

    void removeObserver(TopicObserver observer);

    void notifyObservers(WorldCupTopic topic, List<Post> results);
}
