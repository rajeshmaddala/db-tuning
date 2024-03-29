CREATE TABLE CUSTOMER_MASTER
(
CUST_ID number NOT NULL,
CUST_NAME varchar2(30),
CUST_ADDRESS varchar(255),
CUST_PIN_CODE varchar2(6),
CUST_CITY  varchar(20),
CUST_CONTACT_NO NUMERIC(10) ,
CUST_EMAIL varchar2(20) ,
CONSTRAINT CUSTOMER_PK  PRIMARY KEY (CUST_ID)
) ;


CREATE TABLE PLANT_MASTER
(
PLANT_CODE varchar2(9) NOT NULL,
PLANT_NAME varchar2(30) NOT NULL,
PLANT_DESC varchar(255),
SHIPPING_PLANT varchar2(6),
PLANT_ADDR varchar(255),
CONSTRAINT PLANT_PK  PRIMARY KEY (PLANT_CODE)
) ;


CREATE TABLE MATERIAL_MASTER
(
MAT_CODE varchar2(9) NOT NULL,
MAT_NAME varchar2(30) NOT NULL,
MAT_DESC varchar(255),
PLANT_CODE varchar2(9),
MAT_PRICE  NUMERIC(10,2) ,
MAT_EXPIRY DATE,
MAT_CREATED DATE,
CONSTRAINT MATERIAL_PK  PRIMARY KEY (MAT_CODE),
CONSTRAINT MATERIAL_PLANT_FK FOREIGN KEY (PLANT_CODE) REFERENCES PLANT_MASTER(PLANT_CODE)
) ;


CREATE TABLE ORDER_INVOICE
(
ORDER_NUMBER varchar2(9) NOT NULL,
ORDER_CREATED DATE,
ORDER_STATUS varchar2(1),
CUST_ID number,
ORDER_VALUE  NUMERIC(10,2) ,
SHIPPING_DATE DATE,
MAT_CODE varchar2(9),
PLANT_CODE varchar2(9),
CHEQUE_NUMBER VARCHAR2(14),
BANK_NAME VARCHAR2(20),
CONSTRAINT ORDER_PK  PRIMARY KEY (ORDER_NUMBER),
CONSTRAINT ORDER_PLANT_FK FOREIGN KEY (PLANT_CODE) REFERENCES PLANT_MASTER(PLANT_CODE),
CONSTRAINT ORDER_MATERIAL_FK FOREIGN KEY (MAT_CODE) REFERENCES MATERIAL_MASTER(MAT_CODE),
CONSTRAINT ORDER_CUSTOMER_FK FOREIGN KEY (CUST_ID) REFERENCES CUSTOMER_MASTER(CUST_ID)
) ;


CREATE TABLE ORDER_DETAIL
(
ORDER_NUMBER varchar2(9) NOT NULL,
MAT_CODE varchar2(9) NOT NULL,
ITEM_QTY INT,
ITEM_VALUE NUMERIC(10,2) ,
CONSTRAINT ORDER_DETAIL_PK  PRIMARY KEY (ORDER_NUMBER,MAT_CODE),
CONSTRAINT ORDER_DETAIL_INVOICE_FK FOREIGN KEY (ORDER_NUMBER) REFERENCES ORDER_INVOICE(ORDER_NUMBER),
CONSTRAINT ORDER_DETAIL_MATERIAL_FK FOREIGN KEY (MAT_CODE) REFERENCES MATERIAL_MASTER(MAT_CODE)
);

CREATE TABLE STOCK_MASTER
(
MAT_CODE varchar2(9) NOT NULL,
PLANT_CODE varchar2(9) NOT NULL,
STOCK_QTY INT,
STOCK_AVALIABLITY_STATUS VARCHAR2(2) ,
CONSTRAINT STOCK_PK  PRIMARY KEY (PLANT_CODE,MAT_CODE),
CONSTRAINT STOCK_PLANT_FK FOREIGN KEY (PLANT_CODE) REFERENCES PLANT_MASTER(PLANT_CODE),
CONSTRAINT STOCK_MATERIAL_FK FOREIGN KEY (MAT_CODE) REFERENCES MATERIAL_MASTER(MAT_CODE)
);



create table threshold_config (
param_name varchar2(40),
min_healthy number(10,2),
max_healthy number(10,2));


create table quarterly(
rate number(10,2),
recorded_time timestamp);

create table hourly(
rate number(10,2),
recorded_time timestamp);


create table buffer_quarterly(
rate number(10,2),
recorded_time timestamp);

create table buffer_hourly(
rate number(10,2),
recorded_time timestamp);

create table report_data ( 
Dictionary_Hit_Rate number(10,2),
Library_Cache_Hit_Rate number(10,2),
Library_Cache_Get_Hit_Rate number(10,2),
Library_Cache_Pin_Hit_Rate number(10,2),
Shared_Pool_Reload_Ratio number(10,2),
Rollback_Segment_Hit_Ratio number(10,2),
Redo_Space_Wait_Ratio number(10,2),
Redo_Log_Buffer_Entry_Ratio number(10,2),
Shared_Pool_Free_Ratio number(10,2),
Short_to_Full_Scan_Ratio number(10,2),
Chained_Fetch_Ratio number(10,2),
Free_List_Contention number(10,2),
CPU_Parse_Overlod number(10,2),
reported_time timestamp
);