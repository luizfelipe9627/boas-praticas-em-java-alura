CREATE TABLE tutores
(
    id       BIGSERIAL    NOT NULL,
    nome     VARCHAR(100) NOT NULL,
    telefone VARCHAR(14)  NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
