package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CampeonesObserver implements TopicObserver {

    @Override
    public void onTopicQueried(WorldCupTopic topic, List<Post> results) {
        if (topic != WorldCupTopic.CAMPEONES) {
            return;
        }
        log.info("[Observer::Campeones] Consulta ejecutada — {} campeones encontrados. " +
                 "Campos activos: championYear, titlesCount, lastFinal, goalsInFinals.", results.size());
    }
}
