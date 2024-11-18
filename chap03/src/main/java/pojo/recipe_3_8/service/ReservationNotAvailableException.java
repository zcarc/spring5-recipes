package pojo.recipe_3_8.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@Setter
@Getter
public class ReservationNotAvailableException extends RuntimeException {

    private String courtName;
    private Date date;
    private int hour;
}
