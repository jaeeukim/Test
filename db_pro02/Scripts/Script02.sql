
/*
 * 지출내역서(가계부를 위한 테이블 만들기)
 * 		- 테이블 이름은 지출내역서_T라고 한다.
 * 		- 날짜, 입금액, 출금액, 비고를 저장할 수 있는 컬럼 필요하다.
 * 		- 비고의 경우 한글 150자까지 저장 가능 
 */
CREATE TABLE 지출내역서_T(
 	  날짜    DATE
	, 입금액   NUMBER
	, 출금액   NUMBER
	, 비고    VARCHAR2(150 CHAR)
);

SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = '지출내역서_T';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '지출내역서_T';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = '지출내역서_T';

/*
 * 위에서 만든 지출내역서에 다음의 컬럼 정보를 추가 및 수정
 * 		- 행 데이터를 식별하기 위한 식별자용 컬럼을 추가 이 컬럼 이름은 ACCOUNT_ID
 * 		- 비고의 데이터 저장 크기를 기존보다 2배 늘린다.
 * 		- 지출 내역 항목을 구분하기 위한 ACCOUNT_TYPE 컬럼 추가 해당 컬럼은 FK
 * 		- ACCOUNT_TYPE컬럼이 참조하는 테이블은 "지출내역구분_T"라는 테이블로 만든다
 * 		- 지출내역구분 테이블에는 식별자용 컬럼, 구분명 컬럼이 있어야한다.
 * 
 */


ALTER TABLE 지출내역서_T ADD ACCOUNT_ID NUMBER CONSTRAINT PK_지출내역서_T_ACCOUNT_ID PRIMARY KEY;
ALTER TABLE 지출내역서_T MODIFY 비고 VARCHAR2(300 CHAR);

CREATE TABLE 지출내역구분_T(
	  구분ID  NUMBER CONSTRAINT PK_지출내역구분_구분ID   PRIMARY KEY
	, 구분명  VARCHAR2(100) 
);

ALTER TABLE 지출내역서_T ADD ACCOUNT_TYPE NUMBER CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE REFERENCES 지출내역구분_T(구분ID);

SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME LIKE '지출내역%';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME LIKE '지출내역%';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME LIKE '지출내역%';





INSERT INTO 지출내역구분_T VALUES(1, '은행예금');
INSERT INTO 지출내역구분_T VALUES(2, '은행적금');
INSERT INTO 지출내역구분_T(구분ID, 구분명) VALUES(3, '교통비');
INSERT INTO 지출내역구분_T(구분명, 구분ID) VALUES('통신비', 4);


CREATE TABLE 지출내역구분_COPY(
	  구분ID  NUMBER CONSTRAINT PK_지출내역구분_COPY_구분ID   PRIMARY KEY
	, 구분명  VARCHAR2(100) 
);

-- 서브쿼리 사용해서 INSERT하기
INSERT INTO 지출내역구분_COPY (
	SELECT 구분ID, 구분명
		FROM 지출내역구분_T 
);
COMMIT;

SELECT * FROM 지출내역구분_COPY;
DELETE FROM 지출내역구분_COPY;
SELECT * FROM 지출내역구분_COPY WHERE 구분ID IN (2, 3);
DELETE FROM 지출내역구분_COPY WHERE 구분ID IN (2, 3);

SELECT * FROM 지출내역구분_COPY WHERE 1 = 0; -- 아무내용 안나오게 1 = 1모든내용 보게?

ROLLBACK; --트랜잭션

CREATE TABLE 지출내역구분_COPY2
	AS SELECT 구분명 FROM 지출내역구분_T 
		WHERE 1 = 0;
	
INSERT ALL
	INTO 지출내역구분_COPY VALUES(idx, name)
	INTO 지출내역구분_COPY2 VALUES(name)
SELECT 구분ID AS idx, 구분명 AS name
 FROM 지출내역구분_T;
 
SELECT * FROM 지출내역구분_COPY;
SELECT * FROM 지출내역구분_COPY2;
DELETE FROM 지출내역구분_COPY;
DELETE FROM 지출내역구분_COPY2;


