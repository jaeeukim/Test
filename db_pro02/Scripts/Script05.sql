-- 결과에서 왼쪽이 EMPLOYEES, 오른쪽이 DEPARTMENTS
SELECT * 
	FROM EMPLOYEES, DEPARTMENTS;
-- 컬럼명이 같기 때문에 WHERE절은 안써도 괜찮음
-- WHERE EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID;

-- JOIN..USING..
-- 결합조건 컬럼이 맨앞으로 나옴 / 결합조건이 한번만나옴
SELECT * 
	FROM EMPLOYEES
	JOIN DEPARTMENTS
   USING(DEPARTMENT_ID);
  
-- JOIN..ON.. 
-- WHERE절과 방식같음 / 결합조건 2번나옴
  SELECT * 
	FROM EMPLOYEES
	JOIN DEPARTMENTS
 	  ON (EMPLOYEES.DEPARTMENT_ID = DEPARTMENTS.DEPARTMENT_ID); 

 	

-- OUTER JOIN (LEFT, RIGHT, FULL)
-- LEFT JOIN
-- 왼쪽 테이블을 기준으로 없는 애들을 포함시킨다
SELECT * 
	FROM EMPLOYEES
   LEFT JOIN DEPARTMENTS
   USING(DEPARTMENT_ID)
 WHERE EMPLOYEE_ID = 178; 	 

-- RIGHT JOIN
-- 오른쪽 테이블을 기준으로 포함 안된 컬럼을 포함시킨다(그럼 왼쪽애들은 NULL로밖에 안나온다~)
SELECT * 
	FROM EMPLOYEES
   RIGHT JOIN DEPARTMENTS
   USING(DEPARTMENT_ID); 	 
 	 
 	 
-- FULL JOIN 
-- 왼쪽 오른쪽 모두 기준으로 컬럼들 출력 (왼쪽에도 NULL있는 열이 있고, 오른쪽에 NULL있는것도 있다)
SELECT * 
	FROM EMPLOYEES
   FULL JOIN DEPARTMENTS
   USING(DEPARTMENT_ID);  
   
-- 3개의 테이블 결합도 가	
SELECT * 
	FROM EMPLOYEES E -- 테이블 별칭부여
	JOIN DEPARTMENTS D 
	  ON E.DEPARTMENT_ID = D.DEPARTMENT_ID
	JOIN JOBS J 
	  ON E.JOB_ID  = J.JOB_ID 
	WHERE E.COMMISSION_PCT IS NOT NULL
   	  AND D.DEPARTMENT_ID = 80;  
   	 
--상(호연)관 서브쿼리 //노트에쓰기
--(EXIST 존재 유무를 확인)
--FROM EMPLOYEE M에서 M은 테이블의 별칭
   	 
   	 
-- E의 한행을 D의 모든행과 결합하는 모든조합   	 
SELECT COUNT(*) 
	FROM EMPLOYEES E
 CROSS JOIN DEPARTMENTS D;  

-- COUNT하면 107과 27나옴
SELECT X, Y
	, (X * Y)
	FROM(SELECT (SELECT COUNT(*) FROM EMPLOYEES) AS X
	  	,(SELECT COUNT(*) FROM DEPARTMENTS) AS Y
	   FROM DUAL);

-- NON_EQUAL JOIN
-- 값이 같지 않아도 범위에 해당되면 출력됨
SELECT E.EMPLOYEE_ID 
	, E.FIRST_NAME || ' ' || E.LAST_NAME 
	, E.JOB_ID 
	, J.JOB_ID 
	, J.JOB_TITLE
	, E.SALARY 
	, J.MIN_SALARY
	, J.MAX_SALARY
	FROM EMPLOYEES E
	JOIN JOBS J
		ON E.SALARY BETWEEN J.MIN_SALARY AND J.MAX_SALARY
		   AND E.JOB_ID  = J.JOB_ID 
	ORDER BY 1;

-- SELF JOIN	  
-- 자신과 같은 테이블 2개로 비교하기
SELECT E1.EMPLOYEE_ID
	 , E1.FIRST_NAME || ' ' || E1.LAST_NAME 
	 , E2.EMPLOYEE_ID 
	 , E2.FIRST_NAME || ' ' || E2.LAST_NAME
	 , E2.MANAGER_ID 
	FROM EMPLOYEES E1
	JOIN EMPLOYEES E2
	  ON E1.EMPLOYEE_ID = E2.MANAGER_ID;
	  
	  
	  
	  
	  
	  
	  
	  
	  