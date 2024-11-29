## 레시피 4-1 REST 서비스로 XML 발행하기

```java
@InitBinder
public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
}

@GetMapping("/test/{date}")
@ResponseBody
public void date(@PathVariable("date") Date date) {
    System.out.println("date = " + date);
}
```    

클라이언트에서 2010-01-01 으로 요청이 왔을 때, 문자열로 데이터를 바인딩하기 때문에 Date 타입으로 커스텀 데이터 바인딩하는 코드이다.

실제로 테스트 해본 결과 http://localhost:8080/test/2010-01-01 요청을 보낸다면 initBinder 메서드가 없을 때, 아래와 같은 예외가 발생한다.

~~~text
org.springframework.web.method.annotation.MethodArgumentTypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'java.util.Date'; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type [java.lang.String] to type [@org.springframework.web.bind.annotation.PathVariable java.util.Date] for value '2010-01-01'; nested exception is java.lang.IllegalArgumentException
~~~

## Prompt:

이 코드에서 initBinder 메서드의 역할이 뭐야? 클라이언트에서 요청이 2010-01-01 이런식으로 데이터가 URL 에 GET 요청을 할 때 저 코드가 쓰이던데 어떤 역할을 하는지 모르겠는데 필요한 이유하고 저 코드가 없다면 어떻게 되는지도 궁금하고 저 방법말고 다른 방법도 있는지도 궁금해 스프링부트말고 스프링프레임워크 기준이야

## Response:

`initBinder` 메서드는 **Spring MVC에서 데이터 바인딩을 커스터마이징**할 때 사용됩니다. 특히, 클라이언트 요청으로 들어오는 문자열 데이터를 컨트롤러 메서드 파라미터에 맞게 변환하는 역할을 합니다.

### **코드의 역할**

```java
@InitBinder
public void initBinder(WebDataBinder binder) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
}
```

* `@InitBinder` 애너테이션은 **특정 컨트롤러에서 사용되는**`WebDataBinder`를 초기화할 때 호출됩니다.
* 위 코드에서는 클라이언트 요청에서 들어오는 **`String` 타입의 날짜 값**을 **`Date` 객체로 변환**하기 위해 `CustomDateEditor`를 등록합니다.
* `SimpleDateFormat("yyyy-MM-dd")` 설정 덕분에 `2010-01-01`과 같은 형식의 문자열이 **자동으로 `Date` 타입**으로 변환됩니다.

### **이 코드가 필요한 이유**

* 클라이언트가 **문자열 형태의 날짜**를 요청에 포함시킬 때, Spring MVC는 기본적으로 이를 `Date`로 변환할 방법을 알지 못합니다.
* 예를 들어, 아래와 같은 요청이 있다고 가정해 봅시다.
  ```sql
  GET /member/123?birthDate=2010-01-01
  ```
  컨트롤러에서 `Date birthDate`를 파라미터로 받으려면, 해당 문자열(`2010-01-01`)을 **`Date` 타입으로 변환**해야 하는데, `initBinder`가 이를 가능하게 해줍니다.

### **코드가 없다면?**

* Spring은 기본적으로 **문자열을 날짜로 변환할 수 없기 때문에** 예외가 발생합니다. `HTTP 400 - Bad Request` 에러가 발생하면서 **BindingException**이 발생합니다.

  에러 예시:

  ```text
  Failed to convert value of type 'java.lang.String' to required type 'java.util.Date';
  ```

### **대체 방법**

1. **`@DateTimeFormat` 사용 (Spring 3.2 이상)**

   ```java
   @GetMapping("/member/{memberId}")
   @ResponseBody
   public Member getMember(@PathVariable("memberId") Long memberId, 
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate) {
       return memberService.find(memberId);
   }
   ```

    * 이렇게 하면 `initBinder` 없이도 `2010-01-01`과 같은 문자열을 **자동으로 `Date`로 변환**합니다.

2. **`LocalDate`와 `@DateTimeFormat` 사용 (Java 8 이상)**

   ```java
   public Member getMember(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthDate) {
       // 2010-01-01을 LocalDate로 매핑
   }
   ```

3. **커스텀 컨버터 등록 (`Converter` 인터페이스 구현)**

   ```java
   @Component
   public class StringToDateConverter implements Converter<String, Date> {
       private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

       @Override
       public Date convert(String source) {
           try {
               return dateFormat.parse(source);
           } catch (ParseException e) {
               throw new IllegalArgumentException("Invalid date format", e);
           }
       }
   }
   ```

### **정리**

* `initBinder`는 **문자열 데이터를 특정 형식의 객체로 변환**하기 위한 도구입니다.
* 이 코드를 사용하면 `yyyy-MM-dd` 형식의 문자열을 **자동으로 `Date` 객체로 변환**할 수 있습니다.
* **대체 방법**으로는 `@DateTimeFormat` 애너테이션을 사용하는 것이 더 간단하며, 최신 코드에서는 `LocalDate`와 함께 사용하는 것이 권장됩니다.

## Prompt:
대중적이고 가장 간단하고 유지보수하기 쉬운 방법 한가지만 고르라면 어떤게 있어? 그러니까 종합적으로 봤을 때 좋은방법 한가지만 꼽으라면?

## Response:
**가장 대중적이고 유지보수가 쉬운 방법**은 **`@DateTimeFormat` 애너테이션을 사용하는 방법**입니다.

### **이 방법을 추천하는 이유:**

1. **간결함**: `initBinder`나 커스텀 컨버터를 따로 설정할 필요 없이, **애너테이션 한 줄**로 해결할 수 있습니다.
2. **표준화**: Spring 프레임워크에서 제공하는 **표준 방식**이므로, 다른 개발자들도 쉽게 이해할 수 있습니다.
3. **유지보수성**: 코드가 **컨트롤러 메서드와 함께 명시적으로 선언**되기 때문에, 어떤 필드에 어떤 형식의 날짜가 적용되는지 바로 알 수 있습니다.
4. **유연성**: 다양한 날짜 형식을 지원하며, `LocalDate`, `Date`, `LocalDateTime` 등 **다양한 타입**에 적용할 수 있습니다.

### **추천 코드 예시**:

```java
@GetMapping("/member/{memberId}")
@ResponseBody
public Member getMember(@PathVariable("memberId") Long memberId, 
                        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date birthDate) {
    return memberService.find(memberId);
}
```

이 방식은 **직관적이고 유지보수하기 쉽기 때문에** 팀 협업이나 코드 리뷰에서도 긍정적인 평가를 받을 가능성이 높습니다. **Spring Boot**와 **Spring Framework** 모두에서 사용 가능하며, 최신 코드에서도 **권장**되는 방식입니다.

