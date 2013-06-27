
-- customer_master table
declare
  i number;
begin
  for i in 1 .. 1000000 loop
    insert into CUSTOMER_MASTER(CUST_ID, CUST_NAME, CUST_ADDRESS,CUST_PIN_CODE,CUST_CITY,CUST_CONTACT_NO,CUST_EMAIL) values
    (i, 'Customer '|| i, ' ',' ',' ',0000000000,' ');
  end loop;
  commit;
end;
/


-- plant_master table
declare
  i number;
begin
  for i in 1 .. 200 loop
    insert into PLANT_MASTER(PLANT_CODE, PLANT_NAME, PLANT_DESC,SHIPPING_PLANT,PLANT_ADDR) values
    ('Plant '|| i, 'PlantName '|| i,' ',' ',' ');
  end loop;
  commit;
end;
/

-- material_master table
declare
  i number;
  j number;
  k number;
  plant_code_var varchar2(9) :='';
  date1 varchar2(20) :='';
  date2 varchar2(20) :='';
begin
  for i in 1 .. 500 loop
    j:=i;
	k:=i;
    if j > 199 then
    j:=1;
    else
    j:=i+1;
    end if;
    	select PLANT_CODE into   plant_code_var
      from PLANT_MASTER where PLANT_CODE = 'Plant '|| j;
	if k > 20 then
	k :=1;
	else
	k:=i;
	end if;
	  date1:= k || '/01/2013';
	  date2:= k+10 || '/03/2013';
	  insert into MATERIAL_MASTER(MAT_CODE, MAT_NAME, MAT_DESC,PLANT_CODE,MAT_PRICE,MAT_EXPIRY,MAT_CREATED) values
    ('Mat '|| i, 'MaterialName '|| i, ' ',plant_code_var,0,to_date(date1,'dd/mm/yyyy'),to_date(date2,'dd/mm/yyyy'));
  end loop;
  EXCEPTION
   WHEN NO_DATA_FOUND THEN
      raise_application_error (-20001,'No data found!');
  commit;
end;
/

--CREATE TABLE ORDER_INVOICE
declare
  i number;
  j number;
  k number;
  x number;
  y number;
  plant_code_var varchar2(9) :='';
  mat_code_var varchar2(9):='';
  status varchar2(9):='';
  cust_id_var number;
  date1 varchar2(20) :='';
  date2 varchar2(20) :='';
begin
  for i in 1 .. 500 loop
	j:=i;
	
	if mod(i,2) = 0 then
	status:='a';
	else
	status:='n';
	end if;

	if j > 199 then
    j:=1;
    else
    j:=i+1;
    end if;
    	select PLANT_CODE into   plant_code_var
      from PLANT_MASTER where PLANT_CODE = 'Plant '|| j;
	  
	k:=i;
    
	if k > 499 then
    k:=1;
    else
    k:=i+1;
    end if;
    	select MAT_CODE into   mat_code_var
      from MATERIAL_MASTER where MAT_CODE = 'Mat '|| k;
	  
	select CUST_ID into cust_id_var
	from CUSTOMER_MASTER where CUST_ID = i;
	x:=i;
	if x > 20 then
	x :=1;
	else
	x:=i;
	end if;
	  date1:= x || '/01/2013';
	  date2:= x+10 || '/03/2013';
	
	  insert into ORDER_INVOICE(ORDER_NUMBER, ORDER_CREATED, ORDER_STATUS,CUST_ID,ORDER_VALUE,SHIPPING_DATE,MAT_CODE,PLANT_CODE,CHEQUE_NUMBER,BANK_NAME) values
    ('Order ' || i,to_date(date1,'dd/mm/yyyy'),status,cust_id_var,0,to_date(date2,'dd/mm/yyyy'),mat_code_var,plant_code_var,' ',' ');
  end loop;
  EXCEPTION
   WHEN NO_DATA_FOUND THEN
      raise_application_error (-20001,'No data found!');
  commit;
end;
/

--order_detail

declare
  i number;
  j number;
  k number;
  order_code_var varchar2(9) :='';
  mat_code_var varchar2(9):='';
 
begin
  for i in 1 .. 500 loop
	j:=i;

	if j > 199 then
    j:=1;
    else
    j:=i+1;
    end if;
    	select ORDER_NUMBER into   order_code_var
      from ORDER_INVOICE where ORDER_NUMBER = 'Order '|| j;
	  
	k:=i;
    
	if k > 499 then
    k:=1;
    else
    k:=i+1;
    end if;
    	select MAT_CODE into   mat_code_var
      from MATERIAL_MASTER where MAT_CODE = 'Mat '|| k;
	
	  insert into ORDER_DETAIL(ORDER_NUMBER, MAT_CODE, ITEM_QTY,ITEM_VALUE) values
    (order_code_var,mat_code_var, i,j);
  end loop;
  EXCEPTION
   WHEN NO_DATA_FOUND THEN
      raise_application_error (-20001,'No data found!');
  commit;
end;
/


