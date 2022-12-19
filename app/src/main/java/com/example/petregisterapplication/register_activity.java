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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_activity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth; //firebase 인증 정보 저장하는 멤버 객체
    private DatabaseReference mDatabaseReference; //실시간 database 저장하는 멤버 객체
    private EditText mEtEmail, mEtPwd, mEtRpwd,mEtUsername, mEtPetname; // 회원가입 입력필드
    private Button mBtnRegister,mBtn_registlogin; // 가입완료, 로그인 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //firebase 초기화
        mFirebaseAuth = FirebaseAuth.getInstance();
        //database 초기화
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("petregisterapplication");

        //회원가입 입력필드 초기화
        mEtEmail = findViewById(R.id.et_email); //이메일
        mEtPwd = findViewById(R.id.et_pwd);  //비밀번호
        mEtRpwd = findViewById(R.id.et_rpwd); //비밀번호 재확인
        mEtUsername = findViewById(R.id.et_username); //사용자 이름
        mEtPetname = findViewById(R.id.et_petname); //반려견 이름
        mBtnRegister = findViewById(R.id.btn_register); // 가입완료 버튼

        mBtn_registlogin = findViewById(R.id.btn_registlogin);//회원가입 화면의 로그인 버튼
        mBtn_registlogin.setOnClickListener(new View.OnClickListener() { //로그인 화면으로 이동
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register_activity.this, login_activity.class);
                startActivity(intent);
            }
        });

        mBtnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 회원가입 처리 시작
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();
                String strRpwd = mEtRpwd.getText().toString();
                String strUsername = mEtUsername.getText().toString();
                String strPetname = mEtPetname.getText().toString();

                if (strPwd.equals(strRpwd)){ //비밀번호와 비밀번호 재확인 일치여부 확인

                    //firebase Auth 진행(인증처리 절차 진행)
                    //firebase에서 email,password를 이용해서 사용자를 생성한다.
                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(register_activity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) { //회원가입 성공
                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); //회원가입된 사용자를 가져온다.
                                UserAccount account = new UserAccount(); //사용자 계정 정보 모델 클래스
                                account.setIdToken(firebaseUser.getUid()); // firebase Uid(고유 정보)
                                account.setEmailId(firebaseUser.getEmail()); //로그인된 email을 가져온다.
                                account.setPassword(strPwd);
                                account.setUsername(strUsername);
                                account.setPetname(strPetname);

                                //setValue: database에 삽입하기
                                mDatabaseReference.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
                                Toast.makeText(register_activity.this, "회원가입에 성공하셨습니다", Toast.LENGTH_SHORT).show();

                              //회원가입 실패
                            } else {Toast.makeText(register_activity.this, "회원가입에 실패하셨습니다", Toast.LENGTH_SHORT).show();}
                        }
                    });

                  //비밀번호와 비밀번호 재확인 부분 불일치
                } else{Toast.makeText(register_activity.this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();}
            }
        });
    }
}