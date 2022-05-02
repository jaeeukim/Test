/*
 * 세달치 분량의 지출내역을 지출내역서_T와 지출내역구분_T 에 만들어본다.
 * 		- 실제 체크카드 내역 및 신용카드 내역을 확인해서 데이터 추가 작업을 진행.
 * 		- 구분은 교통비, 식비, 편의점, 등의 사용 정보를 확인하여 5 ~ 10개 정도의 구분을 생성.
 * 		- 지출내역서는 매달 사용한 내역을 기준으로 10 ~ 15개 정도 
 */


SELECT * FROM 지출내역구분_T;
DELETE FROM 지출내역구분_T;
COMMIT;
INSERT INTO 지출내역구분_T VALUES(1, '용돈');
INSERT INTO 지출내역구분_T VALUES(2, '급여');
INSERT INTO 지출내역구분_T VALUES(3, '교통비');
INSERT INTO 지출내역구분_T VALUES(4, '식비');
INSERT INTO 지출내역구분_T VALUES(5, '월세');
INSERT INTO 지출내역구분_T VALUES(6, '전기세');
INSERT INTO 지출내역구분_T VALUES(7, '수도세');
INSERT INTO 지출내역구분_T VALUES(8, '차량유지비');
INSERT INTO 지출내역구분_T VALUES(9, '유류비');

SELECT * FROM 지출내역서_T;
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(1, 1, TO_DATE(20220405), 500000, 0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(2, 3, TO_DATE(20220405), 0, 50000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(3, 4, TO_DATE(20220410), 0, 15000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(4, 5, TO_DATE(20220412), 0, 350000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(5, 8, TO_DATE(20220423), 0, 100000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(6, 9, TO_DATE(20220427), 0, 100000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(7, 1, TO_DATE(20220505), 500000, 0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(8, 3, TO_DATE(20220510), 0, 50000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(9, 6, TO_DATE(20220516), 0, 34000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(10, 7, TO_DATE(20220520), 0, 10000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(11, 9, TO_DATE(20220530), 0, 20000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(12, 1, TO_DATE(20220605), 50000, 0, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(13, 5, TO_DATE(20220613), 0, 40000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(14, 4, TO_DATE(20220620), 0, 100000, '');
INSERT INTO 지출내역서_T(ACCOUNT_ID, ACCOUNT_TYPE, 날짜, 입금액, 출금액, 비고)
	VALUES(15, 4, TO_DATE(20220621), 0, 80000, '');

SELECT * FROM 지출내역서_T;
COMMIT;

UPDATE EMP_COMMISSION 
	SET COMMISSION_PCT = 0.5
 WHERE EMPLOYEE_ID <= 149;
ROLLBACK;

UPDATE 지출내역서_T  
	SET 입금액 = 500000
	WHERE ACCOUNT_ID = 7;
/*
 * 위의 작업을 완료한 후에는 다음의 작업을 진행한다.
 * 		- 지출내역을 토대로 매달 결산을 위한 테이블 생성 (지출결산_T)
 * 		- 지출결산_T에는 년, 월, 입금액, 출금액, 비고 컬럼 사용.
 */


CREATE TABLE 지출결산_T
	AS SELECT EXTRACT(YEAR FROM 날짜) AS 년, EXTRACT(MONTH FROM 날짜) AS 월, 입금액, 출금액, 비고
		FROM 지출내역서_T
	WHERE 1 = 0;
SELECT * FROM 지출결산_T; 
DROP TABLE 지출결산_T;

INSERT ALL INTO 지출결산_T VALUES(년, 월, 입금액, 출금액, 비고)
SELECT EXTRACT(YEAR FROM 날짜) AS 년
	, EXTRACT (MONTH FROM 날짜) AS 월
	, SUM(입금액) AS 입금액
	, SUM(출금액) AS 출금액
	, CASE WHEN SUM(입금액) < SUM(출금액) THEN '너무 많이 소비 했다.'
		   WHEN SUM(입금액) > SUM(출금액) THEN '절약을 했다.'
		   ELSE '적절한 소비'
		   END AS 비고
	FROM 지출내역서_T
GROUP BY EXTRACT(YEAR FROM 날짜), EXTRACT(MONTH FROM 날짜)
ORDER BY 년, 월;

ROLLBACK;
COMMIT;


/*
 * 2022년부터 월세가 10% 인상 된것을 깜빡하고 반영하지 않았다.
 * 이를 UPDATE를 사용해서 10% 인상된 월세로 수정하고
 * 결산테이블에도 수정하도록한다.
 */

UPDATE 지출내역서_T 
	SET 출금액 = 출금액 * 1.1
WHERE ACCOUNT_TYPE = (SELECT 구분ID FROM 지출내역구분_T WHERE 구분명 ='월세');

SELECT * FROM 지출내역서_T WHERE ACCOUNT_TYPE = (SELECT 구분ID FROM 지출내역구분_T WHERE 구분명 ='월세');

UPDATE 지출결산_T
	SET (입금액, 출금액) = (SELECT SUM(입금액) AS 입금액, SUM(출금액) AS 출금액
						FROM 지출내역서_T
						WHERE EXTRACT(YEAR FROM 날짜) = 2022
						   AND EXTRACT(MONTH FROM 날짜) = 6
						GROUP BY EXTRACT(YEAR FROM 날짜), EXTRACT(MONTH FROM 날짜))
 WHERE 년 = 2022
   AND 월 = 6;

COMMIT;
ROLLBACK;

SELECT * FROM 지출내역서_T;
SELECT * FROM 지출결산_T;
SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = '지출결산_T';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = '지출결산_T';