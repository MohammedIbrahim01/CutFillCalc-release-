package com.example.x.cutfillcalc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private int rows = 0;
    private final onButtonClickListener onButtonClickListener;

    public MyAdapter(MyAdapter.onButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    public interface onButtonClickListener {
        void onButtonClick(int row, float level);
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return rows;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.point_editText)
        EditText pointEditText;
        @BindView(R.id.add_point_button)
        Button addPointButton;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            addPointButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    float level = Float.valueOf(pointEditText.getText().toString());
                    onButtonClickListener.onButtonClick(getAdapterPosition(), level);
                    pointEditText.getText().clear();
                }
            });
        }
    }
}
