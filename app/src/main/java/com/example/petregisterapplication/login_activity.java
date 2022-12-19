package com.example.petregisterapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class login_activity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //firebase 인증 정보 저장하는 멤버 객체
    private DatabaseReference mDatabaseReference; //실시간 database 저장하는 멤버 객체
    private EditText mEtEmail, mEtPwd, mEtUsername, mEtPetname; // 회원가입 입력필드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //firebase 초기화
        mFirebaseAuth = FirebaseAuth.getInstance();
        //database 초기화
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("petregisterapplication");

        //회원가입 입력필드 초기화
        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        mEtUsername = findViewById(R.id.et_username);
        mEtPetname = findViewById(R.id.et_petname);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() //로그인 하기
        {
            @Override
            public void onClick(View view) { //로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strUsername = mEtUsername.getText().toString();
                String strPetname = mEtPetname.getText().toString();

                //signInWithEmailAndPassword: firebase에 있는 email, password 일치여부 확인
                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { //로그인 성공
                            Intent intent = new Intent(login_activity.this,MainActivity.class); //로그인 성공시 메인화면으로 이동
                            startActivity(intent);
                        } else{ // 로그인 실패
                            Toast.makeText(login_activity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
               });
            }
        });

        Button btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() { //회원가입 화면으로 이등
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}