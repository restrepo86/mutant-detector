create table mutant_detector_history
(
	id						serial primary key,
	creation_date   		timestamp without time zone,
	dna 					text,
	mutant				    boolean
);