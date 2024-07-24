package com.example.diploma.controllers.mainMenu.necklaces.necklace;

import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.models.StoneType;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import com.example.diploma.controllers.SceneOpener;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.models.StoneType;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class NecklaceController extends SceneOpener {

    @Autowired
    private NecklaceRepo necklaceRepo;
    @Autowired
    private StoneRepo stoneRepo;

    @Autowired
    private StoneTypeRepo stoneTypeRepo;

    private Necklace necklace;

    int page = 0;

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
    private Button stoneButton11;

    @FXML
    private Button stoneButton2;

    @FXML
    private Button stoneButton3;

    @FXML
    private Text stoneText1;

    @FXML
    private Text stoneText2;

    @FXML
    private Text stoneText3;

    @FXML
    private Text stoneText4;

    @FXML
    private Text title;

    @FXML
    private Text valueText;

    @FXML
    private Text weightText;

    @FXML
    private TextField bound1;

    @FXML
    private TextField bound2;

    @FXML
    private Line firstLine;

    @FXML
    private Line secondLine;

    @FXML
    private Line thirdLine;

    @FXML
    void initialize() {
        bound1.setText("0");
        bound2.setText("1");

        bound1.textProperty().addListener(doubleInputWithRemap(bound1));
        bound2.textProperty().addListener(doubleInputWithRemap(bound2));

        necklace = necklaceRepo.getNecklaceById(data.getCurrentNecklace().getId());
        remapButtons();
        title.setText(necklace.getName());
        valueText.setText(String.format("total value: %s",getTotalValue()));
        weightText.setText(String.format("total weight: %s", getTotalWeight()));

        nextPageButton.setOnAction(actionEvent -> {
            page++;
            remapButtons();
        });
        previousPageButton.setOnAction(actionEvent -> {
            page--;
            remapButtons();
        });

        newStoneButton.setOnAction(actionEvent -> openEditNecklaceMenu(newStoneButton));
        backButton.setOnAction(actionEvent -> openNecklacesMenu(backButton));
    }

    private void remapButtons() {
        Pageable pageable = PageRequest.of(page, 4);
        List<Button> buttons = collectButtons();
        List<Stone> stones = stoneRepo.getStoneByNecklaceAndTransparencyBetweenOrderByPosInNecklace(data.getCurrentNecklace(), Double.parseDouble(bound1.getText()), Double.parseDouble(bound2.getText()), pageable).stream().toList();

        nextPageButton.setDisable(stones.size() < 4 || necklace.getStones().size() == (page+1)*4);
        previousPageButton.setDisable(page == 0);

        List<Text> texts = collectTexts();

        texts.forEach(text -> text.setVisible(false));
        buttons.forEach(button -> button.setVisible(false));

        firstLine.setVisible(false);
        secondLine.setVisible(false);
        thirdLine.setVisible(false);

        for (int i = 0; i < stones.size(); i++) {
            buttons.get(i).setId(String.valueOf(i));
            texts.get(i).setText(stones.get(i).getName());
            setImg(buttons.get(i), stones.get(i));
            buttons.get(i).setVisible(true);
            texts.get(i).setVisible(true);
        }

        buttons.forEach(button -> button.setOnAction(actionEvent -> {
            data.setCurrentStone(stones.get(Integer.parseInt(button.getId())));
            openStoneInNecklace(button);
        }));
    }

    private void setImg(Button button, Stone stone) {
        List <StoneType> types = stoneTypeRepo.findAll();
        StoneType type = types.stream().filter(stoneType -> stoneType.getName().equals(stone.getType().getName())).findFirst().orElse(types.get(0));
        String path = String.format("/img/%s.png",type.getName());
        BackgroundImage backgroundImage = new BackgroundImage(new Image(getClass().getResource(path).toExternalForm()), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setLine(button);
        Background background = new Background(backgroundImage);
        button.setBackground(background);

    }

    private void setLine(Button button) {
        if (stoneButton11.equals(button)) {
            firstLine.setVisible(true);
        } else if (stoneButton1.equals(button)) {
            secondLine.setVisible(true);
        } else if (stoneButton2.equals(button)) {
            thirdLine.setVisible(true);
        }
    }

    private List<Text> collectTexts() {
        return new ArrayList<>(Arrays.asList(stoneText1,stoneText2,stoneText3,stoneText4));
    }

    private String getTotalWeight() {
        return String.valueOf(
                necklace.getStones().stream()
                        .map(Stone::getWeight)
                        .mapToInt(Integer::intValue)
                        .sum()
        );
    }

    private String getTotalValue() {
        return String.valueOf(
                necklace.getStones().stream()
                        .map(
                                stone -> stone.getWeight()*stone.getPricePerCarat()
                        ).mapToInt(Integer::intValue)
                        .sum()
        );
    }

    private List<Button> collectButtons(){
        return new ArrayList<>(Arrays.asList(stoneButton11, stoneButton1, stoneButton2, stoneButton3));
    }

    private ChangeListener<String> doubleInputWithRemap(TextField node){
        return new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                newValue = matchesDouble(newValue, node);
                try {
                    Double.parseDouble(newValue);
                    remapButtons();
                } catch (Exception e) {
//                do nothing
                }
            }
        };
    }

    public void setPageToZero() {
        this.page = 0;
    }
}
