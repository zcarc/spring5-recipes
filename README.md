
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