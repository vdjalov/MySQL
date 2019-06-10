-- 1. Employees with Salary Above 35000
DELIMITER $$
create procedure usp_get_employees_salary_above_35000 ()
begin 
select e.`first_name`, e.`last_name`
from employees as e
where e.`salary` > 35000
order by e.`first_name`, e.`last_name`, e.`employee_id`;
end $$

-- call usp_get_employees_salary_above_35000 ();


-- 2. Employees with Salary Above Number
DELIMITER $$
create procedure usp_get_employees_salary_above (enter_number double)
begin 
select e.`first_name`, e.`last_name` 
from employees as e
where e.`salary` >= enter_number
order by e.`first_name`, e.`last_name`, e.`employee_id` asc;
end $$ 

-- call usp_get_employees_salary_above(48100);


-- 3. Town Names Starting With
DELIMITER $$
create procedure usp_get_towns_starting_with (word varchar(45))
begin
select t.`name` as `town_name` 
from towns as t
where t.`name` like concat(word, '%')
order by t.`name`;
end $$

-- call usp_get_towns_starting_with('b');


-- 4. Employees from Town
DELIMITER $$
create procedure usp_get_employees_from_town (town_name varchar(45))
begin
select e.`first_name`, e.`last_name`
from employees as e
join addresses as a
on e.`address_id` = a.`address_id`
join towns as t 
on a.`town_id` = t.`town_id`
where t.`name` = town_name
order by e.`first_name`, e.`last_name`, e.`employee_id` asc;
end $$

-- call usp_get_employees_from_town('Sofia');


-- 5. Salary Level Function
DELIMITER $$
create function ufn_get_salary_level(current_salary decimal(32, 4)) 
returns varchar(45)  
begin
declare result varchar(45);
	if current_salary < 30000 then set result := 'Low';
	elseif current_salary between 30000 and 50000 then set result := 'Average';
	elseif current_salary > 50000 then set result := 'High';
	end if;
return result;
end $$

-- select ufn_get_salary_level(10000);


-- 6. Employees by Salary Level
DELIMITER $$
create procedure usp_get_employees_by_salary_level(level_of_salary varchar(45))
begin  
	select e.`first_name`, e.`last_name` 
     from employees as e
     where ufn_get_salary_level(e.`salary`) = level_of_salary
     order by e.`first_name` desc, e.`last_name` desc;
end $$

-- call usp_get_employees_by_salary_level('Low');


-- 7. Define Function
DELIMITER $$
create function ufn_is_word_comprised(set_of_letters varchar(50), word varchar(50))  
returns bit(2)
begin 
declare result bit(2);
declare indx int;
declare symbol varchar(1);
set indx := 1;
set result := 1;

	while indx <= char_length(word) do 
		set symbol := substring(word, indx, 1);
        if (locate(symbol, set_of_letters) = 0 )
			then set result := 0;
			return result;
           end if; 
        set indx := indx + 1;
        end while;
        return result;
end $$

-- select ufn_is_word_comprised('oistmiahf', 'Sofia');

-- 8. Find Full Name
DELIMITER $$
create procedure usp_get_holders_full_name() 
begin 
select concat_ws(' ', ah.`first_name`, ah.`last_name`) as `full_name`
from account_holders as ah
order by `full_name`, ah.`id` asc;
end $$

-- call usp_get_holders_full_name();


-- 9.	People with Balance Higher Than
DELIMITER $$
create procedure usp_get_holders_with_balance_higher_than(current_value double)
begin 
select ah.`first_name`, ah.`last_name`
from account_holders as ah
join accounts as a
on  a.`account_holder_id` = ah.`id` 
group by ah.`id`
having sum(a.`balance`) > current_value
order by a.`id` ;
end $$

-- call usp_get_holders_with_balance_higher_than(7000);


-- 10. Future Value Function
DELIMITER $$
create function ufn_calculate_future_value(current_sum double, yearly_interest_rate double, number_of_years double)
returns double
begin  
declare result double;
set result := current_sum * (power((1 + yearly_interest_rate), number_of_years));
return result;
end $$

-- select ufn_calculate_future_value (1000, 0.1,5);


