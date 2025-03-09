-- DROP SCHEMA auth;

CREATE SCHEMA auth AUTHORIZATION postgres;

CREATE TABLE auth."role" (
	nivel varchar(255) NOT NULL,
	CONSTRAINT role_pkey PRIMARY KEY (nivel)
);

CREATE SEQUENCE auth.usuario_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;-- auth."role" definition
	
CREATE TABLE auth.usuario (
	id bigserial NOT NULL,
	is_ativo bool NOT NULL,
	data_atualizacao timestamp(6) NULL,
	data_criacao timestamp(6) NULL,
	"version" int4 NOT NULL,
	ativado bool NOT NULL,
	email varchar(255) NULL,
	login varchar(255) NULL,
	nome varchar(255) NULL,
	senha varchar(255) NULL,
	role_name varchar(255) NOT NULL,
	CONSTRAINT usuario_pkey PRIMARY KEY (id),
	CONSTRAINT fkkcj1gkcxmvml6g6tws0uvxl8v FOREIGN KEY (role_name) REFERENCES auth."role"(nivel)
);

INSERT INTO auth.role (nivel) VALUES ('ROLE_USER'), ('ROLE_ADMIN') ON CONFLICT DO NOTHING;

INSERT INTO auth.usuario (id, is_ativo, data_atualizacao, data_criacao, "version", ativado, email, login, nome, senha, role_name) VALUES(1, true, now(), now(), 0, true, 'changeme@email.com', 'milo', 'Milo', '$2a$10$4z90FoQhXWW1PNRk3Q/h5ep/2L1jXNC0QPf8.KIHu9tkmR/el4kw6', 'ROLE_ADMIN');