package com.example.petregisterapplication;

/**
 *  사용자 계정 정보 모델 클래스
 */
public class UserAccount {

    private String idToken;
    private String emailId;
    private String password;
    private String rpwd;
    private String username;
    private String petname;

    public UserAccount(){ }//firebase를 사용을 위한 기본 생성자

    public String getIdToken() {
        return idToken;
    }//firebase Uid(고유정보)

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRpwd() {return rpwd;}

    public void setRpwd(String rpwd) {this.rpwd = rpwd;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPetname() {return petname;}

    public void setPetname(String petname) {this.petname = petname;}

}
