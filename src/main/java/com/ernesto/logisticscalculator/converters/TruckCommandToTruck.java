package com.ernesto.logisticscalculator.converters;

import com.ernesto.logisticscalculator.commands.TruckCommand;
import com.ernesto.logisticscalculator.model.Truck;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TruckCommandToTruck implements Converter<TruckCommand, Truck> {

    @Override
    @Transactional
    @Nullable
    public Truck convert(TruckCommand source) {
        if (source == null) return null;

        final Truck truck = new Truck();

        truck.setId(source.getId());
        truck.setType(source.getType());
        truck.setCost(source.getCost());
        truck.setCapacityInTons(source.getCapacityInTons());

        return truck;
    }
}
