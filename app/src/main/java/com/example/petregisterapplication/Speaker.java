package com.example.petregisterapplication;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Bundle;
import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Speaker extends AppCompatActivity {
    String TAG = "Speaker";
    UUID BT_MODULE_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    TextView textStatus;
    Button btnParied;
    Button btnSend1,btnSend2,btnSend3,btn_opinion;
    ListView listView;    //페어링 된 기기의 목록을 받아오는 listview

    BluetoothAdapter btAdapter;     //블루투스 활성화
    Set<BluetoothDevice> pairedDevices;
    ArrayAdapter<String> btArrayAdapter;
    ArrayList<String> deviceAddressArray;

    private final static int REQUEST_ENABLE_BT = 1;  //요청이 오면 connected Thread 불러온다.
    BluetoothSocket btSocket = null;
    ConnectedThread connectedThread;


    @SuppressLint({"MissingPermission", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);


            String[] permission_list =     //permission 받아온다.
                {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                };
        ActivityCompat.requestPermissions(Speaker.this, permission_list, 1);

        btAdapter = BluetoothAdapter.getDefaultAdapter();  //블루투스 버튼(item)을 누르면 블루투스 연결
        if (!btAdapter.isEnabled()) {   //연결이 되면
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);   //버튼을 활성화 한다.
        }
        //변수선언
        textStatus = (TextView) findViewById(R.id.text_status);
        btnParied = (Button) findViewById(R.id.btn_paired);
        btnSend1 = (Button) findViewById(R.id.btn_send1);
        btnSend2 = (Button) findViewById(R.id.btn_send3);
        btnSend3 = (Button) findViewById(R.id.btn_send2);
        btn_opinion=(Button) findViewById(R.id.opinion);
        listView = (ListView) findViewById(R.id.listview);


        //페어링된 기기들을 보여준다.
        btArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        deviceAddressArray = new ArrayList<>();
        listView.setAdapter(btArrayAdapter);
        listView.setOnItemClickListener(new myOnItemClickListener());
    }


    @SuppressLint("MissingPermission")
    public void onClickButtonPaired(View view){
        btArrayAdapter.clear();
        if(deviceAddressArray!=null && !deviceAddressArray.isEmpty()){ deviceAddressArray.clear(); }
        pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            //블루투스 버튼을 누른 후, 페어링된 기기가 있다면 각 기기의 주소와 이름을 가지고 온다.
            for (BluetoothDevice device : pairedDevices) {
                String deviceName = device.getName();  //device name에 저장.
                String deviceHardwareAddress = device.getAddress();   //address 저장.
                btArrayAdapter.add(deviceName);   //list에서 device name을 추가한다.
                deviceAddressArray.add(deviceHardwareAddress);
            }
        }
    }
    //문자열을 보낸다.
    public void onClickButtonSend1(View view){
        if(connectedThread!=null){connectedThread.write("1");}  //null값이 아니고 send 1 버튼을 누르면, 1의 값을 보낸다.
    }
    public void onClickButtonSend2(View view){
        if(connectedThread!=null){connectedThread.write("2");}  //null값이 아니고 send 2 버튼을 누르면, 2의 값을 보낸다.
    }
    public void onClickButtonSend3(View view){
        if(connectedThread!=null){connectedThread.write("3");}  //null값이 아니고 send 3 버튼을 누르면, 3의 값을 보낸다.
    }

    public void onClickButtonopinion(View view)    //의견 보내기 버튼을 누르면
    {
        Intent intent = new Intent(Speaker.this, memoInsert.class); //메모 화면으로 이동
        startActivity(intent);
    }


        public class myOnItemClickListener implements AdapterView.OnItemClickListener  //list 목록에 출력된 item 누르면 블루투스 연결시작
    {

        @SuppressLint("MissingPermission")
        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), btArrayAdapter.getItem(position), Toast.LENGTH_SHORT).show();
            textStatus.setText("try...");

            final String name = btArrayAdapter.getItem(position);       //name을 불러온다.
            final String address = deviceAddressArray.get(position);   //주소를 불러온다.
            boolean flag = true;   //flag default 값은 true로 설정

            BluetoothDevice device = btAdapter.getRemoteDevice(address);

            //소켓을 생성하고, 연결한다.
            try {
                btSocket = createBluetoothSocket(device);
                //버튼 소켓값이 null이 아니면 연결.
                btSocket.connect();
            } catch (IOException e) {
                flag = false;   //플래그 값이 false일때
                textStatus.setText("connection failed!");  //연결 실패
                e.printStackTrace();
            }
            //블루투스 통신 시작
            if (flag) {
                textStatus.setText("connected to " + name);
                connectedThread = new ConnectedThread(btSocket);  //버튼 소켓 활성화
                connectedThread.start();   //connectedThread 연결시작

            }
        }
    }

    @SuppressLint("MissingPermission")
    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException {
        try {
            final Method m = device.getClass().getMethod("createInsecureRfcommSocketToServiceRecord", UUID.class);
            return (BluetoothSocket) m.invoke(device, BT_MODULE_UUID);
        } catch (Exception e) {
            Log.e(TAG, "Could not create Insecure RFComm Connection",e);
        }
        return  device.createRfcommSocketToServiceRecord(BT_MODULE_UUID);
    }
}