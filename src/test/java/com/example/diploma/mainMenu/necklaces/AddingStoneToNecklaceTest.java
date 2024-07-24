package com.example.diploma.mainMenu.necklaces;

import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import javafx.stage.Stage;
import org.hibernate.annotations.Source;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddingStoneToNecklaceTest extends TestBaseClass {
    Necklace necklace;

    Stone stone;

    @Override
    public void start(Stage stage) throws Exception {

        necklace = necklaceRepo.getNecklaceById(1);
        stoneRepo.getStonesByNecklace(necklace).forEach(stone1 -> {
            stone1.setNecklace(null);
            stone = stoneRepo.save(stone1);
        });
        data.setCurrentNecklace(necklace);
        openNecklaceMenu();
    }

    @Test
    void addingStoneToNecklace() {
        Assertions.assertEquals(0, stoneRepo.getStonesByNecklace(necklace).size());
        clickOn("#newStoneButton");
        clickOn("#0");
        clickOn("#editButton");
        Assertions.assertEquals(1, stoneRepo.getStonesByNecklace(necklace).size());
    }
}
