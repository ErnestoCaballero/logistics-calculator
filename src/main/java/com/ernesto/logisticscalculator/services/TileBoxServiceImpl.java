package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.repositories.TileBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service  // Annotate as spring component
public class TileBoxServiceImpl implements TileBoxService {

    private final TileBoxRepository tileBoxRepository;

    // The constructor method will perform the dependency injector by the IoC into the Spring Data JPA implementation
    public TileBoxServiceImpl(TileBoxRepository tileBoxRepository) {
        this.tileBoxRepository = tileBoxRepository;
    }

    @Override
    @Transactional
    public Set<TileBox> findAll() {
        log.debug("I'm in the service to findAll Tile Boxes and put them in a Set");

        Set<TileBox> tileBoxes = new HashSet<>();

        for (TileBox tileBox : tileBoxRepository.findAll()) {
            tileBoxes.add(tileBox);
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
        tileBoxRepository.deleteById(id);
    }
}
