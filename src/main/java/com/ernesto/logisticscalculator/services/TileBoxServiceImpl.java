package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.commands.TileBoxCommand;
import com.ernesto.logisticscalculator.converters.TileBoxCommandToTileBox;
import com.ernesto.logisticscalculator.converters.TileBoxToTileBoxCommand;
import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.repositories.TileBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service  // Annotate as spring component
public class TileBoxServiceImpl implements TileBoxService {

    private final TileBoxRepository tileBoxRepository;
    private final TileBoxCommandToTileBox tileBoxCommandToTileBox;
    private final TileBoxToTileBoxCommand tileBoxToTileBoxCommand;

    // The constructor method will perform the dependency injector by the IoC into the Spring Data JPA implementation


    public TileBoxServiceImpl(TileBoxRepository tileBoxRepository, TileBoxCommandToTileBox tileBoxCommandToTileBox, TileBoxToTileBoxCommand tileBoxToTileBoxCommand) {
        this.tileBoxRepository = tileBoxRepository;
        this.tileBoxCommandToTileBox = tileBoxCommandToTileBox;
        this.tileBoxToTileBoxCommand = tileBoxToTileBoxCommand;
    }

    @Override
    @Transactional
    public Set<TileBox> findAll() {
        log.debug("I'm in the service to findAll Tile Boxes and put them in a Set");

        Set<TileBox> tileBoxes = new HashSet<>();

        for (TileBox tileBox : tileBoxRepository.findAll()) {
            if (!tileBox.getIsDeleted()) {
                tileBoxes.add(tileBox);
            }
        }

        return tileBoxes;
    }

    @Override
    @Transactional
    public TileBox save(TileBox tileBox) {
        TileBox result = tileBoxRepository.save(tileBox);

        log.debug("Saved tile box: " + tileBox.getId());

        return result;
    }

    @Override
    @Transactional
    public TileBox findById(Long id) {
        Optional<TileBox> optionalTileBox = tileBoxRepository.findById(id);

        if (optionalTileBox.isEmpty()) {
            throw new RuntimeException("Tile Box not found!");
        }

        return optionalTileBox.get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("Enter deleteById() method in TileBoxService");

        TileBox tileBox = tileBoxRepository.findById(id).orElse(null);

        if (tileBox == null) {
            throw new IllegalArgumentException("TileBox not found with id: " + id);
        }

        if (tileBox.getTripDetails().isEmpty()) {
            tileBoxRepository.deleteById(id);
        } else {
            tileBox.setIsDeleted(true);
            tileBoxRepository.save(tileBox);
        }
    }

    // The purpose of this method is to save the command object that would be bind
    // in the addproduct.html document and received by the addNewProduct() method
    @Override
    @Transactional
    public TileBoxCommand saveTileBoxCommand(TileBoxCommand command) {
        TileBox detachedTileBox = tileBoxCommandToTileBox.convert(command);

        TileBox savedTileBox = tileBoxRepository.save(detachedTileBox);

        log.debug("Saved new product with id: " + savedTileBox.getId());

        return tileBoxToTileBoxCommand.convert(savedTileBox);
    }
}
