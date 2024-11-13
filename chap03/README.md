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