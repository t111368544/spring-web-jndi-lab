create table Placard (
	id identity,
	message varchar(140) not null,
	created_at timestamp not null
);

create table aUser (
	id identity,
	username varchar(20) unique not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);
