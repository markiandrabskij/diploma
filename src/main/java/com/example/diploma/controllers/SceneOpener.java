package com.example.diploma.controllers;

import com.example.diploma.messages.Messages;
import com.example.diploma.controllers.enums.ChooseData;
import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.controllers.enums.StoneAction;
import com.example.diploma.controllers.mainMenu.necklaces.necklace.NecklaceController;
import com.example.diploma.messages.Messages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Setter
public abstract class SceneOpener {
    @Autowired
    public ApplicationContext applicationContext;
    @Autowired
    public Messages messages;
    @Autowired
    public Data data;

    @SneakyThrows
    private void openNewScene(Node anyNode, StageSrc stageSrc){
        anyNode.getScene().getWindow().hide();
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

    public void openStonesMenu(Node node){
        data.setChooseData(ChooseData.STONES);
        openNewScene(node, StageSrc.CHOOSE_MENU);
    }

    public void openNecklacesMenu(Node node){
        data.setChooseData(ChooseData.NECKLACES);
        openNewScene(node, StageSrc.CHOOSE_MENU);
    }

    public void openEditNecklaceMenu(Node node){
        data.setChooseData(ChooseData.STONES_NOT_IN_NECKLACE);
        openNewScene(node, StageSrc.CHOOSE_MENU);
    }

    public void openStoneNotInNecklace(Node node){
        data.setStoneAction(StoneAction.ADD_TO_NECKLACE);
        openNewScene(node, StageSrc.STONE);
    }

    public void openEditStone(Node node){
        data.setStoneAction(StoneAction.EDIT);
        openNewScene(node, StageSrc.NEW_STONE);
    }

    public void openCreateStone(Node node){
        data.setStoneAction(StoneAction.CREATE);
        openNewScene(node, StageSrc.NEW_STONE);
    }

    public void openNecklaceMenu(Node node){
        NecklaceController necklaceController = (NecklaceController) applicationContext.getBean("necklaceController");
        necklaceController.setPageToZero();
        openNewScene(node, StageSrc.NECKLACE);
    }

    public void returnToNecklace(Node node){
        openNewScene(node, StageSrc.NECKLACE);
    }


    public void openStoneInNecklace(Node node){
        data.setStoneAction(StoneAction.IN_NECKLACE);
        openNewScene(node, StageSrc.STONE);
    }

    public void openStone(Node node){
        data.setStoneAction(StoneAction.VIEW);
        openNewScene(node, StageSrc.STONE);
    }

    public void openCreateNecklace(Node node){
        openNewScene(node, StageSrc.NEW_NECKLACE);
    }

    public void openMainMenu(Node node){
        openNewScene(node, StageSrc.MAIN_MENU);
    }

    protected static ChangeListener<String> numberInput (TextField node){
        return new ChangeListener<>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    node.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        };
    }

    protected static ChangeListener<String> doubleInput(TextField node){
        return new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                NecklaceController.matchesDouble(newValue, node);
            }
        };
    }

    public static String matchesDouble(String newValue, TextField node) {
        if (!newValue.matches("[0-1](\\.[0-9]*)?")) {
            node.setText(newValue.replaceAll("[^\\d.]", ""));
            StringBuilder aus = new StringBuilder(newValue);
            boolean firstPointFound = false;
            for (int i = 0; i < aus.length(); i++){
                if(aus.charAt(i) == '.') {
                    if(!firstPointFound)
                        firstPointFound = true;
                    else
                        aus.deleteCharAt(i);
                }
            }
            newValue = aus.toString();
        }
        return newValue;
    }
}
