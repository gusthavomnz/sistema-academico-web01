# Sistema Acadêmico — API REST

API REST para gestão acadêmica institucional desenvolvida como projeto da disciplina de **Programação Web I**, inspirada no modelo do SUAP.

**Stack:** Java 17 · Spring Boot 3.2.5 · Spring Data JPA · MySQL · Flyway · Lombok · Maven

---

## Como executar

```sql
CREATE DATABASE IF NOT EXISTS academico;
```

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/academico
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

```bash
./mvnw spring-boot:run
```

**Base URL:** `http://localhost:8080`

---

## Perfis

| Perfil | Acesso |
|---|---|
| `COORDENADOR` | Gerencia usuários, gerencia notas/faltas de qualquer turma, consulta dados de qualquer aluno |
| `PROFESSOR` | Gerencia notas/faltas das suas próprias turmas, acessa chat |
| `ALUNO` | Visualiza próprios dados acadêmicos, acessa chat |

---

## Seed — IDs para teste

**Usuários**

| ID | Nome | Perfil |
|---|---|---|
| 1 | Ricardo Oliveira Fontes | COORDENADOR |
| 2 | Carlos Eduardo Santana | PROFESSOR — Turma 1 |
| 3 | Maria Clara Nunes | PROFESSOR — Turma 2 |
| 4 | Roberto Almeida Filho | PROFESSOR — Turma 3 |
| 5 | Fernanda Silva Costa | PROFESSOR — Turma 4 |
| 6 | Lucas Mendes Pereira | PROFESSOR — Turma 5 |
| 7 | José Menezes | ALUNO |
| 8 | Ana Beatriz Lima | ALUNO |
| 9 | Pedro Henrique Santos | ALUNO |
| 10 | Sofia Almeida | ALUNO |
| 11 | João Vitor Gomes | ALUNO |
| 12 | Laura Ribeiro | ALUNO |
| 13 | Gabriel Oliveira | ALUNO |
| 14 | Isabella Souza | ALUNO |
| 15 | Matheus Costa | ALUNO |
| 16 | Luiza Carvalho | ALUNO |

**Turmas**

| ID | Disciplina | Professor (usuarioId) |
|---|---|---|
| 1 | Fundamentos de Programação | Carlos (2) |
| 2 | Lógica Matemática | Maria Clara (3) |
| 3 | Arquitetura e Organização de Computadores | Roberto (4) |
| 4 | Paradigma Orientado a Objetos | Fernanda (5) |
| 5 | Modelagem de Dados | Lucas (6) |

**Matrículas (matriculaTurmaId)**

| ID | Aluno (usuarioId) | Turma |
|---|---|---|
| 1 | José Menezes (7) | 1 |
| 2 | Ana Beatriz Lima (8) | 1 |
| 3 | Pedro Henrique Santos (9) | 1 |
| 4 | Sofia Almeida (10) | 1 |
| 5 | João Vitor Gomes (11) | 1 |
| 6 | Laura Ribeiro (12) | 2 |
| 7 | Gabriel Oliveira (13) | 2 |
| 8 | Isabella Souza (14) | 2 |
| 9 | Matheus Costa (15) | 2 |
| 10 | Luiza Carvalho (16) | 2 |
| 11 | José Menezes (7) | 4 |
| 12 | Ana Beatriz Lima (8) | 4 |

**Notas**

| ID | matriculaTurmaId | Aluno | Descrição | Valor | Peso |
|---|---|---|---|---|---|
| 1 | 1 | José Menezes | Prova 1 | 8.5 | 1.0 |
| 2 | 2 | Ana Beatriz Lima | Prova 1 | 9.0 | 1.0 |
| 3 | 3 | Pedro Henrique Santos | Prova 1 | 7.5 | 1.0 |
| 4 | 11 | José Menezes | Projeto Prático | 10.0 | 2.0 |

**Faltas**

| ID | matriculaTurmaId | Aluno | Data | Qtd | Justificativa |
|---|---|---|---|---|---|
| 1 | 1 | José Menezes | 2024-03-10 | 2 | Atestado médico |
| 2 | 5 | João Vitor Gomes | 2024-03-15 | 4 | — |
| 3 | 6 | Laura Ribeiro | 2024-03-12 | 2 | Problemas com internet |

