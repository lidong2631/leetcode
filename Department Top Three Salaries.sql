用correlated subquery

SELECT 
	D.Name AS Department, 
	E.Name AS Employee, 
	E.Salary AS Salary 
FROM Employee E, Department D
WHERE (
		SELECT COUNT(DISTINCT(Salary)) 
		FROM Employee 
       	WHERE DepartmentId = E.DepartmentId 
       		AND Salary > E.Salary
       	) < 3
AND E.DepartmentId = D.Id 
ORDER by E.DepartmentId, E.Salary DESC;

https://leetcode.com/discuss/23002/my-tidy-solution

I don't understand, why distinct, I've tried to remove distinct then failed.
consider about several highest value having the same value.
top 3 highest salaries dont have to be of only 3 different people


另一种解法
https://leetcode.com/discuss/22988/solution-using-variables-rank-salaries-within-department


http://en.wikipedia.org/wiki/Correlated_subquery
