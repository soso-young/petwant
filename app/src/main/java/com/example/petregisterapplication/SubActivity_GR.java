package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity_GR extends AppCompatActivity {

    private TextView tv_sub_gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_gr);

        tv_sub_gr = findViewById(R.id.tv_sub_gr);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv_sub_gr.setText(str);
    }
}