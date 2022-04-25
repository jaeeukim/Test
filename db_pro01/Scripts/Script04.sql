-- LENGTH 길이 (TABLE에서)
SELECT FIRST_NAME  
	, LENGTH(FIRST_NAME) AS "길이"
	FROM EMPLOYEES;
	
-- LENGTH 길이(임시 테이블인 DUAL 사용)
SELECT LENGTH('HELLO ORACLE')
	, LENGTH('오라클 안녕')
	FROM DUAL;

-- LENGTHB 바이트 수
SELECT LENGTHB('HELLO ORACLE')
	, LENGTHB('오라클 안녕')
	FROM DUAL;

-- INSTR @가 왼쪽(1) / 오른쪽(-1)에서 몇번 위치에 있는가_ 찾는 순서
SELECT INSTR('sample@example@com', '@', 1)
	, INSTR('sample@exmaple@com', '@', -1)
	, INSTR('sample@exmaple@com', '@', -1, 2) -- 우측기준 2번째에있는 @를 찾아라	
	FROM DUAL;
	
-- LPAD, LPAD (Left Padding, Right Padding) 공백을 왼쪽으로 / 오른쪽으로
-- LPAD(str, idx) str을 포함한 idx의 길이를 출력하고 부족한 길이만큼 공백으로
-- 그 길이를 오버하면 그냥 잘림
-- LPAD(str, idx, c) 공백대신 원하는 c값으로 채워짐
SELECT '!' || LPAD('A', 4)
	, '!' || LPAD('AB', 4)
	, '!' || LPAD('ABC', 4)
	, '!' || LPAD('ABCDE', 4)
	, '!' || LPAD('A', 4, '_')
	,RPAD('A', 4) || '!'
	,RPAD('AB', 4) || '!'
	,RPAD('ABC', 4) || '!'
	,RPAD('ABCDE', 4) || '!'
	,RPAD('A', 4, '_') || '!'
	FROM DUAL;

-- 우측에 지정된 문자열을 제거한다 (아무것도 안쓰면 공백제거)
-- 기준을 어디로 볼건지 
SELECT RTRIM('userId@example.com', '@example.com')
	, LTRIM('010-1234-5678', '010-')
	, LTRIM(RTRIM('    PASSWORD INPUT    '))
	, TRIM('     PASSWORD INPUT    ') --LTRIM과 RTRIM의 결합
	, TRIM('A' FROM 'AAAAAHelloAAAAA') --우측 문자열에서 A를 제거
	, TRIM(LEADING 'A' FROM 'AAAAHelloAAAAA') -- 앞에 제거
	, TRIM(TRAILING 'A' FROM 'AAAAHelloAAAAA') -- 뒤에 제거
	, TRIM(BOTH 'A' FROM 'AAAAHelloAAAAA') -- 양쪽
	FROM DUAL;

-- 1이랑 8은 위치/ 6이랑 7은 갯수, 갯수 지정안하면 끝까지
-- 음수사용하면 끝에거 출력
SELECT SUBSTR('userId@example.com', 1, 6)
	,  SUBSTR('userId@example.com', 8, 7)
	,  SUBSTR('userId@example.com', 8)
	,  SUBSTR('userId@example.com', -3)
	FROM DUAL;


-- 소문자, 대문자
-- INITCAP 첫번째 글자만 대문자로 나머진 소문자로
SELECT LOWER('userId@example.com')
	, UPPER('userId@example.com')
	, INITCAP('userId@example.com')
	FROM DUAL;

-- 문자열 연결(||사용해도 되지만 함수도 있다는것을 알아두자)
SELECT CONCAT('Hello', 'World')
	FROM DUAL;
	
-- REPLACE(원본, 찾을문자열, 변경할문자열)
SELECT REPLACE('userId@example.com', '@example.com', '@example.co.kr')
	FROM DUAL;
	
-- 절댓값
SELECT ABS(10)
	, ABS(-10)
	, ABS(-10.12)
	FROM DUAL;
	
-- 나머지 MOD(NUM1, NUM2) NUM1을 NUM2로 나눴을때 나머지
SELECT  MOD(10, 3)
	, MOD(-10, 3)
	, MOD(-10.5, 3)
	, MOD(10.5, 3)
	FROM DUAL;
	
-- 반올림 
SELECT ROUND(10.4)
	, ROUND(10.5)
	, ROUND(10.45) -- 정수반환
	, ROUND(10.45, 1) -- 소수점 첫번째짜리까지 표현하겠다 
	, ROUND(10.45, 2) 
	, ROUND(18.456, -1) -- 20 
	FROM DUAL;
	
-- 내림 / 올림
SELECT FLOOR(10.34)
	, FLOOR(-10.34)
	, CEIL(10.34)
	, CEIL(-10.34)
	FROM DUAL;
	
-- 원하는 소수점위치에서 절삭(버려버림)
SELECT TRUNC(10.34, 1)
	, TRUNC(10.36, 1)
	, TRUNC(10.3456, 2)
	, TRUNC(1234, -1) -- 소수점기준 왼쪽으로 갈수록 -값이 커짐
	FROM DUAL;




-- 날짜

-- ALTER SESSION SET NLS_LANGUAGE = AMERICAN; --언어설정 영어로
ALTER SESSION SET NLS_LANGUAGE = KOREAN;

