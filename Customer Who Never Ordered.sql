# Write your MySQL query statement below
select Name
from Customers
where Id not in (
    select distinct CustomerId
    from Orders
)



https://leetcode.com/discuss/22624/three-accepted-solutions

SELECT A.Name from Customers A
LEFT JOIN Orders B on  a.Id = B.CustomerId
WHERE b.CustomerId is NULL