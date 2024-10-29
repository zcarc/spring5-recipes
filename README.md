
# recipe_2_10_iii - 스프링 팩토리 빈으로 POJO 생성하기

## AbstractFactoryBean 사용 시, 할인 적용 시뮬레이션:

### 1\. **애플리케이션 컨텍스트 초기화**

먼저, `Main` 클래스에서 `AnnotationConfigApplicationContext`를 사용하여 애플리케이션 컨텍스트가 초기화됩니다.

```java
javaAnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(ShopConfiguration.class);
```

이 과정에서 Spring은 `ShopConfiguration` 클래스에 정의된 모든 빈을 스캔하고 초기화합니다.

### 2\. **제품 빈 생성**

`ShopConfiguration` 클래스에서 정의된 제품 빈들이 순차적으로 생성됩니다:

* `aaa()` 메서드가 호출되어 `Battery` 객체가 생성됩니다.
* `cdrw()` 메서드가 호출되어 `Disc` 객체가 생성됩니다.
* `dvdrw()` 메서드가 호출되어 `Disc` 객체가 생성됩니다.

이 제품들은 각각 Spring 컨텍스트에 `"aaa"`, `"cdrw"`, `"dvdrw"`라는 이름으로 등록됩니다.

### 3\. **팩토리 빈 생성 및 등록**

다음으로, `DiscountFactoryBean` 빈들이 생성됩니다:

* `discountFactoryBeanAAA()` 메서드가 호출되어 `DiscountFactoryBean` 객체가 생성됩니다. 이 팩토리 빈에는 `"aaa"` 제품이 할당되고 20% 할인이 설정됩니다.
* `discountFactoryBeanCDRW()` 메서드가 호출되어 `"cdrw"` 제품에 10% 할인이 적용된 팩토리 빈이 생성됩니다.
* `discountFactoryBeanDVDRW()` 메서드가 호출되어 `"dvdrw"` 제품에 10% 할인이 적용된 팩토리 빈이 생성됩니다.

이 팩토리 빈들도 Spring 컨텍스트에 등록되지만, 여기서 중요한 것은 이 빈들이 실제로 제품 객체를 반환한다는 점입니다.

### 4\. **할인 적용**

팩토리 빈이 등록된 후, Spring은 팩토리 빈을 통해 실제 제품 객체를 생성할 때 할인을 적용합니다.

* 예를 들어, `aaa` 빈을 요청하면 Spring은 먼저 `discountFactoryBeanAAA`를 통해 생성된 객체가 있는지 확인하고, 해당 객체가 존재하면 이를 반환합니다. 이때 이미 20% 할인이 적용된 상태로 반환됩니다.

### 5\. **빈 조회**

이제 `Main` 클래스에서 다음과 같이 제품 빈을 조회합니다:

```java
javaProduct aaa = context.getBean("aaa", Product.class);
Product cdrw = context.getBean("cdrw", Product.class);
Product dvdrw = context.getBean("dvdrw", Product.class);
```

이때 각 빈은 이미 팩토리 빈을 통해 생성된 할인된 제품을 반환합니다. 즉, `aaa`는 원래 가격에서 20% 할인이 적용된 가격을 가진 상태로 반환되며, `cdrw`와 `dvdrw`도 각각 10% 할인이 적용된 상태로 반환됩니다.

### 6\. **쇼핑 카트에 제품 추가**

마지막으로, 각 제품을 쇼핑 카트에 추가하고 출력합니다. 이때 출력되는 제품들은 이미 할인이 적용된 가격을 가진 상태입니다.

```java
javaShoppingCart cart1 = context.getBean("shoppingCart", ShoppingCart.class);
cart1.addItem(aaa);
cart1.addItem(cdrw);
System.out.println("Shopping cart 1 contains " + cart1.getItems());

ShoppingCart cart2 = context.getBean("shoppingCart", ShoppingCart.class);
cart2.addItem(dvdrw);
System.out.println("Shopping cart 2 contains " + cart2.getItems());
```

### 요약

* 프로그램이 실행되면, 제품 빈이 생성되고 팩토리 빈을 통해 할인이 적용됩니다.
* `getBean` 메서드를 사용해 빈을 조회할 때, 이미 할인이 적용된 상태로 반환됩니다.
* 이를 통해 최종적으로 쇼핑 카트에 추가된 제품들은 할인된 가격으로 처리됩니다.

이렇게 프로그램 실행 흐름에 따라 할인이 어떻게 적용되는지 시뮬레이션할 수 있습니다.


# 레시피 2-11 스프링 환경 및 프로파일마다 다른 POJO 로드하기
## recipe_2_11
 - ### @Profile로 자바 구성 클래스를 프로파일별로 작성하기
 - ### 프로파일을 환경에 로드하기
 - ### 기본 프로파일 지정하기


### 프로파일을 환경에 로드하기

자바 런타임 플래그로 로드할 프로파일을 명시하는 방법 <br/>
가령 global, winter 프로파일에 속할 빈을 로드하려면 다음 플래그를 추가합니다.

```
-Dspring.profiles.active=global,winter
```

