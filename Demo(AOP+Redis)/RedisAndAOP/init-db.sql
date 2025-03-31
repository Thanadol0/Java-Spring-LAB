CREATE TABLE IF NOT EXISTS account (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100),
    balance NUMERIC
);

-- Optional: seed data
INSERT INTO account (id, name, balance) VALUES
(1, 'Alice', 1000.00),
(2, 'Bob', 2500.50)
ON CONFLICT (id) DO NOTHING;