package com.example.diploma.mainMenu.necklaces;

import com.example.diploma.TestBaseClass;
import com.example.diploma.models.Necklace;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreateNecklaceTest extends TestBaseClass {
    @Override
    public void start(Stage stage) {
        openCreateNecklace();
    }

    public static Stream<Arguments> names() {
        return Stream.of( Arguments.of("Created Necklace", true),
                Arguments.of(" ", false),
                Arguments.of("", false));
    }

    @ParameterizedTest
    @MethodSource("names")
    void creatingNecklaceTest(String name, boolean created) {
        toFxTextField("#nameField").setText(name);
        clickOn("#saveButton");
        Necklace necklace = necklaceRepo.findAll().stream().filter(necklace1 -> necklace1.getName().equals(name)).findFirst().orElse(null);
//        necklaceRepo.findAll().forEach(System.out::println);
        if (created) {
            assertNotNull(necklace);
            necklaceRepo.delete(necklace);
        } else assertNull(necklace);


    }
}
