CREATE DATABASE IF NOT EXISTS todo_list;
use todo_list;

CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    data_criacao DATE NOT NULL,
    usuario_admin BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS grupo_de_tarefas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_criacao DATE NOT NULL,
    dono_id BIGINT NOT NULL,
    FOREIGN KEY (dono_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS desafio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    completo DOUBLE NOT NULL, -- de 0 a 1 (100%)
    dono_id BIGINT NOT NULL,
    FOREIGN KEY (dono_id) REFERENCES usuario(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tarefa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descricao TEXT,
    data_criacao DATE NOT NULL,
    prazo DATE,
    concluida BOOLEAN NOT NULL,
    prioridade INT NOT NULL,
    grupo_id BIGINT,
    FOREIGN KEY (grupo_id) REFERENCES grupo_de_tarefas(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS tarefa_desafio (
    tarefa_id BIGINT NOT NULL,
    desafio_id BIGINT NOT NULL,
    PRIMARY KEY (tarefa_id, desafio_id),
    FOREIGN KEY (tarefa_id) REFERENCES tarefa(id) ON DELETE CASCADE,
    FOREIGN KEY (desafio_id) REFERENCES desafio(id) ON DELETE CASCADE
);





