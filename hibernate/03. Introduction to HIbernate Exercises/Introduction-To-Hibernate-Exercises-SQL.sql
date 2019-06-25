
-- 7. Addresses with Employee Count
select a.address_text, t.name,
(select count(employee_id)
from employees as ee
 where ee.address_id = a.address_id
) as `number_of_employees`
from employees as e 
join addresses as a 
on e.address_id = a.address_id
join towns as t on a.town_id = t.town_id
group by a.address_text
order by `number_of_employees` desc, a.town_id; 


-- 8.	Get Employee with Project
select concat(e.first_name, ' ', e.last_name) as full_name, e.job_title,
(select pp.name 
from projects as pp
where pp.project_id = ep.project_id
) as `project_name`
from employees as e 
join employees_projects ep on e.employee_id = ep.employee_id
join projects as p on p.project_id = ep.project_id
where e.employee_id = 83
order by project_name;

-- 13. Employees Maximum Salaries
select d.name, max(e.salary) as max_salary
from employees as e
join departments as d
on e.department_id = d.department_id
where salary not between 30000 and 70000
group by e.department_id;











