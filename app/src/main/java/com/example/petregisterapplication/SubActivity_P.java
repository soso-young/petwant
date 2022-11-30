package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity_P extends AppCompatActivity {

    private TextView tv_sub_p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_p);

        tv_sub_p = findViewById(R.id.tv_sub_p);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        tv_sub_p.setText(str);
    }
}