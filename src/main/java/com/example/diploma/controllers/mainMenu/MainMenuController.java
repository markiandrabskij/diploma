package com.example.diploma.controllers.mainMenu;

import com.example.diploma.controllers.enums.ChooseData;
import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.controllers.enums.StageSrc;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
@Lazy
public class MainMenuController extends SceneOpener {
    @FXML
    private Button necklaceButton;

    @FXML
    private Button stonesButton;

    @FXML
    void initialize() {
        necklaceButton.setOnAction(actionEvent ->{
            data.setChooseData(ChooseData.NECKLACES);
            openNecklacesMenu(necklaceButton);
        });
        stonesButton.setOnAction(actionEvent ->{
            data.setChooseData(ChooseData.STONES);
            openStonesMenu(stonesButton);
        });
    }

}
