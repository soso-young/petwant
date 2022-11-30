package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity_K extends AppCompatActivity {

    private TextView tv_sub_k;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_k);

        tv_sub_k = findViewById(R.id.tv_sub_k);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv_sub_k.setText(str);
    }
}