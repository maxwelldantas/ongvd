CREATE TABLE endereco (
	id bigserial NOT NULL,
	bairro varchar(255) NOT NULL,
	cep varchar(255) NOT NULL,
	cidade varchar(255) NOT NULL,
	complemento varchar(255) NULL,
	logradouro varchar(255) NOT NULL,
	numero int4 NULL,
	uf varchar(255) NOT NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE endereco OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE endereco TO ubdeggcbvkafwn;

CREATE TABLE evento (
	id bigserial NOT NULL,
	bairro varchar(255) NOT NULL,
	cep varchar(255) NOT NULL,
	cidade varchar(255) NOT NULL,
	complemento varchar(255) NULL,
	data_atualizacao timestamp NULL,
	data_desabilitado timestamp NULL,
	data_inclusao timestamp NOT NULL,
	descricao varchar(5000) NOT NULL,
	habilitado bool NULL,
	horario varchar(255) NOT NULL,
	ingresso varchar(255) NOT NULL,
	logradouro varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	numero int4 NULL,
	uf varchar(255) NOT NULL,
	ong_id int8 NULL,
	CONSTRAINT evento_pkey PRIMARY KEY (id),
	CONSTRAINT ong_fk FOREIGN KEY (ong_id) REFERENCES ong(id)
);

-- Permissions

ALTER TABLE evento OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE evento TO ubdeggcbvkafwn;

CREATE TABLE ong (
	id bigserial NOT NULL,
	area_de_atuacao varchar(255) NOT NULL,
	ativo bool NULL,
	cnpj varchar(255) NOT NULL,
	contato varchar(255) NOT NULL,
	email varchar(255) NOT NULL,
	fundacao date NULL,
	nome_fantasia varchar(255) NOT NULL,
	razao_social varchar(255) NOT NULL,
	responsavel varchar(255) NOT NULL,
	senha varchar(255) NOT NULL,
	telefone varchar(255) NOT NULL,
	website varchar(255) NULL,
	whatsapp varchar(255) NULL,
	CONSTRAINT ong_pkey PRIMARY KEY (id),
	CONSTRAINT email_cnpj_unique UNIQUE (email, cnpj)
);

-- Permissions

ALTER TABLE ong OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE ong TO ubdeggcbvkafwn;

CREATE TABLE pedido_doacao (
	id bigserial NOT NULL,
	data_atualizacao timestamp NULL,
	data_desabilitado timestamp NULL,
	data_inclusao timestamp NOT NULL,
	descricao varchar(5000) NOT NULL,
	habilitado bool NULL,
	item_pedido varchar(255) NOT NULL,
	nome varchar(255) NOT NULL,
	quantidade varchar(255) NULL,
	valor_pedido varchar(255) NULL,
	ong_id int8 NULL,
	CONSTRAINT pedido_doacao_pkey PRIMARY KEY (id),
	CONSTRAINT ong_fk FOREIGN KEY (ong_id) REFERENCES ong(id)
);

-- Permissions

ALTER TABLE pedido_doacao OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE pedido_doacao TO ubdeggcbvkafwn;

CREATE TABLE "role" (
	id bigserial NOT NULL,
	nome varchar(255) NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE "role" OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE "role" TO ubdeggcbvkafwn;

CREATE TABLE servico_voluntario (
	id bigserial NOT NULL,
	data_atualizacao timestamp NULL,
	data_desabilitado timestamp NULL,
	data_inclusao timestamp NOT NULL,
	descricao varchar(5000) NOT NULL,
	habilitado bool NULL,
	nome varchar(255) NOT NULL,
	ong_id int8 NULL,
	CONSTRAINT servico_voluntario_pkey PRIMARY KEY (id),
	CONSTRAINT ong_fk FOREIGN KEY (ong_id) REFERENCES ong(id)
);

-- Permissions

ALTER TABLE servico_voluntario OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE servico_voluntario TO ubdeggcbvkafwn;

CREATE TABLE users_enderecos (
	ong_id int8 NOT NULL,
	endereco_id int8 NOT NULL,
	CONSTRAINT ong_fk FOREIGN KEY (ong_id) REFERENCES ong(id),
	CONSTRAINT endereco_fk FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- Permissions

ALTER TABLE users_enderecos OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE users_enderecos TO ubdeggcbvkafwn;

CREATE TABLE users_roles (
	ong_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT ong_fk FOREIGN KEY (ong_id) REFERENCES ong(id),
	CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Permissions

ALTER TABLE users_roles OWNER TO ubdeggcbvkafwn;
GRANT ALL ON TABLE users_roles TO ubdeggcbvkafwn;
