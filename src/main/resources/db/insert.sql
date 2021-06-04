--Do All This Before running your test, after which added must have been added
--Reset by truncating and refresh all tables Created.
SET FOREIGN_KEY_CHECKS = 0;

truncate table post;
truncate table author;
truncate table comment;
truncate table author_posts;
truncate table post_comments;

INSERT INTO post(id, title, content, date_created)
VALUES(41, 'Title Post 1', 'Post content 1','2021-06-03T11:33:56'),
(42, 'Title Post 2', 'Post content 2','2021-06-03T11:34:56'),
(43, 'Title Post 3', 'Post content 3','2021-06-03T11:35:56'),
(44, 'Title Post 4', 'Post content 4','2021-06-03T11:36:56'),
(45, 'Title Post 5', 'Post content 5','2021-06-03T11:37:56');
--TheSql Statements above is to add data to table and save to the database, to be able to find all() data
--from the Post Table in the database.

SET FOREIGN_KEY_CHECKS = 1;