package com.example.diploma;//package com.kursowa.stones;
//
//import com.github.spring.boot.javafx.SpringJavaFXApplication;
//
//import com.kursowa.stones.controller.StageSrc;
//import com.kursowa.stones.repo.StoneRepo;
//import javafx.application.Application;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Profile;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.lang.reflect.Field;
//
//@SpringBootApplication
//public class StonesApplication extends SpringJavaFXApplication {
//	public static void main(String[] args) {
//		launch(StonesApplication.class, args);
//	}
//
//	@Override
//	public void start(Stage primaryStage) throws Exception {
//		applicationContext.start();
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(StageSrc.MAIN_MENU.getUrl()));
//
//
////		fxmlLoader.setControllerFactory(applicationContext::getBean);
////		Parent root = FXMLLoader.load(Objects.requireNonNull(StonesApplication.class.getResource(StageSrc.MAIN_MENU.getUrl())));
//		Parent root = fxmlLoader.load();
//
//		applicationContext.getAutowireCapableBeanFactory().autowireBean(StoneRepo.class);
//
//
//		primaryStage.setTitle("MainMenu");
//		primaryStage.setScene(new Scene(root, 1000, 600));
//		primaryStage.show();
//	}
//
//
//}


