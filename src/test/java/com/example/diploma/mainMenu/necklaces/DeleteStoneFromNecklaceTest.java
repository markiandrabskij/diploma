package com.example.diploma.mainMenu.necklaces;

import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import javafx.stage.Stage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeleteStoneFromNecklaceTest extends TestBaseClass {

    Necklace necklace;

    @Override
    public void start(Stage stage){
        necklace = necklaceRepo.findAll().get(0);
        data.setCurrentNecklace(necklace);
        openNecklaceMenu();
    }

//    @Test
//    void deletingStoneFromNecklaceTest() {
//        Assertions.assertEquals(1, stoneRepo.getStonesByNecklace(necklace).size());
//        clickOn("#0");
//        clickOn("#editButton");
//        Assertions.assertEquals(0, stoneRepo.getStonesByNecklace(necklace).size());
//    }
}
