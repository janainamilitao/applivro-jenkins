CREATE SEQUENCE profile_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1;
  

CREATE TABLE profile
(
  id bigint NOT NULL,
  date_of_birth date NOT NULL,
  email character varying(50) NOT NULL,
  ip_address character varying(50) NOT NULL,
  name character varying(50) NOT NULL,
  postal_code character varying(50) NOT NULL,
  CONSTRAINT profile_pkey PRIMARY KEY (id)
)
