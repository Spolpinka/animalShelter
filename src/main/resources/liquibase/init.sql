CREATE TABLE cat
(
    id      BIGSERIAL           PRIMARY KEY,
    name    VARCHAR(60)         NOT NULL,
    age     INT                 NOT NULL,
    weight  DOUBLE PRECISION    NOT NULL,
    history VARCHAR(250),
    image   BIGINT
);

CREATE TABLE cat_adopter
(
    id                 BIGSERIAL    PRIMARY KEY,
    first_name         VARCHAR(60)  NOT NULL,
    last_name          VARCHAR(60)  NOT NULL,
    phone_number       VARCHAR(20),
    email              VARCHAR(100),
    address            VARCHAR(100),
    cat_id             BIGINT       NOT NULL,
    is_adopted         BOOLEAN      NOT NULL,
    is_reported_2_days BOOLEAN      NOT NULL,
    trial_period       INT          NOT NULL
);

CREATE TABLE dog
(
    id      BIGSERIAL   NOT NULL PRIMARY KEY,
    name    VARCHAR(60) NOT NULL,
    age     INT         NOT NULL,
    weight  BOOLEAN     NOT NULL,
    history VARCHAR(250),
    image   BIGINT
);

CREATE TABLE dog_adopter
(
    id                 BIGSERIAL    NOT NULL PRIMARY KEY,
    first_name         VARCHAR(60)  NOT NULL,
    last_name          VARCHAR(60)  NOT NULL,
    phone_number       VARCHAR(20)  NOT NULL,
    email              VARCHAR(100) NOT NULL,
    address            VARCHAR(100) NOT NULL,
    dog_id             BIGINT       NOT NULL,
    is_adopted         BOOLEAN      NOT NULL,
    is_reported_2_days BOOLEAN      NOT NULL,
    trial_period       INT          NOT NULL
);

CREATE TABLE user_info
(
    id                   BIGSERIAL   NOT NULL PRIMARY KEY,
    name                 VARCHAR(60) NOT NULL,
    chat_id              BIGINT      NOT NULL,
    time_of_registration timestamp   NOT NULL,
    is_dog               BOOLEAN     NOT NULL
);