-- 현재 언어가 무엇으로 설정되어있는지
SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER LIKE '%LANG%';
SELECT SYSDATE  
	, ADD_MONTHS(SYSDATE, 3) --3개월 더하기
	, ADD_MONTHS(SYSDATE, -3) --3개월 빼기
	, LAST_DAY(SYSDATE) --현재 달에 마지막 날
	-- , NEXT_DAY(SYSDATE, 'FRIDAY')
	-- , NEXT_DAY(SYSDATE, 'FRI')
	, NEXT_DAY(SYSDATE, '금요일') --언어가 영어로 되어있으면 금요일이라하면 안됨
	, NEXT_DAY(SYSDATE, '금') 
	, NEXT_DAY(SYSDATE, 6) -- 1:일 2:월 3:화...7:토
	FROM DUAL;
	
SELECT EXTRACT (YEAR FROM SYSDATE) -- 연도 분리
	, EXTRACT(MONTH FROM SYSDATE) -- 월 분리
	, EXTRACT(DAY FROM SYSTIMESTAMP) -- 날짜 분리
	, EXTRACT(HOUR FROM SYSTIMESTAMP) -- 시 분리
	, EXTRACT(MINUTE FROM SYSTIMESTAMP) -- 분 분리
	, EXTRACT(SECOND FROM SYSTIMESTAMP) -- 분 분리
	, SYSDATE
	, SYSTIMESTAMP
	FROM DUAL;

-- 월차이 계산 (앞에서 뒤에꺼 뺌)
SELECT MONTHS_BETWEEN(SYSDATE, ADD_MONTHS(SYSDATE, 3))
	, MONTHS_BETWEEN(ADD_MONTHS(SYSDATE, 3), SYSDATE) 
	FROM DUAL;

SELECT SYSDATE
	, SYSDATE + 1 -- +하루
	, SYSDATE + 2 -- +이틀
	, SYSDATE - 1
	, SYSDATE - 2
	, SYSDATE + INTERVAL '1' DAY
	, SYSDATE + INTERVAL '1' MONTH
	, SYSDATE + INTERVAL '1' YEAR
	, SYSDATE + INTERVAL '1' HOUR
	, SYSDATE + INTERVAL '1' MINUTE
	, SYSDATE + INTERVAL '1' SECOND
	FROM DUAL;

SELECT SYSTIMESTAMP 
	, SYSTIMESTAMP + INTERVAL '30' DAY -- MONTH, YEAR도 됨	
	, SYSTIMESTAMP + INTERVAL '30' SECOND
	, SYSTIMESTAMP + INTERVAL '30' MINUTE
	, SYSTIMESTAMP + INTERVAL '3' HOUR
	, SYSTIMESTAMP - INTERVAL '30' SECOND
	, SYSTIMESTAMP - INTERVAL '30' MINUTE
	, SYSTIMESTAMP - INTERVAL '3' HOUR
	FROM DUAL;


-- 통화 단위의 심볼 변경이 필요한 경우 다음의 ALTER 문을 사용한다.
-- L입력했을때 원하는 값(원화나 달러 등) 나오게 설정
ALTER SESSION SET NLS_CURRENCY = '$'; 
SELECT * FROM V$NLS_PARAMETERS WHERE PARAMETER = 'NLS_CURRENCY'



-- 숫자를 문자열로 변환
SELECT 1234 
	, TO_CHAR(1234)
	, TO_CHAR(SYSDATE, 'YYYYMMDD')
	, TO_CHAR(SYSDATE, 'YYYY/MM/DD') 
	, TO_CHAR(SYSDATE, 'YYYY-MM-DD') 
	, TO_CHAR(SYSDATE, 'YYYY"년" MM"월" DD"일"') 
	, TO_CHAR(SYSDATE, 'YYYY-MM-DD AM HH:MI:SS')  -- 시:분:초
	, TO_CHAR(SYSDATE, 'YYYY-MM-DD HH24:MI:SS')  -- 24시간기준 시:분:초
	, TO_CHAR(SYSTIMESTAMP , 'YYYY-MM-DD AM HH:MI:SS.FF3')  -- ms 3자리 출력
	, TO_CHAR(1000000, '999,999,999') -- 콤마까지 포함해서 출력
	, TO_CHAR(1000000, '000,000,000') -- 여백이 0으로 채워짐
	, TO_CHAR(1000000, '999,999,999L') -- 원화 표시 출력
	FROM DUAL;
	

-- 날짜로 변환
SELECT TO_DATE('20220425', 'YYYYMMDD')
	, TO_DATE(20220425) -- 문자열이 아닌 숫자도 변환시켜줌
	, TO_DATE('20220425') -- 대표적인 방식이라 생략해도 ㄱㅊ
	, TO_DATE('2022/04/25')  
	, TO_DATE('2022-04-25')  
	, TO_DATE('2022.04.25')  
	, TO_DATE('2022년 04월 25일','YYYY"년" MM"월" DD"일"')
	FROM DUAL;

-- 숫자로 변환
SELECT TO_NUMBER('20220425') 
	, TO_NUMBER('20,220,425', '999,999,999') -- 형식 지정
	, TO_NUMBER('FFFF', 'XXXX') -- 16진수를 정수로 변환
	FROM DUAL;

-- 날짜를 숫자로 변환하기(문자열로 변환하고 다시 숫자로변환)
SELECT TO_NUMBER(TO_CHAR(SYSDATE, 'YYYYMMDD'))
	FROM DUAL;