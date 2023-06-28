package com.ernesto.logisticscalculator.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TripDetailCommand {
    private Long id;
    private Long tripId;
    private Long tileBoxId;
    private Integer sentBoxes;

    public Long getTileBoxId() {
        return tileBoxId;
    }
}
