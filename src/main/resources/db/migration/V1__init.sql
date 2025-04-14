
CREATE TABLE IF NOT EXISTS users
(
    id                BIGSERIAL PRIMARY KEY,
    name              VARCHAR UNIQUE NOT NULL,
    email             VARCHAR UNIQUE NOT NULL,
    email_confirmed   BOOLEAN        NOT NULL,
    verification_code VARCHAR,
    password          VARCHAR        NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT  NOT NULL REFERENCES users ON DELETE CASCADE,
    role    VARCHAR NOT NULL,
    PRIMARY KEY (user_id, role)
);

CREATE TABLE IF NOT EXISTS rank_categories
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL UNIQUE
);

INSERT INTO rank_categories (title)
VALUES ('Рядовой состав'),
       ('Сержантский состав'),
       ('Офицерский состав');



CREATE TABLE IF NOT EXISTS ranks
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR NOT NULL UNIQUE,
    level            INTEGER NOT NULL,
    rank_category_id INTEGER NOT NULL REFERENCES rank_categories
);

CREATE TABLE IF NOT EXISTS militaries
(
    id          SERIAL PRIMARY KEY,
    first_name  VARCHAR NOT NULL,
    last_name   VARCHAR NOT NULL,
    middle_name VARCHAR,
    birth_date  DATE    NOT NULL,
    rank_id     INTEGER REFERENCES ranks,
    unit_id INTEGER
);

CREATE TABLE IF NOT EXISTS militaries_properties
(
    id          SERIAL PRIMARY KEY,
    military_id INTEGER NOT NULL REFERENCES militaries ON DELETE CASCADE,
    title       VARCHAR NOT NULL,
    rank_id     INTEGER NOT NULL REFERENCES ranks,
    value       VARCHAR NOT NULL,
    UNIQUE (military_id, title, rank_id)
);

CREATE TABLE IF NOT EXISTS specialties
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS militaries_specialties
(
    military_id  INTEGER NOT NULL REFERENCES militaries ON DELETE CASCADE,
    specialty_id INTEGER NOT NULL REFERENCES specialties,
    PRIMARY KEY (military_id, specialty_id)
);

CREATE TABLE IF NOT EXISTS units
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR          NOT NULL,
    lat          DOUBLE PRECISION NOT NULL,
    lng          DOUBLE PRECISION NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE CASCADE
);

ALTER TABLE IF EXISTS militaries
    ADD FOREIGN KEY (unit_id) REFERENCES units (id) ON DELETE SET NULL ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS companies
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL,
    unit_id      INTEGER NOT NULL REFERENCES units ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS platoons
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL,
    company_id   INTEGER REFERENCES companies ON DELETE SET NULL
);


CREATE TABLE IF NOT EXISTS squads
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL,
    platoon_id   INTEGER REFERENCES platoons ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS divisions
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS divisions_units
(
    unit_id     INTEGER NOT NULL REFERENCES units ON DELETE CASCADE,
    division_id INTEGER NOT NULL REFERENCES divisions ON DELETE CASCADE,
    PRIMARY KEY (division_id, unit_id)
);

CREATE TABLE IF NOT EXISTS brigades
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS brigades_units
(
    brigade_id INTEGER NOT NULL REFERENCES brigades ON DELETE CASCADE,
    unit_id    INTEGER NOT NULL REFERENCES units ON DELETE CASCADE,
    PRIMARY KEY (brigade_id, unit_id)
);

CREATE TABLE IF NOT EXISTS corps
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS corps_units
(
    corps_id INTEGER NOT NULL REFERENCES corps ON DELETE CASCADE,
    unit_id  INTEGER NOT NULL REFERENCES units ON DELETE CASCADE,
    PRIMARY KEY (corps_id, unit_id)
);

