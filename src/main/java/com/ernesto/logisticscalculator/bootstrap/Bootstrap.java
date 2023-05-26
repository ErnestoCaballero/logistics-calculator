package com.ernesto.logisticscalculator.bootstrap;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.model.Trip;
import com.ernesto.logisticscalculator.model.TripDetail;
import com.ernesto.logisticscalculator.model.Truck;
import com.ernesto.logisticscalculator.repositories.TileBoxRepository;
import com.ernesto.logisticscalculator.repositories.TripDetailRepository;
import com.ernesto.logisticscalculator.repositories.TripRepository;
import com.ernesto.logisticscalculator.services.TileBoxService;
import com.ernesto.logisticscalculator.services.TileBoxServiceImpl;
import com.ernesto.logisticscalculator.services.TruckService;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    public TileBoxRepository tileBoxRepository;
    public TileBoxService tileBoxService;
    private final TripRepository tripRepository;
    private final TripDetailRepository tripDetailRepository;
    private final TruckService truckService;

    public Bootstrap(TileBoxRepository tileBoxRepository, TileBoxService tileBoxService, TripRepository tripRepository, TripDetailRepository tripDetailRepository, TruckService truckService) {
        this.tileBoxRepository = tileBoxRepository;
        this.tileBoxService = tileBoxService;
        this.tripRepository = tripRepository;
        this.tripDetailRepository = tripDetailRepository;
        this.truckService = truckService;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        TileBox tileBox1 = new TileBox();

        tileBox1.setCode("PP059");
        tileBox1.setDescription("Colossale");
        tileBox1.setM2PerBox(2.88);
        tileBox1.setBoxesPerPallet(20);
        tileBox1.setWeightPerBox(23.9);

        tileBoxRepository.save(tileBox1);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's crate now a new TileBox using the TileBoxService...");
        TileBox tileBox2 = new TileBox();

        System.out.println("Instantiation finish. Adding attributes...");
        tileBox2.setCode("CM02");
        tileBox2.setDescription("Cementi");
        tileBox2.setM2PerBox(1.44);
        tileBox2.setBoxesPerPallet(22);
        tileBox2.setWeightPerBox(25.8);

        System.out.println("Saving the new product using TileBoxService...");
        tileBoxService.save(tileBox2);

        // Create a Trip
        System.out.println("Let's now create a Trip");
        System.out.println("Indicate the truck id you're sending: ");
        Long truck_id = scanner.nextLong();
        Truck truck = truckService.findById(truck_id);

        Trip trip = new Trip();
        trip.setDate(new Date());
        trip.setTotalCargoWeight(0.0);
        trip.setTotalSquareMeters(0.0);
        trip.setTripCost(truck.getCost());
        trip.setTruckCapacity(truck.getCapacityInTons());
        trip.setTruck(truck);

        tripRepository.save(trip);

        // Create Trip details
        System.out.println("checking the newly generated trip id...");
        Long newTripId = trip.getId();
        System.out.println("The id for the new trip generated is " + trip.getId());

        List<TripDetail> tripDetails = new ArrayList<>();

        // Set properties for trip 1
        int sentBoxes1 = 12;
        Long productId1 = 5L;
        TripDetail tripDetail1 = new TripDetail();
        tripDetail1.setTrip(tripRepository.findById(newTripId).get());
        TileBox product1 = tileBoxRepository.findById(productId1).get();
        tripDetail1.setProduct(product1);
        tripDetail1.setSentBoxes(sentBoxes1);
        tripDetail1.setTotalSquareMeters(product1.getM2PerBox() * sentBoxes1);
        tripDetail1.setTotalWeight(product1.getWeightPerBox() * sentBoxes1);
        tripDetail1.setProductName(product1.getDescription());

        // Set properties for trip 2
        int sentBoxes2 = 8;
        Long productId2 = 2L;
        TripDetail tripDetail2 = new TripDetail();
        tripDetail2.setTrip(tripRepository.findById(newTripId).get());
        TileBox product2 = tileBoxRepository.findById(productId2).get();
        tripDetail2.setProduct(product2);
        tripDetail2.setSentBoxes(sentBoxes2);
        tripDetail2.setTotalSquareMeters(product2.getM2PerBox() * sentBoxes2);
        tripDetail2.setTotalWeight(product2.getWeightPerBox() * sentBoxes2);
        tripDetail2.setProductName(product2.getDescription());

        // Adding tripDetails to List
        tripDetails.add(tripDetail1);
        tripDetails.add(tripDetail2);

        trip.setTripDetails(tripDetails);

        tripRepository.save(trip);
    }


}
