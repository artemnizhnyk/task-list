insert into task_list_schema.users (id, name, username, password)
values (nextval('users_seq'), 'Artem Nizhnyk', 'artem@gmail.com', '$2a$10$Xl0yhvzLIaJCDdKBS0Lld.ksK7c2Zytg/ZKFdtIYYQUv8rUfvCR4W'),
       (nextval('users_seq'), 'Any Any', 'any@gmail.com', '$2a$10$fFLij9aYgaNCFPTL9WcA/uoCRukxnwf.vOQ8nrEEOskrCNmGsxY7m');

insert into tasks (id, title, description, status, expiration_date, user_id)
values (nextval('tasks_seq'), 'Buy cheese', null, 'TODO', '2023-01-29 12:00:00', 1),
       (nextval('tasks_seq'), 'Do homework', 'Math, Physics, Literature', 'IN_PROGRESS', '2023-01-31 00:00:00', 1),
       (nextval('tasks_seq'), 'Clean rooms', null, 'DONE', null, 1),
       (nextval('tasks_seq'), 'Call Mike', 'Ask about meeting', 'TODO', '2023-02-01 00:00:00', 1);