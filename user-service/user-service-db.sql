-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id bigserial NOT NULL,
	username varchar(255) NOT NULL,
	email varchar(50) NOT NULL,
	password_hash varchar(100) NOT NULL,
	first_name varchar(100) NULL,
	last_name varchar(100) NULL,
	phone_number varchar(11) NULL,
	created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	updated_at timestamp DEFAULT CURRENT_TIMESTAMP NULL,
	is_active bool DEFAULT true NULL,
	address varchar(255) NULL,
	"name" varchar(255) NULL,
	phone varchar(255) NULL,
	gender varchar(255) NOT NULL,
	CONSTRAINT ukq4gvg4dl2a3fpetfwspodde8e UNIQUE (email),
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_phone_number_key null,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_username_key UNIQUE (username)
);

-- public.roles definition

-- Drop table

-- DROP TABLE public.roles;

CREATE TABLE public.roles (
	id bigserial NOT NULL,
	"name" varchar(60) NOT NULL,
	CONSTRAINT roles_name_key UNIQUE (name),
	CONSTRAINT roles_pkey PRIMARY KEY (id),
	CONSTRAINT ukgdlljajjmqywje8kdxft3auoy UNIQUE (name)
);

-- public.user_role definition

-- Drop table

-- DROP TABLE public.user_role;

CREATE TABLE public.user_role (
	user_id int8 NOT NULL,
	role_id int8 NOT NULL,
	CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id)
);


-- public.user_role foreign keys

ALTER TABLE public.user_role ADD CONSTRAINT fkj345gk1bovqvfame88rcx7yyx FOREIGN KEY (user_id) REFERENCES public.users(id);
ALTER TABLE public.user_role ADD CONSTRAINT fkt7e7djp752sqn6w22i6ocqy6q FOREIGN KEY (role_id) REFERENCES public.roles(id);