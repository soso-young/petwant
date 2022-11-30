package com.example.petregisterapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

class NotificationMain extends ContextWrapper
{

    public static final String channelId = "channel1Id";
    public static final String channel1name = "channel1";
    private NotificationManager manager;

    public NotificationMain(Context base) {
        super(base);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            creatChannels();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void creatChannels(){

        NotificationChannel channel1 = new NotificationChannel(channelId, channel1name, NotificationManager.IMPORTANCE_DEFAULT);
        channel1.enableLights(false);
        channel1.enableVibration(false);
        channel1.setLightColor(com.google.android.material.R.color.design_default_color_primary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(channel1);

    }


    public NotificationManager getManager()
    {
        if(manager == null)
        {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;

    }

    public NotificationCompat.Builder getChannel1Notification(String title, String message)
    {

        //알림에 채널 지정하기
        //생성자에서 채널 ID를 넣어줄 수 도 있고 .setChannel()함수를 이용해서 채널 ID를 지정해줄 수도 있다.
        return new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(title) //text->title로 하여,title도 띄우기
                .setContentText(message)//message 띄우기
                .setOngoing(true)   //알림 상당바에 계속 고정하기
                .setSmallIcon(R.drawable.ic_launcher_background);

    }
}
