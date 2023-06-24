package com.ernesto.logisticscalculator.forms;

import com.ernesto.logisticscalculator.commands.TripDetailCommand;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TripDetailsForm {
    private List<TripDetailCommand> tripDetails;

    public List<TripDetailCommand> getTripDetails() {
        return tripDetails;
    }

    public void setTripDetails(List<TripDetailCommand> tripDetails) {
        this.tripDetails = tripDetails;
    }
}
