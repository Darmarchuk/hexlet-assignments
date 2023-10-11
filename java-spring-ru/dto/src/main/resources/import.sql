insert into author (author_id, first_Name, last_Name,email) values (1, 'Dmirty', 'Armarchuk','mail@author.ru');


insert into posts (id, title, body,author_id) values (1, 'Post Title 1', 'Post Body 1',1);
insert into posts (id, title, body,author_Id) values (2, 'Post Title 2', 'Post Body 2',1);
insert into posts (id, title, body,author_Id) values (3, 'Post Title 3', 'Post Body 3',1);
insert into posts (id, title, body,author_Id) values (4, 'Post Title 4', 'Post Body 4',1);
insert into posts (id, title, body,author_Id) values (5, 'Post Title 5', 'Post Body 5',1);
insert into posts (id, title, body,author_Id) values (6, 'Post Title 6', 'Post Body 6',1);

insert into comments (id, post_id, body) values (1, 1, 'Comment Body 1');
insert into comments (id, post_id, body) values (2, 1, 'Comment Body 2');
insert into comments (id, post_id, body) values (3, 1, 'Comment Body 3');
insert into comments (id, post_id, body) values (4, 2, 'Comment Body 4');
insert into comments (id, post_id, body) values (5, 2, 'Comment Body 5');
insert into comments (id, post_id, body) values (6, 3, 'Comment Body 6');
