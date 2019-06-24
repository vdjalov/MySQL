-- 2.Get Villains’ Names
select concat(v.`name`, ' ', count(m.`name`)) as output
from villains as v 
join minions_villains as mv
on v.`id` = mv.`villain_id`
join minions as m
on m.`id` = mv.`minion_id`
where v.`id` = mv.`villain_id`
group by v.`name`
having count(m.`name`) > 15
order by count(m.`name`) desc;


-- 3. Get Minion Names
select m.`name`, m.`age`
from villains as v
join minions_villains as mv on v.`id` = mv.`villain_id`
join minions as m on m.`id` = mv.`minion_id`
where mv.villain_id = 1;


-- 4. Add Minion
select t.`id` 
from towns as t 
where t.`name` = 'Berli';


-- 9. Increase Age Stored Procedure
DELIMITER $$
create procedure usp_get_older(minion_id int)
begin 
update minions as m 
set m.`age` = m.`age` + 1
where m.`id` = minion_id;
end $$

call usp_get_older(1);

























