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
        //memo_insert activity(사용자가 문장을 입력하면 pet want팀의 firebase에 저장이 되는 layout)

        btnInsertData = findViewById(R.id.btnInsertData);
        btnInsertData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnInsertData:        //관리자에게 전달이라는 버튼을 누르면
                startActivity(new Intent(memo_activity.this,memoInsert.class));
                //memo activity에서 memoInsert activity로 화면 전환.
                break;
        }
    }
}



