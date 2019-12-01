BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (product_id bigserial PRIMARY KEY, title_fld VARCHAR(255), cost_fld float);
INSERT INTO products (title_fld, cost_fld) VALUES
('milk', 10.5),
('meat', 37.2),
('tea', 371.2),
('apple', 372.2),
('carrots', 378.2),
('limone', 337.2),
('pizza', 327.2),
('cofe', 37.2),
('cake', 374.2),
('toast', 357.2),
('mango', 367.2),
('vegetables', 377.2),
('bread', 387.2),
('biscuits', 397.2),
('popcorn', 317.2),
('water', 27.2),
('coke', 47.2),
('potatoes', 57.2),
('pasta', 77.2),
('orange juce', 837.2),
('burgers', 72.2);



COMMIT;