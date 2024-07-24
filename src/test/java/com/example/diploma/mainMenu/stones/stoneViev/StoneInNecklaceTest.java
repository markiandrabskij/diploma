package com.example.diploma.mainMenu.stones.stoneViev;

import com.example.diploma.models.Stone;
import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class StoneInNecklaceTest extends TestBaseClass {

    Stone stone;
    Necklace necklace;

    long id;

    @Override
    public void start(Stage stage) {
        necklace = necklaceRepo.findAll().get(0);
        data.setCurrentNecklace(necklace);

        stone = stoneRepo.getStonesByNecklace(necklace).stream().findFirst().orElse(null);
        data.setCurrentStone(stone);
        openNecklaceMenu();
    }

    @Test
    void mappingTest() {
        clickOn("#0");
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

        assertEquals("Remove", toFxButton("#editButton").getText());
    }

    @Test
    void backButtonReturnsToNecklace() {
        clickOn("#0");
        clickOn("#backButton");
        assertEquals("total value: 130", toFxText("#valueText").getText());
    }

    @Test
    void editButtonDeleteStoneFromNecklace() {
        clickOn("#0");
        clickOn("#editButton");
        Necklace necklace1 = necklaceRepo.findAll().stream().filter(necklace2 -> necklace2.getName().equals(necklace.getName())).findFirst().orElse(null);
        Set<Stone> stones = stoneRepo.getStonesByNecklace(necklace1);
        assertEquals(0, stones.size());
    }
}
