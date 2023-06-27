package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.Trip;
import com.ernesto.logisticscalculator.repositories.TripRepository;

import java.util.List;

public interface TripService {

    List<Trip> findAll();

    Trip save(Trip trip);

    Trip findById(Long id);

    void deleteById(Long id);

    void delete(Trip trip);
}
