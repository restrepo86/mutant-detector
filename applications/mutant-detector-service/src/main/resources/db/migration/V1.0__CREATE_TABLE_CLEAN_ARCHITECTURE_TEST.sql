create table task
(
	id						serial primary key,
	name_of_task 			text,
	creation_date			timestamp without time zone,
	day_of_task   		    integer
);