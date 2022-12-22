package com.example.petregisterapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class memoInsert extends AppCompatActivity {

    EditText etName;
    Button btnInsertData;
    private DatabaseReference mDatabaseReference; //실시간 database 저장하는 멤버 객체
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_insert);

        etName = findViewById(R.id.etName);       //'불편사항을 입력해주세요' 라는 edit text를 etName으로 선언
        btnInsertData = findViewById(R.id.btnInsertData);    //'관리자에게 전달'이라는 버튼을 insert data으로 선언

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("complain_request");
        //petwant팀 firebase에 complain_request라는 부분에 저장
        btnInsertData.setOnClickListener(new View.OnClickListener() {    //'관리자에게 전달'이라는 버튼을 누르면
            @Override
            public void onClick(View view) {
                insertStudentData();     //insert Student data 함수가 호출
            }
        });
    }

    private void insertStudentData(){

        String content = etName.getText().toString();     //사용자가 입력한 불편사항의 내용을 content로 firebase에 저장.
        String id= mDatabaseReference.push().getKey();
        Student Students = new Student(content);
        assert id != null;
        mDatabaseReference.child(id).setValue(Students);
        Toast.makeText(memoInsert.this,"관리자에게 전달되었습니다.",Toast.LENGTH_SHORT).show();
        //'관리자에게 전달되었다'는 메시지가 출력(pet want팀은 firebase에서 사용자 요청사항 확인 가능)->의사소통의 효과
    }
}