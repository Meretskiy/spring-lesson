BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost bigint);
INSERT INTO products (title, cost) VALUES
('Milk', 95),
('Bread', 47),
('Cheese', 660),
('Beer', 160),
('Eggs', 99);

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO buyers (name) VALUES
('MrOne'),
('MrTwo'),
('MrThree');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders
(
    id          bigserial PRIMARY KEY,
    buyer_id    bigint REFERENCES buyers (id),
    product_id bigint REFERENCES products (id),
    current_price bigint,
    date timestamp
);
INSERT INTO orders (buyer_id, product_id, current_price, date) VALUES
(1, 1, 95, current_date),
(1, 2, 47, current_date),
(1, 3, 660, current_date),
(2, 2, 47, current_date),
(2, 3, 660, current_date),
(2, 4, 160, current_date),
(2, 5, 99, current_date);

COMMIT;

