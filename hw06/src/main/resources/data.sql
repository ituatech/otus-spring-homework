insert into AUTHORS (AUTHOR_NAME) values ('vasya');
insert into AUTHORS (AUTHOR_NAME) values ('petya');
insert into AUTHORS (AUTHOR_NAME) values ('masha');
insert into AUTHORS (AUTHOR_NAME) values ('olya');

insert into GENRES (GENRE_NAME) values ('comedy');
insert into GENRES (GENRE_NAME) values ('fiction');
insert into GENRES (GENRE_NAME) values ('horror');

insert into BOOKS (title, description) values ('title1', 'descr1');
insert into BOOKS (title, description) values ('title2', 'descr2');

insert into AUTHOR_BOOK (BOOK_ID, AUTHOR_ID) values (1, 1);
insert into AUTHOR_BOOK (BOOK_ID, AUTHOR_ID) values (1, 3);
insert into AUTHOR_BOOK (BOOK_ID, AUTHOR_ID) values (2, 2);

insert into GENRE_BOOK (BOOK_ID, GENRE_ID) values (1, 1);
insert into GENRE_BOOK (BOOK_ID, GENRE_ID) values (2, 2);
insert into GENRE_BOOK (BOOK_ID, GENRE_ID) values (2, 3);

insert into COMMENTS (COMMENT_TEXT, CREATED_AT, USER_NAME, BOOK_ID) values ('test comment', '2018-01-01T12:00:00.0000000+00:00', 'test user', 1)