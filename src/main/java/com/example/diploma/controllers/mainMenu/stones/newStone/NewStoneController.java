package com.example.diploma.controllers.mainMenu.stones.newStone;
import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.models.Stone;
import com.example.diploma.models.StoneType;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.models.Stone;
import com.example.diploma.models.StoneType;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.collections.*;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewStoneController extends SceneOpener {
    @Autowired
    private StoneRepo stoneRepo;

    @Autowired
    private StoneTypeRepo typeRepo;

    private Stone stone;

    private List<StoneType> types;

    @FXML
    private Text title;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField pricePerCaratField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField transparencyField;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField weightField;

    @FXML
    void initialize() {
        types = typeRepo.findAll();
        List<String> names = types.stream().map(StoneType::getName).toList();
        typeChoiceBox.setItems(FXCollections.observableList(names));
        typeChoiceBox.setValue(String.valueOf(names.get(0)));

        pricePerCaratField.textProperty().addListener(numberInput(pricePerCaratField));
        weightField.textProperty().addListener(numberInput(weightField));
        transparencyField.textProperty().addListener(doubleInput(transparencyField));

        switch (data.getStoneAction()){
            case EDIT -> editStone();
            case CREATE -> createStone();
        }

        backButton.setOnAction(actionEvent -> openStonesMenu(backButton));
        saveButton.setOnAction(actionEvent -> collectData());
    }

    private void createStone() {
        stone = new Stone();
    }

    private void editStone() {
        title.setText("Edit Stone");
        stone = data.getCurrentStone();
        setFieldValues();
    }

    private void setFieldValues() {
        nameField.setText(stone.getName());
        transparencyField.setText(String.valueOf(stone.getTransparency()));
        weightField.setText(String.valueOf(stone.getWeight()));
        pricePerCaratField.setText(String.valueOf(stone.getPricePerCarat()));
        typeChoiceBox.setValue(stone.getType().getName());

    }

    private void collectData() {
        try {
            if (!fieldsNotBlank())
                throw new RuntimeException(messages.allFieldsMustBeNotEmpty);
            if (Double.parseDouble(transparencyField.getText())>1)
                throw new RuntimeException(messages.invalidTransparency);
            stone.setName(nameField.getText());
            stone.setWeight(Integer.parseInt(weightField.getText()));
            stone.setType(types.stream().filter(stoneType -> stoneType.getName().equals(typeChoiceBox.getSelectionModel().getSelectedItem())).findAny().orElse(null));
            stone.setPricePerCarat(Integer.parseInt(pricePerCaratField.getText()));
            stone.setTransparency(Double.parseDouble(transparencyField.getText()));
            stoneRepo.save(stone);
            openStonesMenu(saveButton);
        } catch (Exception e){
            openDialog(e.getMessage());
        }
    }

    private boolean fieldsNotBlank() {
        return !nameField.getText().isBlank() && !weightField.getText().isBlank() && !pricePerCaratField.getText().isBlank() && !transparencyField.getText().isBlank();
    }
}
