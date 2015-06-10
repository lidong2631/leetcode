# Write your MySQL query statement below
delete from Person
where Id not in (
    select A.Id
    from
    (
        select min(Id) as Id
        from Person
        group by Email
    ) A
)

https://leetcode.com/discuss/31211/a-simple-ac-solution



# Write your MySQL query statement below
delete p1
from Person p1, Person p2
where p1.Email = p2.Email and p1.Id>p2.Id

https://leetcode.com/discuss/29787/simple-solution