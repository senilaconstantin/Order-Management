Drop
database if exists tema3;
CREATE SCHEMA  tema3;
USE
tema3;

drop table if exists Clienti;
CREATE TABLE IF NOT EXISTS Clienti (
    idClienti INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    numeClienti VARCHAR(50),
    adresa VARCHAR(50),
    email Varchar(50)
);
insert into Clienti
values (1, 'Senila Constantin', 'Campeni','senila.constantin@campeni.ro');


drop table if exists Orders;
create table if not exists Orders(
idOrders int auto_increment not null primary key,
numeClient varchar(50),
numeOrders varchar(50),
total int,
pret double
);
insert into Orders values(1, 'Senila Constantin','Sampon',3, 56.3);


drop table if exists Product;
create table if not exists Product(
idProduct int not null auto_increment primary key,
numeProduct varchar(50),
cantitate int,
pret double
);
insert into Product values(1,'Sampon',56,24.5);
