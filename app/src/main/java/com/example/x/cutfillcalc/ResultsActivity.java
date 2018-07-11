package com.example.x.cutfillcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultsActivity extends AppCompatActivity {

    public static final String KEY_FL_ORIGIN = "key-flOrigin";
    public static final String KEY_SNS = "key-sns";
    public static final String KEY_SWE = "key-swe";
    public static final String KEY_LEVELS = "key-levels";

    private float Swe;
    private float Sns;
    private float FLOrigin;

    private float x;
    private float y;
    private float FL;
    private float levels[][];

    @BindView(R.id.results_textView)
    TextView resultsTextView;
    @BindView(R.id.x_editText)
    EditText xEditText;
    @BindView(R.id.y_editText)
    EditText yEditText;
    @BindView(R.id.final_result_textView)
    TextView finalResultTextView;

    @OnClick(R.id.get_fl_button)
    void getFL() {
        x = Float.valueOf(xEditText.getText().toString());
        y = Float.valueOf(yEditText.getText().toString());

        FL = FLOrigin + Swe * x + Sns * y;
        finalResultTextView.setText("\n At Point (" + x + "," + y + ")  : " );
        finalResultTextView.append("\n FL : " + FL);
        float height = 0;
        height = levels[(int)y-1][(int)x-1] - FL;
        String cutOrFill = "";
        if (height >= 0)
            cutOrFill = "cut";
        else
            cutOrFill = "fill";
        finalResultTextView.append("\n " + cutOrFill + " : " + height);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(KEY_FL_ORIGIN)) {
            Swe = intent.getFloatExtra(KEY_SWE, 0);
            Sns = intent.getFloatExtra(KEY_SNS, 0);
            FLOrigin = intent.getFloatExtra(KEY_FL_ORIGIN, 0);
            levels = (float[][]) intent.getExtras().getSerializable(KEY_LEVELS);
        }

        resultsTextView.append(" Swe : " + Swe);
        resultsTextView.append("\n Sns : " + Sns);
        resultsTextView.append("\n FL at origin : " + FLOrigin);

    }
}
