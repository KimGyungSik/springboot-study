## SpringBoot 

### 출처 : 스프링 부트의 정석 https://fastcampus.co.kr/pages/35899

* ## Filter와 Intercepter의 차이
* <img src="https://github.com/user-attachments/assets/5e3060b4-0f48-47a1-b0f1-dd668b07d27e" width="500">
* <img src="https://github.com/user-attachments/assets/d2c10ce0-c97d-4e13-908c-31e2f4496d52" width="500">
* ### 인터셉터는 Bean들을 사용할 수 있음 (가장 큰 차이)
* ### Filter 전처리 -> Intercepter 전처리 -> Intercepter 후처리 -> Filter 후처리

* ## Redirect와 Forward의 차이
* ### redirect 요청 2번, 응답 2번
  * <img src="https://github.com/user-attachments/assets/859534f1-9157-4392-9955-7d058ddfbdce" width="500">

* ### forward 요청 1번, 응답 1번
  * <img src="https://github.com/user-attachments/assets/87b89ae2-5cd5-46e3-a961-109d6a39f0bb" width="500">

  * ### forward의 예시
  * <img src="https://github.com/user-attachments/assets/766b29b4-14d9-4caa-a4fb-c060598c7fe6" width="500">


* ## HTTP 요청과 요청 방법
  * ### URL 직접입력 (GET)
  * ### 링크 <a> 로 요청 (GET)
  * ### 폼<form>으로 요청 (GET,POST)
  * ### redirect -다른 URL로 이동 (GET), 자동재요청, 브라우저 URL 변경됨
  * ### forward - 요청(GET,POST)을 다른 URL로 전달, 브라우저 URL 변경 X

* ## RedirectAttributes -> Model의 자손
  * ### redirect 요청 시 사용
  * ### addAttribute : QueryString에 붙음 
  * ### FlashAttribute : 세션객체에 저장, 일회성
  * ```java
     @PostMapping("/login")
         public String login(String id, String pwd, RedirectAttributes model) throws Exception{
                 // 1. id, pwd를 확인
                    if(loginCheck(id,pwd)) {
                 // 2. 일치하면, userInfo.html
                         model.addAttribute("id", id);
                         model.addAttribute("pwd", pwd);
                         return "userInfo" ;  // userInfo.html
                   } else {
                 //    일치하지 않으면, login.html로 이동
                 //           String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
                              String msg = "id 또는 pwd가 일치하지 않습니다.";
                              model.addAttribute("msg",msg);  -> 2번쨰인 자동 요청시 QueryString에 붙어짐
                              model.addFlashAttribute("msg","일회용 메세지"); -> 2번째인 자동 요청시 세션객체에 저장됨/한번 전달되고 지워짐
                              req.setAttribute("msg","request에 저장된 msg"); -> request객체에 저장되는 것, redirect시 저장안됨
                              return "forward:/";
                 //           return "redirect:/login/login?msg="+msg;  // GET - URL재작성
         }
     }
    

* ## Thymeleaf란?
  * <img src="https://github.com/user-attachments/assets/c4f8be83-f1dd-4842-9294-1474fd2cf006" width="500">

  * <img src="https://github.com/user-attachments/assets/c7116cbe-580c-4fd3-8c25-ed8102f6367d" width="500">

  * <img src="https://github.com/user-attachments/assets/2a47df43-7a26-4029-b661-eee534b8a931" width="500">

  * <img src="https://github.com/user-attachments/assets/936e5609-589d-4bcf-8296-8c882e598a7d" width="500">

  * <img src="https://github.com/user-attachments/assets/55a8c067-5625-4ba4-934e-a7abfe3db4f3" width="500">
 
  * <img src="https://github.com/user-attachments/assets/6653060e-f6c1-4f2a-858f-3f897c9539ca" width="500">


  * <img src="https://github.com/user-attachments/assets/66621453-8231-4580-9a14-83cbec568d2e" width="500">

  * <img src="https://github.com/user-attachments/assets/b4579c53-823b-4fff-88bb-56abc4abdc9a" width="500">

  * <img src="https://github.com/user-attachments/assets/e6fc59c6-604f-42a0-8226-8b775ed4bbcc" width="500">

  * <img src="https://github.com/user-attachments/assets/6786de3a-8926-4599-9f33-80f7ea65e7c6" width="500">


* ## Thymeleaf 메서드들  
  * ### https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#dates
* ## 기본객체 접근법 (저장 : setAttribute(), 읽기 : getAttribute())
  * <img src="https://github.com/user-attachments/assets/2371f267-a05b-4bb8-9db8-d40d67f84417" width="500">
---

* ## 사용과 생성을 분리하는 이유  
  * ### 사용 x n , 생성 x 1 
  * ### 변경부분은 생성하는 쪽에서만 발생하므로 변경사항이 1개
  * ### 생성하는 쪽에서 new연산자로 객체생성을 하기보다 Reflection API를 활용해 사용시 동적으로 생성 -> '변경에 유리'
  * ### new 연산자로 객체생성 : 컴파일 시점에 결정
  * ### Reflection API 사용 : 런타임 시점에 결정 
* ## Java Reflection API란?  
  * * <img src="https://github.com/user-attachments/assets/801576d3-98c1-4bf3-bebc-59e336502964" width="500">

* ## Class 객체란?
  * * <img src="https://github.com/user-attachments/assets/0d498393-1105-48ba-bdd3-448ad0ec1824" width="500">


* ## 스프링 애너테이션 - @ComponentScan과 @Component
