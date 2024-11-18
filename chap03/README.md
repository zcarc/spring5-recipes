## recipe_3_1

```java
@RequestMapping("/reservationQuery")
@Controller
public class ReservationQueryController {
    
    private final ReservationService reservationService;

    public ReservationQueryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    
    @GetMapping
    public void setupForm() {}
    
    @PostMapping
    public String submitForm(@RequestParam("courtName") String courtName,
                             Model model) {

        List<Reservation> reservations = Collections.emptyList();
        if(courtName != null) {
            reservations = reservationService.query(courtName);
        }
        model.addAttribute("reservations", reservations);
        return "reservationQuery";
    }
}
```
setupForm() 메서드
- /reservationQuery URL의 GET 요청의 기본 핸들러 메서드입니다.
- 매개변수, 본문코드가 없는 건 컨트롤러에서는 데이터는 하나도 추가하지 않으니 (JSP 같은) 구현체 템플릿에서 하드코딩된 데이터를 뷰에서 보여주겠다는 의미입니다.
- 반환값이 없다는 것은 기본 뷰 이름이 요청 URL에 따라 결정된다는 뜻입니다. 가령, 요청 URL이 /reservationQuery면 reservationQuery라는 이름의 뷰가 반환된다는 의미입니다.

submitForm() 메서드
- /reservationQuery URL의 POST 요청의 기본 핸들러 메서드입니다.
- return 값을 void를 반환해도 요청 URL에 따라 reservationQuery라는 이름의 뷰가 반환됩니다.

## 레시피 3-3 핸들러 인터셉터로 요청 가로채기

afterCompletion() 메서드는 요청 처리가 모두 끝난(즉, 뷰 렌더링까지 완료된) 이후 호출됩니다.

자바 언어에서 인터페이스를 구현할 때에는 원하지 않는 메서드까지 모조히 구현해야 하는 규칙이 있습니다.
그래서 인터페이스를 구현하는 대신 인터셉터 어댑터 클래스를 상속받아 쓰는 게 더 낫습니다.
인터셉터 어댑터는 인터페이스에 선언된 메서드를 모두 기본 구현한 클래스라서 필요한 메서드만 오버라이드해 쓰면 됩니다.

## 레시피 3-6 이름으로 뷰 해석하기

### XML 구성 파일에 따라 뷰 해석하기

- 뷰를 스프링 빈으로 선언하고 해당 빈 이름으로 해석하는 전략
- 애플리케이션 컨텍스트에 등록해도 되지만 별도 파일로 분리하는게 좋습니다.
- XmlViewResolver는 기본적으로 /WEB-INF/view.xml 파일에서 뷰 빈을 읽습니다.

~~~java
@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer, ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Bean
    public ViewResolver viewResolver() {
        XmlViewResolver viewResolver = new XmlViewResolver();
        viewResolver.setLocation(resourceLoader.getResource("/WEB-INF/court-view.xml"));
        return viewResolver;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
~~~

~~~xml
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="welcome" class="org.springframework.web.servlet.view.JstlView">
        <property name="url" value="/WEB-INF/jsp/welcome.jsp"/>
    </bean>

    <bean id="reservationQuery" class="org.springframework.web.servlet.view.JstlView">
        <property name="url" value="/WEB-INF/jsp/reservationQuery.jsp"/>
    </bean>

    <bean id="welcomeRedirect" class="org.springframework.web.servlet.view.RedirectView">
        <property name="url" value="welcome"/>
    </bean>
</beans>

~~~

`setResourceLoader` 메서드는 Spring의 ResourceLoaderAware 인터페이스를 구현하는 부분으로, Spring 컨테이너가 자동으로 ResourceLoader를 주입(inject)해주는 역할을 합니다.

구체적으로 설명하면:

1.  `ResourceLoaderAware` 인터페이스를 구현하면, Spring은 해당 빈이 초기화될 때 자동으로 `setResourceLoader` 메서드를 호출합니다.
2.  `ResourceLoader`는 Spring에서 리소스(XML, properties 파일 등)를 로드하는 기능을 제공하는 유틸리티입니다.
3.  이렇게 주입받은 `ResourceLoader`는 클래스의 필드에 저장되어, 나중에 `viewResolver()` 메서드에서 사용됩니다.

예시 코드에서는:

```java
viewResolver.setLocation(resourceLoader.getResource("/WEB-INF/court-view.xml"));
```

이 부분에서 주입받은 `resourceLoader`를 사용하여 `/WEB-INF/court-view.xml` 파일을 로드하고 있습니다.

이런 방식의 장점은:

*   리소스 로딩 로직을 추상화할 수 있음
*   다양한 위치(클래스패스, 파일시스템, URL 등)의 리소스를 일관된 방식으로 접근 가능
*   테스트하기 쉬움 (ResourceLoader를 모킹할 수 있음)

### 리소스 번들에 따라 뷰 해석하기

- 클래스패스 루트에 있는 리소스 번들에서 뷰 빈을 읽습니다.
- 로케일별로 리소스 번들을 따로따로 로드할 수 있습니다.
- classpath:recipe_3_6_ii/court-views 는 resources/recipe_3_6_ii/court-views.properties 를 읽습니다.
- XML 파일에 빈 선언을 할 때와 큰 차이는 없습니다.

~~~java
@Configuration
public class ViewResolverConfiguration {

    @Bean
    public ResourceBundleViewResolver viewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setBasename("classpath:recipe_3_6_ii/court-views");
        return viewResolver;
    }
}
~~~

