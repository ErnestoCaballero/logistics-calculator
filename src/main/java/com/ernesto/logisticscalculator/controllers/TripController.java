package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.commands.TileBoxCommand;
import com.ernesto.logisticscalculator.commands.TripDetailCommand;
import com.ernesto.logisticscalculator.converters.TripDetailCommandToTripDetail;
import com.ernesto.logisticscalculator.forms.TripDetailsForm;
import com.ernesto.logisticscalculator.model.Trip;
import com.ernesto.logisticscalculator.model.TripDetail;
import com.ernesto.logisticscalculator.model.Truck;
import com.ernesto.logisticscalculator.repositories.TripDetailRepository;
import com.ernesto.logisticscalculator.repositories.TripRepository;
import com.ernesto.logisticscalculator.services.TileBoxService;
import com.ernesto.logisticscalculator.services.TripService;
import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class TripController {

    private final TruckService truckService;
    private final TripRepository tripRepository;
    private final TileBoxService tileBoxService;
    private final TripService tripService;
    private final TripDetailRepository tripDetailRepository;
    private final TripDetailCommandToTripDetail tripDetailCommandToTripDetail;

    public TripController(TruckService truckService, TripRepository tripRepository, TileBoxService tileBoxService, TripService tripService, TripDetailRepository tripDetailRepository, TripDetailCommandToTripDetail tripDetailCommandToTripDetail) {
        this.truckService = truckService;
        this.tripRepository = tripRepository;
        this.tileBoxService = tileBoxService;
        this.tripService = tripService;
        this.tripDetailRepository = tripDetailRepository;
        this.tripDetailCommandToTripDetail = tripDetailCommandToTripDetail;
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

        tripRepository.save(trip);

        log.debug("Trip created with id: " + trip.getId());

        model.addAttribute("tripId", trip.getId());

        List<TripDetailCommand> tripDetails = new ArrayList<>();

        for (int i = 0; i < itemsNumber; i++) {
            TripDetailCommand tripDetailCommand = new TripDetailCommand();
            tripDetailCommand.setTripId(trip.getId());

            tripDetails.add(tripDetailCommand);
        }

        TripDetailsForm tripDetailsForm = new TripDetailsForm();
        tripDetailsForm.setTripDetails(tripDetails);

        model.addAttribute("tripDetailsForm", tripDetailsForm);

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
    public String saveTripDetails(@ModelAttribute("tripDetailsForm") TripDetailsForm tripDetailsForm) {
        log.debug("Enter the saveTripDetails() in TripController class");

        List<TripDetailCommand> tripDetails = tripDetailsForm.getTripDetails();

        List<TripDetail> tripDetailsToSave = new ArrayList<>();

        log.debug("Searching for the trip to be updated...");
        Trip tripToUpdate = tripService.findById(tripDetailsForm.getTripDetails().get(0).getTripId());
        log.debug("The trip to be updated is " + tripToUpdate.getId());

        Double totalCargoWeight = 0.0;
        Double totalSquareMeters = 0.0;

        for (TripDetailCommand tripDetailCommand : tripDetails) {
            log.debug("The first product entered to be saved is: " + tileBoxService.findById(tripDetailCommand.getTileBoxId()).getDescription());
            log.debug("The trip id for which is going to be saved is: " + tripDetailCommand.getTripId());
            TripDetail detachedTripDetail = tripDetailCommandToTripDetail.convert(tripDetailCommand);
            tripDetailsToSave.add(detachedTripDetail);
            log.debug("Adding total weight and total square meters to trip for each product...");
            totalCargoWeight += detachedTripDetail.getTotalWeight();
            totalSquareMeters += detachedTripDetail.getTotalSquareMeters();
        }

        if (totalCargoWeight > tripToUpdate.getTruck().getCapacityInTons() * 1_000) {
            log.debug("The TotalCargoWeight parameter exceeded the Truck capacity...");
            tripService.deleteById(tripToUpdate.getId());
            return "entities/error/exceedcapacity";
        }

        tripToUpdate.setTotalCargoWeight(totalCargoWeight);
        tripToUpdate.setTotalSquareMeters(totalSquareMeters);

        Trip updatedTrip = tripService.save(tripToUpdate);

        for (TripDetail tripDetail : tripDetailsToSave) {
            tripDetailRepository.save(tripDetail);
        }

        return "redirect:/";
    }
}
