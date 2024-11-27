package pojo.recipe_3_9.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pojo.recipe_3_9.domain.Player;
import pojo.recipe_3_9.domain.Reservation;
import pojo.recipe_3_9.domain.ReservationValidator;
import pojo.recipe_3_9.domain.SportType;
import pojo.recipe_3_9.service.ReservationService;

import java.util.List;

@Controller
@RequestMapping("/reservationForm")
@SessionAttributes("reservation")
public class ReservationFormController {

    private final ReservationService reservationService;
    private final ReservationValidator reservationValidator;

    @Autowired
    public ReservationFormController(ReservationService reservationService, ReservationValidator reservationValidator) {
        this.reservationService = reservationService;
        this.reservationValidator = reservationValidator;
    }

    @ModelAttribute("sportTypes")
    public List<SportType> populateSportTypes() {
        return reservationService.getAllSportTypes();
    }

    @GetMapping
    public String setupForm(
            @RequestParam(required = false, value = "username") String username,
            Model model
    ) {
        Reservation reservation = new Reservation();
        reservation.setPlayer(new Player(username, null));
        model.addAttribute("reservation", reservation);
        return "recipe_3_9/reservationForm";
    }

    @PostMapping
    public String submitForm(@ModelAttribute("reservation") @Validated Reservation reservation,
                             BindingResult bindingResult,
                             SessionStatus status) {

        if (bindingResult.hasErrors()) {
            return "recipe_3_9/reservationForm";
        } else {
            reservationService.make(reservation);
            status.setComplete(); // 세션에 저장된 폼 객체를 전송 후에 삭제 처리해야 서버에 성공적으로 반영된 데이터를 깔끔하게 지울 수 있다.
            return "redirect:/reservationSuccess";
        }
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(reservationValidator);
    }
}
