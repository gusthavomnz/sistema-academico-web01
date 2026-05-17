INSERT INTO usuario (nome, email, login, senha, perfil, suap_id) VALUES
('Ricardo Oliveira Fontes', 'ricardo.fontes@academico.ifs.edu.br', 'ricardo.fontes', '123456', 'COORDENADOR', '9988776'),
('Carlos Eduardo Santana', 'carlos.santana@academico.ifs.edu.br', 'carlos.santana', '123456', 'PROFESSOR', '1122334'),
('Maria Clara Nunes', 'maria.nunes@academico.ifs.edu.br', 'maria.nunes', '123456', 'PROFESSOR', '2233445'),
('Roberto Almeida Filho', 'roberto.almeida@academico.ifs.edu.br', 'roberto.almeida', '123456', 'PROFESSOR', '3344556'),
('Fernanda Silva Costa', 'fernanda.costa@academico.ifs.edu.br', 'fernanda.costa', '123456', 'PROFESSOR', '4455667'),
('Lucas Mendes Pereira', 'lucas.pereira@academico.ifs.edu.br', 'lucas.pereira', '123456', 'PROFESSOR', '5566778'),
('José Menezes', 'jose.menezes064@academico.ifs.edu.br', 'jose.menezes064', '123456', 'ALUNO', '202410001'),
('Ana Beatriz Lima', 'ana.lima012@academico.ifs.edu.br', 'ana.lima012', '123456', 'ALUNO', '202410002'),
('Pedro Henrique Santos', 'pedro.santos089@academico.ifs.edu.br', 'pedro.santos089', '123456', 'ALUNO', '202410003'),
('Sofia Almeida', 'sofia.almeida045@academico.ifs.edu.br', 'sofia.almeida045', '123456', 'ALUNO', '202410004'),
('João Vitor Gomes', 'joao.gomes078@academico.ifs.edu.br', 'joao.gomes078', '123456', 'ALUNO', '202410005'),
('Laura Ribeiro', 'laura.ribeiro034@academico.ifs.edu.br', 'laura.ribeiro034', '123456', 'ALUNO', '202410006'),
('Gabriel Oliveira', 'gabriel.oliveira091@academico.ifs.edu.br', 'gabriel.oliveira091', '123456', 'ALUNO', '202410007'),
('Isabella Souza', 'isabella.souza056@academico.ifs.edu.br', 'isabella.souza056', '123456', 'ALUNO', '202410008'),
('Matheus Costa', 'matheus.costa023@academico.ifs.edu.br', 'matheus.costa023', '123456', 'ALUNO', '202410009'),
('Luiza Carvalho', 'luiza.carvalho067@academico.ifs.edu.br', 'luiza.carvalho067', '123456', 'ALUNO', '202410010');

INSERT INTO professor (usuario_id, matricula_siape, suap_id) VALUES
((SELECT id FROM usuario WHERE login = 'carlos.santana'), '1122334', '1122334'),
((SELECT id FROM usuario WHERE login = 'maria.nunes'), '2233445', '2233445'),
((SELECT id FROM usuario WHERE login = 'roberto.almeida'), '3344556', '3344556'),
((SELECT id FROM usuario WHERE login = 'fernanda.costa'), '4455667', '4455667'),
((SELECT id FROM usuario WHERE login = 'lucas.pereira'), '5566778', '5566778');

INSERT INTO aluno (usuario_id, curso_id, matricula, suap_id) VALUES
((SELECT id FROM usuario WHERE login = 'jose.menezes064'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410001', '202410001'),
((SELECT id FROM usuario WHERE login = 'ana.lima012'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410002', '202410002'),
((SELECT id FROM usuario WHERE login = 'pedro.santos089'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410003', '202410003'),
((SELECT id FROM usuario WHERE login = 'sofia.almeida045'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410004', '202410004'),
((SELECT id FROM usuario WHERE login = 'joao.gomes078'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410005', '202410005'),
((SELECT id FROM usuario WHERE login = 'laura.ribeiro034'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410006', '202410006'),
((SELECT id FROM usuario WHERE login = 'gabriel.oliveira091'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410007', '202410007'),
((SELECT id FROM usuario WHERE login = 'isabella.souza056'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410008', '202410008'),
((SELECT id FROM usuario WHERE login = 'matheus.costa023'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410009', '202410009'),
((SELECT id FROM usuario WHERE login = 'luiza.carvalho067'), (SELECT id FROM curso WHERE codigo_suap = 'SI01'), '202410010', '202410010');