-- 11. Calculating Interest
Delimiter $$
create procedure usp_calculate_future_value_for_account(current_id int, current_interest_rate decimal(18, 4))
begin 
select a.`id` as `account_id`, ah.`first_name`, ah.`last_name`, a.`balance` as `current_balance`,
cast(ufn_calculate_future_value(a.`balance`, current_interest_rate, 5) as decimal(18, 4))  as `balance_in_5_years`
from account_holders as ah
join accounts as a 
on ah.`id` = a.`account_holder_id`
where a.`id` = current_id
group by ah.`id`;
end $$

-- call usp_calculate_future_value_for_account(1, 0.1);


-- 12. Deposit Money
DELIMITER $$ 
create procedure usp_deposit_money(account_id int, money_amount decimal(14.4))
begin 
	start transaction; 
    
    if(select a.`id`
    from accounts as a
    where a.`id` = account_id) is null then rollback;
    elseif(account_id < 0) then rollback;
    elseif((select count(a.`id`) from accounts as a)  < account_id) then rollback;
    elseif(money_amount < 0) then rollback;
    else
    update accounts as a 
    set a.`balance` = a.`balance` + money_amount
    where a.`id` = account_id;
       commit;
    end if;
 
   -- select a.`id` as `account_id`, a.`account_holder_id`, a.`balance`
   -- from accounts as a
   -- where a.`id` = account_id;
end $$

-- call usp_deposit_money(1800, 10);


-- 13. Withdraw Money
DELIMITER $$
create procedure usp_withdraw_money(account_id int , money_amount decimal(14,4)) 
begin 
	start transaction;
    if(
    ((select a.`balance`
    from accounts as a
    where a.`id` = account_id) - money_amount) < 0  
    ) then rollback;
    elseif(money_amount < 0) then rollback;
    else 
    update accounts as a
    set a.`balance` = a.`balance` - money_amount
    where a.`id` = account_id;
	commit;	
    end if;
end $$

-- call usp_withdraw_money(1, 10);


-- 14. Money Transfer
DELIMITER $$
create procedure usp_transfer_money(from_account_id int, to_account_id int, amount double)
begin
	start transaction;
	if
    (
    (select a.`id` 
    from accounts as a
    where a.`id` = from_account_id) is null 
    ) then rollback;
    elseif (
    (select a.`id` 
    from accounts as a
    where a.`id` = to_account_id) is null 
    ) then rollback;
    elseif(
    (select a.`balance` 
    from accounts as a
    where a.`id` = from_account_id) - amount < 0 
    ) then rollback;
	elseif (amount <= 0) then rollback;
	else 
    update accounts as a 
    set a.`balance` = a.`balance` - amount
    where a.`id` = from_account_id;
    
    update accounts as a 
    set a.`balance` = a.`balance` + amount
    where a.`id` = to_account_id;
    commit;
	end if;
end $$

-- call usp_transfer_money(1, 2, 150);


-- 15. Log Accounts Trigger
create table `logs`(
`log_id` int auto_increment primary key,
`account_id` int,
`old_sum` decimal(18, 4),
`new_sum` decimal(18, 4)
);


create trigger tr_on_balance_change
after update 
on accounts
for each row
begin 
	insert into logs(`account_id`, `old_sum`, `new_sum`)
    values(old.`id`, old.`balance`, new.`balance`);
end 


-- 16. Emails Trigger
create table `logs`(
`log_id` int auto_increment primary key,
`account_id` int,
`old_sum` decimal(18, 4),
`new_sum` decimal(18, 4)
);

create trigger tr_on_balance_change
after update 
on accounts
for each row
begin 
	insert into logs(`account_id`, `old_sum`, `new_sum`)
    values(old.`id`, old.`balance`, new.`balance`);
end ;

create table notification_emails(
id int auto_increment primary key,
recipient int,
subject varchar(255),
body varchar(255));

 DELIMITER $$
create trigger tr_on_balance_change_e_mail_notification
after update
on accounts
for each row
begin
	insert into notification_emails(recipient, `subject`, body)
    values(
    new.`id`,
    concat('Balance change for account: ', new.`id`),
    concat('On ', now(), ' your balance was changed from ', old.`balance`, ' to ', new.`balance`, '.')
    );
end $$

































