package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.services.TruckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final TruckService truckService;

    public IndexController(TruckService truckService) {
        this.truckService = truckService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting index page...");

        model.addAttribute("trucks", truckService.findAll());

        return "index";
    }
}
