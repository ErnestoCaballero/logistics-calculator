package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.repositories.TripRepository;
import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        
        model.addAttribute("itemsNumber", itemsNumber);

        return "entities/trip/tripform";
    }

    @PostMapping("trip/{truckId}/submit")
    public String submitItems(@PathVariable String truckId, @RequestParam("itemsNumber") int itemsNumber) {
        log.debug("Enter the submitItems() method");
        log.debug("The inputted items to send are: " + itemsNumber);
        log.debug("The truck id is: " + truckId);

        return "redirect:/trip/" + truckId + "/start/" + itemsNumber;
    }
}
