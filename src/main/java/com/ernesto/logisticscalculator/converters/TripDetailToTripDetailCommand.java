package com.ernesto.logisticscalculator.converters;

import com.ernesto.logisticscalculator.commands.TripDetailCommand;
import com.ernesto.logisticscalculator.model.TripDetail;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TripDetailToTripDetailCommand implements Converter<TripDetail, TripDetailCommand> {

    @Override
    @Transactional
    @Nullable
    public TripDetailCommand convert(TripDetail source) {
        if (source == null) return null;

        final TripDetailCommand tripDetailCommand = new TripDetailCommand();

        tripDetailCommand.setId(source.getId());
        tripDetailCommand.setTripId(source.getTrip().getId());
        tripDetailCommand.setProductId(source.getProduct().getId());
        tripDetailCommand.setProductName(source.getProductName());
        tripDetailCommand.setSentBoxes(source.getSentBoxes());
        tripDetailCommand.setTotalSquareMeters(source.getTotalSquareMeters());
        tripDetailCommand.setTotalWeight(source.getTotalWeight());

        return tripDetailCommand;
    }
}
