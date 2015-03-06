# Write your MySQL query statement below
SELECT DISTINCT(log1.Num)
FROM
(
    SELECT *FROM Logs
) log1
JOIN
(   
    SELECT *FROM Logs
) log2
ON log1.Num = log2.Num AND log1.Id = log2.Id+1
Join
(
    SELECT *FROM Logs
) log3
ON log2.Num = log3.Num AND log2.Id = log3.Id+1




# Write your MySQL query statement below
SELECT DISTINCT Num
FROM (
    SELECT
        Num,
        CASE
        WHEN @record = Num THEN @count:=@count+1
        WHEN @record <> @record:=Num THEN @count:=1
        END As n
    From Logs, (
        SELECT 
            @count:=0,
            @record:=(SELECT Num From Logs LIMIT 0,1)
        ) a
) tmp
WHERE tmp.n>=3










SELECT 
	DISTINCT Num 
FROM (
	SELECT 
		Num, 
		COUNT(Rank) AS Cnt 
	FROM (
    SELECT 
		Num,
        @curRank := @curRank + IF(@prevNum = Num, 0, 1) AS rank, 
        @prevNum := Num
    FROM  Logs s, (SELECT @curRank := 0) r, (SELECT @prevNum := NULL) p
    ORDER BY  ID ASC
	) t 
	GROUP BY Rank 
	HAVING Cnt >= 3 
) n;