### 기본 프로파일 지정하기

액티브 프로파일을 하나라도 찾지 못할 경우 기본 프로파일이 로드됩니다.

```java
// 액티브 프로파일 대신
context.setActiveProfiles("global", "winter");

// 디폴트 프로파일 사용
context.setDefaultProfiles("global", "winter");
```

자바 런타임 플래그로 지정할 경우

```
-Dspring.profiles.default=global,winter
```

# 레시피 2-12 POJO에게 IoC 컨테이너 리소스 알려주기



## Prompt:
스프링 빈 라이프사이클에서 빈 @PostConstruct (initmethod) 하고 BeanNameAware (Aware 인터페이스 구현), postProcessBeforeInitialization, postProcessAfterInitialization 이렇게 모두 설정한다면 라이프사이클이 어떤 순서로 실행돼?

## Response:
스프링 빈의 라이프사이클에서, `@PostConstruct`, `BeanNameAware`와 같은 Aware 인터페이스, 그리고 `BeanPostProcessor`의 메서드들이 함께 사용될 때 실행 순서는 다음과 같습니다:

1. **빈의 인스턴스화**: 스프링이 빈을 생성합니다. (`new` 키워드를 사용하거나 리플렉션을 통해 인스턴스화)

2. **의존성 주입**: 빈에 필요한 의존성을 주입합니다. (`@Autowired`, `@Inject`, `@Resource` 등)

3. **`BeanNameAware.setBeanName()` 호출**: 빈이 `BeanNameAware` 인터페이스를 구현한 경우, 빈의 이름이 설정됩니다.

4. **기타 Aware 인터페이스들 호출**: 빈이 다른 Aware 인터페이스를 구현했다면, 그에 따라 호출됩니다. 예를 들어, `BeanFactoryAware`, `ApplicationContextAware` 등이 있습니다.

5. **`BeanPostProcessor.postProcessBeforeInitialization()` 호출**: `BeanPostProcessor`를 구현한 빈들이 있으면, 초기화 전(`@PostConstruct` 또는 `init-method` 실행 전)에 이 메서드가 호출됩니다.

6. **`@PostConstruct` 메서드 호출**: 빈에 `@PostConstruct` 애노테이션이 붙은 메서드가 있다면, 이 시점에서 호출됩니다.

7. **초기화 메서드 호출 (`init-method`)**: XML 설정이나 자바 설정에서 명시적으로 지정한 초기화 메서드가 있다면, 이 시점에서 호출됩니다.

8. **`BeanPostProcessor.postProcessAfterInitialization()` 호출**: 초기화 후에 `BeanPostProcessor`를 구현한 빈들이 있으면 이 메서드가 호출됩니다.

따라서, 빈 라이프사이클에서 위 요소들이 실행되는 순서는 다음과 같습니다:

1. 빈 인스턴스화 및 의존성 주입
2. `BeanNameAware.setBeanName()`
3. 기타 Aware 인터페이스 메서드들
4. `BeanPostProcessor.postProcessBeforeInitialization()`
5. `@PostConstruct` 메서드
6. 초기화 메서드 (`init-method`)
7. `BeanPostProcessor.postProcessAfterInitialization()`

## Prompt:
그러면 beanNameAware 이 빈이 실제로 초기화되기전에 호출되는 가장 빠른 요소야?

## Response:
네, `BeanNameAware`의 `setBeanName(String name)` 메서드는 빈이 초기화되기 전, 즉 가장 먼저 호출되는 요소 중 하나입니다.

정확히 말하면, `BeanNameAware`의 `setBeanName()` 메서드는 빈이 인스턴스화되고 의존성 주입이 이루어진 후에 호출됩니다. 이 메서드는 빈의 이름을 설정하는 역할을 하며, 빈이 스프링 컨테이너에서 자신의 이름을 알 수 있도록 합니다. 그 이후에 다른 `Aware` 인터페이스들(`BeanFactoryAware`, `ApplicationContextAware` 등)이 호출되고, 그 다음에 `BeanPostProcessor`의 `postProcessBeforeInitialization()` 메서드가 호출됩니다.

따라서, `BeanNameAware`는 초기화 관련 콜백들 중에서 가장 먼저 실행되는 콜백 중 하나로, 빈이 자신의 이름을 인식할 수 있도록 하기 위해 초기화 전에 호출됩니다.

## Prompt:
의존성 주입이라는게 실제 @Autowired 가 붙은 변수에 값을 할당하는시점이야?

## Response:
네, 맞습니다. 의존성 주입이란, 스프링 컨테이너가 빈의 생성자, 필드, 또는 메서드에 필요한 의존성을 자동으로 주입하는 과정을 말합니다.

특히, `@Autowired` 애노테이션이 붙은 필드나 메서드에 값을 할당하는 시점이 바로 의존성 주입이 이루어지는 순간입니다. 이 과정은 빈이 인스턴스화된 후에 일어나며, 빈이 실제로 사용될 준비를 하기 전에 이루어집니다.

### 구체적인 과정:

1. **빈 인스턴스화**: 스프링이 빈의 인스턴스를 생성합니다.

