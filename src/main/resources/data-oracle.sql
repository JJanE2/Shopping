
-- Member data
INSERT INTO member (id, loginId, nickname, password, role)
VALUES (member_seq.NEXTVAL, 'q', 'test', 'q', 'OWNER')/

-- Product data
INSERT INTO product (id, memberId, name, price, description)
VALUES (product_seq.NEXTVAL, 1, 'testProduct', 1000, 'testDesc')/

-- Order data
INSERT INTO orders (id, memberId, totalPrice)
VALUES (orders_seq.NEXTVAL, 1, 3000)/

-- OrderProduct data
INSERT INTO orderProduct (id, orderId, productId, productName, price, quantity)
VALUES (orderProduct_seq.NEXTVAL, 1, 1, 'testProduct', 1000, 3)/