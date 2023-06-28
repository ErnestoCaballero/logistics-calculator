package com.ernesto.logisticscalculator.services;

import com.ernesto.logisticscalculator.model.Trip;
import com.ernesto.logisticscalculator.model.TripDetail;
import com.ernesto.logisticscalculator.repositories.TripDetailRepository;
import com.ernesto.logisticscalculator.repositories.TripRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final TripDetailRepository tripDetailRepository;

    public TripServiceImpl(TripRepository tripRepository, TripDetailRepository tripDetailRepository) {
        this.tripRepository = tripRepository;
        this.tripDetailRepository = tripDetailRepository;
    }

    @Override
    @Transactional
    public List<Trip> findAll() {
        log.debug("I'm in the service to findAll Trips and put them in an List");
        List<Trip> trips = new ArrayList<>();

        for (Trip trip : tripRepository.findAll()) {
            trips.add(trip);
        }

        return trips;
    }

    @Override
    @Transactional
    public Trip save(Trip trip) {
        Trip savedTrip = tripRepository.save(trip);

        log.debug("Saved trip with id: " + savedTrip.getId());

        return savedTrip;
    }

    @Override
    @Transactional
    public Trip findById(Long id) {
        Optional<Trip> optionalTrip = tripRepository.findById(id);

        if (optionalTrip.isEmpty()) {
            throw new RuntimeException("Trip was not found!");
        }

        return optionalTrip.get();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Trip trip = tripRepository.findById(id).orElse(null);

        if (trip == null) {
            throw new IllegalArgumentException("Trip not found with id: " + id);
        }

        if (trip.getTripDetails().isEmpty()) {
            tripRepository.deleteById(id);
        } else {
            // If the trip does have Trip Details associated, Delete the Trip Details in order to delete the trip record
            for (TripDetail tripDetail : trip.getTripDetails()) {
                tripDetailRepository.deleteById(tripDetail.getId());
            }

            tripRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public void delete(Trip trip) {
        tripRepository.delete(trip);
    }
}