2. **의존성 주입**: 스프링 컨테이너가 `@Autowired`가 붙은 필드나 메서드에 적절한 의존성을 주입합니다. 이 시점에 주입된 빈이 이미 스프링 컨테이너에 의해 관리되고 있어야 하며, 주입할 빈이 없는 경우에는 오류가 발생할 수 있습니다(기본적으로는 예외를 던지지만, `required=false`로 설정하면 주입을 생략할 수 있습니다).

3. **Aware 인터페이스 메서드 호출**: 필요한 경우 `BeanNameAware`, `ApplicationContextAware` 등 Aware 인터페이스의 메서드가 호출됩니다.

이 과정에서 `@Autowired`가 붙은 필드나 메서드에 실제로 빈이 주입되므로, 이 시점 이후에 해당 필드를 사용할 수 있게 됩니다.

## Prompt:
그렇다면 postProcessBeforeInitialization 이거는 빈이 초기화되기전에 호출되어야 하는거 아니야? 위에서 1번부터 7번까지의 과정에서 1번은 빈 인스턴스화 및 의존성 주입인데 4번에서 BeanPostProcessor.postProcessBeforeInitialization() 이렇게 되어 있는데, 어떻게 의존성 주입이 라이프사이클에서 우선순위인거야?

## Response:
좋은 질문입니다! 이 부분을 명확히 하기 위해, 스프링 빈의 라이프사이클에서의 각 단계를 조금 더 자세히 살펴보겠습니다.

### 스프링 빈 라이프사이클의 실제 순서

1. **빈 인스턴스화**: 스프링이 빈의 인스턴스를 생성합니다. (예: `new` 키워드를 통해 객체를 생성)

2. **의존성 주입**: 생성된 빈의 필드나 메서드에 `@Autowired`, `@Inject`, `@Resource` 등을 통해 의존성이 주입됩니다.

3. **`BeanNameAware`, `BeanFactoryAware`, `ApplicationContextAware` 등 Aware 인터페이스 호출**: 빈이 자신의 이름, 소속된 빈 팩토리, 애플리케이션 컨텍스트 등을 알 수 있도록 해당 인터페이스 메서드가 호출됩니다.

4. **`BeanPostProcessor.postProcessBeforeInitialization()` 호출**: `BeanPostProcessor`를 구현한 모든 빈에서 이 메서드가 호출됩니다. 이는 빈의 초기화 메서드(`@PostConstruct` 또는 `init-method`)가 호출되기 전에 수행됩니다.

5. **빈 초기화 단계**:

    * **`@PostConstruct`**: 해당 빈에 `@PostConstruct` 애노테이션이 붙은 메서드가 호출됩니다.
    * **`init-method`**: XML 설정이나 자바 설정에서 명시적으로 지정한 초기화 메서드가 호출됩니다.

6. **`BeanPostProcessor.postProcessAfterInitialization()` 호출**: 빈 초기화 후에 `BeanPostProcessor`를 구현한 빈에서 이 메서드가 호출됩니다.

### 왜 의존성 주입이 먼저 일어나는가?

`postProcessBeforeInitialization()` 메서드는 빈이 완전히 초기화되기 전에 호출되지만, 이 메서드가 호출되기 전에 **의존성 주입이 반드시 완료되어야 합니다**. 왜냐하면 `BeanPostProcessor`의 구현체가 빈의 속성 값을 변경하거나, 빈을 래핑(wrapping)하거나, 특정 설정을 적용할 수 있기 때문에, 해당 메서드가 호출될 때 빈은 이미 완전히 구성된 상태여야 합니다.

### 요약:

1. **빈 인스턴스화**
2. **의존성 주입**
3. **`BeanPostProcessor.postProcessBeforeInitialization()` 호출**

의존성 주입은 빈이 완전한 상태로 구성되도록 하기 위해 초기화 작업(`@PostConstruct`, `init-method`)이 실행되기 전에 이루어지며, 그 다음에 `BeanPostProcessor`의 `postProcessBeforeInitialization()` 메서드가 호출됩니다. 이것이 스프링이 의존성 주입을 빈 초기화 이전에 수행하는 이유입니다.

## Prompt:
그렇다면 의존성 주입이라는게 말이 안되는게 뭐냐면, 의존성이 주입이 된다는거는 이미 실행가능한 상태인 상태라는거 아니야? 그렇다면 실제로 주입이 된 상태에서 BeanNameAware, postProcessBeforeInitialization 이런 콜백함수를 호출한다면 빈이 초기화 한 다음에 이런거를 호출해서 다른 작업을 해서 그 결과는 어떻게 다시 이미 주입된 변수에 값을 적용하는거지?

## Response:
이해할 수 있게 설명해드릴게요. 스프링 빈 라이프사이클에서 의존성 주입과 초기화 과정은 명확하게 구분되어 있고, 각 단계는 다른 작업을 수행합니다. 여기서 의문이 생길 수 있는 부분은 의존성 주입 후에 빈 초기화 전에 콜백 메서드(`BeanNameAware`, `postProcessBeforeInitialization()`)가 호출될 때, 그 시점에서 빈이 이미 "실행 가능한" 상태라는 점입니다. 이 점을 하나씩 풀어보겠습니다.

