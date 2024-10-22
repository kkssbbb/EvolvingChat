# Log4j2 스프링 스타일 로그 포맷 가이드

## 개요
이 문서는 Log4j2를 사용하여 스프링 부트의 기본 로그 포맷과 유사한 로그 출력을 구성하는 방법을 설명합니다. 각 구성 요소에 대한 상세한 설명과 사용 목적을 제공합니다.

## 로그 패턴 구성요소
1. 날짜 및 시간 (%d{yyyy-MM-dd HH:mm:ss.SSS})
- 형식: yyyy-MM-dd HH:mm:ss.SSS
- 설명: ISO 8601 형식의 날짜와 시간을 밀리초 단위까지 표시
- 예시: 2023-06-15 10:30:55.123
2. 로그 레벨 (%highlight{%-5level})
- 형식: %-5level에 하이라이트 적용
- 설명: 로그 레벨(INFO, ERROR 등)을 5자리로 맞추고 색상 강조
- 예시: "color: green;">INFO, "color: red;">ERROR
3. 프로세스 ID (%magenta{%pid})
- 형식: %pid에 자홍색 적용
- 설명: 현재 실행 중인 JVM의 프로세스 ID를 표시
- 예시: "color: magenta;">1234
4. 구분자 (---)
- 형식: 고정 문자열 ---
- 설명: 로그 항목 간 시각적 구분을 위한 구분자
5. 스레드 이름 ([%15.15t])
- 형식: %15.15t
- 설명: 현재 스레드 이름을 최대 15자로 표시 (오른쪽 정렬)
- 예시: [      main], [    Thread-1]
6. 로거 이름 (%cyan{%-40.40c{1.}})
- 형식: %-40.40c{1.}에 청록색 적용
- 설명: 로거 이름(일반적으로 클래스 이름)을 최대 40자로 제한하고 왼쪽 정렬
- 예시: "color: cyan;">com.example.MyClass
7. 로그 메시지 (: %msg%n)
- 형식: : %msg%n
- 설명: 실제 로그 메시지와 줄바꿈 문자
- 예시: : Application started successfully

## 전체 패턴

%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight{%-5level} %magenta{%pid} --- [%15.15t] %cyan{%-40.40c{1.}} : %msg%n
## 사용 예시

2023-06-15 10:30:55.123 INFO 1234 --- [           main] com.example.MyApplication               : Starting MyApplication using Java 11.0.12
2023-06-15 10:30:56.234 WARN 1234 --- [       Thread-1] com.example.Service                     : Resource usage is high
## 주의사항
- 색상 강조는 지원하는 콘솔에서만 적용됩니다.
- 파일 로깅 시에는 색상 관련 패턴을 제거하는 것이 좋습니다.