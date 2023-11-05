-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.


--CREATE DATABASE management;
--\c management;

CREATE SCHEMA users;
CREATE SCHEMA applications;
CREATE SCHEMA enterprise;


CREATE TABLE IF NOT EXISTS users."user"
(
    id_user SERIAL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL UNIQUE,
    login character varying(30) COLLATE pg_catalog."default" NOT NULL UNIQUE,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    high_date timestamp NOT NULL,
    high_id_user integer references users.user(id_user),
    delete_date timestamp,
    delete_id_user integer references users.user(id_user),
    CONSTRAINT user_pk PRIMARY KEY (id_user)
);

CREATE TABLE IF NOT EXISTS applications.application
(
    id_application SERIAL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    high_date timestamp NOT NULL,
    high_id_user integer  NOT NULL references users.user(id_user),
    delete_date timestamp,
    delete_id_user integer references users.user(id_user),
    CONSTRAINT applications_pk PRIMARY KEY (id_application)
);

CREATE TABLE IF NOT EXISTS applications.attribute
(
    id_atribute SERIAL  ,
    id_application integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT attribute_pkey PRIMARY KEY (id_atribute)
);

CREATE TABLE IF NOT EXISTS applications.attribute_language
(
    id_attribute integer NOT NULL,
    id_language integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT attribute_language_pkey PRIMARY KEY (id_attribute, id_language)
);

CREATE TABLE IF NOT EXISTS applications.permission
(
    id_permision SERIAL  ,
    id_application integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT permission_pkey PRIMARY KEY (id_permision)
);

CREATE TABLE IF NOT EXISTS applications.permission_language
(
    id_permission integer NOT NULL,
    id_language integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT permission_language_pkey PRIMARY KEY (id_permission, id_language)
);

CREATE TABLE IF NOT EXISTS applications.role
(
    id_role SERIAL  ,
    id_application integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (id_role)
);

CREATE TABLE IF NOT EXISTS applications.role_language
(
    id_role integer NOT NULL,
    id_language integer NOT NULL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    description character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT role_language_pkey PRIMARY KEY (id_role, id_language)
);

CREATE TABLE IF NOT EXISTS applications.role_permission
(
    id_role integer NOT NULL,
    id_permission integer NOT NULL,
    CONSTRAINT role_permission_pkey PRIMARY KEY (id_role, id_permission)
);

CREATE TABLE IF NOT EXISTS public.language
(
    id_language SERIAL,
    name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    iso_639_1_code character varying(50) COLLATE pg_catalog."default" NOT NULL,
    language_family character varying(50) NOT NULL,
    CONSTRAINT language_pkey PRIMARY KEY (id_language)
);


CREATE TABLE IF NOT EXISTS users.user_application
(
    id_user integer NOT NULL,
    id_application integer NOT NULL,
    CONSTRAINT user_application_pkey PRIMARY KEY (id_user, id_application)
);

CREATE TABLE IF NOT EXISTS users.user_attribute
(
    id_user integer NOT NULL,
    id_attribute integer NOT NULL,
    value character varying(255) COLLATE pg_catalog."default" NOT NULL
);

CREATE TABLE IF NOT EXISTS users.user_permission
(
    id_user integer NOT NULL,
    id_permission integer NOT NULL,
    CONSTRAINT user_permission_pkey PRIMARY KEY (id_user, id_permission)
);

CREATE TABLE IF NOT EXISTS users.user_role
(
    id_user integer NOT NULL,
    id_role integer NOT NULL,
    CONSTRAINT user_role_pkey PRIMARY KEY (id_user, id_role)
);

CREATE TABLE IF NOT EXISTS applications.application_language
(
    id_application integer NOT NULL,
    id_language integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(255),
    PRIMARY KEY (id_application, id_language)
);

ALTER TABLE IF EXISTS applications.attribute
    ADD CONSTRAINT fk_attribute_id_application FOREIGN KEY (id_application)
    REFERENCES applications.application (id_application) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.attribute_language
    ADD CONSTRAINT fk_attribute_language_id_attribute FOREIGN KEY (id_attribute)
    REFERENCES applications.attribute (id_atribute) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.attribute_language
    ADD CONSTRAINT fk_attribute_language_id_language FOREIGN KEY (id_language)
    REFERENCES public.language (id_language) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.permission
    ADD CONSTRAINT fk_permission_id_application FOREIGN KEY (id_application)
    REFERENCES applications.application (id_application) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.permission_language
    ADD CONSTRAINT fk_permission_language_id_language FOREIGN KEY (id_language)
    REFERENCES public.language (id_language) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.permission_language
    ADD CONSTRAINT fk_permission_language_id_permission FOREIGN KEY (id_permission)
    REFERENCES applications.permission (id_permision) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.role
    ADD CONSTRAINT fk_role_application_id_application FOREIGN KEY (id_application)
    REFERENCES applications.application (id_application) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.role_language
    ADD CONSTRAINT fk_role_language_id_language FOREIGN KEY (id_language)
    REFERENCES public.language (id_language) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.role_language
    ADD CONSTRAINT fk_role_language_id_role FOREIGN KEY (id_role)
    REFERENCES applications.role (id_role) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.role_permission
    ADD CONSTRAINT fk_role_permission_id_permission FOREIGN KEY (id_permission)
    REFERENCES applications.permission (id_permision) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.role_permission
    ADD CONSTRAINT fk_role_permission_id_role FOREIGN KEY (id_role)
    REFERENCES applications.role (id_role) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_application
    ADD CONSTRAINT fk_user_application_id_application FOREIGN KEY (id_application)
    REFERENCES applications.application (id_application) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_application
    ADD CONSTRAINT fk_user_application_id_user FOREIGN KEY (id_user)
    REFERENCES users."user" (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_attribute
    ADD CONSTRAINT fk_user_attribute_id_attribute FOREIGN KEY (id_attribute)
    REFERENCES applications.attribute (id_atribute) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_attribute
    ADD CONSTRAINT fk_user_attribute_id_user FOREIGN KEY (id_user)
    REFERENCES users."user" (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_permission
    ADD CONSTRAINT fk_user_permission_id_permission FOREIGN KEY (id_permission)
    REFERENCES applications.permission (id_permision) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_permission
    ADD CONSTRAINT fk_user_permission_id_user FOREIGN KEY (id_user)
    REFERENCES users."user" (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_role
    ADD CONSTRAINT fk_user_role_id_role FOREIGN KEY (id_role)
    REFERENCES applications.role (id_role) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS users.user_role
    ADD CONSTRAINT fk_user_role_id_user FOREIGN KEY (id_user)
    REFERENCES users."user" (id_user) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.application_language
    ADD CONSTRAINT fk_application_language_id_application FOREIGN KEY (id_application)
    REFERENCES applications.application (id_application) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS applications.application_language
    ADD CONSTRAINT fk_application_language_id_language FOREIGN KEY (id_language)
    REFERENCES public.language (id_language) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;