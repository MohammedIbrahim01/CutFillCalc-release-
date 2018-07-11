package com.example.x.cutfillcalc;

import java.util.ArrayList;
import java.util.List;

public class RowTable {

    private int rows;
    private List<Float> rowRL = new ArrayList<>();
    private List<Float> rowH = new ArrayList<>();

    private float segRL = 0;

    private float segD = 0;
    private float segH = 0;
    private float segHD = 0;
    private float segD2 = 0;
    private float SNS = 0;

    public RowTable(int rows, List<Float> rowRL, List<Float> rowH) {
        this.rows = rows;
        this.rowRL = rowRL;
        this.rowH = rowH;
    }

    public float getSegRL() {
        for (int i = 0; i < rows; i++) {
            segRL += rowRL.get(i);
        }
        return segRL;
    }

    private void getSegs() {

        for (int i = 0; i <= rows; i++) {
            segD += i;
        }

        for (int i = 0; i < rows; i++) {
            segH += rowH.get(i);
        }

        for (int i = 0; i < rows; i++) {
            segHD += (i + 1) * rowH.get(i);
        }

        for (int i = 0; i <= rows; i++) {
            segD2 += i * i;
        }
    }

    public float getSNS() {
        getSegs();

        SNS = (segHD - (segH * segD) / rows) / (segD2 - ((segD * segD) / rows));
        return SNS;
    }

}
