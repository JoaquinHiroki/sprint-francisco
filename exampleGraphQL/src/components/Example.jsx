import { useState } from "react";

export default function Example() {
    // Estado para guardar los posts obtenidos del backend
    const [posts, setPosts] = useState([]);
    // Estado que define qué campos queremos pedir dinámicamente a GraphQL
    const [fields, setFields] = useState(["title"]);
    // Estado para manejar errores (GraphQL o conexión)
    const [error, setError] = useState(null);

    // Función que agrega o quita campos del arreglo (checkboxes)
    const toggleField = (value) => {
        setFields((prev) =>
            prev.includes(value)
                ? prev.filter((f) => f !== value) // elimina si ya existe
                : [...prev, value] // agrega si no existe
        );
    };

    // Función que hace la petición a GraphQL
    const getPosts = async () => {
        try {
            // Convierte ["title", "author"] → "title\nauthor"
            const queryFields = fields.join("\n");

            const query = `
                query {
                    getAllPosts {
                        id
                        ${queryFields}
                    }
                }
            `;

            const res = await fetch("http://localhost:8080/graphql", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ query })
            });

            // Conversión de respuesta a JSON
            const data = await res.json();

            console.log("Response completa:", data);

            if (data.errors) {
                setError(data.errors[0]?.message);
                setPosts([]);
                return;
            }

            setPosts(data?.data?.getAllPosts ?? []);
            setError(null);

        } catch (err) {
            console.log(err);
            setError("Error de conexión");
            setPosts([]);
        }
    };

    return (
        <div style={{ padding: 20 }}>
            <h1>Posts dinámicos</h1>

            {error && <p style={{ color: "red" }}>{error}</p>}

            <label>
                <input
                    type="checkbox"
                    checked={fields.includes("title")}
                    onChange={() => toggleField("title")}
                />
                Title
            </label>

            <label>
                <input
                    type="checkbox"
                    checked={fields.includes("author")}
                    onChange={() => toggleField("author")}
                />
                Author
            </label>

            <label>
                <input
                    type="checkbox"
                    checked={fields.includes("content")}
                    onChange={() => toggleField("content")}
                />
                Content
            </label>

            <br /><br />

            <button onClick={getPosts}>Cargar Posts</button>

            <ul  style={{listStyle: "none", padding:0}}>
                {Array.isArray(posts) && posts.length > 0 ? (
                    posts.map((post) => (
                        <li key={post.id}>
                            {fields.map((f) => post?.[f]).join(" | ")}
                        </li>
                    ))
                ) : (
                    <li>No hay posts</li>
                )}
            </ul>
        </div>
    );
}