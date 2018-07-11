package com.example.x.cutfillcalc;

import java.util.ArrayList;
import java.util.List;

public class ColumnTable {

    private int columns;
    private List<Float> columnRL = new ArrayList<>();
    private List<Float> columnH = new ArrayList<>();

    private float segRL = 0;

    private float segD = 0;
    private float segH = 0;
    private float segHD = 0;
    private float segD2 = 0;
    private float SWE = 0;

    public ColumnTable(int columns, List<Float> columnRL, List<Float> columnH) {
        this.columns = columns;
        this.columnRL = columnRL;
        this.columnH = columnH;
    }

    private void getSegRL() {
        for (int i = 0; i < columns; i++) {
            segRL += columnRL.get(i);
        }
    }

    private void getSegs() {

        for (int i = 0; i <= columns; i++) {
            segD += i;
        }

        for (int i = 0; i < columns; i++) {
            segH += columnH.get(i);
        }

        for (int i = 0; i < columns; i++) {
            segHD += (i + 1) * columnH.get(i);
        }

        for (int i = 0; i <= columns; i++) {
            segD2 += i * i;
        }
    }

    public float getSWE() {
        getSegs();

        SWE = (segHD - (segH * segD) / columns) / (segD2 - ((segD * segD) / columns));
        return SWE;
    }

}
