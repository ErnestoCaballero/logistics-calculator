package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.model.Trip;
import com.ernesto.logisticscalculator.model.Truck;
import com.ernesto.logisticscalculator.repositories.TruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;
    private final TripService tripService;

    public TruckServiceImpl(TruckRepository truckRepository, TripService tripService) {
        this.truckRepository = truckRepository;
        this.tripService = tripService;
    }

    @Override
    @Transactional
    public Set<Truck> findAll() {
        log.debug("I'm on the service to findAll trucks and put them in a set");

        Set<Truck> truckSet = new HashSet<>();

        for (Truck truck : truckRepository.findAll()) {
            if (!truck.getIsDeleted()) {
                truckSet.add(truck);
            }
        }

        return truckSet;
    }

    @Override
    @Transactional
    public Truck save(Truck truck) {
        Truck truckSaved = truckRepository.save(truck);

        log.debug("The truck saved is " + truckSaved.getType());

        return truckSaved;
    }

    @Override
    @Transactional
    public Truck findById(Long id) {
        Optional<Truck> optionalTruck = truckRepository.findById(id);

        if (optionalTruck.isEmpty()) {
            throw new RuntimeException("The truck was not found");
        }

        return optionalTruck.get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {

        for (Trip trip : tripService.findAll()) {
            if (Objects.equals(trip.getTruck().getId(), id)) {
                Truck truckToDelete = truckRepository.findById(id).orElse(null);

                if (truckToDelete == null) {
                    throw new IllegalArgumentException("Truck not found with id: " + id);
                }

                truckToDelete.setIsDeleted(true);
                Truck deletedTruck = truckRepository.save(truckToDelete);
                log.debug("isDeleted property of Truck with id " + deletedTruck.getId() + " was set to true");
                return;
            }
        }

        truckRepository.deleteById(id);
    }
}
