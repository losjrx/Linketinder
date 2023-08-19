create database linketinder;

CREATE TABLE USUARIO (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    id_type INT DEFAULT NULL,
    nome VARCHAR(100) DEFAULT NULL,
    email VARCHAR(50) DEFAULT NULL,
    pais VARCHAR(50) DEFAULT NULL,
    estado VARCHAR(50) DEFAULT NULL,
    cep VARCHAR(10) DEFAULT NULL,
    descricao VARCHAR(300) DEFAULT NULL,
    username VARCHAR(25) DEFAULT NULL,
    senha VARCHAR(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE CANDIDATO (
    id_candidato INT,
    idade INT,
    cpf VARCHAR(14) PRIMARY KEY,
    FOREIGN KEY (id_candidato) REFERENCES USUARIO(id_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE EMPRESA (
    id_empresa INT,
    cnpj VARCHAR(20) PRIMARY KEY,
    FOREIGN KEY (id_empresa) REFERENCES USUARIO(id_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE VAGAS_REQUERIDAS (
    id_candidato INT,
    id_vaga INT,
    PRIMARY KEY (id_candidato, id_vaga),
    FOREIGN KEY (id_candidato) REFERENCES CANDIDATO(id_candidato),
    FOREIGN KEY (id_vaga) REFERENCES VAGA(id_vaga)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE CURRICULO (
    id_candidato INT,
    formacao VARCHAR(255),
    cursos_complementares VARCHAR(1000),
    pretensao_salarial DECIMAL(10, 2),
    PRIMARY KEY (id_candidato),
    FOREIGN KEY (id_candidato) REFERENCES CANDIDATO(id_candidato)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE VAGA (
    id_vaga INT AUTO_INCREMENT PRIMARY KEY,
    id_empresa INT,
    nome VARCHAR(255),
    tipo VARCHAR(100),
    salario DECIMAL(10, 2),
    definicao VARCHAR(1000),
    disponivel BOOLEAN,
    FOREIGN KEY (id_empresa) REFERENCES EMPRESA(id_empresa)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE INDEX idx_id_type ON USUARIO(id_type);

CREATE TABLE CURTIDA (
    id_user INT,
    id_type INT,
    id_user_curtido INT,
    id_type_curtido INT,
    FOREIGN KEY (id_user) REFERENCES USUARIO(id_user),
    FOREIGN KEY (id_user_curtido) REFERENCES USUARIO(id_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;