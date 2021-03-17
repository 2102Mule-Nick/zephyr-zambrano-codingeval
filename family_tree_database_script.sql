create table accounts.accounts (
	user_name text primary key,
	pass_word text
);

create table familytree.familytree (
	person text primary key,
	parent1 text,
	parent2 text,
	user_name text
);

select * from accounts.accounts;

select * from familytree.familytree;