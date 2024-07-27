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
  * ### Spring MVC는 .xml 설정파일을 Bean을 등록했는데 Spring Boot에서는 자바설정파일(에너테이션 활용)에 Bean을 등록  
  * <img src="https://github.com/user-attachments/assets/615a4964-acd9-4e7d-add3-3ccf29aa42c6" width="500">

  * <img src="https://github.com/user-attachments/assets/27499bf0-590f-47d0-93fb-ec1d3b0bd811" width="500">



* ## 의존성 관리와 설정의 자동화 (Spring Framework와 Spring Boot의 가장 큰 차이점)
  * <img src="https://github.com/user-attachments/assets/b093b837-6ade-4199-b35b-659dca0d185a" width="500">

  * <img src="https://github.com/user-attachments/assets/901b907d-d6e0-4028-8ad2-a790f26a4721" width="500">

  * <img src="https://github.com/user-attachments/assets/b9e9f44d-85c4-4c18-a6bc-1f29c1d3a448" width="500">

  * <img src="https://github.com/user-attachments/assets/547227fb-1e3d-496c-8170-303b65a0e75d" width="500">

  * <img src="https://github.com/user-attachments/assets/04c36ea4-d38b-4be7-b6ee-c4a26d665eb8" width="500">

  * <img src="https://github.com/user-attachments/assets/35a3eed1-e8fa-4a96-99da-9c9fad83cec0" width="500">

  
  
  * ### Condition과 @Conditional : 개별 빈을 동적으로 조건부 등록
    * <img src="https://github.com/user-attachments/assets/594b6b80-9197-4c96-b566-403d681d32ea" width="500">
  * ### @Import와 ImportSelector : 그룹 빈을 동적으로 조건부 등록
    * <img src="https://github.com/user-attachments/assets/222d9d58-d832-458d-9fce-a3174e68f1e7" width="500">
  * ### 외부 설정 파일 - application.properties
    * <img src="" width="500">
    * ### # 빈등록시 중복된 이름으로 빈등록할 때 덮어쓰기 허용 (@Configuration으로 @Bean 자동 등록시 이름 충돌)
           spring.main.allow-bean-definition-overriding=true
    * <img src="" width="500">

  
* ## 7/27 공부하다 생긴 의문점들
  * ## @Component와 @Bean의 차이
    * ### @Component는 클래스 레벨에서 붙어지는 에너테이션
    * ### @Bean은 메서드 레벨에서 붙어지는 에터테이션 (@Configuration이 붙은 클래스 안에서 정의)
    * ### 둘다 스프링 빈으로 등록된다는 공통점이 있음 
  * ## @ComponentScan과 @Configuration을 같이 쓰는 이유?
    * ### @Configuration는 내부에 @Component가 있어서 해당 설정이 자동으로 스프링 빈으로 등록 -> 빈으로 등록되어야 @ComponentScan도 동작
    * ### @Configuration : 스프링의 빈으로 등록되면서 각종 자바코드 구성정보(@Bean메서드)와 부가 에너테이션을 스프링 컨테이너가 인식할 수 있게 해줌
    * ### @ComponentScan : @Component 에너테이션이 붙은 클래스를 자동으로 빈 등록
    * ### @ComponentScan은 @Configuration이 붙은 클래스에 사용해야 스프링 컨테이너가 인식할 수 있다고 생각
    * ### @ComponentScan이 붙은 @Configuration클래스도 @ComponentScan이 동작해서 빈으로 등록이 되는데, @Configuration 클래스가 빈으로 등록이 안되면, @ComponentScan을 스프링이 인식을 못함
  * ## static변수에 왜 @Autowired를 붙이지 못하나?
    * ### 정적 필드가 클래스 로더에 의해 인스턴스화 될 때 아직 Spring Context는 로드되지 않았기 때문에 
    * ### 해결방법 2가지
      * ### @PostConstruct 사용
        * ```java
              @Autowired
               private FooObject nonStaticObj;

               private static FooObject staticObj;

               @PostConstruct
               private void initStatic() {
                   staticObj = this.nonStaticObj;
               }
        * ### @PostConstruct는 Spring에 의존적이지 않은 Java의 기능, 의존성 주입이 끝난 이후의 작업이 필요할 경우 사용
        * ### 생성자가 동작하는 시점에는 의존성 주입이 되지 않은 상태 
        * ### @PostConstruct가 적용된 메서드는 Bean LifeCycle에서 한 번만 수행되는 것이 보장됨 -> 생성자가 여러번 호출이 되는 것을 방지
      * ### @Autowired 생성자 주입
        * ```java
                @Component
                public class TestObject {
                   private static FooObject fooObj;

                   @Autowired
                   private TestObject(FooObject fooObj) {
                         this.fooObj = fooObj;
                   }

                   // 혹은 @Autowired setter를 이용할 수도 있다.
                   @Autowired
                   public void setFooObj(FooObject fooObj) {
                      this.fooObj = fooObj;
                   }
                }
      * ### 하지만 Spring은 기본적으로 빈을 싱글톤으로 관리하므로, static 필드를 사용하지 않아도 같은 인스턴스 공유가능
      * ###  static필드는 여러 문제를 초래할 수 있어 권장하지 않음 (private final 권장)
  * ## Bean(싱글톤)등록시 유의할 점
    * ### 싱글톤(공유객체) 안에 프로토타입(매번 새로운 객체 생성)을 넣지마라
    * ### read-only
    * ### 불변 -> 변하지 않으려면 상태가 없어야함 (iv 존재 X)
    * ### iv가 있으면 동기화 처리가 되어 있어야함
  * ## Spring Context가 로딩 시점에 로직 실행 하는 방법 (@PostCOnstruct, @PreDestroy)
    * ### 스프링 프레임워크는 Bean 생성부터 소멸까지의 생명주기 관리를 해줌 (즉, 객체 관리 주체가 개발자가 아닌 프레임워크)
    * ### @PostConstruct : '초기화 콜백'
    * ### @PreDestroy : '소멸 콜백'
  