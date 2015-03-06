 
 
IF OBJECT_ID('TEMPDB..#AVGMB') IS NOT NULL
    DROP TABLE #AVGMB
       
IF OBJECT_ID('TEMPDB..#AVGMG') IS NOT NULL
    DROP TABLE #AVGMG   
    
        
IF OBJECT_ID('TEMPDB..#AVGBAL') IS NOT NULL
    DROP TABLE #AVGBAL  
        

       /**
       * 
       * 
       * #AVGMB
       **/
SELECT * INTO #AVGMB
FROM   (
    
           SELECT ACCOUNT_NUMBER,
                  [CURRENCY],
                  ONLINE_ACTUAL_BAL,
                  COMPANY,
                  ASOFDATE
           FROM   MULTI_ACCOUNT_BALANCE_HIST
           WHERE  ASOFDATE BETWEEN '20091231' AND '20100630'
       )
       AC
WHERE ISNUMERIC(ac.ACCOUNT_NUMBER)=1
ORDER BY
       AC.ASOFDATE
            /**
       * 
       * 
       * #AVGMG
       **/  
SELECT DISTINCT BAL.*,
       AC.MINDATE,
       AC.MAXDATE INTO #AVGMG
FROM   #AVGMB BAL
       LEFT JOIN (
                SELECT ACCOUNT_NUMBER,
                       MIN(ASOFDATE) AS MINDATE,
                       MAX(ASOFDATE) AS MAXDATE
                FROM   #AVGMB AC
                GROUP BY
                       ACCOUNT_NUMBER
            ) AC
            ON  BAL.ACCOUNT_NUMBER = AC.ACCOUNT_NUMBER
ORDER BY
       BAL.ASOFDATE

 
       /**
       * 
       * 
       * #AVGBAL
       **/
SELECT DISTINCT ACCOUNT_NUMBER,
       CURRENCY,
       (
           SELECT TOP 1 ONLINE_ACTUAL_BAL
           FROM   #AVGMG MA
           WHERE  D.ID >= MA.ASOFDATE
                  AND MA.ACCOUNT_NUMBER = AC.ACCOUNT_NUMBER
           ORDER BY
                  MA.ASOFDATE DESC
       ) AS ONLINE_ACTUAL_BAL,
       COMPANY,
       D.ID INTO #AVGBAL
FROM   #AVGMG AC
       LEFT JOIN DATA_MART_BR.DBO.CALDATES D
            ON  D.ID BETWEEN MINDATE AND MAXDATE
ORDER BY
       ID 
       
    DROP TABLE #AVGMB        
    DROP TABLE #AVGMG   
    
SELECT *
FROM   #AVGBAL


       /*
       * 
       * 
    
       SELECT DISTINCT BAL.ACCOUNT_NUMBER,
       CUSINFO.CUSTOMER,
       CUSTYPE.BOCSG_CUS_TYPE,BAL.COMPANY INTO dbo.CUS_TYPE
FROM   MULTI_ACCOUNT_BALANCE_HIST BAL
       LEFT JOIN (
                SELECT DISTINCT STA.ACCOUNT_NUMBER,
                       STA.CUSTOMER,
                       STA.COMPANY,
                       STA.DEPT_CODE,
                       STA.CO_CODE
                FROM   MULTI_ACCOUNT_STATIC_HIST STA
                       INNER JOIN (
                                SELECT ACCOUNT_NUMBER,
                                       COMPANY,
                                       MAX(ASOFDATE) AS ASOFDATE
                                FROM   MULTI_ACCOUNT_STATIC_HIST
                                GROUP BY
                                       ACCOUNT_NUMBER,
                                       COMPANY
                            ) MAXDATE
                            ON  STA.ACCOUNT_NUMBER = MAXDATE.ACCOUNT_NUMBER
                            AND STA.COMPANY = MAXDATE.COMPANY
                            AND STA.ASOFDATE = MAXDATE.ASOFDATE
            ) CUSINFO
            ON  BAL.ACCOUNT_NUMBER = CUSINFO.ACCOUNT_NUMBER
            AND BAL.COMPANY = CUSINFO.COMPANY
       LEFT JOIN FBNK_CUSTOMER CUS
            ON  CUSINFO.CUSTOMER = CUS.ID
       LEFT JOIN DATA_MART_BR.DBO.F_BOCSG_CUSTYPE_PARAM CUSTYPE
            ON  CUS.SECTOR = CUSTYPE.T24_SECTOR_CODE
            AND CUSTYPE.ID = 'US'
       */











IF OBJECT_ID('TEMPDB..#AVGMB') IS NOT NULL
    DROP TABLE #AVGMB
       
IF OBJECT_ID('TEMPDB..#AVGMG') IS NOT NULL
    DROP TABLE #AVGMG   
    
        
IF OBJECT_ID('TEMPDB..#AVGBAL') IS NOT NULL
    DROP TABLE #AVGBAL  
        

       /** 
       * #AVGMB
       **/
SELECT * INTO #AVGMB
FROM   (
           SELECT ID,
                  CUSTOMER_ID,
                  CO_CODE,
                  CURRENCY,
                  PRINCIPAL,
                  ASOFDATE
           FROM   MULTI_MM_HIST
           WHERE  ASOFDATE BETWEEN '20091231' AND '20100630'
       )
       AC

ORDER BY
       AC.ASOFDATE
          
          
            /**
       * #AVGMG
       **/  
SELECT DISTINCT BAL.*,
       AC.MINDATE,
       AC.MAXDATE INTO #AVGMG
FROM   #AVGMB BAL
       LEFT JOIN (
                SELECT ID,
                CO_CODE,
                       MIN(ASOFDATE) AS MINDATE,
                       MAX(ASOFDATE) AS MAXDATE
                FROM   #AVGMB AC
                GROUP BY
                       ID,CO_CODE
            ) AC
            ON  BAL.ID = AC.ID AND BAL.CO_CODE=AC.CO_CODE
ORDER BY
       BAL.ASOFDATE

DROP TABLE #AVGMB 
 
       /**
       * #AVGBAL
       **/
SELECT DISTINCT ID,
       CURRENCY,
       (
           SELECT TOP 1 PRINCIPAL
           FROM   #AVGMG MA
           WHERE  D.ID >= MA.ASOFDATE
                  AND MA.ID = AC.ID AND MA.CO_CODE = AC.CO_CODE
           ORDER BY
                  MA.ASOFDATE DESC
       ) AS PRINCIPAL,
       CO_CODE,
       D.ID INTO #AVGBAL
FROM   #AVGMG AC
       LEFT JOIN DATA_MART_BR.DBO.CALDATES D
            ON  D.ID BETWEEN MINDATE AND MAXDATE
WHERE  ID BETWEEN '20100101' AND '20100630'
ORDER BY
       ID 
       
          
    DROP TABLE #AVGMG   
    
SELECT *
FROM   #AVGBAL











SELECT LEFT(ID,6),COUNT(*) FROM DATA_MART_BR.dbo.CALDATES
GROUP BY LEFT(ID,6)
ORDER BY LEFT(ID,6)

SELECT LEFT(ID,4),COUNT(*) FROM  DATA_MART_BR.dbo.CALDATES
GROUP BY LEFT(ID,4)
ORDER BY LEFT(ID,4)


A table contains MM information hist

ID,CUSTOMER_ID,CURRENCY,PRINCIPAL,CO_CODE,ASOFDATE

