# Write your MySQL query statement below
select id, movie, description, rating
from cinema
where description <> 'boring'
AND mod(id, 2) = 1 
order by rating Desc
