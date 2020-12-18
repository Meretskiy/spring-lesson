BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('Milk', 95),
('Bread', 47),
('Cheese', 660),
('Beer', 160),
('Eggs', 99);

COMMIT;