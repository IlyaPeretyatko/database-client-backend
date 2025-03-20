CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    email_confirmed BOOLEAN NOT NULL,
    verification_code VARCHAR,
    password VARCHAR NOT NULL
);
