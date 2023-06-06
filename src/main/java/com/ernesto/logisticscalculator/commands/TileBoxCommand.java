package com.ernesto.logisticscalculator.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TileBoxCommand {
    private Long id;
    private String code;
    private String description;
    private Double m2PerBox;
    private Integer boxesPerPallet;
    private Double weightPerBox;
}
