
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
