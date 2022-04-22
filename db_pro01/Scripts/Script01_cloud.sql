SELECT 'Hello Cloud' FROM DUAL;

-- ������ �н����� �����ϴ� ���
CREATE USER puser1 IDENTIFIED BY padmin1234;

-- ������ ������ ���� �ο�
GRANT RESOURCE, CONNECT TO puser1;
GRANT INSERT ANY TABLE, UPDATE ANY TABLE
	, DELETE ANY TABLE, CREATE VIEW
	TO puser1;
	
-- ���̺� �����̽� ��� ���� �ο�
ALTER USER puser1 quota 10M ON USERS;