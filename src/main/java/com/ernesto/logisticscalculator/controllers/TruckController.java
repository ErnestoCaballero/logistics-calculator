package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping({"/truck/{id}/show/", "/truck/{id}/show"})
    public String showTrucks(@PathVariable String id, Model model) {
        model.addAttribute("truck", truckService.findById(Long.valueOf(id)));
        return "entities/truck/show";
    }

    @GetMapping({"/truck/{id}/delete/", "/truck/{id}/delete"})
    public String deleteTruck(@PathVariable String id) {
        truckService.deleteById(Long.valueOf(id));

        return "redirect:/";
    }
}
