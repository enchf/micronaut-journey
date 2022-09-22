----------------------------
-- USER & SCHEMA
----------------------------

CREATE ROLE cataloguser WITH
  LOGIN
  SUPERUSER
  INHERIT
  CREATEDB
  CREATEROLE
  REPLICATION
  PASSWORD 'cataloguser';


CREATE SCHEMA catalogschema
    AUTHORIZATION cataloguser;

-----------------------------------------------------------
-- SCHEMA
-----------------------------------------------------------

SET SCHEMA 'catalogschema';

DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS types;

CREATE TABLE types (
	id serial,
	name character varying(80),
	CONSTRAINT pk_types PRIMARY KEY (id)
);
ALTER SEQUENCE types_id_seq restart with 4;
CREATE INDEX idx_types_name ON types (name);

CREATE TABLE products (
	id serial,
	name character varying(30),
	description character varying(255),
	type_id integer NOT NULL,
	FOREIGN KEY (type_id) REFERENCES types (id),
	CONSTRAINT pk_products PRIMARY KEY (id)
);
ALTER SEQUENCE products_id_seq restart with 5;
CREATE INDEX idx_products_name ON products (name);

------------------------------------------------------------
-- DATA
------------------------------------------------------------

SET SCHEMA 'catalogschema';

INSERT INTO types VALUES (1, 'Maceta');
INSERT INTO types VALUES (2, 'Planta');
INSERT INTO types VALUES (3, 'Otros');

INSERT INTO products VALUES (1, 'Cactus', 'Cactus Clasico', 2);
INSERT INTO products VALUES (2, 'Maceta Africana', 'Maceta con grecas africanas laterales', 1);
INSERT INTO products VALUES (3, 'Macrame Doble', 'Macrame doble de 1.5 mts de altura', 3);
INSERT INTO products VALUES (4, 'Suculenta', 'Suculenta Clasica', 2);
