create database `ex`;
use `ex`;

-- 1. One-To-One Relationship
create table passports(
	`passport_id` int unique primary key,
    `passport_number` varchar(45) 
);


create table persons(
	`person_id` int auto_increment primary key,
    `first_name` varchar(45) not null,
    `salary` decimal(53, 2),
    `passport_id` int unique not null
);


alter table persons
add constraint `persons_passports`
foreign key(`passport_id`)
references `passports`(`passport_id`);

insert into passports(`passport_id`,`passport_number`)
values(101, 'N34FG21B');
insert into passports(`passport_id`, `passport_number`)
values(102, 'K65LO4R7');
insert into passports(`passport_id`, `passport_number`)
values(103, 'ZE657QP2');

insert into persons(`first_name`, `salary`, `passport_id`)
values ('Roberto', 43300, 102);
insert into persons(`first_name`, `salary`, `passport_id`)
values ('Tom', 56100.00, 103);
insert into persons(`first_name`, `salary`, `passport_id`)
values ('Yana', 60200.00, 101);


-- 2. One-To-Many Relationship
create table manufacturers(
`manufacturer_id` int auto_increment primary key,
`name` varchar(55),
`established_on` date
);

create table models(
`model_id` int unique primary key,
`name` varchar(55),
`manufacturer_id` int 
);

alter table models
add constraint `models_manufacturers`
foreign key(`manufacturer_id`)
references manufacturers(`manufacturer_id`);

insert into manufacturers(`name`, `established_on`)
values('BMW', '1916/03/01');
insert into manufacturers(`name`, `established_on`)
values('Tesla', '2003/01/01');
insert into manufacturers(`name`, `established_on`)
values('Lada', '1966/05/01');

insert into models(`model_id`, `name`, `manufacturer_id`)
values(101, 'X1', 1);
insert into models(`model_id`, `name`, `manufacturer_id`)
values(102, 'i6', 1);
insert into models(`model_id`, `name`, `manufacturer_id`)
values(103, 'Model S', 2);
insert into models(`model_id`, `name`, `manufacturer_id`)
values(104, 'Model X', 2);
insert into models(`model_id`, `name`, `manufacturer_id`)
values(105, 'Model 3', 2);
insert into models(`model_id`, `name`, `manufacturer_id`)
values(106, 'Nova', 3);


-- 3. Many-To-Many Relationship
create table students(
`student_id` int auto_increment primary key,
`name` varchar(105)
);

create table exams(
`exam_id` int unique primary key,
`name` varchar(255)
);

create table students_exams(
`student_id` int,
`exam_id` int
);

alter table students_exams
add constraint `pk_students_exams` primary key(`student_id`, `exam_id`);

alter table students_exams
add constraint `fk_students_exams_students`
foreign key(`student_id`)
references students(`student_id`);

alter table students_exams
add constraint `fk_students_exams_exams`
foreign key(`exam_id`)
references exams(`exam_id`);


insert into students(`name`)
values ('Mila');
insert into students(`name`)
values ('Toni');
insert into students(`name`)
values ('Ron');

insert into exams(`exam_id`, `name`)
values(101, 'Spring MVC');
insert into exams(`exam_id`, `name`)
values(102, 'Neo4j');
insert into exams(`exam_id`, `name`)
values(103, 'Oracle 11g');

insert into students_exams(`student_id`, `exam_id`)
values(1, 101);
insert into students_exams(`student_id`, `exam_id`)
values(1, 102);
insert into students_exams(`student_id`, `exam_id`)
values(2, 101);
insert into students_exams(`student_id`, `exam_id`)
values(3, 103);
insert into students_exams(`student_id`, `exam_id`)
values(2, 102);
insert into students_exams(`student_id`, `exam_id`)
values(2, 103);


-- 4. Self-Referencing
create table teachers (
`teacher_id` int unique primary key,
`name` varchar(255), 	
`manager_id` int
);

insert into teachers(`teacher_id`, `name`, `manager_id`)
values(101, 'John', null);
insert into teachers(`teacher_id`, `name`, `manager_id`)
values(102, 'Maya', 106);
insert into teachers(`teacher_id`, `name`, `manager_id`)
values(103, 'Silvia', 106);
insert into teachers(`teacher_id`, `name`, `manager_id`)
values(104, 'Ted', 105);
insert into teachers(`teacher_id`, `name`, `manager_id`)
values(105, 'Mark', 101);
insert into teachers(`teacher_id`, `name`, `manager_id`)
values(106, 'Greta', 101);

alter table teachers
add constraint `fk_teachers_teachers`
foreign key(`manager_id`)
references teachers(`teacher_id`);


-- 5. Online Store Database
create table items(
item_id int unique primary key,
`name` varchar(50),
item_type_id int
);

create table order_items(
order_id int,
item_id int
);

create table item_types(
item_type_id int unique primary key not null,
name varchar(50)
);

create table cities(
city_id int unique primary key not null,
name varchar(50)
);

create table customers(
customer_id int unique primary key not null,
name varchar(50),
birthday date,
city_id int
);

create table orders(
order_id int unique primary key not null,
customer_id int
);

alter table order_items
add constraint pk_order_items
primary key(`order_id`, `item_id`);

alter table items
add constraint `items_items_types`
foreign key(`item_type_id`)
references item_types(`item_type_id`); 

alter table order_items
add constraint `order_items_items`
foreign key(`item_id`)
references items(`item_id`); 

alter table order_items
add constraint `orders_items_types`
foreign key(`order_id`)
references orders(`order_id`); 

alter table customers
add constraint `customers_cities`
foreign key(`city_id`)
references cities(`city_id`);

alter table orders
add constraint `orders_customers`
foreign key(`customer_id`)
references customers(`customer_id`);  


-- 6. University Database
create table subjects(
`subject_id` int primary key unique not null,
`subject_name` varchar(50) 
);

create table agenda(
`student_id` int,
`subject_id` int, 
 constraint primary key(`student_id`, `subject_id`)
);

create table majors(
`major_id` int primary key unique not null,
`name` varchar(50) 
);

create table payments(
`payment_id` int primary key unique not null,
`payment_date` date,
`payment_amount` decimal(8,2),
`student_id` int 
);

create table students(
`student_id` int primary key unique not null,
`student_number` varchar(12),
`student_name` varchar(50),
`major_id` int 
);

alter table agenda
add constraint `fk_agenda_subjects`
foreign key(`subject_id`)
references subjects(`subject_id`);

alter table agenda
add constraint `fk_agenda_students`
foreign key(`student_id`)
references students(`student_id`);

alter table students
add constraint `fk_students_majors`
foreign key(`major_id`)
references majors(`major_id`);

alter table payments
add constraint `fk_payments_students`
foreign key(`student_id`)
references students(`student_id`);















-- 9. Peaks in Rila
select m.`mountain_range`, p.`peak_name`, p.`elevation` as `peak_elevation`
from mountains as m
join peaks as p
on m.`id` = p.`mountain_id`
where m.`mountain_range` = 'Rila'
order by `peak_elevation` desc;






