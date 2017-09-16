The Employee table holds all employees. The employee table has three columns: Employee Id, Company Name, and Salary.

+-----+------------+--------+
|Id   | Company    | Salary |
+-----+------------+--------+
|1    | A          | 2341   |
|2    | A          | 341    |
|3    | A          | 15     |
|4    | A          | 15314  |
|5    | A          | 451    |
|6    | A          | 513    |
|7    | B          | 15     |
|8    | B          | 13     |
|9    | B          | 1154   |
|10   | B          | 1345   |
|11   | B          | 1221   |
|12   | B          | 234    |
|13   | C          | 2345   |
|14   | C          | 2645   |
|15   | C          | 2645   |
|16   | C          | 2652   |
|17   | C          | 65     |
+-----+------------+--------+
Write a SQL query to find the median salary of each company. Bonus points if you can solve it without using any built-in SQL functions.

+-----+------------+--------+
|Id   | Company    | Salary |
+-----+------------+--------+
|5    | A          | 451    |
|6    | A          | 513    |
|12   | B          | 234    |
|9    | B          | 1154   |
|14   | C          | 2645   |
+-----+------------+--------+


select
	Id,
	Company,
	Salary
from (
	select
		e.Id,
		e.Salary,
		e.Company,
		if (@prev = e.Company, @Rank := @Rank + 1, @Rank := 1) as rank,
		@prev := e.Company
	from Employee e,
	(select @Rank := 0, @prev := 0) as temp order by e.Company, e.Salary, e.Id
) Ranking
inner join
(
	select 
		count(*) as totalcount,
		Company as name
	from Employee e2
	group by e2.Company
) companycount
on companycount.name = Ranking.Company
where Rank = floor((totalcount+1)/2) or Rank = floor((totalcount+2)/2)




{
	"headers": 
		[
			"Id", "Salary", "Company", "rank", "@prev := e.Company"
		], 
	"values": 
		[
			[3, 15, "A", 1.0, "A"], 
			[2, 341, "A", 2.0, "A"], 
			[5, 451, "A", 3.0, "A"], 
			[6, 513, "A", 4.0, "A"], 
			[1, 2341, "A", 5.0, "A"], 
			[4, 15314, "A", 6.0, "A"], 
			[8, 13, "B", 1.0, "B"], 
			[7, 15, "B", 2.0, "B"], 
			[12, 234, "B", 3.0, "B"], 
			[9, 1154, "B", 4.0, "B"], 
			[11, 1221, "B", 5.0, "B"], 
			[10, 1345, "B", 6.0, "B"], 
			[17, 65, "C", 1.0, "C"], 
			[13, 2345, "C", 2.0, "C"], 
			[14, 2645, "C", 3.0, "C"], 
			[15, 2645, "C", 4.0, "C"], 
			[16, 2652, "C", 5.0, "C"]
		]
}



{
	"headers": 
		[
			"totalcount", "name"
		], 
	"values": 
		[
			[6, "A"], 
			[6, "B"], 
			[5, "C"]
		]
}

























