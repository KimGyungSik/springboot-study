## SpringBoot 

### 출처 : 스프링 부트의 정석 https://fastcampus.co.kr/pages/35899

* ## Filter와 Intercepter의 차이
* <img src="https://github.com/user-attachments/assets/5e3060b4-0f48-47a1-b0f1-dd668b07d27e" width="500">
* <img src="https://github.com/user-attachments/assets/d2c10ce0-c97d-4e13-908c-31e2f4496d52" width="500">
* ### 인터셉터는 Bean들을 사용할 수 있음 (가장 큰 차이)
* ### Filter 전처리 -> Intercepter 전처리 -> Intercepter 후처리 -> Filter 후처리

* ## Redirect와 Forward의 차이
* ### redirect 요청 2번, 응답 2번
  * <img src="" width="500">
* ### forward 요청 1번, 응답 1번
  * <img src="" width="500">
  * ### forward의 예시
  * <img src="" width="500">

* ## HTTP 요청과 요청 방법
  * ### URL 직접입력 (GET)
  * ### 링크 <a> 로 요청 (GET)
  * ### 폼<form>으로 요청 (GET,POST)
  * ### redirect -다른 URL로 이동 (GET), 자동재요청, 브라우저 URL 변경됨
  * ### forward - 요청(GET,POST)을 다른 URL로 전달, 브라우저 URL 변경 X