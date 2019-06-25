CREATE TABLE email (
	id bigserial NOT NULL,
	sendgrid boolean NULL,
	apikey varchar(255) NULL,
	CONSTRAINT email_pkey PRIMARY KEY (id)
);
