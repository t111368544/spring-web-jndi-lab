create table Placard (
	id integer not null,
	message varchar(140) not null,
	created_at timestamp not null
);

create table User (
	id integer not null,
	username varchar(20) not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);
