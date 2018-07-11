package com.example.x.cutfillcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MyAdapter.onButtonClickListener {

    public static final String KEY_FL_ORIGIN = "key-flOrigin";
    public static final String KEY_SNS = "key-sns";
    public static final String KEY_SWE = "key-swe";
    public static final String KEY_LEVELS = "key-levels";

    @BindView(R.id.rows_editText)
    EditText rowsEditText;
    @BindView(R.id.columns_EditText)
    EditText columnsEditText;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.test)
    TextView test;

    private int rows;
    private int columns;
    private MyAdapter adapter = new MyAdapter(this);
    private float levels[][];
    private int currColumn = 0;

    private List<Float> columnRL = new ArrayList<>();
    private List<Float> columnH = new ArrayList<>();
    private List<Float> rowRL = new ArrayList<>();
    private List<Float> rowH = new ArrayList<>();

    private Table table;

    @OnClick(R.id.submit_button)
    void start() {
        rows = Integer.valueOf(rowsEditText.getText().toString());
        columns = Integer.valueOf(columnsEditText.getText().toString());

        levels = new float[rows][columns];

        adapter.setRows(rows);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.calculate_fab)
    void calculateAndGoToResultsActivity() {
        getRlH();

        table = new Table(rows, columns, columnRL, columnH, rowRL, rowH);
        table.generateSNS();
        table.generateSWE();

        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_LEVELS, levels);

        Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
        intent.putExtra(KEY_SWE, table.getSWE());
        intent.putExtra(KEY_SNS, table.getSNS());
        intent.putExtra(KEY_FL_ORIGIN, table.getFLOriginal());
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void getRlH() {

        //for Columns
        for (int i = 0; i < columns; i++) {
            float currValue = 0;
            for (int j = 0; j < rows; j++) {
                currValue += levels[j][i];
            }
            columnRL.add(currValue);
        }

        for (int i = 0; i < columns; i++) {
            columnH.add(columnRL.get(i) / rows);
        }

        //for Rows
        for (int i = 0; i < rows; i++) {
            float currValue = 0;
            for (int j = 0; j < columns; j++) {
                currValue += levels[i][j];////////////////////
            }
            rowRL.add(currValue);
        }

        for (int i = 0; i < rows; i++) {
            rowH.add(rowRL.get(i) / columns);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onButtonClick(int row, float level) {
        levels[row][currColumn++] = level;
        if (currColumn == columns)
            currColumn = 0;
    }
}