/*
 * COMMISSION_PCT 유무에 따라 
 * 있는경우 EMP_COMMISSION테이블에 사번, 이름, 급여, 커미션 PCT정보를 저장
 * 없는경우 EMP_NO_COMMISSION테이블에 사번, 이름, 급여 정보를 저장
 */
SELECT * FROM EMPLOYEES;

CREATE TABLE EMP_COMMISSION(
	EMPLOYEE_ID NUMBER
	, FIRST_NAME VARCHAR2(100)
	, SALARY NUMBER
	, COMMISSION_PCT NUMBER
);

--CREATE TABLE EMP_COMMISSION 
--	AS SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT
--		FROM EMPLOYEESS
--		WHERE COMMISSION_PCT IS 1 = 0;
--
--INSERT ALL
--  INTO EMP_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT)
--SELECT EMPLOYEE_ID, FIRST_NAME, LAST_NAME, SALARY, COMMISSION_PCT
--  FROM EMPLOYEES
-- WHERE COMMISSION_PCT IS NOT NULL;


CREATE TABLE EMP_NO_COMMISSION(
	EMPLOYEE_ID NUMBER
	, FIRST_NAME VARCHAR2(100)
	, SALARY NUMBER
);

INSERT ALL
WHEN COMMISSION_PCT IS NOT NULL THEN
	INTO EMP_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, SALARY, COMMISSION_PCT)
WHEN COMMISSION_PCT IS NULL THEN
	INTO EMP_NO_COMMISSION VALUES(EMPLOYEE_ID, FIRST_NAME, SALARY)
SELECT EMPLOYEE_ID, FIRST_NAME, SALARY, COMMISSION_PCT
	FROM EMPLOYEES;

SELECT * FROM EMP_COMMISSION;
SELECT * FROM EMP_NO_COMMISSION;

DROP TABLE EMP_COMMISSION;
DROP TABLE EMP_NO_COMMISSION;


UPDATE EMP_COMMISSION 
	SET COMMISSION_PCT = 0.5
 WHERE EMPLOYEE_ID <= 149;
ROLLBACK;

UPDATE EMP_COMMISSION 	
	SET SALARY = (SELECT SALARY FROM EMP_COMMISSION WHERE EMPLOYEE_ID = 145)
	, COMMISSION_PCT = (SELECT COMMISSION_PCT FROM EMP_COMMISSION WHERE EMPLOYEE_ID = 145)
 WHERE EMPLOYEE_ID = 147;
-- 위와 아래는 동일하다.
UPDATE EMP_COMMISSION 	
	SET (SALARY, COMMISSION_PCT) = (SELECT SALARY, COMMISSION_PCT FROM EMP_COMMISSION WHERE EMPLOYEE_ID = 145)
WHERE EMPLOYEE_ID = 147;


SELECT * FROM 지출내역서_T;
SELECT * FROM 지출내역구분_T;
INSERT INTO 지출내역서_T VALUES(SYSDATE, 1000, 0, 'Test', 1, 2);
DELETE FROM 지출내역구분_T WHERE 구분ID = 2;
INSERT INTO 지출내역구분_T VALUES(2, '은행적금');

COMMIT;
-- FOREIGN KEY 제약조건으로 컬럼 삭제가 불가한경우 비활성화 > 다시 활성화
-- 웬만하면 하지말기.....
ALTER TABLE 지출내역서_T DISABLE CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE CASCADE;
ALTER TABLE 지출내역서_T ENABLE CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE;

ALTER TABLE 지출내역서_T DROP CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE;
ALTER TABLE 지출내역서_T ADD CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE
							FOREIGN KEY(ACCOUNT_TYPE) REFERENCES 지출내역구분_T(구분ID) ON DELETE CASCADE;
ALTER TABLE 지출내역서_T ADD CONSTRAINT FK_지출내역서_T_ACCOUNT_TYPE
							FOREIGN KEY(ACCOUNT_TYPE) REFERENCES 지출내역구분_T(구분ID) ON DELETE SET NULL;


SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = '지출내역서_T';

SELECT * FROM 지출내역서_T;
DELETE FROM 지출내역서_T; -- ROLLBACK 가능
TRUNCATE TABLE 지출내역서_T; -- ROLLBACK 안됨
COMMIT;
ROLLBACK;








