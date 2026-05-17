INSERT INTO turma (disciplina_id, professor_id, periodo_letivo_id, descricao, codigo_suap) VALUES
((SELECT id FROM disciplina WHERE codigo_suap = 'GRAD.3993'), (SELECT id FROM professor WHERE matricula_siape = '1122334'), (SELECT id FROM periodo_letivo WHERE ano = 2024 AND semestre = 1), 'Turma A - Fundamentos de Programação', 'T-GRAD.3993-20241'),
((SELECT id FROM disciplina WHERE codigo_suap = 'GRAD.3996'), (SELECT id FROM professor WHERE matricula_siape = '2233445'), (SELECT id FROM periodo_letivo WHERE ano = 2024 AND semestre = 1), 'Turma A - Lógica Matemática', 'T-GRAD.3996-20241'),
((SELECT id FROM disciplina WHERE codigo_suap = 'GRAD.3999'), (SELECT id FROM professor WHERE matricula_siape = '3344556'), (SELECT id FROM periodo_letivo WHERE ano = 2024 AND semestre = 1), 'Turma A - Arquitetura e Organização de Computadores', 'T-GRAD.3999-20241'),
((SELECT id FROM disciplina WHERE codigo_suap = 'GRAD.4005'), (SELECT id FROM professor WHERE matricula_siape = '4455667'), (SELECT id FROM periodo_letivo WHERE ano = 2024 AND semestre = 1), 'Turma A - Paradigma Orientado a Objetos', 'T-GRAD.4005-20241'),
((SELECT id FROM disciplina WHERE codigo_suap = 'GRAD.4010'), (SELECT id FROM professor WHERE matricula_siape = '5566778'), (SELECT id FROM periodo_letivo WHERE ano = 2024 AND semestre = 1), 'Turma A - Modelagem de Dados', 'T-GRAD.4010-20241');

INSERT INTO matricula_turma (aluno_id, turma_id, situacao, data_matricula) VALUES
((SELECT id FROM aluno WHERE matricula = '202410001'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241'), 'MATRICULADO', '2024-02-01'),
((SELECT id FROM aluno WHERE matricula = '202410002'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241'), 'MATRICULADO', '2024-02-01'),
((SELECT id FROM aluno WHERE matricula = '202410003'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241'), 'MATRICULADO', '2024-02-02'),
((SELECT id FROM aluno WHERE matricula = '202410004'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241'), 'MATRICULADO', '2024-02-02'),
((SELECT id FROM aluno WHERE matricula = '202410005'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241'), 'MATRICULADO', '2024-02-03'),
((SELECT id FROM aluno WHERE matricula = '202410006'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3996-20241'), 'MATRICULADO', '2024-02-01'),
((SELECT id FROM aluno WHERE matricula = '202410007'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3996-20241'), 'MATRICULADO', '2024-02-01'),
((SELECT id FROM aluno WHERE matricula = '202410008'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3996-20241'), 'MATRICULADO', '2024-02-02'),
((SELECT id FROM aluno WHERE matricula = '202410009'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3996-20241'), 'MATRICULADO', '2024-02-02'),
((SELECT id FROM aluno WHERE matricula = '202410010'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3996-20241'), 'MATRICULADO', '2024-02-03'),
((SELECT id FROM aluno WHERE matricula = '202410001'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.4005-20241'), 'MATRICULADO', '2024-02-01'),
((SELECT id FROM aluno WHERE matricula = '202410002'), (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.4005-20241'), 'MATRICULADO', '2024-02-01');

INSERT INTO nota (matricula_turma_id, descricao, valor, peso, data_avaliacao) VALUES
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410001') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241')), 'Prova 1', 8.5, 1.0, '2024-04-15'),
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410002') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241')), 'Prova 1', 9.0, 1.0, '2024-04-15'),
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410003') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241')), 'Prova 1', 7.5, 1.0, '2024-04-15'),
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410001') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.4005-20241')), 'Projeto Prático', 10.0, 2.0, '2024-05-10');

INSERT INTO falta (matricula_turma_id, data_aula, quantidade, justificativa) VALUES
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410001') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241')), '2024-03-10', 2, 'Atestado médico'),
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410005') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3993-20241')), '2024-03-15', 4, NULL),
((SELECT id FROM matricula_turma WHERE aluno_id = (SELECT id FROM aluno WHERE matricula = '202410006') AND turma_id = (SELECT id FROM turma WHERE codigo_suap = 'T-GRAD.3996-20241')), '2024-03-12', 2, 'Problemas com internet');
