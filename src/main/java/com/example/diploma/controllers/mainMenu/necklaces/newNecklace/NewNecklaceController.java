package com.example.diploma.controllers.mainMenu.necklaces.newNecklace;

import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.models.Necklace;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.models.Necklace;
import com.example.diploma.repositories.NecklaceRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewNecklaceController extends SceneOpener {

    @Autowired
    private NecklaceRepo necklaceRepo;
    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize(){
        backButton.setOnAction(actionEvent -> openNecklacesMenu(backButton));

        saveButton.setOnAction(actionEvent -> {
            if (!nameField.getText().isBlank())
                necklaceRepo.save(Necklace.builder().name(nameField.getText()).build());
            else openDialog(messages.allFieldsMustBeNotEmpty);
            openNecklacesMenu(saveButton);
        });
    }
}
