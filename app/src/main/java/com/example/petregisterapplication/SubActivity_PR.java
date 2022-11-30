package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity_PR extends AppCompatActivity {

    private TextView tv_sub_pr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_pr);

        tv_sub_pr = findViewById(R.id.tv_sub_pr);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv_sub_pr.setText(str);
    }
}