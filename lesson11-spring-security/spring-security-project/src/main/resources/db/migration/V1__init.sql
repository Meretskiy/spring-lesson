create table score (
    id                  bigserial,
    quantity            int,
    primary key (id)
);

create table users (
  id                    bigserial,
  username              varchar(30) not null,
  password              varchar(80) not null,
  email                 varchar(50) unique,
  score_id              bigint,
  primary key (id),
  foreign key (score_id) references score (id)
);

create table roles (
  id                    bigserial,
  name                  varchar(50) not null,
  primary key (id)
);

CREATE TABLE users_roles (
  user_id               bigint not null,
  role_id               bigint not null,
  primary key (user_id, role_id),
  foreign key (user_id) references users (id),
  foreign key (role_id) references roles (id)
);

insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into score (quantity)
values
(60),
(70),
(80);

insert into users (username, password, email, score_id)
values
('user1', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user1@gmail.com', 1),
('user2', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user2@gmail.com', 2),
('user3', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user3@gmail.com', 3);

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 1),
(3, 1),
(1, 2);

