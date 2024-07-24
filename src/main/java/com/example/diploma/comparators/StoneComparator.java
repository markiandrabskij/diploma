package com.example.diploma.comparators;

import com.example.diploma.models.Stone;

import java.util.Comparator;

public class StoneComparator implements Comparator<Stone> {

    @Override
    public int compare(Stone o1, Stone o2) {
        if (o1.getType().getValue()!=o2.getType().getValue()) {
            return Integer.compare(o1.getType().getValue(), o2.getType().getValue());
        } else if (o1.getPricePerCarat() != o2.getPricePerCarat())
            return Integer.compare(o1.getPricePerCarat(), o2.getPricePerCarat());
        else
            return o1.getName().compareTo(o2.getName());
    }
}
