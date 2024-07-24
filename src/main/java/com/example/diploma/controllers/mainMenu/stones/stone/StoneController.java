package com.example.diploma.controllers.mainMenu.stones.stone;

import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class StoneController extends SceneOpener {

    @Autowired
    StoneRepo stoneRepo;

    @Autowired
    NecklaceRepo necklaceRepo;

    private Stone stone;

    @FXML
    private Button backButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField pricePerCaratField;

    @FXML
    private Button editButton;

    @FXML
    private TextField transparencyField;

    @FXML
    private TextField typeField;

    @FXML
    private TextField weightField;

    @FXML
    void initialize() {
        stone = data.getCurrentStone();
        prepareFields();

        switch (data.getStoneAction()){
            case VIEW -> viewStone();
            case IN_NECKLACE -> stoneInNecklace();
            case ADD_TO_NECKLACE -> addingToNecklace();

        }
    }

    private void viewStone() {
        backButton.setOnAction(actionEvent -> openStonesMenu(backButton));
        editButton.setOnAction(actionEvent -> openEditStone(editButton));
    }

    private void addingToNecklace() {
        Necklace necklace = necklaceRepo.getNecklaceById(data.getCurrentNecklace().getId());

        editButton.setText("Add");
        editButton.setOnAction(actionEvent -> {
            stone.setNecklace(necklace);
            stone.setPosInNecklace(necklace.getNextStonePos());
            necklace.setNextStonePos(necklace.getNextStonePos()+1);
            necklaceRepo.save(necklace);
            stoneRepo.save(data.getCurrentStone());
            returnToNecklace(editButton);
        });
        backButton.setOnAction(actionEvent -> returnToNecklace(backButton));
    }

    private void stoneInNecklace() {
        editButton.setText("Remove");
        editButton.setOnAction(actionEvent -> {
            stone.setNecklace(null);
            stoneRepo.save(stone);
            returnToNecklace(editButton);
        });
        backButton.setOnAction(actionEvent -> returnToNecklace(backButton));
    }

    private void prepareFields() {
        nameField.setText(stone.getName());
        pricePerCaratField.setText(String.valueOf(stone.getPricePerCarat()));
        transparencyField.setText(String.valueOf(stone.getTransparency()));
        weightField.setText(String.valueOf(stone.getWeight()));
        typeField.setText(stone.getType().getName());
    }

}