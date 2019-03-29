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
