DROP TABLE IF EXISTS meals;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  calories_per_day INTEGER DEFAULT 2000    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


CREATE TABLE meals
(
  id          INTEGER DEFAULT nextval('global_seq' :: REGCLASS) NOT NULL
    CONSTRAINT meals_pkey
    PRIMARY KEY,
    id_user   INTEGER                                           NOT NULL
    CONSTRAINT meals_users_id_fk
    REFERENCES users
    ON DELETE CASCADE,
  datetime    TIMESTAMP,
  description VARCHAR                                           NOT NULL,
  calories    INTEGER                                           NOT NULL
);


CREATE UNIQUE INDEX meals_datetime_uindex
  ON meals (datetime);