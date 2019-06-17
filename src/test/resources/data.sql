INSERT INTO user (	id,
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
	whatsapp,) VALUES (1, 'Adolescentes e Crianças', true, 'info@memorynotfound.com', '$2a$10$RyY4bXtV3LKkDCutlUTYDOKd2AiJYZGp4Y7MPVdLzWzT1RX.JRZyG');

INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_MANAGER');
INSERT INTO role (id, name) VALUES (3, 'ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (1, 2);