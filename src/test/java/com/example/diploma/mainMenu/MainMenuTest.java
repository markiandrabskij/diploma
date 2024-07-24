package com.example.diploma.mainMenu;

import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.TestBaseClass;
import com.example.diploma.controllers.enums.ChooseData;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MainMenuTest extends TestBaseClass {

    @Autowired
    NecklaceRepo necklaceRepo;

    @Autowired
    StoneRepo stoneRepo;

    @Override
    public void start(Stage stage) {
        openMainMenu();
    }

    @Test
    void necklaceButtonOpensChooseWindowWithNecklacesTest() {
        clickOn("#necklaceButton");
        assertEquals(ChooseData.NECKLACES, data.getChooseData());
        assertEquals("Necklaces", toFxText("#title").getText());
        List<Necklace> necklaceList = necklaceRepo.findAll();
        assertEquals(necklaceList.get(0).getName(), toFxButton("#0").getText());
        assertEquals(necklaceList.get(1).getName(), toFxButton("#1").getText());
    }

    @Test
    void necklaceButtonOpensChooseWindowWithStonesTest() {
        clickOn("#stonesButton");
        assertEquals(ChooseData.STONES, data.getChooseData());
        assertEquals("Stones", toFxText("#title").getText());
        List<Stone> stonesList = stoneRepo.findAll();
        assertEquals(stonesList.get(0).getName(), toFxButton("#0").getText());
        assertEquals(stonesList.get(1).getName(), toFxButton("#1").getText());
    }
}
