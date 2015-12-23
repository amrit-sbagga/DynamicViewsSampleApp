package com.amrit.dynamicviewssampleapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etTextIn;
    private Button btnAdd;
    private LinearLayout llContainer;
    private TextView tvReList;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTextIn = (EditText) findViewById(R.id.etTextIn);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        llContainer = (LinearLayout) findViewById(R.id.llContainer);
        tvReList = (TextView) findViewById(R.id.tvReList);
        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo.setMovementMethod(new ScrollingMovementMethod());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                LayoutInflater layoutInflater =
                        (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row_layout, null);
                TextView tvOut = (TextView) addView.findViewById(R.id.tvOut);
                tvOut.setText(etTextIn.getText().toString());
                Button btnRemove = (Button) addView.findViewById(R.id.btnRemove);

                final View.OnClickListener thisListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tvInfo.append("thisListener called:\t" + this + "\n");
                        tvInfo.append("Remove addView: " + addView + "\n\n");
                        ((LinearLayout) addView.getParent()).removeView(addView);

                        listAllAddView();
                    }
                };

                btnRemove.setOnClickListener(thisListener);
                llContainer.addView(addView);

                tvInfo.append(
                        "thisListener:\t" + thisListener + "\n"
                                + "addView:\t" + addView + "\n\n"
                );

                listAllAddView();

            }
        });

    }

    private void listAllAddView() {
        tvReList.setText("");

        int childCount = llContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View thisChild = llContainer.getChildAt(i);
            tvReList.append(thisChild + "\n");
        }
    }
}
