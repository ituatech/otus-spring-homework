insert into AUTHORS (AUTHOR_NAME) values ('vasya');
insert into AUTHORS (AUTHOR_NAME) values ('petya');
insert into AUTHORS (AUTHOR_NAME) values ('masha');
insert into AUTHORS (AUTHOR_NAME) values ('olya');

insert into GENRES (GENRE_NAME) values ('comedy');
insert into GENRES (GENRE_NAME) values ('fiction');
insert into GENRES (GENRE_NAME) values ('horror');

insert into BOOKS (title, description) values ('title1', 'descr1');
insert into BOOKS (title, description) values ('title2', 'descr2');

insert into BOOK_TO_AUTHOR (BOOK_ID, AUTHOR_ID) values (1, 1);
insert into BOOK_TO_AUTHOR (BOOK_ID, AUTHOR_ID) values (1, 3);
insert into BOOK_TO_AUTHOR (BOOK_ID, AUTHOR_ID) values (2, 2);

insert into BOOK_TO_GENRE (BOOK_ID, GENRE_ID) values (1, 1);
insert into BOOK_TO_GENRE (BOOK_ID, GENRE_ID) values (2, 2);
insert into BOOK_TO_GENRE (BOOK_ID, GENRE_ID) values (2, 3);