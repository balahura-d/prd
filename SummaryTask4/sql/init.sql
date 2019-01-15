-- ==============================================================
-- SummaryTask4 DB creation script for MySQL
-- ==============================================================


-- these commands remove all tables from the database
-- it implies an error if tables not exist in DB, just ignore it

DROP TABLE IF EXISTS requests;
DROP TABLE IF EXISTS flights;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS autos;
DROP TABLE IF EXISTS conditions;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;

-- --------------------------------------------------------------
-- ROLES -- 1
-- users roles
-- --------------------------------------------------------------
CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INT NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE) 	
	name VARCHAR(20) NOT NULL UNIQUE
);

-- this three commands insert data into roles table
-- --------------------------------------------------------------
-- we use ENUM as the Role entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'dispatcher');
INSERT INTO roles VALUES(2, 'driver');

-- --------------------------------------------------------------
-- USERS -- 2
-- --------------------------------------------------------------
CREATE TABLE users (
-- 'auto_increment' means id is autoincrement field 
-- (from 1 up to Integer.MAX_VALUE with the step 1)
	id INT NOT NULL auto_increment PRIMARY KEY,
	
-- 'UNIQUE' means logins values should not be repeated in login column of table	
	login VARCHAR(20) NOT NULL UNIQUE,
	
-- not null string columns	
	password VARCHAR(20) NOT NULL,
	full_name VARCHAR(40) NOT NULL,
	
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL,
    FOREIGN KEY(role_id) REFERENCES roles(id) 

-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
		ON DELETE RESTRICT 

-- the same as previous but updating is used insted deleting
		ON UPDATE CASCADE
);

INSERT INTO users VALUES(DEFAULT, 'admin', 'adminpass', 'Ivan Ivanov', 0);
INSERT INTO users VALUES(DEFAULT, 'disp1', 'disp1pass', 'Petr Petrov', 1);
INSERT INTO users VALUES(DEFAULT, 'driver1', 'driver1pass', 'Driver 1 name', 2);
INSERT INTO users VALUES(DEFAULT, 'driver2', 'driver2pass', 'Driver 2 name', 2);



-- --------------------------------------------------------------
-- CONDITIONS -- 3
-- conditions of autos
-- 0 - auto is used in flight
-- --------------------------------------------------------------
CREATE TABLE IF NOT EXISTS conditions (
  id INT NOT NULL PRIMARY KEY,
  name VARCHAR(15) NOT NULL UNIQUE
);

-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Status entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO conditions VALUES(0, 'is_used');
INSERT INTO conditions VALUES(1, 'new');
INSERT INTO conditions VALUES(2, 'was_in_use');
INSERT INTO conditions VALUES(3, 'minor_defects');
INSERT INTO conditions VALUES(4, 'damaged');
INSERT INTO conditions VALUES(5, 'to_write_off');

-- --------------------------------------------------------------
-- AUTOS -- 4
-- autos that our Motor Depot has
-- --------------------------------------------------------------
CREATE TABLE autos(
	id INTEGER NOT NULL auto_increment PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	seats INTEGER NOT NULL,
	condition_id INTEGER NOT NULL,
    FOREIGN KEY(condition_id) REFERENCES conditions(id) 
		ON DELETE RESTRICT 
		ON UPDATE CASCADE
);


INSERT INTO autos VALUES(DEFAULT, 'Газель', 18, 0); -- 1
INSERT INTO autos VALUES(DEFAULT, 'Богдан', 28, 1); -- 2
INSERT INTO autos VALUES(DEFAULT, 'Богдан', 28, 3); -- 3
INSERT INTO autos VALUES(DEFAULT, 'MAN', 50, 2); -- 4
												-- or not
-- --------------------------------------------------------------
-- STATUSES -- 5
-- statuses for orders
-- --------------------------------------------------------------
CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE
);

-- --------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Status entity, so the numeration must started 
-- from 0 with the step equaled to 1
-- --------------------------------------------------------------
INSERT INTO statuses VALUES(0, 'opened');
INSERT INTO statuses VALUES(1, 'in_progress');
INSERT INTO statuses VALUES(2, 'closed');
INSERT INTO statuses VALUES(3, 'canceled');

-- --------------------------------------------------------------
-- FLIGHTS -- 6
-- list of flights
-- --------------------------------------------------------------
CREATE TABLE flights(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20) NOT NULL,
	create_date DATE NOT NULL,
	auto_id INTEGER,
    FOREIGN KEY(auto_id) REFERENCES autos(id) 
		ON DELETE RESTRICT 
		ON UPDATE CASCADE,
	driver_id INTEGER,
    FOREIGN KEY(driver_id) REFERENCES users(id)    
		ON DELETE RESTRICT 
		ON UPDATE CASCADE,
	status_id INTEGER,
    FOREIGN KEY(status_id) REFERENCES statuses(id) 
		ON DELETE RESTRICT 
		ON UPDATE CASCADE
);

INSERT INTO flights VALUES (DEFAULT, '1472', '2008-10-24', 1,  3, 1);
INSERT INTO flights VALUES (DEFAULT, 'в Одессу', '2018-10-24', null,  null, 0);
INSERT INTO flights VALUES (DEFAULT, 'да тут рядом', '2015-10-24', null,  null, 0);

-- --------------------------------------------------------------
-- REQUESTS -- 7
-- --------------------------------------------------------------
CREATE TABLE requests(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	driver_id INTEGER NOT NULL,
    FOREIGN KEY(driver_id) REFERENCES users(id)
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	flight_id INTEGER NOT NULL,
    FOREIGN KEY(flight_id) REFERENCES flights(id) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	seats INT NOT NULL,
	status_id INTEGER,
    FOREIGN KEY(status_id) REFERENCES statuses(id) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	processed_by INTEGER,
    FOREIGN KEY(processed_by) REFERENCES users(id)
		ON DELETE SET NULL
        ON UPDATE CASCADE
);

insert into requests values (default, 4, 2, 15, 0, 4);	
-- --------------------------------------------------------------
-- test database:
-- --------------------------------------------------------------
SELECT * FROM requests;
SELECT * FROM flights;
SELECT * FROM statuses;
SELECT * FROM autos;
SELECT * FROM conditions;
SELECT * FROM users;
SELECT * FROM roles;

-- DISCONNECT;