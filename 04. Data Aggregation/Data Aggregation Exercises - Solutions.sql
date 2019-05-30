
-- 1. Records’ Count
select count(*)
from `wizzard_deposits`;

-- 2. Longest Magic Wand
select max(`magic_wand_size`) as `longest_magic_wand`
from `wizzard_deposits`;


-- 3. Longest Magic Wand per Deposit Groups
select `deposit_group`, max(`magic_wand_size`) as `longest_magic_wand`
from `wizzard_deposits`
group by `deposit_group`
order by `longest_magic_wand`, `deposit_group`;


-- 4. Smallest Deposit Group per Magic Wand Size*
select `deposit_group`
from  `wizzard_deposits`
group by `deposit_group`
order by avg(`magic_wand_size`) asc limit 1;


-- 5. Deposits Sum
select `deposit_group`, sum(`deposit_amount`) as `total_sum`
from `wizzard_deposits`
group by `deposit_group`
order by `total_sum` asc;


-- 6. Deposits Sum for Ollivander family
select `deposit_group`, sum(`deposit_amount`) as `total_sum`
from `wizzard_deposits`
where `magic_wand_creator` = 'Ollivander family'
group by `deposit_group`
order by `deposit_group` asc;


-- 7. Deposits Filter
select `deposit_group`, sum(`deposit_amount`) as `total_sum`
from `wizzard_deposits`
where `magic_wand_creator` = 'Ollivander family'
group by `deposit_group`
having `total_sum` < 150000 
order by `total_sum` desc;


-- 8. Deposit charge
select `deposit_group`, `magic_wand_creator`, min(`deposit_charge`)
from `wizzard_deposits`
group by `deposit_group`, `magic_wand_creator`
order by `magic_wand_creator`, `deposit_group`;


-- 9. Age Groups
select
case 
	when `age` >= 0 and `age` <= 10 then '[0-10]'
	when `age` >= 11 and `age` <= 20 then '[11-20]'
    when `age` >= 21 and `age` <= 30 then '[21-30]'
    when `age` >= 31 and `age` <= 40 then '[31-40]'
    when `age` >= 41 and `age` <= 50 then '[41-50]'
    when `age` >= 51 and `age` <= 60 then '[51-60]'
    else '[61+]'
end as `age_group`,
count(`age`) as `wizard_count`
from `wizzard_deposits`
group by `age_group`
order by `age_group`;
	

-- 10. First Letter
select 
	substring(`first_name`, 1, 1) as `first_letter`
    from `wizzard_deposits`
    where `deposit_group` = 'Troll chest'
    group by `first_letter`
	order by `first_letter`;


-- 11. Average Interest
select `deposit_group`, `is_deposit_expired`, avg(`deposit_interest`) as `average_interest`
from `wizzard_deposits`
where (extract(day from `deposit_start_date`) > 1 
		and extract(month from `deposit_start_date`) >= 1 
			and extract(year from `deposit_start_date`) >= 1985) 
   or (extract(day from `deposit_start_date`) >= 1 
		and extract(month from `deposit_start_date`) > 1 
			and extract(year from `deposit_start_date`) >= 1985)
group by `deposit_group`, `is_deposit_expired`
order by `deposit_group` desc, `is_deposit_expired` asc;


-- 12. Rich Wizard, Poor Wizard*
select sum(`diff`.`next_wizard`) as `sum_difference`
from (
	select `deposit_amount` - 
		(select `deposit_amount` 
        from `wizzard_deposits`
        where `id` = `wd`.`id` + 1) as `next_wizard`
	from `wizzard_deposits` as `wd`) as `diff`;


-- 13. Employees Minimum Salaries
select e.`department_id`, min(e.`salary`) as `minimum_salary`
from `employees` as e
where (e.`department_id` = 2 or e.`department_id` = 5 or e.`department_id` = 7) and e.`hire_date` > '2000/01/01'
group by e.`department_id`
order by e.`department_id`;


-- 14. Employees Average Salaries
create view `e_selected_employees` as
select * 
from `employees` as e
where e.`salary` > 30000 and `manager_id` <> 42;

update `e_selected_employees`
set `salary` = `salary` + 5000
where `department_id` = 1;

select `department_id`, avg(`salary`) as `avg_salary`
from `e_selected_employees`
group by `department_id`
order by `department_id` asc;


-- 15. Employees Maximum Salaries
select `department_id`, max(`salary`) as `max_salary`
from `employees`
group by `department_id`
having `max_salary` not between 30000 and 70000
order by `department_id`;


-- 16. Employees Count Salaries
select count(*) 
from `employees`
where `manager_id` is null;


-- 17. 3rd Highest Salary*
SELECT
    `e1`.`department_id`,
    (SELECT `e2`.`salary`
        FROM
            `employees` AS `e2`
        WHERE
            `e2`.`department_id` = `e1`.`department_id`
        group by `e2`.`salary`    
        ORDER BY `e2`.`salary` DESC
        limit 1 offset 2) AS `third_highest_salary`
FROM
    `employees` AS `e1`
GROUP BY `e1`.`department_id`
HAVING `third_highest_salary` IS NOT NULL;
	

-- 18. Salary Challenge**
select `e1`.`first_name`, `e1`.`last_name`, `e1`.`department_id`
from `employees` as `e1`
where `e1`.`salary` > (
    select avg(`e2`.`salary`)
	from `employees` as `e2`
	where `e2`.`department_id` = `e1`.`department_id`
	group by `e2`.`department_id` 
) 

order by `e1`.`department_id`, `e1`.`employee_id`  limit 10;


-- 19. Departments Total Salaries
select `department_id`, sum(`salary`) as `total_salary`
from `employees`
group by `department_id`
order by `department_id`;





















