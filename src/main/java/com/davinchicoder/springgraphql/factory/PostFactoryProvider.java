package com.davinchicoder.springgraphql.factory;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PostFactoryProvider {

    private final Map<String, PostFactory> factories;

    public PostFactoryProvider(
            ParticipantePostFactory participanteFactory,
            CampeonPostFactory campeonFactory,
            CiudadSedePostFactory ciudadSedeFactory) {

        this.factories = Map.of(
            "PARTICIPANTES_2026", participanteFactory,
            "CAMPEONES",          campeonFactory,
            "CIUDADES_SEDE_2026", ciudadSedeFactory
        );
    }

    public PostFactory getFactory(String topic) {
        PostFactory factory = factories.get(topic.toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("No hay fábrica registrada para el tópico: " + topic);
        }
        return factory;
    }
}
