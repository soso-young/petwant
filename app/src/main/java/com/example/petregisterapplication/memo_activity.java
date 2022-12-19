package com.example.petregisterapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class memo_activity extends AppCompatActivity implements View.OnClickListener {

    Button btnInsertData;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_insert);

        btnInsertData = findViewById(R.id.btnInsertData);
        btnInsertData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnInsertData:
                startActivity(new Intent(memo_activity.this,memoInsert.class));
                break;
        }
    }
}