### 1\. 의존성 주입이란 무엇인가?

의존성 주입은 스프링 컨테이너가 빈을 생성하고, 해당 빈이 필요로 하는 다른 빈이나 리소스를 제공하는 과정입니다. 이 과정에서:

* 스프링은 빈 인스턴스를 생성합니다.
* 빈의 필드나 메서드에 `@Autowired` 같은 애노테이션이 붙어 있으면, 스프링은 해당 필드나 메서드에 다른 빈을 주입합니다.

이 단계가 완료되면, 빈은 모든 의존성을 가진 상태로 "구성"됩니다. 그러나 이는 "실행 가능"한 상태와는 약간 다릅니다.

### 2\. BeanNameAware와 postProcessBeforeInitialization의 역할

* **`BeanNameAware` 인터페이스**: 이 인터페이스는 빈이 자신의 이름을 알 수 있도록 해줍니다. 빈 인스턴스화 및 의존성 주입 후, 이 메서드가 호출됩니다.

* **`postProcessBeforeInitialization`**: 이 메서드는 초기화 메서드(`@PostConstruct` 또는 `init-method`)가 호출되기 전에 빈에 대해 추가적인 처리를 수행할 수 있도록 합니다. 이 메서드는 빈이 완전히 초기화되기 전에 마지막으로 실행되는 콜백입니다.

이 단계에서 빈이 "실행 가능한" 상태가 되도록 추가적인 설정이나 변경을 할 수 있습니다. 즉, 빈의 상태를 변경하거나, 주입된 의존성을 조정하거나, 다른 작업을 수행할 수 있는 기회를 제공합니다.

### 3\. 콜백 후의 상태 변화

`postProcessBeforeInitialization`이나 `BeanNameAware` 같은 콜백 메서드가 호출되면, 해당 메서드에서 빈의 상태를 변경할 수 있습니다. 이 변경은 빈이 초기화된 후에도 유효합니다. 예를 들어:

* `BeanNameAware`는 빈에게 자신의 이름을 알리는 역할을 하고, 빈은 이 정보를 이후의 작업에서 사용할 수 있습니다.
* `postProcessBeforeInitialization`에서 빈의 필드 값을 변경하거나, 래핑(wrapping)하거나, 다른 설정을 추가할 수 있습니다.

이때, 스프링은 이미 주입된 의존성을 다시 주입하지 않습니다. 대신, 콜백 메서드에서 변경된 사항이 이후의 라이프사이클에서 반영되도록 합니다.

### 4\. 초기화 후 상태

초기화가 완료된 후, 빈은 스프링 애플리케이션 내에서 사용될 준비가 완료된 상태가 됩니다. 이 상태에서 `postProcessAfterInitialization` 같은 후처리 메서드가 호출되어 추가적인 작업이 가능합니다.

### 요약:

* 의존성 주입은 빈이 사용할 준비가 되도록 모든 필요한 리소스와 의존성을 설정하는 과정입니다.
* `BeanNameAware`나 `postProcessBeforeInitialization` 같은 콜백 메서드는 주입된 의존성이나 빈의 상태를 초기화 전에 변경할 기회를 제공합니다.
* 이러한 변경은 초기화 이후에도 반영되며, 스프링이 이를 관리합니다.

스프링의 설계는 유연하고 확장 가능하도록 되어 있기 때문에, 초기화 전후에 다양한 후처리 작업을 수행할 수 있습니다. 이 점에서 콜백 메서드는 중요한 역할을 합니다.

## Prompt:
위의 라이프사이클대로 라면, BeanNameAware 가 postProcessBeforeInitialization 보다 먼저 호출된다면 postProcessBeforeInitialization에서 다른 빈으로 교체하고 반환하면 다른 빈이 될텐데 그러면, BeanNameAware는 교체된 빈을 알지못하는거야?

## Response:
네, 좋은 질문입니다! 이 상황에서의 순서를 다시 생각해보겠습니다.

### 빈 라이프사이클에서의 주요 단계:

1. **빈 인스턴스화**: 스프링이 빈의 인스턴스를 생성합니다.
2. **의존성 주입**: 스프링이 해당 빈에 필요한 의존성을 주입합니다.
3. **`BeanNameAware.setBeanName()` 호출**: 스프링은 `BeanNameAware` 인터페이스를 구현한 빈에 대해 이 메서드를 호출하여 빈이 자신의 이름을 알 수 있도록 합니다.
4. **`BeanPostProcessor.postProcessBeforeInitialization()` 호출**: `BeanPostProcessor` 인터페이스를 구현한 빈의 `postProcessBeforeInitialization` 메서드가 호출됩니다.
5. **초기화 메서드 호출 (`@PostConstruct`, `init-method`)**: 빈의 초기화 메서드들이 호출됩니다.
6. **`BeanPostProcessor.postProcessAfterInitialization()` 호출**: `postProcessAfterInitialization` 메서드가 호출됩니다.

