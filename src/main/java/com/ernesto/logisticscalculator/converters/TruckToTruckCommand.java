package com.ernesto.logisticscalculator.converters;

import com.ernesto.logisticscalculator.commands.TruckCommand;
import com.ernesto.logisticscalculator.model.Truck;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TruckToTruckCommand implements Converter<Truck, TruckCommand> {

    @Override
    @Nullable
    @Transactional
    public TruckCommand convert(Truck source) {
        if (source == null) return null;

        final TruckCommand truckCommand = new TruckCommand();

        truckCommand.setId(source.getId());
        truckCommand.setType(source.getType());
        truckCommand.setCost(source.getCost());
        truckCommand.setCapacityInTons(source.getCapacityInTons());
        truckCommand.setIsDeleted(source.getIsDeleted());

        return truckCommand;
    }
}