~~~properties
welcome.(class)=org.springframework.web.servlet.view.JstlView
welcome.url=/WEB-INF/jsp/welcome.jsp
reservationQuery.(class)=org.springframework.web.servlet.view.JstlView
reservationQuery.url=/WEB-INF/jsp/reservationQuery.jsp
welcomeRedirect.(class)=org.springframework.web.servlet.view.RedirectView
welcomeRedirect.url=welcome
~~~

### 여러 리졸버를 이용해 뷰 해석하기

- 웹 애플리케이션 뷰가 여러 개면 한 가지 뷰 해석 전략만으로는 부족합니다.
- 내부 JSP 뷰는 대부분 InternalResourceViewResolver가 해석한다 해도 ResourceBundleViewResolver로 해석해야 할 뷰도 있습니다. 그럴 경우에는 두 가지 뷰 전략을 함께 적용합니다.
- 둘 이상의 전략을 사용할 때는 우선순위가 중요합니다.
- 뷰 리졸버 빈의 order 프로퍼티 값으로 우선순위를 지정합니다.(값이 작을수록 우선순위가 높습니다.)
- 뷰의 존재 여부와 상관없이 InternalResourceViewResolver는 항상 뷰를 해석하므로 우선순위를 가장 낮게 설정해야 합니다. 그렇지 않으면 다른 뷰 리졸버는 뷰를 해석할 기회조차 없습니다.
- InternalResourceViewResolver로 해석할 수 없는 뷰만 (RedirectView 같은) court-views.properties 리소스 번들 파일에 기재하면 됩니다.

~~~java
@Configuration
public class ViewResolverConfiguration {