---

## Endpoints

---

### Usuários `/api/usuarios`

```
GET    /api/usuarios?usuarioIdRequisitante=1
POST   /api/usuarios
PUT    /api/usuarios/{id}
DELETE /api/usuarios/{id}/inativar?usuarioIdRequisitante=1
```

**GET /api/usuarios?usuarioIdRequisitante=1** — requer COORDENADOR

Resposta `200`:
```json
[{"id": 1, "nome": "Ricardo Oliveira Fontes", "email": "ricardo.fontes@academico.ifs.edu.br", "login": "ricardo.fontes", "perfil": "COORDENADOR", "suapId": "9988776", "status": "A", "dataCadastro": "2024-01-01T00:00:00"}, {"id": 2, "nome": "Carlos Eduardo Santana", "email": "carlos.santana@academico.ifs.edu.br", "login": "carlos.santana", "perfil": "PROFESSOR", "suapId": "1122334", "status": "A", "dataCadastro": "2024-01-01T00:00:00"}, {"id": 7, "nome": "José Menezes", "email": "jose.menezes064@academico.ifs.edu.br", "login": "jose.menezes064", "perfil": "ALUNO", "suapId": "202410001", "status": "A", "dataCadastro": "2024-01-01T00:00:00"}]
```

**POST /api/usuarios** — requer COORDENADOR

Envio:
```json
{"usuarioIdRequisitante": 1, "nome": "Lucas Ferreira", "email": "lucas.ferreira@academico.ifs.edu.br", "login": "lucas.ferreira", "senha": "123456", "perfil": "PROFESSOR", "suapId": "7788990"}
```
Resposta `201`:
```json
{"id": 17, "nome": "Lucas Ferreira", "email": "lucas.ferreira@academico.ifs.edu.br", "login": "lucas.ferreira", "perfil": "PROFESSOR", "suapId": "7788990", "status": "A", "dataCadastro": "2024-01-01T00:00:00"}
```

**PUT /api/usuarios/2** — `senha` é opcional, demais campos obrigatórios

Envio:
```json
{"usuarioIdRequisitante": 1, "nome": "Carlos Eduardo Santana", "email": "carlos.santana@academico.ifs.edu.br", "login": "carlos.santana", "perfil": "PROFESSOR", "suapId": "1122334"}
```
Resposta `200`:
```json
{"id": 2, "nome": "Carlos Eduardo Santana", "email": "carlos.santana@academico.ifs.edu.br", "login": "carlos.santana", "perfil": "PROFESSOR", "suapId": "1122334", "status": "A", "dataCadastro": "2024-01-01T00:00:00"}
```

**DELETE /api/usuarios/7/inativar?usuarioIdRequisitante=1** — Resposta `204`

---

### Turmas `/api/turmas`

```
GET /api/turmas?usuarioId=2
```

**GET /api/turmas?usuarioId=2** — requer PROFESSOR ou COORDENADOR

Resposta `200` (Carlos leciona apenas Turma 1):
```json
[{"id": 1, "descricao": "Turma A - Fundamentos de Programação", "codigoSuap": "T-GRAD.3993-20241", "status": "A", "disciplina": {"id": 1, "nome": "FUNDAMENTOS DE PROGRAMAÇÃO", "codigoSuap": "GRAD.3993", "cargaHoraria": 120}, "periodoLetivo": {"id": 1, "ano": 2024, "semestre": 1, "descricao": "2024.1"}, "professor": {"id": 1, "nome": "Carlos Eduardo Santana", "matriculaSiape": null}}]
```

---

### Matrículas `/api/turmas/{turmaId}/matriculas`

```
GET /api/turmas/{turmaId}/matriculas?usuarioId=2
GET /api/turmas/{turmaId}/matriculas/situacao?usuarioId=7
```

**GET /api/turmas/1/matriculas?usuarioId=2** — requer PROFESSOR da turma ou COORDENADOR

