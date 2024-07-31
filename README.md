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
    * <img src="https://github.com/user-attachments/assets/e471428b-650f-4620-beb4-cdc614304724" width="500">

    * ### # 빈등록시 중복된 이름으로 빈등록할 때 덮어쓰기 허용 (@Configuration으로 @Bean 자동 등록시 이름 충돌)
           spring.main.allow-bean-definition-overriding=true
    * <img src="https://github.com/user-attachments/assets/be9b2291-49e1-441b-819b-49e3e21d368a" width="500">

  
* ## 7/27 공부하다 생긴 의문점들
  * ## @Component와 @Bean의 차이
    > ### @Component는 클래스 레벨에서 붙어지는 에너테이션
    > ### @Bean은 메서드 레벨에서 붙어지는 에터테이션 (@Configuration이 붙은 클래스 안에서 정의)
    > ### 둘다 스프링 빈으로 등록된다는 공통점이 있음 
  * ## @ComponentScan과 @Configuration을 같이 쓰는 이유?
    > ### @Configuration는 내부에 @Component가 있어서 해당 설정이 자동으로 스프링 빈으로 등록 -> 빈으로 등록되어야 @ComponentScan도 동작
    > ### @Configuration : 스프링의 빈으로 등록되면서 각종 자바코드 구성정보(@Bean메서드)와 부가 에너테이션을 스프링 컨테이너가 인식할 수 있게 해줌
    > ### @ComponentScan : @Component 에너테이션이 붙은 클래스를 자동으로 빈 등록
    > ### @ComponentScan은 @Configuration이 붙은 클래스에 사용해야 스프링 컨테이너가 인식할 수 있다고 생각
    > ### @ComponentScan이 붙은 @Configuration클래스도 @ComponentScan이 동작해서 빈으로 등록이 되는데, @Configuration 클래스가 빈으로 등록이 안되면, @ComponentScan을 스프링이 인식을 못함
  * ## static변수에 왜 @Autowired를 붙이지 못하나?
    > ### 정적 필드가 클래스 로더에 의해 인스턴스화 될 때 아직 Spring Context는 로드되지 않았기 때문에 
    > ### 해결방법 2가지
      * ### @PostConstruct 사용
        * ```java
              @Autowired
               private FooObject nonStaticObj;

               private static FooObject staticObj;

               @PostConstruct
               private void initStatic() {
                   staticObj = this.nonStaticObj;
               }
        > ### @PostConstruct는 Spring에 의존적이지 않은 Java의 기능, 의존성 주입이 끝난 이후의 작업이 필요할 경우 사용
        > ### 생성자가 동작하는 시점에는 의존성 주입이 되지 않은 상태 
        > ### @PostConstruct가 적용된 메서드는 Bean LifeCycle에서 한 번만 수행되는 것이 보장됨 -> 생성자가 여러번 호출이 되는 것을 방지
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
      > ### 하지만 Spring은 기본적으로 빈을 싱글톤으로 관리하므로, static 필드를 사용하지 않아도 같은 인스턴스 공유가능
      > ###  static필드는 여러 문제를 초래할 수 있어 권장하지 않음 (private final 권장)
  * ## Bean(싱글톤)등록시 유의할 점
    > ### 싱글톤(공유객체) 안에 프로토타입(매번 새로운 객체 생성)을 넣지마라
    > ### read-only
    > ### 불변 -> 변하지 않으려면 상태가 없어야함 (iv 존재 X)
    > ### iv가 있으면 동기화 처리가 되어 있어야함
  * ## Spring Context가 로딩 시점에 로직 실행 하는 방법 (@PostCOnstruct, @PreDestroy)
    > ### 스프링 프레임워크는 Bean 생성부터 소멸까지의 생명주기 관리를 해줌 (즉, 객체 관리 주체가 개발자가 아닌 프레임워크)
    * <img src="https://github.com/user-attachments/assets/a03c59a4-4874-46c9-923d-07ac030cbea3" width="500">
    > ### @PostConstruct : '초기화 콜백'
    > ### @PreDestroy : '소멸 콜백'

* ## Filter, Intercepter, AOP 차이점 정리
  > ### 호출되는 시기가 각기 다름
    * <img src="https://github.com/user-attachments/assets/0a9c92e2-87f3-4785-93f5-5de8d44daa4b" width="500">

  > ### Filter - 스프링과 무관하게 동작, Dispatcher Servlet(적합한 컨트롤러를 찾아 위임 해주는 역할)이 실행 되기전 수행 -> 스프링과 분리되어야 하는 기능에 적합
  
  > ### Interceptor - Controller로 넘겨주는 정보(데이터)의 가공 / 로그인 체크, 권한 체크, 프로그램 실행시간 계산작업 로그확인 등의 작업
  
  > ### Aop - Interceptor나 Filter와 달리 메서드 전후의 지점에 자유롭게 설정가능 즉, URL 기반이 아닌 PointCut 단위로 동작 / 로깅, 트랜잭션, 에러 처리 등 비즈니스단의 메서드에서 조금 더 세밀하게 조정하고 싶을 떄 사용

  
---

* ## JPA란? (Java Persistence API)
  * ### JDBC와 유사 (인터페이스의 집합)
  * ### HIBERNATE : JPA 구현체
  * ### ORM 프레임워크 : 객체와 DB테이블 간의 연결, 차이를 해소해줌
  * <img src="https://github.com/user-attachments/assets/c7ce79f9-c8e5-480c-bb48-fcbec3ad4158" width="500">

  * <img src="https://github.com/user-attachments/assets/a7d5431f-daf1-4cf3-8132-0232f32ab1a4" width="500">

  * <img src="https://github.com/user-attachments/assets/ebe2c4d7-6c52-4e5c-b7d4-dffd09c88aca" width="500">


  * ### MyBatis(DB 중심 개발) vs HIBERNATE(애플리케이션 중심 개발)
  * <img src="https://github.com/user-attachments/assets/df43c0a7-4e02-4db1-ba41-9e9136d0aa7f" width="500">

  * 애플리케이션 중심 개발 : 1. 자바클래스 작성 -> 2. Table 자동 생성 ===> 자바클래스 변경 시 테이블 자동 변경

  * ### Spring Data와 Spring Data JPA
  * <img src="https://github.com/user-attachments/assets/3dd1e547-08dc-41ac-b828-efd3dbc87d41" width="500">

  
  
* ## EntityManager와 EntityManagerFactory
  * <img src="https://github.com/user-attachments/assets/1be386f3-9ac8-4c1f-a189-72adffb920b7" width="500">

  * <img src="https://github.com/user-attachments/assets/735b7894-9798-4424-9402-184c452012a4" width="500">

  * <img src="https://github.com/user-attachments/assets/cdc0883a-41b3-4e97-a6a2-eb7a5483c120" width="500">

  * <img src="https://github.com/user-attachments/assets/444ee34a-9877-4288-a45c-3aa07e93a957" width="500">

  * ### PreparedStatement를 쓰는 이유 : 1. 성능 2. 보안
  * <img src="https://github.com/user-attachments/assets/e600b3c8-a9dc-495e-b8e4-77f7c15b2433" width="500">


  * ### PersistenceContext (엔티티 저장공간)
  * <img src="https://github.com/user-attachments/assets/e1e49dd5-7bd9-4182-a380-2ca1e9e0bb83" width="500">

  > ## flush() : SQL전송 / commit() : SQL전송(flush()자동호출) + DB반영 
  * <img src="https://github.com/user-attachments/assets/91712703-ceee-4bef-9519-c18ad6776b4d" width="500">

  > ## 변경했을떄 SQL문이 생기는건 영속상태일때만 !!
  
  * ### PersistenceContext의 캐시(Map으로 관리)
  * <img src="https://github.com/user-attachments/assets/e7af3c10-d227-4f15-b579-9e43ba340e12" width="500">

  * em.find() : entity 조회(찾기)
  * <img src="https://github.com/user-attachments/assets/2fd6f7ab-8e41-480e-a808-ec76d82fdffb" width="500">

  * 못찾으면 DB에서 조회
  * <img src="https://github.com/user-attachments/assets/ed325ea2-a103-4cf0-a6a5-334eedf60f47" width="500">

  * em.clear() : 캐시를 다 지움
  * <img src="https://github.com/user-attachments/assets/a7974607-77bc-486f-8a91-382920091dab" width="500">


* ## Spring Data JPA 
  * <img src="https://github.com/user-attachments/assets/e56c38b8-2061-4002-8747-21cefeac8645" width="500">

  * <img src="https://github.com/user-attachments/assets/9284fda1-cf6b-47e8-8b91-332cf589c91c" width="500">

  * <img src="https://github.com/user-attachments/assets/58f0ed58-7519-437d-8a44-066eab8070eb" width="500">
  * ### PersistenceContext에 없으면 무조건 DB에 select문을 실행

* ## 쿼리 메서드 (DB에 독립적 -> 오류 검출에 쉬움)
  * <img src="https://github.com/user-attachments/assets/7495f511-50bf-4e1f-b162-8ac6d9966eb4" width="500">
  * <img src="https://github.com/user-attachments/assets/2e7ee4a7-2e6e-4f49-900b-0ddc9b88e8ec" width="500">
  * <img src="https://github.com/user-attachments/assets/0370faa4-9d7b-49d2-97d9-aa545e93e1fe" width="500">
  > delete메서드는 n번 실행될 수 있기 때문에 @Transactional 처리를 해줘야함 / delete의 경우, 여러 건을 delete할 수 있기 때문에 Tx처리 필수
  

* ## JPQL과 네이티브 쿼리
  * ### SQL은 DB테이블을 조회 / JPQL은 엔티티를 조회
  * ### SQL은 대소문자 구별 X / JPQL은 대소문자 구별 
  * <img src="https://github.com/user-attachments/assets/d6242663-3148-4ea4-95dd-111fcf1d79dc" width="500">

  * ### 수동 Query 방법 2가지 (메서드 이름 상관 X)
    * <img src="https://github.com/user-attachments/assets/7297598a-b53a-441d-b63e-f6cc5b64dfe8" width="500">

  * ### JPQL의 매개변수를 지정하는 방법 2가지
    * <img src="https://github.com/user-attachments/assets/84b1a2e0-2763-4be4-93ff-75a2c46901d9" width="500">

  * ### 네이티브 쿼리 작성 (메서드 이름 상관 X) -> 훨씬 복잡한 쿼리 작성할때 쓰임
    * <img src="https://github.com/user-attachments/assets/768ed63b-f0e8-4b5d-99c2-ee4df6a96b8a" width="500">

  * ### 페이징과 정렬
    * <img src="https://github.com/user-attachments/assets/2469597a-9abf-432b-8422-6a12d28c6a7e" width="500">

  
  
* ## Querydsl로 동적 쿼리 작성하기
  * ### 문자열(Query) -> 메서드 (타입이나 구문체크 쉬움)
  * <img src="https://github.com/user-attachments/assets/c43cfd73-c925-44f2-b459-159eb3da2197" width="500">


  * ### JPAQuery와 JPAQueryFactory
    * ### 1. em에서 JPAQueryFactory를 생성 
    * ### 2. JPAQueryFactory로 JPAQuery 생성 -> 쿼리를 메서드로 작성
    * ### 3. JPAQuery를 실행해서 조회결과 얻기
  * <img src="https://github.com/user-attachments/assets/74906ab8-941c-4c3b-9887-f3ff0939c4a0" width="500">


  * ### JPAQueryFactory로 쿼리 작성하기
    * ### 전체 필드 조회 : JPAQuery<T> / 일부 필드 조회 : JPAQuery<Tuple>
  * <img src="https://github.com/user-attachments/assets/7ebc4116-5393-4e59-ac34-832375f1dc0c" width="500">


  * ### BooleanBuilder로 동적 쿼리 작성하기
  * <img src="https://github.com/user-attachments/assets/f2999290-a28f-4d53-b065-59ed568ede4e" width="500">

  
* ## 연관 관계 맵핑하기
  * ### 객체모델(단방향) X 2 = 양방향
  * <img src="https://github.com/user-attachments/assets/f04e67b1-507a-4efd-9561-e794588802e5" width="500">

  * ### 일대일 관계 - 단방향
  * <img src="https://github.com/user-attachments/assets/890df861-6c88-4af8-8fb3-de3cd8370c31" width="500">

  * ### 일대일 관계 - 양방향 
    * ### Key 종류 2가지 : PK (테이블의 한 행을 구별해주는 컬럼) / FK (다른 Table의 PK)
    * ### 조인 종류 2가지 : outer join (null포함) / inner join(null포함X)
    * ### nullable = false -> 필수값만 (inner join 한다는 것)
    * ### FK가 양쪽에 생길 필요가 없음 한곳에만 생기게 해야함 (mappedBy = "테이블명") -> FK 안생김
    * ### FK 2개 -> join 2번 ==> 성능저하
  * <img src="https://github.com/user-attachments/assets/66a3cf00-01f9-4089-9e47-114c1b648dba" width="500">


  
  * ### 다대일 관계 - 단방향
    * ### nullable = false --> outer join에서 inner join으로 바꿔줌
  * <img src="https://github.com/user-attachments/assets/6262257a-e75f-4de6-9419-9a1787803f7c" width="500">

  * ### 다대일 관계 - 양방향
    * ### (Default)FetchType(LAZY) : 선택적(User,Board 선택해서)으로 조회 / FetchType(EAGER) : 한번에 조회 (User,Board 한번에)
    * ### FetchType.EAGER - 두 엔티티의 정보를 같이 가져오는것(join)
    * ### FetchType.LAZY - 따로 가져오는 것. 나중에 getList(). default
    * ### LAZY로 처리 시 해당 메서드에 @Transactional을 붙여줘야함   
  * <img src="https://github.com/user-attachments/assets/a0ceed37-ea3b-4581-95cb-d346bbe42572" width="500">

  
* ## 레이어 아키텍처 
  * <img src="" width="500">
  