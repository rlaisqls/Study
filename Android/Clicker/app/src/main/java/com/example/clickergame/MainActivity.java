package com.example.clickergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button plus, minus, reset;
    TextView result;
    int a, b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = findViewById(R.id.btPlus);
        minus = findViewById(R.id.btMinus);
        reset = findViewById(R.id.btReset);
        result = findViewById(R.id.tvResult);


        result.setText(String.valueOf(a));

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b = Integer.parseInt(result.getText().toString());
                b = b + 1;
                result.setText(String.valueOf(b));
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b = Integer.parseInt(result.getText().toString());
                b = b - 1;
                result.setText(String.valueOf(b));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b = 0;
                result.setText(String.valueOf(b));
            }
        });
    }

}