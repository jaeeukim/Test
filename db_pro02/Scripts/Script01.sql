/*
 * DDL
 *     - Data Definition Language : 데이터 정의어
 *     - 객체를 정의하기 위한 CREATE(생성), ALTER(수정), DROP(삭제) 구문이 있다.
 *     - 오라클 객체는 TABLE, VIEW, SEQUENCE, FUNCTION, PROCEDUAL, USER, .... 등이 있다.
 */

/*
 * CREATE TABLE 테이블명 (
 *     컬럼명 자료형(크기) [제약조건]
 *   , ...
 * );
 */

CREATE TABLE sample_t (
       u_id      NUMBER       -- PRIMARY KEY
     , jumin     CHAR(13)     -- UNIQUE
     , name      VARCHAR2(50) NOT NULL
     , age       NUMBER(3)    DEFAULT(0)
     , gender    CHAR(1)      CHECK(GENDER IN ('M', 'F'))
     , birth_day DATE         DEFAULT(SYSDATE)
     , ref_col   NUMBER       -- REFERENCES ref_t(r_id)
     , CONSTRAINT PK_U_ID PRIMARY KEY(u_id)
     , CONSTRAINT UK_JUMIN UNIQUE(jumin)
     , CONSTRAINT FK_REF_T_R_ID FOREIGN KEY(ref_col) REFERENCES ref_t(r_id)
);
CREATE TABLE ref_t (
       r_id NUMBER PRIMARY KEY
     , note VARCHAR2(100)
);
DROP TABLE sample_t;
DROP TABLE ref_t;

SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = 'SAMPLE_T';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'SAMPLE_T';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'SAMPLE_T';
-- DESC SAMPLE_T;

COMMENT ON COLUMN sample_t.name IS '이름';
COMMENT ON COLUMN sample_t.age IS '나이';
COMMENT ON COLUMN sample_t.gender IS '성별(M:남자, F:여자)';
COMMENT ON COLUMN sample_t.birth_day IS '생년월일';

SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = 'SAMPLE_T';


CREATE TABLE ALT_T(
	 u_id        NUMBER 
     , name      VARCHAR(10) 
     , g_type 	 CHAR(1)
     , now_date  DATE
     , remark	 VARCHAR(100)
);

CREATE TABLE MY_TEST_REF(
		  ref_id   NUMBER       PRIMARY KEY
		, ref_text VARCHAR(100)
);





-- TABLE 이름 바꿀수 있다!(비추천_참조할경우 에러날수도)
ALTER TABLE ALT_T RENAME TO MY_TEST;
-- 컬럼명 바꾸기(비추천)
ALTER TABLE MY_TEST RENAME COLUMN now_date TO birth_date;
-- 컬럼 추가 [제약조건]
ALTER TABLE MY_TEST ADD age NUMBER(3) DEFAULT(0) NOT NULL;
ALTER TABLE MY_TEST ADD ref_col NUMBER;
-- 컬럼 수정(크기 줄일때 주의하기_크기 늘리는 방향 추천)
ALTER TABLE MY_TEST MODIFY name VARCHAR(20);
-- 컬럼 제거 (비추천)
ALTER TABLE MY_TEST DROP COLUMN remark;

-- 제약조건 추가(컬럼 수정할때 제약조건 추가해서 수정한것)
ALTER TABLE MY_TEST MODIFY u_id PRIMARY KEY; -- 컬럼레벨
ALTER TABLE MY_TEST ADD PRIMARY KEY(u_id); 			-- 테이블레벨

ALTER TABLE MY_TEST MODIFY name UNIQUE;
ALTER TABLE MY_TEST ADD UNIQUE(name);

ALTER TABLE MY_TEST MODIFY ref_col REFERENCES MY_TEST_REF(ref_id);
ALTER TABLE MY_TEST ADD FOREIGN KEY(ref_col) REFERENCES MY_TEST_REF(ref_id);

-- NOT NULL은 컬럼레벨만 가능한
ALTER TABLE MY_TEST MODIFY g_type NOT NULL;
ALTER TABLE MY_TEST MODIFY age CHECK(AGE BETWEEN 0 AND 150);

-- 제약 제거
ALTER TABLE MY_TEST DROP CONSTRAINT SYS_C007798;

-- CASCADE사용해서 참조도 연달아 삭제하기(비추천)
ALTER TABLE MY_TEST_REF DROP COLUMN ref_id CASCADE CONSTRAINT;
DROP TABLE MY_TEST_REF CASCADE CONSTRAINT;


SELECT * FROM USER_ALL_TABLES WHERE TABLE_NAME = 'MY_TEST';
SELECT * FROM USER_TAB_COLUMNS WHERE TABLE_NAME = 'MY_TEST';
SELECT * FROM USER_CONSTRAINTS WHERE TABLE_NAME = 'MY_TEST';


