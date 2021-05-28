--Do All This Before running your test, after which added must have been added
--Reset by truncating and refresh all tables Created.
SET FOREIGN_KEY_CHECKS = 0;

truncate table post;
truncate table author;
truncate table comment;
truncate table author_posts;

INSERT INTO post(id, title, content)
VALUES(41, 'Title Post 1', 'Post content 1'),
(42, 'Title Post 2', 'Post content 2'),
(43, 'Title Post 3', 'Post content 3'),
(44, 'Title Post 4', 'Post content 4'),
(45, 'Title Post 5', 'Post content 5');
--TheSql Statements above is to add data to table and save to the database, to be able to find all() data
--from the Post Table in the database.

SET FOREIGN_KEY_CHECKS = 1;