
-- 1. Employee Address
select e.`employee_id`, e.`job_title`, a.`address_id`, a.`address_text`
from employees as e
join addresses as a
on e.`address_id` = a.`address_id`
order by a.`address_id` limit 5;


-- 2. Addresses with Towns
select 
e.`first_name`,
e.`last_name`, 
(select ct.`name`
	from towns as ct
    where a.`town_id` = ct.`town_id`
) as `town`
, a.`address_text`
from employees as e
join addresses as a
on e.`address_id` = a.`address_id`
order by e.`first_name` asc, e.`last_name` asc limit 5;


-- 3. Sales Employee
select e.`employee_id`, e.`first_name`, e.`last_name`, d.`name` as `department_name`
from employees as e
join departments as d
on e.`department_id` = d.`department_id`
where d.`name` = 'Sales'
order by e.`employee_id` desc;


-- 4. Employee Departments
select e.`employee_id`, e.`first_name`, e.`salary`, d.`name` as `department_name`
from employees as e
join departments as d
on e.`department_id` = d.`department_id`
where e.`salary` > 15000
order by e.`department_id` desc limit 5;


-- 5. Employees Without Project
select e.`employee_id`, e.`first_name`
from employees as e 
left join employees_projects as ep
on e.`employee_id` = ep.`employee_id`
where `project_id` is null
order by e.`employee_id` desc limit 3;


-- 6. Employees Hired After
select e.`first_name`, e.`last_name`, e.`hire_date`, d.`name` as `dept_name`
from employees as e
join departments as d
on e.`department_id` = d.`department_id`
where  (d.`name` = 'Sales' or d.`name` = 'Finance') and date(e.`hire_date`) > '1999/01/01'
order by e.`hire_date` asc;


-- 7. Employees with Project
select 
(
select ce.`employee_id` 
from employees as ce 
where ce.`employee_id` = ep.`employee_id`) as `employee_id`,
(
select ce.`first_name`
from employees as ce
where ce.`employee_id` = ep.`employee_id`
) as `first_name`,
p.`name`
from employees_projects as ep
join projects as p
on ep.`project_id` = p.`project_id`
where date(p.`start_date`) > '2002-08-13' and date(p.`end_date`) is null
order by 
(
select e.`first_name` from employees as e
where e.`employee_id` = ep.`employee_id`
), p.`name` limit 5;


-- 8. Employee 24
select e.`employee_id`, e.`first_name`,
(
select if(extract(year from p.`start_date`) < 2005, p.`name`, null)
from projects as p
where p.`project_id` = ep.`project_id`
) as `project_name`
from employees as e
join employees_projects as ep
on e.`employee_id` = ep.`employee_id`
where ep.`employee_id` = 24 
order by `project_name`;


-- 9. Employee Manager
select e.`employee_id`, e.`first_name`, e.`manager_id`,
(
select e1.`first_name`
from employees as e1
where e.`manager_id` = e1.`employee_id`
) as `manager_name`
from employees as e
where e.`manager_id` in (3, 7)
order by e.`first_name` asc;



-- 10. Employee Summary
select 
e.`employee_id`,
concat_ws(' ', e.`first_name`, e.`last_name`) as `employee_name`,
(
select concat_ws(' ', e1.`first_name`,e1.`last_name`)
from employees as e1
where e.`manager_id` = e1.`employee_id`
) as `manager_name`,
(
select d1.`name`
from departments as d1
where d.`department_id` = d1.`department_id` 
) as `department_name`

from employees as e
join departments as d
on e.`department_id` = d.`department_id`
where e.`manager_id` is not null
order by e.`employee_id` limit 5;


-- 11. Min Average Salary
select avg(e.`salary`) as `min_average_salary`
from employees as e
group by e.`department_id` 
order by `min_average_salary` limit 1;


-- 12. Highest Peaks in Bulgaria
select
mc.`country_code`,
(
select m.`mountain_range`
from mountains as m
where mc.`mountain_id` = m.`id`
) as `mountain_range`,
p.`peak_name`, p.`elevation`
from peaks as p
join mountains_countries as mc
on p.`mountain_id` = mc.`mountain_id`
where p.`elevation` > 2835 and mc.`country_code` = 'BG'
order by p.`elevation` desc;


-- 13. Count Mountain Ranges
select mc.`country_code`,
sum((
select count(m.`mountain_range`)
from mountains as m
where m.`id` = mc.`mountain_id` 
) )as `mountain_range`
from mountains_countries as mc
where mc.`country_code` in('BG', 'RU', 'US')
group by mc.country_code
order by `mountain_range` desc;


-- 14. Countries with Rivers
select 
c.`country_name`, 
(
select r.`river_name`
from rivers as r 
where r.`id` = cr.`river_id`
) as `river_name`
from countries as c 
left join countries_rivers as cr
on c.`country_code` = cr.`country_code`
where c.`continent_code` = 'AF'
order by c.`country_name` asc limit 5;


-- 15
select 
c.`continent_code`,
(
select count(distinct cc.`currency_code`)
from countries as cc
where  c.`continent_code` = cc.`continent_code`


) as `currency_usage`
from countries as c
group by c.`continent_code`
order by c.`continent_code`;






















-- 16. Countries without any Mountains
select 
count(c.`country_name`) - 
(
select count(distinct(mc.`country_code`))
from mountains_countries as mc 
) as `country_count`
from countries as c;


-- 17. Highest Peak and Longest River by Country
select c.`country_name`,
(
select max(p.`elevation`)
from peaks as p 
join mountains_countries as mc
on mc.`mountain_id` = p.`mountain_id`
where mc.`country_code` = c.`country_code`
) as `highest_peak_elevation`,
(
select max(r.`length`) 
from rivers as r 
join countries_rivers as cr
on r.`id` = cr.`river_id`
where cr.`country_code` = c.`country_code` 
) as `longest_river_length`
from countries as c
order by `highest_peak_elevation` desc, `longest_river_length` desc, c.`country_name` limit 5;



















