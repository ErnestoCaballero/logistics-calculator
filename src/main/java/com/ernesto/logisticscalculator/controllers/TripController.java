package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.forms.TripDetailForm;
import com.ernesto.logisticscalculator.model.Trip;
import com.ernesto.logisticscalculator.model.TripDetail;
import com.ernesto.logisticscalculator.model.Truck;
import com.ernesto.logisticscalculator.repositories.TripRepository;
import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class TripController {

    private final TruckService truckService;
    private final TripRepository tripRepository;

    public TripController(TruckService truckService, TripRepository tripRepository) {
        this.truckService = truckService;
        this.tripRepository = tripRepository;
    }

    @GetMapping("/trip/{truckId}/start/{itemsNumber}")
    public String startTrip(@PathVariable String truckId, @PathVariable int itemsNumber, Model model) {
        log.debug("Enter the startTrip() method");
        log.debug("The truckId is: " + truckId + " and the itemsNumber is: " + itemsNumber);

        // Add itemsNumber to Model to create as many entries in tripform.html as required
        model.addAttribute("itemsNumber", itemsNumber);

        // Create new Trip
        log.debug("Creating new Trip");
        Truck truck = truckService.findById(Long.valueOf(truckId));

        Trip trip = new Trip();
        trip.setDate(new Date());
        trip.setTotalCargoWeight(0.0);
        trip.setTotalSquareMeters(0.0);
        trip.setTripCost(truck.getCost());
        trip.setTruckCapacity(truck.getCapacityInTons());
        trip.setTruck(truck);

        // Setting the trip details List to be populated after the submission of the tripform.html
//        List<TripDetail> tripDetails = new ArrayList<>();
//        for (int i = 0; i < itemsNumber; i++) {
//            TripDetail tripDetail = new TripDetail();
//            tripDetail.setTrip(trip);
//
//            tripDetails.add(tripDetail);
//        }
//
//        trip.setTripDetails(tripDetails);

        tripRepository.save(trip);

        model.addAttribute("tripId", trip.getId());

        log.debug("Trip created with id: " + trip.getId());

        return "entities/trip/tripform";
    }

    @PostMapping("trip/{truckId}/submit")
    public String submitItems(@PathVariable String truckId, @RequestParam("itemsNumber") int itemsNumber) {
        log.debug("Enter the submitItems() method");
        log.debug("The inputted items to send are: " + itemsNumber);
        log.debug("The truck id is: " + truckId);

        return "redirect:/trip/" + truckId + "/start/" + itemsNumber;
    }

    @PostMapping("trip/save")
    public String saveTripDetails() {
        log.debug("Enter the saveTripDetails() in TripController class");

        return "redirect:/";
    }
}
