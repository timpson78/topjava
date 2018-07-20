DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO meals (id_user, datetime, description, calories) VALUES (100000, '2016-07-16 09:11', 'Завтрак', 600);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100000, '2016-07-16 13:11', 'Обед', 500);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100000, '2016-07-16 16:15', 'Ланч', 200);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100000, '2016-07-16 19:10', 'Ужин', 700);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100001, '2017-07-16 09:13', 'Завтрак', 800);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100001, '2017-07-16 14:14', 'Обед', 700);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100001, '2017-07-16 20:14', 'Ужин', 700);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100001, '2018-07-16 09:16', 'Завтрак', 900);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100001, '2018-07-16 13:17', 'Обед', 400);
INSERT INTO meals (id_user, datetime, description, calories) VALUES (100001, '2018-07-16 20:17', 'Ужин', 600);
