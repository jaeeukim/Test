/*
 * PL/SQL
 * 		- 프로그래밍 기능이 추가된 SQL
 * 		- 변수 정의, 조건문, 반복문을 만들어서 SQL 문장에 대한 처리 가능
 */
-- 3개 다 쓸필요는 없음 BEGIN END는 기본적으로 사용
DECLARE
	/*
	 * 변수 선언부
	 */
BEGIN
	/*
	 * 프로그래밍 로직이 작성되는 부분
	 */
	DBMS_OUTPUT.PUT_LINE('HELLO WORLD');
EXCEPTION
	/*
	 * 예외처리 부분
	 */
END;

