create schema costtracker;

create table costtracker.agent (
	agent_id int not null auto_increment,
	agent_name varchar(200),
	
	primary key (agent_id)
);

create table costtracker.category (
	cat_id int not null auto_increment,
	cat_name varchar(200),
	cat_desc varchar(2000),

	primary key(cat_id)
);

create table costtracker.cost (
	cost_id int not null auto_increment,
	cost_name varchar(200),
	cost_desc varchar(2000),
	cost_cat int not null,
	cost_agent int not null,
	cost_amount double,
	cost_dt timestamp default current_timestamp,

	primary key(cost_id),
	foreign key(cost_agent) references costtracker.agent(agent_id),
	foreign key(cost_cat) references costtracker.category(cat_id)
);

-- drop schema costtracker;

