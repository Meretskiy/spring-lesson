create table products (
    id                      bigserial primary key,
    title                   varchar(255) not null,
    price                   int not null
);

insert into products (title, price)
values
('Milk', 80),
('Bread', 25);