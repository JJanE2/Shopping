
-- Member data
INSERT INTO member (id, loginId, nickname, password, role)
VALUES (member_seq.NEXTVAL, 'q', 'test', '$2a$10$RP.04RMF9J29f3bhyKJxfeI1o49jN9RexdpntZInDAsayVxsOna4O', 'OWNER')/
INSERT INTO member (id, loginId, nickname, password, role)
VALUES (member_seq.NEXTVAL, 'a', '테스트유저', '$2a$10$tyIAhMED3L6nG/fcJvZhpu1goSpFwX61lTB.WcRCzFXaI3jAh5Fwq', 'CUSTOMER')/

-- Product data
INSERT INTO product (id, memberId, name, price, description, stockQuantity)
VALUES (product_seq.NEXTVAL, 1, '테스트 상품', 1000, 'testDesc', 50)/
INSERT INTO product (id, memberId, name, price, description, stockQuantity)
VALUES (product_seq.NEXTVAL, 1, '테스트 상품2', 23000, '상품상세테스트', 50)/

-- Order data
INSERT INTO orders (id, memberId, totalPrice)
VALUES (orders_seq.NEXTVAL, 1, 3000)/
INSERT INTO orders (id, memberId, totalPrice, status)
VALUES (orders_seq.NEXTVAL, 1, 46000, 'COMPLETED')/

-- OrderProduct data
INSERT INTO orderProduct (id, orderId, productId, productName, price, quantity)
VALUES (orderProduct_seq.NEXTVAL, 1, 1, '테스트 상품', 1000, 3)/
INSERT INTO orderProduct (id, orderId, productId, productName, price, quantity)
VALUES (orderProduct_seq.NEXTVAL, 2, 2, '테스트 상품2', 23000, 2)/

-- Cart data
INSERT INTO cart (id, memberId)
VALUES (cart_seq.NEXTVAL, 1)/
INSERT INTO cart (id, memberId)
VALUES (cart_seq.NEXTVAL, 2)/

-- CartItem data
INSERT INTO cartItem (id, cartId, productId, productName, price, quantity)
VALUES (cartItem_seq.NEXTVAL, 1, 1, '테스트 상품', 1000, 9)/

-- Review data
INSERT INTO review (id, memberId, productId, content, rating, memberNickname)
VALUES (review_seq.NEXTVAL, 1, 1, 'testReviewContent', 5.0, 'test')/
INSERT INTO review (id, memberId, productId, content, rating, memberNickname)
VALUES (review_seq.NEXTVAL, 2, 1, '리뷰내용 테스트', 3.0, '테스트유저')/