
-- Member data
INSERT INTO member (id, loginId, nickname, password, role)
VALUES (member_seq.NEXTVAL, 'q', 'test', 'q', 'OWNER')/

-- Product data
INSERT INTO product (id, memberId, name, price, description)
VALUES (product_seq.NEXTVAL, 1, 'testProduct', 1000, 'testDesc')/