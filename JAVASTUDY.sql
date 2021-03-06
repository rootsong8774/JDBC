alter session set "_ORACLE_SCRIPT"=true;
CREATE USER javastudy IDENTIFIED BY javastudy;
GRANT CONNECT, RESOURCE, CREATE VIEW,  unlimited tablespace TO javastudy ;

drop table product;
CREATE TABLE PRODUCT (PRODUCT_NO CHAR(4) NOT NULL ,
PRODUCT_NAME VARCHAR2(200) NOT NULL ,
PRICE NUMBER NOT NULL ,
CONSTRAINT PK_PRODUCT PRIMARY KEY(PRODUCT_NO));

-- PRODUCT_PURCHACE 테이블 생성 
drop table PRODUCT_PURCHASE;
CREATE TABLE PRODUCT_PURCHASE (ACCOUNT_NO CHAR(9) NOT NULL ,
SALE_DATE DATE NOT NULL, 
TOTAL NUMBER NOT NULL, 
REMARK VARCHAR2(1000) NULL ,
CONSTRAINT PK_PRODUCT_PURCHASE PRIMARY KEY(ACCOUNT_NO)
);
-- PRODUCT_PURCHACE 테이볼 수정 
ALTER TABLE PRODUCT_PURCHASE MODIFY (SALE_DATE DATE DEFAULT SYSDATE,
TOTAL NUMBER DEFAULT 0 );

-- PRODUCT_PURCHAGE_DETAIL 테이블 생성 
drop table product_purchase_detail;
CREATE TABLE PRODUCT_PURCHASE_DETAIL(
ACCOUNT_NO CHAR(9) NOT NULL,
SEQ NUMBER DEFAULT 1, 
PRODUCT_NO CHAR(4) NOT NULL ,
PRICE NUMBER NOT NULL ,
QTY NUMBER NOT NULL ,
CONSTRAINT PK_PRODUCT_PURCHASE_DETAIL PRIMARY KEY(ACCOUNT_NO, SEQ) ,
CONSTRAINT FK_PRODUCT_PURCHASE_DETAIL_ACCOUNT_NO 
FOREIGN KEY(ACCOUNT_NO) REFERENCES PRODUCT_PURCHASE(ACCOUNT_NO), 
CONSTRAINT FK_PRODUCT_PURCHASE_DETAIL_PRODUCT_NO 
FOREIGN KEY(PRODUCT_NO)  REFERENCES PRODUCT(PRODUCT_NO));

ALTER TABLE PRODUCT_PURCHASE_DETAIL MODIFY(SEQ NUMBER NOT NULL ,
PRICE NUMBER DEFAULT 0 );

-- PRODUCT 테이블에 자료 등록 
delete from product;
INSERT INTO PRODUCT(PRODUCT_NO, PRODUCT_NAME, PRICE)
VALUES('A001', '아메리카노', 4000);
INSERT INTO PRODUCT(PRODUCT_NO, PRODUCT_NAME, PRICE) 
VALUES('A002', '까페라떼', 4300); 
INSERT INTO PRODUCT(PRODUCT_NO, PRODUCT_NAME, PRICE) 
VALUES('A003', '까페모카', 4500); 
commit;
-- PRODUCT_PURCHASE 테이블에 자료 등록 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAl, REMARK) 
VALUES('201900001', '2019-01-01', 8300, NULL); 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAl, REMARK) 
VALUES('201900002', '2019-01-01', 9000, NULL); 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAl, REMARK) 
VALUES('201900003', '2019-01-01', 26000, NULL); 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAL, REMARK) 
VALUES('201900004', '2019-01-02', 26000, NULL); 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAL, REMARK) 
VALUES('201900005', '2019-01-02', 8600, NULL); 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAL, REMARK) 
VALUES('201900006', '2019-01-03', 4500, NULL); 
INSERT INTO PRODUCT_PURCHASE (ACCOUNT_NO, SALE_DATE, TOTAl,REMARK) 
VALUES('201900007', '2019-01-03', 17400, NULL); 
commit;
delete from product_purchase;
delete from PRODUCT_PURCHASE_DETAIL;
iNSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900001', 1, 'A001', 4000, 1); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900001', 2, 'A002', 4300,1); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900002', 1, 'A003', 4500, 2); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900003', 1, 'A002', 4300, 1); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900003', 2, 'A002', 4500, 3); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900003', 3, 'A001', 4000, 2); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900004', 1, 'A002', 4300, 1); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY)
VALUES('201900004', 2, 'A002', 4000, 2); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900004', 3, 'A003', 4500, 3); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900005', 1, 'A002', 4300, 2); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUEs('201900006', 1, 'A002', 4500, 1); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900007', 1, 'A002', 4300, 2); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY) 
VALUES('201900007', 2, 'A001', 4300, 1); 
INSERT INTO PRODUCT_PURCHASE_DETAIL (ACCOUNT_NO, SEQ, PRODUCT_NO, PRICE, QTY)
VALUES('201900007', 3, 'A001', 4000, 1); 
commit;
rollback;