--stock_master
declare
  i number;
  j number;
  k number;
  plant_code_var varchar2(9) :='';
  mat_code_var varchar2(9):='';
  status varchar2(9):='';
begin
  for i in 1 .. 500 loop
	j:=i;
	
	if mod(i,2) = 0 then
	status:='a';
	else
	status:='na';
	end if;

	if j > 199 then
    j:=1;
    else
    j:=i+1;
    end if;
    	select PLANT_CODE into   plant_code_var
      from PLANT_MASTER where PLANT_CODE = 'Plant '|| j;
	  
	k:=i;
    
	if k > 499 then
    k:=1;
    else
    k:=i+1;
    end if;
	select MAT_CODE into   mat_code_var
    rom MATERIAL_MASTER where MAT_CODE = 'Mat '|| k;
	
	
	insert into STOCK_MASTER(MAT_CODE, PLANT_CODE, STOCK_QTY,STOCK_AVALIABLITY_STATUS) values
    (mat_code_var, plant_code_var, i,status);
  end loop;
  EXCEPTION
   WHEN NO_DATA_FOUND THEN
      raise_application_error (-20001,'No data found!');
  commit;
end;
/



--insert in the quarterly table for data dictionary cache hit raise_application_error
CREATE OR REPLACE Procedure record_quarterly  as  
BEGIN
 insert into quarterly(rate,recorded_time) select round((1-(sum(getmisses)/(sum(gets) + sum(getmisses))))*100,2),sysdate from v$rowcache;
   commit;
END;
/
--schedular job for the bove procedure
begin
  dbms_scheduler.create_job(
      job_name => 'record_every_15'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             record_quarterly;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= MINUTELY;INTERVAL=15'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every 15 min');
end;
/ 


--insert in the hourly table for data dictionary cache hit raise_application_error
CREATE OR REPLACE Procedure record_hourly  as  
BEGIN
 insert into hourly(rate,recorded_time) select round((1-(sum(getmisses)/(sum(gets) + sum(getmisses))))*100,2),sysdate from v$rowcache;
   commit;
END;
/

--schedular job for the bove procedure
begin
  dbms_scheduler.create_job(
      job_name => 'record_every_hour'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             record_hourly;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= hourly;INTERVAL=1'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every hourly');
end;
/ 


--insert in the quarterly table for data dictionary cache hit raise_application_error
create or replace 
Procedure record_quarterly_buffer  as  
BEGIN
 insert into SYS.buffer_quarterly(rate,recorded_time) select round(100 * ((a.value+b.value)-c.value) /(a.value+b.value)),sysdate from v$sysstat a, v$sysstat b,v$sysstat c WHERE a.name = 'db block gets' AND b.name = 'consistent gets' AND c.name = 'physical reads';
   commit;
END;
--schedular job for the bove procedure
begin
  dbms_scheduler.create_job(
      job_name => 'RECORD_EVERY_15_BUFFER'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             record_quarterly_buffer;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= MINUTELY;INTERVAL=15'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every 15 min');
end;
/ 


--insert in the hourly table for data dictionary cache hit raise_application_error
create or replace 
Procedure record_hourly_buffer  as  
BEGIN
 insert into SYS.buffer_hourly(rate,recorded_time) select round(100 * ((a.value+b.value)-c.value) /(a.value+b.value)),sysdate from v$sysstat a, v$sysstat b,v$sysstat c WHERE a.name = 'db block gets' AND b.name = 'consistent gets' AND c.name = 'physical reads';
   commit;
END;
/

--schedular job for the bove procedure
begin
  dbms_scheduler.create_job(
      job_name => 'RECORD_EVERY_HOUR_BUFFER'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             record_hourly_buffer;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= hourly;INTERVAL=1'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every hourly');
end;
/



--updating customer_master
create or replace 
Procedure update_customer  as  
BEGIN
 update sys.customer_master set cust_contact_no=cust_contact_no+1;
   commit;
END;
/

begin
  dbms_scheduler.create_job(
      job_name => 'UPDATE_CUSTOMER_MASTER'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             update_customer;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= MINUTELY;INTERVAL=15'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every 15 min');
end;
/



--updating plant_master
create or replace 
Procedure update_plant  as  
BEGIN
 update plant_master set shipping_plant='shipment';
   commit;
END;
/

begin
  dbms_scheduler.create_job(
      job_name => 'update_plant_master'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             update_plant;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= MINUTELY;INTERVAL=2'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every 2 minutes');
end;
/


--updating ORDER_INVOICE
create or replace 
Procedure update_order_invoice  as  
BEGIN
 update ORDER_INVOICE set ORDER_VALUE=ORDER_VALUE+1;
   commit;
END;
/

begin
  dbms_scheduler.create_job(
      job_name => 'UPDATE_ORDER_INVOICE_PROC'
     ,job_type => 'PLSQL_BLOCK'
     ,job_action => 'begin
             update_order_invoice;
             end;'
     ,start_date => sysdate
     ,repeat_interval => 'FREQ= MINUTELY;INTERVAL=2'
     ,enabled => TRUE
     ,comments => 'This SP is run to perform x every 2 minutes');
end;
/