### 질문의 핵심:

만약 `postProcessBeforeInitialization`에서 빈을 다른 빈으로 교체하고 반환한다면, 교체된 빈이 `BeanNameAware`를 구현하고 있지 않은 한, `BeanNameAware.setBeanName()` 메서드가 교체된 빈에 대해서는 호출되지 않습니다. 이는 `BeanNameAware`가 원래 빈에 대해 호출된 후에 `postProcessBeforeInitialization`에서 빈이 교체되기 때문입니다.

### 상황 설명:

1. **원래 빈 인스턴스 생성 및 의존성 주입**:

    * 빈이 인스턴스화되고, 의존성이 주입됩니다.

2. **`BeanNameAware.setBeanName()` 호출**:

    * 원래 빈에 대해 `BeanNameAware.setBeanName()`이 호출되어, 빈은 자신의 이름을 알게 됩니다.

3. **`postProcessBeforeInitialization`에서 빈 교체**:

    * `postProcessBeforeInitialization` 메서드에서 원래 빈을 새로운 빈으로 교체할 수 있습니다.
    * 이 교체된 빈이 `BeanNameAware`를 구현하지 않았다면, `setBeanName()` 메서드는 호출되지 않으며, 새로운 빈은 자신의 이름을 알지 못합니다.
    * 교체된 빈이 `BeanNameAware`를 구현하고 있더라도, `postProcessBeforeInitialization` 이후에는 `setBeanName()` 메서드가 다시 호출되지 않으므로 이름을 설정받지 못합니다.

### 결론:

* `BeanNameAware`의 `setBeanName()`은 **원래** 빈에 대해 호출되며, `postProcessBeforeInitialization`에서 빈이 교체된 경우, 교체된 빈은 자신의 이름을 알 수 없게 됩니다.
* 따라서, `postProcessBeforeInitialization` 단계에서 빈을 교체하게 되면, 이후 단계에서 교체된 빈은 스프링 컨테이너에서 제공하는 일부 초기화 콜백을 놓칠 수 있습니다.

스프링에서 이런 디자인을 채택한 이유는 유연성을 제공하기 위함입니다. `BeanPostProcessor`를 통해 빈을 동적으로 변경할 수 있지만, 이러한 변경이 빈의 초기 설정에 영향을 줄 수 있다는 점은 고려해야 합니다. 만약 교체된 빈이 자신의 이름을 알도록 하려면, 교체된 빈에 대해 별도로 `BeanNameAware`를 수동으로 호출해주거나, 교체된 빈이 초기화에 필요한 콜백을 따로 처리해야 합니다.


# 레시피 2-13 애너테이션을 활용해 애스펙트(Aspect) 지향 프로그래밍하기

## 애스펙트, 어드바이스, 포인트컷 선언하기

### recipe_2_13_i

- 애스펙트는 여러 타입과 객체에 공통 관심사(예: 로깅, 트랜잭션 등)를 모듈화한 자바 클래스로 @Aspect를 붙여 표현합니다.
- AOP에서 말하는 애스펙트란 어디에서(포인트컷) 무엇을 할 것인지(어드바이스)를 합쳐놓은 개념입니다.
어드바이스에는 5가지로 @Before, @After, @Around, @AfterReturning, @AfterThrowing 애너테이션이 있습니다.
- 조인포인트는 호출될 수 있는 모든 메서드이고, 조인포인트는 포인트컷의 후보로 볼 수 있다.

Note: @Aspect만 붙이면 스프링이 클래스 패스에서 자동으로 감지하지 않기 때문에 POJO 마다 개별적으로 @Component를 붙여야 합니다.

### recipe_2_13_ii

- @After 어드바이스는 조인포인트가 끝나면 실행되는 메서드로 조인포인트가 정상 실행되든, 도중에 예외가 발생하든 상관없이 실행됩니다.

### recipe_2_13_iii

- @AfterReturning 어드바이스는 조인포인트 실행의 성공 여부와 상관없이 작동합니다.
- 조인포인트가 값을 반환할 경우에만 호출됩니다.
- 반환한 결괏값은 @AfterReturning의 returning 속성으로 지정한 변수명을 어드바이스 메서드의 파라미터로 지정해서 가져옵니다.

### recipe_2_13_iv

- @AfterThrowing 어드바이스는 조인포인트 실행 중 예외가 발생할 경우에만 실행됩니다.
- 반환한 결괏값은 @AfterThrowing의 throwing 속성으로 지정한 변수명을 어드바이스 메서드의 파라미터로 지정해서 가져옵니다.
- 모든 에러/예외가 발생했을 때 결과를 가져오려면 Throwable 타입을 사용하는데 이 타입은 모든 에러/예외 클래스의 상위 타입으로 지정합니다.
- 파라미터 타입에 반환받을 원하는 타입을 지정하면 해당 타입만 가져올 수 있습니다.

### recipe_2_13_v

