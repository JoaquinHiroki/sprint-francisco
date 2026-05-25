package com.davinchicoder.springgraphql.observer;

import com.davinchicoder.springgraphql.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class CiudadesSedeObserver implements TopicObserver {

    @Override
    public void onTopicQueried(WorldCupTopic topic, List<Post> results) {
        if (topic != WorldCupTopic.CIUDADES_SEDE_2026) {
            return;
        }
        log.info("[Observer::CiudadesSede2026] Consulta ejecutada — {} ciudades sede encontradas. " +
                 "Campos activos: country, stadiumName, stadiumCapacity, region.", results.size());
    }
}
