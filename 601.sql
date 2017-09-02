# Write your MySQL query statement below
select id, date, people from
(select id, date, people,
    @count := if(id=@last+1, @count + 1, 1) as row_count,
    @last  := id as srow,
    @si := id - @count as ind
from stadium,
    (select @count:=0, @last:=-1, @si = -1) init  
where people >= 100
) e 
where row_count == 3;
