create database billing_system;
use billing_system;
create table user_details (user_id varchar(7), pwd varchar(20), cust_name varchar(50), meter_no int, cust_id varchar(8), primary key (cust_id));
insert into user_details values ('user001', 'abc001', 'Aditi Shriya', 123456, 'LMNO1400'),
 ('user002', 'def002', 'Puja Sowmondal', 123457, 'LMNO1410'),
 ('user003', 'ghi003', 'Ausmita', 123458, 'LMNO1420'),
 ('user004', 'jkl004', 'Priya Sharma', 123459, 'LMNO1430'),
 ('user005', 'mno005', 'Vikrant Kumar', 123460, 'LMNO1440');
create table Consumer_Information(Area varchar(20),Circle varchar(20), Division varchar(20), SubDivision varchar(20),cust_id varchar(20),
	Consumer_No varchar(20),Consumer_Name varchar(20),Mobile_No varchar(20),Email_ID varchar(20),Address varchar(20), primary key (cust_id));
insert into Consumer_Information values ('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1400','UN01','Aditi Shriya','9570121221','aaditi@gmail.com','109/2/2'),
 ('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1410','UN02','Puja Sowmondal','0123456789','pujas@gmail.com','110/2/2'),
 ('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1420','UN03','Ausmita','9876543210','aushmita@gmail.com','111/2/2'),
 ('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1430','UN04','Priya Sharma','9657822221','priya@gmail.com','112/2/2'),
 ('Jharkhand','E.Singhbhum','Jamshedpur','Golmuri','LMNO1440','UN05','Vikrant kumar','9570121221','vk@gmail.com','113/2/2');