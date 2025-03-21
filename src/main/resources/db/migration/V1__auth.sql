CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR UNIQUE NOT NULL,
    email VARCHAR UNIQUE NOT NULL,
    email_confirmed BOOLEAN NOT NULL,
    verification_code VARCHAR,
    password VARCHAR NOT NULL
);

CREATE TYPE role AS ENUM ('ROLE_USER', 'ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS users_roles (
    user_id BIGINT NOT NULL REFERENCES users ON DELETE CASCADE,
    role role NOT NULL,
    PRIMARY KEY (user_id, role)
);