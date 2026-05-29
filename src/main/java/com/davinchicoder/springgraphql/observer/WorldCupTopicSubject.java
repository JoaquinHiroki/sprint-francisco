package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorldCupTopicSubject implements TopicSubject {

    private final List<TopicObserver> observers;

    public WorldCupTopicSubject(List<TopicObserver> observers) {
        this.observers = observers;
    }

    @Override
    public void registerObserver(TopicObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(TopicObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(WorldCupTopic topic, List<Post> results) {
        observers.forEach(observer -> observer.onTopicQueried(topic, results));
    }
}
