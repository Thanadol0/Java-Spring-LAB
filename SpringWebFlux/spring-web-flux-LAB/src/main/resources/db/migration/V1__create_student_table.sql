CREATE TABLE IF NOT EXISTS student (
    id   SERIAL PRIMARY KEY,          -- Integer + auto-increment
    name VARCHAR(255) NOT NULL,
    age  INTEGER      NOT NULL
);
