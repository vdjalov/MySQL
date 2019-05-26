-- 1. Find Names of All Employees by First Name
select `first_name`, `last_name`
from `employees`
where left(`first_name`, 2) = 'Sa' order by `employee_id`;


-- 2. Find Names of All employees by Last Name 
select `first_name`, `last_name`
from `employees`
where `last_name` like '%ei%' 
order by `employee_id`;


-- 3. Find First Names of All Employees
select `first_name`
from `employees`
where (`department_id` = 3 or `department_id` = 10)  and extract(year from `hire_date`) between '1995' and '2005'
order by `employee_id`;


-- 4. Find All Employees Except Engineers
select `first_name`, `last_name`
from `employees`
where `job_title` not like '%engineer%'
order by `employee_id`;


-- 5. Find Towns with Name Length
select `name` 
from `towns`
where length(`name`) between 5 and 6 
order by `name` asc;


-- 6. Find Towns Starting With
-- FIRST WAY
select `town_id`, `name`
from `towns`
where substring(`name`,1, 1) = 'M' 
or substring(`name`,1, 1) = 'K' 
or substring(`name`,1, 1) = 'B' 
or substring(`name`,1, 1) = 'E'
order by `name` asc;

-- SECOND WAY
select `town_id`, `name`
from `towns`
where `name` regexp '^\[E, B, K, M\]'
order by `name` asc;


-- 7. Find Towns Not Starting With
-- FIRST WAY 
select `town_id`, `name`
from `towns`
where substring(`name`,1, 1) <> 'R' 
and substring(`name`,1, 1) <> 'B' 
and substring(`name`,1, 1) <> 'D'
order by `name` asc;

-- Second WAy
select `town_id`, `name`
from `towns`
where `name` regexp '^\[^R, ^B, ^D\]'
order by `name` asc;


-- 8. Create View Employees Hired After 2000 Year
create view `v_employees_hired_after_2000`
as
select `first_name`, `last_name`
from `employees`
where extract(year from `hire_date`) > 2000;


-- 9. Length of Last Name
select `first_name`, `last_name`
from `employees`
where length(`last_name`) = 5;


-- 10. Countries Holding ‘A’ 3 or More Times
select `country_name`, `iso_code`
from `countries`
where `country_name` like '%a%a%a%'
order by `iso_code` asc;


-- 11. Mix of Peak and River Names
select `peak_name`, `river_name`,
concat_ws('', lower(`peak_name`), substring(lower(`river_name`), 2, length(`river_name`))) as `mix`
from `rivers`, `peaks` 
where right(`peak_name`, 1) = left(`river_name`, 1)
order by `mix` ;


-- 12. Games from 2011 and 2012 year
select `name`, date_format(`start`, '%Y-%m-%d') as date
from `games`
where extract(year from `start`) between 2011 and 2012 
order by `start`, `name` limit 50;


-- 13. User Email Providers
select `user_name`, 
substring(`email`, locate('@', `email`) + 1, length(`email`)) as `Email Provider`
from `users` order by `Email Provider`, `user_name`;


-- 14. Get Users with IP Address Like Pattern
select `user_name`, `ip_address`
from `users`
where `ip_address` like '___.1%.%.___'
order by `user_name`;


-- 15. Show All Games with Duration and Part of the Day
select `name` as `game`, 
case
	when extract(hour from `start`) >= 0 and extract(hour from `start`) < 12 then 'Morning'
    when extract(hour from `start`) >= 12 and extract(hour from `start`) < 18 then 'Afternoon'
    when extract(hour from `start`) >= 18 and extract(hour from `start`) < 24 then 'Evening'
    end as `Part of the Day`,

case 
	when `duration` <= 3 then 'Extra Short'
    when `duration` > 3 and `duration` <= 6 then 'Short'
    when `duration` > 6 and `duration` <= 10 then 'Long'
    else 'Extra Long'
	end as `Duration`
from `games`;


-- 16. Orders Table
select `product_name`, `order_date`,
date_add(`order_date`, interval 3 day) as `pay_due`,
date_add(`order_date`, interval 1 month) as `delivery_due`
from `orders`; 














