-- 1) 먼저  MariaDB 밑에 "shop" database 생성하기 (unless it exists already)
-- create database shop;


-- 2) "shop" database 밑에 products 테이블 생성하기

-- drop table products;

create table products(
		prd_num int primary key,
		prd_price int not null,
		prd_stock int not null,
		prd_name varchar(30) not null,
		prd_kind varchar(20) not null,
		prd_com varchar(30) not null,
		prd_date datetime default now()
);

select * from products;


-- 3) products 테이블에 랜덤 자료 생성하기 (하나씩 순서대로 실행)

--------------------------------------------------------------
-- 제품 번호: 101 ~ 999
-- 제품 종류: 전자제품, 화장품, 옷

-- 제품 가격: 100,000부터 900,000까지 random (천원 단위까지만 나오게)
-- select 1000 * floor(100 + (rand() * (900-100+1)));

-- 제품 가격: 10,000부터 90,000까지 random (천원 단위까지만 나오게)
-- select 100 * floor(100 + (rand() * (900-100+1)));

-- 제품 재고량: 10부터 99까지 random
-- select floor(10 + (rand() * (99-10+1)));
--------------------------------------------------------------

-- 전자제품 (삼성: 101~200)
delimiter //
begin not atomic
	declare i int default 101;
	while(i<=200) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 1000 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('전자제품_', i), '전자제품', '삼성', now());
		set i = i + 1;
	end while;
end;

-- 전자제품 (애플: 201~300)
delimiter //
begin not atomic
	declare i int default 201;
	while(i<=300) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 1000 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('전자제품_', i), '전자제품', '애플', now());
		set i = i + 1;
	end while;
end;

-- 전자제품 (LG: 301~400)	
delimiter //
begin not atomic
	declare i int default 301;
	while(i<=400) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 1000 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('전자제품_', i), '전자제품', 'LG', now());
		set i = i + 1;
	end while;
end;

-- 화장품 (아모레퍼시픽: 401~500)
delimiter //
begin not atomic
	declare i int default 401;
	while(i<=500) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 100 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('화장품_', i), '화장품', '아모레퍼시픽', now());
		set i = i + 1;
	end while;
end;

-- 화장품 (LG생활건강: 501~600)
delimiter //
begin not atomic
	declare i int default 501;
	while(i<=600) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 100 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('화장품_', i), '화장품', 'LG생활건강', now());
		set i = i + 1;
	end while;
end;

-- 옷 (한섬: 601~700)
delimiter //
begin not atomic
	declare i int default 601;
	while(i<=700) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 1000 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('옷_', i), '옷', '한섬', now());
		set i = i + 1;
	end while;
end;

-- 옷 (코오롱: 701~800)
delimiter //
begin not atomic
	declare i int default 701;
	while(i<=800) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 1000 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('옷_', i), '옷', '코오롱', now());
		set i = i + 1;
	end while;
end;

 옷 (신성통상: 801~903)
delimiter //
begin not atomic
	declare i int default 801;
	while(i<=903) do
		insert into products(prd_num, prd_price, prd_stock, prd_name, prd_kind, prd_com, prd_date)
					 values(i, 1000 * floor(100 + (rand() * (900-100+1))), floor(10 + (rand() * (99-10+1))),
					      	concat('옷_', i), '옷', '신성통상', now());
		set i = i + 1;
	end while;
end;