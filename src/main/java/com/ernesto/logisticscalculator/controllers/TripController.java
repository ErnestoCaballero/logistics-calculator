package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.repositories.TripRepository;
import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class TripController {

    private final TruckService truckService;
    private final TripRepository tripRepository;

    public TripController(TruckService truckService, TripRepository tripRepository) {
        this.truckService = truckService;
        this.tripRepository = tripRepository;
    }

    @GetMapping("/trip/{truckId}/start")
    public String startTrip(@PathVariable String truckId) {
        log.debug("Enter the startTrip() method");

        return "entities/trip/tripform";
    }
}
