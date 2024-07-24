package com.example.diploma.mainMenu.necklaces;

import com.example.diploma.TestBaseClass;
import com.example.diploma.comparators.StoneComparator;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.TestBaseClass;
import com.example.diploma.comparators.StoneComparator;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class SortStonesInNecklaceTest extends TestBaseClass {

    Necklace necklace;
    TreeSet<Stone> stones = new TreeSet<>(new StoneComparator());

    @Override
    public void start(Stage stage) throws Exception {
        necklace = necklaceRepo.getNecklaceById(2);
        data.setCurrentNecklace(necklace);
        stones.addAll(stoneRepo.getStonesByNecklace(necklace).stream().toList());
        openNecklaceMenu();
    }

    @Test
    void sortingStonesInNecklaceTest() {
        List<Long> idSorted = stones.stream().map(Stone::getId).toList();
        List<Long> idNotSorted = getNotSorted();

        assertNotEquals(idSorted, idNotSorted);

        clickOn("#newStoneButton");
        clickOn("#sortButton");

        idNotSorted = getNotSorted();
        assertEquals(idSorted, idNotSorted);
    }

    private List<Long> getNotSorted() {
        return stoneRepo.getStoneByNecklaceAndTransparencyBetweenOrderByPosInNecklace(
                necklace,
                0,
                1,
                Pageable.ofSize(100)).stream().map(Stone::getId).toList();
    }


}
