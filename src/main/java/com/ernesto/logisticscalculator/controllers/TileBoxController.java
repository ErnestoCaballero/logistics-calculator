package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.services.TileBoxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller //Should be annotated as Spring Component
public class TileBoxController {

    private final TileBoxService tileBoxService;

    // This will perform dependency injection and implement the class TileBoxServiceImpl that we annotate as @Service
    public TileBoxController(TileBoxService tileBoxService) {
        this.tileBoxService = tileBoxService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model ) {
        model.addAttribute("products", tileBoxService.findAll());
        return "entities/listofproducts";
    }
}
