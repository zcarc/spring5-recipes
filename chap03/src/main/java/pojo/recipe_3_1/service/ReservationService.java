package pojo.recipe_3_1.service;

import pojo.recipe_3_1.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> query(String courtName);
}
