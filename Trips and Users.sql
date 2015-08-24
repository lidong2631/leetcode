MYSQL

SELECT
	t.Request_at as DAY,
	ROUND(
		SUM(
			CASE WHEN t.Status LIKE 'cancelled_%' THEN 1
			ELSE 0
			END
		)/COUNT(*), 2
	) as Rate
FROM Trips t
JOIN Users u
ON t.Client_Id = u.Users_id AND u.Banned = 'No'
WHERE t.Request_at BETWEEN '2013-10-01' AND '2013-10-03'
GROUP BY t.Request_at

https://leetcode.com/discuss/52594/sharing-my-solution



SQL Server

select 
	t.Request_at as DAY,
	CONVERT(
		DECIMAL(4,2),
		sum(
			case when t.Status like 'cancelled_%' then 1 
			else 0 
			end)/CONVERT(decimal(4,2),count(*)),2
	) as Rate
from Trips t 
inner join Users u 
on t.Client_Id = u.Users_Id and u.Banned='No'
where t.Request_at between '2013-10-01' and '2013-10-03'
group by t.Request_at