Resposta `200`:
```json
[{"id": 1, "alunoId": 1, "nomeAluno": "José Menezes", "matriculaAluno": "202410001", "situacao": "MATRICULADO", "dataMatricula": "2024-02-01", "status": "A"}, {"id": 2, "alunoId": 2, "nomeAluno": "Ana Beatriz Lima", "matriculaAluno": "202410002", "situacao": "MATRICULADO", "dataMatricula": "2024-02-01", "status": "A"}, {"id": 3, "alunoId": 3, "nomeAluno": "Pedro Henrique Santos", "matriculaAluno": "202410003", "situacao": "MATRICULADO", "dataMatricula": "2024-02-02", "status": "A"}]
```

**GET /api/turmas/1/matriculas/situacao?usuarioId=7** — requer ALUNO matriculado ou COORDENADOR

Resposta `200`:
```json
"MATRICULADO"
```

---

### Notas `/api/turmas/{turmaId}/notas`

```
GET  /api/turmas/{turmaId}/notas?usuarioId=7
GET  /api/turmas/{turmaId}/notas/matriculas/{matriculaTurmaId}?usuarioId=2
POST /api/turmas/{turmaId}/notas
PUT  /api/turmas/{turmaId}/notas/{notaId}
```

**GET /api/turmas/1/notas?usuarioId=7** — aluno lista suas próprias notas na turma

Resposta `200` (José tem 1 nota no seed — adicione mais via POST para expandir a lista):
```json
[{"id": 1, "matriculaTurmaId": 1, "nomeAluno": "José Menezes", "turmaDescricao": "Turma A - Fundamentos de Programação", "descricao": "Prova 1", "valor": 8.50, "peso": 1.00, "dataAvaliacao": "2024-04-15"}]
```

**GET /api/turmas/1/notas/matriculas/2?usuarioId=2** — professor lista notas de Ana Beatriz (matriculaTurmaId=2)

Resposta `200`:
```json
[{"id": 2, "matriculaTurmaId": 2, "nomeAluno": "Ana Beatriz Lima", "turmaDescricao": "Turma A - Fundamentos de Programação", "descricao": "Prova 1", "valor": 9.00, "peso": 1.00, "dataAvaliacao": "2024-04-15"}]
```

**POST /api/turmas/1/notas** — requer PROFESSOR da turma ou COORDENADOR

Envio:
```json
{"usuarioIdRequisitante": 2, "matriculaTurmaId": 1, "descricao": "Prova 2", "valor": 7.0, "peso": 1.0, "dataAvaliacao": "2024-06-10"}
```
Resposta `201`:
```json
{"id": 5, "matriculaTurmaId": 1, "nomeAluno": "José Menezes", "turmaDescricao": "Turma A - Fundamentos de Programação", "descricao": "Prova 2", "valor": 7.00, "peso": 1.00, "dataAvaliacao": "2024-06-10"}
```

**PUT /api/turmas/1/notas/1** — requer PROFESSOR da turma ou COORDENADOR. Resposta `204`

Envio:
```json
{"usuarioIdRequisitante": 2, "matriculaTurmaId": 1, "descricao": "Prova 1 (revisada)", "valor": 9.0, "peso": 1.0, "dataAvaliacao": "2024-04-15"}
```

---

### Faltas `/api/turmas/{turmaId}/faltas`

```
GET  /api/turmas/{turmaId}/faltas?usuarioId=7
GET  /api/turmas/{turmaId}/faltas/matriculas/{matriculaTurmaId}?usuarioId=2
POST /api/turmas/{turmaId}/faltas
PUT  /api/turmas/{turmaId}/faltas/{faltaId}
```

**GET /api/turmas/1/faltas?usuarioId=7** — aluno lista suas próprias faltas na turma

Resposta `200` (José tem 1 falta no seed — adicione mais via POST para expandir a lista):
```json
[{"id": 1, "matriculaTurmaId": 1, "dataAula": "2024-03-10", "quantidade": 2, "justificativa": "Atestado médico"}]
```

