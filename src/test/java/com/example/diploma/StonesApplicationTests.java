package com.example.diploma;

import com.example.diploma.controllers.enums.ChooseData;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StonesApplicationTests extends TestBaseClass {

	@Override
	public void start(Stage stage) {
		openMainMenu();
	}

	@Test
	void contextLoads() {
		clickOn("#necklaceButton");
		assertEquals(ChooseData.NECKLACES, data.getChooseData());
		assertEquals("Necklaces", toFxText("#title").getText());
		assertEquals("New necklace", toFxButton("#newStoneButton").getText());
	}

}
