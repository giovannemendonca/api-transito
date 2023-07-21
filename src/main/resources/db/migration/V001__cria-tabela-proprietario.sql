-- a pasta db.migration é padrão do flyway para armazenar os scripts de migração
-- o nome do arquivo segue o padrão V001__cria-tabela-proprietario.sql

CREATE TABLE proprietario(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20) NOT NULL,

    PRIMARY KEY (id)
);
ALTER TABLE proprietario ADD CONSTRAINT uk_proprietario UNIQUE (email);
