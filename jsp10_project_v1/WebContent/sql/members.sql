-- 1) 먼저 MariaDB 밑에 "shop" database 생성하기 (unless it exists already)
-- create database shop;


-- 2) "shop" database 밑에 members 테이블 생성하기

-- drop table members;
 
create table members(
		mem_id varchar(15) primary key,
		mem_pw varchar(20) not null,
		mem_name varchar(20) not null,
		mem_addr varchar(50) not null,
		mem_tel varchar(20) not null,
		mem_email varchar(30) not null,
		mem_pts int default 0 not null,
		mem_date datetime default now()
);

select * from members;