**GET /api/turmas/1/faltas/matriculas/5?usuarioId=2** — professor lista faltas de João Vitor

Resposta `200`:
```json
[{"id": 2, "matriculaTurmaId": 5, "dataAula": "2024-03-15", "quantidade": 4, "justificativa": null}]
```

**POST /api/turmas/1/faltas** — requer PROFESSOR da turma ou COORDENADOR

Envio:
```json
{"usuarioIdRequisitante": 2, "matriculaTurmaId": 1, "dataAula": "2024-05-20", "quantidade": 2, "justificativa": null}
```
Resposta `201`:
```json
{"id": 4, "matriculaTurmaId": 1, "dataAula": "2024-05-20", "quantidade": 2, "justificativa": null}
```

**PUT /api/turmas/1/faltas/1** — requer PROFESSOR da turma ou COORDENADOR. Resposta `204`

Envio:
```json
{"usuarioIdRequisitante": 2, "matriculaTurmaId": 1, "dataAula": "2024-03-10", "quantidade": 1, "justificativa": "Justificativa retroativa aceita"}
```

---

### Boletins `/api/boletins`

```
GET /api/boletins?usuarioId=7
GET /api/boletins/{alunoUsuarioId}?usuarioIdRequisitante=1
```

**GET /api/boletins?usuarioId=7** — aluno consulta próprio boletim

Resposta `200`:
```json
{"nomeAluno": "José Menezes", "matricula": "202410001", "periodos": [{"ano": 2024, "semestre": 1, "descricaoPeriodo": "2024.1", "disciplinas": [{"nomeDisciplina": "FUNDAMENTOS DE PROGRAMAÇÃO", "cargaHoraria": 120, "situacao": "MATRICULADO", "notas": [{"descricao": "Prova 1", "valor": 8.50, "peso": 1.00, "dataAvaliacao": "2024-04-15"}], "totalFaltas": 2}, {"nomeDisciplina": "PARADIGMA ORIENTADO A OBJETOS", "cargaHoraria": 90, "situacao": "MATRICULADO", "notas": [{"descricao": "Projeto Prático", "valor": 10.00, "peso": 2.00, "dataAvaliacao": null}], "totalFaltas": 0}]}]}
```

**GET /api/boletins/7?usuarioIdRequisitante=1** — requer COORDENADOR. Resposta idêntica ao GET acima.

---

### Chat `/api/chat`

```
POST   /api/chat/mensagens
GET    /api/chat/turmas/{turmaId}/mensagens
DELETE /api/chat/mensagens/{mensagemId}?usuarioId=7
```

> O chat da turma é criado automaticamente na primeira mensagem enviada.

**POST /api/chat/mensagens** — requer ser professor ou aluno da turma

Envio:
```json
{"turmaId": 1, "usuarioId": 7, "mensagem": "Professor, qual é a data da próxima prova?"}
```
Resposta `201`:
```json
{"id": 1, "mensagem": "Professor, qual é a data da próxima prova?", "dataEnvio": "2024-06-01T10:00:00", "nomeRemetente": "José Menezes", "perfilRemetente": "ALUNO", "isApagada": false}
```

**GET /api/chat/turmas/1/mensagens** — qualquer participante da turma. Resposta `200`:
```json
[{"id": 1, "mensagem": "Professor, qual é a data da próxima prova?", "dataEnvio": "2024-06-01T10:00:00", "nomeRemetente": "José Menezes", "perfilRemetente": "ALUNO", "isApagada": false}, {"id": 2, "mensagem": "A próxima prova será dia 10/06.", "dataEnvio": "2024-06-01T10:05:00", "nomeRemetente": "Carlos Eduardo Santana", "perfilRemetente": "PROFESSOR", "isApagada": false}]
```

**DELETE /api/chat/mensagens/1?usuarioId=7** — apenas o autor pode apagar. Resposta `204`

Mensagem fica visível como:
```json
{"id": 1, "mensagem": "🚫 Mensagem apagada", "dataEnvio": "2024-06-01T10:00:00", "nomeRemetente": "José Menezes", "perfilRemetente": "ALUNO", "isApagada": true}
```
