package pojo.recipe_3_9.service;

import pojo.recipe_3_9.domain.Reservation;
import pojo.recipe_3_9.domain.SportType;

import java.util.List;

public interface ReservationService {

    void make(Reservation reservation) throws ReservationNotAvailableException;

    List<SportType> getAllSportTypes();

    public SportType getSportType(int sportTypeId);
}