CREATE TABLE IF NOT EXISTS armies
(
    id           SERIAL PRIMARY KEY,
    title        VARCHAR NOT NULL,
    commander_id INTEGER REFERENCES militaries ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS armies_corps
(
    army_id  INTEGER NOT NULL REFERENCES armies ON DELETE CASCADE,
    corps_id INTEGER NOT NULL REFERENCES corps ON DELETE CASCADE,
    PRIMARY KEY (army_id, corps_id)
);

CREATE TABLE IF NOT EXISTS armies_divisions
(
    army_id     INTEGER NOT NULL REFERENCES armies ON DELETE CASCADE,
    division_id INTEGER NOT NULL REFERENCES divisions ON DELETE CASCADE,
    PRIMARY KEY (army_id, division_id)
);

CREATE TABLE IF NOT EXISTS armies_brigades
(
    army_id    INTEGER NOT NULL REFERENCES armies ON DELETE CASCADE,
    brigade_id INTEGER NOT NULL REFERENCES brigades ON DELETE CASCADE,
    PRIMARY KEY (army_id, brigade_id)
);

CREATE TABLE IF NOT EXISTS buildings
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR NOT NULL,
    address VARCHAR,
    unit_id INTEGER REFERENCES units ON DELETE CASCADE,
    UNIQUE (title, unit_id)
);

CREATE TABLE IF NOT EXISTS building_properties
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR NOT NULL,
    building_id INTEGER NOT NULL REFERENCES buildings ON DELETE CASCADE,
    value       VARCHAR NOT NULL,
    UNIQUE (building_id, title)
);

CREATE TABLE IF NOT EXISTS buildings_companies
(
    building_id INTEGER NOT NULL REFERENCES buildings ON DELETE CASCADE,
    company_id  INTEGER NOT NULL REFERENCES companies ON DELETE CASCADE,
    PRIMARY KEY (building_id, company_id)
);

CREATE TABLE IF NOT EXISTS buildings_platoons
(
    building_id INTEGER NOT NULL REFERENCES buildings ON DELETE CASCADE,
    platoon_id  INTEGER NOT NULL REFERENCES platoons ON DELETE CASCADE,
    PRIMARY KEY (building_id, platoon_id)
);

CREATE TABLE IF NOT EXISTS buildings_squads
(
    building_id INTEGER NOT NULL REFERENCES buildings ON DELETE CASCADE,
    squad_id    INTEGER NOT NULL REFERENCES squads ON DELETE CASCADE,
    PRIMARY KEY (building_id, squad_id)
);

CREATE TABLE IF NOT EXISTS military_equipment_categories
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS military_equipment_types
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR NOT NULL,
    category_id INTEGER NOT NULL REFERENCES military_equipment_categories,
    UNIQUE (title, category_id)
);

CREATE TABLE IF NOT EXISTS military_equipments
(
    id            SERIAL PRIMARY KEY,
    serial_number VARCHAR NOT NULL UNIQUE,
    type_id       INTEGER REFERENCES military_equipment_types,
    unit_id       INTEGER REFERENCES units ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS military_equipment_properties
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR NOT NULL,
    type_id INTEGER NOT NULL REFERENCES military_equipment_types,
    value   VARCHAR NOT NULL,
    UNIQUE (title, type_id)
);

CREATE TABLE IF NOT EXISTS weapons_categories
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS weapons_types
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR NOT NULL,
    category_id INTEGER NOT NULL REFERENCES weapons_categories,
    UNIQUE (title, category_id)
);

CREATE TABLE IF NOT EXISTS weapons
(
    id            SERIAL PRIMARY KEY,
    serial_number VARCHAR NOT NULL UNIQUE,
    type_id       INTEGER REFERENCES weapons_types,
    unit_id       INTEGER REFERENCES units ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS weapons_properties
(
    id      SERIAL PRIMARY KEY,
    title   VARCHAR NOT NULL,
    type_id INTEGER NOT NULL REFERENCES weapons_types,
    value   VARCHAR NOT NULL,
    UNIQUE (title, type_id)
);