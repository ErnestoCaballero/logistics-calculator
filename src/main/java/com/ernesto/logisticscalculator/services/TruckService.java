package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.model.Truck;

import java.util.Set;

public interface TruckService {
    Set<Truck> findAll();

    Truck save(Truck truck);

    Truck findById(Long id);

    void deleteById(Long id);
}
