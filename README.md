# Spring GraphQL — Mundial 2026

Backend hecho con Spring Boot + Spring GraphQL que expone datos del Mundial 2026 usando Observer y Factory Method

---

## Integracion de los patrones

La idea central es que cada vez que alguien consulta un tópico (`PARTICIPANTES_2026`, `CAMPEONES` o `CIUDADES_SEDE_2026`), dos cosas pasan de forma independiente:

1. **Factory Method** se encarga de crear los posts con los campos correctos según el tópico.
2. **Observer** se encarga de reaccionar cuando se consulta un tópico por ejemplo logueando cuántos resultados se encontraron.

El controller es el punto donde ambos se encuentran:

```java
// Al guardar: Factory Method elige qué fábrica construye el Post
@MutationMapping
public Post savePost(@Argument PostDto postDto) {
    PostFactory factory = postFactoryProvider.getFactory(postDto.getTopic());
    Post post = factory.createPost(postDto);
    return postRepository.save(post);
}

// Al consultar: Observer notifica a todos los interesados
@QueryMapping
public List<Post> getPostsByTopic(@Argument String topic) {
    List<Post> results = postRepository.getPostsByTopic(topic);
    topicSubject.notifyObservers(WorldCupTopic.valueOf(topic.toUpperCase()), results);
    return results;
}
```

---

## Patrón Observer

Cuando se consulta `getPostsByTopic`, el sistema avisa automáticamente a todos los observadores registrados para ese tópico. El controller no sabe qué observadores existen ni qué hacen solo llama `notifyObservers` y cada quien reacciona por su cuenta.

Hay un observer por tópico (`CampeonesObserver`, `ParticipantesObserver`, `CiudadesSedeObserver`), y Spring los inyecta automáticamente en el subject sin necesidad de registrarlos manualmente:

```java
@Component
public class WorldCupTopicSubject implements TopicSubject {

    private final List<TopicObserver> observers;

    // Spring detecta todos los beans que implementan TopicObserver y los inyecta aquí
    public WorldCupTopicSubject(List<TopicObserver> observers) {
        this.observers = observers;
    }

    @Override
    public void notifyObservers(WorldCupTopic topic, List<Post> results) {
        observers.forEach(observer -> observer.onTopicQueried(topic, results));
    }
}
```

Cada observer filtra por su tópico y actúa solo cuando le corresponde:

```java
@Component
public class CampeonesObserver implements TopicObserver {

    @Override
    public void onTopicQueried(WorldCupTopic topic, List<Post> results) {
        if (topic != WorldCupTopic.CAMPEONES) return;
        log.info("[Observer::Campeones] {} campeones encontrados.", results.size());
    }
}
```

---

## Patrón Factory Method

Cuando alguien guarda un post con `savePost`, el controller no construye el objeto directamente. Le pasa el trabajo a una fábrica concreta según el tópico, y cada fábrica sabe exactamente qué campos llenar:

`PostFactoryProvider` es quien decide qué fábrica usar según el tópico del DTO:

```java
public PostFactory getFactory(String topic) {
    PostFactory factory = factories.get(topic.toUpperCase());
    if (factory == null) throw new IllegalArgumentException("Tópico desconocido: " + topic);
    return factory;
}
```

---

## Ejecución

```bash
JAVA_HOME=$(/usr/libexec/java_home -v 21) ./mvnw spring-boot:run
```

- **Endpoint GraphQL:** `http://localhost:8080/graphql`
- **GraphiQL (playground):** `http://localhost:8080/graphiql`
