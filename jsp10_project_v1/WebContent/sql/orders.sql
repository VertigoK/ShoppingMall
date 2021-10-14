-- 1) 먼저  MariaDB 밑에 "shop" database 생성하기 (unless it exists already)
-- create database shop;


-- 2) "shop" database 밑에 orders 테이블 생성하기
--    members와 products 테이블 생성 후에 orders 테이블 생성할 것!

-- drop table orders;

create table orders(
		ord_num int primary key,
		ord_qty int not null,
		ord_cost int not null,
		ord_prd_num int not null,
		ord_mem_id varchar(20) not null,
		ord_date datetime default now(),
		constraint fk_ref_to_products foreign key(ord_prd_num) references products(prd_num) on update cascade,
		constraint fk_ref_to_members foreign key(ord_mem_id) references members(mem_id) on delete cascade
);

select * from orders;