    @Bean
    public ResourceBundleViewResolver viewResolver() {
        ResourceBundleViewResolver viewResolver = new ResourceBundleViewResolver();
        viewResolver.setOrder(0);
        viewResolver.setBasename("classpath:court-views");
        return viewResolver;
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
~~~

~~~properties
welcomeRedirect.(class)=org.springframework.web.servlet.view.RedirectView
welcomeRedirect.url=welcome
~~~

### 리다이렉트 접두사(prefix) 붙이기

- InternalResourceViewResolver가 웹 애플리케이션 컨텍스트가 구성되어 있을 경우 뷰 이름이 redirect: 접두사를 붙이면 리다이렉트 뷰로 해석할 수 있습니다.
- 접두사 다음에 나오는 뷰 이름을 리다이렉트 URL로 처리합니다.

## 레시피 3-7 뷰와 컨텐트 협상 활용하기
컨트롤러에서 welcome.html, welcome.pdf 이 아닌 welcome 처럼 확장자가 없는 URL을 매핑하려고 합니다. 

- 스프링 MVC 컨텐트 협상 기능은 ContentNegotiatingViewResolver 클래스에 기반한 리졸버 형태로 구성합니다.
- ContentNegotiationManager는 configureContentNegotiation 메서드를 오버라이드할 때 생성됩니다.
- 컨텐트 협상 기능이 제대로 작동하려면 ContentNegotiatingViewResolver의 우선순위를 가장 높게 설정해야 합니다.
- 이 리졸버는 스스로 뷰를 해석하지 않고 자신이 자동으로 감지한 다른 리졸버에게 작업을 넘깁니다.

어떤 컨트롤러가 /reservationSummary.xml 요청을 받았고 핸들러 메서드는 처리를 마치고 reservation 이라는 논리 뷰로 제어권을 넘긴다고 합시다. <br>
바로 이 시점에 스프링 MVC 리졸버가 개입해서 ContentNegotiatingViewResolver가 우선순위가 가장 높아 첫번째로 작업을 처리하게 됩니다.

ContentNegotiatingViewResolver가 미디어 타입을 결정하는 과정은 다음과 같습니다. <br>
요청경로에 포함된 확장자(.html, .xml, .pdf)를 ContentNegotiationManager 빈 구성 시 지정한 mediatypes 맵을 이용해 기본 미디어 타입(ex: text/html)과 비교합니다.
1. 요청 경로에 확장자는 있지만 기본 미디어 타입과 매치되는 확장자가 없으면 JAF의 FileTypeMap을 이용해 이 확장자의 미디어 타입을 결정합니다.
2. 요청 경로에 확장자가 없으면 Http Accept 헤더를 활용합니다. 

- /reservationSummary.xml 요청은 1에서 미디어 타입이 application/xml로 확정되고, /reservationSummary 요청은 Accept 헤더값까지 확인되어야 합니다.
- Accept 헤더에 지정된 값 (ex: Accept: text/html, Accept: application/pdf)을 보면 요청 URL에 확장자가 없어도 클라이언트가 기대하는 미디어 타입을 추론하는데 도움이 됩니다.
- ContentNegotiatingViewResolver는 미디어 타입을 알아내면 reservation이라는 논리 뷰를 얻게되고 이 정보를 바탕으로 지정한 우선순위에 따라 차례대로 나머지 리졸버를 순회하면서 논리 뷰에 가장 적합한 뷰를 결정합니다.

이러한 프로세스 덕분에 ContentNegotiatingViewResolver는 이름이 같은 논리 뷰가 여럿 있어도 제각기 지원하는 미디어 타입 (ex: HTML, PDF, XML)과 가장 부합하는 뷰를 해석할 수 있습니다. <br>
어떤 미디어 타입 (ex: pdfReservation, xmlReservation, htmlReservation) 을 생성할 때 필요한 논리 뷰를 일일이 하드코딩하지 않고도 뷰 하나만 있으면 ContentNegotiatingViewResolver가 가장 적합한 뷰를 알아서 찾아주기 때문에 컨트롤러 설계를 간소화할 수 있습니다. 

예상되는 컨텐트 협상 결과는 다음과 같습니다.

- 미디어 타입이 application/pdf로 결정됩니다. 우선순위가 가장 높은 (order 값이 제일 작은) 리졸버가 reservation 논리 뷰에 매핑되어 있지만 이 뷰가 application/pdf 타입을 지원하지 않을 경우 매치는 실패하고 나머지 리졸버를 계속 스캐닝합니다.
- 미디어 타입이 application/pdf로 결정됩니다. 우선순위가 가장 높은 (order 값이 제일 작은) 리졸버가 reservation 논리 뷰에 매핑되어 있고 이 뷰가 application/pdf 타입을 지원하면 매치에 성공합니다.
- 미디어 타입이 text/html로 결정됩니다. reservation 논리 뷰는 네 종류의 리졸버에 매핑되어 있지만 그중 우선순위가 가장 높은 두 리졸버는 text/html을 지원하는 뷰에 매핑된 리졸버가 매치됩니다.

애플리케이션에 구성된 리졸버는 모두 위와 같은 과정으로 뷰를 자동 스캐닝합니다. <br>
뷰를 찾지 못해도 ContentNegotiatingViewResolver 외부 구성에 의존해서 찾고 싶지 않을 경우, 기본 뷰와 리졸버를 ContentNegotiatingViewResolver 빈에 구성하면 됩니다. <br>
ContentNegotiatingViewResolver 를 이용해 컨트롤러에서 애플리케이션 뷰를 결정하는 문제는 [레시피 3-11]에서 다룹니다.

~~~java
@Configuration
public class ViewResolverConfiguration implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        Map<String, MediaType> mediatypes = new HashMap<>();
        mediatypes.put("html", MediaType.TEXT_HTML);
        mediatypes.put("pdf", MediaType.valueOf("application/json"));
        mediatypes.put("xls", MediaType.valueOf("application/vnd.ms-excel"));
        mediatypes.put("xml", MediaType.APPLICATION_XML);
        mediatypes.put("json", MediaType.APPLICATION_JSON);
        configurer.mediaTypes(mediatypes);
    }

