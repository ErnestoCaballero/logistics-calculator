package com.ernesto.logisticscalculator.converters;

import com.ernesto.logisticscalculator.commands.TripDetailCommand;
import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.model.TripDetail;
import com.ernesto.logisticscalculator.repositories.TripRepository;
import com.ernesto.logisticscalculator.services.TileBoxService;
import com.ernesto.logisticscalculator.services.TripService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TripDetailCommandToTripDetail implements Converter<TripDetailCommand, TripDetail> {

    private final TileBoxService tileBoxService;
    private final TripService tripService;

    public TripDetailCommandToTripDetail(TileBoxService tileBoxService, TripService tripService) {
        this.tileBoxService = tileBoxService;
        this.tripService = tripService;
    }

    @Override
    @Nullable
    @Transactional
    public TripDetail convert(TripDetailCommand source) {
        if (source == null) return null;

        TripDetail tripDetail = new TripDetail();

        TileBox selectedTileBox = tileBoxService.findById(source.getTileBoxId());

        tripDetail.setProduct(selectedTileBox);
        tripDetail.setSentBoxes(source.getSentBoxes());
        tripDetail.setTotalSquareMeters(source.getSentBoxes() * selectedTileBox.getM2PerBox());
        tripDetail.setTotalWeight(source.getSentBoxes() * selectedTileBox.getWeightPerBox());
        tripDetail.setProductName(selectedTileBox.getDescription());
        tripDetail.setTrip(tripService.findById(source.getTripId()));

        return tripDetail;
    }
}
