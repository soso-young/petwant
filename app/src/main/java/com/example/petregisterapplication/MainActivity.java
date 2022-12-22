package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; // firebase 인증처리
    private EditText eTitle;
    private EditText eMessage;
    private Button channel1btn,btn_opinion;
    private notification mNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_logout = findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() { //로그아웃 하기
            @Override
            public void onClick(View view) {
                mFirebaseAuth.signOut();
                Intent intent = new Intent(MainActivity.this, login_activity.class); //로그인 화면으로 이동
                startActivity(intent);
            }
        });

        Button btn_speak = findViewById(R.id.btn_speak);
        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,Speaker_activity.class);
                //스피커 버튼 클릭 시 스피커 화면으로 접속
                startActivity(intent);
            }
        });

        Button btn_cam = findViewById(R.id.btn_cam);
        btn_cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.20.10.3"));
                //카메라 버튼 클릭 시 영상주소로 접속
                startActivity(intent);
            }
        });

        Button btn_note = findViewById(R.id.btn_note);
        btn_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, helathnote.class);  //건강수첩화면으로 이동
                startActivity(intent);
            }
        });

        Button btn_notification= findViewById(R.id.btn_notification);
        btn_notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, notification.class); //알림화면으로 이동
                startActivity(intent);
            }
        });

        btn_opinion=(Button) findViewById(R.id.opinion);
        btn_opinion.setOnClickListener(new View.OnClickListener(){   //'관리자 문의' 버튼을 누르면
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, memoInsert.class); //관리자 문의(memoinsert)화면으로 이동
                startActivity(intent);
            }
        });
    }
}
