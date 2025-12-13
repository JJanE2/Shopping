
-- Member data
INSERT INTO member (id, loginId, nickname, password, role)
VALUES (member_seq.NEXTVAL, 'q', 'test', 'q', 'OWNER')/

-- Product data
INSERT INTO product (id, memberId, name, price, description, stockQuantity)
VALUES (product_seq.NEXTVAL, 1, 'testProduct', 1000, 'testDesc', 50)/

-- Order data
INSERT INTO orders (id, memberId, totalPrice)
VALUES (orders_seq.NEXTVAL, 1, 3000)/

-- OrderProduct data
INSERT INTO orderProduct (id, orderId, productId, productName, price, quantity)
VALUES (orderProduct_seq.NEXTVAL, 1, 1, 'testProduct', 1000, 3)/

-- Cart data
INSERT INTO cart (id, memberId)
VALUES (cart_seq.NEXTVAL, 1)/

-- CartItem data
INSERT INTO cartItem (id, cartId, productId, productName, price, quantity)
VALUES (cartItem_seq.NEXTVAL, 1, 1, 'testProduct', 1000, 9)/

-- Review data
INSERT INTO review (id, memberId, productId, content, rating)
VALUES (review_seq.NEXTVAL, 1, 1, 'testReviewContent', 5.0)/