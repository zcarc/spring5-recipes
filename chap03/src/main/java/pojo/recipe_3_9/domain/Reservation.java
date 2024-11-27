package pojo.recipe_3_9.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Reservation {

    private String courtName;
    private LocalDate date;
    private int hour;
    private Player player;
    private SportType sportType;

    public Date getDateAsUtilDate() {
        return Date.from(this.date.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
