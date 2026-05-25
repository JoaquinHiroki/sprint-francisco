package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;


@Slf4j
@Component
public class ParticipantesObserver implements TopicObserver {

    @Override
    public void onTopicQueried(WorldCupTopic topic, List<Post> results) {
        if (topic != WorldCupTopic.PARTICIPANTES_2026) {
            return;
        }
        log.info("[Observer::Participantes2026] Consulta ejecutada — {} selecciones encontradas. " +
                 "Campos activos: teamName, confederation, fifaRanking, coach.", results.size());
    }
}
