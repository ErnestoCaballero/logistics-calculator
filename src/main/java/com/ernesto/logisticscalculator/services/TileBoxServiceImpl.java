package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.repositories.TileBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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
    public Set<TileBox> findAll() {
        log.debug("I'm in the service to findAll Tile Boxes and put them in a Set");

        Set<TileBox> tileBoxes = new HashSet<>();

        for (TileBox tileBox : tileBoxRepository.findAll()) {
            tileBoxes.add(tileBox);
        }

        return tileBoxes;
    }
}
