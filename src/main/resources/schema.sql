DROP ALL OBJECTS; 

CREATE SCHEMA `laboratory`;

use `laboratory`;

-- CREATE User table
CREATE TABLE userslab (
    id INT NOT NULL,
    email varchar(100) not null default '',
    name varchar(50) default null,
    firstname varchar(30) default null,
    password varchar(100) not null,
    role varchar(10) not null default 'VIEWER',
    CONSTRAINT pk_id PRIMARY KEY (id)
);