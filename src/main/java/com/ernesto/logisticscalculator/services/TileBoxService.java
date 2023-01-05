package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.TileBox;

import java.util.Set;

public interface TileBoxService {

    Set<TileBox> findAll();

    TileBox save(TileBox tileBox);

}
