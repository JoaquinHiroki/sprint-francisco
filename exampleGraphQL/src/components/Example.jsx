import { useEffect, useState } from "react";

export default function Example() {

    /*
    ==================================================
    SUBJECT (ESTADO OBSERVADO)
    ==================================================

    React observará cambios en "topic".

    Cuando "topic" cambie:
    - React notificará componentes
    - useEffect reaccionará automáticamente
    - GraphQL cambiará el query

    Esto representa el patrón Observer.
    */
    const [topic, setTopic] = useState("PARTICIPANTES_2026");



    /*
    ==================================================
    ESTADO DE ENTRADAS DEL MUNDIAL
    ==================================================

    Guarda la información obtenida desde GraphQL.
    */
    const [entries, setEntries] = useState([]);



    /*
    ==================================================
    ESTADO DE ERRORES
    ==================================================
    */
    const [error, setError] = useState(null);



    /*
    ==================================================
    QUERIES DINÁMICOS
    ==================================================

    Cada tópico solicita información distinta.

    Esto simula cómo una app real solicita
    diferentes datos dependiendo de la vista.
    */
    const queries = {

        PARTICIPANTES_2026: `
            query {
                getPostsByTopic(topic: "PARTICIPANTES_2026") {
                    id
                    title
                    confederation
                    fifaRanking
                    coach
                    group
                }
            }
        `,

        CAMPEONES: `
            query {
                getPostsByTopic(topic: "CAMPEONES") {
                    id
                    title
                    championYear
                    titlesCount
                    lastFinal
                    goalsInFinals
                }
            }
        `,

        CIUDADES_SEDE_2026: `
            query {
                getPostsByTopic(topic: "CIUDADES_SEDE_2026") {
                    id
                    title
                    country
                    stadiumName
                    stadiumCapacity
                    region
                }
            }
        `
    };



    /*
    ==================================================
    OBSERVER
    ==================================================

    useEffect está OBSERVANDO "topic".

    El arreglo [topic] funciona como:
    - lista de dependencias
    - suscripción
    - observer

    Cuando topic cambia:
    - React detecta el cambio
    - ejecuta automáticamente getEntries()

    Esto es comportamiento Observer.
    */
    useEffect(() => {

        getEntries();

    }, [topic]);



    /*
    ==================================================
    FETCH GRAPHQL
    ==================================================
    */
    const getEntries = async () => {

        try {

            /*
            ==========================================
            QUERY DINÁMICO
            ==========================================

            Dependiendo del tópico seleccionado,
            se obtiene un query diferente.
            */
            const query = queries[topic];



            const res = await fetch(
                "http://localhost:8080/graphql",
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify({ query })
                }
            );



            const data = await res.json();

            console.log(data);



            /*
            ==========================================
            MANEJO DE ERRORES
            ==========================================
            */
            if (data.errors) {

                setError(data.errors[0]?.message);

                setEntries([]);

                return;
            }



            /*
            ==========================================
            ACTUALIZACIÓN REACTIVA
            ==========================================

            React actualizará automáticamente la UI
            cuando entries cambie.
            */
            setEntries(
                data?.data?.getPostsByTopic ?? []
            );



            setError(null);

        } catch (err) {

            console.log(err);

            setError("Error de conexión — ¿está corriendo el backend?");

            setEntries([]);
        }
    };



    return (

        <div>

            <h1>Mundial 2026 — Dynamic GraphQL</h1>

            <p>
                Tópico actual:
                <strong>
                    {" "}
                    {topic}
                </strong>
            </p>



            {/* ======================================
                BOTONES
            ======================================

            Cuando se hace click:
            - cambia topic
            - React detecta cambio
            - useEffect reacciona
            - GraphQL cambia query
            - UI cambia automáticamente

            Flujo completo Observer.
            ====================================== */}
            <div className="genres">

                <button
                    onClick={() => setTopic("PARTICIPANTES_2026")}
                >
                    Participantes 2026
                </button>



                <button
                    onClick={() => setTopic("CAMPEONES")}
                >
                    Campeones
                </button>



                <button
                    onClick={() => setTopic("CIUDADES_SEDE_2026")}
                >
                    Ciudades Sede 2026
                </button>

            </div>



            <br />



            {error && (

                <p style={{ color: "red" }}>
                    {error}
                </p>

            )}



            {/* ======================================
                RENDERIZADO REACTIVO
            ======================================

            Cuando entries cambia:
            React vuelve a renderizar automáticamente.
            */}
            <div className="movies-container">

                {
                    entries.map((entry) => (

                        <div
                            className="movie-card"
                            key={entry.id}
                        >

                            <h3>
                                {entry.title}
                            </h3>



                            {/* PARTICIPANTES_2026 */}
                            {entry.confederation && (

                                <p>
                                    Confederación:
                                    {" "}
                                    {entry.confederation}
                                </p>

                            )}



                            {entry.fifaRanking && (

                                <p>
                                    Ranking FIFA:
                                    {" "}
                                    #{entry.fifaRanking}
                                </p>

                            )}



                            {entry.coach && (

                                <p>
                                    Entrenador:
                                    {" "}
                                    {entry.coach}
                                </p>

                            )}



                            {entry.group && (

                                <p>
                                    Grupo:
                                    {" "}
                                    {entry.group}
                                </p>

                            )}



                            {/* CAMPEONES */}
                            {entry.championYear && (

                                <p>
                                    Año campeón:
                                    {" "}
                                    {entry.championYear}
                                </p>

                            )}



                            {entry.titlesCount && (

                                <p>
                                    Títulos totales:
                                    {" "}
                                    {entry.titlesCount}
                                </p>

                            )}



                            {entry.lastFinal && (

                                <p>
                                    Última final vs:
                                    {" "}
                                    {entry.lastFinal}
                                </p>

                            )}



                            {entry.goalsInFinals && (

                                <p>
                                    Goles en finales:
                                    {" "}
                                    {entry.goalsInFinals}
                                </p>

                            )}



                            {/* CIUDADES_SEDE_2026 */}
                            {entry.country && (

                                <p>
                                    País:
                                    {" "}
                                    {entry.country}
                                </p>

                            )}



                            {entry.stadiumName && (

                                <p>
                                    Estadio:
                                    {" "}
                                    {entry.stadiumName}
                                </p>

                            )}



                            {entry.stadiumCapacity && (

                                <p>
                                    Capacidad:
                                    {" "}
                                    {entry.stadiumCapacity.toLocaleString()}
                                </p>

                            )}



                            {entry.region && (

                                <p>
                                    Región:
                                    {" "}
                                    {entry.region}
                                </p>

                            )}

                        </div>
                    ))
                }

            </div>

        </div>
    );
}
