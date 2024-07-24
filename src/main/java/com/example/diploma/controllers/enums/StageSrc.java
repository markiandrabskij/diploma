package com.example.diploma.controllers.enums;

public enum StageSrc {
    MAIN_MENU("/static/mainMenu.fxml"), CHOOSE_MENU("/static/chooseMenu.fxml"), STONE("/static/stone.fxml"), NECKLACE("/static/necklace.fxml"), NEW_STONE("/static/newStone.fxml"), NEW_NECKLACE("/static/newNecklace.fxml");

    private final String url;

    StageSrc(String location) {
        this.url = location;
    }

    public String getUrl() {
        return url;
    }
}
