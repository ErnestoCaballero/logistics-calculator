package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.model.Truck;
import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping({"/truck/{id}/select/", "/truck/{id}/select"})
    public String selectTruck(@PathVariable String id, Model model) {
        model.addAttribute("truck", truckService.findById(Long.valueOf(id)));

        return "entities/truck/selectedtruck";
    }

    @GetMapping({"/truck/{id}/show/", "/truck/{id}/show"})
    public String showTruck(@PathVariable String id, Model model) {
        model.addAttribute("truck", truckService.findById(Long.valueOf(id)));
        return "entities/truck/show";
    }

    @GetMapping({"/truck/{id}/delete/", "/truck/{id}/delete"})
    public String deleteTruck(@PathVariable String id) {
        log.debug("Deleting id: " + id);

        truckService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }

    @GetMapping({"/truck/{id}/update/", "/truck/{id}/update"})
    public String updateTruck(@PathVariable String id, Model model) {

        model.addAttribute("truck", truckService.findById(Long.valueOf(id)));

        return "entities/truck/truckform";
    }

    @PostMapping("truck/update")
    public String submitUpdate(@ModelAttribute Truck truck) {
        log.debug("Saving changes to truck with ID: " + truck.getId());
        Truck truck1 = truckService.save(truck);
        return "redirect:/truck/" + truck1.getId() + "/show/";
    }



}
