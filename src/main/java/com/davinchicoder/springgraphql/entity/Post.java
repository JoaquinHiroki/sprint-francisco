package com.davinchicoder.springgraphql.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


/** Entity: representa el modelo interno/persistente (BD) con identidad y estado;
 * DTO: objeto ligero para transferir datos entre capas sin exponer la entidad ni su lógica. */
@Data
@Builder
public class Post {

    private Long id;

    /** Nombre del país/ciudad según el tópico. */
    private String title;
    private String imageUrl;

    /** Tópico al que pertenece: PARTICIPANTES_2026 | CAMPEONES | CIUDADES_SEDE_2026 */
    private String topic;

    // ── PARTICIPANTES_2026: selecciones clasificadas ──────────────────────────
    private String confederation;
    private Integer fifaRanking;
    private String coach;
    private String group;

    // ── CAMPEONES: historial de campeones del mundo ───────────────────────────
    private Integer championYear;
    private Integer titlesCount;
    private String lastFinal;
    private Integer goalsInFinals;

    // ── CIUDADES_SEDE_2026: ciudades anfitrionas ──────────────────────────────
    private String country;
    private String stadiumName;
    private Integer stadiumCapacity;
    private String region;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

}
