package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    protected Button button;
    public String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et);
        button = findViewById(R.id.bt);

        ArrayList<String> input = new ArrayList<String>();
        RecyclerView recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = editText.getText().toString();
                if (a.length() == 0) {
                    Toast.makeText(view.getContext(), "문자열이 비어있습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    input.add(a);
                    RecyclerAdapter adapter = new RecyclerAdapter(input);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}