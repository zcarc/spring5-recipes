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