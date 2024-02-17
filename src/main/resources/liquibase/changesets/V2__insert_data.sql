insert into users (id, name, username, password, role)
values (nextval('users_id_seq'), 'Artem Nizhnyk', 'artem@gmail.com', '$2a$12$LjwUO4iEMm8OvysL0K2E.u32PLe6HILuvkkub6pBIMDyiqIFuAIju', 'ROLE_USER'),
       (nextval('users_id_seq'), 'Any Any', 'any@gmail.com', '$2a$12$ka45EqjF6LLi./tAIVg1UOr3T09.5asve3FAFopV4hjtdxKy6ZT3e', 'ROLE_USER');

insert into tasks (id, title, description, status, expiration_date, user_id)
values (nextval('tasks_id_seq'), 'Buy cheese', null, 'TODO', '2023-01-29 12:00:00', 1),
       (nextval('tasks_id_seq'), 'Do homework', 'Math, Physics, Literature', 'IN_PROGRESS', '2023-01-31 00:00:00', 1),
       (nextval('tasks_id_seq'), 'Clean rooms', null, 'DONE', null, 2),
       (nextval('tasks_id_seq'), 'Call Mike', 'Ask about meeting', 'TODO', '2023-02-01 00:00:00', 2);