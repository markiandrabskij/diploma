package com.example.diploma.mainMenu.stones.newStoneViev;

import com.example.diploma.TestBaseClass;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.models.Stone;
import com.example.diploma.models.StoneType;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreateStoneTest extends TestBaseClass {

    @Override
    public void start(Stage stage) {
        openCreateStone();
    }

    @Test
    void createValidStoneTest() {
        assertEquals("Save", toFxButton("#saveButton").getText());

        List<StoneType> typeList = stoneTypeRepo.findAll();

        Stone stone = Stone.builder().id(123L).name("test").type(typeList.get(0)).pricePerCarat(12).transparency(0.2).weight(12).build();

        toFxTextField("#nameField").setText(stone.getName());
        toFxTextField("#pricePerCaratField").setText(String.valueOf(stone.getPricePerCarat()));
        toFxTextField("#transparencyField").setText(String.valueOf(stone.getTransparency()));
        toFxTextField("#weightField").setText(String.valueOf(stone.getWeight()));

//        Not on FX application thread; currentThread = main
//        toFxChoiceBox("#typeChoiceBox").setValue(typeList.get(1).getName());

        clickOn("#saveButton");

        assertNotNull(stoneRepo.findAll().stream().filter(stone1 -> stone1.equals(stone)).findFirst().orElse(null));
    }

    @Test
    void createStoneWithBlankFieldsTest() {
        assertEquals("Save", toFxButton("#saveButton").getText());

        toFxTextField("#nameField").setText("name");
        toFxTextField("#transparencyField").setText("0.3");

        clickOn("#saveButton");

        assertEquals(messages.allFieldsMustBeNotEmpty, data.getDialogText());
    }

    @Test
    void createStoneWithInvalidTransparencyFieldTest() {
        assertEquals("Save", toFxButton("#saveButton").getText());

        toFxTextField("#nameField").setText("Name");
        toFxTextField("#pricePerCaratField").setText("1");
        toFxTextField("#transparencyField").setText("2.3");
        toFxTextField("#weightField").setText("12");

        clickOn("#saveButton");

        assertEquals(messages.invalidTransparency, data.getDialogText());
    }
}
