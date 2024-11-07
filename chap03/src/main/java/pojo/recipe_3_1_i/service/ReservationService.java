package pojo.recipe_3_1_i.service;

import pojo.recipe_3_1_i.domain.Reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> query(String courtName);
}
