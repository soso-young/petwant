package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity_CH extends AppCompatActivity {

    private TextView tv_sub_ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_ch);

        tv_sub_ch = findViewById(R.id.tv_sub_ch);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv_sub_ch.setText(str);
    }
}