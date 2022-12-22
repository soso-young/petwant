package com.example.petregisterapplication;
import android.bluetooth.BluetoothSocket;
import java.io.IOException;
import java.io.OutputStream;

public class ConnectedThread extends Thread {
    private final BluetoothSocket mmSocket;   //블루투스 소켓 선언
    private final OutputStream mmOutStream;      //OutputStream 클래스 선언

    public ConnectedThread(BluetoothSocket socket) {
        mmSocket = socket;
        OutputStream tmpOut = null;
        try
        {
            tmpOut = socket.getOutputStream();    //tmpOut에 write()메소드 소켓 가지고온다.
        }
        catch (IOException e) {   //예외처리
        }

        mmOutStream = tmpOut;   //mmOutStreamp에 tmpOut값 저장.
    }
    public void write(String input) {
        byte[] bytes = input.getBytes();      //string문자열을 byte로 변환
        try {
            mmOutStream.write(bytes);     //byte 값을 write 한다.
        }
        catch (IOException e) {
        }
    }
}