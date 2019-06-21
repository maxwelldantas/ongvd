INSERT INTO ong (
	id,
	area_de_atuacao,
	ativo,
	cnpj,
	contato,
	email,
	fundacao,
	nome_fantasia,
	razao_social,
	responsavel,
	senha,
	telefone,
	website,
	whatsapp,) VALUES (1, 'Adolescentes e Crianças', true, '16.908.250/0001-64',
	'Maxwell', 'maxwelldsouza@hotmail.com', '2009-12-12', 'Missão Africa',
	'Missão Africa', 'Maxwell', '$2a$10$HuVeZpeW1r36paOvwGBL7e2hcKXq64KN.V6QNO.opHIBFTtFsxFqO',
	'(34) 99204-0640', 'www.missaoafrica.org.br', '+55 (34) 9204-0640');

INSERT INTO endereco (
	id,
	bairro,
	cep,
	cidade,
	complemento,
	logradouro,
	numero,
	uf) VALUES (1, 'Planalto', '38413-195', 'Uberlândia',
	'', 'Rua do Carteiro', 8686, 'MG');

INSERT INTO role (id, nome) VALUES (1, 'ROLE_USER');
INSERT INTO users_roles (ong_id, role_id) VALUES (1, 1);
INSERT INTO users_enderecos (ong_id, endereco_id) VALUES (1, 1);

