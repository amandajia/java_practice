# Write your MySQL query statement below
delete from Person
where Id in (select b.Id
from (select * from Person) AS a, (select * from Person) AS b
where a.Email = b.Email
and a.Id < b.Id );
