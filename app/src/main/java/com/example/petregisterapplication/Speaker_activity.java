package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Speaker_activity extends AppCompatActivity {
    private Button btn_speak_send;
    private Button btn_speak_eat,btn_speak_war,btn_speak_love;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker2);
        Button btn_speak_send = findViewById(R.id.btn_speak_send);
        btn_speak_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.4.1"));
                //send 버튼 누르면 insert 할수있는(사용자가 입력하고 싶은 말 스피커로 출력되는) 화면으로 접속
                startActivity(intent);
            }
        });

        Button btn_speak_eat = findViewById(R.id.btn_speak_eat);
        btn_speak_eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.4.1/speaker1"));
                //밥먹어 버튼 클릭 시 '밥먹어'라는 스피커가 음성으로 출력되는 사이트로 접속
                startActivity(intent);
            }
        });

        Button btn_speak_war = findViewById(R.id.btn_speak_war);
        btn_speak_war.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.4.1/speaker2"));
                //멍멍 버튼 클릭 시 '멍멍'이라는 스피커가 음성으로 출력되는 사이트로 접속
                startActivity(intent);
            }
        });

        Button btn_speak_love = findViewById(R.id.btn_speak_love);
        btn_speak_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.4.1/speaker3"));
                //사랑해 버튼 클릭 시 '사랑해'라는 스피커가 음성으로 출력되는 사이트로 접속
                startActivity(intent);
            }
        });

    }

}


