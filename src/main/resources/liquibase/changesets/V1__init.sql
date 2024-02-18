CREATE TABLE IF NOT EXISTS users
(
    id       bigserial primary key,
    name     varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    role     varchar(255) not null check (role in ('ROLE_USER', 'ROLE_ADMIN'))
);

CREATE TABLE IF NOT EXISTS tasks
(
    id              bigserial primary key,
    title           varchar(255) not null,
    description     varchar(255) null,
    status          varchar(255) not null,
    expiration_date timestamp(6) null,
    user_id         bigint       not null,
    constraint fk_users foreign key (user_id) references users (id)
        on delete cascade on update no action
);

create table if not exists tasks_images
(
    task_id bigint       not null,
    image   varchar(255) not null,
    constraint fk_tasks_images_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
    );