- @Around 어드바이스는 가장 강력한 어드바이스로 다른 어드바이스를 이 어드바이스로 조합할 수 있습니다.
- 파라미터 타입은 ProceedingJoinPoint로 고정되어 있습니다.
- @Around는 매우 강력하고 유연한 어드바이스지만 원본 조인포인트 호출을 하지 않는 경우가 있으니 주의가 필요합니다.

> **Tip**_ 최소한의 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는 게 바람직합니다.

# 레시피 2-14 조인포인트 정보 가져오기

### recipe_2_14

#### 과제
AOP에서 어드바이스는 여러 조인포인트, 즉 프로그램 실행 지점 곳곳에 적용됩니다. 어드바이스가 정확하게 작동하려면 조인포인트에 관한 세부 정보가 필요한 경우가 많습니다.

- 프록시로 감싼 원본 빈은 대상 객체라고 하며 프록시 객체는 this로 참조합니다.

# 레시피 2-15 @Order로 애스펙트 우선순위 설정하기

### recipe_2_15

#### 과제

같은 조인포인트에 애스펙트를 여러 개 적용할 경우, 애스펙트 간 우선순위를 정하세요.

- Ordered 인터페이스를 구현할 경우 getOrder 메서드의 반환 값이 낮은 애스펙트가 반환된다.
- @Order 인터페이스를 애스펙트 클래스에 선언해서 더 깔끔하게 구현할 수 있다.

# 레시피 2-16 애스펙트 포인트컷 재사용하기

### recipe_2_16_i

#### 과제

포인트컷(어디에서) 표현식을 여러 번 되풀이해서 쓸 경우에는 어드바이스 애너테이션에 직접 써넣는 것보다 재사용할 방법이 필요합니다.

#### 해결책

@Pointcut을 이용해서 여러 어드바이스에서 재사용할 수 있습니다.

- 포인트컷의 가시성은 public, protected, private 메서드의 수정자로 조정합니다.
- 포인트컷은 메서드명으로 참조합니다.

### recipe_2_16_ii

- 여러 애스펙트가 포인트컷을 공유하는 경우라면 공통 클래스 한 곳에 포인트컷을 모아두는 편이 좋습니다. (포인트컷 메서드는 public으로 선언합니다.)
- 포인트컷을 참조할 때는 클래스명도 함께 적습니다. 참조할 포인트컷이 현재 애스펙트와 다른 패키지에 있으면 패키지명까지 기재합니다.

# 레시피 2-17 AspectJ 포인트컷 표현식 작성하기

### recipe_2_17_i

#### 과제

공통 관심사는 프로그램이 실행되는 지점, 여러 조인포인트에 분포합니다. 조인포인트는 종류 여러가지 일 수도 있으므로 강력한 표현식 언어로 매치할 방법이 필요합니다.

#### 해결책

스프링 AOP가 지원하는 조인포인트 대상은 IoC 컨테이너 안에 선언된 빈에 국한됩니다.
스프링 AOP에서는 AspectJ 포인트컷 언어를 활용해 포인트컷을 정의하며 런타임에 AspectJ 라이브러리를 이용해 포인트컷 표현식을 해석합니다. 
스프링 AOP에서 AspectJ 포인트컷 표현식을 작성할 경우 스프링 AOP가 IoC 컨테이너 안에 있는 빈에만 조인포인트를 지원한다는 점을 반드시 기억하세요.
이 스코프에 벗어나 포인트컷 표현식을 쓰면 IllegalArgumentException 예외가 발생합니다.

- `execution(* Calculator.*(..))`
- 대상 클래스나 인터페이스가 애스펙트와 같은 패키지에 있다면 패키지명은 생략 가능합니다.
- 메서드 시그니처 패턴이 있습니다. 
- 타입 시그니처 패턴이 있습니다.

`execution(* Calculator.*(..))`는 Spring AOP (Aspect-Oriented Programming)에서 사용되는 포인트컷 표현식입니다. 이 표현식은 특정 메서드 실행 지점을 가리키며, 각 부분이 다음과 같은 의미를 갖습니다:

1. `execution`은 메서드 실행을 가리키는 포인트컷 지시어입니다.

2. `*`: 메서드의 반환 타입을 의미하며, 여기서는 와일드카드 `*`를 사용하여 반환 타입이 어떤 것이든 상관없이 모든 메서드를 타겟으로 합니다.

3. `Calculator.*`: `Calculator` 클래스에 있는 모든 메서드를 가리킵니다. 와일드카드 `*`는 메서드의 이름을 의미하며, 어떤 이름이든 상관없이 모든 메서드가 대상이 됩니다.

4. `(..)`: 메서드의 매개변수를 의미하는 부분입니다. `..`는 와일드카드로, 매개변수의 개수와 타입에 관계없이 모든 메서드를 타겟으로 합니다.

따라서 `execution(* Calculator.*(..))`는 `Calculator` 클래스의 모든 메서드 호출 시, 반환 타입이나 매개변수와 관계없이 AOP가 적용되도록 설정된 것입니다.

