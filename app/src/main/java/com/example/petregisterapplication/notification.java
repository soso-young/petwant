package com.example.petregisterapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class notification extends AppCompatActivity{
    private EditText eTitle, eMessage;
    private Button channel1btn;
    private com.example.petregisterapplication.NotificationMain mNotification;

    //알림 화면 구현
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        eTitle = findViewById(R.id.edit_title);
        eMessage = findViewById(R.id.edit_message);
        channel1btn = findViewById(R.id.btn_channel1);
        mNotification = new com.example.petregisterapplication.NotificationMain(this);

        //알림띄우기 버튼 구현
        channel1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = eTitle.getText().toString();
                String message = eMessage.getText().toString();
                sendonchannel1(title, message);
            }
        });
    }

    //알림 만들기
    public void sendonchannel1(String title, String message){
        NotificationCompat.Builder nb = mNotification.getChannel1Notification(title,message);
        mNotification.getManager().notify(1, nb.build());
    }
}