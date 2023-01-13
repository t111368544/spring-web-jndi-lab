drop table Placard;

create table Placard (
	id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	message varchar(140) not null,
	created_at timestamp not null
);

drop table aUser;

create table aUser (
	id integer not null GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	username varchar(20) not null,
	password varchar(20) not null,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);
