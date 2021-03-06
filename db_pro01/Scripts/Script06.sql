
-- NULL에 대한 연산은 NULL이 나옴
-- NVL NULL이면 0으로 계산하라
SELECT 10 * NVL(NULL, 0) AS COL1
	FROM DUAL;
	
-- DECODE는 특정 값 지정하는것 (SWITCH문)
SELECT COUNTRY_ID
	, COUNTRY_NAME
	, DECODE(COUNTRY_ID, 'IT', '이태리', 'JP', '일본', 'US', '미국', 'CA', '캐나다', COUNTRY_NAME) AS COUNTRY_KOR
	FROM COUNTRIES;
	
-- CASE는 직접 수(범위)를 넣는것 (IF문)
SELECT EMPLOYEE_ID 
	, FIRST_NAME 
	, LAST_NAME 
	, CASE WHEN SALARY >= 1000 AND SALARY < 3000 THEN '하위소득'
		   WHEN SALARY >= 3000 AND SALARY < 6000 THEN '중위소득'
		   WHEN SALARY >= 6000 AND SALARY < 10000 THEN '고소득'
		   ELSE '미분류'
	   END AS "소득분류"
	FROM EMPLOYEES;

-- 그룹함수
-- 그룹으로 묶어 연산, 하나의 컬럼 반환 (SUM, AVG, COUNT, MAX, MIN)

SELECT SUM(SALARY) 
	FROM EMPLOYEES;

SELECT AVG(SALARY)
	FROM EMPLOYEES;

-- AVG 할때 NULL값 신경써주기
SELECT AVG(COMMISSION_PCT)
	FROM EMPLOYEES
 WHERE COMMISSION_PCT IS NOT NULL;

SELECT MIN(COMMISSION_PCT)
	, MIN(HIRE_DATE)
	, MIN(FIRST_NAME)
	FROM EMPLOYEES;

SELECT MAX(COMMISSION_PCT)
	, MAX(HIRE_DATE)
	, MAX(FIRST_NAME)
	FROM EMPLOYEES;

-- 행의 갯수 (뭘 넣어도 똑같아서 * 넣어도 됨)
SELECT COUNT(*)
	FROM EMPLOYEES;
	
