-- -- DEFAULT TYPES
-- INSERT INTO TYPES VALUES(1, 'Clothing');
-- INSERT INTO TYPES VALUES(2, 'Drink');
-- INSERT INTO TYPES VALUES(3, 'Cleaning');
-- INSERT INTO TYPES VALUES(4, 'Fruit');

-- -- DEFAULT PRODUCTS
-- INSERT INTO PRODUCTS ( ID , PRODUCT_NAME , PRODUCT_DESCRIPTION , PRODUCT_PRICE , PRODUCT_STOCK , TYPE_ID ) VALUES(1, 'T-Shirt', 'Red T-shirt', 19.99, 120, 1);
-- INSERT INTO PRODUCTS ( ID , PRODUCT_NAME , PRODUCT_DESCRIPTION , PRODUCT_PRICE , PRODUCT_STOCK , TYPE_ID ) VALUES(2, 'Rink', 'Red Drink', 15.99, 33, 2);
-- INSERT INTO PRODUCTS ( ID , PRODUCT_NAME , PRODUCT_DESCRIPTION , PRODUCT_PRICE , PRODUCT_STOCK , TYPE_ID ) VALUES(3, 'Ajax', 'Red ajax', 9.99, 23, 3);
-- INSERT INTO PRODUCTS ( ID , PRODUCT_NAME , PRODUCT_DESCRIPTION , PRODUCT_PRICE , PRODUCT_STOCK , TYPE_ID ) VALUES(4, 'Apple', 'Red apple', 5.99, 55, 4);

-- -- DEFAULT PERMISSIONS
-- INSERT INTO PERMISSIONS VALUES(1, 'CREATE');
-- INSERT INTO PERMISSIONS VALUES(2, 'READ');
-- INSERT INTO PERMISSIONS VALUES(3, 'UPDATE');
-- INSERT INTO PERMISSIONS VALUES(4,'DELETE');

-- -- DEFAULT ROLES
-- INSERT INTO ROLES VALUES(1, 'ADMIN');
-- INSERT INTO ROLES VALUES(2, 'USER');

-- -- DEFAULT USER
-- INSERT INTO USERS ( ID , USERNAME , PASSWORD ,  IS_ENABLED , ACCOUNT_NO_EXPIRED , ACCOUNT_NO_LOCKED , CREDENTIAL_NO_EXPIRED) VALUES (1, 'tesifon', '$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6', true, true, true, true);
-- INSERT INTO USERS ( ID , USERNAME , PASSWORD ,  IS_ENABLED , ACCOUNT_NO_EXPIRED , ACCOUNT_NO_LOCKED , CREDENTIAL_NO_EXPIRED) VALUES (2, 'jmaldonado', '$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6', true, true, true, true);

-- -- DEFAULT USER_ROLES
-- INSERT INTO USER_ROLES VALUES(1, 1);
-- INSERT INTO USER_ROLES VALUES(2, 2);


-- -- ROLE_PERMISSION
-- INSERT INTO ROLE_PERMISSIONS (PERMISSION_ID, ROLE_ID, USER_ROLE_ID) VALUES(1, 1, 1);
-- INSERT INTO ROLE_PERMISSIONS (PERMISSION_ID, ROLE_ID, USER_ROLE_ID) VALUES(2, 1, 1);
-- INSERT INTO ROLE_PERMISSIONS (PERMISSION_ID, ROLE_ID, USER_ROLE_ID) VALUES(3, 1, 1);
-- INSERT INTO ROLE_PERMISSIONS (PERMISSION_ID, ROLE_ID, USER_ROLE_ID) VALUES(4, 1, 1);
-- INSERT INTO ROLE_PERMISSIONS (PERMISSION_ID, ROLE_ID, USER_ROLE_ID) VALUES(2, 2, 2);

