package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.model.Truck;
import com.ernesto.logisticscalculator.repositories.TruckRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    public TruckServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    @Transactional
    public Set<Truck> findAll() {
        log.debug("I'm on the service to findAll trucks and put them in a set");

        Set<Truck> truckSet = new HashSet<>();

        for (Truck truck : truckRepository.findAll()) {
            truckSet.add(truck);
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
        truckRepository.deleteById(id);
    }
}
