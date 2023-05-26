package com.ernesto.logisticscalculator.repositories;

import com.ernesto.logisticscalculator.model.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip, Long> {

}
