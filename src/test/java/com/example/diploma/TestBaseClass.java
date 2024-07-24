package com.example.diploma;

import com.example.diploma.controllers.Data;
import com.example.diploma.controllers.mainMenu.necklaces.necklace.NecklaceController;
import com.example.diploma.messages.Messages;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import com.example.diploma.controllers.Data;
import com.example.diploma.controllers.enums.ChooseData;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.controllers.enums.StoneAction;
import com.example.diploma.controllers.mainMenu.necklaces.necklace.NecklaceController;
import com.example.diploma.messages.Messages;
import com.example.diploma.repositories.NecklaceRepo;
import com.example.diploma.repositories.StoneRepo;
import com.example.diploma.repositories.StoneTypeRepo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.IOException;

import static org.testfx.api.FxAssert.assertContext;

@Component
public abstract class TestBaseClass extends ApplicationTest {

    @Autowired
    public Messages messages;

    @Autowired
    public Data data;

    @Autowired
    public StoneRepo stoneRepo;

    @Autowired
    public NecklaceRepo necklaceRepo;

    @Autowired
    public StoneTypeRepo stoneTypeRepo;
    @Autowired
    public ApplicationContext applicationContext;

    static <T extends Node> T toNode(String nodeQuery) {
        org.testfx.service.finder.NodeFinder nodeFinder = assertContext().getNodeFinder();
        return nodeFinder.lookup(nodeQuery).query();
    }

    public static Text toFxText(String nodeQuery) {
        return toNode(nodeQuery);
    }

    public static Button toFxButton(String nodeQuery) {
        return toNode(nodeQuery);
    }

    public static TextField toFxTextField(String nodeQuery) {
        return toNode(nodeQuery);
    }

    public static ChoiceBox<String> toFxChoiceBox(String nodeQuery) {
        return toNode(nodeQuery);
    }
    @SneakyThrows
    private void openNewScene(StageSrc stageSrc){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(stageSrc.getUrl()));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        Parent root = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 1000, 600));
        stage.setResizable(false);
        stage.show();
    }

    public void openDialog(String text){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/static/dialog.fxml"));
        fxmlLoader.setControllerFactory(applicationContext::getBean);
        data.setDialogText(text);
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void openStonesMenu(){
        data.setChooseData(ChooseData.STONES);
        openNewScene(StageSrc.CHOOSE_MENU);
    }

    public void openNecklacesMenu(){
        data.setChooseData(ChooseData.NECKLACES);
        openNewScene(StageSrc.CHOOSE_MENU);
    }

    public void openEditNecklaceMenu(){
        data.setChooseData(ChooseData.STONES_NOT_IN_NECKLACE);
        openNewScene(StageSrc.CHOOSE_MENU);
    }

    public void openStoneNotInNecklace(){
        data.setStoneAction(StoneAction.ADD_TO_NECKLACE);
        openNewScene(StageSrc.STONE);
    }

    public void openEditStone(){
        data.setStoneAction(StoneAction.EDIT);
        openNewScene(StageSrc.NEW_STONE);
    }

    public void openCreateStone(){
        data.setStoneAction(StoneAction.CREATE);
        openNewScene(StageSrc.NEW_STONE);
    }

    public void openNecklaceMenu(){
        NecklaceController necklaceController = (NecklaceController) applicationContext.getBean("necklaceController");
        necklaceController.setPageToZero();
        openNewScene(StageSrc.NECKLACE);
    }

    public void returnToNecklace(){
        openNewScene(StageSrc.NECKLACE);
    }


    public void openStoneInNecklace(){
        data.setStoneAction(StoneAction.IN_NECKLACE);
        openNewScene(StageSrc.STONE);
    }

    public void openStone(){
        data.setStoneAction(StoneAction.VIEW);
        openNewScene(StageSrc.STONE);
    }

    public void openCreateNecklace(){
        openNewScene(StageSrc.NEW_NECKLACE);
    }

    public void openMainMenu(){
        openNewScene(StageSrc.MAIN_MENU);
    }

}
