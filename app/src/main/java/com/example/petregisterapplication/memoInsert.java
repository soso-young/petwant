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
    private FirebaseAuth mFirebaseAuth; //firebase 인증 정보 저장하는 멤버 객체
    private DatabaseReference mDatabaseReference; //실시간 database 저장하는 멤버 객체


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_insert);

        etName = findViewById(R.id.etName);
        btnInsertData = findViewById(R.id.btnInsertData);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("petregisterapplication");
        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudentData();
            }
        });
    }

    private void insertStudentData(){

        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
        String name = etName.getText().toString();
        String id = mDatabaseReference.push().getKey();
        Student Students = new Student(name);
        assert id != null;
        mDatabaseReference.child("Student").child(firebaseUser.getUid()).setValue(Students);
        Toast.makeText(memoInsert.this,"Data inserted!",Toast.LENGTH_SHORT).show();
    }
}