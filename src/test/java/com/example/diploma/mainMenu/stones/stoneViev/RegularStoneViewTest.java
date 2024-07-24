package com.example.diploma.mainMenu.stones.stoneViev;

import com.example.diploma.models.Stone;
import com.example.diploma.TestBaseClass;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.models.Stone;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class RegularStoneViewTest extends TestBaseClass {

    Stone stone;

    @Override
    public void start(Stage stage) throws Exception {
        stone = Stone.builder().id(123).name("test").weight(12).transparency(0.7).pricePerCarat(12).type(stoneTypeRepo.findAll().get(1)).build();
        data.setCurrentStone(stone);
        openStone();
    }


    @Test
    void fieldsMappingTest() {
        assertEquals(stone.getType().getName(), toFxTextField("#typeField").getText());
        assertEquals(String.valueOf(stone.getPricePerCarat()), toFxTextField("#pricePerCaratField").getText());
        assertEquals(String.valueOf(stone.getTransparency()), toFxTextField("#transparencyField").getText());
        assertEquals(String.valueOf(stone.getWeight()), toFxTextField("#weightField").getText());
        assertEquals(stone.getName(), toFxTextField("#nameField").getText());

        assertFalse(toFxTextField("#typeField").editableProperty().getValue());
        assertFalse(toFxTextField("#pricePerCaratField").editableProperty().getValue());
        assertFalse(toFxTextField("#transparencyField").editableProperty().getValue());
        assertFalse(toFxTextField("#weightField").editableProperty().getValue());
        assertFalse(toFxTextField("#nameField").editableProperty().getValue());

        assertEquals("Edit", toFxButton("#editButton").getText());
    }

    @Test
    void backButtonReturnsToStones() {
        clickOn("#backButton");
        assertEquals("Stones", toFxText("#title").getText());
    }

    @Test
    void editButtonOpenEditScene() {
        clickOn("#editButton");
        assertEquals("Save",toFxButton("#saveButton").getText());
    }
}
