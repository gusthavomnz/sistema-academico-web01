CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL,
    login VARCHAR(100) NOT NULL,
    senha VARCHAR(255),
    perfil VARCHAR(30) NOT NULL,
    suap_id VARCHAR(100),
    status CHAR(1) NOT NULL DEFAULT 'A',
    data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_usuario PRIMARY KEY (id),
    CONSTRAINT uk_usuario_email UNIQUE (email),
    CONSTRAINT uk_usuario_login UNIQUE (login)
) ENGINE=InnoDB;

CREATE TABLE periodo_letivo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    ano INT NOT NULL,
    semestre INT NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_periodo_letivo PRIMARY KEY (id),
    CONSTRAINT uk_periodo_letivo_ano_semestre UNIQUE (ano, semestre)
) ENGINE=InnoDB;

CREATE TABLE curso (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    codigo_suap VARCHAR(100),
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_curso PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE aluno (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    curso_id BIGINT,
    matricula VARCHAR(50) NOT NULL,
    suap_id VARCHAR(100),
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_aluno PRIMARY KEY (id),
    CONSTRAINT uk_aluno_matricula UNIQUE (matricula),
    CONSTRAINT fk_aluno_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_aluno_curso FOREIGN KEY (curso_id) REFERENCES curso(id)
) ENGINE=InnoDB;

CREATE TABLE professor (
    id BIGINT NOT NULL AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    matricula_siape VARCHAR(50),
    suap_id VARCHAR(100),
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_professor PRIMARY KEY (id),
    CONSTRAINT fk_professor_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB;

CREATE TABLE disciplina (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(150) NOT NULL,
    codigo VARCHAR(50),
    codigo_suap VARCHAR(100),
    carga_horaria INT,
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_disciplina PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE turma (
    id BIGINT NOT NULL AUTO_INCREMENT,
    disciplina_id BIGINT NOT NULL,
    professor_id BIGINT,
    periodo_letivo_id BIGINT NOT NULL,
    descricao VARCHAR(150) NOT NULL,
    codigo_suap VARCHAR(100),
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_turma PRIMARY KEY (id),
    CONSTRAINT fk_turma_disciplina FOREIGN KEY (disciplina_id) REFERENCES disciplina(id),
    CONSTRAINT fk_turma_professor FOREIGN KEY (professor_id) REFERENCES professor(id),
    CONSTRAINT fk_turma_periodo FOREIGN KEY (periodo_letivo_id) REFERENCES periodo_letivo(id)
) ENGINE=InnoDB;

CREATE TABLE matricula_turma (
    id BIGINT NOT NULL AUTO_INCREMENT,
    aluno_id BIGINT NOT NULL,
    turma_id BIGINT NOT NULL,
    situacao VARCHAR(50),
    data_matricula DATE,
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_matricula_turma PRIMARY KEY (id),
    CONSTRAINT uk_aluno_turma UNIQUE (aluno_id, turma_id),
    CONSTRAINT fk_matricula_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id),
    CONSTRAINT fk_matricula_turma FOREIGN KEY (turma_id) REFERENCES turma(id)
) ENGINE=InnoDB;

CREATE TABLE nota (
    id BIGINT NOT NULL AUTO_INCREMENT,
    matricula_turma_id BIGINT NOT NULL,
    descricao VARCHAR(100) NOT NULL,
    valor DECIMAL(5,2),
    peso DECIMAL(5,2),
    data_avaliacao DATE,
    codigo_suap VARCHAR(100),
    CONSTRAINT pk_nota PRIMARY KEY (id),
    CONSTRAINT fk_nota_matricula FOREIGN KEY (matricula_turma_id) REFERENCES matricula_turma(id)
) ENGINE=InnoDB;

CREATE TABLE falta (
    id BIGINT NOT NULL AUTO_INCREMENT,
    matricula_turma_id BIGINT NOT NULL,
    data_aula DATE NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    justificativa TEXT,
    codigo_suap VARCHAR(100),
    CONSTRAINT pk_falta PRIMARY KEY (id),
    CONSTRAINT fk_falta_matricula FOREIGN KEY (matricula_turma_id) REFERENCES matricula_turma(id)
) ENGINE=InnoDB;

CREATE TABLE chat_turma (
    id BIGINT NOT NULL AUTO_INCREMENT,
    turma_id BIGINT NOT NULL,
    titulo VARCHAR(150),
    status CHAR(1) NOT NULL DEFAULT 'A',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_chat_turma PRIMARY KEY (id),
    CONSTRAINT fk_chat_turma_turma FOREIGN KEY (turma_id) REFERENCES turma(id)
) ENGINE=InnoDB;

CREATE TABLE chat_mensagem (
    id BIGINT NOT NULL AUTO_INCREMENT,
    chat_turma_id BIGINT NOT NULL,
    usuario_id BIGINT NOT NULL,
    mensagem TEXT NOT NULL,
    data_envio TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status CHAR(1) NOT NULL DEFAULT 'A',
    CONSTRAINT pk_chat_mensagem PRIMARY KEY (id),
    CONSTRAINT fk_mensagem_chat FOREIGN KEY (chat_turma_id) REFERENCES chat_turma(id),
    CONSTRAINT fk_mensagem_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
) ENGINE=InnoDB;
