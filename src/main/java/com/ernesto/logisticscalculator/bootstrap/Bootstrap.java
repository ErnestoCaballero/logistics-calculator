package com.ernesto.logisticscalculator.bootstrap;

import com.ernesto.logisticscalculator.model.TileBox;
import com.ernesto.logisticscalculator.repositories.TileBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Scanner;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    public TileBoxRepository tileBoxRepository;

    public Bootstrap(TileBoxRepository tileBoxRepository) {
        this.tileBoxRepository = tileBoxRepository;
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

//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Please insert the id of the item you want to find");
//        Long id = scanner.nextLong();
//
//        Optional<TileBox> optionalTileBox = tileBoxRepository.findById(id);
//
//        System.out.println("The description of the item is: " + optionalTileBox.get().getDescription());
//
//        System.out.println("Select the id of the item to be deleted: ");
//        Long idToDelete = scanner.nextLong();
//        tileBoxRepository.deleteById(idToDelete);

    }
}
