SELECT 'Hello Local, Docker' FROM DUAL;

-- ������ �н����� �����ϴ� ���
CREATE USER puser1 IDENTIFIED BY puser1;

-- ������ ������ ���� �ο�
GRANT RESOURCE, CONNECT TO puser1;
GRANT INSERT ANY TABLE, UPDATE ANY TABLE
	, DELETE ANY TABLE, CREATE VIEW
	TO puser1;
	
-- ���� ��� Ȯ��
SELECT USERNAME FROM ALL_USERS;

-- ������ �ο��� ���� Ȯ�� (������ �������� Ȯ�ιٶ�)
SELECT PRIVILEGE FROM DBA_SYS_PRIVS WHERE GRANTEE = 'PUSER1';

-- ������ �ο��� ��(Role) Ȯ�� (������ �������� Ȯ�ιٶ�)
SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = 'PUSER1';

-- ���̺� �����̽� ��� ���� �ο�
ALTER USER puser1 quota 10M ON USERS;
COMMIT;