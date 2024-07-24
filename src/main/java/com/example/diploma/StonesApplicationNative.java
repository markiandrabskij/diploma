package com.example.diploma;

import com.example.diploma.controllers.enums.StageSrc;
import com.example.diploma.repositories.StoneRepo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StonesApplicationNative extends Application {

    private ConfigurableApplicationContext springContext;
    private Parent root;

    @Override
    public void init() throws Exception {
        springContext = SpringApplication.run(StonesApplicationNative.class);
        springContext
                .getAutowireCapableBeanFactory()
                .autowireBeanProperties(
                        this,
                        AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE,
                        true
                );
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(StageSrc.MAIN_MENU.getUrl()));
        fxmlLoader.setControllerFactory(springContext::getBean);
        springContext.getAutowireCapableBeanFactory().autowireBean(StoneRepo.class);
        root = fxmlLoader.load();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//		primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }


    public static void main(String[] args) {
        launch(StonesApplicationNative.class, args);
    }
}
