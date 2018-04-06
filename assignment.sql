create database if not exists assignment1;
use assignment1;
create table if not exists user
(	
id int not null auto_increment primary key,
name varchar(45),
address varchar(45),
username varchar(45),
parola varchar(45),
admin bool);

create table if not exists clienti
(	
id int not null auto_increment primary key,
nume varchar(45),
card int,
cnp varchar(45),
address varchar(45));

create table if not exists reservation
(	
id int not null auto_increment primary key,
destination varchar(45),
hotel varchar(45),
number int,
price int,
id_c int,
final_date date);

insert into  user
(id,name,address,username,parola,admin) values
(1,'admin','Cluj','admin','admin',true),
(2,'Ana','Cluj','ana','ana',false),
(3,'User','Cluj','user','user',false);

insert into  clienti
(id,nume,card,cnp,address) values
(1,'Popescu Diana',255,'296031245050','Strada Independenti 16/25, Timisoara'),
(2,'Pop Andreea',556,'2960306243625','Strada Arges 2/14, Brasov');

insert into  reservation
(id,destination,hotel,number,price,id_c,final_date) values
(1,'Austria','Melia',5,3500,1,'2018-05-21'),
(2,'Rusia','Volna',2,1800,2,'2018-02-15');

select id_c from reservation where curdate()>final_date;
