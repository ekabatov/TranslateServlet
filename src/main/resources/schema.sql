create table words(
	id serial primary key,
	word VARCHAR(255)
);
CREATE TABLE transWords(
    id serial primary key,
    id_word INT,
    word VARCHAR(255)
);