- 애너테이션을 사용해서 조인포인트를 지정할 수 있다.
- 클래스 레벨에 애너테이션을 붙이면 모든 메서드가 포인트컷에 매치되는 조인포인트가 된다.
- 특정 메서드에 붙이면 해당 메서드만 포인트컷에 매치되는 조인포인트가 된다.
- 실제 호출이 되는 부분은 어드바이스의 포인트컷 부분인데 이 포인트컷은 애너테이션을 가리키고 있다. 그래서 해당 애너테이션이 붙은 메서드만 조인포인트가 된다.


## 메서드 시그니처 패턴과 타입 시그니처 패턴의 차이

### 메서드 시그니처 패턴

메서드 시그니처 패턴은 특정 메서드에 대한 포인트컷을 설정할 때 사용됩니다. 메서드의 반환 타입, 이름, 매개변수 타입 등을 기준으로 매칭하여 AOP를 적용할 대상을 지정합니다.

예를 들어, `execution(public * com.example.service.*.*(..))`와 같은 표현은:

* `public` 반환 타입을 가지는
* `com.example.service` 패키지 아래에 있는 모든 클래스의
* 모든 메서드 이름과
* 모든 매개변수 타입을 가진 메서드들

에 대해 AOP를 적용하겠다는 의미입니다.

### 타입 시그니처 패턴

타입 시그니처 패턴은 특정 클래스나 인터페이스 전체를 기준으로 포인트컷을 설정할 때 사용됩니다. 여기서는 메서드 이름이나 반환 타입이 아니라, 클래스나 인터페이스 자체를 기준으로 AOP를 적용할 대상을 지정합니다.

예를 들어, `within(com.example.service..*)`와 같은 표현은:

* `com.example.service` 패키지 및 그 하위 패키지의
* 모든 클래스에

대해 AOP를 적용하겠다는 의미입니다. 이때 클래스의 메서드들이 모두 포인트컷의 대상이 됩니다.

### 주요 차이점 요약

1. **적용 대상의 범위**:

   * **메서드 시그니처 패턴**은 개별 메서드를 대상으로 지정합니다. 메서드의 반환 타입, 이름, 매개변수 타입 등을 기준으로 포인트컷을 정의할 수 있습니다.
   * **타입 시그니처 패턴**은 클래스나 인터페이스 전체를 대상으로 지정합니다. 특정 패키지에 있는 클래스들이나 특정 클래스의 메서드 전체에 대해 포인트컷을 설정합니다.

2. **표현 방식**:

   * **메서드 시그니처 패턴**은 `execution`을 사용하여 메서드의 실행을 기준으로 포인트컷을 설정합니다.
   * **타입 시그니처 패턴**은 `within`을 사용하여 특정 클래스나 패키지를 기준으로 포인트컷을 설정합니다.

3. **적용 범위의 유연성**:

   * **메서드 시그니처 패턴**은 개별 메서드를 구체적으로 지정할 수 있기 때문에, 특정 메서드에만 AOP를 적용할 때 적합합니다.
   * **타입 시그니처 패턴**은 클래스 전체에 AOP를 적용할 때 사용되므로, 클래스의 모든 메서드에 공통적으로 적용할 로직이 있을 때 유용합니다.

### 예시로 보는 차이점

```java
// 메서드 시그니처 패턴
@Pointcut("execution(* com.example.service.AccountService.*(..))")
public void accountServiceMethods() {}

// 타입 시그니처 패턴
@Pointcut("within(com.example.service.AccountService)")
public void accountServiceType() {}
```

위 예시에서 `accountServiceMethods()`는 `AccountService` 클래스의 모든 메서드에 AOP를 적용하지만, `accountServiceType()`는 `AccountService` 클래스 전체를 대상으로 하여 모든 메서드가 적용 대상이 됩니다.

두 패턴은 서로 보완적인 역할을 하며, 적절한 상황에 맞게 사용하면 됩니다. 메서드 시그니처 패턴은 세부적으로 메서드를 지정할 때, 타입 시그니처 패턴은 더 넓은 범위의 클래스나 패키지에 일괄적으로 적용할 때 적합합니다.


# 레시피 2-18 인트로덕션을 이용해 POJO에 기능 더하기

~~~java
@DeclareParents(
    value = "pojo.recipe_2_18.ArithmeticCalculatorImpl+",
    defaultImpl = MaxCalculatorImpl.class)
public MaxCalculator maxCalculator;

@DeclareParents(
    value = "pojo.recipe_2_18.ArithmeticCalculatorImpl+",
    defaultImpl = MinCalculatorImpl.class)
public MinCalculator minCalculator;
~~~

\+ 기호는 AspectJ 포인트컷 표현식에서 해당 클래스와 그 서브클래스들을 모두 포함한다는 의미입니다.


# 레시피 2-20 AspectJ 애스펙트를 로드 타임 위빙하기

#### 문제 
스프링 AOP 프레임워크는 제한된 타입의 AspectJ 포인트컷만 지원하며 IoC 컨테이너에 선언한 빈에 한하여 애스펙트를 적용할 수 있습니다. 따라서 포인트컷 타입을 추가하거나, IoC 컨테이너 외부 객체에 애스펙트를 적용하려면 스프링 애플리케이션에서 AspectJ 프레임워크를 직접 끌어쓰는 수밖에 없습니다.

