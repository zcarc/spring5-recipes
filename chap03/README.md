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