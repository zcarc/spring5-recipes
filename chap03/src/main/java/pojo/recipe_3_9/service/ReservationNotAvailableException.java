package pojo.recipe_3_9.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
public class ReservationNotAvailableException extends RuntimeException {

    private final String courtName;
    private final LocalDate date;
    private final int hour;
}
