package com.ernesto.logisticscalculator.commands;

import com.ernesto.logisticscalculator.model.Truck;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class TripCommand {
    private Long id;
    private Date date;
    private TruckCommand truckCommand;
    private Double totalCargoWeight;
    private Double truckCapacity;
    private Double totalSquareMeters;
    private Double tripCost;
    private List<TripDetailCommand> tripDetails = new ArrayList<>();
}
