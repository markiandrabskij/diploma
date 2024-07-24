package com.example.diploma.controllers;

import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import com.example.diploma.controllers.enums.ChooseData;
import com.example.diploma.controllers.enums.StoneAction;
import com.example.diploma.models.Necklace;
import com.example.diploma.models.Stone;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class Data {
    Necklace currentNecklace;
    Stone CurrentStone;
    ChooseData chooseData;
    String dialogText;
    StoneAction stoneAction;
}
