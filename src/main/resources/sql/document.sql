CREATE TABLE document (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,  -- Short header/title for the document
    content TEXT NOT NULL,        -- Full document text
    embedding VECTOR(1024)        -- AI-generated embedding (mxbai-embed-large)
);
