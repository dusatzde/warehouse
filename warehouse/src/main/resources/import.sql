INSERT INTO address (version, bnr, city, country, street, zip) VALUES (0, '6', 'Prague', 'Czech Republic', 'Kralicka','100 00');
INSERT INTO address (version, bnr, city, country, street, zip) VALUES (0, '6', 'Prague', 'Czech Republic', 'Kralicka', '100 00');
INSERT INTO address (version, bnr, city, country, street, zip) VALUES (0, '6', 'Prague', 'Czech Republic', 'Kralicka', '100 00');
INSERT INTO address (version, bnr, city, country, street, zip) VALUES (0, '6', 'New York', 'USA', '5th Avenue.', '100 00');


INSERT INTO userentity (version, firstname, lastname, email, password, phone, role, address_id) VALUES (0, 'Zdenek', 'Dusatko', 'dusatze@fel.cvut.cz', MD5('mojeheslo'), '607793283', 'customer', 1);
INSERT INTO userentity (version, firstname, lastname, email, password, phone, role, address_id) VALUES (0, 'Zdenek', 'Dusatko', 'manager', MD5('manager'), '607793283', 'manager', 2);
INSERT INTO userentity (version, firstname, lastname, email, password, phone, role, address_id) VALUES (0, 'Zdenek', 'Dusatko', 'admin', MD5('admin'), '607793283', 'storekeeper', 3);
INSERT INTO userentity (version, firstname, lastname, email, password, phone, role, address_id) VALUES (0, 'Clint', 'Eastwood', 'clint@gmail.com', MD5('admin'), '607793283', 'storekeeper', 4);



INSERT INTO category (version, name) VALUES (0, 'Books');
INSERT INTO category (version, name) VALUES (0, 'Music');
INSERT INTO category (version, name) VALUES (0, 'Movies');

INSERT INTO product (version, name, price, count , category_id, description) VALUES (0, 'product A', 10, 2, 1, 'Description of product');
INSERT INTO product (version, name, price, count , category_id, description) VALUES (0, 'product B', 10, 2, 2, 'Description of product');
INSERT INTO product (version, name, price, count , category_id, description) VALUES (0, 'product C', 10, 2, 3, 'Description of product');
