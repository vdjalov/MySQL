-- 01. Create Tables

CREATE TABLE minions (
	id INT PRIMARY KEY NOT NULL,
    name VARCHAR(45) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE towns (
	id INT PRIMARY KEY NOT NULL,
    name VARCHAR(45) NOT NULL
);



-- 02. Alter Minions Table

ALTER TABLE minions 
ADD COLUMN town_id INT NOT NULL AFTER age;

ALTER TABLE minions
ADD FOREIGN KEY(town_id) references towns(id);



-- 03. Insert Records in Both Tables

INSERT INTO towns(id, name) VALUES(1,'Sofia');
INSERT INTO towns(id, name) VALUES(2,'Plovdiv');
INSERT INTO towns(id, name) VALUES(3,'Varna');
INSERT INTO minions(id, name, age, town_id) VALUES(1,'Kevin', 22, 1);
INSERT INTO minions(id, name, age, town_id) VALUES(2,'Bob', 15, 3);
INSERT INTO minions(id, name, age, town_id) VALUES(3,'Steward', null, 2);



-- 04. Truncate Table Minions

TRUNCATE TABLE `minions`;



-- 05. Drop All Tables

DROP TABLE minions;
DROP TABLE towns;



-- 06. Create Table People

CREATE TABLE people(
`id` int primary key auto_increment,
`name` varchar(200) not null,
`picture` blob,
`height` double(10, 2),
`weight` double(10, 2),
`gender` char(1) not null, 
`birthdate` date not null,
`biography` text
);

insert into people(id, name, picture, height, weight, gender, birthdate, biography)
values (1, 'Pesho', 'file.dat', 2.356, 1.8833, 'm', '2012-12-3', 'Nothing importnat');

insert into people(id, name, picture, height, weight, gender, birthdate, biography)
values (2, 'Pesho', 'file.dat', 2.356, 1.8833, 'm', '2012-12-3', 'Nothing importnat');

insert into people(id, name, picture, height, weight, gender, birthdate, biography)
values (3, 'Pesho', 'file.dat', 2.356, 1.8833, 'm', '2012-12-3', 'Nothing importnat');

insert into people(id, name, picture, height, weight, gender, birthdate, biography)
values (4, 'Pesho', 'file.dat', 2.356, 1.8833, 'm', '2012-12-3', 'Nothing importnat');

insert into people(id, name, picture, height, weight, gender, birthdate, biography)
values (5, 'Pesho', 'file.dat', 2.356, 1.8833, 'm', '2012-12-3', 'Nothing importnat');



-- 07. Create Table Users 

create table `users`(
`id` int primary key auto_increment,
`username` varchar(30) not null,
`password` varchar(26),
`profile_picture` blob(900),
`last_login_time` time,
`is_deleted` bit not null
);

insert into users(id, username, password, profile_picture, last_login_time, is_deleted) values 
(1, 'Vlad', '12345', 'somefile.img', '22:18:22', 1);

insert into users(id, username, password, profile_picture, last_login_time, is_deleted) values 
(2, 'Vlad', '12345', 'somefile.img', '22:18:22', 1);

insert into users(id, username, password, profile_picture, last_login_time, is_deleted) values 
(3, 'Vlad', '12345', 'somefile.img', '22:18:22', 1);

insert into users(id, username, password, profile_picture, last_login_time, is_deleted) values 
(4, 'Vlad', '12345', 'somefile.img', '22:18:22', 1);

insert into users(id, username, password, profile_picture, last_login_time, is_deleted) values 
(5, 'Vlad', '12345', 'somefile.img', '22:18:22', 1);



-- 08. Change Primary Key 

alter table `users` change column `id` `id` int not null;
alter table `users` drop primary key;
alter table `users` add constraint pk_users primary key(`id`, `username`);



-- 9. Set Default Value of a Field 

alter table `users` change column `last_login_time` `last_login_time` timestamp; 




-- 10. Set Unique Field

alter table `users` change column `id` `id` int not null;
alter table `users` drop primary key;
alter table `users` add constraint `pk_users` primary key(`id`);
alter table `users` add unique(`username`);



-- 11. Movies Database

- create database `Movies`;
create table `directors`(
`id` int primary key auto_increment,
`director_name` varchar(55) not null,
`notes` text 	
);

create table `genres`(
`id` int primary key auto_increment,
`genre_name` varchar(55) not null,
`notes` text 	
);

create table `categories`(
`id` int primary key auto_increment,
`category_name` varchar(55) not null,
`notes` text 	
);


create table `movies`(
`id` int primary key auto_increment,
`title` varchar(45) not null,
`director_id` int not null,
`copyright_year` year,
`length` time,
`genre_id` int not null,
`category_id` int not null,
`rating` double(38,2),
`notes` text
);

insert into `directors`(id, director_name, notes) 
values(1, 'George Lucas', 'Best recognized for star wars ');
insert into `directors`(id, director_name, notes) 
values(2, 'George Lucas', 'Best recognized for star wars ');
insert into `directors`(id, director_name, notes) 
values(3, 'George Lucas', 'Best recognized for star wars ');
insert into `directors`(id, director_name, notes) 
values(4, 'George Lucas', 'Best recognized for star wars ');
insert into `directors`(id, director_name, notes) 
values(5, 'George Lucas', 'Best recognized for star wars ');	


insert into `genres`(id, genre_name, notes) 
values(1, 'Sci-Fi', 'Crazy');
insert into `genres`(id, genre_name, notes) 
values(2, 'Drama', 'OMG');
insert into `genres`(id, genre_name, notes) 
values(3, 'Comedy', 'HAHA');
insert into `genres`(id, genre_name, notes) 
values(4, 'Chalga', 'Crap');
insert into `genres`(id, genre_name, notes) 
values(5, 'Documentary', 'mhm');

insert into `categories`(id, category_name, notes) 
values(1, 'Science and Finction', 'Crazy');
insert into `categories`(id, category_name, notes) 
values(2, 'Drama Queeeeeen', 'OMG');
insert into `categories`(id, category_name, notes) 
values(3, 'FUN', 'HAHA');
insert into `categories`(id, category_name, notes) 
values(4, 'Taste killer', 'Crap');
insert into `categories`(id, category_name, notes) 
values(5, 'Educational and Soul Builder', 'ok');

insert into `movies`(id, title, director_id, copyright_year, length, genre_id, category_id, rating, notes) 
values(1, 'Star Wars 1', 1, '1987', '2:22:22', 1, 1, 9.99, 'Top movie of all time sci fi movies');
insert into `movies`(id, title, director_id, copyright_year, length, genre_id, category_id, rating, notes) 
values(2, 'Star Wars 2', 1, '1989', '3:13:33', 1, 1, 9.99, 'Top movie of all time sci fi movies');
insert into `movies`(id, title, director_id, copyright_year, length, genre_id, category_id, rating, notes) 
values(3, 'Star Wars 3', 1, '1990', '3:12:25', 1, 1, 9.99, 'Top movie of all time sci fi movies');
insert into `movies`(id, title, director_id, copyright_year, length, genre_id, category_id, rating, notes) 
values(4, 'Star Wars 4', 1, '2001', '2:25:06', 1, 1, 9.99, 'Top movie of all time sci fi movies');
insert into `movies`(id, title, director_id, copyright_year, length, genre_id, category_id, rating, notes) 
values(5, 'Star Wars 5', 1, '2007', '1:57:00', 1, 1, 9.99, 'Top movie of all time sci fi movies');



-- 12. Car Rental Database

REATE TABLE `categories` (
    `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `category` ENUM('sedan', 'van', 'combi'),
    `daily_rate` DOUBLE(8,2) NOT NULL DEFAULT 0,
    `weekly_rate` DOUBLE(8,2),
    `monthly_rate` DOUBLE(8,2),
    `weekend_rate` DOUBLE(8,2)
);
CREATE TABLE `cars` (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    plate_number VARCHAR(20) NOT NULL,
    make DATETIME,
    model VARCHAR(30),
    car_year YEAR,
    category_id INT,
    doors INT,
    picture BLOB,
    car_condition VARCHAR(200) COLLATE UTF8MB4_UNICODE_CI,
    available BOOLEAN DEFAULT true
);
CREATE TABLE employees (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    title ENUM ('Mr', 'Mrs', 'Ms') DEFAULT 'Mr',
    notes VARCHAR(255) COLLATE UTF8MB4_UNICODE_CI
);
CREATE TABLE customers (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    driver_licence_number INT NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    city VARCHAR(50),
    zip_code VARCHAR(50),
    notes VARCHAR(255) COLLATE UTF8MB4_UNICODE_CI
);
CREATE TABLE rental_orders (
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    employee_id INT NOT NULL,
    customer_id INT NOT NULL,
    car_id INT NOT NULL,
    car_condition VARCHAR(200) COLLATE UTF8MB4_UNICODE_CI,
    tank_level ENUM ('full', 'middle', 'empty') NOT NULL,
    kilometrage_start INT,
    kilometrage_end INT,
    total_kilometrage INT,
    start_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    end_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    total_days DOUBLE(8,2) NOT NULL,
    rate_applied DOUBLE(8,2) NOT NULL,
    tax_rate DOUBLE(8,2),
    order_status ENUM ('OK', 'whaiting', 'descr') DEFAULT 'whaiting',
    notes VARCHAR(255) COLLATE UTF8MB4_UNICODE_CI
);
 
INSERT INTO `categories` (`category`, `daily_rate`) VALUES ('van', 122.4);
INSERT INTO `categories` (`category`, `daily_rate`) VALUES ('sedan', 108.4);
INSERT INTO `categories` (`category`, `daily_rate`) VALUES ('combi', 112.4);
 
INSERT INTO `cars` (plate_number, make, model, car_year) VALUES ('CA1288XM', '2017-5-15 18:23:00', 'VW', 2017);
INSERT INTO `cars` (plate_number, make, model, car_year) VALUES ('CA1289XM', '2016-5-15 18:23:00', 'VW', 2016);
INSERT INTO `cars` (plate_number, make, model, car_year) VALUES ('CA1278XM', '2015-5-15 18:23:00', 'VW', 2015);
 
INSERT INTO `employees` (first_name, last_name, notes) VALUES ('ton4o', 'tokmak4iev', 'нещо не ми харедва');
INSERT INTO `employees` (first_name, last_name, notes) VALUES ('stilian', 'bozkov', 'този е екстра');
INSERT INTO `employees` (first_name, last_name, notes) VALUES ('Poni', 'Tenev', 'хмммм....');
 
INSERT INTO `customers` (driver_licence_number, full_name, address) VALUES (122450, 'Tokamak Tomov', 'somewhere in Bulgaria');
INSERT INTO `customers` (driver_licence_number, full_name, address) VALUES (122313, 'Pilat Tomov', 'somewhere in Bulgaria');
INSERT INTO `customers` (driver_licence_number, full_name, address) VALUES (122212, 'Svetlin Tomov', 'somewhere in Bulgaria');
 
INSERT INTO `rental_orders`
(employee_id, customer_id, car_id, tank_level, total_kilometrage, total_days, rate_applied)
VALUES
(1, 1, 1, 'full', 21520, 8.44, 144.55);
INSERT INTO `rental_orders`
(employee_id, customer_id, car_id, tank_level, total_kilometrage, total_days, rate_applied)
VALUES
(2, 2, 2, 'middle', 21420, 8.44, 144.55);
INSERT INTO `rental_orders`
(employee_id, customer_id, car_id, tank_level, total_kilometrage, total_days, rate_applied)
VALUES
(2, 2, 1, 'full', 213320, 8.44, 144.55);



-- 13. Hotel Database

CREATE TABLE `employees`(
    `id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    # might be not null
    `title` VARCHAR(20),
    `notes` TINYTEXT
);
 
INSERT INTO `employees` (first_name, last_name, title, notes)
    VALUES("Kaloyan", "Hristov", "Nikoi", "blabla");
INSERT INTO `employees` (first_name, last_name, title, notes)
    VALUES("fsafsa", "asfijasfisa", "Nikoi", "blabla");
INSERT INTO `employees` (first_name, last_name, title, notes)
    VALUES("Pan4o", "Vladigerov", "Nikoi", "blabla");
   
CREATE TABLE `customers`(
    `account_number` int primary key NOT NULL UNIQUE,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `phone_number` INT(20) NOT NULL,
    `emergency_name` VARCHAR(20) NOT NULL,
    `emergency_number` INT(20) NOT NULL,
    `notes` TINYTEXT
);
 
INSERT INTO `customers` (account_number, first_name, last_name, phone_number, emergency_name, emergency_number, notes)
    VALUES(2332, "Dan4o", "Petkov", 005390539, "Ivanka", 394802394, "Blabla");
INSERT INTO `customers` (account_number, first_name, last_name, phone_number, emergency_name, emergency_number, notes)
    VALUES(1, "Dan4o", "Petkov", 005390539, "Ivanka", 394802394, "Blabla");
INSERT INTO `customers` (account_number, first_name, last_name, phone_number, emergency_name, emergency_number, notes)
    VALUES(4, "Dan4o", "Petkov", 005390539, "Ivanka", 394802394, "Blabla");
 
/* room_status (room_status, notes) */
 
CREATE TABLE `room_status`(
    `room_status` VARCHAR(20) NOT NULL PRIMARY KEY,
    `notes` TINYTEXT
);
 
INSERT INTO `room_status` (room_status, notes)
    VALUES("bla", "blabla");
INSERT INTO `room_status` (room_status, notes)
    VALUES("ako", "blabla");
INSERT INTO `room_status` (room_status, notes)
    VALUES("petyr", "blabla");
 
CREATE TABLE `room_types`(
    `room_type` VARCHAR(20) NOT NULL PRIMARY KEY,
    `notes` TINYTEXT
);
 
INSERT INTO `room_types` (room_type, notes)
    VALUES("FLAT", "blabla");
INSERT INTO `room_types` (room_type, notes)
    VALUES("TWO BED", "blabla");
INSERT INTO `room_types` (room_type, notes)
    VALUES("apartment", "blabla");
   
CREATE TABLE `bed_types`(
    `bed_type` VARCHAR(20) NOT NULL PRIMARY KEY,
    `notes` TINYTEXT
);
 
INSERT INTO `bed_types` (bed_type, notes)
    VALUES("family", "blabla");
INSERT INTO `bed_types` (bed_type, notes)
    VALUES("nqkakvo", "blabla");
INSERT INTO `bed_types` (bed_type, notes)
    VALUES("neznam kakvo", "blabla");
 
/* rooms (room_number, room_type, bed_type, rate, room_status, notes) */
 
CREATE TABLE `rooms`(
   
    `room_number` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `room_type` VARCHAR(20) NOT NULL,
    `bed_type` VARCHAR(20) NOT NULL,
    `rate` FLOAT(5, 2),
    `room_status` BOOLEAN NOT NULL,
    `notes` TINYTEXT
);
 
INSERT INTO `rooms` (room_number, room_type, bed_type, rate, room_status, notes)
    VALUES(123, "type", "two", 34.2, FALSE, "blabla");
INSERT INTO `rooms` (room_number, room_type, bed_type, rate, room_status, notes)
    VALUES(55435, "type", "two", 23.2, true, "blabla");
INSERT INTO `rooms` (room_number, room_type, bed_type, rate, room_status, notes)
    VALUES(453543, "type", "two", 3.2, FALSE, "blabla");
 
/*
payments (id, employee_id, payment_date, account_number,
 first_date_occupied, last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes)
*/
 
CREATE TABLE `payments`(
    `id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `employee_id` INT NOT NULL,
    `payment_date` DATE NOT NULL,
    `account_number` BIGINT NOT NULL,
    `first_date_occupied` DATE NOT NULL,
    `last_date_occupied` DATE NOT NULL,
    `total_days` INT NOT NULL,
    `amount_charged` DECIMAL(14,2) NOT NULL,
    `tax_rate` DECIMAL(14,2) NOT NULL,
    `tax_amount` DECIMAL(14,2) NOT NULL,
    `payment_total` DECIMAL(14,2) NOT NULL,
    `notes` TINYTEXT
);
 
INSERT INTO `payments` (employee_id, payment_date, account_number,
    first_date_occupied, last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes)
    VALUES(2, "2095-05-02", 3213, "2095-05-02", "2095-05-06", 10, 235, 23, 23, 255, "bla");
   
INSERT INTO `payments` (employee_id, payment_date, account_number,
    first_date_occupied, last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes)
    VALUES(5, "2095-05-02", 3213, "2095-05-02", "2095-05-06", 10, 235, 23, 23, 255,"fdsfds");
   
INSERT INTO `payments` (employee_id, payment_date, account_number,
    first_date_occupied, last_date_occupied, total_days, amount_charged, tax_rate, tax_amount, payment_total, notes)
    VALUES(8, "2095-05-02", 3213, "2095-05-02", "2095-05-06", 10, 235, 23, 23, 255, "fsafsdfds");
 
CREATE TABLE `occupancies`(
    `id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `employee_id` INT NOT NULL,
    `date_occupied` DATE NOT NULL,
    `account_number` BIGINT NOT NULL,
    `room_number` INT NOT NULL,
    `rate_applied` DECIMAL(14,2) NOT NULL,
    `phone_charge` DECIMAL(14,2) NOT NULL,
    `notes` TINYTEXT
);
 
INSERT INTO `occupancies` (employee_id, date_occupied, account_number, room_number, rate_applied, phone_charge, notes)
    VALUES(34, "2004-05-01", 234353, 233, 23, 34, "blabla");
INSERT INTO `occupancies` (employee_id, date_occupied, account_number, room_number, rate_applied, phone_charge, notes)
    VALUES(53, "2333-05-01", 1, 233,  23, 34, "gfdgfd");
INSERT INTO `occupancies` (employee_id, date_occupied, account_number, room_number, rate_applied, phone_charge, notes)
    VALUES(123, "2356-05-01", 45, 233, 23.5, 34, "gfdgfd");
	
	
	
-- 14. Create SoftUni Database

create table `towns`(
`id` int primary key auto_increment,
`name` varchar(45) not null
);

create table `addresses` (
`id` int primary key auto_increment,
`address_text` varchar(45) not null,
`town_id` int not null
);

alter table `addresses` add constraint fk_addresses_towns foreign key(`town_id`) references `towns`(`id`);
create table `departments` (
`id` int primary key auto_increment,
`name` varchar(45) not null
);

create table `employees` (
`id` int primary key auto_increment,
`first_name` varchar(45) not null,
`middle_name` varchar(45) not null,
`last_name` varchar(45) not null,
`job_title` varchar(45) not null,
`department_id` int not null,
`hire_date` date,
`salary` double(38, 2), 
`address_id` int not null
);

alter table `employees` add constraint fk_employees_departments foreign key(`department_id`) references `departments`(`id`);
alter table `employees` add constraint fk_employees_addresses foreign key(`address_id`) references `addresses`(`id`);



-- 15. Basic Insert

insert into `towns`(name) value('Sofia');
insert into `towns`(name) value('Plovdiv');
insert into `towns`(name) value('Varna');
insert into `towns`(name) value('Burgas');

insert into `departments`(name) value('Engineering');
insert into `departments`(name) value('Sales');
insert into `departments`(name) value('Marketing');
insert into `departments`(name) value('Software Development');
insert into `departments`(name) value('Quality Assurance');

insert into `addresses`(address_text, town_id) values('Tintqva Street', 1);
insert into `addresses`(address_text, town_id) values('Bunardjika', 2);
insert into `addresses`(address_text, town_id) values('Moreto', 3);
insert into `addresses`(address_text, town_id) values('Sinite vulni', 4);

insert into `employees`(first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
value('Ivan', 'Ivanov', 'Ivanov', '.NET Developer', 4, '2013-02-01', 3500.00, 1);
insert into `employees`(first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
value('Petar', 'Petrov', 'Petrov', 'Senior Engineer', 1, '2004-03-02', 4000.00, 3);
insert into `employees`(first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
value('Maria', 'Petrova', 'Ivanova', 'Intern', 5, '2016-08-28', 525.25, 1);
insert into `employees`(first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
value('Georgi', 'Terziev', 'Ivanov', 'CEO', 2, '2007-12-09', 3000.00, 1);
insert into `employees`(first_name, middle_name, last_name, job_title, department_id, hire_date, salary, address_id)
value('Peter', 'Pan', 'Pan', 'Intern', 3, '2016-08-28', 599.88, 2);	



-- 16. Basic Select All Fields

select * from `towns`;
select * from `departments`;
select * from `employees`;


-- 17. Basic Select All Fields and Order Them 

select * from `towns` order by `name`;
select * from `departments` order by `name`;
select * from `employees` order by `salary` desc;


-- 18. Basic Select Some Fields 

select `name` from `towns` order by `name`;
select `name` from `departments` order by `name`;
select `first_name`, `last_name`, `job_title`, `salary` from `employees` order by `salary` desc;


-- 19. Increase Employees Salary

select (`salary` * 1.1) as possible_increase from `employees`;


-- 20. Decrease Tax Rate

select (`tax_rate` * 0.97) from payments;


-- 21. Delete All Records

truncate table `occupancies`;


































































