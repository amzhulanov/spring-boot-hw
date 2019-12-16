BEGIN;
set search_path to bootdata;
DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE products (product_id bigserial PRIMARY KEY, category_fld VARCHAR(255),description_fld VARCHAR(512), title_fld VARCHAR(255), cost_fld float);
INSERT INTO products (title_fld, category_fld,description_fld, cost_fld) VALUES
('milk','dairt','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 10.5),
('sausage','meat','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 37.2),
('curd','dairt','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 371.2),
('sour cream','dairt','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 372.2),
('carrots','vegetables','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 378.2),
('limone','fruits','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 337.2),
('pizza','fastfood','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 327.2),
('cofe','drinks','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 37.2),
('cake','dessert','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 374.2),
('toast','fastfood','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 357.2),
('mango','fruits','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 367.2),
('bread','dessert','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 387.2),
('biscuits','dessert','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 397.2),
('popcorn', 'fastfood','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 317.2),
('water','drinks','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 27.2),
('coke','drinks','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 47.2),
('potatoes','vegetables','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 57.2),
('pasta','spaghetti','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 77.2),
('orange juce','drinks','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black', 837.2),
('burgers','fastfood','Seat and back with upholstery made of cold cure foam. Steel frame, available in matt powder-coated black',  72.2);



COMMIT;