#### 해결방법
- weaving(엮어넣기)은 애스펙트를 대상 객체에 적용하는 과정입니다. 스프링 AOP는 런타임에 동적 프록시를 활용해 위빙을 하는 반면, AspectJ 프레임워크는 compile-time weaving, load-time weaving 모두 지원합니다.
AspectJ에서 compile-time weaving은 **ajc**라는 전용 컴파일러가 담당합니다. compile-time weaving은 애스펙트를 자바 소스 파일에 weave하고 weave된 바이너리 클래스 파일을 결과물로 내놓습니다.
이미 컴파일된 클래스 파일이나 JAR 파일 안에도 애스펙트를 weave 할 수 있습니다.
이를 포스트 컴파일 타임 위빙(post-compile-time weaving)이라고 합니다.
compile-time weaving, post-compile-time weaving 모두
클래스를 IoC 컨테이너에 선언하기 이전에 수행할 수 있으며, 스프링은 위빙 과정에 전혀 관여하지 않습니다. 자세한 내용은 AspectJ 문서를 참고하세요.
- AspectJ 로드 타임 위빙(LTW)은 JVM이 클래스 로더를 이용해 대상 클래스를 로드하는 시점에 일어납니다. 바이트코드에 코드를 넣어 클래스를 **weaving**하려면 특수한 클래스 로더가 필요합니다. AspectJ, 스프링 둘 다 클래스 로더에 **weaving** 기능을 부여한 로드 타임 weaver를 제공합니다. **load-time-weaver**는 간단한 설정으로 바로 사용할 수 있습니다.

#### 참고

- AspectJ 로드 타임 위빙 (LTW) 는 JVM이 클래스 로더를 이용해 대상 클래스를 로드하는 시점에 일어납니다. 바이트코드에 코드를 넣어 클래스를 위빙하려면 특수한 클래스가 필요한데 AspectJ, 스프링 둘 다 클래스 로더에 위빙 기능을 부여한 로드 타임 위버를 제공합니다.
- 기본적으로 스프링에서 포인트컷 표현식 call은 지원하지 않으므로 사용하려면 AspectJ 프레임워크를 직접 사용해야 합니다.
- load-time weaving를 사용하는 방법에는 AspectJ weaver, Spring weaver로 weaving 할 수 있습니다.
- AspectJ load-time weaving 실행은 다음 명령으로 실행할 수 있습니다.
  - java -javaagent:aspectjweaver-1.9.22.1.jar -jar recipe_2_20_ii.jar
- 스프링 로드 타임 위버는 IoC 컨테이너에 선언한 빈이 new Complex(int, int) 생성자를 호출할 경우에만 스프링 에이저느가 어드바이스를 적용합니다. 그래서 main 함수 내에서 new Complex(int, int)를 호출할 경우에는 어드바이스가 적용되지 않습니다.

#### Q. 
인자를 2개를 받는 생성자 int, int 형으로 Complex를 생성하는데, IoC 컨테이너에 등록하지 않고 AspectJ weaver를 사용해서 캐시로 등록하는데 왜 이런식으로 Complex를 캐시하는지, 인자 2개를 받는 Complex를 IoC에 등록하는게 더 간단할 수도 있는데 캐시를 사용하는 이유는?

#### A. 
AspectJ를 사용하여 `Complex` 객체를 캐싱하는 것은 Spring IoC 컨테이너의 빈 관리와는 다른 목적을 가지고 있습니다. IoC 컨테이너에서는 보통 정적으로 생성되고 재사용 가능한 빈을 관리합니다. 그러나 `Complex(1, 2)`, `Complex(2, 3)`와 같이 다양한 인수를 가진 객체가 동적으로 생성될 때마다 동일한 객체를 재사용하고 싶다면, Aspect를 이용한 캐싱 패턴이 효과적일 수 있습니다.
    캐싱을 통해 얻을 수 있는 주요 이점은 **성능 최적화**입니다. 예를 들어 `new Complex(1, 2)`와 같은 객체를 여러 번 호출할 경우, 이미 생성된 객체를 반환하여 불필요한 객체 생성을 방지할 수 있습니다. AspectJ를 통해 객체 생성에 관한 로직을 분리함으로써, 메인 로직(예: 계산)을 깔끔하게 유지하면서도 캐싱을 쉽게 적용할 수 있습니다.
    `Complex`와 같은 동적 파라미터가 필요한 객체를 IoC 컨테이너에 등록하기는 다소 비효율적입니다. IoC 컨테이너는 보통 같은 객체의 인스턴스를 빈으로 재사용하는 경우에 적합하지만, 다양한 파라미터 조합을 캐시로 관리할 때는 Aspect를 이용해 조건에 따라 캐싱하는 것이 더 간단하고 효율적입니다.

#### recipe_2_20_ii - AspectJ 위버로 로드 타임에 위빙
#### recipe_2_20_iii - 스프링 로드 타임 위버로 로드 타임에 위빙하기
- 이 부분은 실행이 정상적으로 되지 않아서 패스