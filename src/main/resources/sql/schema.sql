CREATE TABLE IF NOT EXISTS breed (
    id              VARCHAR(255)    PRIMARY KEY,
    name            VARCHAR(255)    NOT NULL,
    temperament     VARCHAR(255)    NOT NULL,
    origin          VARCHAR(255)    NOT NULL
);

CREATE TABLE IF NOT EXISTS cat_image (
    id      VARCHAR(255)    PRIMARY KEY,
    width   INT             NOT NULL,
    height  INT             NOT NULL,
    url     VARCHAR(255)    NOT NULL
);

CREATE TABLE IF NOT EXISTS cat_breed (
    id              BIGINT          PRIMARY KEY AUTO_INCREMENT,
    breed_id        VARCHAR(255)    NOT NULL,
    cat_image_id    VARCHAR(255)    NOT NULL
);