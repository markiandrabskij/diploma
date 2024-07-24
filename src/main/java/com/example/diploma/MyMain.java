package com.example.diploma;

import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

//@SpringBootApplication
//public class MyMain {
//    public static void main(String[] args) {
//        ConfigurableApplicationContext springApplication = SpringApplication.run(MyMain.class, args);
//        Smth smth = (Smth) springApplication.getBean("smth");
//        smth.dosmth();
//    }
//}

//@Component
//class Smth {
//    private final StoneTypeRepo stoneTypeRepo;
//    private final StoneRepo stoneRepo;
//    private final NecklaceRepo necklaceRepo;
//
//    public Smth(StoneTypeRepo stoneTypeRepo, StoneRepo stoneRepo, NecklaceRepo necklaceRepo) {
//        this.stoneTypeRepo = stoneTypeRepo;
//        this.stoneRepo = stoneRepo;
//        this.necklaceRepo = necklaceRepo;
//    }
//
//    public void dosmth(){
//        necklaceRepo.save(Necklace.builder().name("new").build());
//        List<Necklace> necklaces = necklaceRepo.findAll();
////        System.out.println(stoneRepo.findStonesByNecklaceNot(necklaces.get(0), Pageable.ofSize(5)));
//        Page<Stone> stones =stoneRepo.findAllByNecklaceIsNull(Pageable.ofSize(5));
//        stones.get().forEach(System.out::println);
//    }
//}
