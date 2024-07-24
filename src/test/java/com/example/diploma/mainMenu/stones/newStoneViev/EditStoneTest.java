package com.example.diploma.mainMenu.stones.newStoneViev;

import com.example.diploma.TestBaseClass;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.models.Stone;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EditStoneTest extends TestBaseClass {
    Stone stone;

    @Override
    public void start(Stage stage) {
        stone = Stone.builder().id(123).weight(12).transparency(0.7).pricePerCarat(12).type(stoneTypeRepo.findAll().get(1)).build();
        data.setCurrentStone(stone);
        openEditStone();
    }

    @Test
    void fieldsMappingTest() {
        assertEquals(stone.getType().getName(), toFxChoiceBox("#typeChoiceBox").getValue());
        assertEquals(String.valueOf(stone.getPricePerCarat()), toFxTextField("#pricePerCaratField").getText());
        assertEquals(String.valueOf(stone.getTransparency()), toFxTextField("#transparencyField").getText());
        assertEquals(String.valueOf(stone.getWeight()), toFxTextField("#weightField").getText());
        assertEquals(stone.getName(), toFxTextField("#nameField").getText());
    }

    @Test
    void editStoneTest() {
        stoneRepo.save(stone);
        stone.setName("changed");
        toFxTextField("#nameField").setText(stone.getName());

        clickOn("#saveButton");

        assertTrue(stoneRepo.findAll().contains(stone));
    }
}
