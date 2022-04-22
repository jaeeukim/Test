SELECT 'Hello Cloud' FROM DUAL;

-- 계정과 패스워드 생성하는 방법
CREATE USER puser1 IDENTIFIED BY padmin1234;

-- 생성한 계정에 권한 부여
GRANT RESOURCE, CONNECT TO puser1;
GRANT INSERT ANY TABLE, UPDATE ANY TABLE
	, DELETE ANY TABLE, CREATE VIEW
	TO puser1;
	
-- 테이블 스페이스 사용 권한 부여
ALTER USER puser1 quota 10M ON USERS;