CREATE TABLE items (
    id serial PRIMARY KEY,
    name text,
    embedding vector(300)  -- 300-dimensional vector
);
