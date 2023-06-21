package com.ernesto.logisticscalculator.controllers;

import com.ernesto.logisticscalculator.commands.TileBoxCommand;
import com.ernesto.logisticscalculator.converters.TileBoxCommandToTileBox;
import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.services.TileBoxService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller //Should be annotated as Spring Component
public class TileBoxController {

    private final TileBoxService tileBoxService;

    // This will perform dependency injection and implement the class TileBoxServiceImpl that we annotate as @Service
    public TileBoxController(TileBoxService tileBoxService) {
        this.tileBoxService = tileBoxService;
    }

    @GetMapping({"/products", "/products/"})
    public String showAllProducts(Model model ) {
        model.addAttribute("products", tileBoxService.findAll());
        return "entities/product/listofproducts";
    }

    @GetMapping("products/new")
    public String getNewProductDocument(Model model) {
        log.debug("Requesting the addproduct document");

        model.addAttribute("tileBox", new TileBoxCommand());

        return "entities/product/addproduct";
    }

    @PostMapping("products/add")
    public String addNewProduct(@ModelAttribute TileBoxCommand command) {
        TileBoxCommand savedCommand = tileBoxService.saveTileBoxCommand(command);

        log.debug("command saved with id: " + savedCommand.getId());

        return "redirect:/products";
    }

    @GetMapping({"/products/{id}/delete", "/products/{id}/delete/"})
    public String deleteProduct(@PathVariable String id) {
        log.debug("Enter the deleteProduct() method with id: " + id);

        tileBoxService.deleteById(Long.valueOf(id));

        log.debug("Deleted item with id: " + id);

        return "redirect:/products";
    }

    @GetMapping({"/products/{id}/update", "/products/{id}/update/"})
    public String updateProduct(@PathVariable String id, Model model) {
        log.debug("Enter the updateProduct() method with id: " + id);

        model.addAttribute("tileBox", tileBoxService.findById(Long.valueOf(id)));

        return "entities/product/addproduct";
    }


}
