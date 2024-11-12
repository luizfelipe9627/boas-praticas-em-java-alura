CREATE TABLE adocoes
(
    id                   BIGSERIAL    NOT NULL,
    data                 TIMESTAMP    NOT NULL,
    tutor_id             BIGINT       NOT NULL,
    pet_id               BIGINT       NOT NULL,
    motivo               VARCHAR(255) NOT NULL,
    status               VARCHAR(100) NOT NULL,
    justificativa_status VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_adocoes_tutor_id FOREIGN KEY (tutor_id) REFERENCES tutores (id),
    CONSTRAINT fk_adocoes_pet_id FOREIGN KEY (pet_id) REFERENCES pets (id)
);