package pojo.recipe_3_9.service;

import org.springframework.stereotype.Service;
import pojo.recipe_3_9.domain.Reservation;
import pojo.recipe_3_9.domain.SportType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final SportType TENNIS = new SportType(1, "Tennis");
    private static final SportType SOCCER = new SportType(2, "Soccer");

    private final List<Reservation> reservations = new ArrayList<>();

    @Override
    public void make(Reservation reservation) throws ReservationNotAvailableException {

        long count = reservations.stream()
                .filter(made -> Objects.equals(made.getCourtName(), reservation.getCourtName()))
                .filter(made -> Objects.equals(made.getDate(), reservation.getDate()))
                .filter(made -> made.getHour() == reservation.getHour())
                .count();

        // 예약 줄복 시 에러 발생
        if (count > 0) {
            throw new ReservationNotAvailableException(
                    reservation.getCourtName(),
                    reservation.getDate(),
                    reservation.getHour()
            );
        } else {
            reservations.add(reservation);
        }
    }

    @Override
    public List<SportType> getAllSportTypes() {
        return Arrays.asList(TENNIS, SOCCER);
    }

    @Override
    public SportType getSportType(int sportTypeId) {

        switch (sportTypeId) {
            case 1:
                return TENNIS;
            case 2:
                return SOCCER;
            default:
                return null;
        }
    }
}
