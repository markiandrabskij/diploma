package com.example.diploma.controllers;

import com.example.diploma.comparators.StoneComparator;
import com.example.diploma.models.GetIdAndName;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.comparators.StoneComparator;
import com.example.diploma.models.GetIdAndName;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

@Component
@Lazy
public class ChooseMenuController extends SceneOpener {
    @Autowired
    private StoneRepo stoneRepo;
    @Autowired
    private NecklaceRepo necklaceRepo;

    private int page = 0;

    private Pageable pageable;
    @FXML
    private Button backButton;

    @FXML
    private Button newStoneButton;

    @FXML
    private Button nextPageButton;

    @FXML
    private Button previousPageButton;

    @FXML
    private Button stoneButton1;

    @FXML
    private Button stoneButton2;

    @FXML
    private Button stoneButton3;

    @FXML
    private Button stoneButton4;

    @FXML
    private Button stoneButton5;

    @FXML
    private Button stoneButton6;

    @FXML
    private Button stoneButton7;

    @FXML
    private Button stoneButton8;

    @FXML
    private Button stoneButton9;

    @FXML
    private Button stoneButton10;

    @FXML
    private Button stoneButton11;

    @FXML
    private Button stoneButton12;

    @FXML
    private Text title;

    @FXML
    private Button sortButton;

    private List<Button> buttons;

    @FXML
    void initialize() {
        sortButton.setVisible(false);

        page = 0;
        remap();

        nextPageButton.setOnAction(actionEvent -> {
            page++;
            remap();
        });

        previousPageButton.setOnAction(actionEvent -> {
            page--;
            remap();
        });
    }

    private void remap() {
        pageable = PageRequest.of(page, 12);
        switch (data.getChooseData()){
            case STONES -> remapToStones();
            case NECKLACES -> remapToNecklaces();
            case STONES_NOT_IN_NECKLACE -> remapToAddingToNecklace();
        }
    }

    private void remapToAddingToNecklace() {
        title.setText("Add stone");
        newStoneButton.setText("deleteNecklace");

        sortButton.setVisible(true);
        sortButton.setOnAction(actionEvent -> {
            sortStonesInNecklace();
            openNecklaceMenu(sortButton);
            openDialog("""
                    Каміння в намисті відсортовано за типом,
                    при однаковому типі за вартістю,
                    а при однаковій вартості та типу за назвою""");

        });

        newStoneButton.setOnAction(actionEvent -> {
            stoneRepo.getStonesByNecklace(data.getCurrentNecklace()).forEach(stone -> {
                stone.setNecklace(null);
                stoneRepo.save(stone);
            });

            data.getCurrentNecklace().setStones(null);
            necklaceRepo.delete(data.getCurrentNecklace());

            data.setCurrentNecklace(null);
            openNecklacesMenu(newStoneButton);
        });
        backButton.setOnAction(actionEvent -> returnToNecklace(backButton));

        List<Stone> stones = stoneRepo.findAllByNecklaceIsNull(pageable).stream().toList();
        remapButtons(stones.stream().map(stone -> (GetIdAndName) stone).toList());

        buttons.forEach(button -> button.setOnAction(actionEvent -> {
            data.setCurrentStone(stones.get(Integer.parseInt(button.getId())));
            openStoneNotInNecklace(button);
        }));
    }

    private void sortStonesInNecklace() {
        TreeSet<Stone> stones = new TreeSet<>(new StoneComparator());
        stones.addAll(stoneRepo.getStonesByNecklace(data.getCurrentNecklace()));
        ArrayList<Stone> sortedStones = new ArrayList<>(stones);
        for (int i = 0; i < stones.size(); i++) {
            sortedStones.get(i).setPosInNecklace(i);
            stoneRepo.save(sortedStones.get(i));
        }
    }

    private void remapToNecklaces() {
        title.setText("Necklaces");
        newStoneButton.setText("New necklace");
        newStoneButton.setOnAction(actionEvent -> openCreateNecklace(newStoneButton));
        backButton.setOnAction(actionEvent -> openMainMenu(backButton));

        List<Necklace> necklaces = necklaceRepo.findAll(pageable).stream().toList();
        remapButtons(necklaces.stream().map(necklace -> (GetIdAndName) necklace).toList());

        buttons.forEach(button -> button.setOnAction(actionEvent -> {
            data.setCurrentNecklace(necklaces.get(Integer.parseInt(button.getId())));
            openNecklaceMenu(button);
        }));
    }

    private void remapToStones() {
        title.setText("Stones");
        newStoneButton.setText("New stone");
        newStoneButton.setOnAction(actionEvent -> openCreateStone(newStoneButton));
        backButton.setOnAction(actionEvent -> openMainMenu(backButton));

        List<Stone> stones = stoneRepo.findAll(pageable).stream().toList();
        remapButtons(stones.stream().map(stone -> (GetIdAndName) stone).toList());

        buttons.forEach(button -> button.setOnAction(actionEvent -> {
            data.setCurrentStone(stones.get(Integer.parseInt(button.getId())));
            openStone(button);
        }));
    }

    private void remapButtons(List<GetIdAndName> items){
        buttons = collectButtons();

        previousPageButton.setDisable(page == 0);
        nextPageButton.setDisable(items.size() < 12);

        buttons.forEach(button -> button.setVisible(false));

        for (int i = 0; i < items.size(); i++) {
            buttons.get(i).setId(String.valueOf(i));
            buttons.get(i).setText(items.get(i).getName());
            buttons.get(i).setVisible(true);
        }
    }

    private List<Button> collectButtons(){
        return new ArrayList<>(Arrays.asList(stoneButton1, stoneButton2, stoneButton3, stoneButton4, stoneButton5, stoneButton6, stoneButton7, stoneButton8, stoneButton9, stoneButton10, stoneButton11, stoneButton12));
    }

}