    @Bean
    public ContentNegotiatingViewResolver contentNegotiatingViewResolver(ContentNegotiationManager contentNegotiationManager) {

        ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
        viewResolver.setContentNegotiationManager(contentNegotiationManager);
        return viewResolver;
    }
}
~~~

## 레시피 3-8 뷰에 예외 매핑하기

서버에서 예기치 않은 예외가 발생하면 예외 스택 스테이스(stack trace)가 화면에 출력됩니다. <br>
일반 사용자의 사용자 경험에도 좋지 않고, 내부 메서드 호출구조가 출력돼서 잠재적인 보안 위험이 있습니다. <br> 
HTTP 에러나 클래스 예외가 발생해도 JSP 페이지가 보이도록 web.xml 파일을 설정하면 되긴 하나,
스프링 MVC는 클래스 예외 처리용 뷰를 관리하는 기능까지 지원합니다.

- 스프링 MVC 에서 컨텍스트 레벨에 예외 리졸버 빈을 하나 이상 등록하면 붙잡히지 않는 예외를 처리할 수 있습니다.
- HandlerExceptionResolver 인터페이스를 구현한 예외 리졸버 빈은 DispatcherServlet이 자동으로 감지합니다.
- 스프링 MVC 에 내장된 단순 예외 리졸버를 이용하면 예외 카테고리별로 뷰를 하나씩 매핑할 수 있습니다.

- 붙잡히지 않는 예외는 HandlerExceptionResolver 인터페이스를 구현한 커스텀 예외 리졸버로 해석할 수 있습니다. 
- 보통 예외 카테고리별로 각각의 에러 페이지를 매핑합니다.
- 스프링 MVC에 내장된 예외 리졸버 SimpleMappingExceptionResolver를 이용하면 웹 애플리케이션 컨텍스트에서 발생한 예외를 매핑할 수 있습니다.

### HandlerExceptionResolver로 매핑하기

recipe_3_8 패키지에 구현

### @ExceptionHandler로 매핑하기

- HandlerExceptionResolver를 구성하지 않고 메서드에 @ExceptionHandler를 붙여서 예외 핸들러를 매핑하는 방법도 있습니다.
- 여러 타입인 ModelAndView, View 등의 객체도 반환할 수 있습니다.
- 유연하게 사용가능하지만 애노테이션이 정의된 컨트롤러에서만 작동하기 때문에 다른 컨트롤러에서 예외가 발생하면 호출되지 않습니다.

~~~java
    @ExceptionHandler(ReservationNotAvailableException.class)
    public String handle(ReservationNotAvailableException ex) {
        return "reservationNotAvailable";
    }

    @ExceptionHandler
    public String handleDefault(Exception e) {
        return "error";
    }
~~~

- handle: ReservationNotAvailableException 하나만 처리하는 메서드
- handleDefault 나머지 모든 예외 처리 메서드

### 범용적인 예외 처리 클래스 @ControllerAdvice로 매핑하기

- 애플리케이션 컨텍스트에 존재하는 모든 컨트롤러에 적용됩니다.
- 모든 컨트롤러에 어드바이스를 적용한다는 의미입니다.

~~~java
@ControllerAdvice
public class ExceptionHandlingAdvice {

    @ExceptionHandler(ReservationNotAvailableException.class)
    public String handle(ReservationNotAvailableException ex) {
        return "reservationNotAvailable";
    }

    @ExceptionHandler
    public String handleDefault(Exception e) {
        return "error";
    }
}
~~~