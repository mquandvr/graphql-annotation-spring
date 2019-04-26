INSERT INTO STORE (STORE_ID, STORE_NAME, CREATED_DATE, CREATED_BY) VALUES
  (1, 'Cua hang tien loi', TO_DATE('20190303', 'yyyyMMdd'), 'user 1'),
  (2, 'Vincom center', TO_DATE('20190303', 'yyyyMMdd'), 'user 2');

INSERT INTO FOOD (FOOD_ID, FOOD_NAME, FOOD_TYPE, STORE_ID, CERTIFICATION, CREATED_DATE, CREATED_BY) VALUES
  (1,'Cơm Gà', 'fast food', 1, 'Đã kiểm dịch, đạt chuẩn hoa hậu', TO_DATE('20190303', 'yyyyMMdd'), 'user 1'),
  (2,'Banh cuon 50k', 'traditional food', 1, 'VSATTP', TO_DATE('20190303', 'yyyyMMdd'), 'user 1'),
  (3,'Banh bao', 'fast food', 1, 'VSATTP', TO_DATE('20190303', 'yyyyMMdd'), 'user 1'),
  (4,'Hu tieu', 'vietnam food', 1, 'VSATTP', TO_DATE('20190303', 'yyyyMMdd'), 'user 1'),
  (5,'Pho Hotpot', 'vietnam food', 2, 'VSATTP', TO_DATE('20190303', 'yyyyMMdd'), 'user 2'),
  (6,'Com tam cali', 'vietnam food', 2, 'VSATTP', TO_DATE('20190303', 'yyyyMMdd'), 'user 2');

INSERT INTO ROLE (ROLE_ID, ROLE_NAME, CREATED_DATE, CREATED_BY) VALUES
  (1, 'ROLE_ADMIN', TO_DATE('20190303', 'yyyyMMdd'), 'admin'),
  (2, 'ROLE_USER', TO_DATE('20190303', 'yyyyMMdd'), 'admin');
  
INSERT INTO USER (ID, USERNAME, USER_PASS, FIRSTNAME, LASTNAME, EMAIL, TOKEN, ENABLED, ROLE_ROLE_ID, CREATED_DATE, CREATED_BY) VALUES
 (1, 'admin', '$2a$10$iNB4YppjRJ6u5BMYNQ7vtO0FAxxCjpYpDzntdajvOy/RWGbKmlNAa', 'Ad', 'min', 'admin@gmail.com', '123', 1, 1, TO_DATE('20190303', 'yyyyMMdd'), 'admin'),
 (2, 'user', '$2a$10$y4o0k6V1i/6oY6k1nmDHUOJHGufMjYd6OnYdS16wAXQneOZvPnGJ2', 'Ad', 'min', 'user@gmail.com', '123', 1, 2, TO_DATE('20190303', 'yyyyMMdd'), 'admin')