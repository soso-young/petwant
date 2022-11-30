package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class helathnote extends AppCompatActivity {

    private EditText  et_info;
    private Button btn_info;
    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helathnote);

        et_info = findViewById(R.id.et_info);

        btn_info = findViewById(R.id.btn_info);
        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = et_info.getText().toString();

                if(str.equals("말티즈")){
                    Intent intent = new Intent(helathnote.this, SubActivity_ML.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

                else if(str.equals("푸들")){
                    Intent intent = new Intent(helathnote.this, SubActivity_P.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

                else if(str.equals("포메라니안")){
                    Intent intent = new Intent(helathnote.this, SubActivity_PR.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

                else if(str.equals("치와와")){
                    Intent intent = new Intent(helathnote.this, SubActivity_CH.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

                else if(str.equals("시츄")){
                    Intent intent = new Intent(helathnote.this, SubActivity_ST.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

                else if(str.equals("골든리트리버")){
                    Intent intent = new Intent(helathnote.this, SubActivity_GR.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

                else if(str.equals("진돗개")){
                    Intent intent = new Intent(helathnote.this, SubActivity_K.class);
                    intent.putExtra("str",str);
                    startActivity(intent); // 액티비티 이동
                }

            }

        });
    }
}