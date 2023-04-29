drop table junction_table;
drop table albums;
drop table artists;
drop table genres;


create table artists (id int primary key, name varchar2(100) not null);
create table genres (id int primary key, name varchar2(100) not null);
create table albums (
                        id int primary key,
                        release_year int not null,
                        title varchar2(100) not null,
                        artist int not null,
                        genre int not null,
                        constraint album_id_artist foreign key (artist) references artists(id),
                        constraint album_id_genre foreign key (genre) references genres(id)
);
create table junction_table(
                               id_gen int not null,
                               id_album int not null,
                               constraint junct_gen foreign key (id_gen) references genres(id),
                               constraint junct_album foreign key(id_album) references albums(id),
                               constraint pk_key primary key(id_gen, id_album)
);

CREATE SEQUENCE artist_increment START WITH 1;
CREATE SEQUENCE genres_increment START WITH 1;
CREATE SEQUENCE albums_increment START WITH 1;

CREATE OR REPLACE TRIGGER artist_add
    BEFORE INSERT ON artists
    FOR EACH ROW
BEGIN
    SELECT artist_increment.NEXTVAL
    INTO   :new.id
    FROM   dual;
END;

CREATE OR REPLACE TRIGGER genres_add
    BEFORE INSERT ON genres
    FOR EACH ROW
BEGIN
    SELECT genres_increment.NEXTVAL
    INTO   :new.id
    FROM   dual;
END;

CREATE OR REPLACE TRIGGER albums_add
    BEFORE INSERT ON albums
    FOR EACH ROW

BEGIN
    SELECT albums_increment.NEXTVAL
    INTO   :new.id
    FROM   dual;
END;

drop trigger genres_add;
drop trigger albums_add;
drop trigger artist_add;




drop sequence albums_increment;
drop sequence artist_increment;
drop sequence genres_increment;