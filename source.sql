BEGIN;
set search_path to bootdata;

drop table if exists categories cascade;
create table categories (category_id bigserial, title_fld varchar(255), primary key(category_id));
insert into categories
(title_fld) values
('drinks'), ('fastfood'),('dessert'),('fruits');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (product_id bigserial PRIMARY KEY, category_id bigint,description_fld VARCHAR(512), title_fld VARCHAR(255), cost_fld numeric(8,2), constraint fk_cat_id foreign key (category_id) references categories (category_id));
INSERT INTO products (title_fld, category_id,description_fld, cost_fld) VALUES
('milk',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 10.5),
('sausage',2,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 37.2),
('curd',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 371.2),
('sour cream',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 372.2),
('carrots',4,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 378.2),
('limone',4,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 337.2),
('pizza',2,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 327.2),
('cofe',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 37.2),
('cake',3,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 374.2),
('toast',2,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 357.2),
('mango',4,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 367.2),
('bread',3,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 387.2),
('biscuits',3,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 397.2),
('popcorn', 2,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 317.2),
('water',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 27.2),
('coke',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 47.2),
('potatoes',4,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 57.2),
('pasta',2,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 77.2),
('orange juce',1,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 837.2),
('burgers',2,'Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black',  72.2);



DROP TABLE IF EXISTS users cascade;
DROP TABLE IF EXISTS roles cascade;
DROP TABLE IF EXISTS users_roles cascade;
CREATE TABLE users (
                       id                    bigserial,
                       phone                 VARCHAR(30) NOT NULL UNIQUE,
                       password              VARCHAR(80),
                       email                 VARCHAR(50) UNIQUE,
                       first_name            VARCHAR(50),
                       last_name             VARCHAR(50),
                       PRIMARY KEY (id)
);

CREATE TABLE roles (
                       id                    serial,
                       name                  VARCHAR(50) NOT NULL,
                       PRIMARY KEY (id)
);

CREATE TABLE users_roles (
                             user_id               INT NOT NULL,
                             role_id               INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id)
                                 REFERENCES users (id),
                             FOREIGN KEY (role_id)
                                 REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_CUSTOMER'), ('ROLE_MANAGER'), ('ROLE_ADMIN');

INSERT INTO users (phone, password, first_name, last_name, email)
VALUES
('1111','$2y$04$M6i.Dnslf6AumF9iCI9OfeFUWJJuU/EILpD3cDpGZXbYeWKDe2VMO','Admin','Admin','admin@gmail.com'),
('manager1','$2y$04$M6i.Dnslf6AumF9iCI9OfeFUWJJuU/EILpD3cDpGZXbYeWKDe2VMO','Manager1','Manager1','manager1@gmail.com'),
('user1','$2y$04$M6i.Dnslf6AumF9iCI9OfeFUWJJuU/EILpD3cDpGZXbYeWKDe2VMO','user1','user1','user1@gmail.com');

INSERT INTO users_roles (user_id, role_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(3, 1);

drop table if exists orders cascade;
create table orders (order_id bigserial, user_id bigint, cost_fld numeric(8, 2), primary key(order_id), constraint fk_user_id foreign key (user_id) references users (id));

drop table if exists orders_items cascade;
create table orders_items (orders_items_id bigserial, order_id bigint, product_id bigint, quantity_fld int, cost_fld numeric(8, 2), primary key(orders_items_id), constraint fk_prod_id foreign key (product_id) references products (product_id), constraint fk_order_id foreign key (order_id) references orders (order_id));

COMMIT;