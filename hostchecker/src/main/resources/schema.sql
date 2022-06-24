CREATE SEQUENCE IF NOT EXISTS host_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS hosts (
	id INT NOT NULL default (next value for host_seq) PRIMARY KEY,
	name VARCHAR(100) NOT NULL UNIQUE,
	ip VARCHAR(100) NOT NULL UNIQUE,
	is_alive VARCHAR(10) NOT NULL DEFAULT 'false',
	last_alive_time DATETIME,
	created_date DATETIME,
	modified_date DATETIME
);
