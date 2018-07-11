package com.example.x.cutfillcalc;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private int rows;
    private int columns;

    private int points;
    private float levelCG;
    private float Xcg;
    private float Ycg;
    private float FLOriginal;

    private float SWE;
    private float SNS;

    private List<Float> columnRL = new ArrayList<>();
    private List<Float> columnH = new ArrayList<>();
    private List<Float> rowRL = new ArrayList<>();
    private List<Float> rowH = new ArrayList<>();

    private ColumnTable columnTable;
    private RowTable rowTable;


    public Table(int rows, int columns, List<Float> columnRL, List<Float> columnH, List<Float> rowRL, List<Float> rowH) {
        this.rows = rows;
        this.columns = columns;
        this.columnRL = columnRL;
        this.columnH = columnH;
        this.rowRL = rowRL;
        this.rowH = rowH;
        columnTable = new ColumnTable(columns, columnRL, columnH);
        rowTable = new RowTable(rows, rowRL, rowH);
    }

    public void generateSWE() {
        SWE = columnTable.getSWE();
    }

    public void generateSNS() {
        SNS = rowTable.getSNS();
    }

    public float getSWE() {
        return SWE;
    }

    public float getSNS() {
        return SNS;
    }

    public float getFLOriginal() {

        points = rows * columns;
        levelCG = rowTable.getSegRL() / points;
        Xcg = (columns + 1) / 2f;
        Ycg = (rows + 1) / 2f;

        FLOriginal = levelCG - (SWE * Xcg) - (SNS * Ycg);
        Log.i("WWW", "levelCG: " + levelCG + " Xcg: " + Xcg + " Ycg: " + Ycg + " Swe: " + SWE + " SNS: " + SNS);

        return FLOriginal;

    }
}
