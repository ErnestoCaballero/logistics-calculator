package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.commands.TileBoxCommand;
import com.ernesto.logisticscalculator.model.TileBox;

import java.util.Optional;
import java.util.Set;

public interface TileBoxService {

    Set<TileBox> findAll();

    TileBox save(TileBox tileBox);

    TileBox findById(Long id);

    void deleteById(Long id);

    TileBoxCommand saveTileBoxCommand(TileBoxCommand command);

}
